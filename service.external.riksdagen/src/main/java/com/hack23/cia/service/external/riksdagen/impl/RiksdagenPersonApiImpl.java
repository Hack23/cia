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

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.external.riksdagen.personlista.impl.PersonContainerElement;
import com.hack23.cia.service.external.common.api.XmlAgent;
import com.hack23.cia.service.external.common.api.XmlAgentException;
import com.hack23.cia.service.external.riksdagen.api.DataFailureException;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenPersonApi;

/**
 * The Class RiksdagenPersonApiImpl.
 */
@Component
final class RiksdagenPersonApiImpl implements RiksdagenPersonApi {

	/** The Constant HTTP_PERSON_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL. */
	private static final String HTTP_PERSON_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "http://person.riksdagen.external.model.cia.hack23.com/impl";

	/**
	 * The Constant HTTP_PERSONLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String HTTP_PERSONLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "http://personlista.riksdagen.external.model.cia.hack23.com/impl";

	/** The Constant ID_KEY. */
	private static final String ID_KEY = "${ID_KEY}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RiksdagenPersonApiImpl.class);

	/** The Constant PERSON. */
	private static final String PERSON = "https://data.riksdagen.se/person/${ID_KEY}";

	/** The Constant PERSON_LIST. */
	private static final String PERSON_LIST = "https://data.riksdagen.se/personlista/?iid=&fnamn=&enamn=&f_ar=&kn=&parti=&valkrets=&rdlstatus=samtliga&org=";

	/** The Constant PROBLEM_GETTING_PERSON_DATA_ID_S_FROM_DATA_RIKSDAGEN_SE. */
	private static final String PROBLEM_GETTING_PERSON_DATA_ID_S_FROM_DATA_RIKSDAGEN_SE = "Problem getting person data id:{}  from data.riksdagen.se";

	/** The Constant PROBLEM_GETTING_PERSON_LIST_FROM_DATA_RIKSDAGEN_SE. */
	private static final String PROBLEM_GETTING_PERSON_LIST_FROM_DATA_RIKSDAGEN_SE = "Problem getting person list from data.riksdagen.se";

	/** The person list unmarshaller. */
	@Autowired
	@Qualifier("riksdagenPersonListMarshaller")
	private Unmarshaller personListUnmarshaller;

	/** The person unmarshaller. */
	@Autowired
	@Qualifier("riksdagenPersonMarshaller")
	private Unmarshaller personUnmarshaller;

	/** The xml agent. */
	private final XmlAgent xmlAgent;

	/**
	 * Instantiates a new riksdagen person api impl.
	 *
	 * @param xmlAgent
	 *            the xml agent
	 */
	@Autowired
	public RiksdagenPersonApiImpl(final XmlAgent xmlAgent) {
		super();
		this.xmlAgent = xmlAgent;
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.riksdagen.api.RiksdagenPersonApi#getPerson(java.lang.String)
	 */
	@Override
	public PersonData getPerson(final String id) throws DataFailureException {
		try {
			final String url = PERSON.replace(ID_KEY, id);
			return ((JAXBElement<com.hack23.cia.model.external.riksdagen.person.impl.PersonContainerData>) xmlAgent
					.unmarshallXml(personUnmarshaller, url, HTTP_PERSON_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL,
							null, null)).getValue().getPerson();
		} catch (final XmlAgentException e) {
			LOGGER.warn(PROBLEM_GETTING_PERSON_DATA_ID_S_FROM_DATA_RIKSDAGEN_SE, id);
			throw new DataFailureException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.riksdagen.api.RiksdagenPersonApi#getPersonList()
	 */
	@Override
	public PersonContainerElement getPersonList() throws DataFailureException {
		try {
			return ((JAXBElement<PersonContainerElement>) xmlAgent.unmarshallXml(personListUnmarshaller, PERSON_LIST,
					HTTP_PERSONLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL, null, null)).getValue();
		} catch (final XmlAgentException e) {
			LOGGER.warn(PROBLEM_GETTING_PERSON_LIST_FROM_DATA_RIKSDAGEN_SE);
			throw new DataFailureException(e);
		}
	}

}
