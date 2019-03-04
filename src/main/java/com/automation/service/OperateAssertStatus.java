package com.automation.service;

import com.automation.until.Assertion;
import com.automation.until.WaitUntil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;

public class OperateAssertStatus {
    private static Logger logger = Logger.getLogger(OperateAssertStatus.class);
    public static Boolean status = false;

    public static void assertStatus(String text,String type, Boolean expect,String filename,WebDriver driver, WebDriverWait wait) throws IOException {
        switch (type){
            case "input":
                if(!StringUtils.isNotBlank(text)){
                    logger.error("-----------request input is null-----------");
                }else{
                    String inputStatus_xpath="//div/label[text()='" + text + "']/following-sibling::div/div/div/input";
                    WaitUntil.waitUntil(wait,inputStatus_xpath);
                    //WebElement bt = wait.until(visibilityOf(driver.findElement(By.xpath("//div/label[text()='" + la.get(5) + "']/following-sibling::div/div/div/input"))));
                    status = driver.findElement(By.xpath(inputStatus_xpath)).isEnabled();
                }
                break;
            case "button":
                if(!StringUtils.isNotBlank(text)){
                    logger.error("-----------request button is null-----------");
                }else{
                    String buttonStatus_xpath = "//button/span[text()='" + text + "']";
                    status = WaitUntil.waitUntilisEnable(wait,buttonStatus_xpath);
                }
                break;
        }
        logger.info("----------Assertion Finish----------actual:" + status + "  expect:" + expect);
        Assertion.verifyEquals(status,expect,filename);
    }
}
