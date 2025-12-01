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
 * Data quality monitoring dashboard for OSINT sources.
 * 
 * <p>Provides admin views to monitor data quality metrics for:</p>
 * <ul>
 *   <li>Riksdagen API (Swedish Parliament)</li>
 *   <li>Election Authority (Valmyndigheten)</li>
 *   <li>World Bank Open Data</li>
 *   <li>Financial Authority (ESV)</li>
 * </ul>
 * 
 * <p>Tracks:</p>
 * <ul>
 *   <li>Data freshness (last update timestamps)</li>
 *   <li>Data completeness (missing records, coverage)</li>
 *   <li>Data accuracy (validation failures, anomalies)</li>
 *   <li>Alert system for quality issues</li>
 *   <li>Historical trends</li>
 * </ul>
 */
package com.hack23.cia.web.impl.ui.application.views.admin.dataquality;
