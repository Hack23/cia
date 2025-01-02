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
package com.hack23.cia.service.external.esv.api;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class GovernmentBodyAnnualOutcomeSummary.
 */
public final class GovernmentBodyAnnualOutcomeSummary implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The goverment body. */
	private final String govermentBody;

	/** The org number. */
	private final String orgNumber;

	/** The ministry. */
	private final String ministry;

	/** The year. */
	private final int year;

	/** The year total. */
	private double yearTotal;

	/** The value map. */
	private final Map<Date, Double> valueMap = new HashMap<>();

	/** The description fields. */
	private final Map<String, String> descriptionFields = new HashMap<>();

	/**
	 * Instantiates a new government body annual outcome summary.
	 *
	 * @param govermentBody the goverment body
	 * @param orgNumber     the org number
	 * @param ministry      the ministry
	 * @param year          the year
	 */
	public GovernmentBodyAnnualOutcomeSummary(final String govermentBody, final String orgNumber, final String ministry, final int year) {
		super();
		this.govermentBody = govermentBody;
		this.orgNumber = orgNumber;
		this.ministry = ministry;
		this.year = year;
	}

	/**
	 * Gets the goverment body id.
	 *
	 * @return the goverment body id
	 */
	public String getGovermentBody() {
		return govermentBody;
	}

	/**
	 * Gets the org number.
	 *
	 * @return the org number
	 */
	public String getOrgNumber() {
		return orgNumber;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Gets the ministry.
	 *
	 * @return the ministry
	 */
	public String getMinistry() {
		return ministry;
	}

	/**
	 * Adds the data.
	 *
	 * @param month
	 *            the month
	 * @param value
	 *            the value
	 */
	public void addData(final int month, final Double value) {
		yearTotal = yearTotal + value;
		final Date date = new GregorianCalendar(year, month, 1).getTime();
		valueMap.put(date, value);
	}

	/**
	 * Gets the year total.
	 *
	 * @return the year total
	 */
	public double getYearTotal() {
		return yearTotal;
	}

	/**
	 * Adds the description field.
	 *
	 * @param field
	 *            the field
	 * @param value
	 *            the value
	 */
	public void addDescriptionField(final String field, final String value) {
		descriptionFields.put(field, value);
	}


	/**
	 * Gets the value map.
	 *
	 * @return the value map
	 */
	public Map<Date, Double> getValueMap() {
		return valueMap;
	}

	/**
	 * Gets the description fields.
	 *
	 * @return the description fields
	 */
	public Map<String, String> getDescriptionFields() {
		return descriptionFields;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
