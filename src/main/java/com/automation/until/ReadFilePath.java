package com.automation.until;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 读取文件夹下所有的xml文件
 */
public class ReadFilePath {

    public static List<String> filelist =new ArrayList<String>();

    /**
     * @Description: 获取xml文件列表数据
     * @Param:  strPath
     * @return:  List<String>
     **/
    public static List<String> getFileList(String strPath) {
        File dir = new File(ShareUtil.TESTCASE_PATH + strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
//        System.out.println("_____文件目录输出：_____" + Arrays.toString(files));
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(strPath + File.separator + files[i].getName()); // 获取文件绝对路径
                }
//                不可用else if ，否则递归会重复添加xml文件到文件列表中
                if (fileName.endsWith("xml")) { // 判断文件名是否以.xml结尾
                    filelist.add(strPath +File.separator +  fileName);
                } else {
                    continue;
                }
            }
        }
//        System.out.println("_____最终文件目录输出：_____" + filelist.toString());
        return filelist;
    }

    public static void writeFile(String filename,String data){
        BufferedWriter bwriter=null;
        try {
            bwriter = new BufferedWriter(new FileWriter(filename, true));
            bwriter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bwriter.close();
            }catch (IOException closebw){
                closebw.printStackTrace();
            }
        }
    }
}
