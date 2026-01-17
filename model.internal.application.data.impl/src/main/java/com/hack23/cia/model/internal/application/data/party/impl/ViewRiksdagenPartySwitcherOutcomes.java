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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Immutable;

/**
 * The Class ViewRiksdagenPartySwitcherOutcomes.
 * Database view measuring post-transition career success for party switchers.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_party_switcher_outcomes")
public class ViewRiksdagenPartySwitcherOutcomes implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ViewRiksdagenPartySwitcherOutcomesEmbeddedId embeddedId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "previous_party")
	private String previousParty;

	@Column(name = "new_party")
	private String newParty;

	@Temporal(TemporalType.DATE)
	@Column(name = "next_election")
	private Date nextElection;

	@Column(name = "months_until_next_election")
	private Integer monthsUntilNextElection;

	@Column(name = "total_subsequent_assignments")
	private Long totalSubsequentAssignments;

	@Column(name = "total_days_served_after_switch")
	private Long totalDaysServedAfterSwitch;

	@Column(name = "continued_as_active_mp")
	private Integer continuedAsActiveMp;

	@Column(name = "served_in_next_election")
	private Integer servedInNextElection;

	@Column(name = "attained_leadership_post_switch")
	private Integer attainedLeadershipPostSwitch;

	@Column(name = "post_switch_roles")
	private String postSwitchRoles;

	@Column(name = "current_status")
	private String currentStatus;

	/**
	 * Instantiates a new view riksdagen party switcher outcomes.
	 */
	public ViewRiksdagenPartySwitcherOutcomes() {
		super();
	}

	/**
	 * Gets the embedded id.
	 *
	 * @return the embedded id
	 */
	public ViewRiksdagenPartySwitcherOutcomesEmbeddedId getEmbeddedId() {
		return embeddedId;
	}

	/**
	 * Sets the embedded id.
	 *
	 * @param embeddedId the new embedded id
	 */
	public void setEmbeddedId(final ViewRiksdagenPartySwitcherOutcomesEmbeddedId embeddedId) {
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
	 * Sets the person id.
	 *
	 * @param personId the new person id
	 */
	public void setPersonId(final String personId) {
		if (this.embeddedId == null) {
			this.embeddedId = new ViewRiksdagenPartySwitcherOutcomesEmbeddedId();
		}
		this.embeddedId.setPersonId(personId);
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
		return embeddedId != null ? embeddedId.getTransitionDate() : null;
	}

	/**
	 * Sets the transition date.
	 *
	 * @param transitionDate the new transition date
	 */
	public void setTransitionDate(final Date transitionDate) {
		if (this.embeddedId == null) {
			this.embeddedId = new ViewRiksdagenPartySwitcherOutcomesEmbeddedId();
		}
		this.embeddedId.setTransitionDate(transitionDate);
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
	 * Gets the total subsequent assignments.
	 *
	 * @return the total subsequent assignments
	 */
	public Long getTotalSubsequentAssignments() {
		return totalSubsequentAssignments;
	}

	/**
	 * Sets the total subsequent assignments.
	 *
	 * @param totalSubsequentAssignments the new total subsequent assignments
	 */
	public void setTotalSubsequentAssignments(final Long totalSubsequentAssignments) {
		this.totalSubsequentAssignments = totalSubsequentAssignments;
	}

	/**
	 * Gets the total days served after switch.
	 *
	 * @return the total days served after switch
	 */
	public Long getTotalDaysServedAfterSwitch() {
		return totalDaysServedAfterSwitch;
	}

	/**
	 * Sets the total days served after switch.
	 *
	 * @param totalDaysServedAfterSwitch the new total days served after switch
	 */
	public void setTotalDaysServedAfterSwitch(final Long totalDaysServedAfterSwitch) {
		this.totalDaysServedAfterSwitch = totalDaysServedAfterSwitch;
	}

	/**
	 * Gets the continued as active mp.
	 *
	 * @return the continued as active mp
	 */
	public Integer getContinuedAsActiveMp() {
		return continuedAsActiveMp;
	}

	/**
	 * Sets the continued as active mp.
	 *
	 * @param continuedAsActiveMp the new continued as active mp
	 */
	public void setContinuedAsActiveMp(final Integer continuedAsActiveMp) {
		this.continuedAsActiveMp = continuedAsActiveMp;
	}

	/**
	 * Gets the served in next election.
	 *
	 * @return the served in next election
	 */
	public Integer getServedInNextElection() {
		return servedInNextElection;
	}

	/**
	 * Sets the served in next election.
	 *
	 * @param servedInNextElection the new served in next election
	 */
	public void setServedInNextElection(final Integer servedInNextElection) {
		this.servedInNextElection = servedInNextElection;
	}

	/**
	 * Gets the attained leadership post switch.
	 *
	 * @return the attained leadership post switch
	 */
	public Integer getAttainedLeadershipPostSwitch() {
		return attainedLeadershipPostSwitch;
	}

	/**
	 * Sets the attained leadership post switch.
	 *
	 * @param attainedLeadershipPostSwitch the new attained leadership post switch
	 */
	public void setAttainedLeadershipPostSwitch(final Integer attainedLeadershipPostSwitch) {
		this.attainedLeadershipPostSwitch = attainedLeadershipPostSwitch;
	}

	/**
	 * Gets the post switch roles.
	 *
	 * @return the post switch roles
	 */
	public String getPostSwitchRoles() {
		return postSwitchRoles;
	}

	/**
	 * Sets the post switch roles.
	 *
	 * @param postSwitchRoles the new post switch roles
	 */
	public void setPostSwitchRoles(final String postSwitchRoles) {
		this.postSwitchRoles = postSwitchRoles;
	}

	/**
	 * Gets the current status.
	 *
	 * @return the current status
	 */
	public String getCurrentStatus() {
		return currentStatus;
	}

	/**
	 * Sets the current status.
	 *
	 * @param currentStatus the new current status
	 */
	public void setCurrentStatus(final String currentStatus) {
		this.currentStatus = currentStatus;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final ViewRiksdagenPartySwitcherOutcomes that = (ViewRiksdagenPartySwitcherOutcomes) obj;
		return Objects.equals(getPersonId(), that.getPersonId()) &&
				Objects.equals(getTransitionDate(), that.getTransitionDate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPersonId(), getTransitionDate());
	}

	@Override
	public String toString() {
		return "ViewRiksdagenPartySwitcherOutcomes{" +
				"personId='" + getPersonId() + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", previousParty='" + previousParty + '\'' +
				", newParty='" + newParty + '\'' +
				", transitionDate=" + getTransitionDate() +
				", totalSubsequentAssignments=" + totalSubsequentAssignments +
				", continuedAsActiveMp=" + continuedAsActiveMp +
				", servedInNextElection=" + servedInNextElection +
				", attainedLeadershipPostSwitch=" + attainedLeadershipPostSwitch +
				", currentStatus='" + currentStatus + '\'' +
				'}';
	}
}
