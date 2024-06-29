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
package com.hack23.cia.service.impl.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hack23.cia.model.internal.application.data.impl.DataAgentOperation;
import com.hack23.cia.model.internal.application.data.impl.DataAgentTarget;
import com.hack23.cia.model.internal.application.data.impl.DataAgentWorkOrder;

/**
 * The Class AgentJob.
 */
public final class AgentJob extends AbstractJob {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected void executeInternal(final JobExecutionContext jobContext) throws JobExecutionException {
		  getJobContextHolder(jobContext).getDataAgentApi().execute(new DataAgentWorkOrder().withOperation(DataAgentOperation.IMPORT).withTarget(DataAgentTarget.MODEL_EXTERNAL_RIKSDAGEN));
		  getJobContextHolder(jobContext).getDataAgentApi().execute(new DataAgentWorkOrder().withOperation(DataAgentOperation.IMPORT).withTarget(DataAgentTarget.MODEL_EXTERNAL_WORLDBANK));
	}

}
