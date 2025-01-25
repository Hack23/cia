package com.hack23.cia.systemintegrationtest.user.committee;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandCommitteeConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandCommitteeRankingConstants;

@Category(IntegrationTest.class)
public final class UserCommitteeTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCommitteePage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCommitteeConstants.COMMAND_COMMITTEE);
        pageVisit.validatePage(PageCommandCommitteeConstants.COMMAND_COMMITTEE);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCommitteeRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCommitteeRankingConstants.COMMAND_COMMITTEE_RANKING);
        pageVisit.validatePage(PageCommandCommitteeRankingConstants.COMMAND_COMMITTEE_RANKING);
    }
}
