package WebSocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/*
   TextWebSocketFrame:在netty中，专门为websocket处理文本的对象，frame是消息的载体
 */
public class WSServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
	String text = msg.text();
	for (Channel channel :
		clients) {
	    //消息载体Frame
	    channel.writeAndFlush(new TextWebSocketFrame(LocalDateTime.now()+"[服务器接收到消息："+text+"]\n"));
	}
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
	//当客户端打开连接之后，获取到新的客户的channel放入channelgroup进行管理
	clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //当触发handlerRemoved，channelGroup会自动移除客户端的channel
//        clients.remove(ctx.channel());
	System.out.println(ctx.channel().id().asLongText());
    }
}
