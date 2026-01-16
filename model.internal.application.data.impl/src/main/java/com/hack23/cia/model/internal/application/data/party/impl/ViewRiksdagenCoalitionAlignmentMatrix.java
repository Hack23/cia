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
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

/**
 * The Class ViewRiksdagenCoalitionAlignmentMatrix.
 * Database view for coalition alignment analysis.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_coalition_alignment_matrix")
public class ViewRiksdagenCoalitionAlignmentMatrix implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId embeddedId;

	@Column(name = "alignment_rate")
	private BigDecimal alignmentRate;

	@Column(name = "total_votes")
	private Long totalVotes;

	@Column(name = "aligned_votes")
	private Long alignedVotes;

	/**
	 * Instantiates a new view riksdagen coalition alignment matrix.
	 */
	public ViewRiksdagenCoalitionAlignmentMatrix() {
		super();
	}

	/**
	 * Gets the embedded id.
	 *
	 * @return the embedded id
	 */
	public ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId getEmbeddedId() {
		return embeddedId;
	}

	/**
	 * Sets the embedded id.
	 *
	 * @param embeddedId the new embedded id
	 */
	public void setEmbeddedId(final ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId embeddedId) {
		this.embeddedId = embeddedId;
	}

	/**
	 * Gets the party1.
	 *
	 * @return the party1
	 */
	public String getParty1() {
		return embeddedId != null ? embeddedId.getParty1() : null;
	}

	/**
	 * Gets the party2.
	 *
	 * @return the party2
	 */
	public String getParty2() {
		return embeddedId != null ? embeddedId.getParty2() : null;
	}

	/**
	 * Gets the alignment rate.
	 *
	 * @return the alignment rate
	 */
	public BigDecimal getAlignmentRate() {
		return alignmentRate;
	}

	/**
	 * Sets the alignment rate.
	 *
	 * @param alignmentRate the new alignment rate
	 */
	public void setAlignmentRate(final BigDecimal alignmentRate) {
		this.alignmentRate = alignmentRate;
	}

	/**
	 * Gets the total votes.
	 *
	 * @return the total votes
	 */
	public Long getTotalVotes() {
		return totalVotes;
	}

	/**
	 * Sets the total votes.
	 *
	 * @param totalVotes the new total votes
	 */
	public void setTotalVotes(final Long totalVotes) {
		this.totalVotes = totalVotes;
	}

	/**
	 * Gets the aligned votes.
	 *
	 * @return the aligned votes
	 */
	public Long getAlignedVotes() {
		return alignedVotes;
	}

	/**
	 * Sets the aligned votes.
	 *
	 * @param alignedVotes the new aligned votes
	 */
	public void setAlignedVotes(final Long alignedVotes) {
		this.alignedVotes = alignedVotes;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final ViewRiksdagenCoalitionAlignmentMatrix that = (ViewRiksdagenCoalitionAlignmentMatrix) obj;
		return Objects.equals(embeddedId, that.embeddedId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(embeddedId);
	}

	@Override
	public String toString() {
		return "ViewRiksdagenCoalitionAlignmentMatrix{" +
				"party1='" + getParty1() + '\'' +
				", party2='" + getParty2() + '\'' +
				", alignmentRate=" + alignmentRate +
				", totalVotes=" + totalVotes +
				", alignedVotes=" + alignedVotes +
				'}';
	}
}
