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

/**
 * The Class GovernmentBodyAnnualSummary.
 */
public class GovernmentBodyAnnualSummary {

	/** The year. */
	private final int year;
	
	/** The name. */
	private final String name;
	
	/** The consecutive number. */
	private final String consecutiveNumber;
	
	/** The goverment body id. */
	private final String govermentBodyId;
	
	/** The m code. */
	private final String mCode;
	
	/** The ministry. */
	private final String ministry;
	
	/** The org number. */
	private final String orgNumber;
	
	/** The head count. */
	private final String headCount;
	
	/** The annual work head count. */
	private final String annualWorkHeadCount;
	
	/** The vat. */
	private final String vat;
	
	/** The comment. */
	private final String comment;

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
	public GovernmentBodyAnnualSummary(final int year, final String name, final String consecutiveNumber, final String govermentBodyId,
			final String mCode, final String ministry, final String orgNumber, final String headCount, final String annualWorkHeadCount, final String vat,
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
	 * Gets the year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return year;
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
	 * Gets the consecutive number.
	 *
	 * @return the consecutive number
	 */
	public String getConsecutiveNumber() {
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
	 * Gets the org number.
	 *
	 * @return the org number
	 */
	public String getOrgNumber() {
		return orgNumber;
	}

	/**
	 * Gets the head count.
	 *
	 * @return the head count
	 */
	public String getHeadCount() {
		return headCount;
	}

	/**
	 * Gets the annual work head count.
	 *
	 * @return the annual work head count
	 */
	public String getAnnualWorkHeadCount() {
		return annualWorkHeadCount;
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
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	
}
