/**
 * This package contains implementations for handling voting lists from the Swedish Parliament (Riksdagen).
 *
 * Key classes:
 * - BallotDocumentElement: Represents an element of a ballot document.
 * - VoteListContainerElement: Represents a container for vote lists.
 *
 * Dependencies and relationships:
 * - Depends on javax.persistence for entity and table annotations.
 * - Depends on javax.xml.bind.annotation for XML binding annotations.
 * - Depends on org.apache.commons.lang3.builder for utility classes.
 * - Depends on com.hack23.cia.model.common.api for the ModelObject interface.
 * - Depends on com.hack23.cia.model.external.riksdagen.votering.impl for handling voting data.
 *
 * Annotations:
 * - @XmlSchema: Defines the XML namespace and element form default for the package.
 */
@javax.xml.bind.annotation.XmlSchema(namespace = "http://voteringlista.riksdagen.external.model.cia.hack23.com/impl")
package com.hack23.cia.model.external.riksdagen.voteringlista.impl;
