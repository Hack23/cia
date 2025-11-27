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

import java.util.ArrayList;
import java.util.List;

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
final class DataDAOImpl extends AbstractGenericDAOImpl<WorldBankData, Long> implements DataDAO {

	/** The Constant EXPECTED_NR_ELEMENTS. */
	private static final int EXPECTED_NR_ELEMENTS = 2;

	/** The Constant FIRST_ELEMENT. */
	private static final int FIRST_ELEMENT = 0;

	/** The Constant SECOND_ELEMENT. */
	private static final int SECOND_ELEMENT = 1;

	/**
	 * Instantiates a new data dao impl.
	 */
	public DataDAOImpl() {
		super(WorldBankData.class);
	}

	@Override
	public List<String> getIdList() {
		final CriteriaQuery<Object[]> criteria = getCriteriaBuilder().createQuery(Object[].class);
		final Root<WorldBankData> personRoot = criteria.from(WorldBankData.class);
		final Path<Country> countryPath = personRoot.get(WorldBankData_.country);
		final Path<Indicator> indicatorPath = personRoot.get(WorldBankData_.indicator);
		criteria.select(getCriteriaBuilder().array(countryPath, indicatorPath));
		criteria.distinct(true);
		final List<Object[]> valueArray = getEntityManager().createQuery(criteria).getResultList();
		final List<String> resultList = new ArrayList<>();

		for (final Object[] objects : valueArray) {
			if (objects.length == EXPECTED_NR_ELEMENTS) {
				final StringBuilder stringBuilder = new StringBuilder().append(((Country) objects[FIRST_ELEMENT]).getId())
						.append('.').append(((Indicator) objects[SECOND_ELEMENT]).getId());
				resultList.add(stringBuilder.toString());
			}
		}
		return resultList;
	}

}
