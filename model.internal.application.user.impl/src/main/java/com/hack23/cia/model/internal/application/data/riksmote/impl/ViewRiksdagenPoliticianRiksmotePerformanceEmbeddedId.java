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
package com.hack23.cia.model.internal.application.data.riksmote.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class ViewRiksdagenPoliticianRiksmotePerformanceEmbeddedId.
 * 
 * Composite key for politician riksmöte performance (riksmote + person_id).
 */
@Embeddable
public class ViewRiksdagenPoliticianRiksmotePerformanceEmbeddedId implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The riksmote. */
	@Column(name = "riksmote", nullable = false)
	private String riksmote;

	/** The person id. */
	@Column(name = "person_id", nullable = false)
	private String personId;

	/**
	 * Instantiates a new view riksdagen politician riksmote performance embedded id.
	 */
	public ViewRiksdagenPoliticianRiksmotePerformanceEmbeddedId() {
		super();
	}

	/**
	 * Gets the riksmote.
	 *
	 * @return the riksmote
	 */
	public String getRiksmote() {
		return riksmote;
	}

	/**
	 * Sets the riksmote.
	 *
	 * @param riksmote the new riksmote
	 */
	public void setRiksmote(final String riksmote) {
		this.riksmote = riksmote;
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
