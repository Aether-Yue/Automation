package com.automation.until;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 读取csv文件
 */
public class ReadCsv {
    public static Integer paramNum(File file,String la) {
        Integer a = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));//换成你的文件名
            //reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            List<String> csvlist = new ArrayList<String>();
            while((line=reader.readLine())!=null){
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                //String last = item[item.length-1];//这就是你要的数据了
                //int value = Integer.parseInt(last);//如果是数值，可以转化为数值
                //System.out.println(Arrays.toString(item));
                //System.out.println(item.length);
                String str =Arrays.toString(item);
                csvlist.add(str.substring(1,str.length()-1));
            }
            /*
            for(int i=0;i<csvlist.size();i++){
                System.out.println(csvlist.get(i));
            }
            */
            String[] aa =csvlist.get(0).split(",");
            for (int i=0;i<aa.length;i++){
                if(la.equalsIgnoreCase(aa[i].replace(" ", ""))){
                    a = i;//参数化变量引用的参数名位置
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;//参数化变量引用的参数名位置
    }

    public static List<String> readCsv(File file){
        List<String> csvlist = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));//换成你的文件名
            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            while((line=reader.readLine())!=null){
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                String str =Arrays.toString(item);
                csvlist.add(str.substring(1,str.length()-1));//参数化参数值信息，按行依次读取
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return csvlist;
    }

}