package Conversation;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class GroupChatClient {

    private final String host;
    private final int port;

    public GroupChatClient(String host, int port) {
	this.host = host;
	this.port = port;
    }

    public void run() throws InterruptedException {
	EventLoopGroup eventExecutors = new NioEventLoopGroup();

	try {
	    Bootstrap bootstrap = new Bootstrap()
		    .group(eventExecutors)
		    .channel(NioSocketChannel.class)
		    .handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
			    ChannelPipeline pipeline = ch.pipeline();

			    pipeline.addLast("decoer",new StringDecoder());
			    pipeline.addLast("encoder",new StringEncoder());
			    pipeline.addLast("My",new GroupChatClientHander());
			}
		    });

	    ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
	    Channel channel = channelFuture.channel();
	    System.out.println("-----"+channel.localAddress()+"-----");

	    Scanner scanner = new Scanner(System.in);
	    while (scanner.hasNextLine()){
		String msg = scanner.nextLine();
		channel.writeAndFlush(msg+"\r\n");
	    }
	    channelFuture.channel().closeFuture().sync();
	}finally {
	    eventExecutors.shutdownGracefully();
	}
    }

    public static void main(String[] args) throws InterruptedException {
	new GroupChatClient("127.0.0.1",7000).run();
    }
}
