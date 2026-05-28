package test.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.ITestResult;
import org.testng.annotations.*;
import test.config.ConfigReader;
import test.driver.DriverManager;
import test.listeners.AllureListener;
import test.locale.LocaleManager;
import test.pages.CommonPage;
import test.pages.HomePage;
import test.pages.IntroPage;
import test.pages.LoginPage;
import test.pages.MPassPage;
import test.pages.OTPPage;
import test.pages.AccountPage;
import test.locale.Keyword;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners(AllureListener.class)
public class BaseTest {

    private static AppiumDriverLocalService service;

    @BeforeSuite
    public void globalSetup() {
        defineEnv();
        startAppiumServer();
    }

    private void defineEnv() {
        String env = System.getProperty("env");
        if (env == null) {
            env = ConfigReader.get("env");
        }
        System.setProperty("env", env);
        LocaleManager.init(env);
        System.out.println("Env: " + env);
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
    public void setUpDriver() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(ConfigReader.get("device.name"));
        options.setPlatformVersion(ConfigReader.get("platform.version"));
        options.setAppPackage(ConfigReader.get("app.package"));
        options.setAppActivity(ConfigReader.get("app.activity"));
        options.setNoReset(true);
        options.setAutoGrantPermissions(true);

        try {
            AndroidDriver driver = new AndroidDriver(
                    new URL(ConfigReader.get("appium.server.url")), options);
            DriverManager.setDriver(driver);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Appium URL sai: " + e.getMessage());
        }

        setupAndroid();
    }

    private void setupAndroid() {
        CommonPage  commonPage  = new CommonPage();
        IntroPage   introPage   = new IntroPage();
        LoginPage   loginPage   = new LoginPage();
        MPassPage   mpassPage   = new MPassPage();
        OTPPage     otpPage     = new OTPPage();
        HomePage    homePage    = new HomePage();
        AccountPage accountPage = new AccountPage();

        if (commonPage.isExisted(Keyword.LANGUAGE, 5)) {
            commonPage.tapTextView(Keyword.LANGUAGE);
            commonPage.tapTextView(Keyword.CONTINUE);
        }

        commonPage.tapIfExisted(Keyword.LOG_IN, 2);

        introPage.tapDangNhap();
        loginPage.inputSDT(ConfigReader.get("sdt"));
        loginPage.tapTiepTheo();
        mpassPage.inputMPass(
                ConfigReader.get("mpass"),
                ConfigReader.get("identify.id"),
                ConfigReader.get("dob")
        );
        mpassPage.tapXacThucSMSOTP();

        if (commonPage.isExisted(Keyword.ENTER_OTP, 10)) {
            otpPage.chooseOtpOption("hardcode");
        }

        if (commonPage.isExisted(Keyword.DISMISS, 3)) {
            commonPage.tapTextView(Keyword.DISMISS);
        }
        if (commonPage.isExisted(Keyword.CLOSE, 3)) {
            commonPage.tapTextView(Keyword.CLOSE);
        }

        handleLinkAccountPopup(commonPage);

        homePage.tapIconAvatar();
        handleAccountManagement(commonPage, homePage, accountPage);

        System.out.println("setupAndroid() hoàn thành — env: " + System.getProperty("env"));
    }

    private void handleLinkAccountPopup(CommonPage commonPage) {
        boolean hasLink  = commonPage.isExisted(Keyword.LINK_CURRENT_ACCOUNT,   5);
        boolean hasAdd   = commonPage.isExisted(Keyword.ADD_CURRENT_ACCOUNT,    5);
        boolean hasLink1 = commonPage.isExisted(Keyword.LINK_CURRENT_ACCOUNT_1, 2);

        if (hasLink || hasAdd || hasLink1) {
            if (commonPage.isExisted(Keyword.LINK_CURRENT_ACCOUNT,   3)) commonPage.tapTextView(Keyword.LINK_CURRENT_ACCOUNT);
            if (commonPage.isExisted(Keyword.ADD_CURRENT_ACCOUNT,    3)) commonPage.tapTextView(Keyword.ADD_CURRENT_ACCOUNT);
            if (commonPage.isExisted(Keyword.LINK_CURRENT_ACCOUNT_1, 3)) commonPage.tapTextView(Keyword.LINK_CURRENT_ACCOUNT_1);
        }

        pressBack();
        hardWait(3);
        pressBack();
    }

    private void handleAccountManagement(CommonPage commonPage, HomePage homePage, AccountPage accountPage) {
        boolean hasLink = commonPage.isExisted(Keyword.LINK_CURRENT_ACCOUNT, 5);
        boolean hasAdd  = commonPage.isExisted(Keyword.ADD_CURRENT_ACCOUNT,  5);

        if (hasLink || hasAdd) {
            themTheVaTaiKhoan(commonPage, accountPage);
            pressBack();
            returnToHome(commonPage);
        } else {
            homePage.tapIconAvatar();
            accountPage.tapIconQLTVTK();

            String env = System.getProperty("env");
            boolean isEnglish = env.contains("Eng");
            var elements = DriverManager.getDriver().findElements(
                    isEnglish ? accountPage.getAvailableBalanceEN() : accountPage.getAvailableBalanceVN()
            );

            if (elements.size() == 1) {
                themTheVaTaiKhoan(commonPage, accountPage);
                pressBack();
            }

            pressBack();
            returnToHome(commonPage);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void afterEachTest(ITestResult result) {
        try {
            backToHome();
        } catch (Exception e) {
            System.out.println("backToHome() exception: " + e.getMessage());
        }

        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("FAILED: " + result.getName());
        }

        AndroidDriver driver = DriverManager.getDriver();
        if (driver != null) {
            driver.quit();
            DriverManager.removeDriver();
        }
    }

    private void backToHome() {
        CommonPage commonPage = new CommonPage();
        MPassPage  mpassPage  = new MPassPage();
        int time = 0;

        while (true) {
            try {
                pressBack();

                if (commonPage.isExisted(Keyword.BIOMETRIC_LOGIN, 3)) {
                    commonPage.tapTextView(Keyword.REGISTER_LATER);
                }

                if (commonPage.isExisted(Keyword.LOANS,     3) ||
                    commonPage.isExisted(Keyword.DISCOVERY, 3)) {
                    System.out.println("Đã về Home sau " + time + " lần");
                    break;
                }

                if      (commonPage.isExisted(Keyword.BACK_TO_TRANSACTION,      2)) commonPage.tapTextView(Keyword.BACK_TO_TRANSACTION);
                else if (commonPage.isExisted(Keyword.BACK_HOME,                2)) commonPage.tapTextView(Keyword.BACK_HOME);
                else if (commonPage.isExisted(Keyword.BACK_TO_HOME,             2)) commonPage.tapTextView(Keyword.BACK_TO_HOME);
                else if (commonPage.isExisted(Keyword.BACK_TO_HOME1,            2)) commonPage.tapTextView(Keyword.BACK_TO_HOME1);
                else if (commonPage.isExisted(Keyword.BACK_TO_HOME2,            2)) commonPage.tapTextView(Keyword.BACK_TO_HOME2);
                else if (commonPage.isExisted(Keyword.BACK_TO_TRANSACTION_PAGE, 2)) commonPage.tapTextView(Keyword.BACK_TO_TRANSACTION_PAGE);
                else if (commonPage.isExisted(Keyword.CANCEL_TRANSACTION, 2)) {
                    commonPage.tapTextView(Keyword.CONTINUE);
                }
                else if (commonPage.isExisted(Keyword.CLOSE, 1)) {
                    commonPage.tapTextView(Keyword.CLOSE);
                }
                else if (commonPage.isExisted(Keyword.CHANGE_ACCOUNT, 2) ||
                         commonPage.isExisted(Keyword.TXT_PASSWORD,   2)) {
                    mpassPage.inputMPass(
                            ConfigReader.get("mpass"),
                            ConfigReader.get("identify.id"),
                            ConfigReader.get("dob")
                    );
                }

                time++;
                System.out.println("backToHome attempt: " + time);

                if (time >= 5) {
                    System.out.println("Timeout backToHome — restart app");
                    restartApp();
                    break;
                }

            } catch (Exception e) {
                System.out.println("backToHome exception: " + e.getMessage());
                restartApp();
                break;
            }
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

    private void restartApp() {
        try {
            AndroidDriver driver = DriverManager.getDriver();
            String pkg = ConfigReader.get("app.package");
            driver.terminateApp(pkg);
            driver.activateApp(pkg);
            System.out.println("App restarted: " + pkg);
        } catch (Exception e) {
            System.out.println("restartApp exception: " + e.getMessage());
        }
    }

    private void returnToHome(CommonPage commonPage) {
        pressBack();
        hardWait(3);
        pressBack();
    }

    private void themTheVaTaiKhoan(CommonPage commonPage, AccountPage accountPage) {
        // TODO: implement thêm thẻ và tài khoản
    }
}
