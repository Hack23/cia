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

import org.hibernate.annotations.Immutable;

/**
 * JPA entity for view_politician_behavioral_trends database view.
 * 
 * Intelligence Purpose: Tracks individual politician behavioral metrics 
 * (absence, effectiveness, rebellion) over time with automated classification.
 * 
 * Created by: Liquibase v1.30 (OSINT Performance Tracking)
 * Risk Rules Supported: P-01 to P-24 (All politician behavioral analysis rules)
 * 
 * Time Series Analysis: Monthly granularity with 3-year historical window
 * Behavioral Metrics: Attendance, voting effectiveness, party discipline
 * Classification: Automated risk assessment and performance categorization
 */
@Entity(name = "ViewPoliticianBehavioralTrends")
@Immutable
@Table(name = "view_politician_behavioral_trends")
public class ViewPoliticianBehavioralTrends implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The person id. */
	@Id
	@Column(name = "person_id", nullable = false, length = 255)
	private String personId;

	/** The period start. */
	@Id
	@Column(name = "period_start", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date periodStart;

	/** The period end. */
	@Column(name = "period_end")
	@Temporal(TemporalType.DATE)
	private Date periodEnd;

	/** The first name. */
	@Column(name = "first_name", length = 255)
	private String firstName;

	/** The last name. */
	@Column(name = "last_name", length = 255)
	private String lastName;

	/** The party. */
	@Column(name = "party", length = 50)
	private String party;

	/** The total ballots. */
	@Column(name = "total_ballots")
	private Long totalBallots;

	/** The total votes. */
	@Column(name = "total_votes")
	private Long totalVotes;

	/** The avg absence rate. */
	@Column(name = "avg_absence_rate", precision = 5, scale = 2)
	private BigDecimal avgAbsenceRate;

	/** The avg yes rate. */
	@Column(name = "avg_yes_rate", precision = 5, scale = 2)
	private BigDecimal avgYesRate;

	/** The avg no rate. */
	@Column(name = "avg_no_rate", precision = 5, scale = 2)
	private BigDecimal avgNoRate;

	/** The avg abstain rate. */
	@Column(name = "avg_abstain_rate", precision = 5, scale = 2)
	private BigDecimal avgAbstainRate;

	/** The avg win rate. */
	@Column(name = "avg_win_rate", precision = 5, scale = 2)
	private BigDecimal avgWinRate;

	/** The avg rebel rate. */
	@Column(name = "avg_rebel_rate", precision = 5, scale = 2)
	private BigDecimal avgRebelRate;

	/** The violation count. */
	@Column(name = "violation_count")
	private Integer violationCount;

	/** The violation types. */
	@Column(name = "violation_types")
	private Integer violationTypes;

	/** The absence trend. */
	@Column(name = "absence_trend", precision = 5, scale = 2)
	private BigDecimal absenceTrend;

	/** The win rate trend. */
	@Column(name = "win_rate_trend", precision = 5, scale = 2)
	private BigDecimal winRateTrend;

	/** The rebel rate trend. */
	@Column(name = "rebel_rate_trend", precision = 5, scale = 2)
	private BigDecimal rebelRateTrend;

	/** The ma 3month absence. */
	@Column(name = "ma_3month_absence", precision = 5, scale = 2)
	private BigDecimal movingAvg3MonthAbsence;

	/** The ma 3month win rate. */
	@Column(name = "ma_3month_win_rate", precision = 5, scale = 2)
	private BigDecimal movingAvg3MonthWinRate;

	/** The ma 3month rebel rate. */
	@Column(name = "ma_3month_rebel_rate", precision = 5, scale = 2)
	private BigDecimal movingAvg3MonthRebelRate;

	/** The attendance status. */
	@Column(name = "attendance_status", length = 50)
	private String attendanceStatus;

	/** The effectiveness status. */
	@Column(name = "effectiveness_status", length = 50)
	private String effectivenessStatus;

	/** The discipline status. */
	@Column(name = "discipline_status", length = 50)
	private String disciplineStatus;

	/** The behavioral assessment. */
	@Column(name = "behavioral_assessment", length = 50)
	private String behavioralAssessment;

	/**
	 * Instantiates a new view politician behavioral trends.
	 */
	public ViewPoliticianBehavioralTrends() {
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
	 * Gets the total ballots.
	 *
	 * @return the total ballots
	 */
	public Long getTotalBallots() {
		return totalBallots;
	}

	/**
	 * Sets the total ballots.
	 *
	 * @param totalBallots the new total ballots
	 */
	public void setTotalBallots(final Long totalBallots) {
		this.totalBallots = totalBallots;
	}

	/**
	 * Gets the total votes.
	 *
	 * @return the total votes
	 */
	public Long getTotalVotes() {
		return totalVotes;
	}

	/**
	 * Sets the total votes.
	 *
	 * @param totalVotes the new total votes
	 */
	public void setTotalVotes(final Long totalVotes) {
		this.totalVotes = totalVotes;
	}

	/**
	 * Gets the avg absence rate.
	 *
	 * @return the avg absence rate
	 */
	public BigDecimal getAvgAbsenceRate() {
		return avgAbsenceRate;
	}

	/**
	 * Sets the avg absence rate.
	 *
	 * @param avgAbsenceRate the new avg absence rate
	 */
	public void setAvgAbsenceRate(final BigDecimal avgAbsenceRate) {
		this.avgAbsenceRate = avgAbsenceRate;
	}

	/**
	 * Gets the avg yes rate.
	 *
	 * @return the avg yes rate
	 */
	public BigDecimal getAvgYesRate() {
		return avgYesRate;
	}

	/**
	 * Sets the avg yes rate.
	 *
	 * @param avgYesRate the new avg yes rate
	 */
	public void setAvgYesRate(final BigDecimal avgYesRate) {
		this.avgYesRate = avgYesRate;
	}

	/**
	 * Gets the avg no rate.
	 *
	 * @return the avg no rate
	 */
	public BigDecimal getAvgNoRate() {
		return avgNoRate;
	}

	/**
	 * Sets the avg no rate.
	 *
	 * @param avgNoRate the new avg no rate
	 */
	public void setAvgNoRate(final BigDecimal avgNoRate) {
		this.avgNoRate = avgNoRate;
	}

	/**
	 * Gets the avg abstain rate.
	 *
	 * @return the avg abstain rate
	 */
	public BigDecimal getAvgAbstainRate() {
		return avgAbstainRate;
	}

	/**
	 * Sets the avg abstain rate.
	 *
	 * @param avgAbstainRate the new avg abstain rate
	 */
	public void setAvgAbstainRate(final BigDecimal avgAbstainRate) {
		this.avgAbstainRate = avgAbstainRate;
	}

	/**
	 * Gets the avg win rate.
	 *
	 * @return the avg win rate
	 */
	public BigDecimal getAvgWinRate() {
		return avgWinRate;
	}

	/**
	 * Sets the avg win rate.
	 *
	 * @param avgWinRate the new avg win rate
	 */
	public void setAvgWinRate(final BigDecimal avgWinRate) {
		this.avgWinRate = avgWinRate;
	}

	/**
	 * Gets the avg rebel rate.
	 *
	 * @return the avg rebel rate
	 */
	public BigDecimal getAvgRebelRate() {
		return avgRebelRate;
	}

	/**
	 * Sets the avg rebel rate.
	 *
	 * @param avgRebelRate the new avg rebel rate
	 */
	public void setAvgRebelRate(final BigDecimal avgRebelRate) {
		this.avgRebelRate = avgRebelRate;
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
	 * Gets the violation types.
	 *
	 * @return the violation types
	 */
	public Integer getViolationTypes() {
		return violationTypes;
	}

	/**
	 * Sets the violation types.
	 *
	 * @param violationTypes the new violation types
	 */
	public void setViolationTypes(final Integer violationTypes) {
		this.violationTypes = violationTypes;
	}

	/**
	 * Gets the absence trend.
	 *
	 * @return the absence trend
	 */
	public BigDecimal getAbsenceTrend() {
		return absenceTrend;
	}

	/**
	 * Sets the absence trend.
	 *
	 * @param absenceTrend the new absence trend
	 */
	public void setAbsenceTrend(final BigDecimal absenceTrend) {
		this.absenceTrend = absenceTrend;
	}

	/**
	 * Gets the win rate trend.
	 *
	 * @return the win rate trend
	 */
	public BigDecimal getWinRateTrend() {
		return winRateTrend;
	}

	/**
	 * Sets the win rate trend.
	 *
	 * @param winRateTrend the new win rate trend
	 */
	public void setWinRateTrend(final BigDecimal winRateTrend) {
		this.winRateTrend = winRateTrend;
	}

	/**
	 * Gets the rebel rate trend.
	 *
	 * @return the rebel rate trend
	 */
	public BigDecimal getRebelRateTrend() {
		return rebelRateTrend;
	}

	/**
	 * Sets the rebel rate trend.
	 *
	 * @param rebelRateTrend the new rebel rate trend
	 */
	public void setRebelRateTrend(final BigDecimal rebelRateTrend) {
		this.rebelRateTrend = rebelRateTrend;
	}

	/**
	 * Gets the moving avg 3 month absence.
	 *
	 * @return the moving avg 3 month absence
	 */
	public BigDecimal getMovingAvg3MonthAbsence() {
		return movingAvg3MonthAbsence;
	}

	/**
	 * Sets the moving avg 3 month absence.
	 *
	 * @param movingAvg3MonthAbsence the new moving avg 3 month absence
	 */
	public void setMovingAvg3MonthAbsence(final BigDecimal movingAvg3MonthAbsence) {
		this.movingAvg3MonthAbsence = movingAvg3MonthAbsence;
	}

	/**
	 * Gets the moving avg 3 month win rate.
	 *
	 * @return the moving avg 3 month win rate
	 */
	public BigDecimal getMovingAvg3MonthWinRate() {
		return movingAvg3MonthWinRate;
	}

	/**
	 * Sets the moving avg 3 month win rate.
	 *
	 * @param movingAvg3MonthWinRate the new moving avg 3 month win rate
	 */
	public void setMovingAvg3MonthWinRate(final BigDecimal movingAvg3MonthWinRate) {
		this.movingAvg3MonthWinRate = movingAvg3MonthWinRate;
	}

	/**
	 * Gets the moving avg 3 month rebel rate.
	 *
	 * @return the moving avg 3 month rebel rate
	 */
	public BigDecimal getMovingAvg3MonthRebelRate() {
		return movingAvg3MonthRebelRate;
	}

	/**
	 * Sets the moving avg 3 month rebel rate.
	 *
	 * @param movingAvg3MonthRebelRate the new moving avg 3 month rebel rate
	 */
	public void setMovingAvg3MonthRebelRate(final BigDecimal movingAvg3MonthRebelRate) {
		this.movingAvg3MonthRebelRate = movingAvg3MonthRebelRate;
	}

	/**
	 * Gets the attendance status.
	 *
	 * @return the attendance status
	 */
	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	/**
	 * Sets the attendance status.
	 *
	 * @param attendanceStatus the new attendance status
	 */
	public void setAttendanceStatus(final String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	/**
	 * Gets the effectiveness status.
	 *
	 * @return the effectiveness status
	 */
	public String getEffectivenessStatus() {
		return effectivenessStatus;
	}

	/**
	 * Sets the effectiveness status.
	 *
	 * @param effectivenessStatus the new effectiveness status
	 */
	public void setEffectivenessStatus(final String effectivenessStatus) {
		this.effectivenessStatus = effectivenessStatus;
	}

	/**
	 * Gets the discipline status.
	 *
	 * @return the discipline status
	 */
	public String getDisciplineStatus() {
		return disciplineStatus;
	}

	/**
	 * Sets the discipline status.
	 *
	 * @param disciplineStatus the new discipline status
	 */
	public void setDisciplineStatus(final String disciplineStatus) {
		this.disciplineStatus = disciplineStatus;
	}

	/**
	 * Gets the behavioral assessment.
	 *
	 * @return the behavioral assessment
	 */
	public String getBehavioralAssessment() {
		return behavioralAssessment;
	}

	/**
	 * Sets the behavioral assessment.
	 *
	 * @param behavioralAssessment the new behavioral assessment
	 */
	public void setBehavioralAssessment(final String behavioralAssessment) {
		this.behavioralAssessment = behavioralAssessment;
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
