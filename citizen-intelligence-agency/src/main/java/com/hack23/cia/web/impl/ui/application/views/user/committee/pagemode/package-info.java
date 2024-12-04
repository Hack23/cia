/**
 * This package provides classes and interfaces for managing page modes within the committee views of the Citizen Intelligence Agency web application.
 *
 * Key classes and interfaces:
 * - AbstractCommitteePageModContentFactoryImpl: Abstract base class for committee page mode content factories.
 * - AbstractCommitteeRankingPageModContentFactoryImpl: Abstract base class for committee ranking page mode content factories.
 * - CommitteeBallotDecisionSummaryPageModContentFactoryImpl: Implementation of the committee ballot decision summary page mode content factory.
 * - CommitteeCurrentMembersHistoryPageModContentFactoryImpl: Implementation of the committee current members history page mode content factory.
 * - CommitteeDecisionFlowPageModContentFactoryImpl: Implementation of the committee decision flow page mode content factory.
 * - CommitteeDecisionSummaryPageModContentFactoryImpl: Implementation of the committee decision summary page mode content factory.
 * - CommitteeDecisionTypeDailySummaryPageModContentFactoryImpl2: Implementation of the committee decision type daily summary page mode content factory.
 * - CommitteeDocumentActivityPageModContentFactoryImpl: Implementation of the committee document activity page mode content factory.
 * - CommitteeDocumentHistoryPageModContentFactoryImpl: Implementation of the committee document history page mode content factory.
 * - CommitteeMemberHistoryPageModContentFactoryImpl: Implementation of the committee member history page mode content factory.
 * - CommitteeOverviewPageModContentFactoryImpl: Implementation of the committee overview page mode content factory.
 * - CommitteePageVisitHistoryPageModContentFactoryImpl: Implementation of the committee page visit history page mode content factory.
 * - CommitteeRankingAllCommitteesChartsPageModContentFactoryImpl: Implementation of the committee ranking all committees charts page mode content factory.
 * - CommitteeRankingCommitteeByPartyChartsPageModContentFactoryImpl: Implementation of the committee ranking committee by party charts page mode content factory.
 * - CommitteeRankingCurrentCommitteePartiesChartsPageModContentFactoryImpl: Implementation of the committee ranking current committee parties charts page mode content factory.
 * - CommitteeRankingCurrentCommitteesChartsPageModContentFactoryImpl: Implementation of the committee ranking current committees charts page mode content factory.
 * - CommitteeRankingDataGridPageModContentFactoryImpl: Implementation of the committee ranking data grid page mode content factory.
 * - CommitteeRankingOverviewPageModContentFactoryImpl: Implementation of the committee ranking overview page mode content factory.
 * - CommitteeRankingPageVisitHistoryPageModContentFactoryImpl: Implementation of the committee ranking page visit history page mode content factory.
 * - CommitteeRoleGhantPageModContentFactoryImpl: Implementation of the committee role ghant page mode content factory.
 *
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for AbstractBasicPageModContentFactoryImpl.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for UserViews and CommitteePageMode.
 * - Depends on com.hack23.cia.model.internal.application.data.committee.impl for ViewRiksdagenCommittee and related entities.
 * - Depends on com.hack23.cia.service.api for data container and application manager services.
 * - Depends on org.springframework.security.access.annotation for security annotations.
 * - Depends on org.springframework.stereotype for component annotations.
 * - Depends on com.vaadin.ui for UI components.
 */
package com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode;