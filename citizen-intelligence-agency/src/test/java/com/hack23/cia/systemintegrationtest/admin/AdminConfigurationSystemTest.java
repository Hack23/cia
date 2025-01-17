package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

@Category(IntegrationTest.class)
public class AdminConfigurationSystemTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyLanguageConfigurationTest() throws Exception {
        pageVisit.loginAsAdmin();
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_LANGUAGE);
        pageVisit.verifyPageContent("Language");
        pageVisit.selectFirstGridRow();
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_LANGUAGE);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCountryConfigurationTest() throws Exception {
        pageVisit.loginAsAdmin();
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_COUNTRY);
        pageVisit.verifyPageContent("Country");
        pageVisit.selectFirstGridRow();
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_COUNTRY);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPortalConfigurationTest() throws Exception {
        pageVisit.loginAsAdmin();
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_PORTAL);
        pageVisit.verifyPageContent("Portal Configuration");
        pageVisit.selectFirstGridRow();
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_PORTAL);
    }
}
