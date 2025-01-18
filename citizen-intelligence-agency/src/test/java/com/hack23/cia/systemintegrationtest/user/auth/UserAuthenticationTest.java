package com.hack23.cia.systemintegrationtest.user.auth;


import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandUserConstants;

@Category(IntegrationTest.class)
public final class UserAuthenticationTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyLoginFlow() throws Exception {
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_LOGIN);
        pageVisit.verifyPageContent("Login");
        pageVisit.loginUser("username", "password");
        pageVisit.verifyPageContent("Logged in");
    }
    
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyLogoutFlow() throws Exception {
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_LOGOUT);
        pageVisit.verifyPageContent("Logged out");
    }

    @Test(timeout = DEFAULT_TIMEOUT) 
    public void verifyAuthenticationFlow() throws Exception {
        // Login flow
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_LOGIN);
        pageVisit.verifyPageContent("Login");
        pageVisit.loginUser("username", "password");

        // Userhome access after login
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_USERHOME);
        pageVisit.verifyPageContent("User Home");
        
        // Logout flow
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_LOGOUT);
        pageVisit.verifyPageContent("Logged out");
    }

    // ...existing registration tests...
}
