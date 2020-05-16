package net.impl;

import discovery.ServiceInfo;
import net.NetClient;

/**
 * mina框架实现
 * <a>https://blog.csdn.net/qq_34820803/article/details/87892290</a>
 * @author huangding
 * @date 2020/5/15 22:22
 */
public class MinaNetClient implements NetClient {

    @Override
    public byte[] sendRequest(byte[] data, ServiceInfo serviceInfo) {
        return new byte[0];
    }
}
