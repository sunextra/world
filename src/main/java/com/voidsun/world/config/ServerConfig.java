package com.voidsun.world.config;

/**
 * Created by voidsun on 16/4/24.
 */
public class ServerConfig {
    private int port;
    private App app;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    static class App{
        private String path;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
