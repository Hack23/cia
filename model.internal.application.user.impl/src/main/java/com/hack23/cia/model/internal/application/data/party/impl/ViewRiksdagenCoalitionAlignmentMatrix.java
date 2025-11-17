package com.hack23.cia.model.internal.application.data.party.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * JPA entity for view_riksdagen_coalition_alignment_matrix database view.
 * 
 * Intelligence Purpose: Maps coalition alignment patterns between parties through
 * voting behavior analysis. Identifies coalition likelihood, bloc relationships,
 * and potential government formation scenarios through empirical voting data.
 * 
 * Created by: Liquibase v1.29 (Intelligence Operations)
 * Risk Rules Supported: Pa-03, Pa-08 (Coalition formation and stability assessment)
 */
@Entity(name = "ViewRiksdagenCoalitionAlignmentMatrix")
@Table(name = "view_riksdagen_coalition_alignment_matrix")
public class ViewRiksdagenCoalitionAlignmentMatrix implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "party_1", nullable = false, length = 50)
	private String party1;

	@Id
	@Column(name = "party_2", nullable = false, length = 50)
	private String party2;

	@Column(name = "shared_votes")
	private Long sharedVotes;

	@Column(name = "aligned_votes")
	private Long alignedVotes;

	@Column(name = "opposed_votes")
	private Long opposedVotes;

	@Column(name = "alignment_rate", precision = 5, scale = 2)
	private BigDecimal alignmentRate;

	@Column(name = "coalition_likelihood", length = 50)
	private String coalitionLikelihood;

	@Column(name = "bloc_relationship", length = 100)
	private String blocRelationship;

	@Column(name = "intelligence_comment", length = 500)
	private String intelligenceComment;

	@Column(name = "years_observed")
	private Integer yearsObserved;

	@Column(name = "trend_stability", length = 50)
	private String trendStability;

	@Column(name = "coalition_strength_score", precision = 5, scale = 2)
	private BigDecimal coalitionStrengthScore;

	/**
	 * Default constructor.
	 */
	public ViewRiksdagenCoalitionAlignmentMatrix() {
		super();
	}

	// Getters and Setters

	public String getParty1() {
		return party1;
	}

	public void setParty1(String party1) {
		this.party1 = party1;
	}

	public String getParty2() {
		return party2;
	}

	public void setParty2(String party2) {
		this.party2 = party2;
	}

	public Long getSharedVotes() {
		return sharedVotes;
	}

	public void setSharedVotes(Long sharedVotes) {
		this.sharedVotes = sharedVotes;
	}

	public Long getAlignedVotes() {
		return alignedVotes;
	}

	public void setAlignedVotes(Long alignedVotes) {
		this.alignedVotes = alignedVotes;
	}

	public Long getOpposedVotes() {
		return opposedVotes;
	}

	public void setOpposedVotes(Long opposedVotes) {
		this.opposedVotes = opposedVotes;
	}

	public BigDecimal getAlignmentRate() {
		return alignmentRate;
	}

	public void setAlignmentRate(BigDecimal alignmentRate) {
		this.alignmentRate = alignmentRate;
	}

	public String getCoalitionLikelihood() {
		return coalitionLikelihood;
	}

	public void setCoalitionLikelihood(String coalitionLikelihood) {
		this.coalitionLikelihood = coalitionLikelihood;
	}

	public String getBlocRelationship() {
		return blocRelationship;
	}

	public void setBlocRelationship(String blocRelationship) {
		this.blocRelationship = blocRelationship;
	}

	public String getIntelligenceComment() {
		return intelligenceComment;
	}

	public void setIntelligenceComment(String intelligenceComment) {
		this.intelligenceComment = intelligenceComment;
	}

	public Integer getYearsObserved() {
		return yearsObserved;
	}

	public void setYearsObserved(Integer yearsObserved) {
		this.yearsObserved = yearsObserved;
	}

	public String getTrendStability() {
		return trendStability;
	}

	public void setTrendStability(String trendStability) {
		this.trendStability = trendStability;
	}

	public BigDecimal getCoalitionStrengthScore() {
		return coalitionStrengthScore;
	}

	public void setCoalitionStrengthScore(BigDecimal coalitionStrengthScore) {
		this.coalitionStrengthScore = coalitionStrengthScore;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final ViewRiksdagenCoalitionAlignmentMatrix other = (ViewRiksdagenCoalitionAlignmentMatrix) obj;
		return new EqualsBuilder()
				.append(party1, other.party1)
				.append(party2, other.party2)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(party1)
				.append(party2)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("party1", party1)
				.append("party2", party2)
				.append("alignmentRate", alignmentRate)
				.append("coalitionLikelihood", coalitionLikelihood)
				.append("blocRelationship", blocRelationship)
				.toString();
	}
}
