package server1.impl;

import com.alibaba.fastjson.JSON;
import discovery.ServiceInfo;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import org.I0Itec.zkclient.ZkClient;
import server1.ServiceObject;
import server1.ServiceRegister;
import util.MyZkSerializer;
import util.PropertiesUtils;

/**
 * @author huangding
 * @date 2020/5/16 16:12
 */
public class ZkExportServiceRegister extends DefaultServiceRegister implements ServiceRegister {

    private ZkClient client;

    private final String rootPath = "/rpc";

    public ZkExportServiceRegister() {
        ZkClient zkClient = new ZkClient(PropertiesUtils.getProperties("zk.address"));
        zkClient.setZkSerializer(new MyZkSerializer());
        client = zkClient;
    }

    @Override
    public void register(ServiceObject serviceObject, String protocol, int port) throws Exception {
        //注册
        super.register(serviceObject, protocol, port);
        //暴露 设置serverInfo信息
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setName(serviceObject.getServerName());
        serviceInfo.setProtocol(protocol);
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        serviceInfo.setAddress(hostAddress + ":" + port);
        exportService(serviceInfo);
    }

    private void exportService(ServiceInfo serviceInfo) {
        String json = JSON.toJSONString(serviceInfo);
        String uri = null;
        try {
            //防止乱码 不支持
            uri = URLEncoder.encode(json, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String path = rootPath + "/" + serviceInfo.getName() + "/service";
        if (!client.exists(path)) {
            client.createPersistent(path, true);
        }
        //判断当前数据是否存在
        String serverPath = path + "/" + uri;
        if (client.exists(serverPath)) {
            client.delete(serverPath);
        }
        //创建临时节点
        client.createEphemeral(serverPath);


    }

}
