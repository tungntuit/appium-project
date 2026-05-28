package test.pages;

import test.base.BasePage;
import test.config.ConfigReader;
import test.locators.LoginLocator;

public class OTPPage extends BasePage {

    public void chooseOtpOption(String type) {
        if (type.equals("hardcode")) {
            String otp = ConfigReader.get("hardcode.otp");
            sendKeys(LoginLocator.INPUT_OTP, otp);
            click(LoginLocator.BTN_CONFIRM_OTP);
        }
    }

    public void inputXacThuc() {
        chooseOtpOption("hardcode");
    }
}
