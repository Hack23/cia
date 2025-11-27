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
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hack23.cia.model.common.api.ModelObject;

/**
 * The Class ViewRiksdagenGovermentProposals.
 * 
 * JPA Entity for view_riksdagen_goverment_proposals database view.
 * 
 * Provides access to government propositions (proposals) enabling analysis of
 * government legislative initiatives and policy proposals.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenGovermentProposals", propOrder = {
    "id",
    "title",
    "subTitle",
    "status",
    "org",
    "hangarId",
    "label",
    "madePublicDate",
    "numberValue",
    "documentStatusUrlXml"
})
@Entity(name = "ViewRiksdagenGovermentProposals")
@Table(name = "view_riksdagen_goverment_proposals")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewRiksdagenGovermentProposals implements ModelObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @XmlElement(required = true)
    protected String id;

    /** The title. */
    @XmlElement
    protected String title;

    /** The sub title. */
    @XmlElement(name = "sub_title")
    protected String subTitle;

    /** The status. */
    @XmlElement
    protected String status;

    /** The org. */
    @XmlElement
    protected String org;

    /** The hangar id. */
    @XmlElement(name = "hangar_id")
    protected String hangarId;

    /** The label. */
    @XmlElement
    protected String label;

    /** The made public date. */
    @XmlElement(name = "made_public_date")
    protected Date madePublicDate;

    /** The number value. */
    @XmlElement(name = "number_value")
    protected Integer numberValue;

    /** The document status url xml. */
    @XmlElement(name = "document_status_url_xml")
    protected String documentStatusUrlXml;

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
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    @Basic
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param value the new title
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the sub title.
     *
     * @return the sub title
     */
    @Basic
    @Column(name = "SUB_TITLE")
    public String getSubTitle() {
        return subTitle;
    }

    /**
     * Sets the sub title.
     *
     * @param value the new sub title
     */
    public void setSubTitle(String value) {
        this.subTitle = value;
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
    public void setStatus(String value) {
        this.status = value;
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
    public void setOrg(String value) {
        this.org = value;
    }

    /**
     * Gets the hangar id.
     *
     * @return the hangar id
     */
    @Basic
    @Column(name = "HANGAR_ID")
    public String getHangarId() {
        return hangarId;
    }

    /**
     * Sets the hangar id.
     *
     * @param value the new hangar id
     */
    public void setHangarId(String value) {
        this.hangarId = value;
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
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the made public date.
     *
     * @return the made public date
     */
    @Basic
    @Column(name = "MADE_PUBLIC_DATE")
    @Temporal(TemporalType.DATE)
    public Date getMadePublicDate() {
        return madePublicDate;
    }

    /**
     * Sets the made public date.
     *
     * @param value the new made public date
     */
    public void setMadePublicDate(Date value) {
        this.madePublicDate = value;
    }

    /**
     * Gets the number value.
     *
     * @return the number value
     */
    @Basic
    @Column(name = "NUMBER_VALUE")
    public Integer getNumberValue() {
        return numberValue;
    }

    /**
     * Sets the number value.
     *
     * @param value the new number value
     */
    public void setNumberValue(Integer value) {
        this.numberValue = value;
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
    public void setDocumentStatusUrlXml(String value) {
        this.documentStatusUrlXml = value;
    }

    /**
     * With id.
     *
     * @param value the value
     * @return the view riksdagen goverment proposals
     */
    public ViewRiksdagenGovermentProposals withId(String value) {
        setId(value);
        return this;
    }

    /**
     * With title.
     *
     * @param value the value
     * @return the view riksdagen goverment proposals
     */
    public ViewRiksdagenGovermentProposals withTitle(String value) {
        setTitle(value);
        return this;
    }

    /**
     * With sub title.
     *
     * @param value the value
     * @return the view riksdagen goverment proposals
     */
    public ViewRiksdagenGovermentProposals withSubTitle(String value) {
        setSubTitle(value);
        return this;
    }

    /**
     * With status.
     *
     * @param value the value
     * @return the view riksdagen goverment proposals
     */
    public ViewRiksdagenGovermentProposals withStatus(String value) {
        setStatus(value);
        return this;
    }

    /**
     * With org.
     *
     * @param value the value
     * @return the view riksdagen goverment proposals
     */
    public ViewRiksdagenGovermentProposals withOrg(String value) {
        setOrg(value);
        return this;
    }

    /**
     * With hangar id.
     *
     * @param value the value
     * @return the view riksdagen goverment proposals
     */
    public ViewRiksdagenGovermentProposals withHangarId(String value) {
        setHangarId(value);
        return this;
    }

    /**
     * With label.
     *
     * @param value the value
     * @return the view riksdagen goverment proposals
     */
    public ViewRiksdagenGovermentProposals withLabel(String value) {
        setLabel(value);
        return this;
    }

    /**
     * With made public date.
     *
     * @param value the value
     * @return the view riksdagen goverment proposals
     */
    public ViewRiksdagenGovermentProposals withMadePublicDate(Date value) {
        setMadePublicDate(value);
        return this;
    }

    /**
     * With number value.
     *
     * @param value the value
     * @return the view riksdagen goverment proposals
     */
    public ViewRiksdagenGovermentProposals withNumberValue(Integer value) {
        setNumberValue(value);
        return this;
    }

    /**
     * With document status url xml.
     *
     * @param value the value
     * @return the view riksdagen goverment proposals
     */
    public ViewRiksdagenGovermentProposals withDocumentStatusUrlXml(String value) {
        setDocumentStatusUrlXml(value);
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
