package com.automation.service;

import com.automation.until.WaitUntil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class OperateInput {
    private static Logger logger = Logger.getLogger(OperateInput.class);

    /**
     * input操作
     * @param text 输入参数的名称
     * @param keys 参数值
     * @param xpath 输入参数的xpath(当text为空时使用，text不为空则无效)
     * @param type input输入框属性类型，值placeholder
     * @param driver
     * @param wait
     **/
    public static void getXpathSendkeys(String text, String keys, String xpath,String type, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        if (!StringUtils.isNotBlank(keys)){
            keys = "";
        }else if(keys.contains("get_currtime")){//特殊参数，获取当前时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar c = new GregorianCalendar();
            Date date = new Date();
            if(keys.contains("get_currtime+")){
                String[] key_sp = keys.split("\\+");
                Integer t = Integer.parseInt(key_sp[1]);
                //System.out.println("系统当前时间："+df.format(date));
                c.setTime(date);//设置参数时间
                c.add(Calendar.SECOND,t);//把日期往后增加SECOND 秒.整数往后推,负数往前移动
                date=c.getTime(); //这个时间就是日期往后推一天的结果
                keys = df.format(date) ;
                //System.out.println("keys"+keys);
            }else if(keys.contains("get_currtime-")){
                String[] key_sp = keys.split("-");
                Integer t = Integer.parseInt(key_sp[1]);
                //System.out.println("系统当前时间："+df.format(date));
                c.setTime(date);//设置参数时间
                c.add(Calendar.SECOND,-t);//把日期往后增加SECOND 秒.整数往后推,负数往前移动
                date=c.getTime(); //这个时间就是日期往后推一天的结果
                keys = df.format(date) ;
                //System.out.println("keys"+keys);
            } else {
                keys = df.format(date);
            }
        }

        if (!StringUtils.isNotBlank(text)){
            if(!StringUtils.isNotBlank(xpath)){
                logger.error("-----------text is null-----------");
                return;
            }else {
                WaitUntil.waitUntil(wait,xpath);
                driver.findElement(By.xpath(xpath)).click();
                driver.findElement(By.xpath(xpath)).clear();
                Thread.sleep(500);
                driver.findElement(By.xpath(xpath)).sendKeys(keys);
            }
        }else if(text.equalsIgnoreCase("翻页")) {
            String paging_xpath = "//input[@class='el-pagination__editor']";
            WaitUntil.waitUntil(wait,paging_xpath);
            WebElement paging_div = driver.findElement(By.xpath(paging_xpath));
            try{
                paging_div.click();
                Actions actions=new Actions(driver);
                actions.doubleClick(paging_div).perform();
                Integer.parseInt(keys);
                paging_div.sendKeys(keys);
            }catch (Exception NotFoundException){
                logger.info("-------paging failed---------");
            }
        }else if(StringUtils.isNotBlank(type)){
                if(type.equalsIgnoreCase("placeholder")) {
                String placeholder_xpath = "//input[@placeholder='" + text + "']";
                WaitUntil.waitUntil(wait, placeholder_xpath);
                driver.findElement(By.xpath(placeholder_xpath)).click();
                driver.findElement(By.xpath(placeholder_xpath)).clear();
                Thread.sleep(500);
                driver.findElement(By.xpath(placeholder_xpath)).sendKeys(keys);
            }
        } else {
            try{
                //由标题的输入
                String inputtitle_xpath = "//div/label[text()='" + text + "']/following-sibling::div/div/div/input";
                WaitUntil.waitUntil(wait,inputtitle_xpath);
                driver.findElement(By.xpath(inputtitle_xpath)).click();
                driver.findElement(By.xpath(inputtitle_xpath)).clear();
                Thread.sleep(500);
                driver.findElement(By.xpath(inputtitle_xpath)).sendKeys(keys);
            }catch (Exception NotFoundException){
                //无标题的输入
                String placeholder_xpath = "//input[@placeholder='" + text + "']";
                WaitUntil.waitUntil(wait,placeholder_xpath);
                driver.findElement(By.xpath(placeholder_xpath)).click();
                driver.findElement(By.xpath(placeholder_xpath)).clear();
                Thread.sleep(500);
                driver.findElement(By.xpath(placeholder_xpath)).sendKeys(keys);
            }
        }
        /*点击空白处，时间控件点击有问题，不添加不影响执行，所以暂时注释
        //Thread.sleep(500);
        //Actions actions = new Actions(driver);
        //actions.moveByOffset(0, 0).click().build().perform();
        */
    }

    /**
     * 多DIV input
     * @param text 输入参数的名称
     * @param keys 参数值
     * @param type input输入框属性类型，值placeholder
     * @param xx div的层级数
     * @param driver
     * @param wait
     **/
    public static void getXpathSendkeysMultiple(String text, String keys, String type,String xx, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        String xpath_value = OperateClickMultiple.xpathCreate(xx);
        if (!StringUtils.isNotBlank(keys)){
            keys = "";
        }else if(keys.contains("get_currtime")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar c = new GregorianCalendar();
            Date date = new Date();
            if(keys.contains("get_currtime+")){
                String[] key_sp = keys.split("\\+");
                Integer t = Integer.parseInt(key_sp[1]);
                //System.out.println("系统当前时间："+df.format(date));
                c.setTime(date);//设置参数时间
                c.add(Calendar.SECOND,t);//把日期往后增加SECOND 秒.整数往后推,负数往前移动
                date=c.getTime(); //这个时间就是日期往后推一天的结果
                keys = df.format(date) ;
                //System.out.println("keys"+keys);
            }else if(keys.contains("get_currtime-")){
                String[] key_sp = keys.split("-");
                Integer t = Integer.parseInt(key_sp[1]);
                //System.out.println("系统当前时间："+df.format(date));
                c.setTime(date);//设置参数时间
                c.add(Calendar.SECOND,-t);//把日期往后增加SECOND 秒.整数往后推,负数往前移动
                date=c.getTime(); //这个时间就是日期往后推一天的结果
                keys = df.format(date) ;
                //System.out.println("keys"+keys);
            } else {
                keys = df.format(date);
            }
        }

        if (!StringUtils.isNotBlank(text)){
            logger.error("-----------text is null-----------");
            return;
        }else if(StringUtils.isNotBlank(type)){
            if(type.equalsIgnoreCase("placeholder")) {
                String placeholder_xpath = "//div[@class='el-row']" + xpath_value + "/descendant::input[@placeholder='" + text + "']";
                WaitUntil.waitUntil(wait, placeholder_xpath);
                driver.findElement(By.xpath(placeholder_xpath)).click();
                driver.findElement(By.xpath(placeholder_xpath)).clear();
                Thread.sleep(500);
                driver.findElement(By.xpath(placeholder_xpath)).sendKeys(keys);
            }
        }else {
            try{
                String inputtitle_xpath = "//div[@class='el-row']"+xpath_value+"/descendant::label[text()='" + text + "']/following-sibling::div/div/div/input";
                WaitUntil.waitUntil(wait,inputtitle_xpath);
                driver.findElement(By.xpath(inputtitle_xpath)).click();
                driver.findElement(By.xpath(inputtitle_xpath)).clear();
                Thread.sleep(500);
                driver.findElement(By.xpath(inputtitle_xpath)).sendKeys(keys);
            }catch (Exception NotfoundException){
                String placeholder_xpath = "//div[@class='el-row']"+xpath_value+"/descendant::input[@placeholder='" + text + "']";
                WaitUntil.waitUntil(wait,placeholder_xpath);
                driver.findElement(By.xpath(placeholder_xpath)).click();
                driver.findElement(By.xpath(placeholder_xpath)).clear();
                Thread.sleep(500);
                driver.findElement(By.xpath(placeholder_xpath)).sendKeys(keys);
            }
        }
    }
}
