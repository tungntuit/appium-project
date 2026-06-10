package test.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import test.base.BasePage;

public class LanguagePage extends BasePage {

    private static final By BTN_ENGLISH    = By.xpath("//*[@text='English']");
    private static final By BTN_VIETNAMESE = By.xpath("//*[@text='Tiếng Việt']");
    private static final By BTN_CONTINUE   = By.xpath("//*[@text='Continue' or @text='Tiếp tục']");
    private static final By TXT_LOGIN_EN   = By.xpath("//*[@text='Log in']");
    private static final By TXT_LOGIN_VN   = By.xpath("//*[@text='Đăng nhập']");

    public LanguagePage() {
        super();
    }

    @Step("Verify language selection screen is shown")
    public boolean isLanguageScreenShown() {
        return isExisted(BTN_ENGLISH, 10);
    }

    @Step("Select English language")
    public void selectEnglish() {
        click(BTN_ENGLISH);
        click(BTN_CONTINUE);
    }

    @Step("Select Vietnamese language")
    public void selectVietnamese() {
        click(BTN_VIETNAMESE);
        click(BTN_CONTINUE);
    }

    @Step("Verify app is displayed in English")
    public boolean isEnglishUI() {
        return isExisted(TXT_LOGIN_EN, 10);
    }

    @Step("Verify app is displayed in Vietnamese")
    public boolean isVietnameseUI() {
        return isExisted(TXT_LOGIN_VN, 10);
    }
}
