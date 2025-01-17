package com.hack23.cia.systemintegrationtest.user;

import static com.hack23.cia.systemintegrationtest.suites.TestConstants.DEFAULT_MAX_RETRIES;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

@Category(IntegrationTest.class)
public class MinistryViewTest extends AbstractUITest {
    

    @Test(timeout = DEFAULT_TIMEOUT)
    public void testMinistryView() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.OVERVIEW));
                pageVisit.verifyPageContent("Ministries");
                pageVisit.selectFirstGridRow();
                pageVisit.validatePage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.OVERVIEW));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, DEFAULT_MAX_RETRIES);
    }
}
