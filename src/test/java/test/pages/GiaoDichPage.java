package test.pages;

import test.base.BasePage;
import test.locale.Keyword;

public class GiaoDichPage extends BasePage {

    public void accessTransferPage() {
        tapTextView(Keyword.DOMESTIC_TRANSFER);
    }

    public void accessTietKiemPage() {
        tapTextView(Keyword.SAVINGS);
    }
}
