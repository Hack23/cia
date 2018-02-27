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
import java.util.List;
import java.util.Locale;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummaryDaily;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocumentDailySummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.service.api.action.kpi.ResourceType;

/**
 * The Class PoliticianComplianceCheckImpl.
 */
public final class PoliticianComplianceCheckImpl extends AbstractComplianceCheckImpl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The politician. */
	private final ViewRiksdagenPolitician politician;
	
	private List<ViewRiksdagenPoliticianDocument> documents;
	
	private List<ViewRiksdagenPoliticianDocumentDailySummary> documentDailySummary;
	
	private List<ViewRiksdagenCommitteeBallotDecisionPoliticianSummary> ballotDecisions;
	
	private List<ViewRiksdagenVoteDataBallotPoliticianSummary> ballots;
	
	private List<ViewRiksdagenVoteDataBallotPoliticianSummaryDaily> dailyBallotSummary;
	
	private String name;
	
	/**
	 * Instantiates a new politician compliance check impl.
	 *
	 * @param politician
	 *            the politician
	 */
	public PoliticianComplianceCheckImpl(final ViewRiksdagenPolitician politician) {
		super(ResourceType.POLITICIAN);
		this.politician = politician;
		this.name = politician.getFirstName() + " " + politician.getLastName() + " (" +politician.getParty() +")";
	}

	/**
	 * Gets the politician.
	 *
	 * @return the politician
	 */
	public ViewRiksdagenPolitician getPolitician() {
		return politician;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return new MessageFormat("PoliticianComplianceCheckImpl [getName()={0}, getRuleName()={1}, getRuleDescription()={2}, getResourceType()={3}, getStatus()={4}]",Locale.ENGLISH).format(			
				new Object[] {getName(), getRuleName(), getRuleDescription(), getResourceType(), getStatus()});
	}

	@Override
	public String getId() {
		return politician.getPersonId();
	}

	
}
