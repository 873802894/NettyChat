package WebSocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WSServerInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
	ChannelPipeline pipeline = ch.pipeline();

		//websocket 基于http协议，所以要有http编解码器
	pipeline.addLast(new HttpServerCodec())
		//大文本写器
		.addLast(new ChunkedWriteHandler())

		//
		.addLast(new HttpObjectAggregator(1024 * 64))

		//websocket服务器处理的协议，用于制定给客户端连接访问的路由：/ws
		//心跳检测
		//对于websocket，都是以frames进行传输的
		.addLast(new WebSocketServerProtocolHandler("/ws"))
		.addLast(new WSServerHandler());

    }
}
