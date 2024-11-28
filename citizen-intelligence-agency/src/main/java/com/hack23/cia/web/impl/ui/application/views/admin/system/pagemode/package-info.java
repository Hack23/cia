/**
 * This package provides classes and interfaces for managing various system-related page modes within the Citizen Intelligence Agency web application.
 * 
 * Key classes and interfaces:
 * - AbstractAdminSystemPageModContentFactoryImpl: Base class for admin system page mode content factories.
 * - AdminAgencyPageModContentFactoryImpl: Content factory for the admin agency page mode.
 * - AdminApplicationConfigurationPageModContentFactoryImpl: Content factory for the admin application configuration page mode.
 * - AdminApplicationEventsChartsPageModContentFactoryImpl: Content factory for the admin application events charts page mode.
 * - AdminApplicationEventsPageModContentFactoryImpl: Content factory for the admin application events page mode.
 * - AdminApplicationSessionChartsPageModContentFactoryImpl: Content factory for the admin application session charts page mode.
 * - AdminApplicationSessionPageModContentFactoryImpl: Content factory for the admin application session page mode.
 * - AdminCountryPageModContentFactoryImpl: Content factory for the admin country page mode.
 * - AdminLanguagePageModContentFactoryImpl: Content factory for the admin language page mode.
 * - AdminMonitoringPageModContentFactoryImpl: Content factory for the admin monitoring page mode.
 * - AdminPortalPageModContentFactoryImpl: Content factory for the admin portal page mode.
 * - AdminUserAccountPageModContentFactoryImpl: Content factory for the admin user account page mode.
 * - EmailPageModContentFactoryImpl: Content factory for the email page mode.
 * 
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for AbstractBasicPageModContentFactoryImpl.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.paging for PagingUtil.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for AdminViews and PageMode.
 * - Depends on com.hack23.cia.model.internal.application.system.impl for various system-related entities.
 * - Depends on com.hack23.cia.service.api for data container and application manager services.
 * - Depends on org.springframework.security.access.annotation for security annotations.
 * - Depends on org.springframework.stereotype for component annotations.
 * - Depends on org.springframework.web.context.request for request context holder.
 * - Depends on com.vaadin.ui for UI components.
 */
package com.hack23.cia.web.impl.ui.application.views.admin.system.pagemode;
