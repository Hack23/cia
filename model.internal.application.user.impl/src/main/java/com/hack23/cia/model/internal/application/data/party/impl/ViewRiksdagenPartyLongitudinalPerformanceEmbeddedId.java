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

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class ViewRiksdagenPartyLongitudinalPerformanceEmbeddedId.
 * Composite key for party longitudinal performance tracking (party + election_cycle_id + semester).
 */
@Embeddable
public class ViewRiksdagenPartyLongitudinalPerformanceEmbeddedId implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The party. */
	@Column(name = "party", nullable = false)
	private String party;

	/** The election cycle id. */
	@Column(name = "election_cycle_id", nullable = false)
	private String electionCycleId;

	/** The semester. */
	@Column(name = "semester", nullable = false)
	private String semester;

	/**
	 * Instantiates a new view riksdagen party longitudinal performance embedded id.
	 */
	public ViewRiksdagenPartyLongitudinalPerformanceEmbeddedId() {
		super();
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
	 * Gets the election cycle id.
	 *
	 * @return the election cycle id
	 */
	public String getElectionCycleId() {
		return electionCycleId;
	}

	/**
	 * Sets the election cycle id.
	 *
	 * @param electionCycleId the new election cycle id
	 */
	public void setElectionCycleId(final String electionCycleId) {
		this.electionCycleId = electionCycleId;
	}

	/**
	 * Gets the semester.
	 *
	 * @return the semester
	 */
	public String getSemester() {
		return semester;
	}

	/**
	 * Sets the semester.
	 *
	 * @param semester the new semester
	 */
	public void setSemester(final String semester) {
		this.semester = semester;
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
