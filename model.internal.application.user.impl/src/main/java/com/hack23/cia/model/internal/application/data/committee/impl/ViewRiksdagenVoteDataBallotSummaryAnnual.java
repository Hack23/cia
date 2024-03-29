//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.02.25 at 12:05:09 AM CET
//


package com.hack23.cia.model.internal.application.data.committee.impl;


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
 * <p>Java class for ViewRiksdagenVoteDataBallotSummaryAnnual complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ViewRiksdagenVoteDataBallotSummaryAnnual"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="vote_date" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="avg_born_year" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="total_votes" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="yes_votes" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="no_votes" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="abstain_votes" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="absent_votes" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="percentage_yes" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="percentage_no" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="percentage_absent" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="percentage_abstain" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="number_ballots" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="approved_ballots" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="percentage_approved" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="avg_percentage_yes" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="avg_percentage_no" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="avg_percentage_absent" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="avg_percentage_abstain" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="avg_percentage_male" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenVoteDataBallotSummaryAnnual", propOrder = {
    "voteDate",
    "avgBornYear",
    "totalVotes",
    "yesVotes",
    "noVotes",
    "abstainVotes",
    "absentVotes",
    "percentageYes",
    "percentageNo",
    "percentageAbsent",
    "percentageAbstain",
    "numberBallots",
    "approvedBallots",
    "percentageApproved",
    "avgPercentageYes",
    "avgPercentageNo",
    "avgPercentageAbsent",
    "avgPercentageAbstain",
    "avgPercentageMale"
})
@Entity(name = "ViewRiksdagenVoteDataBallotSummaryAnnual")
@Table(name = "View_Riksdagen_Vote_Data_Ballot_Summary_Annual")
@Inheritance(strategy = InheritanceType.JOINED)
public class ViewRiksdagenVoteDataBallotSummaryAnnual
    implements ModelObject
{

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(name = "vote_date", required = true, type = String.class)
    @XmlJavaTypeAdapter(XmlDateTypeAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date voteDate;
    @XmlElement(name = "avg_born_year", required = true)
    protected BigDecimal avgBornYear;
    @XmlElement(name = "total_votes", required = true)
    protected BigDecimal totalVotes;
    @XmlElement(name = "yes_votes", required = true)
    protected BigDecimal yesVotes;
    @XmlElement(name = "no_votes", required = true)
    protected BigDecimal noVotes;
    @XmlElement(name = "abstain_votes", required = true)
    protected BigDecimal abstainVotes;
    @XmlElement(name = "absent_votes", required = true)
    protected BigDecimal absentVotes;
    @XmlElement(name = "percentage_yes", required = true)
    protected BigDecimal percentageYes;
    @XmlElement(name = "percentage_no", required = true)
    protected BigDecimal percentageNo;
    @XmlElement(name = "percentage_absent", required = true)
    protected BigDecimal percentageAbsent;
    @XmlElement(name = "percentage_abstain", required = true)
    protected BigDecimal percentageAbstain;
    @XmlElement(name = "number_ballots", required = true)
    protected BigDecimal numberBallots;
    @XmlElement(name = "approved_ballots", required = true)
    protected BigDecimal approvedBallots;
    @XmlElement(name = "percentage_approved", required = true)
    protected BigDecimal percentageApproved;
    @XmlElement(name = "avg_percentage_yes", required = true)
    protected BigDecimal avgPercentageYes;
    @XmlElement(name = "avg_percentage_no", required = true)
    protected BigDecimal avgPercentageNo;
    @XmlElement(name = "avg_percentage_absent", required = true)
    protected BigDecimal avgPercentageAbsent;
    @XmlElement(name = "avg_percentage_abstain", required = true)
    protected BigDecimal avgPercentageAbstain;
    @XmlElement(name = "avg_percentage_male", required = true)
    protected BigDecimal avgPercentageMale;

    /**
     * Gets the value of the voteDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Id
    @Column(name = "VOTE_DATE")
    @Temporal(TemporalType.DATE)
    public Date getVoteDate() {
        return voteDate;
    }

    /**
     * Sets the value of the voteDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setVoteDate(final Date value) {
        this.voteDate = value;
    }

    /**
     * Gets the value of the avgBornYear property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    @Basic
    @Column(name = "AVG_BORN_YEAR", precision = 20, scale = 10)
    public BigDecimal getAvgBornYear() {
        return avgBornYear;
    }

    /**
     * Sets the value of the avgBornYear property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setAvgBornYear(final BigDecimal value) {
        this.avgBornYear = value;
    }

    /**
     * Gets the value of the totalVotes property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    @Basic
    @Column(name = "TOTAL_VOTES", precision = 20, scale = 10)
    public BigDecimal getTotalVotes() {
        return totalVotes;
    }

    /**
     * Sets the value of the totalVotes property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setTotalVotes(final BigDecimal value) {
        this.totalVotes = value;
    }

    /**
     * Gets the value of the yesVotes property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    @Basic
    @Column(name = "YES_VOTES", precision = 20, scale = 10)
    public BigDecimal getYesVotes() {
        return yesVotes;
    }

    /**
     * Sets the value of the yesVotes property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setYesVotes(final BigDecimal value) {
        this.yesVotes = value;
    }

    /**
     * Gets the value of the noVotes property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    @Basic
    @Column(name = "NO_VOTES", precision = 20, scale = 10)
    public BigDecimal getNoVotes() {
        return noVotes;
    }

    /**
     * Sets the value of the noVotes property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setNoVotes(final BigDecimal value) {
        this.noVotes = value;
    }

    /**
     * Gets the value of the abstainVotes property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    @Basic
    @Column(name = "ABSTAIN_VOTES", precision = 20, scale = 10)
    public BigDecimal getAbstainVotes() {
        return abstainVotes;
    }

    /**
     * Sets the value of the abstainVotes property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setAbstainVotes(final BigDecimal value) {
        this.abstainVotes = value;
    }

    /**
     * Gets the value of the absentVotes property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    @Basic
    @Column(name = "ABSENT_VOTES", precision = 20, scale = 10)
    public BigDecimal getAbsentVotes() {
        return absentVotes;
    }

    /**
     * Sets the value of the absentVotes property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setAbsentVotes(final BigDecimal value) {
        this.absentVotes = value;
    }

    /**
     * Gets the value of the percentageYes property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    @Basic
    @Column(name = "PERCENTAGE_YES", precision = 20, scale = 10)
    public BigDecimal getPercentageYes() {
        return percentageYes;
    }

    /**
     * Sets the value of the percentageYes property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setPercentageYes(final BigDecimal value) {
        this.percentageYes = value;
    }

    /**
     * Gets the value of the percentageNo property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    @Basic
    @Column(name = "PERCENTAGE_NO", precision = 20, scale = 10)
    public BigDecimal getPercentageNo() {
        return percentageNo;
    }

    /**
     * Sets the value of the percentageNo property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setPercentageNo(final BigDecimal value) {
        this.percentageNo = value;
    }

    /**
     * Gets the value of the percentageAbsent property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    @Basic
    @Column(name = "PERCENTAGE_ABSENT", precision = 20, scale = 10)
    public BigDecimal getPercentageAbsent() {
        return percentageAbsent;
    }

    /**
     * Sets the value of the percentageAbsent property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setPercentageAbsent(final BigDecimal value) {
        this.percentageAbsent = value;
    }

    /**
     * Gets the value of the percentageAbstain property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    @Basic
    @Column(name = "PERCENTAGE_ABSTAIN", precision = 20, scale = 10)
    public BigDecimal getPercentageAbstain() {
        return percentageAbstain;
    }

    /**
     * Sets the value of the percentageAbstain property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setPercentageAbstain(final BigDecimal value) {
        this.percentageAbstain = value;
    }

    /**
     * Gets the value of the numberBallots property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    @Basic
    @Column(name = "NUMBER_BALLOTS", precision = 20, scale = 10)
    public BigDecimal getNumberBallots() {
        return numberBallots;
    }

    /**
     * Sets the value of the numberBallots property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setNumberBallots(final BigDecimal value) {
        this.numberBallots = value;
    }

    /**
     * Gets the value of the approvedBallots property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    @Basic
    @Column(name = "APPROVED_BALLOTS", precision = 20, scale = 10)
    public BigDecimal getApprovedBallots() {
        return approvedBallots;
    }

    /**
     * Sets the value of the approvedBallots property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setApprovedBallots(final BigDecimal value) {
        this.approvedBallots = value;
    }

    /**
     * Gets the value of the percentageApproved property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    @Basic
    @Column(name = "PERCENTAGE_APPROVED", precision = 20, scale = 10)
    public BigDecimal getPercentageApproved() {
        return percentageApproved;
    }

    /**
     * Sets the value of the percentageApproved property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setPercentageApproved(final BigDecimal value) {
        this.percentageApproved = value;
    }

    /**
     * Gets the value of the avgPercentageYes property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    @Basic
    @Column(name = "AVG_PERCENTAGE_YES", precision = 20, scale = 10)
    public BigDecimal getAvgPercentageYes() {
        return avgPercentageYes;
    }

    /**
     * Sets the value of the avgPercentageYes property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setAvgPercentageYes(final BigDecimal value) {
        this.avgPercentageYes = value;
    }

    /**
     * Gets the value of the avgPercentageNo property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    @Basic
    @Column(name = "AVG_PERCENTAGE_NO", precision = 20, scale = 10)
    public BigDecimal getAvgPercentageNo() {
        return avgPercentageNo;
    }

    /**
     * Sets the value of the avgPercentageNo property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setAvgPercentageNo(final BigDecimal value) {
        this.avgPercentageNo = value;
    }

    /**
     * Gets the value of the avgPercentageAbsent property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    @Basic
    @Column(name = "AVG_PERCENTAGE_ABSENT", precision = 20, scale = 10)
    public BigDecimal getAvgPercentageAbsent() {
        return avgPercentageAbsent;
    }

    /**
     * Sets the value of the avgPercentageAbsent property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setAvgPercentageAbsent(final BigDecimal value) {
        this.avgPercentageAbsent = value;
    }

    /**
     * Gets the value of the avgPercentageAbstain property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    @Basic
    @Column(name = "AVG_PERCENTAGE_ABSTAIN", precision = 20, scale = 10)
    public BigDecimal getAvgPercentageAbstain() {
        return avgPercentageAbstain;
    }

    /**
     * Sets the value of the avgPercentageAbstain property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setAvgPercentageAbstain(final BigDecimal value) {
        this.avgPercentageAbstain = value;
    }

    /**
     * Gets the value of the avgPercentageMale property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    @Basic
    @Column(name = "AVG_PERCENTAGE_MALE", precision = 20, scale = 10)
    public BigDecimal getAvgPercentageMale() {
        return avgPercentageMale;
    }

    /**
     * Sets the value of the avgPercentageMale property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setAvgPercentageMale(final BigDecimal value) {
        this.avgPercentageMale = value;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withVoteDate(final Date value) {
        setVoteDate(value);
        return this;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withAvgBornYear(final BigDecimal value) {
        setAvgBornYear(value);
        return this;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withTotalVotes(final BigDecimal value) {
        setTotalVotes(value);
        return this;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withYesVotes(final BigDecimal value) {
        setYesVotes(value);
        return this;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withNoVotes(final BigDecimal value) {
        setNoVotes(value);
        return this;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withAbstainVotes(final BigDecimal value) {
        setAbstainVotes(value);
        return this;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withAbsentVotes(final BigDecimal value) {
        setAbsentVotes(value);
        return this;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withPercentageYes(final BigDecimal value) {
        setPercentageYes(value);
        return this;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withPercentageNo(final BigDecimal value) {
        setPercentageNo(value);
        return this;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withPercentageAbsent(final BigDecimal value) {
        setPercentageAbsent(value);
        return this;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withPercentageAbstain(final BigDecimal value) {
        setPercentageAbstain(value);
        return this;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withNumberBallots(final BigDecimal value) {
        setNumberBallots(value);
        return this;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withApprovedBallots(final BigDecimal value) {
        setApprovedBallots(value);
        return this;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withPercentageApproved(final BigDecimal value) {
        setPercentageApproved(value);
        return this;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withAvgPercentageYes(final BigDecimal value) {
        setAvgPercentageYes(value);
        return this;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withAvgPercentageNo(final BigDecimal value) {
        setAvgPercentageNo(value);
        return this;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withAvgPercentageAbsent(final BigDecimal value) {
        setAvgPercentageAbsent(value);
        return this;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withAvgPercentageAbstain(final BigDecimal value) {
        setAvgPercentageAbstain(value);
        return this;
    }

    public ViewRiksdagenVoteDataBallotSummaryAnnual withAvgPercentageMale(final BigDecimal value) {
        setAvgPercentageMale(value);
        return this;
    }

	@Override
	public final String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public final boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public final int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
