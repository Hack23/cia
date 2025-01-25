package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandGovernmentBodyConstants.
 */
public interface PageCommandGovernmentBodyConstants {

    /** The government body command expenditure. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODY_EXPENDITURE = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_VIEW_NAME, "expenditure");

    /** The government body command headcount. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODY_HEADCOUNT = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_VIEW_NAME, "headcount");

    /** The government body command income. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODY_INCOME = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_VIEW_NAME, "income");

    /** The government body command overview. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODY_OVERVIEW = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_VIEW_NAME, PageMode.OVERVIEW);

    /** The command government body headcount. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_HEADCOUNT = new PageModeMenuCommand(
        UserViews.GOVERNMENT_BODY_VIEW_NAME, PageMode.CHARTS, "GOVERNMENT_BODIES_HEADCOUNT");

    /** The command government body income. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_INCOME = new PageModeMenuCommand(
        UserViews.GOVERNMENT_BODY_VIEW_NAME, PageMode.CHARTS, "GOVERNMENT_BODIES_INCOME");

    /** The command government body expenditure. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_EXPENDITURE = new PageModeMenuCommand(
        UserViews.GOVERNMENT_BODY_VIEW_NAME, PageMode.CHARTS, "GOVERNMENT_BODIES_EXPENDITURE");
}
