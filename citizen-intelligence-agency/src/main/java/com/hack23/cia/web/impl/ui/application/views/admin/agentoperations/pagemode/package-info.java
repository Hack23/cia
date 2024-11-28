/**
 * This package provides classes and interfaces for managing the page modes within the agent operations view of the Citizen Intelligence Agency web application.
 * 
 * Key classes and interfaces:
 * - AbstractAgentOperationsPageModContentFactoryImpl: Abstract base class for agent operations page mode content factories.
 * - AgentOperationsOverviewPageModContentFactoryImpl: Implementation of the agent operations overview page mode content factory.
 * 
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for AbstractBasicPageModContentFactoryImpl.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for AdminViews.
 * - Depends on com.hack23.cia.model.internal.application.data.impl for DataAgentOperation and DataAgentTarget.
 * - Depends on com.hack23.cia.model.internal.application.system.impl for ApplicationEventGroup.
 * - Depends on com.hack23.cia.web.impl.ui.application.action for ViewAction.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.labelfactory for LabelFactory.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.rows for RowUtil.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.sizing for ContentRatio.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.pageclicklistener for StartAgentClickListener.
 * - Depends on com.jarektoro.responsivelayout for ResponsiveRow.
 * - Depends on com.vaadin.icons for VaadinIcons.
 * - Depends on com.vaadin.ui for Button, Layout, MenuBar, Panel, VerticalLayout.
 * - Depends on org.springframework.security.access.annotation for Secured.
 * - Depends on org.springframework.stereotype for Component.
 */
package com.hack23.cia.web.impl.ui.application.views.admin.agentoperations.pagemode;
