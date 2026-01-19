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
 * The Class ViewRiksdagenElectionYearAnomalies.
 * Database view for election year anomaly detection with z-score thresholds (|z| > 1.5).
 * Filters election years only with statistically unusual patterns.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_election_year_anomalies")
public class ViewRiksdagenElectionYearAnomalies implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "year", nullable = false)
	private Integer year;

	@Column(name = "total_ballots")
	private Long totalBallots;

	@Column(name = "documents_produced")
	private Long documentsProduced;

	@Column(name = "motions_filed")
	private Long motionsFiled;

	@Column(name = "proposals_filed")
	private Long proposalsFiled;

	@Column(name = "attendance_rate")
	private BigDecimal attendanceRate;

	@Column(name = "ballot_z_score_vs_election_avg")
	private BigDecimal ballotZScoreVsElectionAvg;

	@Column(name = "doc_z_score_vs_election_avg")
	private BigDecimal docZScoreVsElectionAvg;

	@Column(name = "motion_z_score")
	private BigDecimal motionZScore;

	@Column(name = "year_classification")
	private String yearClassification;

	@Column(name = "composite_classification")
	private String compositeClassification;

	@Column(name = "yoy_ballot_change_pct")
	private BigDecimal yoyBallotChangePct;

	@Column(name = "has_ballot_anomaly")
	private Boolean hasBallotAnomaly;

	@Column(name = "has_doc_anomaly")
	private Boolean hasDocAnomaly;

	@Column(name = "has_motion_anomaly")
	private Boolean hasMotionAnomaly;

	@Column(name = "anomaly_count")
	private Integer anomalyCount;

	@Column(name = "anomaly_types")
	private String anomalyTypes;

	@Column(name = "anomaly_severity")
	private String anomalySeverity;

	@Column(name = "max_z_score")
	private BigDecimal maxZScore;

	@Column(name = "anomaly_direction")
	private String anomalyDirection;

	@Column(name = "election_avg_ballots")
	private BigDecimal electionAvgBallots;

	@Column(name = "election_stddev_ballots")
	private BigDecimal electionStddevBallots;

	@Column(name = "election_avg_docs")
	private BigDecimal electionAvgDocs;

	@Column(name = "election_stddev_docs")
	private BigDecimal electionStddevDocs;

	@Column(name = "election_avg_motions")
	private BigDecimal electionAvgMotions;

	@Column(name = "election_stddev_motions")
	private BigDecimal electionStddevMotions;

	/**
	 * Instantiates a new view riksdagen election year anomalies.
	 */
	public ViewRiksdagenElectionYearAnomalies() {
		super();
	}

	// Getters and setters

	public Integer getYear() {
		return year;
	}

	public void setYear(final Integer year) {
		this.year = year;
	}

	public Long getTotalBallots() {
		return totalBallots;
	}

	public void setTotalBallots(final Long totalBallots) {
		this.totalBallots = totalBallots;
	}

	public Long getDocumentsProduced() {
		return documentsProduced;
	}

	public void setDocumentsProduced(final Long documentsProduced) {
		this.documentsProduced = documentsProduced;
	}

	public Long getMotionsFiled() {
		return motionsFiled;
	}

	public void setMotionsFiled(final Long motionsFiled) {
		this.motionsFiled = motionsFiled;
	}

	public Long getProposalsFiled() {
		return proposalsFiled;
	}

	public void setProposalsFiled(final Long proposalsFiled) {
		this.proposalsFiled = proposalsFiled;
	}

	public BigDecimal getAttendanceRate() {
		return attendanceRate;
	}

	public void setAttendanceRate(final BigDecimal attendanceRate) {
		this.attendanceRate = attendanceRate;
	}

	public BigDecimal getBallotZScoreVsElectionAvg() {
		return ballotZScoreVsElectionAvg;
	}

	public void setBallotZScoreVsElectionAvg(final BigDecimal ballotZScoreVsElectionAvg) {
		this.ballotZScoreVsElectionAvg = ballotZScoreVsElectionAvg;
	}

	public BigDecimal getDocZScoreVsElectionAvg() {
		return docZScoreVsElectionAvg;
	}

	public void setDocZScoreVsElectionAvg(final BigDecimal docZScoreVsElectionAvg) {
		this.docZScoreVsElectionAvg = docZScoreVsElectionAvg;
	}

	public BigDecimal getMotionZScore() {
		return motionZScore;
	}

	public void setMotionZScore(final BigDecimal motionZScore) {
		this.motionZScore = motionZScore;
	}

	public String getYearClassification() {
		return yearClassification;
	}

	public void setYearClassification(final String yearClassification) {
		this.yearClassification = yearClassification;
	}

	public String getCompositeClassification() {
		return compositeClassification;
	}

	public void setCompositeClassification(final String compositeClassification) {
		this.compositeClassification = compositeClassification;
	}

	public BigDecimal getYoyBallotChangePct() {
		return yoyBallotChangePct;
	}

	public void setYoyBallotChangePct(final BigDecimal yoyBallotChangePct) {
		this.yoyBallotChangePct = yoyBallotChangePct;
	}

	public Boolean getHasBallotAnomaly() {
		return hasBallotAnomaly;
	}

	public void setHasBallotAnomaly(final Boolean hasBallotAnomaly) {
		this.hasBallotAnomaly = hasBallotAnomaly;
	}

	public Boolean getHasDocAnomaly() {
		return hasDocAnomaly;
	}

	public void setHasDocAnomaly(final Boolean hasDocAnomaly) {
		this.hasDocAnomaly = hasDocAnomaly;
	}

	public Boolean getHasMotionAnomaly() {
		return hasMotionAnomaly;
	}

	public void setHasMotionAnomaly(final Boolean hasMotionAnomaly) {
		this.hasMotionAnomaly = hasMotionAnomaly;
	}

	public Integer getAnomalyCount() {
		return anomalyCount;
	}

	public void setAnomalyCount(final Integer anomalyCount) {
		this.anomalyCount = anomalyCount;
	}

	public String getAnomalyTypes() {
		return anomalyTypes;
	}

	public void setAnomalyTypes(final String anomalyTypes) {
		this.anomalyTypes = anomalyTypes;
	}

	public String getAnomalySeverity() {
		return anomalySeverity;
	}

	public void setAnomalySeverity(final String anomalySeverity) {
		this.anomalySeverity = anomalySeverity;
	}

	public BigDecimal getMaxZScore() {
		return maxZScore;
	}

	public void setMaxZScore(final BigDecimal maxZScore) {
		this.maxZScore = maxZScore;
	}

	public String getAnomalyDirection() {
		return anomalyDirection;
	}

	public void setAnomalyDirection(final String anomalyDirection) {
		this.anomalyDirection = anomalyDirection;
	}

	public BigDecimal getElectionAvgBallots() {
		return electionAvgBallots;
	}

	public void setElectionAvgBallots(final BigDecimal electionAvgBallots) {
		this.electionAvgBallots = electionAvgBallots;
	}

	public BigDecimal getElectionStddevBallots() {
		return electionStddevBallots;
	}

	public void setElectionStddevBallots(final BigDecimal electionStddevBallots) {
		this.electionStddevBallots = electionStddevBallots;
	}

	public BigDecimal getElectionAvgDocs() {
		return electionAvgDocs;
	}

	public void setElectionAvgDocs(final BigDecimal electionAvgDocs) {
		this.electionAvgDocs = electionAvgDocs;
	}

	public BigDecimal getElectionStddevDocs() {
		return electionStddevDocs;
	}

	public void setElectionStddevDocs(final BigDecimal electionStddevDocs) {
		this.electionStddevDocs = electionStddevDocs;
	}

	public BigDecimal getElectionAvgMotions() {
		return electionAvgMotions;
	}

	public void setElectionAvgMotions(final BigDecimal electionAvgMotions) {
		this.electionAvgMotions = electionAvgMotions;
	}

	public BigDecimal getElectionStddevMotions() {
		return electionStddevMotions;
	}

	public void setElectionStddevMotions(final BigDecimal electionStddevMotions) {
		this.electionStddevMotions = electionStddevMotions;
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
