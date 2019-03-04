package com.automation.service;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class OperateGettext {

    private static Logger logger = Logger.getLogger(OperateGettext.class);
    private static String asmsg=null;
    private static String xpath=null;

    /**
     * 获取指定文本内容
     * @param text 输入参数的名称
     * @param driver
     * @param wait
     **/
    public static String getText(String text, WebDriver driver, WebDriverWait wait){
        if(text.equalsIgnoreCase("收到公告条数")){
            asmsg = wait.until(visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div/header/div/div[@class='pop-btn bulletin']/div[1]"))).getText();
        }
        //通知查看获取不到value，后续想办法解决
        else if(text.equalsIgnoreCase("通知详情标题")){
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("document.querySelector(\"#app > div > div > div.lw-content.lw-flex > div > div.el-row > div.el-col.el-col-6 > div > div.el-card__body > div > div > form > div:nth-child(1) > div > div > div > div > div > input\").removeAttribute(\"disabled\");");
            Object textt = js.executeScript("document.getElementsByTagName(\"textarea\")[0].value;");
            asmsg = driver.findElement(By.xpath("//div/label[text()='标题']/following-sibling::div/div/div/input")).getText();
        }else if(text.equalsIgnoreCase("通知详情内容")){
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("document.querySelector(\"#app > div > div > div.lw-content.lw-flex > div > div.el-row > div.el-col.el-col-6 > div > div.el-card__body > div > div > form > div:nth-child(2) > div > div > div > div > div > textarea\").removeAttribute(\"disabled\");");
            asmsg = driver.findElement(By.xpath("//div/label[text()='内容']/following-sibling::div/div/div/textarea")).getText();
        }
        else  if(text.equalsIgnoreCase("当前登录用户")){
            asmsg = wait.until(visibilityOfElementLocated(By.xpath("//div[@class='lw-menu lw-flex']/div/ul/span/li[1]/div"))).getText();
        } else  if(text.equalsIgnoreCase("登录错误提示")){
            asmsg = wait.until(visibilityOfElementLocated(By.xpath("//span[@class='el-alert__title']"))).getText();
        }
        else {
            asmsg = wait.until(visibilityOfElementLocated(By.xpath(text))).getText();
        }

        if(asmsg.isEmpty()){
            asmsg="null";
        }
        return asmsg;
    }
}
