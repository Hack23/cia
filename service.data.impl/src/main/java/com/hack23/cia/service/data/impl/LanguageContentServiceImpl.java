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
package com.hack23.cia.service.data.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.system.impl.LanguageContentData;
import com.hack23.cia.model.internal.application.system.impl.LanguageContentType;
import com.hack23.cia.service.data.api.LanguageContentDataDAO;
import com.hack23.cia.service.data.api.LanguageContentService;

/**
 * The Class LanguageContentServiceImpl.
 */
@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
final class LanguageContentServiceImpl implements LanguageContentService {


	/** The language content data dao. */
	@Autowired
	private LanguageContentDataDAO languageContentDataDAO;

	/**
	 * Instantiates a new language content service impl.
	 */
	public LanguageContentServiceImpl() {
		super();
	}

	@Override
	public String getLanguageResource(final String key, final String keyGroup,final String language, final String defaultEnglishValue) {
		final LanguageContentData findTranslation = languageContentDataDAO.findTranslation(key, language, language);

		if (findTranslation != null) {
			return findTranslation.getLanguageValue();
		} else {
			final LanguageContentData newDefaultValue = new LanguageContentData().withCreatedDate(new Date()).withFromLanguage("en").withToLanguage("en").withLanguageContentType(LanguageContentType.HUMAN_TRANSLATION).withLanguageValue(defaultEnglishValue).withRefKey(key);
			languageContentDataDAO.persist(newDefaultValue);
			return newDefaultValue.getLanguageValue();
		}
	}

}
