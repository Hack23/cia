package com.hack23.cia.systemintegrationtest.user.governmentbody;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandGovernmentBodyConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandGovernmentBodyRankingConstants;

@Category(IntegrationTest.class)
public final class UserGovernmentBodyTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY);
        pageVisit.validatePage(PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODY_RANKING);
        pageVisit.validatePage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODY_RANKING);
    }
}
