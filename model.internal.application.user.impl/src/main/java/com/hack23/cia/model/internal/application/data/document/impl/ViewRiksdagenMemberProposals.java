/*
 * Copyright 2010-2025 James Pether Sörling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *	$Id$
 *  $HeadURL$
 */
package com.hack23.cia.model.internal.application.data.document.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * JPA entity for view_riksdagen_member_proposals database view.
 * 
 * Intelligence Purpose: Track member proposals (motions) submitted to the Riksdagen.
 * Provides access to all motion documents with complete metadata for proposal analysis
 * and member productivity tracking.
 * 
 * Created by: Liquibase 1.1+
 * Enhanced by: Liquibase 1.33 (fixed case-sensitive document type filtering)
 * 
 * Enables temporal analysis of member legislative initiatives and proposal patterns.
 */
@Entity(name = "ViewRiksdagenMemberProposals")
@Table(name = "view_riksdagen_member_proposals")
public class ViewRiksdagenMemberProposals implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "id", nullable = false, length = 255)
	private String id;

	/** The committee report url xml. */
	@Column(name = "committee_report_url_xml", length = 500)
	private String committeeReportUrlXml;

	/** The created date. */
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	/** The document format. */
	@Column(name = "document_format", length = 50)
	private String documentFormat;

	/** The document status url xml. */
	@Column(name = "document_status_url_xml", length = 500)
	private String documentStatusUrlXml;

	/** The document type. */
	@Column(name = "document_type", length = 50)
	private String documentType;

	/** The document url html. */
	@Column(name = "document_url_html", length = 500)
	private String documentUrlHtml;

	/** The document url text. */
	@Column(name = "document_url_text", length = 500)
	private String documentUrlText;

	/** The hit. */
	@Column(name = "hit")
	private Boolean hit;

	/** The kall id. */
	@Column(name = "kall_id", length = 50)
	private String kallId;

	/** The label. */
	@Column(name = "label", length = 255)
	private String label;

	/** The made public date. */
	@Column(name = "made_public_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date madePublicDate;

	/** The number value. */
	@Column(name = "number_value")
	private Integer numberValue;

	/** The org. */
	@Column(name = "org", length = 255)
	private String org;

	/** The related id. */
	@Column(name = "related_id", length = 255)
	private String relatedId;

	/** The rm. */
	@Column(name = "rm", length = 50)
	private String rm;

	/** The status. */
	@Column(name = "status", length = 255)
	private String status;

	/** The sub title. */
	@Column(name = "sub_title", length = 1000)
	private String subTitle;

	/** The sub type. */
	@Column(name = "sub_type", length = 100)
	private String subType;

	/** The system date. */
	@Column(name = "system_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date systemDate;

	/** The temp label. */
	@Column(name = "temp_label", length = 255)
	private String tempLabel;

	/** The title. */
	@Column(name = "title", length = 1000)
	private String title;

	/** The dokument document container 0. */
	@Column(name = "dokument_document_container__0")
	private Long dokumentDocumentContainer0;

	/**
	 * Instantiates a new view riksdagen member proposals.
	 */
	public ViewRiksdagenMemberProposals() {
		super();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * Gets the committee report url xml.
	 *
	 * @return the committee report url xml
	 */
	public String getCommitteeReportUrlXml() {
		return committeeReportUrlXml;
	}

	/**
	 * Sets the committee report url xml.
	 *
	 * @param committeeReportUrlXml the new committee report url xml
	 */
	public void setCommitteeReportUrlXml(final String committeeReportUrlXml) {
		this.committeeReportUrlXml = committeeReportUrlXml;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the document format.
	 *
	 * @return the document format
	 */
	public String getDocumentFormat() {
		return documentFormat;
	}

	/**
	 * Sets the document format.
	 *
	 * @param documentFormat the new document format
	 */
	public void setDocumentFormat(final String documentFormat) {
		this.documentFormat = documentFormat;
	}

	/**
	 * Gets the document status url xml.
	 *
	 * @return the document status url xml
	 */
	public String getDocumentStatusUrlXml() {
		return documentStatusUrlXml;
	}

	/**
	 * Sets the document status url xml.
	 *
	 * @param documentStatusUrlXml the new document status url xml
	 */
	public void setDocumentStatusUrlXml(final String documentStatusUrlXml) {
		this.documentStatusUrlXml = documentStatusUrlXml;
	}

	/**
	 * Gets the document type.
	 *
	 * @return the document type
	 */
	public String getDocumentType() {
		return documentType;
	}

	/**
	 * Sets the document type.
	 *
	 * @param documentType the new document type
	 */
	public void setDocumentType(final String documentType) {
		this.documentType = documentType;
	}

	/**
	 * Gets the document url html.
	 *
	 * @return the document url html
	 */
	public String getDocumentUrlHtml() {
		return documentUrlHtml;
	}

	/**
	 * Sets the document url html.
	 *
	 * @param documentUrlHtml the new document url html
	 */
	public void setDocumentUrlHtml(final String documentUrlHtml) {
		this.documentUrlHtml = documentUrlHtml;
	}

	/**
	 * Gets the document url text.
	 *
	 * @return the document url text
	 */
	public String getDocumentUrlText() {
		return documentUrlText;
	}

	/**
	 * Sets the document url text.
	 *
	 * @param documentUrlText the new document url text
	 */
	public void setDocumentUrlText(final String documentUrlText) {
		this.documentUrlText = documentUrlText;
	}

	/**
	 * Gets the hit.
	 *
	 * @return the hit
	 */
	public Boolean getHit() {
		return hit;
	}

	/**
	 * Sets the hit.
	 *
	 * @param hit the new hit
	 */
	public void setHit(final Boolean hit) {
		this.hit = hit;
	}

	/**
	 * Gets the kall id.
	 *
	 * @return the kall id
	 */
	public String getKallId() {
		return kallId;
	}

	/**
	 * Sets the kall id.
	 *
	 * @param kallId the new kall id
	 */
	public void setKallId(final String kallId) {
		this.kallId = kallId;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	public void setLabel(final String label) {
		this.label = label;
	}

	/**
	 * Gets the made public date.
	 *
	 * @return the made public date
	 */
	public Date getMadePublicDate() {
		return madePublicDate;
	}

	/**
	 * Sets the made public date.
	 *
	 * @param madePublicDate the new made public date
	 */
	public void setMadePublicDate(final Date madePublicDate) {
		this.madePublicDate = madePublicDate;
	}

	/**
	 * Gets the number value.
	 *
	 * @return the number value
	 */
	public Integer getNumberValue() {
		return numberValue;
	}

	/**
	 * Sets the number value.
	 *
	 * @param numberValue the new number value
	 */
	public void setNumberValue(final Integer numberValue) {
		this.numberValue = numberValue;
	}

	/**
	 * Gets the org.
	 *
	 * @return the org
	 */
	public String getOrg() {
		return org;
	}

	/**
	 * Sets the org.
	 *
	 * @param org the new org
	 */
	public void setOrg(final String org) {
		this.org = org;
	}

	/**
	 * Gets the related id.
	 *
	 * @return the related id
	 */
	public String getRelatedId() {
		return relatedId;
	}

	/**
	 * Sets the related id.
	 *
	 * @param relatedId the new related id
	 */
	public void setRelatedId(final String relatedId) {
		this.relatedId = relatedId;
	}

	/**
	 * Gets the rm.
	 *
	 * @return the rm
	 */
	public String getRm() {
		return rm;
	}

	/**
	 * Sets the rm.
	 *
	 * @param rm the new rm
	 */
	public void setRm(final String rm) {
		this.rm = rm;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * Gets the sub title.
	 *
	 * @return the sub title
	 */
	public String getSubTitle() {
		return subTitle;
	}

	/**
	 * Sets the sub title.
	 *
	 * @param subTitle the new sub title
	 */
	public void setSubTitle(final String subTitle) {
		this.subTitle = subTitle;
	}

	/**
	 * Gets the sub type.
	 *
	 * @return the sub type
	 */
	public String getSubType() {
		return subType;
	}

	/**
	 * Sets the sub type.
	 *
	 * @param subType the new sub type
	 */
	public void setSubType(final String subType) {
		this.subType = subType;
	}

	/**
	 * Gets the system date.
	 *
	 * @return the system date
	 */
	public Date getSystemDate() {
		return systemDate;
	}

	/**
	 * Sets the system date.
	 *
	 * @param systemDate the new system date
	 */
	public void setSystemDate(final Date systemDate) {
		this.systemDate = systemDate;
	}

	/**
	 * Gets the temp label.
	 *
	 * @return the temp label
	 */
	public String getTempLabel() {
		return tempLabel;
	}

	/**
	 * Sets the temp label.
	 *
	 * @param tempLabel the new temp label
	 */
	public void setTempLabel(final String tempLabel) {
		this.tempLabel = tempLabel;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Gets the dokument document container 0.
	 *
	 * @return the dokument document container 0
	 */
	public Long getDokumentDocumentContainer0() {
		return dokumentDocumentContainer0;
	}

	/**
	 * Sets the dokument document container 0.
	 *
	 * @param dokumentDocumentContainer0 the new dokument document container 0
	 */
	public void setDokumentDocumentContainer0(final Long dokumentDocumentContainer0) {
		this.dokumentDocumentContainer0 = dokumentDocumentContainer0;
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
