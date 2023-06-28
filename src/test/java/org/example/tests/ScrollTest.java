package org.example.tests;

import org.example.testbase.TestBase;
import org.example.utilities.general.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Map;

/**
 * @author tangzhou
 * @version 1.0
 * @date 2023/6/26 11:55
 */
public class ScrollTest extends TestBase {
    public ScrollTest() {
    }

    public ScrollTest(Map<String, String> caseData) {
        super(caseData);
    }
    @Test(groups = {"ScrollTest"})
    public void ScrollTest1() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("ScrollDemo/DemoScrollwheel2.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        Thread.sleep(3000);
        // 定位到需要滚动到的元素
        WebElement br=webDriver.findElement(By.xpath("//td[string()='12，2']"));
        //初始化js执行的对象
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        // 通过js滚动到元素位置
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", br);
        Thread.sleep(3000);
    }
    @Test(groups = {"ScrollTest"})
    public void ScrollTest2() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("ScrollDemo/DemoScrollwheel3.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        Thread.sleep(3000);
        //初始化js执行的对象
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        // 通过JS滚动到 最底部。
        jsExecutor.executeScript("window.scrollTo(0,document.documentElement.scrollHeight)");
        Thread.sleep(3000);
    }
    @Test(groups = {"ScrollTest"})
    public void ScrollTest3() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("ScrollDemo/DemoScrollwheel2.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        Thread.sleep(3000);
        // 定位到滚动条所在元素
        WebElement scrollEle=webDriver.findElement(By.xpath("//div[@id='scrolldiv']"));
        //初始化js执行的对象
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        // 通过js滚动到元素位置
        jsExecutor.executeScript("arguments[0].scrollTo(arguments[0].scrollWidth,arguments[0].scrollHeight);", scrollEle);
        Thread.sleep(3000);
    }
    @Test(groups = {"ScrollTest"})
    public void ScrollTest4() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("ScrollDemo/DemoScrollwheel3.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        Thread.sleep(3000);
        //初始化js执行的对象
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        // 获取页面高度
        Object height=jsExecutor.executeScript("return document.documentElement.scrollHeight;");
        // 输出结果为元素的高度 1739
        System.out.println(height);
        Thread.sleep(3000);
    }
}
