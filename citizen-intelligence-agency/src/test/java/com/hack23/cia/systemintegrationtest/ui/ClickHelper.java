package com.hack23.cia.systemintegrationtest.ui;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClickHelper {
    private static final Logger LOG = LoggerFactory.getLogger(ClickHelper.class);

    private final Actions actions;
    private final UserPageVisitHelper helper;

    public ClickHelper(WebDriver driver, UserPageVisitHelper helper) {
        this.actions = new Actions(driver);
        this.helper = helper;
    }

    public void clickWithRetry(WebElement element) {
        try {
            clickElement(element);
        } catch (Exception e) {
            LOG.debug("Regular click failed, trying with delay", e);
            clickWithDelay(element);
        }
    }

    public void clickWithDelay(WebElement element) {
        helper.waitForElement(By.id(element.getDomAttribute("id")));

        actions.pause(Duration.ofMillis(250))
                    .clickAndHold(helper.refreshElement(element))
                    .release()
                    .pause(Duration.ofMillis(250))
                    .perform();

        helper.waitForPageLoad();
        helper.grabScreenshot();
    }

    private void clickElement(WebElement element) {
        helper.waitForElement(By.id(element.getDomAttribute("id")));
        helper.refreshElement(element).click();
        helper.waitForPageLoad();
        helper.grabScreenshot();
    }
}
