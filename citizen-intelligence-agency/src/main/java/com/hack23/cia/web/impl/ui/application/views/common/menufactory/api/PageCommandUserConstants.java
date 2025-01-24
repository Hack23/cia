package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

public interface PageCommandUserConstants extends 
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
    PageCommandPageModeConstants {
    
    // Remove constants moved to PageCommandMainViewConstants:
    // - COMMAND_MAINVIEW_OVERVIEW
    // - COMMAND_DASHBOARDVIEW_OVERVIEW 
    // - COMMAND_MAINVIEW_PAGEVISITHISTORY
    // - COMMAND_LOGIN
    // - COMMAND_LOGOUT
    // - COMMAND_REGISTER

    // Remove constants moved to PageCommandPageModeConstants:
    // - COMMAND_OVERVIEW
    // - COMMAND_PAGEVISITHISTORY
    // - COMMAND_DATAGRID

    // ...existing code...
}