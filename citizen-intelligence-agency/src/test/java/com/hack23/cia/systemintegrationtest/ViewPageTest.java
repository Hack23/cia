package com.hack23.cia.systemintegrationtest;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import static com.hack23.cia.systemintegrationtest.TestConstants.*;

@RunWith(Parameterized.class)
@Category(IntegrationTest.class)
public final class ViewPageTest extends AbstractUITest {
    private static final ViewAction[] VIEW_ACTIONS = {
        ViewAction.VISIT_ADMIN_AGENT_OPERATION_VIEW,
        ViewAction.VISIT_ADMIN_DATA_SUMMARY_VIEW,
        ViewAction.VISIT_POLITICIAN_RANKING_VIEW,
        ViewAction.VISIT_PARTY_RANKING_VIEW,
        ViewAction.VISIT_MINISTRY_RANKING_VIEW,
        ViewAction.VISIT_COMMITTEE_RANKING_VIEW,
        ViewAction.VISIT_COUNTRY_VIEW
    };
    
    private final Browser browser;

    public ViewPageTest(final Browser browser) {
        this.browser = browser;
    }

    @Override
    protected Browser getBrowser() {
        return browser;
    }

    @Parameters(name = "ViewPageTest{index}: browser({0})")
    public static Collection<Browser[]> browsers() {
        return Arrays.asList(new Browser[][] { { Browser.CHROME } });
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldViewMainPage() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.visitMainView();
                pageVisit.verifyViewActions(VIEW_ACTIONS);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, DEFAULT_MAX_RETRIES);
    }

    @Test(timeout = 60000)
    public void shouldViewStartPage() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.visitStartPage();
                pageVisit.verifyPageContent("Citizen Intelligence Agency");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, DEFAULT_MAX_RETRIES);
    }
}
