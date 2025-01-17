package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.systemintegrationtest.suites.TestConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;

@RunWith(Parameterized.class)
@Category(IntegrationTest.class)
public final class AdminAgentTest extends AbstractUITest {


    @Test(timeout = TestConstants.DEFAULT_TIMEOUT, expected = Exception.class)
    public void shouldStartImportOperations() throws Exception {
        try {
            retryOnFailure(() -> {
                try {
                    pageVisit.loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_AGENT_OPERATIONVIEW_NAME, ""));

                    WebElement worldBankButton = pageVisit.findButton("Start IMPORT MODEL_EXTERNAL_WORLDBANK");
                    assertNotNull("WorldBank import button should exist", worldBankButton);

                    WebElement riksdagenButton = pageVisit.findButton("Start IMPORT MODEL_EXTERNAL_RIKSDAGEN");
                    assertNotNull("Riksdagen import button should exist", riksdagenButton);

                    pageVisit.performClickAction(riksdagenButton);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (AssertionError e) {
            throw new Exception("Test failed", e);
        }
    }
}
