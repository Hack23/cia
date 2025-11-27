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
package com.hack23.cia.service.data.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.hack23.cia.model.internal.application.system.impl.LanguageContentData;
import com.hack23.cia.model.internal.application.system.impl.LanguageContentData_;
import com.hack23.cia.service.data.api.LanguageContentDataDAO;

/**
 * The Class LanguageContentDataDAOImpl.
 */
@Repository("LanguageContentDataDAO")
final class LanguageContentDataDAOImpl extends
AbstractGenericDAOImpl<LanguageContentData, Long> implements
LanguageContentDataDAO {

	/**
	 * Instantiates a new language content data dao impl.
	 */
	public LanguageContentDataDAOImpl() {
		super(LanguageContentData.class);
	}

	@Override
	public LanguageContentData findTranslation(final String key,
			final String fromLanguage, final String toLanguage) {

		final CriteriaQuery<LanguageContentData> criteriaQuery = getCriteriaBuilder()
				.createQuery(getPersistentClass());
		final Root<LanguageContentData> root = criteriaQuery
				.from(getPersistentClass());
		criteriaQuery.select(root);
		final Predicate keyCondition = getCriteriaBuilder().equal(root.get(LanguageContentData_.refKey),
				key);
		final Predicate fromCondition = getCriteriaBuilder().equal(
				root.get(LanguageContentData_.fromLanguage),
				fromLanguage);
		final Predicate toCondition = getCriteriaBuilder().equal(root.get(LanguageContentData_.toLanguage),
				toLanguage);

		final Predicate and = getCriteriaBuilder().and(keyCondition, fromCondition, toCondition);

		criteriaQuery.where(and);

		final TypedQuery<LanguageContentData> typedQuery = getEntityManager()
				.createQuery(criteriaQuery);
		addCacheHints(typedQuery, "findTranslation");

		final List<LanguageContentData> resultList = typedQuery.getResultList();

		if (resultList.isEmpty()) {
			return null;
		} else {
			return resultList.get(0);
		}

	}

}
