package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;

import com.hack23.cia.systemintegrationtest.ui.TestConstants;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

public final class AdminUserAccountTest extends AbstractAdminTest {

    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldDisplayUserAccounts() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_USERACCOUNT);
        
        verifyViewContent(
            AdminViewConstants.ADMIN_USER_ACCOUNT_MANAGEMENT,
            AdminViewConstants.USER_ACCOUNT_OVERVIEW,
            AdminViewConstants.USER_ACCOUNT_MANAGEMENT_DESCRIPTION
        );
    }
}
