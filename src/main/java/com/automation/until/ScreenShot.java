package com.automation.until;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * 失败截图
 */
public class ScreenShot {
    public static WebDriver driver;
    public void takeScreenShot(ITestResult tr) throws IOException {
        SimpleDateFormat smf = new SimpleDateFormat("MMddHHmmss") ;
        String curTime = smf.format(new java.util.Date());
        String fileName = tr.getName()+"_"+curTime+".png";
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //把截图拷贝到自定义的目录
        FileUtils.moveFile(srcFile, new File("failedimage"+ File.separator +fileName));
    }

    public void takeScreenShot(String filename) throws IOException {
        SimpleDateFormat smf = new SimpleDateFormat("yyyyMMddHHmmss") ;
        String curTime = smf.format(new java.util.Date());
        String fileName = filename + "_"+curTime+".png";
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //把截图拷贝到自定义的目录
        SimpleDateFormat savedate = new SimpleDateFormat("yyyyMMdd") ;
        File save_path = new File("failedimage" + File.separator + savedate.format(new java.util.Date()));
        if(save_path.exists()){
            if(save_path.isDirectory()){
                System.out.println("dir is exists , not need create");
            }else {
                System.out.println("the same name file exists, can not create dir");
            }
        }else {
            System.out.println("dir is not exists, create it ...");
            save_path.mkdirs();
        }
        FileUtils.moveFile(srcFile, new File(save_path + File.separator + fileName));
    }
}
