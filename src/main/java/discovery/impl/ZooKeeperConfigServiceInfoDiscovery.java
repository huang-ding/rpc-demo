package discovery.impl;

import com.alibaba.fastjson.JSON;
import discovery.ServiceDiscovery;
import discovery.ServiceInfo;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.I0Itec.zkclient.ZkClient;
import util.MyZkSerializer;
import util.PropertiesUtils;

/**
 * 使用zk存储过程服务信息
 *
 * @author huangding
 * @date 2020/5/15 21:24
 */
public class ZooKeeperConfigServiceInfoDiscovery implements ServiceDiscovery {

    private ZkClient client;

    private final String rootPath = "/rpc";

    public ZooKeeperConfigServiceInfoDiscovery() {
        ZkClient zkClient = new ZkClient(PropertiesUtils.getProperties("zk.address"));
        zkClient.setZkSerializer(new MyZkSerializer());
        client = zkClient;
    }

    @Override
    public List<ServiceInfo> getServiceInfo(String name) {
        //获取所有该服务的子节点
        String path = rootPath + "/" + name + "/service";
        List<String> children = client.getChildren(path);
        if (children == null) {
            return null;
        }
        List<ServiceInfo> serviceInfoList = new ArrayList<ServiceInfo>(children.size());
        for (String child : children) {
            try {
                String decode = URLDecoder.decode(child, "utf-8");
                serviceInfoList.add(JSON.parseObject(decode, ServiceInfo.class));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return serviceInfoList;
    }
}
