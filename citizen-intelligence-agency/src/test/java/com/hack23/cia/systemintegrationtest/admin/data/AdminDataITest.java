package com.hack23.cia.systemintegrationtest.admin.data;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.hack23.cia.systemintegrationtest.admin.AbstractAdminTest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.admin.datasummary.pagemode.DataSummaryOverviewPageModContentFactoryImpl;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandAdminConstants;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.RefreshDataViewsClickListener;

/**
 * The Class AdminDataITest.
 */
@Category(IntegrationTest.class)
public final class AdminDataITest extends AbstractAdminTest {

    /**
     * Verify data summary.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDataSummary() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_DATASUMMARY);
        pageVisit.verifyViewContent(
            AdminViewConstants.ADMIN_DATA_SUMMARY_OVERVIEW,
            AdminViewConstants.DATA_SUMMARY_OVERVIEW
        );
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_DATASUMMARY);

    	final WebElement refreshViewsButton = pageVisit.findButton(DataSummaryOverviewPageModContentFactoryImpl.REFRESH_VIEWS);
		assertNotNull(DataSummaryOverviewPageModContentFactoryImpl.REFRESH_VIEWS,refreshViewsButton);

		pageVisit.performClickAction(refreshViewsButton);
		pageVisit.checkNotificationMessage(RefreshDataViewsClickListener.REFRESH_VIEWS_STARTED + RefreshDataViewsClickListener.REFRESH_DESC);
    }


    /**
     * Verify data summary author.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDataSummaryAuthor() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_AUTHOR_DATASUMMARY);
        pageVisit.verifyViewContent(
            AdminViewConstants.ADMIN_AUTHOR_SUMMARY,
            AdminViewConstants.AUTHOR_OVERVIEW, AdminViewConstants.AUTHOR_DETAILED_SUMMARY
        );
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_AUTHOR_DATASUMMARY);

    }

    /**
     * Verify application events.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyApplicationEvents() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_EVENTS);
        pageVisit.verifyViewContent(
        		AdminViewConstants.ADMIN_APPLICATION_EVENTS, AdminViewConstants.EVENT_DETAILS,
				AdminViewConstants.EVENT_REVIEW
        );
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_EVENTS);
		pageVisit.selectFirstGridRow();
		pageVisit.verifyViewContent(AdminViewConstants.EVENT_GROUP);

    }

    /**
     * Verify application events charts.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyApplicationEventsCharts() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_EVENTS_CHARTS);
        pageVisit.verifyViewContent(
        		AdminViewConstants.ADMIN_APPLICATION_EVENT_CHARTS, AdminViewConstants.EVENT_ANALYSIS_HEADER, AdminViewConstants.EVENT_ANALYSIS
        );
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_EVENTS_CHARTS);
    }

    /**
     * Verify application sessions.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyApplicationSessions() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);
        pageVisit.verifyViewContent(
        		 AdminViewConstants.ADMIN_APPLICATION_SESSIONS,
				 AdminViewConstants.SESSION_DETAILS,
				 AdminViewConstants.SESSION_OVERVIEW
		);
        pageVisit.selectFirstGridRow();
        pageVisit.verifyViewContent(
            AdminViewConstants.APPLICATION_SESSION_DETAILS,
            AdminViewConstants.SESSION_TYPE,
            AdminViewConstants.USER_ID,
            AdminViewConstants.SESSION_ID
        );
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);
    }

    /**
     * Verify session charts.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifySessionCharts() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION_CHARTS);
        pageVisit.verifyViewContent(
            AdminViewConstants.ADMIN_APPLICATION_SESSION_CHARTS,
            AdminViewConstants.SESSION_ANALYSIS
        );
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION_CHARTS);
    }
}
