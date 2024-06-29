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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.Serializable;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.test.util.ReflectionTestUtils;

import com.hack23.cia.model.internal.application.data.impl.DataAgentOperation;
import com.hack23.cia.model.internal.application.data.impl.DataAgentTarget;
import com.hack23.cia.model.internal.application.data.impl.DataAgentWorkOrder;
import com.hack23.cia.service.component.agent.impl.common.jms.JmsSender;
import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * The Class DataAgentApiTest.
 */
public final class DataAgentApiTest extends AbstractUnitTest {


	/**
	 * Import riksdagen data success test.
	 *
	 * @throws JMSException
	 *             the JMS exception
	 */
	@Test
	public void importRiksdagenDataSuccessTest() throws JMSException {
		final DataAgentApiImpl dataAgentApiImpl = new DataAgentApiImpl();
		final JmsSender jmsSenderMock = mock(JmsSender.class);
        ReflectionTestUtils.setField(dataAgentApiImpl, "jmsSender", jmsSenderMock);

		dataAgentApiImpl.execute(new DataAgentWorkOrder().withOperation(DataAgentOperation.IMPORT).withTarget(DataAgentTarget.MODEL_EXTERNAL_RIKSDAGEN));

		final ArgumentCaptor<Destination> destCaptor = ArgumentCaptor.forClass(Destination.class);

		final ArgumentCaptor<Serializable> stringCaptor = ArgumentCaptor.forClass(Serializable.class);

		verify(jmsSenderMock, times(7)).send(destCaptor.capture(),stringCaptor.capture());

		final List<Serializable> capturedStrings = stringCaptor.getAllValues();
		final List<Destination> capturedDestinations = destCaptor.getAllValues();

		assertNotNull(capturedStrings);
		assertNotNull(capturedDestinations);
	}

	/**
	 * Import worldbank data success test.
	 *
	 * @throws JMSException
	 *             the JMS exception
	 */
	@Test
	public void importWorldbankDataSuccessTest() throws JMSException {
		final DataAgentApiImpl dataAgentApiImpl = new DataAgentApiImpl();
		final JmsSender jmsSenderMock = mock(JmsSender.class);
        ReflectionTestUtils.setField(dataAgentApiImpl, "jmsSender", jmsSenderMock);

		dataAgentApiImpl.execute(new DataAgentWorkOrder().withOperation(DataAgentOperation.IMPORT).withTarget(DataAgentTarget.MODEL_EXTERNAL_WORLDBANK));
		final ArgumentCaptor<Destination> destCaptor = ArgumentCaptor.forClass(Destination.class);

		final ArgumentCaptor<Serializable> stringCaptor = ArgumentCaptor.forClass(Serializable.class);

		verify(jmsSenderMock, times(4)).send(destCaptor.capture(),stringCaptor.capture());

		final List<Serializable> capturedStrings = stringCaptor.getAllValues();
		final List<Destination> capturedDestinations = destCaptor.getAllValues();

		assertNotNull(capturedStrings);
		assertNotNull(capturedDestinations);
	}

}
