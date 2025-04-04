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
// Generated on: 2019.02.24 at 11:40:02 PM CET
//


package com.hack23.cia.model.external.riksdagen.dokumentlista.impl;

import java.math.BigInteger;

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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.IdentifierBridgeRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.DocumentId;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.hack23.cia.model.common.api.ModelObject;


/**
 * The Class DocumentElement.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentElement", propOrder = {
    "hit",
    "id",
    "domainOrg",
    "documentName",
    "debateName",
    "noteTitle",
    "note",
    "summary",
    "databaseSource",
    "origin",
    "lang",
    "rm",
    "relatedId",
    "documentType",
    "docType",
    "subType",
    "status",
    "label",
    "tempLabel",
    "org",
    "numberValue",
    "title",
    "subTitle",
    "createdDate",
    "madePublicDate",
    "systemDate",
    "kallId",
    "documentFormat",
    "documentUrlText",
    "documentUrlHtml",
    "documentStatusUrlXml",
    "committeeReportUrlXml"
})
@Entity(name = "DocumentElement")
@Table(name = "DOCUMENT_ELEMENT")
@Inheritance(strategy = InheritanceType.JOINED)
@Indexed
public class DocumentElement
    implements ModelObject
{

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** The hit. */
    @XmlElement(name = "traff", required = true)
    protected BigInteger hit;


    /** The id. */
    @XmlElement(required = true)
    @DocumentId(identifierBridge = @IdentifierBridgeRef(type = DefaultStringIdentifier.class))
    protected String id;

    /** The domain org. */
    @XmlElement(name = "domain", required = true)
    @GenericField
    protected String domainOrg;

    /** The document name. */
    @XmlElement(name = "dokumentnamn", required = true)
    @GenericField
    protected String documentName;

    /** The debate name. */
    @XmlElement(name = "debattnamn", required = true)
    @GenericField
    protected String debateName;

    /** The note title. */
    @XmlElement(name = "notisrubrik", required = true)
    @GenericField
    protected String noteTitle;

    /** The note. */
    @XmlElement(name = "notis", required = true)
    @GenericField
    protected String note;

    /** The summary. */
    @XmlElement(required = true)
    @GenericField
    protected String summary;

    /** The database source. */
    @XmlElement(name = "database", required = true)
    @GenericField
    protected String databaseSource;

    /** The origin. */
    @XmlElement(name = "kalla", required = true)
    @GenericField
    protected String origin;

    /** The lang. */
    @XmlElement(required = true)
    @GenericField
    protected String lang;

    /** The rm. */
    @XmlElement(required = true)
    @GenericField
    protected String rm;

    /** The related id. */
    @XmlElement(name = "relaterat_id", required = true)
    @GenericField
    protected String relatedId;

    /** The document type. */
    @XmlElement(name = "typ", required = true)
    @GenericField
    protected String documentType;

    /** The doc type. */
    @XmlElement(name = "doktyp", required = true)
    @GenericField
    protected String docType;

    /** The sub type. */
    @XmlElement(name = "subtyp", required = true)
    @GenericField
    protected String subType;

    /** The status. */
    @XmlElement(required = true)
    @GenericField
    protected String status;

    /** The label. */
    @XmlElement(name = "beteckning", required = true)
    @GenericField
    protected String label;

    /** The temp label. */
    @XmlElement(name = "tempbeteckning", required = true)
    @GenericField
    protected String tempLabel;

    /** The org. */
    @XmlElement(name = "organ", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    @GenericField
    protected String org;

    /** The number value. */
    @XmlElement(name = "nummer", required = true)
    protected BigInteger numberValue;

    /** The title. */
    @XmlElement(name = "titel", required = true)
    @GenericField
    protected String title;

    /** The sub title. */
    @XmlElement(name = "undertitel", required = true)
    @GenericField
    protected String subTitle;

    /** The created date. */
    @XmlElement(name = "datum", required = true)
    protected String createdDate;

    /** The made public date. */
    @XmlElement(name = "publicerad", required = true)
    protected String madePublicDate;

    /** The system date. */
    @XmlElement(name = "systemdatum", required = true)
    protected String systemDate;

    /** The kall id. */
    @XmlElement(name = "kall_id", required = true)
    protected String kallId;

    /** The document format. */
    @XmlElement(name = "dokumentformat", required = true)
    protected String documentFormat;

    /** The document url text. */
    @XmlElement(name = "dokument_url_text", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String documentUrlText;

    /** The document url html. */
    @XmlElement(name = "dokument_url_html", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String documentUrlHtml;

    /** The document status url xml. */
    @XmlElement(name = "dokumentstatus_url_xml", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String documentStatusUrlXml;

    /** The committee report url xml. */
    @XmlElement(name = "utskottsforslag_url_xml", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String committeeReportUrlXml;

    /**
	 * Gets the hit.
	 *
	 * @return the hit
	 */
    @Basic
    @Column(name = "HIT", precision = 20)
    public BigInteger getHit() {
        return hit;
    }

    /**
	 * Sets the hit.
	 *
	 * @param value the new hit
	 */
    public void setHit(final BigInteger value) {
        this.hit = value;
    }

    /**
	 * Gets the id.
	 *
	 * @return the id
	 */
    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    /**
	 * Sets the id.
	 *
	 * @param value the new id
	 */
    public void setId(final String value) {
        this.id = value;
    }

    /**
	 * Gets the domain org.
	 *
	 * @return the domain org
	 */
    @Basic
    @Column(name = "DOMAIN_ORG")
    public String getDomainOrg() {
        return domainOrg;
    }

    /**
	 * Sets the domain org.
	 *
	 * @param value the new domain org
	 */
    public void setDomainOrg(final String value) {
        this.domainOrg = value;
    }

    /**
	 * Gets the document name.
	 *
	 * @return the document name
	 */
    @Basic
    @Column(name = "DOCUMENT_NAME")
    public String getDocumentName() {
        return documentName;
    }

    /**
	 * Sets the document name.
	 *
	 * @param value the new document name
	 */
    public void setDocumentName(final String value) {
        this.documentName = value;
    }

    /**
	 * Gets the debate name.
	 *
	 * @return the debate name
	 */
    @Basic
    @Column(name = "DEBATE_NAME")
    public String getDebateName() {
        return debateName;
    }

    /**
	 * Sets the debate name.
	 *
	 * @param value the new debate name
	 */
    public void setDebateName(final String value) {
        this.debateName = value;
    }

    /**
	 * Gets the note title.
	 *
	 * @return the note title
	 */
    @Basic
    @Column(name = "NOTE_TITLE")
    public String getNoteTitle() {
        return noteTitle;
    }

    /**
	 * Sets the note title.
	 *
	 * @param value the new note title
	 */
    public void setNoteTitle(final String value) {
        this.noteTitle = value;
    }

    /**
	 * Gets the note.
	 *
	 * @return the note
	 */
    @Basic
    @Column(name = "NOTE", length = 10485760)
    public String getNote() {
        return note;
    }

    /**
	 * Sets the note.
	 *
	 * @param value the new note
	 */
    public void setNote(final String value) {
        this.note = value;
    }

    /**
	 * Gets the summary.
	 *
	 * @return the summary
	 */
    @Basic
    @Column(name = "SUMMARY",length = 10485760)
    public String getSummary() {
        return summary;
    }

    /**
	 * Sets the summary.
	 *
	 * @param value the new summary
	 */
    public void setSummary(final String value) {
        this.summary = value;
    }

    /**
	 * Gets the database source.
	 *
	 * @return the database source
	 */
    @Basic
    @Column(name = "DATABASE_SOURCE")
    public String getDatabaseSource() {
        return databaseSource;
    }

    /**
	 * Sets the database source.
	 *
	 * @param value the new database source
	 */
    public void setDatabaseSource(final String value) {
        this.databaseSource = value;
    }

    /**
	 * Gets the origin.
	 *
	 * @return the origin
	 */
    @Basic
    @Column(name = "ORIGIN")
    public String getOrigin() {
        return origin;
    }

    /**
	 * Sets the origin.
	 *
	 * @param value the new origin
	 */
    public void setOrigin(final String value) {
        this.origin = value;
    }

    /**
	 * Gets the lang.
	 *
	 * @return the lang
	 */
    @Basic
    @Column(name = "LANG")
    public String getLang() {
        return lang;
    }

    /**
	 * Sets the lang.
	 *
	 * @param value the new lang
	 */
    public void setLang(final String value) {
        this.lang = value;
    }

    /**
	 * Gets the rm.
	 *
	 * @return the rm
	 */
    @Basic
    @Column(name = "RM")
    public String getRm() {
        return rm;
    }

    /**
	 * Sets the rm.
	 *
	 * @param value the new rm
	 */
    public void setRm(final String value) {
        this.rm = value;
    }

    /**
	 * Gets the related id.
	 *
	 * @return the related id
	 */
    @Basic
    @Column(name = "RELATED_ID")
    public String getRelatedId() {
        return relatedId;
    }

    /**
	 * Sets the related id.
	 *
	 * @param value the new related id
	 */
    public void setRelatedId(final String value) {
        this.relatedId = value;
    }

    /**
	 * Gets the document type.
	 *
	 * @return the document type
	 */
    @Basic
    @Column(name = "DOCUMENT_TYPE")
    public String getDocumentType() {
        return documentType;
    }

    /**
	 * Sets the document type.
	 *
	 * @param value the new document type
	 */
    public void setDocumentType(final String value) {
        this.documentType = value;
    }

    /**
	 * Gets the doc type.
	 *
	 * @return the doc type
	 */
    @Basic
    @Column(name = "DOC_TYPE")
    public String getDocType() {
        return docType;
    }

    /**
	 * Sets the doc type.
	 *
	 * @param value the new doc type
	 */
    public void setDocType(final String value) {
        this.docType = value;
    }

    /**
	 * Gets the sub type.
	 *
	 * @return the sub type
	 */
    @Basic
    @Column(name = "SUB_TYPE")
    public String getSubType() {
        return subType;
    }

    /**
	 * Sets the sub type.
	 *
	 * @param value the new sub type
	 */
    public void setSubType(final String value) {
        this.subType = value;
    }

    /**
	 * Gets the status.
	 *
	 * @return the status
	 */
    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    /**
	 * Sets the status.
	 *
	 * @param value the new status
	 */
    public void setStatus(final String value) {
        this.status = value;
    }

    /**
	 * Gets the label.
	 *
	 * @return the label
	 */
    @Basic
    @Column(name = "LABEL")
    public String getLabel() {
        return label;
    }

    /**
	 * Sets the label.
	 *
	 * @param value the new label
	 */
    public void setLabel(final String value) {
        this.label = value;
    }

    /**
	 * Gets the temp label.
	 *
	 * @return the temp label
	 */
    @Basic
    @Column(name = "TEMP_LABEL")
    public String getTempLabel() {
        return tempLabel;
    }

    /**
	 * Sets the temp label.
	 *
	 * @param value the new temp label
	 */
    public void setTempLabel(final String value) {
        this.tempLabel = value;
    }

    /**
	 * Gets the org.
	 *
	 * @return the org
	 */
    @Basic
    @Column(name = "ORG")
    public String getOrg() {
        return org;
    }

    /**
	 * Sets the org.
	 *
	 * @param value the new org
	 */
    public void setOrg(final String value) {
        this.org = value;
    }

    /**
	 * Gets the number value.
	 *
	 * @return the number value
	 */
    @Basic
    @Column(name = "NUMBER_VALUE", precision = 20)
    public BigInteger getNumberValue() {
        return numberValue;
    }

    /**
	 * Sets the number value.
	 *
	 * @param value the new number value
	 */
    public void setNumberValue(final BigInteger value) {
        this.numberValue = value;
    }

    /**
	 * Gets the title.
	 *
	 * @return the title
	 */
    @Basic
    @Column(name = "TITLE", length = 65536)
    public String getTitle() {
        return title;
    }

    /**
	 * Sets the title.
	 *
	 * @param value the new title
	 */
    public void setTitle(final String value) {
        this.title = value;
    }

    /**
	 * Gets the sub title.
	 *
	 * @return the sub title
	 */
    @Basic
    @Column(name = "SUB_TITLE", length = 65536)
    public String getSubTitle() {
        return subTitle;
    }

    /**
	 * Sets the sub title.
	 *
	 * @param value the new sub title
	 */
    public void setSubTitle(final String value) {
        this.subTitle = value;
    }

    /**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
    @Basic
    @Column(name = "CREATED_DATE")
    public String getCreatedDate() {
        return createdDate;
    }

    /**
	 * Sets the created date.
	 *
	 * @param value the new created date
	 */
    public void setCreatedDate(final String value) {
        this.createdDate = value;
    }

    /**
	 * Gets the made public date.
	 *
	 * @return the made public date
	 */
    @Basic
    @Column(name = "MADE_PUBLIC_DATE")
    public String getMadePublicDate() {
        return madePublicDate;
    }

    /**
	 * Sets the made public date.
	 *
	 * @param value the new made public date
	 */
    public void setMadePublicDate(final String value) {
        this.madePublicDate = value;
    }

    /**
	 * Gets the system date.
	 *
	 * @return the system date
	 */
    @Basic
    @Column(name = "SYSTEM_DATE")
    public String getSystemDate() {
        return systemDate;
    }

    /**
	 * Sets the system date.
	 *
	 * @param value the new system date
	 */
    public void setSystemDate(final String value) {
        this.systemDate = value;
    }

    /**
	 * Gets the kall id.
	 *
	 * @return the kall id
	 */
    @Basic
    @Column(name = "KALL_ID")
    public String getKallId() {
        return kallId;
    }

    /**
	 * Sets the kall id.
	 *
	 * @param value the new kall id
	 */
    public void setKallId(final String value) {
        this.kallId = value;
    }

    /**
	 * Gets the document format.
	 *
	 * @return the document format
	 */
    @Basic
    @Column(name = "DOCUMENT_FORMAT")
    public String getDocumentFormat() {
        return documentFormat;
    }

    /**
	 * Sets the document format.
	 *
	 * @param value the new document format
	 */
    public void setDocumentFormat(final String value) {
        this.documentFormat = value;
    }

    /**
	 * Gets the document url text.
	 *
	 * @return the document url text
	 */
    @Basic
    @Column(name = "DOCUMENT_URL_TEXT")
    public String getDocumentUrlText() {
        return documentUrlText;
    }

    /**
	 * Sets the document url text.
	 *
	 * @param value the new document url text
	 */
    public void setDocumentUrlText(final String value) {
        this.documentUrlText = value;
    }

    /**
	 * Gets the document url html.
	 *
	 * @return the document url html
	 */
    @Basic
    @Column(name = "DOCUMENT_URL_HTML")
    public String getDocumentUrlHtml() {
        return documentUrlHtml;
    }

    /**
	 * Sets the document url html.
	 *
	 * @param value the new document url html
	 */
    public void setDocumentUrlHtml(final String value) {
        this.documentUrlHtml = value;
    }

    /**
	 * Gets the document status url xml.
	 *
	 * @return the document status url xml
	 */
    @Basic
    @Column(name = "DOCUMENT_STATUS_URL_XML")
    public String getDocumentStatusUrlXml() {
        return documentStatusUrlXml;
    }

    /**
	 * Sets the document status url xml.
	 *
	 * @param value the new document status url xml
	 */
    public void setDocumentStatusUrlXml(final String value) {
        this.documentStatusUrlXml = value;
    }

    /**
	 * Gets the committee report url xml.
	 *
	 * @return the committee report url xml
	 */
    @Basic
    @Column(name = "COMMITTEE_REPORT_URL_XML")
    public String getCommitteeReportUrlXml() {
        return committeeReportUrlXml;
    }

    /**
	 * Sets the committee report url xml.
	 *
	 * @param value the new committee report url xml
	 */
    public void setCommitteeReportUrlXml(final String value) {
        this.committeeReportUrlXml = value;
    }

    /**
	 * With hit.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withHit(final BigInteger value) {
        setHit(value);
        return this;
    }

    /**
	 * With id.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withId(final String value) {
        setId(value);
        return this;
    }

    /**
	 * With domain org.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withDomainOrg(final String value) {
        setDomainOrg(value);
        return this;
    }

    /**
	 * With document name.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withDocumentName(final String value) {
        setDocumentName(value);
        return this;
    }

    /**
	 * With debate name.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withDebateName(final String value) {
        setDebateName(value);
        return this;
    }

    /**
	 * With note title.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withNoteTitle(final String value) {
        setNoteTitle(value);
        return this;
    }

    /**
	 * With note.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withNote(final String value) {
        setNote(value);
        return this;
    }

    /**
	 * With summary.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withSummary(final String value) {
        setSummary(value);
        return this;
    }

    /**
	 * With database source.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withDatabaseSource(final String value) {
        setDatabaseSource(value);
        return this;
    }

    /**
	 * With origin.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withOrigin(final String value) {
        setOrigin(value);
        return this;
    }

    /**
	 * With lang.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withLang(final String value) {
        setLang(value);
        return this;
    }

    /**
	 * With rm.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withRm(final String value) {
        setRm(value);
        return this;
    }

    /**
	 * With related id.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withRelatedId(final String value) {
        setRelatedId(value);
        return this;
    }

    /**
	 * With document type.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withDocumentType(final String value) {
        setDocumentType(value);
        return this;
    }

    /**
	 * With doc type.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withDocType(final String value) {
        setDocType(value);
        return this;
    }

    /**
	 * With sub type.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withSubType(final String value) {
        setSubType(value);
        return this;
    }

    /**
	 * With status.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withStatus(final String value) {
        setStatus(value);
        return this;
    }

    /**
	 * With label.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withLabel(final String value) {
        setLabel(value);
        return this;
    }

    /**
	 * With temp label.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withTempLabel(final String value) {
        setTempLabel(value);
        return this;
    }

    /**
	 * With org.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withOrg(final String value) {
        setOrg(value);
        return this;
    }

    /**
	 * With number value.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withNumberValue(final BigInteger value) {
        setNumberValue(value);
        return this;
    }

    /**
	 * With title.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withTitle(final String value) {
        setTitle(value);
        return this;
    }

    /**
	 * With sub title.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withSubTitle(final String value) {
        setSubTitle(value);
        return this;
    }

    /**
	 * With created date.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withCreatedDate(final String value) {
        setCreatedDate(value);
        return this;
    }

    /**
	 * With made public date.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withMadePublicDate(final String value) {
        setMadePublicDate(value);
        return this;
    }

    /**
	 * With system date.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withSystemDate(final String value) {
        setSystemDate(value);
        return this;
    }

    /**
	 * With kall id.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withKallId(final String value) {
        setKallId(value);
        return this;
    }

    /**
	 * With document format.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withDocumentFormat(final String value) {
        setDocumentFormat(value);
        return this;
    }

    /**
	 * With document url text.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withDocumentUrlText(final String value) {
        setDocumentUrlText(value);
        return this;
    }

    /**
	 * With document url html.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withDocumentUrlHtml(final String value) {
        setDocumentUrlHtml(value);
        return this;
    }

    /**
	 * With document status url xml.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withDocumentStatusUrlXml(final String value) {
        setDocumentStatusUrlXml(value);
        return this;
    }

    /**
	 * With committee report url xml.
	 *
	 * @param value the value
	 * @return the document element
	 */
    public DocumentElement withCommitteeReportUrlXml(final String value) {
        setCommitteeReportUrlXml(value);
        return this;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}



    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
	public boolean equals(final Object object) {
    	return EqualsBuilder.reflectionEquals(this,object);
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
