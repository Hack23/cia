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
package com.hack23.cia.service.data.api;

import java.io.IOException;

/**
 * Service interface for exporting intelligence products as JSON.
 * 
 * Provides JSON export capabilities for risk assessments, coalition alignment,
 * and temporal trends to support CDN-ready static file generation and API endpoints.
 * 
 * @author intelligence-operative
 * @since v1.36 (Intelligence Products JSON Export)
 */
public interface IntelligenceExportService {

	/**
	 * Export risk assessment data for all rule violations.
	 * 
	 * Generates JSON containing all 50 risk rules with their current violations,
	 * severity levels, and affected entities (politicians, parties, committees, ministries).
	 * 
	 * @return JSON string containing risk assessment data
	 * @throws IOException if JSON generation fails
	 */
	String exportRiskAssessments() throws IOException;

	/**
	 * Export coalition alignment matrix showing voting alignment between parties.
	 * 
	 * Generates JSON containing pairwise party alignment rates, total votes,
	 * and aligned votes for coalition stability analysis.
	 * 
	 * @return JSON string containing coalition alignment matrix
	 * @throws IOException if JSON generation fails
	 */
	String exportCoalitionAlignment() throws IOException;

	/**
	 * Export temporal trend analysis with daily/weekly/monthly/annual patterns.
	 * 
	 * Generates JSON containing decision volumes, approval rates, moving averages,
	 * and year-over-year comparisons for trend forecasting.
	 * 
	 * @return JSON string containing temporal trends data
	 * @throws IOException if JSON generation fails
	 */
	String exportTemporalTrends() throws IOException;

	/**
	 * Write JSON export to file for CDN deployment.
	 * 
	 * @param jsonContent the JSON content to write
	 * @param fileName the file name (e.g., "risk-assessments.json")
	 * @param outputDirectory the output directory path
	 * @throws IOException if file write fails
	 */
	void writeJsonToFile(String jsonContent, String fileName, String outputDirectory) throws IOException;
}
