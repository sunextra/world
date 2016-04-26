package com.voidsun.world.server.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by voidsun on 16/4/26.
 */
public class NioHandler extends Handler {

    final Selector selector;

    public NioHandler(Selector selector){
        this.selector = selector;
    }

    @Override
    public void doStart() {
        while(true){
            try {
                int select = selector.select();
                if(select > 0){
                    Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                    while(keys.hasNext()) {
                        SelectionKey key = keys.next();
                        keys.remove();
                        if(key.isAcceptable()) accept(key);
                        if(key.isReadable()) read(key);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel)key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        String msg = new String(buffer.array()).trim();
        System.out.println("receive " + msg);
        channel.write(ByteBuffer.wrap(("has received "+msg+"\n").getBytes()));

    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
        SocketChannel channel = serverSocketChannel.accept();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);
        System.out.println("new accept");
        channel.write(ByteBuffer.wrap("hello world!\n".getBytes()));
    }

    @Override
    public void doStop() {

    }
}
