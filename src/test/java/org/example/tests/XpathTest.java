package org.example.tests;

import org.example.testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author tangzhou
 * @version 1.0
 * @date 2023/6/15 17:07
 */
public class XpathTest extends TestBase {
    public XpathTest() {
    }

    public XpathTest(Map<String, String> caseData) {
        super(caseData);
    }
    @Test(groups = {"XpathTest"},enabled=true)
    public void xpathTest1() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("XpathDemo/DemoXpath2.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);

        Thread.sleep(5000);
    }
    @Test(groups = {"XpathTest"},enabled=false)
    public void xpathTest2() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("XpathDemo/DemoXpath2.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        //用户名输入
        webDriver.findElement(By.xpath("//label[.='用户名:']/../input")).sendKeys("tangzhou");
        //密码输入
        webDriver.findElement(By.xpath("//label[.='密  码:']/../input")).sendKeys("123456");
        //确认密码输入
        webDriver.findElement(By.xpath("//label[.='确认密码:']/../input")).sendKeys("123456");
        //性别选择
        webDriver.findElement(By.xpath("//input[@value='m']")).click();
        //定位到所有的爱好复选框
        List<WebElement> ahCheckBoxs = webDriver.findElements(By.xpath("//label[.='爱好:']/../input[@type='checkbox']"));
        //全选所有复选框
        for(WebElement checkbox : ahCheckBoxs){
            //判断复选框如果处于非选择状态，就点击选择
            if(!checkbox.isSelected()){
                checkbox.click();
                Thread.sleep(3000);
            }
        }
        //文件上传
        webDriver.findElement(By.xpath("//input[@type='file' and @name='pic']")).sendKeys(filePath);
        //定位到城市下拉框
        WebElement city=webDriver.findElement(By.xpath("//select[@name='city']"));
        //初始化下拉框对象
        Select citySelect=new Select(city);
        //按照文本选择 北京
        citySelect.selectByVisibleText("北京");
        //内容文本框中输入任意内容
        webDriver.findElement(By.xpath("//label[.='内容:']/../textarea")).sendKeys("23333tttt");

        Thread.sleep(5000);
    }
}

