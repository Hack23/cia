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
package com.hack23.cia.model.internal.application.data.party.impl;

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
 * The Class ViewRiksdagenPartyDefectorAnalysis.
 * Database view analyzing behavioral patterns of politicians who defected from their party.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_party_defector_analysis")
public class ViewRiksdagenPartyDefectorAnalysis implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "person_id", nullable = false)
	private String personId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "previous_party")
	private String previousParty;

	@Column(name = "new_party")
	private String newParty;

	@Temporal(TemporalType.DATE)
	@Column(name = "transition_date")
	private Date transitionDate;

	@Column(name = "months_until_next_election")
	private Integer monthsUntilNextElection;

	@Column(name = "pre_transition_attendance")
	private BigDecimal preTransitionAttendance;

	@Column(name = "post_transition_attendance")
	private BigDecimal postTransitionAttendance;

	@Column(name = "attendance_change")
	private BigDecimal attendanceChange;

	@Column(name = "docs_before")
	private Long docsBefore;

	@Column(name = "docs_after")
	private Long docsAfter;

	@Column(name = "defection_timing")
	private String defectionTiming;

	/**
	 * Instantiates a new view riksdagen party defector analysis.
	 */
	public ViewRiksdagenPartyDefectorAnalysis() {
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
	 * Gets the previous party.
	 *
	 * @return the previous party
	 */
	public String getPreviousParty() {
		return previousParty;
	}

	/**
	 * Sets the previous party.
	 *
	 * @param previousParty the new previous party
	 */
	public void setPreviousParty(final String previousParty) {
		this.previousParty = previousParty;
	}

	/**
	 * Gets the new party.
	 *
	 * @return the new party
	 */
	public String getNewParty() {
		return newParty;
	}

	/**
	 * Sets the new party.
	 *
	 * @param newParty the new new party
	 */
	public void setNewParty(final String newParty) {
		this.newParty = newParty;
	}

	/**
	 * Gets the transition date.
	 *
	 * @return the transition date
	 */
	public Date getTransitionDate() {
		return transitionDate;
	}

	/**
	 * Sets the transition date.
	 *
	 * @param transitionDate the new transition date
	 */
	public void setTransitionDate(final Date transitionDate) {
		this.transitionDate = transitionDate;
	}

	/**
	 * Gets the months until next election.
	 *
	 * @return the months until next election
	 */
	public Integer getMonthsUntilNextElection() {
		return monthsUntilNextElection;
	}

	/**
	 * Sets the months until next election.
	 *
	 * @param monthsUntilNextElection the new months until next election
	 */
	public void setMonthsUntilNextElection(final Integer monthsUntilNextElection) {
		this.monthsUntilNextElection = monthsUntilNextElection;
	}

	/**
	 * Gets the pre transition attendance.
	 *
	 * @return the pre transition attendance
	 */
	public BigDecimal getPreTransitionAttendance() {
		return preTransitionAttendance;
	}

	/**
	 * Sets the pre transition attendance.
	 *
	 * @param preTransitionAttendance the new pre transition attendance
	 */
	public void setPreTransitionAttendance(final BigDecimal preTransitionAttendance) {
		this.preTransitionAttendance = preTransitionAttendance;
	}

	/**
	 * Gets the post transition attendance.
	 *
	 * @return the post transition attendance
	 */
	public BigDecimal getPostTransitionAttendance() {
		return postTransitionAttendance;
	}

	/**
	 * Sets the post transition attendance.
	 *
	 * @param postTransitionAttendance the new post transition attendance
	 */
	public void setPostTransitionAttendance(final BigDecimal postTransitionAttendance) {
		this.postTransitionAttendance = postTransitionAttendance;
	}

	/**
	 * Gets the attendance change.
	 *
	 * @return the attendance change
	 */
	public BigDecimal getAttendanceChange() {
		return attendanceChange;
	}

	/**
	 * Sets the attendance change.
	 *
	 * @param attendanceChange the new attendance change
	 */
	public void setAttendanceChange(final BigDecimal attendanceChange) {
		this.attendanceChange = attendanceChange;
	}

	/**
	 * Gets the docs before.
	 *
	 * @return the docs before
	 */
	public Long getDocsBefore() {
		return docsBefore;
	}

	/**
	 * Sets the docs before.
	 *
	 * @param docsBefore the new docs before
	 */
	public void setDocsBefore(final Long docsBefore) {
		this.docsBefore = docsBefore;
	}

	/**
	 * Gets the docs after.
	 *
	 * @return the docs after
	 */
	public Long getDocsAfter() {
		return docsAfter;
	}

	/**
	 * Sets the docs after.
	 *
	 * @param docsAfter the new docs after
	 */
	public void setDocsAfter(final Long docsAfter) {
		this.docsAfter = docsAfter;
	}

	/**
	 * Gets the defection timing.
	 *
	 * @return the defection timing
	 */
	public String getDefectionTiming() {
		return defectionTiming;
	}

	/**
	 * Sets the defection timing.
	 *
	 * @param defectionTiming the new defection timing
	 */
	public void setDefectionTiming(final String defectionTiming) {
		this.defectionTiming = defectionTiming;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final ViewRiksdagenPartyDefectorAnalysis that = (ViewRiksdagenPartyDefectorAnalysis) obj;
		return Objects.equals(personId, that.personId) &&
				Objects.equals(transitionDate, that.transitionDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(personId, transitionDate);
	}

	@Override
	public String toString() {
		return "ViewRiksdagenPartyDefectorAnalysis{" +
				"personId='" + personId + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", previousParty='" + previousParty + '\'' +
				", newParty='" + newParty + '\'' +
				", transitionDate=" + transitionDate +
				", monthsUntilNextElection=" + monthsUntilNextElection +
				", preTransitionAttendance=" + preTransitionAttendance +
				", postTransitionAttendance=" + postTransitionAttendance +
				", attendanceChange=" + attendanceChange +
				", defectionTiming='" + defectionTiming + '\'' +
				'}';
	}
}
