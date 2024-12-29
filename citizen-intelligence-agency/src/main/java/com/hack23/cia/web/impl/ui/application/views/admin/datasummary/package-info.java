/**
 * This package provides classes and interfaces for summarizing data within the Citizen Intelligence Agency web application.
 *
 * Key classes and interfaces:
 * - AdminDataSummaryView: Main view class for data summary.
 * - AbstractDataSummaryPageModContentFactoryImpl: Abstract base class for data summary page mode content factories.
 * - DataSummaryAuthorPageModContentFactoryImpl: Implementation of the data summary author page mode content factory.
 * - DataSummaryOverviewPageModContentFactoryImpl: Implementation of the data summary overview page mode content factory.
 *
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.admin.common for AbstractAdminView.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for PageModeContentFactory.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for AdminViews and DataSummaryPageMode.
 * - Depends on com.hack23.cia.model.internal.application.data.audit.impl for ViewAuditAuthorSummary and ViewAuditDataSummary.
 * - Depends on com.hack23.cia.service.api for DataContainer and ApplicationManager services.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.sizing for ContentRatio.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.pageclicklistener for RefreshDataViewsClickListener, RemoveDataClickListener, and UpdateSearchIndexClickListener.
 * - Depends on com.jarektoro.responsivelayout for ResponsiveRow.
 * - Depends on com.vaadin.icons for VaadinIcons.
 * - Depends on com.vaadin.ui for Button, Layout, MenuBar, Panel, VerticalLayout.
 * - Depends on org.springframework.security.access.annotation for Secured.
 * - Depends on org.springframework.stereotype for Component.
 * - Depends on org.springframework.context for ApplicationContext.
 * - Depends on com.vaadin.spring.annotation for SpringView.
 *
 * The package is responsible for providing the necessary structure and implementation for summarizing data within the Citizen Intelligence Agency web application. The key classes and interfaces within this package manage various aspects of data summary, such as data summary views, page mode content factories, and their implementation. The package also has dependencies on other packages for specific functionalities, such as common admin views, page mode content factories, view names, audit data summaries, application manager services, content sizing, page click listeners, responsive layout, Vaadin icons, UI components, security annotations, component annotations, application context, and Spring framework.
 */
package com.hack23.cia.web.impl.ui.application.views.admin.datasummary;
