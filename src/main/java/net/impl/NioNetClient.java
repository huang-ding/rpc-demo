package net.impl;

import discovery.ServiceInfo;
import net.NetClient;

/**
 * nio 方式
 * @author huangding
 * @date 2020/5/15 22:19
 */
public class NioNetClient implements NetClient {

    @Override
    public byte[] sendRequest(byte[] data, ServiceInfo serviceInfo) {
        return new byte[0];
    }
}
