package protocol;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author huangding
 * @date 2020/5/15 21:42
 */
public class Request implements Serializable {


    private static final long serialVersionUID = 7584638574061692319L;
    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 执行方法
     */
    private String method;

    /**
     * 请求头
     */
    private Map<String, String> headers = new HashMap<String, String>();

    /**
     * 请求参数类型
     * 请求参数对象需要实现序列化
     */
    private Class<?>[] parameterTypes;

    /**
     * 请求数据
     * 请求参数对象需要实现序列化
     */
    private Object[] parameters;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    public void addHeader(String name, String value) {
        this.headers.put(name, value);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
