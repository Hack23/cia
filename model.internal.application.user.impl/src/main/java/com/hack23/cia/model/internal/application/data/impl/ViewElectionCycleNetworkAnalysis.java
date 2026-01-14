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
@Column(name = "PARTY1", length = 50)
public String getParty1() {
		return party1;
}

public void setParty1(final String value) {


	this.party1 = value;


}

@Basic
@Column(name = "PARTY2", length = 50)
public String getParty2() {
		return party2;
}

public void setParty2(final String value) {


	this.party2 = value;


}

@Basic
@Column(name = "ALIGNMENT_RATE", precision = 5, scale = 2)
public BigDecimal getAlignmentRate() {
		return alignmentRate;
}

public void setAlignmentRate(final BigDecimal value) {


	this.alignmentRate = value;


}

@Basic
@Column(name = "COALITION_STRENGTH", length = 50)
public String getCoalitionStrength() {
		return coalitionStrength;
}

public void setCoalitionStrength(final String value) {


	this.coalitionStrength = value;


}

@Basic
@Column(name = "INFLUENTIAL_POLITICIANS", precision = 20)
public Long getInfluentialPoliticians() {
		return influentialPoliticians;
}

public void setInfluentialPoliticians(final Long value) {


	this.influentialPoliticians = value;


}

@Basic
@Column(name = "AVG_NETWORK_CENTRALITY", precision = 5, scale = 2)
public BigDecimal getAvgNetworkCentrality() {
		return avgNetworkCentrality;
}

public void setAvgNetworkCentrality(final BigDecimal value) {


	this.avgNetworkCentrality = value;


}

@Basic
@Column(name = "POWER_BROKER_COUNT", precision = 20)
public Long getPowerBrokerCount() {
		return powerBrokerCount;
}

public void setPowerBrokerCount(final Long value) {


	this.powerBrokerCount = value;


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
