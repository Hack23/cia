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
package com.hack23.cia.service.external.val.impl;

import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBElement;

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
import com.hack23.cia.service.external.common.api.XmlAgentException;
import com.hack23.cia.service.external.val.api.ValApi;
import com.hack23.cia.service.external.val.api.ValApiException;

/**
 * The Class ValApiImpl.
 */
@Component
final class ValApiImpl implements ValApi {

	/** The xml agent. */
	@Autowired
	private final XmlAgent xmlAgent;

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

	/**
	 * Instantiates a new val api impl.
	 *
	 * @param xmlAgent
	 *            the xml agent
	 */
	@Autowired
	public ValApiImpl(final XmlAgent xmlAgent) {
		super();
		this.xmlAgent = xmlAgent;
	}

	@Override
	public List<SwedenElectionType> getElectionTypes() throws ValApiException {

		try {
			final URL resource = ValApiImpl.class.getResource("/partier20151217.xml");
			return ((JAXBElement<SwedenElectionTypeContainerElement>) xmlAgent.unmarshallXml(valPartierMarshaller,
					resource.toString(), "http://partier.val.external.model.cia.hack23.com/impl", null, null))
							.getValue().getElectionTypes();
		} catch (final XmlAgentException e) {
			throw new ValApiException("Problem getElectionTypes",e);
		}
	}

	@Override
	public List<SwedenParliamentElectoralRegion> getParliamentElectoralRegions() throws ValApiException {
		try {
			final URL resource = ValApiImpl.class.getResource("/riksdagsvalkrets.xml");

			return ((JAXBElement<SwedenParliamentElectoralRegionContainer>) xmlAgent.unmarshallXml(valRiksdagMarshaller,
					resource.toString(), "http://riksdagsvalkrets.val.external.model.cia.hack23.com/impl", null, null))
							.getValue().getParliamentElectoralRegions();
		} catch (final XmlAgentException e) {
			throw new ValApiException("Problem getParliamentElectoralRegions",e);
		}

	}

	@Override
	public List<SwedenCountyElectoralRegion> getCountyElectoralRegions() throws ValApiException {
		try {
			final URL resource = ValApiImpl.class.getResource("/landstingvalkrets.xml");

			return ((JAXBElement<SwedenCountyElectoralRegionContainer>) xmlAgent.unmarshallXml(valLandstingMarshaller,
					resource.toString(), "http://landstingvalkrets.val.external.model.cia.hack23.com/impl", null, null))
							.getValue().getCountyElectoralRegions();
		} catch (final XmlAgentException e) {
			throw new ValApiException("Problem getCountyElectoralRegions",e);
		}

	}

	@Override
	public List<SwedenCountyData> getCountyRegions() throws ValApiException {
		try {
			final URL resource = ValApiImpl.class.getResource("/kommunvalkrets.xml");

			return ((JAXBElement<SwedenCountyDataContainer>) xmlAgent.unmarshallXml(valKommunMarshaller,
					resource.toString(), "http://kommunvalkrets.val.external.model.cia.hack23.com/impl", null, null))
							.getValue().getCountyRegions();
		} catch (final XmlAgentException e) {
			throw new ValApiException("Problem getCountyRegions",e);
		}
	}

	@Override
	public SwedenElectionRegion getSwedenElectionRegion() throws ValApiException {
		final URL resource = ValApiImpl.class.getResource("/partier20151217.xml");

		try {
			return ((JAXBElement<SwedenElectionTypeContainerElement>) xmlAgent.unmarshallXml(valPartierMarshaller,
					resource.toString(), "http://partier.val.external.model.cia.hack23.com/impl", null, null))
							.getValue().getElectionTypes().get(0).getRegion();
		} catch (final XmlAgentException e) {
			throw new ValApiException("Problem getSwedenElectionRegion",e);
		}

	}

	@Override
	public List<SwedenPoliticalParty> getSwedenPoliticalParties() throws ValApiException {
		return getSwedenElectionRegion().getParties();
	}

}
