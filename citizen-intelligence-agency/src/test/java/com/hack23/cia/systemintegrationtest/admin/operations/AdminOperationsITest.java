package com.hack23.cia.systemintegrationtest.admin.operations;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.hack23.cia.systemintegrationtest.admin.AbstractAdminTest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandAdminConstants;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.SendEmailClickListener;

/**
 * The Class AdminOperationsITest.
 */
@Category(IntegrationTest.class)
public final class AdminOperationsITest extends AbstractAdminTest {

    /**
     * Verify agent operation.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyAgentOperation() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_AGENT_OPERATION);
        pageVisit.verifyViewContent(
            AdminViewConstants.ADMIN_AGENT_OPERATION_OVERVIEW,
            AdminViewConstants.ADMIN_AGENT_OPERATION,
            AdminViewConstants.ADMIN_AGENT_OPERATION_TASKS_OVERVIEW
        );
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_AGENT_OPERATION);

		final WebElement importSwedenDataButton =pageVisit.findButton(AdminViewConstants.ADMIN_AGENT_OPERATION_DEPLOY_SWEDEN_BUTTON);
		assertNotNull(AdminViewConstants.ADMIN_AGENT_OPERATION_DEPLOY_SWEDEN_BUTTON,importSwedenDataButton);

		final WebElement importWorldDataButton =pageVisit.findButton(AdminViewConstants.ADMIN_AGENT_OPERATION_DEPLOY_WORLD_BUTTON);
		assertNotNull(AdminViewConstants.ADMIN_AGENT_OPERATION_DEPLOY_WORLD_BUTTON,importWorldDataButton);

		pageVisit.performClickAction(importSwedenDataButton);
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_AGENT_OPERATION);
    }

    /**
     * Verify email operation failed.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyEmailOperationFailed() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_EMAIL);
        pageVisit.verifyPageContent(AdminViewConstants.ADMIN_EMAIL_MANAGEMENT);
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_EMAIL);

        pageVisit.sendEmailOnEmailPage("nonvalidemail", "siteAdminEmailFailedNoValidEmailTest", "siteAdminEmailFailedNoValidEmailTest content");
    }


    /**
     * Verify email operation success.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyEmailOperationSuccess() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_EMAIL);
        pageVisit.verifyPageContent(AdminViewConstants.ADMIN_EMAIL_MANAGEMENT);
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_EMAIL);

        pageVisit.sendEmailOnEmailPage("james@hack23.com", "siteAdminEmailTest", "siteAdminEmailTest content");
        pageVisit.checkNotificationMessage(SendEmailClickListener.EMAIL_SENT + SendEmailClickListener.EMAIL_DESC);
    }

    /**
     * Verify monitoring operation.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMonitoringOperation() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_MONITORING);
        pageVisit.verifyPageContent(AdminViewConstants.ADMIN_MONITORING);
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_MONITORING);
    }
}
