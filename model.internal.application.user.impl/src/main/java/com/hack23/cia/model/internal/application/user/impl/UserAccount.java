//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.02.25 at 12:05:09 AM CET
//


package com.hack23.cia.model.internal.application.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
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
import com.hack23.cia.model.common.impl.xml.XmlDateTimeTypeAdapter;


/**
 * <p>Java class for UserAccount complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="UserAccount"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="modelObjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="modelObjectVersion" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userpassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numberOfVisits" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="userType" type="{http://user.application.internal.model.cia.hack23.com/impl}UserType" minOccurs="0"/&gt;
 *         &lt;element name="userRole" type="{http://user.application.internal.model.cia.hack23.com/impl}UserRole" minOccurs="0"/&gt;
 *         &lt;element name="userLockStatus" type="{http://user.application.internal.model.cia.hack23.com/impl}UserLockStatus" minOccurs="0"/&gt;
 *         &lt;element name="userEmailStatus" type="{http://user.application.internal.model.cia.hack23.com/impl}UserEmailStatus" minOccurs="0"/&gt;
 *         &lt;element name="createdDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserAccount", propOrder = {
    "modelObjectId",
    "modelObjectVersion",
    "country",
    "username",
    "email",
    "userId",
    "userpassword",
    "numberOfVisits",
    "address",
    "userType",
    "userRole",
    "userLockStatus",
    "userEmailStatus",
    "createdDate"
})
@Entity(name = "UserAccount")
@Table(name = "USER_ACCOUNT")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserAccount
    implements ModelObject
{

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected Integer modelObjectId;
    protected int modelObjectVersion;
    protected String country;
    protected String username;
    protected String email;
    protected String userId;
    protected String userpassword;
    protected Integer numberOfVisits;
    @XmlElement(nillable = true)
    protected List<String> address;
    @XmlSchemaType(name = "string")
    protected UserType userType;
    @XmlSchemaType(name = "string")
    protected UserRole userRole;
    @XmlSchemaType(name = "string")
    protected UserLockStatus userLockStatus;
    @XmlSchemaType(name = "string")
    protected UserEmailStatus userEmailStatus;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(XmlDateTimeTypeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date createdDate;
    @XmlAttribute(name = "Hjid")
    protected Long hjid;

    /**
     * Gets the value of the modelObjectId property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    @Basic
    @Column(name = "MODEL_OBJECT_ID", precision = 10, scale = 0)
    public Integer getModelObjectId() {
        return modelObjectId;
    }

    /**
     * Sets the value of the modelObjectId property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setModelObjectId(final Integer value) {
        this.modelObjectId = value;
    }

    /**
     * Gets the value of the modelObjectVersion property.
     *
     */
    @Version
    @Column(name = "MODEL_OBJECT_VERSION")
    public int getModelObjectVersion() {
        return modelObjectVersion;
    }

    /**
     * Sets the value of the modelObjectVersion property.
     *
     */
    public void setModelObjectVersion(final int value) {
        this.modelObjectVersion = value;
    }

    /**
     * Gets the value of the country property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCountry(final String value) {
        this.country = value;
    }

    /**
     * Gets the value of the username property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUsername(final String value) {
        this.username = value;
    }

    /**
     * Gets the value of the email property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setEmail(final String value) {
        this.email = value;
    }

    /**
     * Gets the value of the userId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "USER_ID")
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUserId(final String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the userpassword property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "USERPASSWORD")
    public String getUserpassword() {
        return userpassword;
    }

    /**
     * Sets the value of the userpassword property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUserpassword(final String value) {
        this.userpassword = value;
    }

    /**
     * Gets the value of the numberOfVisits property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    @Basic
    @Column(name = "NUMBER_OF_VISITS", precision = 10, scale = 0)
    public Integer getNumberOfVisits() {
        return numberOfVisits;
    }

    /**
     * Sets the value of the numberOfVisits property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setNumberOfVisits(final Integer value) {
        this.numberOfVisits = value;
    }

    /**
     * Gets the value of the address property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the address property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddress().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     *
     *
     */
    @ElementCollection
    @OrderColumn(name = "HJINDEX")
    @Column(name = "HJVALUE")
    @CollectionTable(name = "USER_ACCOUNT_ADDRESS", joinColumns = {
        @JoinColumn(name = "HJID")
    })
    public List<String> getAddress() {
        if (address == null) {
            address = new ArrayList<>();
        }
        return this.address;
    }

    /**
     *
     *
     */
    public void setAddress(final List<String> address) {
        this.address = address;
    }

    /**
     * Gets the value of the userType property.
     *
     * @return
     *     possible object is
     *     {@link UserType }
     *
     */
    @Basic
    @Column(name = "USER_TYPE")
    @Enumerated(EnumType.STRING)
    public UserType getUserType() {
        return userType;
    }

    /**
     * Sets the value of the userType property.
     *
     * @param value
     *     allowed object is
     *     {@link UserType }
     *
     */
    public void setUserType(final UserType value) {
        this.userType = value;
    }

    /**
     * Gets the value of the userRole property.
     *
     * @return
     *     possible object is
     *     {@link UserRole }
     *
     */
    @Basic
    @Column(name = "USER_ROLE")
    @Enumerated(EnumType.STRING)
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Sets the value of the userRole property.
     *
     * @param value
     *     allowed object is
     *     {@link UserRole }
     *
     */
    public void setUserRole(final UserRole value) {
        this.userRole = value;
    }

    /**
     * Gets the value of the userLockStatus property.
     *
     * @return
     *     possible object is
     *     {@link UserLockStatus }
     *
     */
    @Basic
    @Column(name = "USER_LOCK_STATUS")
    @Enumerated(EnumType.STRING)
    public UserLockStatus getUserLockStatus() {
        return userLockStatus;
    }

    /**
     * Sets the value of the userLockStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link UserLockStatus }
     *
     */
    public void setUserLockStatus(final UserLockStatus value) {
        this.userLockStatus = value;
    }

    /**
     * Gets the value of the userEmailStatus property.
     *
     * @return
     *     possible object is
     *     {@link UserEmailStatus }
     *
     */
    @Basic
    @Column(name = "USER_EMAIL_STATUS")
    @Enumerated(EnumType.STRING)
    public UserEmailStatus getUserEmailStatus() {
        return userEmailStatus;
    }

    /**
     * Sets the value of the userEmailStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link UserEmailStatus }
     *
     */
    public void setUserEmailStatus(final UserEmailStatus value) {
        this.userEmailStatus = value;
    }

    /**
     * Gets the value of the createdDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the value of the createdDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCreatedDate(final Date value) {
        this.createdDate = value;
    }

    public UserAccount withModelObjectId(final Integer value) {
        setModelObjectId(value);
        return this;
    }

    public UserAccount withModelObjectVersion(final int value) {
        setModelObjectVersion(value);
        return this;
    }

    public UserAccount withCountry(final String value) {
        setCountry(value);
        return this;
    }

    public UserAccount withUsername(final String value) {
        setUsername(value);
        return this;
    }

    public UserAccount withEmail(final String value) {
        setEmail(value);
        return this;
    }

    public UserAccount withUserId(final String value) {
        setUserId(value);
        return this;
    }

    public UserAccount withUserpassword(final String value) {
        setUserpassword(value);
        return this;
    }

    public UserAccount withNumberOfVisits(final Integer value) {
        setNumberOfVisits(value);
        return this;
    }

    public UserAccount withAddress(final List<String> address) {
        setAddress(address);
        return this;
    }

    public UserAccount withUserType(final UserType value) {
        setUserType(value);
        return this;
    }

    public UserAccount withUserRole(final UserRole value) {
        setUserRole(value);
        return this;
    }

    public UserAccount withUserLockStatus(final UserLockStatus value) {
        setUserLockStatus(value);
        return this;
    }

    public UserAccount withUserEmailStatus(final UserEmailStatus value) {
        setUserEmailStatus(value);
        return this;
    }

    public UserAccount withCreatedDate(final Date value) {
        setCreatedDate(value);
        return this;
    }

	@Override
	public final String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

    /**
     * Gets the value of the hjid property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    @Id
    @Column(name = "HJID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getHjid() {
        return hjid;
    }

    /**
     * Sets the value of the hjid property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setHjid(final Long value) {
        this.hjid = value;
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
