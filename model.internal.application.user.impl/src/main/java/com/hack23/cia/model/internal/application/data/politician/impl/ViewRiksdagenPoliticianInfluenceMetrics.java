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
 * JPA entity for view_riksdagen_politician_influence_metrics database view.
 * 
 * Intelligence Purpose: Measures individual politician influence and network
 * position through connectivity analysis. Identifies key influencers, broker
 * roles, and cross-party bridge builders in the parliamentary network.
 * 
 * Created by: Liquibase v1.29 (Intelligence Operations)
 * Risk Rules Supported: P-20, P-21 (Network influence and broker role assessment)
 */
@Entity(name = "ViewRiksdagenPoliticianInfluenceMetrics")
@Table(name = "view_riksdagen_politician_influence_metrics")
public class ViewRiksdagenPoliticianInfluenceMetrics implements Serializable {

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

	@Column(name = "network_connections")
	private Long networkConnections;

	@Column(name = "cross_party_bridges")
	private Long crossPartyBridges;

	@Column(name = "normalized_centrality", precision = 10, scale = 6)
	private BigDecimal normalizedCentrality;

	@Column(name = "connectivity_level", length = 50)
	private String connectivityLevel;

	@Column(name = "broker_classification", length = 100)
	private String brokerClassification;

	@Column(name = "influence_score", precision = 10, scale = 4)
	private BigDecimal influenceScore;

	@Column(name = "influence_assessment", length = 255)
	private String influenceAssessment;

	/**
	 * Default constructor.
	 */
	public ViewRiksdagenPoliticianInfluenceMetrics() {
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

	public Long getNetworkConnections() {
		return networkConnections;
	}

	public void setNetworkConnections(Long networkConnections) {
		this.networkConnections = networkConnections;
	}

	public Long getCrossPartyBridges() {
		return crossPartyBridges;
	}

	public void setCrossPartyBridges(Long crossPartyBridges) {
		this.crossPartyBridges = crossPartyBridges;
	}

	public BigDecimal getNormalizedCentrality() {
		return normalizedCentrality;
	}

	public void setNormalizedCentrality(BigDecimal normalizedCentrality) {
		this.normalizedCentrality = normalizedCentrality;
	}

	public String getConnectivityLevel() {
		return connectivityLevel;
	}

	public void setConnectivityLevel(String connectivityLevel) {
		this.connectivityLevel = connectivityLevel;
	}

	public String getBrokerClassification() {
		return brokerClassification;
	}

	public void setBrokerClassification(String brokerClassification) {
		this.brokerClassification = brokerClassification;
	}

	public BigDecimal getInfluenceScore() {
		return influenceScore;
	}

	public void setInfluenceScore(BigDecimal influenceScore) {
		this.influenceScore = influenceScore;
	}

	public String getInfluenceAssessment() {
		return influenceAssessment;
	}

	public void setInfluenceAssessment(String influenceAssessment) {
		this.influenceAssessment = influenceAssessment;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final ViewRiksdagenPoliticianInfluenceMetrics other = (ViewRiksdagenPoliticianInfluenceMetrics) obj;
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
				.append("influenceScore", influenceScore)
				.append("brokerClassification", brokerClassification)
				.toString();
	}
}
