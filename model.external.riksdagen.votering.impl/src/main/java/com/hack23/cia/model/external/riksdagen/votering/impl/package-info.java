/**
 * This package contains implementations for handling voting data from the Swedish Parliament (Riksdagen).
 *
 * Key classes:
 * - BallotContainer: Represents a container for ballot documents.
 * - BallotDocumentData: Represents the data of a ballot document.
 * - BallotDocumentElement: Represents an element of a ballot document.
 * - VoteData: Represents the data of a vote.
 * - VoteDataDto: Represents a data transfer object for vote data.
 * - VoteDataEmbeddedId: Represents an embedded ID for vote data.
 * - VoteDecision: Enum representing different vote decisions.
 * - BallotType: Enum representing different ballot types.
 * - SexType: Enum representing different sex types.
 * - VoteIssueType: Enum representing different vote issue types.
 *
 * Dependencies and relationships:
 * - Depends on javax.persistence for entity and table annotations.
 * - Depends on javax.xml.bind.annotation for XML binding annotations.
 * - Depends on org.apache.commons.lang3.builder for utility classes.
 * - Depends on com.hack23.cia.model.common.api for the ModelObject interface.
 */
@javax.xml.bind.annotation.XmlSchema(namespace = "http://votering.riksdagen.external.model.cia.hack23.com/impl")
package com.hack23.cia.model.external.riksdagen.votering.impl;
