package com.automation.service;

import com.automation.until.Assertion;
import com.automation.until.WaitUntil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;

public class AssertText {
    private static Logger logger = Logger.getLogger(AssertText.class);

    public static void assertText(String text, String expect, String type,String filename,WebDriver driver, WebDriverWait wait) throws IOException {
        String form_msg = null;
        if(StringUtils.isNotBlank(type)) {
            if (type.equalsIgnoreCase("url")) {
                form_msg = driver.getCurrentUrl();
            } else if (type.equalsIgnoreCase("messagebox")) {
                String notice_xpath = "//body/div[contains(@class,'el-message el-message--')][last()]/p";
                WebDriverWait wait_notice = new WebDriverWait(driver, 10);
                WaitUntil.waitUntil(wait_notice, notice_xpath);
                form_msg = driver.findElement(By.xpath(notice_xpath)).getText();
            }
        }else {
            if (!StringUtils.isNotBlank(text)) {
                logger.error("-----------request xpath is null-----------");
            } else {
                form_msg = OperateGettext.getText(text, driver, wait);
            }
        }
        asstext(form_msg,expect,filename);
    }


    public static void asstext(String currmsg,String expect,String filename) throws IOException {
        if (!StringUtils.isNotBlank(expect)) {
            if (StringUtils.isNotBlank(currmsg)) {
                Assertion.verifyEquals(currmsg, expect, filename);
                logger.info("----------Assertion Finish----------actual:" + currmsg + "  expect:" + expect);
            } else {
                logger.error("-----------request expect is null-----------");
            }
        } else {
            Assertion.verifyEquals(currmsg, expect, filename);
            logger.info("----------Assertion Finish----------actual:" + currmsg + "  expect:" + expect);
        }
    }

}
