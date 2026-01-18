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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

/**
 * The Class ViewRiksdagenPreElectionQuarterlyActivity.
 * Database view for aggregate Q4 activity analysis comparing election years vs non-election years.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_pre_election_quarterly_activity")
public class ViewRiksdagenPreElectionQuarterlyActivity implements Serializable {

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

	@Column(name = "avg_attendance_rate")
	private BigDecimal avgAttendanceRate;

	@Column(name = "total_votes")
	private Long totalVotes;

	@Column(name = "avg_win_rate")
	private BigDecimal avgWinRate;

	@Column(name = "avg_rebel_rate")
	private BigDecimal avgRebelRate;

	@Column(name = "total_documents")
	private Long totalDocuments;

	@Column(name = "active_document_authors")
	private Long activeDocumentAuthors;

	@Column(name = "total_proposals")
	private Long totalProposals;

	@Column(name = "total_motions")
	private Long totalMotions;

	@Column(name = "total_new_assignments")
	private Long totalNewAssignments;

	@Column(name = "politicians_with_new_roles")
	private Long politiciansWithNewRoles;

	@Column(name = "leadership_appointments")
	private Long leadershipAppointments;

	@Column(name = "active_parties")
	private Long activeParties;

	@Column(name = "avg_party_win_rate")
	private BigDecimal avgPartyWinRate;

	@Column(name = "avg_party_absence_rate")
	private BigDecimal avgPartyAbsenceRate;

	@Column(name = "party_documents_total")
	private Long partyDocumentsTotal;

	@Column(name = "ma_party_win_rate")
	private BigDecimal maPartyWinRate;

	@Column(name = "high_performing_parties")
	private Long highPerformingParties;

	@Column(name = "baseline_ballots")
	private BigDecimal baselineBallots;

	@Column(name = "stddev_ballots")
	private BigDecimal stddevBallots;

	@Column(name = "baseline_documents")
	private BigDecimal baselineDocuments;

	@Column(name = "baseline_assignments")
	private BigDecimal baselineAssignments;

	@Column(name = "ballot_deviation_from_baseline")
	private BigDecimal ballotDeviationFromBaseline;

	@Column(name = "document_deviation_from_baseline")
	private BigDecimal documentDeviationFromBaseline;

	@Column(name = "assignment_deviation_from_baseline")
	private BigDecimal assignmentDeviationFromBaseline;

	@Column(name = "ballot_percent_change_from_baseline")
	private BigDecimal ballotPercentChangeFromBaseline;

	@Column(name = "document_percent_change_from_baseline")
	private BigDecimal documentPercentChangeFromBaseline;

	@Column(name = "baseline_attendance")
	private BigDecimal baselineAttendance;

	@Column(name = "stddev_attendance")
	private BigDecimal stddevAttendance;

	@Column(name = "attendance_deviation_from_baseline")
	private BigDecimal attendanceDeviationFromBaseline;

	@Column(name = "ballot_z_score")
	private BigDecimal ballotZScore;

	@Column(name = "document_z_score")
	private BigDecimal documentZScore;

	@Column(name = "q4_activity_classification")
	private String q4ActivityClassification;

	@Column(name = "prev_year_ballots")
	private Long prevYearBallots;

	@Column(name = "yoy_ballot_change_pct")
	private BigDecimal yoyBallotChangePct;

	@Column(name = "rank_by_q4_activity")
	private Long rankByQ4Activity;

	/**
	 * Instantiates a new view riksdagen pre election quarterly activity.
	 */
	public ViewRiksdagenPreElectionQuarterlyActivity() {
		super();
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(final Integer year) {
		this.year = year;
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
	 * Gets the avg attendance rate.
	 *
	 * @return the avg attendance rate
	 */
	public BigDecimal getAvgAttendanceRate() {
		return avgAttendanceRate;
	}

	/**
	 * Sets the avg attendance rate.
	 *
	 * @param avgAttendanceRate the new avg attendance rate
	 */
	public void setAvgAttendanceRate(final BigDecimal avgAttendanceRate) {
		this.avgAttendanceRate = avgAttendanceRate;
	}

	/**
	 * Gets the total votes.
	 *
	 * @return the total votes
	 */
	public Long getTotalVotes() {
		return totalVotes;
	}

	/**
	 * Sets the total votes.
	 *
	 * @param totalVotes the new total votes
	 */
	public void setTotalVotes(final Long totalVotes) {
		this.totalVotes = totalVotes;
	}

	/**
	 * Gets the avg win rate.
	 *
	 * @return the avg win rate
	 */
	public BigDecimal getAvgWinRate() {
		return avgWinRate;
	}

	/**
	 * Sets the avg win rate.
	 *
	 * @param avgWinRate the new avg win rate
	 */
	public void setAvgWinRate(final BigDecimal avgWinRate) {
		this.avgWinRate = avgWinRate;
	}

	/**
	 * Gets the avg rebel rate.
	 *
	 * @return the avg rebel rate
	 */
	public BigDecimal getAvgRebelRate() {
		return avgRebelRate;
	}

	/**
	 * Sets the avg rebel rate.
	 *
	 * @param avgRebelRate the new avg rebel rate
	 */
	public void setAvgRebelRate(final BigDecimal avgRebelRate) {
		this.avgRebelRate = avgRebelRate;
	}

	/**
	 * Gets the total documents.
	 *
	 * @return the total documents
	 */
	public Long getTotalDocuments() {
		return totalDocuments;
	}

	/**
	 * Sets the total documents.
	 *
	 * @param totalDocuments the new total documents
	 */
	public void setTotalDocuments(final Long totalDocuments) {
		this.totalDocuments = totalDocuments;
	}

	/**
	 * Gets the active document authors.
	 *
	 * @return the active document authors
	 */
	public Long getActiveDocumentAuthors() {
		return activeDocumentAuthors;
	}

	/**
	 * Sets the active document authors.
	 *
	 * @param activeDocumentAuthors the new active document authors
	 */
	public void setActiveDocumentAuthors(final Long activeDocumentAuthors) {
		this.activeDocumentAuthors = activeDocumentAuthors;
	}

	/**
	 * Gets the total proposals.
	 *
	 * @return the total proposals
	 */
	public Long getTotalProposals() {
		return totalProposals;
	}

	/**
	 * Sets the total proposals.
	 *
	 * @param totalProposals the new total proposals
	 */
	public void setTotalProposals(final Long totalProposals) {
		this.totalProposals = totalProposals;
	}

	/**
	 * Gets the total motions.
	 *
	 * @return the total motions
	 */
	public Long getTotalMotions() {
		return totalMotions;
	}

	/**
	 * Sets the total motions.
	 *
	 * @param totalMotions the new total motions
	 */
	public void setTotalMotions(final Long totalMotions) {
		this.totalMotions = totalMotions;
	}

	/**
	 * Gets the total new assignments.
	 *
	 * @return the total new assignments
	 */
	public Long getTotalNewAssignments() {
		return totalNewAssignments;
	}

	/**
	 * Sets the total new assignments.
	 *
	 * @param totalNewAssignments the new total new assignments
	 */
	public void setTotalNewAssignments(final Long totalNewAssignments) {
		this.totalNewAssignments = totalNewAssignments;
	}

	/**
	 * Gets the politicians with new roles.
	 *
	 * @return the politicians with new roles
	 */
	public Long getPoliticiansWithNewRoles() {
		return politiciansWithNewRoles;
	}

	/**
	 * Sets the politicians with new roles.
	 *
	 * @param politiciansWithNewRoles the new politicians with new roles
	 */
	public void setPoliticiansWithNewRoles(final Long politiciansWithNewRoles) {
		this.politiciansWithNewRoles = politiciansWithNewRoles;
	}

	/**
	 * Gets the leadership appointments.
	 *
	 * @return the leadership appointments
	 */
	public Long getLeadershipAppointments() {
		return leadershipAppointments;
	}

	/**
	 * Sets the leadership appointments.
	 *
	 * @param leadershipAppointments the new leadership appointments
	 */
	public void setLeadershipAppointments(final Long leadershipAppointments) {
		this.leadershipAppointments = leadershipAppointments;
	}

	/**
	 * Gets the active parties.
	 *
	 * @return the active parties
	 */
	public Long getActiveParties() {
		return activeParties;
	}

	/**
	 * Sets the active parties.
	 *
	 * @param activeParties the new active parties
	 */
	public void setActiveParties(final Long activeParties) {
		this.activeParties = activeParties;
	}

	/**
	 * Gets the avg party win rate.
	 *
	 * @return the avg party win rate
	 */
	public BigDecimal getAvgPartyWinRate() {
		return avgPartyWinRate;
	}

	/**
	 * Sets the avg party win rate.
	 *
	 * @param avgPartyWinRate the new avg party win rate
	 */
	public void setAvgPartyWinRate(final BigDecimal avgPartyWinRate) {
		this.avgPartyWinRate = avgPartyWinRate;
	}

	/**
	 * Gets the avg party absence rate.
	 *
	 * @return the avg party absence rate
	 */
	public BigDecimal getAvgPartyAbsenceRate() {
		return avgPartyAbsenceRate;
	}

	/**
	 * Sets the avg party absence rate.
	 *
	 * @param avgPartyAbsenceRate the new avg party absence rate
	 */
	public void setAvgPartyAbsenceRate(final BigDecimal avgPartyAbsenceRate) {
		this.avgPartyAbsenceRate = avgPartyAbsenceRate;
	}

	/**
	 * Gets the party documents total.
	 *
	 * @return the party documents total
	 */
	public Long getPartyDocumentsTotal() {
		return partyDocumentsTotal;
	}

	/**
	 * Sets the party documents total.
	 *
	 * @param partyDocumentsTotal the new party documents total
	 */
	public void setPartyDocumentsTotal(final Long partyDocumentsTotal) {
		this.partyDocumentsTotal = partyDocumentsTotal;
	}

	/**
	 * Gets the ma party win rate.
	 *
	 * @return the ma party win rate
	 */
	public BigDecimal getMaPartyWinRate() {
		return maPartyWinRate;
	}

	/**
	 * Sets the ma party win rate.
	 *
	 * @param maPartyWinRate the new ma party win rate
	 */
	public void setMaPartyWinRate(final BigDecimal maPartyWinRate) {
		this.maPartyWinRate = maPartyWinRate;
	}

	/**
	 * Gets the high performing parties.
	 *
	 * @return the high performing parties
	 */
	public Long getHighPerformingParties() {
		return highPerformingParties;
	}

	/**
	 * Sets the high performing parties.
	 *
	 * @param highPerformingParties the new high performing parties
	 */
	public void setHighPerformingParties(final Long highPerformingParties) {
		this.highPerformingParties = highPerformingParties;
	}

	/**
	 * Gets the baseline ballots.
	 *
	 * @return the baseline ballots
	 */
	public BigDecimal getBaselineBallots() {
		return baselineBallots;
	}

	/**
	 * Sets the baseline ballots.
	 *
	 * @param baselineBallots the new baseline ballots
	 */
	public void setBaselineBallots(final BigDecimal baselineBallots) {
		this.baselineBallots = baselineBallots;
	}

	/**
	 * Gets the stddev ballots.
	 *
	 * @return the stddev ballots
	 */
	public BigDecimal getStddevBallots() {
		return stddevBallots;
	}

	/**
	 * Sets the stddev ballots.
	 *
	 * @param stddevBallots the new stddev ballots
	 */
	public void setStddevBallots(final BigDecimal stddevBallots) {
		this.stddevBallots = stddevBallots;
	}

	/**
	 * Gets the baseline documents.
	 *
	 * @return the baseline documents
	 */
	public BigDecimal getBaselineDocuments() {
		return baselineDocuments;
	}

	/**
	 * Sets the baseline documents.
	 *
	 * @param baselineDocuments the new baseline documents
	 */
	public void setBaselineDocuments(final BigDecimal baselineDocuments) {
		this.baselineDocuments = baselineDocuments;
	}

	/**
	 * Gets the baseline assignments.
	 *
	 * @return the baseline assignments
	 */
	public BigDecimal getBaselineAssignments() {
		return baselineAssignments;
	}

	/**
	 * Sets the baseline assignments.
	 *
	 * @param baselineAssignments the new baseline assignments
	 */
	public void setBaselineAssignments(final BigDecimal baselineAssignments) {
		this.baselineAssignments = baselineAssignments;
	}

	/**
	 * Gets the ballot deviation from baseline.
	 *
	 * @return the ballot deviation from baseline
	 */
	public BigDecimal getBallotDeviationFromBaseline() {
		return ballotDeviationFromBaseline;
	}

	/**
	 * Sets the ballot deviation from baseline.
	 *
	 * @param ballotDeviationFromBaseline the new ballot deviation from baseline
	 */
	public void setBallotDeviationFromBaseline(final BigDecimal ballotDeviationFromBaseline) {
		this.ballotDeviationFromBaseline = ballotDeviationFromBaseline;
	}

	/**
	 * Gets the document deviation from baseline.
	 *
	 * @return the document deviation from baseline
	 */
	public BigDecimal getDocumentDeviationFromBaseline() {
		return documentDeviationFromBaseline;
	}

	/**
	 * Sets the document deviation from baseline.
	 *
	 * @param documentDeviationFromBaseline the new document deviation from baseline
	 */
	public void setDocumentDeviationFromBaseline(final BigDecimal documentDeviationFromBaseline) {
		this.documentDeviationFromBaseline = documentDeviationFromBaseline;
	}

	/**
	 * Gets the assignment deviation from baseline.
	 *
	 * @return the assignment deviation from baseline
	 */
	public BigDecimal getAssignmentDeviationFromBaseline() {
		return assignmentDeviationFromBaseline;
	}

	/**
	 * Sets the assignment deviation from baseline.
	 *
	 * @param assignmentDeviationFromBaseline the new assignment deviation from baseline
	 */
	public void setAssignmentDeviationFromBaseline(final BigDecimal assignmentDeviationFromBaseline) {
		this.assignmentDeviationFromBaseline = assignmentDeviationFromBaseline;
	}

	/**
	 * Gets the ballot percent change from baseline.
	 *
	 * @return the ballot percent change from baseline
	 */
	public BigDecimal getBallotPercentChangeFromBaseline() {
		return ballotPercentChangeFromBaseline;
	}

	/**
	 * Sets the ballot percent change from baseline.
	 *
	 * @param ballotPercentChangeFromBaseline the new ballot percent change from baseline
	 */
	public void setBallotPercentChangeFromBaseline(final BigDecimal ballotPercentChangeFromBaseline) {
		this.ballotPercentChangeFromBaseline = ballotPercentChangeFromBaseline;
	}

	/**
	 * Gets the document percent change from baseline.
	 *
	 * @return the document percent change from baseline
	 */
	public BigDecimal getDocumentPercentChangeFromBaseline() {
		return documentPercentChangeFromBaseline;
	}

	/**
	 * Sets the document percent change from baseline.
	 *
	 * @param documentPercentChangeFromBaseline the new document percent change from baseline
	 */
	public void setDocumentPercentChangeFromBaseline(final BigDecimal documentPercentChangeFromBaseline) {
		this.documentPercentChangeFromBaseline = documentPercentChangeFromBaseline;
	}

	/**
	 * Gets the baseline attendance.
	 *
	 * @return the baseline attendance
	 */
	public BigDecimal getBaselineAttendance() {
		return baselineAttendance;
	}

	/**
	 * Sets the baseline attendance.
	 *
	 * @param baselineAttendance the new baseline attendance
	 */
	public void setBaselineAttendance(final BigDecimal baselineAttendance) {
		this.baselineAttendance = baselineAttendance;
	}

	/**
	 * Gets the stddev attendance.
	 *
	 * @return the stddev attendance
	 */
	public BigDecimal getStddevAttendance() {
		return stddevAttendance;
	}

	/**
	 * Sets the stddev attendance.
	 *
	 * @param stddevAttendance the new stddev attendance
	 */
	public void setStddevAttendance(final BigDecimal stddevAttendance) {
		this.stddevAttendance = stddevAttendance;
	}

	/**
	 * Gets the attendance deviation from baseline.
	 *
	 * @return the attendance deviation from baseline
	 */
	public BigDecimal getAttendanceDeviationFromBaseline() {
		return attendanceDeviationFromBaseline;
	}

	/**
	 * Sets the attendance deviation from baseline.
	 *
	 * @param attendanceDeviationFromBaseline the new attendance deviation from baseline
	 */
	public void setAttendanceDeviationFromBaseline(final BigDecimal attendanceDeviationFromBaseline) {
		this.attendanceDeviationFromBaseline = attendanceDeviationFromBaseline;
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
	 * Gets the document z score.
	 *
	 * @return the document z score
	 */
	public BigDecimal getDocumentZScore() {
		return documentZScore;
	}

	/**
	 * Sets the document z score.
	 *
	 * @param documentZScore the new document z score
	 */
	public void setDocumentZScore(final BigDecimal documentZScore) {
		this.documentZScore = documentZScore;
	}

	/**
	 * Gets the q4 activity classification.
	 *
	 * @return the q4 activity classification
	 */
	public String getQ4ActivityClassification() {
		return q4ActivityClassification;
	}

	/**
	 * Sets the q4 activity classification.
	 *
	 * @param q4ActivityClassification the new q4 activity classification
	 */
	public void setQ4ActivityClassification(final String q4ActivityClassification) {
		this.q4ActivityClassification = q4ActivityClassification;
	}

	/**
	 * Gets the prev year ballots.
	 *
	 * @return the prev year ballots
	 */
	public Long getPrevYearBallots() {
		return prevYearBallots;
	}

	/**
	 * Sets the prev year ballots.
	 *
	 * @param prevYearBallots the new prev year ballots
	 */
	public void setPrevYearBallots(final Long prevYearBallots) {
		this.prevYearBallots = prevYearBallots;
	}

	/**
	 * Gets the yoy ballot change pct.
	 *
	 * @return the yoy ballot change pct
	 */
	public BigDecimal getYoyBallotChangePct() {
		return yoyBallotChangePct;
	}

	/**
	 * Sets the yoy ballot change pct.
	 *
	 * @param yoyBallotChangePct the new yoy ballot change pct
	 */
	public void setYoyBallotChangePct(final BigDecimal yoyBallotChangePct) {
		this.yoyBallotChangePct = yoyBallotChangePct;
	}

	/**
	 * Gets the rank by q4 activity.
	 *
	 * @return the rank by q4 activity
	 */
	public Long getRankByQ4Activity() {
		return rankByQ4Activity;
	}

	/**
	 * Sets the rank by q4 activity.
	 *
	 * @param rankByQ4Activity the new rank by q4 activity
	 */
	public void setRankByQ4Activity(final Long rankByQ4Activity) {
		this.rankByQ4Activity = rankByQ4Activity;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final ViewRiksdagenPreElectionQuarterlyActivity that = (ViewRiksdagenPreElectionQuarterlyActivity) obj;
		return Objects.equals(year, that.year);
	}

	@Override
	public int hashCode() {
		return Objects.hash(year);
	}

	@Override
	public String toString() {
		return "ViewRiksdagenPreElectionQuarterlyActivity{" +
				"year=" + year +
				", isElectionYear=" + isElectionYear +
				", totalBallots=" + totalBallots +
				", activePoliticians=" + activePoliticians +
				", totalDocuments=" + totalDocuments +
				", q4ActivityClassification='" + q4ActivityClassification + '\'' +
				", ballotZScore=" + ballotZScore +
				", documentZScore=" + documentZScore +
				'}';
	}
}
