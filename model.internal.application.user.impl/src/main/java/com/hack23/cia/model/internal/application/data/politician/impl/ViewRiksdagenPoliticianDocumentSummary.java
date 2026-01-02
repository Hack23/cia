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
package com.hack23.cia.model.internal.application.data.politician.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class ViewRiksdagenPoliticianDocumentSummary.
 * 
 * <p>Maps to the materialized view <code>view_riksdagen_politician_document_summary</code>
 * which provides all-time aggregated document statistics by politician.</p>
 * 
 * <p><strong>Intelligence Purpose:</strong></p>
 * <ul>
 *   <li><strong>Temporal Analysis Framework:</strong> All-time politician productivity comparison and analysis</li>
 *   <li><strong>Pattern Recognition:</strong> Identifies document activity profiles (Party-focused, Individual-focused, etc.)</li>
 *   <li><strong>Performance Monitoring:</strong> Tracks overall politician legislative productivity metrics</li>
 *   <li><strong>Strategic Analysis:</strong> Analyzes collaboration patterns and activity levels across careers</li>
 * </ul>
 * 
 * <p><strong>Key Metrics:</strong></p>
 * <ul>
 *   <li>Total documents and document type breakdown (motions, propositions)</li>
 *   <li>Motion type classification (party, individual, committee, multi-party)</li>
 *   <li>Activity timeline (first/last document, years active, docs per year)</li>
 *   <li>Activity profile classification (Party-focused/Individual-focused/Committee-focused/Mixed)</li>
 *   <li>Activity level classification (Very High/High/Medium/Low/Inactive)</li>
 *   <li>Collaboration percentage based on multi-party motions</li>
 * </ul>
 * 
 * @author James Pether Sörling
 */
@Entity
@Table(name = "view_riksdagen_politician_document_summary")
public class ViewRiksdagenPoliticianDocumentSummary implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The person reference id. */
	@Id
	@Column(name = "person_reference_id", nullable = false, length = 255)
	private String personReferenceId;

	/** The total documents. */
	@Column(name = "total_documents", nullable = false)
	private BigInteger totalDocuments;

	/** The party motions. */
	@Column(name = "party_motions", nullable = false)
	private BigInteger partyMotions;

	/** The individual motions. */
	@Column(name = "individual_motions", nullable = false)
	private BigInteger individualMotions;

	/** The follow up motions. */
	@Column(name = "follow_up_motions", nullable = false)
	private BigInteger followUpMotions;

	/** The committee motions. */
	@Column(name = "committee_motions", nullable = false)
	private BigInteger committeeMotions;

	/** The multi party motions. */
	@Column(name = "multi_party_motions", nullable = false)
	private BigInteger multiPartyMotions;

	/** The party motions pct. */
	@Column(name = "party_motions_pct", precision = 131089)
	private BigDecimal partyMotionsPct;

	/** The individual motions pct. */
	@Column(name = "individual_motions_pct", precision = 131089)
	private BigDecimal individualMotionsPct;

	/** The committee motions pct. */
	@Column(name = "committee_motions_pct", precision = 131089)
	private BigDecimal committeeMotionsPct;

	/** The first document date. */
	@Column(name = "first_document_date")
	@Temporal(TemporalType.DATE)
	private Date firstDocumentDate;

	/** The last document date. */
	@Column(name = "last_document_date")
	@Temporal(TemporalType.DATE)
	private Date lastDocumentDate;

	/** The years active. */
	@Column(name = "years_active")
	private Integer yearsActive;

	/** The docs per year. */
	@Column(name = "docs_per_year", precision = 131089)
	private BigDecimal docsPerYear;

	/** The documents last year. */
	@Column(name = "documents_last_year", nullable = false)
	private BigInteger documentsLastYear;

	/** The propositions. */
	@Column(name = "propositions", nullable = false)
	private BigInteger propositions;

	/** The government communications. */
	@Column(name = "government_communications", nullable = false)
	private BigInteger governmentCommunications;

	/** The document types as string. */
	@Column(name = "document_types")
	private String documentTypesString;

	/** The activity profile. */
	@Column(name = "activity_profile", length = 20)
	private String activityProfile;

	/** The activity level. */
	@Column(name = "activity_level", length = 20)
	private String activityLevel;

	/** The collaboration percentage. */
	@Column(name = "collaboration_percentage", precision = 131089)
	private BigDecimal collaborationPercentage;

	/**
	 * Instantiates a new view riksdagen politician document summary.
	 */
	public ViewRiksdagenPoliticianDocumentSummary() {
		super();
	}

	/**
	 * Gets the person reference id.
	 *
	 * @return the person reference id
	 */
	public String getPersonReferenceId() {
		return personReferenceId;
	}

	/**
	 * Sets the person reference id.
	 *
	 * @param personReferenceId the new person reference id
	 */
	public void setPersonReferenceId(final String personReferenceId) {
		this.personReferenceId = personReferenceId;
	}

	/**
	 * Gets the total documents.
	 *
	 * @return the total documents
	 */
	public BigInteger getTotalDocuments() {
		return totalDocuments;
	}

	/**
	 * Sets the total documents.
	 *
	 * @param totalDocuments the new total documents
	 */
	public void setTotalDocuments(final BigInteger totalDocuments) {
		this.totalDocuments = totalDocuments;
	}

	/**
	 * Gets the party motions.
	 *
	 * @return the party motions
	 */
	public BigInteger getPartyMotions() {
		return partyMotions;
	}

	/**
	 * Sets the party motions.
	 *
	 * @param partyMotions the new party motions
	 */
	public void setPartyMotions(final BigInteger partyMotions) {
		this.partyMotions = partyMotions;
	}

	/**
	 * Gets the individual motions.
	 *
	 * @return the individual motions
	 */
	public BigInteger getIndividualMotions() {
		return individualMotions;
	}

	/**
	 * Sets the individual motions.
	 *
	 * @param individualMotions the new individual motions
	 */
	public void setIndividualMotions(final BigInteger individualMotions) {
		this.individualMotions = individualMotions;
	}

	/**
	 * Gets the follow up motions.
	 *
	 * @return the follow up motions
	 */
	public BigInteger getFollowUpMotions() {
		return followUpMotions;
	}

	/**
	 * Sets the follow up motions.
	 *
	 * @param followUpMotions the new follow up motions
	 */
	public void setFollowUpMotions(final BigInteger followUpMotions) {
		this.followUpMotions = followUpMotions;
	}

	/**
	 * Gets the committee motions.
	 *
	 * @return the committee motions
	 */
	public BigInteger getCommitteeMotions() {
		return committeeMotions;
	}

	/**
	 * Sets the committee motions.
	 *
	 * @param committeeMotions the new committee motions
	 */
	public void setCommitteeMotions(final BigInteger committeeMotions) {
		this.committeeMotions = committeeMotions;
	}

	/**
	 * Gets the multi party motions.
	 *
	 * @return the multi party motions
	 */
	public BigInteger getMultiPartyMotions() {
		return multiPartyMotions;
	}

	/**
	 * Sets the multi party motions.
	 *
	 * @param multiPartyMotions the new multi party motions
	 */
	public void setMultiPartyMotions(final BigInteger multiPartyMotions) {
		this.multiPartyMotions = multiPartyMotions;
	}

	/**
	 * Gets the party motions pct.
	 *
	 * @return the party motions pct
	 */
	public BigDecimal getPartyMotionsPct() {
		return partyMotionsPct;
	}

	/**
	 * Sets the party motions pct.
	 *
	 * @param partyMotionsPct the new party motions pct
	 */
	public void setPartyMotionsPct(final BigDecimal partyMotionsPct) {
		this.partyMotionsPct = partyMotionsPct;
	}

	/**
	 * Gets the individual motions pct.
	 *
	 * @return the individual motions pct
	 */
	public BigDecimal getIndividualMotionsPct() {
		return individualMotionsPct;
	}

	/**
	 * Sets the individual motions pct.
	 *
	 * @param individualMotionsPct the new individual motions pct
	 */
	public void setIndividualMotionsPct(final BigDecimal individualMotionsPct) {
		this.individualMotionsPct = individualMotionsPct;
	}

	/**
	 * Gets the committee motions pct.
	 *
	 * @return the committee motions pct
	 */
	public BigDecimal getCommitteeMotionsPct() {
		return committeeMotionsPct;
	}

	/**
	 * Sets the committee motions pct.
	 *
	 * @param committeeMotionsPct the new committee motions pct
	 */
	public void setCommitteeMotionsPct(final BigDecimal committeeMotionsPct) {
		this.committeeMotionsPct = committeeMotionsPct;
	}

	/**
	 * Gets the first document date.
	 *
	 * @return the first document date
	 */
	public Date getFirstDocumentDate() {
		return firstDocumentDate;
	}

	/**
	 * Sets the first document date.
	 *
	 * @param firstDocumentDate the new first document date
	 */
	public void setFirstDocumentDate(final Date firstDocumentDate) {
		this.firstDocumentDate = firstDocumentDate;
	}

	/**
	 * Gets the last document date.
	 *
	 * @return the last document date
	 */
	public Date getLastDocumentDate() {
		return lastDocumentDate;
	}

	/**
	 * Sets the last document date.
	 *
	 * @param lastDocumentDate the new last document date
	 */
	public void setLastDocumentDate(final Date lastDocumentDate) {
		this.lastDocumentDate = lastDocumentDate;
	}

	/**
	 * Gets the years active.
	 *
	 * @return the years active
	 */
	public Integer getYearsActive() {
		return yearsActive;
	}

	/**
	 * Sets the years active.
	 *
	 * @param yearsActive the new years active
	 */
	public void setYearsActive(final Integer yearsActive) {
		this.yearsActive = yearsActive;
	}

	/**
	 * Gets the docs per year.
	 *
	 * @return the docs per year
	 */
	public BigDecimal getDocsPerYear() {
		return docsPerYear;
	}

	/**
	 * Sets the docs per year.
	 *
	 * @param docsPerYear the new docs per year
	 */
	public void setDocsPerYear(final BigDecimal docsPerYear) {
		this.docsPerYear = docsPerYear;
	}

	/**
	 * Gets the documents last year.
	 *
	 * @return the documents last year
	 */
	public BigInteger getDocumentsLastYear() {
		return documentsLastYear;
	}

	/**
	 * Sets the documents last year.
	 *
	 * @param documentsLastYear the new documents last year
	 */
	public void setDocumentsLastYear(final BigInteger documentsLastYear) {
		this.documentsLastYear = documentsLastYear;
	}

	/**
	 * Gets the propositions.
	 *
	 * @return the propositions
	 */
	public BigInteger getPropositions() {
		return propositions;
	}

	/**
	 * Sets the propositions.
	 *
	 * @param propositions the new propositions
	 */
	public void setPropositions(final BigInteger propositions) {
		this.propositions = propositions;
	}

	/**
	 * Gets the government communications.
	 *
	 * @return the government communications
	 */
	public BigInteger getGovernmentCommunications() {
		return governmentCommunications;
	}

	/**
	 * Sets the government communications.
	 *
	 * @param governmentCommunications the new government communications
	 */
	public void setGovernmentCommunications(final BigInteger governmentCommunications) {
		this.governmentCommunications = governmentCommunications;
	}

	/**
	 * Gets the document types.
	 *
	 * @return the document types
	 */
	public String[] getDocumentTypes() {
		if (documentTypesString != null) {
			return documentTypesString.replace("{", "").replace("}", "").split(",");
		}
		return new String[0];
	}

	/**
	 * Sets the document types.
	 *
	 * @param documentTypes the new document types
	 */
	public void setDocumentTypes(final String[] documentTypes) {
		if (documentTypes != null && documentTypes.length > 0) {
			this.documentTypesString = "{" + String.join(",", documentTypes) + "}";
		} else {
			this.documentTypesString = null;
		}
	}

	/**
	 * Gets the document types string.
	 *
	 * @return the document types string
	 */
	public String getDocumentTypesString() {
		return documentTypesString;
	}

	/**
	 * Sets the document types string.
	 *
	 * @param documentTypesString the new document types string
	 */
	public void setDocumentTypesString(final String documentTypesString) {
		this.documentTypesString = documentTypesString;
	}

	/**
	 * Gets the activity profile.
	 *
	 * @return the activity profile
	 */
	public String getActivityProfile() {
		return activityProfile;
	}

	/**
	 * Sets the activity profile.
	 *
	 * @param activityProfile the new activity profile
	 */
	public void setActivityProfile(final String activityProfile) {
		this.activityProfile = activityProfile;
	}

	/**
	 * Gets the activity level.
	 *
	 * @return the activity level
	 */
	public String getActivityLevel() {
		return activityLevel;
	}

	/**
	 * Sets the activity level.
	 *
	 * @param activityLevel the new activity level
	 */
	public void setActivityLevel(final String activityLevel) {
		this.activityLevel = activityLevel;
	}

	/**
	 * Gets the collaboration percentage.
	 *
	 * @return the collaboration percentage
	 */
	public BigDecimal getCollaborationPercentage() {
		return collaborationPercentage;
	}

	/**
	 * Sets the collaboration percentage.
	 *
	 * @param collaborationPercentage the new collaboration percentage
	 */
	public void setCollaborationPercentage(final BigDecimal collaborationPercentage) {
		this.collaborationPercentage = collaborationPercentage;
	}

	@Override
	public boolean equals(final Object obj) {
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
