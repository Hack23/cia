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
package com.hack23.cia.model.internal.application.data.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * The Class ViewDecisionTemporalTrends.
 * 
 * JPA Entity for view_decision_temporal_trends database view.
 * 
 * Provides daily decision-making trends with moving averages and year-over-year
 * comparisons. Tracks decision volume, approval rates, and temporal patterns to
 * identify seasonal variations, long-term trends, and anomalies in legislative
 * decision-making activity.
 * 
 * <p>Intelligence Value: ⭐⭐⭐⭐⭐ VERY HIGH</p>
 * <ul>
 * <li>Temporal pattern identification - detect seasonal and cyclical trends</li>
 * <li>Workload analysis - measure legislative productivity over time</li>
 * <li>Trend forecasting - predict future decision volumes and patterns</li>
 * <li>Anomaly detection - identify unusual spikes or drops in activity</li>
 * <li>Year-over-year comparison - track long-term changes in decision patterns</li>
 * </ul>
 * 
 * <p>Use Cases:</p>
 * <ul>
 * <li>Legislative Calendar Planning: Optimize scheduling based on patterns</li>
 * <li>Resource Allocation: Predict workload for committee staffing</li>
 * <li>Trend Analysis: Identify shifts in legislative productivity</li>
 * <li>Performance Benchmarking: Compare current vs historical activity</li>
 * <li>Crisis Detection: Spot unusual patterns indicating problems</li>
 * </ul>
 * 
 * @author intelligence-operative
 * @since v1.35 (Decision Intelligence)
 * @see <a href="https://github.com/Hack23/cia/issues/7921">Issue #7921</a>
 * @see ViewRiksdagenPartyDecisionFlow Party-level decision patterns
 * @see ViewRiksdagenPoliticianDecisionPattern Individual politician patterns
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewDecisionTemporalTrends", propOrder = {
    "decisionDay",
    "dailyDecisions",
    "dailyApprovalRate",
    "approvedDecisions",
    "rejectedDecisions",
    "referredBackDecisions",
    "ma7dayDecisions",
    "ma30dayDecisions",
    "ma90dayDecisions",
    "ma30dayApprovalRate",
    "decisionsLastYear",
    "yoyDecisionsChange",
    "yoyDecisionsChangePct",
    "decisionYear",
    "decisionMonth",
    "decisionWeek",
    "dayOfWeek"
})
@Entity(name = "ViewDecisionTemporalTrends")
@Table(name = "view_decision_temporal_trends")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewDecisionTemporalTrends implements ModelObject {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "decision_day", required = true)
	protected Date decisionDay;

	@XmlElement(name = "daily_decisions")
	protected long dailyDecisions;

	@XmlElement(name = "daily_approval_rate")
	protected BigDecimal dailyApprovalRate;

	@XmlElement(name = "approved_decisions")
	protected long approvedDecisions;

	@XmlElement(name = "rejected_decisions")
	protected long rejectedDecisions;

	@XmlElement(name = "referred_back_decisions")
	protected long referredBackDecisions;

	@XmlElement(name = "ma_7day_decisions")
	protected BigDecimal ma7dayDecisions;

	@XmlElement(name = "ma_30day_decisions")
	protected BigDecimal ma30dayDecisions;

	@XmlElement(name = "ma_90day_decisions")
	protected BigDecimal ma90dayDecisions;

	@XmlElement(name = "ma_30day_approval_rate")
	protected BigDecimal ma30dayApprovalRate;

	@XmlElement(name = "decisions_last_year")
	protected Long decisionsLastYear;

	@XmlElement(name = "yoy_decisions_change")
	protected Long yoyDecisionsChange;

	@XmlElement(name = "yoy_decisions_change_pct")
	protected BigDecimal yoyDecisionsChangePct;

	@XmlElement(name = "decision_year")
	protected Integer decisionYear;

	@XmlElement(name = "decision_month")
	protected Integer decisionMonth;

	@XmlElement(name = "decision_week")
	protected Integer decisionWeek;

	@XmlElement(name = "day_of_week")
	protected Integer dayOfWeek;

	/**
	 * Gets the decision day.
	 *
	 * @return the decision day
	 */
	@Id
	@Column(name = "DECISION_DAY")
	@Temporal(TemporalType.DATE)
	public Date getDecisionDay() {
		return decisionDay;
	}

	/**
	 * Sets the decision day.
	 *
	 * @param value the new decision day
	 */
	public void setDecisionDay(final Date value) {
		this.decisionDay = value;
	}

	/**
	 * Gets the daily decisions.
	 *
	 * @return the daily decisions
	 */
	@Basic
	@Column(name = "DAILY_DECISIONS", precision = 20)
	public long getDailyDecisions() {
		return dailyDecisions;
	}

	/**
	 * Sets the daily decisions.
	 *
	 * @param value the new daily decisions
	 */
	public void setDailyDecisions(final long value) {
		this.dailyDecisions = value;
	}

	/**
	 * Gets the daily approval rate.
	 *
	 * @return the daily approval rate
	 */
	@Basic
	@Column(name = "DAILY_APPROVAL_RATE", precision = 5, scale = 2)
	public BigDecimal getDailyApprovalRate() {
		return dailyApprovalRate;
	}

	/**
	 * Sets the daily approval rate.
	 *
	 * @param value the new daily approval rate
	 */
	public void setDailyApprovalRate(final BigDecimal value) {
		this.dailyApprovalRate = value;
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
	 * Gets the 7-day moving average decisions.
	 *
	 * @return the 7-day moving average
	 */
	@Basic
	@Column(name = "MA_7DAY_DECISIONS", precision = 10, scale = 2)
	public BigDecimal getMa7dayDecisions() {
		return ma7dayDecisions;
	}

	/**
	 * Sets the 7-day moving average decisions.
	 *
	 * @param value the new 7-day moving average
	 */
	public void setMa7dayDecisions(final BigDecimal value) {
		this.ma7dayDecisions = value;
	}

	/**
	 * Gets the 30-day moving average decisions.
	 *
	 * @return the 30-day moving average
	 */
	@Basic
	@Column(name = "MA_30DAY_DECISIONS", precision = 10, scale = 2)
	public BigDecimal getMa30dayDecisions() {
		return ma30dayDecisions;
	}

	/**
	 * Sets the 30-day moving average decisions.
	 *
	 * @param value the new 30-day moving average
	 */
	public void setMa30dayDecisions(final BigDecimal value) {
		this.ma30dayDecisions = value;
	}

	/**
	 * Gets the 90-day moving average decisions.
	 *
	 * @return the 90-day moving average
	 */
	@Basic
	@Column(name = "MA_90DAY_DECISIONS", precision = 10, scale = 2)
	public BigDecimal getMa90dayDecisions() {
		return ma90dayDecisions;
	}

	/**
	 * Sets the 90-day moving average decisions.
	 *
	 * @param value the new 90-day moving average
	 */
	public void setMa90dayDecisions(final BigDecimal value) {
		this.ma90dayDecisions = value;
	}

	/**
	 * Gets the 30-day moving average approval rate.
	 *
	 * @return the 30-day moving average approval rate
	 */
	@Basic
	@Column(name = "MA_30DAY_APPROVAL_RATE", precision = 5, scale = 2)
	public BigDecimal getMa30dayApprovalRate() {
		return ma30dayApprovalRate;
	}

	/**
	 * Sets the 30-day moving average approval rate.
	 *
	 * @param value the new 30-day moving average approval rate
	 */
	public void setMa30dayApprovalRate(final BigDecimal value) {
		this.ma30dayApprovalRate = value;
	}

	/**
	 * Gets the decisions last year.
	 *
	 * @return the decisions last year
	 */
	@Basic
	@Column(name = "DECISIONS_LAST_YEAR", precision = 20)
	public Long getDecisionsLastYear() {
		return decisionsLastYear;
	}

	/**
	 * Sets the decisions last year.
	 *
	 * @param value the new decisions last year
	 */
	public void setDecisionsLastYear(final Long value) {
		this.decisionsLastYear = value;
	}

	/**
	 * Gets the year-over-year decisions change.
	 *
	 * @return the year-over-year change
	 */
	@Basic
	@Column(name = "YOY_DECISIONS_CHANGE", precision = 20)
	public Long getYoyDecisionsChange() {
		return yoyDecisionsChange;
	}

	/**
	 * Sets the year-over-year decisions change.
	 *
	 * @param value the new year-over-year change
	 */
	public void setYoyDecisionsChange(final Long value) {
		this.yoyDecisionsChange = value;
	}

	/**
	 * Gets the year-over-year decisions change percentage.
	 *
	 * @return the year-over-year change percentage
	 */
	@Basic
	@Column(name = "YOY_DECISIONS_CHANGE_PCT", precision = 5, scale = 2)
	public BigDecimal getYoyDecisionsChangePct() {
		return yoyDecisionsChangePct;
	}

	/**
	 * Sets the year-over-year decisions change percentage.
	 *
	 * @param value the new year-over-year change percentage
	 */
	public void setYoyDecisionsChangePct(final BigDecimal value) {
		this.yoyDecisionsChangePct = value;
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
	 * Gets the decision month.
	 *
	 * @return the decision month
	 */
	@Basic
	@Column(name = "DECISION_MONTH")
	public Integer getDecisionMonth() {
		return decisionMonth;
	}

	/**
	 * Sets the decision month.
	 *
	 * @param value the new decision month
	 */
	public void setDecisionMonth(final Integer value) {
		this.decisionMonth = value;
	}

	/**
	 * Gets the decision week.
	 *
	 * @return the decision week
	 */
	@Basic
	@Column(name = "DECISION_WEEK")
	public Integer getDecisionWeek() {
		return decisionWeek;
	}

	/**
	 * Sets the decision week.
	 *
	 * @param value the new decision week
	 */
	public void setDecisionWeek(final Integer value) {
		this.decisionWeek = value;
	}

	/**
	 * Gets the day of week.
	 *
	 * @return the day of week
	 */
	@Basic
	@Column(name = "DAY_OF_WEEK")
	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * Sets the day of week.
	 *
	 * @param value the new day of week
	 */
	public void setDayOfWeek(final Integer value) {
		this.dayOfWeek = value;
	}

	/**
	 * With decision day.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewDecisionTemporalTrends withDecisionDay(final Date value) {
		setDecisionDay(value);
		return this;
	}

	/**
	 * With daily decisions.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewDecisionTemporalTrends withDailyDecisions(final long value) {
		setDailyDecisions(value);
		return this;
	}

	/**
	 * With daily approval rate.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewDecisionTemporalTrends withDailyApprovalRate(final BigDecimal value) {
		setDailyApprovalRate(value);
		return this;
	}

	/**
	 * With approved decisions.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewDecisionTemporalTrends withApprovedDecisions(final long value) {
		setApprovedDecisions(value);
		return this;
	}

	/**
	 * With rejected decisions.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewDecisionTemporalTrends withRejectedDecisions(final long value) {
		setRejectedDecisions(value);
		return this;
	}

	/**
	 * With referred back decisions.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewDecisionTemporalTrends withReferredBackDecisions(final long value) {
		setReferredBackDecisions(value);
		return this;
	}

	/**
	 * With 7-day moving average.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewDecisionTemporalTrends withMa7dayDecisions(final BigDecimal value) {
		setMa7dayDecisions(value);
		return this;
	}

	/**
	 * With 30-day moving average.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewDecisionTemporalTrends withMa30dayDecisions(final BigDecimal value) {
		setMa30dayDecisions(value);
		return this;
	}

	/**
	 * With 90-day moving average.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewDecisionTemporalTrends withMa90dayDecisions(final BigDecimal value) {
		setMa90dayDecisions(value);
		return this;
	}

	/**
	 * With 30-day approval rate.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewDecisionTemporalTrends withMa30dayApprovalRate(final BigDecimal value) {
		setMa30dayApprovalRate(value);
		return this;
	}

	/**
	 * With decisions last year.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewDecisionTemporalTrends withDecisionsLastYear(final Long value) {
		setDecisionsLastYear(value);
		return this;
	}

	/**
	 * With year-over-year change.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewDecisionTemporalTrends withYoyDecisionsChange(final Long value) {
		setYoyDecisionsChange(value);
		return this;
	}

	/**
	 * With year-over-year change percentage.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewDecisionTemporalTrends withYoyDecisionsChangePct(final BigDecimal value) {
		setYoyDecisionsChangePct(value);
		return this;
	}

	/**
	 * With decision year.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewDecisionTemporalTrends withDecisionYear(final Integer value) {
		setDecisionYear(value);
		return this;
	}

	/**
	 * With decision month.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewDecisionTemporalTrends withDecisionMonth(final Integer value) {
		setDecisionMonth(value);
		return this;
	}

	/**
	 * With decision week.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewDecisionTemporalTrends withDecisionWeek(final Integer value) {
		setDecisionWeek(value);
		return this;
	}

	/**
	 * With day of week.
	 *
	 * @param value the value
	 * @return the view
	 */
	public ViewDecisionTemporalTrends withDayOfWeek(final Integer value) {
		setDayOfWeek(value);
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
