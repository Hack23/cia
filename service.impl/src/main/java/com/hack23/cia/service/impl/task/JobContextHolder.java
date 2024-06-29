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

import com.hack23.cia.service.component.agent.api.DataAgentApi;

/**
 * The Interface JobContextHolder.
 */
public interface JobContextHolder {

	/**
	 * Gets the data agent api.
	 *
	 * @return the data agent api
	 */
	DataAgentApi getDataAgentApi();

	/**
	 * Update search index.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	void updateSearchIndex() throws InterruptedException;

	/**
	 * Refresh views.
	 */
	void refreshViews();

	/**
	 * Update rule violations.
	 */
	void updateRuleViolations();

}
