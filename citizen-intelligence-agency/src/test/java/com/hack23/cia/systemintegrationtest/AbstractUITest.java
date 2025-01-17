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

public abstract class AbstractUITest extends AbstractRoleSystemITest {
	private static final Logger LOG = LoggerFactory.getLogger(AbstractUITest.class);
	protected static final long DEFAULT_TIMEOUT = TestConstants.DEFAULT_TIMEOUT;
	protected static final String BASE_URL = System.getProperty("system.test.target.url",
			CitizenIntelligenceAgencyServer.ACCESS_URL);

	protected static WebDriver driver;
	protected static UserPageVisit pageVisit;

	@BeforeClass
	public static void globalSetup() {
		LOG.info("Setting up test with browser");
		driver = WebDriverFactory.createDriver();
		pageVisit = new UserPageVisit(driver);
	}

	@AfterClass
	public static void globalTeardown() {
		LOG.info("Tearing down WebDriver after all tests have run");
		if (driver != null) {
			driver.quit();
		}
	}

	@Before
	public void setup() {
		cleanBrowser();
	}

	@After
	public void teardown() {
		cleanBrowser();
	}

	private void cleanBrowser() {
		if (driver != null) {
			driver.manage().deleteAllCookies();
			driver.get("about:blank");
		}
	}

}
