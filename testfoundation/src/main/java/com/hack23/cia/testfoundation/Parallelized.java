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
 *	$Id$
 *  $HeadURL$
 */
package com.hack23.cia.testfoundation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.runners.Parameterized;
import org.junit.runners.model.RunnerScheduler;

/**
 * The Class Parallelized.
 */
public class Parallelized extends Parameterized
{

	/**
	 * The Class ThreadPoolScheduler.
	 */
	private static class ThreadPoolScheduler implements RunnerScheduler
	{

		/** The executor. */
		private final ExecutorService executor;

		/**
		 * Instantiates a new thread pool scheduler.
		 */
		public ThreadPoolScheduler()
		{
			final String threads = System.getProperty("junit.parallel.threads", "2");
			final int numThreads = Integer.parseInt(threads);
			executor = Executors.newFixedThreadPool(numThreads);
		}

		/* (non-Javadoc)
		 * @see org.junit.runners.model.RunnerScheduler#finished()
		 */
		@Override
		public void finished()
		{
			executor.shutdown();
			try
			{
				executor.awaitTermination(4, TimeUnit.MINUTES);
			}
			catch (final InterruptedException exc)
			{
				throw new RuntimeException(exc);
			}
		}

		/* (non-Javadoc)
		 * @see org.junit.runners.model.RunnerScheduler#schedule(java.lang.Runnable)
		 */
		@Override
		public void schedule(final Runnable childStatement)
		{
			executor.submit(childStatement);
		}
	}

	/**
	 * Instantiates a new parallelized.
	 *
	 * @param klass
	 *            the klass
	 * @throws Throwable
	 *             the throwable
	 */
	public Parallelized(final Class<?> klass) throws Throwable
	{
		super(klass);
		setScheduler(new ThreadPoolScheduler());
	}
}