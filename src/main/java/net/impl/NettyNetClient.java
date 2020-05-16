package net.impl;

import discovery.ServiceInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.concurrent.CountDownLatch;
import net.NetClient;

/**
 * netty 方式
 *
 * @author huangding
 * @date 2020/5/15 22:20
 */
public class NettyNetClient implements NetClient {

    @Override
    public byte[] sendRequest(byte[] data, ServiceInfo serviceInfo) {

        String[] addressInfo = serviceInfo.getAddress().split(":");
        //数据处理
        final SendHandler sendHandler = new SendHandler(data);
        byte[] respData = null;
        //配置netty客户端
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(sendHandler);
                    }
                });

            // 启动客户端连接
            bootstrap.connect(addressInfo[0], Integer.valueOf(addressInfo[1])).sync();
            respData = (byte[]) sendHandler.rspData();
            System.out.println("--------------返回数据：" + respData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            group.shutdownGracefully();
        }
        return respData;
    }

    /**
     * 处理数据
     */
    private static class SendHandler extends ChannelInboundHandlerAdapter {

        private byte[] data;

        private Object readMsg = null;

        private CountDownLatch latch;

        SendHandler(byte[] data) {
            latch = new CountDownLatch(1);
            this.data = data;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("链接成功");
            ByteBuf buffer = Unpooled.buffer(data.length);
            buffer.writeBytes(data);
            System.out.println("消息发送");
            ctx.writeAndFlush(buffer);
        }

        public Object rspData() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return readMsg;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("读取返回数据");
            ByteBuf byteBuf = (ByteBuf) msg;
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);
            readMsg = bytes;
            latch.countDown();
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            System.out.println("发生了点问题");
            ctx.close();
        }
    }
}
