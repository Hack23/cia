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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Immutable data class containing performance metrics for an operation.
 * 
 * Captures total execution time, phase-level timings, and memory usage.
 */
public final class PerformanceMetrics {
	
	private final String operationName;
	private final long totalTimeMs;
	private final Map<String, Long> phaseDurations;
	private final long memoryUsedMb;
	
	/**
	 * Create performance metrics.
	 * 
	 * @param operationName name of the operation
	 * @param totalTimeMs total execution time in milliseconds
	 * @param phaseDurations map of phase names to durations in milliseconds
	 * @param memoryUsedMb memory used in megabytes
	 * @throws IllegalArgumentException if operationName is null
	 */
	public PerformanceMetrics(final String operationName, final long totalTimeMs, 
			final Map<String, Long> phaseDurations, final long memoryUsedMb) {
		if (operationName == null) {
			throw new IllegalArgumentException("operationName cannot be null");
		}
		this.operationName = operationName;
		this.totalTimeMs = totalTimeMs;
		this.phaseDurations = phaseDurations != null ? new HashMap<>(phaseDurations) : new HashMap<>();
		this.memoryUsedMb = memoryUsedMb;
	}
	
	/**
	 * Get the operation name.
	 * 
	 * @return operation name
	 */
	public String getOperationName() {
		return operationName;
	}
	
	/**
	 * Get total execution time in milliseconds.
	 * 
	 * @return total time in milliseconds
	 */
	public long getTotalTimeMs() {
		return totalTimeMs;
	}
	
	/**
	 * Get phase durations.
	 * 
	 * @return unmodifiable map of phase names to durations in milliseconds
	 */
	public Map<String, Long> getPhases() {
		return Collections.unmodifiableMap(phaseDurations);
	}
	
	/**
	 * Get duration of a specific phase.
	 * 
	 * @param phaseName name of the phase
	 * @return duration in milliseconds, or 0 if phase not found
	 */
	public long getPhaseTimeMs(final String phaseName) {
		return phaseDurations.getOrDefault(phaseName, 0L);
	}
	
	/**
	 * Get memory used in megabytes.
	 * 
	 * @return memory used in MB
	 */
	public long getMemoryUsedMb() {
		return memoryUsedMb;
	}
}
