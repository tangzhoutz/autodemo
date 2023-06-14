package org.example.tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestBase {
    public AppTest() {
        System.out.println("AppTest 3333");
    }

    public AppTest(Map<String, String> caseData) {
        super(caseData);
    }

    /**
     * 登录
     */
    //todo test方法注入 ITestContext Method后 retry时会报错参数数量异常
    @Test(groups = {"main_business1"}, alwaysRun = true)
    public void login() throws Exception {
        System.out.println("login=("+caseData.get("username")+","+caseData.get("password")+")");

        //打开指定网页
        webDriver.get("https://tt-dev.unidms.com/Track/");

        //输入账号
        //这里分为2步：1、定位到操作元素，2、执行操作
        webDriver.findElement(By.xpath("//input[@name='UserName']")).sendKeys("guanliyuan");
        //输入密码
        webDriver.findElement(By.xpath("//input[@name='Password']")).sendKeys("Aa12345678!");
        //点击登录按钮
        WebElement loginBtn=webDriver.findElement(By.id("loginBtn"));
        loginBtn.click();
        //获取登录后主页的成功标识
        WebElement tab_title = webDriver.findElement(By.xpath("//a[text()='主页']"));

        //断言判断上一步的成功标识是否显示，以此判断登录是否成功
        Assert.assertEquals(tab_title.isDisplayed(), true, "login succeed");
        /**
         * 以下是操作工厂管理查询的代码
         */
        //点击左侧基础信息菜单
        webDriver.findElement(By.xpath("//span[text()='基础信息']")).click();
        //点击基础信息下 工厂管理子菜单
        webDriver.findElement(By.xpath("//a[@data-moduleid='4113']")).click();

        //定位到iframe元素，和普通元素定位一样
        WebElement iframe_Factory =webDriver.findElement(By.xpath("//iframe[@data-moduleid='4113']"));
        //切换到此iframe
        webDriver.switchTo().frame(iframe_Factory);

        //在新打开的页面中，查询条件文本框输入工厂编码
        webDriver.findElement(By.xpath("//div[@id='FactoryID']/input")).sendKeys("XIX");
        //selenium动作太快，页面还没反应过来就点了查询，这里加个等待，保证正常查询到数据
        Thread.sleep(3000);
        //点击查询按钮
        webDriver.findElement(By.className("icon-search")).click();
        //切换到父iframe
//        webDriver.switchTo().parentFrame();
        //切换到默认iframe，这里2个方法都可以，但是推荐使用切换到默认iframe
        webDriver.switchTo().defaultContent();

    }

    /**
     * 退出登录
     */
    @Test(groups = {"main_business1"},dependsOnMethods = {"login"}, alwaysRun = true)
    public void loginOut() throws Exception {
        try {
            //设置休眠等待3秒
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //切换到默认iframe
        webDriver.switchTo().defaultContent();
        //点击退出按钮
        webDriver.findElement(By.xpath("//a[contains(text(),'退出')]")).click();
        //退出弹框中点击确认按钮
        webDriver.findElement(By.xpath("//a[contains(text(),'确定')]")).click();

    }

}
