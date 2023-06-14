package org.example.tests;

import org.example.testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Map;

/**
 * @author tangzhou
 * @version 1.0
 * @date 2023/6/2 15:04
 */
public class DateTimeTest extends TestBase {
    public DateTimeTest() {
    }

    public DateTimeTest(Map<String, String> caseData) {
        super(caseData);
    }

    @Test(groups = {"DateTimeTest"},enabled=true)
    public void a1() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("DateTimeDemo/DemoDateTime2.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        Thread.sleep(3000);
        //定位到年月日时分秒日期控件
        WebElement dateTimeInput=webDriver.findElement(By.id("test04"));
        //直接使用方法输入 年月日时分秒
        dateTimeInput.sendKeys("2023-12-24 19:30:31");
        //定位到 年月日控件，带有readonly属性
        WebElement dateInput=webDriver.findElement(By.id("test03"));
        //初始化js执行的对象
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        // 通过js删除掉日期输入框的只读属性
        jsExecutor.executeScript("arguments[0].removeAttribute(\"readonly\");", dateInput);
        //直接使用方法输入 年月日
        dateInput.sendKeys("2023-12-24");
        Thread.sleep(5000);
    }
    @Test(groups = {"DateTimeTest"},enabled=true)
    public void a2() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("DateTimeDemo/DemoDateTime2.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        Thread.sleep(3000);
        //定位到年 日期控件
        WebElement yearInput=webDriver.findElement(By.id("test01"));
        //点击日期控件
        yearInput.click();
        Thread.sleep(3000);
        //定位到日期控件选择框中的 2027年
        WebElement yearBtn=webDriver.findElement(By.xpath("//div[@id='jedate']//span[text()='2027年']"));
        //点击2027年
        yearBtn.click();
        Thread.sleep(3000);
        //定位到确认按钮
        WebElement confirm=webDriver.findElement(By.xpath("//div[@id='jedate']//span[text()='确定']"));
        //点击确认按钮
        confirm.click();
        Thread.sleep(5000);
    }
    @Test(groups = {"DateTimeTest"},enabled=false)
    public void login() throws Exception {
        /**
         * 首先登录
         */
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


    }
    @Test(groups = {"DateTimeTest"},dependsOnMethods = {"login"},enabled=false)
    public void openMenu() throws Exception {
        /**
         * 以下是操作打开线路主数据菜单
         */
        //点击左侧基础信息菜单
        webDriver.findElement(By.xpath("//span[text()='基础信息']")).click();
        Thread.sleep(2000);
        // 定位到线路子数据子菜单元素
        WebElement subMenu=webDriver.findElement(By.xpath("//a[@data-moduleid='4148']"));
        //初始化js执行的对象
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        // 通过js滚动到子菜单位置
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", subMenu);

        subMenu.click();

        //定位到iframe元素，和普通元素定位一样
        WebElement iframe_subMenu =webDriver.findElement(By.xpath("//iframe[@data-moduleid='4148']"));
        //切换到此iframe
        webDriver.switchTo().frame(iframe_subMenu);

        Thread.sleep(3000);
    }
    @Test(groups = {"DateTimeTest"},dependsOnMethods = {"openMenu"},enabled=false)
    public void DateTimeTest() throws Exception {


        Thread.sleep(3000);

        WebElement time1=webDriver.findElement(By.xpath("//label[contains(.,'失效日期') or contains(text(),'失效日期')]"));
        time1.sendKeys("2023-12-24 19:30:31");
        Thread.sleep(5000);
    }



}
