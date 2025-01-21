package com.hack23.cia.systemintegrationtest;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hack23.cia.systemintegrationtest.ui.TestConstants;
import com.hack23.cia.systemintegrationtest.ui.UserPageVisit;
import com.hack23.cia.systemintegrationtest.ui.WebDriverFactory;

/**
 * The Class AbstractUITest.
 */
public abstract class AbstractUITest extends AbstractRoleSystemITest {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AbstractUITest.class);

	/** The Constant DEFAULT_TIMEOUT. */
	protected static final long DEFAULT_TIMEOUT = TestConstants.DEFAULT_TIMEOUT;

	/** The Constant BASE_URL. */
	protected static final String BASE_URL = System.getProperty("system.test.target.url",
			CitizenIntelligenceAgencyServer.ACCESS_URL);

	/** The driver. */
	protected WebDriver driver;

	/** The page visit. */
	protected UserPageVisit pageVisit;

	/**
	 * Global setup.
	 */
	@Before
	public void globalSetup() {
		LOG.info("Setting up test with browser");
		driver = WebDriverFactory.createDriver();
		pageVisit = new UserPageVisit(driver);
	}

	/**
	 * Global teardown.
	 */
	@After
	public void globalTeardown() {
		LOG.info("Tearing down WebDriver after all tests have run");
		if (driver != null) {
			driver.quit();
		}
	}

}
