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
package com.hack23.cia.service.external.val.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.external.val.kommunvalkrets.impl.SwedenCountyData;
import com.hack23.cia.model.external.val.landstingvalkrets.impl.SwedenCountyElectoralRegion;
import com.hack23.cia.model.external.val.partier.impl.SwedenElectionRegion;
import com.hack23.cia.model.external.val.partier.impl.SwedenElectionType;
import com.hack23.cia.model.external.val.partier.impl.SwedenPoliticalParty;
import com.hack23.cia.model.external.val.riksdagsvalkrets.impl.SwedenParliamentElectoralRegion;
import com.hack23.cia.service.external.common.api.XmlAgent;
import com.hack23.cia.service.external.common.api.XmlAgentException;
import com.hack23.cia.service.external.val.api.ValApi;
import com.hack23.cia.service.external.val.api.ValApiException;

/**
 * The Class ValApiTest.
 */
public final class ValApiTest extends AbstractValFunctionalIntegrationTest {

	/** The Constant EXPECT_A_RESULT. */
	private static final String EXPECT_A_RESULT = "Expect a result";

	/** The Constant EXPECTED_TO_MATCH_NUMBER_OF_IMPORT_ENTRIES_IN_DATA_FILE. */
	private static final String EXPECTED_TO_MATCH_NUMBER_OF_IMPORT_ENTRIES_IN_DATA_FILE = "Expected to match number of import entries in data file";

	/** The val api. */
	@Autowired
	private ValApi valApi;

	/**
	 * Test get election types.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetElectionTypes() throws Exception {
		final List<SwedenElectionType> list = valApi.getElectionTypes();
		assertNotNull(EXPECT_A_RESULT,list);
		assertEquals(EXPECTED_TO_MATCH_NUMBER_OF_IMPORT_ENTRIES_IN_DATA_FILE,5, list.size());

	}

	/**
	 * Test get election types failure.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = ValApiException.class)
	public void testGetElectionTypesFailure() throws Exception {
		createMockThrowsXmlAgentException().getElectionTypes();
	}

	/**
	 * Test get sweden political parties.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetSwedenPoliticalParties() throws Exception {
		final List<SwedenPoliticalParty> list = valApi.getSwedenPoliticalParties();
		assertNotNull(EXPECT_A_RESULT,list);
		assertEquals(EXPECTED_TO_MATCH_NUMBER_OF_IMPORT_ENTRIES_IN_DATA_FILE,40, list.size());

	}

	/**
	 * Test get sweden political parties failure.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = ValApiException.class)
	public void testGetSwedenPoliticalPartiesFailure() throws Exception {
		createMockThrowsXmlAgentException().getSwedenPoliticalParties();
	}

	/**
	 * Test sweden election region.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSwedenElectionRegion() throws Exception {
		final SwedenElectionRegion swedenElectionRegion = valApi.getSwedenElectionRegion();
		assertNotNull(EXPECT_A_RESULT,swedenElectionRegion);
	}

	/**
	 * Test sweden election region failure.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = ValApiException.class)
	public void testSwedenElectionRegionFailure() throws Exception {
		createMockThrowsXmlAgentException().getSwedenElectionRegion();
	}

	/**
	 * Creates the mock throws xml agent exception.
	 *
	 * @return the val api
	 * @throws XmlAgentException
	 *             the xml agent exception
	 */
	private ValApi createMockThrowsXmlAgentException() throws XmlAgentException {
		final XmlAgent xmlAgent = mock(XmlAgent.class);
		final ValApi valApiMockedXmlAgent = new ValApiImpl(xmlAgent);
		Mockito.when(xmlAgent.unmarshallXml(isNull(),any(String.class),any(String.class),isNull(),isNull())).thenThrow(new XmlAgentException(new RuntimeException()));
		return valApiMockedXmlAgent;
	}


	/**
	 * Test get county electoral regions.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetCountyElectoralRegions() throws Exception {
		final List<SwedenCountyElectoralRegion> list = valApi.getCountyElectoralRegions();
		assertNotNull(EXPECT_A_RESULT,list);
		assertEquals(EXPECTED_TO_MATCH_NUMBER_OF_IMPORT_ENTRIES_IN_DATA_FILE,20, list.size());
	}

	/**
	 * Test get county electoral regions failure.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = ValApiException.class)
	public void testGetCountyElectoralRegionsFailure() throws Exception {
		createMockThrowsXmlAgentException().getCountyElectoralRegions();
	}

	/**
	 * Test get parliament electoral regions.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetParliamentElectoralRegions() throws Exception {
		final List<SwedenParliamentElectoralRegion> list = valApi.getParliamentElectoralRegions();
		assertNotNull(EXPECT_A_RESULT,list);
		assertEquals(EXPECTED_TO_MATCH_NUMBER_OF_IMPORT_ENTRIES_IN_DATA_FILE,29, list.size());
	}


	/**
	 * Test get parliament electoral regions failure.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = ValApiException.class)
	public void testGetParliamentElectoralRegionsFailure() throws Exception {
		createMockThrowsXmlAgentException().getParliamentElectoralRegions();
	}

	/**
	 * Test get county regions.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetCountyRegions() throws Exception {
		final List<SwedenCountyData> list = valApi.getCountyRegions();
		assertNotNull(EXPECT_A_RESULT,list);
		assertEquals(EXPECTED_TO_MATCH_NUMBER_OF_IMPORT_ENTRIES_IN_DATA_FILE,21, list.size());
	}

	/**
	 * Test get county regions failure.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = ValApiException.class)
	public void testGetCountyRegionsFailure() throws Exception {
		createMockThrowsXmlAgentException().getCountyRegions();
	}


}
