package com.automation.until;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *封装断言，避免失败中断
 */
public class Assertion {
    public static boolean flag = true;
    public static List<Error> errors = new ArrayList<Error>();

    public static void verifyEquals(String actual,String expected,String filename) throws IOException {
        if(StringUtils.isNotBlank(actual) && StringUtils.isNotBlank(expected)) {
            try {
                Assert.assertEquals(actual.replace(" ", ""), expected.replace(" ", ""));
            } catch (AssertionError e) {
                errors.add(e);
                flag = false;
                ScreenShot sc = new ScreenShot();
                sc.takeScreenShot(filename);
            }
        }else {
            flag = false;
            ScreenShot sc = new ScreenShot();
            sc.takeScreenShot(filename);
        }
    }

    public static void verifyEqualsLow(String actual,String expected,String filename) throws IOException {
        try{
            Assert.assertEquals(actual.replace(" ", "").toLowerCase(),expected.replace(" ", "").toLowerCase());
        }catch (AssertionError e){
            errors.add(e);
            flag = false;
            ScreenShot sc = new ScreenShot();
            sc.takeScreenShot(filename);
        }
    }

    public static void verifyEquals(Object actual,Object expected) {
        try{
            Assert.assertEquals(actual,expected);
        }catch (Error e2){
            errors.add(e2);
            flag = false;
            //ScreenShot sc = new ScreenShot();
            //sc.takeScreenShot();
        }
    }

    public static void verifyEquals(Boolean actual,Boolean expected,String filename) throws IOException {
        try{
            Assert.assertEquals(actual,expected,filename);
        }catch (Error e){
            errors.add(e);
            flag = false;
            ScreenShot sc = new ScreenShot();
            sc.takeScreenShot(filename);
        }
    }
}
