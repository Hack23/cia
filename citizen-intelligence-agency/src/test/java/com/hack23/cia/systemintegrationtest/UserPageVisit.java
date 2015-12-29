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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hack23.cia.model.internal.application.data.impl.DataAgentOperation;
import com.hack23.cia.model.internal.application.data.impl.DataAgentTarget;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

/**
 * The Class UserPageVisit.
 */
public final class UserPageVisit extends Assert {

	/** The Constant WAIT_FOR_PAGE_DELAY. */
	private static final int WAIT_FOR_PAGE_DELAY = 1500;

	/** The Constant WAIT_FOR_PAGE_ELEMENT. */
	private static final int WAIT_FOR_PAGE_ELEMENT = 20000;

	/** The driver. */
	private final WebDriver driver;

	/** The browser. */
	private final String browser;

	/** The action. */
	private final Actions action;

	/** The web driver backed selenium. */
	private WebDriverBackedSelenium webDriverBackedSelenium;

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
	 * Visit start page.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void visitStartPage() throws Exception {
		webDriverBackedSelenium = new WebDriverBackedSelenium(driver,CitizenIntelligenceAgencyServer.ACCESS_URL);
		driver.get(CitizenIntelligenceAgencyServer.ACCESS_URL);

		Thread.sleep(WAIT_FOR_PAGE_DELAY);

		assertEquals(browser, CitizenIntelligenceAgencyServer.ACCESS_URL,
				driver.getCurrentUrl());
		assertEquals(browser, "Citizen Intelligence Agency", driver.getTitle());
		assertNotNull(browser, driver.getWindowHandle());

		verifyViewActions(new ViewAction[] {
				ViewAction.VISIT_ADMIN_AGENT_OPERATION_VIEW,
				ViewAction.VISIT_ADMIN_DATA_SUMMARY_VIEW,
				ViewAction.VISIT_POLITICIAN_RANKING_VIEW,
				ViewAction.VISIT_PARTY_RANKING_VIEW,
				ViewAction.VISIT_MINISTRY_RANKING_VIEW,
				ViewAction.VISIT_COMMITTEE_RANKING_VIEW,
				ViewAction.VISIT_TEST_CHART_VIEW });
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
		//webDriverBackedSelenium = new WebDriverBackedSelenium(driver,CitizenIntelligenceAgencyServer.ACCESS_URL);
		String url = CitizenIntelligenceAgencyServer.ACCESS_URL  +"#!" + page.getPagePath();
		driver.get(url);

		Thread.sleep(WAIT_FOR_PAGE_DELAY);

		int waitTimeForPageLoad=0;
		while (!getActionsAvailable().contains(ViewAction.VISIT_MAIN_VIEW)) {
			Thread.sleep(10);
			waitTimeForPageLoad=waitTimeForPageLoad + 10;
			if (waitTimeForPageLoad > WAIT_FOR_PAGE_ELEMENT) {
				fail("Exceeded timeout for pageload:" + WAIT_FOR_PAGE_ELEMENT);
			}
		}

		final long end = System.currentTimeMillis() + WAIT_FOR_PAGE_ELEMENT;
		while (System.currentTimeMillis() < end && !getActionsAvailable().contains(ViewAction.VISIT_MAIN_VIEW));

		assertTrue("Each page should contain a MainMenu link",getActionsAvailable().contains(ViewAction.VISIT_MAIN_VIEW));


		String text = getHtmlBodyAsText();
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
	 */
	public void validatePage(PageModeMenuCommand page) {
		String url = CitizenIntelligenceAgencyServer.ACCESS_URL  +"#!" + page.getPagePath();


		final long end = System.currentTimeMillis() + WAIT_FOR_PAGE_ELEMENT;
		while (System.currentTimeMillis() < end && !getActionsAvailable().contains(ViewAction.VISIT_MAIN_VIEW));

		assertTrue("Each page should contain a MainMenu link",getActionsAvailable().contains(ViewAction.VISIT_MAIN_VIEW));


		String text = getHtmlBodyAsText();
		assertNotNull(text);
		assertFalse("Page contains exception, url:" + url ,text.contains("Exception"));
		assertFalse("Page contains widget exception, url:" + url,text.contains("Widget"));

		assertEquals(browser, url,
				driver.getCurrentUrl());
		assertNotNull(browser, driver.getWindowHandle());

	}


	/**
	 * Gets the html body as text.
	 *
	 * @return the html body as text
	 */
	public String getHtmlBodyAsText() {
		return driver.findElement(By.tagName("body")).getText();
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
	public WebElement getMenuItem(String... caption) {
		return getMenuItem(getMenuBar(), 1,caption);
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
	private WebElement getMenuItem(WebElement element,int level,String... caption) {
		if (caption.length == level) {
			List<WebElement> findElements = element.findElements(By.className("v-menubar-menuitem-caption"));
			for (WebElement webElement : findElements) {
				if (caption[level -1].equals(webElement.getText())) {
					return webElement;
				}
			}
		} else {
			List<WebElement> findElements = element.findElements(By.className("v-menubar-menuitem-caption"));

			for (WebElement webElement : findElements) {
				if (caption[level -1].equals(webElement.getText())) {
					return getMenuItem(webElement, level +1 ,caption);
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
		List<WebElement> result = new ArrayList<>();
		List<WebElement> nativeButtons = driver.findElements(By.className("v-nativebutton"));
		List<WebElement> buttons = driver.findElements(By.className("v-button"));
		result.addAll(nativeButtons);
		result.addAll(buttons);
		return result;
	}

	/**
	 * Gets the grid rows.
	 *
	 * @return the grid rows
	 */
	public List<WebElement> getGridRows() {
		WebElement gridBody = driver.findElement(By.className("v-grid-body"));
		return gridBody.findElements(By.className("v-grid-row"));

	}

	/**
	 * Gets the grid headers.
	 *
	 * @return the grid headers
	 */
	public List<WebElement> getGridHeaders() {
		WebElement gridBody = driver.findElement(By.className("v-grid-header"));

		return gridBody.findElements(By.className("v-grid-row"));

	}


	/**
	 * Visit test chart view.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void VisitTestChartView() throws Exception {
		final WebElement politicianViewLink = driver.findElement(By
				.id(ViewAction.VISIT_TEST_CHART_VIEW.name()));
		performClickAction(politicianViewLink, WAIT_FOR_PAGE_DELAY);

		assertEquals("http://localhost:8080/#!testchartview",
				driver.getCurrentUrl());

		verifyViewActions(new ViewAction[] {ViewAction.VISIT_MAIN_VIEW });
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
	private void verifyViewActions(final ViewAction[] viewActions) {
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
	 * Visit admin agent operation view.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void visitAdminAgentOperationView() throws Exception {
		final WebElement adminViewLink = driver.findElement(By
				.id(ViewAction.VISIT_ADMIN_AGENT_OPERATION_VIEW.name()));

		performClickAction(adminViewLink, WAIT_FOR_PAGE_DELAY);

		assertEquals("http://localhost:8080/#!" + AdminViews.ADMIN_AGENT_OPERATIONVIEW_NAME,
				driver.getCurrentUrl());

		verifyViewActions(new ViewAction[] {ViewAction.VISIT_MAIN_VIEW,ViewAction.START_AGENT_BUTTON });

		final List<String> actionIdsBy = getActionIdsBy(ViewAction.START_AGENT_BUTTON);
		assertTrue(actionIdsBy.size() > 0);
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
		//		Select operationSelector = new Select(driver.findElement(By.id(ViewAction.StartAgentButton.name() +"/Operation")));
		//		Select targetSelector = new Select(driver.findElement(By.id(ViewAction.StartAgentButton.name() +"/Target")));
		//
		//		targetSelector.selectByValue(target.name());
		//		operationSelector.selectByValue(operation.name());



		final WebElement startButtion = driver.findElement(By
				.id(ViewAction.START_AGENT_BUTTON.name()));

		performClickAction(startButtion, WAIT_FOR_PAGE_DELAY);
	}

	/**
	 * Visit admin data summary view.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void visitAdminDataSummaryView() throws Exception {
		final WebElement adminDataummaryViewLink = driver.findElement(By
				.id(ViewAction.VISIT_ADMIN_DATA_SUMMARY_VIEW.name()));
		performClickAction(adminDataummaryViewLink, WAIT_FOR_PAGE_DELAY);

		assertEquals("http://localhost:8080/#!" + AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME,
				driver.getCurrentUrl());

		verifyViewActions(new ViewAction[] {ViewAction.VISIT_MAIN_VIEW });

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
				ViewAction.VISIT_TEST_CHART_VIEW });
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
		final List<String> idList = new ArrayList<String>();
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

			WebDriverWait wait = new WebDriverWait(driver, WAIT_FOR_PAGE_ELEMENT);
			wait.until(ExpectedConditions.visibilityOf(clickElement));

			action.clickAndHold(clickElement).release().perform();

//			action.moveToElement(clickElement);
//			//action.doubleClick(clickElement);

//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickElement);
//
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			js.executeScript("arguments[0].click();", clickElement);

		}
		Thread.sleep(waitDelay);
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
	public void registerNewUser(String username,String password) throws Exception {

		setFieldValue("Register.username",username);
		setFieldValue("Register.email",username+"@test.com");
		setFieldValue("Register.country","Sweden");
		setFieldValue("Register.userpassword",password);

		WebElement registerButton = driver.findElement(By.id("Register"));
		performClickAction(registerButton);

		String url = CitizenIntelligenceAgencyServer.ACCESS_URL  +"#!" + UserViews.USERHOME_VIEW_NAME;

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
	public void loginUser(String username,String password) throws Exception {

		setFieldValue("username",username);
		setFieldValue("password",password);

		WebElement loginButton =findButton("Login");
		assertNotNull("Expect to find a Login Button",loginButton);

		performClickAction(loginButton);

		String url = CitizenIntelligenceAgencyServer.ACCESS_URL  +"#!" + UserViews.USERHOME_VIEW_NAME;

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
	private WebElement findButton(String buttonLabel) {
		List<WebElement> buttons = getButtons();
		for (WebElement webElement : buttons) {
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
	private void setFieldValue(String id,String value) {
		WebElement findElement = driver.findElement(By.id(id));
		findElement.clear();
		findElement.sendKeys(value);
	}

}
