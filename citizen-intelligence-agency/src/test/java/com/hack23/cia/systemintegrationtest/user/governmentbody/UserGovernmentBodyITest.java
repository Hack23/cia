package com.hack23.cia.systemintegrationtest.user.governmentbody;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandGovernmentBodyConstants;
import com.hack23.cia.web.impl.ui.application.views.common.constants.GovernmentBodyDescriptionConstants;

/**
 * The Class UserGovernmentBodyITest.
 */
@Category(IntegrationTest.class)
public final class UserGovernmentBodyITest extends AbstractUITest {

    /** The Constant GOV_BODY_ID. */
    private static final String GOV_BODY_ID = "202100-4979";

    /**
     * Verify government body overview page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_OVERVIEW.createItemPageCommand(GOV_BODY_ID));
        pageVisit.verifyViewContent(
                GovernmentBodyDescriptionConstants.OVERVIEW_HEADER,
                GovernmentBodyDescriptionConstants.OVERVIEW_SUBTITLE,
                GovernmentBodyDescriptionConstants.OVERVIEW_DESC);
        pageVisit.validatePage(PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_OVERVIEW.createItemPageCommand(GOV_BODY_ID));
    }

    /**
     * Verify government body headcount page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyHeadcountPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_HEADCOUNT.createItemPageCommand(GOV_BODY_ID));
        pageVisit.verifyViewContent(
                GovernmentBodyDescriptionConstants.HEADCOUNT_HEADER,
                GovernmentBodyDescriptionConstants.HEADCOUNT_SUBTITLE,
                GovernmentBodyDescriptionConstants.HEADCOUNT_DESC);
        pageVisit.validatePage(PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_HEADCOUNT.createItemPageCommand(GOV_BODY_ID));
    }

    /**
     * Verify government body income page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyIncomePage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_INCOME.createItemPageCommand(GOV_BODY_ID));
        pageVisit.verifyViewContent(
                GovernmentBodyDescriptionConstants.INCOME_HEADER,
                GovernmentBodyDescriptionConstants.INCOME_SUBTITLE,
                GovernmentBodyDescriptionConstants.INCOME_DESC);
        pageVisit.validatePage(PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_INCOME.createItemPageCommand(GOV_BODY_ID));
    }

    /**
     * Verify government body expenditure page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyExpenditurePage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_EXPENDITURE.createItemPageCommand(GOV_BODY_ID));
        pageVisit.verifyViewContent(
                GovernmentBodyDescriptionConstants.EXPENDITURE_HEADER,
                GovernmentBodyDescriptionConstants.EXPENDITURE_SUBTITLE,
                GovernmentBodyDescriptionConstants.EXPENDITURE_DESC);
        pageVisit.validatePage(PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_EXPENDITURE.createItemPageCommand(GOV_BODY_ID));
    }

    /**
     * Verify government body page visit page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyPageVisitPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_PAGEVISIT.createItemPageCommand(GOV_BODY_ID));
        pageVisit.verifyViewContent(
                GovernmentBodyDescriptionConstants.VISIT_HISTORY_HEADER,
                GovernmentBodyDescriptionConstants.VISIT_HISTORY_SUBTITLE,
                GovernmentBodyDescriptionConstants.VISIT_HISTORY_DESC);
        pageVisit.validatePage(PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_PAGEVISIT.createItemPageCommand(GOV_BODY_ID));
    }

}
