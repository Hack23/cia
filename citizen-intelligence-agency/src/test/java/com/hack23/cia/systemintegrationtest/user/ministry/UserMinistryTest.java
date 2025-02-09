package com.hack23.cia.systemintegrationtest.user.ministry;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandMinistryConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandUserConstants;

/**
 * The Class UserMinistryRankingTest.
 */
@Category(IntegrationTest.class)
public final class UserMinistryTest extends AbstractUITest implements PageCommandUserConstants {


	/** The ministry id. */
	private final String MINISTRY_ID = "Finansdepartementet";

    /**
     * Verify ministry overview page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryConstants.COMMAND_MINISTRY_OVERVIEW.createItemPageCommand(MINISTRY_ID));
        pageVisit.validatePage(PageCommandMinistryConstants.COMMAND_MINISTRY_OVERVIEW.createItemPageCommand(MINISTRY_ID));
    }
	
}
