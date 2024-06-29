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

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;

import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * The Class AbstractJobTest.
 */
public class AbstractJobTest extends AbstractUnitTest {

	/**
	 * Prepare context mock.
	 *
	 * @param jobContextMock the job context mock
	 * @return the application context
	 * @throws SchedulerException the scheduler exception
	 */
	protected  ApplicationContext prepareContextMock(final JobExecutionContext jobContextMock) throws SchedulerException {
		final Scheduler scheduler = Mockito.mock(Scheduler.class);
		Mockito.when(jobContextMock.getScheduler()).thenReturn(scheduler);
		final SchedulerContext schedulerContext = Mockito.mock(SchedulerContext.class);
		Mockito.when(scheduler.getContext()).thenReturn(schedulerContext);
		final ApplicationContext applicationContext = Mockito.mock(ApplicationContext.class);
		Mockito.when(schedulerContext.get(ArgumentMatchers.any(String.class))).thenReturn(applicationContext);
		return applicationContext;
	}

}
