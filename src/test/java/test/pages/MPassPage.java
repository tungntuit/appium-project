package test.pages;

import test.base.BasePage;
import test.locators.LoginLocator;

public class MPassPage extends BasePage {

    public void inputMPass(String mpass, String identifyId, String dob) {
        sendKeys(LoginLocator.INPUT_MPASS, mpass);
        sendKeys(LoginLocator.INPUT_ID,    identifyId);
        sendKeys(LoginLocator.INPUT_DOB,   dob);
    }

    public void tapXacThucSMSOTP() {
        click(LoginLocator.BTN_XAC_THUC);
    }
}
