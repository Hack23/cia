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
/**
 * JSON export data transfer objects for intelligence products.
 * 
 * <p>This package contains DTOs for exporting intelligence data as JSON,
 * supporting CDN-ready static file generation and API responses.</p>
 * 
 * <h2>Key Classes</h2>
 * <ul>
 *   <li>{@link com.hack23.cia.service.data.impl.export.ExportMetadata} - Common metadata for all exports</li>
 *   <li>{@link com.hack23.cia.service.data.impl.export.RiskAssessmentExportDTO} - Risk assessment violations</li>
 *   <li>{@link com.hack23.cia.service.data.impl.export.CoalitionAlignmentExportDTO} - Party alignment matrix</li>
 *   <li>{@link com.hack23.cia.service.data.impl.export.TemporalTrendsExportDTO} - Temporal trend analysis</li>
 * </ul>
 * 
 * <h2>Usage</h2>
 * <p>These DTOs are populated by {@link com.hack23.cia.service.data.impl.IntelligenceExportServiceImpl}
 * and serialized to JSON using Jackson ObjectMapper with pretty-print and ISO 8601 date formatting.</p>
 * 
 * @author intelligence-operative
 * @since v1.36 (Intelligence Products JSON Export)
 */
package com.hack23.cia.service.data.impl.export;
