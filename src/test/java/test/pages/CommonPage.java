package test.pages;

import org.openqa.selenium.By;
import test.base.BasePage;
import test.locale.Keyword;

public class CommonPage extends BasePage {

    public CommonPage() {
        super();
    }

    public void tapBtnCloseShare() {
        tapIfExisted(By.xpath("//*[@resource-id='btn_close_share']"), 3);
    }

    public void tapButtonTiepTuc() {
        click(By.id("com.sacombankpay:id/btn_continue"));
    }

    public boolean isExisted(Keyword keyword, int timeoutSec) {
        return super.isExisted(keyword, timeoutSec);
    }

    public boolean isExisted(By locator, int timeoutSec) {
        return super.isExisted(locator, timeoutSec);
    }

    public void tapTextView(Keyword keyword) {
        super.tapTextView(keyword);
    }

    public void tapIfExisted(Keyword keyword, int timeoutSec) {
        super.tapIfExisted(keyword, timeoutSec);
    }

    public void tapTextViewAndRetry(Keyword keyword) {
        super.tapTextViewAndRetry(keyword);
    }
}
