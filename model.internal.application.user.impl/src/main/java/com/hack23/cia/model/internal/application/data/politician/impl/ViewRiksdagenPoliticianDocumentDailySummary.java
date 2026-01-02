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
import java.math.BigInteger;
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
 * The Class ViewRiksdagenPoliticianDocumentDailySummary.
 * 
 * <p>Maps to the materialized view <code>view_riksdagen_politician_document_daily_summary</code>
 * which provides daily aggregated counts of documents by politician and document type.</p>
 * 
 * <p><strong>Intelligence Purpose:</strong></p>
 * <ul>
 *   <li><strong>Temporal Analysis Framework:</strong> Daily politician productivity tracking and trend analysis</li>
 *   <li><strong>Pattern Recognition:</strong> Identifies daily document activity patterns and outliers</li>
 *   <li><strong>Performance Monitoring:</strong> Tracks individual politician legislative productivity by day</li>
 * </ul>
 * 
 * <p><strong>Key Analytics:</strong></p>
 * <ul>
 *   <li>Daily document counts per politician (motions, propositions, interpellations)</li>
 *   <li>Time-series analysis of legislative activity</li>
 *   <li>Productivity spikes and patterns detection</li>
 *   <li>Document type distribution by politician and date</li>
 * </ul>
 * 
 * <p><strong>Risk Indicators:</strong></p>
 * <ul>
 *   <li>Sudden productivity drops may indicate health issues or political challenges</li>
 *   <li>Abnormal activity spikes warrant investigation for quality concerns</li>
 *   <li>Extended periods of inactivity signal potential disengagement</li>
 * </ul>
 * 
 * <p><strong>OSINT Applications:</strong></p>
 * <ul>
 *   <li>Track daily legislative workflow and productivity</li>
 *   <li>Identify politicians' activity patterns and work habits</li>
 *   <li>Detect changes in engagement levels over time</li>
 *   <li>Support workload balancing and resource allocation analysis</li>
 * </ul>
 */
@Entity
@Table(name = "view_riksdagen_politician_document_daily_summary")
public class ViewRiksdagenPoliticianDocumentDailySummary implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The Class CompositePrimaryKey.
	 * 
	 * <p>Composite primary key combining public date, person ID, and document type.</p>
	 */
	@Embeddable
	public static class CompositePrimaryKey implements Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** The public date. */
		@Column(name = "embedded_id_public_date", nullable = false)
		@Temporal(TemporalType.DATE)
		private Date publicDate;

		/** The person id. */
		@Column(name = "embedded_id_person_id", nullable = false, length = 255)
		private String personId;

		/** The document type. */
		@Column(name = "embedded_id_document_type", nullable = false, length = 255)
		private String documentType;

		/**
		 * Instantiates a new composite primary key.
		 */
		public CompositePrimaryKey() {
			super();
		}

		/**
		 * Instantiates a new composite primary key.
		 *
		 * @param publicDate the public date
		 * @param personId the person id
		 * @param documentType the document type
		 */
		public CompositePrimaryKey(final Date publicDate, final String personId, final String documentType) {
			super();
			this.publicDate = publicDate;
			this.personId = personId;
			this.documentType = documentType;
		}

		/**
		 * Gets the public date.
		 *
		 * @return the public date
		 */
		public Date getPublicDate() {
			return publicDate;
		}

		/**
		 * Sets the public date.
		 *
		 * @param publicDate the new public date
		 */
		public void setPublicDate(final Date publicDate) {
			this.publicDate = publicDate;
		}

		/**
		 * Gets the person id.
		 *
		 * @return the person id
		 */
		public String getPersonId() {
			return personId;
		}

		/**
		 * Sets the person id.
		 *
		 * @param personId the new person id
		 */
		public void setPersonId(final String personId) {
			this.personId = personId;
		}

		/**
		 * Gets the document type.
		 *
		 * @return the document type
		 */
		public String getDocumentType() {
			return documentType;
		}

		/**
		 * Sets the document type.
		 *
		 * @param documentType the new document type
		 */
		public void setDocumentType(final String documentType) {
			this.documentType = documentType;
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

	/** The total count of documents. */
	@Column(name = "total", nullable = false)
	private BigInteger total;

	/**
	 * Instantiates a new view riksdagen politician document daily summary.
	 */
	public ViewRiksdagenPoliticianDocumentDailySummary() {
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
	 * Gets the total.
	 *
	 * @return the total
	 */
	public BigInteger getTotal() {
		return total;
	}

	/**
	 * Sets the total.
	 *
	 * @param total the new total
	 */
	public void setTotal(final BigInteger total) {
		this.total = total;
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
