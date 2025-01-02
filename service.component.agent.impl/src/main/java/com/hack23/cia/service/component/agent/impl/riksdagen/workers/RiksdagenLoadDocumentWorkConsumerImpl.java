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

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.service.component.agent.impl.common.jms.AbstractMessageListener;
import com.hack23.cia.service.component.agent.impl.common.jms.JmsSender;
import com.hack23.cia.service.external.common.api.ProcessDataStrategy;
import com.hack23.cia.service.external.riksdagen.api.DataFailureException;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenDocumentApi;

/**
 * The Class RiksdagenLoadDocumentWorkConsumerImpl.
 */
@Service("riksdagenLoadDocumentWorkConsumerImpl")
final class RiksdagenLoadDocumentWorkConsumerImpl extends AbstractMessageListener implements
MessageListener {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RiksdagenLoadDocumentWorkConsumerImpl.class);


	/** The document element workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement")
	private Destination documentElementWorkdestination;

	/** The riksdagen api. */
	@Autowired
	private RiksdagenDocumentApi riksdagenApi;

	/** The jms sender. */
	@Autowired
	private JmsSender jmsSender;


	/**
	 * Instantiates a new riksdagen load document work consumer impl.
	 */
	public RiksdagenLoadDocumentWorkConsumerImpl() {
		super();
	}

	@Override
	public void onMessage(final Message message) {
		try {
			configureAuthentication();
			final LoadDocumentWork work = (LoadDocumentWork) ((ObjectMessage) message).getObject();
			riksdagenApi.processDocumentList(work.getFromDate(),work.getToDate(),new DocumentElementWorkProducer());

		} catch (final DataFailureException | JMSException e) {
			LOGGER.warn("Error loading riksdagen document" , e);
		} finally {
			clearAuthentication();
		}
	}


	/**
	 * The Class DocumentElementWorkProducer.
	 */
	private class DocumentElementWorkProducer implements ProcessDataStrategy<DocumentElement> {

		@Override
		public void process(final DocumentElement t) {
				jmsSender.send(documentElementWorkdestination, t);
		}
	}

}
