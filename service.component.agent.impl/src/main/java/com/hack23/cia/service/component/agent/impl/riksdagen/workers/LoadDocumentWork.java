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
package com.hack23.cia.service.component.agent.impl.riksdagen.workers;

import java.io.Serializable;

/**
 * The Class LoadDocumentWork.
 */
public final class LoadDocumentWork implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The from date. */
	private final String fromDate;

	/** The to date. */
	private final String toDate;

	/**
	 * Instantiates a new load document work.
	 *
	 * @param fromDate
	 *            the from date
	 * @param toDate
	 *            the to date
	 */
	public LoadDocumentWork(final String fromDate, final String toDate) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	/**
	 * Gets the from date.
	 *
	 * @return the from date
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * Gets the to date.
	 *
	 * @return the to date
	 */
	public String getToDate() {
		return toDate;
	}

}
