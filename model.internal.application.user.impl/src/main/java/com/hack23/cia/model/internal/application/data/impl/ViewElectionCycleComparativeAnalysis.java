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
 * The Class ViewElectionCycleComparativeAnalysis.
 * 
 * Comparative party-level performance metrics across election cycles.
 * 
 * @author intelligence-operative
 * @since v1.51 (Election Cycle Analytics)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewElectionCycleComparativeAnalysis", propOrder = {
    "embeddedId",
    "cycleYear",
    "calendarYear",
    "party",
    "performanceScore",
    "activeMembers",
    "partyViolations",
    "partyWinRate",
    "partyParticipationRate",
    "documentsLastYear",
    "partyAvgRebelRate",
    "committeesActive"
})
@Entity(name = "ViewElectionCycleComparativeAnalysis")
@Table(name = "view_election_cycle_comparative_analysis")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewElectionCycleComparativeAnalysis implements ModelObject {

private static final long serialVersionUID = 1L;

@XmlElement(required = true)
protected ViewElectionCycleEmbeddedId embeddedId;

@XmlElement(name = "cycle_year")
protected Integer cycleYear;

@XmlElement(name = "calendar_year")
protected Integer calendarYear;

@XmlElement
protected String party;

@XmlElement(name = "performance_score")
protected BigDecimal performanceScore;

@XmlElement(name = "active_members")
protected Long activeMembers;

@XmlElement(name = "party_violations")
protected Long partyViolations;

@XmlElement(name = "party_win_rate")
protected BigDecimal partyWinRate;

@XmlElement(name = "party_participation_rate")
protected BigDecimal partyParticipationRate;

@XmlElement(name = "documents_last_year")
protected Long documentsLastYear;

@XmlElement(name = "party_avg_rebel_rate")
protected BigDecimal partyAvgRebelRate;

@XmlElement(name = "committees_active")
protected Long committeesActive;

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
@Column(name = "PARTY", length = 50)
public String getParty() {
 party;
}

public void setParty(final String value) {
 = value;
}

@Basic
@Column(name = "PERFORMANCE_SCORE", precision = 10, scale = 2)
public BigDecimal getPerformanceScore() {
 performanceScore;
}

public void setPerformanceScore(final BigDecimal value) {
ceScore = value;
}

@Basic
@Column(name = "ACTIVE_MEMBERS", precision = 20)
public Long getActiveMembers() {
 activeMembers;
}

public void setActiveMembers(final Long value) {
= value;
}

@Basic
@Column(name = "PARTY_VIOLATIONS", precision = 20)
public Long getPartyViolations() {
 partyViolations;
}

public void setPartyViolations(final Long value) {
Violations = value;
}

@Basic
@Column(name = "PARTY_WIN_RATE", precision = 5, scale = 2)
public BigDecimal getPartyWinRate() {
 partyWinRate;
}

public void setPartyWinRate(final BigDecimal value) {
WinRate = value;
}

@Basic
@Column(name = "PARTY_PARTICIPATION_RATE", precision = 5, scale = 2)
public BigDecimal getPartyParticipationRate() {
 partyParticipationRate;
}

public void setPartyParticipationRate(final BigDecimal value) {
ParticipationRate = value;
}

@Basic
@Column(name = "DOCUMENTS_LAST_YEAR", precision = 20)
public Long getDocumentsLastYear() {
 documentsLastYear;
}

public void setDocumentsLastYear(final Long value) {
tsLastYear = value;
}

@Basic
@Column(name = "PARTY_AVG_REBEL_RATE", precision = 5, scale = 2)
public BigDecimal getPartyAvgRebelRate() {
 partyAvgRebelRate;
}

public void setPartyAvgRebelRate(final BigDecimal value) {
AvgRebelRate = value;
}

@Basic
@Column(name = "COMMITTEES_ACTIVE", precision = 20)
public Long getCommitteesActive() {
 committeesActive;
}

public void setCommitteesActive(final Long value) {
= value;
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
