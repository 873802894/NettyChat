package Netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
	//客户端需要一个事件循环组
	EventLoopGroup group = new NioEventLoopGroup();

	try {
	    //创建客户端启动对象
	    //注意客户端不是ServerBootStrap ，而是BootStrap
	    Bootstrap bootstrap = new Bootstrap();

	    //设置相关参数
	    bootstrap.group(group) //设置线程组
		    .channel(NioSocketChannel.class) //设置客户端通道的实现类（反射）
		    .handler(new ChannelInitializer<SocketChannel>() {
			protected void initChannel(SocketChannel ch) throws Exception {
			    ch.pipeline().addLast(new NettyClientHander());//加入自己的处理器
			}
		    });
	    System.out.println("客户端准备好了");

	    //启动客户端去连接服务器
	    //涉及netty的异步模型
	    ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6668).sync();
	    //给关闭通道进行监听
	    channelFuture.channel().closeFuture().sync();
	}finally {
	    group.shutdownGracefully();
	}
    }
}
