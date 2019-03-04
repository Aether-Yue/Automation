package com.automation.until;

import com.automation.model.ScreenConfig;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import java.io.File;
import java.io.IOException;

/**
 * 录屏工具类
 */
public class ScreenRecoding {

    ScreenConfig config;
    public Thread thread;
    private long sleepTime;
    private boolean recordSwitch = false;

    public ScreenRecoding() {
        init();
    }

    public void start() {
        recordSwitch = true;

        thread.start();
    }

    public void pause() {
        try {
            thread.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        int width = (int) config.getRectangle().getWidth();
        int height = (int) config.getRectangle().getHeight();
        recordSwitch = false;
        //System.out.println("-------------"+thread.getId()+"--------------");
        PngToAvi.convertPicToAvi(config.basePath + File.separator + config.getCasename(),
                config.basePath + File.separator + config.getCasename() + ".avi",
                config.frame, width, height
        );
    }


    private Integer serialNum = 1000000;

    //public static WebDriver driver;
    public void takeScreenShot() throws IOException {
        WebDriver driver = config.getDriver();
//        SimpleDateFormat smf = new SimpleDateFormat("yyyyMMddHHmmss");
//        String curTime = smf.format(new java.util.Date());
        serialNum++;
        String name = String.valueOf(serialNum) + ".png";
        File srcFile = null;
        try {
            srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        } catch (Exception driverEx) {
            recordSwitch = false;
            return;
        }
        //把截图拷贝到自定义的目录
       String filebase = config.getBasePath() + File.separator + config.getCasename();
        File save_path = new File(filebase);
        if (!save_path.exists()) {
            save_path.mkdirs();
        }
        File newfile = new File(save_path + File.separator + name);
        if (newfile.exists()) {
            newfile.delete();
        }
        FileUtils.moveFile(srcFile, newfile);
    }

    public ScreenConfig getConfig() {
        return config;
    }

    public void setConfig(ScreenConfig config) {
        this.config = config;
        sleepTime = 1000 / config.getFrame();
    }

    public void init() {
        thread = new Thread() {
            @Override
            public void run() {
                while (recordSwitch) {
                    try {
                        takeScreenShot();
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}

