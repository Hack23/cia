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
package com.hack23.cia.service.impl.rules;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.rules.impl.ResourceType;

/**
 * The Class CommitteeComplianceCheckImpl.
 */
public final class CommitteeComplianceCheckImpl extends AbstractComplianceCheckImpl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The committee. */
	private final ViewRiksdagenCommittee committee;

	/** The name. */
	private final String name;

	/**
	 * Instantiates a new committee compliance check impl.
	 *
	 * @param committee the committee
	 */
	public CommitteeComplianceCheckImpl(final ViewRiksdagenCommittee committee) {
		super(ResourceType.COMMITTEE);
		this.committee = committee;
		this.name = committee.getEmbeddedId().getOrgCode();
	}

	/**
	 * Gets the committee.
	 *
	 * @return the committee
	 */
	public ViewRiksdagenCommittee getCommittee() {
		return committee;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getId() {
		return committee.getEmbeddedId().getOrgCode();
	}

}
