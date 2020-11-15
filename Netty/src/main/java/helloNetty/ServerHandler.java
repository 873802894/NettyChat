package helloNetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/*
   入栈
 */
public class ServerHandler extends SimpleChannelInboundHandler<HttpObject>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
	//ctx内容很多，channel很重要
        if (msg instanceof HttpObject){
	    Channel channel = ctx.channel();
	    System.out.println(channel.remoteAddress());

	    //定义发送的数据消息
	    ByteBuf buf = Unpooled.copiedBuffer("<h1>hello netty<h1>", CharsetUtil.UTF_8);
	    //构建一个http response
	    DefaultFullHttpResponse httpResponse =
		    new DefaultFullHttpResponse(
		    		HttpVersion.HTTP_1_1,
			    	HttpResponseStatus.OK,
			    	buf);
	    //为响应增加数据类型和长度
	    httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
	    httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH,buf.readableBytes());

	    ctx.writeAndFlush(httpResponse);
	}
    }
}
