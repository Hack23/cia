/*
 * Copyright 2010-2024 James Pether Sörling
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
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.GovernmentBodyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.MinistryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PartyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PoliticianPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.RiskIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserHomePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Class UserRoleSystemITest.
 */
public final class UserRoleSystemITest extends AbstractRoleSystemITest {

	/** The Constant NO_WEBDRIVER_EXIST_FOR_BROWSER. */
	private static final String NO_WEBDRIVER_EXIST_FOR_BROWSER = "No webdriver exist for browser:";

	/**
	 * Instantiates a new user role system test.
	 *
	 * @param browser
	 *            the browser
	 */
	public UserRoleSystemITest(final String browser) {
		super(browser);
	}

	/**
	 * Browsers strings.
	 *
	 * @return the collection
	 */
	@Parameters(name = "UserRoleSiteTest{index}: browser({0})")
	public static Collection<String[]> browsersStrings() {
		return Arrays.asList(new String[][] { { "chrome" } });
		// return Arrays.asList(new Object[][] { { "firefox" },{ "chrome" }, {
		// "htmlunit-firefox" },{ "htmlunit-ie11" },{ "htmlunit-chrome" } });
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
	 * Site ballot chart multiple votes test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteBallotChartMultipleVotesTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME,PageMode.CHARTS,"A1A613C2-D942-4D5D-AC29-4AE3C4B57486"));

		assertTrue(userPageVisit.checkHtmlBodyContainsText("Ballot"));

	}

	/**
	 * Site ballot chart test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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
	@Test(timeout = 60000)
	public void siteBallotOverviewMultipleVotesTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME,"A1A613C2-D942-4D5D-AC29-4AE3C4B57486"));

		assertTrue(userPageVisit.checkHtmlBodyContainsText("Ballot"));

	}

	/**
	 * Site ballot overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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
	@Test(timeout = 60000)
	public void siteBallotOverviewWithDecisionSummaryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME,"38736D18-7025-4EFE-ABC7-0D22E4AE8C11"));

		assertTrue(userPageVisit.checkHtmlBodyContainsText("Ballot"));

	}

	/**
	 * Site committe decision flow select year test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteCommitteDecisionFlowSelectYearTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.CHARTS +"/"+
						ChartIndicators.DECISION_FLOW_CHART.toString(), "UU[2016/17]"));

	}


	/**
	 * Site committe decision flow test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteCommitteDecisionFlowTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.CHARTS +"/"+
						ChartIndicators.DECISION_FLOW_CHART.toString(), "UU"));

	}

	/**
	 * Site committee ballot decision summary test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteCommitteeBallotDecisionSummaryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.BALLOTDECISIONSUMMARY.toString(), "UU"));

	}

	/**
	 * Site committee current members test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteCommitteeCurrentMembersTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.CURRENT_MEMBERS.toString(), "UU"));

	}

	/**
	 * Site committee decision summary test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteCommitteeDecisionSummaryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DECISIONSUMMARY.toString(), "UU"));

	}

	/**
	 * Site committee decision type daily summary test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteCommitteeDecisionTypeDailySummaryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DECISIONTYPEDAILYSUMMARY.toString(), "UU"));

	}

	/**
	 * Site committee document activity test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteCommitteeDocumentActivityTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DOCUMENTACTIVITY.toString(), "UU"));

	}

	/**
	 * Site committee document history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteCommitteeDocumentHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DOCUMENT_HISTORY.toString(), "UU"));

	}

	/**
	 * Site committee member history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteCommitteeMemberHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.MEMBERHISTORY.toString(), "UU"));

	}

	/**
	 * Site committee overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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
	@Test(timeout = 60000)
	public void siteCommitteePageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.PAGEVISITHISTORY, "UU"));

	}

	/**
	 * Site committee ranking view all committees charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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
	@Test(timeout = 60000)
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
	@Test(timeout = 60000)
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
	@Test(timeout = 60000)
	public void siteCommitteeRankingViewCurrentCommitteesChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTCOMMITTEESBYHEADCOUNT.toString()));

	}


	/**
	 * Site committee ranking view data grid test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteCommitteeRankingViewDataGridTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DATAGRID));
		assertTrue(userPageVisit.checkHtmlBodyContainsText("Committee Data Grid"));

	}


	/**
	 * Site committee ranking view grid navigation test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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
	 * Site committee ranking view overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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
	@Test(timeout = 60000)
	public void siteCommitteeRankingViewPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));

	}


	/**
	 * Site committe role ghant test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteCommitteRoleGhantTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.ROLEGHANT.toString(), "UU"));

	}

	/**
	 * Site country ranking view indicators test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteCountryRankingViewIndicatorsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.INDICATORS, "IT.NET.SECR.P6"));

	}

	/**
	 * Site document details test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteDocumentDetailsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTDETAILS.toString(), "gz02c343"));
	}


	/**
	 * Site document doc activity test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteDocumentDocActivityTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTACTIVITY.toString(), "gz02c343"));

	}

	/**
	 * Site document doc attachment pdf test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteDocumentDocAttachmentPdfTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTATTACHMENTS.toString(), "h5024180"));

	}

	/**
	 * Site document doc attachment test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteDocumentDocAttachmentTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTATTACHMENTS.toString(), "gz02c343"));

	}

	/**
	 * Site document doc data test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteDocumentDocDataTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTDATA.toString(), "gz02c343"));
	}

	/**
	 * Site document doc decision test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteDocumentDocDecisionTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTDECISION.toString(), "gz02c343"));

	}

	/**
	 * Site document doc refb empty test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteDocumentDocRefbEmptyTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTREFERENCES.toString(), "gz02c343"));

	}

	/**
	 * Site document doc refb test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteDocumentDocRefbTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTREFERENCES.toString(), "h701nu6"));

	}


	/**
	 * Site document over view test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteDocumentOverViewTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.OVERVIEW, "gz02c343"));
	}


	/**
	 * Site document page visit history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteDocumentPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.PAGEVISITHISTORY, "gz02c343"));
	}

	/**
	 * Site document person ref test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteDocumentPersonRefTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.PERSONREFERENCES.toString(), "gz02c343"));

	}

	/**
	 * Site documents click last page test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteDocumentsClickLastPageTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENTS_VIEW_NAME,PageMode.OVERVIEW));
		final WebElement nextPageButton = userPageVisit.findButton("last page");
		userPageVisit.performClickAction(nextPageButton);
	}


	/**
	 * Site documents click next test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteDocumentsClickNextTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENTS_VIEW_NAME,PageMode.OVERVIEW));
		final WebElement nextPageButton = userPageVisit.findButton("next page");
		userPageVisit.performClickAction(nextPageButton);

	}

	/**
	 * Site documents overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteDocumentsOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENTS_VIEW_NAME,PageMode.OVERVIEW));
	}


	/**
	 * Site government body expenditure invalid reference test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteGovernmentBodyExpenditureInvalidReferenceTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_VIEW_NAME, GovernmentBodyPageMode.EXPENDITURE.toString(), "InvalidReference"));
		userPageVisit.checkHtmlBodyContainsText("Invalid reference, content not found");
	}

	/**
	 * Site government body expenditure test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteGovernmentBodyExpenditureTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_VIEW_NAME, GovernmentBodyPageMode.EXPENDITURE.toString(), "202100-5026"));
	}

	/**
	 * Site government body headcount invalid reference test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteGovernmentBodyHeadcountInvalidReferenceTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_VIEW_NAME, GovernmentBodyPageMode.HEADCOUNT.toString(), "InvalidReference"));
		userPageVisit.checkHtmlBodyContainsText("Invalid reference, content not found");
	}

	/**
	 * Site government body headcount test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteGovernmentBodyHeadcountTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_VIEW_NAME, GovernmentBodyPageMode.HEADCOUNT.toString(), "202100-5026"));
	}

	/**
	 * Site government body income invalid reference test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteGovernmentBodyIncomeInvalidReferenceTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_VIEW_NAME, GovernmentBodyPageMode.INCOME.toString(), "InvalidReference"));
		userPageVisit.checkHtmlBodyContainsText("Invalid reference, content not found");
	}

	/**
	 * Site government body income test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteGovernmentBodyIncomeTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_VIEW_NAME, GovernmentBodyPageMode.INCOME.toString(), "202100-5026"));
	}


	/**
	 * Site government body overview invalid reference test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteGovernmentBodyOverviewInvalidReferenceTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_VIEW_NAME, PageMode.OVERVIEW, "InvalidReference"));
		userPageVisit.checkHtmlBodyContainsText("Invalid reference, content not found");
	}


	/**
	 * Site government body overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteGovernmentBodyOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_VIEW_NAME, PageMode.OVERVIEW, "202100-5026"));
	}

	/**
	 * Site government body page visit history test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteGovernmentBodyPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_VIEW_NAME, PageMode.PAGEVISITHISTORY, "202100-5026"));
	}

	/**
	 * Site government body ranking datagrid test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteGovernmentBodyRankingDatagridTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.DATAGRID));

	}

	/**
	 * Site government body ranking expenditure test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteGovernmentBodyRankingExpenditureTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, GovernmentBodyPageMode.EXPENDITURE.toString()));
	}

	/**
	 * Site government body ranking head count test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteGovernmentBodyRankingHeadCountTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, GovernmentBodyPageMode.HEADCOUNT.toString()));
	}


	/**
	 * Site government body ranking income test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteGovernmentBodyRankingIncomeTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, GovernmentBodyPageMode.INCOME.toString()));
	}


	/**
	 * Site government body ranking overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteGovernmentBodyRankingOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.OVERVIEW));

	}

	/**
	 * Site government body ranking page visit history test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteGovernmentBodyRankingPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));

	}


	/**
	 * Site login user change password failure test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteLoginUserChangePasswordFailureTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		userPageVisit.registerNewUser(username, password);

		final WebElement userAccountMenuItem = userPageVisit.getMenuItem("Useraccount");
		assertNotNull(userAccountMenuItem);
		userPageVisit.performClickAction(userAccountMenuItem);

		final WebElement securitySettingMenuItem = userPageVisit.getMenuItem("Security settings");
		assertNotNull(securitySettingMenuItem);
		userPageVisit.performClickAction(securitySettingMenuItem);

		userPageVisit.changePassword("wrong" + password,"new"+ password,"new" + password);

		userPageVisit.checkNotificationMessage("Problem changing password");
	}

	/**
	 * Site login user change password test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteLoginUserChangePasswordTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		userPageVisit.registerNewUser(username, password);


		final WebElement userAccountMenuItem = userPageVisit.getMenuItem("Useraccount");
		assertNotNull(userAccountMenuItem);
		userPageVisit.performClickAction(userAccountMenuItem);

		final WebElement securitySettingMenuItem = userPageVisit.getMenuItem("Security settings");
		assertNotNull(securitySettingMenuItem);
		userPageVisit.performClickAction(securitySettingMenuItem);

		userPageVisit.changePassword(password,"new"+ password,"new" + password);
	}


	/**
	 * Site login user check user events test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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

		final UserPageVisit userLoginPageVisit = new UserPageVisit(driver, browser);

		userLoginPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGIN.toString()));

		userLoginPageVisit.loginUser(username + "@test.com", password);


		final WebElement useraccountMenuItem = userLoginPageVisit.getMenuItem("Useraccount");
		assertNotNull(useraccountMenuItem);
		userLoginPageVisit.performClickAction(useraccountMenuItem);

		final WebElement userEventsMenuItem = userLoginPageVisit.getMenuItem("User Events");
		assertNotNull(userEventsMenuItem);
		userLoginPageVisit.performClickAction(userEventsMenuItem);
	}

	/**
	 * Site login user check user visits test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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

		final UserPageVisit userLoginPageVisit = new UserPageVisit(driver, browser);

		userLoginPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGIN.toString()));

		userLoginPageVisit.loginUser(username + "@test.com", password);


		final WebElement useraccountMenuItem = userLoginPageVisit.getMenuItem("Useraccount");
		assertNotNull(useraccountMenuItem);
		userLoginPageVisit.performClickAction(useraccountMenuItem);

		final WebElement userVisitsMenuItem = userLoginPageVisit.getMenuItem("User Visits");
		assertNotNull(userVisitsMenuItem);
		userLoginPageVisit.performClickAction(userVisitsMenuItem);
	}

	@Test(timeout = 60000)
	public void siteLoginUserDisableGoogleAuthenticatorFailureTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		userPageVisit.registerNewUser(username, password);

		final WebElement userAccountMenuItem = userPageVisit.getMenuItem("Useraccount");
		assertNotNull(userAccountMenuItem);
		userPageVisit.performClickAction(userAccountMenuItem);

		final WebElement securitySettingMenuItem = userPageVisit.getMenuItem("Security settings");
		assertNotNull(securitySettingMenuItem);
		userPageVisit.performClickAction(securitySettingMenuItem);

		userPageVisit.disableGoogleAuthenticator("wrong" + password);

		userPageVisit.checkNotificationMessage("Problem disable google authenticatorError message");
	}

	@Test(timeout = 60000)
	public void siteLoginUserDisableGoogleAuthenticatorTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		userPageVisit.registerNewUser(username, password);


		final WebElement userAccountMenuItem = userPageVisit.getMenuItem("Useraccount");
		assertNotNull(userAccountMenuItem);
		userPageVisit.performClickAction(userAccountMenuItem);

		final WebElement securitySettingMenuItem = userPageVisit.getMenuItem("Security settings");
		assertNotNull(securitySettingMenuItem);
		userPageVisit.performClickAction(securitySettingMenuItem);

		userPageVisit.disableGoogleAuthenticator(password);

	}

	@Test(timeout = 60000)
	public void siteLoginUserEnableGoogleAuthenticatorFailedLoginNoOtpTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		userPageVisit.registerNewUser(username, password);

		final WebElement userAccountMenuItem = userPageVisit.getMenuItem("Useraccount");
		assertNotNull(userAccountMenuItem);
		userPageVisit.performClickAction(userAccountMenuItem);

		final WebElement securitySettingMenuItem = userPageVisit.getMenuItem("Security settings");
		assertNotNull(securitySettingMenuItem);
		userPageVisit.performClickAction(securitySettingMenuItem);


		userPageVisit.enableGoogleAuthenticator(password);

		userPageVisit.closeModal();

		userPageVisit.logoutUser();

		final UserPageVisit failedLoginWrongMfaVisit = new UserPageVisit(driver, browser);

		failedLoginWrongMfaVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGIN.toString()));

		failedLoginWrongMfaVisit.loginUserCheckView(username + "@test.com", password,"123456","main/" + ApplicationPageMode.LOGIN);

		failedLoginWrongMfaVisit.checkNotificationMessage("Login failed:" + LoginResponse.ErrorMessage.USERNAME_OR_PASSWORD_DO_NOT_MATCH);
	}

	@Test(timeout = 60000)
	public void siteLoginDeleteAccountTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		userPageVisit.registerNewUser(username, password);

		final WebElement userAccountMenuItem = userPageVisit.getMenuItem("Useraccount");
		assertNotNull(userAccountMenuItem);
		userPageVisit.performClickAction(userAccountMenuItem);

		final WebElement securitySettingMenuItem = userPageVisit.getMenuItem("Security settings");
		assertNotNull(securitySettingMenuItem);
		userPageVisit.performClickAction(securitySettingMenuItem);


		userPageVisit.deleteAccount(password);

		final UserPageVisit failedLoginUserDeletedVisit = new UserPageVisit(driver, browser);

		failedLoginUserDeletedVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGIN.toString()));

		failedLoginUserDeletedVisit.loginUserCheckView(username + "@test.com", password,"123456","main/" + ApplicationPageMode.LOGIN);

		failedLoginUserDeletedVisit.checkNotificationMessage("Login failed:" + LoginResponse.ErrorMessage.USERNAME_OR_PASSWORD_DO_NOT_MATCH);
	}

	@Test(timeout = 60000)
	public void siteLoginUserEnableGoogleAuthenticatorFailureTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		userPageVisit.registerNewUser(username, password);

		final WebElement userAccountMenuItem = userPageVisit.getMenuItem("Useraccount");
		assertNotNull(userAccountMenuItem);
		userPageVisit.performClickAction(userAccountMenuItem);


		final WebElement securitySettingMenuItem = userPageVisit.getMenuItem("Security settings");
		assertNotNull(securitySettingMenuItem);
		userPageVisit.performClickAction(securitySettingMenuItem);


		userPageVisit.enableGoogleAuthenticator("wrong" + password);

		userPageVisit.checkNotificationMessage("Problem enable google authenticatorError message");
	}


	/**
	 * Site login user enable google authenticator test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteLoginUserEnableGoogleAuthenticatorTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		userPageVisit.registerNewUser(username, password);


		final WebElement userAccountMenuItem = userPageVisit.getMenuItem("Useraccount");
		assertNotNull(userAccountMenuItem);
		userPageVisit.performClickAction(userAccountMenuItem);

		final WebElement securitySettingMenuItem = userPageVisit.getMenuItem("Security settings");
		assertNotNull(securitySettingMenuItem);
		userPageVisit.performClickAction(securitySettingMenuItem);

		userPageVisit.enableGoogleAuthenticator(password);

		userPageVisit.closeModal();
	}


	/**
	 * Site login user test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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

		final UserPageVisit userLoginPageVisit = new UserPageVisit(driver, browser);

		userLoginPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGIN.toString()));

		userLoginPageVisit.loginUser(username + "@test.com", password);
	}


	/**
	 * Site login user wrong password test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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

		final UserPageVisit userLoginPageVisit = new UserPageVisit(driver, browser);

		userLoginPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGIN.toString()));

		userLoginPageVisit.loginUserCheckView(username + "@test.com", "wrongpassword","main/" + ApplicationPageMode.LOGIN);

		userLoginPageVisit.checkNotificationMessage("Login failed:" + LoginResponse.ErrorMessage.USERNAME_OR_PASSWORD_DO_NOT_MATCH);

	}


	/**
	 * Site main view page visit history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMainViewPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, PageMode.PAGEVISITHISTORY));

	}

	/**
	 * Site main view test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMainViewTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, PageMode.OVERVIEW));

		final WebElement applicationMenuItem = userPageVisit.getMenuItem("Application");
		assertNotNull(applicationMenuItem);
		userPageVisit.performClickAction(applicationMenuItem);

		final WebElement overviewMenuItem = userPageVisit.getMenuItem("Start");
		assertNotNull(overviewMenuItem);
		userPageVisit.performClickAction(overviewMenuItem);

	}


	@Test(timeout = 60000)
	public void siteDashboardViewTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.DASHBOARD_VIEW_NAME, PageMode.OVERVIEW));

		final WebElement applicationMenuItem = userPageVisit.getMenuItem("Application");
		assertNotNull(applicationMenuItem);
		userPageVisit.performClickAction(applicationMenuItem);

		final WebElement overviewMenuItem = userPageVisit.getMenuItem("Dashboard");
		assertNotNull(overviewMenuItem);
		userPageVisit.performClickAction(overviewMenuItem);

	}


	/**
	 * Site ministry current members invalid reference test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryCurrentMembersInvalidReferenceTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.CURRENTMEMBERS.toString(), "InvalidReference"));
		userPageVisit.checkHtmlBodyContainsText("Invalid reference, content not found");
	}

	/**
	 * Site ministry current members test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryCurrentMembersTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.CURRENTMEMBERS.toString(), "N%C3%A4ringsdepartementet"));

	}


	/**
	 * Site ministry document activity invalid reference test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryDocumentActivityInvalidReferenceTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.DOCUMENTACTIVITY.toString(), "InvalidReference"));
		userPageVisit.checkHtmlBodyContainsText("Invalid reference, content not found");
	}

	/**
	 * Site ministry document activity test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryDocumentActivityTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.DOCUMENTACTIVITY.toString(), "N%C3%A4ringsdepartementet"));

	}


	/**
	 * Site ministry document history invalid reference test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryDocumentHistoryInvalidReferenceTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.DOCUMENTHISTORY.toString(), "InvalidReference"));
		userPageVisit.checkHtmlBodyContainsText("Invalid reference, content not found");
	}

	/**
	 * Site ministry document history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryDocumentHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.DOCUMENTHISTORY.toString(), "N%C3%A4ringsdepartementet"));

	}

	/**
	 * Site ministry government body expenditure test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryGovernmentBodyExpenditureTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_EXPENDITURE.toString(), "N%C3%A4ringsdepartementet"));
	}

	/**
	 * Site ministry government body headcount test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryGovernmentBodyHeadcountTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_HEADCOUNT.toString(), "N%C3%A4ringsdepartementet"));
	}

	/**
	 * Site ministry government body income test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryGovernmentBodyIncomeTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_INCOME.toString(), "N%C3%A4ringsdepartementet"));
	}

	/**
	 * Site ministry government body invalid reference test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryGovernmentBodyInvalidReferenceTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_HEADCOUNT.toString(), "InvalidReference"));
		userPageVisit.checkHtmlBodyContainsText("Invalid reference, content not found");
	}

	/**
	 * Site ministry member history invalid reference test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryMemberHistoryInvalidReferenceTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.MEMBERHISTORY.toString(), "InvalidReference"));
		userPageVisit.checkHtmlBodyContainsText("Invalid reference, content not found");
	}

	/**
	 * Site ministry member history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryMemberHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.MEMBERHISTORY.toString(), "N%C3%A4ringsdepartementet"));
	}

	/**
	 * Site ministry overview invalid ref test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryOverviewInvalidRefTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.OVERVIEW, "InvalidReference"));
		userPageVisit.checkHtmlBodyContainsText("Invalid reference, content not found");
	}


	/**
	 * Site ministry overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.OVERVIEW, "N%C3%A4ringsdepartementet"));
	}



	/**
	 * Site ministry page visit history invalid reference test.
	 *
	 * @throws Exception the exception
	 */
	public void siteMinistryPageVisitHistoryInvalidReferenceTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.PAGEVISITHISTORY,
				"InvalidReference"));
		userPageVisit.checkHtmlBodyContainsText("Invalid reference, content not found");

	}

	/**
	 * Site ministry page visit history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.PAGEVISITHISTORY,
				"N%C3%A4ringsdepartementet"));
	}

	/**
	 * Site ministry ranking all ministries by party charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryRankingAllMinistriesByPartyChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.ALLMINISTRIESPARTYBYTOTALDAYS.toString()));

	}

	/**
	 * Site ministry ranking all ministries charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryRankingAllMinistriesChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.ALLMINISTRIESBYHEADCOUNT.toString()));

	}


	/**
	 * Site ministry ranking all roles charts charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryRankingAllRolesChartsChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.ALL_GOVERNMENT_ROLE_CHART.toString()));

	}

	/**
	 * Site ministry ranking current ministries charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryRankingCurrentMinistriesChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTMINISTRIESBYHEADCOUNT.toString()));

	}

	/**
	 * Site ministry ranking current ministries leader score board test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryRankingCurrentMinistriesLeaderScoreBoardTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTMINISTRIESLEADERSCORECARD.toString()));

	}


	/**
	 * Site ministry ranking current parties charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryRankingCurrentPartiesChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTPARTIESBYHEADCOUNT.toString()));

	}

	/**
	 * Site ministry ranking data grid test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryRankingDataGridTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DATAGRID));

	}


	@Test(timeout = 60000)
	public void siteMinistryRankingGovernmentBodyExpenditureTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_EXPENDITURE.toString()));
	}

	/**
	 * Site ministry ranking government body test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryRankingGovernmentBodyHeadcountTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_HEADCOUNT.toString()));
	}

	@Test(timeout = 60000)
	public void siteMinistryRankingGovernmentBodyIncomeTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_INCOME.toString()));
	}

	/**
	 * Site ministry ranking government outcome test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryRankingGovernmentOutcomeTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, MinistryPageMode.GOVERNMENT_OUTCOME.toString()));

	}

	/**
	 * Site ministry ranking navigation test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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
	 * Site ministry ranking overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryRankingOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW));

	}

	/**
	 * Site ministry ranking page visit history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryRankingPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));

	}

	/**
	 * Site ministry role ghant invalid reference test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryRoleGhantInvalidReferenceTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.ROLEGHANT.toString(), "InvalidReference"));
		userPageVisit.checkHtmlBodyContainsText("Invalid reference, content not found");
	}

	/**
	 * Site ministry role ghant test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteMinistryRoleGhantTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.ROLEGHANT.toString(), "N%C3%A4ringsdepartementet"));
	}


	/**
	 * Site parliament chart decision flow select year test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteParliamentChartDecisionFlowSelectYearTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS,
				ChartIndicators.DECISION_FLOW_CHART.toString()+ "[2016/17]"));

	}

	/**
	 * Site parliament chart decision flow test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteParliamentChartDecisionFlowTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS,
				ChartIndicators.DECISION_FLOW_CHART.toString()));

	}

	/**
	 * Site parliament chart view decsion activity by type test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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
	@Test(timeout = 60000)
	public void siteParliamentChartViewDocumentActivityByTypeTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS,
				ChartIndicators.DOCUMENTACTIVITYBYTYPE.toString()));

	}


	/**
	 * Site parliament chart view party age test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteParliamentChartViewPartyAgeTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS,
				ChartIndicators.PARTYAGE.toString()));

	}

	/**
	 * Site parliament chart view party gender test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteParliamentChartViewPartyGenderTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS,
				ChartIndicators.PARTYGENDER.toString()));

	}

	/**
	 * Site parliament chart view party winner test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteParliamentChartViewPartyWinnerTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS,
				ChartIndicators.PARTYWINNER.toString()));

	}

	/**
	 * Site parliament rules risk summary test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteParliamentRulesRiskSummaryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.RULES,
				RiskIndicators.RISK_SUMMARY.toString()));

		clickFirstRowInGrid(userPageVisit);
	}


	/**
	 * Site parliament rule violations test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteParliamentRuleViolationsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.RULES,
				RiskIndicators.RULE_VIOLATIONS.toString()));
		clickFirstRowInGrid(userPageVisit);
	}



	/**
	 * Site party against coalation missing test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyAgainstCoalationMissingTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.PARTYAGAINSTCOALATIONSSUMMARY.toString(), "PP"));
	}

	/**
	 * Site party against coalation summary chart test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyAgainstCoalationSummaryChartTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.PARTYAGAINSTCOALATIONSSUMMARY.toString(), "S"));
	}


	/**
	 * Site party committee ballot decision summary missing test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyCommitteeBallotDecisionSummaryMissingTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.COMMITTEEBALLOTDECISIONSUMMARY.toString(), "PP"));
	}



	/**
	 * Site party committee ballot decision summary test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyCommitteeBallotDecisionSummaryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.COMMITTEEBALLOTDECISIONSUMMARY.toString(), "S"));
	}

	/**
	 * Site party committee roles test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyCommitteeRolesTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.COMMITTEEROLES.toString(), "S"));
	}

	/**
	 * Site party current leaders test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyCurrentLeadersTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CURRENTLEADERS.toString(), "S"));

	}


	/**
	 * Site party current members test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyCurrentMembersTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CURRENTMEMBERS.toString(), "S"));

	}

	/**
	 * Site party document activity missing test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyDocumentActivityMissingTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DOCUMENTACTIVITY.toString(), "PP"));

	}

	/**
	 * Site party document activity test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyDocumentActivityTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DOCUMENTACTIVITY.toString(), "S"));
	}

	/**
	 * Site party document history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyDocumentHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DOCUMENTHISTORY.toString(), "S"));

	}

	/**
	 * Site party government roles test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyGovernmentRolesTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.GOVERNMENTROLES.toString(), "S"));
	}



	/**
	 * Site party leader history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyLeaderHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.LEADERHISTORY.toString(), "S"));
	}

	/**
	 * Site party member history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyMemberHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.MEMBERHISTORY.toString(), "S"));

	}

	/**
	 * Site party overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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
	@Test(timeout = 60000)
	public void sitePartyPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.PAGEVISITHISTORY, "S"));
	}

	/**
	 * Site party party won daily summary chart missing test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyPartyWonDailySummaryChartMissingTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.PARTYWONDAILYSUMMARYCHART.toString(), "PP"));
	}

	/**
	 * Site party party won daily summary chart test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyPartyWonDailySummaryChartTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.PARTYWONDAILYSUMMARYCHART.toString(), "S"));
	}

	/**
	 * Site party ranking view all parties charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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
	@Test(timeout = 60000)
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
	@Test(timeout = 60000)
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
	@Test(timeout = 60000)
	public void sitePartyRankingViewCurrentPartiesChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTPARTIES.toString()));

	}


	/**
	 * Site party ranking view current parties leader scoreboard test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyRankingViewCurrentPartiesLeaderScoreboardTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTPARTYLEADERSCORECARD.toString()));

	}

	/**
	 * Site party ranking view data grid test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyRankingViewDataGridTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.DATAGRID));

	}

	/**
	 * Site party ranking view overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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
	@Test(timeout = 60000)
	public void sitePartyRankingViewPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));
	}

	/**
	 * Site party role ghant missing test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyRoleGhantMissingTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.ROLEGHANT.toString(), "PP"));
	}

	/**
	 * Site party role ghant test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyRoleGhantTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.ROLEGHANT.toString(), "S"));
	}

	/**
	 * Site party support missing test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void sitePartySupportMissingTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.PARTYSUPPORTSUMMARY.toString(), "PP"));
	}

	/**
	 * Site party support summary chart test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void sitePartySupportSummaryChartTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.PARTYSUPPORTSUMMARY.toString(), "S"));
	}

	/**
	 * Site party vote history missing test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyVoteHistoryMissingTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.VOTEHISTORY.toString(), "PP"));

	}

	/**
	 * Site party vote history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePartyVoteHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.VOTEHISTORY.toString(), "S"));

	}

	/**
	 * Site politican ballot decision summary missing test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void sitePoliticanBallotDecisionSummaryMissingTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.BALLOTDECISIONSUMMARY.toString(), "0885771106404"));

	}

	/**
	 * Site politican ballot decision summary test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePoliticanBallotDecisionSummaryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.BALLOTDECISIONSUMMARY.toString(), "0980681611418"));

	}

	/**
	 * Site politican document activity missing test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void sitePoliticanDocumentActivityMissingTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.DOCUMENTACTIVITY.toString(), "0885771106404"));

	}

	/**
	 * Site politican document activity test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePoliticanDocumentActivityTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.DOCUMENTACTIVITY.toString(), "0980681611418"));

	}

	/**
	 * Site politican document history missing test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void sitePoliticanDocumentHistoryMissingTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.DOCUMENTHISTORY.toString(), "0885771106404"));

	}

	/**
	 * Site politican document history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePoliticanDocumentHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.DOCUMENTHISTORY.toString(), "0980681611418"));

	}



	/**
	 * Site politican indicator missing test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void sitePoliticanIndicatorMissingTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PageMode.INDICATORS.toString(), "0885771106404"));

	}

	/**
	 * Site politican indicator test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePoliticanIndicatorTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PageMode.INDICATORS.toString(), "0980681611418"));

	}



	/**
	 * Site politican over view test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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
	@Test(timeout = 60000)
	public void sitePoliticanPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.PAGEVISITHISTORY, "0980681611418"));
	}


	/**
	 * Site politican role ghant minister test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void sitePoliticanRoleGhantMinisterTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.ROLEGHANT.toString(), "0473783431010"));
	}

	/**
	 * Site politican role ghant prime minister test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void sitePoliticanRoleGhantPrimeMinisterTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.ROLEGHANT.toString(), "0218878014918"));
	}


	/**
	 * Site politican role ghant test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePoliticanRoleGhantTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.ROLEGHANT.toString(), "0980681611418"));
	}

	/**
	 * Site politican role list test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePoliticanRoleListTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.ROLELIST.toString(), "0980681611418"));

	}

	/**
	 * Site politican role summary test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePoliticanRoleSummaryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.ROLESUMMARY.toString(), "0980681611418"));
	}

	/**
	 * Site politican vote history missing test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void sitePoliticanVoteHistoryMissingTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.VOTEHISTORY.toString(), "0885771106404"));

	}


	/**
	 * Site politican vote history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePoliticanVoteHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.VOTEHISTORY.toString(), "0980681611418"));

	}

	/**
	 * Site politician ranking view charts all parties test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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
	@Test(timeout = 60000)
	public void sitePoliticianRankingViewChartsCurrentPartiesTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTPARTIES.toString()));

	}

	/**
	 * Site politician ranking view data grid navigation test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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
	 * Site politician ranking view data grid test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void sitePoliticianRankingViewDataGridTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DATAGRID));

	}


	/**
	 * Site politician ranking view overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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
	@Test(timeout = 60000)
	public void sitePoliticianRankingViewPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));
	}


	/**
	 * Site register user already exist test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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

		final UserPageVisit userRegisterAgainPageVisit = new UserPageVisit(driver, browser);

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
	@Test(timeout = 60000)
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
	 * Site register user test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
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
	 * Site search document test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteSearchDocumentTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.SEARCH_DOCUMENT_VIEW_NAME, ""));

		userPageVisit.searchDocument("2016");

	}


	/**
	 * Site test country ranking overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteTestCountryRankingOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW));
	}


	/**
	 * Site test parliament overview test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteTestParliamentOverviewTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.OVERVIEW));
	}


	/**
	 * Site test parliament page visit history test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteTestParliamentPageVisitHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));
	}


	/**
	 * Site user home events no access not login sec test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteUserHomeEventsNoAccessNotLoginSecTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);
		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, UserHomePageMode.USER_EVENTS.toString()));
		assertTrue("Should be denied",userPageVisit.checkHtmlBodyContainsText("Access denied:userhome"));
	}


	/**
	 * Site user home security setting no access not login sec test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteUserHomeSecuritySettingNoAccessNotLoginSecTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);
		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, UserHomePageMode.SECURITY_SETTINGS.toString()));
		assertTrue("Should be denied",userPageVisit.checkHtmlBodyContainsText("Access denied:userhome"));
	}

	/**
	 * Site user home visits no access not login sec test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteUserHomeVisitsNoAccessNotLoginSecTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);
		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		userPageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, UserHomePageMode.USER_VISITS.toString()));
		assertTrue("Should be denied",userPageVisit.checkHtmlBodyContainsText("Access denied:userhome"));
	}

}
