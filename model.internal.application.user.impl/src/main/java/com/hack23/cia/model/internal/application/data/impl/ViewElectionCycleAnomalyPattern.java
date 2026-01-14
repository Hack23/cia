/*
 * Copyright 2010-2026 James Pether Sörling
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
package com.hack23.cia.model.internal.application.data.impl;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hack23.cia.model.common.api.ModelObject;

/**
 * The Class ViewElectionCycleAnomalyPattern.
 * 
 * Anomaly and pattern detection aggregates across election cycles.
 * 
 * @author intelligence-operative
 * @since v1.51 (Election Cycle Analytics)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewElectionCycleAnomalyPattern", propOrder = {
    "embeddedId",
    "cycleYear",
    "calendarYear",
    "anomalyType",
    "politicianCountWithRisk",
    "avgRiskScore",
    "riskEscalations",
    "highAnomalyCount",
    "avgTotalRebellions",
    "strongConsensusRebels",
    "avgRiskScorePrs",
    "highRiskPoliticians"
})
@Entity(name = "ViewElectionCycleAnomalyPattern")
@Table(name = "view_election_cycle_anomaly_pattern")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewElectionCycleAnomalyPattern implements ModelObject {

private static final long serialVersionUID = 1L;

@XmlElement(required = true)
protected ViewElectionCycleEmbeddedId embeddedId;

@XmlElement(name = "cycle_year")
protected Integer cycleYear;

@XmlElement(name = "calendar_year")
protected Integer calendarYear;

@XmlElement(name = "anomaly_type")
protected String anomalyType;

@XmlElement(name = "politician_count_with_risk")
protected Long politicianCountWithRisk;

@XmlElement(name = "avg_risk_score")
protected BigDecimal avgRiskScore;

@XmlElement(name = "risk_escalations")
protected Long riskEscalations;

@XmlElement(name = "high_anomaly_count")
protected Long highAnomalyCount;

@XmlElement(name = "avg_total_rebellions")
protected BigDecimal avgTotalRebellions;

@XmlElement(name = "strong_consensus_rebels")
protected Long strongConsensusRebels;

@XmlElement(name = "avg_risk_score_prs")
protected BigDecimal avgRiskScorePrs;

@XmlElement(name = "high_risk_politicians")
protected Long highRiskPoliticians;

@EmbeddedId
public ViewElectionCycleEmbeddedId getEmbeddedId() {
	return embeddedId;
}

public void setEmbeddedId(final ViewElectionCycleEmbeddedId value) {
= value;
}

@Basic
@Column(name = "CYCLE_YEAR")
public Integer getCycleYear() {
	return cycleYear;
}

public void setCycleYear(final Integer value) {
cleYear = value;
}

@Basic
@Column(name = "CALENDAR_YEAR")
public Integer getCalendarYear() {
	return calendarYear;
}

public void setCalendarYear(final Integer value) {
darYear = value;
}

@Basic
@Column(name = "ANOMALY_TYPE", length = 50)
public String getAnomalyType() {
	return anomalyType;
}

public void setAnomalyType(final String value) {
omalyType = value;
}

@Basic
@Column(name = "POLITICIAN_COUNT_WITH_RISK", precision = 20)
public Long getPoliticianCountWithRisk() {
	return politicianCountWithRisk;
}

public void setPoliticianCountWithRisk(final Long value) {
		this.CountWithRisk = value;
}

@Basic
@Column(name = "AVG_RISK_SCORE", precision = 5, scale = 2)
public BigDecimal getAvgRiskScore() {
	return avgRiskScore;
}

public void setAvgRiskScore(final BigDecimal value) {
= value;
}

@Basic
@Column(name = "RISK_ESCALATIONS", precision = 20)
public Long getRiskEscalations() {
	return riskEscalations;
}

public void setRiskEscalations(final Long value) {
s = value;
}

@Basic
@Column(name = "HIGH_ANOMALY_COUNT", precision = 20)
public Long getHighAnomalyCount() {
	return highAnomalyCount;
}

public void setHighAnomalyCount(final Long value) {
omalyCount = value;
}

@Basic
@Column(name = "AVG_TOTAL_REBELLIONS", precision = 5, scale = 2)
public BigDecimal getAvgTotalRebellions() {
	return avgTotalRebellions;
}

public void setAvgTotalRebellions(final BigDecimal value) {
s = value;
}

@Basic
@Column(name = "STRONG_CONSENSUS_REBELS", precision = 20)
public Long getStrongConsensusRebels() {
	return strongConsensusRebels;
}

public void setStrongConsensusRebels(final Long value) {
gConsensusRebels = value;
}

@Basic
@Column(name = "AVG_RISK_SCORE_PRS", precision = 5, scale = 2)
public BigDecimal getAvgRiskScorePrs() {
	return avgRiskScorePrs;
}

public void setAvgRiskScorePrs(final BigDecimal value) {
= value;
}

@Basic
@Column(name = "HIGH_RISK_POLITICIANS", precision = 20)
public Long getHighRiskPoliticians() {
	return highRiskPoliticians;
}

public void setHighRiskPoliticians(final Long value) {
s = value;
}

@Override
public final String toString() {
 ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
}

@Override
public final boolean equals(final Object obj) {
 EqualsBuilder.reflectionEquals(this, obj);
}

@Override
public final int hashCode() {
 HashCodeBuilder.reflectionHashCode(this);
}
}
