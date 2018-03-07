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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.drools.core.common.DefaultFactHandle;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummaryDaily;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummaryMonthly;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.service.api.action.kpi.ComplianceCheck;
import com.hack23.cia.service.data.api.DataViewer;

/**
 * The Class RulesEngineImpl.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, timeout = 1200)
public final class RulesEngineImpl implements RulesEngine {

	/** The data viewer. */
	@Autowired
	@Qualifier("DataViewer")
	private DataViewer dataViewer;

	@Autowired
	private KieContainer rulesContainer;
	
	@Override
	@Cacheable("checkRulesCompliance")
	public List<ComplianceCheck> checkRulesCompliance() {
		KieSession ksession = rulesContainer.newKieSession();
		Map<String,ComplianceCheck> complianceChecks = new HashMap<>();
		ksession.addEventListener(new ComplianceCheckAgendaEventListener(complianceChecks));

		insertPoliticians(ksession, dataViewer.getAll(ViewRiksdagenPolitician.class));
		insertParties(ksession, dataViewer.getAll(ViewRiksdagenPartySummary.class));

		ksession.fireAllRules();
		ksession.dispose();
		return new ArrayList(complianceChecks.values());
	}

	/**
	 * Insert politicians.
	 *
	 * @param ksession
	 *            the ksession
	 * @param list
	 *            the list
	 */
	private void insertPoliticians(KieSession ksession, final List<ViewRiksdagenPolitician> list) {

		Map<String, List<ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual>> politicanBallotSummaryAnnualMap = dataViewer
				.getAll(ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual.class).stream()
				.collect(Collectors.groupingBy(p -> p.getEmbeddedId().getIntressentId()));
		Map<String, List<ViewRiksdagenVoteDataBallotPoliticianSummaryMonthly>> politicanBallotSummaryMontlyMap = dataViewer
				.getAll(ViewRiksdagenVoteDataBallotPoliticianSummaryMonthly.class).stream()
				.collect(Collectors.groupingBy(p -> p.getEmbeddedId().getIntressentId()));
		Map<String, List<ViewRiksdagenVoteDataBallotPoliticianSummaryDaily>> politicanBallotSummaryDailyMap = dataViewer
				.getAll(ViewRiksdagenVoteDataBallotPoliticianSummaryDaily.class).stream()
				.collect(Collectors.groupingBy(p -> p.getEmbeddedId().getIntressentId()));
		// Map<String, List<ViewRiksdagenVoteDataBallotPoliticianSummary>>
		// politicanBallotSummaryMap =
		// dataViewer.getAll(ViewRiksdagenVoteDataBallotPoliticianSummary.class).stream().collect(Collectors.groupingBy(p
		// -> p.getEmbeddedId().getIntressentId()));
		// Map<String, List<ViewRiksdagenCommitteeBallotDecisionPoliticianSummary>>
		// politicanCommitteeDecisionSummaryMap =
		// dataViewer.getAll(ViewRiksdagenCommitteeBallotDecisionPoliticianSummary.class).stream().collect(Collectors.groupingBy(p
		// -> p.getEmbeddedId().getIntressentId()));
		// Map<String, List<ViewRiksdagenPoliticianDocumentDailySummary>>
		// politicanDocumentSummaryDailyMap =
		// dataViewer.getAll(ViewRiksdagenPoliticianDocumentDailySummary.class).stream().collect(Collectors.groupingBy(p
		// -> p.getEmbeddedId().getPersonId()));
		// Map<String, List<ViewRiksdagenPoliticianDocument>> politicanDocumentMap =
		// dataViewer.getAll(ViewRiksdagenPoliticianDocument.class).stream().collect(Collectors.groupingBy(p
		// -> p.getPersonReferenceId()));

		for (ViewRiksdagenPolitician politicianData : list) {
			if (politicianData != null) {

				List<ViewRiksdagenVoteDataBallotPoliticianSummaryDaily> dailyList = politicanBallotSummaryDailyMap
						.get(politicianData.getPersonId());
				List<ViewRiksdagenVoteDataBallotPoliticianSummaryMonthly> monthlyList = politicanBallotSummaryMontlyMap
						.get(politicianData.getPersonId());
				List<ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual> annualList = politicanBallotSummaryAnnualMap
						.get(politicianData.getPersonId());

				if (politicianData.isActiveParliament() && dailyList != null && monthlyList != null
						&& annualList != null) {
					Collections.sort(dailyList,
							(e1, e2) -> e1.getEmbeddedId().getVoteDate().compareTo(e2.getEmbeddedId().getVoteDate()));
					Collections.sort(monthlyList,
							(e1, e2) -> e1.getEmbeddedId().getVoteDate().compareTo(e2.getEmbeddedId().getVoteDate()));
					Collections.sort(annualList,
							(e1, e2) -> e1.getEmbeddedId().getVoteDate().compareTo(e2.getEmbeddedId().getVoteDate()));

					PoliticianComplianceCheckImpl politicianComplianceCheckImpl = new PoliticianComplianceCheckImpl(
							politicianData, Iterables.getFirst(dailyList, null), Iterables.getFirst(monthlyList, null),
							Iterables.getFirst(annualList, null));
					ksession.insert(politicianComplianceCheckImpl);
				} else {
					PoliticianComplianceCheckImpl politicianComplianceCheckImpl = new PoliticianComplianceCheckImpl(
							politicianData, null, null, null);
					ksession.insert(politicianComplianceCheckImpl);
				}
			}
		}
	}

	/**
	 * Insert parties.
	 *
	 * @param ksession
	 *            the ksession
	 * @param list
	 *            the list
	 */
	private static void insertParties(KieSession ksession, final List<ViewRiksdagenPartySummary> list) {
		for (ViewRiksdagenPartySummary partyData : list) {
			if (partyData != null) {
				ksession.insert(new PartyComplianceCheckImpl(partyData));
			}
		}
	}

	/**
	 * The listener interface for receiving complianceCheckAgendaEvent events. The
	 * class that is interested in processing a complianceCheckAgendaEvent event
	 * implements this interface, and the object created with that class is
	 * registered with a component using the component's
	 * <code>addComplianceCheckAgendaEventListener<code> method. When the
	 * complianceCheckAgendaEvent event occurs, that object's appropriate method is
	 * invoked.
	 *
	 * @see ComplianceCheckAgendaEventEvent
	 */
	private static final class ComplianceCheckAgendaEventListener extends DefaultAgendaEventListener {
		
		/** The compliance checks. */
		private final Map<String, ComplianceCheck> complianceChecks;

		/**
		 * Instantiates a new compliance check agenda event listener.
		 *
		 * @param complianceChecks
		 *            the compliance checks
		 */
		public ComplianceCheckAgendaEventListener(Map<String, ComplianceCheck> complianceChecks) {
			this.complianceChecks = complianceChecks;
		}

		@Override
		public void afterMatchFired(AfterMatchFiredEvent event) {
			super.afterMatchFired(event);			
			AbstractComplianceCheckImpl check = (AbstractComplianceCheckImpl) ((DefaultFactHandle) event.getMatch().getFactHandles().stream().findFirst().orElse(null)).getObject();			
			complianceChecks.put(check.getId(), check);
		}
	}
}
