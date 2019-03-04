package com.automation.controller;

import com.alibaba.fastjson.JSONObject;
import com.automation.until.FileUtil;
import com.automation.until.ReadFilePath;
import com.google.common.collect.Maps;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/edcase")
public class EditCaseController {
    static Logger log = Logger.getLogger("ClassName : EditCaseController");
    //by Aether.yue
    /** 
     * @Description: 获取测试文件列表控制器
     * @Param:  
     * @return:  
     * @Author: Aether.Yue 
     * @Date: 2019/3/1 
     **/
    @RequestMapping("/edquery")
    public String edcase(@RequestParam(value = "project", required = true) String project) {

        String str = FileUtil.filePathNameToJsonString(ReadFilePath.getFileList(project));
//        log.info("___ edcase str 数据__" + str);
        JSONObject jsStr = JSONObject.parseObject(str);
//        log.info("___ edcase 返回客户端JSON数据__" + jsStr);
        //to do 判断是否存在该文件，存在则返回内容，不存在则提示上传
        ReadFilePath.filelist.clear();
        return str;
    }

    //by Aether.yue 可暂不实现
    @RequestMapping("/edupload")
    public String edupload(@RequestParam(value = "project", required = true) String project,@RequestParam(value = "case", required = true) String casename) {
        //to do 上传文件到目录下，已存在的则替换
        return "这是 demo";
    }



}
