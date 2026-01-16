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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Index;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class GovernmentBodyData.
 * Represents government body information from ESV (Swedish Financial Management Authority).
 * Data source: Myndighetsinformation.xls
 */
@Entity
@Table(name = "government_body_data", indexes = {
    @Index(name = "idx_gov_body_year", columnList = "year"),
    @Index(name = "idx_gov_body_name", columnList = "name"),
    @Index(name = "idx_gov_body_ministry", columnList = "ministry"),
    @Index(name = "idx_gov_body_org_number", columnList = "org_number")
})
public class GovernmentBodyData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "year", nullable = false)
	private Integer year;

	@Column(name = "name", nullable = false, length = 500)
	private String name;

	@Column(name = "consecutive_number")
	private Integer consecutiveNumber;

	@Column(name = "government_body_id", length = 100)
	private String governmentBodyId;

	@Column(name = "m_code", length = 50)
	private String mCode;

	@Column(name = "ministry", length = 500)
	private String ministry;

	@Column(name = "org_number", length = 50)
	private String orgNumber;

	@Column(name = "head_count")
	private Integer headCount;

	@Column(name = "annual_work_head_count")
	private Integer annualWorkHeadCount;

	@Column(name = "vat", length = 50)
	private String vat;

	@Column(name = "comment", length = 1000)
	private String comment;

	/**
	 * Instantiates a new government body data.
	 */
	public GovernmentBodyData() {
		super();
	}

	/**
	 * Instantiates a new government body data.
	 *
	 * @param year                 the year
	 * @param name                 the name
	 * @param consecutiveNumber    the consecutive number
	 * @param governmentBodyId     the government body id
	 * @param mCode                the m code
	 * @param ministry             the ministry
	 * @param orgNumber            the org number
	 * @param headCount            the head count
	 * @param annualWorkHeadCount  the annual work head count
	 * @param vat                  the vat
	 * @param comment              the comment
	 */
	public GovernmentBodyData(final Integer year, final String name, final Integer consecutiveNumber,
			final String governmentBodyId, final String mCode, final String ministry, final String orgNumber,
			final Integer headCount, final Integer annualWorkHeadCount, final String vat, final String comment) {
		this.year = year;
		this.name = name;
		this.consecutiveNumber = consecutiveNumber;
		this.governmentBodyId = governmentBodyId;
		this.mCode = mCode;
		this.ministry = ministry;
		this.orgNumber = orgNumber;
		this.headCount = headCount;
		this.annualWorkHeadCount = annualWorkHeadCount;
		this.vat = vat;
		this.comment = comment;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(final Integer year) {
		this.year = year;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Gets the consecutive number.
	 *
	 * @return the consecutive number
	 */
	public Integer getConsecutiveNumber() {
		return consecutiveNumber;
	}

	/**
	 * Sets the consecutive number.
	 *
	 * @param consecutiveNumber the new consecutive number
	 */
	public void setConsecutiveNumber(final Integer consecutiveNumber) {
		this.consecutiveNumber = consecutiveNumber;
	}

	/**
	 * Gets the government body id.
	 *
	 * @return the government body id
	 */
	public String getGovernmentBodyId() {
		return governmentBodyId;
	}

	/**
	 * Sets the government body id.
	 *
	 * @param governmentBodyId the new government body id
	 */
	public void setGovernmentBodyId(final String governmentBodyId) {
		this.governmentBodyId = governmentBodyId;
	}

	/**
	 * Gets the m code.
	 *
	 * @return the m code
	 */
	public String getmCode() {
		return mCode;
	}

	/**
	 * Sets the m code.
	 *
	 * @param mCode the new m code
	 */
	public void setmCode(final String mCode) {
		this.mCode = mCode;
	}

	/**
	 * Gets the ministry.
	 *
	 * @return the ministry
	 */
	public String getMinistry() {
		return ministry;
	}

	/**
	 * Sets the ministry.
	 *
	 * @param ministry the new ministry
	 */
	public void setMinistry(final String ministry) {
		this.ministry = ministry;
	}

	/**
	 * Gets the org number.
	 *
	 * @return the org number
	 */
	public String getOrgNumber() {
		return orgNumber;
	}

	/**
	 * Sets the org number.
	 *
	 * @param orgNumber the new org number
	 */
	public void setOrgNumber(final String orgNumber) {
		this.orgNumber = orgNumber;
	}

	/**
	 * Gets the head count.
	 *
	 * @return the head count
	 */
	public Integer getHeadCount() {
		return headCount;
	}

	/**
	 * Sets the head count.
	 *
	 * @param headCount the new head count
	 */
	public void setHeadCount(final Integer headCount) {
		this.headCount = headCount;
	}

	/**
	 * Gets the annual work head count.
	 *
	 * @return the annual work head count
	 */
	public Integer getAnnualWorkHeadCount() {
		return annualWorkHeadCount;
	}

	/**
	 * Sets the annual work head count.
	 *
	 * @param annualWorkHeadCount the new annual work head count
	 */
	public void setAnnualWorkHeadCount(final Integer annualWorkHeadCount) {
		this.annualWorkHeadCount = annualWorkHeadCount;
	}

	/**
	 * Gets the vat.
	 *
	 * @return the vat
	 */
	public String getVat() {
		return vat;
	}

	/**
	 * Sets the vat.
	 *
	 * @param vat the new vat
	 */
	public void setVat(final String vat) {
		this.vat = vat;
	}

	/**
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comment.
	 *
	 * @param comment the new comment
	 */
	public void setComment(final String comment) {
		this.comment = comment;
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
