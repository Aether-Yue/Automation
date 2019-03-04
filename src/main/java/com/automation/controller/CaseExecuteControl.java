package com.automation.controller;

import com.automation.model.ReadProject;
import com.automation.service.AutoOperational;
import com.automation.service.AutoWithData;
import com.automation.service.AutoWithDataOperational;
import com.automation.service.SetPath;
import com.automation.until.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.*;



/**
 *执行自动化入口类
 */
public class CaseExecuteControl {

    private static Logger logger = Logger.getLogger(String.valueOf(CaseExecuteControl.class));
    private static Boolean testlink_enable = false;
    public static void caseExecuteControl(String file_name,String case_id,String Descript) throws InterruptedException {
        logger.info(file_name + " Current Thread Id:"+Thread.currentThread().getId()+"--------projectname="+ReadProject.getProjectname());
        String pjname = ReadProject.getProjectname();
        ReadProject.setStatus(true);
        ReadProject.setBeginexe_status(true);
        ReadTestProperties readconf = new ReadTestProperties();
        readconf.setProject_name(ReadProject.getProjectname());
        String execute_path =System.getProperty("user.dir") + File.separator + "testcase";
        File file = new File(execute_path + File.separator + file_name);
        String[] file_name_b = file.getName().split("\\.");
        String filename = file_name_b[0];
        String testlinkEnable = readconf.readTestProperties("testlink_enable");
        if(StringUtils.isNotBlank(testlinkEnable)) {
            if (testlinkEnable.equalsIgnoreCase("true") || testlinkEnable.equalsIgnoreCase("false")) {
                testlink_enable = Boolean.valueOf(testlinkEnable);
            }
        }
        logger.debug(file_name + "开始执行：");
        SetPath.setpath(pjname);
        GetWebDriver getdrver =new GetWebDriver();
        WebDriver driver = getdrver.getWebDriver(pjname);
        WebDriverWait wait = getdrver.getWebWait(driver,pjname);
        ScreenExecute screen = new ScreenExecute();
        screen.screenStart(driver,filename,pjname);
        if(AutoWithData.autoWithData(file)){
            AutoWithDataOperational.executeAutomentWithData(file,driver,wait,pjname);
        }else {
            AutoOperational.executeAutoment(file,driver,wait,pjname);
        }
        screen.screenStop(pjname);

        //测试结果回传testlink
        if (testlink_enable) {
            if(StringUtils.isNotBlank(case_id)) {
                TestlinkResult.testlinkResult(Assertion.flag, case_id,pjname);
            }
        }

        //用例执行结果
        if(!Assertion.flag){
            logger.debug(file_name + "执行失败");
            Assert.assertEquals(1,0);
        }else{
            logger.debug(file_name + "执行成功");
        }

    }
}
