package com.hack23.cia.model.internal.application.data.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import org.apache.commons.lang3.builder.*;
import org.hibernate.annotations.Immutable;
import com.hack23.cia.model.common.api.ModelObject;

@Entity
@Immutable
@Table(name = "view_election_cycle_network_analysis")
public class ViewElectionCycleNetworkAnalysis implements ModelObject, Serializable {
private static final long serialVersionUID = 1L;

@EmbeddedId
private ViewElectionCycleEmbeddedId embeddedId;

@Column(name = "calendar_year")
private Integer calendarYear;

@Column(name = "is_pre_election_semester")
private Boolean isPreElectionSemester;

@Column(name = "avg_alignment_rate")
private BigDecimal avgAlignmentRate;

@Column(name = "unique_party_pairs")
private Long uniquePartyPairs;

@Column(name = "total_alignment_votes")
private Long totalAlignmentVotes;

public ViewElectionCycleNetworkAnalysis() {
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

public BigDecimal getAvgAlignmentRate() {
 avgAlignmentRate;
}

public void setAvgAlignmentRate(BigDecimal avgAlignmentRate) {
mentRate = avgAlignmentRate;
}

public Long getUniquePartyPairs() {
 uniquePartyPairs;
}

public void setUniquePartyPairs(Long uniquePartyPairs) {
iquePartyPairs = uniquePartyPairs;
}

public Long getTotalAlignmentVotes() {
 totalAlignmentVotes;
}

public void setTotalAlignmentVotes(Long totalAlignmentVotes) {
mentVotes = totalAlignmentVotes;
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
