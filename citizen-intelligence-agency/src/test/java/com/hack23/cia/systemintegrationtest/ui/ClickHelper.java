package com.hack23.cia.systemintegrationtest.ui;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ClickHelper {

    private final Actions actions;
    private final UserPageVisitHelper helper;
    public static boolean enableScreenShot = false;

    public ClickHelper(WebDriver driver, UserPageVisitHelper helper) {
        this.actions = new Actions(driver);
        this.helper = helper;
    }

    public void clickWithRetry(WebElement element) {
    	clickElement(element);
    }

    public void clickWithDelay(WebElement element) {
    	clickElement(element);

    }

    private void clickElement(WebElement element) {
        actions.pause(Duration.ofMillis(250))
                    .clickAndHold(helper.refreshElement(element))
                    .release()
                    .pause(Duration.ofMillis(250))
                    .perform();

        helper.waitForPageLoad();

        if(enableScreenShot) {
			helper.grabScreenshot();
		}
    }
}
