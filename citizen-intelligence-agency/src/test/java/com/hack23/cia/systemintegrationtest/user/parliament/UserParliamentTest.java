package com.hack23.cia.systemintegrationtest.user.parliament;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandParliamentConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandParliamentRankingConstants;

@Category(IntegrationTest.class)
public final class UserParliamentTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyParliamentPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandParliamentConstants.COMMAND_PARLIAMENT);
        pageVisit.validatePage(PageCommandParliamentConstants.COMMAND_PARLIAMENT);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyParliamentRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandParliamentRankingConstants.COMMAND_PARLIAMENT_RANKING);
        pageVisit.validatePage(PageCommandParliamentRankingConstants.COMMAND_PARLIAMENT_RANKING);
    }
}
