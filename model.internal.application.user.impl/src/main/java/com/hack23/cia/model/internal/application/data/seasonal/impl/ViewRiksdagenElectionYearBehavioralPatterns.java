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
 * The Class ViewRiksdagenElectionYearBehavioralPatterns.
 * Database view for election year behavioral pattern analysis comparing
 * 7 election years (2002-2026) vs midterm years with statistical baselines.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_election_year_behavioral_patterns")
public class ViewRiksdagenElectionYearBehavioralPatterns implements Serializable {

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

	@Column(name = "avg_yes_rate")
	private BigDecimal avgYesRate;

	@Column(name = "avg_no_rate")
	private BigDecimal avgNoRate;

	@Column(name = "avg_abstain_rate")
	private BigDecimal avgAbstainRate;

	@Column(name = "documents_produced")
	private Long documentsProduced;

	@Column(name = "motions_filed")
	private Long motionsFiled;

	@Column(name = "proposals_filed")
	private Long proposalsFiled;

	@Column(name = "election_median_ballots")
	private BigDecimal electionMedianBallots;

	@Column(name = "election_avg_ballots")
	private BigDecimal electionAvgBallots;

	@Column(name = "election_stddev_ballots")
	private BigDecimal electionStddevBallots;

	@Column(name = "midterm_avg_ballots")
	private BigDecimal midtermAvgBallots;

	@Column(name = "midterm_stddev_ballots")
	private BigDecimal midtermStddevBallots;

	@Column(name = "ballot_ratio_vs_midterm")
	private BigDecimal ballotRatioVsMidterm;

	@Column(name = "ballot_ratio_vs_election_avg")
	private BigDecimal ballotRatioVsElectionAvg;

	@Column(name = "election_median_docs")
	private BigDecimal electionMedianDocs;

	@Column(name = "election_avg_docs")
	private BigDecimal electionAvgDocs;

	@Column(name = "election_stddev_docs")
	private BigDecimal electionStddevDocs;

	@Column(name = "midterm_avg_docs")
	private BigDecimal midtermAvgDocs;

	@Column(name = "doc_ratio_vs_midterm")
	private BigDecimal docRatioVsMidterm;

	@Column(name = "doc_ratio_vs_election_avg")
	private BigDecimal docRatioVsElectionAvg;

	@Column(name = "election_median_motions")
	private BigDecimal electionMedianMotions;

	@Column(name = "election_avg_motions")
	private BigDecimal electionAvgMotions;

	@Column(name = "election_stddev_motions")
	private BigDecimal electionStddevMotions;

	@Column(name = "midterm_avg_motions")
	private BigDecimal midtermAvgMotions;

	@Column(name = "motion_ratio_vs_midterm")
	private BigDecimal motionRatioVsMidterm;

	@Column(name = "election_avg_attendance")
	private BigDecimal electionAvgAttendance;

	@Column(name = "midterm_avg_attendance")
	private BigDecimal midtermAvgAttendance;

	@Column(name = "ballot_z_score_vs_election_avg")
	private BigDecimal ballotZScoreVsElectionAvg;

	@Column(name = "doc_z_score_vs_election_avg")
	private BigDecimal docZScoreVsElectionAvg;

	@Column(name = "year_classification")
	private String yearClassification;

	@Column(name = "composite_classification")
	private String compositeClassification;

	@Column(name = "prev_year_ballots")
	private Long prevYearBallots;

	@Column(name = "yoy_ballot_change_pct")
	private BigDecimal yoyBallotChangePct;

	/**
	 * Instantiates a new view riksdagen election year behavioral patterns.
	 */
	public ViewRiksdagenElectionYearBehavioralPatterns() {
		super();
	}

	// Getters and setters

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

	public BigDecimal getAvgYesRate() {
		return avgYesRate;
	}

	public void setAvgYesRate(final BigDecimal avgYesRate) {
		this.avgYesRate = avgYesRate;
	}

	public BigDecimal getAvgNoRate() {
		return avgNoRate;
	}

	public void setAvgNoRate(final BigDecimal avgNoRate) {
		this.avgNoRate = avgNoRate;
	}

	public BigDecimal getAvgAbstainRate() {
		return avgAbstainRate;
	}

	public void setAvgAbstainRate(final BigDecimal avgAbstainRate) {
		this.avgAbstainRate = avgAbstainRate;
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

	public BigDecimal getElectionMedianBallots() {
		return electionMedianBallots;
	}

	public void setElectionMedianBallots(final BigDecimal electionMedianBallots) {
		this.electionMedianBallots = electionMedianBallots;
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

	public BigDecimal getMidtermAvgBallots() {
		return midtermAvgBallots;
	}

	public void setMidtermAvgBallots(final BigDecimal midtermAvgBallots) {
		this.midtermAvgBallots = midtermAvgBallots;
	}

	public BigDecimal getMidtermStddevBallots() {
		return midtermStddevBallots;
	}

	public void setMidtermStddevBallots(final BigDecimal midtermStddevBallots) {
		this.midtermStddevBallots = midtermStddevBallots;
	}

	public BigDecimal getBallotRatioVsMidterm() {
		return ballotRatioVsMidterm;
	}

	public void setBallotRatioVsMidterm(final BigDecimal ballotRatioVsMidterm) {
		this.ballotRatioVsMidterm = ballotRatioVsMidterm;
	}

	public BigDecimal getBallotRatioVsElectionAvg() {
		return ballotRatioVsElectionAvg;
	}

	public void setBallotRatioVsElectionAvg(final BigDecimal ballotRatioVsElectionAvg) {
		this.ballotRatioVsElectionAvg = ballotRatioVsElectionAvg;
	}

	public BigDecimal getElectionMedianDocs() {
		return electionMedianDocs;
	}

	public void setElectionMedianDocs(final BigDecimal electionMedianDocs) {
		this.electionMedianDocs = electionMedianDocs;
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

	public BigDecimal getMidtermAvgDocs() {
		return midtermAvgDocs;
	}

	public void setMidtermAvgDocs(final BigDecimal midtermAvgDocs) {
		this.midtermAvgDocs = midtermAvgDocs;
	}

	public BigDecimal getDocRatioVsMidterm() {
		return docRatioVsMidterm;
	}

	public void setDocRatioVsMidterm(final BigDecimal docRatioVsMidterm) {
		this.docRatioVsMidterm = docRatioVsMidterm;
	}

	public BigDecimal getDocRatioVsElectionAvg() {
		return docRatioVsElectionAvg;
	}

	public void setDocRatioVsElectionAvg(final BigDecimal docRatioVsElectionAvg) {
		this.docRatioVsElectionAvg = docRatioVsElectionAvg;
	}

	public BigDecimal getElectionMedianMotions() {
		return electionMedianMotions;
	}

	public void setElectionMedianMotions(final BigDecimal electionMedianMotions) {
		this.electionMedianMotions = electionMedianMotions;
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

	public BigDecimal getMidtermAvgMotions() {
		return midtermAvgMotions;
	}

	public void setMidtermAvgMotions(final BigDecimal midtermAvgMotions) {
		this.midtermAvgMotions = midtermAvgMotions;
	}

	public BigDecimal getMotionRatioVsMidterm() {
		return motionRatioVsMidterm;
	}

	public void setMotionRatioVsMidterm(final BigDecimal motionRatioVsMidterm) {
		this.motionRatioVsMidterm = motionRatioVsMidterm;
	}

	public BigDecimal getElectionAvgAttendance() {
		return electionAvgAttendance;
	}

	public void setElectionAvgAttendance(final BigDecimal electionAvgAttendance) {
		this.electionAvgAttendance = electionAvgAttendance;
	}

	public BigDecimal getMidtermAvgAttendance() {
		return midtermAvgAttendance;
	}

	public void setMidtermAvgAttendance(final BigDecimal midtermAvgAttendance) {
		this.midtermAvgAttendance = midtermAvgAttendance;
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

	public Long getPrevYearBallots() {
		return prevYearBallots;
	}

	public void setPrevYearBallots(final Long prevYearBallots) {
		this.prevYearBallots = prevYearBallots;
	}

	public BigDecimal getYoyBallotChangePct() {
		return yoyBallotChangePct;
	}

	public void setYoyBallotChangePct(final BigDecimal yoyBallotChangePct) {
		this.yoyBallotChangePct = yoyBallotChangePct;
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
