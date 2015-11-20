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
package com.hack23.cia.service.data.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement_;
import com.hack23.cia.service.data.api.DocumentElementDAO;

/**
 * The Class DocumentElementDAOImpl.
 */
@Repository(value = "DocumentElementDAO")
public final class DocumentElementDAOImpl extends
AbstractGenericDAOImpl<DocumentElement, String> implements
DocumentElementDAO {

	/** The Constant expectedDocumentElements. */
	private static final Map<String, Long> expectedDocumentElements = new HashMap<String, Long>();

	static {
		expectedDocumentElements.put("", 87L);
		expectedDocumentElements.put("1993/94", 1L);
		expectedDocumentElements.put("1998/99", 1L);
		expectedDocumentElements.put("1999", 261L);
		expectedDocumentElements.put("1999/00", 87L);
		expectedDocumentElements.put("1999/2000", 2822L);
		expectedDocumentElements.put("2000", 630L);
		expectedDocumentElements.put("2000/01", 8340L);
		expectedDocumentElements.put("2001", 631L);
		expectedDocumentElements.put("2001/02", 9304L);
		expectedDocumentElements.put("2002", 719L);
		expectedDocumentElements.put("2002/03", 8654L);
		expectedDocumentElements.put("2003", 774L);
		expectedDocumentElements.put("2003/04", 10458L);
		expectedDocumentElements.put("2004", 800L);
		expectedDocumentElements.put("2004/05", 12267L);
		expectedDocumentElements.put("2005", 731L);
		expectedDocumentElements.put("2005/06", 14594L);
		expectedDocumentElements.put("2006", 1205L);
		expectedDocumentElements.put("2006/07", 10547L);
		expectedDocumentElements.put("2007", 1681L);
		expectedDocumentElements.put("2007/08", 12398L);
		expectedDocumentElements.put("2008", 1427L);
		expectedDocumentElements.put("2008/09", 11903L);
		expectedDocumentElements.put("2009", 1255L);
		expectedDocumentElements.put("2009/10", 12308L);
		expectedDocumentElements.put("2010", 1164L);
		expectedDocumentElements.put("2010/11", 11108L);
		expectedDocumentElements.put("2011", 1383L);
		expectedDocumentElements.put("2011/12", 11752L);
		expectedDocumentElements.put("2012", 1168L);
		expectedDocumentElements.put("2012/12", 1L);
		expectedDocumentElements.put("2012/13", 11501L);
		expectedDocumentElements.put("2013", 1262L);
		expectedDocumentElements.put("2013/13", 1L);
		expectedDocumentElements.put("2013/14", 11858L);
		expectedDocumentElements.put("2013/2014", 2L);
		expectedDocumentElements.put("2014", 1453L);
		expectedDocumentElements.put("2014/", 1L);		
		expectedDocumentElements.put("2014/15", 11340L);
	}

	/** The entity manager. */
	@PersistenceContext(name = "ciaPersistenceUnit")
	private EntityManager entityManager;

	/**
	 * Instantiates a new document element dao impl.
	 */
	public DocumentElementDAOImpl() {
		super(DocumentElement.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.data.api.DocumentElementDAO#checkDocumentElement
	 * (java.lang.String)
	 */
	@Override
	public boolean checkDocumentElement(final String documentId) {
		final CriteriaQuery<DocumentElement> criteriaQuery = getCriteriaBuilder()
				.createQuery(DocumentElement.class);
		final Root<DocumentElement> root = criteriaQuery
				.from(DocumentElement.class);
		criteriaQuery.select(root);
		final Predicate condition = getCriteriaBuilder().equal(
				root.get(DocumentElement_.id), documentId);
		criteriaQuery.where(condition);
		final TypedQuery<DocumentElement> typedQuery = getEntityManager()
				.createQuery(criteriaQuery);
		addCacheHints(typedQuery, "checkDocumentElement");

		final List<DocumentElement> resultList = typedQuery.getResultList();

		if (resultList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.data.api.DocumentElementDAO#getAvaibleDocumentContent
	 * ()
	 */
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

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.data.api.DocumentElementDAO#getAvaibleDocumentStatus
	 * ()
	 */
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

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.data.impl.AbstractRiksdagenDAOImpl#getEntityManager
	 * ()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.data.api.DocumentElementDAO#getIdList()
	 */
	@Override
	public List<String> getIdList() {
		final CriteriaQuery<String> criteria = getCriteriaBuilder()
				.createQuery(String.class);
		final Root<DocumentElement> root = criteria.from(DocumentElement.class);
		criteria.select(root.get(DocumentElement_.id));
		return getEntityManager().createQuery(criteria).getResultList();
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.DocumentElementDAO#getMissingDocumentStartFromYear()
	 */
	@Override
	public int getMissingDocumentStartFromYear() {
		final CriteriaQuery<Tuple> criteria = getCriteriaBuilder().createQuery(
				Tuple.class);
		final Root<DocumentElement> root = criteria.from(DocumentElement.class);
		criteria.multiselect(

				root.get(DocumentElement_.rm),
				getCriteriaBuilder().count(root)).orderBy(
						getCriteriaBuilder().asc(
								root.get(DocumentElement_.rm)));

		criteria.groupBy(root.get(DocumentElement_.rm));

		final List<Tuple> list = getEntityManager().createQuery(criteria)
				.getResultList();

		for (final Tuple tuple : list) {
			final String rm = (String) tuple.get(0);
			final Long sum = (Long) tuple.get(1);

			if (rm != null && !"".equals(rm)) {

				final Long expectedSum= expectedDocumentElements.get(rm.trim());
				if (expectedSum !=null) {

					if (sum < expectedSum) {
						return Integer.parseInt(rm.substring(0, 4));
					}

				} else {

					return Integer.parseInt(rm.substring(0, 4));
				}

			}

		}

		return 2000;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.data.api.AbstractGenericDAO#getSize()
	 */
	@Override
	public Long getSize() {
		return (long) getIdList().size();
	}

}