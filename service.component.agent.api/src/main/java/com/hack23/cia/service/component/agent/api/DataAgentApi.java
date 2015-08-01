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
 *	$Id: DataAgentApi.java 6046 2015-05-06 20:42:53Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/service.component.agent.api/src/main/java/com/hack23/cia/service/component/agent/api/DataAgentApi.java $
 */
package com.hack23.cia.service.component.agent.api;

import com.hack23.cia.model.internal.application.data.impl.DataAgentWorkOrder;

/**
 * The Interface DataAgentApi.
 */
public interface DataAgentApi {

	/**
	 * Execute.
	 *
	 * @param workOrder
	 *            the work order
	 */
	void execute(DataAgentWorkOrder workOrder);
}
