package com.automation.until;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 修改Properties文件
 */
public class ModifyProperties {

    public void modifyProperties(File properties, HashMap<String,String> pre_value) {

        Properties propfile = new Properties();
        try {
            //读取属性文件a.properties
            InputStream in = new BufferedInputStream(new FileInputStream(properties));
            propfile.load(in);     ///加载属性列表
            Iterator<String> it = propfile.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                System.out.println(key + ":" + propfile.getProperty(key));
            }
            in.close();

            ///保存属性到b.properties文件
            FileOutputStream oFile = new FileOutputStream(properties, true);//true表示追加打开
            for (Map.Entry<String, String> entry : pre_value.entrySet()) {
                propfile.setProperty(entry.getKey(), entry.getValue());
            }
            //保存并加注释
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date currdate = new Date();
            String datetime_cu = df.format(currdate);
            propfile.store(oFile, "Update At "+datetime_cu);
            oFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Properties readProperties(File properties){
        Properties propfile = new Properties();
        try {
            //读取属性文件a.properties
            InputStream in = new BufferedInputStream(new FileInputStream(properties));
            propfile.load(in);     ///加载属性列表
            Iterator<String> it = propfile.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                System.out.println(key + ":" + propfile.getProperty(key));
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return propfile;
    }
}
