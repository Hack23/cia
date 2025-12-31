package com.hack23.cia.systemintegrationtest.admin.data;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.admin.AbstractAdminTest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.admin.dataquality.pagemode.DataQualityOverviewPageModContentFactoryImpl;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandAdminConstants;

/**
 * The Class AdminDataQualityTest.
 * 
 * Integration tests for the Data Quality Monitoring Dashboard.
 */
@Category(IntegrationTest.class)
public final class AdminDataQualityTest extends AbstractAdminTest {

    /**
     * Verify data quality dashboard.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDataQualityDashboard() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_DATA_QUALITY);
        pageVisit.verifyViewContent(
            AdminViewConstants.ADMIN_DATA_QUALITY,
            AdminViewConstants.DATA_QUALITY_OVERVIEW
        );
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_DATA_QUALITY);
    }

    /**
     * Verify data source status cards are displayed.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDataSourceStatusCards() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_DATA_QUALITY);
        
        // Verify data source names are present
        pageVisit.verifyViewContent(DataQualityOverviewPageModContentFactoryImpl.RIKSDAGEN_API);
        pageVisit.verifyViewContent(DataQualityOverviewPageModContentFactoryImpl.ELECTION_AUTHORITY);
        pageVisit.verifyViewContent(DataQualityOverviewPageModContentFactoryImpl.WORLD_BANK);
        pageVisit.verifyViewContent(DataQualityOverviewPageModContentFactoryImpl.FINANCIAL_AUTHORITY);
    }

    /**
     * Verify quality metrics are displayed.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyQualityMetrics() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_DATA_QUALITY);
        
        // Verify metric names are present
        pageVisit.verifyViewContent(DataQualityOverviewPageModContentFactoryImpl.DATA_FRESHNESS);
        pageVisit.verifyViewContent(DataQualityOverviewPageModContentFactoryImpl.DATA_COMPLETENESS);
        pageVisit.verifyViewContent(DataQualityOverviewPageModContentFactoryImpl.DATA_ACCURACY);
        pageVisit.verifyViewContent(DataQualityOverviewPageModContentFactoryImpl.ACTIVE_ALERTS);
    }

}
