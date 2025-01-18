package com.hack23.cia.systemintegrationtest.user.views;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandUserConstants;

@Category(IntegrationTest.class)
public final class CountryViewTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCountryView() throws Exception {
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_COUNTRY_RANKING_OVERVIEW);
        pageVisit.verifyPageContent("Country Rankings");
        pageVisit.validatePage(PageCommandUserConstants.COMMAND_COUNTRY_RANKING_OVERVIEW);
    }
}
