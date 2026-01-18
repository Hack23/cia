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
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ViewRiksdagenPoliticianCareerPath10LevelEmbeddedId that = (ViewRiksdagenPoliticianCareerPath10LevelEmbeddedId) o;
		return Objects.equals(personId, that.personId) &&
				Objects.equals(fromDate, that.fromDate) &&
				Objects.equals(roleCode, that.roleCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(personId, fromDate, roleCode);
	}

	@Override
	public String toString() {
		return "ViewRiksdagenPoliticianCareerPath10LevelEmbeddedId{" +
				"personId='" + personId + '\'' +
				", fromDate=" + fromDate +
				", roleCode='" + roleCode + '\'' +
				'}';
	}
}
