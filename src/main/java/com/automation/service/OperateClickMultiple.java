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
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class OperateClickMultiple {
    private static Logger logger = Logger.getLogger(OperateClickMultiple.class);

    public static String xpathCreate(String text){
        String xpath_value = null;
        if(!StringUtils.isNotBlank(text)){
            logger.error("number is null");
        } else if (text.contains("-")) {
            String[] textvalue = text.split("-");
            for (int i=0;i<textvalue.length;i++) {
                try{
                    int ii = Integer.parseInt(textvalue[i]);
                    String ss_xpath = "/div["+ii+"]";
                    xpath_value = xpath_value + ss_xpath;
                }catch (Exception ii){
                    ii.printStackTrace();
                }
            }
        } else {
            try{
                int ii = Integer.parseInt(text);
                xpath_value = "/div["+ii+"]";
            }catch (Exception ii){
                ii.printStackTrace();
            }
        }
        return xpath_value;
    }

    /**
     * 多DIV 普通点击
     * @param text 输入参数的名称
     * @param type 输入类型
     * @param x 位置参数
     * @param driver
     * @param wait
     **/
    public static void getXpathClick(String text, String type, String x, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        String xpath_value = xpathCreate(x);
        if (!StringUtils.isNotBlank(text)){
            logger.error("-----------text is null-----------");
            return;
        }else if (StringUtils.isNotBlank(type)) {
            if (type.equalsIgnoreCase("tab")) {
                if (text.contains("-")) {
                    for (String ss : text.split("-")) {
                        Thread.sleep(500);
                        String ss_xpath = "//div[text()='" + ss + "']";
                        WaitUntil.waitUntil(wait, ss_xpath);
                        driver.findElement(By.xpath(ss_xpath)).click();
                        Thread.sleep(500);
                    }
                } else {
                    Thread.sleep(500);
                    String tab_xpath = "//div[text()='" + text + "']";
                    WaitUntil.waitUntil(wait, tab_xpath);
                    driver.findElement(By.xpath(tab_xpath)).click();
                    Thread.sleep(500);
                }
            } else if (type.equalsIgnoreCase("button")) {
                if (text.equals("编辑")) {
                    String xpath = "//div[@class='el-row']" + xpath_value + "/descendant::div[@class='el-button-group']/button[@class='el-button el-button--primary el-button--small']/span[tesx()='编辑']";
                    WaitUntil.waitUntil(wait, xpath);
                    driver.findElement(By.xpath(xpath)).click();
                    Thread.sleep(500);
                } else if (text.contains("视图管理-")) {
                    int y = 0;
                    String[] viewop = text.split("-");
                    String xpath = "//div[@class='el-row']" + xpath_value + "/descendant::button[@class='el-button el-button--primary el-button--small el-dropdown__caret-button']/span";
                    WaitUntil.waitUntil(wait, xpath);
                    driver.findElement(By.xpath(xpath)).click();
                    String combox_xpath = "//body/ul[@x-placement='bottom-end']";
                    WaitUntil.waitUntil(wait, combox_xpath);
                    String[] combox_text = driver.findElement(By.xpath(combox_xpath)).getText().split("\n");
                    for (int i = 0; i < combox_text.length; i++) {
                        y = i + 1;
                        if (viewop[1].equalsIgnoreCase(combox_text[i])) {
                            if (driver.findElement(By.xpath(combox_xpath + "/li[" + y + "]")).isEnabled()) {
                                driver.findElement(By.xpath(combox_xpath + "/li[" + y + "]")).click();
                            }
                            break;
                        }
                    }
                } else if (text.contains("操作-")) {
                    int y = 0;
                    String[] viewop = text.split("-");
                    String xpath = "//div[@class='el-row']" + xpath_value + "/descendant::button/span[text()='" + viewop[0] + "']";
                    WaitUntil.waitUntil(wait, xpath);
                    driver.findElement(By.xpath(xpath)).click();
                    String combox_xpath = "//body/ul[@x-placement='bottom-end']";
                    WaitUntil.waitUntil(wait, combox_xpath);
                    String[] combox_text = driver.findElement(By.xpath(combox_xpath)).getText().split("\n");
                    for (int i = 0; i < combox_text.length; i++) {
                        y = i + 1;
                        if (viewop[1].equalsIgnoreCase(combox_text[i])) {
                            Thread.sleep(500);
                            if (driver.findElement(By.xpath(combox_xpath + "/li[" + y + "]")).isEnabled()) {
                                driver.findElement(By.xpath(combox_xpath + "/li[" + y + "]")).click();
                            }
                            break;
                        }
                    }
                } else if (text.equalsIgnoreCase("关闭弹出框")) {
                    try {
                        driver.findElement(By.xpath("//div[@class='el-dialog__headerbtn']/i")).click();
                    } catch (Exception NotfoundException) {
                        NotfoundException.printStackTrace();
                    }
                } else if (text.equalsIgnoreCase("关闭提示")) {
                    try {
                        driver.findElement(By.xpath("//div[@class='el-message__closeBtn el-icon-close']")).click();
                    } catch (Exception NotfoundException) {
                        NotfoundException.printStackTrace();
                    }
                } else if (text.equalsIgnoreCase("上一页")) {
                    try {
                        String xpath = "//div[@class='el-row']" + xpath_value + "/descendant::button[@class='btn-prev']";
                        if (wait.until(presenceOfElementLocated(By.xpath(xpath))).isEnabled()) {
                            driver.findElement(By.xpath(xpath)).click();
                        }
                    } catch (Exception e) {
                        logger.info("---------prev button can't click--------");
                    }
                } else if (text.equalsIgnoreCase("下一页")) {
                    try {
                        String xpath = "//div[@class='el-row']" + xpath_value + "/descendant::button[@class='btn-next']";
                        if (wait.until(presenceOfElementLocated(By.xpath(xpath))).isEnabled()) {
                            driver.findElement(By.xpath(xpath)).click();
                        }
                    } catch (Exception e) {
                        logger.info("---------next button can't click--------");
                    }
                } else {
                    String xpath = "//div[@class='el-row']" + xpath_value + "/descendant::button/span[text()='" + text + "']";
                    WaitUntil.waitUntil(wait, xpath);
                    driver.findElement(By.xpath(xpath)).click();
                }
            } else if (type.equalsIgnoreCase("xpath")) {
                WaitUntil.waitUntil(wait, text);
                driver.findElement(By.xpath(text)).click();
            }
        }
        else {
            String xpath = "//div[@class='el-row']"+xpath_value+"/descendant::span[text()='" + text + "']";
            driver.findElement(By.xpath(xpath)).click();
        }
        Thread.sleep(500);
    }

    /**
     * 多DIV 点击多选框
     * @param text 输入参数的名称
     * @param x 位置参数
     * @param driver
     * @param wait
     **/
    public static void clickCheckbox(String text,String x,WebDriver driver, WebDriverWait wait) throws InterruptedException {
        String xpath_value = xpathCreate(x);
        if (!StringUtils.isNotBlank(text)){
            logger.error("-----------text is null-----------");
            return;
        } else {
            String checkbox_xpath = "//div[@class='el-row']"+xpath_value+"/descendant::label/span[text()='" + text + "']/preceding-sibling::span/span";
            WaitUntil.waitUntil(wait,checkbox_xpath);
            driver.findElement(By.xpath(checkbox_xpath)).click();
            Thread.sleep(500);
        }
    }


    /**
     * 多DIV 下拉框操作
     * @param text 输入参数的名称
     * @param id 下拉框要选择的值
     * @param xpath
     * @param x 位置参数
     * @param driver
     * @param wait
     **/
    public static void getXpathForm(String text, String id, String xpath,String x, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        String xpath_value = xpathCreate(x);
        if (!StringUtils.isNotBlank(text)){
            if(StringUtils.isNotBlank(xpath)) {
                wait.until(visibilityOf(driver.findElement(By.xpath(xpath)))).click();
            }else {
                logger.error("-----------text and xpath is null-----------");
                return;
            }
        } else {
            String text_xpath = "//div[@class='el-row']"+xpath_value+"/descendant::input[@placeholder='" + text + "']";
            Boolean isexesist = WaitUntil.waitUntilExesist(wait,text_xpath);
            Boolean clickenable;
            Boolean isredonly;
            if(isexesist) {
                clickenable = WaitUntil.waitUntilisEnable(wait, text_xpath);
                isredonly = IsReadOnly.isreadonly(text_xpath, driver);
                if (clickenable) {
                    driver.findElement(By.xpath(text_xpath)).click();
                    Thread.sleep(500);
                    if (!isredonly) {
                        try {
                            driver.findElement(By.xpath(text_xpath)).clear();
                            Thread.sleep(500);
                            driver.findElement(By.xpath(text_xpath)).sendKeys(id);
                            Thread.sleep(500);
                        } catch (Exception cc) {
                            logger.info("---------input can't clear---------");
                        }
                    }
                    OperateClick.comboxClick(id,driver,wait);
                }
            } else {
                String lable_xpath = "//div[@class='el-row']" + xpath_value + "/descendant::label[text()='" + text + "']/following-sibling::div/descendant::input";
                clickenable = WaitUntil.waitUntilisEnable(wait, lable_xpath);
                isredonly = IsReadOnly.isreadonly(lable_xpath, driver);
                if (clickenable) {
                    driver.findElement(By.xpath(lable_xpath)).click();
                    Thread.sleep(500);
                    if (!isredonly) {
                        try {
                            //wait.until(visibilityOf(driver.findElement(By.xpath("//div/label[text()='" + text + "']/following-sibling::div//input")))).clear();
                            WebElement conbox_clear = driver.findElement(By.xpath(lable_xpath));
                            Actions actions = new Actions(driver);
                            actions.doubleClick(conbox_clear).perform();
                            conbox_clear.sendKeys(Keys.DELETE);
                            Thread.sleep(500);
                            conbox_clear.sendKeys(id);
                            Thread.sleep(500);
                        } catch (Exception cc) {
                            logger.info("---------input can't clear---------");
                        }
                    }
                    OperateClick.comboxClick(id, driver, wait);
                }
            }
        }

        /*
        Thread.sleep(500);
        String[] combox_text;
        String bottom_xpath="//body/div[@x-placement='bottom-start']/descendant::ul";
        String top_xpath="//body/div[@x-placement='top-start']/descendant::ul";
        try {
            WaitUntil.waitUntil(wait,bottom_xpath);
            combox_text=driver.findElement(By.xpath(bottom_xpath)).getText().split("\n");
        }catch (Exception NotfoundException){
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
                        Thread.sleep(1000);
                        try{
                            driver.findElement(By.xpath(bottom_xpath+"/li[" + j + "]")).click();
                        }catch (Exception NotfoundException){
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
                    Thread.sleep(1000);
                    try{
                        driver.findElement(By.xpath(bottom_xpath+"/li[" + j + "]")).click();
                    }catch(Exception NotfoundException){
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
     * 多DIV 点击列头排序
     * @param text 输入参数的名称
     * @param id 序号，sort_up为1，sort_down为2
     * @param x 位置参数
     * @param driver
     * @param wait
     **/
    public static void getXpathSort(String text, int id, String x,WebDriver driver, WebDriverWait wait) throws InterruptedException {
        String xpath_value = xpathCreate(x);
        if (!StringUtils.isNotBlank(text) || id <=0){
            logger.error("-----------text is null-----------");
            return;
        } else {
            String sort_xpath = "//div[@class='el-row']"+xpath_value+"/descendant::div[text()='" + text + "']/span/i[" + id +"]";
            WaitUntil.waitUntil(wait,sort_xpath);
            driver.findElement(By.xpath(sort_xpath)).click();
            Thread.sleep(500);
        }
    }

    /**
     * 多DIV 多个重复页签按钮点击
     * @param text 输入参数的名称
     * @param type 操作类型
     * @param tab 操作的页签
     * @param xx 位置参数
     * @param driver
     * @param wait
     **/
    public static void getXpathClickD(String text, String type,String tab, String xx,WebDriver driver, WebDriverWait wait) throws InterruptedException {
        String xpath_value = xpathCreate(xx);
        if (!StringUtils.isNotBlank(text)) {
            logger.error("-----------text or type is null-----------");
            return;
        }else if (type.equalsIgnoreCase("button")){
            String tabletab_xpath = "//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-tabs__header']";
            WaitUntil.waitUntil(wait,tabletab_xpath);
            String[] tabletab = wait.until(presenceOfElementLocated(By.xpath(tabletab_xpath))).getText().split("\n");
            int x=0;
            for(int s = 0; s < tabletab.length; s++){
                if(tab.equalsIgnoreCase(tabletab[s])){
                    Thread.sleep(500);
                    x=s+1;
                    break;
                }
            }
            String bt_xpath = "//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-tabs__content']/div["+x+"]/descendant::button/span[text()='" + text + "']";
            WebElement bt = driver.findElement(By.xpath(bt_xpath));
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
     * 多DIV 无重复页签table操作
     * @param operation 操作类型
     * @param keys 参数值
     * @param row 行
     * @param column 列
     * @param xx 位置参数
     * @param driver
     * @param wait
     **/
    public static void getXpathTable(int row, String column,String operation,String keys, String xx,WebDriver driver, WebDriverWait wait) throws InterruptedException {
        String xpath_value = xpathCreate(xx);
        if ((row<=0 || !StringUtils.isNotBlank(column)) && !column.equalsIgnoreCase("全选")){
            logger.error("-----------id is null-----------");
            return;
        } else {
            //当前查询结果页
            try {
                String[] tbclm_first = new String[0];
                try{
                    String eel_xpath = "//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-table__header-wrapper']/table/thead/tr/th/div/label";
                    WaitUntil.waitUntil(wait,eel_xpath);
                    WebElement eel = driver.findElement(By.xpath(eel_xpath));
                    if(eel.isEnabled()) {
                        tbclm_first = new String[]{""};
                    }
                }catch (Exception NotFoundExceptiona){
                    logger.info("-----------------not exsist checkbox-----------------");
                }
                Thread.sleep(500);
                String tbclm_mmm_xpath = "//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-table__header-wrapper']/table/thead/tr";
                String tbclm_mmm = wait.until(presenceOfElementLocated(By.xpath(tbclm_mmm_xpath))).getAttribute("innerHTML");
                Document doc = Jsoup.parse(tbclm_mmm);
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

                int y = 0;
                for (int i = 0; i < tbclm.length; i++) {
                    if(tbclm[i].length() == 0 && operation.equalsIgnoreCase("checkbox")){
                        Thread.sleep(500);
                        y = i + 1;
                        if (column.equalsIgnoreCase("勾选")) {
                            try{
                                WebElement aa = driver.findElement(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-tabs__content']/div[1]/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/div/label"));
                                aa.click();
                            }catch (Exception NotFoundExceptiona){
                                driver.findElement(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-table__fixed-body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/div/label")).click();
                            }
                        } else if (column.equalsIgnoreCase("全选")){
                            try{
                                driver.findElement(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-tabs__content']/div[1]/descendant::div[@class='el-table__header-wrapper']/table/thead/tr/th[" + y + "]/div/label")).click();
                            }catch (Exception NotFoundExceptiona){
                                driver.findElement(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-table__fixed-header-wrapper']/table/thead/tr/th[" + y + "]/div/label")).click();
                            }
                        } else {
                            driver.findElement(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/div/label")).click();
                        }
                        Thread.sleep(500);
                        break;
                    }
                    else if (tbclm[i].equalsIgnoreCase(column) && tbclm[i].length() != 0) {
                        Thread.sleep(500);
                        y = i + 1;
                        if (operation.equalsIgnoreCase("checkbox")) {
                            driver.findElement(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/div/label")).click();
                        } else if (operation.equalsIgnoreCase("button")) {
                            try {
                                driver.findElement(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-table__fixed-right']/div[2]/table/tbody/tr[" + row + "]/td[" + y + "]/descendant::button/span[text()='"+column+"']")).click();
                            } catch (Exception eew) {
                                driver.findElement(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/descendant::button/span[text()='"+column+"']")).click();
                            }
                        }else if (operation.equalsIgnoreCase("combox")) {
                            try {
                                WebElement table_div = driver.findElement(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]"));
                                Actions actions = new Actions(driver);
                                actions.doubleClick(table_div).perform();
                            } catch (Exception NotFoundException) {
                                logger.info("----------------Can Not Double Click----------------");
                            }
                            String input_xpath = "//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/descendant::input";
                            Boolean isreadonly = IsReadOnly.isreadonly(input_xpath, driver);
                            WebElement table_input = driver.findElement(By.xpath(input_xpath));
                            table_input.click();
                            Thread.sleep(1000);
                            if(!isreadonly) {
                                table_input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                                table_input.sendKeys(Keys.chord(Keys.DELETE));
                                //table_input.clear();
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
                            driver.findElement(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/descendant::input")).sendKeys(keys);
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

    /**
     * 多DIV 重复页签table操作
     * @param operation 操作类型
     * @param keys 参数值
     * @param row 行
     * @param column 列
     * @param tab 操作的页签
     * @param xx 位置参数
     * @param driver
     * @param wait
     **/
    public static void getXpathTableD(int row, String column,String operation,String keys, String tab,String xx,WebDriver driver, WebDriverWait wait) throws InterruptedException {
        String xpath_value = xpathCreate(xx);
        if ((row<=0 || !StringUtils.isNotBlank(column)) && !column.equalsIgnoreCase("全选")){
            logger.error("-----------id is null-----------");
            return;
        } else {
            String[] tabletab = wait.until(presenceOfElementLocated(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-tabs__header']"))).getText().split("\n");
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
                    WebElement eel = driver.findElement(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-tabs__content']/div[" + x + "]/div/div/descendant::div[@class='el-table__header-wrapper']/table/thead/tr/th/div/label"));
                    if (eel.isEnabled()) {
                        tbclm_first = new String[]{""};
                    }
                }catch (Exception NotFoundException){
                    logger.info("-----------------table not exsist checkbox-----------------");
                    NotFoundException.printStackTrace();
                }
                //获取表格列
                String tbclm_mmm = wait.until(presenceOfElementLocated(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-tabs__content']/div["+x+"]/div/div/descendant::div[@class='el-table__header-wrapper']/table/thead/tr"))).getAttribute("innerHTML");
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
                            driver.findElement(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-tabs__content']/div["+x+"]/div/div/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/div/label")).click();
                        } else if (column.equalsIgnoreCase("全选")){
                            driver.findElement(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-tabs__content']/div["+x+"]/div/div/descendant::div[@class='el-table__header-wrapper']/table/thead/tr/th[" + y + "]/div/label")).click();
                        } else {
                            driver.findElement(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-tabs__content']/div["+x+"]/div/div/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/div/label")).click();
                        }
                        Thread.sleep(500);
                        break;
                    }
                    else if (tbclm[i].equalsIgnoreCase(column) && tbclm[i].length() != 0) {
                        Thread.sleep(500);
                        y = i + 1;
                        if (operation.equalsIgnoreCase("checkbox")) {
                            driver.findElement(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-tabs__content']/div["+x+"]/div/div/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/div/label")).click();
                        } else if (operation.equalsIgnoreCase("button")) {
                            driver.findElement(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-tabs__content']/div["+x+"]/div/div/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/descendant::button/span[text()='"+column+"']")).click();
                        }else if (operation.equalsIgnoreCase("combox")) {
                            try {
                                WebElement table_div = driver.findElement(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-tabs__content']/div["+x+"]/div/div/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]"));
                                Actions actions = new Actions(driver);
                                actions.doubleClick(table_div).perform();
                            } catch (Exception NotFoundException) {
                                logger.info("----------------Can Not Double Click----------------");
                            }
                            String input_xpath = "//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-tabs__content']/div["+x+"]/div/div/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/descendant::input";
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
                            driver.findElement(By.xpath("//div[@class='el-row']"+xpath_value+"/descendant::div[@class='el-tabs__content']/div["+x+"]/div/div/descendant::div[@class='el-table__body-wrapper']/table/tbody/tr[" + row + "]/td[" + y + "]/descendant::input")).sendKeys(keys);
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
