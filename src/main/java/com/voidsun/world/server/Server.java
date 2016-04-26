package com.voidsun.world.server;

import com.voidsun.world.Resource;
import com.voidsun.world.ResourceLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by voidsun on 16/4/26.
 */
public abstract class Server implements ServerComponent {

    private List<ResourceLoader> resourceLoaders = new ArrayList<>();
    private List<ServerComponent> serverComponents = new ArrayList<>();
    protected Resource resource = new Resource();
    public void register(ResourceLoader resourceLoader){
        resourceLoaders.add(resourceLoader);
    }
    public void register(ServerComponent serverComponent){
        serverComponents.add(serverComponent);
    }

    public void start(){
        int idx = 0;
        while(idx++ < resourceLoaders.size()){
            resourceLoaders.get(idx).load(resource);
        }
        idx = 0;
        while(idx++ < serverComponents.size()){
            serverComponents.get(idx).doStart();
        }
    }

    public void stop(){

        doStop();
    }

}
