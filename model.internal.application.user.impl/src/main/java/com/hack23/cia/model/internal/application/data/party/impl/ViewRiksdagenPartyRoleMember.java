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
 * <p>Java class for ViewRiksdagenPartyRoleMember complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ViewRiksdagenPartyRoleMember"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="roleId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="detail" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="role_code" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="first_name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="last_name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="from_date" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="to_date" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="person_id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="party" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="total_days_served" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenPartyRoleMember", propOrder = {
	    "roleId",
	    "detail",
	    "roleCode",
	    "firstName",
	    "lastName",
	    "fromDate",
	    "toDate",
	    "personId",
	    "party",
	    "totalDaysServed",
	    "active",
	    "totalDocuments",
	    "documentsLastYear",
	    "totalMotions",
	    "totalInterpellations",
	    "totalWrittenQuestions",
	    "activityLevel",
	    "roleType"
	})
@Entity(name = "ViewRiksdagenPartyRoleMember")
@Table(name = "VIEW_RIKSDAGEN_PARTY_ROLE_MEMBER")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewRiksdagenPartyRoleMember
    implements ModelObject
{

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The role id. */
	protected long roleId;
    
    /** The detail. */
    @XmlElement(required = true)
    protected String detail;
    
    /** The role code. */
    @XmlElement(name = "role_code", required = true)
    protected String roleCode;
    
    /** The first name. */
    @XmlElement(name = "first_name", required = true)
    protected String firstName;
    
    /** The last name. */
    @XmlElement(name = "last_name", required = true)
    protected String lastName;
    
    /** The from date. */
    @XmlElement(name = "from_date", required = true, type = String.class)
    @XmlJavaTypeAdapter(XmlDateTypeAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date fromDate;
    
    /** The to date. */
    @XmlElement(name = "to_date", required = true, type = String.class)
    @XmlJavaTypeAdapter(XmlDateTypeAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date toDate;
    
    /** The person id. */
    @XmlElement(name = "person_id", required = true)
    protected String personId;
    
    /** The party. */
    @XmlElement(required = true)
    protected String party;
    
    /** The total days served. */
    @XmlElement(name = "total_days_served", required = true, type = Integer.class, nillable = true)
    protected Integer totalDaysServed;
    
    /** The active. */
    protected boolean active;

    /** The total documents. */
    protected long totalDocuments;
    
    /** The documents last year. */
    protected long documentsLastYear;
    
    /** The total motions. */
    protected long totalMotions;
    
    /** The total interpellations. */
    protected long totalInterpellations;
    
    /** The total written questions. */
    protected long totalWrittenQuestions;
    
    /** The activity level. */
    @XmlElement(required = true)
    protected String activityLevel;
    
    /** The role type. */
    @XmlElement(required = true)
    protected String roleType;

    /**
     * Gets the value of the roleId property.
     *
     * @return the role id
     */
    @Id
    @Column(name = "ROLE_ID")
    public long getRoleId() {
        return roleId;
    }

    /**
     * Sets the value of the roleId property.
     *
     * @param value the new role id
     */
    public void setRoleId(final long value) {
        this.roleId = value;
    }

    /**
     * Gets the value of the detail property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "DETAIL")
    public String getDetail() {
        return detail;
    }

    /**
     * Sets the value of the detail property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDetail(final String value) {
        this.detail = value;
    }

    /**
     * Gets the value of the roleCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "ROLE_CODE")
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * Sets the value of the roleCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRoleCode(final String value) {
        this.roleCode = value;
    }

    /**
     * Gets the value of the firstName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFirstName(final String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLastName(final String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the fromDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "FROM_DATE")
    @Temporal(TemporalType.DATE)
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * Sets the value of the fromDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFromDate(final Date value) {
        this.fromDate = value;
    }

    /**
     * Gets the value of the toDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "TO_DATE")
    @Temporal(TemporalType.DATE)
    public Date getToDate() {
        return toDate;
    }

    /**
     * Sets the value of the toDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setToDate(final Date value) {
        this.toDate = value;
    }

    /**
     * Gets the value of the personId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "PERSON_ID")
    public String getPersonId() {
        return personId;
    }

    /**
     * Sets the value of the personId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPersonId(final String value) {
        this.personId = value;
    }

    /**
     * Gets the value of the party property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "PARTY")
    public String getParty() {
        return party;
    }

    /**
     * Sets the value of the party property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setParty(final String value) {
        this.party = value;
    }

    /**
     * Gets the value of the totalDaysServed property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    @Basic
    @Column(name = "TOTAL_DAYS_SERVED", precision = 10, scale = 0)
    public Integer getTotalDaysServed() {
        return totalDaysServed;
    }

    /**
     * Sets the value of the totalDaysServed property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setTotalDaysServed(final Integer value) {
        this.totalDaysServed = value;
    }

    /**
     * Gets the value of the active property.
     *
     * @return true, if is active
     */
    @Basic
    @Column(name = "ACTIVE")
    public boolean isActive() {
        return active;
    }

    /**
     * Gets the total documents.
     *
     * @return the total documents
     */
    @Basic
    @Column(name = "TOTAL_DOCUMENTS", precision = 20)
    public long getTotalDocuments() {
        return totalDocuments;
    }

    /**
     * Sets the total documents.
     *
     * @param value the new total documents
     */
    public void setTotalDocuments(final long value) {
        this.totalDocuments = value;
    }

    /**
     * Gets the documents last year.
     *
     * @return the documents last year
     */
    @Basic
    @Column(name = "DOCUMENTS_LAST_YEAR", precision = 20)
    public long getDocumentsLastYear() {
        return documentsLastYear;
    }

    /**
     * Sets the documents last year.
     *
     * @param value the new documents last year
     */
    public void setDocumentsLastYear(final long value) {
        this.documentsLastYear = value;
    }

    /**
     * Gets the total motions.
     *
     * @return the total motions
     */
    @Basic
    @Column(name = "TOTAL_MOTIONS", precision = 20)
    public long getTotalMotions() {
        return totalMotions;
    }

    /**
     * Sets the total motions.
     *
     * @param value the new total motions
     */
    public void setTotalMotions(final long value) {
        this.totalMotions = value;
    }

    /**
     * Gets the total interpellations.
     *
     * @return the total interpellations
     */
    @Basic
    @Column(name = "TOTAL_INTERPELLATIONS", precision = 20)
    public long getTotalInterpellations() {
        return totalInterpellations;
    }

    /**
     * Sets the total interpellations.
     *
     * @param value the new total interpellations
     */
    public void setTotalInterpellations(final long value) {
        this.totalInterpellations = value;
    }

    /**
     * Gets the total written questions.
     *
     * @return the total written questions
     */
    @Basic
    @Column(name = "TOTAL_WRITTEN_QUESTIONS", precision = 20)
    public long getTotalWrittenQuestions() {
        return totalWrittenQuestions;
    }

    /**
     * Sets the total written questions.
     *
     * @param value the new total written questions
     */
    public void setTotalWrittenQuestions(final long value) {
        this.totalWrittenQuestions = value;
    }

    /**
     * Gets the activity level.
     *
     * @return the activity level
     */
    @Basic
    @Column(name = "ACTIVITY_LEVEL")
    public String getActivityLevel() {
        return activityLevel;
    }

    /**
     * Sets the activity level.
     *
     * @param value the new activity level
     */
    public void setActivityLevel(final String value) {
        this.activityLevel = value;
    }

    /**
     * Gets the role type.
     *
     * @return the role type
     */
    @Basic
    @Column(name = "ROLE_TYPE")
    public String getRoleType() {
        return roleType;
    }

    /**
     * Sets the role type.
     *
     * @param value the new role type
     */
    public void setRoleType(final String value) {
        this.roleType = value;
    }

    /**
     * With total documents.
     *
     * @param value the value
     * @return the view riksdagen party role member
     */
    public ViewRiksdagenPartyRoleMember withTotalDocuments(final long value) {
        setTotalDocuments(value);
        return this;
    }

    /**
     * With documents last year.
     *
     * @param value the value
     * @return the view riksdagen party role member
     */
    public ViewRiksdagenPartyRoleMember withDocumentsLastYear(final long value) {
        setDocumentsLastYear(value);
        return this;
    }

    /**
     * With total motions.
     *
     * @param value the value
     * @return the view riksdagen party role member
     */
    public ViewRiksdagenPartyRoleMember withTotalMotions(final long value) {
        setTotalMotions(value);
        return this;
    }

    /**
     * With total interpellations.
     *
     * @param value the value
     * @return the view riksdagen party role member
     */
    public ViewRiksdagenPartyRoleMember withTotalInterpellations(final long value) {
        setTotalInterpellations(value);
        return this;
    }

    /**
     * With total written questions.
     *
     * @param value the value
     * @return the view riksdagen party role member
     */
    public ViewRiksdagenPartyRoleMember withTotalWrittenQuestions(final long value) {
        setTotalWrittenQuestions(value);
        return this;
    }

    /**
     * With activity level.
     *
     * @param value the value
     * @return the view riksdagen party role member
     */
    public ViewRiksdagenPartyRoleMember withActivityLevel(final String value) {
        setActivityLevel(value);
        return this;
    }

    /**
     * With role type.
     *
     * @param value the value
     * @return the view riksdagen party role member
     */
    public ViewRiksdagenPartyRoleMember withRoleType(final String value) {
        setRoleType(value);
        return this;
    }


    /**
     * Sets the value of the active property.
     *
     * @param value the new active
     */
    public void setActive(final boolean value) {
        this.active = value;
    }

    /**
     * With role id.
     *
     * @param value the value
     * @return the view riksdagen party role member
     */
    public ViewRiksdagenPartyRoleMember withRoleId(final long value) {
        setRoleId(value);
        return this;
    }

    /**
     * With detail.
     *
     * @param value the value
     * @return the view riksdagen party role member
     */
    public ViewRiksdagenPartyRoleMember withDetail(final String value) {
        setDetail(value);
        return this;
    }

    /**
     * With role code.
     *
     * @param value the value
     * @return the view riksdagen party role member
     */
    public ViewRiksdagenPartyRoleMember withRoleCode(final String value) {
        setRoleCode(value);
        return this;
    }

    /**
     * With first name.
     *
     * @param value the value
     * @return the view riksdagen party role member
     */
    public ViewRiksdagenPartyRoleMember withFirstName(final String value) {
        setFirstName(value);
        return this;
    }

    /**
     * With last name.
     *
     * @param value the value
     * @return the view riksdagen party role member
     */
    public ViewRiksdagenPartyRoleMember withLastName(final String value) {
        setLastName(value);
        return this;
    }

    /**
     * With from date.
     *
     * @param value the value
     * @return the view riksdagen party role member
     */
    public ViewRiksdagenPartyRoleMember withFromDate(final Date value) {
        setFromDate(value);
        return this;
    }

    /**
     * With to date.
     *
     * @param value the value
     * @return the view riksdagen party role member
     */
    public ViewRiksdagenPartyRoleMember withToDate(final Date value) {
        setToDate(value);
        return this;
    }

    /**
     * With person id.
     *
     * @param value the value
     * @return the view riksdagen party role member
     */
    public ViewRiksdagenPartyRoleMember withPersonId(final String value) {
        setPersonId(value);
        return this;
    }

    /**
     * With party.
     *
     * @param value the value
     * @return the view riksdagen party role member
     */
    public ViewRiksdagenPartyRoleMember withParty(final String value) {
        setParty(value);
        return this;
    }

    /**
     * With total days served.
     *
     * @param value the value
     * @return the view riksdagen party role member
     */
    public ViewRiksdagenPartyRoleMember withTotalDaysServed(final Integer value) {
        setTotalDaysServed(value);
        return this;
    }

    /**
     * With active.
     *
     * @param value the value
     * @return the view riksdagen party role member
     */
    public ViewRiksdagenPartyRoleMember withActive(final boolean value) {
        setActive(value);
        return this;
    }

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public final String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public final boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}


	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public final int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
