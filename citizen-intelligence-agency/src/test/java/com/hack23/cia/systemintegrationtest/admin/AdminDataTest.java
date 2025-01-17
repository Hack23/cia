package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.systemintegrationtest.suites.TestConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;

@Category(IntegrationTest.class)
public final class AdminDataTest extends AbstractUITest {

    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldViewAdminData() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));
                pageVisit.verifyPageContent("Admin Data Summary");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, TestConstants.DEFAULT_MAX_RETRIES);
    }
}
