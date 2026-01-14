/*
 * Copyright 2010-2026 James Pether Sörling
 */
package com.hack23.cia.model.internal.application.data.impl;

import java.math.BigDecimal;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import org.apache.commons.lang3.builder.*;
import com.hack23.cia.model.common.api.ModelObject;

/**
 * The Class ViewElectionCyclePredictiveIntelligence.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewElectionCyclePredictiveIntelligence")
@Entity(name = "ViewElectionCyclePredictiveIntelligence")
@Table(name = "view_election_cycle_predictive_intelligence")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewElectionCyclePredictiveIntelligence implements ModelObject {
private static final long serialVersionUID = 1L;

@XmlElement(required = true)
protected ViewElectionCycleEmbeddedId embeddedId;

@XmlElement(name = "cycle_year")
protected Integer cycleYear;

@XmlElement(name = "calendar_year")
protected Integer calendarYear;

@XmlElement(name = "risk_forecast_category")
protected String riskForecastCategory;

@XmlElement(name = "politicians_at_risk")
protected Long politiciansAtRisk;

@XmlElement(name = "avg_risk_score_change")
protected BigDecimal avgRiskScoreChange;

@XmlElement(name = "ministries_at_risk")
protected Long ministriesAtRisk;

@XmlElement(name = "avg_ministry_productivity")
protected BigDecimal avgMinistryProductivity;

@XmlElement(name = "avg_party_win_rate_trend")
protected BigDecimal avgPartyWinRateTrend;

@XmlElement(name = "parties_with_increasing_absence")
protected Long partiesWithIncreasingAbsence;

@EmbeddedId
public ViewElectionCycleEmbeddedId getEmbeddedId() {
		return embeddedId;
}

public void setEmbeddedId(final ViewElectionCycleEmbeddedId value) {


	this.embeddedId = value;


}

@Basic
@Column(name = "CYCLE_YEAR")
public Integer getCycleYear() {
		return cycleYear;
}

public void setCycleYear(final Integer value) {


	this.cycleYear = value;


}

@Basic
@Column(name = "CALENDAR_YEAR")
public Integer getCalendarYear() {
		return calendarYear;
}

public void setCalendarYear(final Integer value) {


	this.calendarYear = value;


}

@Basic
@Column(name = "RISK_FORECAST_CATEGORY", length = 50)
public String getRiskForecastCategory() {
		return riskForecastCategory;
}

public void setRiskForecastCategory(final String value) {


	this.riskForecastCategory = value;


}

@Basic
@Column(name = "POLITICIANS_AT_RISK", precision = 20)
public Long getPoliticiansAtRisk() {
		return politiciansAtRisk;
}

public void setPoliticiansAtRisk(final Long value) {


	this.politiciansAtRisk = value;


}

@Basic
@Column(name = "AVG_RISK_SCORE_CHANGE", precision = 5, scale = 2)
public BigDecimal getAvgRiskScoreChange() {
		return avgRiskScoreChange;
}

public void setAvgRiskScoreChange(final BigDecimal value) {


	this.avgRiskScoreChange = value;


}

@Basic
@Column(name = "MINISTRIES_AT_RISK", precision = 20)
public Long getMinistriesAtRisk() {
		return ministriesAtRisk;
}

public void setMinistriesAtRisk(final Long value) {


	this.ministriesAtRisk = value;


}

@Basic
@Column(name = "AVG_MINISTRY_PRODUCTIVITY", precision = 5, scale = 2)
public BigDecimal getAvgMinistryProductivity() {
		return avgMinistryProductivity;
}

public void setAvgMinistryProductivity(final BigDecimal value) {


	this.avgMinistryProductivity = value;


}

@Basic
@Column(name = "AVG_PARTY_WIN_RATE_TREND", precision = 5, scale = 2)
public BigDecimal getAvgPartyWinRateTrend() {
		return avgPartyWinRateTrend;
}

public void setAvgPartyWinRateTrend(final BigDecimal value) {


	this.avgPartyWinRateTrend = value;


}

@Basic
@Column(name = "PARTIES_WITH_INCREASING_ABSENCE", precision = 20)
public Long getPartiesWithIncreasingAbsence() {
		return partiesWithIncreasingAbsence;
}

public void setPartiesWithIncreasingAbsence(final Long value) {


	this.partiesWithIncreasingAbsence = value;


}

@Override
public final String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
}

@Override
public final boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
}

@Override
public final int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
}
}
