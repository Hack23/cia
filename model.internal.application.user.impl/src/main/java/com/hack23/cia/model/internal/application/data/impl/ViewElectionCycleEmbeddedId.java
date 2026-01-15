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
package com.hack23.cia.model.internal.application.data.impl;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hack23.cia.model.common.api.ModelObject;

/**
 * The Class ViewElectionCycleEmbeddedId.
 * 
 * Shared composite key for election cycle views (comparative, anomaly, predictive, network, decision).
 * 
 * @author intelligence-operative
 * @since v1.51 (Election Cycle Analytics)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewElectionCycleEmbeddedId", propOrder = {
    "electionCycleId",
    "semester"
})
@Embeddable
public class ViewElectionCycleEmbeddedId implements ModelObject {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "election_cycle_id", required = true)
	protected String electionCycleId;

	@XmlElement(required = true)
	protected String semester;

	/**
	 * Gets the election cycle id.
	 *
	 * @return the election cycle id
	 */
	@Basic
	@Column(name = "ELECTION_CYCLE_ID", length = 20)
	public String getElectionCycleId() {
		return electionCycleId;
	}

	/**
	 * Sets the election cycle id.
	 *
	 * @param value the new election cycle id
	 */
	public void setElectionCycleId(final String value) {
		this.electionCycleId = value;
	}

	/**
	 * Gets the semester.
	 *
	 * @return the semester
	 */
	@Basic
	@Column(name = "SEMESTER", length = 10)
	public String getSemester() {
		return semester;
	}

	/**
	 * Sets the semester.
	 *
	 * @param value the new semester
	 */
	public void setSemester(final String value) {
		this.semester = value;
	}

	@Override
	public final String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public final boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public final int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
