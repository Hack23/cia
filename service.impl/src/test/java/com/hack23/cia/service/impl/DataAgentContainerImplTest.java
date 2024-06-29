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
package com.hack23.cia.service.impl;

import org.junit.Test;
import org.mockito.Mockito;

import com.hack23.cia.model.internal.application.data.impl.DataAgentOperation;
import com.hack23.cia.model.internal.application.data.impl.DataAgentTarget;
import com.hack23.cia.model.internal.application.data.impl.DataAgentWorkOrder;
import com.hack23.cia.service.component.agent.api.DataAgentApi;
import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * The Class DataAgentContainerImplTest.
 */
public class DataAgentContainerImplTest extends AbstractUnitTest {


	/**
	 * Execute test.
	 */
	@Test
	public void executeTest() {
		final DataAgentApi dataAgentApi = Mockito.mock(DataAgentApi.class);
		final DataAgentContainerImpl dataAgentContainerImpl = new DataAgentContainerImpl(dataAgentApi);
		final DataAgentWorkOrder workOrder = new DataAgentWorkOrder().withTarget(DataAgentTarget.MODEL_EXTERNAL_RIKSDAGEN).withOperation(DataAgentOperation.IMPORT);
		dataAgentContainerImpl.execute(workOrder);
		Mockito.verify(dataAgentApi).execute(workOrder);
	}
}
