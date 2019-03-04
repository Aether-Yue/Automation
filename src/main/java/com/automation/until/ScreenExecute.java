package com.automation.until;

import com.automation.model.ReadProject;
import com.automation.model.ScreenConfig;
import org.openqa.selenium.WebDriver;

/**
 * 视频执行类
 */
public class ScreenExecute {
    private Boolean isScrren;
    private ScreenRecoding screenRecordTest = null;
    private ScreenConfig config = null;

    public void screenStart(WebDriver driver,String casename,String pjname){
//        String pjname= ReadProject.projectname;
        ReadTestProperties readconf = new ReadTestProperties();
        readconf.setProject_name(pjname);
        isScrren = Boolean.valueOf(readconf.readTestProperties("ScreenRecoding"));
        if (isScrren) {
            screenRecordTest = new ScreenRecoding();
            config = new ScreenConfig();
            config.setCasename(casename);
            config.setRectangle(config.getScreenFull());
            config.setDriver(driver);
            screenRecordTest.setConfig(config);
            screenRecordTest.start();
            System.out.println("------------------ screenRecord start -----------------");
        }
    }

    public void screenStop(String pjname){
//        String pjname= ReadProject.projectname;
        ReadTestProperties readconf = new ReadTestProperties();
        readconf.setProject_name(pjname);
        isScrren = Boolean.valueOf(readconf.readTestProperties("ScreenRecoding"));
        if (isScrren) {
            screenRecordTest.setConfig(config);
            screenRecordTest.stop();
            System.out.println("------------------ screenRecord stop -----------------");
        }
    }
}
