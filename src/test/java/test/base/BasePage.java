package test.base;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.config.ConfigReader;
import test.driver.DriverManager;
import test.locale.Keyword;
import test.locale.LocaleManager;

import java.time.Duration;
import java.util.List;

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
        el.click();
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
        } catch (Exception ignored) {
            try {
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().description(\"" + text + "\"))"
                ));
            } catch (Exception e) {
                // Element có thể đã visible, không cần scroll
            }
        }
    }

    protected By byText(Keyword keyword) {
        String text = LocaleManager.get(keyword);
        return byDynamicText(text);
    }

    protected By byTextBilingual(Keyword keyword) {
        String vn = keyword.androidVn();
        String en = keyword.androidEng();
        return By.xpath(
            "//*[@text='" + vn + "' or @text='" + en + "'" +
            " or @content-desc='" + vn + "' or @content-desc='" + en + "']"
        );
    }

    // ── Dynamic locators — truyền text vào, không cần hardcode locator ──────────

    protected By byDynamicText(String text) {
        return By.xpath("//*[@text='" + text + "' or @content-desc='" + text + "']");
    }

    protected By byDynamicButton(String label) {
        return By.xpath(
            "//*[(@text='" + label + "' or @content-desc='" + label + "')" +
            " and (@clickable='true' or @class='android.widget.Button')]"
        );
    }

    protected By byDynamicInput(String hint) {
        return By.xpath(
            "//*[@class='android.widget.EditText'" +
            " and (@text='" + hint + "' or @hint='" + hint + "')]"
        );
    }

    protected void tapByText(String text) {
        scrollToText(text);
        click(byDynamicText(text));
    }

    protected void tapByKeyword(Keyword keyword) {
        tapByText(LocaleManager.get(keyword));
    }

    // ── Swipe / Scroll gestures ──────────────────────────────────────────────

    protected void swipe(int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(800), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(sequence));
    }

    protected void swipeUp() {
        int width  = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        swipe(width / 2, (int)(height * 0.7), width / 2, (int)(height * 0.3));
    }

    protected void swipeDown() {
        int width  = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        swipe(width / 2, (int)(height * 0.3), width / 2, (int)(height * 0.7));
    }

    protected void swipeLeft() {
        int width  = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        swipe((int)(width * 0.8), height / 2, (int)(width * 0.2), height / 2);
    }

    protected void swipeRight() {
        int width  = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        swipe((int)(width * 0.2), height / 2, (int)(width * 0.8), height / 2);
    }
}
