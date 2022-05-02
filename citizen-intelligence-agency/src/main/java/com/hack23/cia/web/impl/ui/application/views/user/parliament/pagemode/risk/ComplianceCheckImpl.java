package com.hack23.cia.web.impl.ui.application.views.user.parliament.pagemode.risk;

import java.util.ArrayList;
import java.util.List;

import com.hack23.cia.model.internal.application.data.rules.impl.ResourceType;
import com.hack23.cia.model.internal.application.data.rules.impl.RuleViolation;
import com.hack23.cia.service.api.action.kpi.ComplianceCheck;

/**
 * The Class ComplianceCheckImpl.
 */
public class ComplianceCheckImpl implements ComplianceCheck {

	private static final long serialVersionUID = 1L;
	private final List<RuleViolation> ruleViolations;
	private final ResourceType resourceType;
	private final String id;
	private final String name;

	/**
	 * Instantiates a new compliance check impl.
	 *
	 * @param ruleViolations the rule violations
	 */
	public ComplianceCheckImpl(final List<RuleViolation> ruleViolations) {
		this.ruleViolations = new ArrayList<>(ruleViolations);
		this.resourceType = ruleViolations.get(0).getResourceType();
		this.id = ruleViolations.get(0).getReferenceId();
		this.name = ruleViolations.get(0).getName();
	}

	@Override
	public ResourceType getResourceType() {
		return resourceType;
	}

	@Override
	public List<RuleViolation> getRuleViolations() {
		return new ArrayList<>(ruleViolations);
	}


	/**
	 * Gets the number rule violations.
	 *
	 * @return the number rule violations
	 */
	public int getNumberRuleViolations() {
		return ruleViolations.size();
	}

	/**
	 * Gets the rule summary.
	 *
	 * @return the rule summary
	 */
	public String getRuleSummary() {
		final StringBuilder builder = new StringBuilder();
		for (final RuleViolation ruleViolation : ruleViolations) {
			builder.append('[').append(ruleViolation.getRuleName()).append('/').append(ruleViolation.getStatus()) .append(']');
		}
		return builder.toString();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
}