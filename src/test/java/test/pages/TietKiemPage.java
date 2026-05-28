package test.pages;

import org.openqa.selenium.By;
import test.base.BasePage;

public class TietKiemPage extends BasePage {

    private static final By BTN_SO_TIEN  = By.id("com.sacombankpay:id/btn_so_tien");
    private static final By INPUT_AMOUNT = By.id("com.sacombankpay:id/edt_amount");
    private static final By BTN_ACTION   = By.id("com.sacombankpay:id/btn_action");

    public void tapNguonTien(String source) {
        scrollToText(source);
        click(By.xpath("//*[@text='" + source + "']"));
    }

    public void tapSoTienTietKiem() {
        click(BTN_SO_TIEN);
    }

    public void inputAmount(String amount) {
        sendKeys(INPUT_AMOUNT, amount);
    }

    public void selectTerm(String term) {
        scrollToText(term);
        click(By.xpath("//*[@text='" + term + "']"));
    }

    public void tapButtonAction() {
        click(BTN_ACTION);
    }
}
