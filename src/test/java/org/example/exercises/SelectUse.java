package org.example.exercises;

import org.example.testbase.TestBase;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Map;

/**
 * @author tangzhou
 * @version 1.0
 * @date 2023/6/30 17:11
 */
public class SelectUse extends TestBase {
    public SelectUse() {
    }

    public SelectUse(Map<String, String> caseData) {
        super(caseData);
    }
    @Test(groups = {"SelectUse"},enabled=true)
    public void SelectTest() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("SelectDemo/DemoSelect2.html");
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
