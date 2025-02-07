package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.GovernmentBodyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandGovernmentBodyConstants.
 */
public interface PageCommandGovernmentBodyConstants {

    /** The government body command expenditure. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODY_EXPENDITURE = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_VIEW_NAME, GovernmentBodyPageMode.EXPENDITURE.toString());

    /** The government body command headcount. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODY_HEADCOUNT = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_VIEW_NAME, GovernmentBodyPageMode.HEADCOUNT.toString());

    /** The government body command income. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODY_INCOME = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_VIEW_NAME, GovernmentBodyPageMode.INCOME.toString());

    /** The government body command overview. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODY_OVERVIEW = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_VIEW_NAME, PageMode.OVERVIEW);


     /** The command government body pagevisit. */
     PageModeMenuCommand COMMAND_GOVERNMENT_BODY_PAGEVISIT = new PageModeMenuCommand(
            UserViews.GOVERNMENT_BODY_VIEW_NAME, PageMode.PAGEVISITHISTORY);

}
