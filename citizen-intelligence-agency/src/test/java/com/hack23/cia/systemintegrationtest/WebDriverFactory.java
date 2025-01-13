package com.hack23.cia.systemintegrationtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverFactory {
    private static final Logger LOG = LoggerFactory.getLogger(WebDriverFactory.class);
    
    private WebDriverFactory() {}
    
    public static WebDriver createDriver(Browser browser) {
        LOG.info("Creating WebDriver for browser: {}", browser);
        
        return switch (browser) {
            case CHROME -> new ChromeDriver();
            case FIREFOX -> new FirefoxDriver();
            case EDGE -> new EdgeDriver();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }
}
