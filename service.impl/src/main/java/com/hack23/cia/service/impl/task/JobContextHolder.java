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

import com.hack23.cia.service.component.agent.api.DataAgentApi;
import com.hack23.cia.service.data.api.SearchIndexer;
import com.hack23.cia.service.data.api.ViewDataManager;

/**
 * The Class JobContextHolder.
 */
@Service("JobContextHolder")
public final class JobContextHolder {

	/** The data agent api. */
	private static DataAgentApi dataAgentApi;

	/** The search indexer. */
	private static SearchIndexer searchIndexer;

	/** The view data manager. */
	private static ViewDataManager viewDataManager;

	@Autowired
	public JobContextHolder(final DataAgentApi dataAgentApi,final SearchIndexer searchIndexer, final ViewDataManager viewDataManager) {
		super();
		JobContextHolder.dataAgentApi = dataAgentApi;
		JobContextHolder.searchIndexer = searchIndexer;
		JobContextHolder.viewDataManager = viewDataManager;
	}


	/**
	 * Gets the data agent api.
	 *
	 * @return the data agent api
	 */
	public static DataAgentApi getDataAgentApi() {
		return dataAgentApi;
	}

	/**
	 * Gets the search indexer.
	 *
	 * @return the search indexer
	 */
	public static SearchIndexer getSearchIndexer() {
		return searchIndexer;
	}

	/**
	 * Gets the view data manager.
	 *
	 * @return the view data manager
	 */
	public static ViewDataManager getViewDataManager() {
		return viewDataManager;
	}

}
