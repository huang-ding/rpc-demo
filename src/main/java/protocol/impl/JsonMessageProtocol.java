package protocol.impl;

import protocol.MessageProtocol;
import protocol.Request;
import protocol.Response;

/**
 * json 序列化方式实现协议，这个需要请求和响应实现序列化接口
 *
 * @author huangding
 * @date 2020/5/15 21:34
 */
public class JsonMessageProtocol implements MessageProtocol {

    @Override
    public byte[] marshallingRequest(Request request) throws Exception {
        return new byte[0];
    }

    @Override
    public Request unMarshallingRequest(byte[] bytes) throws Exception {
        return null;
    }

    @Override
    public byte[] marshallingResponse(Response response) throws Exception {
        return new byte[0];
    }

    @Override
    public Response unMarshallingResponse(byte[] bytes) throws Exception {
        return null;
    }
}
