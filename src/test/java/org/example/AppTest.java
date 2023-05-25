package org.example;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
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

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static int flag=1;
    private String username;
    private String password;
    public WebDriver webDriver;
    @BeforeClass(alwaysRun = true)
    public void beforeClass(){
        //谷歌浏览器的配置选项
        ChromeOptions options = new ChromeOptions();
        //设置分辨率，固定分辨率以达到不同电脑不会出现分辨率不一样导致的脚本不兼容
        options.addArguments("--window-size=1920,1080");
        //安装最新的chromedriver
        WebDriverManager.chromedriver().setup();
        //这里就是初始化驱动对象
        webDriver = new ChromeDriver(options);
        //设置页面加载超时
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
        //设置等待超时
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        //最大化浏览器
        webDriver.manage().window().maximize();
        System.out.println("beforeTest");
        System.out.println("beforeTest=("+username+","+password+")");
    }
    @AfterClass(alwaysRun = true)
    public void afterClass(ITestContext iTestContext) {
        if (webDriver != null) {
            webDriver.quit();
        }
        System.out.println("afterTest");
    }
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result, Method method) throws Exception{
        System.out.println("afterMethod");
    }
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestResult result, Method method) throws Exception{
        System.out.println("beforeMethod");
    }
    public AppTest() {
        System.out.println("AppTest 3333");
    }
    public AppTest(String username, String password) {
        System.out.println("AppTest(String username, String password)=("+username+","+password+")");
        this.username = username;
        this.password = password;
    }
    @DataProvider(name = "twoParam")
    public Object[][] provideDate(){
        System.out.println("provideDate");
        Object[][] o = new Object[][] {
                {"guanliyuan","Aa12345678!"}
//                ,{"fcpmw","Aa12345678"}
//                ,{"fcpcy","Aa12345678"}
        };
        return o;
    }

    /**
     * 工厂实例化模式运行，不能和priority一起使用，否则运行顺序不会以实例化运行。
     * @param username
     * @param password
     * @return
     */
    @Factory(dataProvider ="twoParam")
    public Object[] factory(String username, String password) {
        System.out.println("factory");
        ArrayList<AppTest> test1ArrayList=new ArrayList<>();
        test1ArrayList.add(new AppTest(username,password));
        return test1ArrayList.toArray();
    }
    /**
     * 登录
     */
    //todo test方法注入 ITestContext Method后 retry时会报错参数数量异常
    @Test(groups = {"main_business1"}, alwaysRun = true)
    public void login() throws Exception {
        System.out.println("login=("+username+","+password+")");
//        webDriver.navigate()
        //打开指定网页
        webDriver.get("https://tt-dev.unidms.com/Track/");

        //输入账号
        //这里分为2步：1、定位到操作元素，2、执行操作
        webDriver.findElement(By.xpath("//input[@name='UserName']")).sendKeys(username);
        //输入密码
        webDriver.findElement(By.xpath("//input[@name='Password']")).sendKeys(password);
        //点击登录按钮
        webDriver.findElement(By.id("loginBtn")).click();
        //获取登录后主页的成功标识
        WebElement tab_title = webDriver.findElement(By.xpath("//a[text()='主页']"));
        WebElement tab_close= webDriver.findElement(By.xpath("//button[@class='dropdown J_tabClose']"));

        //依赖于代码
        System.out.println("getAttribute textContent="+tab_close.getAttribute("textContent"));
        //依赖于页面显示
        System.out.println("getAttribute innerText="+tab_close.getAttribute("innerText"));
        System.out.println("getAttribute innerHTML="+tab_close.getAttribute("innerHTML"));
        System.out.println("getAttribute outerHTML="+tab_close.getAttribute("outerHTML"));
        System.out.println("getAttribute class="+tab_close.getAttribute("class"));
        System.out.println("getAttribute data-toggle="+tab_close.getAttribute("data-toggle"));
        System.out.println("getAttribute name="+tab_close.getAttribute("name"));

        System.out.println("DomAttribute willValidate="+tab_close.getDomAttribute("willValidate"));
        System.out.println("DomAttribute textContent="+tab_close.getDomAttribute("textContent"));
        System.out.println("DomAttribute class="+tab_close.getDomAttribute("class"));
        System.out.println("DomAttribute data-toggle="+tab_close.getDomAttribute("data-toggle"));
        System.out.println("DomAttribute name="+tab_close.getDomAttribute("name"));


        System.out.println("DomProperty willValidate="+tab_close.getDomProperty("willValidate"));
        System.out.println("DomProperty textContent="+tab_close.getDomProperty("textContent"));
        System.out.println("DomProperty innerText="+tab_close.getDomProperty("innerText"));
        System.out.println("DomProperty innerHTML="+tab_close.getDomProperty("innerHTML"));
        System.out.println("DomProperty outerHTML="+tab_close.getDomProperty("outerHTML"));
        System.out.println("DomProperty class="+tab_close.getDomProperty("class"));
        System.out.println("DomProperty data-toggle="+tab_close.getDomProperty("data-toggle"));
        System.out.println("DomProperty name="+tab_close.getDomProperty("name"));

        //断言判断上一步的成功标识是否显示，以此判断登录是否成功
        Assert.assertEquals(tab_title.isDisplayed(), true, "login succeed");


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
        //点击退出按钮
        webDriver.findElement(By.xpath("//a[contains(text(),'退出')]")).click();
        //退出弹框中点击确认按钮
        webDriver.findElement(By.xpath("//a[contains(text(),'确定')]")).click();

    }

}
