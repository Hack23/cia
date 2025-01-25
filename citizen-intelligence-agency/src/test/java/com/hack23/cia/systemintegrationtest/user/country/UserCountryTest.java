package com.hack23.cia.systemintegrationtest.user.country;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandCountryRankingConstants;

@Category(IntegrationTest.class)
public final class UserCountryTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCountryRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCountryRankingConstants.COMMAND_COUNTRY_RANKING);
        pageVisit.validatePage(PageCommandCountryRankingConstants.COMMAND_COUNTRY_RANKING);
    }
}
