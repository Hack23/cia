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
package com.hack23.cia.service.impl.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hack23.cia.model.internal.application.data.rules.impl.ResourceType;
import com.hack23.cia.model.internal.application.data.rules.impl.RuleViolation;
import com.hack23.cia.model.internal.application.data.rules.impl.Status;
import com.hack23.cia.service.api.action.kpi.ComplianceCheck;

/**
 * The Class AbstractComplianceCheckImpl.
 */
public abstract class AbstractComplianceCheckImpl implements ComplianceCheck {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The resource type. */
	private final ResourceType resourceType;

	/** The rule violation map. */
	private final Map<String,RuleViolation> ruleViolationMap = new HashMap<>();

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


	/**
	 * Gets the resource type.
	 *
	 * @return the resource type
	 */
	@Override
	public ResourceType getResourceType() {
		return resourceType;
	}

	/**
	 * Gets the rule violations.
	 *
	 * @return the rule violations
	 */
	@Override
	public final List<RuleViolation> getRuleViolations() {
		return new ArrayList<>(ruleViolationMap.values());
	}

	/**
	 * Adds the violation.
	 *
	 * @param status
	 *            the status
	 * @param ruleName
	 *            the rule name
	 * @param ruleGroup
	 *            the rule group
	 * @param ruleDescription
	 *            the rule description
	 * @param positive
	 *            the positive
	 */
	public final void addViolation(final Status status,final String ruleName,final String ruleGroup,final String ruleDescription,final String positive) {
		final RuleViolation currentRuleViolation = ruleViolationMap.get(ruleName);
		if (currentRuleViolation == null || status.ordinal() > currentRuleViolation.getStatus().ordinal()) {
			ruleViolationMap.put(ruleName, new RuleViolation(getId(),getName(),resourceType,ruleName,ruleDescription,ruleGroup,status,positive));
		}
	}

	@Override
	public final boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public final String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public final int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
