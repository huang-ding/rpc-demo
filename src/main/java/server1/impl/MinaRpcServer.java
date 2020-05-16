package server1.impl;

import server1.RequestHandler;
import server1.RpcServer;

/**
 * 提供mina实现
 *
 * @author huangding
 * @date 2020/5/16 14:59
 */
public class MinaRpcServer extends RpcServer {

    public MinaRpcServer(int port, String protocol, RequestHandler requestHandler) {
        super(port, protocol, requestHandler);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
