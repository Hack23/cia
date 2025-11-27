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

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
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
 * The Class ViewRiksdagenGovermentRolesEmbeddedId.
 * 
 * Composite key for government roles view.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenGovermentRolesEmbeddedId", propOrder = {
    "detail",
    "roleCode"
})
@Embeddable
public class ViewRiksdagenGovermentRolesEmbeddedId implements ModelObject {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "embedded_id_detail", required = true)
    protected String detail;

    @XmlElement(name = "role_code", required = true)
    protected String roleCode;

    /**
     * Gets the detail.
     *
     * @return the detail
     */
    @Basic
    @Column(name = "EMBEDDED_ID_DETAIL")
    public String getDetail() {
        return detail;
    }

    /**
     * Sets the detail.
     *
     * @param value the new detail
     */
    public void setDetail(String value) {
        this.detail = value;
    }

    /**
     * Gets the role code.
     *
     * @return the role code
     */
    @Basic
    @Column(name = "ROLE_CODE")
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * Sets the role code.
     *
     * @param value the new role code
     */
    public void setRoleCode(String value) {
        this.roleCode = value;
    }

    /**
     * With detail.
     *
     * @param value the value
     * @return the embedded id
     */
    public ViewRiksdagenGovermentRolesEmbeddedId withDetail(String value) {
        setDetail(value);
        return this;
    }

    /**
     * With role code.
     *
     * @param value the value
     * @return the embedded id
     */
    public ViewRiksdagenGovermentRolesEmbeddedId withRoleCode(String value) {
        setRoleCode(value);
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
