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
package com.hack23.cia.model.internal.application.data.rules.impl;

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
 * JPA entity for view_risk_score_evolution database view.
 * 
 * Intelligence Purpose: Track temporal evolution of risk scores with severity
 * classification and trend analysis. Critical for all 45 rules.
 * 
 * Created by: Liquibase v1.30 (OSINT Performance Tracking)
 * Risk Rules Supported: All 45 rules (cross-cutting risk assessment)
 * 
 * Monthly risk score calculation with severity escalation tracking
 */
@Entity(name = "ViewRiskScoreEvolution")
@Table(name = "view_risk_score_evolution")
public class ViewRiskScoreEvolution implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The person id. */
	@Id
	@Column(name = "person_id", nullable = false, length = 255)
	private String personId;

	/** The assessment period. */
	@Id
	@Column(name = "assessment_period", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date assessmentPeriod;

	/** The first name. */
	@Column(name = "first_name", length = 255)
	private String firstName;

	/** The last name. */
	@Column(name = "last_name", length = 255)
	private String lastName;

	/** The party. */
	@Column(name = "party", length = 50)
	private String party;

	/** The assessment period end. */
	@Column(name = "assessment_period_end")
	@Temporal(TemporalType.DATE)
	private Date assessmentPeriodEnd;

	/** The absence rate. */
	@Column(name = "absence_rate", precision = 5, scale = 2)
	private BigDecimal absenceRate;

	/** The win rate. */
	@Column(name = "win_rate", precision = 5, scale = 2)
	private BigDecimal winRate;

	/** The rebel rate. */
	@Column(name = "rebel_rate", precision = 5, scale = 2)
	private BigDecimal rebelRate;

	/** The ballot count. */
	@Column(name = "ballot_count")
	private Long ballotCount;

	/** The document count. */
	@Column(name = "document_count")
	private Long documentCount;

	/** The violation count. */
	@Column(name = "violation_count")
	private Integer violationCount;

	/** The violation categories. */
	@Column(name = "violation_categories")
	private Integer violationCategories;

	/** The violation types. */
	@Column(name = "violation_types", length = 500)
	private String violationTypes;

	/** The risk score. */
	@Column(name = "risk_score", precision = 10, scale = 2)
	private BigDecimal riskScore;

	/** The prev risk score. */
	@Column(name = "prev_risk_score", precision = 10, scale = 2)
	private BigDecimal prevRiskScore;

	/** The risk score change. */
	@Column(name = "risk_score_change", precision = 10, scale = 2)
	private BigDecimal riskScoreChange;

	/** The risk trend. */
	@Column(name = "risk_trend", length = 50)
	private String riskTrend;

	/** The risk severity. */
	@Column(name = "risk_severity", length = 50)
	private String riskSeverity;

	/** The severity transition. */
	@Column(name = "severity_transition", length = 100)
	private String severityTransition;

	/** The risk assessment. */
	@Column(name = "risk_assessment", length = 255)
	private String riskAssessment;

	/**
	 * Instantiates a new view risk score evolution.
	 */
	public ViewRiskScoreEvolution() {
		super();
	}

	/**
	 * Gets the person id.
	 *
	 * @return the person id
	 */
	public String getPersonId() {
		return personId;
	}

	/**
	 * Sets the person id.
	 *
	 * @param personId the new person id
	 */
	public void setPersonId(final String personId) {
		this.personId = personId;
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
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the party.
	 *
	 * @return the party
	 */
	public String getParty() {
		return party;
	}

	/**
	 * Sets the party.
	 *
	 * @param party the new party
	 */
	public void setParty(final String party) {
		this.party = party;
	}

	/**
	 * Gets the assessment period end.
	 *
	 * @return the assessment period end
	 */
	public Date getAssessmentPeriodEnd() {
		return assessmentPeriodEnd;
	}

	/**
	 * Sets the assessment period end.
	 *
	 * @param assessmentPeriodEnd the new assessment period end
	 */
	public void setAssessmentPeriodEnd(final Date assessmentPeriodEnd) {
		this.assessmentPeriodEnd = assessmentPeriodEnd;
	}

	/**
	 * Gets the absence rate.
	 *
	 * @return the absence rate
	 */
	public BigDecimal getAbsenceRate() {
		return absenceRate;
	}

	/**
	 * Sets the absence rate.
	 *
	 * @param absenceRate the new absence rate
	 */
	public void setAbsenceRate(final BigDecimal absenceRate) {
		this.absenceRate = absenceRate;
	}

	/**
	 * Gets the win rate.
	 *
	 * @return the win rate
	 */
	public BigDecimal getWinRate() {
		return winRate;
	}

	/**
	 * Sets the win rate.
	 *
	 * @param winRate the new win rate
	 */
	public void setWinRate(final BigDecimal winRate) {
		this.winRate = winRate;
	}

	/**
	 * Gets the rebel rate.
	 *
	 * @return the rebel rate
	 */
	public BigDecimal getRebelRate() {
		return rebelRate;
	}

	/**
	 * Sets the rebel rate.
	 *
	 * @param rebelRate the new rebel rate
	 */
	public void setRebelRate(final BigDecimal rebelRate) {
		this.rebelRate = rebelRate;
	}

	/**
	 * Gets the ballot count.
	 *
	 * @return the ballot count
	 */
	public Long getBallotCount() {
		return ballotCount;
	}

	/**
	 * Sets the ballot count.
	 *
	 * @param ballotCount the new ballot count
	 */
	public void setBallotCount(final Long ballotCount) {
		this.ballotCount = ballotCount;
	}

	/**
	 * Gets the document count.
	 *
	 * @return the document count
	 */
	public Long getDocumentCount() {
		return documentCount;
	}

	/**
	 * Sets the document count.
	 *
	 * @param documentCount the new document count
	 */
	public void setDocumentCount(final Long documentCount) {
		this.documentCount = documentCount;
	}

	/**
	 * Gets the violation count.
	 *
	 * @return the violation count
	 */
	public Integer getViolationCount() {
		return violationCount;
	}

	/**
	 * Sets the violation count.
	 *
	 * @param violationCount the new violation count
	 */
	public void setViolationCount(final Integer violationCount) {
		this.violationCount = violationCount;
	}

	/**
	 * Gets the violation categories.
	 *
	 * @return the violation categories
	 */
	public Integer getViolationCategories() {
		return violationCategories;
	}

	/**
	 * Sets the violation categories.
	 *
	 * @param violationCategories the new violation categories
	 */
	public void setViolationCategories(final Integer violationCategories) {
		this.violationCategories = violationCategories;
	}

	/**
	 * Gets the violation types.
	 *
	 * @return the violation types
	 */
	public String getViolationTypes() {
		return violationTypes;
	}

	/**
	 * Sets the violation types.
	 *
	 * @param violationTypes the new violation types
	 */
	public void setViolationTypes(final String violationTypes) {
		this.violationTypes = violationTypes;
	}

	/**
	 * Gets the risk score.
	 *
	 * @return the risk score
	 */
	public BigDecimal getRiskScore() {
		return riskScore;
	}

	/**
	 * Sets the risk score.
	 *
	 * @param riskScore the new risk score
	 */
	public void setRiskScore(final BigDecimal riskScore) {
		this.riskScore = riskScore;
	}

	/**
	 * Gets the prev risk score.
	 *
	 * @return the prev risk score
	 */
	public BigDecimal getPrevRiskScore() {
		return prevRiskScore;
	}

	/**
	 * Sets the prev risk score.
	 *
	 * @param prevRiskScore the new prev risk score
	 */
	public void setPrevRiskScore(final BigDecimal prevRiskScore) {
		this.prevRiskScore = prevRiskScore;
	}

	/**
	 * Gets the risk score change.
	 *
	 * @return the risk score change
	 */
	public BigDecimal getRiskScoreChange() {
		return riskScoreChange;
	}

	/**
	 * Sets the risk score change.
	 *
	 * @param riskScoreChange the new risk score change
	 */
	public void setRiskScoreChange(final BigDecimal riskScoreChange) {
		this.riskScoreChange = riskScoreChange;
	}

	/**
	 * Gets the risk trend.
	 *
	 * @return the risk trend
	 */
	public String getRiskTrend() {
		return riskTrend;
	}

	/**
	 * Sets the risk trend.
	 *
	 * @param riskTrend the new risk trend
	 */
	public void setRiskTrend(final String riskTrend) {
		this.riskTrend = riskTrend;
	}

	/**
	 * Gets the risk severity.
	 *
	 * @return the risk severity
	 */
	public String getRiskSeverity() {
		return riskSeverity;
	}

	/**
	 * Sets the risk severity.
	 *
	 * @param riskSeverity the new risk severity
	 */
	public void setRiskSeverity(final String riskSeverity) {
		this.riskSeverity = riskSeverity;
	}

	/**
	 * Gets the severity transition.
	 *
	 * @return the severity transition
	 */
	public String getSeverityTransition() {
		return severityTransition;
	}

	/**
	 * Sets the severity transition.
	 *
	 * @param severityTransition the new severity transition
	 */
	public void setSeverityTransition(final String severityTransition) {
		this.severityTransition = severityTransition;
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
