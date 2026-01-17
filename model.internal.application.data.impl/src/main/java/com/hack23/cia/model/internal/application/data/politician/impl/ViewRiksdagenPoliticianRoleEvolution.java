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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Immutable;

/**
 * The Class ViewRiksdagenPoliticianRoleEvolution.
 * Database view for politician role evolution and career progression tracking.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_politician_role_evolution")
public class ViewRiksdagenPoliticianRoleEvolution implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ViewRiksdagenPoliticianRoleEvolutionEmbeddedId embeddedId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "party")
	private String party;

	@Column(name = "role_start")
	@Temporal(TemporalType.DATE)
	private Date roleStart;

	@Column(name = "role_end")
	@Temporal(TemporalType.DATE)
	private Date roleEnd;

	@Column(name = "role_start_year")
	private Integer roleStartYear;

	@Column(name = "role_end_year")
	private Integer roleEndYear;

	@Column(name = "role_instances")
	private Long roleInstances;

	@Column(name = "total_days_in_role")
	private Integer totalDaysInRole;

	@Column(name = "is_current_role")
	private Boolean isCurrentRole;

	@Column(name = "role_sequence")
	private Long roleSequence;

	@Column(name = "peak_role_weight")
	private Integer peakRoleWeight;

	@Column(name = "career_first_year")
	private Integer careerFirstYear;

	@Column(name = "career_last_year")
	private Integer careerLastYear;

	@Column(name = "years_in_role")
	private Integer yearsInRole;

	@Column(name = "progression_pattern")
	private String progressionPattern;

	@Column(name = "career_level")
	private String careerLevel;

	@Column(name = "advancement_velocity")
	private BigDecimal advancementVelocity;

	/**
	 * Instantiates a new view riksdagen politician role evolution.
	 */
	public ViewRiksdagenPoliticianRoleEvolution() {
		super();
	}

	/**
	 * Gets the embedded id.
	 *
	 * @return the embedded id
	 */
	public ViewRiksdagenPoliticianRoleEvolutionEmbeddedId getEmbeddedId() {
		return embeddedId;
	}

	/**
	 * Sets the embedded id.
	 *
	 * @param embeddedId the new embedded id
	 */
	public void setEmbeddedId(final ViewRiksdagenPoliticianRoleEvolutionEmbeddedId embeddedId) {
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
	 * Gets the role code.
	 *
	 * @return the role code
	 */
	public String getRoleCode() {
		return embeddedId != null ? embeddedId.getRoleCode() : null;
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
	 * Gets the role start.
	 *
	 * @return the role start
	 */
	public Date getRoleStart() {
		return roleStart;
	}

	/**
	 * Sets the role start.
	 *
	 * @param roleStart the new role start
	 */
	public void setRoleStart(final Date roleStart) {
		this.roleStart = roleStart;
	}

	/**
	 * Gets the role end.
	 *
	 * @return the role end
	 */
	public Date getRoleEnd() {
		return roleEnd;
	}

	/**
	 * Sets the role end.
	 *
	 * @param roleEnd the new role end
	 */
	public void setRoleEnd(final Date roleEnd) {
		this.roleEnd = roleEnd;
	}

	/**
	 * Gets the role start year.
	 *
	 * @return the role start year
	 */
	public Integer getRoleStartYear() {
		return roleStartYear;
	}

	/**
	 * Sets the role start year.
	 *
	 * @param roleStartYear the new role start year
	 */
	public void setRoleStartYear(final Integer roleStartYear) {
		this.roleStartYear = roleStartYear;
	}

	/**
	 * Gets the role end year.
	 *
	 * @return the role end year
	 */
	public Integer getRoleEndYear() {
		return roleEndYear;
	}

	/**
	 * Sets the role end year.
	 *
	 * @param roleEndYear the new role end year
	 */
	public void setRoleEndYear(final Integer roleEndYear) {
		this.roleEndYear = roleEndYear;
	}

	/**
	 * Gets the role instances.
	 *
	 * @return the role instances
	 */
	public Long getRoleInstances() {
		return roleInstances;
	}

	/**
	 * Sets the role instances.
	 *
	 * @param roleInstances the new role instances
	 */
	public void setRoleInstances(final Long roleInstances) {
		this.roleInstances = roleInstances;
	}

	/**
	 * Gets the total days in role.
	 *
	 * @return the total days in role
	 */
	public Integer getTotalDaysInRole() {
		return totalDaysInRole;
	}

	/**
	 * Sets the total days in role.
	 *
	 * @param totalDaysInRole the new total days in role
	 */
	public void setTotalDaysInRole(final Integer totalDaysInRole) {
		this.totalDaysInRole = totalDaysInRole;
	}

	/**
	 * Gets the checks if is current role.
	 *
	 * @return the checks if is current role
	 */
	public Boolean getIsCurrentRole() {
		return isCurrentRole;
	}

	/**
	 * Sets the checks if is current role.
	 *
	 * @param isCurrentRole the new checks if is current role
	 */
	public void setIsCurrentRole(final Boolean isCurrentRole) {
		this.isCurrentRole = isCurrentRole;
	}

	/**
	 * Gets the role sequence.
	 *
	 * @return the role sequence
	 */
	public Long getRoleSequence() {
		return roleSequence;
	}

	/**
	 * Sets the role sequence.
	 *
	 * @param roleSequence the new role sequence
	 */
	public void setRoleSequence(final Long roleSequence) {
		this.roleSequence = roleSequence;
	}

	/**
	 * Gets the peak role weight.
	 *
	 * @return the peak role weight
	 */
	public Integer getPeakRoleWeight() {
		return peakRoleWeight;
	}

	/**
	 * Sets the peak role weight.
	 *
	 * @param peakRoleWeight the new peak role weight
	 */
	public void setPeakRoleWeight(final Integer peakRoleWeight) {
		this.peakRoleWeight = peakRoleWeight;
	}

	/**
	 * Gets the career first year.
	 *
	 * @return the career first year
	 */
	public Integer getCareerFirstYear() {
		return careerFirstYear;
	}

	/**
	 * Sets the career first year.
	 *
	 * @param careerFirstYear the new career first year
	 */
	public void setCareerFirstYear(final Integer careerFirstYear) {
		this.careerFirstYear = careerFirstYear;
	}

	/**
	 * Gets the career last year.
	 *
	 * @return the career last year
	 */
	public Integer getCareerLastYear() {
		return careerLastYear;
	}

	/**
	 * Sets the career last year.
	 *
	 * @param careerLastYear the new career last year
	 */
	public void setCareerLastYear(final Integer careerLastYear) {
		this.careerLastYear = careerLastYear;
	}

	/**
	 * Gets the years in role.
	 *
	 * @return the years in role
	 */
	public Integer getYearsInRole() {
		return yearsInRole;
	}

	/**
	 * Sets the years in role.
	 *
	 * @param yearsInRole the new years in role
	 */
	public void setYearsInRole(final Integer yearsInRole) {
		this.yearsInRole = yearsInRole;
	}

	/**
	 * Gets the progression pattern.
	 *
	 * @return the progression pattern
	 */
	public String getProgressionPattern() {
		return progressionPattern;
	}

	/**
	 * Sets the progression pattern.
	 *
	 * @param progressionPattern the new progression pattern
	 */
	public void setProgressionPattern(final String progressionPattern) {
		this.progressionPattern = progressionPattern;
	}

	/**
	 * Gets the career level.
	 *
	 * @return the career level
	 */
	public String getCareerLevel() {
		return careerLevel;
	}

	/**
	 * Sets the career level.
	 *
	 * @param careerLevel the new career level
	 */
	public void setCareerLevel(final String careerLevel) {
		this.careerLevel = careerLevel;
	}

	/**
	 * Gets the advancement velocity.
	 *
	 * @return the advancement velocity
	 */
	public BigDecimal getAdvancementVelocity() {
		return advancementVelocity;
	}

	/**
	 * Sets the advancement velocity.
	 *
	 * @param advancementVelocity the new advancement velocity
	 */
	public void setAdvancementVelocity(final BigDecimal advancementVelocity) {
		this.advancementVelocity = advancementVelocity;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final ViewRiksdagenPoliticianRoleEvolution that = (ViewRiksdagenPoliticianRoleEvolution) obj;
		return Objects.equals(embeddedId, that.embeddedId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(embeddedId);
	}

	@Override
	public String toString() {
		return "ViewRiksdagenPoliticianRoleEvolution{" +
				"personId='" + getPersonId() + '\'' +
				", roleCode='" + getRoleCode() + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", party='" + party + '\'' +
				", progressionPattern='" + progressionPattern + '\'' +
				", careerLevel='" + careerLevel + '\'' +
				'}';
	}
}
