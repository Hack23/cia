/*
 * Copyright 2014 James Pether Sörling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *	$Id: UserRoleSystemTest.java 6118 2015-07-31 17:41:55Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/citizen-intelligence-agency/src/test/java/com/hack23/cia/systemintegrationtest/UserRoleSystemTest.java $
*/
package com.hack23.cia.systemintegrationtest;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.hack23.cia.model.internal.application.data.impl.DataAgentOperation;
import com.hack23.cia.model.internal.application.data.impl.DataAgentTarget;
import com.hack23.cia.testfoundation.Parallelized;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommitteePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DocumentPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.MinistryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PartyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PoliticianPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.user.committee.CommitteeView;
import com.hack23.cia.web.impl.ui.application.views.user.document.DocumentView;
import com.hack23.cia.web.impl.ui.application.views.user.goverment.MinistryRankingView;
import com.hack23.cia.web.impl.ui.application.views.user.goverment.MinistryView;
import com.hack23.cia.web.impl.ui.application.views.user.party.PartyRankingView;
import com.hack23.cia.web.impl.ui.application.views.user.party.PartyView;
import com.hack23.cia.web.impl.ui.application.views.user.politician.PoliticianRankingView;
import com.hack23.cia.web.impl.ui.application.views.user.politician.PoliticianView;
import com.hack23.cia.web.impl.ui.application.views.user.test.TestChartView;

/**
 * The Class UserRoleSystemTest.
 */
@RunWith(Parallelized.class)
public class UserRoleSystemTest extends AbstractSystemTest {

	/** The test server. */
	private static CitizenIntelligenceAgencyServer testServer;

	/** The browser. */
	private final String browser;

	/** The web driver map. */
	private final Map<String,WebDriver>  webDriverMap= new ConcurrentHashMap<String, WebDriver>();

	/**
	 * Instantiates a new user role system test.
	 *
	 * @param browser
	 *            the browser
	 */
	public UserRoleSystemTest(final String browser) {
		super();
		this.browser = browser;
	}

	/**
	 * Browsers strings.
	 *
	 * @return the collection
	 */
	@Parameters(name = "SiteTest{index}: browser({0})")
	public static Collection<String[]> browsersStrings() {
		return Arrays.asList(new String[][] { { "firefox" }, { "chrome" }});
		// return Arrays.asList(new Object[][] { { "firefox" },{ "chrome" }, {
		// "htmlunit-firefox" },{ "htmlunit-ie11" },{ "htmlunit-chrome" } });
	}

	/**
	 * Start server.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeClass
	public synchronized static void startServer() throws Exception {
		if (testServer == null) {
			testServer = new CitizenIntelligenceAgencyServer();
			testServer.startServer();
			Thread.sleep(19000);
		}
	}

	/**
	 * Stop server.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@AfterClass
	public synchronized static void stopServer() throws Exception {
		if (testServer != null) {
			Thread.sleep(2000);
			testServer.stop();
			testServer = null;
		}
	}

	/**
	 * Site committee ranking view test.
	 */
	@Test
	public void siteCommitteeRankingViewTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.Overview));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DataGrid));
//			userPageVisit.VisitCommitteeView(userPageVisit.getActionIdsBy(ViewAction.VISIT_COMMITTEE_VIEW).iterator().next());

			userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.Charts));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}


	/**
	 * Site party ranking view test.
	 */
	@Test
	public void sitePartyRankingViewTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyRankingView.NAME, PageMode.Overview));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyRankingView.NAME, PageMode.DataGrid));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyRankingView.NAME, PageMode.Charts));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site politician ranking view test.
	 */
	@Test
	public void sitePoliticianRankingViewTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianRankingView.NAME, PageMode.Overview));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianRankingView.NAME, PageMode.DataGrid));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianRankingView.NAME, PageMode.Charts));


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}



	/**
	 * Site ministry test.
	 */
	@Test
	public void siteMinistryTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryView.NAME, PageMode.Overview, "N%C3%A4ringsdepartementet"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryView.NAME, MinistryPageMode.DocumentHistory.toString(), "N%C3%A4ringsdepartementet"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryView.NAME, MinistryPageMode.DocumentActivity.toString(), "N%C3%A4ringsdepartementet"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryView.NAME, MinistryPageMode.CurrentMembers.toString(), "N%C3%A4ringsdepartementet"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryView.NAME, MinistryPageMode.MemberHistory.toString(), "N%C3%A4ringsdepartementet"));
			// userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryView.NAME, MinistryPageMode.RoleGhant.toString(), "N%C3%A4ringsdepartementet"));



		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site committee test.
	 */
	@Test
	public void siteCommitteeTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(CommitteeView.NAME, PageMode.Overview, "UU"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(CommitteeView.NAME, CommitteePageMode.DOCUMENT_HISTORY.toString(), "UU"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(CommitteeView.NAME, CommitteePageMode.CURRENT_MEMBERS.toString(), "UU"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(CommitteeView.NAME, CommitteePageMode.MemberHistory.toString(), "UU"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(CommitteeView.NAME, CommitteePageMode.DocumentActivity.toString(), "UU"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(CommitteeView.NAME, CommitteePageMode.BallotDecisionSummary.toString(), "UU"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(CommitteeView.NAME, CommitteePageMode.DecisionSummary.toString(), "UU"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(CommitteeView.NAME, CommitteePageMode.DecisionTypeDailySummary.toString(), "UU"));
		//	userPageVisit.visitDirectPage(new PageModeMenuCommand(CommitteeView.NAME, CommitteePageMode.RoleGhant.toString(), "UU"));



		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site test chart view test.
	 */
	@Test
	public void siteTestChartViewTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {



			userPageVisit.visitDirectPage(new PageModeMenuCommand(TestChartView.NAME, PageMode.Overview));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(TestChartView.NAME, PageMode.Indicators, "UIS.TEP.5.A"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(TestChartView.NAME, PageMode.Charts, ChartIndicators.PartyWinner.toString()));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(TestChartView.NAME, PageMode.Charts, ChartIndicators.DecsionActivityByType.toString()));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(TestChartView.NAME, PageMode.Charts, ChartIndicators.DocumentActivityByType.toString()));



		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site ministry ranking test.
	 */
	@Test
	public void siteMinistryRankingTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryRankingView.NAME, PageMode.Overview));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryRankingView.NAME, PageMode.DataGrid));
//			userPageVisit.VisitMinistryView(userPageVisit.getActionIdsBy(ViewAction.VISIT_MINISTRY_VIEW).iterator().next());
			userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryRankingView.NAME, PageMode.Charts));


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site document test.
	 */
	@Test
	public void siteDocumentTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(DocumentView.NAME, PageMode.Overview, "GZ02C343"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(DocumentView.NAME, DocumentPageMode.PersonReferences.toString(), "GZ02C343"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(DocumentView.NAME, DocumentPageMode.DocumentDetails.toString(), "GZ02C343"));


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site document3 test.
	 */
	@Test
	public void siteDocument3Test() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(DocumentView.NAME, DocumentPageMode.DocumentActivity.toString(), "GZ02C343"));


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site document2 test.
	 */
	@Test
	public void siteDocument2Test() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(DocumentView.NAME, DocumentPageMode.DocumentData.toString(), "GZ02C343"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(DocumentView.NAME, DocumentPageMode.DocumentReferences.toString(), "GZ02C343"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(DocumentView.NAME, DocumentPageMode.DocumenDecision.toString(), "GZ02C343"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(DocumentView.NAME, DocumentPageMode.DocumentAttachments.toString(), "GZ02C343"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}


	/**
	 * Site party test.
	 */
	@Test
	public void sitePartyTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PageMode.Overview, "S"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.DocumentHistory.toString(), "S"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.CurrentMembers.toString(), "S"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.MemberHistory.toString(), "S"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.CurrentLeaders.toString(), "S"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.LeaderHistory.toString(), "S"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.CommitteeRoles.toString(), "S"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.GovernmentRoles.toString(), "S"));
			// userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.DocumentActivity.toString(), "S"));
			// userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.CommitteeBallotDecisionSummary.toString(), "S"));
			//userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.VoteHistory.toString(), "S"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.PartyWonDailySummaryChart.toString(), "S"));


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}


	/**
	 * Site admin test.
	 */
	@Test
	public void siteAdminTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitStartPage();

			userPageVisit.visitAdminAgentOperationView();
			userPageVisit.performAdminAgentOperation(DataAgentTarget.MODEL_EXTERNAL_RIKSDAGEN, DataAgentOperation.IMPORT);
			userPageVisit.visitMainView();

			userPageVisit.visitAdminDataSummaryView();
			userPageVisit.visitMainView();

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site politican test.
	 */
	@Test
	public void sitePoliticanTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianView.NAME, PageMode.Overview, "0980681611418"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianView.NAME, PoliticianPageMode.RoleSummary.toString(), "0980681611418"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianView.NAME, PoliticianPageMode.RoleList.toString(), "0980681611418"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianView.NAME, PoliticianPageMode.RoleGhant.toString(), "0980681611418"));
			//userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianView.NAME, PoliticianPageMode.VoteHistory.toString(), "0980681611418"));
			//userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianView.NAME, PoliticianPageMode.BallotDecisionSummary.toString(), "0980681611418"));
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianView.NAME, PoliticianPageMode.DocumentHistory.toString(), "0980681611418"));
			//userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianView.NAME, PoliticianPageMode.DocumentActivity.toString(), "0980681611418"));




		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Close web driver.
	 */
	@After
	public void closeWebDriver() {
		webDriverMap.get(browser).quit();
	}

	/**
	 * Gets the web driver.
	 *
	 * @return the web driver
	 */
	private synchronized WebDriver getWebDriver() {

		WebDriver driver = null;
		if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("htmlunit-firefox")) {
			final HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(
					BrowserVersion.FIREFOX_38);
			htmlUnitDriver.setJavascriptEnabled(true);
			driver = htmlUnitDriver;
		} else if (browser.equals("htmlunit-ie11")) {
			final HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(
					BrowserVersion.INTERNET_EXPLORER_11);
			htmlUnitDriver.setJavascriptEnabled(true);
			driver = htmlUnitDriver;
		} else if (browser.equals("htmlunit-chrome")) {
			final HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(
					BrowserVersion.CHROME);
			htmlUnitDriver.setJavascriptEnabled(true);
			driver = htmlUnitDriver;
		} else {
			fail("No valid browser parameter:" + browser);
		}

		webDriverMap.put(browser, driver);
		return driver;
	}

}
