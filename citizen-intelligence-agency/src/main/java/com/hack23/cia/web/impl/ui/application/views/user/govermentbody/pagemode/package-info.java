/**
 * This package provides classes and interfaces for managing page modes within government body views.
 *
 * Key classes and interfaces:
 * - AbstractGovernmentBodyPageModContentFactoryImpl: Abstract base class for government body page mode content factories.
 * - AbstractGovernmentBodyRankingPageModContentFactoryImpl: Abstract base class for government body ranking page mode content factories.
 * - GovernmentBodyExpenditurePageModContentFactoryImpl: Implementation of the government body expenditure page mode content factory.
 * - GovernmentBodyHeadcountPageModContentFactoryImpl: Implementation of the government body headcount page mode content factory.
 * - GovernmentBodyIncomePageModContentFactoryImpl: Implementation of the government body income page mode content factory.
 * - GovernmentBodyOverviewPageModContentFactoryImpl: Implementation of the government body overview page mode content factory.
 * - GovernmentBodyPageVisitHistoryPageModContentFactoryImpl: Implementation of the government body page visit history page mode content factory.
 * - GovernmentBodyRankingDataGridPageModContentFactoryImpl: Implementation of the government body ranking data grid page mode content factory.
 * - GovernmentBodyRankingExpenditurePageModContentFactoryImpl: Implementation of the government body ranking expenditure page mode content factory.
 * - GovernmentBodyRankingHeadCountPageModContentFactoryImpl: Implementation of the government body ranking headcount page mode content factory.
 * - GovernmentBodyRankingIncomePageModContentFactoryImpl: Implementation of the government body ranking income page mode content factory.
 * - GovernmentBodyRankingOverviewPageModContentFactoryImpl: Implementation of the government body ranking overview page mode content factory.
 * - GovernmentBodyRankingPageVisitHistoryPageModContentFactoryImpl: Implementation of the government body ranking page visit history page mode content factory.
 *
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for AbstractBasicPageModContentFactoryImpl.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for UserViews and GovernmentBodyPageMode.
 * - Depends on com.hack23.cia.model.internal.application.data.governmentbody.impl for ViewGovernmentBody and related entities.
 * - Depends on com.hack23.cia.service.api for data container and application manager services.
 * - Depends on org.springframework.security.access.annotation for security annotations.
 * - Depends on org.springframework.stereotype for component annotations.
 * - Depends on com.vaadin.ui for UI components.
 */
package com.hack23.cia.web.impl.ui.application.views.user.govermentbody.pagemode;