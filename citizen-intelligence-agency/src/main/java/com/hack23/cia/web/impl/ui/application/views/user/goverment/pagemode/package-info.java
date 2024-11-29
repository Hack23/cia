/**
 * This package provides classes and interfaces for managing page modes within government views.
 * 
 * Key classes and interfaces:
 * - AbstractMinistryPageModContentFactoryImpl: Abstract base class for ministry page mode content factories.
 * - AbstractMinistryRankingPageModContentFactoryImpl: Abstract base class for ministry ranking page mode content factories.
 * - MinistryCurrentMembersPageModContentFactoryImpl: Implementation of the ministry current members page mode content factory.
 * - MinistryDocumentActivityPageModContentFactoryImpl: Implementation of the ministry document activity page mode content factory.
 * - MinistryDocumentHistoryPageModContentFactoryImpl: Implementation of the ministry document history page mode content factory.
 * - MinistryGovernmentBodiesExpenditureModContentFactoryImpl: Implementation of the ministry government bodies expenditure page mode content factory.
 * - MinistryGovernmentBodiesHeadcountModContentFactoryImpl: Implementation of the ministry government bodies headcount page mode content factory.
 * - MinistryGovernmentBodiesIncomeModContentFactoryImpl: Implementation of the ministry government bodies income page mode content factory.
 * - MinistryMemberHistoryPageModContentFactoryImpl: Implementation of the ministry member history page mode content factory.
 * - MinistryOverviewPageModContentFactoryImpl: Implementation of the ministry overview page mode content factory.
 * - MinistryPageVisitHistoryPageModContentFactoryImpl: Implementation of the ministry page visit history page mode content factory.
 * - MinistryRankingAllMinistriesChartsPageModContentFactoryImpl: Implementation of the ministry ranking all ministries charts page mode content factory.
 * - MinistryRankingAllPartiesChartsPageModContentFactoryImpl: Implementation of the ministry ranking all parties charts page mode content factory.
 * - MinistryRankingAllRolesChartsPageModContentFactoryImpl: Implementation of the ministry ranking all roles charts page mode content factory.
 * - MinistryRankingCurrentMinistriesChartsPageModContentFactoryImpl: Implementation of the ministry ranking current ministries charts page mode content factory.
 * - MinistryRankingCurrentPartiesChartsPageModContentFactoryImpl: Implementation of the ministry ranking current parties charts page mode content factory.
 * - MinistryRankingCurrentPartiesLeaderScoreboardChartsPageModContentFactoryImpl: Implementation of the ministry ranking current parties leader scoreboard charts page mode content factory.
 * - MinistryRankingDataGridPageModContentFactoryImpl: Implementation of the ministry ranking data grid page mode content factory.
 * - MinistryRankingGovernmentBodiesPageModContentFactoryImpl: Implementation of the ministry ranking government bodies page mode content factory.
 * - MinistryRankingGovernmentBodyExpenditurePageModContentFactoryImpl: Implementation of the ministry ranking government body expenditure page mode content factory.
 * - MinistryRankingGovernmentBodyIncomePageModContentFactoryImpl: Implementation of the ministry ranking government body income page mode content factory.
 * - MinistryRankingGovernmentOutcomePageModContentFactoryImpl: Implementation of the ministry ranking government outcome page mode content factory.
 * - MinistryRankingOverviewPageModContentFactoryImpl: Implementation of the ministry ranking overview page mode content factory.
 * - MinistryRankingPageVisitHistoryPageModContentFactoryImpl: Implementation of the ministry ranking page visit history page mode content factory.
 * - MinistryRoleGhantPageModContentFactoryImpl: Implementation of the ministry role ghant page mode content factory.
 * 
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for AbstractBasicPageModContentFactoryImpl.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for UserViews and MinistryPageMode.
 * - Depends on com.hack23.cia.model.internal.application.data.ministry.impl for ViewRiksdagenMinistry and related entities.
 * - Depends on com.hack23.cia.service.api for data container and application manager services.
 * - Depends on org.springframework.security.access.annotation for security annotations.
 * - Depends on org.springframework.stereotype for component annotations.
 * - Depends on com.vaadin.ui for UI components.
 */
package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;