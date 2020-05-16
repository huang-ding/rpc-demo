package protocol.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import protocol.MessageProtocol;
import protocol.Request;
import protocol.Response;

/**
 * java 本地序列化实现协议
 * 参数需要实现序列化接口  Serializable
 * @author huangding
 * @date 2020/5/15 21:37
 */
public class JavaSerializeMessageProtocol implements MessageProtocol {

    private byte[] serialize(Object object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public byte[] marshallingRequest(Request request) throws Exception {

        return serialize(request);
    }

    @Override
    public Request unMarshallingRequest(byte[] bytes) throws Exception {

        ObjectInputStream objectInputStream = new ObjectInputStream(
            new ByteArrayInputStream(bytes));
        return (Request) objectInputStream.readObject();
    }

    @Override
    public byte[] marshallingResponse(Response response) throws Exception {
        return serialize(response);
    }

    @Override
    public Response unMarshallingResponse(byte[] bytes) throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(
            new ByteArrayInputStream(bytes));
        return (Response) objectInputStream.readObject();
    }
}
