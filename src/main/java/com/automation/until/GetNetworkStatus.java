package com.automation.until;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 判断网络是否通过
 */
public class GetNetworkStatus {
    public Boolean netstatus;
    public Boolean getNetworkStatus(String ip,String port){
        try{
            URL url = new URL(String.format("http://%s:%s", ip, port));
            InputStream in = url.openStream();
            in.close();
            netstatus=true;
        } catch (IOException e) {
            netstatus=false;
        }
        return netstatus;
    }
}
