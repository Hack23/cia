/**
 * This package provides classes and interfaces for managing page modes within ballot views.
 * 
 * Key classes and interfaces:
 * - AbstractBallotPageModContentFactoryImpl: Abstract base class for ballot page mode content factories.
 * - BallotChartsPageModContentFactoryImpl: Implementation of the ballot charts page mode content factory.
 * - BallotOverviewPageModContentFactoryImpl: Implementation of the ballot overview page mode content factory.
 * 
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for AbstractBasicPageModContentFactoryImpl.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for UserViews and BallotPageMode.
 * - Depends on com.hack23.cia.model.internal.application.data.committee.impl for ViewRiksdagenVoteDataBallotSummary and related entities.
 * - Depends on com.hack23.cia.service.api for data container and application manager services.
 * - Depends on org.springframework.security.access.annotation for security annotations.
 * - Depends on org.springframework.stereotype for component annotations.
 * - Depends on com.vaadin.ui for UI components.
 */
package com.hack23.cia.web.impl.ui.application.views.user.ballot.pagemode;