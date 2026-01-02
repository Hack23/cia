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

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hack23.cia.model.common.api.ModelObject;
import com.hack23.cia.model.internal.application.data.document.impl.RiksdagenDocumentPersonSummaryEmbeddedId;

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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenPoliticianDocumentDailySummary", propOrder = {
    "embeddedId",
    "total"
})
public class ViewRiksdagenPoliticianDocumentDailySummary implements Serializable, ModelObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The embedded id. */
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "publicDate", column = @Column(name = "EMBEDDED_ID_PUBLIC_DATE")),
		@AttributeOverride(name = "personId", column = @Column(name = "EMBEDDED_ID_PERSON_ID")),
		@AttributeOverride(name = "documentType", column = @Column(name = "EMBEDDED_ID_DOCUMENT_TYPE"))
	})
	@XmlElement(name = "embeddedId", required = true)
	protected RiksdagenDocumentPersonSummaryEmbeddedId embeddedId;

	/** The total count of documents. */
	@Column(name = "total", nullable = false)
	@XmlElement(name = "total", required = true)
	protected BigInteger total;

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
	public RiksdagenDocumentPersonSummaryEmbeddedId getEmbeddedId() {
		return embeddedId;
	}

	/**
	 * Sets the embedded id.
	 *
	 * @param embeddedId the new embedded id
	 */
	public void setEmbeddedId(final RiksdagenDocumentPersonSummaryEmbeddedId embeddedId) {
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

	/**
	 * With embedded id riksdagen document person summary embedded id.
	 *
	 * @param value the value
	 * @return the view riksdagen politician document daily summary
	 */
	public ViewRiksdagenPoliticianDocumentDailySummary withEmbeddedId(final RiksdagenDocumentPersonSummaryEmbeddedId value) {
		setEmbeddedId(value);
		return this;
	}

	/**
	 * With total view riksdagen politician document daily summary.
	 *
	 * @param value the value
	 * @return the view riksdagen politician document daily summary
	 */
	public ViewRiksdagenPoliticianDocumentDailySummary withTotal(final BigInteger value) {
		setTotal(value);
		return this;
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
