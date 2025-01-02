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
package com.hack23.cia.service.external.worldbank.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.worldbank.topic.impl.TopicElement;
import com.hack23.cia.model.external.worldbank.topic.impl.TopicsElement;
import com.hack23.cia.service.external.common.api.XmlAgent;
import com.hack23.cia.service.external.common.api.XmlAgentException;
import com.hack23.cia.service.external.worldbank.api.DataFailureException;
import com.hack23.cia.service.external.worldbank.api.WorldBankTopicApi;

/**
 * The Class WorldbankTopicApiImpl.
 */
@Component
final class WorldbankTopicApiImpl extends BaseWorldBankApiImpl implements WorldBankTopicApi {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(WorldbankTopicApiImpl.class);

	/** The Constant PROBLEM_GETTING_WORLDBANK_TOPIC_LIST. */
	private static final String PROBLEM_GETTING_WORLDBANK_TOPIC_LIST = "Problem getting worldbank topic list";

	/** The Constant TOPICS. */
	private static final String TOPICS = "https://api.worldbank.org/v2/topics?per_page=3000";

	/**
	 * The Constant
	 * XMLNS_WB_HTTP_TOPIC_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String XMLNS_WB_HTTP_TOPIC_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "xmlns:wb=\"http://topic.worldbank.external.model.cia.hack23.com/impl\"";

	/** The topics unmarshaller. */
	@Autowired
	@Qualifier("worldbankOrgTopicsMarshaller")
	private Unmarshaller topicsUnmarshaller;

	/**
	 * Instantiates a new worldbank topic api impl.
	 */
	@Autowired
	public WorldbankTopicApiImpl(final XmlAgent xmlAgent) {
		super(xmlAgent);
	}

	@Override
	public List<TopicElement> getTopics() throws DataFailureException {
		try {
			return ((TopicsElement) getXmlAgent().unmarshallXml(topicsUnmarshaller, TOPICS, null,
					XMLNS_WB_HTTP_WWW_WORLDBANK_ORG, XMLNS_WB_HTTP_TOPIC_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL))
							.getTopic();
		} catch (final XmlAgentException e) {
			LOGGER.warn(PROBLEM_GETTING_WORLDBANK_TOPIC_LIST);
			throw new DataFailureException(e);
		}
	}

}
