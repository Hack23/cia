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
package com.hack23.cia.service.api.action.kpi;

import java.io.Serializable;
import java.util.List;

import com.hack23.cia.model.internal.application.data.rules.impl.ResourceType;
import com.hack23.cia.model.internal.application.data.rules.impl.RuleViolation;

/**
 * The Interface ComplianceCheck.
 */
public interface ComplianceCheck extends Serializable {

	/**
	 * Gets the resource type.
	 *
	 * @return the resource type
	 */
	ResourceType getResourceType();

	/**
	 * Gets the rule violations.
	 *
	 * @return the rule violations
	 */
	List<RuleViolation> getRuleViolations();

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	String getId();

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String getName();
}
