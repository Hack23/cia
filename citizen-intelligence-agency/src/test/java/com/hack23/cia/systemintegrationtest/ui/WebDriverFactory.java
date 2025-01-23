package com.hack23.cia.systemintegrationtest.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

/**
 * A factory for creating WebDriver objects.
 */
public class WebDriverFactory {

	private static WebDriver driver; 

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
       if (driver == null ) {
    	   WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
    	   driver = createChromeDriver();
    	   configureDriver(driver);
       }
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
        driver.manage().timeouts().pageLoadTimeout(TestConstants.DEFAULT_BROWSER_TIMEOUT);
        driver.manage().timeouts().scriptTimeout(TestConstants.DEFAULT_BROWSER_TIMEOUT);
    }
}
