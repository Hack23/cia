/*
 * Copyright 2010-2021 James Pether Sörling
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
package com.hack23.cia.service.api.action;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import com.hack23.cia.service.api.action.admin.ManageUserAccountRequest;
import com.hack23.cia.service.api.action.admin.ManageUserAccountResponse;
import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * The Class ApiActionPojoTest.
 */
public class ApiActionPojoTest extends AbstractUnitTest {


	/**
	 * Are well implemented test.
	 */
	@Test
	public void areWellImplementedTest() {
		assertPojoMethodsFor(ManageUserAccountRequest.class).areWellImplemented();
		assertPojoMethodsFor(ManageUserAccountResponse.class).areWellImplemented();
	}

}
