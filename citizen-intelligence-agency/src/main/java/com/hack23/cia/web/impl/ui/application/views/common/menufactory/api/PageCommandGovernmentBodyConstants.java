package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.GovernmentBodyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandGovernmentBodyConstants {
    PageModeMenuCommand GOVERNMENT_BODY_COMMAND_EXPENDITURE = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_VIEW_NAME,
                     GovernmentBodyPageMode.EXPENDITURE.toString());

    PageModeMenuCommand GOVERNMENT_BODY_COMMAND_HEADCOUNT = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_VIEW_NAME,
                     GovernmentBodyPageMode.HEADCOUNT.toString());

    PageModeMenuCommand GOVERNMENT_BODY_COMMAND_INCOME = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_VIEW_NAME,
                     GovernmentBodyPageMode.INCOME.toString());

    PageModeMenuCommand GOVERNMENT_BODY_COMMAND_OVERVIEW = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_VIEW_NAME,
                     PageMode.OVERVIEW);
}
