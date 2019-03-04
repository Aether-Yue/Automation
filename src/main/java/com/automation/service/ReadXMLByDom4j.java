package com.automation.service;

import com.automation.model.XmlModel;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 用DOM4J方法读取xml文件
 * @xpath lune
 */
public class ReadXMLByDom4j {

    private List<Object> xmlList = null;
    private XmlModel xml = null;

    public List<Object> getXml(File file){

        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(file);//读文件
            Element xmlstore = document.getRootElement();
            Iterator storeit = xmlstore.elementIterator();

            xmlList = new ArrayList<Object>();
            while(storeit.hasNext()){

                xml = new XmlModel();
                Element xmlElement = (Element) storeit.next();

                String ename = xmlElement.getName();
                xml.setElementname(ename);

                String type = "";

                List<Attribute> typelist = xmlElement.attributes();
                for( Attribute attr : typelist){
                    //每循环一次，解析此节点的一个【属性=值】，没有则输出空
                    String name = attr.getName();
                    String value = attr.getValue();
                    //System.out.println(name+"="+value);
                    if(name.equalsIgnoreCase("type")){
                        xml.settype(value);
                        type = value;
                    }else if(name.equalsIgnoreCase("content")){
                        xml.setcontent(value);
                    }else if(name.equalsIgnoreCase("level")){
                        xml.setlevel(value);
                    }else if(name.equalsIgnoreCase("pop-tab")){
                        xml.setpoptab(value);
                    }else if(name.equalsIgnoreCase("operation")){
                        xml.settableoperation(value);
                    }else if(name.equalsIgnoreCase("multiplediv")){
                        xml.setmultiplediv(value);
                    }else if(name.equalsIgnoreCase("tab")){
                        xml.settab(value);
                    }
                }


                if(ename.equals("url")){
                    String url = xmlElement.getText();
                    xml.seturl(url);
                }else if(ename.equals("input")){
                    Iterator xmlit = xmlElement.elementIterator();
                    while(xmlit.hasNext()) {
                        Element child = (Element) xmlit.next();
                        String nodeName = child.getName();
                        if (nodeName.equals("name")) {
                            //System.out.println(child.getStringValue());
                            String name = child.getStringValue();
                            xml.setName(name);
                        } else if (nodeName.equals("keys")) {
                            String keys = child.getStringValue();
                            xml.setkeys(keys);
                        }else if (nodeName.equals("xpath")) {
                            String xpath = child.getStringValue();
                            xml.setxpath(xpath);
                        }
                    }
                }else if(ename.equals("textarea")){
                    Iterator xmlit = xmlElement.elementIterator();
                    while(xmlit.hasNext()) {
                        Element child = (Element) xmlit.next();
                        String nodeName = child.getName();
                        if (nodeName.equals("name")) {
                            //System.out.println(child.getStringValue());
                            String name = child.getStringValue();
                            xml.setName(name);
                        } else if (nodeName.equals("keys")) {
                            String keys = child.getStringValue();
                            xml.setkeys(keys);
                        }else if (nodeName.equals("xpath")) {
                            String xpath = child.getStringValue();
                            xml.setxpath(xpath);
                        }
                    }
                }
                else if(ename.equals("click")){
                    if(type.isEmpty() || type.equalsIgnoreCase("null")){
                        String clicktext = xmlElement.getText();
                        xml.setClick(clicktext);
                    }else if(type.equalsIgnoreCase("table")){
                        Iterator xmlit = xmlElement.elementIterator();
                        while(xmlit.hasNext()) {
                            Element child = (Element) xmlit.next();
                            String nodeName = child.getName();
                            if (nodeName.equals("row")) {
                                //System.out.println(child.getStringValue());
                                String row = child.getStringValue();
                                if(row.isEmpty() || row.equalsIgnoreCase("null")){
                                    row = "0";
                                }
                                xml.setrow(row);
                            } else if (nodeName.equals("column")) {
                                String column = child.getStringValue();
                                if (column.isEmpty() || column.equalsIgnoreCase("null")){
                                    column = "0";
                                }
                                xml.setcolumn(column);
                            }else if (nodeName.equals("keys")) {
                                String keys = child.getStringValue();
                                xml.setkeys(keys);
                            }
                        }
                    } else if(type.equalsIgnoreCase("combox")) {
                        Iterator xmlit = xmlElement.elementIterator();
                        while (xmlit.hasNext()) {
                            Element child = (Element) xmlit.next();
                            String nodeName = child.getName();
                            if (nodeName.equals("name")) {
                                //System.out.println(child.getStringValue());
                                String name = child.getStringValue();
                                xml.setClick(name);
                            } else if (nodeName.equals("poptab")) {
                                String id = child.getStringValue();
                                xml.setid(id);
                            }else if (nodeName.equals("xpath")) {
                                String xpath = child.getStringValue();
                                xml.setxpath(xpath);
                            }
                        }
                    }else if(type.equalsIgnoreCase("tree")) {
                        Iterator xmlit = xmlElement.elementIterator();
                        while (xmlit.hasNext()) {
                            Element child = (Element) xmlit.next();
                            String nodeName = child.getName();
                            if (nodeName.equals("node")) {
                                //System.out.println(child.getStringValue());
                                String node = child.getStringValue();
                                xml.setnode(node);
                            } else if (nodeName.equals("nodeop")) {
                                String nodeop = child.getStringValue();
                                xml.setnodeop(nodeop);
                            }
                        }
                    }
                    else {
                        String click = xmlElement.getText();
                        xml.setClick(click);
                    }
                }
                else if(ename.equals("gettext")){
                    String gettext = xmlElement.getText();
                    xml.setGettext(gettext);
                }
                else if(ename.equals("inputStatus")){
                    String gettext = xmlElement.getText();
                    xml.setGettext(gettext);
                }
                else if(ename.equals("buttonStatus")){
                    String gettext = xmlElement.getText();
                    xml.setGettext(gettext);
                }
                else if(ename.equals("assertText")){
                    Iterator xmlit = xmlElement.elementIterator();
                    while(xmlit.hasNext()) {
                        Element child = (Element) xmlit.next();
                        String nodeName = child.getName();
                        if (nodeName.equals("name")) {
                            //System.out.println(child.getStringValue());
                            String name = child.getStringValue();
                            xml.setName(name);
                        } else if (nodeName.equals("expect")) {
                            String expect = child.getStringValue();
                            xml.setexpect(expect);
                        }
                    }
                }else if(ename.equals("asserttext")){
                    Iterator xmlit = xmlElement.elementIterator();
                    while(xmlit.hasNext()) {
                        Element child = (Element) xmlit.next();
                        String nodeName = child.getName();
                        if (nodeName.equals("name")) {
                            String name = child.getStringValue();
                            xml.setName(name);
                        } else if (nodeName.equals("expect")) {
                            String expect = child.getStringValue();
                            xml.setexpect(expect);
                        }else if (nodeName.equals("xpath")) {
                            String xpath = child.getStringValue();
                            xml.setxpath(xpath);
                        }
                    }
                }
                else if(ename.equals("assertstatus")){
                    Iterator xmlit = xmlElement.elementIterator();
                    while(xmlit.hasNext()) {
                        Element child = (Element) xmlit.next();
                        String nodeName = child.getName();
                        if (nodeName.equals("name")) {
                            String name = child.getStringValue();
                            xml.setName(name);
                        } else if (nodeName.equals("expect")) {
                            String expect = child.getStringValue();
                            xml.setexpect(expect);
                        }else if (nodeName.equals("xpath")) {
                            String xpath = child.getStringValue();
                            xml.setxpath(xpath);
                        }
                    }
                }
                else if(ename.equals("assertgrid")){
                    Iterator xmlit = xmlElement.elementIterator();
                    while(xmlit.hasNext()) {
                        Element child = (Element) xmlit.next();
                        String nodeName = child.getName();
                        if (nodeName.equals("row")) {
                            String row = child.getStringValue();
                            if (row.isEmpty() || row.equalsIgnoreCase("null")) {
                                row = "0";
                            }
                            xml.setrow(row);
                        } else if (nodeName.equals("column")) {
                            String column = child.getStringValue();
                            if (column.isEmpty() || column.equalsIgnoreCase("null")) {
                                column = "0";
                            }
                            xml.setcolumn(column);
                        } else if (nodeName.equals("expect")) {
                            String expect = child.getStringValue();
                            xml.setexpect(expect);
                        }else if (nodeName.equals("xpath")) {
                            String xpath = child.getStringValue();
                            xml.setxpath(xpath);
                        }
                    }
                }
                else if(ename.equals("assertDB")){
                    Iterator xmlit = xmlElement.elementIterator();
                    while(xmlit.hasNext()) {
                        Element child = (Element) xmlit.next();
                        String nodeName = child.getName();
                        if (nodeName.equals("sql")) {
                            //System.out.println(child.getStringValue());
                            String sql = child.getStringValue();
                            xml.setsql(sql);
                        } else if (nodeName.equals("expect")) {
                            String expect = child.getStringValue();
                            xml.setexpect(expect);
                        }else if (nodeName.equals("postgresql")) {
                            String postgresql = child.getStringValue();
                            xml.setpostgresql(postgresql);
                        }
                    }
                }
                else if(ename.equals("assertLog")){
                    Iterator xmlit = xmlElement.elementIterator();
                    while(xmlit.hasNext()) {
                        Element child = (Element) xmlit.next();
                        String nodeName = child.getName();
                        if (nodeName.equals("beginlog")) {
                            String gettext = child.getStringValue();
                            xml.setGettext(gettext);
                        } else if (nodeName.equals("logtext")) {
                            String expect = child.getStringValue();
                            xml.setexpect(expect);
                        }
                    }
                }/*
                else if(ename.equals("beginlog")){
                    String gettext = xmlElement.getText();
                    xml.setGettext(gettext);
                }*/
                else if(ename.equals("gettexttable")) {
                    Iterator xmlit = xmlElement.elementIterator();
                    while (xmlit.hasNext()) {
                        Element child = (Element) xmlit.next();
                        String nodeName = child.getName();
                        if (nodeName.equals("row")) {
                            String row = child.getStringValue();
                            if (row.isEmpty() || row.equalsIgnoreCase("null")) {
                                row = "0";
                            }
                            xml.setrow(row);
                        } else if (nodeName.equals("column")) {
                            String column = child.getStringValue();
                            if (column.isEmpty() || column.equalsIgnoreCase("null")) {
                                column = "0";
                            }
                            xml.setcolumn(column);
                        }
                    }
                }else if(ename.equals("caseFile")){
                    String gettext = xmlElement.getText();
                    xml.setGettext(gettext);
                }else if(ename.equals("dataFile")){
                    String gettext = xmlElement.getText();
                    xml.setGettext(gettext);
                }
                else if(ename.equals("wait")){
                    String gettext = xmlElement.getText();
                    xml.setGettext(gettext);
                }
                else if(ename.equals("dbsql")){
                    /*
                    String getsql = xmlElement.getText();
                    xml.setsql(getsql);
                    */
                    Iterator xmlit = xmlElement.elementIterator();
                    while(xmlit.hasNext()) {
                        Element child = (Element) xmlit.next();
                        String nodeName = child.getName();
                        if (nodeName.equals("sql")) {
                            //System.out.println(child.getStringValue());
                            String sql = child.getStringValue();
                            xml.setsql(sql);
                        } else if (nodeName.equals("postgresql")) {
                            String postgresql = child.getStringValue();
                            xml.setpostgresql(postgresql);
                        }
                    }
                }
                else if(ename.equals("scroll")){
                    String gettext = xmlElement.getText();
                    xml.setGettext(gettext);
                }
                else if(ename.equals("iframe")){
                    String gettext = xmlElement.getText();
                    xml.setGettext(gettext);
                }
                else if(ename.equals("log")){
                    String log = xmlElement.getText();
                    xml.setGettext(log);
                }else if(ename.equalsIgnoreCase("upload")){
                    Iterator xmlit = xmlElement.elementIterator();
                    while(xmlit.hasNext()) {
                        Element child = (Element) xmlit.next();
                        String nodeName = child.getName();
                        if (nodeName.equals("name")) {
                            //System.out.println(child.getStringValue());
                            String name = child.getStringValue();
                            xml.setName(name);
                        } else if (nodeName.equals("filepath")) {
                            String keys = child.getStringValue();
                            xml.setkeys(keys);
                        }else if (nodeName.equals("xpath")) {
                            String xpath = child.getStringValue();
                            xml.setxpath(xpath);
                        }
                    }
                }else if(ename.equalsIgnoreCase("shellexec")){
                    Iterator xmlit = xmlElement.elementIterator();
                    while(xmlit.hasNext()) {
                        Element child = (Element) xmlit.next();
                        String nodeName = child.getName();
                        if (nodeName.equals("shellscript")) {
                            String keys = child.getStringValue();
                            xml.setkeys(keys);
                        }else if (nodeName.equals("shellparam")) {
                            String text = child.getStringValue();
                            xml.setGettext(text);
                        }
                    }
                }else if(ename.equals("depends")){
                    String gettext = xmlElement.getText();
                    xml.setGettext(gettext);
                }else if(ename.equals("group")){
                    String gettext = xmlElement.getText();
                    xml.setGettext(gettext);
                }else if(ename.equals("description")){
                    String gettext = xmlElement.getText();
                    xml.setGettext(gettext);
                }

                //xmlList.add(xml.toString());
                xmlList.add(xml.operateMap());
                xml = null;

            }
        } catch (DocumentException e) {

            e.printStackTrace();
        }
        return xmlList;
    }
}
