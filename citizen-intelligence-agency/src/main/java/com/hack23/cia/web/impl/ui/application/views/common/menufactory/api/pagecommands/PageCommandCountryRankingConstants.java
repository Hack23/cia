package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandCountryRankingConstants.
 */
public interface PageCommandCountryRankingConstants {

    /** The command country ranking overview. */
    // Standardize to: COMMAND_[VIEW]_[ACTION]
    PageModeMenuCommand COMMAND_COUNTRY_RANKING_OVERVIEW = new PageModeMenuCommand(
                     UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW);


    /** The command country ranking indicators. */
    PageModeMenuCommand COMMAND_COUNTRY_RANKING_INDICATORS = new PageModeMenuCommand(
                     UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.INDICATORS);


}
