package com.automation.until;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

import java.io.*;

/**
* 根据projectname创建配置文件
* */
public class MkDirFile {

    private static Logger logger = Logger.getLogger(String.valueOf(MkDirFile.class));

    public void mkDirFile(String projectname){
        File save_path = new File(System.getProperty("user.dir") + File.separator + "config"+ File.separator +projectname);
        File save_file_jdbc = new File(System.getProperty("user.dir") + File.separator + "config"+ File.separator +projectname + File.separator + projectname+"_jdbc.properties");
        File save_file_test = new File(System.getProperty("user.dir") + File.separator + "config"+ File.separator +projectname + File.separator + projectname+"_test.properties");
        File save_file_tng = new File(System.getProperty("user.dir") + File.separator + "config"+ File.separator +projectname + File.separator + projectname+"_testng.xml");

        File jdbc_file = new File(System.getProperty("user.dir") + File.separator + "config"+ File.separator + "jdbc.properties");
        File test_file = new File(System.getProperty("user.dir") + File.separator + "config"+ File.separator + "test.properties");
        File testng_file = new File(System.getProperty("user.dir") + File.separator + "config"+ File.separator + "testng.xml");

        if(save_path.exists()){
            if(save_path.isDirectory()){
                logger.debug("dir is exists , not need create");
            }else {
                logger.debug("the same name file exists, can not create dir");
            }
            if(save_file_test.exists()){
                if(save_file_test.isDirectory()){
                    try {
                        copyFile(test_file,save_file_test);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    logger.debug("the same name file exists, can not create dir");
                }
            }
            if(save_file_jdbc.exists()){
                if(save_file_jdbc.isDirectory()){
                    try {
                        copyFile(jdbc_file,save_file_jdbc);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    logger.debug("the same name file exists, can not create dir");
                }
            }
            if(save_file_tng.exists()){
                if(save_file_tng.isDirectory()){
                    try {
                        copyFile(testng_file,save_file_tng);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    logger.debug("the same name file exists, can not create dir");
                }
            }
        }else {
            logger.debug("dir is not exists, create it ...");
            save_path.mkdirs();
            try {
                copyFile(test_file,save_file_test);
                copyFile(jdbc_file,save_file_jdbc);
                copyFile(testng_file,save_file_tng);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyFile(File sourceFile,File targetFile)
            throws IOException{
        // 新建文件输入流并对它进行缓冲
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff=new BufferedInputStream(input);

        // 新建文件输出流并对它进行缓冲
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff=new BufferedOutputStream(output);

        // 缓冲数组
        byte[] b = new byte[1024 * 5];
        int len;
        while ((len =inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        // 刷新此缓冲的输出流
        outBuff.flush();

        //关闭流
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }

    public void setPara(String project) throws IOException, DocumentException {
        File save_file_tng = new File(System.getProperty("user.dir") + File.separator + "config"+ File.separator +project + File.separator + project+"_testng.xml");
        ReadTestProperties readconf = new ReadTestProperties();
        readconf.setProject_name(project);
        String classname = readconf.readTestProperties("pack_name");
        SetTestngXml.setTestngClass(save_file_tng,classname);
    }
}
