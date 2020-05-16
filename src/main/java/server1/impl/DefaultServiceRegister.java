package server1.impl;

import java.util.HashMap;
import java.util.Map;
import server1.ServiceObject;
import server1.ServiceRegister;

/**
 * 默认实现
 *
 * @author huangding
 * @date 2020/5/16 16:11
 */
public class DefaultServiceRegister implements ServiceRegister {

    private Map<String, ServiceObject> serviceObjectMap = new HashMap<>();

    @Override
    public void register(ServiceObject serviceObject, String protocol, int port) throws Exception {
        if (serviceObject == null) {
            throw new IllegalArgumentException("参数错误");
        }
        serviceObjectMap.put(serviceObject.getServerName(), serviceObject);
    }

    @Override
    public ServiceObject getServiceObject(String serverName) {
        return serviceObjectMap.get(serverName);
    }
}
