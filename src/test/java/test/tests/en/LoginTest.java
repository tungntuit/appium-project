package test.tests.en;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.base.BaseTest;
import test.locale.Keyword;
import test.locale.LocaleManager;
import test.pages.PruLoginPage;
import test.utils.AllureUtils;
import test.utils.JsonReader;

import java.util.Map;

public class LoginTest extends BaseTest {

    @Override
    protected boolean requiresLogin() { return false; }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return JsonReader.toDataProvider("login/login.json");
    }

    @Test(description = "Login - English", dataProvider = "loginData")
    public void testLoginEn(Map<String, String> data) {
        PruLoginPage loginPage = new PruLoginPage();

        loginPage.ensureLanguage();

        Assert.assertTrue(
            loginPage.isLanguageButtonShowing(LocaleManager.get(Keyword.LANGUAGE)),
            "App is not in English"
        );
        AllureUtils.takeScreenshot("Login screen - " + LocaleManager.get(Keyword.LANGUAGE));

        loginPage.login(data.get("username"), data.get("password"));
        AllureUtils.takeScreenshot("After tapping " + LocaleManager.get(Keyword.LOG_IN));
    }
}
