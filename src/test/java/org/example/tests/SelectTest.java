package org.example.tests;

import org.example.testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Map;

/**
 * @author tangzhou
 * @version 1.0
 * @date 2023/5/30 9:28
 */
public class SelectTest extends TestBase {
    public SelectTest() {
    }

    public SelectTest(Map<String, String> caseData) {
        super(caseData);
    }
    @Test(groups = {"SelectTest"})
    public void DemoSelect() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("SelectDemo/DemoSelect.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        // 定位到select元素
        WebElement selectEle=webDriver.findElement(By.id("vehicle"));
        // 初始化Select类对象
        Select select=new Select(selectEle);
        // 通过显示文本定位
        select.selectByVisibleText("Opel");
        // 等待2秒看下页面效果
        Thread.sleep(2000);
    }

}
