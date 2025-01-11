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
package com.hack23.cia.service.external.riksdagen.impl;

import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData;
import com.hack23.cia.service.external.common.api.XmlAgent;
import com.hack23.cia.service.external.common.api.XmlAgentException;
import com.hack23.cia.service.external.riksdagen.api.DataFailureException;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenCommitteeProposalApi;

/**
 * The Class RiksdagenCommitteeProposalApiImpl.
 */
@Component
final class RiksdagenCommitteeProposalApiImpl implements RiksdagenCommitteeProposalApi {

	/** The Constant COMMITTE_PROPOSAL. */
	private static final String COMMITTE_PROPOSAL = "https://data.riksdagen.se/utskottsforslag/${ID_KEY}/xml";

	/**
	 * The Constant
	 * HTTP_UTSKOTTSFORSLAG_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String HTTP_UTSKOTTSFORSLAG_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "http://utskottsforslag.riksdagen.external.model.cia.hack23.com/impl";

	/** The Constant ID_KEY. */
	private static final String ID_KEY = "${ID_KEY}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RiksdagenCommitteeProposalApiImpl.class);

	/**
	 * The Constant
	 * PROBLEM_GETTING_COMMITTEE_PROPOSAL_FOR_ID_S_FROM_DATA_RIKSDAGEN_SE.
	 */
	private static final String PROBLEM_GETTING_COMMITTEE_PROPOSAL_FOR_ID_S_FROM_DATA_RIKSDAGEN_SE = "Problem getting committee proposal for id:{} from data.riksdagen.se";

	/** The riksdagen committee proposal marshaller. */
	@Autowired
	@Qualifier("riksdagenCommitteeProposalMarshaller")
	private Unmarshaller riksdagenCommitteeProposalMarshaller;

	/** The xml agent. */
	private final XmlAgent xmlAgent;

	/**
	 * Instantiates a new riksdagen committee proposal api impl.
	 *
	 * @param xmlAgent
	 *            the xml agent
	 */
	@Autowired
	public RiksdagenCommitteeProposalApiImpl(final XmlAgent xmlAgent) {
		super();
		this.xmlAgent = xmlAgent;
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.riksdagen.api.RiksdagenCommitteeProposalApi#getCommitteeProposal(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CommitteeProposalComponentData getCommitteeProposal(final String id) throws DataFailureException {
		try {
			final String url = COMMITTE_PROPOSAL.replace(ID_KEY, UrlHelper.urlEncode(id,StandardCharsets.UTF_8.toString()));
			return ((JAXBElement<CommitteeProposalComponentData>) xmlAgent.unmarshallXml(
					riksdagenCommitteeProposalMarshaller, url,
					HTTP_UTSKOTTSFORSLAG_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL, null, null)).getValue();
		} catch (final XmlAgentException e) {
			LOGGER.warn(PROBLEM_GETTING_COMMITTEE_PROPOSAL_FOR_ID_S_FROM_DATA_RIKSDAGEN_SE, id);
			throw new DataFailureException(e);
		}
	}

}
