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
package com.hack23.cia.model.internal.application.data.party.impl;

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
 * The Class ViewRiksdagenPartyDecisionFlow.
 * 
 * JPA Entity for view_riksdagen_party_decision_flow database view.
 * 
 * Provides party-level decision flow analysis with approval rates,
 * decision patterns, and temporal trends. Aggregates proposal outcomes
 * (approved, rejected, referred back) by party, committee, decision type,
 * and time period to enable coalition effectiveness tracking and
 * legislative productivity analysis.
 * 
 * <p>Intelligence Value: ⭐⭐⭐⭐⭐ VERY HIGH</p>
 * <ul>
 * <li>Party decision effectiveness tracking - measure success rates by party</li>
 * <li>Coalition alignment analysis - assess inter-party cooperation patterns</li>
 * <li>Legislative productivity metrics - quantify output and efficiency</li>
 * <li>Committee specialization patterns - identify focus areas by party</li>
 * <li>Temporal trend analysis - track changes in decision patterns over time</li>
 * </ul>
 * 
 * <p>Use Cases:</p>
 * <ul>
 * <li>Party Performance Scorecards: Compare approval rates across parties</li>
 * <li>Coalition Stability Monitoring: Detect changes in decision cooperation</li>
 * <li>Legislative Forecasting: Predict proposal outcomes based on historical patterns</li>
 * <li>Strategic Analysis: Identify most effective committees for each party</li>
 * </ul>
 * 
 * @author intelligence-operative
 * @since v1.35 (Decision Intelligence)
 * @see <a href="https://github.com/Hack23/cia/issues/7918">Issue #7918</a>
 * @see ViewRiksdagenPoliticianDecisionPattern Individual politician patterns
 * @see ViewMinistryDecisionImpact Government proposal effectiveness
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenPartyDecisionFlow", propOrder = {
    "embeddedId",
    "committeeOrg",
    "decisionYear",
    "decisionMonthNum",
    "totalProposals",
    "approvedProposals",
    "rejectedProposals",
    "referredBackProposals",
    "otherDecisions",
    "approvalRate",
    "rejectionRate",
    "earliestDecisionDate",
    "latestDecisionDate"
})
@Entity(name = "ViewRiksdagenPartyDecisionFlow")
@Table(name = "view_riksdagen_party_decision_flow")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewRiksdagenPartyDecisionFlow implements ModelObject {

	private static final long serialVersionUID = 1L;

	@XmlElement(required = true)
	protected ViewRiksdagenPartyDecisionFlowEmbeddedId embeddedId;

	@XmlElement(name = "committee_org")
	protected String committeeOrg;

	@XmlElement(name = "decision_year")
	protected Integer decisionYear;

	@XmlElement(name = "decision_month_num")
	protected Integer decisionMonthNum;

	@XmlElement(name = "total_proposals")
	protected long totalProposals;

	@XmlElement(name = "approved_proposals")
	protected long approvedProposals;

	@XmlElement(name = "rejected_proposals")
	protected long rejectedProposals;

	@XmlElement(name = "referred_back_proposals")
	protected long referredBackProposals;

	@XmlElement(name = "other_decisions")
	protected long otherDecisions;

	@XmlElement(name = "approval_rate")
	protected BigDecimal approvalRate;

	@XmlElement(name = "rejection_rate")
	protected BigDecimal rejectionRate;

	@XmlElement(name = "earliest_decision_date")
	protected Date earliestDecisionDate;

	@XmlElement(name = "latest_decision_date")
	protected Date latestDecisionDate;

	/**
	 * Gets the embedded id.
	 *
	 * @return the embedded id
	 */
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "party", column = @Column(name = "EMBEDDED_ID_PARTY")),
		@AttributeOverride(name = "committee", column = @Column(name = "EMBEDDED_ID_COMMITTEE")),
		@AttributeOverride(name = "decisionType", column = @Column(name = "EMBEDDED_ID_DECISION_TYPE")),
		@AttributeOverride(name = "decisionMonth", column = @Column(name = "EMBEDDED_ID_DECISION_MONTH"))
	})
	public ViewRiksdagenPartyDecisionFlowEmbeddedId getEmbeddedId() {
		return embeddedId;
	}

	/**
	 * Sets the embedded id.
	 *
	 * @param value the new embedded id
	 */
	public void setEmbeddedId(final ViewRiksdagenPartyDecisionFlowEmbeddedId value) {
		this.embeddedId = value;
	}

	/**
	 * Gets the committee org.
	 *
	 * @return the committee org
	 */
	@Basic
	@Column(name = "COMMITTEE_ORG")
	public String getCommitteeOrg() {
		return committeeOrg;
	}

	/**
	 * Sets the committee org.
	 *
	 * @param value the new committee org
	 */
	public void setCommitteeOrg(final String value) {
		this.committeeOrg = value;
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
	 * Gets the decision month num.
	 *
	 * @return the decision month num
	 */
	@Basic
	@Column(name = "DECISION_MONTH_NUM")
	public Integer getDecisionMonthNum() {
		return decisionMonthNum;
	}

	/**
	 * Sets the decision month num.
	 *
	 * @param value the new decision month num
	 */
	public void setDecisionMonthNum(final Integer value) {
		this.decisionMonthNum = value;
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
	 * Gets the earliest decision date.
	 *
	 * @return the earliest decision date
	 */
	@Basic
	@Column(name = "EARLIEST_DECISION_DATE")
	@Temporal(TemporalType.DATE)
	public Date getEarliestDecisionDate() {
		return earliestDecisionDate;
	}

	/**
	 * Sets the earliest decision date.
	 *
	 * @param value the new earliest decision date
	 */
	public void setEarliestDecisionDate(final Date value) {
		this.earliestDecisionDate = value;
	}

	/**
	 * Gets the latest decision date.
	 *
	 * @return the latest decision date
	 */
	@Basic
	@Column(name = "LATEST_DECISION_DATE")
	@Temporal(TemporalType.DATE)
	public Date getLatestDecisionDate() {
		return latestDecisionDate;
	}

	/**
	 * Sets the latest decision date.
	 *
	 * @param value the new latest decision date
	 */
	public void setLatestDecisionDate(final Date value) {
		this.latestDecisionDate = value;
	}

	/**
	 * With embedded id.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPartyDecisionFlow withEmbeddedId(final ViewRiksdagenPartyDecisionFlowEmbeddedId value) {
		setEmbeddedId(value);
		return this;
	}

	/**
	 * With committee org.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPartyDecisionFlow withCommitteeOrg(final String value) {
		setCommitteeOrg(value);
		return this;
	}

	/**
	 * With decision year.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPartyDecisionFlow withDecisionYear(final Integer value) {
		setDecisionYear(value);
		return this;
	}

	/**
	 * With decision month num.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPartyDecisionFlow withDecisionMonthNum(final Integer value) {
		setDecisionMonthNum(value);
		return this;
	}

	/**
	 * With total proposals.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPartyDecisionFlow withTotalProposals(final long value) {
		setTotalProposals(value);
		return this;
	}

	/**
	 * With approved proposals.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPartyDecisionFlow withApprovedProposals(final long value) {
		setApprovedProposals(value);
		return this;
	}

	/**
	 * With rejected proposals.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPartyDecisionFlow withRejectedProposals(final long value) {
		setRejectedProposals(value);
		return this;
	}

	/**
	 * With referred back proposals.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPartyDecisionFlow withReferredBackProposals(final long value) {
		setReferredBackProposals(value);
		return this;
	}

	/**
	 * With other decisions.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPartyDecisionFlow withOtherDecisions(final long value) {
		setOtherDecisions(value);
		return this;
	}

	/**
	 * With approval rate.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPartyDecisionFlow withApprovalRate(final BigDecimal value) {
		setApprovalRate(value);
		return this;
	}

	/**
	 * With rejection rate.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPartyDecisionFlow withRejectionRate(final BigDecimal value) {
		setRejectionRate(value);
		return this;
	}

	/**
	 * With earliest decision date.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPartyDecisionFlow withEarliestDecisionDate(final Date value) {
		setEarliestDecisionDate(value);
		return this;
	}

	/**
	 * With latest decision date.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPartyDecisionFlow withLatestDecisionDate(final Date value) {
		setLatestDecisionDate(value);
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
