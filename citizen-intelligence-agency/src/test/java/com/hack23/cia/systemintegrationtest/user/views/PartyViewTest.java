package com.hack23.cia.systemintegrationtest.user.views;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandUserConstants;

@Category(IntegrationTest.class)
public final class PartyViewTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyOverview() throws Exception {
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_PARTY_RANKING_OVERVIEW);
        pageVisit.verifyPageContent("Party Rankings");
        pageVisit.selectFirstGridRow();
        pageVisit.validatePage(PageCommandUserConstants.COMMAND_PARTY_RANKING_OVERVIEW);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyDataGrid() throws Exception {
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_PARTY_RANKING_DATAGRID);
        pageVisit.verifyPageContent("Party Data");
        pageVisit.validatePage(PageCommandUserConstants.COMMAND_PARTY_RANKING_DATAGRID);
    }
}
