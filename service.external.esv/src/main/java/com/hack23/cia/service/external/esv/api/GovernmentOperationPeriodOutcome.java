/*
 * Copyright 2010 James Pether Sörling
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
package com.hack23.cia.service.external.esv.api;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class GovernmentOperationPeriodOutcome.
 */
public class GovernmentOperationPeriodOutcome {
	
	/** The variable name. */
	private String variableName;
	
	/** The period. */
	private String period;
	
	/** The value. */
	private double value;
	
	/** The percentage change from previous to latest. */
	private double percentageChangeFromPreviousToLatest;
	
	/** The Percentage change from same period last year to latest. */
	private double PercentageChangeFromSamePeriodLastYearToLatest;
	
	/** The observation status. */
	private String observationStatus;
	
	/**
	 * Instantiates a new government operation period outcome.
	 */
	public GovernmentOperationPeriodOutcome() {
		super();
	}

	/**
	 * Gets the variable name.
	 *
	 * @return the variable name
	 */
	public String getVariableName() {
		return variableName;
	}

	/**
	 * Sets the variable name.
	 *
	 * @param variableName
	 *            the new variable name
	 */
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	/**
	 * Gets the period.
	 *
	 * @return the period
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * Sets the period.
	 *
	 * @param period
	 *            the new period
	 */
	public void setPeriod(String period) {
		this.period = period;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param d
	 *            the new value
	 */
	public void setValue(double d) {
		this.value = d;
	}

	/**
	 * Gets the percentage change from previous to latest.
	 *
	 * @return the percentage change from previous to latest
	 */
	public double getPercentageChangeFromPreviousToLatest() {
		return percentageChangeFromPreviousToLatest;
	}

	/**
	 * Sets the percentage change from previous to latest.
	 *
	 * @param percentageChangeFromPreviousToLatest
	 *            the new percentage change from previous to latest
	 */
	public void setPercentageChangeFromPreviousToLatest(double percentageChangeFromPreviousToLatest) {
		this.percentageChangeFromPreviousToLatest = percentageChangeFromPreviousToLatest;
	}

	/**
	 * Gets the percentage change from same period last year to latest.
	 *
	 * @return the percentage change from same period last year to latest
	 */
	public double getPercentageChangeFromSamePeriodLastYearToLatest() {
		return PercentageChangeFromSamePeriodLastYearToLatest;
	}

	/**
	 * Sets the percentage change from same period last year to latest.
	 *
	 * @param percentageChangeFromSamePeriodLastYearToLatest
	 *            the new percentage change from same period last year to latest
	 */
	public void setPercentageChangeFromSamePeriodLastYearToLatest(double percentageChangeFromSamePeriodLastYearToLatest) {
		PercentageChangeFromSamePeriodLastYearToLatest = percentageChangeFromSamePeriodLastYearToLatest;
	}

	/**
	 * Gets the observation status.
	 *
	 * @return the observation status
	 */
	public String getObservationStatus() {
		return observationStatus;
	}

	/**
	 * Sets the observation status.
	 *
	 * @param observationStatus
	 *            the new observation status
	 */
	public void setObservationStatus(String observationStatus) {
		this.observationStatus = observationStatus;
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