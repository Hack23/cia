package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandPartyConstants {
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_GOVERNMENT_PARTIES = new PageModeMenuCommand(
                     UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,
                     ChartIndicators.CURRENTGOVERMENTPARTIES.toString());

    PageModeMenuCommand COMMAND_CHARTS_PARTY_WINNER = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.PARTYWINNER.toString());

    PageModeMenuCommand COMMAND_CHARTS_PARTY_GENDER = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.PARTYGENDER.toString());

    PageModeMenuCommand COMMAND_CHARTS_PARTY_AGE = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.PARTYAGE.toString());
}
