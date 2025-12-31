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

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * The Class ViewRiksdagenGovermentRoles.
 * 
 * JPA Entity for view_riksdagen_goverment_roles database view.
 * 
 * Aggregates government role assignments showing ministerial roles,
 * assignment counts, and temporal ranges for government composition analysis.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenGovermentRoles", propOrder = {
    "embeddedId",
    "totalAssignments",
    "firstAssignmentDate",
    "lastAssignmentDate"
})
@Entity(name = "ViewRiksdagenGovermentRoles")
@Table(name = "view_riksdagen_goverment_roles")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewRiksdagenGovermentRoles implements ModelObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The embedded id. */
    @XmlElement(required = true)
    protected ViewRiksdagenGovermentRolesEmbeddedId embeddedId;

    /** The total assignments. */
    protected long totalAssignments;

    /** The first assignment date. */
    @XmlElement(name = "first_assignment_date")
    protected Date firstAssignmentDate;

    /** The last assignment date. */
    @XmlElement(name = "last_assignment_date")
    protected Date lastAssignmentDate;

    /**
     * Gets the embedded id.
     *
     * @return the embedded id
     */
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "detail", column = @Column(name = "EMBEDDED_ID_DETAIL")),
        @AttributeOverride(name = "roleCode", column = @Column(name = "ROLE_CODE"))
    })
    public ViewRiksdagenGovermentRolesEmbeddedId getEmbeddedId() {
        return embeddedId;
    }

    /**
     * Sets the embedded id.
     *
     * @param value the new embedded id
     */
    public void setEmbeddedId(ViewRiksdagenGovermentRolesEmbeddedId value) {
        this.embeddedId = value;
    }

    /**
     * Gets the total assignments.
     *
     * @return the total assignments
     */
    @Basic
    @Column(name = "TOTAL_ASSIGNMENTS")
    public long getTotalAssignments() {
        return totalAssignments;
    }

    /**
     * Sets the total assignments.
     *
     * @param value the new total assignments
     */
    public void setTotalAssignments(long value) {
        this.totalAssignments = value;
    }

    /**
     * Gets the first assignment date.
     *
     * @return the first assignment date
     */
    @Basic
    @Column(name = "FIRST_ASSIGNMENT_DATE")
    @Temporal(TemporalType.DATE)
    public Date getFirstAssignmentDate() {
        return firstAssignmentDate;
    }

    /**
     * Sets the first assignment date.
     *
     * @param value the new first assignment date
     */
    public void setFirstAssignmentDate(Date value) {
        this.firstAssignmentDate = value;
    }

    /**
     * Gets the last assignment date.
     *
     * @return the last assignment date
     */
    @Basic
    @Column(name = "LAST_ASSIGNMENT_DATE")
    @Temporal(TemporalType.DATE)
    public Date getLastAssignmentDate() {
        return lastAssignmentDate;
    }

    /**
     * Sets the last assignment date.
     *
     * @param value the new last assignment date
     */
    public void setLastAssignmentDate(Date value) {
        this.lastAssignmentDate = value;
    }

    /**
     * With embedded id.
     *
     * @param value the value
     * @return the view riksdagen goverment roles
     */
    public ViewRiksdagenGovermentRoles withEmbeddedId(ViewRiksdagenGovermentRolesEmbeddedId value) {
        setEmbeddedId(value);
        return this;
    }

    /**
     * With total assignments.
     *
     * @param value the value
     * @return the view riksdagen goverment roles
     */
    public ViewRiksdagenGovermentRoles withTotalAssignments(long value) {
        setTotalAssignments(value);
        return this;
    }

    /**
     * With first assignment date.
     *
     * @param value the value
     * @return the view riksdagen goverment roles
     */
    public ViewRiksdagenGovermentRoles withFirstAssignmentDate(Date value) {
        setFirstAssignmentDate(value);
        return this;
    }

    /**
     * With last assignment date.
     *
     * @param value the value
     * @return the view riksdagen goverment roles
     */
    public ViewRiksdagenGovermentRoles withLastAssignmentDate(Date value) {
        setLastAssignmentDate(value);
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
