package com.automation.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import java.io.File;

public class OperateCaseFile {
    private static Logger logger = Logger.getLogger(OperateCaseFile.class);

    /**
     * 用例文件调用
     * @param filename 输入参数的名称
     **/
    public static File caseFile(String filename) throws InterruptedException {
        File casefile = null;
        if (!StringUtils.isNotBlank(filename)){
            logger.info("----filename is null----");
        }else {
            File newfile = new File(System.getProperty("user.dir") + File.separator + filename);
            if(!newfile.exists()) {
                File sfile = new File(filename);
                if (!sfile.exists()) {
                    File cfile = new File(System.getProperty("user.dir") + File.separator + "testcase" + File.separator + "sharecase" + File.separator + filename);
                    if(!cfile.exists()){
                        logger.info("----file is not exists----");
                    }else {
                        casefile = cfile;
                    }
                }else {
                    casefile = sfile;
                }
            }else {
                casefile = newfile;
            }
        }
        return casefile;
    }
}
