/**
 * This package provides classes and interfaces for managing the page modes within the data summary view of the Citizen Intelligence Agency web application.
 * 
 * Key classes and interfaces:
 * - AbstractDataSummaryPageModContentFactoryImpl: Abstract base class for data summary page mode content factories.
 * - DataSummaryAuthorPageModContentFactoryImpl: Implementation of the data summary author page mode content factory.
 * - DataSummaryOverviewPageModContentFactoryImpl: Implementation of the data summary overview page mode content factory.
 * 
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for AbstractBasicPageModContentFactoryImpl.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for AdminViews and DataSummaryPageMode.
 * - Depends on com.hack23.cia.model.internal.application.data.audit.impl for ViewAuditAuthorSummary and ViewAuditDataSummary.
 * - Depends on com.hack23.cia.service.api for DataContainer and ApplicationManager services.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.labelfactory for LabelFactory.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.rows for RowUtil.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.sizing for ContentRatio.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.pageclicklistener for RefreshDataViewsClickListener, RemoveDataClickListener, and UpdateSearchIndexClickListener.
 * - Depends on com.jarektoro.responsivelayout for ResponsiveRow.
 * - Depends on com.vaadin.icons for VaadinIcons.
 * - Depends on com.vaadin.ui for Button, Layout, MenuBar, Panel, VerticalLayout.
 * - Depends on org.springframework.security.access.annotation for Secured.
 * - Depends on org.springframework.stereotype for Component.
 */
package com.hack23.cia.web.impl.ui.application.views.admin.datasummary.pagemode;
