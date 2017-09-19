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
import java.util.UUID;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import com.hack23.cia.service.api.action.application.LoginResponse;
import com.hack23.cia.service.api.action.application.RegisterUserResponse;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ApplicationPageMode;
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
public final class UserRoleSystemTest extends AbstractRoleSystemTest {

	/** The Constant NO_WEBDRIVER_EXIST_FOR_BROWSER. */
	private static final String NO_WEBDRIVER_EXIST_FOR_BROWSER = "No webdriver exist for browser:";

	/**
	 * Instantiates a new user role system test.
	 *
	 * @param browser
	 *            the browser
	 */
	public UserRoleSystemTest(final String browser) {
		super(browser);
	}

	/**
	 * Browsers strings.
	 *
	 * @return the collection
	 */
	@Parameters(name = "UserRoleSiteTest{index}: browser({0})")
	public final static Collection<String[]> browsersStrings() {
		return Arrays.asList(new String[][] { { "chrome" } });
		// return Arrays.asList(new Object[][] { { "firefox" },{ "chrome" }, {
		// "htmlunit-firefox" },{ "htmlunit-ie11" },{ "htmlunit-chrome" } });
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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));

	}

	/**
	 * Site committee ranking view data grid test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeRankingViewDataGridTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DATAGRID));
		assertTrue(userPageVisit.checkHtmlBodyContainsText("Datagrid"));

	}


	/**
	 * Site committee ranking view all committees charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeRankingViewAllCommitteesChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.ALLCOMMITTEESBYHEADCOUNT.toString()));

	}

	/**
	 * Site committee ranking view committee by party charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeRankingViewCommitteeByPartyChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.COMMITTEESBYPARTY.toString()));

	}

	/**
	 * Site committee ranking view current committee parties charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeRankingViewCurrentCommitteePartiesChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTCOMMITTEESBYPARTYDAYSSERVED.toString()));

	}

	/**
	 * Site committee ranking view current committees charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeRankingViewCurrentCommitteesChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTCOMMITTEESBYHEADCOUNT.toString()));

	}


	/**
	 * Site committee ranking view grid navigation test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCommitteeRankingViewGridNavigationTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DATAGRID));

		clickFirstRowInGrid(userPageVisit);
		userPageVisit.validatePage(new
		PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,""));


	}

	/**
	 * Site party ranking view overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyRankingViewOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

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
	public void sitePartyRankingViewPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));
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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.DATAGRID));

	}

	/**
	 * Site party ranking view all parties charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyRankingViewAllPartiesChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.ALLPARTIES.toString()));

	}

	/**
	 * Site party ranking view current committee charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyRankingViewCurrentCommitteeChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTCOMMITTEES.toString()));

	}

	/**
	 * Site party ranking view current government charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyRankingViewCurrentGovernmentChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTGOVERMENTPARTIES.toString()));

	}

	/**
	 * Site party ranking view current parties charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePartyRankingViewCurrentPartiesChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTPARTIES.toString()));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW));
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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));
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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DATAGRID));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DATAGRID));
		clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, ""));

	}

	/**
	 * Site politician ranking view charts all parties test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePoliticianRankingViewChartsAllPartiesTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.ALLPARTIES.toString()));

	}

	/**
	 * Site politician ranking view charts current parties test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sitePoliticianRankingViewChartsCurrentPartiesTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTPARTIES.toString()));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.OVERVIEW, "N%C3%A4ringsdepartementet"));
	}

	/**
	 * Site ministry government body test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryGovernmentBodyTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES.toString(), "N%C3%A4ringsdepartementet"));
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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.PAGEVISITHISTORY,
				"N%C3%A4ringsdepartementet"));
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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.PAGEVISITHISTORY, "UU"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.ROLEGHANT.toString(), "UU"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, PageMode.OVERVIEW));

		final WebElement applicationMenuItem = userPageVisit.getMenuItem("Application");
		assertNotNull(applicationMenuItem);
		userPageVisit.performClickAction(applicationMenuItem);

		Thread.sleep(1000);

		final WebElement overviewMenuItem = userPageVisit.getMenuItem("Start");
		assertNotNull(overviewMenuItem);
		userPageVisit.performClickAction(overviewMenuItem);

	}


	/**
	 * Site main view page visit history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMainViewPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, PageMode.PAGEVISITHISTORY));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		userPageVisit.registerNewUser(username, password);

	}


	/**
	 * Site register user already exist test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteRegisterUserAlreadyExistTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		userPageVisit.registerNewUser(username, password);

		userPageVisit.logoutUser();

		driver.quit();

		final WebDriver loginDriver = getWebDriver();

		final UserPageVisit userRegisterAgainPageVisit = new UserPageVisit(loginDriver, browser);

		userRegisterAgainPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));


		userRegisterAgainPageVisit.registerNewUserCheckView(username, password,"main/"+ApplicationPageMode.REGISTER.toString());

		userRegisterAgainPageVisit.checkNotificationMessage("Register failed:" + RegisterUserResponse.ErrorMessage.USER_ALREADY_EXIST);

	}

	/**
	 * Site register user password weak test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteRegisterUserPasswordWeakTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));

		final String username = UUID.randomUUID().toString();
		final String password = "weak";

		userPageVisit.registerNewUserCheckView(username, password,"main/"+ApplicationPageMode.REGISTER.toString());

		userPageVisit.checkNotificationMessage("Register failed:" + "[Password must be 8 or more characters in length., Password must contain 1 or more uppercase characters., Password must contain 1 or more digit characters., Password must contain 1 or more special characters.]");
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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		userPageVisit.registerNewUser(username, password);

		userPageVisit.logoutUser();

		driver.quit();

		final WebDriver loginDriver = getWebDriver();

		final UserPageVisit userLoginPageVisit = new UserPageVisit(loginDriver, browser);

		userLoginPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGIN.toString()));

		userLoginPageVisit.loginUser(username + "@test.com", password);

		userLoginPageVisit.logoutUser();

	}

	private String generatePassword() {
		final List<CharacterRule> rules = Arrays.asList(
				new CharacterRule(EnglishCharacterData.UpperCase, 1),

				new CharacterRule(EnglishCharacterData.LowerCase, 1),

				new CharacterRule(EnglishCharacterData.Digit, 1),

				new CharacterRule(EnglishCharacterData.Special, 1));

		final PasswordGenerator generator = new PasswordGenerator();

		// Generated password is 12 characters long, which complies with policy
		return generator.generatePassword(12, rules);
	}

	/**
	 * Site login user wrong password test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteLoginUserWrongPasswordTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		userPageVisit.registerNewUser(username, password);

		userPageVisit.logoutUser();

		driver.quit();

		final WebDriver loginDriver = getWebDriver();

		final UserPageVisit userLoginPageVisit = new UserPageVisit(loginDriver, browser);

		userLoginPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGIN.toString()));

		userLoginPageVisit.loginUserCheckView(username + "@test.com", "wrongpassword","main/" + ApplicationPageMode.LOGIN);

		userLoginPageVisit.checkNotificationMessage("Login failed:" + LoginResponse.ErrorMessage.USERNAME_OR_PASSWORD_DO_NOT_MATCH);

	}


	/**
	 * Site login user enable google authenticator test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteLoginUserEnableGoogleAuthenticatorTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		userPageVisit.registerNewUser(username, password);

		userPageVisit.logoutUser();

		driver.quit();

		final WebDriver loginDriver = getWebDriver();

		final UserPageVisit userLoginPageVisit = new UserPageVisit(loginDriver, browser);

		userLoginPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGIN.toString()));

		userLoginPageVisit.loginUser(username + "@test.com", password);


		final WebElement userAccountMenuItem = userLoginPageVisit.getMenuItem("Useraccount");
		assertNotNull(userAccountMenuItem);
		userLoginPageVisit.performClickAction(userAccountMenuItem);

		Thread.sleep(1000);


		final WebElement securitySettingMenuItem = userLoginPageVisit.getMenuItem("Security settings");
		assertNotNull(securitySettingMenuItem);
		userLoginPageVisit.performClickAction(securitySettingMenuItem);


		userLoginPageVisit.enableGoogleAuthenticator();

		userLoginPageVisit.logoutUser();

	}


	/**
	 * Site login user check user visits test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteLoginUserCheckUserVisitsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		userPageVisit.registerNewUser(username, password);

		userPageVisit.logoutUser();

		driver.quit();

		final WebDriver loginDriver = getWebDriver();

		final UserPageVisit userLoginPageVisit = new UserPageVisit(loginDriver, browser);

		userLoginPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGIN.toString()));

		userLoginPageVisit.loginUser(username + "@test.com", password);


		final WebElement useraccountMenuItem = userLoginPageVisit.getMenuItem("Useraccount");
		assertNotNull(useraccountMenuItem);
		userLoginPageVisit.performClickAction(useraccountMenuItem);

		Thread.sleep(1000);


		final WebElement userVisitsMenuItem = userLoginPageVisit.getMenuItem("User Visits");
		assertNotNull(userVisitsMenuItem);
		userLoginPageVisit.performClickAction(userVisitsMenuItem);

		userLoginPageVisit.logoutUser();

	}

	/**
	 * Site login user check user events test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteLoginUserCheckUserEventsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		userPageVisit.registerNewUser(username, password);

		userPageVisit.logoutUser();

		driver.quit();

		final WebDriver loginDriver = getWebDriver();

		final UserPageVisit userLoginPageVisit = new UserPageVisit(loginDriver, browser);

		userLoginPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGIN.toString()));

		userLoginPageVisit.loginUser(username + "@test.com", password);


		final WebElement useraccountMenuItem = userLoginPageVisit.getMenuItem("Useraccount");
		assertNotNull(useraccountMenuItem);
		userLoginPageVisit.performClickAction(useraccountMenuItem);

		Thread.sleep(1000);


		final WebElement userEventsMenuItem = userLoginPageVisit.getMenuItem("User Events");
		assertNotNull(userEventsMenuItem);
		userLoginPageVisit.performClickAction(userEventsMenuItem);

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DOCUMENT_HISTORY.toString(), "UU"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.CURRENT_MEMBERS.toString(), "UU"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.MEMBERHISTORY.toString(), "UU"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DOCUMENTACTIVITY.toString(), "UU"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DECISIONSUMMARY.toString(), "UU"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.BALLOTDECISIONSUMMARY.toString(), "UU"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DECISIONTYPEDAILYSUMMARY.toString(), "UU"));

	}

	/**
	 * Site test parliament overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteTestParliamentOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.OVERVIEW));
	}

	/**
	 * Site test country ranking overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteTestCountryRankingOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW));
	}



	/**
	 * Site country ranking view indicators test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteCountryRankingViewIndicatorsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.INDICATORS, "UIS.TEP.5.A"));

	}

	/**
	 * Site parliament chart view party winner test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteParliamentChartViewPartyWinnerTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS,
				ChartIndicators.PARTYWINNER.toString()));

	}

	/**
	 * Site parliament chart view party gender test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteParliamentChartViewPartyGenderTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS,
				ChartIndicators.PARTYGENDER.toString()));

	}

	/**
	 * Site parliament chart view party age test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteParliamentChartViewPartyAgeTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS,
				ChartIndicators.PARTYAGE.toString()));

	}


	/**
	 * Site parliament chart view decsion activity by type test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteParliamentChartViewDecsionActivityByTypeTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS,
				ChartIndicators.DECISIONACTIVITYBYTYPE.toString()));

	}

	/**
	 * Site parliament chart view document activity by type test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteParliamentChartViewDocumentActivityByTypeTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS,
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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW));

	}

	/**
	 * Site ministry ranking government body test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryRankingGovernmentBodyTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES.toString()));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DATAGRID));

	}

	/**
	 * Site ministry ranking all ministries charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryRankingAllMinistriesChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.ALLMINISTRIESBYHEADCOUNT.toString()));

	}

	/**
	 * Site ministry ranking all ministries by party charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryRankingAllMinistriesByPartyChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.ALLMINISTRIESPARTYBYTOTALDAYS.toString()));

	}


	/**
	 * Site ministry ranking all roles charts charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryRankingAllRolesChartsChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.ALL_GOVERNMENT_ROLE_CHART.toString()));

	}



	/**
	 * Site ministry ranking current parties charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryRankingCurrentPartiesChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTPARTIESBYHEADCOUNT.toString()));

	}

	/**
	 * Site ministry ranking current ministries charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteMinistryRankingCurrentMinistriesChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTMINISTRIESBYHEADCOUNT.toString()));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DATAGRID));

		clickFirstRowInGrid(userPageVisit);
		userPageVisit.validatePage(new
		PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,""));

	}



	/**
	 * Site ballot overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteBallotOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME,"A411DA4A-430F-408A-99BE-3539E0E2D82A"));

		assertTrue(userPageVisit.checkHtmlBodyContainsText("Ballot"));

	}

	/**
	 * Site ballot overview with decision summary test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteBallotOverviewWithDecisionSummaryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME,"371B8030-1B48-4E9B-A177-A16CCE74F079"));

		assertTrue(userPageVisit.checkHtmlBodyContainsText("Ballot"));

	}

	/**
	 * Site ballot chart test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteBallotChartTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME,PageMode.CHARTS,"F6E3920D-429A-4438-B22A-5E1AF3A72CEE"));

		assertTrue(userPageVisit.checkHtmlBodyContainsText("Ballot"));

	}


	/**
	 * Site ballot overview multiple votes test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteBallotOverviewMultipleVotesTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME,"A1A613C2-D942-4D5D-AC29-4AE3C4B57486"));

		assertTrue(userPageVisit.checkHtmlBodyContainsText("Ballot"));

	}

	/**
	 * Site ballot chart multiple votes test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteBallotChartMultipleVotesTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME,PageMode.CHARTS,"A1A613C2-D942-4D5D-AC29-4AE3C4B57486"));

		assertTrue(userPageVisit.checkHtmlBodyContainsText("Ballot"));

	}

	/**
	 * Site documents overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteDocumentsOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENTS_VIEW_NAME,PageMode.OVERVIEW));
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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTDETAILS.toString(), "GZ02C343"));
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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.OVERVIEW, "GZ02C343"));
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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.PAGEVISITHISTORY, "GZ02C343"));
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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.PERSONREFERENCES.toString(), "GZ02C343"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.SEARCH_DOCUMENT_VIEW_NAME, ""));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTACTIVITY.toString(), "GZ02C343"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTDATA.toString(), "GZ02C343"));
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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTREFERENCES.toString(), "GZ02C343"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTREFERENCES.toString(), "H101UU1"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTDECISION.toString(), "GZ02C343"));

	}

	/**
	 * Site document doc attachment test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteDocumentDocAttachmentTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.PAGEVISITHISTORY, "S"));
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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DOCUMENTHISTORY.toString(), "S"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CURRENTMEMBERS.toString(), "S"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.MEMBERHISTORY.toString(), "S"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CURRENTLEADERS.toString(), "S"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.LEADERHISTORY.toString(), "S"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.COMMITTEEROLES.toString(), "S"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.PARTYWONDAILYSUMMARYCHART.toString(), "S"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.COMMITTEEBALLOTDECISIONSUMMARY.toString(), "S"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.VOTEHISTORY.toString(), "S"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.OVERVIEW, "0980681611418"));
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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.PAGEVISITHISTORY, "0980681611418"));
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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.ROLELIST.toString(), "0980681611418"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.ROLEGHANT.toString(), "0980681611418"));

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

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
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PageMode.INDICATORS.toString(), "0980681611418"));

	}

}
