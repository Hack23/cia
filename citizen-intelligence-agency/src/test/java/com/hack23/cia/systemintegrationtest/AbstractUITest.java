package com.hack23.cia.systemintegrationtest;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractUITest extends AbstractRoleSystemITest {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractUITest.class);
    protected static final long DEFAULT_TIMEOUT = TestConstants.DEFAULT_TIMEOUT;
    protected static final String BASE_URL = System.getProperty("system.test.target.url",
            CitizenIntelligenceAgencyServer.ACCESS_URL);

    protected WebDriver driver;
    protected UserPageVisit pageVisit;

    @Before
    public void setup() {
        Browser browserType = getBrowser();
        LOG.info("Setting up test with browser: {}", browserType);
        driver = WebDriverFactory.createDriver(browserType);
        pageVisit = new UserPageVisit(driver, browserType);
    }

    @After
    public void teardown() {
        LOG.info("Tearing down test");
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                LOG.error("Error closing WebDriver", e);
            }
        }
    }

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
                LOG.warn("Test failed, attempt {} of {}", i + 1, maxRetries);
            }
        }
        throw new AssertionError("Test failed after " + maxRetries + " attempts", lastException);
    }

}
