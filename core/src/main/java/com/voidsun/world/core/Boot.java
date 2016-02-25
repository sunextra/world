package com.voidsun.world.core;

/**
 * Created by voidsun on 16/1/20.
 */
public class Boot {

    static int port = 4600;

    public static void main(String[] args) {
        if(args != null)setArgs(args);
        Server server = new SocketServer(port);
        server.start();
    }

    static void setArgs(String[] args){
        for(int i=0; i<args.length; i++){
            String[] argValue = args[i].split("=");
            if("--world.port".equals(argValue[0])){
                port = Integer.parseInt(argValue[1]);
            }
        }
    }
}
