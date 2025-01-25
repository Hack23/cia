package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandUserConstants.
 */
public interface PageCommandUserConstants extends  
PageCommandMinistryRankingChartConstants,    
PageCommandAdminConstants,
    PageCommandPartyConstants,
    PageCommandPartyRankingConstants,
    PageCommandCommitteeConstants,
    PageCommandCommitteeRankingConstants,
    PageCommandCountryRankingConstants,
    PageCommandMinistryRankingConstants,
    PageCommandGovernmentBodyRankingConstants,
    PageCommandParliamentRankingConstants,
    PageCommandPoliticianRankingConstants,
    PageCommandDocumentConstants,
    PageCommandMinistryConstants,
    PageCommandGovernmentBodyConstants,
    PageCommandBallotConstants,
    PageCommandUserHomeConstants,
    PageCommandMainViewConstants,
    PageCommandPageModeConstants,
    PageCommandParliamentConstants,PageCommandApplicationChartConstants, PageCommandPoliticianRankingMenuConstants {

    /** The command document overview. */
    PageModeMenuCommand COMMAND_DOCUMENT_OVERVIEW = new PageModeMenuCommand(
        UserViews.DOCUMENT_VIEW_NAME, PageMode.OVERVIEW);
    
    /** The command document activity. */
    PageModeMenuCommand COMMAND_DOCUMENT_ACTIVITY = new PageModeMenuCommand(
        UserViews.DOCUMENT_VIEW_NAME, "documentactivity");
    
    /** The command document data. */
    PageModeMenuCommand COMMAND_DOCUMENT_DATA = new PageModeMenuCommand(
        UserViews.DOCUMENT_VIEW_NAME, "documentdata");
    
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