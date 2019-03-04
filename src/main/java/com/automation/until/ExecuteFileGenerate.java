package com.automation.until;

import com.automation.service.ReadXMLByDom4j;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成执行文件
 */
public class ExecuteFileGenerate {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static void executeFileGenerate(String writepath, String project, JsonObject json){

        ReadTestProperties readconf = new ReadTestProperties();
        readconf.setProject_name(project);

        String class_name=readconf.readTestProperties("class_name");

        File writefile = new File(writepath);
        if(writefile.exists()){
            writefile.delete();
        }

        String begindata = "package com.automation.controller;" +
                LINE_SEPARATOR + LINE_SEPARATOR +
                "import com.automation.until.AssertionListener;" +LINE_SEPARATOR+
                "import org.testng.annotations.Listeners;" +LINE_SEPARATOR+
                "import org.testng.annotations.Test;" +LINE_SEPARATOR+
                "import java.io.File;" +LINE_SEPARATOR+
                LINE_SEPARATOR+
                "@Listeners({AssertionListener.class})" +LINE_SEPARATOR+
                "public class "+class_name+" {" +LINE_SEPARATOR+
                "public "+class_name+"() {" +LINE_SEPARATOR+
                "System.out.println(\"AutomationControlTest代码生成并且编译运行成功！\");" +LINE_SEPARATOR+
                "}"+LINE_SEPARATOR+LINE_SEPARATOR;
        ReadFilePath.writeFile(writepath,begindata);


//        String execute_path = readconf.readTestProperties("execute_path");
        String execute_path = System.getProperty("user.dir") + File.separator + "testcase";

//        List<String> filelist = ReadFilePath.getFileList(execute_path);
        List<String> filelist = GetCaseList.getCaseList(json);
        String testlinkEnable = readconf.readTestProperties("testlink_enable");
        Boolean testlink_enable = false;
        if(StringUtils.isNotBlank(testlinkEnable)) {
            if (testlinkEnable.equalsIgnoreCase("true") || testlinkEnable.equalsIgnoreCase("false")) {
                testlink_enable = Boolean.valueOf(testlinkEnable);
            }
        }
        for(String file_name : filelist) {
            File file = new File(execute_path + File.separator + file_name);
            List<Object> xmlList = new ReadXMLByDom4j().getXml(file);
            String depends=null;
            String group="execute";
            String description="description";
            for (int i = 0; i < xmlList.size(); i++) {//循环遍历所有行
                Map<String, String> rowxml = (HashMap<String, String>) xmlList.get(i);
                for (Map.Entry<String, String> file_entry : rowxml.entrySet()) {
                    if (file_entry.getKey().equalsIgnoreCase("elementname")) {
                        if(file_entry.getValue().equalsIgnoreCase("depends")){
                            depends=rowxml.get("gettext");
                        }else if(file_entry.getValue().equalsIgnoreCase("group")){
                            group=rowxml.get("gettext");
                        }else if(file_entry.getValue().equalsIgnoreCase("description")){
                            description=rowxml.get("gettext");
                        }
                        break;
                    }
                }
                /*
                if (StringUtils.isNotBlank(depends) && StringUtils.isNotBlank(group)) {
                    break;
                }
                */
            }
            String group_str="";
            String depends_str="";

            if(StringUtils.isNotBlank(depends) && StringUtils.isNotBlank(group)){
                group_str="groups = { "+replace_str(group)+"},";
                depends_str="dependsOnMethods={"+replace_str(depends)+"},";
            }else if(!StringUtils.isNotBlank(depends) && StringUtils.isNotBlank(group)){
                group_str="groups = { "+replace_str(group)+"},";
            }else if(StringUtils.isNotBlank(depends) && !StringUtils.isNotBlank(group)){
                depends_str="dependsOnMethods={"+replace_str(depends)+"},";
            }
            String caseid=null;
            String void_name=null;
            if (testlink_enable){
                if(file_name.contains("-")) {
                    String[] name_split = file_name.split("-");
                    caseid = name_split[1];
                    String[] filenamesp = name_split[2].split("\\.");
                    void_name = filenamesp[0];
                }
            }else {
                /*String[] filenamesp = file_name.split("\\.");
                void_name = filenamesp[0];*/
                String[] filenamesp = file_name.split("\\.");
                if(filenamesp[0].contains(File.separator)) {
                    String[] filenamesp2 = filenamesp[0].split(File.separator);
                    void_name = filenamesp2[filenamesp2.length-1];
                }else {
                    void_name = filenamesp[0];
                }
            }

            if(StringUtils.isNotBlank(void_name)) {

                String testdata = "@Test(" + group_str + depends_str + "description = \"" + description + "\")" + LINE_SEPARATOR +
                        "public void " + void_name + "() {" + LINE_SEPARATOR +
                        "String file_name = \"" + file_name + "\";" + LINE_SEPARATOR +
                        //"System.out.println(file_name + \" Current Thread Id:\"+Thread.currentThread().getId());" + LINE_SEPARATOR +
                        "try {" + LINE_SEPARATOR +
                        "CaseExecuteControl.caseExecuteControl(file_name,\"" + caseid + "\",\"" + description + "\");" + LINE_SEPARATOR +
                        "}catch (Exception e) {" + LINE_SEPARATOR +
                        "e.printStackTrace();" + LINE_SEPARATOR +
                        "}" + LINE_SEPARATOR +
                        "} " + LINE_SEPARATOR + LINE_SEPARATOR;
                ReadFilePath.writeFile(writepath, testdata);
            }
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String enddata="}"+LINE_SEPARATOR+"//"+ df.format(new Date());
        ReadFilePath.writeFile(writepath,enddata);
    }

    private static String replace_str(String str_str){
        String return_str=null;
        if(StringUtils.isNotBlank(str_str)){
            if(str_str.contains(",")){
                String tmp = str_str.replace(",","\",\"");
                return_str = "\""+ tmp + "\"";
            }else {
                return_str = "\""+ str_str + "\"";
            }
        }
        return return_str;
    }
}
