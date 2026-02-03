package com.hack23.cia.systemintegrationtest.user.country;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandCountryRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.user.country.pagemode.CountryViewConstants;

/**
 * The Class UserCommonITest.
 */
@Category(IntegrationTest.class)
public final class UserCountryITest extends AbstractUITest {

    /** The Constant INDICATOR_ID. */
    private static final String INDICATOR_ID = "EG.ELC.HYRO.ZS";


	/**
     * Verify country ranking page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCountryRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCountryRankingConstants.COMMAND_COUNTRY_RANKING_OVERVIEW);
        pageVisit.verifyViewContent(CountryViewConstants.RANKING_OVERVIEW_TITLE,
            CountryViewConstants.RANKING_OVERVIEW_SUBTITLE,
            CountryViewConstants.RANKING_OVERVIEW_DESC);
        pageVisit.validatePage(PageCommandCountryRankingConstants.COMMAND_COUNTRY_RANKING_OVERVIEW);
    }


    /**
     * Verify country indicator page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCountryIndicatorPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCountryRankingConstants.COMMAND_COUNTRY_RANKING_INDICATORS.createItemPageCommand(INDICATOR_ID));
        pageVisit.verifyViewContent(CountryViewConstants.INDICATOR_TITLE,
			CountryViewConstants.INDICATOR_SUBTITLE,
			CountryViewConstants.INDICATOR_DESC);
        pageVisit.validatePage(PageCommandCountryRankingConstants.COMMAND_COUNTRY_RANKING_INDICATORS.createItemPageCommand(INDICATOR_ID));
    }


}
