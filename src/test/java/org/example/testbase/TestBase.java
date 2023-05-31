package org.example.testbase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tangzhou
 * @version 1.0
 * @date 2023/5/29 11:11
 */
public class TestBase {

    private Map<String,String> caseData;
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
//        org.openqa.selenium.Dimension dimension = new org.openqa.selenium.Dimension(1024, 768);
//        webDriver.manage().window().setSize(dimension);
        //最大化浏览器，使用后调整分辨率的设置会被覆盖
        webDriver.manage().window().maximize();
        System.out.println("beforeTest");

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
    public TestBase() {
        System.out.println("no-args constructor of "+this.getClass().getName()+"");
    }
    public TestBase(Map<String,String> caseData) {
        System.out.println("have args constructor of "+this.getClass().getName()+"");
        this.caseData=caseData;
    }

    @Factory
    public Object[] factory() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println("classname ="+this.getClass().getName());
        ArrayList<Object> test1ArrayList=new ArrayList<>();
        List<Map<String,String>> caseDataList= new ArrayList<>();
        caseDataList.add(new HashMap<>());
//        caseDataList.add(new HashMap<>());
        int i=0;
        for(Map<String,String> caseData:caseDataList){
            if(i==0) {
                caseData.put("username", "guanliyuan");
                caseData.put("password", "Aa12345678!");
                i++;
            }else {
                caseData.put("username", "guanliyuan2");
                caseData.put("password", "Aa12345678!");
            }

        }

        for(Map<String,String> caseData:caseDataList){
            Constructor<?> declaredConstructorCase = this.getClass().getDeclaredConstructor(Map.class);
            declaredConstructorCase.setAccessible(true);
            Object objectCase = declaredConstructorCase.newInstance(caseData);

            test1ArrayList.add(objectCase);
        }

//        test1ArrayList.add(new AppTest("guanliyuan","Aa12345678!"));
        return test1ArrayList.toArray();
    }

}
