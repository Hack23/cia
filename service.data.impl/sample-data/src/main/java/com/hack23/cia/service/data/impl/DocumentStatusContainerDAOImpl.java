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

import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData_;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.service.data.api.DocumentStatusContainerDAO;

/**
 * The Class DocumentStatusContainerDAOImpl.
 */
@Repository("DocumentStatusContainerDAO")
final class DocumentStatusContainerDAOImpl extends
AbstractGenericDAOImpl<DocumentStatusContainer, Long> implements
DocumentStatusContainerDAO {

	/**
	 * Instantiates a new document status container dao impl.
	 */
	public DocumentStatusContainerDAOImpl() {
		super(DocumentStatusContainer.class);
	}

	@Override
	public int checkExistByDocumentId(final String id) {
		final CriteriaQuery<DocumentData> criteriaQuery = getCriteriaBuilder()
				.createQuery(DocumentData.class);
		final Root<DocumentData> root = criteriaQuery.from(DocumentData.class);
		criteriaQuery.select(root);
		final Predicate condition = getCriteriaBuilder().equal(root.get(DocumentData_.id), id);
		criteriaQuery.where(condition);
		final TypedQuery<DocumentData> typedQuery = getEntityManager()
				.createQuery(criteriaQuery);
		addCacheHints(typedQuery, "findFirstByProperty");

		return typedQuery.getResultList().size();
	}

	@Override
	public List<String> getAvaibleCommitteeProposal() {
		final CriteriaQuery<String> criteria = getCriteriaBuilder().createQuery(String.class);
		final Root<DocumentData> root = criteria.from(DocumentData.class);
		criteria.select(root.get(DocumentData_.id));
		criteria.where(getCriteriaBuilder().isNotNull(root.get(DocumentData_.committeeReportUrlXml)));
		return getEntityManager().createQuery(criteria).getResultList();

	}

	@Override
	public List<String> getIdList() {
		final CriteriaQuery<String> criteria = getCriteriaBuilder().createQuery(String.class);
		final Root<DocumentData> root = criteria.from(DocumentData.class);
		criteria.select(root.get(DocumentData_.id));
		return getEntityManager().createQuery(criteria).getResultList();
	}

}
