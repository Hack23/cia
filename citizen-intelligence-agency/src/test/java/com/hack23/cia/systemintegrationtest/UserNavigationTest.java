package com.hack23.cia.systemintegrationtest;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

@Category(IntegrationTest.class)
public final class UserNavigationTest extends AbstractUITest {
    private static final int MAX_RETRIES = 3;
    // Constants for page modes
    private static final String DOCUMENT_ACTIVITY = "DOCUMENTACTIVITY";
    private static final String ROLE_SUMMARY = "ROLESUMMARY";
    private static final String DECISION_FLOW_CHART = "DECISION_FLOW_CHART";
    private static final String RISK_SUMMARY = "RISK_SUMMARY";

    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldNavigatePoliticianViews() throws Exception {
        retryOnFailure(() -> {
            try {
                String politicianId = "0980681611418";

                // Overview
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                        PageMode.OVERVIEW, politicianId));
                pageVisit.verifyPageContent("Politician");

                // Document Activity
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                        DOCUMENT_ACTIVITY, politicianId));
                pageVisit.verifyPageContent("Document Activity");

                // Role Summary
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                        ROLE_SUMMARY, politicianId));
                pageVisit.verifyPageContent("Role Summary");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, MAX_RETRIES);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldNavigateParliamentViews() throws Exception {
        retryOnFailure(() -> {
            try {
                // Overview
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                        PageMode.OVERVIEW));
                pageVisit.verifyPageContent("Parliament");

                // Decision Flow Chart
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                        PageMode.CHARTS, DECISION_FLOW_CHART));
                pageVisit.verifyPageContent("Decision Flow");

                // Risk Summary
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                        PageMode.RULES, RISK_SUMMARY));
                pageVisit.selectFirstGridRow();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, MAX_RETRIES);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
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

    @Test(timeout = DEFAULT_TIMEOUT)
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

    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldNavigatePartyRanking() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.VisitPartyRankingView();
                pageVisit.verifyPageContent("Party Rankings");

                List<String> actionIdsBy = pageVisit.getActionIdsBy(ViewAction.VISIT_PARTY_VIEW);
                assertTrue(actionIdsBy.size() > 0);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, MAX_RETRIES);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
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

    // Other navigation tests...
}
