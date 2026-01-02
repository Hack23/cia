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
 * JPA entity for view_ministry_risk_evolution database view.
 * 
 * Intelligence Purpose: Track historical changes in ministry risk scores and monitor
 * risk severity transitions for ministries. Identifies risk patterns and triggers
 * at ministry level through quarterly risk assessment with trend analysis.
 * 
 * Created by: Liquibase 1.31+
 * Risk Rules Supported: M-01 through M-04 (Ministry risk forecasting rules)
 * 
 * Enables predictive risk intelligence by analyzing document production decline,
 * staffing shortfalls, and legislative output trends to identify ministries
 * requiring intervention.
 */
@Entity(name = "ViewMinistryRiskEvolution")
@Table(name = "view_ministry_risk_evolution")
public class ViewMinistryRiskEvolution implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The org code. */
	@Id
	@Column(name = "org_code", nullable = false, length = 255)
	private String orgCode;

	/** The assessment period. */
	@Id
	@Column(name = "assessment_period", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date assessmentPeriod;

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

	/** The legislative count. */
	@Column(name = "legislative_count")
	private Long legislativeCount;

	/** The active members. */
	@Column(name = "active_members")
	private Long activeMembers;

	/** The document trend. */
	@Column(name = "document_trend")
	private Long documentTrend;

	/** The legislative trend. */
	@Column(name = "legislative_trend")
	private Long legislativeTrend;

	/** The staffing trend. */
	@Column(name = "staffing_trend")
	private Long staffingTrend;

	/** The rolling avg documents. */
	@Column(name = "rolling_avg_documents", precision = 10, scale = 2)
	private BigDecimal rollingAvgDocuments;

	/** The risk level. */
	@Column(name = "risk_level", length = 50)
	private String riskLevel;

	/** The risk assessment. */
	@Column(name = "risk_assessment", length = 500)
	private String riskAssessment;

	/**
	 * Instantiates a new view ministry risk evolution.
	 */
	public ViewMinistryRiskEvolution() {
		super();
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
	 * Gets the assessment period.
	 *
	 * @return the assessment period
	 */
	public Date getAssessmentPeriod() {
		return assessmentPeriod;
	}

	/**
	 * Sets the assessment period.
	 *
	 * @param assessmentPeriod the new assessment period
	 */
	public void setAssessmentPeriod(final Date assessmentPeriod) {
		this.assessmentPeriod = assessmentPeriod;
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
	 * Gets the legislative count.
	 *
	 * @return the legislative count
	 */
	public Long getLegislativeCount() {
		return legislativeCount;
	}

	/**
	 * Sets the legislative count.
	 *
	 * @param legislativeCount the new legislative count
	 */
	public void setLegislativeCount(final Long legislativeCount) {
		this.legislativeCount = legislativeCount;
	}

	/**
	 * Gets the active members.
	 *
	 * @return the active members
	 */
	public Long getActiveMembers() {
		return activeMembers;
	}

	/**
	 * Sets the active members.
	 *
	 * @param activeMembers the new active members
	 */
	public void setActiveMembers(final Long activeMembers) {
		this.activeMembers = activeMembers;
	}

	/**
	 * Gets the document trend.
	 *
	 * @return the document trend
	 */
	public Long getDocumentTrend() {
		return documentTrend;
	}

	/**
	 * Sets the document trend.
	 *
	 * @param documentTrend the new document trend
	 */
	public void setDocumentTrend(final Long documentTrend) {
		this.documentTrend = documentTrend;
	}

	/**
	 * Gets the legislative trend.
	 *
	 * @return the legislative trend
	 */
	public Long getLegislativeTrend() {
		return legislativeTrend;
	}

	/**
	 * Sets the legislative trend.
	 *
	 * @param legislativeTrend the new legislative trend
	 */
	public void setLegislativeTrend(final Long legislativeTrend) {
		this.legislativeTrend = legislativeTrend;
	}

	/**
	 * Gets the staffing trend.
	 *
	 * @return the staffing trend
	 */
	public Long getStaffingTrend() {
		return staffingTrend;
	}

	/**
	 * Sets the staffing trend.
	 *
	 * @param staffingTrend the new staffing trend
	 */
	public void setStaffingTrend(final Long staffingTrend) {
		this.staffingTrend = staffingTrend;
	}

	/**
	 * Gets the rolling avg documents.
	 *
	 * @return the rolling avg documents
	 */
	public BigDecimal getRollingAvgDocuments() {
		return rollingAvgDocuments;
	}

	/**
	 * Sets the rolling avg documents.
	 *
	 * @param rollingAvgDocuments the new rolling avg documents
	 */
	public void setRollingAvgDocuments(final BigDecimal rollingAvgDocuments) {
		this.rollingAvgDocuments = rollingAvgDocuments;
	}

	/**
	 * Gets the risk level.
	 *
	 * @return the risk level
	 */
	public String getRiskLevel() {
		return riskLevel;
	}

	/**
	 * Sets the risk level.
	 *
	 * @param riskLevel the new risk level
	 */
	public void setRiskLevel(final String riskLevel) {
		this.riskLevel = riskLevel;
	}

	/**
	 * Gets the risk assessment.
	 *
	 * @return the risk assessment
	 */
	public String getRiskAssessment() {
		return riskAssessment;
	}

	/**
	 * Sets the risk assessment.
	 *
	 * @param riskAssessment the new risk assessment
	 */
	public void setRiskAssessment(final String riskAssessment) {
		this.riskAssessment = riskAssessment;
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
