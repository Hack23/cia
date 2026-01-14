package com.hack23.cia.model.internal.application.data.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import org.apache.commons.lang3.builder.*;
import org.hibernate.annotations.Immutable;
import com.hack23.cia.model.common.api.ModelObject;

@Entity
@Immutable
@Table(name = "view_election_cycle_decision_intelligence")
public class ViewElectionCycleDecisionIntelligence implements ModelObject, Serializable {
private static final long serialVersionUID = 1L;

@EmbeddedId
private ViewElectionCycleEmbeddedId embeddedId;

@Column(name = "calendar_year")
private Integer calendarYear;

@Column(name = "is_pre_election_semester")
private Boolean isPreElectionSemester;

@Column(name = "total_proposals")
private Long totalProposals;

@Column(name = "avg_success_rate")
private BigDecimal avgSuccessRate;

@Column(name = "temporal_approval_rate")
private BigDecimal temporalApprovalRate;

@Column(name = "ministry_approval_rate")
private BigDecimal ministryApprovalRate;

@Column(name = "ministries_with_decisions")
private Long ministriesWithDecisions;

public ViewElectionCycleDecisionIntelligence() {
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

public Long getTotalProposals() {
 totalProposals;
}

public void setTotalProposals(Long totalProposals) {
= totalProposals;
}

public BigDecimal getAvgSuccessRate() {
 avgSuccessRate;
}

public void setAvgSuccessRate(BigDecimal avgSuccessRate) {
= avgSuccessRate;
}

public BigDecimal getTemporalApprovalRate() {
 temporalApprovalRate;
}

public void setTemporalApprovalRate(BigDecimal temporalApprovalRate) {
= temporalApprovalRate;
}

public BigDecimal getMinistryApprovalRate() {
 ministryApprovalRate;
}

public void setMinistryApprovalRate(BigDecimal ministryApprovalRate) {
istryApprovalRate = ministryApprovalRate;
}

public Long getMinistriesWithDecisions() {
 ministriesWithDecisions;
}

public void setMinistriesWithDecisions(Long ministriesWithDecisions) {
istriesWithDecisions = ministriesWithDecisions;
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
