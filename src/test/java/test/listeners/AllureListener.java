package test.listeners;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Label;
import io.qameta.allure.model.StatusDetails;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import test.driver.DriverManager;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AllureListener implements ITestListener {

    private final AllureLifecycle lifecycle = Allure.getLifecycle();
    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public void onStart(ITestContext context) {
        System.out.println("═══════════════════════════════════");
        System.out.println("Suite bắt đầu : " + context.getName());
        System.out.println("Thời gian     : " + LocalDateTime.now().format(FORMATTER));
        System.out.println("═══════════════════════════════════");
    }

    @Override
    public void onTestStart(ITestResult result) {
        result.setAttribute("startTime", System.currentTimeMillis());
        System.out.println("\n▶ START: " + result.getName());
        System.out.println("  Thời gian bắt đầu: " + LocalDateTime.now().format(FORMATTER));

        lifecycle.updateTestCase(testResult -> {
            testResult.getLabels().add(new Label()
                .withName("env")
                .withValue(System.getProperty("env", "unknown")));
            testResult.getLabels().add(new Label()
                .withName("device")
                .withValue(System.getProperty("device.name", "emulator")));
        });
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        long elapsed = calcElapsed(result);
        System.out.println("✅ PASSED: " + result.getName());
        System.out.println("   Thời gian kết thúc : " + LocalDateTime.now().format(FORMATTER));
        System.out.println("   Thời gian thực hiện: " + formatElapsed(elapsed));

        Allure.addAttachment("Test Info", "text/plain", buildTestInfo(result, elapsed, "PASSED"));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        long elapsed = calcElapsed(result);
        System.out.println("❌ FAILED: " + result.getName());
        System.out.println("   Thời gian kết thúc : " + LocalDateTime.now().format(FORMATTER));
        System.out.println("   Thời gian thực hiện: " + formatElapsed(elapsed));
        System.out.println("   Lỗi: " + result.getThrowable().getMessage());

        attachScreenshot(result.getName());

        Allure.addAttachment("Error Log", "text/plain", result.getThrowable().getMessage());
        Allure.addAttachment("Test Info", "text/plain", buildTestInfo(result, elapsed, "FAILED"));

        lifecycle.updateTestCase(testResult ->
            testResult.setStatusDetails(new StatusDetails()
                .withMessage(result.getThrowable().getMessage())
                .withTrace(getStackTrace(result.getThrowable()))
            )
        );
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⏭ SKIPPED: " + result.getName());
        Allure.addAttachment(
            "Skip Reason", "text/plain",
            result.getThrowable() != null ? result.getThrowable().getMessage() : "Không rõ lý do skip"
        );
    }

    @Override
    public void onFinish(ITestContext context) {
        int passed  = context.getPassedTests().size();
        int failed  = context.getFailedTests().size();
        int skipped = context.getSkippedTests().size();
        long suiteElapsed = System.currentTimeMillis() - context.getStartDate().getTime();

        System.out.println("\n═══════════════════════════════════");
        System.out.println("Suite kết thúc: " + context.getName());
        System.out.println("Tổng thời gian: " + formatElapsed(suiteElapsed));
        System.out.println("Total  : " + (passed + failed + skipped));
        System.out.println("Passed : " + passed  + " ✅");
        System.out.println("Failed : " + failed  + " ❌");
        System.out.println("Skipped: " + skipped + " ⏭");
        System.out.println("═══════════════════════════════════\n");

        Allure.addAttachment("Suite Summary", "text/plain", buildSuiteSummary(context, suiteElapsed));
    }

    private void attachScreenshot(String testName) {
        try {
            AndroidDriver driver = DriverManager.getDriver();
            if (driver == null) return;

            byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(
                "Screenshot - " + testName,
                "image/png",
                new ByteArrayInputStream(screenshot),
                ".png"
            );
        } catch (Exception e) {
            System.out.println("Chụp screenshot thất bại: " + e.getMessage());
        }
    }

    private long calcElapsed(ITestResult result) {
        Long startTime = (Long) result.getAttribute("startTime");
        return startTime == null ? 0 : System.currentTimeMillis() - startTime;
    }

    private String formatElapsed(long ms) {
        long minutes = ms / 60000;
        long seconds = (ms % 60000) / 1000;
        long millis  = ms % 1000;
        if (minutes > 0) return minutes + "m " + seconds + "s " + millis + "ms";
        if (seconds > 0) return seconds + "s " + millis + "ms";
        return millis + "ms";
    }

    private String buildTestInfo(ITestResult result, long elapsed, String status) {
        Long startTime = (Long) result.getAttribute("startTime");
        long start = startTime != null ? startTime : 0;
        return "Test Name  : " + result.getName()                     + "\n" +
               "Status     : " + status                               + "\n" +
               "Start Time : " + formatTimestamp(start)               + "\n" +
               "End Time   : " + formatTimestamp(start + elapsed)     + "\n" +
               "Elapsed    : " + formatElapsed(elapsed)               + "\n" +
               "Env        : " + System.getProperty("env", "unknown") + "\n" +
               "Device     : " + System.getProperty("device.name", "emulator");
    }

    private String buildSuiteSummary(ITestContext context, long elapsed) {
        int passed  = context.getPassedTests().size();
        int failed  = context.getFailedTests().size();
        int skipped = context.getSkippedTests().size();
        return "Suite      : " + context.getName()                                 + "\n" +
               "Start Time : " + formatTimestamp(context.getStartDate().getTime()) + "\n" +
               "End Time   : " + formatTimestamp(context.getEndDate().getTime())   + "\n" +
               "Elapsed    : " + formatElapsed(elapsed)                            + "\n" +
               "─────────────────────────────────"                                 + "\n" +
               "Total      : " + (passed + failed + skipped)                       + "\n" +
               "Passed     : " + passed                                            + "\n" +
               "Failed     : " + failed                                            + "\n" +
               "Skipped    : " + skipped                                           + "\n" +
               "Env        : " + System.getProperty("env", "unknown");
    }

    private String formatTimestamp(long timestamp) {
        if (timestamp == 0) return "N/A";
        return LocalDateTime.ofEpochSecond(
            timestamp / 1000, 0,
            java.time.ZoneOffset.ofHours(7)
        ).format(FORMATTER);
    }

    private String getStackTrace(Throwable t) {
        if (t == null) return "";
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : t.getStackTrace()) {
            sb.append("\tat ").append(element).append("\n");
            if (sb.toString().split("\n").length > 10) break;
        }
        return sb.toString();
    }
}
