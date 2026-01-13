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
 * The Class ViewRiksdagenPoliticianRiksmotePerformance.
 * 
 * Tracks individual politician performance metrics across riksmöte sessions.
 * Enables career trajectory analysis based on parliamentary sessions.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenPoliticianRiksmotePerformance", propOrder = {
    "embeddedId",
    "firstName",
    "lastName",
    "party",
    "gender",
    "bornYear",
    "ballotParticipation",
    "totalVotesCast",
    "yesVotes",
    "noVotes",
    "abstainVotes",
    "absentVotes",
    "yesRate",
    "noRate",
    "abstainRate",
    "absentRate",
    "attendanceScore"
})
@Entity(name = "ViewRiksdagenPoliticianRiksmotePerformance")
@Table(name = "view_riksdagen_politician_riksmote_performance")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewRiksdagenPoliticianRiksmotePerformance implements ModelObject {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @XmlElement(name = "embedded_id", required = true)
    protected ViewRiksdagenPoliticianRiksmotePerformanceEmbeddedId embeddedId;

    @XmlElement(name = "first_name", required = true)
    @Basic
    @Column(name = "first_name")
    protected String firstName;

    @XmlElement(name = "last_name", required = true)
    @Basic
    @Column(name = "last_name")
    protected String lastName;

    @XmlElement(name = "party", required = true)
    @Basic
    @Column(name = "party")
    protected String party;

    @XmlElement(name = "gender", required = true)
    @Basic
    @Column(name = "gender")
    protected String gender;

    @XmlElement(name = "born_year", required = true)
    @Basic
    @Column(name = "born_year")
    protected Integer bornYear;

    @XmlElement(name = "ballot_participation", required = true)
    @Basic
    @Column(name = "ballot_participation")
    protected Long ballotParticipation;

    @XmlElement(name = "total_votes_cast", required = true)
    @Basic
    @Column(name = "total_votes_cast")
    protected Long totalVotesCast;

    @XmlElement(name = "yes_votes", required = true)
    @Basic
    @Column(name = "yes_votes")
    protected Long yesVotes;

    @XmlElement(name = "no_votes", required = true)
    @Basic
    @Column(name = "no_votes")
    protected Long noVotes;

    @XmlElement(name = "abstain_votes", required = true)
    @Basic
    @Column(name = "abstain_votes")
    protected Long abstainVotes;

    @XmlElement(name = "absent_votes", required = true)
    @Basic
    @Column(name = "absent_votes")
    protected Long absentVotes;

    @XmlElement(name = "yes_rate", required = true)
    @Basic
    @Column(name = "yes_rate")
    protected BigDecimal yesRate;

    @XmlElement(name = "no_rate", required = true)
    @Basic
    @Column(name = "no_rate")
    protected BigDecimal noRate;

    @XmlElement(name = "abstain_rate", required = true)
    @Basic
    @Column(name = "abstain_rate")
    protected BigDecimal abstainRate;

    @XmlElement(name = "absent_rate", required = true)
    @Basic
    @Column(name = "absent_rate")
    protected BigDecimal absentRate;

    @XmlElement(name = "attendance_score", required = true)
    @Basic
    @Column(name = "attendance_score")
    protected BigDecimal attendanceScore;

    /**
     * Instantiates a new view riksdagen politician riksmote performance.
     */
    public ViewRiksdagenPoliticianRiksmotePerformance() {
        super();
    }

    /**
     * Gets the embedded id.
     *
     * @return the embedded id
     */
    public ViewRiksdagenPoliticianRiksmotePerformanceEmbeddedId getEmbeddedId() {
        return embeddedId;
    }

    /**
     * Sets the embedded id.
     *
     * @param embeddedId the new embedded id
     */
    public void setEmbeddedId(final ViewRiksdagenPoliticianRiksmotePerformanceEmbeddedId embeddedId) {
        this.embeddedId = embeddedId;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the party.
     *
     * @return the party
     */
    public String getParty() {
        return party;
    }

    /**
     * Sets the party.
     *
     * @param party the new party
     */
    public void setParty(final String party) {
        this.party = party;
    }

    /**
     * Gets the gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     *
     * @param gender the new gender
     */
    public void setGender(final String gender) {
        this.gender = gender;
    }

    /**
     * Gets the born year.
     *
     * @return the born year
     */
    public Integer getBornYear() {
        return bornYear;
    }

    /**
     * Sets the born year.
     *
     * @param bornYear the new born year
     */
    public void setBornYear(final Integer bornYear) {
        this.bornYear = bornYear;
    }

    /**
     * Gets the ballot participation.
     *
     * @return the ballot participation
     */
    public Long getBallotParticipation() {
        return ballotParticipation;
    }

    /**
     * Sets the ballot participation.
     *
     * @param ballotParticipation the new ballot participation
     */
    public void setBallotParticipation(final Long ballotParticipation) {
        this.ballotParticipation = ballotParticipation;
    }

    /**
     * Gets the total votes cast.
     *
     * @return the total votes cast
     */
    public Long getTotalVotesCast() {
        return totalVotesCast;
    }

    /**
     * Sets the total votes cast.
     *
     * @param totalVotesCast the new total votes cast
     */
    public void setTotalVotesCast(final Long totalVotesCast) {
        this.totalVotesCast = totalVotesCast;
    }

    /**
     * Gets the yes votes.
     *
     * @return the yes votes
     */
    public Long getYesVotes() {
        return yesVotes;
    }

    /**
     * Sets the yes votes.
     *
     * @param yesVotes the new yes votes
     */
    public void setYesVotes(final Long yesVotes) {
        this.yesVotes = yesVotes;
    }

    /**
     * Gets the no votes.
     *
     * @return the no votes
     */
    public Long getNoVotes() {
        return noVotes;
    }

    /**
     * Sets the no votes.
     *
     * @param noVotes the new no votes
     */
    public void setNoVotes(final Long noVotes) {
        this.noVotes = noVotes;
    }

    /**
     * Gets the abstain votes.
     *
     * @return the abstain votes
     */
    public Long getAbstainVotes() {
        return abstainVotes;
    }

    /**
     * Sets the abstain votes.
     *
     * @param abstainVotes the new abstain votes
     */
    public void setAbstainVotes(final Long abstainVotes) {
        this.abstainVotes = abstainVotes;
    }

    /**
     * Gets the absent votes.
     *
     * @return the absent votes
     */
    public Long getAbsentVotes() {
        return absentVotes;
    }

    /**
     * Sets the absent votes.
     *
     * @param absentVotes the new absent votes
     */
    public void setAbsentVotes(final Long absentVotes) {
        this.absentVotes = absentVotes;
    }

    /**
     * Gets the yes rate.
     *
     * @return the yes rate
     */
    public BigDecimal getYesRate() {
        return yesRate;
    }

    /**
     * Sets the yes rate.
     *
     * @param yesRate the new yes rate
     */
    public void setYesRate(final BigDecimal yesRate) {
        this.yesRate = yesRate;
    }

    /**
     * Gets the no rate.
     *
     * @return the no rate
     */
    public BigDecimal getNoRate() {
        return noRate;
    }

    /**
     * Sets the no rate.
     *
     * @param noRate the new no rate
     */
    public void setNoRate(final BigDecimal noRate) {
        this.noRate = noRate;
    }

    /**
     * Gets the abstain rate.
     *
     * @return the abstain rate
     */
    public BigDecimal getAbstainRate() {
        return abstainRate;
    }

    /**
     * Sets the abstain rate.
     *
     * @param abstainRate the new abstain rate
     */
    public void setAbstainRate(final BigDecimal abstainRate) {
        this.abstainRate = abstainRate;
    }

    /**
     * Gets the absent rate.
     *
     * @return the absent rate
     */
    public BigDecimal getAbsentRate() {
        return absentRate;
    }

    /**
     * Sets the absent rate.
     *
     * @param absentRate the new absent rate
     */
    public void setAbsentRate(final BigDecimal absentRate) {
        this.absentRate = absentRate;
    }

    /**
     * Gets the attendance score.
     *
     * @return the attendance score
     */
    public BigDecimal getAttendanceScore() {
        return attendanceScore;
    }

    /**
     * Sets the attendance score.
     *
     * @param attendanceScore the new attendance score
     */
    public void setAttendanceScore(final BigDecimal attendanceScore) {
        this.attendanceScore = attendanceScore;
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
