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

import java.util.List;
import java.util.Map;

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
	 * Coalition scenario data class.
	 */
	static class CoalitionScenario {
		private final List<String> parties;
		private final int totalSeats;
		private final double probability;
		private final int stabilityIndex;
		private final String coalitionType;
		private final String blocRelationship;

		public CoalitionScenario(List<String> parties, int totalSeats, double probability, 
				int stabilityIndex, String coalitionType, String blocRelationship) {
			this.parties = parties;
			this.totalSeats = totalSeats;
			this.probability = probability;
			this.stabilityIndex = stabilityIndex;
			this.coalitionType = coalitionType;
			this.blocRelationship = blocRelationship;
		}

		public List<String> getParties() {
			return parties;
		}

		public int getTotalSeats() {
			return totalSeats;
		}

		public double getProbability() {
			return probability;
		}

		public int getStabilityIndex() {
			return stabilityIndex;
		}

		public String getCoalitionType() {
			return coalitionType;
		}

		public String getBlocRelationship() {
			return blocRelationship;
		}
	}
}
