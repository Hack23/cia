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
 * The Class ViewRiksdagenParty.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenParty", propOrder = {
    "partyId",
    "partyNumber",
    "partyName",
    "headCount",
    "website",
    "registeredDate",
    "totalDocuments",
    "avgDocumentsPerMember",
    "documentsLastYear",
    "veryHighActivityMembers",
    "highActivityMembers",
    "mediumActivityMembers",
    "lowActivityMembers",
    "partyFocusedMembers",
    "committeeFocusedMembers",
    "individualFocusedMembers",
    "avgCollaborationPercentage"
})
@Entity(name = "ViewRiksdagenParty")
@Table(name = "VIEW_RIKSDAGEN_PARTY")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewRiksdagenParty implements ModelObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The party id. */
    @XmlElement(required = true)
    protected String partyId;

    /** The party number. */
    @XmlElement(required = true)
    protected String partyNumber;

    /** The party name. */
    @XmlElement(required = true)
    protected String partyName;

    /** The head count. */
    protected long headCount;

    /** The website. */
    @XmlElement(required = true)
    protected String website;

    /** The registered date. */
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(XmlDateTypeAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date registeredDate;

    /** The total documents. */
    protected long totalDocuments;

    /** The avg documents per member. */
    protected double avgDocumentsPerMember;

    /** The documents last year. */
    protected long documentsLastYear;

    /** The very high activity members. */
    protected long veryHighActivityMembers;

    /** The high activity members. */
    protected long highActivityMembers;

    /** The medium activity members. */
    protected long mediumActivityMembers;

    /** The low activity members. */
    protected long lowActivityMembers;

    /** The party focused members. */
    protected long partyFocusedMembers;

    /** The committee focused members. */
    protected long committeeFocusedMembers;

    /** The individual focused members. */
    protected long individualFocusedMembers;

    /** The avg collaboration percentage. */
    protected double avgCollaborationPercentage;

    /**
     * Gets the party id.
     *
     * @return the party id
     */
    @Id
    @Column(name = "PARTY_ID")
    public String getPartyId() {
        return partyId;
    }

    /**
     * Sets the party id.
     *
     * @param value the new party id
     */
    public void setPartyId(String value) {
        this.partyId = value;
    }

    /**
     * Gets the party number.
     *
     * @return the party number
     */
    @Basic
    @Column(name = "PARTY_NUMBER")
    public String getPartyNumber() {
        return partyNumber;
    }

    /**
     * Sets the party number.
     *
     * @param value the new party number
     */
    public void setPartyNumber(String value) {
        this.partyNumber = value;
    }

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
    public void setPartyName(String value) {
        this.partyName = value;
    }

    /**
     * Gets the head count.
     *
     * @return the head count
     */
    @Basic
    @Column(name = "HEAD_COUNT")
    public long getHeadCount() {
        return headCount;
    }

    /**
     * Sets the head count.
     *
     * @param value the new head count
     */
    public void setHeadCount(long value) {
        this.headCount = value;
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
    public void setWebsite(String value) {
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
    public void setRegisteredDate(Date value) {
        this.registeredDate = value;
    }

    /**
     * Gets the total documents.
     *
     * @return the total documents
     */
    @Basic
    @Column(name = "TOTAL_DOCUMENTS")
    public long getTotalDocuments() {
        return totalDocuments;
    }

    /**
     * Sets the total documents.
     *
     * @param value the new total documents
     */
    public void setTotalDocuments(long value) {
        this.totalDocuments = value;
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
    public void setAvgDocumentsPerMember(double value) {
        this.avgDocumentsPerMember = value;
    }

    /**
     * Gets the documents last year.
     *
     * @return the documents last year
     */
    @Basic
    @Column(name = "DOCUMENTS_LAST_YEAR")
    public long getDocumentsLastYear() {
        return documentsLastYear;
    }

    /**
     * Sets the documents last year.
     *
     * @param value the new documents last year
     */
    public void setDocumentsLastYear(long value) {
        this.documentsLastYear = value;
    }

    /**
     * Gets the very high activity members.
     *
     * @return the very high activity members
     */
    @Basic
    @Column(name = "VERY_HIGH_ACTIVITY_MEMBERS")
    public long getVeryHighActivityMembers() {
        return veryHighActivityMembers;
    }

    /**
     * Sets the very high activity members.
     *
     * @param value the new very high activity members
     */
    public void setVeryHighActivityMembers(long value) {
        this.veryHighActivityMembers = value;
    }

    /**
     * Gets the high activity members.
     *
     * @return the high activity members
     */
    @Basic
    @Column(name = "HIGH_ACTIVITY_MEMBERS")
    public long getHighActivityMembers() {
        return highActivityMembers;
    }

    /**
     * Sets the high activity members.
     *
     * @param value the new high activity members
     */
    public void setHighActivityMembers(long value) {
        this.highActivityMembers = value;
    }

    /**
     * Gets the medium activity members.
     *
     * @return the medium activity members
     */
    @Basic
    @Column(name = "MEDIUM_ACTIVITY_MEMBERS")
    public long getMediumActivityMembers() {
        return mediumActivityMembers;
    }

    /**
     * Sets the medium activity members.
     *
     * @param value the new medium activity members
     */
    public void setMediumActivityMembers(long value) {
        this.mediumActivityMembers = value;
    }

    /**
     * Gets the low activity members.
     *
     * @return the low activity members
     */
    @Basic
    @Column(name = "LOW_ACTIVITY_MEMBERS")
    public long getLowActivityMembers() {
        return lowActivityMembers;
    }

    /**
     * Sets the low activity members.
     *
     * @param value the new low activity members
     */
    public void setLowActivityMembers(long value) {
        this.lowActivityMembers = value;
    }

    /**
     * Gets the party focused members.
     *
     * @return the party focused members
     */
    @Basic
    @Column(name = "PARTY_FOCUSED_MEMBERS")
    public long getPartyFocusedMembers() {
        return partyFocusedMembers;
    }

    /**
     * Sets the party focused members.
     *
     * @param value the new party focused members
     */
    public void setPartyFocusedMembers(long value) {
        this.partyFocusedMembers = value;
    }

    /**
     * Gets the committee focused members.
     *
     * @return the committee focused members
     */
    @Basic
    @Column(name = "COMMITTEE_FOCUSED_MEMBERS")
    public long getCommitteeFocusedMembers() {
        return committeeFocusedMembers;
    }

    /**
     * Sets the committee focused members.
     *
     * @param value the new committee focused members
     */
    public void setCommitteeFocusedMembers(long value) {
        this.committeeFocusedMembers = value;
    }

    /**
     * Gets the individual focused members.
     *
     * @return the individual focused members
     */
    @Basic
    @Column(name = "INDIVIDUAL_FOCUSED_MEMBERS")
    public long getIndividualFocusedMembers() {
        return individualFocusedMembers;
    }

    /**
     * Sets the individual focused members.
     *
     * @param value the new individual focused members
     */
    public void setIndividualFocusedMembers(long value) {
        this.individualFocusedMembers = value;
    }

    /**
     * Gets the avg collaboration percentage.
     *
     * @return the avg collaboration percentage
     */
    @Basic
    @Column(name = "AVG_COLLABORATION_PERCENTAGE", precision = 10, scale = 1)
    public double getAvgCollaborationPercentage() {
        return avgCollaborationPercentage;
    }

    /**
     * Sets the avg collaboration percentage.
     *
     * @param value the new avg collaboration percentage
     */
    public void setAvgCollaborationPercentage(double value) {
        this.avgCollaborationPercentage = value;
    }

    /**
     * With party id.
     *
     * @param value the value
     * @return the view riksdagen party
     */
    // Builder pattern methods
    public ViewRiksdagenParty withPartyId(String value) {
        setPartyId(value);
        return this;
    }

    /**
     * With party number.
     *
     * @param value the value
     * @return the view riksdagen party
     */
    public ViewRiksdagenParty withPartyNumber(String value) {
        setPartyNumber(value);
        return this;
    }

    /**
     * With party name.
     *
     * @param value the value
     * @return the view riksdagen party
     */
    public ViewRiksdagenParty withPartyName(String value) {
        setPartyName(value);
        return this;
    }

    /**
     * With head count.
     *
     * @param value the value
     * @return the view riksdagen party
     */
    public ViewRiksdagenParty withHeadCount(long value) {
        setHeadCount(value);
        return this;
    }

    /**
     * With website.
     *
     * @param value the value
     * @return the view riksdagen party
     */
    public ViewRiksdagenParty withWebsite(String value) {
        setWebsite(value);
        return this;
    }

    /**
     * With registered date.
     *
     * @param value the value
     * @return the view riksdagen party
     */
    public ViewRiksdagenParty withRegisteredDate(Date value) {
        setRegisteredDate(value);
        return this;
    }

    /**
     * With total documents.
     *
     * @param value the value
     * @return the view riksdagen party
     */
    public ViewRiksdagenParty withTotalDocuments(long value) {
        setTotalDocuments(value);
        return this;
    }

    /**
     * With avg documents per member.
     *
     * @param value the value
     * @return the view riksdagen party
     */
    public ViewRiksdagenParty withAvgDocumentsPerMember(double value) {
        setAvgDocumentsPerMember(value);
        return this;
    }

    /**
     * With documents last year.
     *
     * @param value the value
     * @return the view riksdagen party
     */
    public ViewRiksdagenParty withDocumentsLastYear(long value) {
        setDocumentsLastYear(value);
        return this;
    }

    /**
     * With very high activity members.
     *
     * @param value the value
     * @return the view riksdagen party
     */
    public ViewRiksdagenParty withVeryHighActivityMembers(long value) {
        setVeryHighActivityMembers(value);
        return this;
    }

    /**
     * With high activity members.
     *
     * @param value the value
     * @return the view riksdagen party
     */
    public ViewRiksdagenParty withHighActivityMembers(long value) {
        setHighActivityMembers(value);
        return this;
    }

    /**
     * With medium activity members.
     *
     * @param value the value
     * @return the view riksdagen party
     */
    public ViewRiksdagenParty withMediumActivityMembers(long value) {
        setMediumActivityMembers(value);
        return this;
    }

    /**
     * With low activity members.
     *
     * @param value the value
     * @return the view riksdagen party
     */
    public ViewRiksdagenParty withLowActivityMembers(long value) {
        setLowActivityMembers(value);
        return this;
    }

    /**
     * With party focused members.
     *
     * @param value the value
     * @return the view riksdagen party
     */
    public ViewRiksdagenParty withPartyFocusedMembers(long value) {
        setPartyFocusedMembers(value);
        return this;
    }

    /**
     * With committee focused members.
     *
     * @param value the value
     * @return the view riksdagen party
     */
    public ViewRiksdagenParty withCommitteeFocusedMembers(long value) {
        setCommitteeFocusedMembers(value);
        return this;
    }

    /**
     * With individual focused members.
     *
     * @param value the value
     * @return the view riksdagen party
     */
    public ViewRiksdagenParty withIndividualFocusedMembers(long value) {
        setIndividualFocusedMembers(value);
        return this;
    }

    /**
     * With avg collaboration percentage.
     *
     * @param value the value
     * @return the view riksdagen party
     */
    public ViewRiksdagenParty withAvgCollaborationPercentage(double value) {
        setAvgCollaborationPercentage(value);
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