package com.hack23.cia.service.data.impl.util;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import com.hack23.cia.testfoundation.AbstractUnitTest;

public class SecretDataTest extends AbstractUnitTest {

	/**
	 * Are well implemented test.
	 */
	@Test
	public void areWellImplementedTest() {
		assertPojoMethodsFor(SecretData.class).areWellImplemented();
	}

}
