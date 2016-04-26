package com.voidsun.world.boot;

import com.voidsun.world.config.ConfigLoader;
import com.voidsun.world.server.NioServer;
import com.voidsun.world.server.Server;

/**
 * Created by voidsun on 16/4/26.
 */
public class BootManager {

    public static void main(String[] args) {
        Server server = new NioServer();
        server.register(new ConfigLoader());
        server.start();
    }

}
