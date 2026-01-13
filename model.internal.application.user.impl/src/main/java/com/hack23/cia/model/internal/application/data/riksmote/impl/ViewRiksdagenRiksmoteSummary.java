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
 * The Class ViewRiksdagenRiksmoteSummary.
 * 
 * Provides comprehensive metrics for each riksmöte (parliamentary session).
 * Swedish Riksdag sessions run September to August (e.g., "2025/26").
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenRiksmoteSummary", propOrder = {
    "riksmote",
    "totalBallots",
    "activePoliticians",
    "totalVotes",
    "yesVotes",
    "noVotes",
    "abstainVotes",
    "absentVotes",
    "avgBornYear",
    "percentageMale",
    "percentageFemale",
    "avgYesRate",
    "avgNoRate",
    "avgAbstainRate",
    "avgAbsentRate",
    "activeParties",
    "sessionFirstVote",
    "sessionLastVote",
    "isElectionYear"
})
@Entity(name = "ViewRiksdagenRiksmoteSummary")
@Table(name = "view_riksdagen_riksmote_summary")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewRiksdagenRiksmoteSummary implements ModelObject {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "riksmote", required = true)
    @Id
    @Column(name = "riksmote")
    protected String riksmote;

    @XmlElement(name = "total_ballots", required = true)
    @Basic
    @Column(name = "total_ballots")
    protected Long totalBallots;

    @XmlElement(name = "active_politicians", required = true)
    @Basic
    @Column(name = "active_politicians")
    protected Long activePoliticians;

    @XmlElement(name = "total_votes", required = true)
    @Basic
    @Column(name = "total_votes")
    protected Long totalVotes;

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

    @XmlElement(name = "avg_born_year", required = true)
    @Basic
    @Column(name = "avg_born_year")
    protected BigDecimal avgBornYear;

    @XmlElement(name = "percentage_male", required = true)
    @Basic
    @Column(name = "percentage_male")
    protected BigDecimal percentageMale;

    @XmlElement(name = "percentage_female", required = true)
    @Basic
    @Column(name = "percentage_female")
    protected BigDecimal percentageFemale;

    @XmlElement(name = "avg_yes_rate", required = true)
    @Basic
    @Column(name = "avg_yes_rate")
    protected BigDecimal avgYesRate;

    @XmlElement(name = "avg_no_rate", required = true)
    @Basic
    @Column(name = "avg_no_rate")
    protected BigDecimal avgNoRate;

    @XmlElement(name = "avg_abstain_rate", required = true)
    @Basic
    @Column(name = "avg_abstain_rate")
    protected BigDecimal avgAbstainRate;

    @XmlElement(name = "avg_absent_rate", required = true)
    @Basic
    @Column(name = "avg_absent_rate")
    protected BigDecimal avgAbsentRate;

    @XmlElement(name = "active_parties", required = true)
    @Basic
    @Column(name = "active_parties")
    protected Long activeParties;

    @XmlElement(name = "session_first_vote", required = true, type = String.class)
    @XmlJavaTypeAdapter(XmlDateTypeAdapter.class)
    @XmlSchemaType(name = "date")
    @Basic
    @Column(name = "session_first_vote")
    @Temporal(TemporalType.DATE)
    protected Date sessionFirstVote;

    @XmlElement(name = "session_last_vote", required = true, type = String.class)
    @XmlJavaTypeAdapter(XmlDateTypeAdapter.class)
    @XmlSchemaType(name = "date")
    @Basic
    @Column(name = "session_last_vote")
    @Temporal(TemporalType.DATE)
    protected Date sessionLastVote;

    @XmlElement(name = "is_election_year", required = true)
    @Basic
    @Column(name = "is_election_year")
    protected Boolean isElectionYear;

    /**
     * Instantiates a new view riksdagen riksmote summary.
     */
    public ViewRiksdagenRiksmoteSummary() {
        super();
    }

    /**
     * Gets the riksmote.
     *
     * @return the riksmote
     */
    public String getRiksmote() {
        return riksmote;
    }

    /**
     * Sets the riksmote.
     *
     * @param riksmote the new riksmote
     */
    public void setRiksmote(final String riksmote) {
        this.riksmote = riksmote;
    }

    /**
     * Gets the total ballots.
     *
     * @return the total ballots
     */
    public Long getTotalBallots() {
        return totalBallots;
    }

    /**
     * Sets the total ballots.
     *
     * @param totalBallots the new total ballots
     */
    public void setTotalBallots(final Long totalBallots) {
        this.totalBallots = totalBallots;
    }

    /**
     * Gets the active politicians.
     *
     * @return the active politicians
     */
    public Long getActivePoliticians() {
        return activePoliticians;
    }

    /**
     * Sets the active politicians.
     *
     * @param activePoliticians the new active politicians
     */
    public void setActivePoliticians(final Long activePoliticians) {
        this.activePoliticians = activePoliticians;
    }

    /**
     * Gets the total votes.
     *
     * @return the total votes
     */
    public Long getTotalVotes() {
        return totalVotes;
    }

    /**
     * Sets the total votes.
     *
     * @param totalVotes the new total votes
     */
    public void setTotalVotes(final Long totalVotes) {
        this.totalVotes = totalVotes;
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
     * Gets the avg born year.
     *
     * @return the avg born year
     */
    public BigDecimal getAvgBornYear() {
        return avgBornYear;
    }

    /**
     * Sets the avg born year.
     *
     * @param avgBornYear the new avg born year
     */
    public void setAvgBornYear(final BigDecimal avgBornYear) {
        this.avgBornYear = avgBornYear;
    }

    /**
     * Gets the percentage male.
     *
     * @return the percentage male
     */
    public BigDecimal getPercentageMale() {
        return percentageMale;
    }

    /**
     * Sets the percentage male.
     *
     * @param percentageMale the new percentage male
     */
    public void setPercentageMale(final BigDecimal percentageMale) {
        this.percentageMale = percentageMale;
    }

    /**
     * Gets the percentage female.
     *
     * @return the percentage female
     */
    public BigDecimal getPercentageFemale() {
        return percentageFemale;
    }

    /**
     * Sets the percentage female.
     *
     * @param percentageFemale the new percentage female
     */
    public void setPercentageFemale(final BigDecimal percentageFemale) {
        this.percentageFemale = percentageFemale;
    }

    /**
     * Gets the avg yes rate.
     *
     * @return the avg yes rate
     */
    public BigDecimal getAvgYesRate() {
        return avgYesRate;
    }

    /**
     * Sets the avg yes rate.
     *
     * @param avgYesRate the new avg yes rate
     */
    public void setAvgYesRate(final BigDecimal avgYesRate) {
        this.avgYesRate = avgYesRate;
    }

    /**
     * Gets the avg no rate.
     *
     * @return the avg no rate
     */
    public BigDecimal getAvgNoRate() {
        return avgNoRate;
    }

    /**
     * Sets the avg no rate.
     *
     * @param avgNoRate the new avg no rate
     */
    public void setAvgNoRate(final BigDecimal avgNoRate) {
        this.avgNoRate = avgNoRate;
    }

    /**
     * Gets the avg abstain rate.
     *
     * @return the avg abstain rate
     */
    public BigDecimal getAvgAbstainRate() {
        return avgAbstainRate;
    }

    /**
     * Sets the avg abstain rate.
     *
     * @param avgAbstainRate the new avg abstain rate
     */
    public void setAvgAbstainRate(final BigDecimal avgAbstainRate) {
        this.avgAbstainRate = avgAbstainRate;
    }

    /**
     * Gets the avg absent rate.
     *
     * @return the avg absent rate
     */
    public BigDecimal getAvgAbsentRate() {
        return avgAbsentRate;
    }

    /**
     * Sets the avg absent rate.
     *
     * @param avgAbsentRate the new avg absent rate
     */
    public void setAvgAbsentRate(final BigDecimal avgAbsentRate) {
        this.avgAbsentRate = avgAbsentRate;
    }

    /**
     * Gets the active parties.
     *
     * @return the active parties
     */
    public Long getActiveParties() {
        return activeParties;
    }

    /**
     * Sets the active parties.
     *
     * @param activeParties the new active parties
     */
    public void setActiveParties(final Long activeParties) {
        this.activeParties = activeParties;
    }

    /**
     * Gets the session first vote.
     *
     * @return the session first vote
     */
    public Date getSessionFirstVote() {
        return sessionFirstVote;
    }

    /**
     * Sets the session first vote.
     *
     * @param sessionFirstVote the new session first vote
     */
    public void setSessionFirstVote(final Date sessionFirstVote) {
        this.sessionFirstVote = sessionFirstVote;
    }

    /**
     * Gets the session last vote.
     *
     * @return the session last vote
     */
    public Date getSessionLastVote() {
        return sessionLastVote;
    }

    /**
     * Sets the session last vote.
     *
     * @param sessionLastVote the new session last vote
     */
    public void setSessionLastVote(final Date sessionLastVote) {
        this.sessionLastVote = sessionLastVote;
    }

    /**
     * Gets the checks if is election year.
     *
     * @return the checks if is election year
     */
    public Boolean getIsElectionYear() {
        return isElectionYear;
    }

    /**
     * Sets the checks if is election year.
     *
     * @param isElectionYear the new checks if is election year
     */
    public void setIsElectionYear(final Boolean isElectionYear) {
        this.isElectionYear = isElectionYear;
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
