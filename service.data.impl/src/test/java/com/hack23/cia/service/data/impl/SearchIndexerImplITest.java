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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;
import com.hack23.cia.service.data.api.SearchIndexer;

/**
 * The Class SearchIndexerImplITest.
 */
public class SearchIndexerImplITest extends AbstractServiceDataFunctionalIntegrationTest {

	/** The search indexer. */
	@Autowired
	private SearchIndexer searchIndexer;

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
	 * Test update search index.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testCreateSearchIndex() throws Exception {
		searchIndexer.updateSearchIndex();
	}

	@Test
	@Transactional
	public void testSearchIndex() throws Exception {

		QueryBuilder qb = getFullTextEntityManager().getSearchFactory().buildQueryBuilder()
				.forEntity(DocumentContentData.class).get();
		org.apache.lucene.search.Query luceneQuery = qb.keyword().onFields("content").matching("programmering")
				.createQuery();

		// wrap Lucene query in a javax.persistence.Query
		javax.persistence.Query jpaQuery = getFullTextEntityManager().createFullTextQuery(luceneQuery,
				DocumentContentData.class);

		// execute search
		List<DocumentContentData> result = jpaQuery.setMaxResults(500).getResultList();
		assertTrue("expect some result",result.size()> 0);
	}

}
