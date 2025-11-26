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

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.junit.Test;

import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * Unit test for PerformanceMetricsServiceImpl.
 */
public class PerformanceMetricsServiceImplTest extends AbstractUnitTest {

	/**
	 * Test start timer.
	 */
	@Test
	public void testStartTimer() {
		final PerformanceMetricsService service = new PerformanceMetricsServiceImpl();
		
		final PerformanceTimer timer = service.startTimer("test_operation");
		
		assertNotNull("Timer should not be null", timer);
	}
	
	/**
	 * Test record metrics.
	 */
	@Test
	public void testRecordMetrics() {
		final PerformanceMetricsService service = new PerformanceMetricsServiceImpl();
		
		final PerformanceTimer timer = service.startTimer("test_operation");
		timer.stop();
		
		final PerformanceMetrics metrics = timer.getMetrics();
		
		service.recordMetrics("test_operation", "test_context", metrics);
	}
	
	/**
	 * Test send performance alert.
	 */
	@Test
	public void testSendPerformanceAlert() {
		final PerformanceMetricsService service = new PerformanceMetricsServiceImpl();
		
		service.sendPerformanceAlert("Test Alert", "Test message");
	}
	
	/**
	 * Test record metrics with null metrics parameter.
	 */
	@Test
	public void testRecordMetricsWithNullMetrics() {
		final PerformanceMetricsService service = new PerformanceMetricsServiceImpl();
		
		// Should log warning and not throw exception
		service.recordMetrics("test_operation", "test_context", null);
	}
	
	/**
	 * Test send alert with null title.
	 */
	@Test
	public void testSendPerformanceAlertWithNullTitle() {
		final PerformanceMetricsService service = new PerformanceMetricsServiceImpl();
		
		// Should log warning and not throw exception
		service.sendPerformanceAlert(null, "Test message");
	}
	
	/**
	 * Test send alert with null message.
	 */
	@Test
	public void testSendPerformanceAlertWithNullMessage() {
		final PerformanceMetricsService service = new PerformanceMetricsServiceImpl();
		
		// Should log warning and not throw exception
		service.sendPerformanceAlert("Test Alert", null);
	}
	
	/**
	 * Test record metrics with various edge cases.
	 */
	@Test
	public void testRecordMetricsEdgeCases() {
		final PerformanceMetricsService service = new PerformanceMetricsServiceImpl();
		
		// Test with zero time
		final PerformanceMetrics zeroTimeMetrics = createTestMetrics("zero_time", 0L, 0L);
		service.recordMetrics("zero_time", "test", zeroTimeMetrics);
		
		// Test with empty phases
		final PerformanceMetrics emptyPhasesMetrics = createTestMetrics("empty_phases", 100L, 50L);
		service.recordMetrics("empty_phases", "test", emptyPhasesMetrics);
		
		// Test with null context
		service.recordMetrics("test", null, zeroTimeMetrics);
	}
	
	/**
	 * Test PerformanceMetrics with null operation name.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPerformanceMetricsWithNullOperationName() {
		new PerformanceMetrics(null, 100L, new HashMap<>(), 50L);
	}
	
	/**
	 * Helper method to create test metrics.
	 * 
	 * @param operationName the operation name
	 * @param totalTimeMs the total time in milliseconds
	 * @param memoryUsedMb the memory used in MB
	 * @return test performance metrics
	 */
	private PerformanceMetrics createTestMetrics(final String operationName, final long totalTimeMs, final long memoryUsedMb) {
		return new PerformanceMetrics(operationName, totalTimeMs, new HashMap<>(), memoryUsedMb);
	}
}
