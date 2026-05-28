package test.base;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.config.ConfigReader;
import test.driver.DriverManager;
import test.locale.Keyword;
import test.locale.LocaleManager;

import java.time.Duration;

public class BasePage {

    protected AndroidDriver driver;
    protected WebDriverWait wait;
    private static final int WAIT = ConfigReader.getInt("wait.in.second");

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void sendKeys(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(text);
    }

    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    protected boolean isExisted(By locator, int timeoutSec) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSec))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isExisted(Keyword keyword, int timeoutSec) {
        return isExisted(byText(keyword), timeoutSec);
    }

    protected void tapTextView(Keyword keyword) {
        String text = LocaleManager.get(keyword);
        scrollToText(text);
        click(By.xpath("//*[@text='" + text + "']"));
    }

    protected void tapTextViewAndRetry(Keyword keyword) {
        String text  = LocaleManager.get(keyword);
        By locator   = By.xpath("//*[@text='" + text + "']");
        int maxRetry = 3;

        for (int i = 0; i < maxRetry; i++) {
            if (!isExisted(locator, 2)) break;
            click(locator);
            if (!isExisted(locator, 2)) break;
        }
    }

    protected void tapIfExisted(Keyword keyword, int timeoutSec) {
        if (isExisted(keyword, timeoutSec)) {
            tapTextView(keyword);
        }
    }

    protected void tapIfExisted(By locator, int timeoutSec) {
        if (isExisted(locator, timeoutSec)) {
            click(locator);
        }
    }

    protected void scrollToText(String text) {
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                    ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"
            ));
        } catch (Exception e) {
            // Element có thể đã visible, không cần scroll
        }
    }

    protected By byText(Keyword keyword) {
        String text = LocaleManager.get(keyword);
        return By.xpath("//*[@text='" + text + "']");
    }

    protected By byTextBilingual(Keyword keyword) {
        String vn = keyword.androidVn();
        String en = keyword.androidEng();
        return By.xpath("//*[@text='" + vn + "' or @text='" + en + "']");
    }
}
