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
package com.hack23.cia.service.data.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hack23.cia.model.internal.application.data.impl.ViewDecisionTemporalTrends;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenCoalitionAlignmentMatrix;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId;
import com.hack23.cia.model.internal.application.data.rules.impl.ResourceType;
import com.hack23.cia.model.internal.application.data.rules.impl.RuleViolation;
import com.hack23.cia.model.internal.application.data.rules.impl.Status;
import com.hack23.cia.service.data.api.RuleViolationDAO;
import com.hack23.cia.service.data.api.ViewDecisionTemporalTrendsDAO;
import com.hack23.cia.service.data.api.ViewRiksdagenCoalitionAlignmentMatrixDAO;

/**
 * Test class for IntelligenceExportServiceImpl.
 * 
 * @author intelligence-operative
 * @since v1.36
 */
class IntelligenceExportServiceImplTest {

	@Mock
	private RuleViolationDAO ruleViolationDAO;

	@Mock
	private ViewRiksdagenCoalitionAlignmentMatrixDAO coalitionAlignmentDAO;

	@Mock
	private ViewDecisionTemporalTrendsDAO temporalTrendsDAO;

	@InjectMocks
	private IntelligenceExportServiceImpl service;

	private Path tempDir;

	@BeforeEach
	void setUp() throws IOException {
		MockitoAnnotations.openMocks(this);
		tempDir = Files.createTempDirectory("test-json-export");
	}

	@AfterEach
	void tearDown() throws IOException {
		// Clean up temp directory
		if (tempDir != null && Files.exists(tempDir)) {
			Files.walk(tempDir)
				.sorted((a, b) -> b.compareTo(a))
				.forEach(path -> {
					try {
						Files.deleteIfExists(path);
					} catch (IOException e) {
						// Ignore cleanup errors
					}
				});
		}
	}

	@Test
	void testExportRiskAssessments() throws Exception {
		// Arrange
		final List<RuleViolation> violations = new ArrayList<>();
		final RuleViolation violation = new RuleViolation(
			"ref123",
			"Test Person",
			ResourceType.POLITICIAN,
			"HIGH_ABSENCE_RATE",
			"High absence from parliamentary sessions",
			"Attendance",
			Status.MAJOR,
			"Improve attendance"
		);
		violations.add(violation);

		when(ruleViolationDAO.getAll()).thenReturn(violations);

		// Act
		final String json = service.exportRiskAssessments();

		// Assert
		assertNotNull(json);
		assertTrue(json.contains("metadata"));
		assertTrue(json.contains("violations"));
		assertTrue(json.contains("HIGH_ABSENCE_RATE"));
		assertTrue(json.contains("Test Person"));
	}

	@Test
	void testExportCoalitionAlignment() throws Exception {
		// Arrange
		final List<ViewRiksdagenCoalitionAlignmentMatrix> alignments = new ArrayList<>();
		final ViewRiksdagenCoalitionAlignmentMatrix alignment = new ViewRiksdagenCoalitionAlignmentMatrix();
		final ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId embeddedId = 
			new ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId();
		embeddedId.setParty1("S");
		embeddedId.setParty2("V");
		alignment.setEmbeddedId(embeddedId);
		alignment.setAlignmentRate(0.75);
		alignment.setSharedVotes(100L);
		alignment.setAlignedVotes(75L);
		alignments.add(alignment);

		when(coalitionAlignmentDAO.getAll()).thenReturn(alignments);

		// Act
		final String json = service.exportCoalitionAlignment();

		// Assert
		assertNotNull(json);
		assertTrue(json.contains("metadata"));
		assertTrue(json.contains("alignments"));
		assertTrue(json.contains("\"party1\""));
		assertTrue(json.contains("\"party2\""));
	}

	@Test
	void testExportTemporalTrends() throws Exception {
		// Arrange
		final List<ViewDecisionTemporalTrends> trends = new ArrayList<>();
		final ViewDecisionTemporalTrends trend = new ViewDecisionTemporalTrends();
		trend.setDecisionDay(new Date());
		trend.setDailyDecisions(50L);
		trend.setDailyApprovalRate(new BigDecimal("0.75"));
		trend.setApprovedDecisions(38L);
		trend.setRejectedDecisions(12L);
		trend.setMa7dayDecisions(new BigDecimal("45.5"));
		trend.setMa30dayDecisions(new BigDecimal("48.2"));
		trends.add(trend);

		when(temporalTrendsDAO.getAll()).thenReturn(trends);

		// Act
		final String json = service.exportTemporalTrends();

		// Assert
		assertNotNull(json);
		assertTrue(json.contains("metadata"));
		assertTrue(json.contains("trends"));
		assertTrue(json.contains("dailyDecisions"));
		assertTrue(json.contains("dailyApprovalRate"));
	}

	@Test
	void testWriteJsonToFile() throws Exception {
		// Arrange
		final String jsonContent = "{\"test\": \"data\"}";
		final String fileName = "test-output.json";

		// Act
		service.writeJsonToFile(jsonContent, fileName, tempDir.toString());

		// Assert
		final Path filePath = tempDir.resolve(fileName);
		assertTrue(Files.exists(filePath));
		final String content = Files.readString(filePath);
		assertTrue(content.equals(jsonContent));
	}

	@Test
	void testWriteJsonToFileCreatesDirectory() throws Exception {
		// Arrange
		final String jsonContent = "{\"test\": \"data\"}";
		final String fileName = "test-output.json";
		final Path newDir = tempDir.resolve("newdir");

		// Act
		service.writeJsonToFile(jsonContent, fileName, newDir.toString());

		// Assert
		assertTrue(Files.exists(newDir));
		final Path filePath = newDir.resolve(fileName);
		assertTrue(Files.exists(filePath));
	}

	@Test
	void testWriteJsonToFileRejectsPathTraversal() {
		// Arrange
		final String jsonContent = "{\"test\": \"data\"}";

		// Act & Assert - Test various path traversal attempts
		assertThrows(IllegalArgumentException.class, () -> 
			service.writeJsonToFile(jsonContent, "../etc/passwd", tempDir.toString()));
		
		assertThrows(IllegalArgumentException.class, () -> 
			service.writeJsonToFile(jsonContent, "..\\windows\\system32\\config", tempDir.toString()));
		
		assertThrows(IllegalArgumentException.class, () -> 
			service.writeJsonToFile(jsonContent, "path/to/file.json", tempDir.toString()));
		
		assertThrows(IllegalArgumentException.class, () -> 
			service.writeJsonToFile(jsonContent, "path\\to\\file.json", tempDir.toString()));
	}
}
