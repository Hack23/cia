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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.hack23.cia.model.external.worldbank.data.impl.Country;
import com.hack23.cia.model.external.worldbank.data.impl.Indicator;
import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData;
import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData_;
import com.hack23.cia.service.data.api.DataDAO;

/**
 * The Class DataDAOImpl.
 */
@Repository("DataDAOImpl")
public final class DataDAOImpl extends AbstractGenericDAOImpl<WorldBankData, Long> implements DataDAO {

	/** The entity manager. */
	@PersistenceContext(name = "ciaPersistenceUnit")
	private EntityManager entityManager;

	/**
	 * Instantiates a new data dao impl.
	 */
	public DataDAOImpl() {
		super(WorldBankData.class);
	}
	/**
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

	/** (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.DataDAO#getIdList()
	 */
	@Override
	public List<String> getIdList() {
		final CriteriaQuery<Object[]> criteria = getCriteriaBuilder().createQuery(Object[].class);
		final Root<WorldBankData> personRoot = criteria.from(WorldBankData.class);
		final Path<Country> countryPath = personRoot.get(WorldBankData_.country);
		final Path<Indicator> indicatorPath = personRoot.get(WorldBankData_.indicator);
		criteria.select(getCriteriaBuilder().array(countryPath, indicatorPath));
		criteria.distinct(true);
		final List<Object[]> valueArray = getEntityManager().createQuery(criteria).getResultList();
		final List<String> resultList= new ArrayList<>();

		for (final Object[]  objects : valueArray) {
			resultList.add(((Country)  objects[0]).getId() + "." + ((Indicator)  objects[1]).getId());
		}
		return resultList;
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.AbstractGenericDAO#getSize()
	 */
	@Override
	public Long getSize() {
		return (long) getAll().size();
	}

}
