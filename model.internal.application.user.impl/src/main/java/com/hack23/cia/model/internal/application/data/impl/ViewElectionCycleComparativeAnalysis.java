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
    "committeesActive",
    "rankByPerformance",
    "rankByDiscipline",
    "percentRankPerformance",
    "ntilePartyTier",
    "prevCyclePerformance",
    "prevCycleDocuments",
    "changePerformancePct",
    "changeDocumentsPct",
    "stddevPerformance",
    "performanceTrend",
    "disciplineScore",
    "competitivenessIndex"
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

// NEW v1.52: Statistical enhancement fields
@XmlElement(name = "rank_by_performance")
protected Long rankByPerformance;

@XmlElement(name = "rank_by_discipline")
protected Long rankByDiscipline;

@XmlElement(name = "percent_rank_performance")
protected BigDecimal percentRankPerformance;

@XmlElement(name = "ntile_party_tier")
protected Integer ntilePartyTier;

@XmlElement(name = "prev_cycle_performance")
protected BigDecimal prevCyclePerformance;

@XmlElement(name = "prev_cycle_documents")
protected Long prevCycleDocuments;

@XmlElement(name = "change_performance_pct")
protected BigDecimal changePerformancePct;

@XmlElement(name = "change_documents_pct")
protected BigDecimal changeDocumentsPct;

@XmlElement(name = "stddev_performance")
protected BigDecimal stddevPerformance;

@XmlElement(name = "performance_trend")
protected String performanceTrend;

@XmlElement(name = "discipline_score")
protected BigDecimal disciplineScore;

@XmlElement(name = "competitiveness_index")
protected BigDecimal competitivenessIndex;

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

// NEW v1.52: Statistical enhancement getters and setters
@Basic
@Column(name = "RANK_BY_PERFORMANCE", precision = 20)
public Long getRankByPerformance() {
 rankByPerformance;
}

public void setRankByPerformance(final Long value) {
ByPerformance = value;
}

@Basic
@Column(name = "RANK_BY_DISCIPLINE", precision = 20)
public Long getRankByDiscipline() {
 rankByDiscipline;
}

public void setRankByDiscipline(final Long value) {
 = value;
}

@Basic
@Column(name = "PERCENT_RANK_PERFORMANCE", precision = 5, scale = 4)
public BigDecimal getPercentRankPerformance() {
kPerformance;
}

public void setPercentRankPerformance(final BigDecimal value) {
RankPerformance = value;
}

@Basic
@Column(name = "NTILE_PARTY_TIER")
public Integer getNtilePartyTier() {
 ntilePartyTier;
}

public void setNtilePartyTier(final Integer value) {
artyTier = value;
}

@Basic
@Column(name = "PREV_CYCLE_PERFORMANCE", precision = 5, scale = 2)
public BigDecimal getPrevCyclePerformance() {
 prevCyclePerformance;
}

public void setPrevCyclePerformance(final BigDecimal value) {
yclePerformance = value;
}

@Basic
@Column(name = "PREV_CYCLE_DOCUMENTS", precision = 20)
public Long getPrevCycleDocuments() {
 prevCycleDocuments;
}

public void setPrevCycleDocuments(final Long value) {
= value;
}

@Basic
@Column(name = "CHANGE_PERFORMANCE_PCT", precision = 5, scale = 2)
public BigDecimal getChangePerformancePct() {
 changePerformancePct;
}

public void setChangePerformancePct(final BigDecimal value) {
= value;
}

@Basic
@Column(name = "CHANGE_DOCUMENTS_PCT", precision = 5, scale = 2)
public BigDecimal getChangeDocumentsPct() {
 changeDocumentsPct;
}

public void setChangeDocumentsPct(final BigDecimal value) {
DocumentsPct = value;
}

@Basic
@Column(name = "STDDEV_PERFORMANCE", precision = 5, scale = 2)
public BigDecimal getStddevPerformance() {
 stddevPerformance;
}

public void setStddevPerformance(final BigDecimal value) {
vPerformance = value;
}

@Basic
@Column(name = "PERFORMANCE_TREND", length = 50)
public String getPerformanceTrend() {
 performanceTrend;
}

public void setPerformanceTrend(final String value) {
ceTrend = value;
}

@Basic
@Column(name = "DISCIPLINE_SCORE", precision = 5, scale = 2)
public BigDecimal getDisciplineScore() {
 disciplineScore;
}

public void setDisciplineScore(final BigDecimal value) {
 = value;
}

@Basic
@Column(name = "COMPETITIVENESS_INDEX", precision = 5, scale = 2)
public BigDecimal getCompetitivenessIndex() {
 competitivenessIndex;
}

public void setCompetitivenessIndex(final BigDecimal value) {
venessIndex = value;
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
