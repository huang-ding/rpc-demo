package server1;

/**
 * 服务对象
 * @author huangding
 * @date 2020/5/16 16:07
 */
public class ServiceObject {

    /**
     * 服务名称
     */
    private String serverName;

    /**
     * 服务接口
     */
    private Class<?> interf;

    /**
     * 服务提供对象
     */
    private Object obj;

    public ServiceObject(String serverName, Class<?> interf, Object obj) {
        this.serverName = serverName;
        this.interf = interf;
        this.obj = obj;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Class<?> getInterf() {
        return interf;
    }

    public void setInterf(Class<?> interf) {
        this.interf = interf;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
