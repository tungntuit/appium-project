package test.pages;

import io.qameta.allure.Step;
import test.base.BasePage;
import test.locale.Keyword;
import test.locale.LocaleManager;
import test.locators.CommonLocator;
import test.locators.PruLoginLocator;

public class PruLoginPage extends BasePage {

    public PruLoginPage() {
        super();
    }

    @Step("Verify login screen is shown")
    public boolean isLoginScreenShown() {
        return isExisted(PruLoginLocator.BTN_LOGIN, 10);
    }

    @Step("Verify language button shows: {expectedText}")
    public boolean isLanguageButtonShowing(String expectedText) {
        return isExisted(CommonLocator.txtStaticText(expectedText), 5);
    }

    @Step("Tap language button")
    public void tapLanguageButton() {
        click(PruLoginLocator.BTN_LANGUAGE);
    }

    @Step("Ensure language is correct")
    public void ensureLanguage() {
        String expected = LocaleManager.get(Keyword.LANGUAGE);
        if (isLanguageButtonShowing(expected)) return;

        // App đang ở ngôn ngữ ngược → picker hiển thị text theo ngôn ngữ đó
        String pickerText = LocaleManager.isEnglish()
                ? Keyword.LANG_PICKER_EN.androidVn()   // target EN, app đang VN → "Tiếng Anh"
                : Keyword.LANG_PICKER_VN.androidEng();  // target VN, app đang EN → "Vietnamese"
        switchLanguage(pickerText);
    }

    @Step("Switch language to: {language}")
    public void switchLanguage(String language) {
        click(PruLoginLocator.BTN_LANGUAGE);
        click(CommonLocator.btnOption(language));
    }

    @Step("Input username: {username}")
    public void inputUsername(String username) {
        sendKeys(PruLoginLocator.TXT_INPUT_USERNAME, username);
    }

    @Step("Input password")
    public void inputPassword(String password) {
        sendKeys(PruLoginLocator.TXT_INPUT_PASSWORD, password);
    }

    @Step("Tap Login button")
    public void tapLoginButton() {
        click(PruLoginLocator.BTN_LOGIN);
    }

    @Step("Login with username: {username}")
    public void login(String username, String password) {
        inputUsername(username);
        inputPassword(password);
        tapLoginButton();
    }
}
