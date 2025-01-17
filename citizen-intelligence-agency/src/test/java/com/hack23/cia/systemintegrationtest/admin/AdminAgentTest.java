package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.hack23.cia.systemintegrationtest.ui.TestConstants;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

public final class AdminAgentTest extends AbstractAdminTest {

    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldStartImportOperations() throws Exception {

        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_AGENT_OPERATION);
        
        verifyViewContent(
            AdminViewConstants.ADMIN_AGENT_OPERATION_OVERVIEW,
            AdminViewConstants.ADMIN_AGENT_OPERATION,
            AdminViewConstants.ADMIN_AGENT_OPERATION_TASKS_OVERVIEW
        );

        final WebElement worldBankButton = pageVisit
                .findButton(AdminViewConstants.ADMIN_AGENT_OPERATION_DEPLOY_WORLD_BUTTON);
        assertNotNull("World bank button should exist", worldBankButton);

        final WebElement swedenButton = pageVisit
                .findButton(AdminViewConstants.ADMIN_AGENT_OPERATION_DEPLOY_SWEDEN_BUTTON);
        assertNotNull("Sweden button should exist", swedenButton);

        pageVisit.performClickAction(swedenButton);
        
        // Verify operation started
        pageVisit.verifyPageContent(AdminViewConstants.ADMIN_AGENT_OPERATION_DEPLOY_DESC);
    }
}
