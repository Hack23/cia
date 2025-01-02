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
package com.hack23.cia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.impl.DataAgentWorkOrder;
import com.hack23.cia.service.api.AgentContainer;
import com.hack23.cia.service.component.agent.api.DataAgentApi;

/**
 * The Class DataAgentContainerImpl.
 */
@Component("DataAgentContainer")
final class DataAgentContainerImpl implements AgentContainer {

	/** The data agent api. */
	private final DataAgentApi dataAgentApi;

	/**
	 * Instantiates a new data agent container impl.
	 *
	 * @param dataAgentApi
	 *            the data agent api
	 */
	@Autowired
	public DataAgentContainerImpl(final DataAgentApi dataAgentApi) {
		super();
		this.dataAgentApi = dataAgentApi;
	}

	@Secured({"ROLE_ADMIN" })
	@Override
	public void execute(final DataAgentWorkOrder workOrder) {
		dataAgentApi.execute(workOrder);
	}
}
