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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Immutable;

/**
 * The Class ViewRiksdagenSeasonalQuarterlyActivity.
 * Database view for seasonal quarterly activity analysis with z-score anomaly detection.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_seasonal_quarterly_activity")
public class ViewRiksdagenSeasonalQuarterlyActivity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ViewRiksdagenSeasonalQuarterlyActivityEmbeddedId embeddedId;

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

	@Column(name = "q_baseline_ballots")
	private BigDecimal qBaselineBallots;

	@Column(name = "q_stddev_ballots")
	private BigDecimal qStddevBallots;

	@Column(name = "ballot_z_score")
	private BigDecimal ballotZScore;

	@Column(name = "q_baseline_docs")
	private BigDecimal qBaselineDocs;

	@Column(name = "q_stddev_docs")
	private BigDecimal qStddevDocs;

	@Column(name = "doc_z_score")
	private BigDecimal docZScore;

	@Column(name = "q_baseline_attendance")
	private BigDecimal qBaselineAttendance;

	@Column(name = "q_stddev_attendance")
	private BigDecimal qStddevAttendance;

	@Column(name = "attendance_z_score")
	private BigDecimal attendanceZScore;

	@Column(name = "activity_classification")
	private String activityClassification;

	/**
	 * Instantiates a new view riksdagen seasonal quarterly activity.
	 */
	public ViewRiksdagenSeasonalQuarterlyActivity() {
		super();
	}

	public ViewRiksdagenSeasonalQuarterlyActivityEmbeddedId getEmbeddedId() {
		return embeddedId;
	}

	public void setEmbeddedId(final ViewRiksdagenSeasonalQuarterlyActivityEmbeddedId embeddedId) {
		this.embeddedId = embeddedId;
	}

	public Integer getYear() {
		return embeddedId != null ? embeddedId.getYear() : null;
	}

	public Integer getQuarter() {
		return embeddedId != null ? embeddedId.getQuarter() : null;
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

	public BigDecimal getqBaselineBallots() {
		return qBaselineBallots;
	}

	public void setqBaselineBallots(final BigDecimal qBaselineBallots) {
		this.qBaselineBallots = qBaselineBallots;
	}

	public BigDecimal getqStddevBallots() {
		return qStddevBallots;
	}

	public void setqStddevBallots(final BigDecimal qStddevBallots) {
		this.qStddevBallots = qStddevBallots;
	}

	public BigDecimal getBallotZScore() {
		return ballotZScore;
	}

	public void setBallotZScore(final BigDecimal ballotZScore) {
		this.ballotZScore = ballotZScore;
	}

	public BigDecimal getqBaselineDocs() {
		return qBaselineDocs;
	}

	public void setqBaselineDocs(final BigDecimal qBaselineDocs) {
		this.qBaselineDocs = qBaselineDocs;
	}

	public BigDecimal getqStddevDocs() {
		return qStddevDocs;
	}

	public void setqStddevDocs(final BigDecimal qStddevDocs) {
		this.qStddevDocs = qStddevDocs;
	}

	public BigDecimal getDocZScore() {
		return docZScore;
	}

	public void setDocZScore(final BigDecimal docZScore) {
		this.docZScore = docZScore;
	}

	public BigDecimal getqBaselineAttendance() {
		return qBaselineAttendance;
	}

	public void setqBaselineAttendance(final BigDecimal qBaselineAttendance) {
		this.qBaselineAttendance = qBaselineAttendance;
	}

	public BigDecimal getqStddevAttendance() {
		return qStddevAttendance;
	}

	public void setqStddevAttendance(final BigDecimal qStddevAttendance) {
		this.qStddevAttendance = qStddevAttendance;
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
