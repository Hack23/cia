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
package com.hack23.cia.service.impl.rules;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummaryAnnual;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummaryDaily;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummaryMonthly;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.model.internal.application.data.rules.impl.ResourceType;

/**
 * The Class PartyComplianceCheckImpl.
 */
public final class PartyComplianceCheckImpl extends AbstractComplianceCheckImpl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The party. */
	private final ViewRiksdagenPartySummary party;

	/** The name. */
	private final String name;

	/** The daily summary. */
	private final ViewRiksdagenVoteDataBallotPartySummaryDaily dailySummary;

	/** The monthly summary. */
	private final ViewRiksdagenVoteDataBallotPartySummaryMonthly monthlySummary;

	/** The annual summary. */
	private final ViewRiksdagenVoteDataBallotPartySummaryAnnual annualSummary;

	/**
	 * Instantiates a new party compliance check impl.
	 *
	 * @param party           the party
	 * @param annualSummary   the annual summary
	 * @param monthlySummary  the monthly summary
	 * @param dailySummary    the daily summary
	 */
	public PartyComplianceCheckImpl(final ViewRiksdagenPartySummary party,final ViewRiksdagenVoteDataBallotPartySummaryAnnual annualSummary,final ViewRiksdagenVoteDataBallotPartySummaryMonthly monthlySummary,final ViewRiksdagenVoteDataBallotPartySummaryDaily dailySummary) {
		super(ResourceType.PARTY);
		this.party = party;
		this.annualSummary = annualSummary;
		this.monthlySummary = monthlySummary;
		this.dailySummary = dailySummary;
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
	public String getId() {
		return party.getParty();
	}

	/**
	 * Gets the daily summary.
	 *
	 * @return the daily summary
	 */
	public ViewRiksdagenVoteDataBallotPartySummaryDaily getDailySummary() {
		return dailySummary;
	}

	/**
	 * Gets the monthly summary.
	 *
	 * @return the monthly summary
	 */
	public ViewRiksdagenVoteDataBallotPartySummaryMonthly getMonthlySummary() {
		return monthlySummary;
	}


	/**
	 * Gets the annual summary.
	 *
	 * @return the annual summary
	 */
	public ViewRiksdagenVoteDataBallotPartySummaryAnnual getAnnualSummary() {
		return annualSummary;
	}

	/**
	 * Gets the document count.
	 *
	 * @return the document count
	 */
	public int getDocumentCount() {
		return party.getDocumentCount();
	}

	/**
	 * Gets the press release count.
	 *
	 * @return the press release count
	 */
	public int getPressReleaseCount() {
		return party.getPressReleaseCount();
	}

	/**
	 * Gets the days since registration.
	 *
	 * @return the days since registration
	 */
	public long getDaysSinceRegistration() {
		long currentTime = System.currentTimeMillis();
		long registeredTime = party.getRegisteredDate().getTime();
		return (currentTime - registeredTime) / (1000 * 60 * 60 * 24);
	}

	/**
	 * Gets the multiple roles count.
	 *
	 * @return the multiple roles count
	 */
	public int getMultipleRolesCount() {
		return party.getCurrentMinistryAssignments() + party.getCurrentPartyAssignments() + party.getCurrentSpeakerAssignments();
	}

	/**
	 * Gets the frequent party switching count.
	 *
	 * @return the frequent party switching count
	 */
	public int getFrequentPartySwitchingCount() {
		return party.getPartySwitchingCount();
	}
}
