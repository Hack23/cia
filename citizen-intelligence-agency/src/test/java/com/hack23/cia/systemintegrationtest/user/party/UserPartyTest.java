package com.hack23.cia.systemintegrationtest.user.party;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandPartyConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandPartyRankingConstants;

@Category(IntegrationTest.class)
public final class UserPartyTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_PARTY);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_PARTY);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyRankingConstants.COMMAND_PARTY_RANKING);
        pageVisit.validatePage(PageCommandPartyRankingConstants.COMMAND_PARTY_RANKING);
    }
}
