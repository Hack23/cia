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

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hack23.cia.model.internal.application.data.impl.DataAgentOperation;
import com.hack23.cia.model.internal.application.data.impl.DataAgentTarget;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;

/**
 * The Class AdminRoleSystemTest.
 */
public final class AdminRoleSystemTest extends AbstractRoleSystemTest {

	/** The Constant NO_WEBDRIVER_EXIST_FOR_BROWSER. */
	private static final String NO_WEBDRIVER_EXIST_FOR_BROWSER = "No webdriver exist for browser:";

	/**
	 * Instantiates a new admin role system test.
	 *
	 * @param browser
	 *            the browser
	 */
	public AdminRoleSystemTest(final String browser) {
		super(browser);
	}

	/**
	 * Browsers strings.
	 *
	 * @return the collection
	 */
	@Parameters(name = "AdminRoleSiteTest{index}: browser({0})")
	public final static Collection<String[]> browsersStrings() {
		return Arrays.asList(new String[][] { { "chrome" } });
		// return Arrays.asList(new Object[][] { { "firefox" },{ "chrome" }, {
		// "htmlunit-firefox" },{ "htmlunit-ie11" },{ "htmlunit-chrome" } });
	}


	/**
	 * Site admin agency test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminAgencyTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_AGENCY_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Agency"));

		clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_AGENCY_VIEW_NAME, ""));

	}

	/**
	 * Site admin portal test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminPortalTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_PORTAL_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Portal"));

		clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_PORTAL_VIEW_NAME, ""));

	}

	/**
	 * Site admin application configuration test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminApplicationConfigurationTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Application Configuration"));

		clickFirstRowInGrid(userPageVisit);

		userPageVisit
				.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, ""));

		userPageVisit.updateConfigurationProperty("Update Configuration.propertyValue", String.valueOf(false));

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, ""));

	}

	/**
	 * Site admin country test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminCountryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_COUNTRY_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Country"));

		clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_COUNTRY_VIEW_NAME, ""));

	}

	/**
	 * Site admin email test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminEmailTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_EMAIL_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("email"));

		userPageVisit.sendEmailOnEmailPage("james@hack23.com", "siteAdminEmailTest", "siteAdminEmailTest content");

		userPageVisit.checkNotificationMessage("Email Sent");
	}

	/**
	 * Site admin email failed no valid email test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminEmailFailedNoValidEmailTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_EMAIL_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("email"));

		userPageVisit.sendEmailOnEmailPage("nonvalidemail", "siteAdminEmailFailedNoValidEmailTest", "siteAdminEmailFailedNoValidEmailTest content");

		userPageVisit.checkNotificationMessage("Send email failedEmail is not a valid email address");
	}


	/**
	 * Site admin useraccount test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminUseraccountTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Useraccount"));

		clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));

	}

	/**
	 * Site admin language test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminLanguageTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Language"));

		clickFirstRowInGrid(userPageVisit);
		userPageVisit.validatePage(new
		PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME, ""));

	}

	/**
	 * Site admin language content test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminLanguageContentTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_CONTENT_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Language Content"));

		clickFirstRowInGrid(userPageVisit);
		userPageVisit.validatePage(new
		PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_CONTENT_VIEW_NAME,
		""));

	}

	/**
	 * Site admin application session test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminApplicationSessionTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Application Session"));

		clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));

	}

	/**
	 * Site admin application event test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminApplicationEventTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Application Action Event"));

		clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, ""));

	}


	/**
	 * Site admin application event charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminApplicationEventChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, PageMode.CHARTS));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Application Action Event chart"));

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, PageMode.CHARTS));

	}


	/**
	 * Site admin monitoring failed access test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 20000)
	public void siteAdminMonitoringFailedAccessTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_MONITORING_VIEW_NAME, ""));
		assertTrue("Expect this content", userPageVisit.checkHtmlBodyContainsText("Access denided:adminmonitoring"));

		// assertTrue("Expect this content",
		// userPageVisit.getIframesHtmlBodyAsText().contains("Access
		// denided:adminmonitoring"));
	}

	/**
	 * Site admin monitoring success test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminMonitoringSuccessTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_MONITORING_VIEW_NAME, ""));
		assertTrue("Expect this content", userPageVisit.checkHtmlBodyContainsText("Admin Monitoring"));

		assertFalse("Dont expect this content",
				userPageVisit.getIframesHtmlBodyAsText().contains("Login with Username and Password"));
	}


	/**
	 * Visit admin data summary view.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void visitAdminDataSummaryViewTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));

	}

	/**
	 * Visit admin data summary view refresh views test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void visitAdminDataSummaryViewRefreshViewsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));

		final WebElement refreshViewsButton =userPageVisit.findButton("Refresh Views");
		assertNotNull("Expect to find a Refresh Views Button",refreshViewsButton);

		userPageVisit.performClickAction(refreshViewsButton);
	}
	
	
	/**
	 * Visit admin data summary view remove politician test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void visitAdminDataSummaryViewRemovePoliticianTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));

		final WebElement removePoliticiansButton =userPageVisit.findButton("Remove Politicians");
		assertNotNull("Expect to find a Button",removePoliticiansButton);

		userPageVisit.performClickAction(removePoliticiansButton);
	}
	
	/**
	 * Visit admin data summary view remove documents test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void visitAdminDataSummaryViewRemoveDocumentsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));

		final WebElement removeDocumentsButton =userPageVisit.findButton("Remove Documents");
		assertNotNull("Expect to find a Button",removeDocumentsButton);

		userPageVisit.performClickAction(removeDocumentsButton);

	}

	
	/**
	 * Visit admin data summary view remove application history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void visitAdminDataSummaryViewRemoveApplicationHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));

		final WebElement removeApplicationHistoryButton =userPageVisit.findButton("Remove Application History");
		assertNotNull("Expect to find a Button",removeApplicationHistoryButton);

		userPageVisit.performClickAction(removeApplicationHistoryButton);

	}

	
	


	/**
	 * Visit admin data summary view update search index test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void visitAdminDataSummaryViewUpdateSearchIndexTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));

		final WebElement updateSearchIndexButton =userPageVisit.findButton("Update Search Index");
		assertNotNull("Expect to find a Update Search Index Button",updateSearchIndexButton);

		userPageVisit.performClickAction(updateSearchIndexButton);

	}

	/**
	 * Site admin test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void siteAdminAgentOperationTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_AGENT_OPERATIONVIEW_NAME, ""));


		userPageVisit.verifyViewActions(new ViewAction[] {ViewAction.VISIT_MAIN_VIEW,ViewAction.START_AGENT_BUTTON });

		final List<String> actionIdsBy = userPageVisit.getActionIdsBy(ViewAction.START_AGENT_BUTTON);
		assertTrue(actionIdsBy.size() > 0);


		userPageVisit.performAdminAgentOperation(DataAgentTarget.MODEL_EXTERNAL_RIKSDAGEN, DataAgentOperation.IMPORT);

	}



}
