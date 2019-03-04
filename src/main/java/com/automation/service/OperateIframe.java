package com.automation.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class OperateIframe {
    private static Logger logger = Logger.getLogger(OperateIframe.class);

    /**
     * 切换iframe
     * @param text 输入参数的名称
     * @param driver
     * @param wait
     **/
    public static void switchIframe(String text, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        if (!StringUtils.isNotBlank(text)){
            driver.switchTo().defaultContent();
        }else {
            WebElement iframe = wait.until(visibilityOfElementLocated(By.xpath("//iframe[@seamless='" + text + "']")));
            driver.switchTo().frame(iframe);
        }
    }
}
