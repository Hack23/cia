package com.hack23.cia.systemintegrationtest;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.suites.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PartyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

@Category(IntegrationTest.class)
public final class PartyViewTest extends AbstractUITest {
    private static final int MAX_RETRIES = 3;
 
    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldViewPartyCommitteeRoles() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
                        PartyPageMode.COMMITTEEROLES.toString(), "S"));
            } catch (Exception e) {
                throw new RuntimeException("Failed to visit party committee roles page", e);
            }
            pageVisit.verifyPageContent("Committee Roles");
        }, MAX_RETRIES);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldViewPartyDocumentHistory() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
                        PartyPageMode.DOCUMENTHISTORY.toString(), "S"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            pageVisit.verifyPageContent("Document History");
        }, MAX_RETRIES);
    }

    // ... other party view tests
}
