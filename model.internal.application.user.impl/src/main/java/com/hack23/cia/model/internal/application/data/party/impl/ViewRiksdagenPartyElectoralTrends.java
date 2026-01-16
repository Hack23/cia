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
 * The Class ViewRiksdagenPartyElectoralTrends.
 * Database view for tracking party electoral performance trends and seat count proxies across election cycles.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_party_electoral_trends")
public class ViewRiksdagenPartyElectoralTrends implements Serializable {

private static final long serialVersionUID = 1L;

@EmbeddedId
private ViewRiksdagenPartyElectoralTrendsEmbeddedId embeddedId;

	/** The cycle year. */
	@Column(name = "cycle_year")
	private Integer cycleYear;

	/** The calendar year. */
	@Column(name = "calendar_year")
	private Integer calendarYear;

	/** The ballots participated. */
	@Column(name = "ballots_participated")
	private BigDecimal ballotsParticipated;

	/** The win rate. */
	@Column(name = "win_rate")
	private BigDecimal winRate;

	/** The yes rate. */
	@Column(name = "yes_rate")
	private BigDecimal yesRate;

	/** The approval rate. */
	@Column(name = "approval_rate")
	private BigDecimal approvalRate;

	/** The participation rate. */
	@Column(name = "participation_rate")
	private BigDecimal participationRate;

	/** The seat count proxy. */
	@Column(name = "seat_count_proxy")
	private Long seatCountProxy;

	/** The documents produced. */
	@Column(name = "documents_produced")
	private Long documentsProduced;

	/** The avg rebel rate. */
	@Column(name = "avg_rebel_rate")
	private BigDecimal avgRebelRate;

	/** The rank by seats. */
	@Column(name = "rank_by_seats")
	private Long rankBySeats;

	/** The rank by win rate. */
	@Column(name = "rank_by_win_rate")
	private Long rankByWinRate;

	/** The rank by productivity. */
	@Column(name = "rank_by_productivity")
	private Long rankByProductivity;

	/** The rank by engagement. */
	@Column(name = "rank_by_engagement")
	private Long rankByEngagement;

	/** The rank by effectiveness. */
	@Column(name = "rank_by_effectiveness")
	private Long rankByEffectiveness;

	/** The percentile seats. */
	@Column(name = "percentile_seats")
	private Double percentileSeats;

	/** The percentile win rate. */
	@Column(name = "percentile_win_rate")
	private Double percentileWinRate;

	/** The percentile productivity. */
	@Column(name = "percentile_productivity")
	private Double percentileProductivity;

	/** The quartile by size. */
	@Column(name = "quartile_by_size")
	private Integer quartileBySize;

	/** The quartile by performance. */
	@Column(name = "quartile_by_performance")
	private Integer quartileByPerformance;

	/** The prev semester seats. */
	@Column(name = "prev_semester_seats")
	private Long prevSemesterSeats;

	/** The prev semester win rate. */
	@Column(name = "prev_semester_win_rate")
	private BigDecimal prevSemesterWinRate;

	/** The prev semester documents. */
	@Column(name = "prev_semester_documents")
	private Long prevSemesterDocuments;

	/** The prev semester participation. */
	@Column(name = "prev_semester_participation")
	private BigDecimal prevSemesterParticipation;

	/** The next semester seats. */
	@Column(name = "next_semester_seats")
	private Long nextSemesterSeats;

	/** The next semester win rate. */
	@Column(name = "next_semester_win_rate")
	private BigDecimal nextSemesterWinRate;

	/** The stddev seats sector. */
	@Column(name = "stddev_seats_sector")
	private BigDecimal stddevSeatsSector;

	/** The stddev win rate sector. */
	@Column(name = "stddev_win_rate_sector")
	private BigDecimal stddevWinRateSector;

	/** The stddev seats party. */
	@Column(name = "stddev_seats_party")
	private BigDecimal stddevSeatsParty;

	/** The stddev win rate party. */
	@Column(name = "stddev_win_rate_party")
	private BigDecimal stddevWinRateParty;

	/** The ma 3semester seats. */
	@Column(name = "ma_3semester_seats")
	private BigDecimal ma3semesterSeats;

	/** The ma 3semester win rate. */
	@Column(name = "ma_3semester_win_rate")
	private BigDecimal ma3semesterWinRate;

	/** The seat change absolute. */
	@Column(name = "seat_change_absolute")
	private Long seatChangeAbsolute;

	/** The seat change pct. */
	@Column(name = "seat_change_pct")
	private BigDecimal seatChangePct;

	/** The win rate change absolute. */
	@Column(name = "win_rate_change_absolute")
	private BigDecimal winRateChangeAbsolute;

	/** The win rate change pct. */
	@Column(name = "win_rate_change_pct")
	private BigDecimal winRateChangePct;

	/** The documents change. */
	@Column(name = "documents_change")
	private Long documentsChange;

	/** The electoral trend. */
	@Column(name = "electoral_trend")
	private String electoralTrend;

	/** The party size category. */
	@Column(name = "party_size_category")
	private String partySizeCategory;

	/** The volatility classification. */
	@Column(name = "volatility_classification")
	private String volatilityClassification;

	/** The seat forecast. */
	@Column(name = "seat_forecast")
	private String seatForecast;

	/** The performance forecast. */
	@Column(name = "performance_forecast")
	private String performanceForecast;

	/** The seat deviation from ma. */
	@Column(name = "seat_deviation_from_ma")
	private BigDecimal seatDeviationFromMa;

	/** The trend position seats. */
	@Column(name = "trend_position_seats")
	private String trendPositionSeats;

	/** The electoral tier. */
	@Column(name = "electoral_tier")
	private String electoralTier;

	/** The momentum z score seats. */
	@Column(name = "momentum_z_score_seats")
	private BigDecimal momentumZScoreSeats;

	/** The momentum z score win rate. */
	@Column(name = "momentum_z_score_win_rate")
	private BigDecimal momentumZScoreWinRate;

	/** The composite electoral score. */
	@Column(name = "composite_electoral_score")
	private BigDecimal compositeElectoralScore;

	/** The legislative effectiveness index. */
	@Column(name = "legislative_effectiveness_index")
	private BigDecimal legislativeEffectivenessIndex;

	/** The election readiness score. */
	@Column(name = "election_readiness_score")
	private BigDecimal electionReadinessScore;

	/** The projected seat change. */
	@Column(name = "projected_seat_change")
	private BigDecimal projectedSeatChange;

	/** The electoral warning flag. */
	@Column(name = "electoral_warning_flag")
	private String electoralWarningFlag;

	/** The is pre election period. */
	@Column(name = "is_pre_election_period")
	private Boolean isPreElectionPeriod;

	/** The is election period. */
	@Column(name = "is_election_period")
	private Boolean isElectionPeriod;

	/** The is post election period. */
	@Column(name = "is_post_election_period")
	private Boolean isPostElectionPeriod;

	/**
	 * Instantiates a new view riksdagen party electoral trends.
	 */
	public ViewRiksdagenPartyElectoralTrends() {
		super();
	}

	/**
	 * Gets the embedded id.
	 *
	 * @return the embedded id
	 */
	public ViewRiksdagenPartyElectoralTrendsEmbeddedId getEmbeddedId() {
		return embeddedId;
	}

	/**
	 * Sets the embedded id.
	 *
	 * @param embeddedId the new embedded id
	 */
	public void setEmbeddedId(final ViewRiksdagenPartyElectoralTrendsEmbeddedId embeddedId) {
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
	 * Gets the ballots participated.
	 *
	 * @return the ballots participated
	 */
	public BigDecimal getBallotsParticipated() {
		return ballotsParticipated;
	}

	/**
	 * Sets the ballots participated.
	 *
	 * @param ballotsParticipated the new ballots participated
	 */
	public void setBallotsParticipated(final BigDecimal ballotsParticipated) {
		this.ballotsParticipated = ballotsParticipated;
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
	 * Gets the seat count proxy.
	 *
	 * @return the seat count proxy
	 */
	public Long getSeatCountProxy() {
		return seatCountProxy;
	}

	/**
	 * Sets the seat count proxy.
	 *
	 * @param seatCountProxy the new seat count proxy
	 */
	public void setSeatCountProxy(final Long seatCountProxy) {
		this.seatCountProxy = seatCountProxy;
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
	 * Gets the rank by seats.
	 *
	 * @return the rank by seats
	 */
	public Long getRankBySeats() {
		return rankBySeats;
	}

	/**
	 * Sets the rank by seats.
	 *
	 * @param rankBySeats the new rank by seats
	 */
	public void setRankBySeats(final Long rankBySeats) {
		this.rankBySeats = rankBySeats;
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
	 * Gets the rank by engagement.
	 *
	 * @return the rank by engagement
	 */
	public Long getRankByEngagement() {
		return rankByEngagement;
	}

	/**
	 * Sets the rank by engagement.
	 *
	 * @param rankByEngagement the new rank by engagement
	 */
	public void setRankByEngagement(final Long rankByEngagement) {
		this.rankByEngagement = rankByEngagement;
	}

	/**
	 * Gets the rank by effectiveness.
	 *
	 * @return the rank by effectiveness
	 */
	public Long getRankByEffectiveness() {
		return rankByEffectiveness;
	}

	/**
	 * Sets the rank by effectiveness.
	 *
	 * @param rankByEffectiveness the new rank by effectiveness
	 */
	public void setRankByEffectiveness(final Long rankByEffectiveness) {
		this.rankByEffectiveness = rankByEffectiveness;
	}

	/**
	 * Gets the percentile seats.
	 *
	 * @return the percentile seats
	 */
	public Double getPercentileSeats() {
		return percentileSeats;
	}

	/**
	 * Sets the percentile seats.
	 *
	 * @param percentileSeats the new percentile seats
	 */
	public void setPercentileSeats(final Double percentileSeats) {
		this.percentileSeats = percentileSeats;
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
	 * Gets the quartile by size.
	 *
	 * @return the quartile by size
	 */
	public Integer getQuartileBySize() {
		return quartileBySize;
	}

	/**
	 * Sets the quartile by size.
	 *
	 * @param quartileBySize the new quartile by size
	 */
	public void setQuartileBySize(final Integer quartileBySize) {
		this.quartileBySize = quartileBySize;
	}

	/**
	 * Gets the quartile by performance.
	 *
	 * @return the quartile by performance
	 */
	public Integer getQuartileByPerformance() {
		return quartileByPerformance;
	}

	/**
	 * Sets the quartile by performance.
	 *
	 * @param quartileByPerformance the new quartile by performance
	 */
	public void setQuartileByPerformance(final Integer quartileByPerformance) {
		this.quartileByPerformance = quartileByPerformance;
	}

	/**
	 * Gets the prev semester seats.
	 *
	 * @return the prev semester seats
	 */
	public Long getPrevSemesterSeats() {
		return prevSemesterSeats;
	}

	/**
	 * Sets the prev semester seats.
	 *
	 * @param prevSemesterSeats the new prev semester seats
	 */
	public void setPrevSemesterSeats(final Long prevSemesterSeats) {
		this.prevSemesterSeats = prevSemesterSeats;
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
	 * Gets the next semester seats.
	 *
	 * @return the next semester seats
	 */
	public Long getNextSemesterSeats() {
		return nextSemesterSeats;
	}

	/**
	 * Sets the next semester seats.
	 *
	 * @param nextSemesterSeats the new next semester seats
	 */
	public void setNextSemesterSeats(final Long nextSemesterSeats) {
		this.nextSemesterSeats = nextSemesterSeats;
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
	 * Gets the stddev seats sector.
	 *
	 * @return the stddev seats sector
	 */
	public BigDecimal getStddevSeatsSector() {
		return stddevSeatsSector;
	}

	/**
	 * Sets the stddev seats sector.
	 *
	 * @param stddevSeatsSector the new stddev seats sector
	 */
	public void setStddevSeatsSector(final BigDecimal stddevSeatsSector) {
		this.stddevSeatsSector = stddevSeatsSector;
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
	 * Gets the stddev seats party.
	 *
	 * @return the stddev seats party
	 */
	public BigDecimal getStddevSeatsParty() {
		return stddevSeatsParty;
	}

	/**
	 * Sets the stddev seats party.
	 *
	 * @param stddevSeatsParty the new stddev seats party
	 */
	public void setStddevSeatsParty(final BigDecimal stddevSeatsParty) {
		this.stddevSeatsParty = stddevSeatsParty;
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
	 * Gets the ma 3semester seats.
	 *
	 * @return the ma 3semester seats
	 */
	public BigDecimal getMa3semesterSeats() {
		return ma3semesterSeats;
	}

	/**
	 * Sets the ma 3semester seats.
	 *
	 * @param ma3semesterSeats the new ma 3semester seats
	 */
	public void setMa3semesterSeats(final BigDecimal ma3semesterSeats) {
		this.ma3semesterSeats = ma3semesterSeats;
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
	 * Gets the seat change absolute.
	 *
	 * @return the seat change absolute
	 */
	public Long getSeatChangeAbsolute() {
		return seatChangeAbsolute;
	}

	/**
	 * Sets the seat change absolute.
	 *
	 * @param seatChangeAbsolute the new seat change absolute
	 */
	public void setSeatChangeAbsolute(final Long seatChangeAbsolute) {
		this.seatChangeAbsolute = seatChangeAbsolute;
	}

	/**
	 * Gets the seat change pct.
	 *
	 * @return the seat change pct
	 */
	public BigDecimal getSeatChangePct() {
		return seatChangePct;
	}

	/**
	 * Sets the seat change pct.
	 *
	 * @param seatChangePct the new seat change pct
	 */
	public void setSeatChangePct(final BigDecimal seatChangePct) {
		this.seatChangePct = seatChangePct;
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
	 * Gets the electoral trend.
	 *
	 * @return the electoral trend
	 */
	public String getElectoralTrend() {
		return electoralTrend;
	}

	/**
	 * Sets the electoral trend.
	 *
	 * @param electoralTrend the new electoral trend
	 */
	public void setElectoralTrend(final String electoralTrend) {
		this.electoralTrend = electoralTrend;
	}

	/**
	 * Gets the party size category.
	 *
	 * @return the party size category
	 */
	public String getPartySizeCategory() {
		return partySizeCategory;
	}

	/**
	 * Sets the party size category.
	 *
	 * @param partySizeCategory the new party size category
	 */
	public void setPartySizeCategory(final String partySizeCategory) {
		this.partySizeCategory = partySizeCategory;
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
	 * Gets the seat forecast.
	 *
	 * @return the seat forecast
	 */
	public String getSeatForecast() {
		return seatForecast;
	}

	/**
	 * Sets the seat forecast.
	 *
	 * @param seatForecast the new seat forecast
	 */
	public void setSeatForecast(final String seatForecast) {
		this.seatForecast = seatForecast;
	}

	/**
	 * Gets the performance forecast.
	 *
	 * @return the performance forecast
	 */
	public String getPerformanceForecast() {
		return performanceForecast;
	}

	/**
	 * Sets the performance forecast.
	 *
	 * @param performanceForecast the new performance forecast
	 */
	public void setPerformanceForecast(final String performanceForecast) {
		this.performanceForecast = performanceForecast;
	}

	/**
	 * Gets the seat deviation from ma.
	 *
	 * @return the seat deviation from ma
	 */
	public BigDecimal getSeatDeviationFromMa() {
		return seatDeviationFromMa;
	}

	/**
	 * Sets the seat deviation from ma.
	 *
	 * @param seatDeviationFromMa the new seat deviation from ma
	 */
	public void setSeatDeviationFromMa(final BigDecimal seatDeviationFromMa) {
		this.seatDeviationFromMa = seatDeviationFromMa;
	}

	/**
	 * Gets the trend position seats.
	 *
	 * @return the trend position seats
	 */
	public String getTrendPositionSeats() {
		return trendPositionSeats;
	}

	/**
	 * Sets the trend position seats.
	 *
	 * @param trendPositionSeats the new trend position seats
	 */
	public void setTrendPositionSeats(final String trendPositionSeats) {
		this.trendPositionSeats = trendPositionSeats;
	}

	/**
	 * Gets the electoral tier.
	 *
	 * @return the electoral tier
	 */
	public String getElectoralTier() {
		return electoralTier;
	}

	/**
	 * Sets the electoral tier.
	 *
	 * @param electoralTier the new electoral tier
	 */
	public void setElectoralTier(final String electoralTier) {
		this.electoralTier = electoralTier;
	}

	/**
	 * Gets the momentum z score seats.
	 *
	 * @return the momentum z score seats
	 */
	public BigDecimal getMomentumZScoreSeats() {
		return momentumZScoreSeats;
	}

	/**
	 * Sets the momentum z score seats.
	 *
	 * @param momentumZScoreSeats the new momentum z score seats
	 */
	public void setMomentumZScoreSeats(final BigDecimal momentumZScoreSeats) {
		this.momentumZScoreSeats = momentumZScoreSeats;
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
	 * Gets the composite electoral score.
	 *
	 * @return the composite electoral score
	 */
	public BigDecimal getCompositeElectoralScore() {
		return compositeElectoralScore;
	}

	/**
	 * Sets the composite electoral score.
	 *
	 * @param compositeElectoralScore the new composite electoral score
	 */
	public void setCompositeElectoralScore(final BigDecimal compositeElectoralScore) {
		this.compositeElectoralScore = compositeElectoralScore;
	}

	/**
	 * Gets the legislative effectiveness index.
	 *
	 * @return the legislative effectiveness index
	 */
	public BigDecimal getLegislativeEffectivenessIndex() {
		return legislativeEffectivenessIndex;
	}

	/**
	 * Sets the legislative effectiveness index.
	 *
	 * @param legislativeEffectivenessIndex the new legislative effectiveness index
	 */
	public void setLegislativeEffectivenessIndex(final BigDecimal legislativeEffectivenessIndex) {
		this.legislativeEffectivenessIndex = legislativeEffectivenessIndex;
	}

	/**
	 * Gets the election readiness score.
	 *
	 * @return the election readiness score
	 */
	public BigDecimal getElectionReadinessScore() {
		return electionReadinessScore;
	}

	/**
	 * Sets the election readiness score.
	 *
	 * @param electionReadinessScore the new election readiness score
	 */
	public void setElectionReadinessScore(final BigDecimal electionReadinessScore) {
		this.electionReadinessScore = electionReadinessScore;
	}

	/**
	 * Gets the projected seat change.
	 *
	 * @return the projected seat change
	 */
	public BigDecimal getProjectedSeatChange() {
		return projectedSeatChange;
	}

	/**
	 * Sets the projected seat change.
	 *
	 * @param projectedSeatChange the new projected seat change
	 */
	public void setProjectedSeatChange(final BigDecimal projectedSeatChange) {
		this.projectedSeatChange = projectedSeatChange;
	}

	/**
	 * Gets the electoral warning flag.
	 *
	 * @return the electoral warning flag
	 */
	public String getElectoralWarningFlag() {
		return electoralWarningFlag;
	}

	/**
	 * Sets the electoral warning flag.
	 *
	 * @param electoralWarningFlag the new electoral warning flag
	 */
	public void setElectoralWarningFlag(final String electoralWarningFlag) {
		this.electoralWarningFlag = electoralWarningFlag;
	}

	/**
	 * Gets the is pre election period.
	 *
	 * @return the is pre election period
	 */
	public Boolean getIsPreElectionPeriod() {
		return isPreElectionPeriod;
	}

	/**
	 * Sets the is pre election period.
	 *
	 * @param isPreElectionPeriod the new is pre election period
	 */
	public void setIsPreElectionPeriod(final Boolean isPreElectionPeriod) {
		this.isPreElectionPeriod = isPreElectionPeriod;
	}

	/**
	 * Gets the is election period.
	 *
	 * @return the is election period
	 */
	public Boolean getIsElectionPeriod() {
		return isElectionPeriod;
	}

	/**
	 * Sets the is election period.
	 *
	 * @param isElectionPeriod the new is election period
	 */
	public void setIsElectionPeriod(final Boolean isElectionPeriod) {
		this.isElectionPeriod = isElectionPeriod;
	}

	/**
	 * Gets the is post election period.
	 *
	 * @return the is post election period
	 */
	public Boolean getIsPostElectionPeriod() {
		return isPostElectionPeriod;
	}

	/**
	 * Sets the is post election period.
	 *
	 * @param isPostElectionPeriod the new is post election period
	 */
	public void setIsPostElectionPeriod(final Boolean isPostElectionPeriod) {
		this.isPostElectionPeriod = isPostElectionPeriod;
	}

}
