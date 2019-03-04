package com.automation.until;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ReadJDBCProperties {
    private static ResourceBundle resource;
    private static BufferedInputStream inputStream;
    private static String project_name;
    private String parameter_value = null;
    private static Logger logger = Logger.getLogger(ReadJDBCProperties.class);


    public String getProject_name(){
        return project_name;
    }

    public void setProject_name(String projectname){
        this.project_name=projectname;
    }

    public synchronized String readJDBCProperties(String parameter_name) {
        if(StringUtils.isNotBlank(parameter_name)) {
            String dir = System.getProperty("user.dir") + File.separator + "config" + File.separator + project_name + File.separator + project_name + "_jdbc.properties";
            File file = new File(dir);
            if (file.exists()) {
                try {
                    inputStream = new BufferedInputStream(new FileInputStream(dir));
                    resource = new PropertyResourceBundle(inputStream);
                    inputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
                parameter_value = resource.getString(parameter_name);
            }else {
                logger.error("can't find "+project_name + "_test.properties");
            }
        }
        return parameter_value;
    }
}
