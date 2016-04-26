package com.voidsun.world.server.handler;

import com.voidsun.world.server.ServerComponent;

/**
 * Created by voidsun on 16/4/26.
 */
public abstract class Handler implements Runnable, ServerComponent{

    @Override
    public void run() {
        doStart();
    }
}
