/*
 * Copyright 2010-2024 James Pether Sörling
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

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class GovernmentOperationPeriodOutcome.
 */
public final class GovernmentOperationPeriodOutcome implements Comparable<GovernmentOperationPeriodOutcome>, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The variable name. */
	private String variableName;

	/** The period. */
	private String period;

	/** The value. */
	private double value;

	/** The percentage change from previous to latest. */
	private double percentageChangeFromPreviousToLatest;

	/** The Percentage change from same period last year to latest. */
	private double percentageChangeFromSamePeriodLastYearToLatest;

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
	public void setVariableName(final String variableName) {
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
	public void setPeriod(final String period) {
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
	public void setValue(final double d) {
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
	public void setPercentageChangeFromPreviousToLatest(final double percentageChangeFromPreviousToLatest) {
		this.percentageChangeFromPreviousToLatest = percentageChangeFromPreviousToLatest;
	}

	/**
	 * Gets the percentage change from same period last year to latest.
	 *
	 * @return the percentage change from same period last year to latest
	 */
	public double getPercentageChangeFromSamePeriodLastYearToLatest() {
		return percentageChangeFromSamePeriodLastYearToLatest;
	}

	/**
	 * Sets the percentage change from same period last year to latest.
	 *
	 * @param percentageChangeFromSamePeriodLastYearToLatest
	 *            the new percentage change from same period last year to latest
	 */
	public void setPercentageChangeFromSamePeriodLastYearToLatest(final double percentageChangeFromSamePeriodLastYearToLatest) {
		this.percentageChangeFromSamePeriodLastYearToLatest = percentageChangeFromSamePeriodLastYearToLatest;
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
	public void setObservationStatus(final String observationStatus) {
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

	@Override
	public boolean equals(final Object o) {
		return EqualsBuilder.reflectionEquals(this, o, false);
	}

	@Override
	public int compareTo(final GovernmentOperationPeriodOutcome o) {
		return period.compareTo(o.getPeriod());
	}

	/**
	 * The Enum Variables.
	 */
	public enum Variables {

    	/** The total expenditures. */
    	TOTAL_EXPENDITURES ("Total expenditures"),

    	/** The expenditure excl interest gov debt. */
    	EXPENDITURE_EXCL_INTEREST_GOV_DEBT ("Expenditure, excl. interest on central government debt, etc."),

    	/** The interest on gov debt. */
    	INTEREST_ON_GOV_DEBT ("Interest on central government debt, etc."),

    	/** The net lending borrowing. */
    	NET_LENDING_BORROWING ("Net lending/borrowing by the National Debt Office"),

    	/** The adjustment to cash basis. */
    	ADJUSTMENT_TO_CASH_BASIS ("Adjustment to cash basis"),

    	/** The total revenue. */
    	TOTAL_REVENUE ("Total revenue"),

    	/** The budget balance. */
    	BUDGET_BALANCE ("Budget balance");

	    /** The name. */
    	private final String name;

	    /**
		 * Instantiates a new variables.
		 *
		 * @param s
		 *            the s
		 */
    	Variables(final String s) {
	        name = s;
	    }

    	@Override
	    public String toString() {
	       return this.name;
	    }
	}
}