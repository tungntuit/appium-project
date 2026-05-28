package test.pages;

import test.base.BasePage;
import test.locators.LoginLocator;

public class IntroPage extends BasePage {

    public void tapDangNhap() {
        click(LoginLocator.BTN_DANG_NHAP);
    }
}
