package com.hack23.cia.systemintegrationtest;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

import static com.hack23.cia.systemintegrationtest.TestConstants.*;

@RunWith(Parameterized.class)
@Category(IntegrationTest.class)
public final class BallotViewTest extends AbstractUITest {
    private static final String BALLOT_ID = "A1A613C2-D942-4D5D-AC29-4AE3C4B57486";
    private final Browser browser;

    public BallotViewTest(final Browser browser) {
        this.browser = browser;
    }

    @Override
    protected Browser getBrowser() {
        return browser;
    }

    @Parameters(name = "BallotViewTest{index}: browser({0})")
    public static Collection<Browser[]> browsers() {
        return Arrays.asList(new Browser[][] { { Browser.CHROME } });
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldViewBallotChart() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME,
                        PageMode.CHARTS, BALLOT_ID));
                pageVisit.verifyPageContent("Ballot");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, DEFAULT_MAX_RETRIES);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldViewBallotOverview() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME,
                        PageMode.OVERVIEW, BALLOT_ID));
                pageVisit.verifyPageContent("Ballot");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, DEFAULT_MAX_RETRIES);
    }
}
