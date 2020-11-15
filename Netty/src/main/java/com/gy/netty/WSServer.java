package com.gy.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

@Component
public class WSServer {

    private static class SingletonWSServer{
        static final WSServer instance = new WSServer();
    }

    public static WSServer getInstance(){

        return SingletonWSServer.instance;

    }

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap serverBootstrap;
    private ChannelFuture channelFuture;

    public WSServer(){
	bossGroup = new NioEventLoopGroup();
	workerGroup = new NioEventLoopGroup();
	serverBootstrap = new ServerBootstrap();
	serverBootstrap.group(bossGroup, workerGroup)
		.channel(NioServerSocketChannel.class)
		.childHandler(new WSServerInitializer());
    }

    public void start()  {
        this.channelFuture = this.serverBootstrap.bind(8889);
	System.err.println("netty  websocket server 启动完毕");
    }


}
