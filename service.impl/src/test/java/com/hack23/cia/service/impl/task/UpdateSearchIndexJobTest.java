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
package com.hack23.cia.service.impl.task;

import org.junit.Test;
import org.mockito.Mockito;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;

import com.hack23.cia.service.data.api.SearchIndexer;

/**
 * The Class UpdateSearchIndexJobTest.
 */
public class UpdateSearchIndexJobTest extends AbstractJobTest {

	/**
	 * Execute internal success test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void executeInternalSuccessTest() throws Exception {
		final JobExecutionContext jobContextMock = Mockito.mock(JobExecutionContext.class);
		final ApplicationContext applicationContext = prepareContextMock(jobContextMock);

		final SearchIndexer searchIndex = Mockito.mock(SearchIndexer.class);
		final JobContextHolder jobContextHolder = new JobContextHolderImpl(null, searchIndex, null,null);
		Mockito.when(applicationContext.getBean(JobContextHolder.class)).thenReturn(jobContextHolder);

		new UpdateSearchIndexJob().executeInternal(jobContextMock);
		Mockito.verify(searchIndex).updateSearchIndex();
	}

	/**
	 * Execute internal failure test.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = JobExecutionException.class)
	public void executeInternalFailureTest() throws Exception {
		final JobExecutionContext jobContextMock = Mockito.mock(JobExecutionContext.class);
		final ApplicationContext applicationContext = prepareContextMock(jobContextMock);

		final SearchIndexer searchIndex = Mockito.mock(SearchIndexer.class);
		final JobContextHolder jobContextHolder = new JobContextHolderImpl(null, searchIndex, null,null);
		Mockito.when(applicationContext.getBean(JobContextHolder.class)).thenReturn(jobContextHolder);

		Mockito.doThrow(new InterruptedException()).when(searchIndex).updateSearchIndex();

		new UpdateSearchIndexJob().executeInternal(jobContextMock);
	}

	@Test(expected = NullPointerException.class)
	public void executeInternalFailureNoJobContextTest() throws Exception {
		final JobExecutionContext jobContextMock = Mockito.mock(JobExecutionContext.class);
		final Scheduler scheduler = Mockito.mock(Scheduler.class);
		Mockito.when(jobContextMock.getScheduler()).thenReturn(scheduler);
		Mockito.when(scheduler.getContext()).thenThrow(new SchedulerException());
		new UpdateSearchIndexJob().executeInternal(jobContextMock);
	}

}
