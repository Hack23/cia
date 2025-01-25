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
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_CHARTS_CURRENT_GOVERNMENT_PARTIES);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_CHARTS_CURRENT_GOVERNMENT_PARTIES);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyRankingConstants.PARTY_RANKING_COMMAND_DATAGRID);
        pageVisit.validatePage(PageCommandPartyRankingConstants.PARTY_RANKING_COMMAND_DATAGRID);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyWinnerPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_CHARTS_PARTY_WINNER);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_CHARTS_PARTY_WINNER);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyGenderPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_CHARTS_PARTY_GENDER);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_CHARTS_PARTY_GENDER);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyAgePage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_CHARTS_PARTY_AGE);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_CHARTS_PARTY_AGE);
    }
}
