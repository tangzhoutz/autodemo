package org.example.tests;

import org.example.testbase.TestBase;
import org.example.utilities.general.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Map;

/**
 * @author tangzhou
 * @version 1.0
 * @date 2023/7/7 16:58
 */
public class DragAndDropTest extends TestBase {
    public DragAndDropTest() {
    }

    public DragAndDropTest(Map<String, String> caseData) {
        super(caseData);
    }
    /**
     * 模拟鼠标操作示例
     * @throws Exception
     */
    @Test(groups = {"DragAndDropTest"})
    public void dragAndDropTest() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("ActionsDemo/DemoDraggable.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        // 定位到需要拖拽的元素
        WebElement d1=webDriver.findElement(By.xpath("//img"));
        // 定位到拖拽目标位置
        WebElement d2=webDriver.findElement(By.id("d2"));
        // 以下方法是自定义的调用JS代码 拖动元素
        CommonUtils.dragAndDrop(webDriver,d1,d2);
        //初始化action对象 不能使用action的拖动方法，不会报错也不会拖动。
//        Actions action=new Actions(webDriver);
        //H5的拖动控件，不能使用action的拖动方法，不会报错也不会拖动。
//        action.dragAndDrop(d1,d2).perform();
        Thread.sleep(3000);
    }

}
