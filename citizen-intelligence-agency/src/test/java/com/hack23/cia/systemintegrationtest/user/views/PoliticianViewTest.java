package com.hack23.cia.systemintegrationtest.user.views;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandUserConstants;

@Category(IntegrationTest.class)
public final class PoliticianViewTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianOverview() throws Exception {
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_POLITICIAN_RANKING_OVERVIEW);
        pageVisit.verifyPageContent("Politician Rankings");
        pageVisit.validatePage(PageCommandUserConstants.COMMAND_POLITICIAN_RANKING_OVERVIEW);
    }

    @Test(timeout = DEFAULT_TIMEOUT) 
    public void verifyPoliticianDataGrid() throws Exception {
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_POLITICIAN_RANKING_DATAGRID);
        pageVisit.verifyPageContent("Politician Data");
        pageVisit.validatePage(PageCommandUserConstants.COMMAND_POLITICIAN_RANKING_DATAGRID);
    }
}
