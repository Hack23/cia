package com.hack23.cia.systemintegrationtest.admin.data;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.hack23.cia.systemintegrationtest.admin.AbstractAdminTest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

@Category(IntegrationTest.class)
public final class AdminDataTest extends AbstractAdminTest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDataSummary() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_DATASUMMARY);
        verifyViewContent(
            AdminViewConstants.ADMIN_DATA_SUMMARY_OVERVIEW,
            AdminViewConstants.DATA_SUMMARY_OVERVIEW
        );
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_DATASUMMARY);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyApplicationEvents() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_EVENTS);
        verifyViewContent(
            AdminViewConstants.ADMIN_APPLICATION_EVENTS,
            AdminViewConstants.APPLICATION_EVENTS_OVERVIEW,
            AdminViewConstants.APPLICATION_EVENTS_DESCRIPTION
        );
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_EVENTS);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyApplicationEventsCharts() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_EVENTS_CHARTS);
        verifyViewContent(
            AdminViewConstants.ADMIN_APPLICATION_EVENT_CHARTS,
            AdminViewConstants.EVENT_ANALYSIS,
            AdminViewConstants.EVENT_DETAILS
        );
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_EVENTS_CHARTS);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyApplicationSessions() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);
        verifyViewContent(
            AdminViewConstants.ADMIN_APPLICATION_SESSION,
            AdminViewConstants.APPLICATION_SESSION_OVERVIEW,
            AdminViewConstants.APPLICATION_SESSION_DESCRIPTION
        );
        pageVisit.selectFirstGridRow();
        verifyViewContent(
            AdminViewConstants.APPLICATION_SESSION_DETAILS,
            AdminViewConstants.SESSION_TYPE,
            AdminViewConstants.USER_ID,
            AdminViewConstants.SESSION_ID
        );
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifySessionCharts() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION_CHARTS);
        verifyViewContent(
            AdminViewConstants.ADMIN_APPLICATION_SESSION_CHARTS,
            AdminViewConstants.SESSION_ANALYSIS
        );
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION_CHARTS);
    }
}
