package helloNetty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/*
   初始化器，channel注册后，会执行里面的相应的初始化方法
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
	//通过SocketChannel获取管道
	ChannelPipeline pipeline = ch.pipeline();

	//HttpRequestDecoder, HttpResponseEncoder
	pipeline.addLast("HttpServerCodec",new HttpServerCodec());

	//添加自定义的handler，返回hello netty（业务处理）
	pipeline.addLast("myChannelHandler",new ServerHandler());
    }
}
