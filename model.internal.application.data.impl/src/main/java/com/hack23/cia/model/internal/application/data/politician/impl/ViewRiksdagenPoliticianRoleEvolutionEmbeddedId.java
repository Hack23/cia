/*
 * Copyright 2010-2026 James Pether Sörling
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
package com.hack23.cia.model.internal.application.data.politician.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class ViewRiksdagenPoliticianRoleEvolutionEmbeddedId.
 * Composite key for politician role evolution.
 */
@Embeddable
public class ViewRiksdagenPoliticianRoleEvolutionEmbeddedId implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The person id. */
	@Column(name = "person_id", nullable = false)
	private String personId;

	/** The role code. */
	@Column(name = "role_code", nullable = false)
	private String roleCode;

	/** The status. */
	@Column(name = "status")
	private String status;

	/** The assignment type. */
	@Column(name = "assignment_type")
	private String assignmentType;

	/** The org code. */
	@Column(name = "org_code")
	private String orgCode;

	/** The role tier. */
	@Column(name = "role_tier")
	private String roleTier;

	/** The role weight. */
	@Column(name = "role_weight")
	private Integer roleWeight;

	/**
	 * Instantiates a new view riksdagen politician role evolution embedded id.
	 */
	public ViewRiksdagenPoliticianRoleEvolutionEmbeddedId() {
		super();
	}

	/**
	 * Gets the person id.
	 *
	 * @return the person id
	 */
	public String getPersonId() {
		return personId;
	}

	/**
	 * Sets the person id.
	 *
	 * @param personId the new person id
	 */
	public void setPersonId(final String personId) {
		this.personId = personId;
	}

	/**
	 * Gets the role code.
	 *
	 * @return the role code
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * Sets the role code.
	 *
	 * @param roleCode the new role code
	 */
	public void setRoleCode(final String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * Gets the assignment type.
	 *
	 * @return the assignment type
	 */
	public String getAssignmentType() {
		return assignmentType;
	}

	/**
	 * Sets the assignment type.
	 *
	 * @param assignmentType the new assignment type
	 */
	public void setAssignmentType(final String assignmentType) {
		this.assignmentType = assignmentType;
	}

	/**
	 * Gets the org code.
	 *
	 * @return the org code
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * Sets the org code.
	 *
	 * @param orgCode the new org code
	 */
	public void setOrgCode(final String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * Gets the role tier.
	 *
	 * @return the role tier
	 */
	public String getRoleTier() {
		return roleTier;
	}

	/**
	 * Sets the role tier.
	 *
	 * @param roleTier the new role tier
	 */
	public void setRoleTier(final String roleTier) {
		this.roleTier = roleTier;
	}

	/**
	 * Gets the role weight.
	 *
	 * @return the role weight
	 */
	public Integer getRoleWeight() {
		return roleWeight;
	}

	/**
	 * Sets the role weight.
	 *
	 * @param roleWeight the new role weight
	 */
	public void setRoleWeight(final Integer roleWeight) {
		this.roleWeight = roleWeight;
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
