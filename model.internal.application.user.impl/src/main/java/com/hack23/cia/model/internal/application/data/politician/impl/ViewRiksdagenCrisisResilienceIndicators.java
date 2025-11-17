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
 * JPA entity for view_riksdagen_crisis_resilience_indicators database view.
 * 
 * Intelligence Purpose: Assesses politician performance under pressure by comparing
 * behavior during crisis periods versus normal periods. Identifies resilient
 * leaders and those showing performance degradation under stress.
 * 
 * Created by: Liquibase v1.29 (Intelligence Operations)
 * Risk Rules Supported: P-08, P-22 (Performance under pressure assessment)
 */
@Entity(name = "ViewRiksdagenCrisisResilienceIndicators")
@Table(name = "view_riksdagen_crisis_resilience_indicators")
public class ViewRiksdagenCrisisResilienceIndicators implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "person_id", nullable = false, length = 255)
	private String personId;

	@Column(name = "first_name", length = 255)
	private String firstName;

	@Column(name = "last_name", length = 255)
	private String lastName;

	@Column(name = "party", length = 50)
	private String party;

	@Column(name = "crisis_period_votes")
	private Long crisisPeriodVotes;

	@Column(name = "crisis_absence_rate", precision = 5, scale = 2)
	private BigDecimal crisisAbsenceRate;

	@Column(name = "crisis_party_discipline", precision = 5, scale = 2)
	private BigDecimal crisisPartyDiscipline;

	@Column(name = "normal_period_votes")
	private Long normalPeriodVotes;

	@Column(name = "normal_absence_rate", precision = 5, scale = 2)
	private BigDecimal normalAbsenceRate;

	@Column(name = "absence_rate_change", precision = 5, scale = 2)
	private BigDecimal absenceRateChange;

	@Column(name = "resilience_score", precision = 10, scale = 4)
	private BigDecimal resilienceScore;

	@Column(name = "resilience_classification", length = 100)
	private String resilienceClassification;

	@Column(name = "pressure_performance_assessment", length = 255)
	private String pressurePerformanceAssessment;

	/**
	 * Default constructor.
	 */
	public ViewRiksdagenCrisisResilienceIndicators() {
		super();
	}

	// Getters and Setters

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
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

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public Long getCrisisPeriodVotes() {
		return crisisPeriodVotes;
	}

	public void setCrisisPeriodVotes(Long crisisPeriodVotes) {
		this.crisisPeriodVotes = crisisPeriodVotes;
	}

	public BigDecimal getCrisisAbsenceRate() {
		return crisisAbsenceRate;
	}

	public void setCrisisAbsenceRate(BigDecimal crisisAbsenceRate) {
		this.crisisAbsenceRate = crisisAbsenceRate;
	}

	public BigDecimal getCrisisPartyDiscipline() {
		return crisisPartyDiscipline;
	}

	public void setCrisisPartyDiscipline(BigDecimal crisisPartyDiscipline) {
		this.crisisPartyDiscipline = crisisPartyDiscipline;
	}

	public Long getNormalPeriodVotes() {
		return normalPeriodVotes;
	}

	public void setNormalPeriodVotes(Long normalPeriodVotes) {
		this.normalPeriodVotes = normalPeriodVotes;
	}

	public BigDecimal getNormalAbsenceRate() {
		return normalAbsenceRate;
	}

	public void setNormalAbsenceRate(BigDecimal normalAbsenceRate) {
		this.normalAbsenceRate = normalAbsenceRate;
	}

	public BigDecimal getAbsenceRateChange() {
		return absenceRateChange;
	}

	public void setAbsenceRateChange(BigDecimal absenceRateChange) {
		this.absenceRateChange = absenceRateChange;
	}

	public BigDecimal getResilienceScore() {
		return resilienceScore;
	}

	public void setResilienceScore(BigDecimal resilienceScore) {
		this.resilienceScore = resilienceScore;
	}

	public String getResilienceClassification() {
		return resilienceClassification;
	}

	public void setResilienceClassification(String resilienceClassification) {
		this.resilienceClassification = resilienceClassification;
	}

	public String getPressurePerformanceAssessment() {
		return pressurePerformanceAssessment;
	}

	public void setPressurePerformanceAssessment(String pressurePerformanceAssessment) {
		this.pressurePerformanceAssessment = pressurePerformanceAssessment;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final ViewRiksdagenCrisisResilienceIndicators other = (ViewRiksdagenCrisisResilienceIndicators) obj;
		return new EqualsBuilder()
				.append(personId, other.personId)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(personId)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("personId", personId)
				.append("firstName", firstName)
				.append("lastName", lastName)
				.append("party", party)
				.append("resilienceScore", resilienceScore)
				.append("resilienceClassification", resilienceClassification)
				.toString();
	}
}
