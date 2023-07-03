package org.example.tests;

import org.example.testbase.TestBase;
import org.example.utilities.general.TestScreenSize;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.awt.*;
import java.net.URL;
import java.util.Map;

/**
 * @author tangzhou
 * @version 1.0
 * @date 2023/5/29 11:43
 */
public class AlertTest extends TestBase {
    public AlertTest() {
        System.out.println("AlertTest()");
    }

    public AlertTest(Map<String, String> caseData) {
        super(caseData);
    }

    @Test(groups = {"demo"})
    public void DemoAlert() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("AlertTestDemo/DemoAlert.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        // 点击弹框按钮
        webDriver.findElement(By.xpath("//input[@class='alert']")).click();
        // 等待2秒看下效果
        Thread.sleep(2000);
        // 定位到系统弹框
        Alert alert=webDriver.switchTo().alert();
        // 获取弹框的文本
        System.out.println("the text is ："+alert.getText());
        // 点击确认按钮
        alert.accept();
        // 等待2秒看下页面效果
        Thread.sleep(2000);
    }
    @Test(groups = {"demo"},dependsOnMethods = {"DemoAlert"})
    public void DemoConfirm() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("AlertTestDemo/DemoConfirm.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        // 点击弹框按钮
        webDriver.findElement(By.xpath("//input[@class='alert']")).click();
        // 等待2秒看下效果
        Thread.sleep(2000);
        // 定位到系统弹框
        Alert alert=webDriver.switchTo().alert();
        // 获取弹框的文本
        System.out.println("the text is ："+alert.getText());
        // 点击确认按钮
        alert.accept();
        // 点击取消按钮
//        alert.dismiss();
        // 等待2秒看下页面效果
        Thread.sleep(2000);
    }
    @Test(groups = {"demo"},dependsOnMethods = {"DemoConfirm"})
    public void DemoPrompt() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("AlertTestDemo/DemoPrompt.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        // 点击弹框按钮
        webDriver.findElement(By.xpath("//input[@class='alert']")).click();
        // 等待2秒看下效果
        Thread.sleep(2000);
        // 定位到系统弹框
        Alert alert=webDriver.switchTo().alert();
        // 获取弹框的文本
        System.out.println("the text is ："+alert.getText());
        // 弹框中输入文本
        alert.sendKeys("test");
        // 点击确认按钮
        alert.accept();
        // 等待2秒看下页面效果
        Thread.sleep(2000);
    }
    @Test(groups = {"demo"},dependsOnMethods = {"DemoPrompt"})
    public void DemoCustomAlert() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("AlertTestDemo/DemoCustomAlert.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        // 点击弹框按钮
        webDriver.findElement(By.xpath("//button[contains(.,'展示正确弹框')]")).click();
        // 等待2秒看下效果
        Thread.sleep(2000);
        // 定位到系统的文本框
        WebElement alertText=webDriver.findElement(By.id("logcontent"));
        // 获取弹框的文本
        System.out.println("the text is ："+alertText.getText());
        // 定位到确认按钮
        WebElement confirmBtn=webDriver.findElement(By.id("comfir"));
        // 点击确认按钮
        confirmBtn.click();
        // 等待2秒看下页面效果
        Thread.sleep(2000);
    }
}
