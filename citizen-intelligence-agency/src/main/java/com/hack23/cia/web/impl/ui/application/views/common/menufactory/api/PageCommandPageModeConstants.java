package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;

public interface PageCommandPageModeConstants {
    // Base commands for inheritance
    PageModeMenuCommand COMMAND_OVERVIEW = new PageModeMenuCommand("", PageMode.OVERVIEW);
    PageModeMenuCommand COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand("", PageMode.PAGEVISITHISTORY);
    PageModeMenuCommand COMMAND_DATAGRID = new PageModeMenuCommand("", PageMode.DATAGRID);
}
