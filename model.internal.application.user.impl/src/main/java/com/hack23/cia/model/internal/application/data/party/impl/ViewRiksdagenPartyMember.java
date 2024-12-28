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
 * The Class ViewRiksdagenPartyMember.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenPartyMember", propOrder = { "id", "hjid", "address", "city", "coAddress", "email",
		"faxNumber", "partyId", "partyName", "phoneNumber", "postCode", "registeredDate", "shortCode", "website",
		"bornYear", "electionRegion", "firstName", "gender", "hangarGuid", "imageUrl192", "imageUrl80", "imageUrlMax",
		"lastName", "party", "personUrlXml", "place", "status", "totalDocuments", "partyMotions", "individualMotions",
		"committeeMotions", "multiPartyMotions", "documentsLastYear", "activityLevel", "activityProfile",
		"collaborationPercentage", "personAssignmentData", "personDetailData" })
@Entity(name = "ViewRiksdagenPartyMember")
@Table(name = "VIEW_RIKSDAGEN_PARTY_MEMBER")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewRiksdagenPartyMember implements ModelObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@XmlElement(required = true)
	protected String id;

	/** The hjid. */
	protected long hjid;

	/** The address. */
	@XmlElement(required = true)
	protected String address;

	/** The city. */
	@XmlElement(required = true)
	protected String city;

	/** The co address. */
	@XmlElement(required = true)
	protected String coAddress;

	/** The email. */
	@XmlElement(required = true)
	protected String email;

	/** The fax number. */
	@XmlElement(required = true)
	protected String faxNumber;

	/** The party id. */
	@XmlElement(required = true)
	protected String partyId;

	/** The party name. */
	@XmlElement(required = true)
	protected String partyName;

	/** The phone number. */
	@XmlElement(required = true)
	protected String phoneNumber;

	/** The post code. */
	@XmlElement(required = true)
	protected String postCode;

	/** The registered date. */
	@XmlElement(required = true, type = String.class)
	@XmlJavaTypeAdapter(XmlDateTypeAdapter.class)
	@XmlSchemaType(name = "date")
	protected Date registeredDate;

	/** The short code. */
	@XmlElement(required = true)
	protected String shortCode;

	/** The website. */
	@XmlElement(required = true)
	protected String website;

	/** The born year. */
	protected int bornYear;

	/** The election region. */
	@XmlElement(required = true)
	protected String electionRegion;

	/** The first name. */
	@XmlElement(required = true)
	protected String firstName;

	/** The gender. */
	@XmlElement(required = true)
	protected String gender;

	/** The hangar guid. */
	@XmlElement(required = true)
	protected String hangarGuid;

	/** The image url 192. */
	@XmlElement(name = "image_url_192", required = true)
	protected String imageUrl192;

	/** The image url 80. */
	@XmlElement(name = "image_url_80", required = true)
	protected String imageUrl80;

	/** The image url max. */
	@XmlElement(name = "image_url_max", required = true)
	protected String imageUrlMax;

	/** The last name. */
	@XmlElement(name = "last_name", required = true)
	protected String lastName;

	/** The party. */
	@XmlElement(required = true)
	protected String party;

	/** The person url xml. */
	@XmlElement(name = "person_url_xml", required = true)
	protected String personUrlXml;

	/** The place. */
	@XmlElement(required = true)
	protected String place;

	/** The status. */
	@XmlElement(required = true)
	protected String status;

	/** The total documents. */
	protected long totalDocuments;

	/** The party motions. */
	protected long partyMotions;

	/** The individual motions. */
	protected long individualMotions;

	/** The committee motions. */
	protected long committeeMotions;

	/** The activity level. */
	protected String activityLevel;

	/** The person assignment data. */
	protected long personAssignmentData;

	/** The person detail data. */
	protected long personDetailData;

	/** The multi party motions. */
	protected long multiPartyMotions;

	/** The documents last year. */
	protected long documentsLastYear;

	/** The activity profile. */
	protected String activityProfile;

	/** The collaboration percentage. */
	protected double collaborationPercentage;
	



	/**
	 * Gets the value of the id property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	@Id
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setId(final String value) {
		this.id = value;
	}

	/**
	 * Gets the value of the hjid property.
	 *
	 * @return the hjid
	 */
	@Basic
	@Column(name = "HJID", precision = 20)
	public long getHjid() {
		return hjid;
	}

	/**
	 * Sets the value of the hjid property.
	 *
	 * @param value the new hjid
	 */
	public void setHjid(final long value) {
		this.hjid = value;
	}

	/**
	 * Gets the value of the address property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	@Basic
	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the value of the address property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setAddress(final String value) {
		this.address = value;
	}

	/**
	 * Gets the value of the city property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	@Basic
	@Column(name = "CITY")
	public String getCity() {
		return city;
	}

	/**
	 * Sets the value of the city property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setCity(final String value) {
		this.city = value;
	}

	/**
	 * Gets the value of the coAddress property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	@Basic
	@Column(name = "CO_ADDRESS")
	public String getCoAddress() {
		return coAddress;
	}

	/**
	 * Sets the value of the coAddress property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setCoAddress(final String value) {
		this.coAddress = value;
	}

	/**
	 * Gets the value of the email property.
	 *
	 * @return possible object is {@link String }
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
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setEmail(final String value) {
		this.email = value;
	}

	/**
	 * Gets the value of the faxNumber property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	@Basic
	@Column(name = "FAX_NUMBER")
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * Sets the value of the faxNumber property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setFaxNumber(final String value) {
		this.faxNumber = value;
	}

	/**
	 * Gets the value of the partyId property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	@Basic
	@Column(name = "PARTY_ID")
	public String getPartyId() {
		return partyId;
	}

	/**
	 * Sets the value of the partyId property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setPartyId(final String value) {
		this.partyId = value;
	}

	/**
	 * Gets the value of the partyName property.
	 *
	 * @return possible object is {@link String }
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
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setPartyName(final String value) {
		this.partyName = value;
	}

	/**
	 * Gets the value of the phoneNumber property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	@Basic
	@Column(name = "PHONE_NUMBER")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the value of the phoneNumber property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setPhoneNumber(final String value) {
		this.phoneNumber = value;
	}

	/**
	 * Gets the value of the postCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	@Basic
	@Column(name = "POST_CODE")
	public String getPostCode() {
		return postCode;
	}

	/**
	 * Sets the value of the postCode property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setPostCode(final String value) {
		this.postCode = value;
	}

	/**
	 * Gets the value of the registeredDate property.
	 *
	 * @return possible object is {@link String }
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
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setRegisteredDate(final Date value) {
		this.registeredDate = value;
	}

	/**
	 * Gets the value of the shortCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	@Basic
	@Column(name = "SHORT_CODE")
	public String getShortCode() {
		return shortCode;
	}

	/**
	 * Sets the value of the shortCode property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setShortCode(final String value) {
		this.shortCode = value;
	}

	/**
	 * Gets the value of the website property.
	 *
	 * @return possible object is {@link String }
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
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setWebsite(final String value) {
		this.website = value;
	}

	/**
	 * Gets the value of the bornYear property.
	 *
	 * @return the born year
	 */
	@Basic
	@Column(name = "BORN_YEAR", precision = 10, scale = 0)
	public int getBornYear() {
		return bornYear;
	}

	/**
	 * Sets the value of the bornYear property.
	 *
	 * @param value the new born year
	 */
	public void setBornYear(final int value) {
		this.bornYear = value;
	}

	/**
	 * Gets the value of the electionRegion property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	@Basic
	@Column(name = "ELECTION_REGION")
	public String getElectionRegion() {
		return electionRegion;
	}

	/**
	 * Sets the value of the electionRegion property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setElectionRegion(final String value) {
		this.electionRegion = value;
	}

	/**
	 * Gets the value of the firstName property.
	 *
	 * @return possible object is {@link String }
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
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setFirstName(final String value) {
		this.firstName = value;
	}

	/**
	 * Gets the value of the gender property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	@Basic
	@Column(name = "GENDER")
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the value of the gender property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setGender(final String value) {
		this.gender = value;
	}

	/**
	 * Gets the value of the hangarGuid property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	@Basic
	@Column(name = "HANGAR_GUID")
	public String getHangarGuid() {
		return hangarGuid;
	}

	/**
	 * Sets the value of the hangarGuid property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setHangarGuid(final String value) {
		this.hangarGuid = value;
	}

	/**
	 * Gets the value of the imageUrl192 property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	@Basic
	@Column(name = "IMAGE_URL_192")
	public String getImageUrl192() {
		return imageUrl192;
	}

	/**
	 * Sets the value of the imageUrl192 property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setImageUrl192(final String value) {
		this.imageUrl192 = value;
	}

	/**
	 * Gets the value of the imageUrl80 property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	@Basic
	@Column(name = "IMAGE_URL_80")
	public String getImageUrl80() {
		return imageUrl80;
	}

	/**
	 * Sets the value of the imageUrl80 property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setImageUrl80(final String value) {
		this.imageUrl80 = value;
	}

	/**
	 * Gets the value of the imageUrlMax property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	@Basic
	@Column(name = "IMAGE_URL_MAX")
	public String getImageUrlMax() {
		return imageUrlMax;
	}

	/**
	 * Sets the value of the imageUrlMax property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setImageUrlMax(final String value) {
		this.imageUrlMax = value;
	}

	/**
	 * Gets the value of the lastName property.
	 *
	 * @return possible object is {@link String }
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
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setLastName(final String value) {
		this.lastName = value;
	}

	/**
	 * Gets the value of the party property.
	 *
	 * @return possible object is {@link String }
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
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setParty(final String value) {
		this.party = value;
	}

	/**
	 * Gets the value of the personUrlXml property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	@Basic
	@Column(name = "PERSON_URL_XML")
	public String getPersonUrlXml() {
		return personUrlXml;
	}

	/**
	 * Sets the value of the personUrlXml property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setPersonUrlXml(final String value) {
		this.personUrlXml = value;
	}

	/**
	 * Gets the value of the place property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	@Basic
	@Column(name = "PLACE")
	public String getPlace() {
		return place;
	}

	/**
	 * Sets the value of the place property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setPlace(final String value) {
		this.place = value;
	}

	/**
	 * Gets the value of the status property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	@Basic
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
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
	 * Gets the party motions.
	 *
	 * @return the party motions
	 */
	@Basic
	@Column(name = "PARTY_MOTIONS", precision = 20)
	public long getPartyMotions() {
		return partyMotions;
	}

	/**
	 * Sets the party motions.
	 *
	 * @param value the new party motions
	 */
	public void setPartyMotions(final long value) {
		this.partyMotions = value;
	}

	/**
	 * Gets the individual motions.
	 *
	 * @return the individual motions
	 */
	@Basic
	@Column(name = "INDIVIDUAL_MOTIONS", precision = 20)
	public long getIndividualMotions() {
		return individualMotions;
	}

	/**
	 * Sets the individual motions.
	 *
	 * @param value the new individual motions
	 */
	public void setIndividualMotions(final long value) {
		this.individualMotions = value;
	}

	/**
	 * Gets the committee motions.
	 *
	 * @return the committee motions
	 */
	@Basic
	@Column(name = "COMMITTEE_MOTIONS", precision = 20)
	public long getCommitteeMotions() {
		return committeeMotions;
	}

	/**
	 * Sets the committee motions.
	 *
	 * @param value the new committee motions
	 */
	public void setCommitteeMotions(final long value) {
		this.committeeMotions = value;
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
	 * Gets the person assignment data.
	 *
	 * @return the person assignment data
	 */
	@Basic
	@Column(name = "PERSON_ASSIGNMENT_DATA_PERSO_0")
	public long getPersonAssignmentData() {
	    return personAssignmentData;
	}

	/**
	 * Sets the person assignment data.
	 *
	 * @param value the new person assignment data
	 */
	public void setPersonAssignmentData(final long value) {
	    this.personAssignmentData = value;
	}

	/**
	 * Gets the person detail data.
	 *
	 * @return the person detail data
	 */
	@Basic
	@Column(name = "PERSON_DETAIL_DATA_PERSON_DA_0")
	public long getPersonDetailData() {
	    return personDetailData;
	}

	/**
	 * Sets the person detail data.
	 *
	 * @param value the new person detail data
	 */
	public void setPersonDetailData(final long value) {
	    this.personDetailData = value;
	}

	/**
	 * Gets the multi party motions.
	 *
	 * @return the multi party motions
	 */
	@Basic
	@Column(name = "MULTI_PARTY_MOTIONS")
	public long getMultiPartyMotions() {
	    return multiPartyMotions;
	}

	/**
	 * Sets the multi party motions.
	 *
	 * @param value the new multi party motions
	 */
	public void setMultiPartyMotions(final long value) {
	    this.multiPartyMotions = value;
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
	public void setDocumentsLastYear(final long value) {
	    this.documentsLastYear = value;
	}

	/**
	 * Gets the activity profile.
	 *
	 * @return the activity profile
	 */
	@Basic
	@Column(name = "ACTIVITY_PROFILE")
	public String getActivityProfile() {
	    return activityProfile;
	}

	/**
	 * Sets the activity profile.
	 *
	 * @param value the new activity profile
	 */
	public void setActivityProfile(final String value) {
	    this.activityProfile = value;
	}

	/**
	 * Gets the collaboration percentage.
	 *
	 * @return the collaboration percentage
	 */
	@Basic
	@Column(name = "COLLABORATION_PERCENTAGE", precision = 10, scale = 2)
	public double getCollaborationPercentage() {
	    return collaborationPercentage;
	}

	/**
	 * Sets the collaboration percentage.
	 *
	 * @param value the new collaboration percentage
	 */
	public void setCollaborationPercentage(final double value) {
	    this.collaborationPercentage = value;
	}

	/**
	 * With person assignment data.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withPersonAssignmentData(final long value) {
	    setPersonAssignmentData(value);
	    return this;
	}

	/**
	 * With person detail data.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withPersonDetailData(final long value) {
	    setPersonDetailData(value);
	    return this;
	}

	/**
	 * With multi party motions.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withMultiPartyMotions(final long value) {
	    setMultiPartyMotions(value);
	    return this;
	}

	/**
	 * With documents last year.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withDocumentsLastYear(final long value) {
	    setDocumentsLastYear(value);
	    return this;
	}

	/**
	 * With activity profile.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withActivityProfile(final String value) {
	    setActivityProfile(value);
	    return this;
	}

	/**
	 * With collaboration percentage.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withCollaborationPercentage(final double value) {
	    setCollaborationPercentage(value);
	    return this;
	}
	
	/**
	 * With total documents.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	// Builder pattern methods for new fields
	public ViewRiksdagenPartyMember withTotalDocuments(final long value) {
		setTotalDocuments(value);
		return this;
	}

	/**
	 * With party motions.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withPartyMotions(final long value) {
		setPartyMotions(value);
		return this;
	}

	/**
	 * With individual motions.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withIndividualMotions(final long value) {
		setIndividualMotions(value);
		return this;
	}

	/**
	 * With committee motions.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withCommitteeMotions(final long value) {
		setCommitteeMotions(value);
		return this;
	}

	/**
	 * With activity level.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withActivityLevel(final String value) {
		setActivityLevel(value);
		return this;
	}


	/**
	 * Sets the value of the status property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setStatus(final String value) {
		this.status = value;
	}

	/**
	 * With id.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withId(final String value) {
		setId(value);
		return this;
	}

	/**
	 * With hjid.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withHjid(final long value) {
		setHjid(value);
		return this;
	}

	/**
	 * With address.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withAddress(final String value) {
		setAddress(value);
		return this;
	}

	/**
	 * With city.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withCity(final String value) {
		setCity(value);
		return this;
	}

	/**
	 * With co address.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withCoAddress(final String value) {
		setCoAddress(value);
		return this;
	}

	/**
	 * With email.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withEmail(final String value) {
		setEmail(value);
		return this;
	}

	/**
	 * With fax number.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withFaxNumber(final String value) {
		setFaxNumber(value);
		return this;
	}

	/**
	 * With party id.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withPartyId(final String value) {
		setPartyId(value);
		return this;
	}

	/**
	 * With party name.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withPartyName(final String value) {
		setPartyName(value);
		return this;
	}

	/**
	 * With phone number.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withPhoneNumber(final String value) {
		setPhoneNumber(value);
		return this;
	}

	/**
	 * With post code.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withPostCode(final String value) {
		setPostCode(value);
		return this;
	}

	/**
	 * With registered date.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withRegisteredDate(final Date value) {
		setRegisteredDate(value);
		return this;
	}

	/**
	 * With short code.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withShortCode(final String value) {
		setShortCode(value);
		return this;
	}

	/**
	 * With website.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withWebsite(final String value) {
		setWebsite(value);
		return this;
	}

	/**
	 * With born year.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withBornYear(final int value) {
		setBornYear(value);
		return this;
	}

	/**
	 * With election region.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withElectionRegion(final String value) {
		setElectionRegion(value);
		return this;
	}

	/**
	 * With first name.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withFirstName(final String value) {
		setFirstName(value);
		return this;
	}

	/**
	 * With gender.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withGender(final String value) {
		setGender(value);
		return this;
	}

	/**
	 * With hangar guid.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withHangarGuid(final String value) {
		setHangarGuid(value);
		return this;
	}

	/**
	 * With image url 192.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withImageUrl192(final String value) {
		setImageUrl192(value);
		return this;
	}

	/**
	 * With image url 80.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withImageUrl80(final String value) {
		setImageUrl80(value);
		return this;
	}

	/**
	 * With image url max.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withImageUrlMax(final String value) {
		setImageUrlMax(value);
		return this;
	}

	/**
	 * With last name.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withLastName(final String value) {
		setLastName(value);
		return this;
	}

	/**
	 * With party.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withParty(final String value) {
		setParty(value);
		return this;
	}

	/**
	 * With person url xml.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withPersonUrlXml(final String value) {
		setPersonUrlXml(value);
		return this;
	}

	/**
	 * With place.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withPlace(final String value) {
		setPlace(value);
		return this;
	}

	/**
	 * With status.
	 *
	 * @param value the value
	 * @return the view riksdagen party member
	 */
	public ViewRiksdagenPartyMember withStatus(final String value) {
		setStatus(value);
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
