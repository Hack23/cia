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

import java.util.List;
import java.util.Map;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData;
import com.hack23.cia.model.internal.application.data.impl.RiksdagenDataSources;

/**
 * The Class RiksdagenCommitteeProposalWorkGeneratorImpl.
 */
@Service("RiksdagenCommitteeProposalWorkGeneratorImpl")
final class RiksdagenCommitteeProposalWorkGeneratorImpl extends AbstractRiksdagenDataSourcesWorkGenerator {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RiksdagenCommitteeProposalWorkGeneratorImpl.class);

	/** The committee proposal component data workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData")
	private Destination committeeProposalComponentDataWorkdestination;

	/**
	 * Instantiates a new riksdagen committee proposal work generator impl.
	 */
	public RiksdagenCommitteeProposalWorkGeneratorImpl() {
		super(RiksdagenDataSources.COMMITTEE_PROPOSALS);
	}

	@Override
	public void generateWorkOrders() {
		final List<String> avaibleCommitteeProposal = getImportService().getAvaibleCommitteeProposal();
		final Map<String, String> committeeProposalComponentDataMap = getImportService()
				.getCommitteeProposalComponentDataMap();

		LOGGER.info("getAvaibleCommitteeProposal()={} :getCommitteeProposalComponentDataMap()={}",
				avaibleCommitteeProposal.size(), committeeProposalComponentDataMap.size());

		for (final String id : avaibleCommitteeProposal) {
			if (!committeeProposalComponentDataMap.containsKey(id)) {
				getJmsSender().send(committeeProposalComponentDataWorkdestination, id);

				LOGGER.info("load https://data.riksdagen.se/utskottsforslag/{}", id);
			}
		}

		for (final CommitteeProposalComponentData container : getImportService().getNoneCompletedCommitteeProposal()) {
				 getJmsSender().send(committeeProposalComponentDataWorkdestination,container.getDocument().getId());

		}

	}

}
