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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * JPA entity for view_ministry_productivity_matrix database view.
 * 
 * Intelligence Purpose: Annual ministry productivity benchmarking with quartile
 * rankings and performance assessments. Enables comparison of ministry output
 * against peer institutions using statistical benchmarks.
 * 
 * Created by: Database schema v1.0+
 * Risk Rules Supported: M-01 through M-04 (Ministry productivity rules)
 * 
 * Provides percentile-based ministry productivity classification for identification
 * of high-performing and underperforming government institutions.
 */
@Entity(name = "ViewMinistryProductivityMatrix")
@Table(name = "view_ministry_productivity_matrix")
public class ViewMinistryProductivityMatrix implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The Class CompositePrimaryKey.
	 * 
	 * <p>Composite primary key combining organization code and year.</p>
	 */
	@Embeddable
	public static class CompositePrimaryKey implements Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** The org code. */
		@Column(name = "org_code", nullable = false, length = 255)
		private String orgCode;

		/** The year. */
		@Column(name = "year", nullable = false)
		private Integer year;

		/**
		 * Instantiates a new composite primary key.
		 */
		public CompositePrimaryKey() {
			super();
		}

		/**
		 * Instantiates a new composite primary key.
		 *
		 * @param orgCode the org code
		 * @param year the year
		 */
		public CompositePrimaryKey(final String orgCode, final Integer year) {
			super();
			this.orgCode = orgCode;
			this.year = year;
		}

		/**
		 * Gets the org code.
		 *
		 * @return the org code
		 */
		public String getOrgCode() {
			return orgCode;
		}

		/**
		 * Sets the org code.
		 *
		 * @param orgCode the new org code
		 */
		public void setOrgCode(final String orgCode) {
			this.orgCode = orgCode;
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

	/** The embedded id. */
	@EmbeddedId
	private CompositePrimaryKey embeddedId;

	/** The short code. */
	@Column(name = "short_code", length = 255)
	private String shortCode;

	/** The name. */
	@Column(name = "name", length = 255)
	private String name;

	/** The documents produced. */
	@Column(name = "documents_produced")
	private Long documentsProduced;

	/** The propositions. */
	@Column(name = "propositions")
	private Long propositions;

	/** The government bills. */
	@Column(name = "government_bills")
	private Long governmentBills;

	/** The unique contributors. */
	@Column(name = "unique_contributors")
	private Long uniqueContributors;

	/** The earliest document. */
	@Column(name = "earliest_document")
	@Temporal(TemporalType.DATE)
	private Date earliestDocument;

	/** The latest document. */
	@Column(name = "latest_document")
	@Temporal(TemporalType.DATE)
	private Date latestDocument;

	/** The median documents. */
	@Column(name = "median_documents", precision = 10, scale = 2)
	private BigDecimal medianDocuments;

	/** The avg documents. */
	@Column(name = "avg_documents", precision = 10, scale = 2)
	private BigDecimal avgDocuments;

	/** The p25 documents. */
	@Column(name = "p25_documents", precision = 10, scale = 2)
	private BigDecimal p25Documents;

	/** The p75 documents. */
	@Column(name = "p75_documents", precision = 10, scale = 2)
	private BigDecimal p75Documents;

	/** The pct vs average. */
	@Column(name = "pct_vs_average", precision = 10, scale = 2)
	private BigDecimal pctVsAverage;

	/** The productivity quartile. */
	@Column(name = "productivity_quartile", length = 50)
	private String productivityQuartile;

	/** The performance assessment. */
	@Column(name = "performance_assessment", length = 255)
	private String performanceAssessment;

	/**
	 * Instantiates a new view ministry productivity matrix.
	 */
	public ViewMinistryProductivityMatrix() {
		super();
	}

	/**
	 * Gets the embedded id.
	 *
	 * @return the embedded id
	 */
	public CompositePrimaryKey getEmbeddedId() {
		return embeddedId;
	}

	/**
	 * Sets the embedded id.
	 *
	 * @param embeddedId the new embedded id
	 */
	public void setEmbeddedId(final CompositePrimaryKey embeddedId) {
		this.embeddedId = embeddedId;
	}

	/**
	 * Gets the short code.
	 *
	 * @return the short code
	 */
	public String getShortCode() {
		return shortCode;
	}

	/**
	 * Sets the short code.
	 *
	 * @param shortCode the new short code
	 */
	public void setShortCode(final String shortCode) {
		this.shortCode = shortCode;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(final String name) {
		this.name = name;
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
	 * Gets the propositions.
	 *
	 * @return the propositions
	 */
	public Long getPropositions() {
		return propositions;
	}

	/**
	 * Sets the propositions.
	 *
	 * @param propositions the new propositions
	 */
	public void setPropositions(final Long propositions) {
		this.propositions = propositions;
	}

	/**
	 * Gets the government bills.
	 *
	 * @return the government bills
	 */
	public Long getGovernmentBills() {
		return governmentBills;
	}

	/**
	 * Sets the government bills.
	 *
	 * @param governmentBills the new government bills
	 */
	public void setGovernmentBills(final Long governmentBills) {
		this.governmentBills = governmentBills;
	}

	/**
	 * Gets the unique contributors.
	 *
	 * @return the unique contributors
	 */
	public Long getUniqueContributors() {
		return uniqueContributors;
	}

	/**
	 * Sets the unique contributors.
	 *
	 * @param uniqueContributors the new unique contributors
	 */
	public void setUniqueContributors(final Long uniqueContributors) {
		this.uniqueContributors = uniqueContributors;
	}

	/**
	 * Gets the earliest document.
	 *
	 * @return the earliest document
	 */
	public Date getEarliestDocument() {
		return earliestDocument;
	}

	/**
	 * Sets the earliest document.
	 *
	 * @param earliestDocument the new earliest document
	 */
	public void setEarliestDocument(final Date earliestDocument) {
		this.earliestDocument = earliestDocument;
	}

	/**
	 * Gets the latest document.
	 *
	 * @return the latest document
	 */
	public Date getLatestDocument() {
		return latestDocument;
	}

	/**
	 * Sets the latest document.
	 *
	 * @param latestDocument the new latest document
	 */
	public void setLatestDocument(final Date latestDocument) {
		this.latestDocument = latestDocument;
	}

	/**
	 * Gets the median documents.
	 *
	 * @return the median documents
	 */
	public BigDecimal getMedianDocuments() {
		return medianDocuments;
	}

	/**
	 * Sets the median documents.
	 *
	 * @param medianDocuments the new median documents
	 */
	public void setMedianDocuments(final BigDecimal medianDocuments) {
		this.medianDocuments = medianDocuments;
	}

	/**
	 * Gets the avg documents.
	 *
	 * @return the avg documents
	 */
	public BigDecimal getAvgDocuments() {
		return avgDocuments;
	}

	/**
	 * Sets the avg documents.
	 *
	 * @param avgDocuments the new avg documents
	 */
	public void setAvgDocuments(final BigDecimal avgDocuments) {
		this.avgDocuments = avgDocuments;
	}

	/**
	 * Gets the p25 documents.
	 *
	 * @return the p25 documents
	 */
	public BigDecimal getP25Documents() {
		return p25Documents;
	}

	/**
	 * Sets the p25 documents.
	 *
	 * @param p25Documents the new p25 documents
	 */
	public void setP25Documents(final BigDecimal p25Documents) {
		this.p25Documents = p25Documents;
	}

	/**
	 * Gets the p75 documents.
	 *
	 * @return the p75 documents
	 */
	public BigDecimal getP75Documents() {
		return p75Documents;
	}

	/**
	 * Sets the p75 documents.
	 *
	 * @param p75Documents the new p75 documents
	 */
	public void setP75Documents(final BigDecimal p75Documents) {
		this.p75Documents = p75Documents;
	}

	/**
	 * Gets the pct vs average.
	 *
	 * @return the pct vs average
	 */
	public BigDecimal getPctVsAverage() {
		return pctVsAverage;
	}

	/**
	 * Sets the pct vs average.
	 *
	 * @param pctVsAverage the new pct vs average
	 */
	public void setPctVsAverage(final BigDecimal pctVsAverage) {
		this.pctVsAverage = pctVsAverage;
	}

	/**
	 * Gets the productivity quartile.
	 *
	 * @return the productivity quartile
	 */
	public String getProductivityQuartile() {
		return productivityQuartile;
	}

	/**
	 * Sets the productivity quartile.
	 *
	 * @param productivityQuartile the new productivity quartile
	 */
	public void setProductivityQuartile(final String productivityQuartile) {
		this.productivityQuartile = productivityQuartile;
	}

	/**
	 * Gets the performance assessment.
	 *
	 * @return the performance assessment
	 */
	public String getPerformanceAssessment() {
		return performanceAssessment;
	}

	/**
	 * Sets the performance assessment.
	 *
	 * @param performanceAssessment the new performance assessment
	 */
	public void setPerformanceAssessment(final String performanceAssessment) {
		this.performanceAssessment = performanceAssessment;
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
