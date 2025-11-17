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
 * JPA entity for view_committee_productivity_matrix database view.
 * 
 * Intelligence Purpose: Tracks committee productivity metrics over time with
 * quarterly aggregation, trend analysis, and automated productivity classifications.
 * Provides comprehensive assessment of committee performance patterns and efficiency.
 * 
 * Created by: Liquibase v1.30 (OSINT Performance Tracking)
 * Risk Rules Supported: C-01 to C-04 (Committee performance and productivity rules)
 */
@Entity(name = "ViewCommitteeProductivityMatrix")
@Table(name = "view_committee_productivity_matrix")
public class ViewCommitteeProductivityMatrix implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "committee_code", nullable = false, length = 50)
	private String committeeCode;

	@Id
	@Column(name = "period_start", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date periodStart;

	@Column(name = "committee_name", length = 255)
	private String committeeName;

	@Column(name = "committee_category", length = 100)
	private String committeeCategory;

	@Column(name = "period_end")
	@Temporal(TemporalType.TIMESTAMP)
	private Date periodEnd;

	@Column(name = "year")
	private Integer year;

	@Column(name = "quarter")
	private Integer quarter;

	@Column(name = "total_documents")
	private Long totalDocuments;

	@Column(name = "committee_reports")
	private Long committeeReports;

	@Column(name = "motions_handled")
	private Long motionsHandled;

	@Column(name = "active_members")
	private Integer activeMembers;

	@Column(name = "documents_per_member", precision = 10, scale = 2)
	private BigDecimal documentsPerMember;

	@Column(name = "reports_per_member", precision = 10, scale = 2)
	private BigDecimal reportsPerMember;

	@Column(name = "document_change")
	private Long documentChange;

	@Column(name = "document_change_pct", precision = 10, scale = 2)
	private BigDecimal documentChangePct;

	@Column(name = "ma_4quarter_documents", precision = 10, scale = 2)
	private BigDecimal movingAvg4QuarterDocuments;

	@Column(name = "period_avg_documents", precision = 10, scale = 2)
	private BigDecimal periodAvgDocuments;

	@Column(name = "period_median_documents", precision = 10, scale = 2)
	private BigDecimal periodMedianDocuments;

	@Column(name = "period_max_documents")
	private Long periodMaxDocuments;

	@Column(name = "period_min_documents")
	private Long periodMinDocuments;

	@Column(name = "vs_average", precision = 10, scale = 2)
	private BigDecimal vsAverage;

	@Column(name = "vs_average_pct", precision = 10, scale = 2)
	private BigDecimal vsAveragePct;

	@Column(name = "productivity_level", length = 50)
	private String productivityLevel;

	@Column(name = "productivity_trend", length = 50)
	private String productivityTrend;

	@Column(name = "productivity_assessment", length = 255)
	private String productivityAssessment;

	@Column(name = "first_document_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date firstDocumentDate;

	@Column(name = "last_document_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastDocumentDate;

	@Column(name = "activity_span_days")
	private Integer activitySpanDays;

	/**
	 * Default constructor.
	 */
	public ViewCommitteeProductivityMatrix() {
		super();
	}

	// Getters and Setters

	public String getCommitteeCode() {
		return committeeCode;
	}

	public void setCommitteeCode(String committeeCode) {
		this.committeeCode = committeeCode;
	}

	public Date getPeriodStart() {
		return periodStart;
	}

	public void setPeriodStart(Date periodStart) {
		this.periodStart = periodStart;
	}

	public String getCommitteeName() {
		return committeeName;
	}

	public void setCommitteeName(String committeeName) {
		this.committeeName = committeeName;
	}

	public String getCommitteeCategory() {
		return committeeCategory;
	}

	public void setCommitteeCategory(String committeeCategory) {
		this.committeeCategory = committeeCategory;
	}

	public Date getPeriodEnd() {
		return periodEnd;
	}

	public void setPeriodEnd(Date periodEnd) {
		this.periodEnd = periodEnd;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getQuarter() {
		return quarter;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	public Long getTotalDocuments() {
		return totalDocuments;
	}

	public void setTotalDocuments(Long totalDocuments) {
		this.totalDocuments = totalDocuments;
	}

	public Long getCommitteeReports() {
		return committeeReports;
	}

	public void setCommitteeReports(Long committeeReports) {
		this.committeeReports = committeeReports;
	}

	public Long getMotionsHandled() {
		return motionsHandled;
	}

	public void setMotionsHandled(Long motionsHandled) {
		this.motionsHandled = motionsHandled;
	}

	public Integer getActiveMembers() {
		return activeMembers;
	}

	public void setActiveMembers(Integer activeMembers) {
		this.activeMembers = activeMembers;
	}

	public BigDecimal getDocumentsPerMember() {
		return documentsPerMember;
	}

	public void setDocumentsPerMember(BigDecimal documentsPerMember) {
		this.documentsPerMember = documentsPerMember;
	}

	public BigDecimal getReportsPerMember() {
		return reportsPerMember;
	}

	public void setReportsPerMember(BigDecimal reportsPerMember) {
		this.reportsPerMember = reportsPerMember;
	}

	public Long getDocumentChange() {
		return documentChange;
	}

	public void setDocumentChange(Long documentChange) {
		this.documentChange = documentChange;
	}

	public BigDecimal getDocumentChangePct() {
		return documentChangePct;
	}

	public void setDocumentChangePct(BigDecimal documentChangePct) {
		this.documentChangePct = documentChangePct;
	}

	public BigDecimal getMovingAvg4QuarterDocuments() {
		return movingAvg4QuarterDocuments;
	}

	public void setMovingAvg4QuarterDocuments(BigDecimal movingAvg4QuarterDocuments) {
		this.movingAvg4QuarterDocuments = movingAvg4QuarterDocuments;
	}

	public BigDecimal getPeriodAvgDocuments() {
		return periodAvgDocuments;
	}

	public void setPeriodAvgDocuments(BigDecimal periodAvgDocuments) {
		this.periodAvgDocuments = periodAvgDocuments;
	}

	public BigDecimal getPeriodMedianDocuments() {
		return periodMedianDocuments;
	}

	public void setPeriodMedianDocuments(BigDecimal periodMedianDocuments) {
		this.periodMedianDocuments = periodMedianDocuments;
	}

	public Long getPeriodMaxDocuments() {
		return periodMaxDocuments;
	}

	public void setPeriodMaxDocuments(Long periodMaxDocuments) {
		this.periodMaxDocuments = periodMaxDocuments;
	}

	public Long getPeriodMinDocuments() {
		return periodMinDocuments;
	}

	public void setPeriodMinDocuments(Long periodMinDocuments) {
		this.periodMinDocuments = periodMinDocuments;
	}

	public BigDecimal getVsAverage() {
		return vsAverage;
	}

	public void setVsAverage(BigDecimal vsAverage) {
		this.vsAverage = vsAverage;
	}

	public BigDecimal getVsAveragePct() {
		return vsAveragePct;
	}

	public void setVsAveragePct(BigDecimal vsAveragePct) {
		this.vsAveragePct = vsAveragePct;
	}

	public String getProductivityLevel() {
		return productivityLevel;
	}

	public void setProductivityLevel(String productivityLevel) {
		this.productivityLevel = productivityLevel;
	}

	public String getProductivityTrend() {
		return productivityTrend;
	}

	public void setProductivityTrend(String productivityTrend) {
		this.productivityTrend = productivityTrend;
	}

	public String getProductivityAssessment() {
		return productivityAssessment;
	}

	public void setProductivityAssessment(String productivityAssessment) {
		this.productivityAssessment = productivityAssessment;
	}

	public Date getFirstDocumentDate() {
		return firstDocumentDate;
	}

	public void setFirstDocumentDate(Date firstDocumentDate) {
		this.firstDocumentDate = firstDocumentDate;
	}

	public Date getLastDocumentDate() {
		return lastDocumentDate;
	}

	public void setLastDocumentDate(Date lastDocumentDate) {
		this.lastDocumentDate = lastDocumentDate;
	}

	public Integer getActivitySpanDays() {
		return activitySpanDays;
	}

	public void setActivitySpanDays(Integer activitySpanDays) {
		this.activitySpanDays = activitySpanDays;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final ViewCommitteeProductivityMatrix other = (ViewCommitteeProductivityMatrix) obj;
		return new EqualsBuilder()
				.append(committeeCode, other.committeeCode)
				.append(periodStart, other.periodStart)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(committeeCode)
				.append(periodStart)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("committeeCode", committeeCode)
				.append("periodStart", periodStart)
				.append("committeeName", committeeName)
				.append("productivityLevel", productivityLevel)
				.append("productivityAssessment", productivityAssessment)
				.toString();
	}
}
