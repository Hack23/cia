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
package com.hack23.cia.model.internal.application.data.committee.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * JPA entity for view_committee_productivity database view.
 * 
 * Intelligence Purpose: Comprehensive committee productivity and efficiency metrics
 * combining membership, decisions, documents, and ballots to generate productivity scores
 * and identify performance concerns.
 * 
 * Created by: Database schema v1.0+
 * Risk Rules Supported: C-01 through C-04 (Committee performance rules)
 * 
 * Enables identification of underperforming committees and operational bottlenecks
 * through multi-dimensional productivity analysis.
 */
@Entity(name = "ViewCommitteeProductivity")
@Table(name = "view_committee_productivity")
public class ViewCommitteeProductivity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The committee code. */
	@Id
	@Column(name = "committee_code", nullable = false, length = 255)
	private String committeeCode;

	/** The committee name. */
	@Column(name = "committee_name", length = 255)
	private String committeeName;

	/** The total members. */
	@Column(name = "total_members")
	private Long totalMembers;

	/** The chairs count. */
	@Column(name = "chairs_count")
	private Long chairsCount;

	/** The vice chairs count. */
	@Column(name = "vice_chairs_count")
	private Long viceChairsCount;

	/** The regular members. */
	@Column(name = "regular_members")
	private Long regularMembers;

	/** The substitutes. */
	@Column(name = "substitutes")
	private Long substitutes;

	/** The staffing status. */
	@Column(name = "staffing_status", length = 50)
	private String staffingStatus;

	/** The total decisions all time. */
	@Column(name = "total_decisions_all_time")
	private Long totalDecisionsAllTime;

	/** The decisions last year. */
	@Column(name = "decisions_last_year")
	private Long decisionsLastYear;

	/** The decisions last month. */
	@Column(name = "decisions_last_month")
	private Long decisionsLastMonth;

	/** The latest decision date. */
	@Column(name = "latest_decision_date")
	@Temporal(TemporalType.DATE)
	private Date latestDecisionDate;

	/** The days since last decision. */
	@Column(name = "days_since_last_decision")
	private Integer daysSinceLastDecision;

	/** The total documents. */
	@Column(name = "total_documents")
	private Long totalDocuments;

	/** The documents last year. */
	@Column(name = "documents_last_year")
	private Long documentsLastYear;

	/** The motions count. */
	@Column(name = "motions_count")
	private Long motionsCount;

	/** The propositions count. */
	@Column(name = "propositions_count")
	private Long propositionsCount;

	/** The reports count. */
	@Column(name = "reports_count")
	private Long reportsCount;

	/** The decisions per member. */
	@Column(name = "decisions_per_member", precision = 10, scale = 2)
	private BigDecimal decisionsPerMember;

	/** The documents per member. */
	@Column(name = "documents_per_member", precision = 10, scale = 2)
	private BigDecimal documentsPerMember;

	/** The ballots decided last year. */
	@Column(name = "ballots_decided_last_year")
	private Long ballotsDecidedLastYear;

	/** The avg approval rate. */
	@Column(name = "avg_approval_rate", precision = 10, scale = 2)
	private BigDecimal avgApprovalRate;

	/** The productivity score. */
	@Column(name = "productivity_score", precision = 10, scale = 2)
	private BigDecimal productivityScore;

	/** The productivity level. */
	@Column(name = "productivity_level", length = 50)
	private String productivityLevel;

	/** The performance concerns. */
	@Column(name = "performance_concerns", length = 500)
	private String performanceConcerns;

	/** The recommendation. */
	@Column(name = "recommendation", length = 500)
	private String recommendation;

	/**
	 * Instantiates a new view committee productivity.
	 */
	public ViewCommitteeProductivity() {
		super();
	}

	/**
	 * Gets the committee code.
	 *
	 * @return the committee code
	 */
	public String getCommitteeCode() {
		return committeeCode;
	}

	/**
	 * Sets the committee code.
	 *
	 * @param committeeCode the new committee code
	 */
	public void setCommitteeCode(final String committeeCode) {
		this.committeeCode = committeeCode;
	}

	/**
	 * Gets the committee name.
	 *
	 * @return the committee name
	 */
	public String getCommitteeName() {
		return committeeName;
	}

	/**
	 * Sets the committee name.
	 *
	 * @param committeeName the new committee name
	 */
	public void setCommitteeName(final String committeeName) {
		this.committeeName = committeeName;
	}

	/**
	 * Gets the total members.
	 *
	 * @return the total members
	 */
	public Long getTotalMembers() {
		return totalMembers;
	}

	/**
	 * Sets the total members.
	 *
	 * @param totalMembers the new total members
	 */
	public void setTotalMembers(final Long totalMembers) {
		this.totalMembers = totalMembers;
	}

	/**
	 * Gets the chairs count.
	 *
	 * @return the chairs count
	 */
	public Long getChairsCount() {
		return chairsCount;
	}

	/**
	 * Sets the chairs count.
	 *
	 * @param chairsCount the new chairs count
	 */
	public void setChairsCount(final Long chairsCount) {
		this.chairsCount = chairsCount;
	}

	/**
	 * Gets the vice chairs count.
	 *
	 * @return the vice chairs count
	 */
	public Long getViceChairsCount() {
		return viceChairsCount;
	}

	/**
	 * Sets the vice chairs count.
	 *
	 * @param viceChairsCount the new vice chairs count
	 */
	public void setViceChairsCount(final Long viceChairsCount) {
		this.viceChairsCount = viceChairsCount;
	}

	/**
	 * Gets the regular members.
	 *
	 * @return the regular members
	 */
	public Long getRegularMembers() {
		return regularMembers;
	}

	/**
	 * Sets the regular members.
	 *
	 * @param regularMembers the new regular members
	 */
	public void setRegularMembers(final Long regularMembers) {
		this.regularMembers = regularMembers;
	}

	/**
	 * Gets the substitutes.
	 *
	 * @return the substitutes
	 */
	public Long getSubstitutes() {
		return substitutes;
	}

	/**
	 * Sets the substitutes.
	 *
	 * @param substitutes the new substitutes
	 */
	public void setSubstitutes(final Long substitutes) {
		this.substitutes = substitutes;
	}

	/**
	 * Gets the staffing status.
	 *
	 * @return the staffing status
	 */
	public String getStaffingStatus() {
		return staffingStatus;
	}

	/**
	 * Sets the staffing status.
	 *
	 * @param staffingStatus the new staffing status
	 */
	public void setStaffingStatus(final String staffingStatus) {
		this.staffingStatus = staffingStatus;
	}

	/**
	 * Gets the total decisions all time.
	 *
	 * @return the total decisions all time
	 */
	public Long getTotalDecisionsAllTime() {
		return totalDecisionsAllTime;
	}

	/**
	 * Sets the total decisions all time.
	 *
	 * @param totalDecisionsAllTime the new total decisions all time
	 */
	public void setTotalDecisionsAllTime(final Long totalDecisionsAllTime) {
		this.totalDecisionsAllTime = totalDecisionsAllTime;
	}

	/**
	 * Gets the decisions last year.
	 *
	 * @return the decisions last year
	 */
	public Long getDecisionsLastYear() {
		return decisionsLastYear;
	}

	/**
	 * Sets the decisions last year.
	 *
	 * @param decisionsLastYear the new decisions last year
	 */
	public void setDecisionsLastYear(final Long decisionsLastYear) {
		this.decisionsLastYear = decisionsLastYear;
	}

	/**
	 * Gets the decisions last month.
	 *
	 * @return the decisions last month
	 */
	public Long getDecisionsLastMonth() {
		return decisionsLastMonth;
	}

	/**
	 * Sets the decisions last month.
	 *
	 * @param decisionsLastMonth the new decisions last month
	 */
	public void setDecisionsLastMonth(final Long decisionsLastMonth) {
		this.decisionsLastMonth = decisionsLastMonth;
	}

	/**
	 * Gets the latest decision date.
	 *
	 * @return the latest decision date
	 */
	public Date getLatestDecisionDate() {
		return latestDecisionDate;
	}

	/**
	 * Sets the latest decision date.
	 *
	 * @param latestDecisionDate the new latest decision date
	 */
	public void setLatestDecisionDate(final Date latestDecisionDate) {
		this.latestDecisionDate = latestDecisionDate;
	}

	/**
	 * Gets the days since last decision.
	 *
	 * @return the days since last decision
	 */
	public Integer getDaysSinceLastDecision() {
		return daysSinceLastDecision;
	}

	/**
	 * Sets the days since last decision.
	 *
	 * @param daysSinceLastDecision the new days since last decision
	 */
	public void setDaysSinceLastDecision(final Integer daysSinceLastDecision) {
		this.daysSinceLastDecision = daysSinceLastDecision;
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
	 * Gets the documents last year.
	 *
	 * @return the documents last year
	 */
	public Long getDocumentsLastYear() {
		return documentsLastYear;
	}

	/**
	 * Sets the documents last year.
	 *
	 * @param documentsLastYear the new documents last year
	 */
	public void setDocumentsLastYear(final Long documentsLastYear) {
		this.documentsLastYear = documentsLastYear;
	}

	/**
	 * Gets the motions count.
	 *
	 * @return the motions count
	 */
	public Long getMotionsCount() {
		return motionsCount;
	}

	/**
	 * Sets the motions count.
	 *
	 * @param motionsCount the new motions count
	 */
	public void setMotionsCount(final Long motionsCount) {
		this.motionsCount = motionsCount;
	}

	/**
	 * Gets the propositions count.
	 *
	 * @return the propositions count
	 */
	public Long getPropositionsCount() {
		return propositionsCount;
	}

	/**
	 * Sets the propositions count.
	 *
	 * @param propositionsCount the new propositions count
	 */
	public void setPropositionsCount(final Long propositionsCount) {
		this.propositionsCount = propositionsCount;
	}

	/**
	 * Gets the reports count.
	 *
	 * @return the reports count
	 */
	public Long getReportsCount() {
		return reportsCount;
	}

	/**
	 * Sets the reports count.
	 *
	 * @param reportsCount the new reports count
	 */
	public void setReportsCount(final Long reportsCount) {
		this.reportsCount = reportsCount;
	}

	/**
	 * Gets the decisions per member.
	 *
	 * @return the decisions per member
	 */
	public BigDecimal getDecisionsPerMember() {
		return decisionsPerMember;
	}

	/**
	 * Sets the decisions per member.
	 *
	 * @param decisionsPerMember the new decisions per member
	 */
	public void setDecisionsPerMember(final BigDecimal decisionsPerMember) {
		this.decisionsPerMember = decisionsPerMember;
	}

	/**
	 * Gets the documents per member.
	 *
	 * @return the documents per member
	 */
	public BigDecimal getDocumentsPerMember() {
		return documentsPerMember;
	}

	/**
	 * Sets the documents per member.
	 *
	 * @param documentsPerMember the new documents per member
	 */
	public void setDocumentsPerMember(final BigDecimal documentsPerMember) {
		this.documentsPerMember = documentsPerMember;
	}

	/**
	 * Gets the ballots decided last year.
	 *
	 * @return the ballots decided last year
	 */
	public Long getBallotsDecidedLastYear() {
		return ballotsDecidedLastYear;
	}

	/**
	 * Sets the ballots decided last year.
	 *
	 * @param ballotsDecidedLastYear the new ballots decided last year
	 */
	public void setBallotsDecidedLastYear(final Long ballotsDecidedLastYear) {
		this.ballotsDecidedLastYear = ballotsDecidedLastYear;
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
	 * Gets the productivity score.
	 *
	 * @return the productivity score
	 */
	public BigDecimal getProductivityScore() {
		return productivityScore;
	}

	/**
	 * Sets the productivity score.
	 *
	 * @param productivityScore the new productivity score
	 */
	public void setProductivityScore(final BigDecimal productivityScore) {
		this.productivityScore = productivityScore;
	}

	/**
	 * Gets the productivity level.
	 *
	 * @return the productivity level
	 */
	public String getProductivityLevel() {
		return productivityLevel;
	}

	/**
	 * Sets the productivity level.
	 *
	 * @param productivityLevel the new productivity level
	 */
	public void setProductivityLevel(final String productivityLevel) {
		this.productivityLevel = productivityLevel;
	}

	/**
	 * Gets the performance concerns.
	 *
	 * @return the performance concerns
	 */
	public String getPerformanceConcerns() {
		return performanceConcerns;
	}

	/**
	 * Sets the performance concerns.
	 *
	 * @param performanceConcerns the new performance concerns
	 */
	public void setPerformanceConcerns(final String performanceConcerns) {
		this.performanceConcerns = performanceConcerns;
	}

	/**
	 * Gets the recommendation.
	 *
	 * @return the recommendation
	 */
	public String getRecommendation() {
		return recommendation;
	}

	/**
	 * Sets the recommendation.
	 *
	 * @param recommendation the new recommendation
	 */
	public void setRecommendation(final String recommendation) {
		this.recommendation = recommendation;
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
