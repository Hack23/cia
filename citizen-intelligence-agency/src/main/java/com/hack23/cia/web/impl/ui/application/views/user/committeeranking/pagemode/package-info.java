/**
 * This package provides classes and interfaces for managing page modes within committee ranking views.
 *
 * Key classes and interfaces:
 * - AbstractCommitteeRankingPageModContentFactoryImpl: Abstract base class for committee ranking page mode content factories.
 * - CommitteeRankingAllCommitteesChartsPageModContentFactoryImpl: Implementation of the committee ranking all committees charts page mode content factory.
 * - CommitteeRankingCommitteeByPartyChartsPageModContentFactoryImpl: Implementation of the committee ranking committee by party charts page mode content factory.
 * - CommitteeRankingCurrentCommitteePartiesChartsPageModContentFactoryImpl: Implementation of the committee ranking current committee parties charts page mode content factory.
 * - CommitteeRankingCurrentCommitteesChartsPageModContentFactoryImpl: Implementation of the committee ranking current committees charts page mode content factory.
 * - CommitteeRankingDataGridPageModContentFactoryImpl: Implementation of the committee ranking data grid page mode content factory.
 * - CommitteeRankingOverviewPageModContentFactoryImpl: Implementation of the committee ranking overview page mode content factory.
 * - CommitteeRankingPageVisitHistoryPageModContentFactoryImpl: Implementation of the committee ranking page visit history page mode content factory.
 *
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for AbstractBasicPageModContentFactoryImpl.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for UserViews and CommitteeRankingPageMode.
 * - Depends on com.hack23.cia.model.internal.application.data.committee.impl for ViewRiksdagenCommittee and related entities.
 * - Depends on com.hack23.cia.service.api for data container and application manager services.
 * - Depends on org.springframework.security.access.annotation for security annotations.
 * - Depends on org.springframework.stereotype for component annotations.
 * - Depends on com.vaadin.ui for UI components.
 */
package com.hack23.cia.web.impl.ui.application.views.user.committeeranking.pagemode;
