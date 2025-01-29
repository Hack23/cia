/**
 * This package provides classes and interfaces for managing page modes within government body ranking views.
 *
 * Key classes and interfaces:
 * - AbstractGovernmentBodyRankingPageModContentFactoryImpl: Abstract base class for government body ranking page mode content factories.
 * - GovernmentBodyRankingDataGridPageModContentFactoryImpl: Implementation of the government body ranking data grid page mode content factory.
 * - GovernmentBodyRankingExpenditurePageModContentFactoryImpl: Implementation of the government body ranking expenditure page mode content factory.
 * - GovernmentBodyRankingHeadCountPageModContentFactoryImpl: Implementation of the government body ranking head count page mode content factory.
 * - GovernmentBodyRankingIncomePageModContentFactoryImpl: Implementation of the government body ranking income page mode content factory.
 * - GovernmentBodyRankingOverviewPageModContentFactoryImpl: Implementation of the government body ranking overview page mode content factory.
 * - GovernmentBodyRankingPageVisitHistoryPageModContentFactoryImpl: Implementation of the government body ranking page visit history page mode content factory.
 *
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for AbstractBasicPageModContentFactoryImpl.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for UserViews and GovernmentBodyRankingPageMode.
 * - Depends on com.hack23.cia.model.internal.application.data.governmentbody.impl for ViewRiksdagenGovernmentBody and related entities.
 * - Depends on com.hack23.cia.service.api for data container and application manager services.
 * - Depends on org.springframework.security.access.annotation for security annotations.
 * - Depends on org.springframework.stereotype for component annotations.
 * - Depends on com.vaadin.ui for UI components.
 */
package com.hack23.cia.web.impl.ui.application.views.user.govermentbodyranking.pagemode;
