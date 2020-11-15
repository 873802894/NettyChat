package Conversation;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GroupChatServerHander extends SimpleChannelInboundHandler<String> {

    /*
    	定义一个channel组，管理所有的channel
    	GlobalEventExecutor.INSTANCE是全局的事件执行器，是一个单例
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

     /*
     handlerAdded 表示连接建立，一旦连接，第一个被执行
     将当前channel 加入到 channelGroup
      */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
	Channel channel = ctx.channel();
	//将该客户加入聊天的信息推送给其他在线的客户端
	//该方法会将 channelGroup 中所有的chennel遍历，并发送消息
	channelGroup.writeAndFlush("[客户端]"+ channel.remoteAddress() +"加入聊天\n");
	channelGroup.add(channel);
    }

    /*
    表示channel 处于活跃状态，提示 xx上线
    1.当建立一个新的连接时调用 ChannelActive()
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
	System.out.println(ctx.channel().remoteAddress()+"上线"+sdf.format(new Date())+"\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	System.out.println(ctx.channel().remoteAddress()+"离线");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
	Channel channel = ctx.channel();
	channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"离开了\n");

    }

    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
	//获取到当前channel
	final Channel channel = ctx.channel();

	//这是我们遍历channelGroup，除去自己
	channelGroup.forEach(ch ->{
	    if (channel!=ch){
	        ch.writeAndFlush("[客户]"+channel.remoteAddress()+"发送了消息"+s+"\n");
	    }else {
	        ch.writeAndFlush("[自己]发送了消息"+s+"\n");
	    }
	});
    }

    /*
    	读操作时捕获到异常时调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	ctx.close();
    }
}
