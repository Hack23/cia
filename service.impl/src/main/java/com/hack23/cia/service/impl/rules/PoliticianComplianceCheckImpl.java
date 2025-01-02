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

import java.time.LocalDate;
import java.time.ZoneId;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummaryDaily;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummaryMonthly;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.rules.impl.ResourceType;

/**
 * The Class PoliticianComplianceCheckImpl.
 */
public final class PoliticianComplianceCheckImpl extends AbstractComplianceCheckImpl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The politician. */
	private final ViewRiksdagenPolitician politician;

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
	 */
	public PoliticianComplianceCheckImpl(final ViewRiksdagenPolitician politician,final ViewRiksdagenVoteDataBallotPoliticianSummaryDaily dailySummary, final ViewRiksdagenVoteDataBallotPoliticianSummaryMonthly monthlySummary,final ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual annualSummary) {
		super(ResourceType.POLITICIAN);
		this.politician = politician;
		this.dailySummary = dailySummary;
		this.monthlySummary = monthlySummary;
		this.annualSummary = annualSummary;
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
	public static int getYear() {
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

	 /**
     * Ratio of committee leadership days to total committee days.
     *
     * @return a double ratio (0.0 if total committee days is 0).
     */
    public double getCommitteeLeadershipRatio() {
        final long totalCommittee = politician.getTotalDaysServedCommittee();
        if (totalCommittee == 0) {
            return 0.0;
        }
        return (double) politician.getTotalDaysServedCommitteeLeadership() / (double) totalCommittee;
    }

    /**
     * Ratio of committee substitute days to total committee days.
     *
     * @return a double ratio (0.0 if total committee days is 0).
     */
    public double getCommitteeSubstituteRatio() {
        final long totalCommittee = politician.getTotalDaysServedCommittee();
        if (totalCommittee == 0) {
            return 0.0;
        }
        return (double) politician.getTotalDaysServedCommitteeSubstitute() / (double) totalCommittee;
    }

}
