package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.systemintegrationtest.ui.TestConstants;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;

@Category(IntegrationTest.class)
public final class AdminEmailTest extends AbstractAdminTest {

	@Test(timeout = TestConstants.DEFAULT_TIMEOUT)
	public void shouldSendValidEmail() throws Exception {
		pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_EMAIL_VIEW_NAME, ""));
		pageVisit.verifyPageContent("email");
		pageVisit.sendEmailOnEmailPage("james@hack23.com", "Test Subject", "Test content");
		pageVisit.checkNotificationMessage("Email Sentdesc");

	}

	@Test(timeout = TestConstants.DEFAULT_TIMEOUT)
	public void shouldFailWithInvalidEmail() throws Exception {
		pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_EMAIL_VIEW_NAME, ""));
		pageVisit.verifyPageContent("email");
		pageVisit.sendEmailOnEmailPage("invalidemail", "Test Subject", "Test content");

	}

    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldDisplayEmailConfig() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_EMAIL);
        
        verifyViewContent(
            AdminViewConstants.ADMIN_EMAIL_MANAGEMENT,
            AdminViewConstants.EMAIL_OVERVIEW,
            AdminViewConstants.EMAIL_MANAGEMENT_PAGE_DESCRIPTION
        );
    }
}
