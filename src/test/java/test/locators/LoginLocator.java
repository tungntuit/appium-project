package test.locators;

import org.openqa.selenium.By;

public class LoginLocator {

    public static final By BTN_DANG_NHAP   = By.id("com.sacombankpay:id/btn_login");

    public static final By INPUT_PHONE     = By.id("com.sacombankpay:id/edt_phone");
    public static final By BTN_TIEP_THEO   = By.id("com.sacombankpay:id/btn_next");
    public static final By LBL_ERROR_PHONE = By.id("com.sacombankpay:id/txt_error_phone");

    public static final By INPUT_MPASS     = By.id("com.sacombankpay:id/edt_mpass");
    public static final By INPUT_ID        = By.id("com.sacombankpay:id/edt_identify_id");
    public static final By INPUT_DOB       = By.id("com.sacombankpay:id/edt_dob");
    public static final By BTN_XAC_THUC    = By.id("com.sacombankpay:id/btn_verify_sms");

    public static final By INPUT_OTP       = By.id("com.sacombankpay:id/edt_otp");
    public static final By BTN_CONFIRM_OTP = By.id("com.sacombankpay:id/btn_confirm_otp");
}
