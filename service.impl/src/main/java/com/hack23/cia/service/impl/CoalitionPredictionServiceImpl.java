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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenCoalitionAlignmentMatrix;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.service.data.api.DataViewer;

/**
 * Implementation of coalition prediction service.
 * 
 * Uses existing view_riksdagen_coalition_alignment_matrix to generate
 * coalition formation scenarios based on historical voting patterns.
 */
@Service
@Transactional(readOnly = true)
@Secured({"ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN"})
public class CoalitionPredictionServiceImpl implements CoalitionPredictionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoalitionPredictionServiceImpl.class);

	private static final int MAJORITY_SEATS = 175;
	private static final List<String> ALL_PARTIES = Arrays.asList("S", "M", "SD", "C", "V", "KD", "MP", "L");

	@Autowired
	private DataViewer dataViewer;

	@Override
	public List<CoalitionScenario> predictCoalitions(final String year) {
		LOGGER.info("Generating coalition scenarios for year: {}", year);

		final List<ViewRiksdagenCoalitionAlignmentMatrix> alignmentData = dataViewer.getAll(ViewRiksdagenCoalitionAlignmentMatrix.class);
		final Map<String, Map<String, Double>> alignmentMatrix = buildAlignmentMatrix(alignmentData);
		final Map<String, Integer> seatCounts = loadSeatCounts(year);

		final List<CoalitionScenario> scenarios = new ArrayList<>();

		// Generate 2-party coalitions
		scenarios.addAll(generateTwoPartyCoalitions(alignmentMatrix, seatCounts));

		// Generate 3-party coalitions
		scenarios.addAll(generateThreePartyCoalitions(alignmentMatrix, seatCounts));

		// Generate 4-party coalitions
		scenarios.addAll(generateFourPartyCoalitions(alignmentMatrix, seatCounts));

		// Sort by probability descending
		scenarios.sort(Comparator.comparingDouble(CoalitionScenario::getProbability).reversed());

		// Return top 10 scenarios
		return scenarios.stream().limit(10).collect(Collectors.toList());
	}

	@Override
	public Map<String, Map<String, Double>> getAlignmentMatrix(final String year) {
		final List<ViewRiksdagenCoalitionAlignmentMatrix> alignmentData = dataViewer.getAll(ViewRiksdagenCoalitionAlignmentMatrix.class);
		return buildAlignmentMatrix(alignmentData);
	}

	@Override
	public int calculateStabilityIndex(final List<String> parties, final String year) {
		if (parties == null || parties.size() < 2) {
			return 0;
		}

		final Map<String, Map<String, Double>> alignmentMatrix = getAlignmentMatrix(year);
		final List<Double> pairwiseAlignments = new ArrayList<>();

		for (int i = 0; i < parties.size(); i++) {
			for (int j = i + 1; j < parties.size(); j++) {
				final String party1 = parties.get(i);
				final String party2 = parties.get(j);
				final Double alignment = getAlignment(alignmentMatrix, party1, party2);
				if (alignment != null) {
					pairwiseAlignments.add(alignment);
				}
			}
		}

		if (pairwiseAlignments.isEmpty()) {
			return 0;
		}

		final double avgAlignment = pairwiseAlignments.stream()
				.mapToDouble(Double::doubleValue)
				.average()
				.orElse(0.0);

		return (int) (avgAlignment * 100);
	}

	private Map<String, Map<String, Double>> buildAlignmentMatrix(final List<ViewRiksdagenCoalitionAlignmentMatrix> alignmentData) {
		final Map<String, Map<String, Double>> matrix = new HashMap<>();

		for (final String party : ALL_PARTIES) {
			matrix.put(party, new HashMap<>());
		}

		for (final ViewRiksdagenCoalitionAlignmentMatrix data : alignmentData) {
			final String party1 = data.getParty1();
			final String party2 = data.getParty2();
			final BigDecimal alignmentRate = data.getAlignmentRate();

			if (alignmentRate != null) {
				final double rate = alignmentRate.doubleValue();
				matrix.computeIfAbsent(party1, k -> new HashMap<>()).put(party2, rate);
				matrix.computeIfAbsent(party2, k -> new HashMap<>()).put(party1, rate);
			}
		}

		// Set self-alignment to 1.0
		for (final String party : ALL_PARTIES) {
			matrix.get(party).put(party, 1.0);
		}

		return matrix;
	}

	private Map<String, Integer> loadSeatCounts(final String year) {
		// Load actual seat counts from ViewRiksdagenParty or similar data source
		// For now, using approximate recent values as fallback
		final Map<String, Integer> seatCounts = new HashMap<>();
		
		try {
			final List<ViewRiksdagenParty> parties = dataViewer.getAll(ViewRiksdagenParty.class);
			for (final ViewRiksdagenParty party : parties) {
				if (party.getHeadCount() > 0) {
					seatCounts.put(party.getPartyId(), (int) party.getHeadCount());
				}
			}
		} catch (final Exception e) {
			LOGGER.warn("Could not load seat counts from database, using defaults", e);
		}
		
		// Fallback to approximate recent election results if database query fails
		if (seatCounts.isEmpty()) {
			seatCounts.put("S", 107);
			seatCounts.put("M", 68);
			seatCounts.put("SD", 73);
			seatCounts.put("C", 24);
			seatCounts.put("V", 24);
			seatCounts.put("KD", 19);
			seatCounts.put("MP", 18);
			seatCounts.put("L", 16);
		}
		
		return seatCounts;
	}

	private List<CoalitionScenario> generateTwoPartyCoalitions(
			final Map<String, Map<String, Double>> alignmentMatrix,
			final Map<String, Integer> seatCounts) {
		final List<CoalitionScenario> scenarios = new ArrayList<>();

		for (int i = 0; i < ALL_PARTIES.size(); i++) {
			for (int j = i + 1; j < ALL_PARTIES.size(); j++) {
				final String party1 = ALL_PARTIES.get(i);
				final String party2 = ALL_PARTIES.get(j);
				final List<String> coalition = Arrays.asList(party1, party2);

				final int totalSeats = getTotalSeats(coalition, seatCounts);
				if (totalSeats < MAJORITY_SEATS) {
					continue;
				}

				final double alignment = getAlignment(alignmentMatrix, party1, party2);
				final double probability = calculateProbability(alignment, totalSeats);
				final int stability = (int) (alignment * 100);
				final String blocRelation = determineBlocRelationship(coalition);

				scenarios.add(new CoalitionScenario(coalition, totalSeats, probability, 
						stability, "TWO_PARTY", blocRelation));
			}
		}

		return scenarios;
	}

	private List<CoalitionScenario> generateThreePartyCoalitions(
			final Map<String, Map<String, Double>> alignmentMatrix,
			final Map<String, Integer> seatCounts) {
		final List<CoalitionScenario> scenarios = new ArrayList<>();

		for (int i = 0; i < ALL_PARTIES.size(); i++) {
			for (int j = i + 1; j < ALL_PARTIES.size(); j++) {
				for (int k = j + 1; k < ALL_PARTIES.size(); k++) {
					final String party1 = ALL_PARTIES.get(i);
					final String party2 = ALL_PARTIES.get(j);
					final String party3 = ALL_PARTIES.get(k);
					final List<String> coalition = Arrays.asList(party1, party2, party3);

					final int totalSeats = getTotalSeats(coalition, seatCounts);
					if (totalSeats < MAJORITY_SEATS) {
						continue;
					}

					final double avgAlignment = calculateAverageAlignment(coalition, alignmentMatrix);
					final double probability = calculateProbability(avgAlignment, totalSeats);
					final int stability = (int) (avgAlignment * 100);
					final String blocRelation = determineBlocRelationship(coalition);

					scenarios.add(new CoalitionScenario(coalition, totalSeats, probability, 
							stability, "THREE_PARTY", blocRelation));
				}
			}
		}

		return scenarios;
	}

	private List<CoalitionScenario> generateFourPartyCoalitions(
			final Map<String, Map<String, Double>> alignmentMatrix,
			final Map<String, Integer> seatCounts) {
		final List<CoalitionScenario> scenarios = new ArrayList<>();

		for (int i = 0; i < ALL_PARTIES.size(); i++) {
			for (int j = i + 1; j < ALL_PARTIES.size(); j++) {
				for (int k = j + 1; k < ALL_PARTIES.size(); k++) {
					for (int l = k + 1; l < ALL_PARTIES.size(); l++) {
						final String party1 = ALL_PARTIES.get(i);
						final String party2 = ALL_PARTIES.get(j);
						final String party3 = ALL_PARTIES.get(k);
						final String party4 = ALL_PARTIES.get(l);
						final List<String> coalition = Arrays.asList(party1, party2, party3, party4);

						final int totalSeats = getTotalSeats(coalition, seatCounts);
						if (totalSeats < MAJORITY_SEATS) {
							continue;
						}

						final double avgAlignment = calculateAverageAlignment(coalition, alignmentMatrix);
						final double probability = calculateProbability(avgAlignment, totalSeats);
						final int stability = (int) (avgAlignment * 100);
						final String blocRelation = determineBlocRelationship(coalition);

						scenarios.add(new CoalitionScenario(coalition, totalSeats, probability, 
								stability, "FOUR_PARTY", blocRelation));
					}
				}
			}
		}

		return scenarios;
	}

	private int getTotalSeats(final List<String> parties, final Map<String, Integer> seatCounts) {
		return parties.stream()
				.mapToInt(party -> seatCounts.getOrDefault(party, 0))
				.sum();
	}

	private double getAlignment(final Map<String, Map<String, Double>> alignmentMatrix, 
			final String party1, final String party2) {
		return alignmentMatrix.getOrDefault(party1, new HashMap<>()).getOrDefault(party2, 0.0);
	}

	private double calculateAverageAlignment(final List<String> parties, 
			final Map<String, Map<String, Double>> alignmentMatrix) {
		final List<Double> alignments = new ArrayList<>();

		for (int i = 0; i < parties.size(); i++) {
			for (int j = i + 1; j < parties.size(); j++) {
				final double alignment = getAlignment(alignmentMatrix, parties.get(i), parties.get(j));
				alignments.add(alignment);
			}
		}

		return alignments.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
	}

	private double calculateProbability(final double alignment, final int totalSeats) {
		// Simple probability model:
		// - Base probability from alignment (60% weight)
		// - Seats above majority threshold (40% weight)
		final double alignmentFactor = alignment * 0.6;
		final double seatsFactor = Math.min((totalSeats - MAJORITY_SEATS) / 100.0, 0.4);
		return Math.min(alignmentFactor + seatsFactor, 1.0);
	}

	private String determineBlocRelationship(final List<String> parties) {
		final List<String> leftBloc = Arrays.asList("S", "V", "MP");
		final List<String> rightBloc = Arrays.asList("M", "KD", "L", "C");

		final boolean hasLeft = parties.stream().anyMatch(leftBloc::contains);
		final boolean hasRight = parties.stream().anyMatch(rightBloc::contains);
		final boolean hasSD = parties.contains("SD");

		if (hasLeft && hasRight) {
			return "CROSS_BLOC";
		} else if (hasLeft && hasSD) {
			return "LEFT_SD_COALITION";
		} else if (hasRight && hasSD) {
			return "RIGHT_SD_COALITION";
		} else if (hasLeft) {
			return "LEFT_BLOC";
		} else if (hasRight) {
			return "RIGHT_BLOC";
		} else {
			return "OTHER";
		}
	}
}
