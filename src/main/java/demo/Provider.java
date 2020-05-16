package demo;

import demo.impl.DemoServiceImpl;
import protocol.impl.JavaSerializeMessageProtocol;
import server1.RequestHandler;
import server1.RpcServer;
import server1.ServiceObject;
import server1.ServiceRegister;
import server1.impl.NettyRpcServer;
import server1.impl.ZkExportServiceRegister;
import util.PropertiesUtils;

/**
 * @author huangding
 * @date 2020/5/16 16:16
 */
public class Provider {

    public static void main(String[] args) throws Exception {
        //端口号
        int port = Integer.parseInt(PropertiesUtils.getProperties("rpc.port"));
        //协议
        String protocol = (PropertiesUtils.getProperties("rpc.protocol"));

        //服务注册
        ServiceRegister serviceRegister = new ZkExportServiceRegister();

        //服务实现者
        DemoService demoService = new DemoServiceImpl();
        ServiceObject serviceObject = new ServiceObject(DemoService.class.getName(),
            DemoService.class, demoService);
        serviceRegister.register(serviceObject, protocol, port);
        //服务过程执行
        RequestHandler requestHandler = new RequestHandler(new JavaSerializeMessageProtocol(),
            serviceRegister);
        //设置rpc server
        RpcServer rpcServer = new NettyRpcServer(port, protocol, requestHandler);
        // 启动
        rpcServer.start();
        //阻塞 按任意键退出
        System.in.read();
        //关闭
        rpcServer.stop();
    }
}
