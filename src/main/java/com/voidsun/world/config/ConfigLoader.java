package com.voidsun.world.config;

import com.alibaba.fastjson.JSON;
import com.voidsun.world.Resource;
import com.voidsun.world.ResourceLoader;
import com.voidsun.world.std.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by voidsun on 16/4/24.
 */
public class ConfigLoader implements ResourceLoader {

    static Logger LOGGER = LogManager.getLogger(ConfigLoader.class);

    public void load(Resource resource){
        InputStream inputStream = null;
        ServerConfig serverConfig = null;
        try {
            inputStream = ConfigLoader.class.getResourceAsStream("/world.json");
            int length;
            byte[] buff = new byte[10240000];
            StringBuilder json = new StringBuilder();
            while((length = inputStream.read(buff)) != -1){
                json.append(new String(buff, 0, length));
            }
            LOGGER.trace(json);
            serverConfig = JSON.parseObject(json.toString(), ServerConfig.class);
        } catch (IOException e) {
            LOGGER.error("", e);
        } finally {
            CommonUtil.silenceClose(inputStream);
        }
        resource.setServerConfig(serverConfig);
    }



}
