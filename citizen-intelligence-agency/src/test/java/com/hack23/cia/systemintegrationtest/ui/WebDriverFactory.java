package com.hack23.cia.systemintegrationtest.ui;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverFactory {
    private static final Logger LOG = LoggerFactory.getLogger(WebDriverFactory.class);
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(90);

    private WebDriverFactory() {
    }

    public static WebDriver createDriver() {
        LOG.info("Creating WebDriver for browser");
        final WebDriver driver = createChromeDriver();
        configureDriver(driver);
        return driver;
    }

    private static WebDriver createChromeDriver() {
        final ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--allow-insecure-localhost",
                "--start-maximized");
        return new ChromeDriver(options);
    }

    private static void configureDriver(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(DEFAULT_TIMEOUT);
        driver.manage().timeouts().scriptTimeout(DEFAULT_TIMEOUT);
    }
}
