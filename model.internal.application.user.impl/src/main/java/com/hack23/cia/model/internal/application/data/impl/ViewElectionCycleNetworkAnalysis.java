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
 * The Class ViewElectionCycleNetworkAnalysis.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewElectionCycleNetworkAnalysis")
@Entity(name = "ViewElectionCycleNetworkAnalysis")
@Table(name = "view_election_cycle_network_analysis")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewElectionCycleNetworkAnalysis implements ModelObject {
private static final long serialVersionUID = 1L;

@XmlElement(required = true)
protected ViewElectionCycleEmbeddedId embeddedId;

@XmlElement(name = "cycle_year")
protected Integer cycleYear;

@XmlElement(name = "calendar_year")
protected Integer calendarYear;

@XmlElement
protected String party1;

@XmlElement
protected String party2;

@XmlElement(name = "alignment_rate")
protected BigDecimal alignmentRate;

@XmlElement(name = "coalition_strength")
protected String coalitionStrength;

@XmlElement(name = "influential_politicians")
protected Long influentialPoliticians;

@XmlElement(name = "avg_network_centrality")
protected BigDecimal avgNetworkCentrality;

@XmlElement(name = "power_broker_count")
protected Long powerBrokerCount;

@EmbeddedId
public ViewElectionCycleEmbeddedId getEmbeddedId() {
 embeddedId;
}

public void setEmbeddedId(final ViewElectionCycleEmbeddedId value) {
= value;
}

@Basic
@Column(name = "CYCLE_YEAR")
public Integer getCycleYear() {
 cycleYear;
}

public void setCycleYear(final Integer value) {
cleYear = value;
}

@Basic
@Column(name = "CALENDAR_YEAR")
public Integer getCalendarYear() {
 calendarYear;
}

public void setCalendarYear(final Integer value) {
darYear = value;
}

@Basic
@Column(name = "PARTY1", length = 50)
public String getParty1() {
 party1;
}

public void setParty1(final String value) {
1 = value;
}

@Basic
@Column(name = "PARTY2", length = 50)
public String getParty2() {
 party2;
}

public void setParty2(final String value) {
2 = value;
}

@Basic
@Column(name = "ALIGNMENT_RATE", precision = 5, scale = 2)
public BigDecimal getAlignmentRate() {
 alignmentRate;
}

public void setAlignmentRate(final BigDecimal value) {
mentRate = value;
}

@Basic
@Column(name = "COALITION_STRENGTH", length = 50)
public String getCoalitionStrength() {
 coalitionStrength;
}

public void setCoalitionStrength(final String value) {
Strength = value;
}

@Basic
@Column(name = "INFLUENTIAL_POLITICIANS", precision = 20)
public Long getInfluentialPoliticians() {
 influentialPoliticians;
}

public void setInfluentialPoliticians(final Long value) {
fluentialPoliticians = value;
}

@Basic
@Column(name = "AVG_NETWORK_CENTRALITY", precision = 5, scale = 2)
public BigDecimal getAvgNetworkCentrality() {
 avgNetworkCentrality;
}

public void setAvgNetworkCentrality(final BigDecimal value) {
etworkCentrality = value;
}

@Basic
@Column(name = "POWER_BROKER_COUNT", precision = 20)
public Long getPowerBrokerCount() {
 powerBrokerCount;
}

public void setPowerBrokerCount(final Long value) {
t = value;
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
