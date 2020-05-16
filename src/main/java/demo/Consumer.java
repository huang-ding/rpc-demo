package demo;

import client.ClientStubProxyFactory;
import com.alibaba.fastjson.JSON;
import discovery.impl.ZooKeeperConfigServiceInfoDiscovery;
import java.util.HashMap;
import java.util.Map;
import net.impl.NettyNetClient;
import protocol.MessageProtocol;
import protocol.impl.JavaSerializeMessageProtocol;

/**
 * @author huangding
 * @date 2020/5/16 14:34
 */
public class Consumer {

    public static void main(String[] args) {
        ClientStubProxyFactory clientStubProxyFactory = new ClientStubProxyFactory();
        // 设置服务发现
        clientStubProxyFactory.setServiceDiscovery(new ZooKeeperConfigServiceInfoDiscovery());
        //设置编组协议
        Map<String, MessageProtocol> supportMessageProtocols = new HashMap<>();
        supportMessageProtocols.put("javas", new JavaSerializeMessageProtocol());
        clientStubProxyFactory.setSupportMessageProtocols(supportMessageProtocols);
        //设置网络方式
        clientStubProxyFactory.setNetClient(new NettyNetClient());

        //获取代理服务
        DemoService proxy = clientStubProxyFactory.getProxy(DemoService.class);
        String world = proxy.sayHello("World");
        System.out.println(world);
        //由于是java序列化方式 对象必须实现Serializable序列化接口
        User userInfo = proxy.getUserInfo(1);
        System.out.println(JSON.toJSONString(userInfo));

    }
}
