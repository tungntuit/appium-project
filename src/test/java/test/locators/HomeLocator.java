package test.locators;

import org.openqa.selenium.By;

public class HomeLocator {

    public static final By LBL_GREETING = By.xpath(
        "//*[@type='XCUIElementTypeStaticText' and @label='Hello!' and @name='Hello!' and @value='Hello!']" +
        " | //*[@type='XCUIElementTypeStaticText' and @label='Good morning!' and @name='Good morning!' and @value='Good morning!']" +
        " | //*[@class='android.widget.TextView' and (@text='Hello!' or contains(@text,'Good morning'))]"
    );

    public static final By ICO_AVATAR       = By.id("com.sacombankpay:id/img_avatar");
    public static final By TAB_HOME         = By.id("com.sacombankpay:id/tab_home");
    public static final By TAB_TRANSACTION  = By.id("com.sacombankpay:id/tab_transaction");
    public static final By TAB_DISCOVERY    = By.id("com.sacombankpay:id/tab_discovery");

    public static final By BTN_TRANSFER     = By.id("com.sacombankpay:id/btn_transfer");
    public static final By BTN_PAYMENT      = By.id("com.sacombankpay:id/btn_payment");
    public static final By BTN_TOPUP        = By.id("com.sacombankpay:id/btn_topup");

    public static final By ICO_NOTIFICATION = By.id("com.sacombankpay:id/ico_notification");
}
