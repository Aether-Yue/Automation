package com.automation.service;

import com.automation.until.WaitUntil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class OperateGettextTable {
    private static Logger logger = Logger.getLogger(OperateGettextTable.class);

    /**
     * 获取表格信息
     * @param row
     * @param column
     * @param content
     * @param driver
     * @param wait
     * @return
     * @throws InterruptedException
     */
    public static String getTextTable(int row, String column, String content, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        String text_msg = null;
        if (row <0 || !StringUtils.isNotBlank(column) || !StringUtils.isNotBlank(content)){
            logger.error("-----------xml edit is error-----------");
        } else if(content.equalsIgnoreCase("row")) {
            if (row <= 0) {
                logger.error("-----------row or column edit is error-----------");
            } else {
                Thread.sleep(500);
                String msg_xpath = "//div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]";
                WaitUntil.waitUntil(wait,msg_xpath);
                text_msg = driver.findElement(By.xpath(msg_xpath)).getText();
            }
        }
        else if (content.equalsIgnoreCase("separate")){
            if(row <= 0 || column.isEmpty() || column.equalsIgnoreCase("null") ){
                logger.error("-----------row or column edit is error-----------");
            }else{
                String tbclm_mmm = wait.until(presenceOfElementLocated(By.xpath("//div[@class='el-table__header-wrapper']/table/thead/tr"))).getAttribute("innerHTML");
                org.jsoup.nodes.Document doc = Jsoup.parse(tbclm_mmm);
                String[] tbclm = doc.getElementsByClass("cell").text().split(" ");
                int y=0;
                for(int i=0;i<tbclm.length;i++){
                    if (tbclm[i].equalsIgnoreCase(column)) {
                        y=i+1;
                        Thread.sleep(500);
                        String msg_xpath = "//div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/div";
                        WaitUntil.waitUntil(wait,msg_xpath);
                        text_msg = driver.findElement(By.xpath(msg_xpath)).getText();
                        break;
                    }
                }
            }
        }
        else if(content.equalsIgnoreCase("all")){
            Thread.sleep(500);
            String msg_xpath = "//div[@class='el-table__body-wrapper']/table/tbody";
            WaitUntil.waitUntil(wait,msg_xpath);
            text_msg = driver.findElement(By.xpath(msg_xpath)).getText();
        }else {
            text_msg = "-2 not found column";
        }
        return text_msg;
    }

    /**
     * 多DIV 获取表格信息
     * @param row
     * @param column
     * @param content
     * @param driver
     * @param wait
     * @return
     * @throws InterruptedException
     */
    public static String getTextTableMultiple(int row, String column, String content,String xx, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        String xpath_value = OperateClickMultiple.xpathCreate(xx);
        String text_msg = null;
        if (row <0 || !StringUtils.isNotBlank(column)|| !StringUtils.isNotBlank(content)){
            logger.error("-----------xml edit is error-----------");
        } else if(content.equalsIgnoreCase("row")) {
            if (row <= 0) {
                logger.error("-----------row or column edit is error-----------");
            } else {
                Thread.sleep(500);
                String msg_xpath = "//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]";
                WaitUntil.waitUntil(wait,msg_xpath);
                text_msg = driver.findElement(By.xpath(msg_xpath)).getText();
            }
        }
        else if (content.equalsIgnoreCase("separate")){
            if(row <= 0 || !StringUtils.isNotBlank(column)){
                logger.error("-----------row or column edit is error-----------");
            }else{
                String tbclm_mmm = wait.until(presenceOfElementLocated(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-table__header-wrapper']/table/thead/tr"))).getAttribute("innerHTML");
                org.jsoup.nodes.Document doc = Jsoup.parse(tbclm_mmm);
                String[] tbclm = doc.getElementsByClass("cell").text().split(" ");
                int y=0;
                for(int i=0;i<tbclm.length;i++){
                    if (tbclm[i].equalsIgnoreCase(column)) {
                        y=i+1;
                        Thread.sleep(500);
                        String msg_xpath = "//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/div";
                        WaitUntil.waitUntil(wait,msg_xpath);
                        text_msg = driver.findElement(By.xpath(msg_xpath)).getText();
                        break;
                    }
                }
            }
        }
        else if(content.equalsIgnoreCase("all")){
            Thread.sleep(500);
            String msg_xpath = "//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-table__body-wrapper']/table/tbody";
            WaitUntil.waitUntil(wait,msg_xpath);
            text_msg = driver.findElement(By.xpath(msg_xpath)).getText();
        }else {
            text_msg = "-2 not found column";
        }
        return text_msg;
    }
}
