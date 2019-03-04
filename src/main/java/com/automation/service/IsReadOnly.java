package com.automation.service;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IsReadOnly {
    /**
     * 判断元素是否只读
     * @param xpath
     * @param driver
     * @return
     */
    public static Boolean isreadonly(String xpath,WebDriver driver){
        Boolean isreadonly = false;
        try {
            String readonly = driver.findElement(By.xpath(xpath)).getAttribute("readonly");
            if (StringUtils.isNotBlank(readonly)) {
                if (readonly.equalsIgnoreCase(readonly)) {
                    isreadonly = true;
                }
            }
        }catch (Exception redonlyex){
            redonlyex.printStackTrace();
        }

        return isreadonly;
    }
}
