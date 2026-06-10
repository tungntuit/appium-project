package test.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.*;
import test.config.ConfigReader;
import test.driver.DriverManager;
import test.listeners.AllureListener;
import test.locale.LocaleManager;
import test.pages.PruLoginPage;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners(AllureListener.class)
public class BaseTest {

    private static AppiumDriverLocalService service;

    // Override trong LoginTest để skip auto-login
    protected boolean requiresLogin() {
        return true;
    }

    @BeforeSuite
    public void globalSetup() {
        startAppiumServer();
    }

    private void defineEnv(Method method) {
        String pkg = method.getDeclaringClass().getPackage().getName();
        boolean isAndroid = !pkg.contains(".ios");
        boolean isEnglish = pkg.endsWith(".en");

        String env;
        if      (isAndroid && isEnglish)  env = "androidEng";
        else if (isAndroid && !isEnglish) env = "androidVn";
        else if (!isAndroid && isEnglish) env = "iOSEng";
        else                              env = "iOSVn";

        System.setProperty("env", env);
        LocaleManager.init(env);
        System.out.println("Environment: " + env + " (" + pkg + ")");
    }

    private void startAppiumServer() {
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();
        System.out.println("Appium server started at port 4723");
    }

    @BeforeMethod
    public void setUpDriver(Method method) {
        defineEnv(method);

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(ConfigReader.get("device.name"));
        options.setUdid(ConfigReader.get("device.udid"));
        options.setPlatformVersion(ConfigReader.get("platform.version"));
        options.setAppPackage(ConfigReader.get("app.package"));
        options.setAppActivity(ConfigReader.get("app.activity"));
        options.setNoReset(true);
        options.setAutoGrantPermissions(true);
        options.setAppWaitDuration(Duration.ofSeconds(15));

        try {
            AndroidDriver driver = new AndroidDriver(
                    new URL(ConfigReader.get("appium.server.url")), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            DriverManager.setDriver(driver);
            System.out.println("Driver ready: " + ConfigReader.get("app.package"));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Appium URL sai: " + e.getMessage());
        }

        if (requiresLogin()) {
            appSetup();
        }
    }

    private void appSetup() {
        PruLoginPage loginPage = new PruLoginPage();
        loginPage.ensureLanguage();
        loginPage.login(ConfigReader.get("sdt"), ConfigReader.get("mpass"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownDriver() {
        AndroidDriver driver = DriverManager.getDriver();
        if (driver != null) {
            try {
                driver.terminateApp(ConfigReader.get("app.package"));
            } catch (Exception ignored) {}
            driver.quit();
            DriverManager.removeDriver();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        if (service != null && service.isRunning()) {
            service.stop();
            System.out.println("Appium server stopped");
        }
    }

    protected void pressBack() {
        DriverManager.getDriver().pressKey(new KeyEvent(AndroidKey.BACK));
    }

    protected void hardWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
