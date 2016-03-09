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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Repository;

import com.hack23.cia.service.data.api.SearchIndexer;

/**
 * The Class SearchIndexerImpl.
 */
@Repository
public final class SearchIndexerImpl implements SearchIndexer {

	/** The entity manager. */
	@PersistenceContext(name = "ciaPersistenceUnit")
	private EntityManager entityManager;

	/** The full text entity manager. */
	private FullTextEntityManager fullTextEntityManager;

	/**
	 * Gets the full text entity manager.
	 *
	 * @return the full text entity manager
	 */
	private FullTextEntityManager getFullTextEntityManager() {
	        if (fullTextEntityManager == null) {
	        	fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
	        }
	        return fullTextEntityManager;
	}

	/**
	 * {@inheritDoc}
	 * @see com.hack23.cia.service.data.impl.SearchIndexer#updateSearchIndex()
	 */
	@Override
	public void updateSearchIndex() throws InterruptedException {
		getFullTextEntityManager().createIndexer().startAndWait();
	}
}
