package com.automation.service;

import com.automation.model.ReadProject;
import com.automation.until.GetSSHChannel;
import com.automation.until.JDBCSSHChannel;
import com.automation.until.ReadTestProperties;
import com.automation.until.RemoteShellExecutor;

import java.io.*;

/**
 * 调用执行shell脚本
 */
public class ShellExec {

    private static String ip;
    private static String username;
    private static String password;
    private static Integer port;

    private static Boolean isChanel;

    public static void shellexecOperation(String shellpath, String shellparam,String pjname) throws Exception {
        ReadTestProperties readconf = new ReadTestProperties();
        readconf.setProject_name(pjname);
        ip = readconf.readTestProperties("server_ip");
        username = readconf.readTestProperties("server_username");
        password = readconf.readTestProperties("server_password");
        port = Integer.valueOf(readconf.readTestProperties("server_port"));
        isChanel = Boolean.valueOf(readconf.readTestProperties("ssh.sshChanel"));
        JDBCSSHChannel channel = null;
        if (isChanel) {
            //开启隧道
            channel = new JDBCSSHChannel();
            try {
                GetSSHChannel getd = new GetSSHChannel();
                getd.getSSHShell(channel, ip, port,pjname);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ip = "localhost";
            port = GetSSHChannel.localport_shell;
        }
        RemoteShellExecutor executor = new RemoteShellExecutor(ip, port, username, password);
        int ResultCode = executor.exec(shellpath + " " + shellparam);
        if (isChanel) {
            channel.close();
        }
    }

    public static String shellexecs(String shell,String pjname) throws Exception {
        ReadTestProperties readconf = new ReadTestProperties();
        readconf.setProject_name(pjname);
        ip = readconf.readTestProperties("server_ip");
        username = readconf.readTestProperties("server_username");
        password = readconf.readTestProperties("server_password");
        port = Integer.valueOf(readconf.readTestProperties("server_port"));
        isChanel = Boolean.valueOf(readconf.readTestProperties("ssh.sshChanel"));
        JDBCSSHChannel channel = null;
        if (isChanel) {
            //开启隧道
            channel = new JDBCSSHChannel();
            try {
                GetSSHChannel getS = new GetSSHChannel();
                getS.getSSHShell(channel, ip, port,pjname);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ip = "localhost";
            port = GetSSHChannel.localport_shell;
        }
        RemoteShellExecutor executor = new RemoteShellExecutor(ip, port, username, password);
        String result = executor.execs(shell);
        if (isChanel) {
            channel.close();
        }
        return result;
    }
}
