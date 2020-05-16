package discovery;

import java.util.List;

/**
 * 服务发现接口
 *
 * @author huangding
 * @date 2020/5/15 21:20
 */
public interface ServiceDiscovery {

    /**
     * 获取服务信息
     * @param name 过程服务名称
     * @return 服务信息，可能出现多节点提供服务
     */
    List<ServiceInfo> getServiceInfo(String name);
}
