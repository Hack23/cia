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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public final class UserRoleSystemTest extends AbstractSystemIntegrationTest {

	/** The test server. */
	private static CitizenIntelligenceAgencyServer testServer;

	/** The browser. */
	private final String browser;

	/** The web driver map. */
	private final Map<String, WebDriver> webDriverMap = new ConcurrentHashMap<>();


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
		return Arrays.asList(new String[][] { { "firefox" }});
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
	static synchronized public void startServer() throws Exception {
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
	static synchronized public void stopServer() throws Exception {
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
				.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.OVERVIEW));

	}

	/**
	 * Site committee ranking view page visit history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeRankingViewPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));

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
				.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DATAGRID));
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

		loginAsAdmin(userPageVisit);

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
		loginAsAdmin(userPageVisit);


		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_PORTAL_VIEW_NAME, ""));
		assertTrue(userPageVisit.getHtmlBodyAsText().contains("Portal"));

		final String pageId = clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_PORTAL_VIEW_NAME, pageId));


	}


	/**
	 * Site admin application configuration test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminApplicationConfigurationTest()  throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);


		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, ""));
		assertTrue(userPageVisit.getHtmlBodyAsText().contains("Application Configuration"));

		final String pageId = clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, pageId));


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
		loginAsAdmin(userPageVisit);


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
		loginAsAdmin(userPageVisit);


		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));
		userPageVisit.assertHtmlBodyContainsText("Useraccount");

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
		loginAsAdmin(userPageVisit);


		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME, ""));
		assertTrue(userPageVisit.getHtmlBodyAsText().contains("Language"));

//		String pageId = clickFirstRowInGrid(userPageVisit);
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
		loginAsAdmin(userPageVisit);


		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_CONTENT_VIEW_NAME, ""));
		assertTrue(userPageVisit.getHtmlBodyAsText().contains("Language Content"));

//		String pageId = clickFirstRowInGrid(userPageVisit);
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
		loginAsAdmin(userPageVisit);


		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
		userPageVisit.assertHtmlBodyContainsText("Application Session");

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
		loginAsAdmin(userPageVisit);


		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, ""));
		userPageVisit.assertHtmlBodyContainsText("Application Action Event");

		final String pageId = clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, pageId));

	}

	/**
	 * Site admin monitoring failed access test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout=20000)
	public void siteAdminMonitoringFailedAccessTest()  throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_MONITORING_VIEW_NAME, ""));
		assertTrue("Expect this content",userPageVisit.getHtmlBodyAsText().contains("Admin Monitoring"));

		assertTrue("Expect this content",userPageVisit.getIframesHtmlBodyAsText().contains("Login with Username and Password"));
	}

	/**
	 * Site admin monitoring success test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminMonitoringSuccessTest()  throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_MONITORING_VIEW_NAME, ""));
		assertTrue("Expect this content",userPageVisit.getHtmlBodyAsText().contains("Admin Monitoring"));

		assertFalse("Dont expect this content",userPageVisit.getIframesHtmlBodyAsText().contains("Login with Username and Password"));
	}


	/**
	 * Login as admin.
	 *
	 * @param userPageVisit
	 *            the user page visit
	 * @throws Exception
	 *             the exception
	 */
	private void loginAsAdmin(final UserPageVisit userPageVisit) throws Exception {
		userPageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,PageMode.OVERVIEW));
		userPageVisit.loginUser("admin@admin.com", "admin");
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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS));

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
				.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DATAGRID));

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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.OVERVIEW));
	}


	/**
	 * Site party ranking view page visit history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyRankingViewPageVisitHistoryTest()  throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));
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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.DATAGRID));

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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS));

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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW));
	}

	/**
	 * Site politician ranking view page visit history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePoliticianRankingViewPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));
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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DATAGRID));

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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DATAGRID));
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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.CHARTS));

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
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.OVERVIEW, "N%C3%A4ringsdepartementet"));
	}

	/**
	 * Site ministry page visit history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.PAGEVISITHISTORY, "N%C3%A4ringsdepartementet"));
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
				MinistryPageMode.DOCUMENTHISTORY.toString(), "N%C3%A4ringsdepartementet"));

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
				MinistryPageMode.DOCUMENTACTIVITY.toString(), "N%C3%A4ringsdepartementet"));

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
				MinistryPageMode.ROLEGHANT.toString(), "N%C3%A4ringsdepartementet"));

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
				MinistryPageMode.CURRENTMEMBERS.toString(), "N%C3%A4ringsdepartementet"));

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
				MinistryPageMode.MEMBERHISTORY.toString(), "N%C3%A4ringsdepartementet"));
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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.OVERVIEW, "UU"));


	}

	/**
	 * Site committee page visit history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteePageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.PAGEVISITHISTORY, "UU"));


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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.ROLEGHANT.toString(), "UU"));

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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,PageMode.OVERVIEW));

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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,PageMode.OVERVIEW));

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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,PageMode.OVERVIEW));

		final String username = UUID.randomUUID().toString();
		final String password = UUID.randomUUID().toString();

		userPageVisit.registerNewUser(username,password);

		userPageVisit.logoutUser();

		driver.quit();

		final WebDriver loginDriver = getWebDriver();

		final UserPageVisit userLoginPageVisit = new UserPageVisit(loginDriver, browser);

		userLoginPageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,PageMode.OVERVIEW));

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
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.MEMBERHISTORY.toString(), "UU"));

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
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.DOCUMENTACTIVITY.toString(), "UU"));

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
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.DECISIONSUMMARY.toString(), "UU"));

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
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.BALLOTDECISIONSUMMARY.toString(), "UU"));

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
				CommitteePageMode.DECISIONTYPEDAILYSUMMARY.toString(), "UU"));

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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.OVERVIEW));


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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.INDICATORS, "UIS.TEP.5.A"));

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
				new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.CHARTS, ChartIndicators.PARTYWINNER.toString()));

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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.CHARTS,
				ChartIndicators.DECSIONACTIVITYBYTYPE.toString()));

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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.CHARTS,
				ChartIndicators.DOCUMENTACTIVITYBYTYPE.toString()));

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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW));

	}

	/**
	 * Site ministry ranking page visit history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryRankingPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));

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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DATAGRID));

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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS));

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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DATAGRID));


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
				new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.DOCUMENTDETAILS.toString(), "GZ02C343"));


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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.OVERVIEW, "GZ02C343"));
	}

	/**
	 * Site document page visit history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteDocumentPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.PAGEVISITHISTORY, "GZ02C343"));
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
				new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.PERSONREFERENCES.toString(), "GZ02C343"));

	}

	/**
	 * Site search document test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteSearchDocumentTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.SEARCH_DOCUMENT_VIEW_NAME,""));

		userPageVisit.searchDocument("2016");

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
				new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.DOCUMENTACTIVITY.toString(), "GZ02C343"));

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
				new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.DOCUMENTDATA.toString(), "GZ02C343"));
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
				new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.DOCUMENTREFERENCES.toString(), "GZ02C343"));

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
				new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.DOCUMENTREFERENCES.toString(), "H101UU1"));

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
				new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.DOCUMENDECISION.toString(), "GZ02C343"));

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
				DocumentPageMode.DOCUMENTATTACHMENTS.toString(), "GZ02C343"));

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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.OVERVIEW, "S"));
	}

	/**
	 * Site party page visit history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.PAGEVISITHISTORY, "S"));
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
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DOCUMENTHISTORY.toString(), "S"));

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
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.CHARTS.toString(), "S"));
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
				.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CURRENTMEMBERS.toString(), "S"));

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
				.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.MEMBERHISTORY.toString(), "S"));

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
				.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CURRENTLEADERS.toString(), "S"));

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
				.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.LEADERHISTORY.toString(), "S"));

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
				.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.COMMITTEEROLES.toString(), "S"));

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
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.GOVERNMENTROLES.toString(), "S"));

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
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.PARTYWONDAILYSUMMARYCHART.toString(), "S"));

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
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DOCUMENTACTIVITY.toString(), "S"));

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
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.ROLEGHANT.toString(), "S"));

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
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.COMMITTEEBALLOTDECISIONSUMMARY.toString(), "S"));

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
				.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.VOTEHISTORY.toString(), "S"));

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

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,PageMode.OVERVIEW));

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

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,PageMode.OVERVIEW));

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

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.OVERVIEW, "0980681611418"));
	}


	/**
	 * Site politican page visit history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePoliticanPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.PAGEVISITHISTORY, "0980681611418"));
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
				PoliticianPageMode.ROLESUMMARY.toString(), "0980681611418"));
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
				new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PoliticianPageMode.ROLELIST.toString(), "0980681611418"));

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
				new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PoliticianPageMode.ROLEGHANT.toString(), "0980681611418"));

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
				PoliticianPageMode.DOCUMENTHISTORY.toString(), "0980681611418"));

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
				PoliticianPageMode.VOTEHISTORY.toString(), "0980681611418"));

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
				PoliticianPageMode.BALLOTDECISIONSUMMARY.toString(), "0980681611418"));

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
				PoliticianPageMode.DOCUMENTACTIVITY.toString(), "0980681611418"));

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
				new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.INDICATORS.toString(), "0980681611418"));

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
				new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.CHARTS.toString(), "0980681611418"));

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
		if ("firefox".equals(browser)) {
			driver = new FirefoxDriver();
		} else if ("chrome".equals(browser)) {
			driver = new ChromeDriver();
		} else if ("htmlunit-firefox".equals(browser)) {
			final HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.FIREFOX_38);
			htmlUnitDriver.setJavascriptEnabled(true);
			driver = htmlUnitDriver;
		} else if ("htmlunit-ie11".equals(browser)) {
			final HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_11);
			htmlUnitDriver.setJavascriptEnabled(true);
			driver = htmlUnitDriver;
		} else if ("htmlunit-edge".equals(browser)) {
			final HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.EDGE);
			htmlUnitDriver.setJavascriptEnabled(true);
			driver = htmlUnitDriver;
		} else if ("htmlunit-chrome".equals(browser)) {
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
