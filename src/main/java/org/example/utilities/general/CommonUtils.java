package org.example.utilities.general;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * @author tangzhou
 * @version 1.0
 * @date 2023/6/28 14:52
 */
public class CommonUtils {
    static WebDriverWait wait;

    /**
     * 判断元素知否存在，但存在并不代表元素在前台是可见的
     * 此方法支持设置等待元素的时间，他会依据传入的时间，等待指定时间看元素是否存在
     * @param driver 传入webdriver对象
     * @param by by定位对象，如 By.xpath("//div[@id='scrolldiv']")
     * @param time 等待的时间，单位秒。
     * @return  元素存在时返回true 否则返回false
     */
    public static boolean isElementExist(WebDriver driver,  By by, int time) {
        boolean exist = true;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            /**
             * wait.ignoring 添加可能出现的忽略异常 ，默认已添加WebDriverException下的NotFoundException
             * 如果等待过程中可能出现其他类型的异常，又想要不影响等待，则可以手动添加WebDriverException的子类
             */
            wait.ignoring(InvalidElementStateException.class);
            wait.ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            exist=false;
            e.printStackTrace();
        }
        return exist;
    }

    /**
     * 判断元素是否存，但存在并不代表元素在前台是可见的
     * @param webDriver  传入webdriver对象
     * @param by   by定位对象，如 By.xpath("//div[@id='scrolldiv']")
     * @return  元素存在时返回true 否则返回false
     */
    public static boolean isElementExist(WebDriver webDriver, By by) {
        boolean exist = false;
        try {
            webDriver.findElement(by);
            exist = true;
        } catch (NoSuchElementException e) {
        }
        return exist;
    }
    /**
     * 调用js代码实现拖动元素，把A元素拖动到B元素位置
     * @param driver
     * @param draggable
     * @param droppable
     */
    public static void dragAndDrop(WebDriver driver, WebElement draggable, WebElement droppable) {

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
