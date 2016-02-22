package com.voidsun.world.core;

/**
 * Created by voidsun on 16/1/20.
 */
public abstract class Server {
    final private int port;
    abstract void start();
    protected int getPort(){
        return this.port;
    }
    public Server(int port){
        this.port = port;
    }
}
