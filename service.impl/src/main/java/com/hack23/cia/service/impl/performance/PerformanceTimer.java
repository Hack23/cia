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

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Timer for tracking performance of operations across multiple phases.
 * 
 * Tracks execution time for an operation broken down into phases,
 * enabling detailed performance analysis and bottleneck identification.
 */
public final class PerformanceTimer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceTimer.class);
	
	private final String operationName;
	private final long startTime;
	private final Map<String, Long> phaseStartTimes;
	private final Map<String, Long> phaseDurations;
	private String currentPhase;
	private long stopTime;
	private boolean stopped;
	
	/**
	 * Create a new performance timer for the specified operation.
	 * 
	 * @param operationName name of the operation being timed
	 */
	public PerformanceTimer(final String operationName) {
		this.operationName = operationName;
		this.startTime = System.currentTimeMillis();
		this.phaseStartTimes = new HashMap<>();
		this.phaseDurations = new HashMap<>();
		this.currentPhase = null;
		this.stopped = false;
	}
	
	/**
	 * Start a new phase of the operation.
	 * Completes timing for the current phase if one is active.
	 * 
	 * @param phaseName name of the phase to start
	 */
	public void phase(final String phaseName) {
		final long now = System.currentTimeMillis();
		
		if (currentPhase != null) {
			final long duration = now - phaseStartTimes.get(currentPhase);
			phaseDurations.put(currentPhase, duration);
		}
		
		currentPhase = phaseName;
		phaseStartTimes.put(phaseName, now);
	}
	
	/**
	 * Stop the timer and complete timing for the current phase.
	 */
	public void stop() {
		if (stopped) {
			return;
		}
		
		stopTime = System.currentTimeMillis();
		
		if (currentPhase != null) {
			final long duration = stopTime - phaseStartTimes.get(currentPhase);
			phaseDurations.put(currentPhase, duration);
		}
		
		stopped = true;
	}
	
	/**
	 * Get performance metrics for the timed operation.
	 * 
	 * @return performance metrics
	 */
	public PerformanceMetrics getMetrics() {
		if (!stopped) {
			stop();
		}
		
		final long totalTime = stopTime - startTime;
		final long memoryUsed = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024);
		
		return new PerformanceMetrics(operationName, totalTime, phaseDurations, memoryUsed);
	}
	
	/**
	 * Record an error during the timed operation.
	 * 
	 * @param e the exception that occurred
	 */
	public void error(final Exception e) {
		stop();
		LOGGER.error("Performance tracking error in {}: {}", operationName, e.getMessage());
	}
}
