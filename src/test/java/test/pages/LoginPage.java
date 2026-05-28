package test.pages;

import test.base.BasePage;
import test.locators.LoginLocator;

public class LoginPage extends BasePage {

    public void inputSDT(String sdt) {
        sendKeys(LoginLocator.INPUT_PHONE, sdt);
    }

    public void tapTiepTheo() {
        click(LoginLocator.BTN_TIEP_THEO);
    }

    public String getErrorMessage() {
        return getText(LoginLocator.LBL_ERROR_PHONE);
    }

    public boolean hasError() {
        return isExisted(LoginLocator.LBL_ERROR_PHONE, 3);
    }
}
