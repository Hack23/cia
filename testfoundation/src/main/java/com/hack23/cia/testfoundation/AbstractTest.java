/*
 * Copyright 2010 James Pether Sörling
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
 *	$Id: AbstractTest.java 6080 2015-05-25 23:04:36Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/testfoundation/src/main/java/com/hack23/cia/testfoundation/AbstractTest.java $
 */

package com.hack23.cia.testfoundation;

import org.junit.Assert;

/**
 * The Class AbstractTest.
 */
public abstract class AbstractTest extends Assert  {

	/**
	 * Instantiates a new abstract test.
	 */
	protected AbstractTest() {
		super();	
	}

}
