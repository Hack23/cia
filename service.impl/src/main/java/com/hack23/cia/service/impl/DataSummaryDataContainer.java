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
package com.hack23.cia.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.DataSummary;
import com.hack23.cia.service.data.api.CommitteeProposalComponentDataDAO;
import com.hack23.cia.service.data.api.DocumentContentDataDAO;
import com.hack23.cia.service.data.api.DocumentElementDAO;
import com.hack23.cia.service.data.api.DocumentStatusContainerDAO;
import com.hack23.cia.service.data.api.PersonDataDAO;
import com.hack23.cia.service.data.api.VoteDataDAO;

/**
 * The Class DataSummaryDataContainer.
 */
@Component("DataSummaryDataContainer")
@Transactional(propagation=Propagation.REQUIRED)
@Secured({"ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
public final class DataSummaryDataContainer implements DataContainer<DataSummary,String>{

	private static final int PARTICIPANTS_EACH_BALLOT = 349;

	/** The committee proposal component data dao. */
	@Autowired
	private CommitteeProposalComponentDataDAO committeeProposalComponentDataDAO;

	/** The document content data dao. */
	@Autowired
	private DocumentContentDataDAO documentContentDataDAO;

	/** The document element dao. */
	@Autowired
	private DocumentElementDAO documentElementDAO;

	/** The document status container dao. */
	@Autowired
	private DocumentStatusContainerDAO documentStatusContainerDAO;

	/** The person data dao. */
	@Autowired
	private PersonDataDAO personDataDAO;

	/** The vote data dao. */
	@Autowired
	private VoteDataDAO voteDataDAO;

	/**
	 * Instantiates a new data summary data container.
	 */
	public DataSummaryDataContainer() {
		super();
	}

	@Override
	public <T, V> T findByQueryProperty(final Class<T> clazz,
			final SingularAttribute<T, ? extends Object> property, final Class<V> clazz2,
			final SingularAttribute<V, ? extends Object> property2, final Object value) {
		return null;
	}

	@Override
	public <T, V> List<T> findListByEmbeddedProperty(final Class<T> clazz,
			final SingularAttribute<T, V> property, final Class<V> clazz2,
			final SingularAttribute<V, ? extends Object> property2, final Object value) {
		return new ArrayList<>();
	}

	@Override
	public List<DataSummary> findListByProperty(final Object[] values,
			final SingularAttribute<DataSummary, ? extends Object>... properties) {
		return new ArrayList<>();
	}

	@Override
	public <T, V> List<T> findOrderedByPropertyListByEmbeddedProperty(final Class<T> clazz, final SingularAttribute<T, V> property,
			final Class<V> clazz2, final SingularAttribute<V, ? extends Object> property2, final Object value,
			final SingularAttribute<T, ? extends Object> orderByProperty) {
		return new ArrayList<>();
	}

	@Override
	public <T, V> List<T> findOrderedListByEmbeddedProperty(final Class<T> clazz, final SingularAttribute<T, V> property,
			final Class<V> clazz2, final SingularAttribute<V, ? extends Object> property2, final Object value,
			final SingularAttribute<V, ? extends Object> orderByProperty) {
		return new ArrayList<>();
	}

	@Override
	public List<DataSummary> findOrderedListByProperty(final SingularAttribute<DataSummary, ? extends Object> property,
			final Object value, final SingularAttribute<DataSummary, ? extends Object> orderByProperty) {
		return new ArrayList<>();
	}

	@Override
	public List<DataSummary> findOrderedListByProperty(final SingularAttribute<DataSummary, ? extends Object> orderByProperty,
			final Object[] values, final SingularAttribute<DataSummary, ? extends Object>... properties) {
		return new ArrayList<>();
	}

	@Override
	public List<DataSummary> getAll() {
		final List<DataSummary> list = new ArrayList<>();
		list.add(load(null));
		return list;
	}

	@Override
	public List<DataSummary> getAllBy(
			final SingularAttribute<DataSummary, ? extends Object> property,
			final Object value) {
		return new ArrayList<>();
	}

	@Override
	public List<DataSummary> getAllOrderBy(final SingularAttribute<DataSummary, ? extends Object> property) {
		return new ArrayList<>();
	}

	@Override
	public DataSummary load(final String id) {
		final long personSize=personDataDAO.getAll().size();
		final long votes = voteDataDAO.getSize();
		return new DataSummary(personSize,votes,votes / PARTICIPANTS_EACH_BALLOT ,documentElementDAO.getSize(),documentContentDataDAO.getSize(),documentStatusContainerDAO.getSize(),committeeProposalComponentDataDAO.getSize());
	}

	@Override
	public List<DataSummary> getPage(final int pageNr, final int resultPerPage) {
		return new ArrayList<>();
	}

	@Override
	public List<DataSummary> getPageOrderBy(final int pageNr, final int resultPerPage,
			final SingularAttribute<DataSummary, ? extends Object> orderBy) {
		return new ArrayList<>();
	}

	@Override
	public Long getSize() {
		return Long.valueOf(getAll().size());
	}
}
