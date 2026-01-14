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
 *	$Id$
 *  $HeadURL$
 */
package com.hack23.cia.model.internal.application.data.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Immutable;

import com.hack23.cia.model.common.api.ModelObject;

/**
 * The Class ViewElectionCycleTemporalTrends.
 * 
 * JPA Entity for view_election_cycle_temporal_trends database view.
 * 
 * Aggregates longitudinal trends for attendance, violations, and decisions
 * across Swedish parliamentary election cycles (4-year cycles since 1994).
 * Provides semester-level granularity (autumn/spring) with pre-election markers.
 * 
 * <p>Intelligence Value: ⭐⭐⭐⭐⭐ VERY HIGH</p>
 * <ul>
 * <li>Election cycle comparative analysis - benchmark across 4-year periods</li>
 * <li>Pre-election behavioral pattern detection - identify campaign impacts</li>
 * <li>Temporal trend forecasting - predict future cycle patterns</li>
 * <li>Seasonal variation analysis - autumn vs spring semester differences</li>
 * </ul>
 * 
 * @author intelligence-operative
 * @since v1.51 (Election Cycle Analysis)
 */
@Entity
@Immutable
@Table(name = "view_election_cycle_temporal_trends")
public class ViewElectionCycleTemporalTrends implements ModelObject, Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ViewElectionCycleTemporalTrendsEmbeddedId embeddedId;

	@Column(name = "calendar_year")
	private Integer calendarYear;

	@Column(name = "is_pre_election_semester")
	private Boolean isPreElectionSemester;

	@Column(name = "months_until_election")
	private Integer monthsUntilElection;

	@Column(name = "avg_attendance_rate")
	private BigDecimal avgAttendanceRate;

	@Column(name = "avg_ma_absence")
	private BigDecimal avgMaAbsence;

	@Column(name = "avg_rebel_rate")
	private BigDecimal avgRebelRate;

	@Column(name = "violation_count")
	private Long violationCount;

	@Column(name = "avg_approval_rate")
	private BigDecimal avgApprovalRate;

	@Column(name = "total_decisions")
	private Long totalDecisions;

	@Column(name = "avg_committee_productivity")
	private BigDecimal avgCommitteeProductivity;

	public ViewElectionCycleTemporalTrends() {
		super();
	}

	public ViewElectionCycleTemporalTrendsEmbeddedId getEmbeddedId() {
		return embeddedId;
	}

	public void setEmbeddedId(ViewElectionCycleTemporalTrendsEmbeddedId embeddedId) {
		this.embeddedId = embeddedId;
	}

	public String getElectionCycleId() {
		return embeddedId != null ? embeddedId.getElectionCycleId() : null;
	}

	public Integer getCycleYear() {
		return embeddedId != null ? embeddedId.getCycleYear() : null;
	}

	public String getSemester() {
		return embeddedId != null ? embeddedId.getSemester() : null;
	}

	public Integer getCalendarYear() {
		return calendarYear;
	}

	public void setCalendarYear(Integer calendarYear) {
		this.calendarYear = calendarYear;
	}

	public Boolean getIsPreElectionSemester() {
		return isPreElectionSemester;
	}

	public void setIsPreElectionSemester(Boolean isPreElectionSemester) {
		this.isPreElectionSemester = isPreElectionSemester;
	}

	public Integer getMonthsUntilElection() {
		return monthsUntilElection;
	}

	public void setMonthsUntilElection(Integer monthsUntilElection) {
		this.monthsUntilElection = monthsUntilElection;
	}

	public BigDecimal getAvgAttendanceRate() {
		return avgAttendanceRate;
	}

	public void setAvgAttendanceRate(BigDecimal avgAttendanceRate) {
		this.avgAttendanceRate = avgAttendanceRate;
	}

	public BigDecimal getAvgMaAbsence() {
		return avgMaAbsence;
	}

	public void setAvgMaAbsence(BigDecimal avgMaAbsence) {
		this.avgMaAbsence = avgMaAbsence;
	}

	public BigDecimal getAvgRebelRate() {
		return avgRebelRate;
	}

	public void setAvgRebelRate(BigDecimal avgRebelRate) {
		this.avgRebelRate = avgRebelRate;
	}

	public Long getViolationCount() {
		return violationCount;
	}

	public void setViolationCount(Long violationCount) {
		this.violationCount = violationCount;
	}

	public BigDecimal getAvgApprovalRate() {
		return avgApprovalRate;
	}

	public void setAvgApprovalRate(BigDecimal avgApprovalRate) {
		this.avgApprovalRate = avgApprovalRate;
	}

	public Long getTotalDecisions() {
		return totalDecisions;
	}

	public void setTotalDecisions(Long totalDecisions) {
		this.totalDecisions = totalDecisions;
	}

	public BigDecimal getAvgCommitteeProductivity() {
		return avgCommitteeProductivity;
	}

	public void setAvgCommitteeProductivity(BigDecimal avgCommitteeProductivity) {
		this.avgCommitteeProductivity = avgCommitteeProductivity;
	}

	@Override
	public final boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public final int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public final String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
