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

package com.hack23.cia.architecturerules;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jdepend.framework.JDepend;

/**
 * The Class ArchitectureRuleTest.
 */
public final class ArchitectureRuleTest extends Assert {

	/** The jdepend. */
	JDepend jdepend = new JDepend();

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		jdepend.addDirectory("target/classes"); //$NON-NLS-1$
	}

	/**
	 * Test architecture no cycles allowed.
	 */
	@Test(timeout = 2000)
	public void testArchitectureNoCyclesAllowed() {
		jdepend.analyze();
		Assert.assertFalse("Project contains cycles", jdepend.containsCycles()); //$NON-NLS-1$
	}
}
