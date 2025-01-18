package com.hack23.cia.systemintegrationtest.user.views;

import org.junit.Test;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandUserConstants;

public class BallotViewTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyBallotView() throws Exception {
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_MAINVIEW_OVERVIEW);
        pageVisit.verifyPageContent("Ballot View");
        pageVisit.validatePage(PageCommandUserConstants.COMMAND_MAINVIEW_OVERVIEW);
    }
}
