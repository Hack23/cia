/*
 * Copyright 2010 James Pether Sörling
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
package com.hack23.cia.service.component.agent.impl.vdem;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.impl.VdemDataSources;
import com.hack23.cia.service.component.agent.impl.common.AbstractAgentWorkConsumerImpl;
import com.hack23.cia.service.external.vdem.api.VdemService;

/**
 * The Class VdemApiAgentWorkConsumerImpl.
 */
@Service("VdemApiAgentWorkConsumer")
public final class VdemApiAgentWorkConsumerImpl extends AbstractAgentWorkConsumerImpl {


	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(VdemApiAgentWorkConsumerImpl.class);

	/** The vdem service. */
	@Autowired
	private VdemService vdemService;

	/**
	 * Instantiates a new vdem api agent work consumer impl.
	 */
	public VdemApiAgentWorkConsumerImpl() {
		super();
	}

	@Override
	public void onMessage(final Message message) {
		final ObjectMessage msg = (ObjectMessage) message;

		try {
			final VdemDataSources source =  (VdemDataSources) msg.getObject();
			LOGGER.info("Consumed message:{}", source);

			switch (source) {
			case QUESTIONS:
				vdemService.getQuestions();
				break;
			case QUESTION_COUNTRY_YEAR_DATA:
				vdemService.getCountryQuestionData();
				break;
			default:
				break;
			}

		} catch (final JMSException e1) {
			LOGGER.warn("jms", e1);
		}

	}


}