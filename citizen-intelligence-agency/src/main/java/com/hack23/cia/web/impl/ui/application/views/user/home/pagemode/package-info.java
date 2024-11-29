/**
 * This package provides classes and interfaces for managing page modes within home views.
 * 
 * Key classes and interfaces:
 * - AbstractUserHomePageModContentFactoryImpl: Abstract base class for user home page mode content factories.
 * - UserHomeApplicationEventsPageModContentFactoryImpl: Implementation of the user home application events page mode content factory.
 * - UserHomeApplicationSessionsPageModContentFactoryImpl: Implementation of the user home application sessions page mode content factory.
 * - UserHomeOverviewPageModContentFactoryImpl: Implementation of the user home overview page mode content factory.
 * - UserHomeSecuritySettingsPageModContentFactoryImpl: Implementation of the user home security settings page mode content factory.
 * 
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for AbstractBasicPageModContentFactoryImpl.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for UserViews and HomePageMode.
 * - Depends on com.hack23.cia.service.api for data container and application manager services.
 * - Depends on org.springframework.security.access.annotation for security annotations.
 * - Depends on org.springframework.stereotype for component annotations.
 * - Depends on com.vaadin.ui for UI components.
 */
package com.hack23.cia.web.impl.ui.application.views.user.home.pagemode;