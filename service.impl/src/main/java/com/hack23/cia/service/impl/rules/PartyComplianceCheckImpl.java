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
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummaryAnnual;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummaryDaily;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummaryMonthly;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPartyDocumentDailySummary;
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
	
	/** The document daily summary. */
	private List<ViewRiksdagenPartyDocumentDailySummary> documentDailySummary = new ArrayList<>();
	
	/** The ballot decisions. */
	private List<ViewRiksdagenCommitteeBallotDecisionPartySummary> ballotDecisions = new ArrayList<>();
	
	/** The ballots. */
	private List<ViewRiksdagenVoteDataBallotPartySummary> ballots = new ArrayList<>();
	
	/** The daily ballot summary. */
	private List<ViewRiksdagenVoteDataBallotPartySummaryDaily> dailyBallotSummary = new ArrayList<>();

	/** The daily summary. */
	private final ViewRiksdagenVoteDataBallotPartySummaryDaily dailySummary;
	
	/** The monthly summary. */
	private final ViewRiksdagenVoteDataBallotPartySummaryMonthly monthlySummary;
	
	/** The annual summary. */
	private final ViewRiksdagenVoteDataBallotPartySummaryAnnual annualSummary;

	/**
	 * Instantiates a new party compliance check impl.
	 *
	 * @param party
	 *            the party
	 * @param annualSummary
	 *            the annual summary
	 * @param monthlySummary
	 *            the monthly summary
	 * @param dailySummary
	 *            the daily summary
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
	 * Gets the document daily summary.
	 *
	 * @return the document daily summary
	 */
	public List<ViewRiksdagenPartyDocumentDailySummary> getDocumentDailySummary() {
		return Collections.unmodifiableList(documentDailySummary);
	}

	/**
	 * Sets the document daily summary.
	 *
	 * @param documentDailySummary
	 *            the new document daily summary
	 */
	public void setDocumentDailySummary(List<ViewRiksdagenPartyDocumentDailySummary> documentDailySummary) {
		this.documentDailySummary = Collections.unmodifiableList(documentDailySummary);
	}

	/**
	 * Gets the ballots.
	 *
	 * @return the ballots
	 */
	public List<ViewRiksdagenVoteDataBallotPartySummary> getBallots() {
		return Collections.unmodifiableList(ballots);
	}

	/**
	 * Sets the ballots.
	 *
	 * @param ballots
	 *            the new ballots
	 */
	public void setBallots(List<ViewRiksdagenVoteDataBallotPartySummary> ballots) {
		this.ballots = Collections.unmodifiableList(ballots);
	}

	/**
	 * Gets the daily ballot summary.
	 *
	 * @return the daily ballot summary
	 */
	public List<ViewRiksdagenVoteDataBallotPartySummaryDaily> getDailyBallotSummary() {
		return Collections.unmodifiableList(dailyBallotSummary);
	}

	/**
	 * Sets the daily ballot summary.
	 *
	 * @param dailyBallotSummary
	 *            the new daily ballot summary
	 */
	public void setDailyBallotSummary(List<ViewRiksdagenVoteDataBallotPartySummaryDaily> dailyBallotSummary) {
		this.dailyBallotSummary = Collections.unmodifiableList(dailyBallotSummary);
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
	 * Gets the ballot decisions.
	 *
	 * @return the ballot decisions
	 */
	public List<ViewRiksdagenCommitteeBallotDecisionPartySummary> getBallotDecisions() {
		return Collections.unmodifiableList(ballotDecisions);
	}

	/**
	 * Sets the ballot decisions.
	 *
	 * @param ballotDecisions
	 *            the new ballot decisions
	 */
	public void setBallotDecisions(final List<ViewRiksdagenCommitteeBallotDecisionPartySummary> ballotDecisions) {
		this.ballotDecisions = Collections.unmodifiableList(ballotDecisions);
	}

}
