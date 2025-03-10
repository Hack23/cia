/*
 * Copyright 2010 -2025 James Pether Sörling
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
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.02.24 at 11:40:10 PM CET
//


package com.hack23.cia.model.external.riksdagen.utskottsforslag.impl;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hack23.cia.model.common.api.ModelObject;


/**
 * The Class CommitteeProposalComponentData.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommitteeProposalComponentData", propOrder = {
    "againstProposalContainer",
    "document",
    "committeeProposalContainer"
})
@Entity(name = "CommitteeProposalComponentData")
@Table(name = "COMMITTEE_PROPOSAL_COMPONENT_0")
@Inheritance(strategy = InheritanceType.JOINED)
public class CommitteeProposalComponentData
    implements ModelObject
{

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** The against proposal container. */
    @XmlElement(name = "dokmotforslag")
    protected AgainstProposalContainer againstProposalContainer;

    /** The document. */
    @XmlElement(name = "dokument")
    protected CommitteeDocumentData document;

    /** The committee proposal container. */
    @XmlElement(name = "dokutskottsforslag")
    protected CommitteeProposalContainer committeeProposalContainer;

    /** The hjid. */
    @XmlAttribute(name = "Hjid")
    protected Long hjid;

    /**
	 * Gets the against proposal container.
	 *
	 * @return the against proposal container
	 */
    @ManyToOne(targetEntity = AgainstProposalContainer.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "AGAINST_PROPOSAL_CONTAINER_C_0")
    public AgainstProposalContainer getAgainstProposalContainer() {
        return againstProposalContainer;
    }

    /**
	 * Sets the against proposal container.
	 *
	 * @param value the new against proposal container
	 */
    public void setAgainstProposalContainer(final AgainstProposalContainer value) {
        this.againstProposalContainer = value;
    }

    /**
	 * Gets the document.
	 *
	 * @return the document
	 */
    @ManyToOne(targetEntity = CommitteeDocumentData.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "DOCUMENT_COMMITTEE_PROPOSAL__0")
    public CommitteeDocumentData getDocument() {
        return document;
    }

    /**
	 * Sets the document.
	 *
	 * @param value the new document
	 */
    public void setDocument(final CommitteeDocumentData value) {
        this.document = value;
    }

    /**
	 * Gets the committee proposal container.
	 *
	 * @return the committee proposal container
	 */
    @ManyToOne(targetEntity = CommitteeProposalContainer.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "COMMITTEE_PROPOSAL_CONTAINER_0")
    public CommitteeProposalContainer getCommitteeProposalContainer() {
        return committeeProposalContainer;
    }

    /**
	 * Sets the committee proposal container.
	 *
	 * @param value the new committee proposal container
	 */
    public void setCommitteeProposalContainer(final CommitteeProposalContainer value) {
        this.committeeProposalContainer = value;
    }

    /**
	 * With against proposal container.
	 *
	 * @param value the value
	 * @return the committee proposal component data
	 */
    public CommitteeProposalComponentData withAgainstProposalContainer(final AgainstProposalContainer value) {
        setAgainstProposalContainer(value);
        return this;
    }

    /**
	 * With document.
	 *
	 * @param value the value
	 * @return the committee proposal component data
	 */
    public CommitteeProposalComponentData withDocument(final CommitteeDocumentData value) {
        setDocument(value);
        return this;
    }

    /**
	 * With committee proposal container.
	 *
	 * @param value the value
	 * @return the committee proposal component data
	 */
    public CommitteeProposalComponentData withCommitteeProposalContainer(final CommitteeProposalContainer value) {
        setCommitteeProposalContainer(value);
        return this;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}


    /**
	 * Gets the hjid.
	 *
	 * @return the hjid
	 */
    @Id
    @Column(name = "HJID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getHjid() {
        return hjid;
    }

    /**
	 * Sets the hjid.
	 *
	 * @param value the new hjid
	 */
    public void setHjid(final Long value) {
        this.hjid = value;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
	public boolean equals(final Object object) {
    	return EqualsBuilder.reflectionEquals(this,object,"hjid");
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
