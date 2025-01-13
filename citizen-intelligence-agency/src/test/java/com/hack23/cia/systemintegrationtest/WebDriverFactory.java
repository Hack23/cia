package com.hack23.cia.systemintegrationtest;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverFactory {
    private static final Logger LOG = LoggerFactory.getLogger(WebDriverFactory.class);
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(90);

    private WebDriverFactory() {
    }

    @SuppressWarnings("null")
    public static WebDriver createDriver(Browser browser) {
        LOG.info("Creating WebDriver for browser: {}", browser);

        WebDriver driver = switch (browser) {
            case CHROME -> createChromeDriver();
            case FIREFOX -> createFirefoxDriver();
            case EDGE -> createEdgeDriver();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };

        configureDriver(driver);
        return driver;
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--allow-insecure-localhost",
                "--start-maximized");
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("marionette", true);
        FirefoxOptions options = new FirefoxOptions(capabilities);
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver createEdgeDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        return new EdgeDriver(options);
    }

    private static void configureDriver(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(DEFAULT_TIMEOUT);
        driver.manage().timeouts().scriptTimeout(DEFAULT_TIMEOUT);
    }
}
