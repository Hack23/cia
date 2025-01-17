package com.hack23.cia.systemintegrationtest.user;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

@Category(IntegrationTest.class)
public final class CommitteeViewTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void testCommitteeView() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, ""));
                pageVisit.verifyPageContent("Committee View");
                pageVisit.selectFirstGridRow();
                pageVisit.validatePage(new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, ""));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, 3);
    }
}