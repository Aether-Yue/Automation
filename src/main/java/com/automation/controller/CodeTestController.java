package com.automation.controller;

import com.automation.model.ReadProject;
import com.automation.until.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.uncommons.reportng.HTMLReporter;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(path = "/codetest")
public class CodeTestController {
    private static Logger logger = Logger.getLogger(String.valueOf(CodeTestController.class));
    private static Boolean isChanel;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String codetest(@RequestParam(value = "project", required = true) String project_name, @RequestBody String body) {

        JsonParser parser = new JsonParser();
        JsonObject jsonParam = (JsonObject) parser.parse(body);

        List<ITestResult> result = new ArrayList<>();
        Boolean beginexecute = false;
        for (Integer i = 0; i < 180; i++) {
            try {
                Thread.sleep(1000);
                beginexecute = ReadProject.getBeginexe();
                if (beginexecute) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.debug("--------------wait---------" + project_name + "------currentThread=" + Thread.currentThread().getId());
        }
        if (beginexecute) {
            ReadProject.setProjectname(project_name);
            ReadTestProperties readconf = new ReadTestProperties();
            readconf.setProject_name(project_name);
            String CLASS_NAME = readconf.readTestProperties("class_name");
            String WRITER_PATH = System.getProperty("user.dir") + File.separator + readconf.readTestProperties("writer_path") + File.separator + "com" + File.separator + "automation" + File.separator + "controller" + File.separator + CLASS_NAME + ".java";
            String WRITER_Class = System.getProperty("user.dir") + File.separator + readconf.readTestProperties("writer_path") + File.separator + "com" + File.separator + "automation" + File.separator + "controller" + File.separator + CLASS_NAME + ".class";
            String PACK_PATH = readconf.readTestProperties("pack_name");
            String ClassFile_path = System.getProperty("user.dir") + File.separator + readconf.readTestProperties("writer_path");

            File WRITERClass = new File(WRITER_Class);
            if (WRITERClass.exists()) {
                WRITERClass.delete();
            }

            CodeGenerate code = new CodeGenerate();
            try {
                //读文本文件
                //生成java类
                ExecuteFileGenerate.executeFileGenerate(WRITER_PATH, project_name, jsonParam);
                //编译java类
                code.javac(WRITER_PATH);
                //运行java类
                code.java(PACK_PATH, ClassFile_path);
            } catch (Exception batch_run) {
                batch_run.printStackTrace();
                ReadProject.setStatus(true);
                ReadProject.setBeginexe_status(true);
            }

            isChanel = Boolean.valueOf(readconf.readTestProperties("ssh.sshChanel"));
            JDBCSSHChannel channel = null;
            if (isChanel) {
                //开启隧道
                channel = new JDBCSSHChannel();
                try {
                    GetSSHChannel getsshchanel = new GetSSHChannel();
                    getsshchanel.getSSH(channel, project_name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            List<String> suites = new ArrayList<String>();
            String xmlpath = System.getProperty("user.dir") + File.separator + "config" + File.separator + project_name + File.separator + project_name + "_testng.xml";
            suites.add(xmlpath);
            TestNG tng = new TestNG();
            tng.setTestSuites(suites);
            tng.addListener(new HTMLReporter());
            tng.setUseDefaultListeners(false);
            tng.run();

            if (isChanel) {
                //关闭隧道
                channel.close();
            }

            for (ITestResult passedTest : AssertionListener.itx.getPassedTests().getAllResults()) {
                result.add(passedTest);
            }
            for (ITestResult passedTest : AssertionListener.itx.getFailedTests().getAllResults()) {
                result.add(passedTest);
            }
            for (ITestResult passedTest : AssertionListener.itx.getSkippedTests().getAllResults()) {
                result.add(passedTest);
            }
        }

        List<Map> result_list = new ArrayList<>();
        for (int k = 0; k < result.size(); k++) {
            Map<String, String> result_map = new HashMap<>();
            String name = result.get(k).getName();
            int code = result.get(k).getStatus();
            String status;
            if (code == 1) {
                status = "SUCCESS";
            } else if (code == 2) {
                status = "FAILED";
            } else if (code == 3) {
                status = "SKIPS";
            } else {
                status = "UNKNOWN";
            }
            result_map.put(name, status);
            result_list.add(result_map);
        }
        Gson list_json = new Gson();
        String result_json = list_json.toJson(result_list);
        return result_json;
    }
}

