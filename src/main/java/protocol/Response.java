package protocol;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sun.plugin2.message.Serializer;

/**
 * @author huangding
 * @date 2020/5/15 21:42
 */
public class Response implements Serializable {


    private static final long serialVersionUID = 254404244158531754L;

    public Response(Status status) {
        this.status = status;
    }

    public Response() {
    }

    /**
     * 响应状态
     */
    private Status status;

    /**
     * 响应头
     */
    private Map<String, String> headers = new HashMap<String, String>();

    /**
     * 响应参数类型
     * 响应对象需要实现序列化
     */
    private Class<?> returnTypes;

    /**
     * 响应数据
     * 响应对象需要实现序列化
     */
    private Object returnValues;

    /**
     * 错误异常
     */
    private Exception exception;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getHeader(String name) {
        return headers.get(name);
    }


    public void addHeader(String name, String value) {
        this.headers.put(name, value);
    }

    public Class<?> getReturnTypes() {
        return returnTypes;
    }

    public void setReturnTypes(Class<?> returnTypes) {
        this.returnTypes = returnTypes;
    }

    public Object getReturnValues() {
        return returnValues;
    }

    public void setReturnValues(Object returnValues) {
        this.returnValues = returnValues;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
