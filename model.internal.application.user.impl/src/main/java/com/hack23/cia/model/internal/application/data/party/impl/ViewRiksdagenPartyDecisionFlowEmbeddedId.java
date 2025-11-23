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
package com.hack23.cia.model.internal.application.data.party.impl;

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
 * The Class ViewRiksdagenPartyDecisionFlowEmbeddedId.
 * 
 * Composite key for party decision flow view.
 * 
 * @author intelligence-operative
 * @since v1.35 (Decision Intelligence)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenPartyDecisionFlowEmbeddedId", propOrder = {
    "party",
    "committee",
    "decisionType",
    "decisionMonth"
})
@Embeddable
public class ViewRiksdagenPartyDecisionFlowEmbeddedId implements ModelObject {

	private static final long serialVersionUID = 1L;

	@XmlElement(required = true)
	protected String party;

	@XmlElement(required = true)
	protected String committee;

	@XmlElement(name = "decision_type", required = true)
	protected String decisionType;

	@XmlElement(name = "decision_month", required = true)
	protected Date decisionMonth;

	/**
	 * Gets the party.
	 *
	 * @return the party
	 */
	@Basic
	@Column(name = "PARTY")
	public String getParty() {
		return party;
	}

	/**
	 * Sets the party.
	 *
	 * @param value the new party
	 */
	public void setParty(final String value) {
		this.party = value;
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
	 * Gets the decision month.
	 *
	 * @return the decision month
	 */
	@Basic
	@Column(name = "DECISION_MONTH")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDecisionMonth() {
		return decisionMonth;
	}

	/**
	 * Sets the decision month.
	 *
	 * @param value the new decision month
	 */
	public void setDecisionMonth(final Date value) {
		this.decisionMonth = value;
	}

	/**
	 * With party.
	 *
	 * @param value the value
	 * @return the embedded id
	 */
	public ViewRiksdagenPartyDecisionFlowEmbeddedId withParty(final String value) {
		setParty(value);
		return this;
	}

	/**
	 * With committee.
	 *
	 * @param value the value
	 * @return the embedded id
	 */
	public ViewRiksdagenPartyDecisionFlowEmbeddedId withCommittee(final String value) {
		setCommittee(value);
		return this;
	}

	/**
	 * With decision type.
	 *
	 * @param value the value
	 * @return the embedded id
	 */
	public ViewRiksdagenPartyDecisionFlowEmbeddedId withDecisionType(final String value) {
		setDecisionType(value);
		return this;
	}

	/**
	 * With decision month.
	 *
	 * @param value the value
	 * @return the embedded id
	 */
	public ViewRiksdagenPartyDecisionFlowEmbeddedId withDecisionMonth(final Date value) {
		setDecisionMonth(value);
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
