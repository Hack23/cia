/*
 * Copyright 2010 -2025 James Pether Sörling
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

import java.util.Collection;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationConfiguration;
import com.hack23.cia.model.internal.application.system.impl.ConfigurationGroup;
import com.hack23.cia.service.data.api.ApplicationConfigurationService;

/**
 * The Class EmailServiceImpl.
 */
@Component("MailService")
@Secured({"ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
public final class EmailServiceImpl implements EmailService {

	private static final String DEFAULT_SMTP_PORT = "587";

	/** The Constant MAIL_SMTP_STARTTLS_ENABLE. */
	private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";

	/** The Constant MAIL_SMTP_AUTH. */
	private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";

	/** The Constant APPLICATION_EMAIL_SMTP_STARTTLS_ENABLE. */
	private static final String APPLICATION_EMAIL_SMTP_STARTTLS_ENABLE = "application.email.smtp.starttls.enable";

	/** The Constant APPLICATION_EMAIL_SMTP_AUTH. */
	private static final String APPLICATION_EMAIL_SMTP_AUTH = "application.email.smtp.auth";

	/** The Constant APPLICATION_EMAIL_SMTP_PASSWORD. */
	private static final String APPLICATION_EMAIL_SMTP_SECRET = "application.email.smtp.password";

	/** The Constant APPLICATION_EMAIL_SMTP_USERNAME. */
	private static final String APPLICATION_EMAIL_SMTP_USERNAME = "application.email.smtp.username";

	/** The Constant APPLICATION_EMAIL_SMTP_PORT. */
	private static final String APPLICATION_EMAIL_SMTP_PORT = "application.email.smtp.port";

	/** The Constant APPLICATION_EMAIL_SMTP_HOST. */
	private static final String APPLICATION_EMAIL_SMTP_HOST = "application.email.smtp.host";

	/** The Constant APPLICATION_EMAIL_FROM_EMAIL. */
	private static final String APPLICATION_EMAIL_FROM_EMAIL = "application.email.from.email";

	/** The Constant APPLICATION_EMAIL_SEND_EMAIL. */
	private static final String APPLICATION_EMAIL_SEND_EMAIL = "application.email.send.email";

	/** The Constant RESPONSIBLE_FOR_SENDING_EMAIL. */
	private static final String RESPONSIBLE_FOR_SENDING_EMAIL = "Responsible for sending email";

	/** The Constant SMTP_STARTTLS_ENABLE. */
	private static final String SMTP_STARTTLS_ENABLE = "Smtp starttls enable";

	/** The Constant SMTP_AUTH. */
	private static final String SMTP_AUTH = "Smtp auth";

	/** The Constant SMTP_PASSWORD. */
	private static final String SMTP_SECRET = "Smtp password";

	/** The Constant SMTP_USERNAME. */
	private static final String SMTP_USERNAME = "Smtp username";

	/** The Constant SMTP_PORT. */
	private static final String SMTP_PORT = "Smtp port";

	/** The Constant SMTP_HOST. */
	private static final String SMTP_HOST = "Smtp Host";

	/** The Constant FROM_EMAIL. */
	private static final String FROM_EMAIL = "From email";

	/** The Constant SEND_EMAIL. */
	private static final String SEND_EMAIL = "Send email";

	/** The Constant EMAIL_CONFIGURATION_SMTP_STARTTLS_ENABLE. */
	private static final String EMAIL_CONFIGURATION_SMTP_STARTTLS_ENABLE = "Email configuration smtp starttls enable";

	/** The Constant EMAIL_CONFIGURATION_SMTP_AUTH. */
	private static final String EMAIL_CONFIGURATION_SMTP_AUTH = "Email configuration smtp auth";

	/** The Constant EMAIL_CONFIGURATION_SMTP_PASSWORD. */
	private static final String EMAIL_CONFIGURATION_SMTP_SECRET = "Email configuration smtp password";

	/** The Constant EMAIL_CONFIGURATION_SMTP_USERNAME. */
	private static final String EMAIL_CONFIGURATION_SMTP_USERNAME = "Email configuration smtp username";

	/** The Constant EMAIL_CONFIGURATION_SMTP_PORT. */
	private static final String EMAIL_CONFIGURATION_SMTP_PORT = "Email configuration smtp port";

	/** The Constant EMAIL_CONFIGURATION_SMTP_HOST. */
	private static final String EMAIL_CONFIGURATION_SMTP_HOST = "Email configuration smtp host";

	/** The Constant EMAIL_CONFIGURATION_FROM_EMAIL. */
	private static final String EMAIL_CONFIGURATION_FROM_EMAIL = "Email configuration from email";

	/** The Constant EMAIL_CONFIGURATION_SEND_EMAILS. */
	private static final String EMAIL_CONFIGURATION_SEND_EMAILS = "Email configuration send emails";

	/** The Constant EMAIL_SETTINGS. */
	private static final String EMAIL_SETTINGS = "Email settings:{}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

	/** The application configuration service. */
	@Autowired
	private ApplicationConfigurationService applicationConfigurationService;


	/**
	 * Instantiates a new email service impl.
	 */
	public EmailServiceImpl() {
		super();
	}


	/**
	 * Inits the settings.
	 */
	@PostConstruct
	public void initSettings() {
		final Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
		final Authentication authentication = new UsernamePasswordAuthenticationToken("system.init", "n/a", authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		LOGGER.info(EMAIL_SETTINGS,applicationConfigurationService.checkValueOrLoadDefault(EMAIL_CONFIGURATION_SEND_EMAILS, SEND_EMAIL, ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), SEND_EMAIL, RESPONSIBLE_FOR_SENDING_EMAIL, APPLICATION_EMAIL_SEND_EMAIL, "false"));
		LOGGER.info(EMAIL_SETTINGS,applicationConfigurationService.checkValueOrLoadDefault(EMAIL_CONFIGURATION_FROM_EMAIL, FROM_EMAIL, ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), SEND_EMAIL, RESPONSIBLE_FOR_SENDING_EMAIL, APPLICATION_EMAIL_FROM_EMAIL, "admin@hack23.com"));
		LOGGER.info(EMAIL_SETTINGS,applicationConfigurationService.checkValueOrLoadDefault(EMAIL_CONFIGURATION_SMTP_HOST, SMTP_HOST, ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), SMTP_HOST, RESPONSIBLE_FOR_SENDING_EMAIL, APPLICATION_EMAIL_SMTP_HOST, "localhost"));
		LOGGER.info(EMAIL_SETTINGS,applicationConfigurationService.checkValueOrLoadDefault(EMAIL_CONFIGURATION_SMTP_PORT, SMTP_PORT, ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), SMTP_PORT, RESPONSIBLE_FOR_SENDING_EMAIL, APPLICATION_EMAIL_SMTP_PORT, DEFAULT_SMTP_PORT));
		LOGGER.info(EMAIL_SETTINGS,applicationConfigurationService.checkValueOrLoadDefault(EMAIL_CONFIGURATION_SMTP_USERNAME, SMTP_USERNAME, ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), SMTP_USERNAME, RESPONSIBLE_FOR_SENDING_EMAIL, APPLICATION_EMAIL_SMTP_USERNAME, "username"));
		LOGGER.info(EMAIL_SETTINGS,applicationConfigurationService.checkValueOrLoadDefault(EMAIL_CONFIGURATION_SMTP_SECRET, SMTP_SECRET, ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), SMTP_SECRET, RESPONSIBLE_FOR_SENDING_EMAIL, APPLICATION_EMAIL_SMTP_SECRET, "password"));
		LOGGER.info(EMAIL_SETTINGS,applicationConfigurationService.checkValueOrLoadDefault(EMAIL_CONFIGURATION_SMTP_AUTH, SMTP_AUTH, ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), SMTP_AUTH, RESPONSIBLE_FOR_SENDING_EMAIL, APPLICATION_EMAIL_SMTP_AUTH, "true"));
		LOGGER.info(EMAIL_SETTINGS,applicationConfigurationService.checkValueOrLoadDefault(EMAIL_CONFIGURATION_SMTP_STARTTLS_ENABLE, SMTP_STARTTLS_ENABLE, ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), SMTP_STARTTLS_ENABLE, RESPONSIBLE_FOR_SENDING_EMAIL, APPLICATION_EMAIL_SMTP_STARTTLS_ENABLE, "true"));
		SecurityContextHolder.getContext().setAuthentication(null);
	}


	@Override
	public void sendEmail(final String toEmail,final String subject, final String content) {
		final ApplicationConfiguration sendEmail = applicationConfigurationService.checkValueOrLoadDefault(EMAIL_CONFIGURATION_SEND_EMAILS, SEND_EMAIL, ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), SEND_EMAIL, RESPONSIBLE_FOR_SENDING_EMAIL, APPLICATION_EMAIL_SEND_EMAIL, "false");
		final ApplicationConfiguration fromEmail = applicationConfigurationService.checkValueOrLoadDefault(EMAIL_CONFIGURATION_FROM_EMAIL, FROM_EMAIL, ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), SEND_EMAIL, RESPONSIBLE_FOR_SENDING_EMAIL, APPLICATION_EMAIL_FROM_EMAIL, "admin@hack23.com");

		final JavaMailSender javaMailSender = getMailSender();

	    final SimpleMailMessage emailMessage = new SimpleMailMessage();
	    emailMessage.setFrom(fromEmail.getPropertyValue());
        emailMessage.setTo(toEmail);
        emailMessage.setText(content);
		emailMessage.setSubject(subject);

		if("true".equalsIgnoreCase(sendEmail.getPropertyValue())) {
			LOGGER.info("Sending email:{}",emailMessage);
			javaMailSender.send(emailMessage);
		} else {
			LOGGER.info("Email sending disabled, do not send email:{}",emailMessage);
		}
	}

	/**
	 * Gets the mail sender.
	 *
	 * @return the mail sender
	 */
	private JavaMailSender getMailSender() {
		final JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		final ApplicationConfiguration smtpHostConfig = applicationConfigurationService.checkValueOrLoadDefault(EMAIL_CONFIGURATION_SMTP_HOST, SMTP_HOST, ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), SMTP_HOST, RESPONSIBLE_FOR_SENDING_EMAIL, APPLICATION_EMAIL_SMTP_HOST, "localhost");
		final ApplicationConfiguration smtpPort = applicationConfigurationService.checkValueOrLoadDefault(EMAIL_CONFIGURATION_SMTP_PORT, SMTP_PORT, ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), SMTP_PORT, RESPONSIBLE_FOR_SENDING_EMAIL, APPLICATION_EMAIL_SMTP_PORT, DEFAULT_SMTP_PORT);
		final ApplicationConfiguration smtpUsername = applicationConfigurationService.checkValueOrLoadDefault(EMAIL_CONFIGURATION_SMTP_USERNAME, SMTP_USERNAME, ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), SMTP_USERNAME, RESPONSIBLE_FOR_SENDING_EMAIL, APPLICATION_EMAIL_SMTP_USERNAME, "username");
		final ApplicationConfiguration smtpPassword = applicationConfigurationService.checkValueOrLoadDefault(EMAIL_CONFIGURATION_SMTP_SECRET, SMTP_SECRET, ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), SMTP_SECRET, RESPONSIBLE_FOR_SENDING_EMAIL, APPLICATION_EMAIL_SMTP_SECRET, "password");
		final ApplicationConfiguration smtpAuth = applicationConfigurationService.checkValueOrLoadDefault(EMAIL_CONFIGURATION_SMTP_AUTH, SMTP_AUTH, ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), SMTP_AUTH, RESPONSIBLE_FOR_SENDING_EMAIL, APPLICATION_EMAIL_SMTP_AUTH, "true");
		final ApplicationConfiguration smtpStartTlsEnable = applicationConfigurationService.checkValueOrLoadDefault(EMAIL_CONFIGURATION_SMTP_STARTTLS_ENABLE, SMTP_STARTTLS_ENABLE, ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), SMTP_STARTTLS_ENABLE, RESPONSIBLE_FOR_SENDING_EMAIL, APPLICATION_EMAIL_SMTP_STARTTLS_ENABLE, "true");


		javaMailSender.setHost(smtpHostConfig.getPropertyValue());
		javaMailSender.setPort(getSmtpPort(smtpPort));
		javaMailSender.setUsername(smtpUsername.getPropertyValue());
		javaMailSender.setPassword(smtpPassword.getPropertyValue());

		final Properties javaMailProperties = new Properties();

		javaMailProperties.setProperty(MAIL_SMTP_AUTH, smtpAuth.getPropertyValue());
		javaMailProperties.setProperty(MAIL_SMTP_STARTTLS_ENABLE, smtpStartTlsEnable.getPropertyValue());

		javaMailSender.setJavaMailProperties(javaMailProperties);

		return javaMailSender;
	}


	/**
	 * Gets the smtp port.
	 *
	 * @param smtpPort the smtp port
	 * @return the smtp port
	 */
	private static int getSmtpPort(final ApplicationConfiguration smtpPort) {
		if (StringUtils.isNumeric(smtpPort.getPropertyValue())) {
			return Integer.parseInt(smtpPort.getPropertyValue());
		} else {
			return Integer.parseInt(DEFAULT_SMTP_PORT);
		}
	}

}
