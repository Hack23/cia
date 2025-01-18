package com.hack23.cia.systemintegrationtest.user.navigation;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandUserConstants;

@Category(IntegrationTest.class)
public final class UserNavigationTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMainNavigation() throws Exception {
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_MAINVIEW_OVERVIEW);
        pageVisit.verifyPageContent("Main View");
        
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_DASHBOARDVIEW_OVERVIEW);
        pageVisit.verifyPageContent("Dashboard");
    }
    
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyUserHomeNavigation() throws Exception {
        pageVisit.loginAsAdmin(); // Ensure proper access
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_USERHOME);
        pageVisit.verifyPageContent("User Home");
    }
}
