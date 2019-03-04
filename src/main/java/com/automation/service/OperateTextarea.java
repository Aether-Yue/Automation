package com.automation.service;

import com.automation.until.WaitUntil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OperateTextarea {
    private static Logger logger = Logger.getLogger(OperateTextarea.class);

    /**
     * textarea操作
     * @param text 输入参数的名称
     * @param keys 参数值
     * @param xpath 输入参数的xpath(当text为空时使用，text不为空则无效)
     * @param driver
     * @param wait
     **/
    public static void getTextareaSendkeys(String text, String keys, String xpath, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        if (!StringUtils.isNotBlank(keys)){
            keys = "";
        }

        if (!StringUtils.isNotBlank(text)){
            if(xpath.isEmpty() || xpath.equalsIgnoreCase("null")){
                logger.error("-----------text is null-----------");
                return;
            }else {
                WaitUntil.waitUntil(wait,xpath);
                driver.findElement(By.xpath(xpath)).click();
                driver.findElement(By.xpath(xpath)).clear();
                Thread.sleep(500);
                driver.findElement(By.xpath(xpath)).sendKeys(keys);
            }
        } else {
            try{
                String inputtitle_xpath = "//div/label[text()='" + text + "']/following-sibling::div/div/div/textarea";
                WaitUntil.waitUntil(wait,inputtitle_xpath);
                driver.findElement(By.xpath(inputtitle_xpath)).click();
                driver.findElement(By.xpath(inputtitle_xpath)).clear();
                Thread.sleep(500);
                driver.findElement(By.xpath(inputtitle_xpath)).sendKeys(keys);
            }catch (Exception NotFoundException){
                NotFoundException.printStackTrace();
            }
        }
        Actions actions = new Actions(driver);
        actions.moveByOffset(0, 0).click().build().perform();
    }

    /**
     * 多DIV textarea操作
     * @param text 输入参数的名称
     * @param keys 参数值
     * @param xx div的层级数
     * @param driver
     * @param wait
     **/
    public static void getTextareaSendkeysMultiple(String text, String keys, String xpath, String xx, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        String xpath_value = OperateClickMultiple.xpathCreate(xx);
        if (!StringUtils.isNotBlank(keys)){
            keys = "";
        }

        if (!StringUtils.isNotBlank(text)){
            if(xpath.isEmpty() || xpath.equalsIgnoreCase("null")){
                logger.error("-----------text is null-----------");
                return;
            }else {
                WaitUntil.waitUntil(wait,xpath);
                driver.findElement(By.xpath(xpath)).click();
                driver.findElement(By.xpath(xpath)).clear();
                Thread.sleep(500);
                driver.findElement(By.xpath(xpath)).sendKeys(keys);
            }
        } else {
            try{
                String inputtitle_xpath = "//div[@class='el-row']"+xpath_value+"/descendant::label[text()='" + text + "']/following-sibling::div/div/div/textarea";
                WaitUntil.waitUntil(wait,inputtitle_xpath);
                driver.findElement(By.xpath(inputtitle_xpath)).click();
                driver.findElement(By.xpath(inputtitle_xpath)).clear();
                Thread.sleep(500);
                driver.findElement(By.xpath(inputtitle_xpath)).sendKeys(keys);
            }catch (Exception NotFoundException){
                NotFoundException.printStackTrace();
            }
        }
        Actions actions = new Actions(driver);
        actions.moveByOffset(0, 0).click().build().perform();
    }
    }
