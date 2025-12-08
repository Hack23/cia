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
package com.hack23.cia.service.data.impl.export;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for temporal trends JSON export.
 * 
 * @author intelligence-operative
 * @since v1.36
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemporalTrendsExportDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("metadata")
	private ExportMetadata metadata;

	@JsonProperty("trends")
	private List<TrendDataPoint> trends;

	public TemporalTrendsExportDTO() {
		this.trends = new ArrayList<>();
	}

	public ExportMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(final ExportMetadata metadata) {
		this.metadata = metadata;
	}

	public List<TrendDataPoint> getTrends() {
		return Collections.unmodifiableList(trends);
	}

	public void setTrends(final List<TrendDataPoint> trends) {
		this.trends = trends;
	}

	/**
	 * Trend data point.
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class TrendDataPoint implements Serializable {
		private static final long serialVersionUID = 1L;

		@JsonProperty("decisionDay")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
		private Date decisionDay;

		@JsonProperty("dailyDecisions")
		private Long dailyDecisions;

		@JsonProperty("dailyApprovalRate")
		private BigDecimal dailyApprovalRate;

		@JsonProperty("approvedDecisions")
		private Long approvedDecisions;

		@JsonProperty("rejectedDecisions")
		private Long rejectedDecisions;

		@JsonProperty("referredBackDecisions")
		private Long referredBackDecisions;

		@JsonProperty("committeeReferralDecisions")
		private Long committeeReferralDecisions;

		@JsonProperty("ma7dayDecisions")
		private BigDecimal ma7dayDecisions;

		@JsonProperty("ma30dayDecisions")
		private BigDecimal ma30dayDecisions;

		@JsonProperty("ma90dayDecisions")
		private BigDecimal ma90dayDecisions;

		@JsonProperty("ma30dayApprovalRate")
		private BigDecimal ma30dayApprovalRate;

		@JsonProperty("decisionsLastYear")
		private Long decisionsLastYear;

		@JsonProperty("yoyDecisionsChange")
		private Long yoyDecisionsChange;

		@JsonProperty("yoyDecisionsChangePct")
		private BigDecimal yoyDecisionsChangePct;

		@JsonProperty("decisionYear")
		private Integer decisionYear;

		@JsonProperty("decisionMonth")
		private Integer decisionMonth;

		@JsonProperty("decisionWeek")
		private Integer decisionWeek;

		@JsonProperty("dayOfWeek")
		private Integer dayOfWeek;

		// Getters and setters
		public Date getDecisionDay() {
			return decisionDay != null ? new Date(decisionDay.getTime()) : null;
		}

		public void setDecisionDay(final Date decisionDay) {
			this.decisionDay = decisionDay != null ? new Date(decisionDay.getTime()) : null;
		}

		public Long getDailyDecisions() {
			return dailyDecisions;
		}

		public void setDailyDecisions(final Long dailyDecisions) {
			this.dailyDecisions = dailyDecisions;
		}

		public BigDecimal getDailyApprovalRate() {
			return dailyApprovalRate;
		}

		public void setDailyApprovalRate(final BigDecimal dailyApprovalRate) {
			this.dailyApprovalRate = dailyApprovalRate;
		}

		public Long getApprovedDecisions() {
			return approvedDecisions;
		}

		public void setApprovedDecisions(final Long approvedDecisions) {
			this.approvedDecisions = approvedDecisions;
		}

		public Long getRejectedDecisions() {
			return rejectedDecisions;
		}

		public void setRejectedDecisions(final Long rejectedDecisions) {
			this.rejectedDecisions = rejectedDecisions;
		}

		public Long getReferredBackDecisions() {
			return referredBackDecisions;
		}

		public void setReferredBackDecisions(final Long referredBackDecisions) {
			this.referredBackDecisions = referredBackDecisions;
		}

		public Long getCommitteeReferralDecisions() {
			return committeeReferralDecisions;
		}

		public void setCommitteeReferralDecisions(final Long committeeReferralDecisions) {
			this.committeeReferralDecisions = committeeReferralDecisions;
		}

		public BigDecimal getMa7dayDecisions() {
			return ma7dayDecisions;
		}

		public void setMa7dayDecisions(final BigDecimal ma7dayDecisions) {
			this.ma7dayDecisions = ma7dayDecisions;
		}

		public BigDecimal getMa30dayDecisions() {
			return ma30dayDecisions;
		}

		public void setMa30dayDecisions(final BigDecimal ma30dayDecisions) {
			this.ma30dayDecisions = ma30dayDecisions;
		}

		public BigDecimal getMa90dayDecisions() {
			return ma90dayDecisions;
		}

		public void setMa90dayDecisions(final BigDecimal ma90dayDecisions) {
			this.ma90dayDecisions = ma90dayDecisions;
		}

		public BigDecimal getMa30dayApprovalRate() {
			return ma30dayApprovalRate;
		}

		public void setMa30dayApprovalRate(final BigDecimal ma30dayApprovalRate) {
			this.ma30dayApprovalRate = ma30dayApprovalRate;
		}

		public Long getDecisionsLastYear() {
			return decisionsLastYear;
		}

		public void setDecisionsLastYear(final Long decisionsLastYear) {
			this.decisionsLastYear = decisionsLastYear;
		}

		public Long getYoyDecisionsChange() {
			return yoyDecisionsChange;
		}

		public void setYoyDecisionsChange(final Long yoyDecisionsChange) {
			this.yoyDecisionsChange = yoyDecisionsChange;
		}

		public BigDecimal getYoyDecisionsChangePct() {
			return yoyDecisionsChangePct;
		}

		public void setYoyDecisionsChangePct(final BigDecimal yoyDecisionsChangePct) {
			this.yoyDecisionsChangePct = yoyDecisionsChangePct;
		}

		public Integer getDecisionYear() {
			return decisionYear;
		}

		public void setDecisionYear(final Integer decisionYear) {
			this.decisionYear = decisionYear;
		}

		public Integer getDecisionMonth() {
			return decisionMonth;
		}

		public void setDecisionMonth(final Integer decisionMonth) {
			this.decisionMonth = decisionMonth;
		}

		public Integer getDecisionWeek() {
			return decisionWeek;
		}

		public void setDecisionWeek(final Integer decisionWeek) {
			this.decisionWeek = decisionWeek;
		}

		public Integer getDayOfWeek() {
			return dayOfWeek;
		}

		public void setDayOfWeek(final Integer dayOfWeek) {
			this.dayOfWeek = dayOfWeek;
		}
	}
}
