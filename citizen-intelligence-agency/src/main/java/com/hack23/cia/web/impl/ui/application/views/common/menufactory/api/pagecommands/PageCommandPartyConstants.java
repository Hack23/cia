package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PartyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandPartyConstants.
 */
public interface PageCommandPartyConstants {

    /** The command party overview. */
    PageModeMenuCommand COMMAND_PARTY_OVERVIEW = new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.OVERVIEW);

    /** The command party current leaders. */
    PageModeMenuCommand COMMAND_PARTY_CURRENT_LEADERS = new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CURRENTLEADERS.toString());

    /** The command party leader history. */
    PageModeMenuCommand COMMAND_PARTY_LEADER_HISTORY = new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.LEADERHISTORY.toString());

    /** The command party current members. */
    PageModeMenuCommand COMMAND_PARTY_CURRENT_MEMBERS = new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CURRENTMEMBERS.toString());

    /** The command party member history. */
    PageModeMenuCommand COMMAND_PARTY_MEMBER_HISTORY = new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.MEMBERHISTORY.toString());

    /** The command party government roles. */
    PageModeMenuCommand COMMAND_PARTY_GOVERNMENT_ROLES = new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.GOVERNMENTROLES.toString());

    /** The command party committee roles. */
    PageModeMenuCommand COMMAND_PARTY_COMMITTEE_ROLES = new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.COMMITTEEROLES.toString());

    /** The command party role ghant. */
    PageModeMenuCommand COMMAND_PARTY_ROLE_GHANT = new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.ROLEGHANT.toString());

    /** The command party document activity. */
    PageModeMenuCommand COMMAND_PARTY_DOCUMENT_ACTIVITY = new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DOCUMENTACTIVITY.toString());

    /** The command party document history. */
    PageModeMenuCommand COMMAND_PARTY_DOCUMENT_HISTORY = new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DOCUMENTHISTORY.toString());

    /** The command party vote history. */
    PageModeMenuCommand COMMAND_PARTY_VOTE_HISTORY = new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.VOTEHISTORY.toString());

    /** The command party ballot decision summary. */
    PageModeMenuCommand COMMAND_PARTY_BALLOT_DECISION_SUMMARY = new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.COMMITTEEBALLOTDECISIONSUMMARY.toString());

    /** The command party won daily summary chart. */
    PageModeMenuCommand COMMAND_PARTY_WON_DAILY_SUMMARY_CHART = new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.PARTYWONDAILYSUMMARYCHART.toString());

    /** The command party against coalitions summary. */
    PageModeMenuCommand COMMAND_PARTY_AGAINST_COALITIONS_SUMMARY = new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.PARTYAGAINSTCOALATIONSSUMMARY.toString());

    /** The command party support summary. */
    PageModeMenuCommand COMMAND_PARTY_SUPPORT_SUMMARY = new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.PARTYSUPPORTSUMMARY.toString());

    /** The command party page visit history. */
    PageModeMenuCommand COMMAND_PARTY_PAGE_VISIT_HISTORY = new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.PAGEVISITHISTORY);

}
