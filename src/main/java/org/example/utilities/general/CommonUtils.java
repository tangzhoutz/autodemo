package org.example.utilities.general;

import org.openqa.selenium.*;
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
}
