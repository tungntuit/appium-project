package test.pages;

import org.openqa.selenium.WebElement;
import test.base.BasePage;
import test.locale.Keyword;
import test.locators.TransactionLocator;

import java.util.List;

public class TransactionPage extends BasePage {

    public boolean isLoaded() {
        return isExisted(TransactionLocator.LBL_TITLE, 10);
    }

    public void tapFilter() {
        click(TransactionLocator.BTN_FILTER);
    }

    public void tapFirstTransaction() {
        click(TransactionLocator.ITEM_TRANSACTION);
    }

    public void tapShare() {
        click(TransactionLocator.BTN_SHARE);
    }

    public int getTransactionCount() {
        List<WebElement> items = driver.findElements(TransactionLocator.ITEM_TRANSACTION);
        return items.size();
    }

    public String getTransactionStatus() {
        return getText(TransactionLocator.LBL_STATUS);
    }

    public boolean isTransactionSuccessful() {
        return isExisted(Keyword.TRANSACTION_SUCCESSFUL, 10);
    }
}
