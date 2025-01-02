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
package com.hack23.cia.service.external.esv.impl;

import static org.mockito.Mockito.times;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;

import com.hack23.cia.service.external.esv.api.EsvApi;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualOutcomeSummary;
import com.hack23.cia.service.external.esv.api.GovernmentOperationPeriodOutcome;
import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * The Class EsvApImplTest.
 */
public class EsvApImplTest extends AbstractUnitTest {

	/**
	 * Gets the report failure test.
	 *
	 * @return the report failure test
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void getReportFailureTest() throws IOException {
		final EsvGovernmentOperationsExcelReader mock = Mockito.mock(EsvGovernmentOperationsExcelReader.class);
		final EsvApi esvApi = new EsvApiImpl(null, mock,null);
		Mockito.when(mock.getReport()).thenThrow(new IOException());
		final Map<String, List<GovernmentOperationPeriodOutcome>> report = esvApi.getReport();
		assertTrue(report.isEmpty());
		Mockito.verify(mock,times(1)).getReport();
	}

	/**
	 * Gets the government body report failure test.
	 *
	 * @return the government body report failure test
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void getGovernmentBodyReportFailureTest() throws IOException {
		final EsvGovernmentBodyOperationOutcomeReader mock = Mockito.mock(EsvGovernmentBodyOperationOutcomeReader.class);
		final EsvApi esvApi = new EsvApiImpl(null, null, mock);
		Mockito.when(mock.readIncomeCsv()).thenThrow(new IOException());
		final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> report = esvApi.getGovernmentBodyReport();
		assertTrue(report.isEmpty());
		Mockito.verify(mock,times(1)).readIncomeCsv();
	}



}
