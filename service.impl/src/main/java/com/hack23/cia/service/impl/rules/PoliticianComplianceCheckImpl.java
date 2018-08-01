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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummaryDaily;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummaryMonthly;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.service.api.action.kpi.ResourceType;

/**
 * The Class PoliticianComplianceCheckImpl.
 */
public final class PoliticianComplianceCheckImpl extends AbstractComplianceCheckImpl {

	/** The Constant NO. */
	private static final String NO = "NEJ";

	/** The Constant YES. */
	private static final String YES = "JA";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The politician. */
	private final ViewRiksdagenPolitician politician;
	
	/** The ballot decisions. */
	private final List<ViewRiksdagenCommitteeBallotDecisionPoliticianSummary> ballotDecisions;
	
	/** The daily summary. */
	private final ViewRiksdagenVoteDataBallotPoliticianSummaryDaily dailySummary;
	
	/** The monthly summary. */
	private final ViewRiksdagenVoteDataBallotPoliticianSummaryMonthly monthlySummary;
	
	/** The annual summary. */
	private final ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual annualSummary;
	
	/** The name. */
	private final String name;
	
	/**
	 * Instantiates a new politician compliance check impl.
	 *
	 * @param politician     the politician
	 * @param dailySummary   the daily summary
	 * @param monthlySummary the monthly summary
	 * @param annualSummary  the annual summary
	 * @param decisionList   the decision list
	 */
	public PoliticianComplianceCheckImpl(final ViewRiksdagenPolitician politician,final ViewRiksdagenVoteDataBallotPoliticianSummaryDaily dailySummary, final ViewRiksdagenVoteDataBallotPoliticianSummaryMonthly monthlySummary,final ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual annualSummary, List<ViewRiksdagenCommitteeBallotDecisionPoliticianSummary> decisionList) {
		super(ResourceType.POLITICIAN);
		this.politician = politician;
		this.dailySummary = dailySummary;
		this.monthlySummary = monthlySummary;
		this.annualSummary = annualSummary;
		this.ballotDecisions = Collections.unmodifiableList(decisionList);
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
	public String getId() {
		return politician.getPersonId();
	}

	/**
	 * Supports.
	 *
	 * @param committeeReport the committee report
	 * @param rm              the rm
	 * @return true, if successful
	 */
	public boolean supports(final String committeeReport,final String rm) {
		for (ViewRiksdagenCommitteeBallotDecisionPoliticianSummary summary : ballotDecisions) {
			if (summary.getRm().equalsIgnoreCase(rm) && summary.getCommitteeReport().equalsIgnoreCase(committeeReport)) {
				return YES.equalsIgnoreCase(summary.getVote());
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
		for (ViewRiksdagenCommitteeBallotDecisionPoliticianSummary summary : ballotDecisions) {
			if (summary.getRm().equalsIgnoreCase(rm) && summary.getCommitteeReport().equalsIgnoreCase(committeeReport)) {
				return NO.equalsIgnoreCase(summary.getVote());
			}			
		}
		return false;
	}
	

	/**
	 * Gets the daily summary.
	 *
	 * @return the daily summary
	 */
	public ViewRiksdagenVoteDataBallotPoliticianSummaryDaily getDailySummary() {
		return dailySummary;
	}

	/**
	 * Gets the monthly summary.
	 *
	 * @return the monthly summary
	 */
	public ViewRiksdagenVoteDataBallotPoliticianSummaryMonthly getMonthlySummary() {
		return monthlySummary;
	}


	/**
	 * Gets the annual summary.
	 *
	 * @return the annual summary
	 */
	public ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual getAnnualSummary() {
		return annualSummary;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return LocalDate.now(ZoneId.of("UTC")).getYear();
	}
	
	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public int getAge() {
		return getYear() - politician.getBornYear();
	}
	
}
