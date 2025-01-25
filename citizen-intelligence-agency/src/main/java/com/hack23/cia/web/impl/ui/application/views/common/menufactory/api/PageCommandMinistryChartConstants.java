package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandMinistryChartConstants {
    PageModeMenuCommand COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT = new PageModeMenuCommand(
        UserViews.MINISTRY_VIEW_NAME, PageMode.CHARTS, "ALL_MINISTRIES_BY_HEADCOUNT");
        
    PageModeMenuCommand COMMAND_CHARTS_ALLMINISTRIES_BY_TOTAL_DAYS = new PageModeMenuCommand(
        UserViews.MINISTRY_VIEW_NAME, PageMode.CHARTS, "ALLMINISTRIES_BY_TOTAL_DAYS");
        
    PageModeMenuCommand COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT = new PageModeMenuCommand(
        UserViews.MINISTRY_VIEW_NAME, PageMode.CHARTS, "ALL_GOVERNMENT_ROLE_GANTT");
}
