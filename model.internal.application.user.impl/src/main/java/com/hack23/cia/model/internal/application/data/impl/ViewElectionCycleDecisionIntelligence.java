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
 * The Class ViewElectionCycleDecisionIntelligence.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewElectionCycleDecisionIntelligence")
@Entity(name = "ViewElectionCycleDecisionIntelligence")
@Table(name = "view_election_cycle_decision_intelligence")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewElectionCycleDecisionIntelligence implements ModelObject {
private static final long serialVersionUID = 1L;

@XmlElement(required = true)
protected ViewElectionCycleEmbeddedId embeddedId;

@XmlElement(name = "cycle_year")
protected Integer cycleYear;

@XmlElement(name = "calendar_year")
protected Integer calendarYear;

@XmlElement
protected String party;

@XmlElement(name = "total_proposals")
protected Long totalProposals;

@XmlElement(name = "approved_proposals")
protected Long approvedProposals;

@XmlElement(name = "rejected_proposals")
protected Long rejectedProposals;

@XmlElement(name = "avg_approval_rate")
protected BigDecimal avgApprovalRate;

@XmlElement(name = "decision_effectiveness")
protected String decisionEffectiveness;

@XmlElement(name = "temporal_approval_rate")
protected BigDecimal temporalApprovalRate;

@XmlElement(name = "temporal_decision_count")
protected Long temporalDecisionCount;

@XmlElement(name = "ministry_impact_score")
protected BigDecimal ministryImpactScore;

@XmlElement(name = "ministries_with_decisions")
protected Long ministriesWithDecisions;

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
@Column(name = "PARTY", length = 50)
public String getParty() {
		return party;
}

public void setParty(final String value) {


	this.party = value;


}

@Basic
@Column(name = "TOTAL_PROPOSALS", precision = 20)
public Long getTotalProposals() {
		return totalProposals;
}

public void setTotalProposals(final Long value) {


	this.totalProposals = value;


}

@Basic
@Column(name = "APPROVED_PROPOSALS", precision = 20)
public Long getApprovedProposals() {
		return approvedProposals;
}

public void setApprovedProposals(final Long value) {


	this.approvedProposals = value;


}

@Basic
@Column(name = "REJECTED_PROPOSALS", precision = 20)
public Long getRejectedProposals() {
		return rejectedProposals;
}

public void setRejectedProposals(final Long value) {


	this.rejectedProposals = value;


}

@Basic
@Column(name = "AVG_APPROVAL_RATE", precision = 5, scale = 2)
public BigDecimal getAvgApprovalRate() {
		return avgApprovalRate;
}

public void setAvgApprovalRate(final BigDecimal value) {


	this.avgApprovalRate = value;


}

@Basic
@Column(name = "DECISION_EFFECTIVENESS", length = 50)
public String getDecisionEffectiveness() {
		return decisionEffectiveness;
}

public void setDecisionEffectiveness(final String value) {


	this.decisionEffectiveness = value;


}

@Basic
@Column(name = "TEMPORAL_APPROVAL_RATE", precision = 5, scale = 2)
public BigDecimal getTemporalApprovalRate() {
		return temporalApprovalRate;
}

public void setTemporalApprovalRate(final BigDecimal value) {


	this.temporalApprovalRate = value;


}

@Basic
@Column(name = "TEMPORAL_DECISION_COUNT", precision = 20)
public Long getTemporalDecisionCount() {
		return temporalDecisionCount;
}

public void setTemporalDecisionCount(final Long value) {


	this.temporalDecisionCount = value;


}

@Basic
@Column(name = "MINISTRY_IMPACT_SCORE", precision = 5, scale = 2)
public BigDecimal getMinistryImpactScore() {
		return ministryImpactScore;
}

public void setMinistryImpactScore(final BigDecimal value) {


	this.ministryImpactScore = value;


}

@Basic
@Column(name = "MINISTRIES_WITH_DECISIONS", precision = 20)
public Long getMinistriesWithDecisions() {
		return ministriesWithDecisions;
}

public void setMinistriesWithDecisions(final Long value) {


	this.ministriesWithDecisions = value;


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
