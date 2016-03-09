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
package com.hack23.cia.service.data.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee_;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry_;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary_;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty_;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician_;
import com.hack23.cia.service.data.api.DataViewer;

/**
 * The Class DataViewerITest.
 */
// @PerfTest(threads = 10, duration = 3000, warmUp = 1000)
// @Required(max = 2000,average = 20,percentile95=20,throughput=4000)
public final class DataViewerITest extends
		AbstractServiceDataFunctionalIntegrationTest {

	/** The data viewer. */
	@Autowired
	private DataViewer dataViewer;

	// @Rule
	// public ContiPerfRule i = new ContiPerfRule();

	/**
	 * View riksdagen party.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void viewRiksdagenParty() throws Exception {

		final List<ViewRiksdagenParty> parties = dataViewer
				.getAll(ViewRiksdagenParty.class);
		assertNotNull(parties);
		if (parties.size() > 0) {
			final ViewRiksdagenParty viewRiksdagenParty = parties.get(0);
			final ViewRiksdagenParty viewRiksdagenPartyLoaded = dataViewer
					.load(ViewRiksdagenParty.class,
							viewRiksdagenParty.getPartyId());
			assertNotNull(viewRiksdagenPartyLoaded);

			final ViewRiksdagenParty viewRiksdagenPartyFound = dataViewer
					.findFirstByProperty(ViewRiksdagenParty.class,
							ViewRiksdagenParty_.partyName,
							viewRiksdagenPartyLoaded.getPartyName());

			assertEquals(viewRiksdagenPartyLoaded, viewRiksdagenPartyFound);
		}
	}

	/**
	 * View riksdagen ministry.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void viewRiksdagenMinistry() throws Exception {

		final List<ViewRiksdagenMinistry> ministries = dataViewer
				.getAll(ViewRiksdagenMinistry.class);
		assertNotNull(ministries);
		if (ministries.size() > 0) {
			final ViewRiksdagenMinistry viewRiksdagenMinistry = ministries
					.get(0);
			final ViewRiksdagenMinistry viewRiksdagenMinistryLoaded = dataViewer
					.load(ViewRiksdagenMinistry.class,
							viewRiksdagenMinistry.getNameId());
			assertNotNull(viewRiksdagenMinistryLoaded);

			final ViewRiksdagenMinistry viewRiksdagenMinistryFound = dataViewer
					.findFirstByProperty(ViewRiksdagenMinistry.class,
							ViewRiksdagenMinistry_.nameId,
							viewRiksdagenMinistryLoaded.getNameId());

			assertEquals(viewRiksdagenMinistryLoaded,
					viewRiksdagenMinistryFound);
		}
	}

	/**
	 * View riksdagen committee.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void viewRiksdagenCommittee() throws Exception {

		final List<ViewRiksdagenCommittee> committees = dataViewer
				.getAll(ViewRiksdagenCommittee.class);
		assertNotNull(committees);
		if (committees.size() > 0) {
			final ViewRiksdagenCommittee viewRiksdagenCommittee = committees
					.get(0);
			final ViewRiksdagenCommittee viewRiksdagenCommitteeLoaded = dataViewer
					.load(ViewRiksdagenCommittee.class,
							viewRiksdagenCommittee.getEmbeddedId());
			assertNotNull(viewRiksdagenCommitteeLoaded);

			final ViewRiksdagenCommittee viewRiksdagenCommitteeFound = dataViewer
					.findFirstByProperty(ViewRiksdagenCommittee.class,
							ViewRiksdagenCommittee_.embeddedId,
							viewRiksdagenCommitteeLoaded.getEmbeddedId());

			assertEquals(viewRiksdagenCommitteeLoaded,
					viewRiksdagenCommitteeFound);
		}
	}

	/**
	 * View riksdagen politician.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void viewRiksdagenPolitician() throws Exception {

		final List<ViewRiksdagenPolitician> politicians = dataViewer
				.getAll(ViewRiksdagenPolitician.class);
		assertNotNull(politicians);
		if (politicians.size() > 0) {
			final ViewRiksdagenPolitician viewRiksdagenPolitician = politicians
					.get(4);

			final ViewRiksdagenPolitician viewRiksdagenPoliticianLoaded = dataViewer
					.load(ViewRiksdagenPolitician.class,
							viewRiksdagenPolitician.getPersonId());
			assertNotNull(viewRiksdagenPoliticianLoaded);

			final ViewRiksdagenPolitician viewRiksdagenPoliticianFound = dataViewer
					.findFirstByProperty(ViewRiksdagenPolitician.class,
							ViewRiksdagenPolitician_.personId,
							viewRiksdagenPoliticianLoaded.getPersonId());

			assertEquals(viewRiksdagenPoliticianLoaded,
					viewRiksdagenPoliticianFound);


			assertEquals("Should always be 349 in parliament",349,dataViewer
			.findListByProperty(ViewRiksdagenPolitician.class,
					ViewRiksdagenPolitician_.activeParliament,
					true).size());
		}
	}

	/**
	 * View riksdagen party summary.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void viewRiksdagenPartySummary() throws Exception {

		final List<ViewRiksdagenPartySummary> committees = dataViewer
				.getAll(ViewRiksdagenPartySummary.class);
		assertNotNull(committees);
		if (committees.size() > 0) {
			final ViewRiksdagenPartySummary viewRiksdagenPartySummary = committees
					.get(4);
			final ViewRiksdagenPartySummary viewRiksdagenPartySummaryLoaded = dataViewer
					.load(ViewRiksdagenPartySummary.class,
							viewRiksdagenPartySummary.getParty());
			assertNotNull(viewRiksdagenPartySummaryLoaded);

			final ViewRiksdagenPartySummary viewRiksdagenPartySummaryFound = dataViewer
					.findFirstByProperty(ViewRiksdagenPartySummary.class,
							ViewRiksdagenPartySummary_.party,
							viewRiksdagenPartySummaryLoaded.getParty());

			assertEquals(viewRiksdagenPartySummaryLoaded,
					viewRiksdagenPartySummaryFound);
		}

		long parliamentSum = 0;
		long euSum = 0;
		long governmentSum = 0;

		for (final ViewRiksdagenPartySummary viewRiksdagenPartySummary : committees) {
			if (viewRiksdagenPartySummary != null) {
				parliamentSum = parliamentSum
						+ viewRiksdagenPartySummary.getTotalActiveParliament();
				euSum = euSum + viewRiksdagenPartySummary.getTotalActiveEu();
				governmentSum = governmentSum
						+ viewRiksdagenPartySummary.getTotalActiveGovernment();
			}
		}

		final List<ViewRiksdagenPolitician> activeWithNoParty = dataViewer.findListByProperty(ViewRiksdagenPolitician.class, new Object[]  {true,null},ViewRiksdagenPolitician_.activeParliament,ViewRiksdagenPolitician_.party);


		assertEquals("Should always be 349 in parliament", 349, parliamentSum + activeWithNoParty.size());
		assertEquals(
				"Should always be 20 in eu, but riksdagen data contains only 15",
				15, euSum);
		assertEquals("Should always be 24 in current government", 24,
				governmentSum);

	}

}
