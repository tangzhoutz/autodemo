package org.example.exercises;

import org.example.testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Map;

/**
 * @author tangzhou
 * @version 1.0
 * @date 2023/6/30 10:53
 */
public class IframeTest extends TestBase {
    public IframeTest() {
    }

    public IframeTest(Map<String, String> caseData) {
        super(caseData);
    }
    @Test(groups = {"IframeTest"},enabled=true)
    public void iframeTest() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("IframeDemo/ExamIframe1.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        /**
         * 在这后面填写相关代码即可
         */



        Thread.sleep(5000);
    }
}
