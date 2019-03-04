package com.automation.service;

import com.automation.model.ReadProject;
import com.automation.until.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import java.io.File;

public class SwitchOperate {
    private static Logger logger = Logger.getLogger(SwitchOperate.class);
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static String asmsg = "-1";
    public static String beginlog = null;
    public static String logfilepath = null;
    public static void switchOperate(String elementname, String url, String name, String keys, String clicktext,
                                     String gettext, String expect, String sql, String type, String row, String column, String content,
                                     String xpath, String level, String comboxid, String poptab, String tableoperation, String node,
                                     String nodeop, String multiplediv, String tab, String postgresql,
                                     String filename,
                                     WebDriver driver,WebDriverWait wait,String pjname){
        //String asmsg = "-1";//断言默认值
        //String beginlog = null;//日志断言初始日志
        //String logfilepath = null;
        ScreenShot.driver = driver;//失败截图需要传入driver
        ScreenShot sc = new ScreenShot();
        try {
            Thread.sleep(500);
            switch (elementname) {
                case "url":
                    if (!StringUtils.isNotBlank(url)) {
                        logger.error("-----------request URL is null-----------");
                        return;
                    } else {
                        try {
                            driver.get(url);
                            driver.manage().window().maximize();
                        } catch (Exception UrlException) {
                            logger.error(UrlException);
                            Assertion.flag = false;
                            sc.takeScreenShot(filename);
                        }
                    }
                    break;
                case "input":
                    try {
                        if (StringUtils.isNotBlank(multiplediv)) {
                            OperateInput.getXpathSendkeysMultiple(name, keys, type, multiplediv, driver, wait);
                        } else {
                            OperateInput.getXpathSendkeys(name, keys, xpath, type, driver, wait);
                        }
                    } catch (Exception InputException) {
                        logger.error(InputException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                case "textarea":
                    try {
                        if (StringUtils.isNotBlank(multiplediv)) {
                            OperateTextarea.getTextareaSendkeysMultiple(name, keys, xpath, multiplediv, driver, wait);
                        } else {
                            OperateTextarea.getTextareaSendkeys(name, keys, xpath, driver, wait);
                        }
                    } catch (Exception TextareaException) {
                        logger.error(TextareaException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                case "upload":
                    try {
                        OperateUpload.upload(name, keys, xpath, driver, wait);
                    } catch (Exception UploadException) {
                        logger.error(UploadException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                case "wait":
                    try {
                        Thread.sleep(Integer.parseInt(gettext));
                    } catch (Exception WaitException) {
                        logger.error(WaitException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                case "caseFile":
                    //File newfile = new File(System.getProperty("user.dir") + File.separator + "testcase" + File.separator + "sharecase" + File.separator + gettext);
                    try {
                        File newfile = OperateCaseFile.caseFile(gettext);
                        if (newfile != null) {
                            AutoOperational.executeAutoment(newfile, driver, wait,pjname);
                        }
                    }catch (Exception caseFileEx){
                        logger.error(caseFileEx);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                case "dataFile":
                    logger.info("----this is dataFile----");
                    break;
                case "click":
                    try {
                        if(StringUtils.isNotBlank(type)){
                            if (StringUtils.isNotBlank(multiplediv)) {
                            //多div操作
                                if (type.equalsIgnoreCase("table") && !StringUtils.isNotBlank(poptab)) {
                                    if (StringUtils.isNotBlank(tab)) {
                                        //存在重复页签table操作
                                        OperateClickMultiple.getXpathTableD(Integer.parseInt(row), column, tableoperation, keys, tab, multiplediv, driver, wait);
                                    } else {
                                        //无重复页签的table操作
                                        OperateClickMultiple.getXpathTable(Integer.parseInt(row), column, tableoperation, keys, multiplediv, driver, wait);
                                    }
                                }
                            /*
                            else if (StringUtils.isNotBlank(poptab) && poptab.equalsIgnoreCase("null") == false) {
                                //弹出窗操作
                                if (type.equalsIgnoreCase("combox")) {
                                    MultipleDiv.getXpathPop(poptab, clicktext, comboxid,xpath,Integer.parseInt(multiplediv),driver,wait);
                                } else if (type.equalsIgnoreCase("button")) {
                                    MultipleDiv.getXpathPopBu(poptab, clicktext,Integer.parseInt(multiplediv),driver,wait);
                                } else if (type.equalsIgnoreCase("table")) {
                                    MultipleDiv.getXpathPopTb(poptab, Integer.parseInt(row), column, tableoperation,Integer.parseInt(multiplediv),driver,wait);
                                } else if (type.equalsIgnoreCase("tree")) {
                                    OperationalDiv.getXpathPopTr(poptab, node, nodeop,driver,wait);
                                }
                            }
                            */
                                else {
                                    //if (StringUtils.isNotBlank(clicktext)) {
                                        if (type.equalsIgnoreCase("sort_up")) {
                                            OperateClickMultiple.getXpathSort(clicktext, 1, multiplediv, driver, wait);
                                        } else if (type.equalsIgnoreCase("sort_down")) {
                                            OperateClickMultiple.getXpathSort(clicktext, 2, multiplediv, driver, wait);
                                        } else if (type.equalsIgnoreCase("combox")) {
                                            OperateClickMultiple.getXpathForm(clicktext, comboxid, xpath, multiplediv, driver, wait);
                                        } else if (type.equalsIgnoreCase("checkbox")) {
                                            OperateClickMultiple.clickCheckbox(clicktext, multiplediv, driver, wait);
                                        } else {
                                            OperateClickMultiple.getXpathClick(clicktext, type, multiplediv, driver, wait);
                                        }
                                    //}
                                }
                        } else {//单div操作
                            if (type.equalsIgnoreCase("table") && !StringUtils.isNotBlank(poptab)) {
                                //不是弹出框中的table
                                if (StringUtils.isNotBlank(tab)) {
                                    //存在重复页签table操作
                                    OperateClick.getXpathTableD(Integer.parseInt(row), column, tableoperation, keys, tab, driver, wait);
                                } else {
                                    //无重复页签的table操作
                                    OperateClick.getXpathTable(Integer.parseInt(row), column, tableoperation, keys, driver, wait);
                                }
                            } else if (StringUtils.isNotBlank(poptab)) {
                                //弹出窗操作
                                if (type.equalsIgnoreCase("combox")) {
                                    OperateClick.getXpathPop(poptab, clicktext, comboxid, xpath, driver, wait);
                                } else if (type.equalsIgnoreCase("button")) {
                                    OperateClick.getXpathPopBu(poptab, clicktext, driver, wait);
                                } else if (type.equalsIgnoreCase("table")) {
                                    OperateClick.getXpathPopTb(poptab, Integer.parseInt(row), column, tableoperation, driver, wait);
                                } else if (type.equalsIgnoreCase("tree")) {
                                    OperateClick.getXpathPopTr(poptab, node, nodeop, driver, wait);
                                }
                            } else {
                                if (StringUtils.isNotBlank(tab)) {
                                    //重复页签按钮点击
                                    OperateClick.getXpathClickD(clicktext, type, tab, driver, wait);
                                } else {
                                    //普通操作
                                    if (StringUtils.isNotBlank(clicktext)) {
                                        if (type.equalsIgnoreCase("sort_up")) {
                                            OperateClick.getXpathSort(clicktext, 1, driver, wait);
                                        } else if (type.equalsIgnoreCase("sort_down")) {
                                            OperateClick.getXpathSort(clicktext, 2, driver, wait);
                                        } else if (type.equalsIgnoreCase("combox")) {
                                            OperateClick.getXpathForm(clicktext, comboxid, xpath, driver, wait);
                                        } else if (type.equalsIgnoreCase("checkbox")) {
                                            OperateClick.clickCheckbox(clicktext, driver, wait);
                                        } else {
                                            OperateClick.getXpathClick(clicktext, type, driver, wait);
                                        }
                                    }
                                }
                            }
                        }
                        }else if(!StringUtils.isNotBlank(clicktext)){
                            Actions actions = new Actions(driver);
                            actions.moveByOffset(0, 0).click().build().perform();
                        }else {
                            if (StringUtils.isNotBlank(multiplediv)){
                                OperateClickMultiple.getXpathClick(clicktext, type, multiplediv, driver, wait);
                            }else {
                                OperateClick.getXpathClick(clicktext, type, driver, wait);
                            }
                        }
                    } catch (Exception ClickException) {
                        logger.error(ClickException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                case "iframe":
                    try {
                        OperateIframe.switchIframe(gettext, driver, wait);
                    } catch (Exception IframeException) {
                        logger.error(IframeException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                case "log":
                    try {
                        if (!StringUtils.isNotBlank(level)) {
                            logger.error("-----------log level is null-----------");
                            return;
                        } else if (level.equalsIgnoreCase("debug")) {
                            logger.debug(gettext);
                            Reporter.log(gettext);
                        } else if (level.equalsIgnoreCase("info")) {
                            logger.info(gettext);
                            Reporter.log(gettext);
                        } else if (level.equalsIgnoreCase("error")) {
                            logger.error(gettext);
                            Reporter.log(gettext);
                        }
                    } catch (Exception LogException) {
                        logger.error(LogException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                case "dbsql":
                    try {
                        String dbsql = null;
                        if (CurrentSql.curentsql().equalsIgnoreCase("postgresql")) {
                            dbsql = postgresql;
                        } else {
                            dbsql = sql;
                        }

                        if (dbsql.startsWith("s") || dbsql.startsWith("S")) {
                            //asmsg = PostgreSqlConnect.sqlQueryCount(dbsql);
                            asmsg = DbTestDru.selectRows(dbsql,pjname);
                        } else {
                            //PostgreSqlConnect exc = new PostgreSqlConnect();
                            //exc.dbsql(dbsql);
                            DbTestDru exc = new DbTestDru();
                            exc.dbTestDu(dbsql,pjname);
                        }
                    } catch (Exception DbsqlException) {
                        logger.error(DbsqlException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                case "scroll"://已实现但使用不方便，滚动条不会影响自动化测试，所以暂时不使用
                    try {
                        if (!StringUtils.isNotBlank(type)) {
                            return;
                        } else {
                            OperateScroll.scrollOperation(type, gettext, driver, wait);
                        }
                    } catch (Exception ScrollException) {
                        ScrollException.printStackTrace();
                        Assertion.flag = false;
                    }
                    break;
                case "shellexec":
                    try {
                        if (!StringUtils.isNotBlank(keys)) {
                            return;
                        } else {
                            ShellExec.shellexecOperation(keys, gettext,pjname);
                        }
                    } catch (Exception ShellexecException) {
                        logger.error(ShellexecException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                case "gettext":
                    if (!StringUtils.isNotBlank(gettext)) {
                        logger.error("-----------request xpath is null-----------");
                        return;
                    } else {
                        try {
                            asmsg = OperateGettext.getText(gettext, driver, wait);
                        } catch (Exception GettextException) {
                            logger.error(GettextException);
                            Assertion.flag = false;
                            sc.takeScreenShot(filename);
                        }
                    }
                    break;
                case "gettexttable":
                    try {
                        if (StringUtils.isNotBlank(multiplediv)) {
                            asmsg = OperateGettextTable.getTextTableMultiple(Integer.parseInt(row), column, content, multiplediv, driver, wait);
                            Thread.sleep(500);
                        } else {
                            asmsg = OperateGettextTable.getTextTable(Integer.parseInt(row), column, content, driver, wait);
                        }
                    } catch (Error GettexttableException) {
                        logger.error(GettexttableException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                case "geturl":
                    try {
                        asmsg = driver.getCurrentUrl();
                    } catch (Exception GeturlException) {
                        logger.error(GeturlException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                case "getnotice":
                    try {
                        String notice_xpath = "//body/div[contains(@class,'el-message el-message--')][last()]/p";
                        WebDriverWait wait_notice = new WebDriverWait(driver, 10);
                        WaitUntil.waitUntil(wait_notice, notice_xpath);
                        asmsg = driver.findElement(By.xpath(notice_xpath)).getText();
                    } catch (Exception GetnoticeException) {
                        logger.error(GetnoticeException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                case "asserttext":
                    try {
                        if (!StringUtils.isNotBlank(xpath)) {
                            logger.error("-----------request xpath is null-----------");
                        } else {
                            asmsg = xpath;
                        }
                        AssertText.assertText(asmsg,expect,type,filename,driver,wait);
                    } catch (Exception assertException) {
                        logger.error(assertException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                case "assertgrid":
                    try {
                        String grid_msg = null;
                        if (StringUtils.isNotBlank(multiplediv)) {
                            grid_msg = OperateGettextTable.getTextTableMultiple(Integer.parseInt(row), column, content, multiplediv, driver, wait);
                            Thread.sleep(500);
                        } else {
                            grid_msg = OperateGettextTable.getTextTable(Integer.parseInt(row), column, content, driver, wait);
                        }
                        AssertText.asstext(grid_msg,expect,filename);
                    } catch (Exception assertException) {
                        logger.error(assertException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                case "assertstatus":
                    try {
                        if (!StringUtils.isNotBlank(name) || !StringUtils.isNotBlank(type)) {
                            logger.error("-----------request input is null-----------");
                            return;
                        } else {
                            if(expect.equalsIgnoreCase("true") || expect.equalsIgnoreCase("false")){
                                Boolean boolen_expect = Boolean.parseBoolean(expect);
                                OperateAssertStatus.assertStatus(name, type,boolen_expect,filename, driver, wait);
                            }else {
                                logger.error("-----------request expect is null-----------");
                                return;
                            }
                        }
                    } catch (Exception assertException) {
                        logger.error(assertException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                    /*
                case "inputStatus":
                    try {
                        if (gettext.equalsIgnoreCase("null") || gettext.length() == 0) {
                            logger.error("-----------request input is null-----------");
                            return;
                        } else {
                            String inputStatus_xpath = "//div/label[text()='" + gettext + "']/following-sibling::div/div/div/input";
                            WaitUntil.waitUntil(wait, inputStatus_xpath);
                            //WebElement bt = wait.until(visibilityOf(driver.findElement(By.xpath("//div/label[text()='" + gettext + "']/following-sibling::div/div/div/input"))));
                            Boolean status = driver.findElement(By.xpath(inputStatus_xpath)).isEnabled();
                            if (status) {
                                asmsg = "true";
                            } else {
                                asmsg = "false";
                            }
                        }
                    } catch (Exception InputstatusException) {
                        InputstatusException.printStackTrace();
                    }
                    break;
                case "buttonStatus":
                    try {
                        if (gettext.equalsIgnoreCase("null") || gettext.length() == 0) {
                            logger.error("-----------request button is null-----------");
                            return;
                        } else {
                            String buttonStatus_xpath = "//button/span[text()='" + gettext + "']";
                            Boolean status = WaitUntil.waitUntilisEnable(wait, buttonStatus_xpath);
                            if (status) {
                                asmsg = "true";
                            } else {
                                asmsg = "false";
                            }
                        }
                    } catch (Exception ButtonstatusException) {
                        ButtonstatusException.printStackTrace();
                    }
                    break;
                    */
                case "assertDB":
                    String getsql = null;
                    try {
                        if (CurrentSql.curentsql().equalsIgnoreCase("postgresql")) {
                            getsql = postgresql;
                        } else {
                            getsql = sql;
                        }

                        if (!StringUtils.isNotBlank(expect)) {
                            //String num = PostgreSqlConnect.sqlQueryCount(getsql);
                            String num = DbTestDru.selectCount(getsql,pjname);
                            Assertion.verifyEquals(num, asmsg);
                            logger.info("----------Assertion Finish----------actual:" + num + "  expect:" + asmsg);
                        } else {
                            //String num = PostgreSqlConnect.sqlQueryCount(getsql);
                            String num = DbTestDru.selectCount(getsql,pjname);
                            Assertion.verifyEquals(num, expect);
                            logger.info("----------Assertion Finish----------actual:" + num + "  expect:" + expect);
                        }
                    } catch (Exception AssertDBException) {
                        logger.error(AssertDBException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                case "assertText":
                    try {
                        AssertText.asstext(asmsg,expect,filename);
                    } catch (Error AssertTextException) {
                        logger.error(AssertTextException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                    /*
                case "beginlog":
                    try {
                        if (!StringUtils.isNotBlank(gettext)) {
                            return;
                        } else {
                            logfilepath = gettext;
                            String beginlogtmp = ShellExec.shellexecs("tail -n 1000 " + gettext + " | cut -d \" \" -f 1,2 |grep $(date +%Y-%m-%d)|tail -n 1");
                            logger.info("----------------------------beginlogtmp = " + beginlogtmp + "----------------------------");
                            beginlog = beginlogtmp.substring(0, 23);
                            logger.info("----------------------------beginlog = " + beginlog + "----------------------------");
                        }
                    } catch (Exception BeginlogException) {
                        BeginlogException.printStackTrace();
                    }
                    break;
                    */
                case "assertLog":
                    try{
                        if (!StringUtils.isNotBlank(gettext)) {
                            return;
                        } else {
                            logfilepath = gettext;
                            String beginlogtmp = ShellExec.shellexecs("tail -n 1000 " + gettext + " | cut -d \" \" -f 1,2 |grep $(date +%Y-%m-%d)|tail -n 1",pjname);
                            logger.info("----------------------------beginlogtmp = " + beginlogtmp + "----------------------------");
                            beginlog = beginlogtmp.substring(0, 23);
                            logger.info("----------------------------beginlog = " + beginlog + "----------------------------");
                        }

                        if (!StringUtils.isNotBlank(expect) || !StringUtils.isNotBlank(logfilepath)) {
                            logger.error("-----------request expect is null-----------");
                            return;
                        } else {
                            String asmsgtmp = ShellExec.shellexecs("grep -A 100000 '" + beginlog + "' " + logfilepath + " |grep -i -o '" + expect + "'",pjname);
                            logger.info("----------------------------" + LINE_SEPARATOR +
                                    "grep -A 100000 '" + beginlog + "' " + logfilepath + " |grep -i -o '" + expect + "'" + LINE_SEPARATOR +
                                    "grep asmsgtmp = " + asmsgtmp + LINE_SEPARATOR +
                                    "----------------------------");
                            if (asmsgtmp.length() >= expect.length()) {
                                asmsg = asmsgtmp.substring(0, expect.length());
                            } else {
                                asmsg = "-1";
                            }
                            Assertion.verifyEqualsLow(asmsg, expect, filename);
                            logger.info("----------Assertion Finish----------actual:" + asmsg + "  expect:" + expect);
                        }
                    }catch (Error AssertlogException) {
                        logger.error(AssertlogException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                case "quit":
                    try {
                        driver.quit();
                    } catch (Exception QuitException) {
                        logger.error(QuitException);
                        Assertion.flag = false;
                        sc.takeScreenShot(filename);
                    }
                    break;
                default:
                    logger.error("nothing to do");
                    return;
            }
        } catch (Exception ExecuteAutomentException) {
            ExecuteAutomentException.printStackTrace();
            Assertion.flag = false;
        }
    }
}
