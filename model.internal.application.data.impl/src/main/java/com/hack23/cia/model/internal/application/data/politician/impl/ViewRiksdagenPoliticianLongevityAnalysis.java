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
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Immutable;

/**
 * The Class ViewRiksdagenPoliticianLongevityAnalysis.
 * Database view for politician career longevity and activity pattern analysis.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_politician_longevity_analysis")
public class ViewRiksdagenPoliticianLongevityAnalysis implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "person_id", nullable = false)
	private String personId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "party")
	private String party;

	@Column(name = "status")
	private String status;

	@Column(name = "born_year")
	private Integer bornYear;

	@Column(name = "career_start_date")
	@Temporal(TemporalType.DATE)
	private Date careerStartDate;

	@Column(name = "career_end_date")
	@Temporal(TemporalType.DATE)
	private Date careerEndDate;

	@Column(name = "first_activity_year")
	private Integer firstActivityYear;

	@Column(name = "last_activity_year")
	private Integer lastActivityYear;

	@Column(name = "total_career_days")
	private Integer totalCareerDays;

	@Column(name = "total_career_years")
	private BigDecimal totalCareerYears;

	@Column(name = "age_at_career_start")
	private Integer ageAtCareerStart;

	@Column(name = "age_at_career_end")
	private Integer ageAtCareerEnd;

	@Column(name = "election_cycles_active")
	private Long electionCyclesActive;

	@Column(name = "total_votes_cast")
	private Long totalVotesCast;

	@Column(name = "total_assignments")
	private Long totalAssignments;

	@Column(name = "is_currently_active")
	private Boolean isCurrentlyActive;

	@Column(name = "avg_votes_per_year")
	private BigDecimal avgVotesPerYear;

	@Column(name = "avg_assignments_per_year")
	private BigDecimal avgAssignmentsPerYear;

	@Column(name = "career_continuity_score")
	private BigDecimal careerContinuityScore;

	@Column(name = "longevity_category")
	private String longevityCategory;

	@Column(name = "activity_level")
	private String activityLevel;

	@Column(name = "continuity_pattern")
	private String continuityPattern;

	@Column(name = "career_life_stage")
	private String careerLifeStage;

	@Column(name = "retention_risk")
	private String retentionRisk;

	/**
	 * Instantiates a new view riksdagen politician longevity analysis.
	 */
	public ViewRiksdagenPoliticianLongevityAnalysis() {
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
	 * Gets the born year.
	 *
	 * @return the born year
	 */
	public Integer getBornYear() {
		return bornYear;
	}

	/**
	 * Sets the born year.
	 *
	 * @param bornYear the new born year
	 */
	public void setBornYear(final Integer bornYear) {
		this.bornYear = bornYear;
	}

	/**
	 * Gets the career start date.
	 *
	 * @return the career start date
	 */
	public Date getCareerStartDate() {
		return careerStartDate;
	}

	/**
	 * Sets the career start date.
	 *
	 * @param careerStartDate the new career start date
	 */
	public void setCareerStartDate(final Date careerStartDate) {
		this.careerStartDate = careerStartDate;
	}

	/**
	 * Gets the career end date.
	 *
	 * @return the career end date
	 */
	public Date getCareerEndDate() {
		return careerEndDate;
	}

	/**
	 * Sets the career end date.
	 *
	 * @param careerEndDate the new career end date
	 */
	public void setCareerEndDate(final Date careerEndDate) {
		this.careerEndDate = careerEndDate;
	}

	/**
	 * Gets the first activity year.
	 *
	 * @return the first activity year
	 */
	public Integer getFirstActivityYear() {
		return firstActivityYear;
	}

	/**
	 * Sets the first activity year.
	 *
	 * @param firstActivityYear the new first activity year
	 */
	public void setFirstActivityYear(final Integer firstActivityYear) {
		this.firstActivityYear = firstActivityYear;
	}

	/**
	 * Gets the last activity year.
	 *
	 * @return the last activity year
	 */
	public Integer getLastActivityYear() {
		return lastActivityYear;
	}

	/**
	 * Sets the last activity year.
	 *
	 * @param lastActivityYear the new last activity year
	 */
	public void setLastActivityYear(final Integer lastActivityYear) {
		this.lastActivityYear = lastActivityYear;
	}

	/**
	 * Gets the total career days.
	 *
	 * @return the total career days
	 */
	public Integer getTotalCareerDays() {
		return totalCareerDays;
	}

	/**
	 * Sets the total career days.
	 *
	 * @param totalCareerDays the new total career days
	 */
	public void setTotalCareerDays(final Integer totalCareerDays) {
		this.totalCareerDays = totalCareerDays;
	}

	/**
	 * Gets the total career years.
	 *
	 * @return the total career years
	 */
	public BigDecimal getTotalCareerYears() {
		return totalCareerYears;
	}

	/**
	 * Sets the total career years.
	 *
	 * @param totalCareerYears the new total career years
	 */
	public void setTotalCareerYears(final BigDecimal totalCareerYears) {
		this.totalCareerYears = totalCareerYears;
	}

	/**
	 * Gets the age at career start.
	 *
	 * @return the age at career start
	 */
	public Integer getAgeAtCareerStart() {
		return ageAtCareerStart;
	}

	/**
	 * Sets the age at career start.
	 *
	 * @param ageAtCareerStart the new age at career start
	 */
	public void setAgeAtCareerStart(final Integer ageAtCareerStart) {
		this.ageAtCareerStart = ageAtCareerStart;
	}

	/**
	 * Gets the age at career end.
	 *
	 * @return the age at career end
	 */
	public Integer getAgeAtCareerEnd() {
		return ageAtCareerEnd;
	}

	/**
	 * Sets the age at career end.
	 *
	 * @param ageAtCareerEnd the new age at career end
	 */
	public void setAgeAtCareerEnd(final Integer ageAtCareerEnd) {
		this.ageAtCareerEnd = ageAtCareerEnd;
	}

	/**
	 * Gets the election cycles active.
	 *
	 * @return the election cycles active
	 */
	public Long getElectionCyclesActive() {
		return electionCyclesActive;
	}

	/**
	 * Sets the election cycles active.
	 *
	 * @param electionCyclesActive the new election cycles active
	 */
	public void setElectionCyclesActive(final Long electionCyclesActive) {
		this.electionCyclesActive = electionCyclesActive;
	}

	/**
	 * Gets the total votes cast.
	 *
	 * @return the total votes cast
	 */
	public Long getTotalVotesCast() {
		return totalVotesCast;
	}

	/**
	 * Sets the total votes cast.
	 *
	 * @param totalVotesCast the new total votes cast
	 */
	public void setTotalVotesCast(final Long totalVotesCast) {
		this.totalVotesCast = totalVotesCast;
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
	public void setTotalAssignments(final Long totalAssignments) {
		this.totalAssignments = totalAssignments;
	}

	/**
	 * Gets the checks if is currently active.
	 *
	 * @return the checks if is currently active
	 */
	public Boolean getIsCurrentlyActive() {
		return isCurrentlyActive;
	}

	/**
	 * Sets the checks if is currently active.
	 *
	 * @param isCurrentlyActive the new checks if is currently active
	 */
	public void setIsCurrentlyActive(final Boolean isCurrentlyActive) {
		this.isCurrentlyActive = isCurrentlyActive;
	}

	/**
	 * Gets the avg votes per year.
	 *
	 * @return the avg votes per year
	 */
	public BigDecimal getAvgVotesPerYear() {
		return avgVotesPerYear;
	}

	/**
	 * Sets the avg votes per year.
	 *
	 * @param avgVotesPerYear the new avg votes per year
	 */
	public void setAvgVotesPerYear(final BigDecimal avgVotesPerYear) {
		this.avgVotesPerYear = avgVotesPerYear;
	}

	/**
	 * Gets the avg assignments per year.
	 *
	 * @return the avg assignments per year
	 */
	public BigDecimal getAvgAssignmentsPerYear() {
		return avgAssignmentsPerYear;
	}

	/**
	 * Sets the avg assignments per year.
	 *
	 * @param avgAssignmentsPerYear the new avg assignments per year
	 */
	public void setAvgAssignmentsPerYear(final BigDecimal avgAssignmentsPerYear) {
		this.avgAssignmentsPerYear = avgAssignmentsPerYear;
	}

	/**
	 * Gets the career continuity score.
	 *
	 * @return the career continuity score
	 */
	public BigDecimal getCareerContinuityScore() {
		return careerContinuityScore;
	}

	/**
	 * Sets the career continuity score.
	 *
	 * @param careerContinuityScore the new career continuity score
	 */
	public void setCareerContinuityScore(final BigDecimal careerContinuityScore) {
		this.careerContinuityScore = careerContinuityScore;
	}

	/**
	 * Gets the longevity category.
	 *
	 * @return the longevity category
	 */
	public String getLongevityCategory() {
		return longevityCategory;
	}

	/**
	 * Sets the longevity category.
	 *
	 * @param longevityCategory the new longevity category
	 */
	public void setLongevityCategory(final String longevityCategory) {
		this.longevityCategory = longevityCategory;
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
	 * Gets the continuity pattern.
	 *
	 * @return the continuity pattern
	 */
	public String getContinuityPattern() {
		return continuityPattern;
	}

	/**
	 * Sets the continuity pattern.
	 *
	 * @param continuityPattern the new continuity pattern
	 */
	public void setContinuityPattern(final String continuityPattern) {
		this.continuityPattern = continuityPattern;
	}

	/**
	 * Gets the career life stage.
	 *
	 * @return the career life stage
	 */
	public String getCareerLifeStage() {
		return careerLifeStage;
	}

	/**
	 * Sets the career life stage.
	 *
	 * @param careerLifeStage the new career life stage
	 */
	public void setCareerLifeStage(final String careerLifeStage) {
		this.careerLifeStage = careerLifeStage;
	}

	/**
	 * Gets the retention risk.
	 *
	 * @return the retention risk
	 */
	public String getRetentionRisk() {
		return retentionRisk;
	}

	/**
	 * Sets the retention risk.
	 *
	 * @param retentionRisk the new retention risk
	 */
	public void setRetentionRisk(final String retentionRisk) {
		this.retentionRisk = retentionRisk;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final ViewRiksdagenPoliticianLongevityAnalysis that = (ViewRiksdagenPoliticianLongevityAnalysis) obj;
		return Objects.equals(personId, that.personId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(personId);
	}

	@Override
	public String toString() {
		return "ViewRiksdagenPoliticianLongevityAnalysis{" +
				"personId='" + personId + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", party='" + party + '\'' +
				", longevityCategory='" + longevityCategory + '\'' +
				", activityLevel='" + activityLevel + '\'' +
				", retentionRisk='" + retentionRisk + '\'' +
				", totalCareerYears=" + totalCareerYears +
				'}';
	}
}
