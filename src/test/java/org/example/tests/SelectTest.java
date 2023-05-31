package org.example.tests;

import org.example.testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

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
    public void login() throws Exception {

        //打开指定网页
        webDriver.get("https://tt-dev.unidms.com/Track/");

        //输入账号
        //这里分为2步：1、定位到操作元素，2、执行操作
        webDriver.findElement(By.xpath("//input[@name='UserName']")).sendKeys("guanliyuan");
        //输入密码
        webDriver.findElement(By.xpath("//input[@name='Password']")).sendKeys("Aa12345678!");
        //点击登录按钮
        WebElement loginBtn=webDriver.findElement(By.id("loginBtn"));
        loginBtn.click();
        //获取登录后主页的成功标识
        WebElement tab_title = webDriver.findElement(By.xpath("//a[text()='主页']"));
        //断言判断上一步的成功标识是否显示，以此判断登录是否成功
        Assert.assertEquals(tab_title.isDisplayed(), true, "login succeed");


    }
    @Test(groups = {"SelectTest"},dependsOnMethods = {"login"})
    public void factorySearch() throws Exception {
        /**
         * 以下是操作工厂管理查询的代码
         */
        //点击左侧基础信息菜单
        webDriver.findElement(By.xpath("//span[text()='基础信息']")).click();
        //点击基础信息下 工厂管理子菜单
        webDriver.findElement(By.xpath("//a[@data-moduleid='4113']")).click();
        //定位到iframe元素，和普通元素定位一样
        WebElement iframe_Factory =webDriver.findElement(By.xpath("//iframe[@data-moduleid='4113']"));
        //切换到此iframe
        webDriver.switchTo().frame(iframe_Factory);
        //在新打开的页面中，查询条件文本框输入工厂编码
        webDriver.findElement(By.xpath("//div[@id='FactoryID']/input")).sendKeys("XIX");
        //selenium动作太快，页面还没反应过来就点了查询，这里加个等待，保证正常查询到数据
        Thread.sleep(3000);
        //点击查询按钮
        webDriver.findElement(By.className("icon-search")).click();
        //点击XIX工厂的编辑按钮
        webDriver.findElement(By.xpath("//div[contains(@data-json,'XIX')]//a[@title='编辑']")).click();
        //切换回默认的iframe
        webDriver.switchTo().defaultContent();
        //定位到工厂编辑窗口的iframe
        WebElement iframe_BaseEdit=webDriver.findElement(By.xpath("//iframe[contains(@src,'EBas_Factory/EditIndex')]"));
        //切换到工厂编辑窗口的iframe
        webDriver.switchTo().frame(iframe_BaseEdit);
        //定位到右侧工厂基础信息编辑的iframe（工厂编辑窗口有2个iframe，是嵌套关系）
        WebElement iframe_Edit=webDriver.findElement(By.xpath("//iframe[@id='tmsCostTypeForm']"));
        //切换到右侧工厂基础信息编辑的iframe
        webDriver.switchTo().frame(iframe_Edit);
        //定位到select下拉框元素，注意必须是<select>标签
        WebElement selectEl=webDriver.findElement(By.xpath("//*[@id=\"EditBase\"]//label[contains(text(),\"Region\")]/..//select"));
        //初始化下拉框对象
        Select select=new Select(selectEl);
        Thread.sleep(3000);
        //通过下拉框显示的文本进行选择
        select.selectByVisibleText("R4");
//        //通过下拉框的顺序选择
//        select.selectByIndex(1);
//        //通过下拉框的value属性值选择
//        select.selectByValue("115001");
        Thread.sleep(3000);
        //切换到父iframe
//        webDriver.switchTo().parentFrame();
        //切换到默认iframe，这里2个方法都可以，但是推荐使用切换到默认iframe
        webDriver.switchTo().defaultContent();

    }

    @Test(groups = {"SelectTest"},dependsOnMethods = {"factorySearch"})
    public void factorySearch2() throws Exception {

    }

}
