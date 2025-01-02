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
package com.hack23.cia.service.impl.task;

import static org.mockito.Mockito.times;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.quartz.JobExecutionContext;
import org.springframework.context.ApplicationContext;

import com.hack23.cia.model.internal.application.data.impl.DataAgentWorkOrder;
import com.hack23.cia.service.component.agent.api.DataAgentApi;

/**
 * The Class AgentJobTest.
 */
public class AgentJobTest extends AbstractJobTest {

	/**
	 * Execute internal test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void executeInternalTest() throws Exception {
		final JobExecutionContext jobContextMock = Mockito.mock(JobExecutionContext.class);
		final ApplicationContext applicationContext = prepareContextMock(jobContextMock);

		final DataAgentApi dataAgentApi = Mockito.mock(DataAgentApi.class);
		final JobContextHolder jobContextHolder = new JobContextHolderImpl(dataAgentApi, null, null,null);
		Mockito.when(applicationContext.getBean(JobContextHolder.class)).thenReturn(jobContextHolder);

		new AgentJob().executeInternal(jobContextMock);
		Mockito.verify(dataAgentApi,times(2)).execute(ArgumentMatchers.any(DataAgentWorkOrder.class));
	}

}
