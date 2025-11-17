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

import java.io.Serializable;
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
 * JPA entity for view_riksdagen_intelligence_dashboard database view.
 * 
 * Intelligence Purpose: Executive intelligence dashboard with stability
 * and coalition assessments providing high-level summary across all risk rules.
 * 
 * Created by: Liquibase v1.29 (Intelligence Operations Enhancement)
 * Risk Rules Supported: All 45 rules (executive summary)
 */
@Entity(name = "ViewRiksdagenIntelligenceDashboard")
@Table(name = "view_riksdagen_intelligence_dashboard")
public class ViewRiksdagenIntelligenceDashboard implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The stability assessment. */
	@Id
	@Column(name = "stability_assessment", nullable = false, length = 500)
	private String stabilityAssessment;

	/** The coalition assessment. */
	@Column(name = "coalition_assessment", length = 500)
	private String coalitionAssessment;

	/** The intelligence report timestamp. */
	@Column(name = "intelligence_report_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date intelligenceReportTimestamp;

	/**
	 * Instantiates a new view riksdagen intelligence dashboard.
	 */
	public ViewRiksdagenIntelligenceDashboard() {
		super();
	}

	/**
	 * Gets the stability assessment.
	 *
	 * @return the stability assessment
	 */
	public String getStabilityAssessment() {
		return stabilityAssessment;
	}

	/**
	 * Sets the stability assessment.
	 *
	 * @param stabilityAssessment the new stability assessment
	 */
	public void setStabilityAssessment(final String stabilityAssessment) {
		this.stabilityAssessment = stabilityAssessment;
	}

	/**
	 * Gets the coalition assessment.
	 *
	 * @return the coalition assessment
	 */
	public String getCoalitionAssessment() {
		return coalitionAssessment;
	}

	/**
	 * Sets the coalition assessment.
	 *
	 * @param coalitionAssessment the new coalition assessment
	 */
	public void setCoalitionAssessment(final String coalitionAssessment) {
		this.coalitionAssessment = coalitionAssessment;
	}

	/**
	 * Gets the intelligence report timestamp.
	 *
	 * @return the intelligence report timestamp
	 */
	public Date getIntelligenceReportTimestamp() {
		return intelligenceReportTimestamp;
	}

	/**
	 * Sets the intelligence report timestamp.
	 *
	 * @param intelligenceReportTimestamp the new intelligence report timestamp
	 */
	public void setIntelligenceReportTimestamp(final Date intelligenceReportTimestamp) {
		this.intelligenceReportTimestamp = intelligenceReportTimestamp;
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
