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
package com.hack23.cia.service.component.agent.impl.riksdagen.workers;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.junit.Test;

import com.hack23.cia.service.component.agent.impl.riksdagen.workers.data.RiksdagenUpdateService;
import com.hack23.cia.service.external.riksdagen.api.DataFailureException;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenBallotApi;
import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * The Class RiksdagenVoteDataWorkConsumerImplTest.
 */
public class RiksdagenVoteDataWorkConsumerImplTest extends AbstractUnitTest {

	/**
	 * On message data failure exception test.
	 *
	 * @throws JMSException         the JMS exception
	 * @throws DataFailureException the data failure exception
	 */
	@Test
	public void onMessageDataFailureExceptionTest() throws JMSException, DataFailureException {
		final ObjectMessage message = mock(ObjectMessage.class);

		final String ballotId = "000561B4-3637-41D2-8A8C-5CE5DAED9F57";
		when(message.getObject()).thenReturn(ballotId);

		final RiksdagenBallotApi riksdagenBallotApi = mock(RiksdagenBallotApi.class);

		when(riksdagenBallotApi.getBallot(ballotId)).thenThrow(new DataFailureException(new RuntimeException()));

		final RiksdagenUpdateService riksdagenUpdateService = mock(RiksdagenUpdateService.class);
		new RiksdagenVoteDataWorkConsumerImpl(riksdagenUpdateService,riksdagenBallotApi).onMessage(message);

		verify(message, atLeastOnce()).getObject();
		verify(riksdagenUpdateService, never()).updateVoteDataData(new ArrayList<>());
	}


}
