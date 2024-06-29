/*
 * Copyright 2010-2024 James Pether Sörling
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
package com.hack23.cia.web.widgets.charts;

import java.util.UUID;

import org.junit.Test;

import com.hack23.cia.testfoundation.AbstractUnitTest;

public class SankeyChartTest extends AbstractUnitTest {

	/**
	 * Hash code equals test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void hashCodeEqualsTest() throws Exception {
		SankeyChart sankeyChart = new SankeyChart();
		sankeyChart.setId(UUID.randomUUID().toString());
		SankeyChart sankeyChart2 = new SankeyChart();
		sankeyChart2.setId(UUID.randomUUID().toString());
		
		assertNotEquals(sankeyChart.hashCode(),sankeyChart2.hashCode());
		assertFalse(sankeyChart.equals(sankeyChart2));

	}
}
