/*
 * Copyright 2010-2024 James Pether Sörling
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
package com.hack23.cia.service.impl.task;

import org.junit.Test;
import org.mockito.Mockito;

import com.hack23.cia.service.component.agent.api.DataAgentApi;
import com.hack23.cia.service.data.api.SearchIndexer;
import com.hack23.cia.service.data.api.ViewDataManager;
import com.hack23.cia.service.impl.rules.RulesManager;
import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * The Class JobContextHolderImplTest.
 */
public class JobContextHolderImplTest extends AbstractUnitTest {

	/**
	 * Gets the data agent api test.
	 *
	 * @return the data agent api test
	 * @throws Exception the exception
	 */
	@Test
	public void getDataAgentApiTest() throws Exception {
		final JobContextHolderImpl jobContextHolderImpl = new JobContextHolderImpl(Mockito.mock(DataAgentApi.class),null,null,null);
		assertNotNull(jobContextHolderImpl.getDataAgentApi());
	}

	/**
	 * Update search index test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateSearchIndexTest() throws Exception {
		final SearchIndexer searchIndexer = Mockito.mock(SearchIndexer.class);
		final JobContextHolderImpl jobContextHolderImpl = new JobContextHolderImpl(null,searchIndexer,null,null);
		jobContextHolderImpl.updateSearchIndex();
		Mockito.verify(searchIndexer,Mockito.only()).updateSearchIndex();
	}

	/**
	 * Refresh views test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void refreshViewsTest() throws Exception {
		final ViewDataManager viewDataManager = Mockito.mock(ViewDataManager.class);
		final JobContextHolderImpl jobContextHolderImpl = new JobContextHolderImpl(null,null,viewDataManager,null);
		jobContextHolderImpl.refreshViews();
		Mockito.verify(viewDataManager,Mockito.only()).refreshViews();
	}

	/**
	 * Update rule violations test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateRuleViolationsTest() throws Exception {
		final RulesManager rulesManager = Mockito.mock(RulesManager.class);
		final JobContextHolderImpl jobContextHolderImpl = new JobContextHolderImpl(null,null,null,rulesManager);
		jobContextHolderImpl.updateRuleViolations();
		Mockito.verify(rulesManager,Mockito.only()).processService();
	}

}
