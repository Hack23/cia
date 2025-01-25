package com.hack23.cia.systemintegrationtest.user.ministry;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandMinistryConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandMinistryRankingConstants;

@Category(IntegrationTest.class)
public final class UserMinistryTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryConstants.COMMAND_MINISTRY);
        pageVisit.validatePage(PageCommandMinistryConstants.COMMAND_MINISTRY);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_MINISTRY_RANKING);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_MINISTRY_RANKING);
    }
}
