package com.hack23.cia.systemintegrationtest.ui;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A factory for creating WebDriver objects.
 */
public class WebDriverFactory {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(WebDriverFactory.class);

    /** The Constant DEFAULT_TIMEOUT. */
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(90);

    /**
     * Instantiates a new web driver factory.
     */
    private WebDriverFactory() {
    }

    /**
     * Creates a new WebDriver object.
     *
     * @return the web driver
     */
    public static WebDriver createDriver() {
        LOG.info("Creating WebDriver for browser");
        final WebDriver driver = createChromeDriver();
        configureDriver(driver);
        return driver;
    }

    /**
     * Creates a new WebDriver object.
     *
     * @return the web driver
     */
    private static WebDriver createChromeDriver() {
        final ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--allow-insecure-localhost",
                "--start-maximized");
        return new ChromeDriver(options);
    }

    /**
     * Configure driver.
     *
     * @param driver the driver
     */
    private static void configureDriver(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(DEFAULT_TIMEOUT);
        driver.manage().timeouts().scriptTimeout(DEFAULT_TIMEOUT);
    }
}
