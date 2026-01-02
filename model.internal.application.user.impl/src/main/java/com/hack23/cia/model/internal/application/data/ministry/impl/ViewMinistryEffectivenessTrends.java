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
package com.hack23.cia.model.internal.application.data.ministry.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * JPA entity for view_ministry_effectiveness_trends database view.
 * 
 * Intelligence Purpose: Track ministry-level performance metrics over time,
 * monitoring document production, legislative output, and staffing levels.
 * Enables identification of productive vs. underperforming ministries.
 * 
 * Created by: Liquibase v1.31+ (Fixed materialized view dependency v1.42)
 * Risk Rules Supported: M-01 through M-04 (Ministry performance rules)
 * 
 * Quarterly aggregation with trend analysis and effectiveness classification.
 * Part of Predictive Intelligence Framework for government performance monitoring.
 */
@Entity(name = "ViewMinistryEffectivenessTrends")
@Table(name = "view_ministry_effectiveness_trends")
public class ViewMinistryEffectivenessTrends implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The Class CompositePrimaryKey.
	 * 
	 * <p>Composite primary key combining organization code and period start date.</p>
	 */
	@Embeddable
	public static class CompositePrimaryKey implements Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** The org code. */
		@Column(name = "org_code", nullable = false, length = 255)
		private String orgCode;

		/** The period start. */
		@Column(name = "period_start", nullable = false)
		@Temporal(TemporalType.TIMESTAMP)
		private Date periodStart;

		/**
		 * Instantiates a new composite primary key.
		 */
		public CompositePrimaryKey() {
			super();
		}

		/**
		 * Instantiates a new composite primary key.
		 *
		 * @param orgCode the org code
		 * @param periodStart the period start
		 */
		public CompositePrimaryKey(final String orgCode, final Date periodStart) {
			super();
			this.orgCode = orgCode;
			this.periodStart = periodStart;
		}

		/**
		 * Gets the org code.
		 *
		 * @return the org code
		 */
		public String getOrgCode() {
			return orgCode;
		}

		/**
		 * Sets the org code.
		 *
		 * @param orgCode the new org code
		 */
		public void setOrgCode(final String orgCode) {
			this.orgCode = orgCode;
		}

		/**
		 * Gets the period start.
		 *
		 * @return the period start
		 */
		public Date getPeriodStart() {
			return periodStart;
		}

		/**
		 * Sets the period start.
		 *
		 * @param periodStart the new period start
		 */
		public void setPeriodStart(final Date periodStart) {
			this.periodStart = periodStart;
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

	/** The embedded id. */
	@EmbeddedId
	private CompositePrimaryKey embeddedId;

	/** The short code. */
	@Column(name = "short_code", length = 255)
	private String shortCode;

	/** The name. */
	@Column(name = "name", length = 255)
	private String name;

	/** The period end. */
	@Column(name = "period_end")
	@Temporal(TemporalType.DATE)
	private Date periodEnd;

	/** The year. */
	@Column(name = "year")
	private Integer year;

	/** The quarter. */
	@Column(name = "quarter")
	private Integer quarter;

	/** The documents produced. */
	@Column(name = "documents_produced")
	private Long documentsProduced;

	/** The propositions. */
	@Column(name = "propositions")
	private Long propositions;

	/** The government bills. */
	@Column(name = "government_bills")
	private Long governmentBills;

	/** The legislative documents. */
	@Column(name = "legislative_documents")
	private Long legislativeDocuments;

	/** The active members. */
	@Column(name = "active_members")
	private Integer activeMembers;

	/** The document change. */
	@Column(name = "document_change")
	private Long documentChange;

	/** The member change. */
	@Column(name = "member_change")
	private Integer memberChange;

	/** The legislative change. */
	@Column(name = "legislative_change")
	private Long legislativeChange;

	/** The ma 4quarter documents. */
	@Column(name = "ma_4quarter_documents", precision = 10, scale = 2)
	private BigDecimal movingAvg4QuarterDocuments;

	/** The ma 4quarter legislative. */
	@Column(name = "ma_4quarter_legislative", precision = 10, scale = 2)
	private BigDecimal movingAvg4QuarterLegislative;

	/** The ma 4quarter members. */
	@Column(name = "ma_4quarter_members", precision = 10, scale = 2)
	private BigDecimal movingAvg4QuarterMembers;

	/** The documents per member. */
	@Column(name = "documents_per_member", precision = 10, scale = 2)
	private BigDecimal documentsPerMember;

	/** The legislative per member. */
	@Column(name = "legislative_per_member", precision = 10, scale = 2)
	private BigDecimal legislativePerMember;

	/** The productivity level. */
	@Column(name = "productivity_level", length = 50)
	private String productivityLevel;

	/** The legislative status. */
	@Column(name = "legislative_status", length = 50)
	private String legislativeStatus;

	/** The staffing status. */
	@Column(name = "staffing_status", length = 50)
	private String staffingStatus;

	/** The stagnation indicator. */
	@Column(name = "stagnation_indicator", length = 50)
	private String stagnationIndicator;

	/** The effectiveness assessment. */
	@Column(name = "effectiveness_assessment", length = 500)
	private String effectivenessAssessment;

	/**
	 * Instantiates a new view ministry effectiveness trends.
	 */
	public ViewMinistryEffectivenessTrends() {
		super();
	}

	/**
	 * Gets the embedded id.
	 *
	 * @return the embedded id
	 */
	public CompositePrimaryKey getEmbeddedId() {
		return embeddedId;
	}

	/**
	 * Sets the embedded id.
	 *
	 * @param embeddedId the new embedded id
	 */
	public void setEmbeddedId(final CompositePrimaryKey embeddedId) {
		this.embeddedId = embeddedId;
	}

	/**
	 * Gets the short code.
	 *
	 * @return the short code
	 */
	public String getShortCode() {
		return shortCode;
	}

	/**
	 * Sets the short code.
	 *
	 * @param shortCode the new short code
	 */
	public void setShortCode(final String shortCode) {
		this.shortCode = shortCode;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Gets the period end.
	 *
	 * @return the period end
	 */
	public Date getPeriodEnd() {
		return periodEnd;
	}

	/**
	 * Sets the period end.
	 *
	 * @param periodEnd the new period end
	 */
	public void setPeriodEnd(final Date periodEnd) {
		this.periodEnd = periodEnd;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(final Integer year) {
		this.year = year;
	}

	/**
	 * Gets the quarter.
	 *
	 * @return the quarter
	 */
	public Integer getQuarter() {
		return quarter;
	}

	/**
	 * Sets the quarter.
	 *
	 * @param quarter the new quarter
	 */
	public void setQuarter(final Integer quarter) {
		this.quarter = quarter;
	}

	/**
	 * Gets the documents produced.
	 *
	 * @return the documents produced
	 */
	public Long getDocumentsProduced() {
		return documentsProduced;
	}

	/**
	 * Sets the documents produced.
	 *
	 * @param documentsProduced the new documents produced
	 */
	public void setDocumentsProduced(final Long documentsProduced) {
		this.documentsProduced = documentsProduced;
	}

	/**
	 * Gets the propositions.
	 *
	 * @return the propositions
	 */
	public Long getPropositions() {
		return propositions;
	}

	/**
	 * Sets the propositions.
	 *
	 * @param propositions the new propositions
	 */
	public void setPropositions(final Long propositions) {
		this.propositions = propositions;
	}

	/**
	 * Gets the government bills.
	 *
	 * @return the government bills
	 */
	public Long getGovernmentBills() {
		return governmentBills;
	}

	/**
	 * Sets the government bills.
	 *
	 * @param governmentBills the new government bills
	 */
	public void setGovernmentBills(final Long governmentBills) {
		this.governmentBills = governmentBills;
	}

	/**
	 * Gets the legislative documents.
	 *
	 * @return the legislative documents
	 */
	public Long getLegislativeDocuments() {
		return legislativeDocuments;
	}

	/**
	 * Sets the legislative documents.
	 *
	 * @param legislativeDocuments the new legislative documents
	 */
	public void setLegislativeDocuments(final Long legislativeDocuments) {
		this.legislativeDocuments = legislativeDocuments;
	}

	/**
	 * Gets the active members.
	 *
	 * @return the active members
	 */
	public Integer getActiveMembers() {
		return activeMembers;
	}

	/**
	 * Sets the active members.
	 *
	 * @param activeMembers the new active members
	 */
	public void setActiveMembers(final Integer activeMembers) {
		this.activeMembers = activeMembers;
	}

	/**
	 * Gets the document change.
	 *
	 * @return the document change
	 */
	public Long getDocumentChange() {
		return documentChange;
	}

	/**
	 * Sets the document change.
	 *
	 * @param documentChange the new document change
	 */
	public void setDocumentChange(final Long documentChange) {
		this.documentChange = documentChange;
	}

	/**
	 * Gets the member change.
	 *
	 * @return the member change
	 */
	public Integer getMemberChange() {
		return memberChange;
	}

	/**
	 * Sets the member change.
	 *
	 * @param memberChange the new member change
	 */
	public void setMemberChange(final Integer memberChange) {
		this.memberChange = memberChange;
	}

	/**
	 * Gets the legislative change.
	 *
	 * @return the legislative change
	 */
	public Long getLegislativeChange() {
		return legislativeChange;
	}

	/**
	 * Sets the legislative change.
	 *
	 * @param legislativeChange the new legislative change
	 */
	public void setLegislativeChange(final Long legislativeChange) {
		this.legislativeChange = legislativeChange;
	}

	/**
	 * Gets the moving avg 4 quarter documents.
	 *
	 * @return the moving avg 4 quarter documents
	 */
	public BigDecimal getMovingAvg4QuarterDocuments() {
		return movingAvg4QuarterDocuments;
	}

	/**
	 * Sets the moving avg 4 quarter documents.
	 *
	 * @param movingAvg4QuarterDocuments the new moving avg 4 quarter documents
	 */
	public void setMovingAvg4QuarterDocuments(final BigDecimal movingAvg4QuarterDocuments) {
		this.movingAvg4QuarterDocuments = movingAvg4QuarterDocuments;
	}

	/**
	 * Gets the moving avg 4 quarter legislative.
	 *
	 * @return the moving avg 4 quarter legislative
	 */
	public BigDecimal getMovingAvg4QuarterLegislative() {
		return movingAvg4QuarterLegislative;
	}

	/**
	 * Sets the moving avg 4 quarter legislative.
	 *
	 * @param movingAvg4QuarterLegislative the new moving avg 4 quarter legislative
	 */
	public void setMovingAvg4QuarterLegislative(final BigDecimal movingAvg4QuarterLegislative) {
		this.movingAvg4QuarterLegislative = movingAvg4QuarterLegislative;
	}

	/**
	 * Gets the moving avg 4 quarter members.
	 *
	 * @return the moving avg 4 quarter members
	 */
	public BigDecimal getMovingAvg4QuarterMembers() {
		return movingAvg4QuarterMembers;
	}

	/**
	 * Sets the moving avg 4 quarter members.
	 *
	 * @param movingAvg4QuarterMembers the new moving avg 4 quarter members
	 */
	public void setMovingAvg4QuarterMembers(final BigDecimal movingAvg4QuarterMembers) {
		this.movingAvg4QuarterMembers = movingAvg4QuarterMembers;
	}

	/**
	 * Gets the documents per member.
	 *
	 * @return the documents per member
	 */
	public BigDecimal getDocumentsPerMember() {
		return documentsPerMember;
	}

	/**
	 * Sets the documents per member.
	 *
	 * @param documentsPerMember the new documents per member
	 */
	public void setDocumentsPerMember(final BigDecimal documentsPerMember) {
		this.documentsPerMember = documentsPerMember;
	}

	/**
	 * Gets the legislative per member.
	 *
	 * @return the legislative per member
	 */
	public BigDecimal getLegislativePerMember() {
		return legislativePerMember;
	}

	/**
	 * Sets the legislative per member.
	 *
	 * @param legislativePerMember the new legislative per member
	 */
	public void setLegislativePerMember(final BigDecimal legislativePerMember) {
		this.legislativePerMember = legislativePerMember;
	}

	/**
	 * Gets the productivity level.
	 *
	 * @return the productivity level
	 */
	public String getProductivityLevel() {
		return productivityLevel;
	}

	/**
	 * Sets the productivity level.
	 *
	 * @param productivityLevel the new productivity level
	 */
	public void setProductivityLevel(final String productivityLevel) {
		this.productivityLevel = productivityLevel;
	}

	/**
	 * Gets the legislative status.
	 *
	 * @return the legislative status
	 */
	public String getLegislativeStatus() {
		return legislativeStatus;
	}

	/**
	 * Sets the legislative status.
	 *
	 * @param legislativeStatus the new legislative status
	 */
	public void setLegislativeStatus(final String legislativeStatus) {
		this.legislativeStatus = legislativeStatus;
	}

	/**
	 * Gets the staffing status.
	 *
	 * @return the staffing status
	 */
	public String getStaffingStatus() {
		return staffingStatus;
	}

	/**
	 * Sets the staffing status.
	 *
	 * @param staffingStatus the new staffing status
	 */
	public void setStaffingStatus(final String staffingStatus) {
		this.staffingStatus = staffingStatus;
	}

	/**
	 * Gets the stagnation indicator.
	 *
	 * @return the stagnation indicator
	 */
	public String getStagnationIndicator() {
		return stagnationIndicator;
	}

	/**
	 * Sets the stagnation indicator.
	 *
	 * @param stagnationIndicator the new stagnation indicator
	 */
	public void setStagnationIndicator(final String stagnationIndicator) {
		this.stagnationIndicator = stagnationIndicator;
	}

	/**
	 * Gets the effectiveness assessment.
	 *
	 * @return the effectiveness assessment
	 */
	public String getEffectivenessAssessment() {
		return effectivenessAssessment;
	}

	/**
	 * Sets the effectiveness assessment.
	 *
	 * @param effectivenessAssessment the new effectiveness assessment
	 */
	public void setEffectivenessAssessment(final String effectivenessAssessment) {
		this.effectivenessAssessment = effectivenessAssessment;
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
