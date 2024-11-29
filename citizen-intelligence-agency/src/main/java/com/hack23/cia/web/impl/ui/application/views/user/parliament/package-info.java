/**
 * This package provides classes and interfaces for managing parliament views within the Citizen Intelligence Agency web application.
 * 
 * Key classes and interfaces:
 * - ParliamentRankingView: View class for displaying parliament rankings.
 * - ParliamentOverviewPageModContentFactoryImpl: Factory class for creating parliament overview page mode content.
 * - ParliamentDecisionFlowPageModContentFactoryImpl: Factory class for creating parliament decision flow page mode content.
 * - ParliamentChartsDecisionActivityByTypePageModContentFactoryImpl: Factory class for creating parliament charts decision activity by type page mode content.
 * - ParliamentChartsDocumentActivityByTypePageModContentFactoryImpl: Factory class for creating parliament charts document activity by type page mode content.
 * - ParliamentChartsPartyAgePageModContentFactoryImpl: Factory class for creating parliament charts party age page mode content.
 * - ParliamentChartsPartyGenderPageModContentFactoryImpl: Factory class for creating parliament charts party gender page mode content.
 * - ParliamentChartsPartyWinnerPageModContentFactoryImpl: Factory class for creating parliament charts party winner page mode content.
 * - ParliamentPageVisitHistoryPageModContentFactoryImpl: Factory class for creating parliament page visit history page mode content.
 * - ParliamentRiskPageModContentFactoryImpl: Factory class for creating parliament risk page mode content.
 * - ParliamentRuleViolationsPageModContentFactoryImpl: Factory class for creating parliament rule violations page mode content.
 * 
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for PageModeContentFactory.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for UserViews and ParliamentPageMode.
 * - Depends on com.hack23.cia.model.internal.application.data.parliament.impl for ViewRiksdagenParliament and related entities.
 * - Depends on com.hack23.cia.service.api for data container and application manager services.
 * - Depends on org.springframework.security.access.annotation for security annotations.
 * - Depends on org.springframework.stereotype for component annotations.
 * - Depends on com.vaadin.ui for UI components.
 */
package com.hack23.cia.web.impl.ui.application.views.user.parliament;
