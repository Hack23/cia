/*
 * Copyright 2010-2026 James Pether Sörling
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
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

/**
 * The Class ViewRiksdagenPoliticianCareerTrajectory.
 * Database view for politician career trajectory analysis across election cycles.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_politician_career_trajectory")
public class ViewRiksdagenPoliticianCareerTrajectory implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ViewRiksdagenPoliticianCareerTrajectoryEmbeddedId embeddedId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "party")
	private String party;

	@Column(name = "career_cycle_number")
	private Integer careerCycleNumber;

	@Column(name = "total_cycles")
	private Long totalCycles;

	@Column(name = "career_start_year")
	private Integer careerStartYear;

	@Column(name = "career_end_year")
	private Integer careerEndYear;

	@Column(name = "ballot_count")
	private Long ballotCount;

	@Column(name = "attendance_rate")
	private BigDecimal attendanceRate;

	@Column(name = "win_rate")
	private BigDecimal winRate;

	@Column(name = "leadership_roles")
	private Long leadershipRoles;

	@Column(name = "documents_authored")
	private Long documentsAuthored;

	@Column(name = "avg_career_attendance")
	private BigDecimal avgCareerAttendance;

	@Column(name = "performance_vs_baseline")
	private BigDecimal performanceVsBaseline;

	@Column(name = "career_stage")
	private String careerStage;

	@Column(name = "performance_trend")
	private String performanceTrend;

	@Column(name = "career_pattern")
	private String careerPattern;

	/**
	 * Instantiates a new view riksdagen politician career trajectory.
	 */
	public ViewRiksdagenPoliticianCareerTrajectory() {
		super();
	}

	/**
	 * Gets the embedded id.
	 *
	 * @return the embedded id
	 */
	public ViewRiksdagenPoliticianCareerTrajectoryEmbeddedId getEmbeddedId() {
		return embeddedId;
	}

	/**
	 * Sets the embedded id.
	 *
	 * @param embeddedId the new embedded id
	 */
	public void setEmbeddedId(final ViewRiksdagenPoliticianCareerTrajectoryEmbeddedId embeddedId) {
		this.embeddedId = embeddedId;
	}

	/**
	 * Gets the person id.
	 *
	 * @return the person id
	 */
	public String getPersonId() {
		return embeddedId != null ? embeddedId.getPersonId() : null;
	}

	/**
	 * Gets the election year.
	 *
	 * @return the election year
	 */
	public Integer getElectionYear() {
		return embeddedId != null ? embeddedId.getElectionYear() : null;
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
	 * Gets the career cycle number.
	 *
	 * @return the career cycle number
	 */
	public Integer getCareerCycleNumber() {
		return careerCycleNumber;
	}

	/**
	 * Sets the career cycle number.
	 *
	 * @param careerCycleNumber the new career cycle number
	 */
	public void setCareerCycleNumber(final Integer careerCycleNumber) {
		this.careerCycleNumber = careerCycleNumber;
	}

	/**
	 * Gets the total cycles.
	 *
	 * @return the total cycles
	 */
	public Long getTotalCycles() {
		return totalCycles;
	}

	/**
	 * Sets the total cycles.
	 *
	 * @param totalCycles the new total cycles
	 */
	public void setTotalCycles(final Long totalCycles) {
		this.totalCycles = totalCycles;
	}

	/**
	 * Gets the career start year.
	 *
	 * @return the career start year
	 */
	public Integer getCareerStartYear() {
		return careerStartYear;
	}

	/**
	 * Sets the career start year.
	 *
	 * @param careerStartYear the new career start year
	 */
	public void setCareerStartYear(final Integer careerStartYear) {
		this.careerStartYear = careerStartYear;
	}

	/**
	 * Gets the career end year.
	 *
	 * @return the career end year
	 */
	public Integer getCareerEndYear() {
		return careerEndYear;
	}

	/**
	 * Sets the career end year.
	 *
	 * @param careerEndYear the new career end year
	 */
	public void setCareerEndYear(final Integer careerEndYear) {
		this.careerEndYear = careerEndYear;
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
	 * Gets the attendance rate.
	 *
	 * @return the attendance rate
	 */
	public BigDecimal getAttendanceRate() {
		return attendanceRate;
	}

	/**
	 * Sets the attendance rate.
	 *
	 * @param attendanceRate the new attendance rate
	 */
	public void setAttendanceRate(final BigDecimal attendanceRate) {
		this.attendanceRate = attendanceRate;
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
	 * Gets the leadership roles.
	 *
	 * @return the leadership roles
	 */
	public Long getLeadershipRoles() {
		return leadershipRoles;
	}

	/**
	 * Sets the leadership roles.
	 *
	 * @param leadershipRoles the new leadership roles
	 */
	public void setLeadershipRoles(final Long leadershipRoles) {
		this.leadershipRoles = leadershipRoles;
	}

	/**
	 * Gets the documents authored.
	 *
	 * @return the documents authored
	 */
	public Long getDocumentsAuthored() {
		return documentsAuthored;
	}

	/**
	 * Sets the documents authored.
	 *
	 * @param documentsAuthored the new documents authored
	 */
	public void setDocumentsAuthored(final Long documentsAuthored) {
		this.documentsAuthored = documentsAuthored;
	}

	/**
	 * Gets the avg career attendance.
	 *
	 * @return the avg career attendance
	 */
	public BigDecimal getAvgCareerAttendance() {
		return avgCareerAttendance;
	}

	/**
	 * Sets the avg career attendance.
	 *
	 * @param avgCareerAttendance the new avg career attendance
	 */
	public void setAvgCareerAttendance(final BigDecimal avgCareerAttendance) {
		this.avgCareerAttendance = avgCareerAttendance;
	}

	/**
	 * Gets the performance vs baseline.
	 *
	 * @return the performance vs baseline
	 */
	public BigDecimal getPerformanceVsBaseline() {
		return performanceVsBaseline;
	}

	/**
	 * Sets the performance vs baseline.
	 *
	 * @param performanceVsBaseline the new performance vs baseline
	 */
	public void setPerformanceVsBaseline(final BigDecimal performanceVsBaseline) {
		this.performanceVsBaseline = performanceVsBaseline;
	}

	/**
	 * Gets the career stage.
	 *
	 * @return the career stage
	 */
	public String getCareerStage() {
		return careerStage;
	}

	/**
	 * Sets the career stage.
	 *
	 * @param careerStage the new career stage
	 */
	public void setCareerStage(final String careerStage) {
		this.careerStage = careerStage;
	}

	/**
	 * Gets the performance trend.
	 *
	 * @return the performance trend
	 */
	public String getPerformanceTrend() {
		return performanceTrend;
	}

	/**
	 * Sets the performance trend.
	 *
	 * @param performanceTrend the new performance trend
	 */
	public void setPerformanceTrend(final String performanceTrend) {
		this.performanceTrend = performanceTrend;
	}

	/**
	 * Gets the career pattern.
	 *
	 * @return the career pattern
	 */
	public String getCareerPattern() {
		return careerPattern;
	}

	/**
	 * Sets the career pattern.
	 *
	 * @param careerPattern the new career pattern
	 */
	public void setCareerPattern(final String careerPattern) {
		this.careerPattern = careerPattern;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final ViewRiksdagenPoliticianCareerTrajectory that = (ViewRiksdagenPoliticianCareerTrajectory) obj;
		return Objects.equals(embeddedId, that.embeddedId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(embeddedId);
	}

	@Override
	public String toString() {
		return "ViewRiksdagenPoliticianCareerTrajectory{" +
				"personId='" + getPersonId() + '\'' +
				", electionYear=" + getElectionYear() +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", party='" + party + '\'' +
				", careerStage='" + careerStage + '\'' +
				", performanceTrend='" + performanceTrend + '\'' +
				", careerPattern='" + careerPattern + '\'' +
				", attendanceRate=" + attendanceRate +
				'}';
	}
}
