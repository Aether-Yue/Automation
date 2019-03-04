package com.automation.service;

import com.automation.until.ReadTestProperties;

/**
*设置driver环境变量信息
 */
public class SetPath {

    public static void setpath(String pjname) {
        ReadTestProperties readconf = new ReadTestProperties();
        readconf.setProject_name(pjname);

        String driver_path = readconf.readTestProperties("driver_path");
        String driver_name = readconf.readTestProperties("driver_name");
        // 判断系统
        String os = System.getProperties().getProperty("os.name");
        if (os != null && os.toLowerCase().indexOf("linux") > -1) {
            driver_path = readconf.readTestProperties("driver_path_linux");
        }

        System.setProperty(driver_name, driver_path);
    }
}
