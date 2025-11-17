package com.hack23.cia.model.internal.application.data.politician.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * JPA entity for view_riksdagen_voting_anomaly_detection database view.
 * 
 * Intelligence Purpose: Detects voting anomalies and party discipline violations
 * through analysis of individual politician voting patterns against party lines.
 * Identifies rebellion rates, unanimous vote deviations, and defection risks.
 * 
 * Created by: Liquibase v1.29 (Intelligence Operations)
 * Risk Rules Supported: P-03, P-05, P-13 (Rebellion and party discipline assessment)
 */
@Entity(name = "ViewRiksdagenVotingAnomalyDetection")
@Table(name = "view_riksdagen_voting_anomaly_detection")
public class ViewRiksdagenVotingAnomalyDetection implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "person_id", nullable = false, length = 255)
	private String personId;

	@Id
	@Column(name = "party", nullable = false, length = 50)
	private String party;

	@Column(name = "first_name", length = 255)
	private String firstName;

	@Column(name = "last_name", length = 255)
	private String lastName;

	@Column(name = "total_votes")
	private Long totalVotes;

	@Column(name = "aligned_votes")
	private Long alignedVotes;

	@Column(name = "opposed_votes")
	private Long opposedVotes;

	@Column(name = "party_discipline_score", precision = 5, scale = 2)
	private BigDecimal partyDisciplineScore;

	@Column(name = "rebellion_rate", precision = 5, scale = 2)
	private BigDecimal rebellionRate;

	@Column(name = "unanimous_deviations")
	private Long unanimousDeviations;

	@Column(name = "discipline_classification", length = 100)
	private String disciplineClassification;

	@Column(name = "defection_risk_assessment", length = 255)
	private String defectionRiskAssessment;

	@Column(name = "anomaly_count")
	private Long anomalyCount;

	/**
	 * Default constructor.
	 */
	public ViewRiksdagenVotingAnomalyDetection() {
		super();
	}

	// Getters and Setters

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(Long totalVotes) {
		this.totalVotes = totalVotes;
	}

	public Long getAlignedVotes() {
		return alignedVotes;
	}

	public void setAlignedVotes(Long alignedVotes) {
		this.alignedVotes = alignedVotes;
	}

	public Long getOpposedVotes() {
		return opposedVotes;
	}

	public void setOpposedVotes(Long opposedVotes) {
		this.opposedVotes = opposedVotes;
	}

	public BigDecimal getPartyDisciplineScore() {
		return partyDisciplineScore;
	}

	public void setPartyDisciplineScore(BigDecimal partyDisciplineScore) {
		this.partyDisciplineScore = partyDisciplineScore;
	}

	public BigDecimal getRebellionRate() {
		return rebellionRate;
	}

	public void setRebellionRate(BigDecimal rebellionRate) {
		this.rebellionRate = rebellionRate;
	}

	public Long getUnanimousDeviations() {
		return unanimousDeviations;
	}

	public void setUnanimousDeviations(Long unanimousDeviations) {
		this.unanimousDeviations = unanimousDeviations;
	}

	public String getDisciplineClassification() {
		return disciplineClassification;
	}

	public void setDisciplineClassification(String disciplineClassification) {
		this.disciplineClassification = disciplineClassification;
	}

	public String getDefectionRiskAssessment() {
		return defectionRiskAssessment;
	}

	public void setDefectionRiskAssessment(String defectionRiskAssessment) {
		this.defectionRiskAssessment = defectionRiskAssessment;
	}

	public Long getAnomalyCount() {
		return anomalyCount;
	}

	public void setAnomalyCount(Long anomalyCount) {
		this.anomalyCount = anomalyCount;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final ViewRiksdagenVotingAnomalyDetection other = (ViewRiksdagenVotingAnomalyDetection) obj;
		return new EqualsBuilder()
				.append(personId, other.personId)
				.append(party, other.party)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(personId)
				.append(party)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("personId", personId)
				.append("firstName", firstName)
				.append("lastName", lastName)
				.append("party", party)
				.append("rebellionRate", rebellionRate)
				.append("disciplineClassification", disciplineClassification)
				.toString();
	}
}
