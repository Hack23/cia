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
 *$Id$
 *  $HeadURL$
 */
package com.hack23.cia.model.internal.application.data.party.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

/**
 * The Class ViewRiksdagenPartyLongitudinalPerformance.
 * Database view for party performance tracking across election cycles with semester granularity.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_party_longitudinal_performance")
public class ViewRiksdagenPartyLongitudinalPerformance implements Serializable {

private static final long serialVersionUID = 1L;

@EmbeddedId
private ViewRiksdagenPartyLongitudinalPerformanceEmbeddedId embeddedId;

	/** The cycle year. */
	@Column(name = "cycle_year")
	private Integer cycleYear;

	/** The calendar year. */
	@Column(name = "calendar_year")
	private Integer calendarYear;

	/** The is election year. */
	@Column(name = "is_election_year")
	private Boolean isElectionYear;

	/** The total ballots. */
	@Column(name = "total_ballots")
	private BigDecimal totalBallots;

	/** The participation rate. */
	@Column(name = "participation_rate")
	private BigDecimal participationRate;

	/** The win rate. */
	@Column(name = "win_rate")
	private BigDecimal winRate;

	/** The yes rate. */
	@Column(name = "yes_rate")
	private BigDecimal yesRate;

	/** The no rate. */
	@Column(name = "no_rate")
	private BigDecimal noRate;

	/** The approval rate. */
	@Column(name = "approval_rate")
	private BigDecimal approvalRate;

	/** The total votes. */
	@Column(name = "total_votes")
	private BigDecimal totalVotes;

	/** The yes votes. */
	@Column(name = "yes_votes")
	private BigDecimal yesVotes;

	/** The ballots won. */
	@Column(name = "ballots_won")
	private BigDecimal ballotsWon;

	/** The decisions approved. */
	@Column(name = "decisions_approved")
	private BigDecimal decisionsApproved;

	/** The active members. */
	@Column(name = "active_members")
	private Long activeMembers;

	/** The documents last year. */
	@Column(name = "documents_last_year")
	private Long documentsLastYear;

	/** The avg rebel rate. */
	@Column(name = "avg_rebel_rate")
	private BigDecimal avgRebelRate;

	/** The current performance score. */
	@Column(name = "current_performance_score")
	private BigDecimal currentPerformanceScore;

	/** The rank by win rate. */
	@Column(name = "rank_by_win_rate")
	private Long rankByWinRate;

	/** The rank by participation. */
	@Column(name = "rank_by_participation")
	private Long rankByParticipation;

	/** The rank by size. */
	@Column(name = "rank_by_size")
	private Long rankBySize;

	/** The rank by approval. */
	@Column(name = "rank_by_approval")
	private Long rankByApproval;

	/** The rank by productivity. */
	@Column(name = "rank_by_productivity")
	private Long rankByProductivity;

	/** The rank by discipline. */
	@Column(name = "rank_by_discipline")
	private Long rankByDiscipline;

	/** The percentile win rate. */
	@Column(name = "percentile_win_rate")
	private Double percentileWinRate;

	/** The percentile participation. */
	@Column(name = "percentile_participation")
	private Double percentileParticipation;

	/** The percentile approval. */
	@Column(name = "percentile_approval")
	private Double percentileApproval;

	/** The percentile productivity. */
	@Column(name = "percentile_productivity")
	private Double percentileProductivity;

	/** The quartile by win rate. */
	@Column(name = "quartile_by_win_rate")
	private Integer quartileByWinRate;

	/** The quartile by overall performance. */
	@Column(name = "quartile_by_overall_performance")
	private Integer quartileByOverallPerformance;

	/** The prev semester win rate. */
	@Column(name = "prev_semester_win_rate")
	private BigDecimal prevSemesterWinRate;

	/** The prev semester participation. */
	@Column(name = "prev_semester_participation")
	private BigDecimal prevSemesterParticipation;

	/** The prev semester members. */
	@Column(name = "prev_semester_members")
	private Long prevSemesterMembers;

	/** The prev semester approval. */
	@Column(name = "prev_semester_approval")
	private BigDecimal prevSemesterApproval;

	/** The prev semester documents. */
	@Column(name = "prev_semester_documents")
	private Long prevSemesterDocuments;

	/** The prev semester rebel rate. */
	@Column(name = "prev_semester_rebel_rate")
	private BigDecimal prevSemesterRebelRate;

	/** The next semester win rate. */
	@Column(name = "next_semester_win_rate")
	private BigDecimal nextSemesterWinRate;

	/** The next semester participation. */
	@Column(name = "next_semester_participation")
	private BigDecimal nextSemesterParticipation;

	/** The next semester members. */
	@Column(name = "next_semester_members")
	private Long nextSemesterMembers;

	/** The stddev win rate sector. */
	@Column(name = "stddev_win_rate_sector")
	private BigDecimal stddevWinRateSector;

	/** The stddev participation sector. */
	@Column(name = "stddev_participation_sector")
	private BigDecimal stddevParticipationSector;

	/** The stddev win rate party. */
	@Column(name = "stddev_win_rate_party")
	private BigDecimal stddevWinRateParty;

	/** The stddev participation party. */
	@Column(name = "stddev_participation_party")
	private BigDecimal stddevParticipationParty;

	/** The ma 3semester win rate. */
	@Column(name = "ma_3semester_win_rate")
	private BigDecimal ma3semesterWinRate;

	/** The ma 3semester participation. */
	@Column(name = "ma_3semester_participation")
	private BigDecimal ma3semesterParticipation;

	/** The win rate change absolute. */
	@Column(name = "win_rate_change_absolute")
	private BigDecimal winRateChangeAbsolute;

	/** The win rate change pct. */
	@Column(name = "win_rate_change_pct")
	private BigDecimal winRateChangePct;

	/** The participation change absolute. */
	@Column(name = "participation_change_absolute")
	private BigDecimal participationChangeAbsolute;

	/** The membership change. */
	@Column(name = "membership_change")
	private Long membershipChange;

	/** The approval rate change. */
	@Column(name = "approval_rate_change")
	private BigDecimal approvalRateChange;

	/** The documents change. */
	@Column(name = "documents_change")
	private Long documentsChange;

	/** The discipline change. */
	@Column(name = "discipline_change")
	private BigDecimal disciplineChange;

	/** The trajectory win rate. */
	@Column(name = "trajectory_win_rate")
	private String trajectoryWinRate;

	/** The trajectory participation. */
	@Column(name = "trajectory_participation")
	private String trajectoryParticipation;

	/** The composite performance index. */
	@Column(name = "composite_performance_index")
	private BigDecimal compositePerformanceIndex;

	/** The discipline effectiveness score. */
	@Column(name = "discipline_effectiveness_score")
	private BigDecimal disciplineEffectivenessScore;

	/** The legislative effectiveness score. */
	@Column(name = "legislative_effectiveness_score")
	private BigDecimal legislativeEffectivenessScore;

	/** The volatility classification. */
	@Column(name = "volatility_classification")
	private String volatilityClassification;

	/** The stability classification. */
	@Column(name = "stability_classification")
	private String stabilityClassification;

	/** The forecast trend. */
	@Column(name = "forecast_trend")
	private String forecastTrend;

	/** The trend deviation from ma. */
	@Column(name = "trend_deviation_from_ma")
	private BigDecimal trendDeviationFromMa;

	/** The trend position. */
	@Column(name = "trend_position")
	private String trendPosition;

	/** The momentum z score win rate. */
	@Column(name = "momentum_z_score_win_rate")
	private BigDecimal momentumZScoreWinRate;

	/** The momentum z score participation. */
	@Column(name = "momentum_z_score_participation")
	private BigDecimal momentumZScoreParticipation;

	/** The performance tier. */
	@Column(name = "performance_tier")
	private String performanceTier;

	/** The productivity tier. */
	@Column(name = "productivity_tier")
	private String productivityTier;

	/** The early warning flag. */
	@Column(name = "early_warning_flag")
	private String earlyWarningFlag;

	/** The trajectory confidence score. */
	@Column(name = "trajectory_confidence_score")
	private BigDecimal trajectoryConfidenceScore;

	/** The is pre election spring. */
	@Column(name = "is_pre_election_spring")
	private Boolean isPreElectionSpring;

	/** The is election autumn. */
	@Column(name = "is_election_autumn")
	private Boolean isElectionAutumn;

	/** The is election cycle end. */
	@Column(name = "is_election_cycle_end")
	private Boolean isElectionCycleEnd;

	/**
	 * Instantiates a new view riksdagen party longitudinal performance.
	 */
	public ViewRiksdagenPartyLongitudinalPerformance() {
		super();
	}

	/**
	 * Gets the embedded id.
	 *
	 * @return the embedded id
	 */
	public ViewRiksdagenPartyLongitudinalPerformanceEmbeddedId getEmbeddedId() {
		return embeddedId;
	}

	/**
	 * Sets the embedded id.
	 *
	 * @param embeddedId the new embedded id
	 */
	public void setEmbeddedId(final ViewRiksdagenPartyLongitudinalPerformanceEmbeddedId embeddedId) {
		this.embeddedId = embeddedId;
	}

	/**
	 * Gets the party.
	 *
	 * @return the party
	 */
	public String getParty() {
		return embeddedId != null ? embeddedId.getParty() : null;
	}

	/**
	 * Gets the election cycle id.
	 *
	 * @return the election cycle id
	 */
	public String getElectionCycleId() {
		return embeddedId != null ? embeddedId.getElectionCycleId() : null;
	}

	/**
	 * Gets the semester.
	 *
	 * @return the semester
	 */
	public String getSemester() {
		return embeddedId != null ? embeddedId.getSemester() : null;
	}

	/**
	 * Gets the cycle year.
	 *
	 * @return the cycle year
	 */
	public Integer getCycleYear() {
		return cycleYear;
	}

	/**
	 * Sets the cycle year.
	 *
	 * @param cycleYear the new cycle year
	 */
	public void setCycleYear(final Integer cycleYear) {
		this.cycleYear = cycleYear;
	}

	/**
	 * Gets the calendar year.
	 *
	 * @return the calendar year
	 */
	public Integer getCalendarYear() {
		return calendarYear;
	}

	/**
	 * Sets the calendar year.
	 *
	 * @param calendarYear the new calendar year
	 */
	public void setCalendarYear(final Integer calendarYear) {
		this.calendarYear = calendarYear;
	}

	/**
	 * Gets the is election year.
	 *
	 * @return the is election year
	 */
	public Boolean getIsElectionYear() {
		return isElectionYear;
	}

	/**
	 * Sets the is election year.
	 *
	 * @param isElectionYear the new is election year
	 */
	public void setIsElectionYear(final Boolean isElectionYear) {
		this.isElectionYear = isElectionYear;
	}

	/**
	 * Gets the total ballots.
	 *
	 * @return the total ballots
	 */
	public BigDecimal getTotalBallots() {
		return totalBallots;
	}

	/**
	 * Sets the total ballots.
	 *
	 * @param totalBallots the new total ballots
	 */
	public void setTotalBallots(final BigDecimal totalBallots) {
		this.totalBallots = totalBallots;
	}

	/**
	 * Gets the participation rate.
	 *
	 * @return the participation rate
	 */
	public BigDecimal getParticipationRate() {
		return participationRate;
	}

	/**
	 * Sets the participation rate.
	 *
	 * @param participationRate the new participation rate
	 */
	public void setParticipationRate(final BigDecimal participationRate) {
		this.participationRate = participationRate;
	}

	/**
	 * Gets the win rate.
	 *
	 * @return the win rate
	 */
	public BigDecimal getWinRate() {
		return winRate;
	}

	/**
	 * Sets the win rate.
	 *
	 * @param winRate the new win rate
	 */
	public void setWinRate(final BigDecimal winRate) {
		this.winRate = winRate;
	}

	/**
	 * Gets the yes rate.
	 *
	 * @return the yes rate
	 */
	public BigDecimal getYesRate() {
		return yesRate;
	}

	/**
	 * Sets the yes rate.
	 *
	 * @param yesRate the new yes rate
	 */
	public void setYesRate(final BigDecimal yesRate) {
		this.yesRate = yesRate;
	}

	/**
	 * Gets the no rate.
	 *
	 * @return the no rate
	 */
	public BigDecimal getNoRate() {
		return noRate;
	}

	/**
	 * Sets the no rate.
	 *
	 * @param noRate the new no rate
	 */
	public void setNoRate(final BigDecimal noRate) {
		this.noRate = noRate;
	}

	/**
	 * Gets the approval rate.
	 *
	 * @return the approval rate
	 */
	public BigDecimal getApprovalRate() {
		return approvalRate;
	}

	/**
	 * Sets the approval rate.
	 *
	 * @param approvalRate the new approval rate
	 */
	public void setApprovalRate(final BigDecimal approvalRate) {
		this.approvalRate = approvalRate;
	}

	/**
	 * Gets the total votes.
	 *
	 * @return the total votes
	 */
	public BigDecimal getTotalVotes() {
		return totalVotes;
	}

	/**
	 * Sets the total votes.
	 *
	 * @param totalVotes the new total votes
	 */
	public void setTotalVotes(final BigDecimal totalVotes) {
		this.totalVotes = totalVotes;
	}

	/**
	 * Gets the yes votes.
	 *
	 * @return the yes votes
	 */
	public BigDecimal getYesVotes() {
		return yesVotes;
	}

	/**
	 * Sets the yes votes.
	 *
	 * @param yesVotes the new yes votes
	 */
	public void setYesVotes(final BigDecimal yesVotes) {
		this.yesVotes = yesVotes;
	}

	/**
	 * Gets the ballots won.
	 *
	 * @return the ballots won
	 */
	public BigDecimal getBallotsWon() {
		return ballotsWon;
	}

	/**
	 * Sets the ballots won.
	 *
	 * @param ballotsWon the new ballots won
	 */
	public void setBallotsWon(final BigDecimal ballotsWon) {
		this.ballotsWon = ballotsWon;
	}

	/**
	 * Gets the decisions approved.
	 *
	 * @return the decisions approved
	 */
	public BigDecimal getDecisionsApproved() {
		return decisionsApproved;
	}

	/**
	 * Sets the decisions approved.
	 *
	 * @param decisionsApproved the new decisions approved
	 */
	public void setDecisionsApproved(final BigDecimal decisionsApproved) {
		this.decisionsApproved = decisionsApproved;
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
	 * Gets the current performance score.
	 *
	 * @return the current performance score
	 */
	public BigDecimal getCurrentPerformanceScore() {
		return currentPerformanceScore;
	}

	/**
	 * Sets the current performance score.
	 *
	 * @param currentPerformanceScore the new current performance score
	 */
	public void setCurrentPerformanceScore(final BigDecimal currentPerformanceScore) {
		this.currentPerformanceScore = currentPerformanceScore;
	}

	/**
	 * Gets the rank by win rate.
	 *
	 * @return the rank by win rate
	 */
	public Long getRankByWinRate() {
		return rankByWinRate;
	}

	/**
	 * Sets the rank by win rate.
	 *
	 * @param rankByWinRate the new rank by win rate
	 */
	public void setRankByWinRate(final Long rankByWinRate) {
		this.rankByWinRate = rankByWinRate;
	}

	/**
	 * Gets the rank by participation.
	 *
	 * @return the rank by participation
	 */
	public Long getRankByParticipation() {
		return rankByParticipation;
	}

	/**
	 * Sets the rank by participation.
	 *
	 * @param rankByParticipation the new rank by participation
	 */
	public void setRankByParticipation(final Long rankByParticipation) {
		this.rankByParticipation = rankByParticipation;
	}

	/**
	 * Gets the rank by size.
	 *
	 * @return the rank by size
	 */
	public Long getRankBySize() {
		return rankBySize;
	}

	/**
	 * Sets the rank by size.
	 *
	 * @param rankBySize the new rank by size
	 */
	public void setRankBySize(final Long rankBySize) {
		this.rankBySize = rankBySize;
	}

	/**
	 * Gets the rank by approval.
	 *
	 * @return the rank by approval
	 */
	public Long getRankByApproval() {
		return rankByApproval;
	}

	/**
	 * Sets the rank by approval.
	 *
	 * @param rankByApproval the new rank by approval
	 */
	public void setRankByApproval(final Long rankByApproval) {
		this.rankByApproval = rankByApproval;
	}

	/**
	 * Gets the rank by productivity.
	 *
	 * @return the rank by productivity
	 */
	public Long getRankByProductivity() {
		return rankByProductivity;
	}

	/**
	 * Sets the rank by productivity.
	 *
	 * @param rankByProductivity the new rank by productivity
	 */
	public void setRankByProductivity(final Long rankByProductivity) {
		this.rankByProductivity = rankByProductivity;
	}

	/**
	 * Gets the rank by discipline.
	 *
	 * @return the rank by discipline
	 */
	public Long getRankByDiscipline() {
		return rankByDiscipline;
	}

	/**
	 * Sets the rank by discipline.
	 *
	 * @param rankByDiscipline the new rank by discipline
	 */
	public void setRankByDiscipline(final Long rankByDiscipline) {
		this.rankByDiscipline = rankByDiscipline;
	}

	/**
	 * Gets the percentile win rate.
	 *
	 * @return the percentile win rate
	 */
	public Double getPercentileWinRate() {
		return percentileWinRate;
	}

	/**
	 * Sets the percentile win rate.
	 *
	 * @param percentileWinRate the new percentile win rate
	 */
	public void setPercentileWinRate(final Double percentileWinRate) {
		this.percentileWinRate = percentileWinRate;
	}

	/**
	 * Gets the percentile participation.
	 *
	 * @return the percentile participation
	 */
	public Double getPercentileParticipation() {
		return percentileParticipation;
	}

	/**
	 * Sets the percentile participation.
	 *
	 * @param percentileParticipation the new percentile participation
	 */
	public void setPercentileParticipation(final Double percentileParticipation) {
		this.percentileParticipation = percentileParticipation;
	}

	/**
	 * Gets the percentile approval.
	 *
	 * @return the percentile approval
	 */
	public Double getPercentileApproval() {
		return percentileApproval;
	}

	/**
	 * Sets the percentile approval.
	 *
	 * @param percentileApproval the new percentile approval
	 */
	public void setPercentileApproval(final Double percentileApproval) {
		this.percentileApproval = percentileApproval;
	}

	/**
	 * Gets the percentile productivity.
	 *
	 * @return the percentile productivity
	 */
	public Double getPercentileProductivity() {
		return percentileProductivity;
	}

	/**
	 * Sets the percentile productivity.
	 *
	 * @param percentileProductivity the new percentile productivity
	 */
	public void setPercentileProductivity(final Double percentileProductivity) {
		this.percentileProductivity = percentileProductivity;
	}

	/**
	 * Gets the quartile by win rate.
	 *
	 * @return the quartile by win rate
	 */
	public Integer getQuartileByWinRate() {
		return quartileByWinRate;
	}

	/**
	 * Sets the quartile by win rate.
	 *
	 * @param quartileByWinRate the new quartile by win rate
	 */
	public void setQuartileByWinRate(final Integer quartileByWinRate) {
		this.quartileByWinRate = quartileByWinRate;
	}

	/**
	 * Gets the quartile by overall performance.
	 *
	 * @return the quartile by overall performance
	 */
	public Integer getQuartileByOverallPerformance() {
		return quartileByOverallPerformance;
	}

	/**
	 * Sets the quartile by overall performance.
	 *
	 * @param quartileByOverallPerformance the new quartile by overall performance
	 */
	public void setQuartileByOverallPerformance(final Integer quartileByOverallPerformance) {
		this.quartileByOverallPerformance = quartileByOverallPerformance;
	}

	/**
	 * Gets the prev semester win rate.
	 *
	 * @return the prev semester win rate
	 */
	public BigDecimal getPrevSemesterWinRate() {
		return prevSemesterWinRate;
	}

	/**
	 * Sets the prev semester win rate.
	 *
	 * @param prevSemesterWinRate the new prev semester win rate
	 */
	public void setPrevSemesterWinRate(final BigDecimal prevSemesterWinRate) {
		this.prevSemesterWinRate = prevSemesterWinRate;
	}

	/**
	 * Gets the prev semester participation.
	 *
	 * @return the prev semester participation
	 */
	public BigDecimal getPrevSemesterParticipation() {
		return prevSemesterParticipation;
	}

	/**
	 * Sets the prev semester participation.
	 *
	 * @param prevSemesterParticipation the new prev semester participation
	 */
	public void setPrevSemesterParticipation(final BigDecimal prevSemesterParticipation) {
		this.prevSemesterParticipation = prevSemesterParticipation;
	}

	/**
	 * Gets the prev semester members.
	 *
	 * @return the prev semester members
	 */
	public Long getPrevSemesterMembers() {
		return prevSemesterMembers;
	}

	/**
	 * Sets the prev semester members.
	 *
	 * @param prevSemesterMembers the new prev semester members
	 */
	public void setPrevSemesterMembers(final Long prevSemesterMembers) {
		this.prevSemesterMembers = prevSemesterMembers;
	}

	/**
	 * Gets the prev semester approval.
	 *
	 * @return the prev semester approval
	 */
	public BigDecimal getPrevSemesterApproval() {
		return prevSemesterApproval;
	}

	/**
	 * Sets the prev semester approval.
	 *
	 * @param prevSemesterApproval the new prev semester approval
	 */
	public void setPrevSemesterApproval(final BigDecimal prevSemesterApproval) {
		this.prevSemesterApproval = prevSemesterApproval;
	}

	/**
	 * Gets the prev semester documents.
	 *
	 * @return the prev semester documents
	 */
	public Long getPrevSemesterDocuments() {
		return prevSemesterDocuments;
	}

	/**
	 * Sets the prev semester documents.
	 *
	 * @param prevSemesterDocuments the new prev semester documents
	 */
	public void setPrevSemesterDocuments(final Long prevSemesterDocuments) {
		this.prevSemesterDocuments = prevSemesterDocuments;
	}

	/**
	 * Gets the prev semester rebel rate.
	 *
	 * @return the prev semester rebel rate
	 */
	public BigDecimal getPrevSemesterRebelRate() {
		return prevSemesterRebelRate;
	}

	/**
	 * Sets the prev semester rebel rate.
	 *
	 * @param prevSemesterRebelRate the new prev semester rebel rate
	 */
	public void setPrevSemesterRebelRate(final BigDecimal prevSemesterRebelRate) {
		this.prevSemesterRebelRate = prevSemesterRebelRate;
	}

	/**
	 * Gets the next semester win rate.
	 *
	 * @return the next semester win rate
	 */
	public BigDecimal getNextSemesterWinRate() {
		return nextSemesterWinRate;
	}

	/**
	 * Sets the next semester win rate.
	 *
	 * @param nextSemesterWinRate the new next semester win rate
	 */
	public void setNextSemesterWinRate(final BigDecimal nextSemesterWinRate) {
		this.nextSemesterWinRate = nextSemesterWinRate;
	}

	/**
	 * Gets the next semester participation.
	 *
	 * @return the next semester participation
	 */
	public BigDecimal getNextSemesterParticipation() {
		return nextSemesterParticipation;
	}

	/**
	 * Sets the next semester participation.
	 *
	 * @param nextSemesterParticipation the new next semester participation
	 */
	public void setNextSemesterParticipation(final BigDecimal nextSemesterParticipation) {
		this.nextSemesterParticipation = nextSemesterParticipation;
	}

	/**
	 * Gets the next semester members.
	 *
	 * @return the next semester members
	 */
	public Long getNextSemesterMembers() {
		return nextSemesterMembers;
	}

	/**
	 * Sets the next semester members.
	 *
	 * @param nextSemesterMembers the new next semester members
	 */
	public void setNextSemesterMembers(final Long nextSemesterMembers) {
		this.nextSemesterMembers = nextSemesterMembers;
	}

	/**
	 * Gets the stddev win rate sector.
	 *
	 * @return the stddev win rate sector
	 */
	public BigDecimal getStddevWinRateSector() {
		return stddevWinRateSector;
	}

	/**
	 * Sets the stddev win rate sector.
	 *
	 * @param stddevWinRateSector the new stddev win rate sector
	 */
	public void setStddevWinRateSector(final BigDecimal stddevWinRateSector) {
		this.stddevWinRateSector = stddevWinRateSector;
	}

	/**
	 * Gets the stddev participation sector.
	 *
	 * @return the stddev participation sector
	 */
	public BigDecimal getStddevParticipationSector() {
		return stddevParticipationSector;
	}

	/**
	 * Sets the stddev participation sector.
	 *
	 * @param stddevParticipationSector the new stddev participation sector
	 */
	public void setStddevParticipationSector(final BigDecimal stddevParticipationSector) {
		this.stddevParticipationSector = stddevParticipationSector;
	}

	/**
	 * Gets the stddev win rate party.
	 *
	 * @return the stddev win rate party
	 */
	public BigDecimal getStddevWinRateParty() {
		return stddevWinRateParty;
	}

	/**
	 * Sets the stddev win rate party.
	 *
	 * @param stddevWinRateParty the new stddev win rate party
	 */
	public void setStddevWinRateParty(final BigDecimal stddevWinRateParty) {
		this.stddevWinRateParty = stddevWinRateParty;
	}

	/**
	 * Gets the stddev participation party.
	 *
	 * @return the stddev participation party
	 */
	public BigDecimal getStddevParticipationParty() {
		return stddevParticipationParty;
	}

	/**
	 * Sets the stddev participation party.
	 *
	 * @param stddevParticipationParty the new stddev participation party
	 */
	public void setStddevParticipationParty(final BigDecimal stddevParticipationParty) {
		this.stddevParticipationParty = stddevParticipationParty;
	}

	/**
	 * Gets the ma 3semester win rate.
	 *
	 * @return the ma 3semester win rate
	 */
	public BigDecimal getMa3semesterWinRate() {
		return ma3semesterWinRate;
	}

	/**
	 * Sets the ma 3semester win rate.
	 *
	 * @param ma3semesterWinRate the new ma 3semester win rate
	 */
	public void setMa3semesterWinRate(final BigDecimal ma3semesterWinRate) {
		this.ma3semesterWinRate = ma3semesterWinRate;
	}

	/**
	 * Gets the ma 3semester participation.
	 *
	 * @return the ma 3semester participation
	 */
	public BigDecimal getMa3semesterParticipation() {
		return ma3semesterParticipation;
	}

	/**
	 * Sets the ma 3semester participation.
	 *
	 * @param ma3semesterParticipation the new ma 3semester participation
	 */
	public void setMa3semesterParticipation(final BigDecimal ma3semesterParticipation) {
		this.ma3semesterParticipation = ma3semesterParticipation;
	}

	/**
	 * Gets the win rate change absolute.
	 *
	 * @return the win rate change absolute
	 */
	public BigDecimal getWinRateChangeAbsolute() {
		return winRateChangeAbsolute;
	}

	/**
	 * Sets the win rate change absolute.
	 *
	 * @param winRateChangeAbsolute the new win rate change absolute
	 */
	public void setWinRateChangeAbsolute(final BigDecimal winRateChangeAbsolute) {
		this.winRateChangeAbsolute = winRateChangeAbsolute;
	}

	/**
	 * Gets the win rate change pct.
	 *
	 * @return the win rate change pct
	 */
	public BigDecimal getWinRateChangePct() {
		return winRateChangePct;
	}

	/**
	 * Sets the win rate change pct.
	 *
	 * @param winRateChangePct the new win rate change pct
	 */
	public void setWinRateChangePct(final BigDecimal winRateChangePct) {
		this.winRateChangePct = winRateChangePct;
	}

	/**
	 * Gets the participation change absolute.
	 *
	 * @return the participation change absolute
	 */
	public BigDecimal getParticipationChangeAbsolute() {
		return participationChangeAbsolute;
	}

	/**
	 * Sets the participation change absolute.
	 *
	 * @param participationChangeAbsolute the new participation change absolute
	 */
	public void setParticipationChangeAbsolute(final BigDecimal participationChangeAbsolute) {
		this.participationChangeAbsolute = participationChangeAbsolute;
	}

	/**
	 * Gets the membership change.
	 *
	 * @return the membership change
	 */
	public Long getMembershipChange() {
		return membershipChange;
	}

	/**
	 * Sets the membership change.
	 *
	 * @param membershipChange the new membership change
	 */
	public void setMembershipChange(final Long membershipChange) {
		this.membershipChange = membershipChange;
	}

	/**
	 * Gets the approval rate change.
	 *
	 * @return the approval rate change
	 */
	public BigDecimal getApprovalRateChange() {
		return approvalRateChange;
	}

	/**
	 * Sets the approval rate change.
	 *
	 * @param approvalRateChange the new approval rate change
	 */
	public void setApprovalRateChange(final BigDecimal approvalRateChange) {
		this.approvalRateChange = approvalRateChange;
	}

	/**
	 * Gets the documents change.
	 *
	 * @return the documents change
	 */
	public Long getDocumentsChange() {
		return documentsChange;
	}

	/**
	 * Sets the documents change.
	 *
	 * @param documentsChange the new documents change
	 */
	public void setDocumentsChange(final Long documentsChange) {
		this.documentsChange = documentsChange;
	}

	/**
	 * Gets the discipline change.
	 *
	 * @return the discipline change
	 */
	public BigDecimal getDisciplineChange() {
		return disciplineChange;
	}

	/**
	 * Sets the discipline change.
	 *
	 * @param disciplineChange the new discipline change
	 */
	public void setDisciplineChange(final BigDecimal disciplineChange) {
		this.disciplineChange = disciplineChange;
	}

	/**
	 * Gets the trajectory win rate.
	 *
	 * @return the trajectory win rate
	 */
	public String getTrajectoryWinRate() {
		return trajectoryWinRate;
	}

	/**
	 * Sets the trajectory win rate.
	 *
	 * @param trajectoryWinRate the new trajectory win rate
	 */
	public void setTrajectoryWinRate(final String trajectoryWinRate) {
		this.trajectoryWinRate = trajectoryWinRate;
	}

	/**
	 * Gets the trajectory participation.
	 *
	 * @return the trajectory participation
	 */
	public String getTrajectoryParticipation() {
		return trajectoryParticipation;
	}

	/**
	 * Sets the trajectory participation.
	 *
	 * @param trajectoryParticipation the new trajectory participation
	 */
	public void setTrajectoryParticipation(final String trajectoryParticipation) {
		this.trajectoryParticipation = trajectoryParticipation;
	}

	/**
	 * Gets the composite performance index.
	 *
	 * @return the composite performance index
	 */
	public BigDecimal getCompositePerformanceIndex() {
		return compositePerformanceIndex;
	}

	/**
	 * Sets the composite performance index.
	 *
	 * @param compositePerformanceIndex the new composite performance index
	 */
	public void setCompositePerformanceIndex(final BigDecimal compositePerformanceIndex) {
		this.compositePerformanceIndex = compositePerformanceIndex;
	}

	/**
	 * Gets the discipline effectiveness score.
	 *
	 * @return the discipline effectiveness score
	 */
	public BigDecimal getDisciplineEffectivenessScore() {
		return disciplineEffectivenessScore;
	}

	/**
	 * Sets the discipline effectiveness score.
	 *
	 * @param disciplineEffectivenessScore the new discipline effectiveness score
	 */
	public void setDisciplineEffectivenessScore(final BigDecimal disciplineEffectivenessScore) {
		this.disciplineEffectivenessScore = disciplineEffectivenessScore;
	}

	/**
	 * Gets the legislative effectiveness score.
	 *
	 * @return the legislative effectiveness score
	 */
	public BigDecimal getLegislativeEffectivenessScore() {
		return legislativeEffectivenessScore;
	}

	/**
	 * Sets the legislative effectiveness score.
	 *
	 * @param legislativeEffectivenessScore the new legislative effectiveness score
	 */
	public void setLegislativeEffectivenessScore(final BigDecimal legislativeEffectivenessScore) {
		this.legislativeEffectivenessScore = legislativeEffectivenessScore;
	}

	/**
	 * Gets the volatility classification.
	 *
	 * @return the volatility classification
	 */
	public String getVolatilityClassification() {
		return volatilityClassification;
	}

	/**
	 * Sets the volatility classification.
	 *
	 * @param volatilityClassification the new volatility classification
	 */
	public void setVolatilityClassification(final String volatilityClassification) {
		this.volatilityClassification = volatilityClassification;
	}

	/**
	 * Gets the stability classification.
	 *
	 * @return the stability classification
	 */
	public String getStabilityClassification() {
		return stabilityClassification;
	}

	/**
	 * Sets the stability classification.
	 *
	 * @param stabilityClassification the new stability classification
	 */
	public void setStabilityClassification(final String stabilityClassification) {
		this.stabilityClassification = stabilityClassification;
	}

	/**
	 * Gets the forecast trend.
	 *
	 * @return the forecast trend
	 */
	public String getForecastTrend() {
		return forecastTrend;
	}

	/**
	 * Sets the forecast trend.
	 *
	 * @param forecastTrend the new forecast trend
	 */
	public void setForecastTrend(final String forecastTrend) {
		this.forecastTrend = forecastTrend;
	}

	/**
	 * Gets the trend deviation from ma.
	 *
	 * @return the trend deviation from ma
	 */
	public BigDecimal getTrendDeviationFromMa() {
		return trendDeviationFromMa;
	}

	/**
	 * Sets the trend deviation from ma.
	 *
	 * @param trendDeviationFromMa the new trend deviation from ma
	 */
	public void setTrendDeviationFromMa(final BigDecimal trendDeviationFromMa) {
		this.trendDeviationFromMa = trendDeviationFromMa;
	}

	/**
	 * Gets the trend position.
	 *
	 * @return the trend position
	 */
	public String getTrendPosition() {
		return trendPosition;
	}

	/**
	 * Sets the trend position.
	 *
	 * @param trendPosition the new trend position
	 */
	public void setTrendPosition(final String trendPosition) {
		this.trendPosition = trendPosition;
	}

	/**
	 * Gets the momentum z score win rate.
	 *
	 * @return the momentum z score win rate
	 */
	public BigDecimal getMomentumZScoreWinRate() {
		return momentumZScoreWinRate;
	}

	/**
	 * Sets the momentum z score win rate.
	 *
	 * @param momentumZScoreWinRate the new momentum z score win rate
	 */
	public void setMomentumZScoreWinRate(final BigDecimal momentumZScoreWinRate) {
		this.momentumZScoreWinRate = momentumZScoreWinRate;
	}

	/**
	 * Gets the momentum z score participation.
	 *
	 * @return the momentum z score participation
	 */
	public BigDecimal getMomentumZScoreParticipation() {
		return momentumZScoreParticipation;
	}

	/**
	 * Sets the momentum z score participation.
	 *
	 * @param momentumZScoreParticipation the new momentum z score participation
	 */
	public void setMomentumZScoreParticipation(final BigDecimal momentumZScoreParticipation) {
		this.momentumZScoreParticipation = momentumZScoreParticipation;
	}

	/**
	 * Gets the performance tier.
	 *
	 * @return the performance tier
	 */
	public String getPerformanceTier() {
		return performanceTier;
	}

	/**
	 * Sets the performance tier.
	 *
	 * @param performanceTier the new performance tier
	 */
	public void setPerformanceTier(final String performanceTier) {
		this.performanceTier = performanceTier;
	}

	/**
	 * Gets the productivity tier.
	 *
	 * @return the productivity tier
	 */
	public String getProductivityTier() {
		return productivityTier;
	}

	/**
	 * Sets the productivity tier.
	 *
	 * @param productivityTier the new productivity tier
	 */
	public void setProductivityTier(final String productivityTier) {
		this.productivityTier = productivityTier;
	}

	/**
	 * Gets the early warning flag.
	 *
	 * @return the early warning flag
	 */
	public String getEarlyWarningFlag() {
		return earlyWarningFlag;
	}

	/**
	 * Sets the early warning flag.
	 *
	 * @param earlyWarningFlag the new early warning flag
	 */
	public void setEarlyWarningFlag(final String earlyWarningFlag) {
		this.earlyWarningFlag = earlyWarningFlag;
	}

	/**
	 * Gets the trajectory confidence score.
	 *
	 * @return the trajectory confidence score
	 */
	public BigDecimal getTrajectoryConfidenceScore() {
		return trajectoryConfidenceScore;
	}

	/**
	 * Sets the trajectory confidence score.
	 *
	 * @param trajectoryConfidenceScore the new trajectory confidence score
	 */
	public void setTrajectoryConfidenceScore(final BigDecimal trajectoryConfidenceScore) {
		this.trajectoryConfidenceScore = trajectoryConfidenceScore;
	}

	/**
	 * Gets the is pre election spring.
	 *
	 * @return the is pre election spring
	 */
	public Boolean getIsPreElectionSpring() {
		return isPreElectionSpring;
	}

	/**
	 * Sets the is pre election spring.
	 *
	 * @param isPreElectionSpring the new is pre election spring
	 */
	public void setIsPreElectionSpring(final Boolean isPreElectionSpring) {
		this.isPreElectionSpring = isPreElectionSpring;
	}

	/**
	 * Gets the is election autumn.
	 *
	 * @return the is election autumn
	 */
	public Boolean getIsElectionAutumn() {
		return isElectionAutumn;
	}

	/**
	 * Sets the is election autumn.
	 *
	 * @param isElectionAutumn the new is election autumn
	 */
	public void setIsElectionAutumn(final Boolean isElectionAutumn) {
		this.isElectionAutumn = isElectionAutumn;
	}

	/**
	 * Gets the is election cycle end.
	 *
	 * @return the is election cycle end
	 */
	public Boolean getIsElectionCycleEnd() {
		return isElectionCycleEnd;
	}

	/**
	 * Sets the is election cycle end.
	 *
	 * @param isElectionCycleEnd the new is election cycle end
	 */
	public void setIsElectionCycleEnd(final Boolean isElectionCycleEnd) {
		this.isElectionCycleEnd = isElectionCycleEnd;
	}

}
