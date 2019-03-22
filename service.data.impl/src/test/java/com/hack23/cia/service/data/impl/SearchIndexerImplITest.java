/*
 * Copyright 2010-2019 James Pether Sörling
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

import java.io.File;
import java.util.List;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.io.FileUtils;
import org.hibernate.search.mapper.orm.Search;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.search.engine.search.SearchPredicate;
import org.hibernate.search.engine.search.dsl.predicate.SearchPredicateFactoryContext;
import org.hibernate.search.engine.search.dsl.predicate.SearchPredicateTerminalContext;
import org.hibernate.search.engine.search.dsl.query.SearchQueryContext;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.search.dsl.query.SearchQueryResultDefinitionContext;
import org.hibernate.search.mapper.orm.search.query.SearchQuery;


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

	@Value("${database.search.index.location}")
	private String databaseSearchIndexLocation;

	/**
	 * Test update search index.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	@Transactional(timeout=900)
	@Ignore
	public void testCreateSearchIndex() throws Exception {
		FileUtils.deleteDirectory(new File(databaseSearchIndexLocation));
		searchIndexer.updateSearchIndex();
		assertTrue("Expect index to have been created",new File(databaseSearchIndexLocation).exists());
	}

	@Test
	@Transactional(timeout=30)
	@Ignore
	public void testSearchIndex() throws Exception {
		final SearchQueryResultDefinitionContext<DocumentContentData> queryResult = Search.getSearchSession(entityManager).search(DocumentContentData.class);
		final SearchQueryContext<? extends SearchQuery<DocumentContentData>> query = queryResult.asEntity()
	        .predicate((Function<? super SearchPredicateFactoryContext, SearchPredicateTerminalContext>) t -> t.match().onFields("content").matching("programmering"));        

		
		final List<DocumentContentData> result = query.toQuery().fetch(500).getHits();
		assertTrue("expect some result",result.size()> 0);
	}

}