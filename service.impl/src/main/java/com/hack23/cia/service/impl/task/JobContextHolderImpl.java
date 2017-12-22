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
package com.hack23.cia.service.impl.task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.service.component.agent.api.DataAgentApi;
import com.hack23.cia.service.data.api.SearchIndexer;
import com.hack23.cia.service.data.api.ViewDataManager;

/**
 * The Class JobContextHolder.
 */
@Service("JobContextHolder")
@Transactional(propagation = Propagation.REQUIRED,timeout=1200)
public final class JobContextHolderImpl implements JobContextHolder {

	/** The data agent api. */
	private static DataAgentApi dataAgentApi;

	/** The search indexer. */
	private static SearchIndexer searchIndexer;

	/** The view data manager. */
	private static ViewDataManager viewDataManager;

	/**
	 * Instantiates a new job context holder impl.
	 *
	 * @param dataAgentApi
	 *            the data agent api
	 * @param searchIndexer
	 *            the search indexer
	 * @param viewDataManager
	 *            the view data manager
	 */
	@Autowired
	public JobContextHolderImpl(final DataAgentApi dataAgentApi,final SearchIndexer searchIndexer, final ViewDataManager viewDataManager) {
		super();
		JobContextHolderImpl.dataAgentApi = dataAgentApi;
		JobContextHolderImpl.searchIndexer = searchIndexer;
		JobContextHolderImpl.viewDataManager = viewDataManager;
	}


	@Override
	public DataAgentApi getDataAgentApi() {
		return JobContextHolderImpl.dataAgentApi;
	}

	@Override
	public void updateSearchIndex() throws InterruptedException {
		JobContextHolderImpl.searchIndexer.updateSearchIndex();
	}

	@Override
	public void refreshViews() {
		JobContextHolderImpl.viewDataManager.refreshViews();
	}

}
