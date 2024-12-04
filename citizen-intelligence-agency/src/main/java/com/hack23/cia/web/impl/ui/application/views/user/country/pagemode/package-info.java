/**
 * This package provides classes and interfaces for managing page modes within country views.
 *
 * Key classes and interfaces:
 * - AbstractCountryPageModContentFactoryImpl: Abstract base class for country page mode content factories.
 * - CountryRankingOverviewPageModContentFactoryImpl: Implementation of the country ranking overview page mode content factory.
 * - WorldIndicatorsPageModContentFactoryImpl: Implementation of the world indicators page mode content factory.
 *
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for AbstractBasicPageModContentFactoryImpl.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for UserViews and CountryPageMode.
 * - Depends on com.hack23.cia.model.internal.application.data.country.impl for ViewCountry and related entities.
 * - Depends on com.hack23.cia.service.api for data container and application manager services.
 * - Depends on org.springframework.security.access.annotation for security annotations.
 * - Depends on org.springframework.stereotype for component annotations.
 * - Depends on com.vaadin.ui for UI components.
 */
package com.hack23.cia.web.impl.ui.application.views.user.country.pagemode;