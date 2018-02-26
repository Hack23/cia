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
package com.hack23.cia.service.impl.rules;

import com.hack23.cia.service.api.action.kpi.ComplianceCheck;
import com.hack23.cia.service.api.action.kpi.ResourceType;
import com.hack23.cia.service.api.action.kpi.Status;

/**
 * The Class AbstractComplianceCheckImpl.
 */
public abstract class AbstractComplianceCheckImpl implements ComplianceCheck {

	/** The resource type. */
	private final ResourceType resourceType;
	
	/** The rule name. */
	private String ruleName;

	/** The rule description. */
	private String ruleDescription;	

	/** The status. */
	private Status status = Status.OK;
	
	/**
	 * Instantiates a new abstract compliance check impl.
	 *
	 * @param resourceType
	 *            the resource type
	 */
	public AbstractComplianceCheckImpl(final ResourceType resourceType) {
		super();
		this.resourceType = resourceType;
	}

	@Override
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * Sets the rule name.
	 *
	 * @param ruleName
	 *            the new rule name
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	@Override
	public String getRuleDescription() {
		return ruleDescription;
	}

	/**
	 * Sets the rule description.
	 *
	 * @param ruleDescription
	 *            the new rule description
	 */
	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public ResourceType getResourceType() {
		return resourceType;
	}

	@Override
	public Status getStatus() {
		return status;
	}
}
