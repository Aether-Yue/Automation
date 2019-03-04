package com.automation.mobile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;


public class AndroidDemo {
    public static void main(String[] args) throws MalformedURLException {
        AndroidDriver driver;
        DesiredCapabilities des = new DesiredCapabilities();
        //    des.setCapability("automationName", "Appium");//Selendroid //自动化的模式选择
        //     des.setCapability("app", "C:\\software\\CalcTest.apk");//配置待测试的apk的路径
        //des.setCapability("browserName", "chrome");
        des.setCapability("platformName", "Android");//平台名称
        des.setCapability("platformVersion", "8.0");//手机操作系统版本
        des.setCapability("udid", "192.168.187.101:5555");//连接的物理设备的唯一设备标识
        des.setCapability("deviceName", "device");//使用的手机类型或模拟器类型  UDID

        des.setCapability("appPackage", "com.logwire.app");//App安装后的包名,注意与原来的CalcTest.apk不一样
        des.setCapability("appActivity", "com.logwire.app.activity.WebViewActivity");//app测试人员常常要获取activity，进行相关测试

        des.setCapability("unicodeKeyboard", true);//支持中文输入
        des.setCapability("resetKeyboard", true);//支持中文输入
        des.setCapability("newCommandTimeout", "10");//没有新命令时的超时时间设置
        des.setCapability("nosign", true);//跳过检查和对应用进行 debug 签名的步骤
        des.setCapability("noReset", true);//已经装了则不会重新安装apk

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), des);//appium地址
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//设置超时等待时间,默认250ms


        driver.findElement(By.xpath("//*[@content-desc='基础组件demo1']")).click();
        driver.findElementByAndroidUIAutomator("new UiSelector().description(\"加载flow\")").click();
        String aaa = driver.findElement(By.xpath("//android.widget.WebView/android.view.View[13]")).getText();
        System.out.println(aaa);
        driver.findElementByAndroidUIAutomator("new UiSelector().description(\"萌新\")").click();

    }
}
