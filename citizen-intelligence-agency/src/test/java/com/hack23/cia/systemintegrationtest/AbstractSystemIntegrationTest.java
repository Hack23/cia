package com.hack23.cia.systemintegrationtest;

import org.openqa.selenium.WebDriver;

public abstract class AbstractSystemIntegrationTest {
    protected static final String NO_WEBDRIVER_EXIST_FOR_BROWSER = "No webdriver exist for browser:";
    protected static final int DEFAULT_TIMEOUT = 60000;
    protected static final int DEFAULT_MAX_RETRIES = 3;
    
    protected abstract Browser getBrowser();
    
    protected WebDriver getWebDriver() {
        return WebDriverFactory.createDriver(getBrowser());
    }
    
    protected void retryOnFailure(Runnable test, int maxRetries) {
        Exception lastException = null;
        for (int i = 0; i < maxRetries; i++) {
            try {
                test.run();
                return;
            } catch (Exception e) {
                lastException = e;
            }
        }
        if (lastException != null) {
            throw new RuntimeException("Test failed after " + maxRetries + " retries", lastException);
        }
    }
}
