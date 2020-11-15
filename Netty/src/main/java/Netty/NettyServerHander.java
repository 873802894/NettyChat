package Netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;


//我们自定一个Hander 需要继承netty规定好的某个HandlerApapter
public class NettyServerHander extends ChannelInboundHandlerAdapter{

  /*这里可以读取客户端发送的消息

   1。channelHanderContext ctx：上下文对象，含有 管道pipeline ，通道channel，地址
   2。Object msg：就是客户端发送的数据

   */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	System.out.println("server ctx=" +ctx);
	//将msg转成一个ByteBuf
	//ByteBuf 是netty提供的 ，不是nio 的bytebuffer
	ByteBuf buf = (ByteBuf) msg;
	System.out.println("客户端发送的消息是" +buf.toString(CharsetUtil.UTF_8));
	System.out.println("客户端的地址是"+ctx.channel().remoteAddress());
    }

    //数据读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
	//writeAndFlush
	//将数据写入缓存，并刷新
	//一般的，我们对这个发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端",CharsetUtil.UTF_8));
    }

    //处理异常，一般是关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	ctx.close();
    }
}
