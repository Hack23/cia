/*
 * Copyright 2010-2025 James Pether Sörling
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
package com.hack23.cia.service.data.api;

import java.util.List;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewMinistryDecisionImpact;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewMinistryDecisionImpactEmbeddedId;

/**
 * The Interface ViewMinistryDecisionImpactDAO.
 * 
 * Data access layer for ministry proposal success and effectiveness analysis.
 * 
 * @author intelligence-operative
 * @since v1.35 (Decision Intelligence)
 */
public interface ViewMinistryDecisionImpactDAO
		extends
		AbstractGenericDAO<ViewMinistryDecisionImpact, ViewMinistryDecisionImpactEmbeddedId> {

	/**
	 * Gets the id list.
	 *
	 * @return the id list
	 */
	List<ViewMinistryDecisionImpactEmbeddedId> getIdList();
}
