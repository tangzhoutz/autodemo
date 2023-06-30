package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.testbase.TestBase;
import org.openqa.selenium.*;
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
import java.util.Set;

/**
 * @author tangzhou
 * @version 1.0
 * @date 2023/5/26 14:02
 */
public class NewWindowTest extends TestBase {
    public NewWindowTest() {
    }

    public NewWindowTest(Map<String, String> caseData) {
        super(caseData);
    }
    @Test(groups = {"NewWindowTest"},enabled=true)
    public void login() throws Exception {
        /**
         * 首先登录
         */
        //打开指定网页
        webDriver.get("http://tt-dev.unidms.com/Track/");
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
    }

    @Test(groups = {"NewWindowTest"},dependsOnMethods = {"login"})
    public void switchToWindow() throws Exception {
        /**
         * 以下是窗口切换测试
         */
        // 使用js打开一个新窗口，
        // 初始化js对象
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        //拼接JS字符串
        String js_str = "window.open('%s','_blank');";
        js_str=String.format(js_str,"https://www.baidu.com/");
        //执行JS语句打开一个窗口
        jsExecutor.executeScript(js_str);
        //获取当前的windowshandle，因为切换窗口并没有返回默认窗口的功能，所以必须自己记录一下，方便切换回来
        String defaultwindowHandle=webDriver.getWindowHandle();
        //获取所有的windowshandles
        Set<String> windowHandles= webDriver.getWindowHandles();
        //遍历所有的windowshandles并且切换过去后输出标题
        for (String windowHandle:windowHandles){
            //切换到窗口
            webDriver.switchTo().window(windowHandle);
            System.out.println("windowHandle="+windowHandle);
            System.out.println("title="+webDriver.getTitle());
            if(webDriver.getTitle().contains("百度一下，你就知道")){
                System.out.println("已正确切换到窗口："+webDriver.getTitle());
                //使用break退出循环，防止继续切换到其他窗口。
                break;
            }
            Thread.sleep(3000);
        }
        //切换到原窗口
        webDriver.switchTo().window(defaultwindowHandle);
    }

    /**
     * 退出登录
     */
    @Test(groups = {"NewWindowTest"},dependsOnMethods = {"switchToWindow"}, alwaysRun = true)
    public void loginOut() throws Exception {
        try {
            //设置休眠等待3秒
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //点击退出按钮
        webDriver.findElement(By.xpath("//a[contains(text(),'退出')]")).click();
        //退出弹框中点击确认按钮
        webDriver.findElement(By.xpath("//a[contains(text(),'确定')]")).click();

    }
}
