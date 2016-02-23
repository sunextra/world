package com.voidsun.world.std;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by voidsun on 16/2/23.
 */
public class CommonUtil {
    public static void silenceClose(Closeable closeable){
        if(closeable != null)
            try{
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void silenceClose(AutoCloseable autoCloseable){
        if(autoCloseable != null)
            try{
                autoCloseable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
