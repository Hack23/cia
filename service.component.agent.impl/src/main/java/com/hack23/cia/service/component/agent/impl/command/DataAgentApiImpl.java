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
package com.hack23.cia.service.component.agent.impl.command;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.data.impl.DataAgentWorkOrder;
import com.hack23.cia.model.internal.application.data.impl.RiksdagenDataSources;
import com.hack23.cia.model.internal.application.data.impl.WorldBankDataSources;
import com.hack23.cia.service.component.agent.api.DataAgentApi;
import com.hack23.cia.service.component.agent.impl.common.jms.JmsSender;

/**
 * The Class DataAgentApiImpl.
 */
@Service("DataAgentApi")
@Transactional(propagation=Propagation.REQUIRED)
final class DataAgentApiImpl implements DataAgentApi {

	/** The jms sender. */
	@Autowired
	private JmsSender jmsSender;

	/** The riksdagen api destination. */
	@Autowired
	@Qualifier("riksdagenApiAgentWorkQueue")
	private Destination riksdagenApiDestination;

	/** The world bank api destination. */
	@Autowired
	@Qualifier("worldbankApiAgentWorkQueue")
	private Destination worldBankApiDestination;

	/**
	 * Instantiates a new data agent api impl.
	 */
	public DataAgentApiImpl() {
		super();
	}

	@Override
	public void execute(final DataAgentWorkOrder workOrder) {
		sendMessage(workOrder);
	}

	/**
	 * Send message.
	 *
	 * @param workOrder
	 *            the work order
	 */
	private void sendMessage(final DataAgentWorkOrder workOrder) {

		switch (workOrder.getTarget()) {
		case MODEL_EXTERNAL_RIKSDAGEN:
			for (final RiksdagenDataSources datasource :RiksdagenDataSources.values()) {
				jmsSender.send(riksdagenApiDestination, datasource);
			}
			break;
		case MODEL_EXTERNAL_WORLDBANK:
			for (final WorldBankDataSources datasource :WorldBankDataSources.values()) {
				jmsSender.send(worldBankApiDestination, datasource);
			}
			break;
		default:
			break;
		}
	}

}
