package com.hack23.cia.systemintegrationtest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
	protected static WebDriver driver;

	/** The page visit. */
	protected static UserPageVisit pageVisit;

	/**
	 * Global setup.
	 */
	@BeforeClass
	public static void globalSetup() {
		LOG.info("Setting up test with browser");
		driver = WebDriverFactory.createDriver();
		pageVisit = new UserPageVisit(driver);
	}

	/**
	 * Global teardown.
	 */
	@AfterClass
	public static void globalTeardown() {
		LOG.info("Tearing down WebDriver after all tests have run");
		if (driver != null) {
			driver.quit();
		}
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		cleanBrowser();
	}

	/**
	 * Teardown.
	 */
	@After
	public void teardown() {
		cleanBrowser();
	}

	/**
	 * Clean browser.
	 */
	private void cleanBrowser() {
		if (driver != null) {
			driver.manage().deleteAllCookies();
			driver.get("about:blank");
		}
	}

}
