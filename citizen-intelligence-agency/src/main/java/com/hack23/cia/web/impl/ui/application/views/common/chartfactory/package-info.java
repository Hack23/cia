/**
 * This package provides implementations for creating various charts within the Citizen Intelligence Agency web application.
 *
 * Key classes and interfaces:
 * - AbstractChartDataManagerImpl: Base class for chart data manager implementations.
 * - AbstractGhantChartManagerImpl: Base class for Gantt chart manager implementations.
 * - AdminChartDataManagerImpl: Implementation of the AdminChartDataManager interface.
 * - BallotChartDataManagerImpl: Implementation of the BallotChartDataManager interface.
 * - ChartDataManagerImpl: Implementation of the ChartDataManager interface.
 * - ChartOptionsImpl: Implementation of the ChartOptions interface.
 * - CommitteeGhantChartManagerImpl: Implementation of the CommitteeGhantChartManager interface.
 * - DecisionChartDataManagerImpl: Implementation of the DecisionChartDataManager interface.
 * - DecisionFlowChartManagerImpl: Implementation of the DecisionFlowChartManager interface.
 * - DocumentChartDataManagerImpl: Implementation of the DocumentChartDataManager interface.
 * - GovernmentBodyChartDataManagerImpl: Implementation of the GovernmentBodyChartDataManager interface.
 * - GovernmentOutcomeChartDataManagerImpl: Implementation of the GovernmentOutcomeChartDataManager interface.
 * - MinistryGhantChartManagerImpl: Implementation of the MinistryGhantChartManager interface.
 * - OrgDocumentChartDataManagerImpl: Implementation of the OrgDocumentChartDataManager interface.
 * - PartyChartDataManagerImpl: Implementation of the PartyChartDataManager interface.
 * - PartyCoalationChartDataManagerImpl: Implementation of the PartyCoalationChartDataManager interface.
 * - PartyDocumentChartDataManagerImpl: Implementation of the PartyDocumentChartDataManager interface.
 *
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api for chart factory interfaces.
 * - Depends on com.hack23.cia.model.external.riksdagen.dokumentstatus.impl for DocumentStatusContainer.
 * - Depends on com.hack23.cia.model.external.riksdagen.dokumentstatus.impl for DocumentStatusData.
 * - Depends on com.hack23.cia.model.external.riksdagen.dokumentstatus.impl for DocumentStatusSummary.
 * - Depends on com.hack23.cia.model.external.riksdagen.person.impl for PersonData.
 * - Depends on com.hack23.cia.model.internal.application.data.committee.impl for ViewRiksdagenCommittee.
 * - Depends on com.hack23.cia.model.internal.application.data.ministry.impl for ViewRiksdagenMinistry.
 * - Depends on com.hack23.cia.model.internal.application.data.party.impl for ViewRiksdagenParty.
 * - Depends on com.hack23.cia.model.internal.application.data.politician.impl for ViewRiksdagenPolitician.
 * - Depends on com.hack23.cia.service.api for DataContainer and ApplicationManager services.
 * - Depends on org.dussan.vaadin.dcharts.data for DataSeries.
 * - Depends on org.springframework.stereotype for Component.
 */
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory;
