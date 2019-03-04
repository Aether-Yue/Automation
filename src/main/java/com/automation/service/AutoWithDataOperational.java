package com.automation.service;

import com.automation.until.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.*;



public class AutoWithDataOperational{
    private static Logger logger = Logger.getLogger(AutoWithDataOperational.class);
    /**
     *参数化执行过程
     */
    public static void executeAutomentWithData(File file,WebDriver driver,WebDriverWait wait,String pjname) throws InterruptedException {
        String[] file_name = file.getName().split("\\.");
        String filename = file_name[0];
        ScreenShot.driver = driver;
        List<Object> xmlList = new ReadXMLByDom4j().getXml(file);
        String csvfilename = null;
        for (int i = 0; i < xmlList.size(); i++){
            Map<String,String> operation = (Map<String, String>) xmlList.get(i);
            if(operation.get("elementname").equalsIgnoreCase("dataFile")){
                csvfilename = operation.get("gettext");
                break;
            }
        }
        File csvfile = OperateCaseFile.caseFile(csvfilename);
        //File csvfile = new File(System.getProperty("user.dir") + File.separator + "testcase" + File.separator + "casedata" + File.separator +filename+".csv");
        List<String> csvlist = ReadCsv.readCsv(csvfile);

        for(int n=0;n<csvlist.size();n++) {//获取CSV参数的行数，即为循环执行的次数

            String[] paramdata=csvlist.get(n).split(",");

            for (int k = 0; k < xmlList.size(); k++) {//循环遍历所有行
                Map<String, String> operationmsg = (Map<String, String>) xmlList.get(k);
                String elementname = operationmsg.get("elementname");
                if (StringUtils.isNotBlank(operationmsg.get("elementname"))) {
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

                    if(elementname.equalsIgnoreCase("quit")){
                        while (n == csvlist.size()-1){
                            SwitchOperate.switchOperate( elementname,  url,  name,  keys,  clicktext,
                                    gettext,  expect,  sql,  type,  row,  column,  content,
                                    xpath,  level,  comboxid,  poptab,  tableoperation,  node,
                                    nodeop,  multiplediv,  tab,  postgresql,
                                    filename,
                                    driver, wait,pjname);
                            break;
                        }
                    }else {
                        Map<String, String> dataresult = new HashMap<String, String>();
                        List<String> paradata = AutoWithData.paramdata((HashMap<String, String>) operationmsg);
                        for (int g = 0; g < paradata.size(); g++) {
                            String param_init = operationmsg.get(paradata.get(g));
                            if (StringUtils.isNotBlank(param_init)) {
                                String param_name = param_init.substring(2, param_init.length() - 1);
                                Integer lanum = ReadCsv.paramNum(csvfile, param_name);
                                String params_value = paramdata[lanum];
                                dataresult.put(paradata.get(g), params_value);
                            }
                        }
                /*
                List<String> values = new ArrayList<String>();
                List<Integer> valuesnum = new ArrayList<Integer>();
                for (int j = 0; j < la.size(); j++) {//获取参数名以及对应的参数值，分别存放到list
                    String paraxml = la.get(j);
                    if (paraxml.startsWith("${") && paraxml.endsWith("}")) {
                        String[] params = paraxml.split(",");
                        String params_add =null;
                        String param1 = params[0].substring(2, params[0].length() - 1);
                        Integer lanum1 = ReadCsv.paramNum(csvfile, param1);
                        params_add = paramdata[lanum1];
                        for (int p = 1; p < params.length; p++) {
                            String param = params[p].substring(2, params[p].length() - 1);
                            lanum = ReadCsv.paramNum(csvfile, param);
                            params_add = params_add + " " + paramdata[lanum];
                        }
                        values.add(params_add);//参数值
                        valuesnum.add(j);//参数(la.get(j))
                    }
                }
                */

                        for (Map.Entry<String, String> entry : dataresult.entrySet()) {
                            if (entry.getKey().equalsIgnoreCase("url")) {
                                url = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("name")) {
                                name = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("keys")) {
                                keys = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("clicktext")) {
                                clicktext = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("gettext")) {
                                gettext = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("expect")) {
                                expect = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("sql")) {
                                sql = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("type")) {
                                type = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("row")) {
                                row = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("column")) {
                                column = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("content")) {
                                content = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("xpath")) {
                                xpath = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("level")) {
                                level = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("comboxid")) {
                                comboxid = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("poptab")) {
                                poptab = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("tableoperation")) {
                                tableoperation = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("node")) {
                                node = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("nodeop")) {
                                nodeop = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("multiplediv")) {
                                multiplediv = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("tab")) {
                                tab = entry.getValue();
                            } else if (entry.getKey().equalsIgnoreCase("postgresql")) {
                                postgresql = entry.getValue();
                            }
                        }

                        SwitchOperate.switchOperate(elementname, url, name, keys, clicktext,
                                gettext, expect, sql, type, row, column, content,
                                xpath, level, comboxid, poptab, tableoperation, node,
                                nodeop, multiplediv, tab, postgresql,
                                filename,
                                driver, wait,pjname);
                    }
                }else {
                    logger.error("----element is null----");
                }

                //断言失败则中断该用例执行
                if(!Assertion.flag){
                    while (n == csvlist.size()-1){
                        driver.quit();
                        break;
                    }
                    break;
                }
            }
        }
    }
}
