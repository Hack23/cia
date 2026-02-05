/*
 * Copyright 2010-2025 James Pether Sörling
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
package com.hack23.cia.model.internal.application.data.politician.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

/**
 * The Class ViewRiksdagenSeasonalActivityPatterns.
 * Database view for seasonal pattern analysis identifying Q1-Q4 behavior shifts across election cycles.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_seasonal_activity_patterns")
public class ViewRiksdagenSeasonalActivityPatterns implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ViewRiksdagenSeasonalActivityPatternsEmbeddedId embeddedId;

	@Column(name = "is_election_year")
	private Boolean isElectionYear;

	@Column(name = "election_cycle")
	private String electionCycle;

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

	@Column(name = "base_activity_classification")
	private String baseActivityClassification;

	@Column(name = "prev_quarter_ballots")
	private Long prevQuarterBallots;

	@Column(name = "next_quarter_ballots")
	private Long nextQuarterBallots;

	@Column(name = "prev_quarter_attendance")
	private BigDecimal prevQuarterAttendance;

	@Column(name = "prev_quarter_documents")
	private Long prevQuarterDocuments;

	@Column(name = "cross_year_quarter_avg_ballots")
	private BigDecimal crossYearQuarterAvgBallots;

	@Column(name = "cross_year_quarter_stddev_ballots")
	private BigDecimal crossYearQuarterStddevBallots;

	@Column(name = "cross_year_quarter_avg_docs")
	private BigDecimal crossYearQuarterAvgDocs;

	@Column(name = "activity_quartile_cycle")
	private Integer activityQuartileCycle;

	@Column(name = "qoq_ballot_change_pct")
	private BigDecimal qoqBallotChangePct;

	@Column(name = "deviation_from_typical_quarter")
	private BigDecimal deviationFromTypicalQuarter;

	@Column(name = "cross_year_z_score")
	private BigDecimal crossYearZScore;

	@Column(name = "quarter_label")
	private String quarterLabel;

	@Column(name = "parliamentary_period")
	private String parliamentaryPeriod;

	@Column(name = "seasonal_pattern_classification")
	private String seasonalPatternClassification;

	/**
	 * Instantiates a new view riksdagen seasonal activity patterns.
	 */
	public ViewRiksdagenSeasonalActivityPatterns() {
		super();
	}

	/**
	 * Gets the embedded id.
	 *
	 * @return the embedded id
	 */
	public ViewRiksdagenSeasonalActivityPatternsEmbeddedId getEmbeddedId() {
		return embeddedId;
	}

	/**
	 * Sets the embedded id.
	 *
	 * @param embeddedId the new embedded id
	 */
	public void setEmbeddedId(final ViewRiksdagenSeasonalActivityPatternsEmbeddedId embeddedId) {
		this.embeddedId = embeddedId;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public Integer getYear() {
		return embeddedId != null ? embeddedId.getYear() : null;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(final Integer year) {
		if (this.embeddedId == null) {
			this.embeddedId = new ViewRiksdagenSeasonalActivityPatternsEmbeddedId();
		}
		this.embeddedId.setYear(year);
	}

	/**
	 * Gets the quarter.
	 *
	 * @return the quarter
	 */
	public Integer getQuarter() {
		return embeddedId != null ? embeddedId.getQuarter() : null;
	}

	/**
	 * Sets the quarter.
	 *
	 * @param quarter the new quarter
	 */
	public void setQuarter(final Integer quarter) {
		if (this.embeddedId == null) {
			this.embeddedId = new ViewRiksdagenSeasonalActivityPatternsEmbeddedId();
		}
		this.embeddedId.setQuarter(quarter);
	}

	/**
	 * Gets the is election year.
	 *
	 * @return the is election year
	 */
	public Boolean getIsElectionYear() {
		return isElectionYear;
	}

	/**
	 * Sets the is election year.
	 *
	 * @param isElectionYear the new is election year
	 */
	public void setIsElectionYear(final Boolean isElectionYear) {
		this.isElectionYear = isElectionYear;
	}

	/**
	 * Gets the election cycle.
	 *
	 * @return the election cycle
	 */
	public String getElectionCycle() {
		return electionCycle;
	}

	/**
	 * Sets the election cycle.
	 *
	 * @param electionCycle the new election cycle
	 */
	public void setElectionCycle(final String electionCycle) {
		this.electionCycle = electionCycle;
	}

	/**
	 * Gets the total ballots.
	 *
	 * @return the total ballots
	 */
	public Long getTotalBallots() {
		return totalBallots;
	}

	/**
	 * Sets the total ballots.
	 *
	 * @param totalBallots the new total ballots
	 */
	public void setTotalBallots(final Long totalBallots) {
		this.totalBallots = totalBallots;
	}

	/**
	 * Gets the active politicians.
	 *
	 * @return the active politicians
	 */
	public Long getActivePoliticians() {
		return activePoliticians;
	}

	/**
	 * Sets the active politicians.
	 *
	 * @param activePoliticians the new active politicians
	 */
	public void setActivePoliticians(final Long activePoliticians) {
		this.activePoliticians = activePoliticians;
	}

	/**
	 * Gets the attendance rate.
	 *
	 * @return the attendance rate
	 */
	public BigDecimal getAttendanceRate() {
		return attendanceRate;
	}

	/**
	 * Sets the attendance rate.
	 *
	 * @param attendanceRate the new attendance rate
	 */
	public void setAttendanceRate(final BigDecimal attendanceRate) {
		this.attendanceRate = attendanceRate;
	}

	/**
	 * Gets the documents produced.
	 *
	 * @return the documents produced
	 */
	public Long getDocumentsProduced() {
		return documentsProduced;
	}

	/**
	 * Sets the documents produced.
	 *
	 * @param documentsProduced the new documents produced
	 */
	public void setDocumentsProduced(final Long documentsProduced) {
		this.documentsProduced = documentsProduced;
	}

	/**
	 * Gets the q baseline ballots.
	 *
	 * @return the q baseline ballots
	 */
	public BigDecimal getqBaselineBallots() {
		return qBaselineBallots;
	}

	/**
	 * Sets the q baseline ballots.
	 *
	 * @param qBaselineBallots the new q baseline ballots
	 */
	public void setqBaselineBallots(final BigDecimal qBaselineBallots) {
		this.qBaselineBallots = qBaselineBallots;
	}

	/**
	 * Gets the q stddev ballots.
	 *
	 * @return the q stddev ballots
	 */
	public BigDecimal getqStddevBallots() {
		return qStddevBallots;
	}

	/**
	 * Sets the q stddev ballots.
	 *
	 * @param qStddevBallots the new q stddev ballots
	 */
	public void setqStddevBallots(final BigDecimal qStddevBallots) {
		this.qStddevBallots = qStddevBallots;
	}

	/**
	 * Gets the ballot z score.
	 *
	 * @return the ballot z score
	 */
	public BigDecimal getBallotZScore() {
		return ballotZScore;
	}

	/**
	 * Sets the ballot z score.
	 *
	 * @param ballotZScore the new ballot z score
	 */
	public void setBallotZScore(final BigDecimal ballotZScore) {
		this.ballotZScore = ballotZScore;
	}

	/**
	 * Gets the q baseline docs.
	 *
	 * @return the q baseline docs
	 */
	public BigDecimal getqBaselineDocs() {
		return qBaselineDocs;
	}

	/**
	 * Sets the q baseline docs.
	 *
	 * @param qBaselineDocs the new q baseline docs
	 */
	public void setqBaselineDocs(final BigDecimal qBaselineDocs) {
		this.qBaselineDocs = qBaselineDocs;
	}

	/**
	 * Gets the q stddev docs.
	 *
	 * @return the q stddev docs
	 */
	public BigDecimal getqStddevDocs() {
		return qStddevDocs;
	}

	/**
	 * Sets the q stddev docs.
	 *
	 * @param qStddevDocs the new q stddev docs
	 */
	public void setqStddevDocs(final BigDecimal qStddevDocs) {
		this.qStddevDocs = qStddevDocs;
	}

	/**
	 * Gets the doc z score.
	 *
	 * @return the doc z score
	 */
	public BigDecimal getDocZScore() {
		return docZScore;
	}

	/**
	 * Sets the doc z score.
	 *
	 * @param docZScore the new doc z score
	 */
	public void setDocZScore(final BigDecimal docZScore) {
		this.docZScore = docZScore;
	}

	/**
	 * Gets the q baseline attendance.
	 *
	 * @return the q baseline attendance
	 */
	public BigDecimal getqBaselineAttendance() {
		return qBaselineAttendance;
	}

	/**
	 * Sets the q baseline attendance.
	 *
	 * @param qBaselineAttendance the new q baseline attendance
	 */
	public void setqBaselineAttendance(final BigDecimal qBaselineAttendance) {
		this.qBaselineAttendance = qBaselineAttendance;
	}

	/**
	 * Gets the q stddev attendance.
	 *
	 * @return the q stddev attendance
	 */
	public BigDecimal getqStddevAttendance() {
		return qStddevAttendance;
	}

	/**
	 * Sets the q stddev attendance.
	 *
	 * @param qStddevAttendance the new q stddev attendance
	 */
	public void setqStddevAttendance(final BigDecimal qStddevAttendance) {
		this.qStddevAttendance = qStddevAttendance;
	}

	/**
	 * Gets the attendance z score.
	 *
	 * @return the attendance z score
	 */
	public BigDecimal getAttendanceZScore() {
		return attendanceZScore;
	}

	/**
	 * Sets the attendance z score.
	 *
	 * @param attendanceZScore the new attendance z score
	 */
	public void setAttendanceZScore(final BigDecimal attendanceZScore) {
		this.attendanceZScore = attendanceZScore;
	}

	/**
	 * Gets the base activity classification.
	 *
	 * @return the base activity classification
	 */
	public String getBaseActivityClassification() {
		return baseActivityClassification;
	}

	/**
	 * Sets the base activity classification.
	 *
	 * @param baseActivityClassification the new base activity classification
	 */
	public void setBaseActivityClassification(final String baseActivityClassification) {
		this.baseActivityClassification = baseActivityClassification;
	}

	/**
	 * Gets the prev quarter ballots.
	 *
	 * @return the prev quarter ballots
	 */
	public Long getPrevQuarterBallots() {
		return prevQuarterBallots;
	}

	/**
	 * Sets the prev quarter ballots.
	 *
	 * @param prevQuarterBallots the new prev quarter ballots
	 */
	public void setPrevQuarterBallots(final Long prevQuarterBallots) {
		this.prevQuarterBallots = prevQuarterBallots;
	}

	/**
	 * Gets the next quarter ballots.
	 *
	 * @return the next quarter ballots
	 */
	public Long getNextQuarterBallots() {
		return nextQuarterBallots;
	}

	/**
	 * Sets the next quarter ballots.
	 *
	 * @param nextQuarterBallots the new next quarter ballots
	 */
	public void setNextQuarterBallots(final Long nextQuarterBallots) {
		this.nextQuarterBallots = nextQuarterBallots;
	}

	/**
	 * Gets the prev quarter attendance.
	 *
	 * @return the prev quarter attendance
	 */
	public BigDecimal getPrevQuarterAttendance() {
		return prevQuarterAttendance;
	}

	/**
	 * Sets the prev quarter attendance.
	 *
	 * @param prevQuarterAttendance the new prev quarter attendance
	 */
	public void setPrevQuarterAttendance(final BigDecimal prevQuarterAttendance) {
		this.prevQuarterAttendance = prevQuarterAttendance;
	}

	/**
	 * Gets the prev quarter documents.
	 *
	 * @return the prev quarter documents
	 */
	public Long getPrevQuarterDocuments() {
		return prevQuarterDocuments;
	}

	/**
	 * Sets the prev quarter documents.
	 *
	 * @param prevQuarterDocuments the new prev quarter documents
	 */
	public void setPrevQuarterDocuments(final Long prevQuarterDocuments) {
		this.prevQuarterDocuments = prevQuarterDocuments;
	}

	/**
	 * Gets the cross year quarter avg ballots.
	 *
	 * @return the cross year quarter avg ballots
	 */
	public BigDecimal getCrossYearQuarterAvgBallots() {
		return crossYearQuarterAvgBallots;
	}

	/**
	 * Sets the cross year quarter avg ballots.
	 *
	 * @param crossYearQuarterAvgBallots the new cross year quarter avg ballots
	 */
	public void setCrossYearQuarterAvgBallots(final BigDecimal crossYearQuarterAvgBallots) {
		this.crossYearQuarterAvgBallots = crossYearQuarterAvgBallots;
	}

	/**
	 * Gets the cross year quarter stddev ballots.
	 *
	 * @return the cross year quarter stddev ballots
	 */
	public BigDecimal getCrossYearQuarterStddevBallots() {
		return crossYearQuarterStddevBallots;
	}

	/**
	 * Sets the cross year quarter stddev ballots.
	 *
	 * @param crossYearQuarterStddevBallots the new cross year quarter stddev ballots
	 */
	public void setCrossYearQuarterStddevBallots(final BigDecimal crossYearQuarterStddevBallots) {
		this.crossYearQuarterStddevBallots = crossYearQuarterStddevBallots;
	}

	/**
	 * Gets the cross year quarter avg docs.
	 *
	 * @return the cross year quarter avg docs
	 */
	public BigDecimal getCrossYearQuarterAvgDocs() {
		return crossYearQuarterAvgDocs;
	}

	/**
	 * Sets the cross year quarter avg docs.
	 *
	 * @param crossYearQuarterAvgDocs the new cross year quarter avg docs
	 */
	public void setCrossYearQuarterAvgDocs(final BigDecimal crossYearQuarterAvgDocs) {
		this.crossYearQuarterAvgDocs = crossYearQuarterAvgDocs;
	}

	/**
	 * Gets the activity quartile cycle.
	 *
	 * @return the activity quartile cycle
	 */
	public Integer getActivityQuartileCycle() {
		return activityQuartileCycle;
	}

	/**
	 * Sets the activity quartile cycle.
	 *
	 * @param activityQuartileCycle the new activity quartile cycle
	 */
	public void setActivityQuartileCycle(final Integer activityQuartileCycle) {
		this.activityQuartileCycle = activityQuartileCycle;
	}

	/**
	 * Gets the qoq ballot change pct.
	 *
	 * @return the qoq ballot change pct
	 */
	public BigDecimal getQoqBallotChangePct() {
		return qoqBallotChangePct;
	}

	/**
	 * Sets the qoq ballot change pct.
	 *
	 * @param qoqBallotChangePct the new qoq ballot change pct
	 */
	public void setQoqBallotChangePct(final BigDecimal qoqBallotChangePct) {
		this.qoqBallotChangePct = qoqBallotChangePct;
	}

	/**
	 * Gets the deviation from typical quarter.
	 *
	 * @return the deviation from typical quarter
	 */
	public BigDecimal getDeviationFromTypicalQuarter() {
		return deviationFromTypicalQuarter;
	}

	/**
	 * Sets the deviation from typical quarter.
	 *
	 * @param deviationFromTypicalQuarter the new deviation from typical quarter
	 */
	public void setDeviationFromTypicalQuarter(final BigDecimal deviationFromTypicalQuarter) {
		this.deviationFromTypicalQuarter = deviationFromTypicalQuarter;
	}

	/**
	 * Gets the cross year z score.
	 *
	 * @return the cross year z score
	 */
	public BigDecimal getCrossYearZScore() {
		return crossYearZScore;
	}

	/**
	 * Sets the cross year z score.
	 *
	 * @param crossYearZScore the new cross year z score
	 */
	public void setCrossYearZScore(final BigDecimal crossYearZScore) {
		this.crossYearZScore = crossYearZScore;
	}

	/**
	 * Gets the quarter label.
	 *
	 * @return the quarter label
	 */
	public String getQuarterLabel() {
		return quarterLabel;
	}

	/**
	 * Sets the quarter label.
	 *
	 * @param quarterLabel the new quarter label
	 */
	public void setQuarterLabel(final String quarterLabel) {
		this.quarterLabel = quarterLabel;
	}

	/**
	 * Gets the parliamentary period.
	 *
	 * @return the parliamentary period
	 */
	public String getParliamentaryPeriod() {
		return parliamentaryPeriod;
	}

	/**
	 * Sets the parliamentary period.
	 *
	 * @param parliamentaryPeriod the new parliamentary period
	 */
	public void setParliamentaryPeriod(final String parliamentaryPeriod) {
		this.parliamentaryPeriod = parliamentaryPeriod;
	}

	/**
	 * Gets the seasonal pattern classification.
	 *
	 * @return the seasonal pattern classification
	 */
	public String getSeasonalPatternClassification() {
		return seasonalPatternClassification;
	}

	/**
	 * Sets the seasonal pattern classification.
	 *
	 * @param seasonalPatternClassification the new seasonal pattern classification
	 */
	public void setSeasonalPatternClassification(final String seasonalPatternClassification) {
		this.seasonalPatternClassification = seasonalPatternClassification;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final ViewRiksdagenSeasonalActivityPatterns that = (ViewRiksdagenSeasonalActivityPatterns) obj;
		return Objects.equals(getYear(), that.getYear()) &&
				Objects.equals(getQuarter(), that.getQuarter());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getYear(), getQuarter());
	}

	@Override
	public String toString() {
		return "ViewRiksdagenSeasonalActivityPatterns{" +
				"year=" + getYear() +
				", quarter=" + getQuarter() +
				", isElectionYear=" + isElectionYear +
				", electionCycle='" + electionCycle + '\'' +
				", totalBallots=" + totalBallots +
				", activePoliticians=" + activePoliticians +
				", quarterLabel='" + quarterLabel + '\'' +
				", parliamentaryPeriod='" + parliamentaryPeriod + '\'' +
				", seasonalPatternClassification='" + seasonalPatternClassification + '\'' +
				'}';
	}
}
