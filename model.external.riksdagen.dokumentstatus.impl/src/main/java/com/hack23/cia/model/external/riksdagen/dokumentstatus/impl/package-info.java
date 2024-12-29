/**
 * This package contains implementations for handling document status data from the Swedish Parliament (Riksdagen).
 *
 * Key classes:
 * - DocumentActivityContainer: Represents a container for document activities.
 * - DocumentActivityData: Represents data related to document activities.
 * - DocumentAttachment: Represents a document attachment.
 * - DocumentAttachmentContainer: Represents a container for document attachments.
 * - DocumentData: Represents document data.
 * - DocumentDetailContainer: Represents a container for document details.
 * - DocumentDetailData: Represents data related to document details.
 * - DocumentPersonReferenceContainer: Represents a container for document person references.
 * - DocumentPersonReferenceData: Represents data related to document person references.
 * - DocumentProposalContainer: Represents a container for document proposals.
 * - DocumentProposalData: Represents data related to document proposals.
 * - DocumentReferenceContainer: Represents a container for document references.
 * - DocumentReferenceData: Represents data related to document references.
 * - DocumentStatusContainer: Represents a container for document statuses.
 * - DocumentSubType: Represents a document sub-type.
 * - DocumentType: Represents a document type.
 * - ReferenceType: Represents a reference type.
 *
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.model.common.api for ModelObject interface.
 * - Depends on com.hack23.cia.model.common.impl.xml for XmlDateTypeAdapter.
 * - Uses javax.persistence for JPA annotations.
 * - Uses javax.xml.bind.annotation for XML binding annotations.
 * - Uses org.apache.commons.lang3.builder for EqualsBuilder, HashCodeBuilder, and ToStringBuilder.
 */
@javax.xml.bind.annotation.XmlSchema(namespace = "http://dokumentstatus.riksdagen.external.model.cia.hack23.com/impl")
package com.hack23.cia.model.external.riksdagen.dokumentstatus.impl;
