/**
 * This package provides classes and interfaces for managing page modes within party views.
 * 
 * Key classes and interfaces:
 * - AbstractPartyPageModContentFactoryImpl: Abstract base class for party page mode content factories.
 * - AbstractPartyRankingPageModContentFactoryImpl: Abstract base class for party ranking page mode content factories.
 * - PartyCoalitionsAgainstAnnualSummaryChartPageModContentFactoryImpl: Implementation of the party coalitions against annual summary chart page mode content factory.
 * - PartyCommitteeBallotDecisionSummaryPageModContentFactoryImpl: Implementation of the party committee ballot decision summary page mode content factory.
 * - PartyCommitteeRolesPageModContentFactoryImpl: Implementation of the party committee roles page mode content factory.
 * - PartyCurrentLeadersPageModContentFactoryImpl: Implementation of the party current leaders page mode content factory.
 * - PartyCurrentMembersPageModContentFactoryImpl: Implementation of the party current members page mode content factory.
 * - PartyDocumentActivityPageModContentFactoryImpl: Implementation of the party document activity page mode content factory.
 * - PartyDocumentHistoryPageModContentFactoryImpl: Implementation of the party document history page mode content factory.
 * - PartyGovernmentRolesPageModContentFactoryImpl: Implementation of the party government roles page mode content factory.
 * - PartyLeaderHistoryPageModContentFactoryImpl: Implementation of the party leader history page mode content factory.
 * - PartyMemberHistoryPageModContentFactoryImpl: Implementation of the party member history page mode content factory.
 * - PartyOverviewPageModContentFactoryImpl: Implementation of the party overview page mode content factory.
 * - PartyPageVisitHistoryPageModContentFactoryImpl: Implementation of the party page visit history page mode content factory.
 * - PartyRankingAllPartiesChartsPageModContentFactoryImpl: Implementation of the party ranking all parties charts page mode content factory.
 * - PartyRankingCurrentCommitteeChartsPageModContentFactoryImpl: Implementation of the party ranking current committee charts page mode content factory.
 * - PartyRankingCurrentGovernmentChartsPageModContentFactoryImpl: Implementation of the party ranking current government charts page mode content factory.
 * - PartyRankingCurrentPartiesChartsPageModContentFactoryImpl: Implementation of the party ranking current parties charts page mode content factory.
 * - PartyRankingCurrentPartiesLeaderScoreboardPageModContentFactoryImpl: Implementation of the party ranking current parties leader scoreboard page mode content factory.
 * - PartyRankingDataGridPageModContentFactoryImpl: Implementation of the party ranking data grid page mode content factory.
 * - PartyRankingOverviewPageModContentFactoryImpl: Implementation of the party ranking overview page mode content factory.
 * - PartyRankingPageVisitHistoryPageModContentFactoryImpl: Implementation of the party ranking page visit history page mode content factory.
 * - PartyRoleGhantPageModContentFactoryImpl: Implementation of the party role ghant page mode content factory.
 * - PartySupportAnnualSummaryChartPageModContentFactoryImpl: Implementation of the party support annual summary chart page mode content factory.
 * - PartyVoteHistoryPageModContentFactoryImpl: Implementation of the party vote history page mode content factory.
 * - PartyWonDailySummaryChartPageModContentFactoryImpl: Implementation of the party won daily summary chart page mode content factory.
 * 
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for AbstractBasicPageModContentFactoryImpl.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for UserViews and PartyPageMode.
 * - Depends on com.hack23.cia.model.internal.application.data.party.impl for ViewRiksdagenParty and related entities.
 * - Depends on com.hack23.cia.service.api for data container and application manager services.
 * - Depends on org.springframework.security.access.annotation for security annotations.
 * - Depends on org.springframework.stereotype for component annotations.
 * - Depends on com.vaadin.ui for UI components.
 */
package com.hack23.cia.web.impl.ui.application.views.user.party.pagemode;