package com.automation.until;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;

public class SetTestngXml {
    /**
     * 修改：属性值
     */
    public static synchronized void setTestngClass(File file, String classname) throws DocumentException, IOException {
        //创建Document对象，读取已存在的Xml文件person.xml
        Document doc = new SAXReader().read(file);

        Element nameElem = doc.getRootElement().element("test").element("classes").element("class");
        nameElem.addAttribute("name", classname);

        //指定文件输出的位置
        FileOutputStream out = new FileOutputStream(file);
        // 指定文本的写出的格式：
        OutputFormat format = OutputFormat.createPrettyPrint();   //漂亮格式：有空格换行
        format.setEncoding("UTF-8");
        //1.创建写出对象
        XMLWriter writer = new XMLWriter(out, format);
        //2.写出Document对象
        writer.write(doc);
        //3.关闭流
        writer.close();
    }

    public static synchronized void setTestngSuite(File file, String name, String value) throws DocumentException, IOException {

        //创建Document对象，读取已存在的Xml文件person.xml
        Document doc = new SAXReader().read(file);

        Element nameElem = doc.getRootElement();
        nameElem.addAttribute(name, value);

        //指定文件输出的位置
        FileOutputStream out = new FileOutputStream(file);
        // 指定文本的写出的格式：
        OutputFormat format = OutputFormat.createPrettyPrint();   //漂亮格式：有空格换行
        format.setEncoding("UTF-8");
        //1.创建写出对象
        XMLWriter writer = new XMLWriter(out, format);
        //2.写出Document对象
        writer.write(doc);
        //3.关闭流
        writer.close();
    }
}
