package server1;

/**
 * 服务提供
 *
 * @author huangding
 * @date 2020/5/16 14:55
 */
public abstract class RpcServer {

    protected int port;

    protected String protocol;

    protected RequestHandler handler;

    public RpcServer(int port, String protocol, RequestHandler handler) {
        super();
        this.port = port;
        this.protocol = protocol;
        this.handler = handler;
    }

    /**
     * 开启服务
     */
    public abstract void start();

    /**
     * 停止服务
     */
    public abstract void stop();

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

}
