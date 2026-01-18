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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class ViewRiksdagenElectionProximityTrendsEmbeddedId.
 * Composite key for election proximity trends (person_id + election_date + activity_quarter).
 */
@Embeddable
public class ViewRiksdagenElectionProximityTrendsEmbeddedId implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The person id. */
	@Column(name = "person_id", nullable = false)
	private String personId;

	/** The election date. */
	@Column(name = "election_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date electionDate;

	/** The activity quarter. */
	@Column(name = "activity_quarter", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date activityQuarter;

	/**
	 * Instantiates a new view riksdagen election proximity trends embedded id.
	 */
	public ViewRiksdagenElectionProximityTrendsEmbeddedId() {
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
	 * Gets the election date.
	 *
	 * @return the election date
	 */
	public Date getElectionDate() {
		return electionDate;
	}

	/**
	 * Sets the election date.
	 *
	 * @param electionDate the new election date
	 */
	public void setElectionDate(final Date electionDate) {
		this.electionDate = electionDate;
	}

	/**
	 * Gets the activity quarter.
	 *
	 * @return the activity quarter
	 */
	public Date getActivityQuarter() {
		return activityQuarter;
	}

	/**
	 * Sets the activity quarter.
	 *
	 * @param activityQuarter the new activity quarter
	 */
	public void setActivityQuarter(final Date activityQuarter) {
		this.activityQuarter = activityQuarter;
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
