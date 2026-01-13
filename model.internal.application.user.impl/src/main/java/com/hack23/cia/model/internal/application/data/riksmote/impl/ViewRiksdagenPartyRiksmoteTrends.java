/*
 * Copyright 2010-2026 James Pether Sörling
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
package com.hack23.cia.model.internal.application.data.riksmote.impl;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * The Class ViewRiksdagenPartyRiksmoteTrends.
 * 
 * Analyzes party evolution across riksmöte sessions including size,
 * voting behavior, and market share trends.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenPartyRiksmoteTrends", propOrder = {
    "embeddedId",
    "partySize",
    "ballotsParticipated",
    "totalPartyVotes",
    "partyYesVotes",
    "partyNoVotes",
    "partyAbstainVotes",
    "partyAbsentVotes",
    "partyYesRate",
    "partyNoRate",
    "partyAbsentRate",
    "partyAttendanceRate",
    "partyMarketShare",
    "sizeRank",
    "attendanceRank"
})
@Entity(name = "ViewRiksdagenPartyRiksmoteTrends")
@Table(name = "view_riksdagen_party_riksmote_trends")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewRiksdagenPartyRiksmoteTrends implements ModelObject {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @XmlElement(name = "embedded_id", required = true)
    protected ViewRiksdagenPartyRiksmoteTrendsEmbeddedId embeddedId;

    @XmlElement(name = "party_size", required = true)
    @Basic
    @Column(name = "party_size")
    protected Long partySize;

    @XmlElement(name = "ballots_participated", required = true)
    @Basic
    @Column(name = "ballots_participated")
    protected Long ballotsParticipated;

    @XmlElement(name = "total_party_votes", required = true)
    @Basic
    @Column(name = "total_party_votes")
    protected Long totalPartyVotes;

    @XmlElement(name = "party_yes_votes", required = true)
    @Basic
    @Column(name = "party_yes_votes")
    protected Long partyYesVotes;

    @XmlElement(name = "party_no_votes", required = true)
    @Basic
    @Column(name = "party_no_votes")
    protected Long partyNoVotes;

    @XmlElement(name = "party_abstain_votes", required = true)
    @Basic
    @Column(name = "party_abstain_votes")
    protected Long partyAbstainVotes;

    @XmlElement(name = "party_absent_votes", required = true)
    @Basic
    @Column(name = "party_absent_votes")
    protected Long partyAbsentVotes;

    @XmlElement(name = "party_yes_rate", required = true)
    @Basic
    @Column(name = "party_yes_rate")
    protected BigDecimal partyYesRate;

    @XmlElement(name = "party_no_rate", required = true)
    @Basic
    @Column(name = "party_no_rate")
    protected BigDecimal partyNoRate;

    @XmlElement(name = "party_absent_rate", required = true)
    @Basic
    @Column(name = "party_absent_rate")
    protected BigDecimal partyAbsentRate;

    @XmlElement(name = "party_attendance_rate", required = true)
    @Basic
    @Column(name = "party_attendance_rate")
    protected BigDecimal partyAttendanceRate;

    @XmlElement(name = "party_market_share", required = true)
    @Basic
    @Column(name = "party_market_share")
    protected BigDecimal partyMarketShare;

    @XmlElement(name = "size_rank", required = true)
    @Basic
    @Column(name = "size_rank")
    protected Long sizeRank;

    @XmlElement(name = "attendance_rank", required = true)
    @Basic
    @Column(name = "attendance_rank")
    protected Long attendanceRank;

    /**
     * Instantiates a new view riksdagen party riksmote trends.
     */
    public ViewRiksdagenPartyRiksmoteTrends() {
        super();
    }

    /**
     * Gets the embedded id.
     *
     * @return the embedded id
     */
    public ViewRiksdagenPartyRiksmoteTrendsEmbeddedId getEmbeddedId() {
        return embeddedId;
    }

    /**
     * Sets the embedded id.
     *
     * @param embeddedId the new embedded id
     */
    public void setEmbeddedId(final ViewRiksdagenPartyRiksmoteTrendsEmbeddedId embeddedId) {
        this.embeddedId = embeddedId;
    }

    /**
     * Gets the party size.
     *
     * @return the party size
     */
    public Long getPartySize() {
        return partySize;
    }

    /**
     * Sets the party size.
     *
     * @param partySize the new party size
     */
    public void setPartySize(final Long partySize) {
        this.partySize = partySize;
    }

    /**
     * Gets the ballots participated.
     *
     * @return the ballots participated
     */
    public Long getBallotsParticipated() {
        return ballotsParticipated;
    }

    /**
     * Sets the ballots participated.
     *
     * @param ballotsParticipated the new ballots participated
     */
    public void setBallotsParticipated(final Long ballotsParticipated) {
        this.ballotsParticipated = ballotsParticipated;
    }

    /**
     * Gets the total party votes.
     *
     * @return the total party votes
     */
    public Long getTotalPartyVotes() {
        return totalPartyVotes;
    }

    /**
     * Sets the total party votes.
     *
     * @param totalPartyVotes the new total party votes
     */
    public void setTotalPartyVotes(final Long totalPartyVotes) {
        this.totalPartyVotes = totalPartyVotes;
    }

    /**
     * Gets the party yes votes.
     *
     * @return the party yes votes
     */
    public Long getPartyYesVotes() {
        return partyYesVotes;
    }

    /**
     * Sets the party yes votes.
     *
     * @param partyYesVotes the new party yes votes
     */
    public void setPartyYesVotes(final Long partyYesVotes) {
        this.partyYesVotes = partyYesVotes;
    }

    /**
     * Gets the party no votes.
     *
     * @return the party no votes
     */
    public Long getPartyNoVotes() {
        return partyNoVotes;
    }

    /**
     * Sets the party no votes.
     *
     * @param partyNoVotes the new party no votes
     */
    public void setPartyNoVotes(final Long partyNoVotes) {
        this.partyNoVotes = partyNoVotes;
    }

    /**
     * Gets the party abstain votes.
     *
     * @return the party abstain votes
     */
    public Long getPartyAbstainVotes() {
        return partyAbstainVotes;
    }

    /**
     * Sets the party abstain votes.
     *
     * @param partyAbstainVotes the new party abstain votes
     */
    public void setPartyAbstainVotes(final Long partyAbstainVotes) {
        this.partyAbstainVotes = partyAbstainVotes;
    }

    /**
     * Gets the party absent votes.
     *
     * @return the party absent votes
     */
    public Long getPartyAbsentVotes() {
        return partyAbsentVotes;
    }

    /**
     * Sets the party absent votes.
     *
     * @param partyAbsentVotes the new party absent votes
     */
    public void setPartyAbsentVotes(final Long partyAbsentVotes) {
        this.partyAbsentVotes = partyAbsentVotes;
    }

    /**
     * Gets the party yes rate.
     *
     * @return the party yes rate
     */
    public BigDecimal getPartyYesRate() {
        return partyYesRate;
    }

    /**
     * Sets the party yes rate.
     *
     * @param partyYesRate the new party yes rate
     */
    public void setPartyYesRate(final BigDecimal partyYesRate) {
        this.partyYesRate = partyYesRate;
    }

    /**
     * Gets the party no rate.
     *
     * @return the party no rate
     */
    public BigDecimal getPartyNoRate() {
        return partyNoRate;
    }

    /**
     * Sets the party no rate.
     *
     * @param partyNoRate the new party no rate
     */
    public void setPartyNoRate(final BigDecimal partyNoRate) {
        this.partyNoRate = partyNoRate;
    }

    /**
     * Gets the party absent rate.
     *
     * @return the party absent rate
     */
    public BigDecimal getPartyAbsentRate() {
        return partyAbsentRate;
    }

    /**
     * Sets the party absent rate.
     *
     * @param partyAbsentRate the new party absent rate
     */
    public void setPartyAbsentRate(final BigDecimal partyAbsentRate) {
        this.partyAbsentRate = partyAbsentRate;
    }

    /**
     * Gets the party attendance rate.
     *
     * @return the party attendance rate
     */
    public BigDecimal getPartyAttendanceRate() {
        return partyAttendanceRate;
    }

    /**
     * Sets the party attendance rate.
     *
     * @param partyAttendanceRate the new party attendance rate
     */
    public void setPartyAttendanceRate(final BigDecimal partyAttendanceRate) {
        this.partyAttendanceRate = partyAttendanceRate;
    }

    /**
     * Gets the party market share.
     *
     * @return the party market share
     */
    public BigDecimal getPartyMarketShare() {
        return partyMarketShare;
    }

    /**
     * Sets the party market share.
     *
     * @param partyMarketShare the new party market share
     */
    public void setPartyMarketShare(final BigDecimal partyMarketShare) {
        this.partyMarketShare = partyMarketShare;
    }

    /**
     * Gets the size rank.
     *
     * @return the size rank
     */
    public Long getSizeRank() {
        return sizeRank;
    }

    /**
     * Sets the size rank.
     *
     * @param sizeRank the new size rank
     */
    public void setSizeRank(final Long sizeRank) {
        this.sizeRank = sizeRank;
    }

    /**
     * Gets the attendance rank.
     *
     * @return the attendance rank
     */
    public Long getAttendanceRank() {
        return attendanceRank;
    }

    /**
     * Sets the attendance rank.
     *
     * @param attendanceRank the new attendance rank
     */
    public void setAttendanceRank(final Long attendanceRank) {
        this.attendanceRank = attendanceRank;
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
