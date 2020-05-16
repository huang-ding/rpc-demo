package net;

import discovery.ServiceInfo;

/**
 * @author huangding
 * @date 2020/5/15 22:13
 */
public interface NetClient {

    /**
     * 发送请求
     *
     * @param data 请求数据，协议层编组之后的数据
     * @param serviceInfo 服务信息
     * @return 响应结果 响应解组之后的数据
     */
    byte[] sendRequest(byte[] data, ServiceInfo serviceInfo) throws Exception;
}
