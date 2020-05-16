package protocol;

/**
 * 消息协议接口
 *
 * @author huangding
 * @date 2020/5/15 21:32
 */
public interface MessageProtocol {

    /**
     * 编组 请求
     *
     * @param request 编组数据
     * @throws Exception 编组出现异常
     * @return 编组结果
     */
    byte[] marshallingRequest(Request request) throws Exception;

    /**
     * 解组 请求
     *
     * @param bytes 接收数据
     * @return 返回解组数据
     * @throws Exception 解组出现异常
     */
    Request unMarshallingRequest(byte[] bytes) throws Exception;


    /**
     * 编组 响应
     *
     * @param response 编组数据
     * @throws Exception 编组出现异常
     * @return 编组结果
     */
    byte[] marshallingResponse(Response response) throws Exception;

    /**
     * 解组 响应
     *
     * @param bytes 接收数据
     * @return 返回解组数据
     * @throws Exception 解组出现异常
     */
    Response unMarshallingResponse(byte[] bytes) throws Exception;
}
