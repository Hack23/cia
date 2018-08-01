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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummaryAnnual;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummaryDaily;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummaryMonthly;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.service.api.action.kpi.ResourceType;

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
	
	/** The ballot decisions. */
	private List<ViewRiksdagenCommitteeBallotDecisionPartySummary> ballotDecisions = new ArrayList<>();
	
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
	 * @param ballotDecisions the ballot decisions
	 */
	public PartyComplianceCheckImpl(final ViewRiksdagenPartySummary party,final ViewRiksdagenVoteDataBallotPartySummaryAnnual annualSummary,final ViewRiksdagenVoteDataBallotPartySummaryMonthly monthlySummary,final ViewRiksdagenVoteDataBallotPartySummaryDaily dailySummary,final List<ViewRiksdagenCommitteeBallotDecisionPartySummary> ballotDecisions) {
		super(ResourceType.PARTY);
		this.party = party;
		this.annualSummary = annualSummary;
		this.monthlySummary = monthlySummary;
		this.dailySummary = dailySummary;
		this.name = party.getParty();
		this.ballotDecisions = Collections.unmodifiableList(ballotDecisions);
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
	 * Supports.
	 *
	 * @param committeeReport the committee report
	 * @param rm              the rm
	 * @return true, if successful
	 */
	public boolean supports(final String committeeReport,final String rm) {
		for (ViewRiksdagenCommitteeBallotDecisionPartySummary summary : ballotDecisions) {
			if (summary.getRm().equalsIgnoreCase(rm) && summary.getCommitteeReport().equalsIgnoreCase(committeeReport)) {
				return summary.isPartyApproved();
			}			
		}
		return false;
	}

	/**
	 * Against.
	 *
	 * @param committeeReport the committee report
	 * @param rm              the rm
	 * @return true, if successful
	 */
	public boolean against(final String committeeReport,final String rm) {
		for (ViewRiksdagenCommitteeBallotDecisionPartySummary summary : ballotDecisions) {
			if (summary.getRm().equalsIgnoreCase(rm) && summary.getCommitteeReport().equalsIgnoreCase(committeeReport)) {
				return !summary.isPartyApproved();
			}			
		}
		return false;
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

}
