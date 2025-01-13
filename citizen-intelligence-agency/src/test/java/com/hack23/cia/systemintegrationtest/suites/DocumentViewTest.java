package com.hack23.cia.systemintegrationtest.suites;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.Browser;
import com.hack23.cia.systemintegrationtest.IntegrationTest;
import com.hack23.cia.systemintegrationtest.UserPageVisit;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

@RunWith(Parameterized.class)
@Category(IntegrationTest.class)
public final class DocumentViewTest extends AbstractUITest {

    private final Browser browser;
    private final UserPageVisit pageVisit;

    public DocumentViewTest(final Browser browser) {
        this.browser = browser;
        this.pageVisit = new UserPageVisit(getWebDriver(), browser);
    }

    @Parameters(name = "DocumentViewTest{index}: browser({0})")
    public static Collection<Browser[]> browsers() {
        return Arrays.asList(new Browser[][] { { Browser.CHROME } });
    }

    @Override
    protected Browser getBrowser() {
        return browser;
    }

    @Test(timeout = 60000)
    public void testDocumentView() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, ""));
                pageVisit.verifyPageContent("Document View");
                pageVisit.selectFirstGridRow();
                pageVisit.validatePage(new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, ""));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, 3);
    }
}
