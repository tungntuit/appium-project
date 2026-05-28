package test.tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.base.BaseTest;
import test.constants.AppConstants;
import test.locale.Keyword;
import test.pages.CommonPage;
import test.pages.GiaoDichPage;
import test.pages.OTPPage;
import test.pages.TransferMoneyPage;
import test.testdata.TestDataProvider;
import test.testdata.model.TransferMoneyData;
import test.utils.AllureUtils;

@Feature("Chuyển tiền")
public class TransferMoneyTest extends BaseTest {

    @DataProvider(name = "transferData")
    public Object[][] transferData() {
        return TestDataProvider.readJson("transfer_money.json", TransferMoneyData.class);
    }

    @Test(dataProvider = "transferData",
          groups = {AppConstants.SMOKE, AppConstants.TRANSFER})
    @Story("Chuyển tiền thành công")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Chuyển tiền với nhiều nguồn tiền khác nhau")
    public void testTransferMoneySuccess(TransferMoneyData data) {

        GiaoDichPage      giaoDichPage = new GiaoDichPage();
        TransferMoneyPage transferPage = new TransferMoneyPage();
        CommonPage        commonPage   = new CommonPage();
        OTPPage           otpPage      = new OTPPage();

        step("Vào trang chuyển tiền", () -> {
            giaoDichPage.accessTransferPage();
        });

        step("Chọn nguồn tiền: " + data.source, () -> {
            transferPage.tapNguonTien(data.source);
        });

        step("Nhập thông tin chuyển tiền", () -> {
            transferPage.inputReceiver(data.receiver);
            transferPage.inputCashInMoney(data.amount);
            transferPage.inputDescription(data.description);
            AllureUtils.takeScreenshot("Trước khi xác nhận");
        });

        step("Xác nhận giao dịch", () -> {
            commonPage.tapTextView(Keyword.CONFIRM);
        });

        step("Nhập OTP xác thực", () -> {
            otpPage.inputXacThuc();
        });

        if (commonPage.isExisted(Keyword.CLOSE, 5)) {
            commonPage.tapTextView(Keyword.CLOSE);
        }

        AllureUtils.takeScreenshot("Kết quả giao dịch");
        Assert.assertTrue(
            commonPage.isExisted(Keyword.TRANSFER_MONEY_SUCCESS, 10),
            "Chuyển tiền thất bại — source: " + data.source
        );
    }

    @Test(groups = {AppConstants.REGRESSION, AppConstants.TRANSFER})
    @Story("Chuyển tiền thất bại")
    @Severity(SeverityLevel.NORMAL)
    @Description("Chuyển tiền sai số tài khoản")
    public void testTransferWrongAccount() {

        TransferMoneyPage transferPage = new TransferMoneyPage();
        CommonPage        commonPage   = new CommonPage();

        step("Nhập số tài khoản sai", () -> {
            transferPage.inputReceiver("0000000000");
        });

        step("Tap tiếp theo", () -> {
            commonPage.tapTextView(Keyword.NEXT);
        });

        AllureUtils.takeScreenshot("Màn hình lỗi");
        Assert.assertTrue(
            transferPage.hasError(),
            "Không hiển thị lỗi khi nhập sai tài khoản"
        );
    }

    private void step(String stepName, Runnable action) {
        Allure.step(stepName, action::run);
    }
}
