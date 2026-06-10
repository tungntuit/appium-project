package test.locators;

import org.openqa.selenium.By;

public class CommonLocator {

    // ── Static text / label ───────────────────────────────────────────────────
    // Android native:    android.widget.TextView  → @text
    // Android RN:        android.view.View        → @content-desc  (Prudential app)
    // iOS:               XCUIElementTypeStaticText → @label
    // iOS button-label:  XCUIElementTypeButton    → @label
    public static By txtStaticText(String opt) {
        return By.xpath(
            "//*[@text='" + opt + "' or @content-desc='" + opt + "']" +
            " | //*[@type='XCUIElementTypeStaticText' and @label='" + opt + "']" +
            " | //*[@type='XCUIElementTypeButton'     and @label='" + opt + "']"
        );
    }

    // ── Button ────────────────────────────────────────────────────────────────
    public static By btnOption(String opt) {
        return By.xpath(
            "//*[@class='android.widget.Button' and (@text='" + opt + "' or @content-desc='" + opt + "')]" +
            " | //*[(@text='" + opt + "' or @content-desc='" + opt + "') and @clickable='true']" +
            " | //*[@type='XCUIElementTypeButton' and @label='" + opt + "']"
        );
    }

    // ── Text input ────────────────────────────────────────────────────────────
    public static By txtEditText(String opt) {
        return By.xpath(
            "(//*[@class='android.widget.EditText' and (@text='" + opt + "' or @hint='" + opt + "' or .='" + opt + "')])" +
            " | (//*[@type='XCUIElementTypeTextField' and @value='" + opt + "'])" +
            " | (//*[@type='XCUIElementTypeSecureTextField' and @value='" + opt + "'])"
        );
    }

    // ── Password input ────────────────────────────────────────────────────────
    public static By txtSecureEditText(String opt) {
        return By.xpath(
            "(//*[@class='android.widget.EditText' and @password='true' and (@hint='" + opt + "' or .='" + opt + "')])" +
            " | (//*[@type='XCUIElementTypeSecureTextField' and @value='" + opt + "'])"
        );
    }

    // ── Error message ─────────────────────────────────────────────────────────
    public static By txtMsgError(String opt) {
        return By.xpath(
            "//*[@class='android.widget.TextView' and (@text='" + opt + "' or @content-desc='" + opt + "')]" +
            " | //*[@type='XCUIElementTypeStaticText' and @label='" + opt + "']"
        );
    }

    // ── Success message ───────────────────────────────────────────────────────
    public static By txtMsgSuccess(String opt) {
        return txtMsgError(opt);
    }

    // ── View / container ──────────────────────────────────────────────────────
    public static By viewViewOption(String opt) {
        return By.xpath(
            "//*[@content-desc='" + opt + "' or @resource-id='" + opt + "']" +
            " | //*[@type='XCUIElementTypeOther' and @label='" + opt + "']"
        );
    }

    // ── Checkbox / toggle ─────────────────────────────────────────────────────
    public static By chkOption(String opt) {
        return By.xpath(
            "//*[@class='android.widget.CheckBox' and (@text='" + opt + "' or @content-desc='" + opt + "')]" +
            " | //*[@type='XCUIElementTypeSwitch' and @label='" + opt + "']"
        );
    }
}
