package cn.edu.nju.software.storymapping.map.netty.handler;

import cn.edu.nju.software.storymapping.map.netty.attributes.Attributes;
import cn.edu.nju.software.storymapping.map.netty.config.NettyConfig;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.*;

import java.util.Date;

public class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        WebSocketServerHandshaker handshaker = ctx.channel().attr(Attributes.HAND_SHAKE).get();
        if (frame instanceof CloseWebSocketFrame)
            handshaker.close(ctx.channel(), ((CloseWebSocketFrame) frame).retain());
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
        }
        if (!(frame instanceof TextWebSocketFrame)) {
            System.out.println("目前我们不支持二进制");
            throw new RuntimeException("【\" + this.getClass().getName() + \"】不支持消息");
        }
        String request = ((TextWebSocketFrame) frame).text();
        System.out.println("服务端收到消息-----》" + request);
        TextWebSocketFrame twx = new TextWebSocketFrame(new Date().toString() + ctx.channel().id() + "=====>" + request);
        NettyConfig.group.writeAndFlush(twx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
