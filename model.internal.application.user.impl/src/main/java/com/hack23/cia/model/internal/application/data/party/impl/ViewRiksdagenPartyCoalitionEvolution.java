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
 * The Class ViewRiksdagenPartyCoalitionEvolution.
 * Database view for tracking coalition alignment and evolution between party pairs across election cycles.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_party_coalition_evolution")
public class ViewRiksdagenPartyCoalitionEvolution implements Serializable {

private static final long serialVersionUID = 1L;

@EmbeddedId
private ViewRiksdagenPartyCoalitionEvolutionEmbeddedId embeddedId;

	/** The cycle year. */
	@Column(name = "cycle_year")
	private Integer cycleYear;

	/** The calendar year. */
	@Column(name = "calendar_year")
	private Integer calendarYear;

	/** The joint voting days. */
	@Column(name = "joint_voting_days")
	private Long jointVotingDays;

	/** The joint ballots. */
	@Column(name = "joint_ballots")
	private BigDecimal jointBallots;

	/** The aligned ballots. */
	@Column(name = "aligned_ballots")
	private BigDecimal alignedBallots;

	/** The alignment rate. */
	@Column(name = "alignment_rate")
	private BigDecimal alignmentRate;

	/** The avg vote divergence. */
	@Column(name = "avg_vote_divergence")
	private BigDecimal avgVoteDivergence;

	/** The vote divergence stddev. */
	@Column(name = "vote_divergence_stddev")
	private BigDecimal voteDivergenceStddev;

	/** The rank by alignment. */
	@Column(name = "rank_by_alignment")
	private Long rankByAlignment;

	/** The rank by activity. */
	@Column(name = "rank_by_activity")
	private Long rankByActivity;

	/** The rank by consistency. */
	@Column(name = "rank_by_consistency")
	private Long rankByConsistency;

	/** The percentile alignment. */
	@Column(name = "percentile_alignment")
	private Double percentileAlignment;

	/** The percentile cohesion. */
	@Column(name = "percentile_cohesion")
	private Double percentileCohesion;

	/** The quartile coalition strength. */
	@Column(name = "quartile_coalition_strength")
	private Integer quartileCoalitionStrength;

	/** The prev semester alignment. */
	@Column(name = "prev_semester_alignment")
	private BigDecimal prevSemesterAlignment;

	/** The prev semester joint ballots. */
	@Column(name = "prev_semester_joint_ballots")
	private BigDecimal prevSemesterJointBallots;

	/** The prev semester divergence. */
	@Column(name = "prev_semester_divergence")
	private BigDecimal prevSemesterDivergence;

	/** The next semester alignment. */
	@Column(name = "next_semester_alignment")
	private BigDecimal nextSemesterAlignment;

	/** The next semester divergence. */
	@Column(name = "next_semester_divergence")
	private BigDecimal nextSemesterDivergence;

	/** The stddev alignment sector. */
	@Column(name = "stddev_alignment_sector")
	private BigDecimal stddevAlignmentSector;

	/** The stddev alignment pair. */
	@Column(name = "stddev_alignment_pair")
	private BigDecimal stddevAlignmentPair;

	/** The stddev divergence pair. */
	@Column(name = "stddev_divergence_pair")
	private BigDecimal stddevDivergencePair;

	/** The ma 3semester alignment. */
	@Column(name = "ma_3semester_alignment")
	private BigDecimal ma3semesterAlignment;

	/** The alignment change absolute. */
	@Column(name = "alignment_change_absolute")
	private BigDecimal alignmentChangeAbsolute;

	/** The alignment change pct. */
	@Column(name = "alignment_change_pct")
	private BigDecimal alignmentChangePct;

	/** The activity change. */
	@Column(name = "activity_change")
	private BigDecimal activityChange;

	/** The divergence change. */
	@Column(name = "divergence_change")
	private BigDecimal divergenceChange;

	/** The coalition strength. */
	@Column(name = "coalition_strength")
	private String coalitionStrength;

	/** The coalition trend. */
	@Column(name = "coalition_trend")
	private String coalitionTrend;

	/** The strategic shift. */
	@Column(name = "strategic_shift")
	private String strategicShift;

	/** The volatility classification. */
	@Column(name = "volatility_classification")
	private String volatilityClassification;

	/** The consistency classification. */
	@Column(name = "consistency_classification")
	private String consistencyClassification;

	/** The forecast trend. */
	@Column(name = "forecast_trend")
	private String forecastTrend;

	/** The alignment deviation from ma. */
	@Column(name = "alignment_deviation_from_ma")
	private BigDecimal alignmentDeviationFromMa;

	/** The trend position. */
	@Column(name = "trend_position")
	private String trendPosition;

	/** The coalition tier. */
	@Column(name = "coalition_tier")
	private String coalitionTier;

	/** The momentum z score. */
	@Column(name = "momentum_z_score")
	private BigDecimal momentumZScore;

	/** The stability score. */
	@Column(name = "stability_score")
	private BigDecimal stabilityScore;

	/** The breakup risk score. */
	@Column(name = "breakup_risk_score")
	private BigDecimal breakupRiskScore;

	/** The realignment probability. */
	@Column(name = "realignment_probability")
	private String realignmentProbability;

	/** The coalition density score. */
	@Column(name = "coalition_density_score")
	private BigDecimal coalitionDensityScore;

	/** The bridge classification. */
	@Column(name = "bridge_classification")
	private String bridgeClassification;

	/**
	 * Instantiates a new view riksdagen party coalition evolution.
	 */
	public ViewRiksdagenPartyCoalitionEvolution() {
		super();
	}

	/**
	 * Gets the embedded id.
	 *
	 * @return the embedded id
	 */
	public ViewRiksdagenPartyCoalitionEvolutionEmbeddedId getEmbeddedId() {
		return embeddedId;
	}

	/**
	 * Sets the embedded id.
	 *
	 * @param embeddedId the new embedded id
	 */
	public void setEmbeddedId(final ViewRiksdagenPartyCoalitionEvolutionEmbeddedId embeddedId) {
		this.embeddedId = embeddedId;
	}

	/**
	 * Gets the party 1.
	 *
	 * @return the party 1
	 */
	public String getParty1() {
		return embeddedId != null ? embeddedId.getParty1() : null;
	}

	/**
	 * Gets the party 2.
	 *
	 * @return the party 2
	 */
	public String getParty2() {
		return embeddedId != null ? embeddedId.getParty2() : null;
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
	 * Gets the joint voting days.
	 *
	 * @return the joint voting days
	 */
	public Long getJointVotingDays() {
		return jointVotingDays;
	}

	/**
	 * Sets the joint voting days.
	 *
	 * @param jointVotingDays the new joint voting days
	 */
	public void setJointVotingDays(final Long jointVotingDays) {
		this.jointVotingDays = jointVotingDays;
	}

	/**
	 * Gets the joint ballots.
	 *
	 * @return the joint ballots
	 */
	public BigDecimal getJointBallots() {
		return jointBallots;
	}

	/**
	 * Sets the joint ballots.
	 *
	 * @param jointBallots the new joint ballots
	 */
	public void setJointBallots(final BigDecimal jointBallots) {
		this.jointBallots = jointBallots;
	}

	/**
	 * Gets the aligned ballots.
	 *
	 * @return the aligned ballots
	 */
	public BigDecimal getAlignedBallots() {
		return alignedBallots;
	}

	/**
	 * Sets the aligned ballots.
	 *
	 * @param alignedBallots the new aligned ballots
	 */
	public void setAlignedBallots(final BigDecimal alignedBallots) {
		this.alignedBallots = alignedBallots;
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
	 * Gets the avg vote divergence.
	 *
	 * @return the avg vote divergence
	 */
	public BigDecimal getAvgVoteDivergence() {
		return avgVoteDivergence;
	}

	/**
	 * Sets the avg vote divergence.
	 *
	 * @param avgVoteDivergence the new avg vote divergence
	 */
	public void setAvgVoteDivergence(final BigDecimal avgVoteDivergence) {
		this.avgVoteDivergence = avgVoteDivergence;
	}

	/**
	 * Gets the vote divergence stddev.
	 *
	 * @return the vote divergence stddev
	 */
	public BigDecimal getVoteDivergenceStddev() {
		return voteDivergenceStddev;
	}

	/**
	 * Sets the vote divergence stddev.
	 *
	 * @param voteDivergenceStddev the new vote divergence stddev
	 */
	public void setVoteDivergenceStddev(final BigDecimal voteDivergenceStddev) {
		this.voteDivergenceStddev = voteDivergenceStddev;
	}

	/**
	 * Gets the rank by alignment.
	 *
	 * @return the rank by alignment
	 */
	public Long getRankByAlignment() {
		return rankByAlignment;
	}

	/**
	 * Sets the rank by alignment.
	 *
	 * @param rankByAlignment the new rank by alignment
	 */
	public void setRankByAlignment(final Long rankByAlignment) {
		this.rankByAlignment = rankByAlignment;
	}

	/**
	 * Gets the rank by activity.
	 *
	 * @return the rank by activity
	 */
	public Long getRankByActivity() {
		return rankByActivity;
	}

	/**
	 * Sets the rank by activity.
	 *
	 * @param rankByActivity the new rank by activity
	 */
	public void setRankByActivity(final Long rankByActivity) {
		this.rankByActivity = rankByActivity;
	}

	/**
	 * Gets the rank by consistency.
	 *
	 * @return the rank by consistency
	 */
	public Long getRankByConsistency() {
		return rankByConsistency;
	}

	/**
	 * Sets the rank by consistency.
	 *
	 * @param rankByConsistency the new rank by consistency
	 */
	public void setRankByConsistency(final Long rankByConsistency) {
		this.rankByConsistency = rankByConsistency;
	}

	/**
	 * Gets the percentile alignment.
	 *
	 * @return the percentile alignment
	 */
	public Double getPercentileAlignment() {
		return percentileAlignment;
	}

	/**
	 * Sets the percentile alignment.
	 *
	 * @param percentileAlignment the new percentile alignment
	 */
	public void setPercentileAlignment(final Double percentileAlignment) {
		this.percentileAlignment = percentileAlignment;
	}

	/**
	 * Gets the percentile cohesion.
	 *
	 * @return the percentile cohesion
	 */
	public Double getPercentileCohesion() {
		return percentileCohesion;
	}

	/**
	 * Sets the percentile cohesion.
	 *
	 * @param percentileCohesion the new percentile cohesion
	 */
	public void setPercentileCohesion(final Double percentileCohesion) {
		this.percentileCohesion = percentileCohesion;
	}

	/**
	 * Gets the quartile coalition strength.
	 *
	 * @return the quartile coalition strength
	 */
	public Integer getQuartileCoalitionStrength() {
		return quartileCoalitionStrength;
	}

	/**
	 * Sets the quartile coalition strength.
	 *
	 * @param quartileCoalitionStrength the new quartile coalition strength
	 */
	public void setQuartileCoalitionStrength(final Integer quartileCoalitionStrength) {
		this.quartileCoalitionStrength = quartileCoalitionStrength;
	}

	/**
	 * Gets the prev semester alignment.
	 *
	 * @return the prev semester alignment
	 */
	public BigDecimal getPrevSemesterAlignment() {
		return prevSemesterAlignment;
	}

	/**
	 * Sets the prev semester alignment.
	 *
	 * @param prevSemesterAlignment the new prev semester alignment
	 */
	public void setPrevSemesterAlignment(final BigDecimal prevSemesterAlignment) {
		this.prevSemesterAlignment = prevSemesterAlignment;
	}

	/**
	 * Gets the prev semester joint ballots.
	 *
	 * @return the prev semester joint ballots
	 */
	public BigDecimal getPrevSemesterJointBallots() {
		return prevSemesterJointBallots;
	}

	/**
	 * Sets the prev semester joint ballots.
	 *
	 * @param prevSemesterJointBallots the new prev semester joint ballots
	 */
	public void setPrevSemesterJointBallots(final BigDecimal prevSemesterJointBallots) {
		this.prevSemesterJointBallots = prevSemesterJointBallots;
	}

	/**
	 * Gets the prev semester divergence.
	 *
	 * @return the prev semester divergence
	 */
	public BigDecimal getPrevSemesterDivergence() {
		return prevSemesterDivergence;
	}

	/**
	 * Sets the prev semester divergence.
	 *
	 * @param prevSemesterDivergence the new prev semester divergence
	 */
	public void setPrevSemesterDivergence(final BigDecimal prevSemesterDivergence) {
		this.prevSemesterDivergence = prevSemesterDivergence;
	}

	/**
	 * Gets the next semester alignment.
	 *
	 * @return the next semester alignment
	 */
	public BigDecimal getNextSemesterAlignment() {
		return nextSemesterAlignment;
	}

	/**
	 * Sets the next semester alignment.
	 *
	 * @param nextSemesterAlignment the new next semester alignment
	 */
	public void setNextSemesterAlignment(final BigDecimal nextSemesterAlignment) {
		this.nextSemesterAlignment = nextSemesterAlignment;
	}

	/**
	 * Gets the next semester divergence.
	 *
	 * @return the next semester divergence
	 */
	public BigDecimal getNextSemesterDivergence() {
		return nextSemesterDivergence;
	}

	/**
	 * Sets the next semester divergence.
	 *
	 * @param nextSemesterDivergence the new next semester divergence
	 */
	public void setNextSemesterDivergence(final BigDecimal nextSemesterDivergence) {
		this.nextSemesterDivergence = nextSemesterDivergence;
	}

	/**
	 * Gets the stddev alignment sector.
	 *
	 * @return the stddev alignment sector
	 */
	public BigDecimal getStddevAlignmentSector() {
		return stddevAlignmentSector;
	}

	/**
	 * Sets the stddev alignment sector.
	 *
	 * @param stddevAlignmentSector the new stddev alignment sector
	 */
	public void setStddevAlignmentSector(final BigDecimal stddevAlignmentSector) {
		this.stddevAlignmentSector = stddevAlignmentSector;
	}

	/**
	 * Gets the stddev alignment pair.
	 *
	 * @return the stddev alignment pair
	 */
	public BigDecimal getStddevAlignmentPair() {
		return stddevAlignmentPair;
	}

	/**
	 * Sets the stddev alignment pair.
	 *
	 * @param stddevAlignmentPair the new stddev alignment pair
	 */
	public void setStddevAlignmentPair(final BigDecimal stddevAlignmentPair) {
		this.stddevAlignmentPair = stddevAlignmentPair;
	}

	/**
	 * Gets the stddev divergence pair.
	 *
	 * @return the stddev divergence pair
	 */
	public BigDecimal getStddevDivergencePair() {
		return stddevDivergencePair;
	}

	/**
	 * Sets the stddev divergence pair.
	 *
	 * @param stddevDivergencePair the new stddev divergence pair
	 */
	public void setStddevDivergencePair(final BigDecimal stddevDivergencePair) {
		this.stddevDivergencePair = stddevDivergencePair;
	}

	/**
	 * Gets the ma 3semester alignment.
	 *
	 * @return the ma 3semester alignment
	 */
	public BigDecimal getMa3semesterAlignment() {
		return ma3semesterAlignment;
	}

	/**
	 * Sets the ma 3semester alignment.
	 *
	 * @param ma3semesterAlignment the new ma 3semester alignment
	 */
	public void setMa3semesterAlignment(final BigDecimal ma3semesterAlignment) {
		this.ma3semesterAlignment = ma3semesterAlignment;
	}

	/**
	 * Gets the alignment change absolute.
	 *
	 * @return the alignment change absolute
	 */
	public BigDecimal getAlignmentChangeAbsolute() {
		return alignmentChangeAbsolute;
	}

	/**
	 * Sets the alignment change absolute.
	 *
	 * @param alignmentChangeAbsolute the new alignment change absolute
	 */
	public void setAlignmentChangeAbsolute(final BigDecimal alignmentChangeAbsolute) {
		this.alignmentChangeAbsolute = alignmentChangeAbsolute;
	}

	/**
	 * Gets the alignment change pct.
	 *
	 * @return the alignment change pct
	 */
	public BigDecimal getAlignmentChangePct() {
		return alignmentChangePct;
	}

	/**
	 * Sets the alignment change pct.
	 *
	 * @param alignmentChangePct the new alignment change pct
	 */
	public void setAlignmentChangePct(final BigDecimal alignmentChangePct) {
		this.alignmentChangePct = alignmentChangePct;
	}

	/**
	 * Gets the activity change.
	 *
	 * @return the activity change
	 */
	public BigDecimal getActivityChange() {
		return activityChange;
	}

	/**
	 * Sets the activity change.
	 *
	 * @param activityChange the new activity change
	 */
	public void setActivityChange(final BigDecimal activityChange) {
		this.activityChange = activityChange;
	}

	/**
	 * Gets the divergence change.
	 *
	 * @return the divergence change
	 */
	public BigDecimal getDivergenceChange() {
		return divergenceChange;
	}

	/**
	 * Sets the divergence change.
	 *
	 * @param divergenceChange the new divergence change
	 */
	public void setDivergenceChange(final BigDecimal divergenceChange) {
		this.divergenceChange = divergenceChange;
	}

	/**
	 * Gets the coalition strength.
	 *
	 * @return the coalition strength
	 */
	public String getCoalitionStrength() {
		return coalitionStrength;
	}

	/**
	 * Sets the coalition strength.
	 *
	 * @param coalitionStrength the new coalition strength
	 */
	public void setCoalitionStrength(final String coalitionStrength) {
		this.coalitionStrength = coalitionStrength;
	}

	/**
	 * Gets the coalition trend.
	 *
	 * @return the coalition trend
	 */
	public String getCoalitionTrend() {
		return coalitionTrend;
	}

	/**
	 * Sets the coalition trend.
	 *
	 * @param coalitionTrend the new coalition trend
	 */
	public void setCoalitionTrend(final String coalitionTrend) {
		this.coalitionTrend = coalitionTrend;
	}

	/**
	 * Gets the strategic shift.
	 *
	 * @return the strategic shift
	 */
	public String getStrategicShift() {
		return strategicShift;
	}

	/**
	 * Sets the strategic shift.
	 *
	 * @param strategicShift the new strategic shift
	 */
	public void setStrategicShift(final String strategicShift) {
		this.strategicShift = strategicShift;
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
	 * Gets the consistency classification.
	 *
	 * @return the consistency classification
	 */
	public String getConsistencyClassification() {
		return consistencyClassification;
	}

	/**
	 * Sets the consistency classification.
	 *
	 * @param consistencyClassification the new consistency classification
	 */
	public void setConsistencyClassification(final String consistencyClassification) {
		this.consistencyClassification = consistencyClassification;
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
	 * Gets the alignment deviation from ma.
	 *
	 * @return the alignment deviation from ma
	 */
	public BigDecimal getAlignmentDeviationFromMa() {
		return alignmentDeviationFromMa;
	}

	/**
	 * Sets the alignment deviation from ma.
	 *
	 * @param alignmentDeviationFromMa the new alignment deviation from ma
	 */
	public void setAlignmentDeviationFromMa(final BigDecimal alignmentDeviationFromMa) {
		this.alignmentDeviationFromMa = alignmentDeviationFromMa;
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
	 * Gets the coalition tier.
	 *
	 * @return the coalition tier
	 */
	public String getCoalitionTier() {
		return coalitionTier;
	}

	/**
	 * Sets the coalition tier.
	 *
	 * @param coalitionTier the new coalition tier
	 */
	public void setCoalitionTier(final String coalitionTier) {
		this.coalitionTier = coalitionTier;
	}

	/**
	 * Gets the momentum z score.
	 *
	 * @return the momentum z score
	 */
	public BigDecimal getMomentumZScore() {
		return momentumZScore;
	}

	/**
	 * Sets the momentum z score.
	 *
	 * @param momentumZScore the new momentum z score
	 */
	public void setMomentumZScore(final BigDecimal momentumZScore) {
		this.momentumZScore = momentumZScore;
	}

	/**
	 * Gets the stability score.
	 *
	 * @return the stability score
	 */
	public BigDecimal getStabilityScore() {
		return stabilityScore;
	}

	/**
	 * Sets the stability score.
	 *
	 * @param stabilityScore the new stability score
	 */
	public void setStabilityScore(final BigDecimal stabilityScore) {
		this.stabilityScore = stabilityScore;
	}

	/**
	 * Gets the breakup risk score.
	 *
	 * @return the breakup risk score
	 */
	public BigDecimal getBreakupRiskScore() {
		return breakupRiskScore;
	}

	/**
	 * Sets the breakup risk score.
	 *
	 * @param breakupRiskScore the new breakup risk score
	 */
	public void setBreakupRiskScore(final BigDecimal breakupRiskScore) {
		this.breakupRiskScore = breakupRiskScore;
	}

	/**
	 * Gets the realignment probability.
	 *
	 * @return the realignment probability
	 */
	public String getRealignmentProbability() {
		return realignmentProbability;
	}

	/**
	 * Sets the realignment probability.
	 *
	 * @param realignmentProbability the new realignment probability
	 */
	public void setRealignmentProbability(final String realignmentProbability) {
		this.realignmentProbability = realignmentProbability;
	}

	/**
	 * Gets the coalition density score.
	 *
	 * @return the coalition density score
	 */
	public BigDecimal getCoalitionDensityScore() {
		return coalitionDensityScore;
	}

	/**
	 * Sets the coalition density score.
	 *
	 * @param coalitionDensityScore the new coalition density score
	 */
	public void setCoalitionDensityScore(final BigDecimal coalitionDensityScore) {
		this.coalitionDensityScore = coalitionDensityScore;
	}

	/**
	 * Gets the bridge classification.
	 *
	 * @return the bridge classification
	 */
	public String getBridgeClassification() {
		return bridgeClassification;
	}

	/**
	 * Sets the bridge classification.
	 *
	 * @param bridgeClassification the new bridge classification
	 */
	public void setBridgeClassification(final String bridgeClassification) {
		this.bridgeClassification = bridgeClassification;
	}

}
