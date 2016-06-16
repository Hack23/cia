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
package com.hack23.cia.service.impl.action.application.translation;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.hack23.cia.model.internal.application.system.impl.ApplicationConfiguration;
import com.hack23.cia.model.internal.application.system.impl.ConfigurationGroup;
import com.hack23.cia.service.data.api.ApplicationConfigurationService;

/**
 * The Class TranslationServiceImpl.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public final class TranslationServiceImpl implements TranslationService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TranslationServiceImpl.class);


	/** The application configuration service. */
	@Autowired
	private ApplicationConfigurationService applicationConfigurationService;

	/**
	 * Instantiates a new translation service impl.
	 */
	public TranslationServiceImpl() {
		super();
	}

	@Override
	public String translate(final String translateText, final String targetLanguage) throws TranslationException {

		final ApplicationConfiguration googleTranslateApiKey = applicationConfigurationService.checkValueOrLoadDefault(
				"Google translate api accesskey", "Required for use of the translation service",
				ConfigurationGroup.AUTHENTICATION, TranslationServiceImpl.class.getSimpleName(), "Translation Service",
				"Responsible configure access credentials for google translate api",
				"external.services.google.translate.api.accesskey", "");
		final ApplicationConfiguration googleTranslateApiApplicationName = applicationConfigurationService
				.checkValueOrLoadDefault("Google translate api application name",
						"Required for use of the translation service", ConfigurationGroup.AUTHENTICATION,
						TranslationServiceImpl.class.getSimpleName(), "Translation Service",
						"Responsible configure application name for google translate api",
						"external.services.google.translate.api.application.name", "");

		if (!StringUtils.isBlank(googleTranslateApiKey.getPropertyValue())
				&& !StringUtils.isBlank(googleTranslateApiApplicationName.getPropertyValue())) {

			try {
				final Translate t = new Translate.Builder(
						com.google.api.client.googleapis.javanet.GoogleNetHttpTransport.newTrustedTransport(),
						JacksonFactory.getDefaultInstance(), null)
								.setApplicationName(googleTranslateApiApplicationName.getPropertyValue()).build();

				final Translate.Translations.List list = t.new Translations().list(Arrays.asList(translateText),
						targetLanguage);
				list.setKey(googleTranslateApiKey.getPropertyValue());
				list.setSource("EN");
				final TranslationsListResponse response = list.execute();

				return response.getTranslations().listIterator().next().getTranslatedText();

			} catch (GeneralSecurityException | IOException e) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("Problem translation text:");
				stringBuilder.append(translateText);
				stringBuilder.append("to language:");
				stringBuilder.append(targetLanguage);
				LOGGER.warn(stringBuilder.toString(),e);
				throw new TranslationException(stringBuilder.toString(), e);
			}
		}
		throw new TranslationException("Missing google api key or application name", null);
	}
}
