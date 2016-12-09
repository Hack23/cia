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
package com.hack23.cia.service.impl.email;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import com.hack23.cia.model.internal.application.system.impl.ApplicationConfiguration;
import com.hack23.cia.model.internal.application.system.impl.ConfigurationGroup;
import com.hack23.cia.service.api.action.admin.UpdateApplicationConfigurationRequest;
import com.hack23.cia.service.api.action.admin.UpdateApplicationConfigurationResponse;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.ApplicationConfigurationService;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class EmailServiceITest.
 */
public final class EmailServiceITest extends AbstractServiceFunctionalIntegrationTest {

	/** The application configuration service. */
	@Autowired
	private ApplicationConfigurationService applicationConfigurationService;

	/** The email service. */
	@Autowired
	private EmailService emailService;

	private SimpleSmtpServer dumbster;
	
	private CreateApplicationSessionRequest createTestApplicationSession;
	
	@Before
	public void configureMailServer() throws IOException {
		dumbster = SimpleSmtpServer.start(SimpleSmtpServer.AUTO_SMTP_PORT);
		
		setAuthenticatedAdminuser();

		createTestApplicationSession = createTestApplicationSession();

		final ApplicationConfiguration sendEmail = applicationConfigurationService.checkValueOrLoadDefault(
				"Email configuration send emails", "Send email", ConfigurationGroup.EXTERNAL_SERVICES,
				EmailServiceImpl.class.getSimpleName(), "Send email", "Responsible for sending email",
				"application.email.send.email", "false");

		final ApplicationConfiguration smtpPort = applicationConfigurationService.checkValueOrLoadDefault(
				"Email configuration smtp port", "Smtp port", ConfigurationGroup.EXTERNAL_SERVICES,
				EmailServiceImpl.class.getSimpleName(), "Smtp port", "Responsible for sending email",
				"application.email.smtp.port", "587");

		updateApplicationConfiguration(createTestApplicationSession, sendEmail, "true");
		updateApplicationConfiguration(createTestApplicationSession, smtpPort,
				Integer.toString(dumbster.getPort()));

		
	}
		
	@After
	public void restoreMailConfiguration() {
		final ApplicationConfiguration sendEmail = applicationConfigurationService.checkValueOrLoadDefault(
				"Email configuration send emails", "Send email", ConfigurationGroup.EXTERNAL_SERVICES,
				EmailServiceImpl.class.getSimpleName(), "Send email", "Responsible for sending email",
				"application.email.send.email", "false");

		final ApplicationConfiguration smtpPort = applicationConfigurationService.checkValueOrLoadDefault(
				"Email configuration smtp port", "Smtp port", ConfigurationGroup.EXTERNAL_SERVICES,
				EmailServiceImpl.class.getSimpleName(), "Smtp port", "Responsible for sending email",
				"application.email.smtp.port", "587");

		updateApplicationConfiguration(createTestApplicationSession, sendEmail, "false");
		updateApplicationConfiguration(createTestApplicationSession, smtpPort,"587");
		
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

			List<SmtpMessage> emails = dumbster.getReceivedEmails();
			assertEquals(emails.size(), 1);
			SmtpMessage email = emails.get(0);
			assertEquals(email.getHeaderValue("Subject"), "subject");
			assertEquals(email.getBody(), "content");
			assertEquals(email.getHeaderValue("To"), "test@email.com");
	}

	/**
	 * Update application configuration.
	 *
	 * @param createTestApplicationSession
	 *            the create test application session
	 * @param applicationConfiguration
	 *            the application configuration
	 * @param propertyValue
	 *            the property value
	 */
	private void updateApplicationConfiguration(final CreateApplicationSessionRequest createTestApplicationSession,
			final ApplicationConfiguration applicationConfiguration, final String propertyValue) {

		final UpdateApplicationConfigurationRequest serviceRequest = new UpdateApplicationConfigurationRequest();
		serviceRequest.setApplicationConfigurationId(applicationConfiguration.getHjid());
		serviceRequest.setSessionId(createTestApplicationSession.getSessionId());

		serviceRequest.setComponentDescription(applicationConfiguration.getComponentDescription());
		serviceRequest.setConfigDescription(applicationConfiguration.getConfigDescription());
		serviceRequest.setConfigTitle(applicationConfiguration.getConfigTitle());
		serviceRequest.setComponentTitle(applicationConfiguration.getComponentTitle());
		serviceRequest.setPropertyValue(propertyValue);

		final UpdateApplicationConfigurationResponse response = (UpdateApplicationConfigurationResponse) applicationManager
				.service(serviceRequest);
		assertEquals(EXPECT_SUCCESS, ServiceResult.SUCCESS, response.getResult());
	}

}
