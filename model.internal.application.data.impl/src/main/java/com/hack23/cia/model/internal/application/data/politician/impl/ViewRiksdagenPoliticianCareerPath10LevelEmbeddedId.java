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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class ViewRiksdagenPoliticianCareerPath10LevelEmbeddedId.
 * Composite primary key for the 10-level career path view.
 */
@Embeddable
public class ViewRiksdagenPoliticianCareerPath10LevelEmbeddedId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "person_id", nullable = false)
	private String personId;

	@Column(name = "from_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fromDate;

	@Column(name = "role_code", nullable = false)
	private String roleCode;

	/**
	 * Instantiates a new view riksdagen politician career path 10 level embedded id.
	 */
	public ViewRiksdagenPoliticianCareerPath10LevelEmbeddedId() {
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
	 * Gets the from date.
	 *
	 * @return the from date
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * Sets the from date.
	 *
	 * @param fromDate the new from date
	 */
	public void setFromDate(final Date fromDate) {
		this.fromDate = fromDate;
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

	@Override
	public boolean equals(Object obj) {
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
