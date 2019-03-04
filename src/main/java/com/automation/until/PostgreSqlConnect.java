package com.automation.until;
/**
 * 数据库操作（Postgre和Oracle）
 */

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class PostgreSqlConnect {
    private static ResourceBundle resource;
    private static BufferedInputStream inputStream;
    public static String sqlQueryCount(String sql) throws Exception{
        String count = "-1";
        String dir = System.getProperty("user.dir") + File.separator + "config"+ File.separator+"jdbc.properties";
        try {
            inputStream = new BufferedInputStream(new FileInputStream(dir));
            resource = new PropertyResourceBundle(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            String url = resource.getString("jdbc.url");
            String user = resource.getString("jdbc.username");
            String password = resource.getString("jdbc.password");
            String dbdriver = resource.getString("jdbc.driver");
            Connection connection = null;
            Statement statement = null;
            Class.forName(dbdriver);  //一定要注意和MySQL语法不同
            connection= DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<String> resultlist = new ArrayList<>();
            while (resultSet.next()) {
                count = resultSet.getString(1);
                resultlist.add(count);
            }
            if(resultlist.size()>1) {
                for (int a = 0; a <= resultlist.size() - 2; a++) {
                    count = resultlist.get(resultlist.size()-2-a) +"," + count;
                }
            }
            return count;
        }

        public void dbsql(String sql) throws ClassNotFoundException, SQLException {
            String dir = System.getProperty("user.dir") + File.separator + "config"+ File.separator+"jdbc.properties";
            try {
                inputStream = new BufferedInputStream(new FileInputStream(dir));
                resource = new PropertyResourceBundle(inputStream);
                inputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String url = resource.getString("jdbc.url");
            String user = resource.getString("jdbc.username");
            String password = resource.getString("jdbc.password");
            String dbdriver = resource.getString("jdbc.driver");
            Connection connection = null;
            Statement statement = null;
            Class.forName(dbdriver);  //一定要注意和上面的MySQL语法不同
            connection= DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
    }
