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
package com.hack23.cia.web.impl.ui.application.views.pageclicklistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hack23.cia.model.internal.application.data.impl.DataAgentOperation;
import com.hack23.cia.model.internal.application.data.impl.DataAgentTarget;
import com.hack23.cia.model.internal.application.data.impl.DataAgentWorkOrder;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * The Class StartAgentClickListener.
 *
 * @see com.vaadin.ui.Button.ClickEvent
 */
public final class StartAgentClickListener extends AbstractClickListener implements ClickListener {

	/** The Constant LOG_MSG_EXECUTE_WORKORDER. */
	private static final String LOG_MSG_EXECUTE_WORKORDER = "Execute workorder:{}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(StartAgentClickListener.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The data agent operation. */
	private final DataAgentOperation dataAgentOperation;

	/** The data agent target. */
	private final DataAgentTarget dataAgentTarget;

	/**
	 * Instantiates a new start agent click listener.
	 *
	 * @param dataAgentTarget    the data agent target
	 * @param dataAgentOperation the data agent operation
	 */
	public StartAgentClickListener(final DataAgentTarget dataAgentTarget, final DataAgentOperation dataAgentOperation) {
		super();
		this.dataAgentTarget = dataAgentTarget;
		this.dataAgentOperation = dataAgentOperation;
	}

	@Override
	public void buttonClick(final ClickEvent event) {
		final DataAgentWorkOrder dataAgentWorkOrder = new DataAgentWorkOrder();
		dataAgentWorkOrder.setTarget(dataAgentTarget);
		dataAgentWorkOrder.setOperation(dataAgentOperation);
		LOGGER.info(LOG_MSG_EXECUTE_WORKORDER, dataAgentWorkOrder);
		getApplicationManager().getAgentContainer().execute(dataAgentWorkOrder);
	}

}
