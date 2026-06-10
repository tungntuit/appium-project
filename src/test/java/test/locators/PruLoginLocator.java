package test.locators;

import org.openqa.selenium.By;

public class PruLoginLocator {

    // content-desc thay đổi theo ngôn ngữ hiện tại
    public static final By BTN_LANGUAGE = By.xpath(
        "//*[@content-desc='Tiếng Việt' or @content-desc='English']"
    );

    // Picker options thay đổi theo ngôn ngữ app hiện tại
    // → dùng CommonLocator.btnOption(Keyword.LANG_PICKER_VN/EN) thay vì static locator

    // Button con bên trong View có resource-id ổn định
    public static final By BTN_LOGIN = By.xpath(
        "//android.view.View[@resource-id='ps_sign_in_button_text']//android.widget.Button"
    );

    public static final By BTN_REGISTER = By.xpath(
        "//android.view.View[@resource-id='ps_create_account_button_text']//*[@clickable='true']"
    );

    public static final By BTN_FORGOT_USERNAME = By.xpath(
        "//android.view.View[@resource-id='ps_forgot_username_hyperlink_text']//*[@clickable='true']"
    );

    public static final By BTN_FORGOT_PASSWORD = By.xpath(
        "//android.view.View[@resource-id='ps_forgot_password_hyperlink_text']//*[@clickable='true']"
    );

    // EditText resource-id rỗng → navigate qua View cha
    public static final By TXT_INPUT_USERNAME = By.xpath(
        "//android.view.View[@resource-id='ps_policy_holder_username']//android.widget.EditText"
    );

    public static final By TXT_INPUT_PASSWORD = By.xpath(
        "//android.view.View[@resource-id='ps_policy_holder_password']//android.widget.EditText"
    );
}
