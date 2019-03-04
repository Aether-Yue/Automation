package com.automation.model;

import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;

public class ScreenConfig {
    SimpleDateFormat savedate = new SimpleDateFormat("yyyyMMdd");
    public static String SCREEN_FULL = "FULL_SCREEN";
    public String basePath = "video" + File.separator + savedate.format(new java.util.Date());
    public int frame = 45;
    public Rectangle rectangle;
    public WebDriver driver;
    public String casename;

    public String getCasename() {
        return casename;
    }

    public void setCasename(String casename) {
        this.casename = casename;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public Rectangle getScreenFull() {
        return new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());//可以指定捕获屏幕区域
    }


    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public Rectangle getRectangle() {
        if (rectangle == null)
            return getScreenFull();
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
