package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode; // updated import
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PoliticianPageMode;
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
        UserViews.POLITICIAN_VIEW_NAME, "BALLOTHISTORY");

    /** The command politician document history. */
    PageModeMenuCommand COMMAND_POLITICIAN_DOCUMENT_HISTORY = new PageModeMenuCommand(
        UserViews.POLITICIAN_VIEW_NAME, PoliticianPageMode.DOCUMENTHISTORY.toString());

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
            UserViews.POLITICIAN_VIEW_NAME, "ROLES");

    /** The command politician role summary. */
    PageModeMenuCommand COMMAND_POLITICIAN_ROLE_SUMMARY = new PageModeMenuCommand(
            UserViews.POLITICIAN_VIEW_NAME, PoliticianPageMode.ROLESUMMARY.toString());

    /** The command politician role list. */
    PageModeMenuCommand COMMAND_POLITICIAN_ROLE_LIST = new PageModeMenuCommand(
            UserViews.POLITICIAN_VIEW_NAME, PoliticianPageMode.ROLELIST.toString());

    /** The command politician role ghant. */
    PageModeMenuCommand COMMAND_POLITICIAN_ROLE_GHANT = new PageModeMenuCommand(
            UserViews.POLITICIAN_VIEW_NAME, PoliticianPageMode.ROLEGHANT.toString());

}
