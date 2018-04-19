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
package com.hack23.cia.service.external.esv.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.external.esv.api.EsvApi;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualOutcomeSummary;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;
import com.hack23.cia.service.external.esv.api.GovernmentOperationPeriodOutcome;

/**
 * The Class EsvApiTest.
 */
public final class EsvApiTest extends AbstractEsvFunctionalIntegrationTest {

	/** The esv api. */
	@Autowired
	private EsvApi esvApi;

	/**
	 * Gets the data defence ministry success test.
	 *
	 * @return the data defence ministry success test
	 */
	@Test
	public void getDataDefenceMinistrySuccessTest() {
		final Map<Integer, List<GovernmentBodyAnnualSummary>> governmentBodyAnnualSummaryData = esvApi
				.getDataPerMinistry("Försvarsdepartementet");
		assertNotNull(governmentBodyAnnualSummaryData);
		assertEquals(20, governmentBodyAnnualSummaryData.size());
		for (final List<GovernmentBodyAnnualSummary> list : governmentBodyAnnualSummaryData.values()) {
			for (final GovernmentBodyAnnualSummary governmentBodyAnnualSummary : list) {
				assertNotNull(governmentBodyAnnualSummary);
			}
		}
	}

	/**
	 * Gets the data finance ministry 1900 failure test.
	 *
	 * @return the data finance ministry 1900 failure test
	 */
	@Test
	public void getDataFinanceMinistry1900FailureTest() {
		final List<GovernmentBodyAnnualSummary> list = esvApi.getDataPerMinistryAndYear("Finansdepartementet", 1900);
		assertNotNull(list);
		assertEquals(0, list.size());
	}

	/**
	 * Gets the data finance ministry 1999 success test.
	 *
	 * @return the data finance ministry 1999 success test
	 */
	@Test
	public void getDataFinanceMinistry1999SuccessTest() {
		final List<GovernmentBodyAnnualSummary> list = esvApi.getDataPerMinistryAndYear("Finansdepartementet", 1999);
		assertNotNull(list);
		assertEquals(36, list.size());
		for (final GovernmentBodyAnnualSummary governmentBodyAnnualSummary : list) {
			assertNotNull(governmentBodyAnnualSummary);
		}
	}

	/**
	 * Gets the data finance ministry 2016 success test.
	 *
	 * @return the data finance ministry 2016 success test
	 */
	@Test
	public void getDataFinanceMinistry2016SuccessTest() {
		final List<GovernmentBodyAnnualSummary> list = esvApi.getDataPerMinistryAndYear("Finansdepartementet", 2016);
		assertNotNull(list);
		assertEquals(45, list.size());
		for (final GovernmentBodyAnnualSummary governmentBodyAnnualSummary : list) {
			assertNotNull(governmentBodyAnnualSummary);
		}
	}

	@Test
	public void getDataFinanceMinistry2017SuccessTest() {
		final List<GovernmentBodyAnnualSummary> list = esvApi.getDataPerMinistryAndYear("Finansdepartementet", 2017);
		assertNotNull(list);
		assertEquals(44, list.size());
		for (final GovernmentBodyAnnualSummary governmentBodyAnnualSummary : list) {
			assertNotNull(governmentBodyAnnualSummary);
		}
	}

	
	/**
	 * Gets the data finance ministry success test.
	 *
	 * @return the data finance ministry success test
	 */
	@Test
	public void getDataFinanceMinistrySuccessTest() {
		final Map<Integer, List<GovernmentBodyAnnualSummary>> governmentBodyAnnualSummaryData = esvApi
				.getDataPerMinistry("Finansdepartementet");
		assertNotNull(governmentBodyAnnualSummaryData);
		assertEquals(20, governmentBodyAnnualSummaryData.size());
		for (final List<GovernmentBodyAnnualSummary> list : governmentBodyAnnualSummaryData.values()) {
			for (final GovernmentBodyAnnualSummary governmentBodyAnnualSummary : list) {
				assertNotNull(governmentBodyAnnualSummary);
			}
		}
	}

	/**
	 * Gets the data foreign ministry success test.
	 *
	 * @return the data foreign ministry success test
	 */
	@Test
	public void getDataForeignMinistrySuccessTest() {
		final Map<Integer, List<GovernmentBodyAnnualSummary>> governmentBodyAnnualSummaryData = esvApi
				.getDataPerMinistry("Utrikesdepartementet");
		assertNotNull(governmentBodyAnnualSummaryData);
		assertEquals(20, governmentBodyAnnualSummaryData.size());
		for (final List<GovernmentBodyAnnualSummary> list : governmentBodyAnnualSummaryData.values()) {
			for (final GovernmentBodyAnnualSummary governmentBodyAnnualSummary : list) {
				assertNotNull(governmentBodyAnnualSummary);
			}
		}
	}

	/**
	 * Gets the data per government body success test.
	 *
	 * @return the data per government body success test
	 */
	@Test
	public void getDataPerGovernmentBodySuccessTest() {
		final Map<Integer, GovernmentBodyAnnualSummary> governmentBodyAnnualSummaryData = esvApi
				.getDataPerGovernmentBody("Exportkreditnämnden");
		assertNotNull(governmentBodyAnnualSummaryData);
		assertEquals(20, governmentBodyAnnualSummaryData.size());
		for (final GovernmentBodyAnnualSummary governmentBodyAnnualSummary : governmentBodyAnnualSummaryData.values()) {
			assertNotNull(governmentBodyAnnualSummary);
		}
	}

	/**
	 * Gets the data success test.
	 *
	 * @return the data success test
	 */
	@Test
	public void getDataSuccessTest() {
		final Map<Integer, List<GovernmentBodyAnnualSummary>> governmentBodyAnnualSummaryData = esvApi.getData();
		assertNotNull(governmentBodyAnnualSummaryData);
		assertEquals(20, governmentBodyAnnualSummaryData.size());
		for (final List<GovernmentBodyAnnualSummary> list : governmentBodyAnnualSummaryData.values()) {
			//assertTrue(list.size() > 200);
			for (final GovernmentBodyAnnualSummary governmentBodyAnnualSummary : list) {
				assertNotNull(governmentBodyAnnualSummary);
			}
		}
	}

	/**
	 * Gets the government body names success test.
	 *
	 * @return the government body names success test
	 */
	@Test
	public void getGovernmentBodyNamesSuccessTest() {
		final List<String> list = esvApi.getGovernmentBodyNames();
		assertNotNull(list);
		assertEquals(330, list.size());
	}

	/**
	 * Gets the ministry names success test.
	 *
	 * @return the ministry names success test
	 */
	@Test
	public void getMinistryNamesSuccessTest() {
		final List<String> list = esvApi.getMinistryNames();		
		assertNotNull(list);
		assertEquals(16, list.size());
	}

	/**
	 * Gets the government body names foreign ministry success test.
	 *
	 * @return the government body names foreign ministry success test
	 */
	@Test
	public void getGovernmentBodyNamesForeignMinistrySuccessTest() {
		final List<String> list = esvApi.getGovernmentBodyNames("Utrikesdepartementet");
		assertNotNull(list);
		assertEquals(14, list.size());
	}

	@Test
	public void verifyMinistryAnnualSummaryTest() {
		final Map<Integer, List<GovernmentBodyAnnualSummary>> map = esvApi
				.getDataPerMinistry("Landsbygdsdepartementet");
		assertNotNull(map);
		for (final Entry<Integer, List<GovernmentBodyAnnualSummary>> entry : map.entrySet()) {

			final List<GovernmentBodyAnnualSummary> item = entry.getValue();
			final Integer totalHeadcount = item.stream().collect(Collectors.summingInt(p -> p.getHeadCount()));

			if (entry.getKey() != null && item != null) {
				assertNotNull(totalHeadcount);
			}
		}
	}

	/**
	 * Verify ministry government body annual summary test.
	 */
	@Test
	public void verifyMinistryGovernmentBodyAnnualSummaryTest() {
		final Map<Integer, List<GovernmentBodyAnnualSummary>> map = esvApi.getDataPerMinistry("Försvarsdepartementet");

		final List<String> govBodyNames = esvApi.getGovernmentBodyNames("Försvarsdepartementet");
		assertNotNull(govBodyNames);

		for (final String govBodyName : govBodyNames) {
			assertNotNull(govBodyName);

			for (final Entry<Integer, List<GovernmentBodyAnnualSummary>> entry : map.entrySet()) {

				final List<GovernmentBodyAnnualSummary> item = entry.getValue();
				final Integer totalHeadcount = item.stream().filter(p -> p.getName().equalsIgnoreCase(govBodyName)).collect(Collectors.summingInt(p -> p.getHeadCount()));

				if (entry.getKey() != null && item != null) {
					assertNotNull(totalHeadcount);
				}
			}
		}
	}

	/**
	 * Verify all ministry annual summary test.
	 */
	@Test
	public void verifyAllMinistryAnnualSummaryTest() {
		final Map<Integer, List<GovernmentBodyAnnualSummary>> map = esvApi.getData();
		final List<String> ministryNames = esvApi.getMinistryNames();
		assertNotNull(ministryNames);
		for (final String ministryName : ministryNames) {

			assertNotNull(ministryName);
			for (final Entry<Integer, List<GovernmentBodyAnnualSummary>> entry : map.entrySet()) {

				final List<GovernmentBodyAnnualSummary> item = entry.getValue();
				final Integer totalHeadcount = item.stream().filter(p -> p.getMinistry().equalsIgnoreCase(ministryName))
						.collect(Collectors.summingInt(p -> p.getHeadCount()));

				if (entry.getKey() != null && item != null) {
					assertNotNull(totalHeadcount);
				}
			}
		}
	}
	
	@Test
	public void getReportTest() {
		final Map<String, List<GovernmentOperationPeriodOutcome>> report = esvApi.getReport();
		assertNotNull(report);
		assertFalse(report.isEmpty());
		final List<GovernmentOperationPeriodOutcome> list = report.get(GovernmentOperationPeriodOutcome.Variables.BUDGET_BALANCE.toString());
		assertNotNull(list);
		assertFalse(list.isEmpty());
	}
	

	@Test
	public void getGovernmentBodyReportTest() {
		final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> report = esvApi.getGovernmentBodyReport();
		assertNotNull(report);
		assertFalse(report.isEmpty());
	}
	
	@Test
	public void getGovernmentBodyReportMapByFieldIncomeGroupTest() {
		final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> report = esvApi.getGovernmentBodyReportByField("Inkomsttitelgruppsnamn");
		assertNotNull(report);
		assertFalse(report.isEmpty());
	}

	@Test
	public void getGovernmentBodyReportMapByFieldOutcomeGroupTest() {
		final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> report = esvApi.getGovernmentBodyReportByField("Utgiftsområdesnamn");
		assertNotNull(report);
		assertFalse(report.isEmpty());
	}
	
}
