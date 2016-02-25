package com.voidsun.world.core;

import com.voidsun.world.webapp.WorldXmlConfiguration;
import com.voidsun.world.webapp.XmlConfiguration;
import com.voidsun.world.std.CommonUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by voidsun on 16/2/22.
 */
public class SocketServer extends Server {
    private final List<XmlConfiguration> configList = new ArrayList<>();


    static AtomicInteger count = new AtomicInteger();
    static String response =
            "HTTP/1.1 200 OK\r\n" +
            "\r\n" +
            "Hello, World!";

    public SocketServer(int port) {
        super(port);
    }

    @Override
    protected void preStart() {
        configList.add(WorldXmlConfiguration.instance());
        configList.stream().forEach(XmlConfiguration::load);
    }

    @Override
    protected void postStart() {

    }

    @Override
    protected void doStart() {
        Socket socket;
        ServerSocket server;
        ExecutorService service = Executors.newFixedThreadPool(10);
        try {
            server = new ServerSocket(getPort());
            while(true) {
                socket = server.accept();
                service.execute(new SocketHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class SocketHandler implements Runnable{
        final private Socket socket;

        public SocketHandler(Socket socket){
            this.socket = socket;
        }

        public void run() {
                BufferedReader reader = null;
                BufferedWriter writer = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    String s;
                    while ((s = reader.readLine()) != null) {
                        if(s.isEmpty())break;
                        System.out.println(s);
                    }
                    writer.write(response+count.getAndIncrement()+ Const.EOF);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    CommonUtil.silenceClose(writer);
                    CommonUtil.silenceClose(reader);
                    CommonUtil.silenceClose(socket);
                }
        }
    }
}
