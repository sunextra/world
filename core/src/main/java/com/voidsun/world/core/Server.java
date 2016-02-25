package com.voidsun.world.core;

/**
 * Created by voidsun on 16/1/20.
 */
public abstract class Server {
    final private int port;
    abstract protected void preStart();
    abstract protected void postStart();
    abstract protected void doStart();
    void start(){
        preStart();
        doStart();
        postStart();
    }
    protected int getPort(){
        return this.port;
    }
    public Server(int port){
        this.port = port;
    }
}
