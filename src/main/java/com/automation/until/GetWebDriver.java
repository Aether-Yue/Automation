package com.automation.until;

import com.automation.model.ReadProject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 获取webdriver
 * 暂且只支持Windows界面chrome执行
 */
public class GetWebDriver {

    public WebDriver getWebDriver(String pjname){
//        String pjname= ReadProject.projectname;
        ReadTestProperties readconf = new ReadTestProperties();
        readconf.setProject_name(pjname);

        WebDriver driver = null;
        Boolean isChanel;
        isChanel = Boolean.valueOf(readconf.readTestProperties("ssh.sshChanel"));
        String nodeIP = readconf.readTestProperties("ssh.gridIP");
        String nodePort = readconf.readTestProperties("ssh.gridPort");
        String gridUrl = String.format("http://%s:%s/wd/hub", nodeIP, nodePort);
        if(isChanel){
            GetNetworkStatus netstatus = new GetNetworkStatus();
            netstatus.getNetworkStatus(nodeIP,nodePort);
            if(netstatus.netstatus){
                try {
                    DesiredCapabilities capability = DesiredCapabilities.chrome();
                    driver = new RemoteWebDriver(new URL(gridUrl), capability);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println("----------------can't connect "+nodeIP+"-------------");
                return driver;
            }
        }else {
            driver = new ChromeDriver();
        }
        return driver;

        /*
        // 判断系统
        String os = System.getProperties().getProperty("os.name");
        if (os != null && os.toLowerCase().indexOf("linux") > -1) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        }else {
            driver = new ChromeDriver();
        }*/
    }

    public WebDriverWait getWebWait(WebDriver driver,String pjname){
//        String pjname= ReadProject.projectname;
        ReadTestProperties readconf = new ReadTestProperties();
        readconf.setProject_name(pjname);
        String wait_time = readconf.readTestProperties("driver_wait_time");
        if(wait_time.isEmpty() || !wait_time.matches("\\d+")){
            wait_time = "5";
        }
        WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(wait_time));
        return wait;
    }
}
