package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommitteePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandCommitteeConstants.
 */
public interface PageCommandCommitteeConstants {

    /** The command committee overview. */
    PageModeMenuCommand COMMAND_COMMITTEE_OVERVIEW = new PageModeMenuCommand(
                     UserViews.COMMITTEE_VIEW_NAME, PageMode.OVERVIEW);

    /** The command committee role ghant. */
    PageModeMenuCommand COMMAND_COMMITTEE_ROLE_GHANT = new PageModeMenuCommand(
                     UserViews.COMMITTEE_VIEW_NAME, "roleghant");

    /** The command committee pagevisit history. */
    PageModeMenuCommand COMMAND_COMMITTEE_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.COMMITTEE_VIEW_NAME, PageMode.PAGEVISITHISTORY);

    /** The command charts decision flow. */
    PageModeMenuCommand COMMAND_CHARTS_DECISION_FLOW = new PageModeMenuCommand(
            UserViews.COMMITTEE_VIEW_NAME, PageMode.CHARTS,
            ChartIndicators.DECISION_FLOW_CHART.toString());

    /** The command committee current members. */
    PageModeMenuCommand COMMAND_COMMITTEE_CURRENT_MEMBERS = new PageModeMenuCommand(
            UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.CURRENT_MEMBERS.toString());

    /** The command committee member history. */
    PageModeMenuCommand COMMAND_COMMITTEE_MEMBER_HISTORY = new PageModeMenuCommand(
            UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.MEMBERHISTORY.toString());

    /** The command committee document activity. */
    PageModeMenuCommand COMMAND_COMMITTEE_DOCUMENT_ACTIVITY = new PageModeMenuCommand(
            UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.DOCUMENTACTIVITY.toString());

    /** The command committee document history. */
    PageModeMenuCommand COMMAND_COMMITTEE_DOCUMENT_HISTORY = new PageModeMenuCommand(
            UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.DOCUMENT_HISTORY.toString());

    /** The command committee ballot decision summary. */
    PageModeMenuCommand COMMAND_COMMITTEE_BALLOT_DECISION_SUMMARY = new PageModeMenuCommand(
            UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.BALLOTDECISIONSUMMARY.toString());

    /** The command committee decision summary. */
    PageModeMenuCommand COMMAND_COMMITTEE_DECISION_SUMMARY = new PageModeMenuCommand(
            UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.DECISIONSUMMARY.toString());

    /** The command committee decision type daily summary. */
    PageModeMenuCommand COMMAND_COMMITTEE_DECISION_TYPE_DAILY_SUMMARY = new PageModeMenuCommand(
            UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.DECISIONTYPEDAILYSUMMARY.toString());
}
