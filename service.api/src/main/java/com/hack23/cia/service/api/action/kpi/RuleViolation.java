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
package com.hack23.cia.service.api.action.kpi;

/**
 * The Class RuleViolation.
 */
public final class RuleViolation {

	/** The rule name. */
	private String ruleName;

	/** The rule description. */
	private String ruleDescription;	

	/** The rule group. */
	private String ruleGroup;
	
	/** The status. */
	private Status status = Status.OK;

	/**
	 * Instantiates a new rule violation.
	 *
	 * @param ruleName
	 *            the rule name
	 * @param ruleDescription
	 *            the rule description
	 * @param ruleGroup
	 *            the rule group
	 * @param status
	 *            the status
	 */
	public RuleViolation(final String ruleName, final String ruleDescription, final String ruleGroup, final Status status) {
		super();
		this.ruleName = ruleName;
		this.ruleDescription = ruleDescription;
		this.ruleGroup = ruleGroup;
		this.status = status;
	}

	/**
	 * Gets the rule name.
	 *
	 * @return the rule name
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * Gets the rule description.
	 *
	 * @return the rule description
	 */
	public String getRuleDescription() {
		return ruleDescription;
	}

	/**
	 * Gets the rule group.
	 *
	 * @return the rule group
	 */
	public String getRuleGroup() {
		return ruleGroup;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	
}
