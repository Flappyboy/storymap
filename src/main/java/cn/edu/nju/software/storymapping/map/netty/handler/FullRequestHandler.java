package cn.edu.nju.software.storymapping.map.netty.handler;

import cn.edu.nju.software.storymapping.map.netty.attributes.Attributes;
import cn.edu.nju.software.storymapping.map.netty.config.NettyConfig;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

public class FullRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private WebSocketServerHandshaker handshaker;
    private static final String WEB_SOCKET_URL = "ws://localhost:8888/websocket";


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("当前连接关闭断开");
    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req,
                                  DefaultFullHttpResponse res) {
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        //服务端向客户端发送数据
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (res.getStatus().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        if (!request.getDecoderResult().isSuccess()
                || !("websocket".equals(request.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, request,
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                WEB_SOCKET_URL, null, false);
        handshaker = wsFactory.newHandshaker(request);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), request);
        }
        ctx.channel().attr(Attributes.HAND_SHAKE).set(handshaker);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
