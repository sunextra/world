package com.voidsun.world.core;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by voidsun on 16/2/22.
 */
public class SocketServer extends Server {
    int count = 0;
    String response =
            "HTTP/1.1 200 OK\n" +
            "\n" +
            "Hello, World!" + count++;

    public SocketServer(int port) {
        super(port);
    }

    @Override
    void start() {
        Socket socket;
        ServerSocket server;
        try {
            server = new ServerSocket(getPort());
            socket = server.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            int len;
            while(true) {
                while ((len = inputStream.read()) != -1) {
                    byte[] buffer = new byte[len];
                    inputStream.read(buffer);
                    System.out.println(new String(buffer, "UTF-8"));
                    BufferedOutputStream writer = new BufferedOutputStream(outputStream);
                    writer.write(response.getBytes());
                    writer.flush();
                    writer.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
