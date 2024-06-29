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
package com.hack23.cia.service.component.agent.impl.riksdagen;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.hack23.cia.model.internal.application.data.impl.RiksdagenDataSources;
import com.hack23.cia.service.component.agent.impl.AbstractServiceComponentAgentFunctionalIntegrationTest;

/**
 * The Class RiksdagenApiAgentWorkConsumerImplITest.
 */
public class RiksdagenApiAgentWorkConsumerImplITest extends AbstractServiceComponentAgentFunctionalIntegrationTest {

	/** The messsage listener. */
	@Autowired
	@Qualifier("RiksdagenApiAgentWorkConsumer")
	private MessageListener messsageListener;

	/**
	 * On message success test.
	 *
	 * @throws JMSException
	 *             the JMS exception
	 */
	@Test
	@Transactional
	public void onMessageSuccessTest() throws JMSException {
		final ObjectMessage message = mock(ObjectMessage.class);

		when(message.getObject()).thenReturn(RiksdagenDataSources.PERSONS);

		messsageListener.onMessage(message);

		verify(message, atLeastOnce()).getObject();
	}

	/**
	 * On message failure test.
	 *
	 * @throws JMSException
	 *             the JMS exception
	 */
	@Test
	public void onMessageFailureTest() throws JMSException {
		final ObjectMessage message = mock(ObjectMessage.class);

		when(message.getObject()).thenThrow(new JMSException("test"));

		messsageListener.onMessage(message);
		verify(message, atLeastOnce()).getObject();
	}

}
