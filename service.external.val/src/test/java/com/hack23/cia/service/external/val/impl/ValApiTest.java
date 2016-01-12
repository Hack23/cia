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
package com.hack23.cia.service.external.val.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.external.val.kommunvalkrets.impl.SwedenCountyData;
import com.hack23.cia.model.external.val.landstingvalkrets.impl.SwedenCountyElectoralRegion;
import com.hack23.cia.model.external.val.partier.impl.SwedenElectionRegion;
import com.hack23.cia.model.external.val.partier.impl.SwedenElectionType;
import com.hack23.cia.model.external.val.partier.impl.SwedenPoliticalParty;
import com.hack23.cia.model.external.val.riksdagsvalkrets.impl.SwedenParliamentElectoralRegion;
import com.hack23.cia.service.external.val.api.ValApi;

/**
 * The Class ValApiTest.
 */
public class ValApiTest extends AbstractValFunctionalIntegrationTest {

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
		assertNotNull(list);
		assertEquals(5, list.size());

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
		assertNotNull(list);
		assertEquals(38, list.size());

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
		assertNotNull(swedenElectionRegion);
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
		assertNotNull(list);
		assertEquals(20, list.size());
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
		assertNotNull(list);
		assertEquals(29, list.size());
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
		assertNotNull(list);
		assertEquals(21, list.size());
	}


}
