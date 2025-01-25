package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandMinistryConstants {
    PageModeMenuCommand MINISTRY_COMMAND_CHARTS_CURRENT_BY_HEADCOUNT = new PageModeMenuCommand(
                     UserViews.MINISTRY_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.CURRENTMINISTRIESBYHEADCOUNT.toString());

    PageModeMenuCommand MINISTRY_COMMAND_CHARTS_ALL_BY_HEADCOUNT = new PageModeMenuCommand(
                     UserViews.MINISTRY_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.ALLMINISTRIESBYHEADCOUNT.toString());

    PageModeMenuCommand MINISTRY_COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(
                     UserViews.MINISTRY_VIEW_NAME, PageMode.PAGEVISITHISTORY);
}
