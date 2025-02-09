package com.hack23.cia.systemintegrationtest.user.party;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPartyConstants;

/**
 * The Class UserPartyRankingTest.
 */
@Category(IntegrationTest.class)
public final class UserPartyTest extends AbstractUITest {

	/** The party id. */
	private final String PARTY_ID = "M";

    /**
     * Verify party overview page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_PARTY_OVERVIEW.createItemPageCommand(PARTY_ID));
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_PARTY_OVERVIEW.createItemPageCommand(PARTY_ID));
    }


}
