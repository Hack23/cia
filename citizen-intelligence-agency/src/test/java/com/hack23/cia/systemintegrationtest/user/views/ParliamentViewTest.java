package com.hack23.cia.systemintegrationtest.user.views;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandUserConstants;

@Category(IntegrationTest.class)
public final class ParliamentViewTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyParliamentView() throws Exception {
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_PARLIAMENT_RANKING_OVERVIEW);
        pageVisit.verifyPageContent("Parliament Rankings");
        pageVisit.validatePage(PageCommandUserConstants.COMMAND_PARLIAMENT_RANKING_OVERVIEW);
    }
}
