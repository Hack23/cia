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
package com.hack23.cia.testfoundation;

import java.util.concurrent.Executors;

import org.databene.contiperf.junit.ParallelScheduler;
import org.junit.runners.Parameterized;

/**
 * The Class Parallelized.
 */
public final class Parallelized extends Parameterized {

	/**
	 * Instantiates a new parallelized.
	 *
	 * @param klass
	 *            the klass
	 * @throws Throwable
	 *             the throwable
	 */
	public Parallelized(final Class<?> klass) throws Throwable {
		super(klass);
		setScheduler(new ParallelScheduler(Executors.newCachedThreadPool()));
	}
}