package com.automation.service;

import com.automation.until.WaitUntil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class OperateClick {
    private static Logger logger = Logger.getLogger(OperateClick.class);

    /**
     * 普通点击
     * @param text 输入参数的名称
     * @param type 输入类型
     * @param driver
     * @param wait
     **/
    public static void getXpathClick(String text, String type, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        if (!StringUtils.isNotBlank(text)){
            logger.error("-----------text or type is null-----------");
            return;
        }else if(StringUtils.isNotBlank(type)) {
            if (type.equalsIgnoreCase("tab")) {
                if (text.equalsIgnoreCase("公告") || text.contains("公告-")) {
                    String bulletin_xpath = "//*[@id=\"app\"]/div/header/div/div[@class='pop-btn bulletin']/i";
                    WaitUntil.waitUntil(wait, bulletin_xpath);
                    if (text.contains("-")) {
                        String[] noticeclick = text.split("-");
                        driver.findElement(By.xpath(bulletin_xpath)).click();
                        Thread.sleep(500);
                        //点击公告详细
                        String bulletin_detail_xpath = "//*[@id=\"app\"]/div/header/div/div[3]/following-sibling::div/ul/li/div[text()='" + noticeclick[1] + "']";
                        WaitUntil.waitUntil(wait, bulletin_detail_xpath);
                        driver.findElement(By.xpath(bulletin_detail_xpath)).click();
                        Thread.sleep(500);
                    } else {
                        driver.findElement(By.xpath(bulletin_xpath)).click();
                        Thread.sleep(500);
                    }
                } else if (text.equalsIgnoreCase("消息") || text.contains("消息-")) {
                    String messages_xpath = "//*[@id=\"app\"]/div/header/div/div[@class='pop-btn messages']/i";
                    WaitUntil.waitUntil(wait, messages_xpath);
                    if (text.contains("-")) {
                        String[] msgclick = text.split("-");
                        driver.findElement(By.xpath(messages_xpath)).click();
                        Thread.sleep(500);
                        //点击消息明细
                        String messages_detail_xpath = "//*[@id=\"app\"]/div/header/div/div[@class='pop-btn messages']/following-sibling::div/ul/li/div[text()='" + msgclick[1] + "']";
                        WaitUntil.waitUntil(wait, messages_detail_xpath);
                        driver.findElement(By.xpath(messages_detail_xpath)).click();
                        Thread.sleep(500);
                    } else {
                        driver.findElement(By.xpath(messages_xpath)).click();
                        Thread.sleep(500);
                    }
                } else if (text.equalsIgnoreCase("任务") || text.contains("任务-")) {
                    String tasks_xpath = "//*[@id=\"app\"]/div/header/div/div[@class='pop-btn tasks active']/i";
                    WaitUntil.waitUntil(wait, tasks_xpath);
                    if (text.contains("-")) {
                        String[] taskclick = text.split("-");
                        driver.findElement(By.xpath(tasks_xpath)).click();
                        Thread.sleep(500);
                        //点击任务详情
                        String tasks_detail_xpath = "//*[@id=\"app\"]/div/header/div/div[@class='pop-btn tasks active']/following-sibling::div/ul/li/div[text()='" + taskclick[1] + "']";
                        WaitUntil.waitUntil(wait, tasks_detail_xpath);
                        driver.findElement(By.xpath(tasks_detail_xpath)).click();
                        Thread.sleep(500);
                    } else {
                        driver.findElement(By.xpath(tasks_xpath)).click();
                        Thread.sleep(500);
                    }
                } else if (text.equalsIgnoreCase("登录用户") || text.contains("登录用户-")) {
                    String username_xpath = "//*[@id=\"app\"]/div/header/div/div[@class='pop-wrap username']/div[1]";
                    WaitUntil.waitUntil(wait, username_xpath);
                    if (text.contains("-")) {
                        String[] userclick = text.split("-");
                        driver.findElement(By.xpath(username_xpath)).click();
                        Thread.sleep(500);
                        //点击子菜单
                        String username_detail_xpath = "//*[@id=\"app\"]/div/header/div/div[@class='pop-wrap username']/div/div/span[text()='" + userclick[1] + "']";
                        WaitUntil.waitUntil(wait, username_detail_xpath);
                        driver.findElement(By.xpath(username_detail_xpath)).click();
                        Thread.sleep(500);
                    } else {
                        driver.findElement(By.xpath(username_xpath)).click();
                        Thread.sleep(500);
                    }
                } else {
                    if (text.contains("-")) {
                        for (String name_split : text.split("-")) {
                            String text_xpath = "//div[text()='" + name_split + "']";
                            WaitUntil.waitUntil(wait, text_xpath);
                            driver.findElement(By.xpath(text_xpath)).click();
                            Thread.sleep(500);
                        }
                    } else {
                        String text_xpath = "//div[text()='" + text + "']";
                        WaitUntil.waitUntil(wait, text_xpath);
                        driver.findElement(By.xpath(text_xpath)).click();
                        Thread.sleep(500);
                    }
                }
            } else if (type.equalsIgnoreCase("button")) {
                if (text.equals("编辑")) {
                    String edit_button_xpath = "//button[@class='el-button el-button--primary el-button--small el-dropdown__caret-button']/preceding-sibling::button/span[text()='编辑']";
                    WaitUntil.waitUntil(wait, edit_button_xpath);
                    driver.findElement(By.xpath(edit_button_xpath)).click();
                    Thread.sleep(500);
                }
            /*
            else if (text.equalsIgnoreCase("修改密码")){
                String chgpasswd_xpath = "//*[@id=\"app\"]/div/div/div[2]/div/div[2]/div[1]/div/span/span/button/span";
                Boolean button_enable = WaitUntil.waitUntilisEnable(wait,chgpasswd_xpath);
                if(button_enable){
                    driver.findElement(By.xpath(chgpasswd_xpath)).click();
                }else {
                    logger.info(" change password button can't click ");
                }
            }*/
                else if (text.contains("视图管理-")) {
                    int y = 0;
                    String[] viewop = text.split("-");
                    String view_button_xpath = "//button[@class='el-button el-button--primary el-button--small el-dropdown__caret-button']/span";
                    WaitUntil.waitUntil(wait, view_button_xpath);
                    driver.findElement(By.xpath(view_button_xpath)).click();
                    Thread.sleep(500);
                    String combox_text_xpath = "//body/ul[@x-placement='bottom-end']";
                    WaitUntil.waitUntil(wait, combox_text_xpath);
                    String[] combox_text = driver.findElement(By.xpath(combox_text_xpath)).getText().split("\n");
                    for (int i = 0; i < combox_text.length; i++) {
                        y = i + 1;
                        if (viewop[1].equalsIgnoreCase(combox_text[i])) {
                            String combox_list_xpath = "//body/ul[@x-placement='bottom-end']/li[" + y + "]";
                            Boolean list_enable = WaitUntil.waitUntilisEnable(wait, combox_list_xpath);
                            if (list_enable) {
                                driver.findElement(By.xpath(combox_list_xpath)).click();
                                Thread.sleep(500);
                            }
                            break;
                        }
                    }
                } else if (text.contains("操作-")) {
                    int y = 0;
                    String[] viewop = text.split("-");
                    String viewop_xpath = "//button/span[text()='" + viewop[0] + "']";
                    WaitUntil.waitUntil(wait, viewop_xpath);
                    driver.findElement(By.xpath(viewop_xpath)).click();
                    Thread.sleep(500);
                    String combox_text_xpath = "//body/ul[@x-placement='bottom-end']";
                    WaitUntil.waitUntil(wait, combox_text_xpath);
                    try {
                        String[] combox_text = driver.findElement(By.xpath(combox_text_xpath)).getText().split("\n");
                        for (int i = 0; i < combox_text.length; i++) {
                            y = i + 1;
                            if (viewop[1].equalsIgnoreCase(combox_text[i])) {
                                String combox_list_xpath = "//body/ul[@x-placement='bottom-end']/li[" + y + "]";
                                Boolean list_enable = WaitUntil.waitUntilisEnable(wait, combox_list_xpath);
                                if (list_enable) {
                                    driver.findElement(By.xpath(combox_list_xpath)).click();
                                    Thread.sleep(500);
                                }
                                break;
                            }
                        }
                    } catch (Exception qa) {
                        qa.printStackTrace();
                    }
                } else if (text.equalsIgnoreCase("关闭弹出框")) {
                    String close_xpath = "//div[@class='el-dialog__headerbtn']/i";
                    WaitUntil.waitUntil(wait, close_xpath);
                    driver.findElement(By.xpath(close_xpath)).click();
                    Thread.sleep(500);
                } else if (text.equalsIgnoreCase("关闭提示")) {
                    String close_xpath = "//div[@class='el-message__closeBtn el-icon-close']";
                    WaitUntil.waitUntil(wait, close_xpath);
                    driver.findElement(By.xpath(close_xpath)).click();
                    Thread.sleep(500);
                } else if (text.equalsIgnoreCase("上一页")) {
                    String prev_xpath = "//button[@class='btn-prev']";
                    Boolean prev_enable = WaitUntil.waitUntilisEnable(wait, prev_xpath);
                    if (prev_enable) {
                        driver.findElement(By.xpath(prev_xpath)).click();
                        Thread.sleep(500);
                    } else {
                        logger.info("---------prev button can't click--------");
                    }
                } else if (text.equalsIgnoreCase("下一页")) {
                    String next_xpath = "//button[@class='btn-next']";
                    Boolean next_enable = WaitUntil.waitUntilisEnable(wait, next_xpath);
                    if (next_enable) {
                        driver.findElement(By.xpath(next_xpath)).click();
                        Thread.sleep(500);
                    } else {
                        logger.info("---------next button can't click--------");
                    }
                } else {
                    String button_xpath = "//button/span[text()='" + text + "']";
                    Boolean button_enable = WaitUntil.waitUntilisEnable(wait, button_xpath);
                    WebElement bt = driver.findElement(By.xpath(button_xpath));
                    if (button_enable) {
                        bt.click();
                        Thread.sleep(500);
                    } else {
                        logger.info("---------button can't click--------");
                        return;
                    }
                }
            } else if (type.equalsIgnoreCase("xpath")) {
                WaitUntil.waitUntil(wait, text);
                driver.findElement(By.xpath(text)).click();
                Thread.sleep(500);
            }
        }
        else {
            String xpath = "//span[text()='" + text + "']";
            WaitUntil.waitUntil(wait,xpath);
            driver.findElement(By.xpath(xpath)).click();
            Thread.sleep(500);
        }
    }

    /**
     * 点击多选框
     * @param text 输入参数的名称
     * @param driver
     * @param wait
     **/
    public static void clickCheckbox(String text,WebDriver driver, WebDriverWait wait) throws InterruptedException {
        if (!StringUtils.isNotBlank(text)){
            logger.error("-----------text is null-----------");
            return;
        } else {
            String checkbox_xpath = "//div/label/span[text()='" + text + "']/preceding-sibling::span/span";
            WaitUntil.waitUntil(wait,checkbox_xpath);
            driver.findElement(By.xpath(checkbox_xpath)).click();
            Thread.sleep(500);
        }
    }

    /**
     * 下拉框操作
     * @param text 输入参数的名称
     * @param id 下拉框要选择的值
     * @param xpath
     * @param driver
     * @param wait
     **/
    public static void getXpathForm(String text, String id, String xpath, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        if (!StringUtils.isNotBlank(text)){
            if(StringUtils.isNotBlank(xpath)) {
                WaitUntil.waitUntil(wait,xpath);
                driver.findElement(By.xpath(xpath)).click();
                Thread.sleep(500);
            }else {
                logger.error("-----------text and xpath is null-----------");
                return;
            }
        } else {
            //带标题的下拉框
            String tile_xpath = "//div/label[text()='" + text + "']/following-sibling::div//input";
            Boolean isexesist = WaitUntil.waitUntilExesist(wait,tile_xpath);
            Boolean clickenable;
            Boolean isredonly;
            if(isexesist) {
                clickenable = WaitUntil.waitUntilisEnable(wait,tile_xpath);
                isredonly = IsReadOnly.isreadonly(tile_xpath, driver);
                if (clickenable) {
                    driver.findElement(By.xpath(tile_xpath)).click();
                    Thread.sleep(500);
                    if (!isredonly) {
                        try {
                            //wait.until(visibilityOf(driver.findElement(By.xpath("//div/label[text()='" + text + "']/following-sibling::div//input")))).clear();
                            WebElement conbox_clear = driver.findElement(By.xpath(tile_xpath));
                            Actions actions = new Actions(driver);
                            actions.doubleClick(conbox_clear).perform();
                            conbox_clear.sendKeys(Keys.DELETE);
                            Thread.sleep(500);
                            conbox_clear.sendKeys(id);
                            Thread.sleep(1000);
                        } catch (Exception cc) {
                            logger.info("---------input can't clear---------");
                        }
                    }
                    comboxClick(id, driver, wait);
                }
            }else {
                //不带标题的下拉框
                String placeholder_xpath = "//input[@placeholder='" + text + "']";
                clickenable = WaitUntil.waitUntilisEnable(wait,placeholder_xpath);
                isredonly = IsReadOnly.isreadonly(placeholder_xpath, driver);
                if(clickenable) {
                    driver.findElement(By.xpath(placeholder_xpath)).click();
                    Thread.sleep(500);
                    if (!isredonly) {
                        try {
                            driver.findElement(By.xpath(placeholder_xpath)).clear();
                            Thread.sleep(500);
                            driver.findElement(By.xpath(placeholder_xpath)).sendKeys(id);
                            Thread.sleep(1000);
                        } catch (Exception cc) {
                            logger.info("---------input can't clear---------");
                        }
                    }
                    comboxClick(id,driver,wait);
                }
            }
        }
/*
        String[] combox_text;
        //=wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//body/div/ul[@class='el-select-dropdown__list']")))).getText().split("\n");
        String bottom_xpath="//body/div[@x-placement='bottom-start']/descendant::ul";
        String top_xpath="//body/div[@x-placement='top-start']/descendant::ul";
        try{
            combox_text=driver.findElement(By.xpath(bottom_xpath)).getText().split("\n");
        }catch (Exception NotFoundException){
            combox_text=driver.findElement(By.xpath(top_xpath)).getText().split("\n");
        }
        if (!StringUtils.isNotBlank(id)) {
            logger.error("-----------id is null-----------");
            return;
        } else if (id.contains("--")) {
            for (String ss : id.split("--")) {
                for(int i=0;i<combox_text.length;i++) {
                    if(combox_text[i].equalsIgnoreCase(ss)){
                        int j=i+1;
                        //Thread.sleep(1000);
                        try{
                            driver.findElement(By.xpath(bottom_xpath+"/li[" + j + "]")).click();
                        }catch (Exception NotFoundException){
                            driver.findElement(By.xpath(top_xpath+"/li[" + j + "]")).click();
                        }
                        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//body/div/ul[@class='el-select-dropdown__list']/li[" + j + "]")))).click();
                        Thread.sleep(500);
                        break;
                    }else if(i==combox_text.length-1 && !combox_text[i].equalsIgnoreCase(ss)){
                        logger.error("----can't find combox check----");
                    }
                }
            }
        } else {
            for(int i=0;i<combox_text.length;i++) {
                if(combox_text[i].equalsIgnoreCase(id)){
                    int j=i+1;
                    Thread.sleep(500);
                    try{
                        driver.findElement(By.xpath(bottom_xpath+"/li[" + j + "]")).click();
                    }catch (Exception NotFoundException){
                        driver.findElement(By.xpath(top_xpath+"/li[" + j + "]")).click();
                    }
                    //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//body/div/ul[@class='el-select-dropdown__list']/li[" + j + "]")))).click();
                    Thread.sleep(500);
                    break;
                }else if(i==combox_text.length-1 && !combox_text[i].equalsIgnoreCase(id)){
                    logger.error("----can't find combox check----");
                }
            }
        }*/
    }

    /**
     * 点击下拉框的值
     * @param id 下拉框要选择的值
     * @param driver
     * @param wait
     * @throws InterruptedException
     */
    public static void comboxClick(String id,WebDriver driver,WebDriverWait wait) throws InterruptedException {
        String[] combox_text;
        //=wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//body/div/ul[@class='el-select-dropdown__list']")))).getText().split("\n");
        String bottom_xpath="//body/div[@x-placement='bottom-start']/descendant::ul";
        String top_xpath="//body/div[@x-placement='top-start']/descendant::ul";
        try{
            combox_text=driver.findElement(By.xpath(bottom_xpath)).getText().split("\n");
        }catch (Exception NotFoundException){
            combox_text=driver.findElement(By.xpath(top_xpath)).getText().split("\n");
        }
        if (!StringUtils.isNotBlank(id)) {
            logger.error("-----------id is null-----------");
            return;
        } else if (id.contains("--")) {
            for (String ss : id.split("--")) {
                for(int i=0;i<combox_text.length;i++) {
                    if(combox_text[i].equalsIgnoreCase(ss)){
                        int j=i+1;
                        //Thread.sleep(1000);
                        try{
                            driver.findElement(By.xpath(bottom_xpath+"/li[" + j + "]")).click();
                        }catch (Exception NotFoundException){
                            driver.findElement(By.xpath(top_xpath+"/li[" + j + "]")).click();
                        }
                        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//body/div/ul[@class='el-select-dropdown__list']/li[" + j + "]")))).click();
                        Thread.sleep(500);
                        break;
                    }else if(i==combox_text.length-1 && !combox_text[i].equalsIgnoreCase(ss)){
                        logger.error("----can't find combox check----");
                    }
                }
            }
        } else {
            for(int i=0;i<combox_text.length;i++) {
                if(combox_text[i].equalsIgnoreCase(id)){
                    int j=i+1;
                    Thread.sleep(500);
                    try{
                        driver.findElement(By.xpath(bottom_xpath+"/li[" + j + "]")).click();
                    }catch (Exception NotFoundException){
                        driver.findElement(By.xpath(top_xpath+"/li[" + j + "]")).click();
                    }
                    //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//body/div/ul[@class='el-select-dropdown__list']/li[" + j + "]")))).click();
                    Thread.sleep(500);
                    break;
                }else if(i==combox_text.length-1 && !combox_text[i].equalsIgnoreCase(id)){
                    logger.error("----can't find combox check----");
                }
            }
        }
    }

    /**
     * 点击列头排序
     * @param text 输入参数的名称
     * @param id 序号，sort_up为1，sort_down为2
     * @param driver
     * @param wait
     **/
    public static void getXpathSort(String text, int id, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        if (!StringUtils.isNotBlank(text) || id <=0){
            logger.error("-----------text is null-----------");
            return;
        } else {
            String sort_xpath = "//div[text()='" + text + "']/span/i[" + id +"]";
            WaitUntil.waitUntil(wait,sort_xpath);
            driver.findElement(By.xpath(sort_xpath)).click();
            Thread.sleep(500);
        }
    }

    /**
     * 多个重复页签按钮点击
     * @param text 输入参数的名称
     * @param type 操作类型
     * @param tab 操作的页签
     * @param driver
     * @param wait
     **/
    public static void getXpathClickD(String text, String type,String tab, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        if (!StringUtils.isNotBlank(text) || !StringUtils.isNotBlank(type)) {
            logger.error("-----------text or type is null-----------");
            return;
        }else if (type.equalsIgnoreCase("button")){
            String[] tabletab = wait.until(presenceOfElementLocated(By.xpath("//div[@class='el-tabs__header']"))).getText().split("\n");
            int x=0;
            for(int s = 0; s < tabletab.length; s++){
                if(tab.equalsIgnoreCase(tabletab[s])){
                    x=s+1;
                    break;
                }
            }
            WebElement bt = driver.findElement(By.xpath("//div[@class='el-tabs__content']/div["+x+"]/descendant::button/span[text()='" + text + "']"));
            if (bt.isEnabled()) {
                bt.click();
                Thread.sleep(500);
            } else {
                logger.info("---------button can't click--------");
                return;
            }
        }
    }

    /**
     * 弹出框combox操作
     * @param text 输入参数的名称
     * @param id 下拉框要选择的值
     * @param tab 操作的页签
     * @param xpath
     * @param driver
     * @param wait
     **/
    public static void getXpathPop(String tab, String text, String id, String xpath, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        String[] tabname=wait.until(presenceOfElementLocated(By.xpath("//div[@class='el-tabs__header']"))).getText().split("\n");
        int y = 0;
        int e = 0;
        for(int i=0;i<tabname.length;i++) {
            if(tabname[i].equalsIgnoreCase(tab)){
                y=i+1;
                break;
            }
        }
        if (!StringUtils.isNotBlank(text)){
            if(StringUtils.isNotBlank(xpath)) {
                WaitUntil.waitUntil(wait,xpath);
                driver.findElement(By.xpath(xpath)).click();
                Thread.sleep(1000);
            }else {
                logger.error("-----------text and xpath is null-----------");
                return;
            }
        } else{
            try{
                driver.findElement(By.xpath("//div/label[text()='" + text + "']/following-sibling::div//input")).click();
            }catch (Exception NotFoundException){
                driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/form/div/div/div/div/input[@placeholder='" + text + "']")).click();
            }
            Thread.sleep(1000);
        }

        comboxClick(id,driver,wait);
        /*
        //e = y+2;
        //String[] combox_text=wait.until(visibilityOf(driver.findElement(By.xpath("//body/div[" + e + "]/ul[@class='el-select-dropdown__list']")))).getText().split("\n");
        String[] combox_text;
        String bottom_xpath="//body/div[@x-placement='bottom-start']/descendant::ul";
        String top_xpath="//body/div[@x-placement='top-start']/descendant::ul";
        try{
            combox_text=driver.findElement(By.xpath(bottom_xpath)).getText().split("\n");
        }catch (Exception NotFoundException){
            combox_text=driver.findElement(By.xpath(top_xpath)).getText().split("\n");
        }
        //System.out.println("--------------------combox_text:" + combox_text);
        if (!StringUtils.isNotBlank(id)) {
            logger.error("-----------id is null-----------");
            return;
        } else if (id.contains("--")) {
            for (String ss : id.split("--")) {
                for(int i=0;i<combox_text.length;i++) {
                    if(combox_text[i].equalsIgnoreCase(ss)){
                        int j=i+1;
                        //Thread.sleep(1000);
                        //wait.until(visibilityOf(driver.findElement(By.xpath("//body/div[" + e + "]/ul[@class='el-select-dropdown__list']/li[" + j + "]")))).click();
                        try{
                            driver.findElement(By.xpath(bottom_xpath+"/li[" + j + "]")).click();
                        }catch (Exception NotFoundException){
                            driver.findElement(By.xpath(top_xpath+"/li[" + j + "]")).click();
                        }
                        Thread.sleep(500);
                        break;
                    }else if(i==combox_text.length-1 && !combox_text[i].equalsIgnoreCase(ss)){
                        logger.error("----can't find combox check----");
                    }
                }
            }
        } else {
            for(int i=0;i<combox_text.length;i++) {
                if(combox_text[i].equalsIgnoreCase(id)){
                    int j=i+1;
                    Thread.sleep(500);
                    //wait.until(visibilityOf(driver.findElement(By.xpath("//body/div[" + e + "]/ul[@class='el-select-dropdown__list']/li[" + j + "]")))).click();
                    try{
                        driver.findElement(By.xpath(bottom_xpath+"/li[" + j + "]")).click();
                    }catch (Exception NotFoundException){
                        driver.findElement(By.xpath(top_xpath+"/li[" + j + "]")).click();
                    }
                    Thread.sleep(500);
                    break;
                }else if(i==combox_text.length-1 && !combox_text[i].equalsIgnoreCase(id)){
                    logger.error("----can't find combox check----");
                }
            }
        }
        */
    }

    /**
     * 弹出框button操作
     * @param text 输入参数的名称
     * @param tab 操作的页签
     * @param driver
     * @param wait
     **/
    public static void getXpathPopBu(String tab, String text, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        String[] tabname=wait.until(presenceOfElementLocated(By.xpath("//div[@class='el-tabs__header']"))).getText().split("\n");
        int y = 0;
        for(int i=0;i<tabname.length;i++) {
            if(tabname[i].equalsIgnoreCase(tab)){
                y=i+1;
                break;
            }
        }
        if (!StringUtils.isNotBlank(text)){
            logger.error("-----------text is null-----------");
            return;
        } else{
            String bt_xpath = "//*[@id=\"app\"]//div[" + y + "]/form/div[2]/div/button/span[text()='" + text + "']";
            WaitUntil.waitUntil(wait,bt_xpath);
            driver.findElement(By.xpath(bt_xpath)).click();
            Thread.sleep(500);
        }
    }

    /**
     * 弹出框table操作
     * @param operation 操作类型
     * @param tab 操作的页签
     * @param row 行
     * @param column 列
     * @param driver
     * @param wait
     **/
    public static void getXpathPopTb(String tab, int row, String column, String operation, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        String[] tabname = wait.until(presenceOfElementLocated(By.xpath("//div[@class='el-tabs__header']"))).getText().split("\n");
        int y = 0;
        int z = 0;
        for (int i = 0; i < tabname.length; i++) {
            if (tabname[i].equalsIgnoreCase(tab)) {
                y = i + 1;
                break;
            }
        }

        String[] colname = new String[0];
        try{
            colname= driver.findElement(By.xpath("//div[@class='el-tabs__content']/div[" + y + "]/div/div/div/div/table")).getText().split(" ");
        }catch (Exception NotFoundException){
            NotFoundException.printStackTrace();
        }

        if (row <= 0 || !StringUtils.isNotBlank(column)) {
            logger.error("-----------id is null-----------");
            return;
        } else {
            for (int i = 0; i < colname.length; i++) {
                z = i + 1;
                if (column.contains("-") && column.contains(colname[i])) {
                    String[] sortname = column.split("-");
                    if(operation.equalsIgnoreCase("radio")){
                        if (sortname[1].equalsIgnoreCase("升序") || sortname[1].equalsIgnoreCase("是")) {
                            String sortxpasc = "//div[@class='el-tabs__content']/div[" + y + "]/div/div/div/div/table/tbody/tr[" + row + "]/td[" + z + "]/div/span//label[1]/span[1]/span";
                            if (wait.until(presenceOfElementLocated(By.xpath(sortxpasc))).isSelected() == false) {
                                driver.findElement(By.xpath(sortxpasc)).click();
                            }
                        } else if (sortname[1].equalsIgnoreCase("降序") || sortname[1].equalsIgnoreCase("否")) {
                            String sortxp = "//div[@class='el-tabs__content']/div[" + y + "]/div/div/div/div/table/tbody/tr[" + row + "]/td[" + z + "]/div/span//label[2]/span[1]/span";
                            if (wait.until(presenceOfElementLocated(By.xpath(sortxp))).isSelected() == false) {
                                driver.findElement(By.xpath(sortxp)).click();
                            }
                        }
                    }else if (operation.equalsIgnoreCase("button")) {
                        Thread.sleep(500);
                        driver.findElement(By.xpath("//div[@class='el-tabs__content']/div[" + y + "]/div/div/div/div/table/tbody/tr[" + row + "]/td[" + z + "]/descendant::button")).click();
                        Thread.sleep(500);
                    }else if(operation.equalsIgnoreCase("combox")){
                        Thread.sleep(500);
                        driver.findElement(By.xpath("//div[@class='el-tabs__content']/div[" + y + "]/div/div/div/div/table/tbody/tr[" + row + "]/td[" + z + "]/descendant::input")).click();
                        Thread.sleep(500);
                        String[] combox_text;
                        //=wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//body/div/ul[@class='el-select-dropdown__list']")))).getText().split("\n");
                        try{
                            combox_text=driver.findElement(By.xpath("//body/div[@x-placement='bottom-start']/descendant::ul")).getText().split("\n");
                        }catch (Exception NotFoundException){
                            combox_text=driver.findElement(By.xpath("//body/div[@x-placement='top-start']/descendant::ul")).getText().split("\n");
                        }
                        for(int c=0;c<combox_text.length;c++) {
                            if(combox_text[c].equalsIgnoreCase(sortname[1])){
                                int j=c+1;
                                Thread.sleep(500);
                                try{
                                    driver.findElement(By.xpath("//body/div[@x-placement='bottom-start']/descendant::ul/li[" + j + "]")).click();
                                }catch (Exception NotFoundException){
                                    driver.findElement(By.xpath("//body/div[@x-placement='top-start']/descendant::ul/li[" + j + "]")).click();
                                }
                                //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//body/div/ul[@class='el-select-dropdown__list']/li[" + j + "]")))).click();
                                Thread.sleep(500);
                                break;
                            }
                        }
                    }else if(operation.equalsIgnoreCase("input")){
                        try{
                            driver.findElement(By.xpath("//div[@class='el-tabs__content']/div[" + y + "]/div/div/div/div/table/tbody/tr[" + row + "]/td[" + z + "]//input")).sendKeys(sortname[1]);
                        }catch (Exception ss){
                            ss.printStackTrace();
                        }
                    }
                    break;
                } else if (colname[i].equalsIgnoreCase(column)) {
                    if (operation.equalsIgnoreCase("radio")) {
                        String sortxpasc = "//div[@class='el-tabs__content']/div[" + y + "]/div/div/div/div/table/tbody/tr[" + row + "]/td[" + z + "]/div/span/label[1]/span[1]/span";
                        if (wait.until(presenceOfElementLocated(By.xpath(sortxpasc))).isSelected() == false) {
                            driver.findElement(By.xpath(sortxpasc)).click();
                        }
                    } else if (operation.equalsIgnoreCase("button")) {
                        driver.findElement(By.xpath("//div[@class='el-tabs__content']/div[" + y + "]/div/div/div/div/table/tbody/tr[" + row + "]/td[" + z + "]/descendant::button")).click();
                    }
                    break;
                }
            }
        }
        Thread.sleep(500);
    }

    /**
     * 弹出框tree操作
     * @param operation 操作类型
     * @param tab 操作的页签
     * @param node 节点
     * @param driver
     * @param wait
     **/
    public static void getXpathPopTr(String tab, String node, String operation, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        String[] tabname=wait.until(presenceOfElementLocated(By.xpath("//div[@class='el-tabs__header']"))).getText().split("\n");
        int y = 0;
        int a = 0;
        for(int i=0;i<tabname.length;i++) {
            if(tabname[i].equalsIgnoreCase(tab)){
                y=i+1;
                break;
            }
        }
        if (!StringUtils.isNotBlank(node)){
            logger.error("-----------node is null-----------");
            return;
        } else {
            String[] addtreelist = new String[0];
            String[] deltreelist = new String[0];
            try{
                addtreelist=driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/div/div/div/div[1]/div/ul")).getText().split("\n");
            }catch (Exception e){
                logger.info("-----------add is null-----------");
            }
            try{
                deltreelist=driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/div/div/div/div[3]/div/ul")).getText().split("\n");
            }catch (Exception e){
                logger.info("-----------del is null-----------");
            }
            if(operation.equalsIgnoreCase("添加")){
                if(node.equalsIgnoreCase("可用字段")){
                    driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/div/div/div/div[1]/div/label/span/span")).click();
                    Thread.sleep(500);
                    driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/div/div/div/div[2]/span[1]/button")).click();
                    Thread.sleep(500);
                }else{
                    for(int s=0;s<addtreelist.length;s++) {
                        if(addtreelist[s].equalsIgnoreCase(node)){
                            a=s+1;
                            driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/div/div/div/div[1]/div/ul/li[" + a + "]/label/span/span")).click();
                            Thread.sleep(500);
                            driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/div/div/div/div[2]/span[1]/button")).click();
                            Thread.sleep(500);
                            break;
                        }else {
                            logger.error("-----------node is not exsist-----------");
                            return;
                        }
                    }
                }
            }else {
                for(int s=0;s<deltreelist.length;s++) {
                    if(deltreelist[s].equalsIgnoreCase(node)){
                        a=s+1;
                        break;
                    }
                }
                if(node.equalsIgnoreCase("已选字段")){
                    if(operation.equalsIgnoreCase("移除")){
                        driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/div/div/div/div[3]/div/label/span/span")).click();
                        Thread.sleep(500);
                        driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/div/div/div/div[2]/span[2]/button")).click();
                        Thread.sleep(500);
                    }else {
                        logger.error("-----------operation is not allow-----------");
                        return;
                    }
                }else if(a>0){
                    if(operation.equalsIgnoreCase("移除")){
                        driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/div/div/div/div[3]/div/ul/li[" + a + "]/label/span/span")).click();
                        Thread.sleep(500);
                        driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/div/div/div/div[2]/span[2]/button")).click();
                        Thread.sleep(500);
                    }else if(operation.equalsIgnoreCase("顶部")){
                        driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/div/div/div/div[3]/div/ul/li[" + a + "]/label/span/span")).click();
                        Thread.sleep(500);
                        driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/div/div/div/div[4]/span[1]/button")).click();
                        Thread.sleep(500);
                    }else if(operation.equalsIgnoreCase("向上")){
                        driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/div/div/div/div[3]/div/ul/li[" + a + "]/label/span/span")).click();
                        Thread.sleep(500);
                        driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/div/div/div/div[4]/span[2]/button")).click();
                        Thread.sleep(500);
                    }else if(operation.equalsIgnoreCase("向下")){
                        driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/div/div/div/div[3]/div/ul/li[" + a + "]/label/span/span")).click();
                        Thread.sleep(500);
                        driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/div/div/div/div[4]/span[3]/button")).click();
                        Thread.sleep(500);
                    }else if(operation.equalsIgnoreCase("底部")){
                        driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/div/div/div/div[3]/div/ul/li[" + a + "]/label/span/span")).click();
                        Thread.sleep(500);
                        driver.findElement(By.xpath("//*[@id=\"app\"]//div[" + y + "]/div/div/div/div[4]/span[4]/button")).click();
                        Thread.sleep(500);
                    }
                }else {
                    logger.info("-------node not exsist--------");
                }
            }
        }
    }


    /**
     * 无重复页签table操作
     * @param operation 操作类型
     * @param keys 参数值
     * @param row 行
     * @param column 列
     * @param driver
     * @param wait
     **/
    public static void getXpathTable(int row, String column,String operation,String keys, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        if ((row <= 0 || !StringUtils.isNotBlank(column)) && !column.equalsIgnoreCase("全选")) {
            logger.error("-----------id is null-----------");
            return;
        } else {
            //当前查询结果页
            try {
                String[] tbclm_first = new String[0];
                try {
                    WebElement eel = driver.findElement(By.xpath("//div[@class='el-table__header-wrapper']/table/thead/tr/th/div/label"));
                    if (eel.isEnabled()) {
                        tbclm_first = new String[]{""};
                    }
                } catch (Exception NotFoundExceptiona) {
                    logger.info("-----------------not exsist checkbox-----------------");
                }
                String tbclm_mmm = wait.until(presenceOfElementLocated(By.xpath("//div[@class='el-table__header-wrapper']/table/thead/tr"))).getAttribute("innerHTML");
                Document doc = Jsoup.parse(tbclm_mmm);
                //String tbclm_x = doc.getElementsByClass("cell").text();
                String[] tbclm_y = doc.getElementsByClass("cell").text().split(" ");
                String[] tbclm;
                if (tbclm_y.length < 2) {
                    tbclm = ArrayUtils.addAll(tbclm_first, tbclm_y);
                } else if (tbclm_y.length > 1 && tbclm_y[1].length() != 0) {
                    tbclm = ArrayUtils.addAll(tbclm_first, tbclm_y);
                } else {
                    tbclm = tbclm_y;
                }

                int y = 0;
                for (int i = 0; i < tbclm.length; i++) {
                    if (tbclm[i].length() == 0 && operation.equalsIgnoreCase("checkbox")) {
                        Thread.sleep(500);
                        y = i + 1;
                        if (column.equalsIgnoreCase("勾选")) {
                            try {
                                WebElement aa = driver.findElement(By.xpath("//div[@class='el-tabs__content']/div[1]/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/div/label"));
                                aa.click();
                            } catch (Exception NotFoundExceptiona) {
                                driver.findElement(By.xpath("//div[@class='el-table__fixed-body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/div/label")).click();
                            }
                        } else if (column.equalsIgnoreCase("全选")) {
                            try {
                                driver.findElement(By.xpath("//div[@class='el-tabs__content']/div[1]/descendant::div[@class='el-table__header-wrapper']/table/thead/tr/th[" + y + "]/div/label")).click();
                            } catch (Exception NotFoundExceptiona) {
                                driver.findElement(By.xpath("//div[@class='el-table__fixed-header-wrapper']/table/thead/tr/th[" + y + "]/div/label")).click();
                            }
                        } else {
                            driver.findElement(By.xpath("//div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/div/label")).click();
                        }
                        Thread.sleep(500);
                        break;
                    } else if (tbclm[i].equalsIgnoreCase(column) && tbclm[i].length() != 0) {
                        Thread.sleep(500);
                        y = i + 1;
                        if (operation.equalsIgnoreCase("checkbox")) {
                            driver.findElement(By.xpath("//div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/div/label")).click();
                        } else if (operation.equalsIgnoreCase("button")) {
                            try {
                                driver.findElement(By.xpath("//div[@class='el-table__fixed-right']/div[2]/table/tbody/tr[" + row + "]/td[" + y + "]/descendant::button/span[text()='" + column + "']")).click();
                            } catch (Exception eew) {
                                driver.findElement(By.xpath("//div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/descendant::button/span[text()='" + column + "']")).click();
                            }
                        } else if (operation.equalsIgnoreCase("combox")) {
                            try {
                                WebElement table_div = driver.findElement(By.xpath("//div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]"));
                                Actions actions = new Actions(driver);
                                actions.doubleClick(table_div).perform();
                            } catch (Exception NotFoundException) {
                                logger.info("----------------Can Not Double Click----------------");
                            }
                            String input_xpath = "//div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/descendant::input";
                            Boolean isreadonly = IsReadOnly.isreadonly(input_xpath, driver);
                            WebElement table_input = driver.findElement(By.xpath(input_xpath));
                            table_input.click();
                            Thread.sleep(1000);
                            if (!isreadonly) {
                                table_input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                                table_input.sendKeys(Keys.chord(Keys.DELETE));
                                Thread.sleep(1000);
                                table_input.sendKeys(keys);
                                Thread.sleep(1000);
                            }
                            //table_input.clear();

                            String[] combox_text;
                            try {
                                combox_text = driver.findElement(By.xpath("//body/div[@x-placement='bottom-start']/descendant::ul")).getText().split("\n");
                            } catch (Exception NotFoundException) {
                                combox_text = driver.findElement(By.xpath("//body/div[@x-placement='top-start']/descendant::ul")).getText().split("\n");
                            }
                            for (int c = 0; c < combox_text.length; c++) {
                                if (combox_text[c].equalsIgnoreCase(keys)) {
                                    int j = c + 1;
                                    Thread.sleep(500);
                                    try {
                                        driver.findElement(By.xpath("//body/div[@x-placement='bottom-start']/descendant::ul/li[" + j + "]")).click();
                                    } catch (Exception NotFoundException) {
                                        driver.findElement(By.xpath("//body/div[@x-placement='top-start']/descendant::ul/li[" + j + "]")).click();
                                    }
                                    Thread.sleep(500);
                                    break;
                                }
                            }
                        } else if (operation.equalsIgnoreCase("input")) {
                            driver.findElement(By.xpath("//div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/descendant::input")).sendKeys(keys);
                        } else {
                            logger.info("---------------------select table content unsupport operation--------------------");
                            return;
                        }
                        Thread.sleep(500);
                        break;
                    }
                }
            } catch (Exception ees) {
                ees.printStackTrace();
            }
        }
    }

    /**
     * 重复页签table操作
     * @param operation 操作类型
     * @param keys 参数值
     * @param row 行
     * @param column 列
     * @param tab 操作的页签
     * @param driver
     * @param wait
     **/
    public static void getXpathTableD(int row, String column,String operation,String keys, String tab,WebDriver driver, WebDriverWait wait) throws InterruptedException {
        if ((row<=0 || !StringUtils.isNotBlank(column)) && !column.equalsIgnoreCase("全选")){
            logger.error("-----------id is null-----------");
            return;
        } else {
            String[] tabletab = wait.until(presenceOfElementLocated(By.xpath("//div[@class='el-tabs__header']"))).getText().split("\n");
            int x=0;
            for(int s = 0; s < tabletab.length; s++){
                if(tab.equalsIgnoreCase(tabletab[s])){
                    Thread.sleep(500);
                    x=s+1;
                    break;
                }
            }
            //当前查询结果页
            try {
                //复选框判断
                String[] tbclm_first = new String[0];
                try {
                    WebElement eel = driver.findElement(By.xpath("//div[@class='el-tabs__content']/div[" + x + "]/div/div/descendant::div[@class='el-table__header-wrapper']/table/thead/tr/th/div/label"));
                    if (eel.isEnabled()) {
                        tbclm_first = new String[]{""};
                    }
                }catch (Exception NotFoundException){
                    logger.info("-----------------table not exsist checkbox-----------------");
                    NotFoundException.printStackTrace();
                }
                //获取表格列
                String tbclm_mmm = wait.until(presenceOfElementLocated(By.xpath("//div[@class='el-tabs__content']/div["+x+"]/div/div/descendant::div[@class='el-table__header-wrapper']/table/thead/tr"))).getAttribute("innerHTML");
                org.jsoup.nodes.Document doc = Jsoup.parse(tbclm_mmm);
                //String tbclm_x = doc.getElementsByClass("cell").text();
                String[] tbclm_y = doc.getElementsByClass("cell").text().split(" ");
                String[] tbclm;
                if(tbclm_y.length < 2){
                    tbclm = ArrayUtils.addAll(tbclm_first, tbclm_y);
                } else if(tbclm_y.length > 1 && tbclm_y[1].length() != 0 ){
                    tbclm = ArrayUtils.addAll(tbclm_first, tbclm_y);
                }else {
                    tbclm = tbclm_y;
                }
                //String[] tbclm = ArrayUtils.addAll(tbclm_first, tbclm_y);

                int y = 0;
                for (int i = 0; i < tbclm.length; i++) {
                    if(tbclm[i].length() == 0 && operation.equalsIgnoreCase("checkbox")){
                        Thread.sleep(500);
                        y = i + 1;
                        if (column.equalsIgnoreCase("勾选")) {
                            driver.findElement(By.xpath("//div[@class='el-tabs__content']/div["+x+"]/div/div/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/div/label")).click();
                        } else if (column.equalsIgnoreCase("全选")){
                            driver.findElement(By.xpath("//div[@class='el-tabs__content']/div["+x+"]/div/div/descendant::div[@class='el-table__header-wrapper']/table/thead/tr/th[" + y + "]/div/label")).click();
                        } else {
                            driver.findElement(By.xpath("//div[@class='el-tabs__content']/div["+x+"]/div/div/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/div/label")).click();
                        }
                        Thread.sleep(500);
                        break;
                    }
                    else if (tbclm[i].equalsIgnoreCase(column) && tbclm[i].length() != 0) {
                        Thread.sleep(500);
                        y = i + 1;
                        if (operation.equalsIgnoreCase("checkbox")) {
                            driver.findElement(By.xpath("//div[@class='el-tabs__content']/div["+x+"]/div/div/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/div/label")).click();
                        } else if (operation.equalsIgnoreCase("button")) {
                            driver.findElement(By.xpath("//div[@class='el-tabs__content']/div["+x+"]/div/div/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/descendant::button/span[text()='"+column+"']")).click();
                        }else if (operation.equalsIgnoreCase("combox")) {
                            try {
                                WebElement table_div = driver.findElement(By.xpath("//div[@class='el-tabs__content']/div["+x+"]/div/div/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]"));
                                Actions actions = new Actions(driver);
                                actions.doubleClick(table_div).perform();
                            } catch (Exception NotFoundException) {
                                logger.info("----------------Can Not Double Click----------------");
                            }
                            String input_xpath="//div[@class='el-tabs__content']/div["+x+"]/div/div/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/descendant::input";
                            Boolean isreadonly = IsReadOnly.isreadonly(input_xpath,driver);
                            WebElement table_input = driver.findElement(By.xpath(input_xpath));
                            table_input.click();
                            Thread.sleep(1000);
                            if(!isreadonly) {
                                table_input.clear();
                                Thread.sleep(1000);
                                table_input.sendKeys(keys);
                                Thread.sleep(1000);
                            }
                            String[] combox_text;
                            try {
                                combox_text = driver.findElement(By.xpath("//body/div[@x-placement='bottom-start']/descendant::ul")).getText().split("\n");
                            } catch (Exception NotFoundException) {
                                combox_text = driver.findElement(By.xpath("//body/div[@x-placement='top-start']/descendant::ul")).getText().split("\n");
                            }
                            for (int c = 0; c < combox_text.length; c++) {
                                if (combox_text[c].equalsIgnoreCase(keys)) {
                                    int j = c + 1;
                                    Thread.sleep(500);
                                    try {
                                        driver.findElement(By.xpath("//body/div[@x-placement='bottom-start']/descendant::ul/li[" + j + "]")).click();
                                    } catch (Exception NotFoundException) {
                                        driver.findElement(By.xpath("//body/div[@x-placement='top-start']/descendant::ul/li[" + j + "]")).click();
                                    }
                                    Thread.sleep(500);
                                    break;
                                }
                            }
                        } else if (operation.equalsIgnoreCase("input")) {
                            driver.findElement(By.xpath("//div[@class='el-tabs__content']/div["+x+"]/div/div/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/descendant::input")).sendKeys(keys);
                        } else {
                            logger.info("---------------------select table content unsupport operation--------------------");
                            return;
                        }
                        Thread.sleep(500);
                        break;
                    }
                }
            }catch (Exception ees){
                ees.printStackTrace();
            }
        }
    }
}
