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
package com.hack23.cia.service.data.impl;

import org.springframework.stereotype.Repository;

import com.hack23.cia.model.internal.application.data.rules.impl.RuleViolation;
import com.hack23.cia.service.data.api.RuleViolationDAO;

/**
 * The Class RuleViolationDAOImpl.
 */
@Repository("RuleViolationDAO")
final class RuleViolationDAOImpl extends AbstractGenericDAOImpl<RuleViolation, Long> implements RuleViolationDAO {

	/**
	 * Instantiates a new rule violation DAO impl.
	 */
	public RuleViolationDAOImpl() {
		super(RuleViolation.class);
	}

}
