/*
 * Copyright 2010-2026 James Pether Sörling
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
package com.hack23.cia.model.internal.application.data.seasonal.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Immutable;

/**
 * The Class ViewRiksdagenElectionYearVsMidterm.
 * Database view for aggregate comparison of election years vs midterm years.
 * Three-row summary: ELECTION_YEARS, MIDTERM_YEARS, COMPARISON_RATIO.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_election_year_vs_midterm")
public class ViewRiksdagenElectionYearVsMidterm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "period_type", nullable = false)
	private String periodType;

	@Column(name = "avg_ballots")
	private BigDecimal avgBallots;

	@Column(name = "avg_documents")
	private BigDecimal avgDocuments;

	@Column(name = "avg_motions")
	private BigDecimal avgMotions;

	@Column(name = "avg_proposals")
	private BigDecimal avgProposals;

	@Column(name = "avg_attendance")
	private BigDecimal avgAttendance;

	@Column(name = "avg_active_politicians")
	private BigDecimal avgActivePoliticians;

	@Column(name = "year_count")
	private Long yearCount;

	@Column(name = "years", columnDefinition = "integer[]")
	private String years;

	@Column(name = "min_ballots")
	private BigDecimal minBallots;

	@Column(name = "max_ballots")
	private BigDecimal maxBallots;

	@Column(name = "stddev_ballots")
	private BigDecimal stddevBallots;

	@Column(name = "min_documents")
	private BigDecimal minDocuments;

	@Column(name = "max_documents")
	private BigDecimal maxDocuments;

	@Column(name = "stddev_documents")
	private BigDecimal stddevDocuments;

	/**
	 * Instantiates a new view riksdagen election year vs midterm.
	 */
	public ViewRiksdagenElectionYearVsMidterm() {
		super();
	}

	// Getters and setters

	public String getPeriodType() {
		return periodType;
	}

	public void setPeriodType(final String periodType) {
		this.periodType = periodType;
	}

	public BigDecimal getAvgBallots() {
		return avgBallots;
	}

	public void setAvgBallots(final BigDecimal avgBallots) {
		this.avgBallots = avgBallots;
	}

	public BigDecimal getAvgDocuments() {
		return avgDocuments;
	}

	public void setAvgDocuments(final BigDecimal avgDocuments) {
		this.avgDocuments = avgDocuments;
	}

	public BigDecimal getAvgMotions() {
		return avgMotions;
	}

	public void setAvgMotions(final BigDecimal avgMotions) {
		this.avgMotions = avgMotions;
	}

	public BigDecimal getAvgProposals() {
		return avgProposals;
	}

	public void setAvgProposals(final BigDecimal avgProposals) {
		this.avgProposals = avgProposals;
	}

	public BigDecimal getAvgAttendance() {
		return avgAttendance;
	}

	public void setAvgAttendance(final BigDecimal avgAttendance) {
		this.avgAttendance = avgAttendance;
	}

	public BigDecimal getAvgActivePoliticians() {
		return avgActivePoliticians;
	}

	public void setAvgActivePoliticians(final BigDecimal avgActivePoliticians) {
		this.avgActivePoliticians = avgActivePoliticians;
	}

	public Long getYearCount() {
		return yearCount;
	}

	public void setYearCount(final Long yearCount) {
		this.yearCount = yearCount;
	}

	public String getYears() {
		return years;
	}

	public void setYears(final String years) {
		this.years = years;
	}

	public BigDecimal getMinBallots() {
		return minBallots;
	}

	public void setMinBallots(final BigDecimal minBallots) {
		this.minBallots = minBallots;
	}

	public BigDecimal getMaxBallots() {
		return maxBallots;
	}

	public void setMaxBallots(final BigDecimal maxBallots) {
		this.maxBallots = maxBallots;
	}

	public BigDecimal getStddevBallots() {
		return stddevBallots;
	}

	public void setStddevBallots(final BigDecimal stddevBallots) {
		this.stddevBallots = stddevBallots;
	}

	public BigDecimal getMinDocuments() {
		return minDocuments;
	}

	public void setMinDocuments(final BigDecimal minDocuments) {
		this.minDocuments = minDocuments;
	}

	public BigDecimal getMaxDocuments() {
		return maxDocuments;
	}

	public void setMaxDocuments(final BigDecimal maxDocuments) {
		this.maxDocuments = maxDocuments;
	}

	public BigDecimal getStddevDocuments() {
		return stddevDocuments;
	}

	public void setStddevDocuments(final BigDecimal stddevDocuments) {
		this.stddevDocuments = stddevDocuments;
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
