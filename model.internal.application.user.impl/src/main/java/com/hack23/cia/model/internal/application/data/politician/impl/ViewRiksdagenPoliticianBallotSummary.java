package com.hack23.cia.model.internal.application.data.politician.impl;

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
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hack23.cia.model.common.api.ModelObject;

/**
 * <p>
 * Java class for ViewRiksdagenPoliticianBallotSummary.
 * </p>
 *
 * <p>
 * Entity mapped to the view: VIEW_RIKSDAGEN_POLITICIAN_BALLOT_SUMMARY
 * </p>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenPoliticianBallotSummary", propOrder = {
    "personId",
    "firstName",
    "lastName",
    "party",
    "bornYear",
    "gender",
    "status",
    "electionRegion",
    "totalVotes",
    "yesPercentage",
    "noPercentage",
    "absenceRate",
    "rebelRate",
    "successRate",
    "votingDays",
    "firstVoteDate",
    "lastVoteDate",
    "votingParticipationRate",
    "loyaltyRate",
    "votingConsistencyScore",
    "analysisComment"
})
@Entity(name = "ViewRiksdagenPoliticianBallotSummary")
@Table(name = "VIEW_RIKSDAGEN_POLITICIAN_BALLOT_SUMMARY")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewRiksdagenPoliticianBallotSummary implements ModelObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The person id (primary key). */
    private String personId;

    /** The first name. */
    private String firstName;

    /** The last name. */
    private String lastName;

    /** The party. */
    private String party;

    /** The born year. */
    private int bornYear;

    /** The gender. */
    private String gender;

    /** The status. */
    private String status;

    /** The election region. */
    private String electionRegion;

    /** The total votes. */
    private long totalVotes;

    /** The yes percentage. */
    private double yesPercentage;

    /** The no percentage. */
    private double noPercentage;

    /** The absence rate. */
    private double absenceRate;

    /** The rebel rate. */
    private double rebelRate;

    /** The success rate. */
    private double successRate;

    /** The number of voting days. */
    private long votingDays;

    /** The first vote date. */
    private Date firstVoteDate;

    /** The last vote date. */
    private Date lastVoteDate;

    /** The voting participation rate. */
    private double votingParticipationRate;

    /** The loyalty rate. */
    private double loyaltyRate;

    /** The voting consistency score. */
    private double votingConsistencyScore;

    /** The analysis comment. */
    private String analysisComment;

    // -----------------------------------------------
    // JPA & XML Mappings
    // -----------------------------------------------

    /**
     * Gets the person id.
     *
     * @return the person id
     */
    @Id
    @Column(name = "PERSON_ID")
    public String getPersonId() {
        return personId;
    }

    /**
     * Sets the person id.
     *
     * @param value the new person id
     */
    public void setPersonId(final String value) {
        this.personId = value;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    @Basic
    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param value the new first name
     */
    public void setFirstName(final String value) {
        this.firstName = value;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    @Basic
    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param value the new last name
     */
    public void setLastName(final String value) {
        this.lastName = value;
    }

    /**
     * Gets the party.
     *
     * @return the party
     */
    @Basic
    @Column(name = "PARTY")
    public String getParty() {
        return party;
    }

    /**
     * Sets the party.
     *
     * @param value the new party
     */
    public void setParty(final String value) {
        this.party = value;
    }

    /**
     * Gets the born year.
     *
     * @return the born year
     */
    @Basic
    @Column(name = "BORN_YEAR")
    public int getBornYear() {
        return bornYear;
    }

    /**
     * Sets the born year.
     *
     * @param value the new born year
     */
    public void setBornYear(final int value) {
        this.bornYear = value;
    }

    /**
     * Gets the gender.
     *
     * @return the gender
     */
    @Basic
    @Column(name = "GENDER")
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     *
     * @param value the new gender
     */
    public void setGender(final String value) {
        this.gender = value;
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
     * Gets the election region.
     *
     * @return the election region
     */
    @Basic
    @Column(name = "ELECTION_REGION")
    public String getElectionRegion() {
        return electionRegion;
    }

    /**
     * Sets the election region.
     *
     * @param value the new election region
     */
    public void setElectionRegion(final String value) {
        this.electionRegion = value;
    }

    /**
     * Gets the total votes.
     *
     * @return the total votes
     */
    @Basic
    @Column(name = "TOTAL_VOTES", precision = 20)
    public long getTotalVotes() {
        return totalVotes;
    }

    /**
     * Sets the total votes.
     *
     * @param value the new total votes
     */
    public void setTotalVotes(final long value) {
        this.totalVotes = value;
    }

    /**
     * Gets the yes percentage.
     *
     * @return the yes percentage
     */
    @Basic
    @Column(name = "YES_PERCENTAGE", precision = 5, scale = 2)
    public double getYesPercentage() {
        return yesPercentage;
    }

    /**
     * Sets the yes percentage.
     *
     * @param value the new yes percentage
     */
    public void setYesPercentage(final double value) {
        this.yesPercentage = value;
    }

    /**
     * Gets the no percentage.
     *
     * @return the no percentage
     */
    @Basic
    @Column(name = "NO_PERCENTAGE", precision = 5, scale = 2)
    public double getNoPercentage() {
        return noPercentage;
    }

    /**
     * Sets the no percentage.
     *
     * @param value the new no percentage
     */
    public void setNoPercentage(final double value) {
        this.noPercentage = value;
    }

    /**
     * Gets the absence rate.
     *
     * @return the absence rate
     */
    @Basic
    @Column(name = "ABSENCE_RATE", precision = 5, scale = 2)
    public double getAbsenceRate() {
        return absenceRate;
    }

    /**
     * Sets the absence rate.
     *
     * @param value the new absence rate
     */
    public void setAbsenceRate(final double value) {
        this.absenceRate = value;
    }

    /**
     * Gets the rebel rate.
     *
     * @return the rebel rate
     */
    @Basic
    @Column(name = "REBEL_RATE", precision = 5, scale = 2)
    public double getRebelRate() {
        return rebelRate;
    }

    /**
     * Sets the rebel rate.
     *
     * @param value the new rebel rate
     */
    public void setRebelRate(final double value) {
        this.rebelRate = value;
    }

    /**
     * Gets the success rate.
     *
     * @return the success rate
     */
    @Basic
    @Column(name = "SUCCESS_RATE", precision = 5, scale = 2)
    public double getSuccessRate() {
        return successRate;
    }

    /**
     * Sets the success rate.
     *
     * @param value the new success rate
     */
    public void setSuccessRate(final double value) {
        this.successRate = value;
    }

    /**
     * Gets the voting days.
     *
     * @return the voting days
     */
    @Basic
    @Column(name = "VOTING_DAYS", precision = 20)
    public long getVotingDays() {
        return votingDays;
    }

    /**
     * Sets the voting days.
     *
     * @param value the new voting days
     */
    public void setVotingDays(final long value) {
        this.votingDays = value;
    }

    /**
     * Gets the first vote date.
     *
     * @return the first vote date
     */
    @Basic
    @Column(name = "FIRST_VOTE_DATE")
    @Temporal(TemporalType.DATE)
    public Date getFirstVoteDate() {
        return firstVoteDate;
    }

    /**
     * Sets the first vote date.
     *
     * @param value the new first vote date
     */
    public void setFirstVoteDate(final Date value) {
        this.firstVoteDate = value;
    }

    /**
     * Gets the last vote date.
     *
     * @return the last vote date
     */
    @Basic
    @Column(name = "LAST_VOTE_DATE")
    @Temporal(TemporalType.DATE)
    public Date getLastVoteDate() {
        return lastVoteDate;
    }

    /**
     * Sets the last vote date.
     *
     * @param value the new last vote date
     */
    public void setLastVoteDate(final Date value) {
        this.lastVoteDate = value;
    }

    /**
     * Gets the voting participation rate.
     *
     * @return the voting participation rate
     */
    @Basic
    @Column(name = "VOTING_PARTICIPATION_RATE", precision = 5, scale = 2)
    public double getVotingParticipationRate() {
        return votingParticipationRate;
    }

    /**
     * Sets the voting participation rate.
     *
     * @param value the new voting participation rate
     */
    public void setVotingParticipationRate(final double value) {
        this.votingParticipationRate = value;
    }

    /**
     * Gets the loyalty rate.
     *
     * @return the loyalty rate
     */
    @Basic
    @Column(name = "LOYALTY_RATE", precision = 5, scale = 2)
    public double getLoyaltyRate() {
        return loyaltyRate;
    }

    /**
     * Sets the loyalty rate.
     *
     * @param value the new loyalty rate
     */
    public void setLoyaltyRate(final double value) {
        this.loyaltyRate = value;
    }

    /**
     * Gets the voting consistency score.
     *
     * @return the voting consistency score
     */
    @Basic
    @Column(name = "VOTING_CONSISTENCY_SCORE", precision = 5, scale = 2)
    public double getVotingConsistencyScore() {
        return votingConsistencyScore;
    }

    /**
     * Sets the voting consistency score.
     *
     * @param value the new voting consistency score
     */
    public void setVotingConsistencyScore(final double value) {
        this.votingConsistencyScore = value;
    }

    /**
     * Gets the analysis comment.
     *
     * @return the analysis comment
     */
    @Basic
    @Column(name = "ANALYSIS_COMMENT")
    public String getAnalysisComment() {
        return analysisComment;
    }

    /**
     * Sets the analysis comment.
     *
     * @param value the new analysis comment
     */
    public void setAnalysisComment(final String value) {
        this.analysisComment = value;
    }

    // -----------------------------------------------
    // Builder-Style Setters
    // -----------------------------------------------

    /**
     * With person id.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withPersonId(final String value) {
        setPersonId(value);
        return this;
    }

    /**
     * With first name.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withFirstName(final String value) {
        setFirstName(value);
        return this;
    }

    /**
     * With last name.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withLastName(final String value) {
        setLastName(value);
        return this;
    }

    /**
     * With party.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withParty(final String value) {
        setParty(value);
        return this;
    }

    /**
     * With born year.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withBornYear(final int value) {
        setBornYear(value);
        return this;
    }

    /**
     * With gender.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withGender(final String value) {
        setGender(value);
        return this;
    }

    /**
     * With status.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withStatus(final String value) {
        setStatus(value);
        return this;
    }

    /**
     * With election region.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withElectionRegion(final String value) {
        setElectionRegion(value);
        return this;
    }

    /**
     * With total votes.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withTotalVotes(final long value) {
        setTotalVotes(value);
        return this;
    }

    /**
     * With yes percentage.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withYesPercentage(final double value) {
        setYesPercentage(value);
        return this;
    }

    /**
     * With no percentage.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withNoPercentage(final double value) {
        setNoPercentage(value);
        return this;
    }

    /**
     * With absence rate.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withAbsenceRate(final double value) {
        setAbsenceRate(value);
        return this;
    }

    /**
     * With rebel rate.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withRebelRate(final double value) {
        setRebelRate(value);
        return this;
    }

    /**
     * With success rate.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withSuccessRate(final double value) {
        setSuccessRate(value);
        return this;
    }

    /**
     * With voting days.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withVotingDays(final long value) {
        setVotingDays(value);
        return this;
    }

    /**
     * With first vote date.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withFirstVoteDate(final Date value) {
        setFirstVoteDate(value);
        return this;
    }

    /**
     * With last vote date.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withLastVoteDate(final Date value) {
        setLastVoteDate(value);
        return this;
    }

    /**
     * With voting participation rate.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withVotingParticipationRate(final double value) {
        setVotingParticipationRate(value);
        return this;
    }

    /**
     * With loyalty rate.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withLoyaltyRate(final double value) {
        setLoyaltyRate(value);
        return this;
    }

    /**
     * With voting consistency score.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withVotingConsistencyScore(final double value) {
        setVotingConsistencyScore(value);
        return this;
    }

    /**
     * With analysis comment.
     *
     * @param value the value
     * @return the view riksdagen politician ballot summary
     */
    public ViewRiksdagenPoliticianBallotSummary withAnalysisComment(final String value) {
        setAnalysisComment(value);
        return this;
    }

    // -----------------------------------------------
    // Object Overrides
    // -----------------------------------------------

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
    public boolean equals(final Object obj) {
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
