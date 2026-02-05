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
package com.hack23.cia.model.internal.application.data.ministry.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
 * The Class ViewMinistryDecisionImpact.
 * 
 * JPA Entity for view_ministry_decision_impact database view.
 * 
 * Tracks ministry-level proposal success rates and effectiveness, enabling
 * analysis of government policy implementation, coalition stability, and
 * ministry performance across different policy domains. Aggregates proposal
 * outcomes by ministry, committee, decision type, and quarter to provide
 * insights into government effectiveness and policy success patterns.
 * 
 * <p>Intelligence Value: ⭐⭐⭐⭐⭐ VERY HIGH</p>
 * <ul>
 * <li>Government policy effectiveness measurement</li>
 * <li>Coalition stability monitoring via proposal success rates</li>
 * <li>Ministry performance benchmarking and comparison</li>
 * <li>Policy domain success patterns identification</li>
 * <li>Strategic planning and resource allocation insights</li>
 * </ul>
 * 
 * <p>Use Cases:</p>
 * <ul>
 * <li>Government Performance Scorecards: Track executive branch effectiveness</li>
 * <li>Coalition Health Monitoring: Detect weakening support for government</li>
 * <li>Policy Success Forecasting: Predict proposal outcomes based on patterns</li>
 * <li>Ministry Rankings: Compare effectiveness across departments</li>
 * <li>Opposition Strategy Analysis: Identify vulnerable policy areas</li>
 * </ul>
 * 
 * @author intelligence-operative
 * @since v1.35 (Decision Intelligence)
 * @see <a href="https://github.com/Hack23/cia/issues/7920">Issue #7920</a>
 * @see com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyDecisionFlow
 * @see ViewRiksdagenMinistry Core ministry information
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewMinistryDecisionImpact", propOrder = {
    "embeddedId",
    "decisionYear",
    "quarterNum",
    "totalProposals",
    "approvedProposals",
    "rejectedProposals",
    "referredBackProposals",
    "committeeReferralProposals",
    "otherDecisions",
    "approvalRate",
    "rejectionRate",
    "committeeReferralRate",
    "earliestProposalDate",
    "latestProposalDate"
})
@Entity(name = "ViewMinistryDecisionImpact")
@Table(name = "view_ministry_decision_impact")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewMinistryDecisionImpact implements ModelObject {

	private static final long serialVersionUID = 1L;

	@XmlElement(required = true)
	protected ViewMinistryDecisionImpactEmbeddedId embeddedId;

	@XmlElement(name = "decision_year")
	protected Integer decisionYear;

	@XmlElement(name = "quarter_num")
	protected Integer quarterNum;

	@XmlElement(name = "total_proposals")
	protected long totalProposals;

	@XmlElement(name = "approved_proposals")
	protected long approvedProposals;

	@XmlElement(name = "rejected_proposals")
	protected long rejectedProposals;

	@XmlElement(name = "referred_back_proposals")
	protected long referredBackProposals;

	@XmlElement(name = "committee_referral_proposals")
	protected long committeeReferralProposals;

	@XmlElement(name = "other_decisions")
	protected long otherDecisions;

	@XmlElement(name = "approval_rate")
	protected BigDecimal approvalRate;

	@XmlElement(name = "rejection_rate")
	protected BigDecimal rejectionRate;

	@XmlElement(name = "committee_referral_rate")
	protected BigDecimal committeeReferralRate;

	@XmlElement(name = "earliest_proposal_date")
	protected Date earliestProposalDate;

	@XmlElement(name = "latest_proposal_date")
	protected Date latestProposalDate;

	/**
	 * Gets the embedded id.
	 *
	 * @return the embedded id
	 */
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "ministryCode", column = @Column(name = "EMBEDDED_ID_MINISTRY_CODE")),
		@AttributeOverride(name = "committee", column = @Column(name = "EMBEDDED_ID_COMMITTEE")),
		@AttributeOverride(name = "decisionType", column = @Column(name = "EMBEDDED_ID_DECISION_TYPE")),
		@AttributeOverride(name = "decisionQuarter", column = @Column(name = "EMBEDDED_ID_DECISION_QUARTER"))
	})
	public ViewMinistryDecisionImpactEmbeddedId getEmbeddedId() {
		return embeddedId;
	}

	/**
	 * Sets the embedded id.
	 *
	 * @param value the new embedded id
	 */
	public void setEmbeddedId(final ViewMinistryDecisionImpactEmbeddedId value) {
		this.embeddedId = value;
	}

	/**
	 * Gets the decision year.
	 *
	 * @return the decision year
	 */
	@Basic
	@Column(name = "DECISION_YEAR")
	public Integer getDecisionYear() {
		return decisionYear;
	}

	/**
	 * Sets the decision year.
	 *
	 * @param value the new decision year
	 */
	public void setDecisionYear(final Integer value) {
		this.decisionYear = value;
	}

	/**
	 * Gets the quarter num.
	 *
	 * @return the quarter num
	 */
	@Basic
	@Column(name = "QUARTER_NUM")
	public Integer getQuarterNum() {
		return quarterNum;
	}

	/**
	 * Sets the quarter num.
	 *
	 * @param value the new quarter num
	 */
	public void setQuarterNum(final Integer value) {
		this.quarterNum = value;
	}

	/**
	 * Gets the total proposals.
	 *
	 * @return the total proposals
	 */
	@Basic
	@Column(name = "TOTAL_PROPOSALS", precision = 20)
	public long getTotalProposals() {
		return totalProposals;
	}

	/**
	 * Sets the total proposals.
	 *
	 * @param value the new total proposals
	 */
	public void setTotalProposals(final long value) {
		this.totalProposals = value;
	}

	/**
	 * Gets the approved proposals.
	 *
	 * @return the approved proposals
	 */
	@Basic
	@Column(name = "APPROVED_PROPOSALS", precision = 20)
	public long getApprovedProposals() {
		return approvedProposals;
	}

	/**
	 * Sets the approved proposals.
	 *
	 * @param value the new approved proposals
	 */
	public void setApprovedProposals(final long value) {
		this.approvedProposals = value;
	}

	/**
	 * Gets the rejected proposals.
	 *
	 * @return the rejected proposals
	 */
	@Basic
	@Column(name = "REJECTED_PROPOSALS", precision = 20)
	public long getRejectedProposals() {
		return rejectedProposals;
	}

	/**
	 * Sets the rejected proposals.
	 *
	 * @param value the new rejected proposals
	 */
	public void setRejectedProposals(final long value) {
		this.rejectedProposals = value;
	}

	/**
	 * Gets the referred back proposals.
	 *
	 * @return the referred back proposals
	 */
	@Basic
	@Column(name = "REFERRED_BACK_PROPOSALS", precision = 20)
	public long getReferredBackProposals() {
		return referredBackProposals;
	}

	/**
	 * Sets the referred back proposals.
	 *
	 * @param value the new referred back proposals
	 */
	public void setReferredBackProposals(final long value) {
		this.referredBackProposals = value;
	}

	/**
	 * Gets the committee referral proposals.
	 *
	 * @return the committee referral proposals
	 */
	@Basic
	@Column(name = "COMMITTEE_REFERRAL_PROPOSALS", precision = 20)
	public long getCommitteeReferralProposals() {
		return committeeReferralProposals;
	}

	/**
	 * Sets the committee referral proposals.
	 *
	 * @param value the new committee referral proposals
	 */
	public void setCommitteeReferralProposals(final long value) {
		this.committeeReferralProposals = value;
	}

	/**
	 * Gets the other decisions.
	 *
	 * @return the other decisions
	 */
	@Basic
	@Column(name = "OTHER_DECISIONS", precision = 20)
	public long getOtherDecisions() {
		return otherDecisions;
	}

	/**
	 * Sets the other decisions.
	 *
	 * @param value the new other decisions
	 */
	public void setOtherDecisions(final long value) {
		this.otherDecisions = value;
	}

	/**
	 * Gets the approval rate.
	 *
	 * @return the approval rate
	 */
	@Basic
	@Column(name = "APPROVAL_RATE", precision = 5, scale = 2)
	public BigDecimal getApprovalRate() {
		return approvalRate;
	}

	/**
	 * Sets the approval rate.
	 *
	 * @param value the new approval rate
	 */
	public void setApprovalRate(final BigDecimal value) {
		this.approvalRate = value;
	}

	/**
	 * Gets the rejection rate.
	 *
	 * @return the rejection rate
	 */
	@Basic
	@Column(name = "REJECTION_RATE", precision = 5, scale = 2)
	public BigDecimal getRejectionRate() {
		return rejectionRate;
	}

	/**
	 * Sets the rejection rate.
	 *
	 * @param value the new rejection rate
	 */
	public void setRejectionRate(final BigDecimal value) {
		this.rejectionRate = value;
	}

	/**
	 * Gets the committee referral rate.
	 *
	 * @return the committee referral rate
	 */
	@Basic
	@Column(name = "COMMITTEE_REFERRAL_RATE", precision = 5, scale = 2)
	public BigDecimal getCommitteeReferralRate() {
		return committeeReferralRate;
	}

	/**
	 * Sets the committee referral rate.
	 *
	 * @param value the new committee referral rate
	 */
	public void setCommitteeReferralRate(final BigDecimal value) {
		this.committeeReferralRate = value;
	}

	/**
	 * Gets the earliest proposal date.
	 *
	 * @return the earliest proposal date
	 */
	@Basic
	@Column(name = "EARLIEST_PROPOSAL_DATE")
	@Temporal(TemporalType.DATE)
	public Date getEarliestProposalDate() {
		return earliestProposalDate;
	}

	/**
	 * Sets the earliest proposal date.
	 *
	 * @param value the new earliest proposal date
	 */
	public void setEarliestProposalDate(final Date value) {
		this.earliestProposalDate = value;
	}

	/**
	 * Gets the latest proposal date.
	 *
	 * @return the latest proposal date
	 */
	@Basic
	@Column(name = "LATEST_PROPOSAL_DATE")
	@Temporal(TemporalType.DATE)
	public Date getLatestProposalDate() {
		return latestProposalDate;
	}

	/**
	 * Sets the latest proposal date.
	 *
	 * @param value the new latest proposal date
	 */
	public void setLatestProposalDate(final Date value) {
		this.latestProposalDate = value;
	}

	/**
	 * With embedded id.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewMinistryDecisionImpact withEmbeddedId(final ViewMinistryDecisionImpactEmbeddedId value) {
		setEmbeddedId(value);
		return this;
	}

	/**
	 * With decision year.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewMinistryDecisionImpact withDecisionYear(final Integer value) {
		setDecisionYear(value);
		return this;
	}

	/**
	 * With quarter num.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewMinistryDecisionImpact withQuarterNum(final Integer value) {
		setQuarterNum(value);
		return this;
	}

	/**
	 * With total proposals.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewMinistryDecisionImpact withTotalProposals(final long value) {
		setTotalProposals(value);
		return this;
	}

	/**
	 * With approved proposals.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewMinistryDecisionImpact withApprovedProposals(final long value) {
		setApprovedProposals(value);
		return this;
	}

	/**
	 * With rejected proposals.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewMinistryDecisionImpact withRejectedProposals(final long value) {
		setRejectedProposals(value);
		return this;
	}

	/**
	 * With referred back proposals.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewMinistryDecisionImpact withReferredBackProposals(final long value) {
		setReferredBackProposals(value);
		return this;
	}

	/**
	 * With committee referral proposals.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewMinistryDecisionImpact withCommitteeReferralProposals(final long value) {
		setCommitteeReferralProposals(value);
		return this;
	}

	/**
	 * With other decisions.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewMinistryDecisionImpact withOtherDecisions(final long value) {
		setOtherDecisions(value);
		return this;
	}

	/**
	 * With approval rate.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewMinistryDecisionImpact withApprovalRate(final BigDecimal value) {
		setApprovalRate(value);
		return this;
	}

	/**
	 * With rejection rate.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewMinistryDecisionImpact withRejectionRate(final BigDecimal value) {
		setRejectionRate(value);
		return this;
	}

	/**
	 * With committee referral rate.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewMinistryDecisionImpact withCommitteeReferralRate(final BigDecimal value) {
		setCommitteeReferralRate(value);
		return this;
	}

	/**
	 * With earliest proposal date.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewMinistryDecisionImpact withEarliestProposalDate(final Date value) {
		setEarliestProposalDate(value);
		return this;
	}

	/**
	 * With latest proposal date.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewMinistryDecisionImpact withLatestProposalDate(final Date value) {
		setLatestProposalDate(value);
		return this;
	}

	@Override
	public final String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public final boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public final int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
