package com.hack23.cia.systemintegrationtest.user.ministry;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandMinistryConstants;
import com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode.MinistryPageModeConstants;
import com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode.MinistryViewConstants;

/**
 * The Class UserMinistryTest.
 */
@Category(IntegrationTest.class)
public final class UserMinistryTest extends AbstractUITest {

    /** The Constant MINISTRY_ID. */
    private static final String MINISTRY_ID = "Finansdepartementet";

    /**
     * Verify ministry overview page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryConstants.COMMAND_MINISTRY_OVERVIEW.createItemPageCommand(MINISTRY_ID));
        pageVisit.verifyViewContent(
            MinistryViewConstants.OVERVIEW_TITLE,
            MinistryViewConstants.OVERVIEW_SUBTITLE,
            MinistryViewConstants.OVERVIEW_DESC);
        pageVisit.validatePage(PageCommandMinistryConstants.COMMAND_MINISTRY_OVERVIEW.createItemPageCommand(MINISTRY_ID));
    }

    /**
     * Verify ministry current members page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryCurrentMembersPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryConstants.COMMAND_MINISTRY_CURRENT_MEMBERS.createItemPageCommand(MINISTRY_ID));
        pageVisit.verifyViewContent(
            MinistryViewConstants.CURRENT_MEMBERS_TITLE,
            MinistryViewConstants.CURRENT_MEMBERS_SUBTITLE,
            MinistryViewConstants.CURRENT_MEMBERS_DESC);
        pageVisit.validatePage(PageCommandMinistryConstants.COMMAND_MINISTRY_CURRENT_MEMBERS.createItemPageCommand(MINISTRY_ID));
    }

    /**
     * Verify ministry member history page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryMemberHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryConstants.COMMAND_MINISTRY_MEMBER_HISTORY.createItemPageCommand(MINISTRY_ID));
        pageVisit.verifyViewContent(
        		MinistryPageModeConstants.MEMBER_HISTORY_TITLE,
    			MinistryPageModeConstants.MEMBER_HISTORY_SUBTITLE,
    			MinistryPageModeConstants.MEMBER_HISTORY_DESC);
        pageVisit.validatePage(PageCommandMinistryConstants.COMMAND_MINISTRY_MEMBER_HISTORY.createItemPageCommand(MINISTRY_ID));
    }

    /**
     * Verify ministry role ghant page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryRoleGhantPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryConstants.COMMAND_MINISTRY_ROLE_GHANT.createItemPageCommand(MINISTRY_ID));
        pageVisit.verifyViewContent(
            MinistryViewConstants.ROLE_GHANT_TITLE,
            MinistryViewConstants.ROLE_GHANT_SUBTITLE,
            MinistryViewConstants.ROLE_GHANT_DESC);
        pageVisit.validatePage(PageCommandMinistryConstants.COMMAND_MINISTRY_ROLE_GHANT.createItemPageCommand(MINISTRY_ID));
    }

    /**
     * Verify ministry document history page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryDocumentHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryConstants.COMMAND_MINISTRY_DOCUMENT_HISTORY.createItemPageCommand(MINISTRY_ID));
        pageVisit.verifyViewContent(
            MinistryViewConstants.DOCUMENT_HISTORY_TITLE,
            MinistryViewConstants.DOCUMENT_HISTORY_SUBTITLE,
            MinistryViewConstants.DOCUMENT_HISTORY_DESC);
        pageVisit.validatePage(PageCommandMinistryConstants.COMMAND_MINISTRY_DOCUMENT_HISTORY.createItemPageCommand(MINISTRY_ID));
    }

    /**
     * Verify ministry document activity page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryDocumentActivityPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryConstants.COMMAND_MINISTRY_DOCUMENT_ACTIVITY.createItemPageCommand(MINISTRY_ID));
        pageVisit.verifyViewContent(
            MinistryViewConstants.DOCUMENT_ACTIVITY_TITLE,
            MinistryViewConstants.DOCUMENT_ACTIVITY_SUBTITLE,
            MinistryViewConstants.DOCUMENT_ACTIVITY_DESC);
        pageVisit.validatePage(PageCommandMinistryConstants.COMMAND_MINISTRY_DOCUMENT_ACTIVITY.createItemPageCommand(MINISTRY_ID));
    }

    /**
     * Verify ministry page visit history page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryPageVisitHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryConstants.COMMAND_MINISTRY_PAGEVISIT_HISTORY.createItemPageCommand(MINISTRY_ID));
        pageVisit.verifyViewContent(
            MinistryViewConstants.PAGEVISIT_TITLE,
            MinistryViewConstants.PAGEVISIT_SUBTITLE,
            MinistryViewConstants.PAGEVISIT_DESC);
        pageVisit.validatePage(PageCommandMinistryConstants.COMMAND_MINISTRY_PAGEVISIT_HISTORY.createItemPageCommand(MINISTRY_ID));
    }

    /**
     * Verify ministry government bodies headcount page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryGovernmentBodiesHeadcountPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryConstants.COMMAND_MINISTRY_GOVERNMENT_BODIES_HEADCOUNT.createItemPageCommand(MINISTRY_ID));
        pageVisit.verifyViewContent(
            MinistryViewConstants.HEADCOUNT_TITLE,
            MinistryViewConstants.HEADCOUNT_SUBTITLE,
            MinistryViewConstants.HEADCOUNT_DESC);
        pageVisit.validatePage(PageCommandMinistryConstants.COMMAND_MINISTRY_GOVERNMENT_BODIES_HEADCOUNT.createItemPageCommand(MINISTRY_ID));
    }

    /**
     * Verify ministry government bodies income page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryGovernmentBodiesIncomePage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryConstants.COMMAND_MINISTRY_GOVERNMENT_BODIES_INCOME.createItemPageCommand(MINISTRY_ID));
        pageVisit.verifyViewContent(
            MinistryViewConstants.INCOME_TITLE,
            MinistryViewConstants.INCOME_SUBTITLE,
            MinistryViewConstants.INCOME_DESC);
        pageVisit.validatePage(PageCommandMinistryConstants.COMMAND_MINISTRY_GOVERNMENT_BODIES_INCOME.createItemPageCommand(MINISTRY_ID));
    }

    /**
     * Verify ministry government bodies expenditure page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryGovernmentBodiesExpenditurePage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryConstants.COMMAND_MINISTRY_GOVERNMENT_BODIES_EXPENDITURE.createItemPageCommand(MINISTRY_ID));
        pageVisit.verifyViewContent(
            MinistryViewConstants.EXPENDITURE_TITLE,
            MinistryViewConstants.EXPENDITURE_SUBTITLE,
            MinistryViewConstants.EXPENDITURE_DESC);
        pageVisit.validatePage(PageCommandMinistryConstants.COMMAND_MINISTRY_GOVERNMENT_BODIES_EXPENDITURE.createItemPageCommand(MINISTRY_ID));
    }


}
