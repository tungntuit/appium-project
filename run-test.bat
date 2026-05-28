@echo off
REM ── Appium Android Auto Test Runner ───────────────────────────────────────
REM Dùng với Windows Task Scheduler để chạy theo giờ cố định

cd /d C:\path\to\your\project

REM ── Chạy Smoke — nhanh, chạy buổi sáng ──────────────────────────────────
REM mvn clean test -Denv=androidVn -DsuiteFile=src/test/resources/suites/testng-smoke.xml

REM ── Chạy Regression — đầy đủ, chạy buổi tối ────────────────────────────
mvn clean test -Denv=androidVn -DsuiteFile=src/test/resources/suites/testng-regression.xml

REM ── Generate Allure report sau khi chạy xong ─────────────────────────────
allure generate allure-results --clean -o allure-report

echo "Done! Xem report tai allure-report/index.html"
