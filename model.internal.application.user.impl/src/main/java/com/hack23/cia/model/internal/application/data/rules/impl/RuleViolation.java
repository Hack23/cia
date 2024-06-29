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
package com.hack23.cia.model.internal.application.data.rules.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class RuleViolation.
 */
@Entity(name = "RuleViolation")
@Table(name = "RULE_VIOLATION")
public class RuleViolation implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

    private Date detectedDate;

	/** The id. */
	private String referenceId;

	/** The name. */
	private String name;

	/** The resource type. */
	private ResourceType resourceType;

	/** The rule name. */
	private String ruleName;

	/** The rule description. */
	private String ruleDescription;

	/** The rule group. */
	private String ruleGroup;

	/** The status. */
	private Status status;

	/** The positive. */
	private String positive;

	/**
	 * Instantiates a new rule violation.
	 */
	public RuleViolation() {

	}

	/**
	 * Instantiates a new rule violation.
	 *
	 * @param referenceId the reference id
	 * @param name            the name
	 * @param resourceType            the resource type
	 * @param ruleName            the rule name
	 * @param ruleDescription            the rule description
	 * @param ruleGroup            the rule group
	 * @param status            the status
	 * @param positive            the positive
	 */
	public RuleViolation(final String referenceId, final String name, final ResourceType resourceType, final String ruleName,
			final String ruleDescription, final String ruleGroup, final Status status, final String positive) {
		super();
		this.referenceId = referenceId;
		this.name = name;
		this.resourceType = resourceType;
		this.ruleName = ruleName;
		this.ruleDescription = ruleDescription;
		this.ruleGroup = ruleGroup;
		this.status = status;
		this.positive = positive;
		this.detectedDate = new Date();
	}



	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Gets the detected date.
	 *
	 * @return the detected date
	 */
    @Basic
    @Column(name = "detected_date")
    @Temporal(TemporalType.DATE)
	public Date getDetectedDate() {
		return new Date(detectedDate.getTime());
	}

	/**
	 * Sets the detected date.
	 *
	 * @param detectedDate the new detected date
	 */
	public void setDetectedDate(final Date detectedDate) {
		this.detectedDate = new Date(detectedDate.getTime());
	}

	/**
	 * Gets the reference id.
	 *
	 * @return the reference id
	 */
    @Basic
    @Column(name = "reference_id")
	public String getReferenceId() {
		return referenceId;
	}

	/**
	 * Sets the reference id.
	 *
	 * @param referenceId the new reference id
	 */
	public void setReferenceId(final String referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
    @Basic
    @Column(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Gets the resource type.
	 *
	 * @return the resource type
	 */
	@Enumerated(EnumType.STRING)
    @Column(name = "resource_type")
	public ResourceType getResourceType() {
		return resourceType;
	}

	/**
	 * Sets the resource type.
	 *
	 * @param resourceType the new resource type
	 */
	public void setResourceType(final ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * Gets the rule name.
	 *
	 * @return the rule name
	 */
    @Basic
    @Column(name = "rule_name")
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * Sets the rule name.
	 *
	 * @param ruleName the new rule name
	 */
	public void setRuleName(final String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * Gets the rule description.
	 *
	 * @return the rule description
	 */
    @Basic
    @Column(name = "rule_description")
	public String getRuleDescription() {
		return ruleDescription;
	}

	/**
	 * Sets the rule description.
	 *
	 * @param ruleDescription the new rule description
	 */
	public void setRuleDescription(final String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}

	/**
	 * Gets the rule group.
	 *
	 * @return the rule group
	 */
    @Basic
    @Column(name = "rule_group")
	public String getRuleGroup() {
		return ruleGroup;
	}

	/**
	 * Sets the rule group.
	 *
	 * @param ruleGroup the new rule group
	 */
	public void setRuleGroup(final String ruleGroup) {
		this.ruleGroup = ruleGroup;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	@Enumerated(EnumType.STRING)
    @Column(name = "status")
	public Status getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(final Status status) {
		this.status = status;
	}

	/**
	 * Gets the positive.
	 *
	 * @return the positive
	 */
    @Basic
    @Column(name = "positive")
	public String getPositive() {
		return positive;
	}

	/**
	 * Sets the positive.
	 *
	 * @param positive the new positive
	 */
	public void setPositive(final String positive) {
		this.positive = positive;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
