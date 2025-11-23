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
package com.hack23.cia.model.internal.application.data.ministry.impl;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hack23.cia.model.common.api.ModelObject;

/**
 * The Class ViewMinistryDecisionImpactEmbeddedId.
 * 
 * Composite key for ministry decision impact view.
 * 
 * @author intelligence-operative
 * @since v1.35 (Decision Intelligence)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewMinistryDecisionImpactEmbeddedId", propOrder = {
    "ministryCode",
    "committee",
    "decisionType",
    "decisionQuarter"
})
@Embeddable
public class ViewMinistryDecisionImpactEmbeddedId implements ModelObject {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "ministry_code", required = true)
	protected String ministryCode;

	@XmlElement(required = true)
	protected String committee;

	@XmlElement(name = "decision_type", required = true)
	protected String decisionType;

	@XmlElement(name = "decision_quarter", required = true)
	protected Date decisionQuarter;

	/**
	 * Gets the ministry code.
	 *
	 * @return the ministry code
	 */
	@Basic
	@Column(name = "MINISTRY_CODE")
	public String getMinistryCode() {
		return ministryCode;
	}

	/**
	 * Sets the ministry code.
	 *
	 * @param value the new ministry code
	 */
	public void setMinistryCode(final String value) {
		this.ministryCode = value;
	}

	/**
	 * Gets the committee.
	 *
	 * @return the committee
	 */
	@Basic
	@Column(name = "COMMITTEE")
	public String getCommittee() {
		return committee;
	}

	/**
	 * Sets the committee.
	 *
	 * @param value the new committee
	 */
	public void setCommittee(final String value) {
		this.committee = value;
	}

	/**
	 * Gets the decision type.
	 *
	 * @return the decision type
	 */
	@Basic
	@Column(name = "DECISION_TYPE")
	public String getDecisionType() {
		return decisionType;
	}

	/**
	 * Sets the decision type.
	 *
	 * @param value the new decision type
	 */
	public void setDecisionType(final String value) {
		this.decisionType = value;
	}

	/**
	 * Gets the decision quarter.
	 *
	 * @return the decision quarter
	 */
	@Basic
	@Column(name = "DECISION_QUARTER")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDecisionQuarter() {
		return decisionQuarter;
	}

	/**
	 * Sets the decision quarter.
	 *
	 * @param value the new decision quarter
	 */
	public void setDecisionQuarter(final Date value) {
		this.decisionQuarter = value;
	}

	/**
	 * With ministry code.
	 *
	 * @param value the value
	 * @return the embedded id
	 */
	public ViewMinistryDecisionImpactEmbeddedId withMinistryCode(final String value) {
		setMinistryCode(value);
		return this;
	}

	/**
	 * With committee.
	 *
	 * @param value the value
	 * @return the embedded id
	 */
	public ViewMinistryDecisionImpactEmbeddedId withCommittee(final String value) {
		setCommittee(value);
		return this;
	}

	/**
	 * With decision type.
	 *
	 * @param value the value
	 * @return the embedded id
	 */
	public ViewMinistryDecisionImpactEmbeddedId withDecisionType(final String value) {
		setDecisionType(value);
		return this;
	}

	/**
	 * With decision quarter.
	 *
	 * @param value the value
	 * @return the embedded id
	 */
	public ViewMinistryDecisionImpactEmbeddedId withDecisionQuarter(final Date value) {
		setDecisionQuarter(value);
		return this;
	}

	@Override
	public final String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public final boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public final int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
