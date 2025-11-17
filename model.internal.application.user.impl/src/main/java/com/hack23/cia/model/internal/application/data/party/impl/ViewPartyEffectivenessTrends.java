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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * JPA entity for view_party_effectiveness_trends database view.
 * 
 * Intelligence Purpose: Monitor party-level performance metrics over time,
 * track win rates, productivity, and collaboration patterns.
 * 
 * Created by: Liquibase v1.30 (OSINT Performance Tracking)
 * Risk Rules Supported: Pa-01 through Pa-10 (All party-level rules)
 * 
 * Quarterly aggregation with trend analysis and performance classification
 */
@Entity(name = "ViewPartyEffectivenessTrends")
@Table(name = "view_party_effectiveness_trends")
public class ViewPartyEffectivenessTrends implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The party. */
	@Id
	@Column(name = "party", nullable = false, length = 50)
	private String party;

	/** The period start. */
	@Id
	@Column(name = "period_start", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date periodStart;

	/** The period end. */
	@Column(name = "period_end")
	@Temporal(TemporalType.DATE)
	private Date periodEnd;

	/** The year. */
	@Column(name = "year")
	private Integer year;

	/** The quarter. */
	@Column(name = "quarter")
	private Integer quarter;

	/** The active members. */
	@Column(name = "active_members")
	private Integer activeMembers;

	/** The total ballots. */
	@Column(name = "total_ballots")
	private Long totalBallots;

	/** The total votes. */
	@Column(name = "total_votes")
	private Long totalVotes;

	/** The avg absence rate. */
	@Column(name = "avg_absence_rate", precision = 5, scale = 2)
	private BigDecimal avgAbsenceRate;

	/** The avg win rate. */
	@Column(name = "avg_win_rate", precision = 5, scale = 2)
	private BigDecimal avgWinRate;

	/** The avg rebel rate. */
	@Column(name = "avg_rebel_rate", precision = 5, scale = 2)
	private BigDecimal avgRebelRate;

	/** The avg yes rate. */
	@Column(name = "avg_yes_rate", precision = 5, scale = 2)
	private BigDecimal avgYesRate;

	/** The documents produced. */
	@Column(name = "documents_produced")
	private Long documentsProduced;

	/** The motions count. */
	@Column(name = "motions_count")
	private Long motionsCount;

	/** The active document authors. */
	@Column(name = "active_document_authors")
	private Integer activeDocumentAuthors;

	/** The violation count. */
	@Column(name = "violation_count")
	private Long violationCount;

	/** The members with violations. */
	@Column(name = "members_with_violations")
	private Integer membersWithViolations;

	/** The win rate trend. */
	@Column(name = "win_rate_trend", precision = 5, scale = 2)
	private BigDecimal winRateTrend;

	/** The absence trend. */
	@Column(name = "absence_trend", precision = 5, scale = 2)
	private BigDecimal absenceTrend;

	/** The member change. */
	@Column(name = "member_change")
	private Integer memberChange;

	/** The ma 4quarter win rate. */
	@Column(name = "ma_4quarter_win_rate", precision = 5, scale = 2)
	private BigDecimal movingAvg4QuarterWinRate;

	/** The ma 4quarter absence. */
	@Column(name = "ma_4quarter_absence", precision = 5, scale = 2)
	private BigDecimal movingAvg4QuarterAbsence;

	/** The docs per member. */
	@Column(name = "docs_per_member", precision = 10, scale = 2)
	private BigDecimal docsPerMember;

	/** The votes per member. */
	@Column(name = "votes_per_member", precision = 10, scale = 2)
	private BigDecimal votesPerMember;

	/** The violations per member. */
	@Column(name = "violations_per_member", precision = 10, scale = 2)
	private BigDecimal violationsPerMember;

	/** The performance level. */
	@Column(name = "performance_level", length = 50)
	private String performanceLevel;

	/** The productivity level. */
	@Column(name = "productivity_level", length = 50)
	private String productivityLevel;

	/** The effectiveness assessment. */
	@Column(name = "effectiveness_assessment", length = 255)
	private String effectivenessAssessment;

	/**
	 * Instantiates a new view party effectiveness trends.
	 */
	public ViewPartyEffectivenessTrends() {
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
	 * Gets the period start.
	 *
	 * @return the period start
	 */
	public Date getPeriodStart() {
		return periodStart;
	}

	/**
	 * Sets the period start.
	 *
	 * @param periodStart the new period start
	 */
	public void setPeriodStart(final Date periodStart) {
		this.periodStart = periodStart;
	}

	/**
	 * Gets the period end.
	 *
	 * @return the period end
	 */
	public Date getPeriodEnd() {
		return periodEnd;
	}

	/**
	 * Sets the period end.
	 *
	 * @param periodEnd the new period end
	 */
	public void setPeriodEnd(final Date periodEnd) {
		this.periodEnd = periodEnd;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(final Integer year) {
		this.year = year;
	}

	/**
	 * Gets the quarter.
	 *
	 * @return the quarter
	 */
	public Integer getQuarter() {
		return quarter;
	}

	/**
	 * Sets the quarter.
	 *
	 * @param quarter the new quarter
	 */
	public void setQuarter(final Integer quarter) {
		this.quarter = quarter;
	}

	/**
	 * Gets the active members.
	 *
	 * @return the active members
	 */
	public Integer getActiveMembers() {
		return activeMembers;
	}

	/**
	 * Sets the active members.
	 *
	 * @param activeMembers the new active members
	 */
	public void setActiveMembers(final Integer activeMembers) {
		this.activeMembers = activeMembers;
	}

	/**
	 * Gets the total ballots.
	 *
	 * @return the total ballots
	 */
	public Long getTotalBallots() {
		return totalBallots;
	}

	/**
	 * Sets the total ballots.
	 *
	 * @param totalBallots the new total ballots
	 */
	public void setTotalBallots(final Long totalBallots) {
		this.totalBallots = totalBallots;
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
	 * Gets the avg absence rate.
	 *
	 * @return the avg absence rate
	 */
	public BigDecimal getAvgAbsenceRate() {
		return avgAbsenceRate;
	}

	/**
	 * Sets the avg absence rate.
	 *
	 * @param avgAbsenceRate the new avg absence rate
	 */
	public void setAvgAbsenceRate(final BigDecimal avgAbsenceRate) {
		this.avgAbsenceRate = avgAbsenceRate;
	}

	/**
	 * Gets the avg win rate.
	 *
	 * @return the avg win rate
	 */
	public BigDecimal getAvgWinRate() {
		return avgWinRate;
	}

	/**
	 * Sets the avg win rate.
	 *
	 * @param avgWinRate the new avg win rate
	 */
	public void setAvgWinRate(final BigDecimal avgWinRate) {
		this.avgWinRate = avgWinRate;
	}

	/**
	 * Gets the avg rebel rate.
	 *
	 * @return the avg rebel rate
	 */
	public BigDecimal getAvgRebelRate() {
		return avgRebelRate;
	}

	/**
	 * Sets the avg rebel rate.
	 *
	 * @param avgRebelRate the new avg rebel rate
	 */
	public void setAvgRebelRate(final BigDecimal avgRebelRate) {
		this.avgRebelRate = avgRebelRate;
	}

	/**
	 * Gets the avg yes rate.
	 *
	 * @return the avg yes rate
	 */
	public BigDecimal getAvgYesRate() {
		return avgYesRate;
	}

	/**
	 * Sets the avg yes rate.
	 *
	 * @param avgYesRate the new avg yes rate
	 */
	public void setAvgYesRate(final BigDecimal avgYesRate) {
		this.avgYesRate = avgYesRate;
	}

	/**
	 * Gets the documents produced.
	 *
	 * @return the documents produced
	 */
	public Long getDocumentsProduced() {
		return documentsProduced;
	}

	/**
	 * Sets the documents produced.
	 *
	 * @param documentsProduced the new documents produced
	 */
	public void setDocumentsProduced(final Long documentsProduced) {
		this.documentsProduced = documentsProduced;
	}

	/**
	 * Gets the motions count.
	 *
	 * @return the motions count
	 */
	public Long getMotionsCount() {
		return motionsCount;
	}

	/**
	 * Sets the motions count.
	 *
	 * @param motionsCount the new motions count
	 */
	public void setMotionsCount(final Long motionsCount) {
		this.motionsCount = motionsCount;
	}

	/**
	 * Gets the active document authors.
	 *
	 * @return the active document authors
	 */
	public Integer getActiveDocumentAuthors() {
		return activeDocumentAuthors;
	}

	/**
	 * Sets the active document authors.
	 *
	 * @param activeDocumentAuthors the new active document authors
	 */
	public void setActiveDocumentAuthors(final Integer activeDocumentAuthors) {
		this.activeDocumentAuthors = activeDocumentAuthors;
	}

	/**
	 * Gets the violation count.
	 *
	 * @return the violation count
	 */
	public Long getViolationCount() {
		return violationCount;
	}

	/**
	 * Sets the violation count.
	 *
	 * @param violationCount the new violation count
	 */
	public void setViolationCount(final Long violationCount) {
		this.violationCount = violationCount;
	}

	/**
	 * Gets the members with violations.
	 *
	 * @return the members with violations
	 */
	public Integer getMembersWithViolations() {
		return membersWithViolations;
	}

	/**
	 * Sets the members with violations.
	 *
	 * @param membersWithViolations the new members with violations
	 */
	public void setMembersWithViolations(final Integer membersWithViolations) {
		this.membersWithViolations = membersWithViolations;
	}

	/**
	 * Gets the win rate trend.
	 *
	 * @return the win rate trend
	 */
	public BigDecimal getWinRateTrend() {
		return winRateTrend;
	}

	/**
	 * Sets the win rate trend.
	 *
	 * @param winRateTrend the new win rate trend
	 */
	public void setWinRateTrend(final BigDecimal winRateTrend) {
		this.winRateTrend = winRateTrend;
	}

	/**
	 * Gets the absence trend.
	 *
	 * @return the absence trend
	 */
	public BigDecimal getAbsenceTrend() {
		return absenceTrend;
	}

	/**
	 * Sets the absence trend.
	 *
	 * @param absenceTrend the new absence trend
	 */
	public void setAbsenceTrend(final BigDecimal absenceTrend) {
		this.absenceTrend = absenceTrend;
	}

	/**
	 * Gets the member change.
	 *
	 * @return the member change
	 */
	public Integer getMemberChange() {
		return memberChange;
	}

	/**
	 * Sets the member change.
	 *
	 * @param memberChange the new member change
	 */
	public void setMemberChange(final Integer memberChange) {
		this.memberChange = memberChange;
	}

	/**
	 * Gets the moving avg 4 quarter win rate.
	 *
	 * @return the moving avg 4 quarter win rate
	 */
	public BigDecimal getMovingAvg4QuarterWinRate() {
		return movingAvg4QuarterWinRate;
	}

	/**
	 * Sets the moving avg 4 quarter win rate.
	 *
	 * @param movingAvg4QuarterWinRate the new moving avg 4 quarter win rate
	 */
	public void setMovingAvg4QuarterWinRate(final BigDecimal movingAvg4QuarterWinRate) {
		this.movingAvg4QuarterWinRate = movingAvg4QuarterWinRate;
	}

	/**
	 * Gets the moving avg 4 quarter absence.
	 *
	 * @return the moving avg 4 quarter absence
	 */
	public BigDecimal getMovingAvg4QuarterAbsence() {
		return movingAvg4QuarterAbsence;
	}

	/**
	 * Sets the moving avg 4 quarter absence.
	 *
	 * @param movingAvg4QuarterAbsence the new moving avg 4 quarter absence
	 */
	public void setMovingAvg4QuarterAbsence(final BigDecimal movingAvg4QuarterAbsence) {
		this.movingAvg4QuarterAbsence = movingAvg4QuarterAbsence;
	}

	/**
	 * Gets the docs per member.
	 *
	 * @return the docs per member
	 */
	public BigDecimal getDocsPerMember() {
		return docsPerMember;
	}

	/**
	 * Sets the docs per member.
	 *
	 * @param docsPerMember the new docs per member
	 */
	public void setDocsPerMember(final BigDecimal docsPerMember) {
		this.docsPerMember = docsPerMember;
	}

	/**
	 * Gets the votes per member.
	 *
	 * @return the votes per member
	 */
	public BigDecimal getVotesPerMember() {
		return votesPerMember;
	}

	/**
	 * Sets the votes per member.
	 *
	 * @param votesPerMember the new votes per member
	 */
	public void setVotesPerMember(final BigDecimal votesPerMember) {
		this.votesPerMember = votesPerMember;
	}

	/**
	 * Gets the violations per member.
	 *
	 * @return the violations per member
	 */
	public BigDecimal getViolationsPerMember() {
		return violationsPerMember;
	}

	/**
	 * Sets the violations per member.
	 *
	 * @param violationsPerMember the new violations per member
	 */
	public void setViolationsPerMember(final BigDecimal violationsPerMember) {
		this.violationsPerMember = violationsPerMember;
	}

	/**
	 * Gets the performance level.
	 *
	 * @return the performance level
	 */
	public String getPerformanceLevel() {
		return performanceLevel;
	}

	/**
	 * Sets the performance level.
	 *
	 * @param performanceLevel the new performance level
	 */
	public void setPerformanceLevel(final String performanceLevel) {
		this.performanceLevel = performanceLevel;
	}

	/**
	 * Gets the productivity level.
	 *
	 * @return the productivity level
	 */
	public String getProductivityLevel() {
		return productivityLevel;
	}

	/**
	 * Sets the productivity level.
	 *
	 * @param productivityLevel the new productivity level
	 */
	public void setProductivityLevel(final String productivityLevel) {
		this.productivityLevel = productivityLevel;
	}

	/**
	 * Gets the effectiveness assessment.
	 *
	 * @return the effectiveness assessment
	 */
	public String getEffectivenessAssessment() {
		return effectivenessAssessment;
	}

	/**
	 * Sets the effectiveness assessment.
	 *
	 * @param effectivenessAssessment the new effectiveness assessment
	 */
	public void setEffectivenessAssessment(final String effectivenessAssessment) {
		this.effectivenessAssessment = effectivenessAssessment;
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
