package server1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import protocol.MessageProtocol;
import protocol.Request;
import protocol.Response;
import protocol.Status;

/**
 * @author huangding
 * @date 2020/5/16 15:10
 */
public class RequestHandler {

    /**
     * 消息协议
     */
    private final MessageProtocol protocol;

    /**
     * 服务注册
     */
    private final ServiceRegister serviceRegister;


    /**
     * @param data 请求参数
     * @return 响应数据
     */
    public byte[] handlerRequest(byte[] data) throws Exception {
        //解组消息
        Request request = protocol.unMarshallingRequest(data);
        //查询需要执行的过程服务
        ServiceObject serviceObject = serviceRegister.getServiceObject(request.getServiceName());
        Response response = new Response();
        if (null == serviceObject) {
            response.setStatus(Status.NOT_FOUND);
        } else {
            Class<?> interf = serviceObject.getInterf();
            try {
                //使用反射获取到执行过程类
                Method method = interf.getMethod(request.getMethod(), request.getParameterTypes());
                if (null == method) {
                    response.setStatus(Status.NOT_FOUND);
                } else {
                    //执行
                    Object invoke = method.invoke(serviceObject.getObj(), request.getParameters());
                    response.setStatus(Status.SUCCESS);
                    response.setReturnValues(invoke);
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                response.setStatus(Status.ERROR);
                response.setException(e);
            }
        }
        //编组 返回执行对象
        return protocol.marshallingResponse(response);
    }

    public RequestHandler(MessageProtocol protocol, ServiceRegister serviceRegister) {
        this.protocol = protocol;
        this.serviceRegister = serviceRegister;
    }
}
