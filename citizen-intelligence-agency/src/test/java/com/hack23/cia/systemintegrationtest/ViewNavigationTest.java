package com.hack23.cia.systemintegrationtest;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PoliticianPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

import static com.hack23.cia.systemintegrationtest.TestConstants.*;

@RunWith(Parameterized.class)
@Category(IntegrationTest.class)
public final class ViewNavigationTest extends AbstractUITest {
    private static final int MAX_RETRIES = 3;
    private final Browser browser;

    public ViewNavigationTest(final Browser browser) {
        this.browser = browser;
    }

    @Override
    protected Browser getBrowser() {
        return browser;
    }

    @Parameters(name = "ViewNavTest{index}: browser({0})")
    public static Collection<Browser[]> browsers() {
        return Arrays.asList(new Browser[][] { { Browser.CHROME } });
    }

    @Test(timeout = 60000)
    public void shouldNavigateCommitteeRanking() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.VisitCommitteeRankingView();
                pageVisit.verifyPageContent("Committee Rankings");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, MAX_RETRIES);
    }

    @Test(timeout = 60000)
    public void shouldNavigateMinistryRanking() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.VisitMinistryRankingView();
                pageVisit.verifyPageContent("Ministry Rankings");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, MAX_RETRIES);
    }

    @Test(timeout = 60000)
    public void shouldNavigatePartyRanking() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.VisitPartyRankingView();
                pageVisit.verifyPageContent("Party Rankings");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, MAX_RETRIES);
    }

    @Test(timeout = 60000)
    public void shouldNavigatePoliticianRanking() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.VisitPoliticianRankingView();
                pageVisit.verifyPageContent("Politician Rankings");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, MAX_RETRIES);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldNavigatePoliticianViews() throws Exception {
        retryOnFailure(() -> {
            try {
                String politicianId = "0980681611418";
                
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                        PageMode.OVERVIEW.toString(), politicianId));
                pageVisit.verifyPageContent("Politician");

                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                        PoliticianPageMode.DOCUMENTACTIVITY.toString(), politicianId));
                pageVisit.verifyPageContent("Document Activity");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, DEFAULT_MAX_RETRIES);
    }
}
