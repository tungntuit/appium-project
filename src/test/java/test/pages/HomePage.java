package test.pages;

import test.base.BasePage;
import test.locale.Keyword;
import test.locators.HomeLocator;

public class HomePage extends BasePage {

    public boolean isLoaded() {
        return isExisted(HomeLocator.LBL_GREETING, 10);
    }

    public void tapIconAvatar() {
        click(HomeLocator.ICO_AVATAR);
    }

    public void tapTabTransaction() {
        click(HomeLocator.TAB_TRANSACTION);
    }

    public void tapTabDiscovery() {
        click(HomeLocator.TAB_DISCOVERY);
    }

    public void tapTransfer() {
        click(HomeLocator.BTN_TRANSFER);
    }

    public void tapPayment() {
        click(HomeLocator.BTN_PAYMENT);
    }

    public void tapNotification() {
        click(HomeLocator.ICO_NOTIFICATION);
    }

    public void tapMenuByName(Keyword keyword) {
        tapTextView(keyword);
    }
}
