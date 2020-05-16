package protocol.impl;

import protocol.MessageProtocol;
import protocol.Request;
import protocol.Response;

/**
 * http 协议实现方式
 * @author huangding
 * @date 2020/5/15 21:34
 */
public class HttpMessageProtocol implements MessageProtocol {

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
