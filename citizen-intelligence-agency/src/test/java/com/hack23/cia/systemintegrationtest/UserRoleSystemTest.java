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
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.hack23.cia.model.internal.application.data.impl.DataAgentOperation;
import com.hack23.cia.model.internal.application.data.impl.DataAgentTarget;
import com.hack23.cia.testfoundation.AbstractSystemIntegrationTest;
import com.hack23.cia.testfoundation.Parallelized;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommitteePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DocumentPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.MinistryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PartyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PoliticianPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Class UserRoleSystemTest.
 */
@RunWith(Parallelized.class)
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class UserRoleSystemTest extends AbstractSystemIntegrationTest {

	/** The test server. */
	private static CitizenIntelligenceAgencyServer testServer;

	/** The browser. */
	private final String browser;

	/** The web driver map. */
	private final Map<String, WebDriver> webDriverMap = new ConcurrentHashMap<String, WebDriver>();


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
		return Arrays.asList(new String[][] { { "firefox" } });
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
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeRankingViewOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.Overview));

	}

	/**
	 * Site committee ranking view data grid test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeRankingViewDataGridTest()  throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DataGrid));
		assertTrue(userPageVisit.getHtmlBodyAsText().contains("Datagrid"));

	}


	/**
	 * Site admin agency test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminAgencyTest()  throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_AGENCY_VIEW_NAME, ""));
		assertTrue(userPageVisit.getHtmlBodyAsText().contains("Agency"));

		final String pageId = clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_AGENCY_VIEW_NAME, pageId));


	}


	/**
	 * Click first row in grid.
	 *
	 * @param userPageVisit
	 *            the user page visit
	 * @return the string
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	private String clickFirstRowInGrid(final UserPageVisit userPageVisit) throws InterruptedException {
		final List<WebElement> gridRows = userPageVisit.getGridRows();
		assertFalse(gridRows.isEmpty());

		final WebElement choosenRow = gridRows.iterator().next();

		final List<WebElement> cells = choosenRow.findElements(By.className("v-grid-cell"));

		final WebElement choosenCell = cells.iterator().next();

		String pageId = choosenCell.getText();
		userPageVisit.performClickAction(choosenCell);

		if (pageId !=null) {
			pageId=pageId.replace(",", "");
		}

		return pageId;
	}

	/**
	 * Site admin portal test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminPortalTest()  throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_PORTAL_VIEW_NAME, ""));
		assertTrue(userPageVisit.getHtmlBodyAsText().contains("Portal"));

		final String pageId = clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_PORTAL_VIEW_NAME, pageId));


	}

	/**
	 * Site admin country test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminCountryTest()  throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_COUNTRY_VIEW_NAME, ""));
		assertTrue(userPageVisit.getHtmlBodyAsText().contains("Country"));

		final String pageId = clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_COUNTRY_VIEW_NAME, pageId));


	}

	/**
	 * Site admin useraccount test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminUseraccountTest()  throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));
		assertTrue(userPageVisit.getHtmlBodyAsText().contains("Useraccount"));

		final String pageId = clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, pageId));

	}

	/**
	 * Site admin language test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminLanguageTest()  throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME, ""));
		assertTrue(userPageVisit.getHtmlBodyAsText().contains("Language"));

//		String pageId = clickFirstRowInGrid(userPageVisit);
//
//		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME, pageId));


	}

	/**
	 * Site admin language content test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminLanguageContentTest()  throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_CONTENT_VIEW_NAME, ""));
		assertTrue(userPageVisit.getHtmlBodyAsText().contains("Language Content"));

//		String pageId = clickFirstRowInGrid(userPageVisit);
//
//		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_CONTENT_VIEW_NAME, pageId));


	}

	/**
	 * Site admin application session test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminApplicationSessionTest()  throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
		assertTrue(userPageVisit.getHtmlBodyAsText().contains("Application Session"));

		final String pageId = clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, pageId));


	}

	/**
	 * Site admin application event test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminApplicationEventTest()  throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, ""));
		assertTrue(userPageVisit.getHtmlBodyAsText().contains("Application Event"));

		final String pageId = clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, pageId));

	}

	/**
	 * Site admin monitoring test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminMonitoringTest()  throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_MONITORING_VIEW_NAME, ""));
		assertTrue(userPageVisit.getHtmlBodyAsText().contains("Admin Monitoring"));
	}


	/**
	 * Site committee ranking view charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeRankingViewChartsTest()  throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.Charts));

	}

	/**
	 * Site committee ranking view grid navigation test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeRankingViewGridNavigationTest()  throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DataGrid));

		final WebElement button = userPageVisit.getButtons().iterator().next();
		assertNotNull(button);

		userPageVisit.performClickAction(button);

	}

	/**
	 * Site party ranking view overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyRankingViewOverviewTest()  throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Overview));
	}

	/**
	 * Site party ranking view data grid test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyRankingViewDataGridTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.DataGrid));

	}

	/**
	 * Site party ranking view charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyRankingViewChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Charts));

	}

	/**
	 * Site politician ranking view overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePoliticianRankingViewOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.Overview));
	}

	/**
	 * Site politician ranking view data grid test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePoliticianRankingViewDataGridTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DataGrid));

	}

	/**
	 * Site politician ranking view data grid navigation test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePoliticianRankingViewDataGridNavigationTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DataGrid));
		final String pageId = clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, pageId));

	}



	/**
	 * Site politician ranking view charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePoliticianRankingViewChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.Charts));

	}

	/**
	 * Site ministry overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.Overview, "N%C3%A4ringsdepartementet"));
	}

	/**
	 * Site ministry document history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryDocumentHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.DocumentHistory.toString(), "N%C3%A4ringsdepartementet"));

	}

	/**
	 * Site ministry document activity test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryDocumentActivityTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.DocumentActivity.toString(), "N%C3%A4ringsdepartementet"));

	}

	/**
	 * Site ministry role ghant test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryRoleGhantTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.RoleGhant.toString(), "N%C3%A4ringsdepartementet"));

	}


	/**
	 * Site ministry current members test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryCurrentMembersTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.CurrentMembers.toString(), "N%C3%A4ringsdepartementet"));

	}

	/**
	 * Site ministry member history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryMemberHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.MemberHistory.toString(), "N%C3%A4ringsdepartementet"));
	}

	/**
	 * Site committee overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.Overview, "UU"));


	}

	/**
	 * Site committe role ghant test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteRoleGhantTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.RoleGhant.toString(), "UU"));

	}

	/**
	 * Site main view test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMainViewTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,PageMode.Overview));

		final WebElement overviewItem = userPageVisit.getMenuItem("Overview");
		assertNotNull(overviewItem);

		userPageVisit.performClickAction(overviewItem);

	}


	/**
	 * Site register user test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteRegisterUserTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,PageMode.Overview));

		final String username = UUID.randomUUID().toString();
		final String password = UUID.randomUUID().toString();

		userPageVisit.registerNewUser(username,password);

	}

	/**
	 * Site login user test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteLoginUserTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,PageMode.Overview));

		final String username = UUID.randomUUID().toString();
		final String password = UUID.randomUUID().toString();

		userPageVisit.registerNewUser(username,password);

		userPageVisit.logoutUser();

		driver.quit();

		final WebDriver loginDriver = getWebDriver();

		final UserPageVisit userLoginPageVisit = new UserPageVisit(loginDriver, browser);

		userLoginPageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,PageMode.Overview));

		userLoginPageVisit.loginUser(username+ "@test.com", password);

		userLoginPageVisit.logoutUser();

	}



	/**
	 * Site committee document history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeDocumentHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.DOCUMENT_HISTORY.toString(), "UU"));

	}

	/**
	 * Site committee current members test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeCurrentMembersTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.CURRENT_MEMBERS.toString(), "UU"));

	}

	/**
	 * Site committee member history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeMemberHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.MemberHistory.toString(), "UU"));

	}

	/**
	 * Site committee document activity test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeDocumentActivityTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.DocumentActivity.toString(), "UU"));

	}

	/**
	 * Site committee decision summary test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeDecisionSummaryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.DecisionSummary.toString(), "UU"));

	}

	/**
	 * Site committee ballot decision summary test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeBallotDecisionSummaryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.BallotDecisionSummary.toString(), "UU"));

	}

	/**
	 * Site committee decision type daily summary test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeDecisionTypeDailySummaryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DecisionTypeDailySummary.toString(), "UU"));

	}

	/**
	 * Site test chart view overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteTestChartViewOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.Overview));


	}

	/**
	 * Site test chart view indicators test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteTestChartViewIndicatorsTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.Indicators, "UIS.TEP.5.A"));

	}

	/**
	 * Site test chart view party winner test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteTestChartViewPartyWinnerTest() throws Exception{
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.Charts, ChartIndicators.PartyWinner.toString()));

	}

	/**
	 * Site test chart view decsion activity by type test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteTestChartViewDecsionActivityByTypeTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.Charts,
				ChartIndicators.DecsionActivityByType.toString()));

	}

	/**
	 * Site test chart view document activity by type test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteTestChartViewDocumentActivityByTypeTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.Charts,
				ChartIndicators.DocumentActivityByType.toString()));

	}

	/**
	 * Site ministry ranking overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryRankingOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.Overview));

	}

	/**
	 * Site ministry ranking data grid test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryRankingDataGridTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DataGrid));

	}

	/**
	 * Site ministry ranking charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryRankingChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.Charts));

	}

	/**
	 * Site ministry ranking navigation test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryRankingNavigationTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DataGrid));


		final WebElement button = userPageVisit.getButtons().iterator().next();
		assertNotNull(button);

		userPageVisit.performClickAction(button);

	}

	/**
	 * Site document details test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteDocumentDetailsTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.DocumentDetails.toString(), "GZ02C343"));


	}

	/**
	 * Site document over view test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteDocumentOverViewTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.Overview, "GZ02C343"));

	}

	/**
	 * Site document person ref test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteDocumentPersonRefTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.PersonReferences.toString(), "GZ02C343"));

	}

	/**
	 * Site document doc activity test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteDocumentDocActivityTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.DocumentActivity.toString(), "GZ02C343"));

	}

	/**
	 * Site document doc data test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteDocumentDocDataTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.DocumentData.toString(), "GZ02C343"));
	}

	/**
	 * Site document doc refb empty test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteDocumentDocRefbEmptyTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.DocumentReferences.toString(), "GZ02C343"));

	}

	/**
	 * Site document doc refb test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteDocumentDocRefbTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.DocumentReferences.toString(), "H101UU1"));

	}



	/**
	 * Site document doc decision test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteDocumentDocDecisionTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.DocumenDecision.toString(), "GZ02C343"));

	}

	/**
	 * Site document doc attachment test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteDocumentDocAttachmentTest()  throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DocumentAttachments.toString(), "GZ02C343"));

	}

	/**
	 * Site party overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.Overview, "S"));


	}

	/**
	 * Site party document history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyDocumentHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DocumentHistory.toString(), "S"));

	}

	/**
	 * Site party charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.Charts.toString(), "S"));
	}


	/**
	 * Site party current members test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyCurrentMembersTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CurrentMembers.toString(), "S"));

	}

	/**
	 * Site party member history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyMemberHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.MemberHistory.toString(), "S"));

	}

	/**
	 * Site party current leaders test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyCurrentLeadersTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CurrentLeaders.toString(), "S"));

	}

	/**
	 * Site party leader history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyLeaderHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.LeaderHistory.toString(), "S"));

	}

	/**
	 * Site party committee roles test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyCommitteeRolesTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CommitteeRoles.toString(), "S"));

	}

	/**
	 * Site party government roles test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyGovernmentRolesTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.GovernmentRoles.toString(), "S"));

	}

	/**
	 * Site party party won daily summary chart test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyPartyWonDailySummaryChartTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.PartyWonDailySummaryChart.toString(), "S"));

	}

	/**
	 * Site party document activity test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyDocumentActivityTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DocumentActivity.toString(), "S"));

	}

	/**
	 * Site party role ghant test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyRoleGhantTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.RoleGhant.toString(), "S"));

	}


	/**
	 * Site party committee ballot decision summary test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyCommitteeBallotDecisionSummaryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CommitteeBallotDecisionSummary.toString(), "S"));

	}

	/**
	 * Site party vote history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyVoteHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.VoteHistory.toString(), "S"));

	}

	/**
	 * Site admin test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitStartPage();

		userPageVisit.visitAdminAgentOperationView();
		userPageVisit.performAdminAgentOperation(DataAgentTarget.MODEL_EXTERNAL_RIKSDAGEN, DataAgentOperation.IMPORT);

	}

	/**
	 * Site admin data summary.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminDataSummary() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitStartPage();
		userPageVisit.visitAdminDataSummaryView();

	}

	/**
	 * Site politican over view test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePoliticanOverViewTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.Overview, "0980681611418"));
	}

	/**
	 * Site politican role summary test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePoliticanRoleSummaryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.RoleSummary.toString(), "0980681611418"));
	}

	/**
	 * Site politican role list test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePoliticanRoleListTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PoliticianPageMode.RoleList.toString(), "0980681611418"));

	}

	/**
	 * Site politican role ghant test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePoliticanRoleGhantTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PoliticianPageMode.RoleGhant.toString(), "0980681611418"));

	}

	/**
	 * Site politican document history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePoliticanDocumentHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.DocumentHistory.toString(), "0980681611418"));

	}

	/**
	 * Site politican vote history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePoliticanVoteHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.VoteHistory.toString(), "0980681611418"));

	}

	/**
	 * Site politican ballot decision summary test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePoliticanBallotDecisionSummaryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.BallotDecisionSummary.toString(), "0980681611418"));

	}

	/**
	 * Site politican document activity test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePoliticanDocumentActivityTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.DocumentActivity.toString(), "0980681611418"));

	}

	/**
	 * Site politican indicator test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePoliticanIndicatorTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.Indicators.toString(), "0980681611418"));

	}

	/**
	 * Site politican chart test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePoliticanChartTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.Charts.toString(), "0980681611418"));

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
			final HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.FIREFOX_38);
			htmlUnitDriver.setJavascriptEnabled(true);
			driver = htmlUnitDriver;
		} else if (browser.equals("htmlunit-ie11")) {
			final HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_11);
			htmlUnitDriver.setJavascriptEnabled(true);
			driver = htmlUnitDriver;
		} else if (browser.equals("htmlunit-chrome")) {
			final HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
			htmlUnitDriver.setJavascriptEnabled(true);
			driver = htmlUnitDriver;
		} else {
			fail("No valid browser parameter:" + browser);
		}

		webDriverMap.put(browser, driver);
		return driver;
	}

}
