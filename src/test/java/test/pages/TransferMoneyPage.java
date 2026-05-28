package test.pages;

import org.openqa.selenium.By;
import test.base.BasePage;

public class TransferMoneyPage extends BasePage {

    private static final By LBL_ERROR     = By.id("com.sacombankpay:id/txt_error_transfer");
    private static final By INPUT_RECEIVER = By.id("com.sacombankpay:id/edt_receiver");
    private static final By INPUT_AMOUNT   = By.id("com.sacombankpay:id/edt_amount");
    private static final By INPUT_DESC     = By.id("com.sacombankpay:id/edt_description");

    public void tapNguonTien(String source) {
        scrollToText(source);
        click(By.xpath("//*[@text='" + source + "']"));
    }

    public void inputReceiver(String receiver) {
        sendKeys(INPUT_RECEIVER, receiver);
    }

    public void inputCashInMoney(String amount) {
        sendKeys(INPUT_AMOUNT, amount);
    }

    public void inputDescription(String description) {
        sendKeys(INPUT_DESC, description);
    }

    public boolean hasError() {
        return isExisted(LBL_ERROR, 3);
    }
}
