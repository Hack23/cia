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
 *	$Id$
 *  $HeadURL$
*/
package com.hack23.cia.systemintegrationtest;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.hack23.cia.model.internal.application.data.impl.DataAgentOperation;
import com.hack23.cia.model.internal.application.data.impl.DataAgentTarget;
import com.hack23.cia.testfoundation.Parallelized;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
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
@FixMethodOrder(value=MethodSorters.NAME_ASCENDING)
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
		return Arrays.asList(new String[][] {  { "firefox" }});
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
			testServer.stop();
			testServer = null;
		}
	}

	/**
	 * Site committee ranking view overview test.
	 */
	@Test
	public void siteCommitteeRankingViewOverviewTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.Overview));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}


	/**
	 * Site committee ranking view data grid test.
	 */
	@Test
	public void siteCommitteeRankingViewDataGridTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DataGrid));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site committee ranking view charts test.
	 */
	@Test
	public void siteCommitteeRankingViewChartsTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.Charts));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}


	/**
	 * Site committee ranking view grid navigation test.
	 */
	@Test
	public void siteCommitteeRankingViewGridNavigationTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DataGrid));
			userPageVisit.VisitCommitteeView(userPageVisit.getActionIdsBy(ViewAction.VISIT_COMMITTEE_VIEW).iterator().next());


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}


	/**
	 * Site party ranking view overview test.
	 */
	@Test
	public void sitePartyRankingViewOverviewTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyRankingView.NAME, PageMode.Overview));
		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}


	/**
	 * Site party ranking view data grid test.
	 */
	@Test
	public void sitePartyRankingViewDataGridTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyRankingView.NAME, PageMode.DataGrid));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site party ranking view charts test.
	 */
	@Test
	public void sitePartyRankingViewChartsTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyRankingView.NAME, PageMode.Charts));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}


	/**
	 * Site politician ranking view overview test.
	 */
	@Test
	public void sitePoliticianRankingViewOverviewTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianRankingView.NAME, PageMode.Overview));
		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site politician ranking view data grid test.
	 */
	@Test
	public void sitePoliticianRankingViewDataGridTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianRankingView.NAME, PageMode.DataGrid));


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}


	/**
	 * Site politician ranking view charts test.
	 */
	@Test
	public void sitePoliticianRankingViewChartsTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianRankingView.NAME, PageMode.Charts));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}




	/**
	 * Site ministry overview test.
	 */
	@Test
	public void siteMinistryOverviewTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryView.NAME, PageMode.Overview, "N%C3%A4ringsdepartementet"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}



	/**
	 * Site ministry document history test.
	 */
	@Test
	public void siteMinistryDocumentHistoryTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryView.NAME, MinistryPageMode.DocumentHistory.toString(), "N%C3%A4ringsdepartementet"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site ministry document activity test.
	 */
	@Test
	public void siteMinistryDocumentActivityTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryView.NAME, MinistryPageMode.DocumentActivity.toString(), "N%C3%A4ringsdepartementet"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site ministry current members test.
	 */
	@Test
	public void siteMinistryCurrentMembersTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryView.NAME, MinistryPageMode.CurrentMembers.toString(), "N%C3%A4ringsdepartementet"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site ministry member history test.
	 */
	@Test
	public void siteMinistryMemberHistoryTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryView.NAME, MinistryPageMode.MemberHistory.toString(), "N%C3%A4ringsdepartementet"));
			// userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryView.NAME, MinistryPageMode.RoleGhant.toString(), "N%C3%A4ringsdepartementet"));


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}




	/**
	 * Site committee overview test.
	 */
	@Test
	public void siteCommitteeOverviewTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(CommitteeView.NAME, PageMode.Overview, "UU"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}



	/**
	 * Site committee document history test.
	 */
	@Test
	public void siteCommitteeDocumentHistoryTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(CommitteeView.NAME, CommitteePageMode.DOCUMENT_HISTORY.toString(), "UU"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site committee current members test.
	 */
	@Test
	public void siteCommitteeCurrentMembersTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(CommitteeView.NAME, CommitteePageMode.CURRENT_MEMBERS.toString(), "UU"));


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}



	/**
	 * Site committee member history test.
	 */
	@Test
	public void siteCommitteeMemberHistoryTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(CommitteeView.NAME, CommitteePageMode.MemberHistory.toString(), "UU"));



		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site committee document activity test.
	 */
	@Test
	public void siteCommitteeDocumentActivityTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(CommitteeView.NAME, CommitteePageMode.DocumentActivity.toString(), "UU"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site committee ballot decision summary test.
	 */
	@Test
	public void siteCommitteeBallotDecisionSummaryTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(CommitteeView.NAME, CommitteePageMode.BallotDecisionSummary.toString(), "UU"));


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site committee decision summary test.
	 */
	@Test
	public void siteCommitteeDecisionSummaryTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(CommitteeView.NAME, CommitteePageMode.DecisionSummary.toString(), "UU"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site committee decision type daily summary test.
	 */
	@Test
	public void siteCommitteeDecisionTypeDailySummaryTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(CommitteeView.NAME, CommitteePageMode.DecisionTypeDailySummary.toString(), "UU"));
		//	userPageVisit.visitDirectPage(new PageModeMenuCommand(CommitteeView.NAME, CommitteePageMode.RoleGhant.toString(), "UU"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}


	/**
	 * Site test chart view overview test.
	 */
	@Test
	public void siteTestChartViewOverviewTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(TestChartView.NAME, PageMode.Overview));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site test chart view indicators test.
	 */
	@Test
	public void siteTestChartViewIndicatorsTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(TestChartView.NAME, PageMode.Indicators, "UIS.TEP.5.A"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site test chart view party winner test.
	 */
	@Test
	@Ignore
	public void siteTestChartViewPartyWinnerTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(TestChartView.NAME, PageMode.Charts, ChartIndicators.PartyWinner.toString()));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site test chart view decsion activity by type test.
	 */
	@Test
	public void siteTestChartViewDecsionActivityByTypeTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(TestChartView.NAME, PageMode.Charts, ChartIndicators.DecsionActivityByType.toString()));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site test chart view document activity by type test.
	 */
	@Test
	public void siteTestChartViewDocumentActivityByTypeTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(TestChartView.NAME, PageMode.Charts, ChartIndicators.DocumentActivityByType.toString()));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}


	/**
	 * Site ministry ranking overview test.
	 */
	@Test
	public void siteMinistryRankingOverviewTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryRankingView.NAME, PageMode.Overview));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}


	/**
	 * Site ministry ranking data grid test.
	 */
	@Test
	public void siteMinistryRankingDataGridTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryRankingView.NAME, PageMode.DataGrid));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site ministry ranking charts test.
	 */
	@Test
	public void siteMinistryRankingChartsTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryRankingView.NAME, PageMode.Charts));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}



	/**
	 * Site ministry ranking navigation test.
	 */
	@Test
	public void siteMinistryRankingNavigationTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(MinistryRankingView.NAME, PageMode.DataGrid));
			userPageVisit.VisitMinistryView(userPageVisit.getActionIdsBy(ViewAction.VISIT_MINISTRY_VIEW).iterator().next());


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site document details test.
	 */
	@Test
	@Ignore
	public void siteDocumentDetailsTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(DocumentView.NAME, DocumentPageMode.DocumentDetails.toString(), "GZ02C343"));


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site document over view test.
	 */
	@Test
	@Ignore
	public void siteDocumentOverViewTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(DocumentView.NAME, PageMode.Overview, "GZ02C343"));


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site document person ref test.
	 */
	@Test
	@Ignore
	public void siteDocumentPersonRefTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(DocumentView.NAME, DocumentPageMode.PersonReferences.toString(), "GZ02C343"));


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site document doc activity test.
	 */
	@Test
	@Ignore
	public void siteDocumentDocActivityTest() {
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
	 * Site document doc data test.
	 */
	@Test
	@Ignore
	public void siteDocumentDocDataTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(DocumentView.NAME, DocumentPageMode.DocumentData.toString(), "GZ02C343"));
		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site document doc refb test.
	 */
	@Test
	@Ignore
	public void siteDocumentDocRefbTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(DocumentView.NAME, DocumentPageMode.DocumentReferences.toString(), "GZ02C343"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site document doc decision test.
	 */
	@Test
	@Ignore
	public void siteDocumentDocDecisionTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(DocumentView.NAME, DocumentPageMode.DocumenDecision.toString(), "GZ02C343"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site document doc attachment test.
	 */
	@Test
	@Ignore
	public void siteDocumentDocAttachmentTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(DocumentView.NAME, DocumentPageMode.DocumentAttachments.toString(), "GZ02C343"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}



	/**
	 * Site party overview test.
	 */
	@Test
	public void sitePartyOverviewTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PageMode.Overview, "S"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}


	/**
	 * Site party document history test.
	 */
	@Test
	public void sitePartyDocumentHistoryTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.DocumentHistory.toString(), "S"));


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site party current members test.
	 */
	@Test
	public void sitePartyCurrentMembersTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.CurrentMembers.toString(), "S"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site party member history test.
	 */
	@Test
	public void sitePartyMemberHistoryTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.MemberHistory.toString(), "S"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site party current leaders test.
	 */
	@Test
	public void sitePartyCurrentLeadersTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.CurrentLeaders.toString(), "S"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site party leader history test.
	 */
	@Test
	public void sitePartyLeaderHistoryTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.LeaderHistory.toString(), "S"));


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site party committee roles test.
	 */
	@Test
	public void sitePartyCommitteeRolesTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.CommitteeRoles.toString(), "S"));


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site party government roles test.
	 */
	@Test
	public void sitePartyGovernmentRolesTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.GovernmentRoles.toString(), "S"));


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site party party won daily summary chart test.
	 */
	@Test
	@Ignore
	public void sitePartyPartyWonDailySummaryChartTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.PartyWonDailySummaryChart.toString(), "S"));


		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}



	/**
	 * Site party document activity test.
	 */
	@Test
	public void sitePartyDocumentActivityTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.DocumentActivity.toString(), "S"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site party committee ballot decision summary test.
	 */
	@Test
	public void sitePartyCommitteeBallotDecisionSummaryTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.CommitteeBallotDecisionSummary.toString(), "S"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site party vote history test.
	 */
	@Test
	@Ignore
	public void sitePartyVoteHistoryTest() {
		//TO SLOW

		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {


			userPageVisit.visitDirectPage(new PageModeMenuCommand(PartyView.NAME, PartyPageMode.VoteHistory.toString(), "S"));


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

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site admin2 test.
	 */
	@Test
	public void siteAdminDataSummary() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {

			userPageVisit.visitStartPage();
			userPageVisit.visitAdminDataSummaryView();

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site politican over view test.
	 */
	@Test
	@Ignore
	public void sitePoliticanOverViewTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianView.NAME, PageMode.Overview, "0980681611418"));
		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}


	/**
	 * Site politican role summary test.
	 */
	@Test
	@Ignore
	public void sitePoliticanRoleSummaryTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianView.NAME, PoliticianPageMode.RoleSummary.toString(), "0980681611418"));
		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site politican role list test.
	 */
	@Test
	@Ignore
	public void sitePoliticanRoleListTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianView.NAME, PoliticianPageMode.RoleList.toString(), "0980681611418"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site politican role ghant test.
	 */
	@Test
	@Ignore
	public void sitePoliticanRoleGhantTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianView.NAME, PoliticianPageMode.RoleGhant.toString(), "0980681611418"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site politican document history test.
	 */
	@Test
	@Ignore
	public void sitePoliticanDocumentHistoryTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianView.NAME, PoliticianPageMode.DocumentHistory.toString(), "0980681611418"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}


	/**
	 * Site politican vote history test.
	 */
	@Test
	@Ignore
	public void sitePoliticanVoteHistoryTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianView.NAME, PoliticianPageMode.VoteHistory.toString(), "0980681611418"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site politican ballot decision summary test.
	 */
	@Test
	@Ignore
	public void sitePoliticanBallotDecisionSummaryTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianView.NAME, PoliticianPageMode.BallotDecisionSummary.toString(), "0980681611418"));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Test Exception:" + e.getMessage());
		}
	}

	/**
	 * Site politican document activity test.
	 */
	@Test
	@Ignore
	public void sitePoliticanDocumentActivityTest() {
		final WebDriver driver = getWebDriver();
		if (driver == null) {
			fail("No valid browser driver");
		}

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		try {
			userPageVisit.visitDirectPage(new PageModeMenuCommand(PoliticianView.NAME, PoliticianPageMode.DocumentActivity.toString(), "0980681611418"));

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
