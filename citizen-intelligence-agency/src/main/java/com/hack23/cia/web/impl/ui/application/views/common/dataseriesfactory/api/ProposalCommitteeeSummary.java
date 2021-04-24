/*
 * Copyright 2010-2021 James Pether Sörling
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
package com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api;

import java.util.Locale;

/**
 * The Class ProposalCommitteeeSummary.
 */
public final class ProposalCommitteeeSummary {

	/** The decision. */
	private final String decision;
	
	/** The doc type. */
	private final String docType;
	
	/** The hangar id. */
	private final String hangarId;
	
	/** The org. */
	private final String org;
	
	/**
	 * Instantiates a new proposal committeee summary.
	 *
	 * @param org
	 *            the org
	 * @param docType
	 *            the doc type
	 * @param decision
	 *            the decision
	 * @param hangarId
	 *            the hangar id
	 */
	public ProposalCommitteeeSummary(final String org, final String docType, final String decision, final String hangarId) {
		super();
		this.org = org.trim().toUpperCase(Locale.ENGLISH);
		this.docType = docType.toUpperCase(Locale.ENGLISH);
		this.decision = decision.replace("=", "").replace(",", "").trim().toUpperCase(Locale.ENGLISH);
		this.hangarId = hangarId;
	}

	/**
	 * Gets the decision.
	 *
	 * @return the decision
	 */
	public String getDecision() {
		return decision;
	}

	/**
	 * Gets the doc type.
	 *
	 * @return the doc type
	 */
	public String getDocType() {
		return docType;
	}

	/**
	 * Gets the hangar id.
	 *
	 * @return the hangar id
	 */
	public String getHangarId() {
		return hangarId;
	}

	/**
	 * Gets the org.
	 *
	 * @return the org
	 */
	public String getOrg() {
		return org;
	}

	@Override
	public String toString() {
		return String.format(Locale.ENGLISH,"ProposalCommitteeeSummary [org=%s, docType=%s, decision=%s, hangarId=%s]", org,
				docType, decision, hangarId);
	}				
}