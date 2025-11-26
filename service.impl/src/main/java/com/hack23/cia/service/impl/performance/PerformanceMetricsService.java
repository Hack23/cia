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

/**
 * Service for tracking and managing application performance metrics.
 * 
 * Provides capabilities for:
 * - Creating performance timers
 * - Recording metrics
 * - Logging performance data
 * - Sending performance alerts
 */
public interface PerformanceMetricsService {
	
	/**
	 * Start a new performance timer for an operation.
	 * 
	 * @param operationName name of the operation to time
	 * @return performance timer instance
	 */
	PerformanceTimer startTimer(String operationName);
	
	/**
	 * Record performance metrics.
	 * 
	 * @param operationName name of the operation
	 * @param context contextual information (e.g., year, parameters)
	 * @param metrics performance metrics to record
	 */
	void recordMetrics(String operationName, String context, PerformanceMetrics metrics);
	
	/**
	 * Send a performance alert for degraded performance.
	 * 
	 * @param title alert title
	 * @param message alert message
	 */
	void sendPerformanceAlert(String title, String message);
}
