/*
 * Copyright 2010-2019 James Pether Sörling
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
package com.hack23.cia.service.api.action.kpi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import com.hack23.cia.service.api.action.common.AbstractResponse;

/**
 * The Class ComplianceCheckResponse.
 */
public final class ComplianceCheckResponse extends AbstractResponse {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The list. */
	private List<ComplianceCheck> list = new ArrayList<>();

	/**
	 * Instantiates a new compliance check response.
	 *
	 * @param result
	 *            the result
	 */
	public ComplianceCheckResponse(final ServiceResult result) {
		super(result);
	}

	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public List<ComplianceCheck> getList() {
		return Collections.unmodifiableList(list);
	}

	/**
	 * Sets the list.
	 *
	 * @param list
	 *            the new list
	 */
	public void setList(final List<ComplianceCheck> list) {
		this.list = Collections.unmodifiableList(ListUtils.emptyIfNull(list));
	}
}
