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
 *	$Id: ValApi.java 6046 2015-05-06 20:42:53Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/service.external.val/src/main/java/com/hack23/cia/service/external/val/api/ValApi.java $
 */
package com.hack23.cia.service.external.val.api;

import java.util.List;

import com.hack23.cia.model.external.val.kommunvalkrets.impl.SwedenCountyData;
import com.hack23.cia.model.external.val.landstingvalkrets.impl.SwedenCountyElectoralRegion;
import com.hack23.cia.model.external.val.partier.impl.SwedenElectionRegion;
import com.hack23.cia.model.external.val.partier.impl.SwedenElectionType;
import com.hack23.cia.model.external.val.partier.impl.SwedenPoliticalParty;
import com.hack23.cia.model.external.val.riksdagsvalkrets.impl.SwedenParliamentElectoralRegion;

/**
 * The Interface ValApi.
 */
public interface ValApi {

	/**
	 * Gets the election types.
	 *
	 * @return the election types
	 * @throws Exception
	 *             the exception
	 */
	List<SwedenElectionType> getElectionTypes() throws Exception;

	/**
	 * Gets the parliament electoral regions.
	 *
	 * @return the parliament electoral regions
	 * @throws Exception
	 *             the exception
	 */
	List<SwedenParliamentElectoralRegion>  getParliamentElectoralRegions() throws Exception;

	/**
	 * Gets the county electoral regions.
	 *
	 * @return the county electoral regions
	 * @throws Exception
	 *             the exception
	 */
	List<SwedenCountyElectoralRegion> getCountyElectoralRegions() throws Exception;

	/**
	 * Gets the county regions.
	 *
	 * @return the county regions
	 * @throws Exception
	 *             the exception
	 */
	List<SwedenCountyData> getCountyRegions() throws Exception;

	/**
	 * Sweden election region.
	 *
	 * @return the list
	 */
	SwedenElectionRegion getSwedenElectionRegion();

	/**
	 * Gets the sweden political parties.
	 *
	 * @return the list
	 */
	List<SwedenPoliticalParty> getSwedenPoliticalParties();

}
