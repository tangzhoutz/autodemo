package org.example.tests;


import org.example.testbase.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Map;

/**
 * @author tangzhou
 * @version 1.0
 * @date 2023/5/30 11:01
 */
public class ActionTest extends TestBase {
    public ActionTest() {
    }

    public ActionTest(Map<String, String> caseData) {
        super(caseData);
    }

    /**
     * 模拟键盘输入示例
     * @throws Exception
     */
    @Test(groups = {"ActionTest"},enabled=false)
    public void KeywordTest() throws Exception {
        //打开指定网页
        webDriver.get("https://tt-dev.unidms.com/Track/");
        //判断平台，如果是mac就使用COMMAND键，否则使用CONTROL。
        Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
        //定位到用户名输入框
        WebElement username = webDriver.findElement(By.xpath("//input[@name='UserName']"));
        //初始化action对象
        Actions action=new Actions(webDriver);
        action.sendKeys(username, "Selenium!") //在用户名输入框输入Selenium!
                .sendKeys(Keys.ARROW_LEFT) //键盘上的左键，也就是往左移动一格
                .keyDown(Keys.SHIFT)    //按下shift 不松开
                .sendKeys(Keys.ARROW_UP) //按方向键上键，配合shift就是全选文本
                .keyUp(Keys.SHIFT) // 松开shift键
                .keyDown(cmdCtrl) //按下COMMAND或者CONTROL，不松开
                .sendKeys("xvv")  //这里相当于依次按下了x v v。也就是先剪切 然后黏贴2次
                .keyUp(cmdCtrl) //松开COMMAND或者CONTROL
                .perform(); //执行操作，这个是固定的，调用后操作才会执行
        Thread.sleep(3000);
        Assert.assertEquals("SeleniumSelenium!", username.getAttribute("value"));
    }
    /**
     * 模拟鼠标操作示例
     * @throws Exception
     */
    @Test(groups = {"ActionTest"})
    public void MouseTest1() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("ActionsDemo/DemoDraggable2.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        //定位到需要拖拽的控件
        WebElement d1=webDriver.findElement(By.id("box1"));
        //初始化action对象
        Actions action=new Actions(webDriver);
        //X和Y坐标都拖拽300
        action.dragAndDropBy(d1,300,300).perform();
        Thread.sleep(3000);

    }
    /**
     * 模拟鼠标操作示例
     * @throws Exception
     */
    @Test(groups = {"ActionTest"})
    public void MouseTest2() throws Exception {
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
        dragAndDrop(webDriver,d1,d2);
        //初始化action对象 不能使用action的拖动方法，不会报错也不会拖动。
//        Actions action=new Actions(webDriver);
        //H5的拖动控件，不能使用action的拖动方法，不会报错也不会拖动。
//        action.dragAndDrop(d1,d2).perform();

        Thread.sleep(3000);

    }

    /**
     * 模拟鼠标操作示例
     * @throws Exception
     */
    @Test(groups = {"ActionTest"})
    public void MouseTest3() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("ActionsDemo/DemoDraggable4.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        WebElement draggable=webDriver.findElement(By.id("dragger"));
        WebElement droppable=webDriver.findElement(By.xpath("//*[@class='drop']"));


        dragAndDrop(webDriver,draggable,droppable);

        Thread.sleep(3000);

    }
    /**
     * 模拟滚动条操作示例
     * @throws Exception
     */
    @Test(groups = {"ActionTest"})
    public void ScrollwheelTest1() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("ActionsDemo/DemoScrollwheel.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        Thread.sleep(3000);
        // 定位到需要滚动到的元素
        WebElement br=webDriver.findElement(By.xpath("/html/body/div/br[60]"));
        // 初始化actions对象
        Actions action=new Actions(webDriver);
        // 滚动到指定元素位置
        action.scrollToElement(br).perform();
        Thread.sleep(3000);
    }
    /**
     * 模拟滚动条操作示例
     * @throws Exception
     */
    @Test(groups = {"ActionTest"},enabled=false)
    public void ScrollwheelTest2() throws Exception {
        /**
         * 首先登录
         */
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

        /**
         * 以下是操作点到点菜单和滚动条的代码
         */
        Actions action=new Actions(webDriver);
        //点击左侧基础信息菜单
        webDriver.findElement(By.xpath("//span[text()='基础信息']")).click();
        Thread.sleep(2000);
        // 定位到点到点子菜单元素
        WebElement placeElement=webDriver.findElement(By.xpath("//a[@data-moduleid='4149']"));
        //初始化js执行的对象
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        // 通过js滚动到子菜单位置
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", placeElement);
        //这个子菜单控件，不支持使用action滚动。执行了没有效果
//        action.scrollToElement(placeElement).perform();
        //点击基础信息下 点到点子菜单
        placeElement.click();

        //定位到iframe元素，和普通元素定位一样
        WebElement iframe_Place =webDriver.findElement(By.xpath("//iframe[@data-moduleid='4149']"));
        //切换到此iframe
        webDriver.switchTo().frame(iframe_Place);
        // 定位列表中最后一行数据的第28列
        WebElement lastElement=webDriver.findElement(By.xpath("//*[@id='20']/td[28]"));
        // 滚动到此元素位置。
        action.scrollToElement(lastElement).perform();
        Thread.sleep(3000);
    }
    /**
     * 模拟画笔操作示例
     * @throws Exception
     */
    @Test(groups = {"ActionTest"})
    public void PenTest() throws Exception {
        // 得到的是指定文件的绝对路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("ActionsDemo/DemoPen3.html");
        // 去掉路径中的第一个/。“/D:/javaspace/autodemo/target/classes/AlertTestDemo/DemoAlert.html”
        String filePath=url.getPath().replaceAll("(?<=\\s|^)/|/(?=\\s|$)","");
        // 打开网页（直接打开html文件）
        webDriver.get(filePath);
        Thread.sleep(3000);
        // 定位到需要滚动到的元素
        WebElement canvas=webDriver.findElement(By.id("canvas"));
        // 初始化actions对象
        Actions action=new Actions(webDriver);
        // 滚动到指定元素位置
        action  //setActivePointer 加不加都一样，具体效果不确定。
                .setActivePointer(PointerInput.Kind.PEN, "default pen")
                .moveToElement(canvas)
                .clickAndHold()
                .moveByOffset(20, 20)
                .release()
                .perform();

        Thread.sleep(3000);
    }
    /**
     * 调用js代码实现拖动元素，把A元素拖动到B元素位置
     * @param driver
     * @param draggable
     * @param droppable
     */
    public void dragAndDrop(WebDriver driver, WebElement draggable, WebElement droppable) {

        // 拖动前先点击并按住要拖拽的元素，避免在elementui，拖放前draggable属性才会变成true，目的是让draggable变成true，如果一开始就是true也可不加这句
        new Actions(driver).clickAndHold(draggable).perform();

        final String java_script = "var args = arguments,"
                + "callback = args[args.length - 1],"
                + "source = args[0],"
                + "target = args[1],"
                + "offsetX = (args.length > 2 && args[2]) || 0,"
                + "offsetY = (args.length > 3 && args[3]) || 0,"
                + "delay = (args.length > 4 && args[4]) || 1;"
                + "if (!source.draggable) throw new Error('Source element is not draggable.');"
                + "var doc = source.ownerDocument,"
                + "win = doc.defaultView,"
                + "rect1 = source.getBoundingClientRect(),"
                + "rect2 = target ? target.getBoundingClientRect() : rect1,"
                + "x = rect1.left + (rect1.width >> 1),"
                + "y = rect1.top + (rect1.height >> 1),"
                + "x2 = rect2.left + (rect2.width >> 1) + offsetX,"
                + "y2 = rect2.top + (rect2.height >> 1) + offsetY,"
                + "dataTransfer = Object.create(Object.prototype, {"
                + "  _items: { value: { } },"
                + "  effectAllowed: { value: 'all', writable: true },"
                + "  dropEffect: { value: 'move', writable: true },"
                + "  files: { get: function () { return undefined } },"
                + "  types: { get: function () { return Object.keys(this._items) } },"
                + "  setData: { value: function (format, data) { this._items[format] = data } },"
                + "  getData: { value: function (format) { return this._items[format] } },"
                + "  clearData: { value: function (format) { delete this._items[format] } },"
                + "  setDragImage: { value: function () { } }" + "});"
                + "target = doc.elementFromPoint(x2, y2);"
                + "if(!target) throw new Error('The target element is not interactable and need to be scrolled into the view.');"
                + "rect2 = target.getBoundingClientRect();"
                + "emit(source, 'dragstart', delay, function () {"
                + "var rect3 = target.getBoundingClientRect();"
                + "x = rect3.left + x2 - rect2.left;"
                + "y = rect3.top + y2 - rect2.top;"
                + "emit(target, 'dragenter', 1, function () {"
                + "  emit(target, 'dragover', delay, function () {"
                + "\ttarget = doc.elementFromPoint(x, y);"
                + "\temit(target, 'drop', 1, function () {"
                + "\t  emit(source, 'dragend', 1, callback);"
                + "});});});});"
                + "function emit(element, type, delay, callback) {"
//                + "var event = doc.createEvent('DragEvent');"
//                + "event.initMouseEvent(type, true, true, win, 0, 0, 0, x, y, false, false, false, false, 0, null);"
                + "var event = new MouseEvent(type, {bubbles: true,cancelable: true,view: win,detail: 0,screenX: 0,screenY: 0,clientX: x,clientY: y,ctrlKey: false,altKey: false,shiftKey: false,metaKey: false,button:0,relatedTarget: null});"
                + "Object.defineProperty(event, 'dataTransfer', { get: function () { return dataTransfer } });"
                + "element.dispatchEvent(event);" + "win.setTimeout(callback, delay);" + "}";

        // 默认拖拽到中心点位置，第3个参数是X坐标偏移量（左负右正），第4个参数为Y坐标偏移量（上负下正），
        // 第5个参数是延迟时间（单位为毫秒，表示当鼠标点下后，延迟指定时间后才开始激活拖拽动作，用来防止误点击）
        ((JavascriptExecutor) driver).executeScript(java_script, draggable, droppable, 0, 0, 500);
    }
}
