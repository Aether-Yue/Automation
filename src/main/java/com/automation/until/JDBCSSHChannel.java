package com.automation.until;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * ssh隧道类
 */
public class JDBCSSHChannel {
    private Session session;
    private Channel channel;

    /**
     *
     * @param localPort  本地host 建议mysql 3306 redis 6379
     * @param sshHost   ssh host
     * @param sshPort   ssh port
     * @param sshUserName   ssh 用户名
     * @param sshPassWord   ssh密码
     * @param remotoHost   远程机器地址
     * @param remotoPort	远程机器端口
     */
    public void goSSH(int localPort, String sshHost, int sshPort,
                      String sshUserName, String sshPassWord,
                      String remotoHost, int remotoPort) {
        try {
            JSch jsch = new JSch();
            //登陆跳板机
            session = jsch.getSession(sshUserName, sshHost, sshPort);
            session.setPassword(sshPassWord);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            //建立通道
            channel = session.openChannel("session");
            channel.connect();
            //通过ssh连接到mysql机器
            int assinged_port = session.setPortForwardingL(localPort, remotoHost, remotoPort);
//             session.setPortForwardingR(remotoPort,remotoHost,localPort);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭
     */
    public void close() {
        if (session != null && session.isConnected() ) {
            session.disconnect();
        }

        if (channel != null && session.isConnected() ) {
            channel.disconnect();
        }
    }
}

