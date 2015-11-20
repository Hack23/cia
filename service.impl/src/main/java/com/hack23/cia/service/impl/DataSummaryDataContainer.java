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

import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
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
@Component(value="DataSummaryDataContainer")
@Transactional(propagation=Propagation.REQUIRED)
@Secured({"ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
public final class DataSummaryDataContainer implements DataContainer<DataSummary,String>{

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

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#getAll()
	 */
	@Override
	public List<DataSummary> getAll() {
		final List<DataSummary> list = new ArrayList<DataSummary>();
		list.add(load(null));
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#load(java.io.Serializable)
	 */
	@Override
	public DataSummary load(final String id) {
		final List<PersonData> all = personDataDAO.getAll();
		final long personSize=all.size();

		return new DataSummary(personSize,voteDataDAO.getSize(),voteDataDAO.getSize(),documentElementDAO.getSize(),documentContentDataDAO.getSize(),documentStatusContainerDAO.getSize(),committeeProposalComponentDataDAO.getSize());
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#getAllBy(javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public List<DataSummary> getAllBy(
			SingularAttribute<DataSummary, ? extends Object> property,
			Object value) {
		return new ArrayList<DataSummary>();
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#findListByProperty(java.lang.Object[], javax.persistence.metamodel.SingularAttribute[])
	 */
	@Override
	public List<DataSummary> findListByProperty(Object[] values,
			SingularAttribute<DataSummary, ? extends Object>... properties) {
		return new ArrayList<DataSummary>();
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#findByQueryProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public <T, V> T findByQueryProperty(Class<T> clazz,
			SingularAttribute<T, ? extends Object> property, Class<V> clazz2,
			SingularAttribute<V, ? extends Object> property2, Object value) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#findListByEmbeddedProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public <T, V> List<T> findListByEmbeddedProperty(Class<T> clazz,
			SingularAttribute<T, V> property, Class<V> clazz2,
			SingularAttribute<V, ? extends Object> property2, Object value) {
		return new ArrayList<T>();
	}
}
