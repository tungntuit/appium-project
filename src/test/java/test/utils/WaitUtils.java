package test.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.driver.DriverManager;

import java.time.Duration;

public class WaitUtils {

    public static void hardWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void waitForVisible(By locator, int timeoutSec) {
        AndroidDriver driver = DriverManager.getDriver();
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSec))
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForClickable(By locator, int timeoutSec) {
        AndroidDriver driver = DriverManager.getDriver();
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSec))
            .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForInvisible(By locator, int timeoutSec) {
        AndroidDriver driver = DriverManager.getDriver();
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSec))
            .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
