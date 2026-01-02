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
 */
package com.hack23.cia.model.internal.application.data.committee.impl;

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
 * The Class ViewRiksdagenCommitteeParliamentMemberProposal.
 * 
 * <p>Provides a view of parliament member proposals (motions) submitted to committees.
 * Maps committee assignments to their associated motion documents for analysis of
 * legislative proposals from parliament members.</p>
 * 
 * <p><b>Intelligence Purpose:</b> Supporting data access for temporal analysis of committee
 * workload, member proposal activity, and legislative agenda patterns.</p>
 * 
 * <p><b>Use Cases:</b>
 * <ul>
 *   <li>Committee workload analysis (motion volume per committee)</li>
 *   <li>Member proposal productivity tracking</li>
 *   <li>Legislative agenda analysis</li>
 *   <li>Committee-member engagement metrics</li>
 * </ul>
 * </p>
 * 
 * <p>Database view: {@code view_riksdagen_committee_parliament_member_proposal}</p>
 * 
 * <p>Schema location: full_schema.sql lines 7775-7803</p>
 */
@Entity
@Table(name = "view_riksdagen_committee_parliament_member_proposal")
public class ViewRiksdagenCommitteeParliamentMemberProposal implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The embedded id detail (committee detail). */
	@Column(name = "embedded_id_detail", length = 255)
	private String embeddedIdDetail;

	/** The embedded id org code (committee org code). */
	@Column(name = "embedded_id_org_code", length = 255)
	private String embeddedIdOrgCode;

	/** The total assignments (committee assignments count). */
	@Column(name = "total_assignments")
	private Long totalAssignments;

	/** The id (document id - primary key). */
	@Id
	@Column(name = "id", length = 255, nullable = false)
	private String id;

	/** The committee report url xml. */
	@Column(name = "committee_report_url_xml", length = 1024)
	private String committeeReportUrlXml;

	/** The document status url www. */
	@Column(name = "document_status_url_www", length = 1024)
	private String documentStatusUrlWww;

	/** The document status url xml. */
	@Column(name = "document_status_url_xml", length = 1024)
	private String documentStatusUrlXml;

	/** The document type (MOT/mot/Motion). */
	@Column(name = "document_type", length = 255)
	private String documentType;

	/** The document url html. */
	@Column(name = "document_url_html", length = 1024)
	private String documentUrlHtml;

	/** The document url text. */
	@Column(name = "document_url_text", length = 1024)
	private String documentUrlText;

	/** The final number (document final number). */
	@Column(name = "final_number")
	private Integer finalNumber;

	/** The hangar id (document hangar identifier). */
	@Column(name = "hangar_id")
	private Integer hangarId;

	/** The label (document label). */
	@Column(name = "label", length = 255)
	private String label;

	/** The made public date. */
	@Temporal(TemporalType.DATE)
	@Column(name = "made_public_date")
	private Date madePublicDate;

	/** The number value (document number). */
	@Column(name = "number_value")
	private Integer numberValue;

	/** The org (organization/committee). */
	@Column(name = "org", length = 255)
	private String org;

	/** The rm (riksmöte - parliament session). */
	@Column(name = "rm", length = 255)
	private String rm;

	/** The status (document status). */
	@Column(name = "status", length = 255)
	private String status;

	/** The sub title. */
	@Column(name = "sub_title", length = 1024)
	private String subTitle;

	/** The sub type (document sub type). */
	@Column(name = "sub_type", length = 255)
	private String subType;

	/** The temp label (temporary label). */
	@Column(name = "temp_label", length = 255)
	private String tempLabel;

	/** The title. */
	@Column(name = "title", length = 1024)
	private String title;

	/**
	 * Instantiates a new view riksdagen committee parliament member proposal.
	 */
	public ViewRiksdagenCommitteeParliamentMemberProposal() {
		super();
	}

	/**
	 * Gets the embedded id detail.
	 *
	 * @return the embedded id detail
	 */
	public String getEmbeddedIdDetail() {
		return embeddedIdDetail;
	}

	/**
	 * Sets the embedded id detail.
	 *
	 * @param embeddedIdDetail the new embedded id detail
	 */
	public void setEmbeddedIdDetail(String embeddedIdDetail) {
		this.embeddedIdDetail = embeddedIdDetail;
	}

	/**
	 * Gets the embedded id org code.
	 *
	 * @return the embedded id org code
	 */
	public String getEmbeddedIdOrgCode() {
		return embeddedIdOrgCode;
	}

	/**
	 * Sets the embedded id org code.
	 *
	 * @param embeddedIdOrgCode the new embedded id org code
	 */
	public void setEmbeddedIdOrgCode(String embeddedIdOrgCode) {
		this.embeddedIdOrgCode = embeddedIdOrgCode;
	}

	/**
	 * Gets the total assignments.
	 *
	 * @return the total assignments
	 */
	public Long getTotalAssignments() {
		return totalAssignments;
	}

	/**
	 * Sets the total assignments.
	 *
	 * @param totalAssignments the new total assignments
	 */
	public void setTotalAssignments(Long totalAssignments) {
		this.totalAssignments = totalAssignments;
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
	public void setId(String id) {
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
	public void setCommitteeReportUrlXml(String committeeReportUrlXml) {
		this.committeeReportUrlXml = committeeReportUrlXml;
	}

	/**
	 * Gets the document status url www.
	 *
	 * @return the document status url www
	 */
	public String getDocumentStatusUrlWww() {
		return documentStatusUrlWww;
	}

	/**
	 * Sets the document status url www.
	 *
	 * @param documentStatusUrlWww the new document status url www
	 */
	public void setDocumentStatusUrlWww(String documentStatusUrlWww) {
		this.documentStatusUrlWww = documentStatusUrlWww;
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
	public void setDocumentStatusUrlXml(String documentStatusUrlXml) {
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
	public void setDocumentType(String documentType) {
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
	public void setDocumentUrlHtml(String documentUrlHtml) {
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
	public void setDocumentUrlText(String documentUrlText) {
		this.documentUrlText = documentUrlText;
	}

	/**
	 * Gets the final number.
	 *
	 * @return the final number
	 */
	public Integer getFinalNumber() {
		return finalNumber;
	}

	/**
	 * Sets the final number.
	 *
	 * @param finalNumber the new final number
	 */
	public void setFinalNumber(Integer finalNumber) {
		this.finalNumber = finalNumber;
	}

	/**
	 * Gets the hangar id.
	 *
	 * @return the hangar id
	 */
	public Integer getHangarId() {
		return hangarId;
	}

	/**
	 * Sets the hangar id.
	 *
	 * @param hangarId the new hangar id
	 */
	public void setHangarId(Integer hangarId) {
		this.hangarId = hangarId;
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
	public void setLabel(String label) {
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
	public void setMadePublicDate(Date madePublicDate) {
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
	public void setNumberValue(Integer numberValue) {
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
	public void setOrg(String org) {
		this.org = org;
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
	public void setRm(String rm) {
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
	public void setStatus(String status) {
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
	public void setSubTitle(String subTitle) {
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
	public void setSubType(String subType) {
		this.subType = subType;
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
	public void setTempLabel(String tempLabel) {
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
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
