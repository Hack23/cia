package com.hack23.cia.systemintegrationtest.ui;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * The Class ClickHelper.
 */
public class ClickHelper {

	/** The actions. */
	private final Actions actions;

	/** The helper. */
	private final UserPageVisitHelper helper;

	/** The enable screen shot. */
	public static boolean enableScreenShot = false;

	/**
	 * Instantiates a new click helper.
	 *
	 * @param driver the driver
	 * @param helper the helper
	 */
	public ClickHelper(WebDriver driver, UserPageVisitHelper helper) {
		this.actions = new Actions(driver);
		this.helper = helper;
	}

	/**
	 * Perform click on a given element, waiting for it to be clickable.
	 *
	 * @param element the element
	 */
	public void performClick(WebElement element) {
		for (int i = 0; i < 2; i++) {
			try {
				actions.pause(TestConstants.CLICK_PAUSE_AFTER).moveToElement(helper.refreshElement(element))
						.pause(TestConstants.CLICK__MOVE_TO_PAUSE).click().pause(TestConstants.CLICK_PAUSE_AFTER)
						.perform();

				if (enableScreenShot) {
					helper.grabScreenshot();
				}
				return; // success, break out of method

			} catch (final StaleElementReferenceException stale) {
				if (i == 1) {
					// if second attempt fails, re-throw
					throw stale;
				}
			}
		}

	}
}