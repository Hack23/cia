package com.hack23.cia.systemintegrationtest.admin.configuration;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.admin.AbstractAdminTest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

@Category(IntegrationTest.class)
public final class AdminConfigurationTest extends AbstractAdminTest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPortalConfiguration() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_PORTAL);
        verifyViewContent(
            AdminViewConstants.ADMIN_PORTAL_MANAGEMENT,
            AdminViewConstants.PORTAL_OVERVIEW,
            AdminViewConstants.PORTAL_MANAGEMENT_DESCRIPTION
        );
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_PORTAL);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCountryConfiguration() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_COUNTRY);
        verifyViewContent(
            AdminViewConstants.ADMIN_COUNTRY_MANAGEMENT,
            AdminViewConstants.COUNTRY_OVERVIEW,
            AdminViewConstants.COUNTRY_MANAGEMENT_DESCRIPTION
        );
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_COUNTRY);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyLanguageConfiguration() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_LANGUAGE);
        verifyViewContent(
            AdminViewConstants.ADMIN_LANGUAGE_MANAGEMENT,
            AdminViewConstants.LANGUAGE_OVERVIEW,
            AdminViewConstants.LANGUAGE_MANAGEMENT_DESCRIPTION
        );
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_LANGUAGE);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyAgencyConfiguration() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_AGENCY);
        verifyViewContent(
            AdminViewConstants.ADMIN_AGENCY_MANAGEMENT,
            AdminViewConstants.AGENCY_OVERVIEW,
            AdminViewConstants.AGENCY_MANAGEMENT_DESCRIPTION
        );
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_AGENCY);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyApplicationConfiguration() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_CONFIGURATION);
        verifyViewContent(
            AdminViewConstants.ADMIN_APPLICATION_CONFIGURATION,
            AdminViewConstants.APPLICATION_CONFIGURATION_OVERVIEW,
            AdminViewConstants.APPLICATION_CONFIGURATION_DESCRIPTION
        );
        pageVisit.selectFirstGridRow();
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_CONFIGURATION);
        pageVisit.updateConfigurationProperty("Update Configuration.propertyValue", String.valueOf(false));
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_CONFIGURATION);
    }
}
