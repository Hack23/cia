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
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId.
 * Composite key for coalition alignment matrix (party1 + party2).
 */
@Embeddable
public class ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId implements Serializable, Comparable<ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The party1. */
	@Column(name = "party1", nullable = false)
	private String party1;

	/** The party2. */
	@Column(name = "party2", nullable = false)
	private String party2;

	/**
	 * Instantiates a new view riksdagen coalition alignment matrix embedded id.
	 */
	public ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId() {
		super();
	}

	/**
	 * Instantiates a new view riksdagen coalition alignment matrix embedded id.
	 *
	 * @param party1 the party1
	 * @param party2 the party2
	 */
	public ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId(final String party1, final String party2) {
		super();
		this.party1 = party1;
		this.party2 = party2;
	}

	/**
	 * Gets the party1.
	 *
	 * @return the party1
	 */
	public String getParty1() {
		return party1;
	}

	/**
	 * Sets the party1.
	 *
	 * @param party1 the new party1
	 */
	public void setParty1(final String party1) {
		this.party1 = party1;
	}

	/**
	 * Gets the party2.
	 *
	 * @return the party2
	 */
	public String getParty2() {
		return party2;
	}

	/**
	 * Sets the party2.
	 *
	 * @param party2 the new party2
	 */
	public void setParty2(final String party2) {
		this.party2 = party2;
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

	@Override
	public int compareTo(final ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId other) {
		if (other == null) {
			return 1;
		}
		// Null-safe comparison for party1
		if (this.party1 == null && other.party1 == null) {
			// Both null, compare party2
			return Objects.compare(this.party2, other.party2, String::compareTo);
		}
		if (this.party1 == null) {
			return -1;
		}
		if (other.party1 == null) {
			return 1;
		}
		
		int partyCompare = this.party1.compareTo(other.party1);
		if (partyCompare != 0) {
			return partyCompare;
		}
		
		// Null-safe comparison for party2
		return Objects.compare(this.party2, other.party2, String::compareTo);
	}
}
