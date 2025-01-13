package com.hack23.cia.systemintegrationtest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserPageVisitHelper {
    private static final Logger LOG = LoggerFactory.getLogger(UserPageVisitHelper.class);
    private static final Duration DEFAULT_WAIT = Duration.ofMillis(120000);
    private static int screenShotNumber;

    private final WebDriver driver;
    private final String browserName;

    UserPageVisitHelper(WebDriver driver, String browserName) {
        this.driver = driver;
        this.browserName = browserName;
    }

    public void waitForElement(By locator) {
        new WebDriverWait(driver, DEFAULT_WAIT)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForPageLoad() {
        new WebDriverWait(driver, DEFAULT_WAIT)
                .until(webDriver -> "complete".equals(
                        ((JavascriptExecutor) webDriver).executeScript("return document.readyState")));
    }

    public void grabScreenshot() {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            screenShotNumber++;
            Path targetPath = Path.of("target", "screenshot_" + screenShotNumber + ".png");
            Files.copy(scrFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            LOG.error("Failed to save screenshot", e);
        }
    }

    public WebElement refreshElement(WebElement element) {
        return StaleElementUtils.refreshElement(element, driver);
    }

    public void handleInterruptedException(InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException("Operation interrupted", e);
    }

    public ExpectedCondition<Boolean> elementIsStale(WebElement element) {
        return ExpectedConditions.stalenessOf(element);
    }

    public String getBrowserName() {
        return browserName;
    }
}
