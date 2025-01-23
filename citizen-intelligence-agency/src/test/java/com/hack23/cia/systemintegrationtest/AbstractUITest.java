package com.hack23.cia.systemintegrationtest;

import org.junit.After;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import com.hack23.cia.systemintegrationtest.ui.TestConstants;
import com.hack23.cia.systemintegrationtest.ui.UserPageVisit;
import com.hack23.cia.systemintegrationtest.ui.WebDriverFactory;

/**
 * The Class AbstractUITest.
 */
public abstract class AbstractUITest extends AbstractRoleSystemITest {


	/** The Constant DEFAULT_TIMEOUT. */
	protected static final long DEFAULT_TIMEOUT = TestConstants.DEFAULT_TIMEOUT;

	
	/** The driver. */
	protected static WebDriver driver;

	/** The page visit. */
	protected static UserPageVisit pageVisit;

	/**
	 * Global setup.
	 */
	@BeforeClass
	public static void globalSetup() {
		driver = WebDriverFactory.createDriver();
		pageVisit = new UserPageVisit(driver);
	}


	@After
	public void cleanBrowser() {
		pageVisit.cleanBrowser();
	}

}
