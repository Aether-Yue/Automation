package com.automation.service;

import com.automation.until.WaitUntil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class OperateUpload {
    private static Logger logger = Logger.getLogger(OperateUpload.class);

    public static void upload(String text, String filepath, String xpath, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        if (!StringUtils.isNotBlank(filepath)){
            filepath = "";
        }
        //driver.get("file:/"+filepath);
        System.out.println(filepath);
        File file = new File(filepath);
        String filePath = file.getAbsolutePath();
        String exefile = System.getProperty("user.dir")+ File.separator +"upload.exe";
        String cmd= "\""+ exefile +"\""+ " "+ "\"chrome\""+ " "+ "\""+ filePath + "\"";
        if (!StringUtils.isNotBlank(text)){
            if(!StringUtils.isNotBlank(xpath)){
                logger.error("-----------text is null-----------");
                return;
            }else {
                if (file.exists()) {
                    WaitUntil.waitUntil(wait,xpath);
                    driver.findElement(By.xpath(xpath)).click();
                    Thread.sleep(500);
                    try{
                        Process p= Runtime.getRuntime().exec(cmd);
                        p.waitFor();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    logger.error("-----------file is not exists-----------");
                    return;
                }
            }
        } else if(StringUtils.isNotBlank(text)){
            //续传暂时不能用，原因还需继续定位 ?
            if(text.equalsIgnoreCase("续传")) {
                if (file.exists()) {
                    WebElement we = driver.findElement(By.xpath("//div[@class='el-draggeer__cover__btns']/span/span[text()='" + text + "']"));
                    System.out.println(we);
                    Actions actions = new Actions(driver);
                    actions.moveToElement(we).click().perform();
                    Thread.sleep(500);
                    try {
                        Process p = Runtime.getRuntime().exec(cmd);
                        p.waitFor();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    logger.error("-----------file is not exists-----------");
                    return;
                }
            }else if(text.equalsIgnoreCase("查看")){
                try{
                    WebElement we = driver.findElement(By.xpath("//div[@class='el-draggeer__cover__btns']"));
                    Actions actions = new Actions(driver);
                    actions.moveToElement(we);
                    Thread.sleep(500);
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    WebElement logout = we.findElement(By.xpath("//div[@class='el-draggeer__cover__btns']/span/span[text()='" + text + "']"));
                    js.executeScript("arguments[0].click();", logout);
                    //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='el-draggeer__cover__btns']/span/span[text()='" + text + "']")))).click();
                    Thread.sleep(500);
                    //Actions actions = new Actions(driver);
                    actions.moveByOffset(0, 0).click().build().perform();
                }catch (Exception NotFoundException){
                    NotFoundException.printStackTrace();
                }
            }else if(text.equalsIgnoreCase("删除")){
                String[] filetype = filepath.split("\\.");
                System.out.println(filetype);
                String filetypemsg = filetype[1];
                if(filetypemsg.equalsIgnoreCase("jpg") || filetypemsg.equalsIgnoreCase("png")){
                    try{
                        WebElement we = driver.findElement(By.xpath("//div[@class='el-draggeer__cover__btns']"));
                        System.out.println(we);
                        Actions actions = new Actions(driver);
                        actions.moveToElement(we);
                        Thread.sleep(500);
                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        WebElement logout = we.findElement(By.xpath("//div[@class='el-draggeer__cover__btns']/span/span[text()='" + text + "']"));
                        js.executeScript("arguments[0].click();", logout);
                        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='el-draggeer__cover__btns']/span/span[text()='" + text + "']")))).click();
                    }catch (Exception NotFoundException){
                        NotFoundException.printStackTrace();
                    }
                }else if (filetypemsg.equalsIgnoreCase("txt") || filetypemsg.equalsIgnoreCase("pdf")){
                    String[] ulmsg = driver.findElement(By.xpath("//ul[@class='el-upload__files']")).getText().split("\n");
                    System.out.println("3-------"+ulmsg);
                    int y=0;
                    for(int i=0;i<ulmsg.length;i++){
                        if(ulmsg[i].equalsIgnoreCase(filepath)){
                            y=i+1;
                            driver.findElement(By.xpath("//ul[@class='el-upload__files']/li["+y+"]/span[text()='删除']")).click();
                            break;
                        }
                    }
                }
            }else if(text.equalsIgnoreCase("点击上传")){
                if (file.exists()) {
                    //找到input，然后利用sendKeys来上传文件
                    String file_xpath = "//button/span[text()='" + text + "']";
                    WaitUntil.waitUntil(wait,file_xpath);
                    driver.findElement(By.xpath(file_xpath)).click();
                    Thread.sleep(500);
                    try {
                        Process p = Runtime.getRuntime().exec(cmd);
                        p.waitFor();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    logger.error("-----------file is not exists-----------");
                    return;
                }
            }else {
                try {
                    driver.findElement(By.xpath("//div/label[text()='" + text + "']/following-sibling::div//div[@class='el-upload__inner el-dragger']")).click();
                }catch (Exception e){
                    driver.findElement(By.xpath("//div/label[text()='" + text + "']/following-sibling::div//div[@class='el-upload__inner']")).click();
                }
                try {
                    Process p = Runtime.getRuntime().exec(cmd);
                    p.waitFor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
