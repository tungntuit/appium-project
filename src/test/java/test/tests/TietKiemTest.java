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
import test.pages.TietKiemPage;
import test.testdata.TestDataProvider;
import test.testdata.model.TietKiemData;
import test.utils.AllureUtils;

@Feature("Tiết kiệm")
public class TietKiemTest extends BaseTest {

    @DataProvider(name = "tietKiemData")
    public Object[][] tietKiemData() {
        return TestDataProvider.readJson("tiet_kiem.json", TietKiemData.class);
    }

    @Test(dataProvider = "tietKiemData",
          groups = {AppConstants.REGRESSION, AppConstants.TIET_KIEM})
    @Story("Tất toán sổ tiết kiệm")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Tất toán sổ tiết kiệm với nhiều kỳ hạn khác nhau")
    public void testTatToanTietKiem(TietKiemData data) {

        GiaoDichPage giaoDichPage = new GiaoDichPage();
        TietKiemPage tietKiemPage = new TietKiemPage();
        CommonPage   commonPage   = new CommonPage();
        OTPPage      otpPage      = new OTPPage();

        step("Vào trang tiết kiệm", () -> {
            giaoDichPage.accessTietKiemPage();
        });

        step("Chọn nguồn tiền: " + data.source, () -> {
            tietKiemPage.tapNguonTien(data.source);
        });

        step("Nhập số tiền: " + data.amount, () -> {
            tietKiemPage.tapSoTienTietKiem();
            tietKiemPage.inputAmount(data.amount);
        });

        step("Chọn kỳ hạn: " + data.term, () -> {
            tietKiemPage.selectTerm(data.term);
        });

        step("Tất toán", () -> {
            tietKiemPage.tapButtonAction();
            commonPage.tapTextViewAndRetry(Keyword.ONLINE_DEPOSIT_SETTLE);
        });

        step("Xác thực OTP", () -> {
            commonPage.tapButtonTiepTuc();
            otpPage.inputXacThuc();
        });

        if (commonPage.isExisted(Keyword.CLOSE, 10)) {
            commonPage.tapTextView(Keyword.CLOSE);
        }

        AllureUtils.takeScreenshot("Kết quả tất toán");
        Assert.assertTrue(
            commonPage.isExisted(Keyword.TRANSACTION_SUCCESSFUL, 10),
            "Tất toán thất bại — source: " + data.source + ", term: " + data.term
        );
    }

    private void step(String stepName, Runnable action) {
        Allure.step(stepName, action::run);
    }
}
