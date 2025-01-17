package com.hack23.cia.systemintegrationtest;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.hack23.cia.systemintegrationtest.suites.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

@Category(IntegrationTest.class)
public final class BallotViewTest extends AbstractUITest {

 
    @Test(timeout = DEFAULT_TIMEOUT)
    public void testBallotView() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME, ""));
                pageVisit.verifyPageContent("Ballot View");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, 3);
    }
}
