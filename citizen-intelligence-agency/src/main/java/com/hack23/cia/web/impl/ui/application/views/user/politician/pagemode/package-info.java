/**
 * This package provides classes and interfaces for managing page modes within politician views.
 * 
 * Key classes and interfaces:
 * - AbstractPoliticianPageModContentFactoryImpl: Abstract base class for politician page mode content factories.
 * - AbstractPoliticianRankingPageModContentFactoryImpl: Abstract base class for politician ranking page mode content factories.
 * - PoliticianBallotDecisionSummaryPageModContentFactoryImpl: Implementation of the politician ballot decision summary page mode content factory.
 * - PoliticianDocumentActivityPageModContentFactoryImpl: Implementation of the politician document activity page mode content factory.
 * - PoliticianDocumentHistoryPageModContentFactoryImpl: Implementation of the politician document history page mode content factory.
 * - PoliticianIndicatorsPageModContentFactoryImpl: Implementation of the politician indicators page mode content factory.
 * - PoliticianOverviewPageModContentFactoryImpl: Implementation of the politician overview page mode content factory.
 * - PoliticianPageVisitHistoryPageModContentFactoryImpl: Implementation of the politician page visit history page mode content factory.
 * - PoliticianRankingChartsAllPartiesPageModContentFactoryImpl: Implementation of the politician ranking charts all parties page mode content factory.
 * - PoliticianRankingChartsCurrentPartiesPageModContentFactoryImpl: Implementation of the politician ranking charts current parties page mode content factory.
 * - PoliticianRankingDataGridPageModContentFactoryImpl: Implementation of the politician ranking data grid page mode content factory.
 * - PoliticianRankingOverviewPageModContentFactoryImpl: Implementation of the politician ranking overview page mode content factory.
 * - PoliticianRankingPageVisitHistoryPageModContentFactoryImpl: Implementation of the politician ranking page visit history page mode content factory.
 * - PoliticianRoleGhantPageModContentFactoryImpl: Implementation of the politician role ghant page mode content factory.
 * - PoliticianRoleListPageModContentFactoryImpl: Implementation of the politician role list page mode content factory.
 * - PoliticianRoleSummaryPageModContentFactoryImpl: Implementation of the politician role summary page mode content factory.
 * - PoliticianVotesHistoryPageModContentFactoryImpl: Implementation of the politician votes history page mode content factory.
 * 
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for AbstractBasicPageModContentFactoryImpl.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for UserViews and PoliticianPageMode.
 * - Depends on com.hack23.cia.model.internal.application.data.politician.impl for ViewRiksdagenPolitician and related entities.
 * - Depends on com.hack23.cia.service.api for data container and application manager services.
 * - Depends on org.springframework.security.access.annotation for security annotations.
 * - Depends on org.springframework.stereotype for component annotations.
 * - Depends on com.vaadin.ui for UI components.
 */
package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;