package discovery.impl;

import discovery.ServiceDiscovery;
import discovery.ServiceInfo;
import java.util.List;

/**
 * 本地配置方式获取服务信息
 * @author huangding
 * @date 2020/5/15 21:23
 */
public class LocalConfigServiceInfoDiscovery implements ServiceDiscovery {

    @Override
    public List<ServiceInfo> getServiceInfo(String name) {
        return null;
    }
}
