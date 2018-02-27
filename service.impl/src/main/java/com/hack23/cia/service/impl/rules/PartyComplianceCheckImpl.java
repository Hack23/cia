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
package com.hack23.cia.service.impl.rules;

import java.text.MessageFormat;
import java.util.Locale;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.service.api.action.kpi.ResourceType;

/**
 * The Class PartyComplianceCheckImpl.
 */
public final class PartyComplianceCheckImpl extends AbstractComplianceCheckImpl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The party. */
	private ViewRiksdagenPartySummary party;
	
	/** The name. */
	private String name;

	/**
	 * Instantiates a new party compliance check impl.
	 *
	 * @param party
	 *            the party
	 */
	public PartyComplianceCheckImpl(final ViewRiksdagenPartySummary party) {
		super(ResourceType.PARTY);
		this.party = party;
		this.name = party.getParty();
	}

	/**
	 * Gets the party.
	 *
	 * @return the party
	 */
	public ViewRiksdagenPartySummary getParty() {
		return party;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return new MessageFormat("PartyComplianceCheckImpl [getName()={0}, getRuleName()={1}, getRuleDescription()={2}, getResourceType()={3}, getStatus()={4}]",Locale.ENGLISH).format(			
				new Object[] {getName(), getRuleName(), getRuleDescription(), getResourceType(), getStatus()});
	}

	@Override
	public String getId() {
		return party.getParty();
	}

}
