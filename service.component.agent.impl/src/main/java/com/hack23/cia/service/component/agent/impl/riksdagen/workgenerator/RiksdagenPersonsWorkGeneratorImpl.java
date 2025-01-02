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
package com.hack23.cia.service.component.agent.impl.riksdagen.workgenerator;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.riksdagen.personlista.impl.PersonElement;
import com.hack23.cia.model.internal.application.data.impl.RiksdagenDataSources;
import com.hack23.cia.service.external.riksdagen.api.DataFailureException;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenPersonApi;

/**
 * The Class RiksdagenPersonsWorkGeneratorImpl.
 */
@Service("RiksdagenPersonsWorkGeneratorImpl")
final class RiksdagenPersonsWorkGeneratorImpl extends AbstractRiksdagenDataSourcesWorkGenerator {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RiksdagenPersonsWorkGeneratorImpl.class);

	/** The person element workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.riksdagen.personlista.impl.PersonElement")
	private Destination personElementWorkdestination;

	/** The riksdagen api. */
	@Autowired
	private RiksdagenPersonApi riksdagenApi;

	/**
	 * Instantiates a new riksdagen persons work generator impl.
	 */
	public RiksdagenPersonsWorkGeneratorImpl() {
		super(RiksdagenDataSources.PERSONS);
	}

	@Override
	public void generateWorkOrders() {
		try {
			final List<PersonElement> personList = riksdagenApi.getPersonList().getPerson();

			for (final PersonElement personElement : personList) {
				LOGGER.info("Send Load Order:{}", personElement.getPersonUrlXml());
				getJmsSender().send(personElementWorkdestination, personElement);
			}
			for (final String personId : readMissingPersonList()) {
				LOGGER.info("Send Load Order:{}{}", "https://data.riksdagen.se/person/", personId);
				getJmsSender().send(personElementWorkdestination, new PersonElement().withId(personId));
			}
		} catch (final DataFailureException exception) {
			LOGGER.warn("Problem during generate work orders", exception);
		}
	}

	/**
	 * Read missing person list.
	 *
	 * @return the string[]
	 */
	private static String[] readMissingPersonList() {

		final Scanner sc = new Scanner(RiksdagenPersonsWorkGeneratorImpl.class.getResourceAsStream("/personlist.txt"),StandardCharsets.UTF_8.name());
		final List<String> lines = new ArrayList<>();
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		sc.close();
		return lines.toArray(new String[0]);
	}

}
