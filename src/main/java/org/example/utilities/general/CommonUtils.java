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
    public static void waitForVisibilityOfElement(WebDriver driver, WebElement element, int time) {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            /**
             * wait.ignoring 添加可能出现的忽略异常 ，默认已添加WebDriverException下的NotFoundException
             * 如果等待过程中可能出现其他类型的异常，又想要不影响等待，则可以手动添加WebDriverException的子类
             */
            wait.ignoring(InvalidElementStateException.class);
            wait.ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
