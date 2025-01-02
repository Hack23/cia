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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class GovernmentBodyAnnualSummary.
 */
public final class GovernmentBodyAnnualSummary implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The annual work head count. */
	private final int annualWorkHeadCount;

	/** The comment. */
	private final String comment;

	/** The consecutive number. */
	private final int consecutiveNumber;

	/** The goverment body id. */
	private final String govermentBodyId;

	/** The head count. */
	private final int headCount;

	/** The m code. */
	private final String mCode;

	/** The ministry. */
	private final String ministry;

	/** The name. */
	private final String name;

	/** The org number. */
	private final String orgNumber;

	/** The vat. */
	private final String vat;

	/** The year. */
	private final int year;

	/**
	 * Instantiates a new government body annual summary.
	 *
	 * @param year
	 *            the year
	 * @param name
	 *            the name
	 * @param consecutiveNumber
	 *            the consecutive number
	 * @param govermentBodyId
	 *            the goverment body id
	 * @param mCode
	 *            the m code
	 * @param ministry
	 *            the ministry
	 * @param orgNumber
	 *            the org number
	 * @param headCount
	 *            the head count
	 * @param annualWorkHeadCount
	 *            the annual work head count
	 * @param vat
	 *            the vat
	 * @param comment
	 *            the comment
	 */
	public GovernmentBodyAnnualSummary(final int year, final String name, final int consecutiveNumber, final String govermentBodyId,
			final String mCode, final String ministry, final String orgNumber, final int headCount, final int annualWorkHeadCount, final String vat,
			final String comment) {
		super();
		this.year = year;
		this.name = name;
		this.consecutiveNumber = consecutiveNumber;
		this.govermentBodyId = govermentBodyId;
		this.mCode = mCode;
		this.ministry = ministry;
		this.orgNumber = orgNumber;
		this.headCount = headCount;
		this.annualWorkHeadCount = annualWorkHeadCount;
		this.vat = vat;
		this.comment = comment;
	}

	/**
	 * Gets the annual work head count.
	 *
	 * @return the annual work head count
	 */
	public int getAnnualWorkHeadCount() {
		return annualWorkHeadCount;
	}

	/**
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Gets the consecutive number.
	 *
	 * @return the consecutive number
	 */
	public int getConsecutiveNumber() {
		return consecutiveNumber;
	}

	/**
	 * Gets the goverment body id.
	 *
	 * @return the goverment body id
	 */
	public String getGovermentBodyId() {
		return govermentBodyId;
	}

	/**
	 * Gets the head count.
	 *
	 * @return the head count
	 */
	public int getHeadCount() {
		return headCount;
	}

	/**
	 * Gets the m code.
	 *
	 * @return the m code
	 */
	public String getmCode() {
		return mCode;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * Gets the vat.
	 *
	 * @return the vat
	 */
	public String getVat() {
		return vat;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return year;
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
