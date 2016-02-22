package com.voidsun.world.core;

/**
 * Created by voidsun on 16/1/20.
 */
public class Boot {

    static int port = 4600;

    public static void main(String[] args) {
        if(args != null)setArgs(args);
        new SocketServer(port).start();
    }

    static void setArgs(String[] args){
        for(int i=0; i<args.length; i++){
            if("--world.port".equals(args[i])){
                port = Integer.parseInt(args[i]);
            }
        }
    }
}
