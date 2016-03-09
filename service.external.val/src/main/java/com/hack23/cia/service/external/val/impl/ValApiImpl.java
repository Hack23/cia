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

import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.val.kommunvalkrets.impl.SwedenCountyData;
import com.hack23.cia.model.external.val.kommunvalkrets.impl.SwedenCountyDataContainer;
import com.hack23.cia.model.external.val.landstingvalkrets.impl.SwedenCountyElectoralRegion;
import com.hack23.cia.model.external.val.landstingvalkrets.impl.SwedenCountyElectoralRegionContainer;
import com.hack23.cia.model.external.val.partier.impl.SwedenElectionRegion;
import com.hack23.cia.model.external.val.partier.impl.SwedenElectionType;
import com.hack23.cia.model.external.val.partier.impl.SwedenElectionTypeContainerElement;
import com.hack23.cia.model.external.val.partier.impl.SwedenPoliticalParty;
import com.hack23.cia.model.external.val.riksdagsvalkrets.impl.SwedenParliamentElectoralRegion;
import com.hack23.cia.model.external.val.riksdagsvalkrets.impl.SwedenParliamentElectoralRegionContainer;
import com.hack23.cia.service.external.common.api.XmlAgent;
import com.hack23.cia.service.external.val.api.ValApi;

/**
 * The Class ValApiImpl.
 */
@Component
public final class ValApiImpl implements ValApi {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ValApiImpl.class);

	/** The xml agent. */
	@Autowired
	private XmlAgent xmlAgent;

	/** The val partier marshaller. */
	@Autowired
	@Qualifier("valPartierMarshaller")
	private Unmarshaller valPartierMarshaller;

	/** The val riksdag marshaller. */
	@Autowired
	@Qualifier("valRiksdagMarshaller")
	private Unmarshaller valRiksdagMarshaller;

	/** The val landsting marshaller. */
	@Autowired
	@Qualifier("valLandstingMarshaller")
	private Unmarshaller valLandstingMarshaller;

	/** The val kommun marshaller. */
	@Autowired
	@Qualifier("valKommunMarshaller")
	private Unmarshaller valKommunMarshaller;

	/* {@inheritDoc}
	 * @see com.hack23.cia.service.external.val.api.ValApi#getElectionTypes()
	 */
	@Override
	public List<SwedenElectionType> getElectionTypes() throws Exception {
		final URL resource = ValApiImpl.class.getResource("/partier20151217.xml");

		return ((JAXBElement<SwedenElectionTypeContainerElement>) xmlAgent
				.unmarshallXml(
						valPartierMarshaller,
						resource.toString(),
						"http://partier.val.external.model.cia.hack23.com/impl",
						null, null)).getValue().getElectionTypes();
	}

	/* {@inheritDoc}
	 * @see com.hack23.cia.service.external.val.api.ValApi#getParliamentElectoralRegions()
	 */
	@Override
	public List<SwedenParliamentElectoralRegion> getParliamentElectoralRegions()
			throws Exception {
		final URL resource = ValApiImpl.class
				.getResource("/riksdagsvalkrets.xml");

		return ((JAXBElement<SwedenParliamentElectoralRegionContainer>) xmlAgent
				.unmarshallXml(
						valRiksdagMarshaller,
						resource.toString(),
						"http://riksdagsvalkrets.val.external.model.cia.hack23.com/impl",
						null, null)).getValue().getParliamentElectoralRegions();
	}

	/* {@inheritDoc}
	 * @see com.hack23.cia.service.external.val.api.ValApi#getCountyElectoralRegions()
	 */
	@Override
	public List<SwedenCountyElectoralRegion> getCountyElectoralRegions()
			throws Exception {
		final URL resource = ValApiImpl.class
				.getResource("/landstingvalkrets.xml");

		return ((JAXBElement<SwedenCountyElectoralRegionContainer>) xmlAgent
				.unmarshallXml(
						valLandstingMarshaller,
						resource.toString(),
						"http://landstingvalkrets.val.external.model.cia.hack23.com/impl",
						null, null)).getValue().getCountyElectoralRegions();
	}

	/* {@inheritDoc}
	 * @see com.hack23.cia.service.external.val.api.ValApi#getCountyRegions()
	 */
	@Override
	public List<SwedenCountyData> getCountyRegions() throws Exception {
		final URL resource = ValApiImpl.class
				.getResource("/kommunvalkrets.xml");

		return ((JAXBElement<SwedenCountyDataContainer>) xmlAgent.unmarshallXml(
				valKommunMarshaller, resource.toString(),
				"http://kommunvalkrets.val.external.model.cia.hack23.com/impl",
				null, null)).getValue().getCountyRegions();
	}

	/* {@inheritDoc}
	 * @see com.hack23.cia.service.external.val.api.ValApi#getSwedenElectionRegion()
	 */
	@Override
	public SwedenElectionRegion getSwedenElectionRegion() {
		final URL resource = ValApiImpl.class.getResource("/partier20151217.xml");

		try {
			return ((JAXBElement<SwedenElectionTypeContainerElement>) xmlAgent
					.unmarshallXml(
							valPartierMarshaller,
							resource.toString(),
							"http://partier.val.external.model.cia.hack23.com/impl",
							null, null)).getValue().getElectionTypes().get(0)
							.getRegion();
		} catch (final Exception e) {
			LOGGER.warn("Problem reading election region",e);
			return null;
		}

	}

	/* {@inheritDoc}
	 * @see com.hack23.cia.service.external.val.api.ValApi#getSwedenPoliticalParties()
	 */
	@Override
	public List<SwedenPoliticalParty> getSwedenPoliticalParties() {
		return getSwedenElectionRegion().getParties();
	}

}
