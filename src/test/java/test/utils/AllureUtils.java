package test.utils;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import test.driver.DriverManager;

import java.io.ByteArrayInputStream;

public class AllureUtils {

    public static void takeScreenshot(String name) {
        try {
            AndroidDriver driver = DriverManager.getDriver();
            if (driver == null) return;

            byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(
                name,
                "image/png",
                new ByteArrayInputStream(screenshot),
                ".png"
            );
        } catch (Exception e) {
            System.out.println("Screenshot thất bại: " + e.getMessage());
        }
    }

    public static void attachLog(String name, String content) {
        Allure.addAttachment(name, "text/plain", content);
    }
}
