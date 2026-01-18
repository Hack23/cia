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
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Immutable;

/**
 * The Class ViewRiksdagenElectionProximityTrends.
 * Database view tracking politician activity by months until election with Q4 pre-election focus.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_election_proximity_trends")
public class ViewRiksdagenElectionProximityTrends implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ViewRiksdagenElectionProximityTrendsEmbeddedId embeddedId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "party")
	private String party;

	@Column(name = "quarter_number")
	private Integer quarterNumber;

	@Column(name = "months_until_election")
	private Integer monthsUntilElection;

	@Column(name = "is_pre_election_q4")
	private Boolean isPreElectionQ4;

	@Column(name = "ballot_count")
	private Long ballotCount;

	@Column(name = "total_votes")
	private Long totalVotes;

	@Column(name = "attendance_rate")
	private BigDecimal attendanceRate;

	@Column(name = "avg_win_rate")
	private BigDecimal avgWinRate;

	@Column(name = "avg_rebel_rate")
	private BigDecimal avgRebelRate;

	@Column(name = "avg_yes_rate")
	private BigDecimal avgYesRate;

	@Column(name = "avg_no_rate")
	private BigDecimal avgNoRate;

	@Column(name = "avg_abstain_rate")
	private BigDecimal avgAbstainRate;

	@Column(name = "violation_count")
	private Long violationCount;

	@Column(name = "behavioral_assessment")
	private String behavioralAssessment;

	@Column(name = "attendance_status")
	private String attendanceStatus;

	@Column(name = "effectiveness_status")
	private String effectivenessStatus;

	@Column(name = "discipline_status")
	private String disciplineStatus;

	@Column(name = "document_count")
	private Long documentCount;

	@Column(name = "proposal_count")
	private Long proposalCount;

	@Column(name = "motion_count")
	private Long motionCount;

	@Column(name = "new_assignment_count")
	private Long newAssignmentCount;

	@Column(name = "peak_role_weight_quarter")
	private BigDecimal peakRoleWeightQuarter;

	@Column(name = "leadership_role_count")
	private Long leadershipRoleCount;

	@Column(name = "committee_assignment_count")
	private Long committeeAssignmentCount;

	@Column(name = "decision_count")
	private Long decisionCount;

	@Column(name = "approved_decisions")
	private Long approvedDecisions;

	@Column(name = "rejected_decisions")
	private Long rejectedDecisions;

	@Column(name = "avg_approval_rate")
	private BigDecimal avgApprovalRate;

	@Column(name = "active_committees")
	private Long activeCommittees;

	@Column(name = "overall_risk_score")
	private BigDecimal overallRiskScore;

	@Column(name = "risk_level")
	private String riskLevel;

	@Column(name = "trend_risk_score")
	private BigDecimal trendRiskScore;

	@Column(name = "anomaly_risk_score")
	private BigDecimal anomalyRiskScore;

	@Column(name = "influence_score")
	private BigDecimal influenceScore;

	@Column(name = "influence_classification")
	private String influenceClassification;

	@Column(name = "centrality_score")
	private BigDecimal centralityScore;

	@Column(name = "broker_score")
	private BigDecimal brokerScore;

	@Column(name = "broker_classification")
	private String brokerClassification;

	@Column(name = "avg_ballot_count_baseline")
	private BigDecimal avgBallotCountBaseline;

	@Column(name = "avg_document_count_baseline")
	private BigDecimal avgDocumentCountBaseline;

	@Column(name = "avg_decision_count_baseline")
	private BigDecimal avgDecisionCountBaseline;

	@Column(name = "avg_assignment_count_baseline")
	private BigDecimal avgAssignmentCountBaseline;

	@Column(name = "ballot_count_deviation")
	private BigDecimal ballotCountDeviation;

	@Column(name = "document_count_deviation")
	private BigDecimal documentCountDeviation;

	@Column(name = "decision_count_deviation")
	private BigDecimal decisionCountDeviation;

	@Column(name = "assignment_count_deviation")
	private BigDecimal assignmentCountDeviation;

	@Column(name = "ballot_activity_ratio")
	private BigDecimal ballotActivityRatio;

	@Column(name = "document_activity_ratio")
	private BigDecimal documentActivityRatio;

	@Column(name = "decision_activity_ratio")
	private BigDecimal decisionActivityRatio;

	@Column(name = "assignment_activity_ratio")
	private BigDecimal assignmentActivityRatio;

	@Column(name = "activity_classification")
	private String activityClassification;

	@Column(name = "composite_activity_score")
	private BigDecimal compositeActivityScore;

	@Column(name = "election_phase")
	private String electionPhase;

	@Column(name = "rank_by_activity_quarter")
	private Long rankByActivityQuarter;

	/**
	 * Instantiates a new view riksdagen election proximity trends.
	 */
	public ViewRiksdagenElectionProximityTrends() {
		super();
	}

	/**
	 * Gets the embedded id.
	 *
	 * @return the embedded id
	 */
	public ViewRiksdagenElectionProximityTrendsEmbeddedId getEmbeddedId() {
		return embeddedId;
	}

	/**
	 * Sets the embedded id.
	 *
	 * @param embeddedId the new embedded id
	 */
	public void setEmbeddedId(final ViewRiksdagenElectionProximityTrendsEmbeddedId embeddedId) {
		this.embeddedId = embeddedId;
	}

	/**
	 * Gets the person id.
	 *
	 * @return the person id
	 */
	public String getPersonId() {
		return embeddedId != null ? embeddedId.getPersonId() : null;
	}

	/**
	 * Sets the person id.
	 *
	 * @param personId the new person id
	 */
	public void setPersonId(final String personId) {
		if (this.embeddedId == null) {
			this.embeddedId = new ViewRiksdagenElectionProximityTrendsEmbeddedId();
		}
		this.embeddedId.setPersonId(personId);
	}

	/**
	 * Gets the election date.
	 *
	 * @return the election date
	 */
	public Date getElectionDate() {
		return embeddedId != null ? embeddedId.getElectionDate() : null;
	}

	/**
	 * Sets the election date.
	 *
	 * @param electionDate the new election date
	 */
	public void setElectionDate(final Date electionDate) {
		if (this.embeddedId == null) {
			this.embeddedId = new ViewRiksdagenElectionProximityTrendsEmbeddedId();
		}
		this.embeddedId.setElectionDate(electionDate);
	}

	/**
	 * Gets the activity quarter.
	 *
	 * @return the activity quarter
	 */
	public Date getActivityQuarter() {
		return embeddedId != null ? embeddedId.getActivityQuarter() : null;
	}

	/**
	 * Sets the activity quarter.
	 *
	 * @param activityQuarter the new activity quarter
	 */
	public void setActivityQuarter(final Date activityQuarter) {
		if (this.embeddedId == null) {
			this.embeddedId = new ViewRiksdagenElectionProximityTrendsEmbeddedId();
		}
		this.embeddedId.setActivityQuarter(activityQuarter);
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the party.
	 *
	 * @return the party
	 */
	public String getParty() {
		return party;
	}

	/**
	 * Sets the party.
	 *
	 * @param party the new party
	 */
	public void setParty(final String party) {
		this.party = party;
	}

	/**
	 * Gets the quarter number.
	 *
	 * @return the quarter number
	 */
	public Integer getQuarterNumber() {
		return quarterNumber;
	}

	/**
	 * Sets the quarter number.
	 *
	 * @param quarterNumber the new quarter number
	 */
	public void setQuarterNumber(final Integer quarterNumber) {
		this.quarterNumber = quarterNumber;
	}

	/**
	 * Gets the months until election.
	 *
	 * @return the months until election
	 */
	public Integer getMonthsUntilElection() {
		return monthsUntilElection;
	}

	/**
	 * Sets the months until election.
	 *
	 * @param monthsUntilElection the new months until election
	 */
	public void setMonthsUntilElection(final Integer monthsUntilElection) {
		this.monthsUntilElection = monthsUntilElection;
	}

	/**
	 * Gets the is pre election q4.
	 *
	 * @return the is pre election q4
	 */
	public Boolean getIsPreElectionQ4() {
		return isPreElectionQ4;
	}

	/**
	 * Sets the is pre election q4.
	 *
	 * @param isPreElectionQ4 the new is pre election q4
	 */
	public void setIsPreElectionQ4(final Boolean isPreElectionQ4) {
		this.isPreElectionQ4 = isPreElectionQ4;
	}

	/**
	 * Gets the ballot count.
	 *
	 * @return the ballot count
	 */
	public Long getBallotCount() {
		return ballotCount;
	}

	/**
	 * Sets the ballot count.
	 *
	 * @param ballotCount the new ballot count
	 */
	public void setBallotCount(final Long ballotCount) {
		this.ballotCount = ballotCount;
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
	 * Gets the avg yes rate.
	 *
	 * @return the avg yes rate
	 */
	public BigDecimal getAvgYesRate() {
		return avgYesRate;
	}

	/**
	 * Sets the avg yes rate.
	 *
	 * @param avgYesRate the new avg yes rate
	 */
	public void setAvgYesRate(final BigDecimal avgYesRate) {
		this.avgYesRate = avgYesRate;
	}

	/**
	 * Gets the avg no rate.
	 *
	 * @return the avg no rate
	 */
	public BigDecimal getAvgNoRate() {
		return avgNoRate;
	}

	/**
	 * Sets the avg no rate.
	 *
	 * @param avgNoRate the new avg no rate
	 */
	public void setAvgNoRate(final BigDecimal avgNoRate) {
		this.avgNoRate = avgNoRate;
	}

	/**
	 * Gets the avg abstain rate.
	 *
	 * @return the avg abstain rate
	 */
	public BigDecimal getAvgAbstainRate() {
		return avgAbstainRate;
	}

	/**
	 * Sets the avg abstain rate.
	 *
	 * @param avgAbstainRate the new avg abstain rate
	 */
	public void setAvgAbstainRate(final BigDecimal avgAbstainRate) {
		this.avgAbstainRate = avgAbstainRate;
	}

	/**
	 * Gets the violation count.
	 *
	 * @return the violation count
	 */
	public Long getViolationCount() {
		return violationCount;
	}

	/**
	 * Sets the violation count.
	 *
	 * @param violationCount the new violation count
	 */
	public void setViolationCount(final Long violationCount) {
		this.violationCount = violationCount;
	}

	/**
	 * Gets the behavioral assessment.
	 *
	 * @return the behavioral assessment
	 */
	public String getBehavioralAssessment() {
		return behavioralAssessment;
	}

	/**
	 * Sets the behavioral assessment.
	 *
	 * @param behavioralAssessment the new behavioral assessment
	 */
	public void setBehavioralAssessment(final String behavioralAssessment) {
		this.behavioralAssessment = behavioralAssessment;
	}

	/**
	 * Gets the attendance status.
	 *
	 * @return the attendance status
	 */
	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	/**
	 * Sets the attendance status.
	 *
	 * @param attendanceStatus the new attendance status
	 */
	public void setAttendanceStatus(final String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	/**
	 * Gets the effectiveness status.
	 *
	 * @return the effectiveness status
	 */
	public String getEffectivenessStatus() {
		return effectivenessStatus;
	}

	/**
	 * Sets the effectiveness status.
	 *
	 * @param effectivenessStatus the new effectiveness status
	 */
	public void setEffectivenessStatus(final String effectivenessStatus) {
		this.effectivenessStatus = effectivenessStatus;
	}

	/**
	 * Gets the discipline status.
	 *
	 * @return the discipline status
	 */
	public String getDisciplineStatus() {
		return disciplineStatus;
	}

	/**
	 * Sets the discipline status.
	 *
	 * @param disciplineStatus the new discipline status
	 */
	public void setDisciplineStatus(final String disciplineStatus) {
		this.disciplineStatus = disciplineStatus;
	}

	/**
	 * Gets the document count.
	 *
	 * @return the document count
	 */
	public Long getDocumentCount() {
		return documentCount;
	}

	/**
	 * Sets the document count.
	 *
	 * @param documentCount the new document count
	 */
	public void setDocumentCount(final Long documentCount) {
		this.documentCount = documentCount;
	}

	/**
	 * Gets the proposal count.
	 *
	 * @return the proposal count
	 */
	public Long getProposalCount() {
		return proposalCount;
	}

	/**
	 * Sets the proposal count.
	 *
	 * @param proposalCount the new proposal count
	 */
	public void setProposalCount(final Long proposalCount) {
		this.proposalCount = proposalCount;
	}

	/**
	 * Gets the motion count.
	 *
	 * @return the motion count
	 */
	public Long getMotionCount() {
		return motionCount;
	}

	/**
	 * Sets the motion count.
	 *
	 * @param motionCount the new motion count
	 */
	public void setMotionCount(final Long motionCount) {
		this.motionCount = motionCount;
	}

	/**
	 * Gets the new assignment count.
	 *
	 * @return the new assignment count
	 */
	public Long getNewAssignmentCount() {
		return newAssignmentCount;
	}

	/**
	 * Sets the new assignment count.
	 *
	 * @param newAssignmentCount the new new assignment count
	 */
	public void setNewAssignmentCount(final Long newAssignmentCount) {
		this.newAssignmentCount = newAssignmentCount;
	}

	/**
	 * Gets the peak role weight quarter.
	 *
	 * @return the peak role weight quarter
	 */
	public BigDecimal getPeakRoleWeightQuarter() {
		return peakRoleWeightQuarter;
	}

	/**
	 * Sets the peak role weight quarter.
	 *
	 * @param peakRoleWeightQuarter the new peak role weight quarter
	 */
	public void setPeakRoleWeightQuarter(final BigDecimal peakRoleWeightQuarter) {
		this.peakRoleWeightQuarter = peakRoleWeightQuarter;
	}

	/**
	 * Gets the leadership role count.
	 *
	 * @return the leadership role count
	 */
	public Long getLeadershipRoleCount() {
		return leadershipRoleCount;
	}

	/**
	 * Sets the leadership role count.
	 *
	 * @param leadershipRoleCount the new leadership role count
	 */
	public void setLeadershipRoleCount(final Long leadershipRoleCount) {
		this.leadershipRoleCount = leadershipRoleCount;
	}

	/**
	 * Gets the committee assignment count.
	 *
	 * @return the committee assignment count
	 */
	public Long getCommitteeAssignmentCount() {
		return committeeAssignmentCount;
	}

	/**
	 * Sets the committee assignment count.
	 *
	 * @param committeeAssignmentCount the new committee assignment count
	 */
	public void setCommitteeAssignmentCount(final Long committeeAssignmentCount) {
		this.committeeAssignmentCount = committeeAssignmentCount;
	}

	/**
	 * Gets the decision count.
	 *
	 * @return the decision count
	 */
	public Long getDecisionCount() {
		return decisionCount;
	}

	/**
	 * Sets the decision count.
	 *
	 * @param decisionCount the new decision count
	 */
	public void setDecisionCount(final Long decisionCount) {
		this.decisionCount = decisionCount;
	}

	/**
	 * Gets the approved decisions.
	 *
	 * @return the approved decisions
	 */
	public Long getApprovedDecisions() {
		return approvedDecisions;
	}

	/**
	 * Sets the approved decisions.
	 *
	 * @param approvedDecisions the new approved decisions
	 */
	public void setApprovedDecisions(final Long approvedDecisions) {
		this.approvedDecisions = approvedDecisions;
	}

	/**
	 * Gets the rejected decisions.
	 *
	 * @return the rejected decisions
	 */
	public Long getRejectedDecisions() {
		return rejectedDecisions;
	}

	/**
	 * Sets the rejected decisions.
	 *
	 * @param rejectedDecisions the new rejected decisions
	 */
	public void setRejectedDecisions(final Long rejectedDecisions) {
		this.rejectedDecisions = rejectedDecisions;
	}

	/**
	 * Gets the avg approval rate.
	 *
	 * @return the avg approval rate
	 */
	public BigDecimal getAvgApprovalRate() {
		return avgApprovalRate;
	}

	/**
	 * Sets the avg approval rate.
	 *
	 * @param avgApprovalRate the new avg approval rate
	 */
	public void setAvgApprovalRate(final BigDecimal avgApprovalRate) {
		this.avgApprovalRate = avgApprovalRate;
	}

	/**
	 * Gets the active committees.
	 *
	 * @return the active committees
	 */
	public Long getActiveCommittees() {
		return activeCommittees;
	}

	/**
	 * Sets the active committees.
	 *
	 * @param activeCommittees the new active committees
	 */
	public void setActiveCommittees(final Long activeCommittees) {
		this.activeCommittees = activeCommittees;
	}

	/**
	 * Gets the overall risk score.
	 *
	 * @return the overall risk score
	 */
	public BigDecimal getOverallRiskScore() {
		return overallRiskScore;
	}

	/**
	 * Sets the overall risk score.
	 *
	 * @param overallRiskScore the new overall risk score
	 */
	public void setOverallRiskScore(final BigDecimal overallRiskScore) {
		this.overallRiskScore = overallRiskScore;
	}

	/**
	 * Gets the risk level.
	 *
	 * @return the risk level
	 */
	public String getRiskLevel() {
		return riskLevel;
	}

	/**
	 * Sets the risk level.
	 *
	 * @param riskLevel the new risk level
	 */
	public void setRiskLevel(final String riskLevel) {
		this.riskLevel = riskLevel;
	}

	/**
	 * Gets the trend risk score.
	 *
	 * @return the trend risk score
	 */
	public BigDecimal getTrendRiskScore() {
		return trendRiskScore;
	}

	/**
	 * Sets the trend risk score.
	 *
	 * @param trendRiskScore the new trend risk score
	 */
	public void setTrendRiskScore(final BigDecimal trendRiskScore) {
		this.trendRiskScore = trendRiskScore;
	}

	/**
	 * Gets the anomaly risk score.
	 *
	 * @return the anomaly risk score
	 */
	public BigDecimal getAnomalyRiskScore() {
		return anomalyRiskScore;
	}

	/**
	 * Sets the anomaly risk score.
	 *
	 * @param anomalyRiskScore the new anomaly risk score
	 */
	public void setAnomalyRiskScore(final BigDecimal anomalyRiskScore) {
		this.anomalyRiskScore = anomalyRiskScore;
	}

	/**
	 * Gets the influence score.
	 *
	 * @return the influence score
	 */
	public BigDecimal getInfluenceScore() {
		return influenceScore;
	}

	/**
	 * Sets the influence score.
	 *
	 * @param influenceScore the new influence score
	 */
	public void setInfluenceScore(final BigDecimal influenceScore) {
		this.influenceScore = influenceScore;
	}

	/**
	 * Gets the influence classification.
	 *
	 * @return the influence classification
	 */
	public String getInfluenceClassification() {
		return influenceClassification;
	}

	/**
	 * Sets the influence classification.
	 *
	 * @param influenceClassification the new influence classification
	 */
	public void setInfluenceClassification(final String influenceClassification) {
		this.influenceClassification = influenceClassification;
	}

	/**
	 * Gets the centrality score.
	 *
	 * @return the centrality score
	 */
	public BigDecimal getCentralityScore() {
		return centralityScore;
	}

	/**
	 * Sets the centrality score.
	 *
	 * @param centralityScore the new centrality score
	 */
	public void setCentralityScore(final BigDecimal centralityScore) {
		this.centralityScore = centralityScore;
	}

	/**
	 * Gets the broker score.
	 *
	 * @return the broker score
	 */
	public BigDecimal getBrokerScore() {
		return brokerScore;
	}

	/**
	 * Sets the broker score.
	 *
	 * @param brokerScore the new broker score
	 */
	public void setBrokerScore(final BigDecimal brokerScore) {
		this.brokerScore = brokerScore;
	}

	/**
	 * Gets the broker classification.
	 *
	 * @return the broker classification
	 */
	public String getBrokerClassification() {
		return brokerClassification;
	}

	/**
	 * Sets the broker classification.
	 *
	 * @param brokerClassification the new broker classification
	 */
	public void setBrokerClassification(final String brokerClassification) {
		this.brokerClassification = brokerClassification;
	}

	/**
	 * Gets the avg ballot count baseline.
	 *
	 * @return the avg ballot count baseline
	 */
	public BigDecimal getAvgBallotCountBaseline() {
		return avgBallotCountBaseline;
	}

	/**
	 * Sets the avg ballot count baseline.
	 *
	 * @param avgBallotCountBaseline the new avg ballot count baseline
	 */
	public void setAvgBallotCountBaseline(final BigDecimal avgBallotCountBaseline) {
		this.avgBallotCountBaseline = avgBallotCountBaseline;
	}

	/**
	 * Gets the avg document count baseline.
	 *
	 * @return the avg document count baseline
	 */
	public BigDecimal getAvgDocumentCountBaseline() {
		return avgDocumentCountBaseline;
	}

	/**
	 * Sets the avg document count baseline.
	 *
	 * @param avgDocumentCountBaseline the new avg document count baseline
	 */
	public void setAvgDocumentCountBaseline(final BigDecimal avgDocumentCountBaseline) {
		this.avgDocumentCountBaseline = avgDocumentCountBaseline;
	}

	/**
	 * Gets the avg decision count baseline.
	 *
	 * @return the avg decision count baseline
	 */
	public BigDecimal getAvgDecisionCountBaseline() {
		return avgDecisionCountBaseline;
	}

	/**
	 * Sets the avg decision count baseline.
	 *
	 * @param avgDecisionCountBaseline the new avg decision count baseline
	 */
	public void setAvgDecisionCountBaseline(final BigDecimal avgDecisionCountBaseline) {
		this.avgDecisionCountBaseline = avgDecisionCountBaseline;
	}

	/**
	 * Gets the avg assignment count baseline.
	 *
	 * @return the avg assignment count baseline
	 */
	public BigDecimal getAvgAssignmentCountBaseline() {
		return avgAssignmentCountBaseline;
	}

	/**
	 * Sets the avg assignment count baseline.
	 *
	 * @param avgAssignmentCountBaseline the new avg assignment count baseline
	 */
	public void setAvgAssignmentCountBaseline(final BigDecimal avgAssignmentCountBaseline) {
		this.avgAssignmentCountBaseline = avgAssignmentCountBaseline;
	}

	/**
	 * Gets the ballot count deviation.
	 *
	 * @return the ballot count deviation
	 */
	public BigDecimal getBallotCountDeviation() {
		return ballotCountDeviation;
	}

	/**
	 * Sets the ballot count deviation.
	 *
	 * @param ballotCountDeviation the new ballot count deviation
	 */
	public void setBallotCountDeviation(final BigDecimal ballotCountDeviation) {
		this.ballotCountDeviation = ballotCountDeviation;
	}

	/**
	 * Gets the document count deviation.
	 *
	 * @return the document count deviation
	 */
	public BigDecimal getDocumentCountDeviation() {
		return documentCountDeviation;
	}

	/**
	 * Sets the document count deviation.
	 *
	 * @param documentCountDeviation the new document count deviation
	 */
	public void setDocumentCountDeviation(final BigDecimal documentCountDeviation) {
		this.documentCountDeviation = documentCountDeviation;
	}

	/**
	 * Gets the decision count deviation.
	 *
	 * @return the decision count deviation
	 */
	public BigDecimal getDecisionCountDeviation() {
		return decisionCountDeviation;
	}

	/**
	 * Sets the decision count deviation.
	 *
	 * @param decisionCountDeviation the new decision count deviation
	 */
	public void setDecisionCountDeviation(final BigDecimal decisionCountDeviation) {
		this.decisionCountDeviation = decisionCountDeviation;
	}

	/**
	 * Gets the assignment count deviation.
	 *
	 * @return the assignment count deviation
	 */
	public BigDecimal getAssignmentCountDeviation() {
		return assignmentCountDeviation;
	}

	/**
	 * Sets the assignment count deviation.
	 *
	 * @param assignmentCountDeviation the new assignment count deviation
	 */
	public void setAssignmentCountDeviation(final BigDecimal assignmentCountDeviation) {
		this.assignmentCountDeviation = assignmentCountDeviation;
	}

	/**
	 * Gets the ballot activity ratio.
	 *
	 * @return the ballot activity ratio
	 */
	public BigDecimal getBallotActivityRatio() {
		return ballotActivityRatio;
	}

	/**
	 * Sets the ballot activity ratio.
	 *
	 * @param ballotActivityRatio the new ballot activity ratio
	 */
	public void setBallotActivityRatio(final BigDecimal ballotActivityRatio) {
		this.ballotActivityRatio = ballotActivityRatio;
	}

	/**
	 * Gets the document activity ratio.
	 *
	 * @return the document activity ratio
	 */
	public BigDecimal getDocumentActivityRatio() {
		return documentActivityRatio;
	}

	/**
	 * Sets the document activity ratio.
	 *
	 * @param documentActivityRatio the new document activity ratio
	 */
	public void setDocumentActivityRatio(final BigDecimal documentActivityRatio) {
		this.documentActivityRatio = documentActivityRatio;
	}

	/**
	 * Gets the decision activity ratio.
	 *
	 * @return the decision activity ratio
	 */
	public BigDecimal getDecisionActivityRatio() {
		return decisionActivityRatio;
	}

	/**
	 * Sets the decision activity ratio.
	 *
	 * @param decisionActivityRatio the new decision activity ratio
	 */
	public void setDecisionActivityRatio(final BigDecimal decisionActivityRatio) {
		this.decisionActivityRatio = decisionActivityRatio;
	}

	/**
	 * Gets the assignment activity ratio.
	 *
	 * @return the assignment activity ratio
	 */
	public BigDecimal getAssignmentActivityRatio() {
		return assignmentActivityRatio;
	}

	/**
	 * Sets the assignment activity ratio.
	 *
	 * @param assignmentActivityRatio the new assignment activity ratio
	 */
	public void setAssignmentActivityRatio(final BigDecimal assignmentActivityRatio) {
		this.assignmentActivityRatio = assignmentActivityRatio;
	}

	/**
	 * Gets the activity classification.
	 *
	 * @return the activity classification
	 */
	public String getActivityClassification() {
		return activityClassification;
	}

	/**
	 * Sets the activity classification.
	 *
	 * @param activityClassification the new activity classification
	 */
	public void setActivityClassification(final String activityClassification) {
		this.activityClassification = activityClassification;
	}

	/**
	 * Gets the composite activity score.
	 *
	 * @return the composite activity score
	 */
	public BigDecimal getCompositeActivityScore() {
		return compositeActivityScore;
	}

	/**
	 * Sets the composite activity score.
	 *
	 * @param compositeActivityScore the new composite activity score
	 */
	public void setCompositeActivityScore(final BigDecimal compositeActivityScore) {
		this.compositeActivityScore = compositeActivityScore;
	}

	/**
	 * Gets the election phase.
	 *
	 * @return the election phase
	 */
	public String getElectionPhase() {
		return electionPhase;
	}

	/**
	 * Sets the election phase.
	 *
	 * @param electionPhase the new election phase
	 */
	public void setElectionPhase(final String electionPhase) {
		this.electionPhase = electionPhase;
	}

	/**
	 * Gets the rank by activity quarter.
	 *
	 * @return the rank by activity quarter
	 */
	public Long getRankByActivityQuarter() {
		return rankByActivityQuarter;
	}

	/**
	 * Sets the rank by activity quarter.
	 *
	 * @param rankByActivityQuarter the new rank by activity quarter
	 */
	public void setRankByActivityQuarter(final Long rankByActivityQuarter) {
		this.rankByActivityQuarter = rankByActivityQuarter;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final ViewRiksdagenElectionProximityTrends that = (ViewRiksdagenElectionProximityTrends) obj;
		return Objects.equals(getPersonId(), that.getPersonId()) &&
				Objects.equals(getElectionDate(), that.getElectionDate()) &&
				Objects.equals(getActivityQuarter(), that.getActivityQuarter());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPersonId(), getElectionDate(), getActivityQuarter());
	}

	@Override
	public String toString() {
		return "ViewRiksdagenElectionProximityTrends{" +
				"personId='" + getPersonId() + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", party='" + party + '\'' +
				", electionDate=" + getElectionDate() +
				", activityQuarter=" + getActivityQuarter() +
				", quarterNumber=" + quarterNumber +
				", monthsUntilElection=" + monthsUntilElection +
				", isPreElectionQ4=" + isPreElectionQ4 +
				", ballotCount=" + ballotCount +
				", activityClassification='" + activityClassification + '\'' +
				", compositeActivityScore=" + compositeActivityScore +
				", electionPhase='" + electionPhase + '\'' +
				'}';
	}
}
