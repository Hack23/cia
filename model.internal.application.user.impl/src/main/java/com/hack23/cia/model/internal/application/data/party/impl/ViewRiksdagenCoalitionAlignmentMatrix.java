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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hack23.cia.model.common.api.ModelObject;

/**
 * Coalition alignment matrix view.
 */
@Entity
@Table(name = "view_riksdagen_coalition_alignment_matrix")
public final class ViewRiksdagenCoalitionAlignmentMatrix implements ModelObject {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId embeddedId;

	@Column(name = "shared_votes")
	private Long sharedVotes;

	@Column(name = "aligned_votes")
	private Long alignedVotes;

	@Column(name = "opposed_votes")
	private Long opposedVotes;

	@Column(name = "alignment_rate")
	private Double alignmentRate;

	@Column(name = "coalition_likelihood")
	private String coalitionLikelihood;

	@Column(name = "bloc_relationship")
	private String blocRelationship;

	@Column(name = "intelligence_comment")
	private String intelligenceComment;

	@Column(name = "first_year")
	private Integer firstYear;

	@Column(name = "last_year")
	private Integer lastYear;

	@Column(name = "years_observed")
	private Integer yearsObserved;

	public ViewRiksdagenCoalitionAlignmentMatrix() {
		super();
	}

	public ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId getEmbeddedId() {
		return embeddedId;
	}

	public void setEmbeddedId(final ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId embeddedId) {
		this.embeddedId = embeddedId;
	}

	public Long getSharedVotes() {
		return sharedVotes;
	}

	public void setSharedVotes(final Long sharedVotes) {
		this.sharedVotes = sharedVotes;
	}

	public Long getAlignedVotes() {
		return alignedVotes;
	}

	public void setAlignedVotes(final Long alignedVotes) {
		this.alignedVotes = alignedVotes;
	}

	public Long getOpposedVotes() {
		return opposedVotes;
	}

	public void setOpposedVotes(final Long opposedVotes) {
		this.opposedVotes = opposedVotes;
	}

	public Double getAlignmentRate() {
		return alignmentRate;
	}

	public void setAlignmentRate(final Double alignmentRate) {
		this.alignmentRate = alignmentRate;
	}

	public String getCoalitionLikelihood() {
		return coalitionLikelihood;
	}

	public void setCoalitionLikelihood(final String coalitionLikelihood) {
		this.coalitionLikelihood = coalitionLikelihood;
	}

	public String getBlocRelationship() {
		return blocRelationship;
	}

	public void setBlocRelationship(final String blocRelationship) {
		this.blocRelationship = blocRelationship;
	}

	public String getIntelligenceComment() {
		return intelligenceComment;
	}

	public void setIntelligenceComment(final String intelligenceComment) {
		this.intelligenceComment = intelligenceComment;
	}

	public Integer getFirstYear() {
		return firstYear;
	}

	public void setFirstYear(final Integer firstYear) {
		this.firstYear = firstYear;
	}

	public Integer getLastYear() {
		return lastYear;
	}

	public void setLastYear(final Integer lastYear) {
		this.lastYear = lastYear;
	}

	public Integer getYearsObserved() {
		return yearsObserved;
	}

	public void setYearsObserved(final Integer yearsObserved) {
		this.yearsObserved = yearsObserved;
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * Compare to.
	 *
	 * @param o the o
	 * @return the int
	 */
	public int compareTo(final ModelObject o) {
		if (this == o) {
			return 0;
		}
		if (embeddedId == null) {
			return -1;
		}
		if (o == null || !(o instanceof ViewRiksdagenCoalitionAlignmentMatrix)) {
			return -1;
		}
		final ViewRiksdagenCoalitionAlignmentMatrix other = (ViewRiksdagenCoalitionAlignmentMatrix) o;
		if (other.embeddedId == null) {
			return 1;
		}
		return embeddedId.compareTo(other.embeddedId);
	}
}
