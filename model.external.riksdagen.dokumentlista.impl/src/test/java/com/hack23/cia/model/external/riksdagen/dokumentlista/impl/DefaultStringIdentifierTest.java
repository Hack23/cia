/*
 * Copyright 2010 -2025 James Pether Sörling
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
package com.hack23.cia.model.external.riksdagen.dokumentlista.impl;

import org.junit.Test;

import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * The Class ModelSanityTest.
 */
public final class DefaultStringIdentifierTest extends AbstractUnitTest {

	@SuppressWarnings("resource")
	@Test
	public void basicTest() {
		final String arg0 = "argo";
		assertEquals(arg0,new DefaultStringIdentifier().fromDocumentIdentifier(arg0,null));
		assertEquals(arg0,new DefaultStringIdentifier().toDocumentIdentifier(arg0,null));

	}

}
