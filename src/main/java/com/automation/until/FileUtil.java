package com.automation.until;


import java.io.File;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;
/**
 * @program: logwire-automation
 * @description: 文件处理类
 * @author:Aether.Yue
 * @create:2019-02-28 11:36
 */
public class FileUtil {

    static Logger log = Logger.getLogger("ClassName : FileUtil");
    //Java对象转JSON字符串
    /** 
     * @Description: 文件路径转为JsonString 
     * @Param:  
     * @return:  
     * @Author: Aether.Yue 
     * @Date: 2019/3/1 
     **/
    public static String filePathNameToJsonString(List<String> fileNameList) {
        StringBuffer stringBuffer = new StringBuffer();
        log.info("\n__________当前路径：" + System.getProperty("user.dir"));

        ListIterator<String> listIterator = fileNameList.listIterator();
        while (listIterator.hasNext()){
            stringBuffer.append(strToJsonElement(listIterator.next()) + ",");
        }
        if(stringBuffer.length()>=1){
            stringBuffer.deleteCharAt(stringBuffer.length()-1);
        }
        String str = "{\"caselist\":[" + stringBuffer.toString() + "]}";
        System.out.println("______转换为JSONString的fileNameList对象：" + str);
        return str;
    }

    /** 
     * @Description: 文件名拼接为json值格式
     * @Param:  
     * @return:  
     * @Author: Aether.Yue 
     * @Date: 2019/3/1 
     **/
    public static String strToJsonElement(String str){
        int i = str.lastIndexOf(File.separator);
        int j = str.lastIndexOf(".");
        String filename = "";
        String filepath = "";
        String  jsonElement = "";

        filename = "\"name\":\"" + str.substring(i+1,j) + "\"";
        log.info("文件名：" + filename);
        filepath = "\"path\":\"" + str.substring(0,i) + "\"";
        log.info("文件路径：" + filepath);
        filepath = filepath.replace("\\","\\\\");
        jsonElement = "{" + filename + "," + filepath + "}";
        return jsonElement;
    }
}
