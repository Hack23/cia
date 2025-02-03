package com.hack23.cia.systemintegrationtest.user.common;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandMainViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CommonViewConstants;

/**
 * The Class UserCommonTest.
 */
@Category(IntegrationTest.class)
public final class UserCommonTest extends AbstractUITest {

    /**
     * Verify main overview page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMainOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMainViewConstants.COMMAND_MAINVIEW_OVERVIEW);
        pageVisit.verifyViewContent(
            CommonViewConstants.OVERVIEW_TITLE_HEADER,
            CommonViewConstants.OVERVIEW_TITLE,
            CommonViewConstants.OVERVIEW_DESCRIPTION);
        pageVisit.validatePage(PageCommandMainViewConstants.COMMAND_MAINVIEW_OVERVIEW);
    }

    /**
     * Verify page visit history page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPageVisitHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMainViewConstants.COMMAND_MAINVIEW_PAGEVISITHISTORY);
        pageVisit.verifyViewContent(
            CommonViewConstants.VISIT_HISTORY_TITLE_HEADER,
            CommonViewConstants.VISIT_HISTORY_TITLE,
            CommonViewConstants.VISIT_HISTORY_DESCRIPTION);
        pageVisit.validatePage(PageCommandMainViewConstants.COMMAND_MAINVIEW_PAGEVISITHISTORY);
    }

    /**
     * Verify login page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyLoginPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMainViewConstants.COMMAND_LOGIN);
        pageVisit.verifyViewContent(
            CommonViewConstants.LOGIN_TITLE_HEADER,
            CommonViewConstants.LOGIN_TITLE,
            CommonViewConstants.LOGIN_DESCRIPTION);
        pageVisit.validatePage(PageCommandMainViewConstants.COMMAND_LOGIN);
    }

    /**
     * Verify register page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyRegisterPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMainViewConstants.COMMAND_REGISTER);
        pageVisit.verifyViewContent(
            CommonViewConstants.REGISTER_TITLE_HEADER,
            CommonViewConstants.REGISTER_TITLE,
            CommonViewConstants.REGISTER_DESCRIPTION);
        pageVisit.validatePage(PageCommandMainViewConstants.COMMAND_REGISTER);
    }

}
