package test.driver;

import io.appium.java_client.android.AndroidDriver;

public class DriverManager {

    private static final ThreadLocal<AndroidDriver> driverThread = new ThreadLocal<>();

    public static AndroidDriver getDriver() {
        return driverThread.get();
    }

    public static void setDriver(AndroidDriver driver) {
        driverThread.set(driver);
    }

    public static void removeDriver() {
        driverThread.remove();
    }
}
