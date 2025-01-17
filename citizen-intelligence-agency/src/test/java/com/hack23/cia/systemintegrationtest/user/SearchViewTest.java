package com.hack23.cia.systemintegrationtest;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import static com.hack23.cia.systemintegrationtest.TestConstants.DEFAULT_MAX_RETRIES;


import com.hack23.cia.systemintegrationtest.suites.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

@Category(IntegrationTest.class)
public class SearchViewTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void testSearch() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.SEARCH_DOCUMENT_VIEW_NAME, ""));
                pageVisit.verifyPageContent("Search");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, DEFAULT_MAX_RETRIES);
    }
}
