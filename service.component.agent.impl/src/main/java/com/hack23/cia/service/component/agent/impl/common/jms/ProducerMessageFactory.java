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
package com.hack23.cia.service.component.agent.impl.common.jms;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

/**
 * A factory for creating ProducerMessage objects.
 */
final class ProducerMessageFactory implements MessageCreator {

	/** The message. */
	private final Serializable message;

	/**
	 * Instantiates a new producer message factory.
	 *
	 * @param message
	 *            the message
	 */
	public ProducerMessageFactory(final Serializable message) {
		this.message = message;
	}

	@Override
	public Message createMessage(final Session session) throws JMSException {
		return session.createObjectMessage(message);
	}
}