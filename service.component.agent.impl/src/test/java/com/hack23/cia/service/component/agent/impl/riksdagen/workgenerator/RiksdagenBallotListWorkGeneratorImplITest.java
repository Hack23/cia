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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.transaction.Transactional;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.util.ReflectionTestUtils;

import com.hack23.cia.model.external.riksdagen.voteringlista.impl.BallotDocumentElement;
import com.hack23.cia.service.component.agent.impl.AbstractServiceComponentAgentFunctionalIntegrationTest;
import com.hack23.cia.service.component.agent.impl.common.jms.JmsSender;
import com.hack23.cia.service.data.api.VoteDataDAO;
import com.hack23.cia.service.external.riksdagen.api.DataFailureException;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenBallotApi;

/**
 * The Class RiksdagenBallotListWorkGeneratorImplITest.
 */
@Transactional
public class RiksdagenBallotListWorkGeneratorImplITest extends AbstractServiceComponentAgentFunctionalIntegrationTest {

	/** The riksdagen data sources work generator. */
	@Autowired
	@Qualifier("RiksdagenBallotListWorkGeneratorImpl")
	private RiksdagenDataSourcesWorkGenerator riksdagenDataSourcesWorkGenerator;

	@Autowired
	private VoteDataDAO voteDataDAO;

	/**
	 * Generate work orders when ballot not exist success test.
	 *
	 * @throws JMSException
	 *             the JMS exception
	 * @throws DataFailureException
	 *             the data failure exception
	 */
	@Test
	public void generateWorkOrdersWhenBallotNotExistSuccessTest() throws JMSException, DataFailureException {
		final JmsSender jmsSenderMock = mock(JmsSender.class);
		final RiksdagenBallotApi riksdagenApi = mock(RiksdagenBallotApi.class);
		ReflectionTestUtils.setField(riksdagenDataSourcesWorkGenerator, "jmsSender", jmsSenderMock);
		ReflectionTestUtils.setField(riksdagenDataSourcesWorkGenerator, "riksdagenApi", riksdagenApi);

		final ArrayList<BallotDocumentElement> value = new ArrayList<>();
		final String ballotId = UUID.randomUUID().toString();
		value.add(new BallotDocumentElement().withBallotId(ballotId));
		when(riksdagenApi.getBallotList()).thenReturn(value);

		final ArgumentCaptor<Destination> destCaptor = ArgumentCaptor.forClass(Destination.class);
		jmsSenderMock.send(destCaptor.capture(),eq(ballotId));

        riksdagenDataSourcesWorkGenerator.generateWorkOrders();

		final List<Destination> capturedDestinations = destCaptor.getAllValues();

		assertNotNull(capturedDestinations);
		assertTrue(capturedDestinations.isEmpty());

	}

	/**
	 * Generate work orders when ballot already exist success test.
	 *
	 * @throws JMSException
	 *             the JMS exception
	 * @throws DataFailureException
	 *             the data failure exception
	 */
	@Test
	public void generateWorkOrdersWhenBallotAlreadyExistSuccessTest() throws JMSException, DataFailureException {
		final JmsSender jmsSenderMock = mock(JmsSender.class);
		final RiksdagenBallotApi riksdagenApi = mock(RiksdagenBallotApi.class);
		ReflectionTestUtils.setField(riksdagenDataSourcesWorkGenerator, "jmsSender", jmsSenderMock);
		ReflectionTestUtils.setField(riksdagenDataSourcesWorkGenerator, "riksdagenApi", riksdagenApi);

		final ArrayList<BallotDocumentElement> value = new ArrayList<>();
		final String ballotId = voteDataDAO.getBallotIdList().iterator().next().getBallotId();
		value.add(new BallotDocumentElement().withBallotId(ballotId));
		when(riksdagenApi.getBallotList()).thenReturn(value);

		verify(jmsSenderMock, never()).send(any(Destination.class),eq(ballotId));

        riksdagenDataSourcesWorkGenerator.generateWorkOrders();
	}

	/**
	 * Generate work orders data failure exception test.
	 *
	 * @throws JMSException
	 *             the JMS exception
	 * @throws DataFailureException
	 *             the data failure exception
	 */
	@Test
	public void generateWorkOrdersDataFailureExceptionTest() throws JMSException, DataFailureException {
		final JmsSender jmsSenderMock = mock(JmsSender.class);
		final RiksdagenBallotApi riksdagenApi = mock(RiksdagenBallotApi.class);
		ReflectionTestUtils.setField(riksdagenDataSourcesWorkGenerator, "jmsSender", jmsSenderMock);
		ReflectionTestUtils.setField(riksdagenDataSourcesWorkGenerator, "riksdagenApi", riksdagenApi);

		final ArrayList<BallotDocumentElement> value = new ArrayList<>();
		final String ballotId = voteDataDAO.getBallotIdList().iterator().next().getBallotId();
		value.add(new BallotDocumentElement().withBallotId(ballotId));
		when(riksdagenApi.getBallotList()).thenThrow(new DataFailureException(new RuntimeException()));

		verify(jmsSenderMock, never()).send(any(Destination.class),eq(ballotId));

        riksdagenDataSourcesWorkGenerator.generateWorkOrders();
	}

}
