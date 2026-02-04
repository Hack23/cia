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
 * The Class ViewRiksdagenPoliticianDecisionPattern.
 * 
 * JPA Entity for view_riksdagen_politician_decision_pattern database view.
 * 
 * Tracks individual politician decision patterns from proposal data, enabling
 * analysis of politician-level proposal success rates, committee work effectiveness,
 * and legislative productivity. Provides granular insights into each politician's
 * decision-making record across different committees and time periods.
 * 
 * <p>Intelligence Value: ⭐⭐⭐⭐⭐ VERY HIGH</p>
 * <ul>
 * <li>Individual politician decision effectiveness tracking</li>
 * <li>Committee specialization and expertise analysis</li>
 * <li>Legislative productivity measurement per politician</li>
 * <li>Party discipline and independent decision patterns</li>
 * <li>Career progression and influence trajectory analysis</li>
 * </ul>
 * 
 * <p>Use Cases:</p>
 * <ul>
 * <li>Politician Scorecards: Individual performance metrics and rankings</li>
 * <li>Committee Expertise Mapping: Identify subject matter experts</li>
 * <li>Influence Analysis: Track decision success rates over time</li>
 * <li>Party Discipline Monitoring: Detect independent vs. party-line voting</li>
 * </ul>
 * 
 * @author intelligence-operative
 * @since v1.35 (Decision Intelligence)
 * @see <a href="https://github.com/Hack23/cia/issues/7919">Issue #7919</a>
 * @see com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyDecisionFlow
 * @see ViewRiksdagenPolitician Core politician information
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenPoliticianDecisionPattern", propOrder = {
    "embeddedId",
    "firstName",
    "lastName",
    "party",
    "committeeOrg",
    "decisionYear",
    "decisionMonthNum",
    "totalDecisions",
    "approvedDecisions",
    "rejectedDecisions",
    "referredBackDecisions",
    "otherDecisions",
    "approvalRate",
    "rejectionRate",
    "earliestDecisionDate",
    "latestDecisionDate"
})
@Entity(name = "ViewRiksdagenPoliticianDecisionPattern")
@Table(name = "view_riksdagen_politician_decision_pattern")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewRiksdagenPoliticianDecisionPattern implements ModelObject {

	private static final long serialVersionUID = 1L;

	@XmlElement(required = true)
	protected ViewRiksdagenPoliticianDecisionPatternEmbeddedId embeddedId;

	@XmlElement(name = "first_name")
	protected String firstName;

	@XmlElement(name = "last_name")
	protected String lastName;

	protected String party;

	@XmlElement(name = "committee_org")
	protected String committeeOrg;

	@XmlElement(name = "decision_year")
	protected Integer decisionYear;

	@XmlElement(name = "decision_month_num")
	protected Integer decisionMonthNum;

	@XmlElement(name = "total_decisions")
	protected long totalDecisions;

	@XmlElement(name = "approved_decisions")
	protected long approvedDecisions;

	@XmlElement(name = "rejected_decisions")
	protected long rejectedDecisions;

	@XmlElement(name = "referred_back_decisions")
	protected long referredBackDecisions;

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
		@AttributeOverride(name = "personId", column = @Column(name = "EMBEDDED_ID_PERSON_ID")),
		@AttributeOverride(name = "committee", column = @Column(name = "EMBEDDED_ID_COMMITTEE")),
		@AttributeOverride(name = "decisionMonth", column = @Column(name = "EMBEDDED_ID_DECISION_MONTH"))
	})
	public ViewRiksdagenPoliticianDecisionPatternEmbeddedId getEmbeddedId() {
		return embeddedId;
	}

	/**
	 * Sets the embedded id.
	 *
	 * @param value the new embedded id
	 */
	public void setEmbeddedId(final ViewRiksdagenPoliticianDecisionPatternEmbeddedId value) {
		this.embeddedId = value;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	@Basic
	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param value the new first name
	 */
	public void setFirstName(final String value) {
		this.firstName = value;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	@Basic
	@Column(name = "LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param value the new last name
	 */
	public void setLastName(final String value) {
		this.lastName = value;
	}

	/**
	 * Gets the party.
	 *
	 * @return the party
	 */
	@Basic
	@Column(name = "PARTY")
	public String getParty() {
		return party;
	}

	/**
	 * Sets the party.
	 *
	 * @param value the new party
	 */
	public void setParty(final String value) {
		this.party = value;
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
	 * Gets the total decisions.
	 *
	 * @return the total decisions
	 */
	@Basic
	@Column(name = "TOTAL_DECISIONS", precision = 20)
	public long getTotalDecisions() {
		return totalDecisions;
	}

	/**
	 * Sets the total decisions.
	 *
	 * @param value the new total decisions
	 */
	public void setTotalDecisions(final long value) {
		this.totalDecisions = value;
	}

	/**
	 * Gets the approved decisions.
	 *
	 * @return the approved decisions
	 */
	@Basic
	@Column(name = "APPROVED_DECISIONS", precision = 20)
	public long getApprovedDecisions() {
		return approvedDecisions;
	}

	/**
	 * Sets the approved decisions.
	 *
	 * @param value the new approved decisions
	 */
	public void setApprovedDecisions(final long value) {
		this.approvedDecisions = value;
	}

	/**
	 * Gets the rejected decisions.
	 *
	 * @return the rejected decisions
	 */
	@Basic
	@Column(name = "REJECTED_DECISIONS", precision = 20)
	public long getRejectedDecisions() {
		return rejectedDecisions;
	}

	/**
	 * Sets the rejected decisions.
	 *
	 * @param value the new rejected decisions
	 */
	public void setRejectedDecisions(final long value) {
		this.rejectedDecisions = value;
	}

	/**
	 * Gets the referred back decisions.
	 *
	 * @return the referred back decisions
	 */
	@Basic
	@Column(name = "REFERRED_BACK_DECISIONS", precision = 20)
	public long getReferredBackDecisions() {
		return referredBackDecisions;
	}

	/**
	 * Sets the referred back decisions.
	 *
	 * @param value the new referred back decisions
	 */
	public void setReferredBackDecisions(final long value) {
		this.referredBackDecisions = value;
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
	public ViewRiksdagenPoliticianDecisionPattern withEmbeddedId(final ViewRiksdagenPoliticianDecisionPatternEmbeddedId value) {
		setEmbeddedId(value);
		return this;
	}

	/**
	 * With first name.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPoliticianDecisionPattern withFirstName(final String value) {
		setFirstName(value);
		return this;
	}

	/**
	 * With last name.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPoliticianDecisionPattern withLastName(final String value) {
		setLastName(value);
		return this;
	}

	/**
	 * With party.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPoliticianDecisionPattern withParty(final String value) {
		setParty(value);
		return this;
	}

	/**
	 * With committee org.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPoliticianDecisionPattern withCommitteeOrg(final String value) {
		setCommitteeOrg(value);
		return this;
	}

	/**
	 * With decision year.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPoliticianDecisionPattern withDecisionYear(final Integer value) {
		setDecisionYear(value);
		return this;
	}

	/**
	 * With decision month num.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPoliticianDecisionPattern withDecisionMonthNum(final Integer value) {
		setDecisionMonthNum(value);
		return this;
	}

	/**
	 * With total decisions.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPoliticianDecisionPattern withTotalDecisions(final long value) {
		setTotalDecisions(value);
		return this;
	}

	/**
	 * With approved decisions.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPoliticianDecisionPattern withApprovedDecisions(final long value) {
		setApprovedDecisions(value);
		return this;
	}

	/**
	 * With rejected decisions.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPoliticianDecisionPattern withRejectedDecisions(final long value) {
		setRejectedDecisions(value);
		return this;
	}

	/**
	 * With referred back decisions.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPoliticianDecisionPattern withReferredBackDecisions(final long value) {
		setReferredBackDecisions(value);
		return this;
	}

	/**
	 * With other decisions.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPoliticianDecisionPattern withOtherDecisions(final long value) {
		setOtherDecisions(value);
		return this;
	}

	/**
	 * With approval rate.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPoliticianDecisionPattern withApprovalRate(final BigDecimal value) {
		setApprovalRate(value);
		return this;
	}

	/**
	 * With rejection rate.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPoliticianDecisionPattern withRejectionRate(final BigDecimal value) {
		setRejectionRate(value);
		return this;
	}

	/**
	 * With earliest decision date.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPoliticianDecisionPattern withEarliestDecisionDate(final Date value) {
		setEarliestDecisionDate(value);
		return this;
	}

	/**
	 * With latest decision date.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewRiksdagenPoliticianDecisionPattern withLatestDecisionDate(final Date value) {
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
