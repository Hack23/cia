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
package com.hack23.cia.model.internal.application.data.party.impl;

import java.lang.reflect.Field;

import javax.persistence.Column;

import org.junit.Test;

import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * Test for ViewRiksdagenCoalitionAlignmentMatrix entity.
 * 
 * Verifies that JPA column mappings match the actual database view schema
 * to prevent SQL errors like "column does not exist".
 */
public final class ViewRiksdagenCoalitionAlignmentMatrixTest extends AbstractUnitTest {

	/**
	 * Test that column mappings match database view schema.
	 * 
	 * This test ensures the entity's @Column annotations match the actual
	 * column names in the view_riksdagen_coalition_alignment_matrix database view.
	 * 
	 * @throws Exception if reflection fails
	 */
	@Test
	public void testColumnMappingsMatchDatabaseSchema() throws Exception {
		// Verify sharedVotes maps to shared_votes
		assertColumnMapping("sharedVotes", "shared_votes");
		
		// Verify alignedVotes maps to aligned_votes
		assertColumnMapping("alignedVotes", "aligned_votes");
		
		// Verify opposedVotes maps to opposed_votes
		assertColumnMapping("opposedVotes", "opposed_votes");
		
		// Verify alignmentRate maps to alignment_rate
		assertColumnMapping("alignmentRate", "alignment_rate");
	}
	
	/**
	 * Test basic entity operations.
	 */
	@Test
	public void testEntityOperations() {
		final ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId embeddedId = 
			new ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId("S", "M");
		
		final ViewRiksdagenCoalitionAlignmentMatrix entity = new ViewRiksdagenCoalitionAlignmentMatrix();
		entity.setEmbeddedId(embeddedId);
		entity.setSharedVotes(100L);
		entity.setAlignedVotes(70L);
		entity.setOpposedVotes(30L);
		entity.setAlignmentRate(0.70);
		entity.setCoalitionLikelihood("MEDIUM");
		entity.setBlocRelationship("CROSS_BLOC");
		entity.setIntelligenceComment("Test comment");
		entity.setFirstYear(2020);
		entity.setLastYear(2024);
		entity.setYearsObserved(4);
		
		// Test getters
		assertEquals(embeddedId, entity.getEmbeddedId());
		assertEquals(Long.valueOf(100L), entity.getSharedVotes());
		assertEquals(Long.valueOf(70L), entity.getAlignedVotes());
		assertEquals(Long.valueOf(30L), entity.getOpposedVotes());
		assertEquals(Double.valueOf(0.70), entity.getAlignmentRate());
		assertEquals("MEDIUM", entity.getCoalitionLikelihood());
		assertEquals("CROSS_BLOC", entity.getBlocRelationship());
		assertEquals("Test comment", entity.getIntelligenceComment());
		assertEquals(Integer.valueOf(2020), entity.getFirstYear());
		assertEquals(Integer.valueOf(2024), entity.getLastYear());
		assertEquals(Integer.valueOf(4), entity.getYearsObserved());
		
		// Test equals, hashCode, toString
		assertNotNull(entity.toString());
		assertEquals(entity, entity);
		assertTrue(entity.hashCode() != 0);
	}
	
	/**
	 * Test compareTo method.
	 */
	@Test
	public void testCompareTo() {
		final ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId id1 = 
			new ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId("M", "S");
		final ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId id2 = 
			new ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId("S", "V");
		
		final ViewRiksdagenCoalitionAlignmentMatrix entity1 = new ViewRiksdagenCoalitionAlignmentMatrix();
		entity1.setEmbeddedId(id1);
		
		final ViewRiksdagenCoalitionAlignmentMatrix entity2 = new ViewRiksdagenCoalitionAlignmentMatrix();
		entity2.setEmbeddedId(id2);
		
		// Test self comparison
		assertEquals(0, entity1.compareTo(entity1));
		
		// Test comparison with different entities
		assertTrue(entity1.compareTo(entity2) != 0);
		
		// Test comparison with null
		assertTrue(entity1.compareTo(null) < 0);
		
		// Test entity with null embeddedId
		final ViewRiksdagenCoalitionAlignmentMatrix entityNullId = new ViewRiksdagenCoalitionAlignmentMatrix();
		assertTrue(entityNullId.compareTo(entity1) < 0);
	}
	
	/**
	 * Asserts that a field has the expected column name mapping.
	 * 
	 * @param fieldName the field name
	 * @param expectedColumnName the expected column name
	 * @throws Exception if reflection fails
	 */
	private void assertColumnMapping(final String fieldName, final String expectedColumnName) throws Exception {
		final Field field = ViewRiksdagenCoalitionAlignmentMatrix.class.getDeclaredField(fieldName);
		final Column columnAnnotation = field.getAnnotation(Column.class);
		
		assertNotNull("Field " + fieldName + " should have @Column annotation", columnAnnotation);
		assertEquals("Field " + fieldName + " should map to column " + expectedColumnName,
			expectedColumnName, columnAnnotation.name());
	}
}
