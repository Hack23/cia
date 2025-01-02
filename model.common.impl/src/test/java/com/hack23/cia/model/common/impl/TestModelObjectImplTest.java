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
package com.hack23.cia.model.common.impl;

import org.junit.Test;

import com.hack23.cia.testfoundation.AbstractTest;

/**
 * The Class TestModelObjectImplTest.
 */
public class TestModelObjectImplTest extends AbstractTest {

	/**
	 * The Class TestModelObject.
	 */
	class TestModelObject extends AbstractModelObjectImpl {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** The value. */
		private String value;

		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * Sets the value.
		 *
		 * @param value the new value
		 */
		public void setValue(final String value) {
			this.value = value;
		}

		/**
		 * With value.
		 *
		 * @param value the value
		 * @return the test model object
		 */
		public TestModelObject withValue(final String value) {
			this.value = value;
			return this;
		}

	}

	/**
	 * Basic test.
	 */
	@Test
	public void basicTest() {
		assertTrue(new TestModelObject().equals(new TestModelObject()));
		assertFalse(new TestModelObject().equals(new TestModelObject().withValue("different")));
		assertEquals("TestModelObjectImplTest.TestModelObject[value=someValue]", new TestModelObject().withValue("someValue").toString());
		assertNotSame(new TestModelObject().hashCode(),new TestModelObject().withValue("someValue").hashCode());
	}

}
