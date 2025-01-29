/**
 * This package provides classes and interfaces for managing page modes within party ranking views.
 *
 * Key classes and interfaces:
 * - AbstractPartyRankingPageModContentFactoryImpl: Abstract base class for party ranking page mode content factories.
 * - PartyRankingAllPartiesChartsPageModContentFactoryImpl: Implementation of the party ranking all parties charts page mode content factory.
 * - PartyRankingCurrentCommitteeChartsPageModContentFactoryImpl: Implementation of the party ranking current committee charts page mode content factory.
 * - PartyRankingCurrentGovernmentChartsPageModContentFactoryImpl: Implementation of the party ranking current government charts page mode content factory.
 * - PartyRankingCurrentPartiesChartsPageModContentFactoryImpl: Implementation of the party ranking current parties charts page mode content factory.
 * - PartyRankingCurrentPartiesLeaderScoreboardPageModContentFactoryImpl: Implementation of the party ranking current parties leader scoreboard page mode content factory.
 * - PartyRankingDataGridPageModContentFactoryImpl: Implementation of the party ranking data grid page mode content factory.
 * - PartyRankingOverviewPageModContentFactoryImpl: Implementation of the party ranking overview page mode content factory.
 * - PartyRankingPageVisitHistoryPageModContentFactoryImpl: Implementation of the party ranking page visit history page mode content factory.
 *
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for AbstractBasicPageModContentFactoryImpl.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for UserViews and PartyRankingPageMode.
 * - Depends on com.hack23.cia.model.internal.application.data.party.impl for ViewRiksdagenParty and related entities.
 * - Depends on com.hack23.cia.service.api for data container and application manager services.
 * - Depends on org.springframework.security.access.annotation for security annotations.
 * - Depends on org.springframework.stereotype for component annotations.
 * - Depends on com.vaadin.ui for UI components.
 */
package com.hack23.cia.web.impl.ui.application.views.user.partyranking.pagemode;
