/*
 * Copyright 2014 James Pether Sörling
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
package com.hack23.cia.web.impl.ui.application.views.pageclicklistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.data.impl.DataAgentOperation;
import com.hack23.cia.model.internal.application.data.impl.DataAgentTarget;
import com.hack23.cia.model.internal.application.data.impl.DataAgentWorkOrder;
import com.hack23.cia.service.api.AgentContainer;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;

/**
 * The Class StartAgentClickListener.
 */
public final class StartAgentClickListener implements ClickListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOG_MSG_EXECUTE_WORKORDER. */
	private static final String LOG_MSG_EXECUTE_WORKORDER = "Execute workorder:{}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(StartAgentClickListener.class);

	/** The target select. */
	private final ComboBox targetSelect;

	/** The operation select. */
	private final ComboBox operationSelect;

	/** The agent container. */
	@Autowired
	private final transient AgentContainer agentContainer;

	/**
	 * Instantiates a new start agent click listener.
	 *
	 * @param targetSelect
	 *            the target select
	 * @param operationSelect
	 *            the operation select
	 * @param agentContainer
	 *            the agent container
	 */
	public StartAgentClickListener(final ComboBox targetSelect, final ComboBox operationSelect, final AgentContainer agentContainer) {
		super();
		this.targetSelect = targetSelect;
		this.operationSelect = operationSelect;
		this.agentContainer = agentContainer;
	}

	@Override
	public void buttonClick(final ClickEvent event) {
		if (targetSelect.getValue() != null && operationSelect.getValue() != null) {
			final DataAgentWorkOrder dataAgentWorkOrder = new DataAgentWorkOrder();
			final DataAgentTarget target = DataAgentTarget.valueOf(targetSelect.getValue().toString());
			dataAgentWorkOrder.setTarget(target);
			dataAgentWorkOrder.setOperation(DataAgentOperation.valueOf(operationSelect.getValue().toString()));
			LOGGER.info(LOG_MSG_EXECUTE_WORKORDER, dataAgentWorkOrder);
			agentContainer.execute(dataAgentWorkOrder);
		}
	}


}
