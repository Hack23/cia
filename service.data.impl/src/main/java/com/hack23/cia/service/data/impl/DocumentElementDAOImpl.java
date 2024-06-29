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

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement_;
import com.hack23.cia.service.data.api.DocumentElementDAO;

/**
 * The Class DocumentElementDAOImpl.
 */
@Repository("DocumentElementDAO")
final class DocumentElementDAOImpl extends
AbstractGenericDAOImpl<DocumentElement, String> implements
DocumentElementDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DocumentElementDAOImpl.class);


	/**
	 * Instantiates a new document element dao impl.
	 */
	public DocumentElementDAOImpl() {
		super(DocumentElement.class);
	}

	@Override
	public List<String> getAvaibleDocumentContent() {
		final CriteriaQuery<String> criteria = getCriteriaBuilder()
				.createQuery(String.class);
		final Root<DocumentElement> root = criteria.from(DocumentElement.class);
		criteria.select(root.get(DocumentElement_.id));
		criteria.where(getCriteriaBuilder().isNotNull(
				root.get(DocumentElement_.documentUrlText)));
		return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<String> getAvaibleDocumentStatus() {
		final CriteriaQuery<String> criteria = getCriteriaBuilder()
				.createQuery(String.class);
		final Root<DocumentElement> root = criteria.from(DocumentElement.class);
		criteria.select(root.get(DocumentElement_.id));
		criteria.where(getCriteriaBuilder().isNotNull(
				root.get(DocumentElement_.documentStatusUrlXml)));
		return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<String> getIdList() {
		return getStringIdList(DocumentElement_.id);
	}

	@Override
	public int getMissingDocumentStartFromYear(final int startCheckYear) {

		final CriteriaQuery<String> criteria = getCriteriaBuilder()
				.createQuery(String.class);
		final Root<DocumentElement> root = criteria.from(DocumentElement.class);
		final Expression<String> createdYear = getCriteriaBuilder().substring(root.get(DocumentElement_.createdDate), 0,5);
		criteria.select(createdYear).groupBy(createdYear).orderBy(getCriteriaBuilder().asc(createdYear));
		final List<String> resultList = getEntityManager().createQuery(criteria).getResultList();

		LOGGER.info("getMissingDocumentStartFromYear current years contain documents:{}",resultList);

		if (resultList.isEmpty() || Integer.parseInt(resultList.get(0)) < startCheckYear) {
			return startCheckYear;
		} else {
			final int lastYear = Integer.parseInt(resultList.get(resultList.size()-1));

			if(lastYear - startCheckYear ==  (resultList.size()-1)) {
				return lastYear;
			} else {
				return startCheckYear;
			}
		}
	}

}