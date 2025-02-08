package com.hack23.cia.systemintegrationtest.user.committee;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandCommitteeConstants;

/**
 * The Class UserBallotTest.
 */
@Category(IntegrationTest.class)
public final class UserCommitteeTest extends AbstractUITest {

	/** The committee id. */
	private final String COMMITTEE_ID = "JuU";

    /**
     * Verify committee overview page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCommitteeOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCommitteeConstants.COMMAND_COMMITTEE_OVERVIEW.createItemPageCommand(COMMITTEE_ID));
        pageVisit.validatePage(PageCommandCommitteeConstants.COMMAND_COMMITTEE_OVERVIEW.createItemPageCommand(COMMITTEE_ID));
    }

}
