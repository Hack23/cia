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

import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData_;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee_;
import com.hack23.cia.model.internal.application.data.document.impl.RiksdagenDocumentPersonSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.document.impl.RiksdagenDocumentPersonSummaryEmbeddedId_;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocumentDailySummary;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocumentDailySummary_;
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

	/** The Constant EXPECT_VALUE_IN_DATABASE. */
	private static final String EXPECT_VALUE_IN_DATABASE = "Expect value in database";

	/** The Constant SHOULD_ALWAYS_BE_349_IN_PARLIAMENT. */
	private static final String SHOULD_ALWAYS_BE_349_IN_PARLIAMENT = "Should always be 349 in parliament";

	/** The Constant EXPECT_SAME_OBJECT_LOADED. */
	private static final String EXPECT_SAME_OBJECT_LOADED = "Expect same object loaded";

	/** The data viewer. */
	@Autowired
	private DataViewer dataViewer;

	// @Rule
	// public ContiPerfRule i = new ContiPerfRule();

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
		assertNotNull(EXPECT_VALUE_IN_DATABASE,committees);
		if (committees.size() > 0) {
			final ViewRiksdagenCommittee viewRiksdagenCommittee = committees
					.get(0);
			final ViewRiksdagenCommittee viewRiksdagenCommitteeLoaded = dataViewer
					.load(ViewRiksdagenCommittee.class,
							viewRiksdagenCommittee.getEmbeddedId());
			assertNotNull(EXPECT_VALUE_IN_DATABASE,viewRiksdagenCommitteeLoaded);

			final ViewRiksdagenCommittee viewRiksdagenCommitteeFound = dataViewer
					.findFirstByProperty(ViewRiksdagenCommittee.class,
							ViewRiksdagenCommittee_.embeddedId,
							viewRiksdagenCommitteeLoaded.getEmbeddedId());

			assertEquals(EXPECT_SAME_OBJECT_LOADED,viewRiksdagenCommitteeLoaded,
					viewRiksdagenCommitteeFound);
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
		assertNotNull(EXPECT_VALUE_IN_DATABASE,ministries);
		if (ministries.size() > 0) {
			final ViewRiksdagenMinistry viewRiksdagenMinistry = ministries
					.get(0);
			final ViewRiksdagenMinistry viewRiksdagenMinistryLoaded = dataViewer
					.load(ViewRiksdagenMinistry.class,
							viewRiksdagenMinistry.getNameId());
			assertNotNull(EXPECT_VALUE_IN_DATABASE,viewRiksdagenMinistryLoaded);

			final ViewRiksdagenMinistry viewRiksdagenMinistryFound = dataViewer
					.findFirstByProperty(ViewRiksdagenMinistry.class,
							ViewRiksdagenMinistry_.nameId,
							viewRiksdagenMinistryLoaded.getNameId());

			assertEquals(EXPECT_SAME_OBJECT_LOADED,viewRiksdagenMinistryLoaded,
					viewRiksdagenMinistryFound);
		}
	}

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
		assertNotNull(EXPECT_VALUE_IN_DATABASE,parties);
		if (parties.size() > 0) {
			final ViewRiksdagenParty viewRiksdagenParty = parties.get(0);
			final ViewRiksdagenParty viewRiksdagenPartyLoaded = dataViewer
					.load(ViewRiksdagenParty.class,
							viewRiksdagenParty.getPartyId());
			assertNotNull(EXPECT_VALUE_IN_DATABASE,viewRiksdagenPartyLoaded);

			final ViewRiksdagenParty viewRiksdagenPartyFound = dataViewer
					.findFirstByProperty(ViewRiksdagenParty.class,
							ViewRiksdagenParty_.partyName,
							viewRiksdagenPartyLoaded.getPartyName());

			assertEquals(EXPECT_SAME_OBJECT_LOADED,viewRiksdagenPartyLoaded, viewRiksdagenPartyFound);
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
		assertNotNull(EXPECT_VALUE_IN_DATABASE,committees);
		if (committees.size() > 0) {
			final ViewRiksdagenPartySummary viewRiksdagenPartySummary = committees
					.get(4);
			final ViewRiksdagenPartySummary viewRiksdagenPartySummaryLoaded = dataViewer
					.load(ViewRiksdagenPartySummary.class,
							viewRiksdagenPartySummary.getParty());
			assertNotNull(EXPECT_VALUE_IN_DATABASE,viewRiksdagenPartySummaryLoaded);

			final ViewRiksdagenPartySummary viewRiksdagenPartySummaryFound = dataViewer
					.findFirstByProperty(ViewRiksdagenPartySummary.class,
							ViewRiksdagenPartySummary_.party,
							viewRiksdagenPartySummaryLoaded.getParty());

			assertEquals(EXPECT_SAME_OBJECT_LOADED,viewRiksdagenPartySummaryLoaded,
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

		assertEquals(SHOULD_ALWAYS_BE_349_IN_PARLIAMENT, 349, parliamentSum + activeWithNoParty.size());
		assertEquals(
				"Should always be 20 in eu, but riksdagen data contains only 15",
				15, euSum);
		assertEquals("Should always be 23 in current government", 23,
				governmentSum);

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
		assertNotNull(EXPECT_VALUE_IN_DATABASE,politicians);
		if (politicians.size() > 0) {
			final ViewRiksdagenPolitician viewRiksdagenPolitician = politicians
					.get(4);

			final ViewRiksdagenPolitician viewRiksdagenPoliticianLoaded = dataViewer
					.load(ViewRiksdagenPolitician.class,
							viewRiksdagenPolitician.getPersonId());
			assertNotNull(EXPECT_VALUE_IN_DATABASE,viewRiksdagenPoliticianLoaded);

			final ViewRiksdagenPolitician viewRiksdagenPoliticianFound = dataViewer
					.findFirstByProperty(ViewRiksdagenPolitician.class,
							ViewRiksdagenPolitician_.personId,
							viewRiksdagenPoliticianLoaded.getPersonId());

			assertEquals(EXPECT_SAME_OBJECT_LOADED,viewRiksdagenPoliticianLoaded,
					viewRiksdagenPoliticianFound);


			assertEquals(SHOULD_ALWAYS_BE_349_IN_PARLIAMENT,349,dataViewer
			.findListByProperty(ViewRiksdagenPolitician.class,
					ViewRiksdagenPolitician_.activeParliament,
					true).size());
		}
	}



	/**
	 * Find by query property test.
	 */
	@Test
	public void findByQueryPropertyTest() {
		DocumentStatusContainer findByQueryProperty = dataViewer.findByQueryProperty(DocumentStatusContainer.class, DocumentStatusContainer_.document,
				DocumentData.class, DocumentData_.id, "H501NU6");
		assertNotNull(findByQueryProperty);
	}

	/**
	 * Find list by embedded property test.
	 */
	@Test
	public void findListByEmbeddedPropertyTest() {
		List<ViewRiksdagenPoliticianDocumentDailySummary> findListByEmbeddedProperty = dataViewer.findListByEmbeddedProperty(ViewRiksdagenPoliticianDocumentDailySummary.class, ViewRiksdagenPoliticianDocumentDailySummary_.embeddedId, RiksdagenDocumentPersonSummaryEmbeddedId.class, RiksdagenDocumentPersonSummaryEmbeddedId_.documentType, "mot");
		assertNotNull(findListByEmbeddedProperty);
		assertFalse(findListByEmbeddedProperty.isEmpty());
	}

	/**
	 * Find ordered by property list by embedded property test.
	 */
	@Test
	public void findOrderedByPropertyListByEmbeddedPropertyTest() {
		List<ViewRiksdagenPoliticianDocumentDailySummary> findListByEmbeddedProperty = dataViewer.findOrderedByPropertyListByEmbeddedProperty(ViewRiksdagenPoliticianDocumentDailySummary.class, ViewRiksdagenPoliticianDocumentDailySummary_.embeddedId, RiksdagenDocumentPersonSummaryEmbeddedId.class, RiksdagenDocumentPersonSummaryEmbeddedId_.documentType, "mot",ViewRiksdagenPoliticianDocumentDailySummary_.total);
		assertNotNull(findListByEmbeddedProperty);
		assertFalse(findListByEmbeddedProperty.isEmpty());
	}


	/**
	 * Find ordered list by embedded property test.
	 */
	@Test
	public void findOrderedListByEmbeddedPropertyTest() {
		List<ViewRiksdagenPoliticianDocumentDailySummary> findListByEmbeddedProperty = dataViewer.findOrderedListByEmbeddedProperty(ViewRiksdagenPoliticianDocumentDailySummary.class, ViewRiksdagenPoliticianDocumentDailySummary_.embeddedId, RiksdagenDocumentPersonSummaryEmbeddedId.class, RiksdagenDocumentPersonSummaryEmbeddedId_.documentType, "mot",RiksdagenDocumentPersonSummaryEmbeddedId_.personId);
		assertNotNull(findListByEmbeddedProperty);
		assertFalse(findListByEmbeddedProperty.isEmpty());
	}

	/**
	 * Gets the page test.
	 *
	 * @return the page test
	 */
	@Test
	public void getPageTest() {
		int resultPerPage = 10;
		List<ViewRiksdagenPolitician> pageOrderBy = dataViewer.getPage(ViewRiksdagenPolitician.class, 1, resultPerPage);
		assertNotNull(pageOrderBy);
		assertFalse(pageOrderBy.isEmpty());
		assertEquals(resultPerPage,pageOrderBy.size());
	}

	/**
	 * Gets the page order by test.
	 *
	 * @return the page order by test
	 */
	@Test
	public void getPageOrderByTest() {
		int resultPerPage = 10;
		List<ViewRiksdagenPolitician> pageOrderBy = dataViewer.getPageOrderBy(ViewRiksdagenPolitician.class, 1, resultPerPage, ViewRiksdagenPolitician_.bornYear);
		assertNotNull(pageOrderBy);
		assertFalse(pageOrderBy.isEmpty());
		assertEquals(resultPerPage,pageOrderBy.size());
	}

	/**
	 * Gets the size test.
	 *
	 * @return the size test
	 */
	@Test
	public void getSizeTest() {
		assertNotNull(dataViewer.getSize(ViewRiksdagenPolitician.class));
	}


}
