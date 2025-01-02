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
package com.hack23.cia.encryption.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

/**
 * The Class EncryptPropertyTest.
 */
public class EncryptPropertyTest {

	/**
	 * Encrypt password success test.
	 */
	@Test
	public void encryptPasswordSuccessTest() {
		final String symmetricKey = "password";
		final String value = "property";
		new EncryptProperty();
		final String encryptValue = EncryptProperty.encryptValue(symmetricKey, value);
		new EncryptProperty();
		assertEquals(value, EncryptProperty.decryptValue(symmetricKey, encryptValue));
	}

	/**
	 * Encrypt demo default test values.
	 */
	@Test
	public void encryptDemoDefaultTestValues() {
		final PrintStream out = mock(PrintStream.class);
		System.setOut(out);

		EncryptProperty.main(new String[] { "allhaildiscordia", "eris" });
		EncryptProperty.main(new String[] { "allhaildiscordia", "discord" });

		final ArgumentCaptor<String> StringCaptor = ArgumentCaptor.forClass(String.class);

		verify(out, times(4)).println(StringCaptor.capture());

		final List<String> capturedStrings = StringCaptor.getAllValues();

		assertTrue(capturedStrings.get(0).startsWith("Encrypted value:"));
		assertTrue(capturedStrings.get(1).startsWith("Encrypted property value:"));
		assertTrue(capturedStrings.get(2).startsWith("Encrypted value:"));
		assertTrue(capturedStrings.get(3).startsWith("Encrypted property value:"));

	}

	/**
	 * Encrypt property main no args.
	 */
	@Test
	public void encryptPropertyMainNoArgs() {
		final PrintStream out = mock(PrintStream.class);
		assertNotNull(out);
		System.setOut(out);

		EncryptProperty.main(new String[] {});
		verify(out).println("Encrypt property value with PBEWITHSHA256AND128BITAES_CBC_BC, using symmetric key and value as arguments. ./encryptProperty [key] [value]");
	}

}
