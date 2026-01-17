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
package com.hack23.cia.model.internal.application.data.seasonal.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Immutable;

/**
 * The Class ViewRiksdagenQ4ElectionYearComparison.
 * Database view for Q4 (October-December) election year vs non-election year comparison.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_q4_election_year_comparison")
public class ViewRiksdagenQ4ElectionYearComparison implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "year", nullable = false)
	private Integer year;

	@Column(name = "is_election_year")
	private Boolean isElectionYear;

	@Column(name = "total_ballots")
	private Long totalBallots;

	@Column(name = "active_politicians")
	private Long activePoliticians;

	@Column(name = "attendance_rate")
	private BigDecimal attendanceRate;

	@Column(name = "documents_produced")
	private Long documentsProduced;

	@Column(name = "baseline_ballots")
	private BigDecimal baselineBallots;

	@Column(name = "baseline_docs")
	private BigDecimal baselineDocs;

	@Column(name = "baseline_attendance")
	private BigDecimal baselineAttendance;

	@Column(name = "ballot_deviation_from_baseline")
	private BigDecimal ballotDeviationFromBaseline;

	@Column(name = "doc_deviation_from_baseline")
	private BigDecimal docDeviationFromBaseline;

	@Column(name = "attendance_deviation_from_baseline")
	private BigDecimal attendanceDeviationFromBaseline;

	@Column(name = "ballot_percent_change")
	private BigDecimal ballotPercentChange;

	@Column(name = "doc_percent_change")
	private BigDecimal docPercentChange;

	@Column(name = "q4_pattern")
	private String q4Pattern;

	@Column(name = "ballot_z_score")
	private BigDecimal ballotZScore;

	@Column(name = "doc_z_score")
	private BigDecimal docZScore;

	@Column(name = "attendance_z_score")
	private BigDecimal attendanceZScore;

	@Column(name = "activity_classification")
	private String activityClassification;

	/**
	 * Instantiates a new view riksdagen Q4 election year comparison.
	 */
	public ViewRiksdagenQ4ElectionYearComparison() {
		super();
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(final Integer year) {
		this.year = year;
	}

	public Boolean getIsElectionYear() {
		return isElectionYear;
	}

	public void setIsElectionYear(final Boolean isElectionYear) {
		this.isElectionYear = isElectionYear;
	}

	public Long getTotalBallots() {
		return totalBallots;
	}

	public void setTotalBallots(final Long totalBallots) {
		this.totalBallots = totalBallots;
	}

	public Long getActivePoliticians() {
		return activePoliticians;
	}

	public void setActivePoliticians(final Long activePoliticians) {
		this.activePoliticians = activePoliticians;
	}

	public BigDecimal getAttendanceRate() {
		return attendanceRate;
	}

	public void setAttendanceRate(final BigDecimal attendanceRate) {
		this.attendanceRate = attendanceRate;
	}

	public Long getDocumentsProduced() {
		return documentsProduced;
	}

	public void setDocumentsProduced(final Long documentsProduced) {
		this.documentsProduced = documentsProduced;
	}

	public BigDecimal getBaselineBallots() {
		return baselineBallots;
	}

	public void setBaselineBallots(final BigDecimal baselineBallots) {
		this.baselineBallots = baselineBallots;
	}

	public BigDecimal getBaselineDocs() {
		return baselineDocs;
	}

	public void setBaselineDocs(final BigDecimal baselineDocs) {
		this.baselineDocs = baselineDocs;
	}

	public BigDecimal getBaselineAttendance() {
		return baselineAttendance;
	}

	public void setBaselineAttendance(final BigDecimal baselineAttendance) {
		this.baselineAttendance = baselineAttendance;
	}

	public BigDecimal getBallotDeviationFromBaseline() {
		return ballotDeviationFromBaseline;
	}

	public void setBallotDeviationFromBaseline(final BigDecimal ballotDeviationFromBaseline) {
		this.ballotDeviationFromBaseline = ballotDeviationFromBaseline;
	}

	public BigDecimal getDocDeviationFromBaseline() {
		return docDeviationFromBaseline;
	}

	public void setDocDeviationFromBaseline(final BigDecimal docDeviationFromBaseline) {
		this.docDeviationFromBaseline = docDeviationFromBaseline;
	}

	public BigDecimal getAttendanceDeviationFromBaseline() {
		return attendanceDeviationFromBaseline;
	}

	public void setAttendanceDeviationFromBaseline(final BigDecimal attendanceDeviationFromBaseline) {
		this.attendanceDeviationFromBaseline = attendanceDeviationFromBaseline;
	}

	public BigDecimal getBallotPercentChange() {
		return ballotPercentChange;
	}

	public void setBallotPercentChange(final BigDecimal ballotPercentChange) {
		this.ballotPercentChange = ballotPercentChange;
	}

	public BigDecimal getDocPercentChange() {
		return docPercentChange;
	}

	public void setDocPercentChange(final BigDecimal docPercentChange) {
		this.docPercentChange = docPercentChange;
	}

	public String getQ4Pattern() {
		return q4Pattern;
	}

	public void setQ4Pattern(final String q4Pattern) {
		this.q4Pattern = q4Pattern;
	}

	public BigDecimal getBallotZScore() {
		return ballotZScore;
	}

	public void setBallotZScore(final BigDecimal ballotZScore) {
		this.ballotZScore = ballotZScore;
	}

	public BigDecimal getDocZScore() {
		return docZScore;
	}

	public void setDocZScore(final BigDecimal docZScore) {
		this.docZScore = docZScore;
	}

	public BigDecimal getAttendanceZScore() {
		return attendanceZScore;
	}

	public void setAttendanceZScore(final BigDecimal attendanceZScore) {
		this.attendanceZScore = attendanceZScore;
	}

	public String getActivityClassification() {
		return activityClassification;
	}

	public void setActivityClassification(final String activityClassification) {
		this.activityClassification = activityClassification;
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
