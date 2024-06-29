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
package com.hack23.cia.service.data.impl;

import org.springframework.stereotype.Repository;

import com.hack23.cia.model.internal.application.system.impl.Agency;
import com.hack23.cia.service.data.api.AgencyDAO;

/**
 * The Class AgencyDAOImpl.
 */
@Repository("AgencyDAOImpl")
final class AgencyDAOImpl extends AbstractGenericDAOImpl<Agency, Long> implements AgencyDAO {

	/**
	 * Instantiates a new agency dao impl.
	 */
	public AgencyDAOImpl() {
		super(Agency.class);
	}

}
