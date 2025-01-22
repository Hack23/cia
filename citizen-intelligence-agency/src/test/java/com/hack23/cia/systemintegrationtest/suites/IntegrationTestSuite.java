package com.hack23.cia.systemintegrationtest.suites;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.hack23.cia.systemintegrationtest.CitizenIntelligenceAgencyServer;
import com.hack23.cia.systemintegrationtest.admin.configuration.AdminConfigurationTest;
import com.hack23.cia.systemintegrationtest.admin.data.AdminDataTest;
import com.hack23.cia.systemintegrationtest.admin.operations.AdminOperationsTest;
import com.hack23.cia.systemintegrationtest.admin.security.AdminSecurityTest;
import com.hack23.cia.systemintegrationtest.user.home.UserHomeTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

/**
 * The Class IntegrationTestSuite.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    AdminConfigurationTest.class,
    AdminDataTest.class,
    AdminOperationsTest.class,
    AdminSecurityTest.class,
    UserHomeTest.class
})
public class IntegrationTestSuite {
	
	protected static final boolean usingExternalServer;

	/** The webdriver setup. */
	private static boolean webdriverSetup = false;

	/** The Constant webDriverMap. */
	static {
		final String systemTestTargetUrlProperty = System.getProperty("system.test.target.url");
		if (systemTestTargetUrlProperty != null && systemTestTargetUrlProperty.isEmpty()) {
			usingExternalServer = true;
		} else {
			usingExternalServer = false;
		}



		CitizenIntelligenceAgencyServer.setEnv("CIA_APP_ENCRYPTION_PASSWORD", "allhaildiscordia");
	}

	/**
	 * Start server.
	 *
	 * @throws Exception
	 *                   the exception
	 */
	@BeforeClass
	public static final synchronized void startServer() throws Exception {
		if (!usingExternalServer) {
			CitizenIntelligenceAgencyServer.startTestServer();
		}
		if(!webdriverSetup) {
			WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
			webdriverSetup=true;
		}
	}

	/**
	 * Stop server.
	 *
	 * @throws Exception
	 *                   the exception
	 */
	@AfterClass
	public static final synchronized void stopServer() throws Exception {
		if (!usingExternalServer) {
			CitizenIntelligenceAgencyServer.stopTestServer();
		}
	}

	
	
}
