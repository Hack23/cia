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
 * The Class ViewRiksdagenPartyTransitionHistory.
 * Database view tracking politicians who switched parties while serving in Riksdagen.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_party_transition_history")
public class ViewRiksdagenPartyTransitionHistory implements Serializable {

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

	@Column(name = "transition_type")
	private String transitionType;

	@Column(name = "transition_year")
	private Integer transitionYear;

	@Temporal(TemporalType.DATE)
	@Column(name = "next_election")
	private Date nextElection;

	@Column(name = "months_until_next_election")
	private Integer monthsUntilNextElection;

	@Temporal(TemporalType.DATE)
	@Column(name = "previous_election")
	private Date previousElection;

	@Column(name = "months_since_last_election")
	private Integer monthsSinceLastElection;

	/**
	 * Instantiates a new view riksdagen party transition history.
	 */
	public ViewRiksdagenPartyTransitionHistory() {
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
	 * Gets the transition type.
	 *
	 * @return the transition type
	 */
	public String getTransitionType() {
		return transitionType;
	}

	/**
	 * Sets the transition type.
	 *
	 * @param transitionType the new transition type
	 */
	public void setTransitionType(final String transitionType) {
		this.transitionType = transitionType;
	}

	/**
	 * Gets the transition year.
	 *
	 * @return the transition year
	 */
	public Integer getTransitionYear() {
		return transitionYear;
	}

	/**
	 * Sets the transition year.
	 *
	 * @param transitionYear the new transition year
	 */
	public void setTransitionYear(final Integer transitionYear) {
		this.transitionYear = transitionYear;
	}

	/**
	 * Gets the next election.
	 *
	 * @return the next election
	 */
	public Date getNextElection() {
		return nextElection;
	}

	/**
	 * Sets the next election.
	 *
	 * @param nextElection the new next election
	 */
	public void setNextElection(final Date nextElection) {
		this.nextElection = nextElection;
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
	 * Gets the previous election.
	 *
	 * @return the previous election
	 */
	public Date getPreviousElection() {
		return previousElection;
	}

	/**
	 * Sets the previous election.
	 *
	 * @param previousElection the new previous election
	 */
	public void setPreviousElection(final Date previousElection) {
		this.previousElection = previousElection;
	}

	/**
	 * Gets the months since last election.
	 *
	 * @return the months since last election
	 */
	public Integer getMonthsSinceLastElection() {
		return monthsSinceLastElection;
	}

	/**
	 * Sets the months since last election.
	 *
	 * @param monthsSinceLastElection the new months since last election
	 */
	public void setMonthsSinceLastElection(final Integer monthsSinceLastElection) {
		this.monthsSinceLastElection = monthsSinceLastElection;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final ViewRiksdagenPartyTransitionHistory that = (ViewRiksdagenPartyTransitionHistory) obj;
		return Objects.equals(personId, that.personId) &&
				Objects.equals(transitionDate, that.transitionDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(personId, transitionDate);
	}

	@Override
	public String toString() {
		return "ViewRiksdagenPartyTransitionHistory{" +
				"personId='" + personId + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", previousParty='" + previousParty + '\'' +
				", newParty='" + newParty + '\'' +
				", transitionDate=" + transitionDate +
				", transitionType='" + transitionType + '\'' +
				", transitionYear=" + transitionYear +
				", monthsUntilNextElection=" + monthsUntilNextElection +
				", monthsSinceLastElection=" + monthsSinceLastElection +
				'}';
	}
}
