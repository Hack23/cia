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
package com.hack23.cia.service.impl.performance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Implementation of performance metrics service.
 * 
 * Provides basic performance tracking capabilities including:
 * - Timer creation
 * - Metrics logging
 * - Performance alerts
 */
@Service
public class PerformanceMetricsServiceImpl implements PerformanceMetricsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceMetricsServiceImpl.class);
	
	@Override
	public PerformanceTimer startTimer(final String operationName) {
		return new PerformanceTimer(operationName);
	}
	
	@Override
	public void recordMetrics(final String operationName, final String context, final PerformanceMetrics metrics) {
		LOGGER.info("Performance Metrics - Operation: {}, Context: {}, Total Time: {}ms, Memory: {}MB, Phases: {}",
			operationName,
			context,
			metrics.getTotalTimeMs(),
			metrics.getMemoryUsedMb(),
			metrics.getPhases());
	}
	
	@Override
	public void sendPerformanceAlert(final String title, final String message) {
		LOGGER.warn("PERFORMANCE ALERT: {} - {}", title, message);
	}
}
