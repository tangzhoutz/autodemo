package org.example.tests;

import org.example.testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author tangzhou
 * @version 1.0
 * @date 2023/6/2 11:18
 */
public class BoxTest extends TestBase {
    public BoxTest() {
    }

    public BoxTest(Map<String, String> caseData) {
        super(caseData);
    }
    /**
     * 单选框示例
     * @throws Exception
     */
    @Test(groups = {"BoxTest"})
    public void RadioBoxTest() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("BoxDemo/DemoBox.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        Thread.sleep(3000);
        //定位到单选框 男
        WebElement RadioBoy=webDriver.findElement(By.id("boy"));
        //定位到单选框 女
        WebElement RadioGirl=webDriver.findElement(By.id("girl"));
        //选择 男
        RadioBoy.click();
        //等待5秒查看效果
        Thread.sleep(5000);
        //选择 女
        RadioGirl.click();
        Thread.sleep(5000);
    }
    /**
     * 复选框示例
     * @throws Exception
     */
    @Test(groups = {"BoxTest"})
    public void CheckBoxTest1() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("BoxDemo/DemoBox.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        Thread.sleep(3000);
        //定位到复选框所有的元素
        List<WebElement> checkBoxs=webDriver.findElements(By.xpath("//*[@type='checkbox']"));
        //全选所有复选框
        for(WebElement checkbox : checkBoxs){
            //判断复选框如果处于非选择状态，就点击选择
            if(!checkbox.isSelected()){
                checkbox.click();
                Thread.sleep(3000);
            }
        }
        //取消选择所有复选框
        for(WebElement checkbox : checkBoxs){
            //判断复选框如果处于选择状态，就点击取消选择
            if(checkbox.isSelected()){
                checkbox.click();
                Thread.sleep(3000);
            }
        }

        Thread.sleep(3000);
    }
}
