package com.hack23.cia.systemintegrationtest.user.politician;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandPoliticianRankingConstants;

@Category(IntegrationTest.class)
public final class UserPoliticianTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.COMMAND_POLITICIAN_RANKING);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.COMMAND_POLITICIAN_RANKING);
    }
}
