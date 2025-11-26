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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for PerformanceTimer.
 */
public class PerformanceTimerTest {

	/**
	 * Test basic timer operation.
	 */
	@Test
	public void testBasicTimer() {
		final PerformanceTimer timer = new PerformanceTimer("test_operation");
		
		timer.phase("phase1");
		sleep(10);
		
		timer.phase("phase2");
		sleep(10);
		
		timer.stop();
		
		final PerformanceMetrics metrics = timer.getMetrics();
		
		assertNotNull(metrics);
		assertEquals("test_operation", metrics.getOperationName());
		assertTrue("Total time should be at least 20ms", metrics.getTotalTimeMs() >= 20);
		assertTrue("Phase1 time should be positive", metrics.getPhaseTimeMs("phase1") > 0);
		assertTrue("Phase2 time should be positive", metrics.getPhaseTimeMs("phase2") > 0);
	}
	
	/**
	 * Test timer without phases.
	 */
	@Test
	public void testTimerWithoutPhases() {
		final PerformanceTimer timer = new PerformanceTimer("simple_operation");
		
		sleep(10);
		
		timer.stop();
		
		final PerformanceMetrics metrics = timer.getMetrics();
		
		assertNotNull(metrics);
		assertEquals("simple_operation", metrics.getOperationName());
		assertTrue("Total time should be at least 10ms", metrics.getTotalTimeMs() >= 10);
	}
	
	/**
	 * Test timer with error.
	 */
	@Test
	public void testTimerWithError() {
		final PerformanceTimer timer = new PerformanceTimer("error_operation");
		
		timer.phase("phase1");
		sleep(10);
		
		timer.error(new RuntimeException("Test error"));
		
		final PerformanceMetrics metrics = timer.getMetrics();
		
		assertNotNull(metrics);
		assertTrue("Total time should be positive", metrics.getTotalTimeMs() > 0);
		assertTrue("Phase1 time should be positive", metrics.getPhaseTimeMs("phase1") > 0);
	}
	
	/**
	 * Test multiple calls to stop.
	 */
	@Test
	public void testMultipleStopCalls() {
		final PerformanceTimer timer = new PerformanceTimer("multi_stop_operation");
		
		sleep(10);
		
		timer.stop();
		final long firstStopTime = timer.getMetrics().getTotalTimeMs();
		
		sleep(10);
		
		timer.stop();
		final long secondStopTime = timer.getMetrics().getTotalTimeMs();
		
		assertEquals("Multiple stop calls should not change metrics", firstStopTime, secondStopTime);
	}
	
	/**
	 * Test getting phase time for non-existent phase.
	 */
	@Test
	public void testGetNonExistentPhaseTime() {
		final PerformanceTimer timer = new PerformanceTimer("test_operation");
		
		timer.phase("phase1");
		sleep(10);
		
		timer.stop();
		
		final PerformanceMetrics metrics = timer.getMetrics();
		
		assertEquals("Non-existent phase should return 0", 0L, metrics.getPhaseTimeMs("non_existent"));
	}
	
	/**
	 * Helper method to sleep for specified milliseconds.
	 * 
	 * @param millis milliseconds to sleep
	 */
	private void sleep(final long millis) {
		try {
			Thread.sleep(millis);
		} catch (final InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
