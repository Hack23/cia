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
 * JPA entity for view_politician_risk_summary database view.
 * 
 * Intelligence Purpose: Aggregated politician risk indicators combining violations,
 * voting behavior, and productivity metrics to generate a comprehensive risk score.
 * Enables risk-based monitoring and early warning for performance issues.
 * 
 * Created by: Database schema v1.39+ (Fixed materialized view dependency)
 * Risk Rules Supported: P-01 through P-24 (All politician risk rules)
 * 
 * Risk scoring algorithm considers:
 * - Rule violations (weighted by count)
 * - Absence rate (last 2 years)
 * - Rebel rate (party discipline)
 * - Document productivity (last year)
 */
@Entity(name = "ViewPoliticianRiskSummary")
@Table(name = "view_politician_risk_summary")
public class ViewPoliticianRiskSummary implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The person id. */
	@Id
	@Column(name = "person_id", nullable = false, length = 255)
	private String personId;

	/** The first name. */
	@Column(name = "first_name", length = 255)
	private String firstName;

	/** The last name. */
	@Column(name = "last_name", length = 255)
	private String lastName;

	/** The party. */
	@Column(name = "party", length = 50)
	private String party;

	/** The status. */
	@Column(name = "status", length = 100)
	private String status;

	/** The total violations. */
	@Column(name = "total_violations")
	private Long totalViolations;

	/** The latest violation date. */
	@Column(name = "latest_violation_date")
	@Temporal(TemporalType.DATE)
	private Date latestViolationDate;

	/** The absenteeism violations. */
	@Column(name = "absenteeism_violations")
	private Long absenteeismViolations;

	/** The effectiveness violations. */
	@Column(name = "effectiveness_violations")
	private Long effectivenessViolations;

	/** The discipline violations. */
	@Column(name = "discipline_violations")
	private Long disciplineViolations;

	/** The productivity violations. */
	@Column(name = "productivity_violations")
	private Long productivityViolations;

	/** The collaboration violations. */
	@Column(name = "collaboration_violations")
	private Long collaborationViolations;

	/** The annual absence rate. */
	@Column(name = "annual_absence_rate", precision = 10, scale = 2)
	private BigDecimal annualAbsenceRate;

	/** The annual rebel rate. */
	@Column(name = "annual_rebel_rate", precision = 10, scale = 2)
	private BigDecimal annualRebelRate;

	/** The annual vote count. */
	@Column(name = "annual_vote_count")
	private Long annualVoteCount;

	/** The documents last year. */
	@Column(name = "documents_last_year")
	private Long documentsLastYear;

	/** The risk score. */
	@Column(name = "risk_score", precision = 10, scale = 2)
	private BigDecimal riskScore;

	/** The risk level. */
	@Column(name = "risk_level", length = 50)
	private String riskLevel;

	/** The risk assessment. */
	@Column(name = "risk_assessment", length = 500)
	private String riskAssessment;

	/**
	 * Instantiates a new view politician risk summary.
	 */
	public ViewPoliticianRiskSummary() {
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
	 * Gets the total violations.
	 *
	 * @return the total violations
	 */
	public Long getTotalViolations() {
		return totalViolations;
	}

	/**
	 * Sets the total violations.
	 *
	 * @param totalViolations the new total violations
	 */
	public void setTotalViolations(final Long totalViolations) {
		this.totalViolations = totalViolations;
	}

	/**
	 * Gets the latest violation date.
	 *
	 * @return the latest violation date
	 */
	public Date getLatestViolationDate() {
		return latestViolationDate;
	}

	/**
	 * Sets the latest violation date.
	 *
	 * @param latestViolationDate the new latest violation date
	 */
	public void setLatestViolationDate(final Date latestViolationDate) {
		this.latestViolationDate = latestViolationDate;
	}

	/**
	 * Gets the absenteeism violations.
	 *
	 * @return the absenteeism violations
	 */
	public Long getAbsenteeismViolations() {
		return absenteeismViolations;
	}

	/**
	 * Sets the absenteeism violations.
	 *
	 * @param absenteeismViolations the new absenteeism violations
	 */
	public void setAbsenteeismViolations(final Long absenteeismViolations) {
		this.absenteeismViolations = absenteeismViolations;
	}

	/**
	 * Gets the effectiveness violations.
	 *
	 * @return the effectiveness violations
	 */
	public Long getEffectivenessViolations() {
		return effectivenessViolations;
	}

	/**
	 * Sets the effectiveness violations.
	 *
	 * @param effectivenessViolations the new effectiveness violations
	 */
	public void setEffectivenessViolations(final Long effectivenessViolations) {
		this.effectivenessViolations = effectivenessViolations;
	}

	/**
	 * Gets the discipline violations.
	 *
	 * @return the discipline violations
	 */
	public Long getDisciplineViolations() {
		return disciplineViolations;
	}

	/**
	 * Sets the discipline violations.
	 *
	 * @param disciplineViolations the new discipline violations
	 */
	public void setDisciplineViolations(final Long disciplineViolations) {
		this.disciplineViolations = disciplineViolations;
	}

	/**
	 * Gets the productivity violations.
	 *
	 * @return the productivity violations
	 */
	public Long getProductivityViolations() {
		return productivityViolations;
	}

	/**
	 * Sets the productivity violations.
	 *
	 * @param productivityViolations the new productivity violations
	 */
	public void setProductivityViolations(final Long productivityViolations) {
		this.productivityViolations = productivityViolations;
	}

	/**
	 * Gets the collaboration violations.
	 *
	 * @return the collaboration violations
	 */
	public Long getCollaborationViolations() {
		return collaborationViolations;
	}

	/**
	 * Sets the collaboration violations.
	 *
	 * @param collaborationViolations the new collaboration violations
	 */
	public void setCollaborationViolations(final Long collaborationViolations) {
		this.collaborationViolations = collaborationViolations;
	}

	/**
	 * Gets the annual absence rate.
	 *
	 * @return the annual absence rate
	 */
	public BigDecimal getAnnualAbsenceRate() {
		return annualAbsenceRate;
	}

	/**
	 * Sets the annual absence rate.
	 *
	 * @param annualAbsenceRate the new annual absence rate
	 */
	public void setAnnualAbsenceRate(final BigDecimal annualAbsenceRate) {
		this.annualAbsenceRate = annualAbsenceRate;
	}

	/**
	 * Gets the annual rebel rate.
	 *
	 * @return the annual rebel rate
	 */
	public BigDecimal getAnnualRebelRate() {
		return annualRebelRate;
	}

	/**
	 * Sets the annual rebel rate.
	 *
	 * @param annualRebelRate the new annual rebel rate
	 */
	public void setAnnualRebelRate(final BigDecimal annualRebelRate) {
		this.annualRebelRate = annualRebelRate;
	}

	/**
	 * Gets the annual vote count.
	 *
	 * @return the annual vote count
	 */
	public Long getAnnualVoteCount() {
		return annualVoteCount;
	}

	/**
	 * Sets the annual vote count.
	 *
	 * @param annualVoteCount the new annual vote count
	 */
	public void setAnnualVoteCount(final Long annualVoteCount) {
		this.annualVoteCount = annualVoteCount;
	}

	/**
	 * Gets the documents last year.
	 *
	 * @return the documents last year
	 */
	public Long getDocumentsLastYear() {
		return documentsLastYear;
	}

	/**
	 * Sets the documents last year.
	 *
	 * @param documentsLastYear the new documents last year
	 */
	public void setDocumentsLastYear(final Long documentsLastYear) {
		this.documentsLastYear = documentsLastYear;
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
