package com.automation.until;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import com.automation.model.ReadProject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * TestLink接口初始化
 */
public class ApiObject {
    private static TestLinkAPI api = null;


    public static TestLinkAPI getAPI(String pjname) {
//        String pjname = ReadProject.projectname;
        ReadTestProperties readconf = new ReadTestProperties();
        readconf.setProject_name(pjname);
        if (null == api) {
            String url = readconf.readTestProperties("testlink_url");
            String devKey = readconf.readTestProperties("testlink_key");
            try {
                api = new TestLinkAPI(new URL(url), devKey);
            } catch (TestLinkAPIException te) {
                te.printStackTrace();
            } catch (MalformedURLException mue) {
                mue.printStackTrace();
            }
        }
        return api;
    }
}
