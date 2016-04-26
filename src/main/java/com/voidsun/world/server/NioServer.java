package com.voidsun.world.server;

import com.voidsun.world.config.ServerConfig;
import com.voidsun.world.server.handler.NioHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by voidsun on 16/4/26.
 */
public class NioServer extends Server {
    ServerSocketChannel channel;
    Selector selector;

    @Override
    public void doStart() {
        ServerConfig serverConfig = resource.getServerConfig();
        try {
            channel = ServerSocketChannel.open();
            channel.configureBlocking(false);
            selector = Selector.open();
            channel.register(selector, SelectionKey.OP_ACCEPT);
            channel.bind(new InetSocketAddress(serverConfig.getPort()));
            register(new NioHandler(selector));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doStop() {

    }
}
