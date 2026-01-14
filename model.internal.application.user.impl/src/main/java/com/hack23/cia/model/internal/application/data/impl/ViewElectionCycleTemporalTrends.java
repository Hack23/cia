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
 * The Class ViewElectionCycleTemporalTrends.
 * 
 * JPA Entity for view_election_cycle_temporal_trends database view.
 * 
 * Provides temporal trends across Swedish parliamentary election cycles (4-year periods),
 * aggregating behavioral patterns, voting activity, decision outcomes, and committee productivity.
 * 
 * <p>Intelligence Value: ⭐⭐⭐⭐⭐ VERY HIGH</p>
 * <ul>
 * <li>Cross-cycle comparative analysis - benchmark performance across election periods</li>
 * <li>Pre-election pattern detection - identify behavioral changes before elections</li>
 * <li>Seasonal analysis - compare autumn vs spring semester patterns</li>
 * <li>Multi-dimensional intelligence - aggregate behavioral, voting, decision, committee data</li>
 * </ul>
 * 
 * @author intelligence-operative
 * @since v1.51 (Election Cycle Analytics)
 * @see ViewElectionCycleTemporalTrendsEmbeddedId Composite key
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewElectionCycleTemporalTrends", propOrder = {
    "embeddedId",
    "calendarYear",
    "isPreElectionSemester",
    "monthsUntilElection",
    "activePoliticians",
    "avgAttendanceRate",
    "totalBallots",
    "totalVotes",
    "avgWinRate",
    "avgRebelRate",
    "violationCount",
    "avgMaAbsence",
    "avgApprovalRate",
    "totalDecisions",
    "avgCommitteeProductivity"
})
@Entity(name = "ViewElectionCycleTemporalTrends")
@Table(name = "view_election_cycle_temporal_trends")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewElectionCycleTemporalTrends implements ModelObject {

private static final long serialVersionUID = 1L;

@XmlElement(required = true)
protected ViewElectionCycleTemporalTrendsEmbeddedId embeddedId;

@XmlElement(name = "calendar_year")
protected Integer calendarYear;

@XmlElement(name = "is_pre_election_semester")
protected Boolean isPreElectionSemester;

@XmlElement(name = "months_until_election")
protected Integer monthsUntilElection;

@XmlElement(name = "active_politicians")
protected Long activePoliticians;

@XmlElement(name = "avg_attendance_rate")
protected BigDecimal avgAttendanceRate;

@XmlElement(name = "total_ballots")
protected Long totalBallots;

@XmlElement(name = "total_votes")
protected Long totalVotes;

@XmlElement(name = "avg_win_rate")
protected BigDecimal avgWinRate;

@XmlElement(name = "avg_rebel_rate")
protected BigDecimal avgRebelRate;

@XmlElement(name = "violation_count")
protected Long violationCount;

@XmlElement(name = "avg_ma_absence")
protected BigDecimal avgMaAbsence;

@XmlElement(name = "avg_approval_rate")
protected BigDecimal avgApprovalRate;

@XmlElement(name = "total_decisions")
protected Long totalDecisions;

@XmlElement(name = "avg_committee_productivity")
protected BigDecimal avgCommitteeProductivity;

@EmbeddedId
public ViewElectionCycleTemporalTrendsEmbeddedId getEmbeddedId() {
 embeddedId;
}

public void setEmbeddedId(final ViewElectionCycleTemporalTrendsEmbeddedId value) {
= value;
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
@Column(name = "IS_PRE_ELECTION_SEMESTER")
public Boolean getIsPreElectionSemester() {
 isPreElectionSemester;
}

public void setIsPreElectionSemester(final Boolean value) {
Semester = value;
}

@Basic
@Column(name = "MONTHS_UNTIL_ELECTION")
public Integer getMonthsUntilElection() {
 monthsUntilElection;
}

public void setMonthsUntilElection(final Integer value) {
thsUntilElection = value;
}

@Basic
@Column(name = "ACTIVE_POLITICIANS", precision = 20)
public Long getActivePoliticians() {
 activePoliticians;
}

public void setActivePoliticians(final Long value) {
s = value;
}

@Basic
@Column(name = "AVG_ATTENDANCE_RATE", precision = 5, scale = 2)
public BigDecimal getAvgAttendanceRate() {
 avgAttendanceRate;
}

public void setAvgAttendanceRate(final BigDecimal value) {
danceRate = value;
}

@Basic
@Column(name = "TOTAL_BALLOTS", precision = 20)
public Long getTotalBallots() {
 totalBallots;
}

public void setTotalBallots(final Long value) {
= value;
}

@Basic
@Column(name = "TOTAL_VOTES", precision = 20)
public Long getTotalVotes() {
 totalVotes;
}

public void setTotalVotes(final Long value) {
= value;
}

@Basic
@Column(name = "AVG_WIN_RATE", precision = 5, scale = 2)
public BigDecimal getAvgWinRate() {
 avgWinRate;
}

public void setAvgWinRate(final BigDecimal value) {
Rate = value;
}

@Basic
@Column(name = "AVG_REBEL_RATE", precision = 5, scale = 2)
public BigDecimal getAvgRebelRate() {
 avgRebelRate;
}

public void setAvgRebelRate(final BigDecimal value) {
= value;
}

@Basic
@Column(name = "VIOLATION_COUNT", precision = 20)
public Long getViolationCount() {
 violationCount;
}

public void setViolationCount(final Long value) {
Count = value;
}

@Basic
@Column(name = "AVG_MA_ABSENCE", precision = 5, scale = 2)
public BigDecimal getAvgMaAbsence() {
 avgMaAbsence;
}

public void setAvgMaAbsence(final BigDecimal value) {
ce = value;
}

@Basic
@Column(name = "AVG_APPROVAL_RATE", precision = 5, scale = 2)
public BigDecimal getAvgApprovalRate() {
 avgApprovalRate;
}

public void setAvgApprovalRate(final BigDecimal value) {
= value;
}

@Basic
@Column(name = "TOTAL_DECISIONS", precision = 20)
public Long getTotalDecisions() {
 totalDecisions;
}

public void setTotalDecisions(final Long value) {
s = value;
}

@Basic
@Column(name = "AVG_COMMITTEE_PRODUCTIVITY", precision = 5, scale = 2)
public BigDecimal getAvgCommitteeProductivity() {
 avgCommitteeProductivity;
}

public void setAvgCommitteeProductivity(final BigDecimal value) {
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
