package server1;

/**
 * 服务注册
 *
 * @author huangding
 * @date 2020/5/16 16:07
 */
public interface ServiceRegister {

    /**
     * 服务注册
     *
     * @param serviceObject 服务对象
     * @param protocol 服务协议
     * @param port 端口
     */
    void register(ServiceObject serviceObject, String protocol, int port) throws Exception;

    /**
     * 获取服务对象
     *
     * @param serverName 服务名称
     */
    ServiceObject getServiceObject(String serverName);
}
