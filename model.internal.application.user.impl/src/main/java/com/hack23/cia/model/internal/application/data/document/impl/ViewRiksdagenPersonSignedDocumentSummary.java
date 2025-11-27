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
package com.hack23.cia.model.internal.application.data.document.impl;

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
 * The Class ViewRiksdagenPersonSignedDocumentSummary.
 * 
 * JPA Entity for view_riksdagen_person_signed_document_summary database view.
 * 
 * Aggregates document signature counts by person, enabling analysis of
 * individual politician participation in legislative documents.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenPersonSignedDocumentSummary", propOrder = {
    "personId",
    "personName",
    "party",
    "total"
})
@Entity(name = "ViewRiksdagenPersonSignedDocumentSummary")
@Table(name = "view_riksdagen_person_signed_document_summary")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewRiksdagenPersonSignedDocumentSummary implements ModelObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The person id. */
    @XmlElement(name = "person_id", required = true)
    protected String personId;

    /** The person name. */
    @XmlElement(name = "person_name")
    protected String personName;

    /** The party. */
    @XmlElement
    protected String party;

    /** The total. */
    protected long total;

    /**
     * Gets the person id.
     *
     * @return the person id
     */
    @Id
    @Column(name = "PERSON_ID")
    public String getPersonId() {
        return personId;
    }

    /**
     * Sets the person id.
     *
     * @param value the new person id
     */
    public void setPersonId(String value) {
        this.personId = value;
    }

    /**
     * Gets the person name.
     *
     * @return the person name
     */
    @Basic
    @Column(name = "PERSON_NAME")
    public String getPersonName() {
        return personName;
    }

    /**
     * Sets the person name.
     *
     * @param value the new person name
     */
    public void setPersonName(String value) {
        this.personName = value;
    }

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
     * With person id.
     *
     * @param value the value
     * @return the view riksdagen person signed document summary
     */
    public ViewRiksdagenPersonSignedDocumentSummary withPersonId(String value) {
        setPersonId(value);
        return this;
    }

    /**
     * With person name.
     *
     * @param value the value
     * @return the view riksdagen person signed document summary
     */
    public ViewRiksdagenPersonSignedDocumentSummary withPersonName(String value) {
        setPersonName(value);
        return this;
    }

    /**
     * With party.
     *
     * @param value the value
     * @return the view riksdagen person signed document summary
     */
    public ViewRiksdagenPersonSignedDocumentSummary withParty(String value) {
        setParty(value);
        return this;
    }

    /**
     * With total.
     *
     * @param value the value
     * @return the view riksdagen person signed document summary
     */
    public ViewRiksdagenPersonSignedDocumentSummary withTotal(long value) {
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
