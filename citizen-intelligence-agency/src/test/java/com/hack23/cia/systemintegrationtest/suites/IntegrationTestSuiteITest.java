package com.hack23.cia.systemintegrationtest.suites;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;

import com.hack23.cia.systemintegrationtest.CitizenIntelligenceAgencyServer;
import com.hack23.cia.systemintegrationtest.admin.configuration.AdminConfigurationITest;
import com.hack23.cia.systemintegrationtest.admin.data.AdminDataITest;
import com.hack23.cia.systemintegrationtest.admin.operations.AdminOperationsITest;
import com.hack23.cia.systemintegrationtest.admin.security.AdminSecurityITest;
import com.hack23.cia.systemintegrationtest.ui.UserPageVisit;
import com.hack23.cia.systemintegrationtest.ui.WebDriverFactory;
import com.hack23.cia.systemintegrationtest.user.ballot.UserBallotITest;
import com.hack23.cia.systemintegrationtest.user.committee.UserCommitteeRankingITest;
import com.hack23.cia.systemintegrationtest.user.committee.UserCommitteeITest;
import com.hack23.cia.systemintegrationtest.user.common.UserCommonITest;
import com.hack23.cia.systemintegrationtest.user.country.UserCountryITest;
import com.hack23.cia.systemintegrationtest.user.docsearch.UserDocumentSearchITest;
import com.hack23.cia.systemintegrationtest.user.document.UserDocumentITest;
import com.hack23.cia.systemintegrationtest.user.documents.UserDocumentsITest;
import com.hack23.cia.systemintegrationtest.user.governmentbody.UserGovernmentBodyRankingITest;
import com.hack23.cia.systemintegrationtest.user.governmentbody.UserGovernmentBodyITest;
import com.hack23.cia.systemintegrationtest.user.home.UserHomeITest;
import com.hack23.cia.systemintegrationtest.user.ministry.UserMinistryRankingITest;
import com.hack23.cia.systemintegrationtest.user.ministry.UserMinistryITest;
import com.hack23.cia.systemintegrationtest.user.parliament.UserParliamentITest;
import com.hack23.cia.systemintegrationtest.user.party.UserPartyRankingITest;
import com.hack23.cia.systemintegrationtest.user.party.UserPartyITest;
import com.hack23.cia.systemintegrationtest.user.politician.UserPoliticianRankingITest;
import com.hack23.cia.systemintegrationtest.user.politician.UserPoliticianITest;

/**
 * The Class IntegrationTestSuiteITest.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	UserCommonITest.class,
    UserPartyRankingITest.class,
    UserPartyITest.class,
    UserCommitteeITest.class,
    UserCommitteeRankingITest.class,
    UserCountryITest.class,
    UserMinistryRankingITest.class,
    UserMinistryITest.class,
    UserGovernmentBodyITest.class,
    UserGovernmentBodyRankingITest.class,
    UserParliamentITest.class,
    UserPoliticianITest.class,
    UserPoliticianRankingITest.class,
    UserDocumentITest.class,
    UserDocumentsITest.class,
    UserDocumentSearchITest.class,
    UserBallotITest.class,
    UserHomeITest.class,
    AdminConfigurationITest.class,
    AdminDataITest.class,
    AdminOperationsITest.class,
    AdminSecurityITest.class
})
public class IntegrationTestSuiteITest {

	/** The Constant BASE_URL. */
	protected static final String BASE_URL = System.getProperty("system.test.target.url",
			CitizenIntelligenceAgencyServer.ACCESS_URL);

	/** The driver. */
	protected static WebDriver driver;


	/** The page visit. */
	protected static UserPageVisit pageVisit;

	/** The Constant usingExternalServer. */
	protected static final boolean usingExternalServer;

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
	 * Global setup.
	 */
	@BeforeClass
	public static void globalSetup() {
		driver = WebDriverFactory.createDriver();
		pageVisit = new UserPageVisit(driver);
	}

	/**
	 * Global teardown.
	 */
	@AfterClass
	public static void globalTeardown() {
		if (driver != null) {
			driver.quit();
		}
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

	/**
	 * Clean browser.
	 */
	@After
	public void cleanBrowser() {
		pageVisit.cleanBrowser();
	}


}
