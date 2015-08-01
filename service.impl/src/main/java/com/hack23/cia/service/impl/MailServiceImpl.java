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
 *	$Id: MailServiceImpl.java 6046 2015-05-06 20:42:53Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/service.impl/src/main/java/com/hack23/cia/service/impl/MailServiceImpl.java $
 */
package com.hack23.cia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.hack23.cia.service.api.MailService;

/**
 * The Class MailServiceImpl.
 */
@Component(value = "MailService")
public final class MailServiceImpl implements MailService {

	/** The mail sender. */
	@Autowired
	private MailSender mailSender;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.api.MailService#sendMail(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendMail(final String from, final String to,
			final String subject, final String msg) {
		final SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
	}

}
