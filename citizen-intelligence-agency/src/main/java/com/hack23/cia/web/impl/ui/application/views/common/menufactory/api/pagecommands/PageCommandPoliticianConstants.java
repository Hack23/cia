package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandPoliticianConstants.
 */
public interface PageCommandPoliticianConstants {

    /** The command politician ranking overview. */
    PageModeMenuCommand COMMAND_POLITICIAN_RANKING_OVERVIEW = new PageModeMenuCommand(
        UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The command politician view. */
    PageModeMenuCommand COMMAND_POLITICIAN_VIEW = new PageModeMenuCommand(
        UserViews.POLITICIAN_VIEW_NAME, PageMode.OVERVIEW);

    /** The command politician ballot history. */
    PageModeMenuCommand COMMAND_POLITICIAN_BALLOT_HISTORY = new PageModeMenuCommand(
        UserViews.POLITICIAN_VIEW_NAME, "ballothistory");

    /** The command politician document history. */
    PageModeMenuCommand COMMAND_POLITICIAN_DOCUMENT_HISTORY = new PageModeMenuCommand(
        UserViews.POLITICIAN_VIEW_NAME, "documenthistory");

    /** The command politicians link. */
    PageModeMenuCommand COMMAND_POLITICIANS_LINK = new PageModeMenuCommand(
            UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The command politician view overview. */
    PageModeMenuCommand COMMAND_POLITICIAN_VIEW_OVERVIEW = new PageModeMenuCommand(
            UserViews.POLITICIAN_VIEW_NAME, PageMode.OVERVIEW);

        /** The command politician view indicators. */
        PageModeMenuCommand COMMAND_POLITICIAN_VIEW_INDICATORS = new PageModeMenuCommand(
            UserViews.POLITICIAN_VIEW_NAME, PageMode.INDICATORS);

        /** The command politician view roles. */
        PageModeMenuCommand COMMAND_POLITICIAN_VIEW_ROLES = new PageModeMenuCommand(
            UserViews.POLITICIAN_VIEW_NAME, "roles");


}
