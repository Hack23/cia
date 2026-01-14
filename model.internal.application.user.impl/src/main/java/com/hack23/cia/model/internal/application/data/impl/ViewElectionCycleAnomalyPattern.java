package com.hack23.cia.model.internal.application.data.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import org.apache.commons.lang3.builder.*;
import org.hibernate.annotations.Immutable;
import com.hack23.cia.model.common.api.ModelObject;

@Entity
@Immutable
@Table(name = "view_election_cycle_anomaly_pattern")
public class ViewElectionCycleAnomalyPattern implements ModelObject, Serializable {
private static final long serialVersionUID = 1L;

@EmbeddedId
private ViewElectionCycleEmbeddedId embeddedId;

@Column(name = "calendar_year")
private Integer calendarYear;

@Column(name = "is_pre_election_semester")
private Boolean isPreElectionSemester;

@Column(name = "avg_risk_score")
private BigDecimal avgRiskScore;

@Column(name = "high_risk_politicians")
private Long highRiskPoliticians;

@Column(name = "high_anomaly_count")
private Long highAnomalyCount;

@Column(name = "avg_total_rebellions")
private BigDecimal avgTotalRebellions;

@Column(name = "strong_consensus_rebels")
private Long strongConsensusRebels;

public ViewElectionCycleAnomalyPattern() {
}

public ViewElectionCycleEmbeddedId getEmbeddedId() {
 embeddedId;
}

public void setEmbeddedId(ViewElectionCycleEmbeddedId embeddedId) {
= embeddedId;
}

public String getElectionCycleId() {
 embeddedId != null ? embeddedId.getElectionCycleId() : null;
}

public String getSemester() {
 embeddedId != null ? embeddedId.getSemester() : null;
}

public Integer getCalendarYear() {
 calendarYear;
}

public void setCalendarYear(Integer calendarYear) {
darYear = calendarYear;
}

public Boolean getIsPreElectionSemester() {
 isPreElectionSemester;
}

public void setIsPreElectionSemester(Boolean isPreElectionSemester) {
Semester = isPreElectionSemester;
}

public BigDecimal getAvgRiskScore() {
 avgRiskScore;
}

public void setAvgRiskScore(BigDecimal avgRiskScore) {
= avgRiskScore;
}

public Long getHighRiskPoliticians() {
 highRiskPoliticians;
}

public void setHighRiskPoliticians(Long highRiskPoliticians) {
s = highRiskPoliticians;
}

public Long getHighAnomalyCount() {
 highAnomalyCount;
}

public void setHighAnomalyCount(Long highAnomalyCount) {
omalyCount = highAnomalyCount;
}

public BigDecimal getAvgTotalRebellions() {
 avgTotalRebellions;
}

public void setAvgTotalRebellions(BigDecimal avgTotalRebellions) {
s = avgTotalRebellions;
}

public Long getStrongConsensusRebels() {
 strongConsensusRebels;
}

public void setStrongConsensusRebels(Long strongConsensusRebels) {
gConsensusRebels = strongConsensusRebels;
}

@Override
public boolean equals(Object obj) {
 EqualsBuilder.reflectionEquals(this, obj);
}

@Override
public int hashCode() {
 HashCodeBuilder.reflectionHashCode(this);
}

@Override
public String toString() {
 ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
}
}
