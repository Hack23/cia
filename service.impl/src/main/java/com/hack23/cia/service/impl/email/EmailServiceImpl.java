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

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationConfiguration;
import com.hack23.cia.model.internal.application.system.impl.ConfigurationGroup;
import com.hack23.cia.service.data.api.ApplicationConfigurationService;

/**
 * The Class EmailServiceImpl.
 */
@Component("MailService")
@Secured({"ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
public class EmailServiceImpl implements EmailService {
		
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
		LOGGER.info("Email settings:{}",applicationConfigurationService.checkValueOrLoadDefault("Email configuration send emails", "Send email", ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), "Send email", "Responsible for sending email", "application.email.send.email", "false"));
		LOGGER.info("Email settings:{}",applicationConfigurationService.checkValueOrLoadDefault("Email configuration from email", "From email", ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), "Send email", "Responsible for sending email", "application.email.from.email", "admin@hack23.com"));		
		LOGGER.info("Email settings:{}",applicationConfigurationService.checkValueOrLoadDefault("Email configuration smtp host", "Smtp Host", ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), "Smtp host", "Responsible for sending email", "application.email.smtp.host", "localhost"));
		LOGGER.info("Email settings:{}",applicationConfigurationService.checkValueOrLoadDefault("Email configuration smtp port", "Smtp port", ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), "Smtp port", "Responsible for sending email", "application.email.smtp.port", "587"));
		LOGGER.info("Email settings:{}",applicationConfigurationService.checkValueOrLoadDefault("Email configuration smtp username", "Smtp username", ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), "Smtp username", "Responsible for sending email", "application.email.smtp.username", "username"));
		LOGGER.info("Email settings:{}",applicationConfigurationService.checkValueOrLoadDefault("Email configuration smtp password", "Smtp password", ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), "Smtp password", "Responsible for sending email", "application.email.smtp.password", "password"));
		LOGGER.info("Email settings:{}",applicationConfigurationService.checkValueOrLoadDefault("Email configuration smtp auth", "Smtp auth", ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), "Smtp auth", "Responsible for sending email", "application.email.smtp.auth", "true"));
		LOGGER.info("Email settings:{}",applicationConfigurationService.checkValueOrLoadDefault("Email configuration smtp starttls enable", "Smtp starttls enable", ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), "Smtp starttls enable", "Responsible for sending email", "application.email.smtp.starttls.enable", "true"));
		
	}


	@Override
	public void sendEmail(final String toEmail,final String subject, final String content) {
		final ApplicationConfiguration sendEmail = applicationConfigurationService.checkValueOrLoadDefault("Email configuration send emails", "Send email", ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), "Send email", "Responsible for sending email", "application.email.send.email", "false");
		final ApplicationConfiguration fromEmail = applicationConfigurationService.checkValueOrLoadDefault("Email configuration from email", "From email", ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), "Send email", "Responsible for sending email", "application.email.from.email", "admin@hack23.com");		

		final JavaMailSender javaMailSender = getMailSender();
		
	    SimpleMailMessage emailMessage = new SimpleMailMessage();
	    emailMessage.setFrom(fromEmail.getPropertyValue());
        emailMessage.setTo(toEmail);
        emailMessage.setText(content);
		emailMessage.setSubject(subject);
		
		if(sendEmail.getPropertyValue().equalsIgnoreCase("true")) {
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

		final ApplicationConfiguration smtpHostConfig = applicationConfigurationService.checkValueOrLoadDefault("Email configuration smtp host", "Smtp Host", ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), "Smtp host", "Responsible for sending email", "application.email.smtp.host", "localhost");
		final ApplicationConfiguration smtpPort = applicationConfigurationService.checkValueOrLoadDefault("Email configuration smtp port", "Smtp port", ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), "Smtp port", "Responsible for sending email", "application.email.smtp.port", "587");
		final ApplicationConfiguration smtpUsername = applicationConfigurationService.checkValueOrLoadDefault("Email configuration smtp username", "Smtp username", ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), "Smtp username", "Responsible for sending email", "application.email.smtp.username", "username");
		final ApplicationConfiguration smtpPassword = applicationConfigurationService.checkValueOrLoadDefault("Email configuration smtp password", "Smtp password", ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), "Smtp password", "Responsible for sending email", "application.email.smtp.password", "password");
		final ApplicationConfiguration smtpAuth = applicationConfigurationService.checkValueOrLoadDefault("Email configuration smtp auth", "Smtp auth", ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), "Smtp auth", "Responsible for sending email", "application.email.smtp.auth", "true");
		final ApplicationConfiguration smtpStartTlsEnable = applicationConfigurationService.checkValueOrLoadDefault("Email configuration smtp starttls enable", "Smtp starttls enable", ConfigurationGroup.EXTERNAL_SERVICES, EmailServiceImpl.class.getSimpleName(), "Smtp starttls enable", "Responsible for sending email", "application.email.smtp.starttls.enable", "true");		

		
		javaMailSender.setHost(smtpHostConfig.getPropertyValue());
		javaMailSender.setPort(Integer.parseInt(smtpPort.getPropertyValue()));
		javaMailSender.setUsername(smtpUsername.getPropertyValue());
		javaMailSender.setPassword(smtpPassword.getPropertyValue());
		
		final Properties javaMailProperties = new Properties();
		
		javaMailProperties.setProperty("mail.smtp.auth", smtpAuth.getPropertyValue());
		javaMailProperties.setProperty("mail.smtp.starttls.enable", smtpStartTlsEnable.getPropertyValue());
		
		javaMailSender.setJavaMailProperties(javaMailProperties);
		
		return javaMailSender;
	}
		
}
