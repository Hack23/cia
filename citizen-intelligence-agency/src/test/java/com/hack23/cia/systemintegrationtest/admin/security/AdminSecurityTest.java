package com.hack23.cia.systemintegrationtest.admin.security;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.admin.AbstractAdminTest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

@Category(IntegrationTest.class)
public final class AdminSecurityTest extends AbstractAdminTest {

    private static final String ACCESS_DENIED = "Access denied";

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyUserAccount() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_USERACCOUNT);
        verifyViewContent(
            AdminViewConstants.ADMIN_USER_ACCOUNT_MANAGEMENT,
            AdminViewConstants.USER_ACCOUNT_OVERVIEW,
            AdminViewConstants.USER_ACCOUNT_MANAGEMENT_DESCRIPTION
        );
        pageVisit.selectFirstGridRow();
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_USERACCOUNT);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifySessionAccess() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);
        pageVisit.verifyPageContent(AdminViewConstants.ADMIN_APPLICATION_SESSION);
        pageVisit.selectFirstGridRow();
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyAnonymousAccess() throws Exception {
        pageVisit.logoutUser();
        
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_USERACCOUNT);
        pageVisit.verifyPageContent(ACCESS_DENIED);
        
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_MONITORING);
        pageVisit.verifyPageContent(ACCESS_DENIED);
        
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_CONFIGURATION);
        pageVisit.verifyPageContent(ACCESS_DENIED);
    }
}
