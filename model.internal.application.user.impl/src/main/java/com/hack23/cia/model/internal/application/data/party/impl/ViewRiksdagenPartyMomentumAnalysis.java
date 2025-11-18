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
 * JPA entity for view_riksdagen_party_momentum_analysis database view.
 * 
 * Intelligence Purpose: Analyzes party performance momentum and acceleration patterns
 * with quarterly aggregation, volatility tracking, and stability classifications.
 * Enables detection of rising/declining party trajectories and political instability.
 * 
 * Created by: Liquibase v1.29 (Intelligence Operations)
 * Risk Rules Supported: Pa-01, Pa-02, Pa-07 (Party momentum and stability assessment)
 */
@Entity(name = "ViewRiksdagenPartyMomentumAnalysis")
@Table(name = "view_riksdagen_party_momentum_analysis")
public class ViewRiksdagenPartyMomentumAnalysis implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "party", nullable = false, length = 50)
	private String party;

	@Id
	@Column(name = "year", nullable = false)
	private Integer year;

	@Id
	@Column(name = "quarter", nullable = false)
	private Integer quarter;

	@Column(name = "momentum", precision = 10, scale = 4)
	private BigDecimal momentum;

	@Column(name = "acceleration", precision = 10, scale = 4)
	private BigDecimal acceleration;

	@Column(name = "prev_quarter_rate", precision = 5, scale = 2)
	private BigDecimal prevQuarterRate;

	@Column(name = "moving_avg_4q", precision = 5, scale = 2)
	private BigDecimal movingAvg4Q;

	@Column(name = "volatility", precision = 10, scale = 4)
	private BigDecimal volatility;

	@Column(name = "trend_direction", length = 50)
	private String trendDirection;

	@Column(name = "stability_classification", length = 100)
	private String stabilityClassification;

	@Column(name = "intelligence_assessment", length = 500)
	private String intelligenceAssessment;

	@Column(name = "sample_size")
	private Long sampleSize;

	@Column(name = "periods_analyzed")
	private Integer periodsAnalyzed;

	@Column(name = "confidence_level", length = 50)
	private String confidenceLevel;

	/**
	 * Default constructor.
	 */
	public ViewRiksdagenPartyMomentumAnalysis() {
		super();
	}

	// Getters and Setters

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getQuarter() {
		return quarter;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	public BigDecimal getMomentum() {
		return momentum;
	}

	public void setMomentum(BigDecimal momentum) {
		this.momentum = momentum;
	}

	public BigDecimal getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(BigDecimal acceleration) {
		this.acceleration = acceleration;
	}

	public BigDecimal getPrevQuarterRate() {
		return prevQuarterRate;
	}

	public void setPrevQuarterRate(BigDecimal prevQuarterRate) {
		this.prevQuarterRate = prevQuarterRate;
	}

	public BigDecimal getMovingAvg4Q() {
		return movingAvg4Q;
	}

	public void setMovingAvg4Q(BigDecimal movingAvg4Q) {
		this.movingAvg4Q = movingAvg4Q;
	}

	public BigDecimal getVolatility() {
		return volatility;
	}

	public void setVolatility(BigDecimal volatility) {
		this.volatility = volatility;
	}

	public String getTrendDirection() {
		return trendDirection;
	}

	public void setTrendDirection(String trendDirection) {
		this.trendDirection = trendDirection;
	}

	public String getStabilityClassification() {
		return stabilityClassification;
	}

	public void setStabilityClassification(String stabilityClassification) {
		this.stabilityClassification = stabilityClassification;
	}

	public String getIntelligenceAssessment() {
		return intelligenceAssessment;
	}

	public void setIntelligenceAssessment(String intelligenceAssessment) {
		this.intelligenceAssessment = intelligenceAssessment;
	}

	public Long getSampleSize() {
		return sampleSize;
	}

	public void setSampleSize(Long sampleSize) {
		this.sampleSize = sampleSize;
	}

	public Integer getPeriodsAnalyzed() {
		return periodsAnalyzed;
	}

	public void setPeriodsAnalyzed(Integer periodsAnalyzed) {
		this.periodsAnalyzed = periodsAnalyzed;
	}

	public String getConfidenceLevel() {
		return confidenceLevel;
	}

	public void setConfidenceLevel(String confidenceLevel) {
		this.confidenceLevel = confidenceLevel;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final ViewRiksdagenPartyMomentumAnalysis other = (ViewRiksdagenPartyMomentumAnalysis) obj;
		return new EqualsBuilder()
				.append(party, other.party)
				.append(year, other.year)
				.append(quarter, other.quarter)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(party)
				.append(year)
				.append(quarter)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("party", party)
				.append("year", year)
				.append("quarter", quarter)
				.append("momentum", momentum)
				.append("trendDirection", trendDirection)
				.append("stabilityClassification", stabilityClassification)
				.toString();
	}
}
