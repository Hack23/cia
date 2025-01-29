/**
 * This package provides classes and interfaces for managing page modes within politician ranking views.
 *
 * Key classes and interfaces:
 * - AbstractPoliticianRankingPageModContentFactoryImpl: Abstract base class for politician ranking page mode content factories.
 * - PoliticianRankingAllPoliticiansChartsPageModContentFactoryImpl: Implementation of the politician ranking all politicians charts page mode content factory.
 * - PoliticianRankingCurrentPoliticiansChartsPageModContentFactoryImpl: Implementation of the politician ranking current politicians charts page mode content factory.
 * - PoliticianRankingDataGridPageModContentFactoryImpl: Implementation of the politician ranking data grid page mode content factory.
 * - PoliticianRankingOverviewPageModContentFactoryImpl: Implementation of the politician ranking overview page mode content factory.
 * - PoliticianRankingPageVisitHistoryPageModContentFactoryImpl: Implementation of the politician ranking page visit history page mode content factory.
 *
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for AbstractBasicPageModContentFactoryImpl.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for UserViews and PoliticianRankingPageMode.
 * - Depends on com.hack23.cia.model.internal.application.data.politician.impl for ViewRiksdagenPolitician and related entities.
 * - Depends on com.hack23.cia.service.api for data container and application manager services.
 * - Depends on org.springframework.security.access.annotation for security annotations.
 * - Depends on org.springframework.stereotype for component annotations.
 * - Depends on com.vaadin.ui for UI components.
 */
package com.hack23.cia.web.impl.ui.application.views.user.politicianranking.pagemode;
