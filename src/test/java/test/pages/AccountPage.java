package test.pages;

import org.openqa.selenium.By;
import test.base.BasePage;
import test.locators.AccountLocator;

public class AccountPage extends BasePage {

    public void tapIconQLTVTK() {
        click(AccountLocator.ICO_QLTVTK);
    }

    public By getAvailableBalanceVN() {
        return AccountLocator.LBL_AVAILABLE_BALANCE_VN;
    }

    public By getAvailableBalanceEN() {
        return AccountLocator.LBL_AVAILABLE_BALANCE_EN;
    }
}
