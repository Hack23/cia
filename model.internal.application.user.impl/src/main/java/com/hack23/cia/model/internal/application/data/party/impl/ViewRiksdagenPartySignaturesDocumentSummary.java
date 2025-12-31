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

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
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
 * The Class ViewRiksdagenPartySignaturesDocumentSummary.
 * 
 * JPA Entity for view_riksdagen_party_signatures_document_summary database view.
 * 
 * Aggregates document signature counts by party, enabling analysis of party
 * participation in legislative documents and collaborative efforts.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenPartySignaturesDocumentSummary", propOrder = {
    "party",
    "total"
})
@Entity(name = "ViewRiksdagenPartySignaturesDocumentSummary")
@Table(name = "view_riksdagen_party_signatures_document_summary")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewRiksdagenPartySignaturesDocumentSummary implements ModelObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The party. */
    @XmlElement(required = true)
    protected String party;

    /** The total. */
    protected long total;

    /**
     * Gets the party.
     *
     * @return the party
     */
    @Id
    @Column(name = "PARTY")
    public String getParty() {
        return party;
    }

    /**
     * Sets the party.
     *
     * @param value the new party
     */
    public void setParty(String value) {
        this.party = value;
    }

    /**
     * Gets the total.
     *
     * @return the total
     */
    @Basic
    @Column(name = "TOTAL")
    public long getTotal() {
        return total;
    }

    /**
     * Sets the total.
     *
     * @param value the new total
     */
    public void setTotal(long value) {
        this.total = value;
    }

    /**
     * With party.
     *
     * @param value the value
     * @return the view riksdagen party signatures document summary
     */
    public ViewRiksdagenPartySignaturesDocumentSummary withParty(String value) {
        setParty(value);
        return this;
    }

    /**
     * With total.
     *
     * @param value the value
     * @return the view riksdagen party signatures document summary
     */
    public ViewRiksdagenPartySignaturesDocumentSummary withTotal(long value) {
        setTotal(value);
        return this;
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
     * Equals.
     *
     * @param obj the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
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
