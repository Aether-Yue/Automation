package com.automation.until;

import com.automation.model.ReadProject;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.util.ResourceBundle;

/**
 * 获取ssh隧道
 * getSSH 为DB隧道
 * getSSHShell 为shell脚本隧道
 */
public class GetSSHChannel {
    private static ResourceBundle resource;
    private static BufferedInputStream inputStream;
    private static Logger logger = Logger.getLogger(GetSSHChannel.class);

    public static Integer localport_db = null;
    public static Integer localport_shell = null;

    public void getSSH(JDBCSSHChannel channel,String pjname) throws IOException {
//        String pjname= ReadProject.projectname;
        ReadTestProperties readconf = new ReadTestProperties();
        readconf.setProject_name(pjname);

        Integer localPort = GetSSHChannel.getLocalPort();
        logger.info("SSHChanel bind port : " + localPort);
        localport_db=localPort;
        String sshHost = readconf.readTestProperties("ssh.sshHost");
        Integer sshPort = Integer.valueOf(readconf.readTestProperties("ssh.sshPort"));
        String sshUserName =readconf.readTestProperties("ssh.sshUserName");
        String sshPassWord =readconf.readTestProperties("ssh.sshPassWord");
        String remotoHost =readconf.readTestProperties("ssh.remotoHost");
        Integer remotoPort = Integer.valueOf(readconf.readTestProperties("ssh.remotoPort"));
//        JDBCSSHChannel channel = new JDBCSSHChannel();
        channel.goSSH(localPort, sshHost, sshPort, sshUserName, sshPassWord,
                remotoHost, remotoPort);
    }

    public void getSSHShell(JDBCSSHChannel channel,String ip,Integer port,String pjname) throws IOException {
//        String pjname= ReadProject.projectname;
        ReadTestProperties readconf = new ReadTestProperties();
        readconf.setProject_name(pjname);

        Integer localPort = GetSSHChannel.getLocalPort();
        logger.info("SSHChanel bind port : " + localPort);
        localport_shell=localPort;
        String sshHost = readconf.readTestProperties("ssh.sshHost");
        Integer sshPort = Integer.valueOf(readconf.readTestProperties("ssh.sshPort"));
        String sshUserName =readconf.readTestProperties("ssh.sshUserName");
        String sshPassWord =readconf.readTestProperties("ssh.sshPassWord");
        String remotoHost =ip;
        Integer remotoPort = port;
//        JDBCSSHChannel channel = new JDBCSSHChannel();
        channel.goSSH(localPort, sshHost, sshPort, sshUserName, sshPassWord,
                remotoHost, remotoPort);
    }

    public static Integer getLocalPort() throws IOException {
        ServerSocket serverSocket =  new ServerSocket(0);
        Integer localPort = serverSocket.getLocalPort();
        serverSocket.close();
        return localPort;
    }
}
