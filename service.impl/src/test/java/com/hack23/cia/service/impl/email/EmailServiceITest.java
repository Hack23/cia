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
package com.hack23.cia.service.impl.email;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class EmailServiceITest.
 */
public final class EmailServiceITest extends AbstractServiceFunctionalIntegrationTest {

	/** The email service. */
	@Autowired
	private EmailService emailService;

	private CreateApplicationSessionRequest createTestApplicationSession;

	private SimpleSmtpServer dumbster;

	@Before
	public void callConfigureMailServer() throws IOException {
		setAuthenticatedAdminuser();
		createTestApplicationSession = createTestApplicationSession();
		dumbster = configureMailServer(createTestApplicationSession);
	}

	@After
	public void callRestoreMailConfiguration() {
		restoreMailConfiguration(createTestApplicationSession,dumbster);
	}

	/**
	 * Send email success test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sendEmailSuccessTest() throws Exception {
		emailService.sendEmail("test@email.com", "subject", "content");

		final List<SmtpMessage> emails = dumbster.getReceivedEmails();
		assertEquals(1, emails.size());
		final SmtpMessage email = emails.get(0);
		assertEquals("subject", email.getHeaderValue("Subject"));
		assertEquals("content", email.getBody());
		assertEquals("test@email.com", email.getHeaderValue("To"));
	}

}
