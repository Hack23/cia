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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hack23.cia.model.internal.application.data.impl.DataAgentOperation;
import com.hack23.cia.model.internal.application.data.impl.DataAgentTarget;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Class UserPageVisit.
 */
public final class UserPageVisit extends Assert {

	/** The Constant WAIT_FOR_PAGE_DELAY. */
	private static final int WAIT_FOR_PAGE_DELAY = 2500;

	/** The Constant WAIT_FOR_TEXT. */
	private static final int WAIT_FOR_TEXT = 5000;

	/** The Constant WAIT_FOR_PAGE_ELEMENT. */
	private static final int WAIT_FOR_PAGE_ELEMENT = 25000;

	/** The screen shot number. */
	private static int screenShotNumber;

	/** The driver. */
	private final WebDriver driver;

	/** The browser. */
	private final String browser;

	/** The action. */
	private final Actions action;

	/** The Constant systemTestTargetUrl. */
	private static final String systemTestTargetUrl;

	static {
		 final String systemTestTargetUrlProperty = System.getProperty("system.test.target.url");
		 if (systemTestTargetUrlProperty != null && systemTestTargetUrlProperty.trim().length() > 0) {
			systemTestTargetUrl = systemTestTargetUrlProperty;
		 } else {
			 systemTestTargetUrl = CitizenIntelligenceAgencyServer.ACCESS_URL;
		 }
	}


	/**
	 * Instantiates a new user page visit.
	 *
	 * @param driver
	 *            the driver
	 * @param browser
	 *            the browser
	 */
	public UserPageVisit(final WebDriver driver, final String browser) {
		super();
		this.driver = driver;
		this.browser = browser;
		action = new Actions(driver);
	}

	/**
	 * Gets the iframes html body as text.
	 *
	 * @return the iframes html body as text
	 */
	public String getIframesHtmlBodyAsText() {
		final StringBuilder stringBuilder = new StringBuilder();
		final List<WebElement> ele = driver.findElements(By.tagName("iframe"));
	    for(final WebElement el : ele){
	    	driver.switchTo().frame(el);

	    	stringBuilder.append(driver.findElement(By.tagName("body")).getText());
	    	driver.switchTo().defaultContent();
	    }
	    return stringBuilder.toString();
	}

	/**
	 * Visit start page.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void visitStartPage() throws Exception {
		driver.get(systemTestTargetUrl);

		waitForBrowser(WAIT_FOR_PAGE_DELAY);

		assertEquals(browser, systemTestTargetUrl,
				driver.getCurrentUrl());
		assertEquals(browser, "Citizen Intelligence Agency", driver.getTitle());
		assertNotNull(browser, driver.getWindowHandle());
		grabScreenshot(driver);

		verifyViewActions(new ViewAction[] {
				ViewAction.VISIT_ADMIN_AGENT_OPERATION_VIEW,
				ViewAction.VISIT_ADMIN_DATA_SUMMARY_VIEW,
				ViewAction.VISIT_POLITICIAN_RANKING_VIEW,
				ViewAction.VISIT_PARTY_RANKING_VIEW,
				ViewAction.VISIT_MINISTRY_RANKING_VIEW,
				ViewAction.VISIT_COMMITTEE_RANKING_VIEW,
				ViewAction.VISIT_COUNTRY_VIEW });
	}

	/**
	 * Wait for browser.
	 *
	 * @param delay
	 *            the delay
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	private static void waitForBrowser(final int delay) throws InterruptedException {
		Thread.sleep(delay);
	}


	/**
	 * Visit direct page.
	 *
	 * @param page
	 *            the page
	 * @throws Exception
	 *             the exception
	 */
	public void visitDirectPage(final PageModeMenuCommand page) throws Exception {
		final String url = systemTestTargetUrl  +"#!" + page.getPagePath();
		driver.get(url);

		waitForBrowser(WAIT_FOR_PAGE_DELAY);

		int waitTimeForPageLoad=0;
		while (!getActionsAvailable().contains(ViewAction.VISIT_MAIN_VIEW)) {
			waitForBrowser(10);
			waitTimeForPageLoad=waitTimeForPageLoad + 10;
			if (waitTimeForPageLoad > WAIT_FOR_PAGE_ELEMENT) {
				fail("Exceeded timeout for pageload:" + WAIT_FOR_PAGE_ELEMENT);
			}
		}

		final long end = System.currentTimeMillis() + WAIT_FOR_PAGE_ELEMENT;
		while (System.currentTimeMillis() < end && !getActionsAvailable().contains(ViewAction.VISIT_MAIN_VIEW)) {
			;
		}

		grabScreenshot(driver);
		assertTrue("Each page should contain a MainMenu link",getActionsAvailable().contains(ViewAction.VISIT_MAIN_VIEW));


		final String text = getHtmlBodyAsText();
		assertNotNull(text);
		assertFalse("Page contains exception, url:" + url ,text.contains("Exception"));
		assertFalse("Page contains widget exception, url:" + url,text.contains("Widget"));

		assertEquals(browser, url,
				driver.getCurrentUrl());
		assertNotNull(browser, driver.getWindowHandle());

	}

	/**
	 * Validate page.
	 *
	 * @param page
	 *            the page
	 * @throws Exception
	 *             the exception
	 */
	public void validatePage(final PageModeMenuCommand page) throws Exception {
		final String url = systemTestTargetUrl  +"#!" + page.getPagePath();


		final long end = System.currentTimeMillis() + WAIT_FOR_PAGE_ELEMENT;
		while (System.currentTimeMillis() < end && !getActionsAvailable().contains(ViewAction.VISIT_MAIN_VIEW)) {
			;
		}

		assertTrue("Each page should contain a MainMenu link",getActionsAvailable().contains(ViewAction.VISIT_MAIN_VIEW));


		final String text = getHtmlBodyAsText();
		assertNotNull(text);
		assertFalse("Page contains exception, url:" + url ,text.contains("Exception"));
		assertFalse("Page contains widget exception, url:" + url,text.contains("Widget"));


		int waitTimeForPageLoad=0;
	    while(!url.equals(driver.getCurrentUrl()) && !driver.getCurrentUrl().contains(url)) {
			waitForBrowser(10);
			waitTimeForPageLoad=waitTimeForPageLoad + 10;
			if (waitTimeForPageLoad > WAIT_FOR_PAGE_ELEMENT) {
				assertEquals("Exceeded timeout:" + WAIT_FOR_PAGE_ELEMENT +" to match or contains url:" + url + " browser:" +browser, url,
						driver.getCurrentUrl());
			}
	    }

		assertNotNull(browser, driver.getWindowHandle());

	}


	/**
	 * Gets the html body as text.
	 *
	 * @return the html body as text
	 */
	private String getHtmlBodyAsText() {
		return driver.findElement(By.tagName("body")).getText();
	}

	/**
	 * Check html body contains text.
	 *
	 * @param text
	 *            the text
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	public boolean checkHtmlBodyContainsText(final String text) throws Exception {
		int waitTimeForPageLoad=0;
		while (!getHtmlBodyAsText().contains(text)) {
			waitForBrowser(10);
			waitTimeForPageLoad=waitTimeForPageLoad + 10;
			if (waitTimeForPageLoad > WAIT_FOR_TEXT) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the menu bar.
	 *
	 * @return the menu bar
	 */
	public WebElement getMenuBar() {
		return driver.findElement(By.className("v-menubar"));
	}

	/**
	 * Gets the menu item.
	 *
	 * @param caption
	 *            the caption
	 * @return the menu item
	 */

	public WebElement getMenuItem(final String... caption) {
		return getMenuItem(getMenuBar(), 1,caption);
	}

	/**
	 * Gets the menu item.
	 *
	 * @param element
	 *            the element
	 * @param caption
	 *            the caption
	 * @return the menu item
	 */
	public WebElement getMenuItem(final WebElement element,final String... caption) {
		return getMenuItem(element, 1,caption);
	}

	/**
	 * Gets the menu item.
	 *
	 * @param element
	 *            the element
	 * @param level
	 *            the level
	 * @param caption
	 *            the caption
	 * @return the menu item
	 */
	private WebElement getMenuItem(final WebElement element,final int level,final String... caption) {
		final List<WebElement> findElements = element.findElements(By.className("v-menubar-menuitem-caption"));
		if (caption.length == level) {
			for (final WebElement webElement : findElements) {
				if (webElement.getText().contains(caption[level -1])) {
					return webElement;
				}
			}
		} else {
			for (final WebElement webElement : findElements) {
				if (caption[level -1].equals(webElement.getText())) {


					return getMenuItem(webElement, level +1 ,caption);
				}
			}
		}

			final List<WebElement> findElements2 = driver.findElements(By.className("v-menubar-menuitem"));
			if (caption.length == level) {
				for (final WebElement webElement : findElements2) {
						if (webElement.getText().contains(caption[level -1])) {
						return webElement;
					}
				}
			}

		return null;
	}


	/**
	 * Gets the buttons.
	 *
	 * @return the buttons
	 */
	public List<WebElement> getButtons() {
		final List<WebElement> result = new ArrayList<>();
		final List<WebElement> nativeButtons = driver.findElements(By.className("v-nativebutton"));
		final List<WebElement> buttons = driver.findElements(By.className("v-button"));
		final List<WebElement> buttonsCaption = driver.findElements(By.className("v-button-caption"));

		result.addAll(nativeButtons);
		result.addAll(buttons);
		result.addAll(buttonsCaption);

		return result;
	}

	/**
	 * Gets the grid rows.
	 *
	 * @return the grid rows
	 */
	public List<WebElement> getGridRows() {
		final WebElement gridBody = driver.findElement(By.className("v-grid-body"));
		return gridBody.findElements(By.className("v-grid-row"));

	}

	/**
	 * Gets the grid headers.
	 *
	 * @return the grid headers
	 */
	public List<WebElement> getGridHeaders() {
		final WebElement gridBody = driver.findElement(By.className("v-grid-header"));

		return gridBody.findElements(By.className("v-grid-row"));

	}


	/**
	 * Visit politician ranking view.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void VisitPoliticianRankingView() throws Exception {
		final WebElement politiciansViewLink = driver.findElement(By
				.id(ViewAction.VISIT_POLITICIAN_RANKING_VIEW.name()));
		performClickAction(politiciansViewLink, WAIT_FOR_PAGE_DELAY);

		assertEquals("http://localhost:8080/#!politicianranking",
				driver.getCurrentUrl());

		verifyViewActions(new ViewAction[] {ViewAction.VISIT_MAIN_VIEW });

//		final List<String> actionIdsBy = getActionIdsBy(ViewAction.VISIT_POLITICIAN_VIEW);
//		assertTrue(!actionIdsBy.isEmpty());
	}

	/**
	 * Visit politician view.
	 *
	 * @param id
	 *            the id
	 * @throws Exception
	 *             the exception
	 */
	public void VisitPoliticianView(final String id) throws Exception {
		final WebElement politicianViewLink = driver.findElement(By.id(id));
		performClickAction(politicianViewLink, WAIT_FOR_PAGE_DELAY*4);

		//		assertEquals(
		//				"http://localhost:8080/#!politicia/"
		//						+ URLEncoder.encode(id.replace(ViewAction.VisitPoliticianView.name() + "/", "")
		//								.trim(),StandardCharsets.UTF_8), driver.getCurrentUrl());

		verifyViewActions(new ViewAction[] {ViewAction.VISIT_MAIN_VIEW });
	}


	/**
	 * Visit committee ranking view.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void VisitCommitteeRankingView() throws Exception {
		final WebElement committeeViewLink = driver.findElement(By
				.id(ViewAction.VISIT_COMMITTEE_RANKING_VIEW.name()));
		performClickAction(committeeViewLink, WAIT_FOR_PAGE_DELAY);

		assertEquals("http://localhost:8080/#!committeeranking",
				driver.getCurrentUrl());

		verifyViewActions(new ViewAction[] {ViewAction.VISIT_MAIN_VIEW });

		final List<String> actionIdsBy = getActionIdsBy(ViewAction.VISIT_COMMITTEE_VIEW);
		assertTrue(!actionIdsBy.isEmpty());
	}

	/**
	 * Visit committee view.
	 *
	 * @param id
	 *            the id
	 * @throws Exception
	 *             the exception
	 */
	public void VisitCommitteeView(final String id) throws Exception {
		final WebElement committeeViewLink = driver.findElement(By.id(id));
		performClickAction(committeeViewLink, WAIT_FOR_PAGE_DELAY);

		//		assertEquals(
		//				"http://localhost:8080/#!committee/"
		//						+ URLEncoder.encode(id.replace(ViewAction.VisitCommitteeView.name() + "/", "")
		//								.trim(),StandardCharsets.UTF_8), driver.getCurrentUrl());

		verifyViewActions(new ViewAction[] {ViewAction.VISIT_MAIN_VIEW });
	}


	/**
	 * Verify view actions.
	 *
	 * @param viewActions
	 *            the view actions
	 */
	public void verifyViewActions(final ViewAction[] viewActions) {
		final Set<ViewAction> actionsAvailable = getActionsAvailable();
		for (final ViewAction viewAction : viewActions) {
			assertTrue(browser, actionsAvailable.contains(viewAction));

		}
	}

	/**
	 * Visit ministry ranking view.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void VisitMinistryRankingView() throws Exception {
		final WebElement ministryViewLink = driver.findElement(By
				.id(ViewAction.VISIT_MINISTRY_RANKING_VIEW.name()));
		performClickAction(ministryViewLink, WAIT_FOR_PAGE_DELAY);

		assertEquals("http://localhost:8080/#!ministryranking",
				driver.getCurrentUrl());

		verifyViewActions(new ViewAction[] {ViewAction.VISIT_MAIN_VIEW});

		final List<String> actionIdsBy = getActionIdsBy(ViewAction.VISIT_MINISTRY_VIEW);
		assertTrue(actionIdsBy.size() > 0);
	}

	/**
	 * Visit ministry view.
	 *
	 * @param id
	 *            the id
	 * @throws Exception
	 *             the exception
	 */
	public void VisitMinistryView(final String id) throws Exception {
		final WebElement ministryViewLink = driver.findElement(By.id(id));
		performClickAction(ministryViewLink, WAIT_FOR_PAGE_DELAY);

		//		assertEquals(
		//				"http://localhost:8080/#!ministry/"
		//						+ URLEncoder.encode(id.replace(ViewAction.VisitMinistryView.name() + "/", "")
		//								.trim(),StandardCharsets.UTF_8), driver.getCurrentUrl());

		verifyViewActions(new ViewAction[] {ViewAction.VISIT_MAIN_VIEW });
	}



	/**
	 * Perform admin agent operation.
	 *
	 * @param target
	 *            the target
	 * @param operation
	 *            the operation
	 * @throws Exception
	 *             the exception
	 */
	public void performAdminAgentOperation(final DataAgentTarget target,final DataAgentOperation operation) throws Exception {
		setValueForSelectField(ViewAction.START_AGENT_BUTTON.name() +"/Target",target.name().replace("_", " ").toLowerCase().replaceFirst("m", "M"));
		setValueForSelectField(ViewAction.START_AGENT_BUTTON.name() +"/Operation",operation.name());

		final WebElement startButtion = driver.findElement(By
				.id(ViewAction.START_AGENT_BUTTON.name()));

		performClickAction(startButtion, WAIT_FOR_PAGE_DELAY);
	}

	/**
	 * Sets the value for select field.
	 *
	 * @param selectId
	 *            the select id
	 * @param value
	 *            the value
	 */
	private void setValueForSelectField(final String selectId, final String value) {
		final WebElement selectField = driver.findElement(By.id(selectId));

		final WebElement selectInputField = selectField.findElement(By.className("v-filterselect-input"));
		selectInputField.sendKeys(value);
		selectInputField.sendKeys(Keys.ENTER);
	}


	/**
	 * Check notification message.
	 *
	 * @param expectedValue
	 *            the expected value
	 */
	public void checkNotificationMessage(final String expectedValue) {
		final WebElement notification = driver.findElement(By.className("v-Notification"));
		assertNotNull(notification);
		assertEquals(expectedValue, notification.getText());

	}

	/**
	 * Visit main view.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void visitMainView() throws Exception {
		final WebElement mainViewLink = driver.findElement(By
				.id(ViewAction.VISIT_MAIN_VIEW.name()));
		performClickAction(mainViewLink, WAIT_FOR_PAGE_DELAY);

		verifyViewActions(new ViewAction[] {
				ViewAction.VISIT_ADMIN_AGENT_OPERATION_VIEW,
				ViewAction.VISIT_ADMIN_DATA_SUMMARY_VIEW,
				ViewAction.VISIT_POLITICIAN_RANKING_VIEW,
				ViewAction.VISIT_PARTY_RANKING_VIEW,
				ViewAction.VISIT_MINISTRY_RANKING_VIEW,
				ViewAction.VISIT_COMMITTEE_RANKING_VIEW,
				ViewAction.VISIT_COUNTRY_VIEW });
	}

	/**
	 * Gets the actions available.
	 *
	 * @return the actions available
	 */
	public Set<ViewAction> getActionsAvailable() {
		final Set<ViewAction> actions = new HashSet<>();

		for (final ViewAction action : ViewAction.values()) {
			if (driver.findElements(By.id(action.name())).size() > 0) {
				actions.add(action);
			}
		}
		return actions;
	}

	/**
	 * Gets the action ids by.
	 *
	 * @param action
	 *            the action
	 * @return the action ids by
	 */
	public List<String> getActionIdsBy(final ViewAction action) {
		final List<String> idList = new ArrayList<>();
		final String xPath = "//*[contains(@id,'" + action.name() + "')]";

		for (final WebElement webElement : driver.findElements(By.xpath(xPath))) {
			idList.add(webElement.getAttribute("id"));

		}

		return idList;
	}



	/**
	 * Wait until displayed.
	 *
	 * @param element
	 *            the element
	 */
	private void waitUntilDisplayed(final WebElement element) {
		// Sleep until the div we want is visible or 5 seconds is over
		final long end = System.currentTimeMillis() + WAIT_FOR_PAGE_ELEMENT;
		while (System.currentTimeMillis() < end) {
			// If results have been returned, the results are displayed in a
			// drop
			if (element.isDisplayed() && element.isEnabled()) {
				break;
			}
		}
	}

	/**
	 * Visit party view.
	 *
	 * @param id
	 *            the id
	 * @throws Exception
	 *             the exception
	 */
	public void VisitPartyView(final String id) throws Exception {
		final WebElement politicianViewLink = driver.findElement(By.id(id));
		performClickAction(politicianViewLink, WAIT_FOR_PAGE_DELAY);

		//		assertEquals(
		//				"http://localhost:8080/#!party/"
		//						+ URLEncoder.encode(id.replace(ViewAction.VisitPartyView.name() + "/", "")
		//								.trim(),StandardCharsets.UTF_8), driver.getCurrentUrl());

		verifyViewActions(new ViewAction[] {ViewAction.VISIT_MAIN_VIEW });
	}


	/**
	 * Perform click action.
	 *
	 * @param clickElement
	 *            the click element
	 * @param waitDelay
	 *            the wait delay
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	private void performClickAction(final WebElement clickElement, final int waitDelay)
			throws InterruptedException {
		assertNotNull(clickElement);
		waitUntilDisplayed(clickElement);

		if (browser.contains("htmlunit")) {
			clickElement.click();
		} else {

			final WebDriverWait wait = new WebDriverWait(driver, WAIT_FOR_PAGE_ELEMENT);
			wait.until(ExpectedConditions.visibilityOf(clickElement));

			action.clickAndHold(clickElement).release().perform();

		}
		waitForBrowser(waitDelay);
		grabScreenshot(driver);

	}

	/**
	 * Perform click action.
	 *
	 * @param clickElement
	 *            the click element
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void performClickAction(final WebElement clickElement)
			throws InterruptedException {
		performClickAction(clickElement, WAIT_FOR_PAGE_DELAY);
	}


	/**
	 * Visit party ranking view.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void VisitPartyRankingView() throws Exception {
		performClickAction(driver.findElement(By
				.id(ViewAction.VISIT_PARTY_RANKING_VIEW.name())),
				WAIT_FOR_PAGE_DELAY);

		assertEquals("http://localhost:8080/#!partyranking",
				driver.getCurrentUrl());
		verifyViewActions(new ViewAction[] {ViewAction.VISIT_MAIN_VIEW });

		final List<String> actionIdsBy = getActionIdsBy(ViewAction.VISIT_PARTY_VIEW);
		assertTrue(actionIdsBy.size() > 0);

	}

	/**
	 * Register new user.
	 *
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @throws Exception
	 *             the exception
	 */
	public void registerNewUser(final String username,final String password) throws Exception {
		registerNewUserCheckView(username,password,UserViews.USERHOME_VIEW_NAME);
	}

	/**
	 * Register new user check view.
	 *
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @param userView
	 *            the user view
	 * @throws Exception
	 *             the exception
	 */
	public void registerNewUserCheckView(final String username,final String password,final String userView) throws Exception {

		setFieldValue("Register.username",username);
		setFieldValue("Register.email",username+"@test.com");
		setFieldValue("Register.country","Sweden");
		setFieldValue("Register.userpassword",password);

		final WebElement registerButton = driver.findElement(By.id("Register"));
		performClickAction(registerButton);

		if (userView != null) {
			final String url = systemTestTargetUrl  +"#!" + userView;
			assertEquals(browser, url,driver.getCurrentUrl());
		}

	}

	public void sendEmailOnEmailPage(final String email,final String subject,final String content) throws Exception {

		setFieldValue("Email.email",email);
		setFieldValue("Email.subject",subject);
		setFieldValue("Email.content",content);

		final WebElement emailButton = driver.findElement(By.id("Email"));
		performClickAction(emailButton);
	}


	/**
	 * Search document.
	 *
	 * @param search
	 *            the search
	 * @throws Exception
	 *             the exception
	 */
	public void searchDocument(final String search) throws Exception {

		setFieldValue("Search.searchExpression",search);

		final WebElement searchButton = driver.findElement(By.id("Search"));
		performClickAction(searchButton);

		final String url = systemTestTargetUrl  +"#!" + UserViews.SEARCH_DOCUMENT_VIEW_NAME;

		assertEquals(browser, url,
				driver.getCurrentUrl());

	}


	/**
	 * Login user.
	 *
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @throws Exception
	 *             the exception
	 */
	public void loginUser(final String username,final String password) throws Exception {
		loginUserCheckView(username,password,UserViews.USERHOME_VIEW_NAME);
	}

	/**
	 * Login user check view.
	 *
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @param view
	 *            the view
	 * @throws Exception
	 *             the exception
	 */
	public void loginUserCheckView(final String username,final String password,final String view) throws Exception {

		setFieldValue("username",username);
		setFieldValue("password",password);

		final WebElement loginButton =findButton("Login");
		assertNotNull("Expect to find a Login Button",loginButton);

		performClickAction(loginButton);

		final String url = systemTestTargetUrl  +"#!" + view;

		assertEquals(browser, url,
				driver.getCurrentUrl());
	}

	/**
	 * Logout user.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void logoutUser() throws Exception {

		final WebElement logoutButton =findButton("Logout");
		assertNotNull("Expect to find a Logout Button",logoutButton);

		final Cookie cookie= driver.manage().getCookieNamed("JSESSIONID");
		final String sessionId = cookie.getValue();


		performClickAction(logoutButton);

		final WebElement body = driver.findElement(By.tagName("body"));
		body.sendKeys(Keys.ESCAPE);


		waitForBrowser(1000);
		driver.navigate().refresh();
		waitForBrowser(2000);

		final Cookie newCookie= driver.manage().getCookieNamed("JSESSIONID");

		final String newSessionId = newCookie.getValue();

		assertNotEquals(sessionId,newSessionId);

		final String url = systemTestTargetUrl  +"#!" + CommonsViews.MAIN_VIEW_NAME;

		assertEquals(browser, url,
				driver.getCurrentUrl());



	}


	/**
	 * Find button.
	 *
	 * @param buttonLabel
	 *            the button label
	 * @return the web element
	 */
	public final WebElement findButton(final String buttonLabel) {
		final List<WebElement> buttons = getButtons();
		for (final WebElement webElement : buttons) {
			if (buttonLabel.equalsIgnoreCase(webElement.getText().trim())) {
				return webElement;
			}
		}
		return null;
	}

	/**
	 * Sets the field value.
	 *
	 * @param id
	 *            the id
	 * @param value
	 *            the value
	 */
	private void setFieldValue(final String id,final String value) {
		final WebElement findElement = driver.findElement(By.id(id));
		findElement.clear();
		findElement.sendKeys(value);
	}

	/**
	 * Update configuration property.
	 *
	 * @param property
	 *            the property
	 * @param value
	 *            the value
	 * @throws Exception
	 *             the exception
	 */
	public void updateConfigurationProperty(final String property, final String value) throws Exception {
		setFieldValue(property, value);
		final WebElement updateConfigButton = findButton("Update Configuration");
		assertNotNull("Expect to find a Update Config Button",updateConfigButton);

		performClickAction(updateConfigButton);

	}

	/**
	 * Enable google authenticator.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void enableGoogleAuthenticator() throws Exception {

		final WebElement enableGoogleAuthButton = findButton("Enable Google Authenticator");
		assertNotNull("Expect to find a Enable Google Authenticator Button",enableGoogleAuthButton);

		performClickAction(enableGoogleAuthButton);

		closeModal();
	}

	/**
	 * Close modal.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void closeModal() throws Exception {
		final WebElement closeModalWindow = driver.findElement(By.className("v-window-closebox"));
		assertNotNull("Expect to find a Window close Box",closeModalWindow);

		performClickAction(closeModalWindow);

	}


	/**
	 * Grab screenshot.
	 *
	 * @param webDriver
	 *            the web driver
	 */
	private static void grabScreenshot(final WebDriver webDriver)
	{
	    final File scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
	    try {
	    	screenShotNumber = screenShotNumber +1;
	    	final String screenShotName = webDriver.getCurrentUrl().replace(systemTestTargetUrl, "").replaceAll("#", "-").replaceAll("!", "-").replaceAll("/", "-").replaceAll("--", "-");
	        FileUtils.copyFile(scrFile, new File("target/site/screenshots/Page"+screenShotName+ "-" + screenShotNumber+ ".png"));
	    } catch (final IOException e) {
	        e.printStackTrace();
	    }
	}
}
