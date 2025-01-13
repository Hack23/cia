package com.hack23.cia.systemintegrationtest.suites;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.Browser;
import com.hack23.cia.systemintegrationtest.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

@Category(IntegrationTest.class)
public class SearchViewTest extends AbstractUITest {
    private static final int DEFAULT_MAX_RETRIES = 3;

    @Override
    protected Browser getBrowser() {
        return Browser.CHROME;
    }

    @Test(timeout = 60000)
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
