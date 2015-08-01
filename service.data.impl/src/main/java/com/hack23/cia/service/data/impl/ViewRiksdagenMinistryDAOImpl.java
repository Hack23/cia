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
 *	$Id: ViewRiksdagenMinistryDAOImpl.java 6055 2015-05-08 20:50:29Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/service.data.impl/src/main/java/com/hack23/cia/service/data/impl/ViewRiksdagenMinistryDAOImpl.java $
 */
package com.hack23.cia.service.data.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry_;
import com.hack23.cia.service.data.api.ViewRiksdagenMinistryDAO;

/**
 * The Class ViewRiksdagenMinistryDAOImpl.
 */
@Repository(value = "ViewRiksdagenMinistryDAO")
public final class ViewRiksdagenMinistryDAOImpl extends
		AbstractGenericDAOImpl<ViewRiksdagenMinistry, String> implements
		ViewRiksdagenMinistryDAO {

	/** The entity manager. */
	@PersistenceContext(name = "ciaPersistenceUnit")
	private EntityManager entityManager;

	/**
	 * Instantiates a new view riksdagen ministry dao impl.
	 */
	public ViewRiksdagenMinistryDAOImpl() {
		super(ViewRiksdagenMinistry.class);
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
	 * @see com.hack23.cia.service.data.api.VoteDataDAO#getIdList()
	 */
	@Override
	public List<String> getIdList() {
		final CriteriaQuery<String> criteria = getCriteriaBuilder()
				.createQuery(String.class);
		final Root<ViewRiksdagenMinistry> root = criteria
				.from(ViewRiksdagenMinistry.class);
		criteria.select(getCriteriaBuilder().construct(String.class,
				root.get(ViewRiksdagenMinistry_.nameId)));
		return getEntityManager().createQuery(criteria).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hack23.cia.service.data.api.AbstractGenericDAO#getSize()
	 */
	@Override
	public Long getSize() {

		final CriteriaBuilder cb = getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(ViewRiksdagenMinistry.class)));

		return getEntityManager().createQuery(cq).getSingleResult();

	}

}
