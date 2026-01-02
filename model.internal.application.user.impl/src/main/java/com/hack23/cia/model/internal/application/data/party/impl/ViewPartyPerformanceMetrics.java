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
 * JPA entity for view_party_performance_metrics database view.
 * 
 * Intelligence Purpose: Comprehensive party performance indicators combining
 * member counts, voting records, document production, violations, and government representation.
 * Calculates an overall performance score based on multiple factors.
 * 
 * Created by: Database schema v1.0+
 * Risk Rules Supported: Pa-01 through Pa-10 (All party-level effectiveness rules)
 * 
 * Provides snapshot metrics for party benchmarking and comparative analysis
 */
@Entity(name = "ViewPartyPerformanceMetrics")
@Table(name = "view_party_performance_metrics")
public class ViewPartyPerformanceMetrics implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The party. */
	@Id
	@Column(name = "party", nullable = false, length = 50)
	private String party;

	/** The party name. */
	@Column(name = "party_name", length = 255)
	private String partyName;

	/** The registration date. */
	@Column(name = "registration_date")
	@Temporal(TemporalType.DATE)
	private Date registrationDate;

	/** The active members. */
	@Column(name = "active_members")
	private Long activeMembers;

	/** The inactive members. */
	@Column(name = "inactive_members")
	private Long inactiveMembers;

	/** The total violations. */
	@Column(name = "total_violations")
	private Long totalViolations;

	/** The members with violations. */
	@Column(name = "members_with_violations")
	private Long membersWithViolations;

	/** The violation rate percentage. */
	@Column(name = "violation_rate_percentage", precision = 10, scale = 2)
	private BigDecimal violationRatePercentage;

	/** The latest member violation. */
	@Column(name = "latest_member_violation")
	@Temporal(TemporalType.DATE)
	private Date latestMemberViolation;

	/** The total votes last year. */
	@Column(name = "total_votes_last_year")
	private Long totalVotesLastYear;

	/** The avg absence rate. */
	@Column(name = "avg_absence_rate", precision = 10, scale = 2)
	private BigDecimal avgAbsenceRate;

	/** The avg win rate. */
	@Column(name = "avg_win_rate", precision = 10, scale = 2)
	private BigDecimal avgWinRate;

	/** The avg rebel rate. */
	@Column(name = "avg_rebel_rate", precision = 10, scale = 2)
	private BigDecimal avgRebelRate;

	/** The avg participation rate. */
	@Column(name = "avg_participation_rate", precision = 10, scale = 2)
	private BigDecimal avgParticipationRate;

	/** The documents last year. */
	@Column(name = "documents_last_year")
	private Long documentsLastYear;

	/** The motions last year. */
	@Column(name = "motions_last_year")
	private Long motionsLastYear;

	/** The propositions last year. */
	@Column(name = "propositions_last_year")
	private Long propositionsLastYear;

	/** The docs per member. */
	@Column(name = "docs_per_member", precision = 10, scale = 2)
	private BigDecimal docsPerMember;

	/** The current ministers. */
	@Column(name = "current_ministers")
	private Long currentMinisters;

	/** The current committee chairs. */
	@Column(name = "current_committee_chairs")
	private Long currentCommitteeChairs;

	/** The performance score. */
	@Column(name = "performance_score", precision = 10, scale = 2)
	private BigDecimal performanceScore;

	/** The performance level. */
	@Column(name = "performance_level", length = 50)
	private String performanceLevel;

	/** The strengths. */
	@Column(name = "strengths", length = 500)
	private String strengths;

	/** The weaknesses. */
	@Column(name = "weaknesses", length = 500)
	private String weaknesses;

	/**
	 * Instantiates a new view party performance metrics.
	 */
	public ViewPartyPerformanceMetrics() {
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
	 * Gets the party name.
	 *
	 * @return the party name
	 */
	public String getPartyName() {
		return partyName;
	}

	/**
	 * Sets the party name.
	 *
	 * @param partyName the new party name
	 */
	public void setPartyName(final String partyName) {
		this.partyName = partyName;
	}

	/**
	 * Gets the registration date.
	 *
	 * @return the registration date
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * Sets the registration date.
	 *
	 * @param registrationDate the new registration date
	 */
	public void setRegistrationDate(final Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * Gets the active members.
	 *
	 * @return the active members
	 */
	public Long getActiveMembers() {
		return activeMembers;
	}

	/**
	 * Sets the active members.
	 *
	 * @param activeMembers the new active members
	 */
	public void setActiveMembers(final Long activeMembers) {
		this.activeMembers = activeMembers;
	}

	/**
	 * Gets the inactive members.
	 *
	 * @return the inactive members
	 */
	public Long getInactiveMembers() {
		return inactiveMembers;
	}

	/**
	 * Sets the inactive members.
	 *
	 * @param inactiveMembers the new inactive members
	 */
	public void setInactiveMembers(final Long inactiveMembers) {
		this.inactiveMembers = inactiveMembers;
	}

	/**
	 * Gets the total violations.
	 *
	 * @return the total violations
	 */
	public Long getTotalViolations() {
		return totalViolations;
	}

	/**
	 * Sets the total violations.
	 *
	 * @param totalViolations the new total violations
	 */
	public void setTotalViolations(final Long totalViolations) {
		this.totalViolations = totalViolations;
	}

	/**
	 * Gets the members with violations.
	 *
	 * @return the members with violations
	 */
	public Long getMembersWithViolations() {
		return membersWithViolations;
	}

	/**
	 * Sets the members with violations.
	 *
	 * @param membersWithViolations the new members with violations
	 */
	public void setMembersWithViolations(final Long membersWithViolations) {
		this.membersWithViolations = membersWithViolations;
	}

	/**
	 * Gets the violation rate percentage.
	 *
	 * @return the violation rate percentage
	 */
	public BigDecimal getViolationRatePercentage() {
		return violationRatePercentage;
	}

	/**
	 * Sets the violation rate percentage.
	 *
	 * @param violationRatePercentage the new violation rate percentage
	 */
	public void setViolationRatePercentage(final BigDecimal violationRatePercentage) {
		this.violationRatePercentage = violationRatePercentage;
	}

	/**
	 * Gets the latest member violation.
	 *
	 * @return the latest member violation
	 */
	public Date getLatestMemberViolation() {
		return latestMemberViolation;
	}

	/**
	 * Sets the latest member violation.
	 *
	 * @param latestMemberViolation the new latest member violation
	 */
	public void setLatestMemberViolation(final Date latestMemberViolation) {
		this.latestMemberViolation = latestMemberViolation;
	}

	/**
	 * Gets the total votes last year.
	 *
	 * @return the total votes last year
	 */
	public Long getTotalVotesLastYear() {
		return totalVotesLastYear;
	}

	/**
	 * Sets the total votes last year.
	 *
	 * @param totalVotesLastYear the new total votes last year
	 */
	public void setTotalVotesLastYear(final Long totalVotesLastYear) {
		this.totalVotesLastYear = totalVotesLastYear;
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
	 * Gets the avg participation rate.
	 *
	 * @return the avg participation rate
	 */
	public BigDecimal getAvgParticipationRate() {
		return avgParticipationRate;
	}

	/**
	 * Sets the avg participation rate.
	 *
	 * @param avgParticipationRate the new avg participation rate
	 */
	public void setAvgParticipationRate(final BigDecimal avgParticipationRate) {
		this.avgParticipationRate = avgParticipationRate;
	}

	/**
	 * Gets the documents last year.
	 *
	 * @return the documents last year
	 */
	public Long getDocumentsLastYear() {
		return documentsLastYear;
	}

	/**
	 * Sets the documents last year.
	 *
	 * @param documentsLastYear the new documents last year
	 */
	public void setDocumentsLastYear(final Long documentsLastYear) {
		this.documentsLastYear = documentsLastYear;
	}

	/**
	 * Gets the motions last year.
	 *
	 * @return the motions last year
	 */
	public Long getMotionsLastYear() {
		return motionsLastYear;
	}

	/**
	 * Sets the motions last year.
	 *
	 * @param motionsLastYear the new motions last year
	 */
	public void setMotionsLastYear(final Long motionsLastYear) {
		this.motionsLastYear = motionsLastYear;
	}

	/**
	 * Gets the propositions last year.
	 *
	 * @return the propositions last year
	 */
	public Long getPropositionsLastYear() {
		return propositionsLastYear;
	}

	/**
	 * Sets the propositions last year.
	 *
	 * @param propositionsLastYear the new propositions last year
	 */
	public void setPropositionsLastYear(final Long propositionsLastYear) {
		this.propositionsLastYear = propositionsLastYear;
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
	 * Gets the current ministers.
	 *
	 * @return the current ministers
	 */
	public Long getCurrentMinisters() {
		return currentMinisters;
	}

	/**
	 * Sets the current ministers.
	 *
	 * @param currentMinisters the new current ministers
	 */
	public void setCurrentMinisters(final Long currentMinisters) {
		this.currentMinisters = currentMinisters;
	}

	/**
	 * Gets the current committee chairs.
	 *
	 * @return the current committee chairs
	 */
	public Long getCurrentCommitteeChairs() {
		return currentCommitteeChairs;
	}

	/**
	 * Sets the current committee chairs.
	 *
	 * @param currentCommitteeChairs the new current committee chairs
	 */
	public void setCurrentCommitteeChairs(final Long currentCommitteeChairs) {
		this.currentCommitteeChairs = currentCommitteeChairs;
	}

	/**
	 * Gets the performance score.
	 *
	 * @return the performance score
	 */
	public BigDecimal getPerformanceScore() {
		return performanceScore;
	}

	/**
	 * Sets the performance score.
	 *
	 * @param performanceScore the new performance score
	 */
	public void setPerformanceScore(final BigDecimal performanceScore) {
		this.performanceScore = performanceScore;
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
	 * Gets the strengths.
	 *
	 * @return the strengths
	 */
	public String getStrengths() {
		return strengths;
	}

	/**
	 * Sets the strengths.
	 *
	 * @param strengths the new strengths
	 */
	public void setStrengths(final String strengths) {
		this.strengths = strengths;
	}

	/**
	 * Gets the weaknesses.
	 *
	 * @return the weaknesses
	 */
	public String getWeaknesses() {
		return weaknesses;
	}

	/**
	 * Sets the weaknesses.
	 *
	 * @param weaknesses the new weaknesses
	 */
	public void setWeaknesses(final String weaknesses) {
		this.weaknesses = weaknesses;
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
