package com.automation.until;

import java.io.*;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/*
*判断当前数据库使用的是orcl还是postgresql
 */
public class CurrentSql {
    private static ResourceBundle resource;
    private static BufferedInputStream inputStream;
    public static String curentsql() {
        String dir = System.getProperty("user.dir") + File.separator + "config" + File.separator + "jdbc.properties";
        try {
            inputStream = new BufferedInputStream(new FileInputStream(dir));
            resource = new PropertyResourceBundle(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ee) {
            ee.printStackTrace();
        }
        String driver_name = resource.getString("jdbc.driver");
        String dbtype = null;
        if(driver_name.equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            dbtype="oracle";
        }else if(driver_name.equalsIgnoreCase("org.postgresql.Driver")){
            dbtype="postgresql";
        }
        return dbtype;
    }
}
