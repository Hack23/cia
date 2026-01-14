package com.hack23.cia.model.internal.application.data.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import org.apache.commons.lang3.builder.*;
import org.hibernate.annotations.Immutable;
import com.hack23.cia.model.common.api.ModelObject;

@Entity
@Immutable
@Table(name = "view_election_cycle_comparative_analysis")
public class ViewElectionCycleComparativeAnalysis implements ModelObject, Serializable {
private static final long serialVersionUID = 1L;

@EmbeddedId
private ViewElectionCycleEmbeddedId embeddedId;

@Column(name = "calendar_year")
private Integer calendarYear;

@Column(name = "is_pre_election_semester")
private Boolean isPreElectionSemester;

@Column(name = "performance_score")
private BigDecimal performanceScore;

@Column(name = "documents_last_year")
private Long documentsLastYear;

@Column(name = "party_avg_rebel_rate")
private BigDecimal partyAvgRebelRate;

@Column(name = "avg_productivity")
private BigDecimal avgProductivity;

@Column(name = "committee_code")
private String committeeCode;

public ViewElectionCycleComparativeAnalysis() {
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

public BigDecimal getPerformanceScore() {
 performanceScore;
}

public void setPerformanceScore(BigDecimal performanceScore) {
ceScore = performanceScore;
}

public Long getDocumentsLastYear() {
 documentsLastYear;
}

public void setDocumentsLastYear(Long documentsLastYear) {
tsLastYear = documentsLastYear;
}

public BigDecimal getPartyAvgRebelRate() {
 partyAvgRebelRate;
}

public void setPartyAvgRebelRate(BigDecimal partyAvgRebelRate) {
AvgRebelRate = partyAvgRebelRate;
}

public BigDecimal getAvgProductivity() {
 avgProductivity;
}

public void setAvgProductivity(BigDecimal avgProductivity) {
 = avgProductivity;
}

public String getCommitteeCode() {
 committeeCode;
}

public void setCommitteeCode(String committeeCode) {
= committeeCode;
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
