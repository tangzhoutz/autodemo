package org.example.tests;

import org.example.testbase.TestBase;
import org.example.utilities.general.RobotUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Map;

/**
 * @author tangzhou
 * @version 1.0
 * @date 2023/6/13 15:15
 */
public class FileTest extends TestBase {
    public FileTest() {
    }

    public FileTest(Map<String, String> caseData) {
        super(caseData);
    }
    @Test(groups = {"FileTest"},enabled=true)
    public void fileTest1() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("FileDemo/DemoFile.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get("https://html-file-upload.netlify.app/single/index.html");
        Thread.sleep(3000);
        //定位到文件上传input控件
        WebElement fileInput=webDriver.findElement(By.id("file-uploader"));
        //直接使用方法输入 文件路径即可
        fileInput.sendKeys(filePath);

        Thread.sleep(5000);
    }
    @Test(groups = {"FileTest"},enabled=true)
    public void fileTest2() throws Exception {

        String filePath="D:\\javaspace\\autodemo\\target\\classes\\FileDemo\\DemoFile.html";
        // 打开网页（直接打开html文件）
        webDriver.get("https://html-file-upload.netlify.app/single/index.html");
        Thread.sleep(3000);
        //定位到文件上传input控件
        WebElement fileInput=webDriver.findElement(By.id("file-uploader"));
        // 这里使用action进行偏移点击，因为click是点击元素中间点，然后上传控件的中间位置是文本框
        Actions action=new Actions(webDriver);
        // 向X轴往左侧偏移100 进行点击。
        action.moveToElement(fileInput,-100,0).click().perform();
        Thread.sleep(3000);
        try {
            //初始化对象
            Robot robot=new Robot();
            //模拟键盘输入文件路径
            RobotUtils.keyPressString(robot,filePath);
            Thread.sleep(3000);
            //模拟键盘输入回车键
            RobotUtils.keyPress(robot, KeyEvent.VK_ENTER);
        }catch (Exception e){
            e.printStackTrace();
        }


        Thread.sleep(5000);
    }
}
