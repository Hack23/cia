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
package com.hack23.cia.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.annotation.Secured;

/**
 * Service for coalition prediction and analysis.
 * 
 * Provides intelligence products for:
 * - Coalition formation likelihood
 * - Coalition stability assessment
 * - Party alignment analysis
 * - Government formation scenarios
 */
public interface CoalitionPredictionService {

	/**
	 * Get coalition scenarios ranked by probability.
	 * 
	 * @param year the year or electoral period (e.g., "2023/24")
	 * @return list of coalition scenarios ordered by probability
	 */
	List<CoalitionScenario> predictCoalitions(String year);

	/**
	 * Get party alignment matrix showing voting pattern compatibility.
	 * 
	 * @param year the year or electoral period
	 * @return map of party-to-party alignment scores (0.0-1.0)
	 */
	Map<String, Map<String, Double>> getAlignmentMatrix(String year);

	/**
	 * Calculate coalition stability index.
	 * 
	 * @param parties list of party short codes
	 * @param year the year or electoral period
	 * @return stability index (0-100)
	 */
	int calculateStabilityIndex(List<String> parties, String year);

	/**
	 * Coalition scenario data class representing a potential government formation.
	 * This immutable class encapsulates all relevant information about a coalition scenario
	 * including participating parties, parliamentary strength, formation probability, and stability metrics.
	 */
	static class CoalitionScenario {
		private final List<String> parties;
		private final int totalSeats;
		private final double probability;
		private final int stabilityIndex;
		private final String coalitionType;
		private final String blocRelationship;

		/**
		 * Constructs a new coalition scenario with defensive copying for immutability.
		 *
		 * @param parties the list of party IDs participating in the coalition (e.g., ["S", "M", "C"])
		 * @param totalSeats the total number of parliamentary seats held by the coalition
		 * @param probability the formation probability (0.0-1.0, where 1.0 = 100% likely)
		 * @param stabilityIndex the coalition stability score (0-100, where 100 = most stable)
		 * @param coalitionType the type of coalition (TWO_PARTY, THREE_PARTY, or FOUR_PARTY)
		 * @param blocRelationship the political bloc relationship (e.g., LEFT_BLOC, CROSS_BLOC)
		 */
		public CoalitionScenario(List<String> parties, int totalSeats, double probability, 
				int stabilityIndex, String coalitionType, String blocRelationship) {
			// Defensive copy to ensure immutability
			this.parties = parties != null ? new ArrayList<>(parties) : new ArrayList<>();
			this.totalSeats = totalSeats;
			this.probability = probability;
			this.stabilityIndex = stabilityIndex;
			this.coalitionType = coalitionType;
			this.blocRelationship = blocRelationship;
		}

		/**
		 * Gets the list of party IDs in this coalition.
		 *
		 * @return unmodifiable list of party IDs to preserve immutability
		 */
		public List<String> getParties() {
			// Return unmodifiable view to preserve immutability
			return Collections.unmodifiableList(parties);
		}

		/**
		 * Gets the total number of parliamentary seats.
		 *
		 * @return total seats held by all parties in the coalition
		 */
		public int getTotalSeats() {
			return totalSeats;
		}

		/**
		 * Gets the formation probability.
		 *
		 * @return probability value between 0.0 and 1.0 (multiply by 100 for percentage)
		 */
		public double getProbability() {
			return probability;
		}

		/**
		 * Gets the coalition stability index.
		 *
		 * @return stability score from 0 to 100, where higher values indicate more stable coalitions
		 */
		public int getStabilityIndex() {
			return stabilityIndex;
		}

		/**
		 * Gets the coalition type.
		 *
		 * @return coalition type string (TWO_PARTY, THREE_PARTY, or FOUR_PARTY)
		 */
		public String getCoalitionType() {
			return coalitionType;
		}

		/**
		 * Gets the political bloc relationship.
		 *
		 * @return bloc relationship string (e.g., LEFT_BLOC, RIGHT_BLOC, CROSS_BLOC)
		 */
		public String getBlocRelationship() {
			return blocRelationship;
		}
	}
}
