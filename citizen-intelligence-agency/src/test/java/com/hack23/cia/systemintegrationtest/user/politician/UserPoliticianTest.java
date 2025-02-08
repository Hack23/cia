package com.hack23.cia.systemintegrationtest.user.politician;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPoliticianConstants;

/**
 * The Class UserPoliticianTest.
 */
@Category(IntegrationTest.class)
public final class UserPoliticianTest extends AbstractUITest {
	
	/** The pol id. */
	private final String POL_ID = "0222691314314";

    /**
     * Verify politician overview page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_VIEW_OVERVIEW.createItemPageCommand(POL_ID));
        pageVisit.validatePage(PageCommandPoliticianConstants.COMMAND_POLITICIAN_VIEW_OVERVIEW.createItemPageCommand(POL_ID));
    }

}
