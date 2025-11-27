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
package com.hack23.cia.model.internal.application.data.document.impl;

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
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hack23.cia.model.common.api.ModelObject;

/**
 * The Class ViewDocumentDataCommitteeReportUrl.
 * 
 * JPA Entity for view_document_data_committee_report_url database view.
 * 
 * Provides access to documents with committee report URLs for linkage analysis
 * and report validation workflows.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewDocumentDataCommitteeReportUrl", propOrder = {
    "id",
    "committeeReportUrlXml"
})
@Entity(name = "ViewDocumentDataCommitteeReportUrl")
@Table(name = "view_document_data_committee_report_url")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewDocumentDataCommitteeReportUrl implements ModelObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @XmlElement(required = true)
    protected String id;

    /** The committee report url xml. */
    @XmlElement(name = "committee_report_url_xml")
    protected String committeeReportUrlXml;

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
    public void setCommitteeReportUrlXml(String value) {
        this.committeeReportUrlXml = value;
    }

    /**
     * With id.
     *
     * @param value the value
     * @return the view document data committee report url
     */
    public ViewDocumentDataCommitteeReportUrl withId(String value) {
        setId(value);
        return this;
    }

    /**
     * With committee report url xml.
     *
     * @param value the value
     * @return the view document data committee report url
     */
    public ViewDocumentDataCommitteeReportUrl withCommitteeReportUrlXml(String value) {
        setCommitteeReportUrlXml(value);
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
