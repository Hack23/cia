//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.02.25 at 12:05:09 AM CET
//


package com.hack23.cia.model.internal.application.data.party.impl;


import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 * <p>Java class for ViewRiksdagenParty complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ViewRiksdagenParty"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="partyId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="partyNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="partyName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="headCount" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="website" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="registeredDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenParty", propOrder = {
    "partyId",
    "partyNumber",
    "partyName",
    "headCount",
    "website",
    "registeredDate"
})
@Entity(name = "ViewRiksdagenParty")
@Table(name = "VIEW_RIKSDAGEN_PARTY")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewRiksdagenParty
    implements ModelObject
{

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(required = true)
    protected String partyId;
    @XmlElement(required = true)
    protected String partyNumber;
    @XmlElement(required = true)
    protected String partyName;
    protected long headCount;
    @XmlElement(required = true)
    protected String website;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(XmlDateTypeAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date registeredDate;

    /**
     * Gets the value of the partyId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Id
    @Column(name = "PARTY_ID")
    public String getPartyId() {
        return partyId;
    }

    /**
     * Sets the value of the partyId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPartyId(final String value) {
        this.partyId = value;
    }

    /**
     * Gets the value of the partyNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "PARTY_NUMBER")
    public String getPartyNumber() {
        return partyNumber;
    }

    /**
     * Sets the value of the partyNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPartyNumber(final String value) {
        this.partyNumber = value;
    }

    /**
     * Gets the value of the partyName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "PARTY_NAME")
    public String getPartyName() {
        return partyName;
    }

    /**
     * Sets the value of the partyName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPartyName(final String value) {
        this.partyName = value;
    }

    /**
     * Gets the value of the headCount property.
     *
     */
    @Basic
    @Column(name = "HEAD_COUNT", precision = 20)
    public long getHeadCount() {
        return headCount;
    }

    /**
     * Sets the value of the headCount property.
     *
     */
    public void setHeadCount(final long value) {
        this.headCount = value;
    }

    /**
     * Gets the value of the website property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "WEBSITE")
    public String getWebsite() {
        return website;
    }

    /**
     * Sets the value of the website property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setWebsite(final String value) {
        this.website = value;
    }

    /**
     * Gets the value of the registeredDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "REGISTERED_DATE")
    @Temporal(TemporalType.DATE)
    public Date getRegisteredDate() {
        return registeredDate;
    }

    /**
     * Sets the value of the registeredDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRegisteredDate(final Date value) {
        this.registeredDate = value;
    }

    public ViewRiksdagenParty withPartyId(final String value) {
        setPartyId(value);
        return this;
    }

    public ViewRiksdagenParty withPartyNumber(final String value) {
        setPartyNumber(value);
        return this;
    }

    public ViewRiksdagenParty withPartyName(final String value) {
        setPartyName(value);
        return this;
    }

    public ViewRiksdagenParty withHeadCount(final long value) {
        setHeadCount(value);
        return this;
    }

    public ViewRiksdagenParty withWebsite(final String value) {
        setWebsite(value);
        return this;
    }

    public ViewRiksdagenParty withRegisteredDate(final Date value) {
        setRegisteredDate(value);
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
