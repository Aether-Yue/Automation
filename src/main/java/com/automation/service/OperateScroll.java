package com.automation.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class OperateScroll {
    private static Logger logger = Logger.getLogger(OperateScroll.class);

    /**
     * 操作滚动条
     * @param type
     * @param value
     * @param driver
     * @param wait
     **/
    public static void scrollOperation(String type, String value, WebDriver driver, WebDriverWait wait){
        try {
            if(StringUtils.isNotBlank(value)){
                if(type.equalsIgnoreCase("top")){
                    WebElement target = driver.findElement(By.xpath(value));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", target);
                    //((JavascriptExecutor) driver).executeScript("document.documentElement.scrollTop=1000");
                }else if(type.equalsIgnoreCase("bottom")){
                    WebElement target = driver.findElement(By.xpath(value));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", target);
                }else if(type.equalsIgnoreCase("right") && isNumeric(value)){
                    ((JavascriptExecutor) driver).executeScript("document.body.scrollLeft(" + value + ",0)");
                }else if(type.equalsIgnoreCase("left") && isNumeric(value)){
                    ((JavascriptExecutor) driver).executeScript("document.body.scrollLeft(-" + value + ",0)");
                }
            }else {
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
