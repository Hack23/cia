package com.hack23.cia.model.internal.application.data.ministry.impl;


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
 * <p>Java class for ViewRiksdagenMinistry complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ViewRiksdagenMinistry"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nameId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="totalAssignments" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="firstAssignmentDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="lastAssignmentDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="totalDaysServed" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="currentMemberSize" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
@XmlType(name = "ViewRiksdagenMinistry", propOrder = {
    "nameId",
    "totalAssignments",
    "firstAssignmentDate",
    "lastAssignmentDate",
    "totalDaysServed",
    "currentMemberSize",
    "active",
    "totalDocuments",
    "documentsLastYear",
    "avgDocumentsPerMember",
    "totalPropositions",
    "totalGovernmentBills",
    "activityLevel"
})
@Entity(name = "ViewRiksdagenMinistry")
@Table(name = "VIEW_RIKSDAGEN_GOVERMENT")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewRiksdagenMinistry
    implements ModelObject
{

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The name id. */
	@XmlElement(required = true)
    protected String nameId;

    /** The total assignments. */
    protected long totalAssignments;

    /** The first assignment date. */
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(XmlDateTypeAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date firstAssignmentDate;

    /** The last assignment date. */
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(XmlDateTypeAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date lastAssignmentDate;

    /** The total days served. */
    protected long totalDaysServed;

    /** The current member size. */
    protected long currentMemberSize;

    /** The active. */
    protected boolean active;

    /** The total documents. */
    protected long totalDocuments;

    /** The documents last year. */
    protected long documentsLastYear;

    /** The avg documents per member. */
    protected double avgDocumentsPerMember;

    /** The total propositions. */
    protected long totalPropositions;

    /** The total government bills. */
    protected long totalGovernmentBills;

    /** The activity level. */
    @XmlElement(required = true)
    protected String activityLevel;

    /**
     * Gets the value of the nameId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Id
    @Column(name = "NAME_ID")
    public String getNameId() {
        return nameId;
    }

    /**
     * Sets the value of the nameId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNameId(final String value) {
        this.nameId = value;
    }

    /**
     * Gets the value of the totalAssignments property.
     *
     * @return the total assignments
     */
    @Basic
    @Column(name = "TOTAL_ASSIGNMENTS", precision = 20)
    public long getTotalAssignments() {
        return totalAssignments;
    }

    /**
     * Sets the value of the totalAssignments property.
     *
     * @param value the new total assignments
     */
    public void setTotalAssignments(final long value) {
        this.totalAssignments = value;
    }

    /**
     * Gets the value of the firstAssignmentDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "FIRST_ASSIGNMENT_DATE")
    @Temporal(TemporalType.DATE)
    public Date getFirstAssignmentDate() {
        return firstAssignmentDate;
    }

    /**
     * Sets the value of the firstAssignmentDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFirstAssignmentDate(final Date value) {
        this.firstAssignmentDate = value;
    }

    /**
     * Gets the value of the lastAssignmentDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "LAST_ASSIGNMENT_DATE")
    @Temporal(TemporalType.DATE)
    public Date getLastAssignmentDate() {
        return lastAssignmentDate;
    }

    /**
     * Sets the value of the lastAssignmentDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLastAssignmentDate(final Date value) {
        this.lastAssignmentDate = value;
    }

    /**
     * Gets the value of the totalDaysServed property.
     *
     * @return the total days served
     */
    @Basic
    @Column(name = "TOTAL_DAYS_SERVED", precision = 20)
    public long getTotalDaysServed() {
        return totalDaysServed;
    }

    /**
     * Sets the value of the totalDaysServed property.
     *
     * @param value the new total days served
     */
    public void setTotalDaysServed(final long value) {
        this.totalDaysServed = value;
    }

    /**
     * Gets the value of the currentMemberSize property.
     *
     * @return the current member size
     */
    @Basic
    @Column(name = "CURRENT_MEMBER_SIZE", precision = 20)
    public long getCurrentMemberSize() {
        return currentMemberSize;
    }

    /**
     * Sets the value of the currentMemberSize property.
     *
     * @param value the new current member size
     */
    public void setCurrentMemberSize(final long value) {
        this.currentMemberSize = value;
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
     * Sets the value of the active property.
     *
     * @param value the new active
     */
    public void setActive(final boolean value) {
        this.active = value;
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
     * Gets the avg documents per member.
     *
     * @return the avg documents per member
     */
    @Basic
    @Column(name = "AVG_DOCUMENTS_PER_MEMBER", precision = 10, scale = 1)
    public double getAvgDocumentsPerMember() {
        return avgDocumentsPerMember;
    }

    /**
     * Sets the avg documents per member.
     *
     * @param value the new avg documents per member
     */
    public void setAvgDocumentsPerMember(final double value) {
        this.avgDocumentsPerMember = value;
    }

    /**
     * Gets the total propositions.
     *
     * @return the total propositions
     */
    @Basic
    @Column(name = "TOTAL_PROPOSITIONS", precision = 20)
    public long getTotalPropositions() {
        return totalPropositions;
    }

    /**
     * Sets the total propositions.
     *
     * @param value the new total propositions
     */
    public void setTotalPropositions(final long value) {
        this.totalPropositions = value;
    }

    /**
     * Gets the total government bills.
     *
     * @return the total government bills
     */
    @Basic
    @Column(name = "TOTAL_GOVERNMENT_BILLS", precision = 20)
    public long getTotalGovernmentBills() {
        return totalGovernmentBills;
    }

    /**
     * Sets the total government bills.
     *
     * @param value the new total government bills
     */
    public void setTotalGovernmentBills(final long value) {
        this.totalGovernmentBills = value;
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
     * With total documents.
     *
     * @param value the value
     * @return the view riksdagen ministry
     */
    // New builder pattern methods
    public ViewRiksdagenMinistry withTotalDocuments(final long value) {
        setTotalDocuments(value);
        return this;
    }

    /**
     * With documents last year.
     *
     * @param value the value
     * @return the view riksdagen ministry
     */
    public ViewRiksdagenMinistry withDocumentsLastYear(final long value) {
        setDocumentsLastYear(value);
        return this;
    }

    /**
     * With avg documents per member.
     *
     * @param value the value
     * @return the view riksdagen ministry
     */
    public ViewRiksdagenMinistry withAvgDocumentsPerMember(final double value) {
        setAvgDocumentsPerMember(value);
        return this;
    }

    /**
     * With total propositions.
     *
     * @param value the value
     * @return the view riksdagen ministry
     */
    public ViewRiksdagenMinistry withTotalPropositions(final long value) {
        setTotalPropositions(value);
        return this;
    }

    /**
     * With total government bills.
     *
     * @param value the value
     * @return the view riksdagen ministry
     */
    public ViewRiksdagenMinistry withTotalGovernmentBills(final long value) {
        setTotalGovernmentBills(value);
        return this;
    }

    /**
     * With activity level.
     *
     * @param value the value
     * @return the view riksdagen ministry
     */
    public ViewRiksdagenMinistry withActivityLevel(final String value) {
        setActivityLevel(value);
        return this;
    }

    /**
     * With name id.
     *
     * @param value the value
     * @return the view riksdagen ministry
     */
    public ViewRiksdagenMinistry withNameId(final String value) {
        setNameId(value);
        return this;
    }

    /**
     * With total assignments.
     *
     * @param value the value
     * @return the view riksdagen ministry
     */
    public ViewRiksdagenMinistry withTotalAssignments(final long value) {
        setTotalAssignments(value);
        return this;
    }

    /**
     * With first assignment date.
     *
     * @param value the value
     * @return the view riksdagen ministry
     */
    public ViewRiksdagenMinistry withFirstAssignmentDate(final Date value) {
        setFirstAssignmentDate(value);
        return this;
    }

    /**
     * With last assignment date.
     *
     * @param value the value
     * @return the view riksdagen ministry
     */
    public ViewRiksdagenMinistry withLastAssignmentDate(final Date value) {
        setLastAssignmentDate(value);
        return this;
    }

    /**
     * With total days served.
     *
     * @param value the value
     * @return the view riksdagen ministry
     */
    public ViewRiksdagenMinistry withTotalDaysServed(final long value) {
        setTotalDaysServed(value);
        return this;
    }

    /**
     * With current member size.
     *
     * @param value the value
     * @return the view riksdagen ministry
     */
    public ViewRiksdagenMinistry withCurrentMemberSize(final long value) {
        setCurrentMemberSize(value);
        return this;
    }

    /**
     * With active.
     *
     * @param value the value
     * @return the view riksdagen ministry
     */
    public ViewRiksdagenMinistry withActive(final boolean value) {
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
