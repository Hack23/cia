/**
 * This package contains implementations for handling committee data within the internal application.
 *
 * Key classes:
 * - RiksdagenCommitteeDecisionTypeOrgSummaryEmbeddedId: Represents the embedded ID for Riksdagen committee decision type organization summary.
 * - RiksdagenCommitteeDecisionTypeSummaryEmbeddedId: Represents the embedded ID for Riksdagen committee decision type summary.
 * - RiksdagenCommitteeEmbeddedId: Represents the embedded ID for Riksdagen committee.
 * - RiksdagenVoteDataBallotEmbeddedId: Represents the embedded ID for Riksdagen vote data ballot.
 * - ViewRiksdagenCommittee: Represents a view of Riksdagen committee data.
 *
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.model.internal.application.data for data-related classes and interfaces.
 * - Depends on com.hack23.cia.service.api for service-related classes and interfaces.
 * - Depends on org.springframework for Spring framework classes and interfaces.
 *
 * Annotations:
 * - @XmlSchema: Defines the XML namespace and element form default for the package.
 *
 * The package is responsible for providing the necessary structure and implementation for managing committee data within the internal application of the Citizen Intelligence Agency. The key classes and interfaces within this package manage various aspects of committee data, such as embedded IDs for committee decision type organization summary, committee decision type summary, committee, vote data ballot, and views of committee data. The package also has dependencies on other packages for specific functionalities, such as data-related classes, service-related classes, and Spring framework.
 */
@javax.xml.bind.annotation.XmlSchema(namespace = "http://committee.data.application.internal.model.cia.hack23.com/impl", elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)
package com.hack23.cia.model.internal.application.data.committee.impl;
