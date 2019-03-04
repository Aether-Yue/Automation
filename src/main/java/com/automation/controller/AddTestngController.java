package com.automation.controller;

import com.automation.until.MkDirFile;
import com.automation.until.ModifyProperties;
import com.automation.until.ShareUtil;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping(path = "/xmltest")
public class AddTestngController {

    @RequestMapping
    public Boolean xmltest(@RequestParam(value = "project", required = true) String project) {
        Boolean isCreated = false;
        if(StringUtils.isNotBlank(project)) {
            File dir = new File("testcase");
            File[] files = dir.listFiles();
            Boolean notExistPj = true;
            for(File name : files){
                if(name.isDirectory()) {
                    String dirname = name.getName();
                    if (dirname.equals(project)) {
                        notExistPj = false;
                        break;
                    }
                }
            }
            if (notExistPj) {
                MkDirFile mkf = new MkDirFile();
                mkf.mkDirFile(project);
                try {
                    mkf.setPara(project);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                isCreated = true;
            }
        }
        return isCreated;
    }

    //by Aether.yue
    @RequestMapping("/ngtail")
    public String ngtail(@RequestParam(value = "project", required = true) String project) {
        File modify_file = new File(project);
        ModifyProperties modify = new ModifyProperties();
        modify.readProperties(modify_file);
        //to do 返回配置信息
        return "aaaa";
    }

    //by Aether.yue
    @RequestMapping("/ngset")
    public String ngset(@RequestParam(value = "project", required = true) String project) {
        File modify_file = new File(project);
        HashMap<String,String> map = new HashMap<String,String>();
        ModifyProperties modify = new ModifyProperties();
        modify.modifyProperties(modify_file,map);
        //to do 设置配置信息，返回设置后的信息
        return "aaaa";
    }

    //by Aether.yue
    @RequestMapping("/tetail")
    public String tetail(@RequestParam(value = "project", required = true) String project) {
        //to do 返回配置信息
        return "aaaa";
    }

    //by Aether.yue
    @RequestMapping("/teset")
    public String teset(@RequestParam(value = "project", required = true) String project) {
        //to do 设置配置信息，返回设置后的信息
        return "aaaa";
    }

    //by Aether.yue
    @RequestMapping("/jdtail")
    public String jdtail(@RequestParam(value = "project", required = true) String project) {
        //to do 返回配置信息
        return "aaaa";
    }

    //by Aether.yue
    @RequestMapping("/jdset")
    public String jdset(@RequestParam(value = "project", required = true) String project) {
        //to do 设置配置信息，返回设置后的信息
        return "aaaa";
    }
}
