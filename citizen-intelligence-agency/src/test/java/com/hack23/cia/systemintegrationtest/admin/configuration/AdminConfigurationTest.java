package com.hack23.cia.systemintegrationtest.admin.configuration;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.admin.AbstractAdminTest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandAdminConstants;

/**
 * The Class AdminConfigurationTest.
 */
@Category(IntegrationTest.class)
public final class AdminConfigurationTest extends AbstractAdminTest {


	/**
	 * Verify portal configuration.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyPortalConfiguration() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_PORTAL);
		pageVisit.verifyViewContent(AdminViewConstants.ADMIN_PORTAL_MANAGEMENT, AdminViewConstants.PORTAL_OVERVIEW,
				AdminViewConstants.PORTAL_MANAGEMENT_OVERVIEW);
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_PORTAL);
	}

	/**
	 * Verify country configuration.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyCountryConfiguration() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_COUNTRY);
		pageVisit.verifyViewContent(AdminViewConstants.ADMIN_COUNTRY_MANAGEMENT, AdminViewConstants.COUNTRY_OVERVIEW,
				AdminViewConstants.COUNTRY_OVERVIEW_DESCRIPTION);
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_COUNTRY);
	}

	/**
	 * Verify language configuration.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyLanguageConfiguration() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_LANGUAGE);
		pageVisit.verifyViewContent(AdminViewConstants.ADMIN_LANGUAGE_MANAGEMENT, AdminViewConstants.LANGUAGE_OVERVIEW,
				AdminViewConstants.LANGUAGE_ADMINISTRATION);
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_LANGUAGE);
	}

	/**
	 * Verify agency configuration.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyAgencyConfiguration() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_AGENCY);
		pageVisit.verifyViewContent(AdminViewConstants.ADMIN_AGENCY_MANAGEMENT, AdminViewConstants.AGENCY_OVERVIEW_HEADER,
				AdminViewConstants.AGENCY_OVERVIEW);
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_AGENCY);
	}

	/**
	 * Verify application configuration.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyApplicationConfiguration() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_CONFIGURATION);
		pageVisit.verifyViewContent(AdminViewConstants.ADMIN_APPLICATION_CONFIGURATION,
				AdminViewConstants.APPLICATION_CONFIGURATION_OVERVIEW,
				AdminViewConstants.APPLICATION_CONFIGURATION_OVERVIEW);
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_CONFIGURATION);
		pageVisit.updateConfigurationProperty("Update Configuration.propertyValue", String.valueOf(false));
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_CONFIGURATION);
	}
}
