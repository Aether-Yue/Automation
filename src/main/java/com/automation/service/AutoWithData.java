package com.automation.service;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoWithData {
    /**
    *判断xml文件中是否包含变量引用
     */
    public static Boolean autoWithData(File file) {
        List<Object> xmlList = new ReadXMLByDom4j().getXml(file);
        Boolean param = false;
        OUT:
        for (int i = 0; i < xmlList.size(); i++) {//循环遍历所有行
            Map<String, String> rowxml = (HashMap<String, String>) xmlList.get(i);
            for (String paraxml : rowxml.values()) {
                if (StringUtils.isNotBlank(paraxml)) {
                    if (paraxml.startsWith("${") && paraxml.endsWith("}")) {
                        char[] chars = paraxml.toCharArray();
                        char c1 = '$';
                        char c2 = '{';
                        char c3 = '}';
                        int num1 = 0;
                        int num2 = 0;
                        int num3 = 0;
                        for (int ch = 0; ch < chars.length; ch++) {
                            if (c1 == chars[ch]) {
                                num1++;
                            } else if (c2 == chars[ch]) {
                                num2++;
                            } else if (c3 == chars[ch]) {
                                num3++;
                            }
                        }
                        if (num1 == 1 && num2 == 1 && num3 == 1) {
                            param = true;
                            break OUT;
                        }
                    }
                }
            }
        }
        return param;
    }

    /**
     * 找出一个操作中所有参数化的标签
     * @param filemap
     * @return
     */
    public static List<String> paramdata(HashMap<String,String> filemap) {
        List<String> paralist = new ArrayList<>();
        if(filemap.size()>0) {
            for (Map.Entry<String, String> entry : filemap.entrySet()) {
                if (StringUtils.isNotBlank(entry.getValue())) {
                    if (entry.getValue().startsWith("${") && entry.getValue().endsWith("}")) {
                        char[] chars = entry.getValue().toCharArray();
                        char c1 = '$';
                        char c2 = '{';
                        char c3 = '}';
                        int num1 = 0;
                        int num2 = 0;
                        int num3 = 0;
                        for (int ch = 0; ch < chars.length; ch++) {
                            if (c1 == chars[ch]) {
                                num1++;
                            } else if (c2 == chars[ch]) {
                                num2++;
                            } else if (c3 == chars[ch]) {
                                num3++;
                            }
                        }
                        if (num1 == 1 && num2 == 1 && num3 == 1) {
                            paralist.add(entry.getKey());
                        }
                    }
                }
            }
        }
        return paralist;
    }
}
