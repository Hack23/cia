package com.hack23.cia.systemintegrationtest.user.politician;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPoliticianConstants;
import com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode.PoliticianPageTitleConstants;

/**
 * The Class UserPoliticianTest.
 */
@Category(IntegrationTest.class)
public final class UserPoliticianTest extends AbstractUITest {
	
    /** The pol id. */
    private static final String POL_ID = "0222691314314";

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_VIEW_OVERVIEW.createItemPageCommand(POL_ID));
        pageVisit.verifyViewContent(
            PoliticianPageTitleConstants.OVERVIEW_TITLE,
            PoliticianPageTitleConstants.OVERVIEW_SUBTITLE,
            PoliticianPageTitleConstants.OVERVIEW_DESC);
        pageVisit.validatePage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_VIEW_OVERVIEW.createItemPageCommand(POL_ID));
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianDocumentHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_DOCUMENT_HISTORY.createItemPageCommand(POL_ID));
        pageVisit.verifyViewContent(
            PoliticianPageTitleConstants.DOCUMENT_HISTORY_TITLE,
            PoliticianPageTitleConstants.HISTORY_SUBTITLE,
            PoliticianPageTitleConstants.DOC_HISTORY_DESC);
        pageVisit.validatePage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_DOCUMENT_HISTORY.createItemPageCommand(POL_ID));
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianDocumentActivityPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_DOCUMENT_ACTIVITY.createItemPageCommand(POL_ID));
        pageVisit.verifyViewContent(
            PoliticianPageTitleConstants.DOCUMENT_ACTIVITY_TITLE,
            PoliticianPageTitleConstants.ACTIVITY_SUBTITLE,
            PoliticianPageTitleConstants.DOC_ACTIVITY_DESC);
        pageVisit.validatePage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_DOCUMENT_ACTIVITY.createItemPageCommand(POL_ID));
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianIndicatorsPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_VIEW_INDICATORS.createItemPageCommand(POL_ID));
        pageVisit.verifyViewContent(
            PoliticianPageTitleConstants.INDICATORS_TITLE,
            PoliticianPageTitleConstants.INDICATOR_SUBTITLE,
            PoliticianPageTitleConstants.INDICATORS_DESC);
        pageVisit.validatePage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_VIEW_INDICATORS.createItemPageCommand(POL_ID));
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianVoteHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_VOTE_HISTORY.createItemPageCommand(POL_ID));
        pageVisit.verifyViewContent(
            PoliticianPageTitleConstants.VOTES_HISTORY_TITLE,
            PoliticianPageTitleConstants.VOTES_SUBTITLE,
            PoliticianPageTitleConstants.VOTES_DESC);
        pageVisit.validatePage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_VOTE_HISTORY.createItemPageCommand(POL_ID));
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRoleSummaryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_ROLE_SUMMARY.createItemPageCommand(POL_ID));
        pageVisit.verifyViewContent(
            PoliticianPageTitleConstants.ROLE_SUMMARY_TITLE,
            PoliticianPageTitleConstants.SUMMARY_SUBTITLE,
            PoliticianPageTitleConstants.ROLE_SUMMARY_DESC);
        pageVisit.validatePage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_ROLE_SUMMARY.createItemPageCommand(POL_ID));
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRoleListPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_ROLE_LIST.createItemPageCommand(POL_ID));
        pageVisit.verifyViewContent(
            PoliticianPageTitleConstants.ROLE_LIST_TITLE,
            PoliticianPageTitleConstants.ROLES_SUBTITLE,
            PoliticianPageTitleConstants.ROLE_LIST_DESC);
        pageVisit.validatePage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_ROLE_LIST.createItemPageCommand(POL_ID));
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRoleGhantPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_ROLE_GHANT.createItemPageCommand(POL_ID));
        pageVisit.verifyViewContent(
            PoliticianPageTitleConstants.ROLE_GANTT_TITLE,
            PoliticianPageTitleConstants.GANTT_SUBTITLE,
            PoliticianPageTitleConstants.GANTT_DESC);
        pageVisit.validatePage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_ROLE_GHANT.createItemPageCommand(POL_ID));
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianPageVisitHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_PAGEVISIT.createItemPageCommand(POL_ID));
        pageVisit.verifyViewContent(
            PoliticianPageTitleConstants.PAGEVISIT_TITLE,
            PoliticianPageTitleConstants.PAGEVISIT_SUBTITLE,
            PoliticianPageTitleConstants.PAGEVISIT_DESC);
        pageVisit.validatePage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_PAGEVISIT.createItemPageCommand(POL_ID));
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianBallotHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_BALLOT_HISTORY.createItemPageCommand(POL_ID));
        pageVisit.verifyViewContent(
            PoliticianPageTitleConstants.BALLOT_SUMMARY_TITLE,
            PoliticianPageTitleConstants.BALLOT_SUBTITLE,
            PoliticianPageTitleConstants.BALLOT_DESC);
        pageVisit.validatePage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_BALLOT_HISTORY.createItemPageCommand(POL_ID));
    }
}

