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
// Generated on: 2019.02.24 at 11:39:47 PM CET
//


package com.hack23.cia.model.external.val.partier.impl;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hack23.cia.model.common.api.ModelObject;
import com.hack23.cia.model.common.impl.xml.XmlDateTypeAdapter;


/**
 * The Class SwedenPoliticalParty.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SwedenPoliticalParty", propOrder = {
    "partyName",
    "shortCode",
    "partyId",
    "coAddress",
    "phoneNumber",
    "address",
    "faxNumber",
    "postCode",
    "city",
    "email",
    "website",
    "registeredDate"
})
@Entity(name = "SwedenPoliticalParty")
@Table(name = "SWEDEN_POLITICAL_PARTY")
@Inheritance(strategy = InheritanceType.JOINED)
public class SwedenPoliticalParty
    implements ModelObject
{

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** The party name. */
    @XmlElement(name = "partibeteckning", required = true)
    protected String partyName;

    /** The short code. */
    @XmlElement(name = "forkortning", required = true)
    protected String shortCode;

    /** The party id. */
    @XmlElement(name = "id_parti", required = true)
    protected String partyId;

    /** The co address. */
    @XmlElement(name = "co_adress", required = true)
    protected String coAddress;

    /** The phone number. */
    @XmlElement(name = "telefon", required = true)
    protected String phoneNumber;

    /** The address. */
    @XmlElement(name = "gatuadress", required = true)
    protected String address;

    /** The fax number. */
    @XmlElement(name = "telefax", required = true)
    protected String faxNumber;

    /** The post code. */
    @XmlElement(name = "postnummer", required = true)
    protected String postCode;

    /** The city. */
    @XmlElement(name = "postort", required = true)
    protected String city;

    /** The email. */
    @XmlElement(name = "epost", required = true)
    protected String email;

    /** The website. */
    @XmlElement(name = "webbplats", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String website;

    /** The registered date. */
    @XmlElement(name = "registreringsdatum", required = true, type = String.class)
    @XmlJavaTypeAdapter(XmlDateTypeAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date registeredDate;

    /** The hjid. */
    @XmlAttribute(name = "Hjid")
    protected Long hjid;

    /**
	 * Gets the party name.
	 *
	 * @return the party name
	 */
    @Basic
    @Column(name = "PARTY_NAME")
    public String getPartyName() {
        return partyName;
    }

    /**
	 * Sets the party name.
	 *
	 * @param value the new party name
	 */
    public void setPartyName(final String value) {
        this.partyName = value;
    }

    /**
	 * Gets the short code.
	 *
	 * @return the short code
	 */
    @Basic
    @Column(name = "SHORT_CODE")
    public String getShortCode() {
        return shortCode;
    }

    /**
	 * Sets the short code.
	 *
	 * @param value the new short code
	 */
    public void setShortCode(final String value) {
        this.shortCode = value;
    }

    /**
	 * Gets the party id.
	 *
	 * @return the party id
	 */
    @Basic
    @Column(name = "PARTY_ID")
    public String getPartyId() {
        return partyId;
    }

    /**
	 * Sets the party id.
	 *
	 * @param value the new party id
	 */
    public void setPartyId(final String value) {
        this.partyId = value;
    }

    /**
	 * Gets the co address.
	 *
	 * @return the co address
	 */
    @Basic
    @Column(name = "CO_ADDRESS")
    public String getCoAddress() {
        return coAddress;
    }

    /**
	 * Sets the co address.
	 *
	 * @param value the new co address
	 */
    public void setCoAddress(final String value) {
        this.coAddress = value;
    }

    /**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
    @Basic
    @Column(name = "PHONE_NUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
	 * Sets the phone number.
	 *
	 * @param value the new phone number
	 */
    public void setPhoneNumber(final String value) {
        this.phoneNumber = value;
    }

    /**
	 * Gets the address.
	 *
	 * @return the address
	 */
    @Basic
    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    /**
	 * Sets the address.
	 *
	 * @param value the new address
	 */
    public void setAddress(final String value) {
        this.address = value;
    }

    /**
	 * Gets the fax number.
	 *
	 * @return the fax number
	 */
    @Basic
    @Column(name = "FAX_NUMBER")
    public String getFaxNumber() {
        return faxNumber;
    }

    /**
	 * Sets the fax number.
	 *
	 * @param value the new fax number
	 */
    public void setFaxNumber(final String value) {
        this.faxNumber = value;
    }

    /**
	 * Gets the post code.
	 *
	 * @return the post code
	 */
    @Basic
    @Column(name = "POST_CODE")
    public String getPostCode() {
        return postCode;
    }

    /**
	 * Sets the post code.
	 *
	 * @param value the new post code
	 */
    public void setPostCode(final String value) {
        this.postCode = value;
    }

    /**
	 * Gets the city.
	 *
	 * @return the city
	 */
    @Basic
    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    /**
	 * Sets the city.
	 *
	 * @param value the new city
	 */
    public void setCity(final String value) {
        this.city = value;
    }

    /**
	 * Gets the email.
	 *
	 * @return the email
	 */
    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    /**
	 * Sets the email.
	 *
	 * @param value the new email
	 */
    public void setEmail(final String value) {
        this.email = value;
    }

    /**
	 * Gets the website.
	 *
	 * @return the website
	 */
    @Basic
    @Column(name = "WEBSITE")
    public String getWebsite() {
        return website;
    }

    /**
	 * Sets the website.
	 *
	 * @param value the new website
	 */
    public void setWebsite(final String value) {
        this.website = value;
    }

    /**
	 * Gets the registered date.
	 *
	 * @return the registered date
	 */
    @Basic
    @Column(name = "REGISTERED_DATE")
    @Temporal(TemporalType.DATE)
    public Date getRegisteredDate() {
        return registeredDate;
    }

    /**
	 * Sets the registered date.
	 *
	 * @param value the new registered date
	 */
    public void setRegisteredDate(final Date value) {
        this.registeredDate = value;
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

	@Override
	public final boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public final String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public final int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
