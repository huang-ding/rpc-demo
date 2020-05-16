package client;

import discovery.ServiceDiscovery;
import discovery.ServiceInfo;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.NetClient;
import protocol.MessageProtocol;
import protocol.Request;
import protocol.Response;

/**
 * 客户端stub工厂
 *
 * @author huangding
 * @date 2020/5/15 20:19
 */
public class ClientStubProxyFactory {


    /**
     * 消息协议编解组实现
     */
    private Map<String, MessageProtocol> supportMessageProtocols;

    /**
     * 服务信息
     */
    private ServiceDiscovery serviceDiscovery;

    /**
     * 对象缓存
     */
    private Map<Class<?>, Object> objectCache = new HashMap<Class<?>, Object>();


    /**
     * 请求发送方式 网络
     */
    private NetClient netClient;

    /**
     * 获取代理
     *
     * @param interf 代理过程服务
     * @param <T> 类型
     * @return 返回代理
     */
    public <T> T getProxy(Class<T> interf) {
        T obj = (T) this.objectCache.get(interf);
        if (obj == null) {
            obj = (T) Proxy.newProxyInstance(interf.getClassLoader(), new Class<?>[] { interf },
                new ClientStubInvocationHandler(interf));
            this.objectCache.put(interf, obj);
        }

        return obj;
    }


    /**
     * 设置服务信息实现方式
     */
    void setServiceInfoDiscovery(ServiceDiscovery serviceInfoDiscovery) {

    }

    public Map<String, MessageProtocol> getSupportMessageProtocols() {
        return supportMessageProtocols;
    }

    public void setSupportMessageProtocols(
        Map<String, MessageProtocol> supportMessageProtocols) {
        this.supportMessageProtocols = supportMessageProtocols;
    }

    public ServiceDiscovery getServiceDiscovery() {
        return serviceDiscovery;
    }

    public void setServiceDiscovery(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    public NetClient getNetClient() {
        return netClient;
    }

    public void setNetClient(NetClient netClient) {
        this.netClient = netClient;
    }

    class ClientStubInvocationHandler<T> implements InvocationHandler {

        private Class<T> target;
        /**
         * 当多个的时候进行数据处理
         */
        private Random random = new Random();

        /**
         * 忽略的一些方法
         */
        private String[] ignores = {"toString", "hashCode", "equals"};

        public ClientStubInvocationHandler(Class<T> target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //1.过滤一些无用的接口调用
            String methodName = method.getName();
            List<String> list = Arrays.asList(ignores);
            if (list.contains(methodName)) {
                throw new UnsupportedOperationException("这些操作就不实现了");
            }

            //2.获取服务信息，服务名称=接口名（进行约定）服务发现
            String serviceName = this.target.getName();
            List<ServiceInfo> serviceInfos = serviceDiscovery.getServiceInfo(serviceName);
            if (null == serviceInfos || serviceInfos.size() == 0) {
                throw new NullPointerException("无对应服务实现");
            }
            //3.选择对应的服务提供者(软负载均衡)
            ServiceInfo serviceInfo = serviceInfos.get(random.nextInt(serviceInfos.size()));
            //4.构建请求对象
            Request request = new Request();
            request.setServiceName(serviceName);
            request.setMethod(method.getName());
            request.setParameterTypes(method.getParameterTypes());
            request.setParameters(args);
            //5.协议编组，获取该服务的协议实现，
            MessageProtocol messageProtocol = supportMessageProtocols
                .get(serviceInfo.getProtocol());
            byte[] requestData = messageProtocol.marshallingRequest(request);
            //6.发送请求
            byte[] responseData = netClient.sendRequest(requestData, serviceInfo);
            //7.获得响应信息，进行响应解组
            Response response = messageProtocol.unMarshallingResponse(responseData);
            //8.对结果进行解析，处理
            if (null != response.getException()) {
                throw response.getException();
            }
            return response.getReturnValues();
        }

    }
}
