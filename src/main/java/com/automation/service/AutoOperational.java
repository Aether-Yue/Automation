package com.automation.service;

import com.automation.until.Assertion;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 *获取xml操作内容
 */
public class AutoOperational {
    private static Logger logger = Logger.getLogger(String.valueOf(AutoOperational.class));
    public static void executeAutoment(File file,WebDriver driver,WebDriverWait wait,String pjname) {
        List<Object> xmlList = new ReadXMLByDom4j().getXml(file);//读取xml文件
        String[] file_name = file.getName().split("\\.");
        String filename = file_name[0];//获取filename(用来标识对应截图等结果信息)
        for (int i = 0; i < xmlList.size(); i++) {//循环遍历所有行
            Map<String,String > operationmsg= (Map<String, String>) xmlList.get(i);//xml每一个标签操作
            String elementname = operationmsg.get("elementname");
            if(StringUtils.isNotBlank(elementname)) {
                String url = operationmsg.get("url");
                String name = operationmsg.get("name");
                String keys = operationmsg.get("keys");
                String clicktext = operationmsg.get("clicktext");
                String gettext = operationmsg.get("gettext");
                String expect = operationmsg.get("expect");
                String sql = operationmsg.get("sql");
                String type = operationmsg.get("type");
                String row = operationmsg.get("row");
                String column = operationmsg.get("column");
                String content = operationmsg.get("content");
                String xpath = operationmsg.get("xpath");
                String level = operationmsg.get("level");
                String comboxid = operationmsg.get("comboxid");
                String poptab = operationmsg.get("poptab");
                String tableoperation = operationmsg.get("tableoperation");
                String node = operationmsg.get("node");
                String nodeop = operationmsg.get("nodeop");
                String multiplediv = operationmsg.get("multiplediv");
                String tab = operationmsg.get("tab");
                String postgresql = operationmsg.get("postgresql");

                String loginfo = "elementname=" + operationmsg.get("elementname");
                for (Map.Entry<String, String> logentry : operationmsg.entrySet()){
                    if(StringUtils.isNotBlank(logentry.getValue()) && !logentry.getKey().equalsIgnoreCase("elementname")) {
                        loginfo = loginfo + "," + logentry.getKey()+"="+logentry.getValue();
                    }
                }
                logger.info("Operate:{"+loginfo+"}");

                SwitchOperate.switchOperate( elementname,  url,  name,  keys,  clicktext,
                         gettext,  expect,  sql,  type,  row,  column,  content,
                         xpath,  level,  comboxid,  poptab,  tableoperation,  node,
                         nodeop,  multiplediv,  tab,  postgresql,
                         filename,
                         driver, wait,pjname);
            }else {
                logger.error("----element is null----");
            }

            //断言失败则中断该用例执行
            if(!Assertion.flag){
                driver.quit();
                break;
            }
        }
    }
}



