package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserHomePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandUserHomeConstants.
 */
public interface PageCommandUserHomeConstants {

    /** The command userhome overview. */
    PageModeMenuCommand COMMAND_USERHOME_OVERVIEW = new PageModeMenuCommand(
                     UserViews.USERHOME_VIEW_NAME, PageMode.OVERVIEW);

    /** The command userhome security settings. */
    PageModeMenuCommand COMMAND_USERHOME_SECURITY_SETTINGS = new PageModeMenuCommand(
                     UserViews.USERHOME_VIEW_NAME, UserHomePageMode.SECURITY_SETTINGS.toString());

    /** The command userhome user visits. */
    PageModeMenuCommand COMMAND_USERHOME_USER_VISITS = new PageModeMenuCommand(
                     UserViews.USERHOME_VIEW_NAME, UserHomePageMode.USER_VISITS.toString());

    /** The command userhome user events. */
    PageModeMenuCommand COMMAND_USERHOME_USER_EVENTS = new PageModeMenuCommand(
                     UserViews.USERHOME_VIEW_NAME, UserHomePageMode.USER_EVENTS.toString());

}
