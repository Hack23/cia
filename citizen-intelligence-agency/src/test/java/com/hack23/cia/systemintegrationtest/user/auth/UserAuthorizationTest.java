package com.hack23.cia.systemintegrationtest.user.auth;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandUserConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserHomePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

@Category(IntegrationTest.class)
public final class UserAuthorizationTest extends AbstractUITest {
    
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyAdminPageAccess() {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_AGENT_OPERATION);
        pageVisit.verifyPageContent("Access denied");

        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_AUTHOR_DATASUMMARY);
        pageVisit.verifyPageContent("Access denied");
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPublicPageAccess() {
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_MAINVIEW_OVERVIEW);
        pageVisit.verifyPageContent("Welcome");

        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_POLITICIAN_RANKING_OVERVIEW);
        pageVisit.verifyPageContent("Politicians");
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyUserHomeAccess() throws Exception {
        pageVisit.visitDirectPage(
                new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, UserHomePageMode.USER_EVENTS.toString()));
        pageVisit.verifyPageContent("Access denied:userhome");

        pageVisit.visitDirectPage(
                new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, UserHomePageMode.SECURITY_SETTINGS.toString()));
        pageVisit.verifyPageContent("Access denied:userhome");
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMainPageAccess() throws Exception {
        pageVisit.visitMainView();
        pageVisit.verifyViewActions(new ViewAction[] { ViewAction.VISIT_MAIN_VIEW });
        
        pageVisit.visitStartPage();
        pageVisit.verifyPageContent("Citizen Intelligence Agency");
    }
}
