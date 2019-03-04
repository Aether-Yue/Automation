package com.automation.until;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.automation.model.ReadProject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbTestDru {
    private static Boolean isChanel;

    public static DruidDataSource getDb(String pjname) {
//        String pjname= ReadProject.projectname;
        ReadTestProperties readconf = new ReadTestProperties();
        readconf.setProject_name(pjname);
        ReadJDBCProperties readjdbc = new ReadJDBCProperties();
        readjdbc.setProject_name(pjname);
        String url = readjdbc.readJDBCProperties("jdbc.url");
        String user = readjdbc.readJDBCProperties("jdbc.username");
        String password = readjdbc.readJDBCProperties("jdbc.password");
        String dbdriver = readjdbc.readJDBCProperties("jdbc.driver");
        String url_new = url;
        isChanel = Boolean.valueOf(readconf.readTestProperties("ssh.sshChanel"));
        if(isChanel) {
            String[] url_before = url.split(":");
            if (url_before[1].equalsIgnoreCase("oracle")) {
                url_before[3] = "@localhost";
                url_before[4] = GetSSHChannel.localport_db.toString();
                for (int i = 0; i < url_before.length; i++) {
                    url_new = url_new + url_before[i];
                }
            } else if (url_before[2].equalsIgnoreCase("sqlserver")) {
                url_before[3] = "//localhost";
                url_before[4] = GetSSHChannel.localport_db.toString();
                for (int i = 0; i < url_before.length; i++) {
                    url_new = url_new + url_before[i];
                }
            } else {
                url_before[2] = "//localhost";
                url_before[3] = GetSSHChannel.localport_db.toString();
                for (int i = 0; i < url_before.length; i++) {
                    url_new = url_new + url_before[i];
                }
            }
        }

        DruidDataSource dataSource = new DruidDataSource();
        //获取驱动
        dataSource.setDriverClassName(dbdriver);
        //建立连接
        dataSource.setUrl(url_new);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    public static String selectCount(String sql,String pjname) {
        String count = "-1";
        try {
//            DbTestDru.getSSH();
            //获取连接
            DruidPooledConnection conn = getDb(pjname).getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            List<String> resultlist = new ArrayList<>();
            while (resultSet.next()) {
                count = resultSet.getString(1);
                resultlist.add(count);
            }
            if (resultlist.size() > 1) {
                for (int a = 0; a <= resultlist.size() - 2; a++) {
                    count = resultlist.get(resultlist.size() - 2 - a) + "," + count;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        channel.close();
        return count;
    }

    public static void dbTestDu(String sql,String pjname) {
        try {
//            DbTestDru.getSSH();
            //获取连接
            DruidPooledConnection conn = getDb(pjname).getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            int sql_status = statement.executeUpdate();
            System.out.println("sql_status:" + sql_status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        channel.close();
    }

    public static String selectRows(String sql,String pjname) {
        String rows = "-1";
        try {
//            DbTestDru.getSSH();
            //获取连接
            DruidPooledConnection conn = getDb(pjname).getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            List<String> resultlist = new ArrayList<>();
            while (resultSet.next()) {
                rows = resultSet.getString(1);
                resultlist.add(rows);
            }
            if (resultlist.size() > 1) {
                for (int a = 0; a <= resultlist.size() - 2; a++) {
                    rows = resultlist.get(resultlist.size() - 2 - a) + "," + rows;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        channel.close();
        return rows;
    }



/*    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        JDBCSSHChannel channel = new JDBCSSHChannel();
        try {
            GetSSHChannel.getSSH(channel);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        PostgreSqlConnect pg = new PostgreSqlConnect();
//        pg.dbsql("insert into t01_test140 values(4,4)");
        DbTestDru.dbTestDu("insert into t01_test140 values(7,7)");
        channel.close();
    }*/

}
