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

import java.io.Serializable;

import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * The Class AbstractJob.
 */
public abstract class AbstractJob extends QuartzJobBean implements Serializable{

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractJob.class);

	/** The Constant APPLICATION_CONTEXT. */
	private static final String APPLICATION_CONTEXT = "applicationContext";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Gets the job context holder.
	 *
	 * @param jobContext
	 *            the job context
	 * @return the job context holder
	 */
	protected static final JobContextHolder getJobContextHolder(final JobExecutionContext jobContext) {
		final Scheduler scheduler = jobContext.getScheduler();
		    JobContextHolder bean = null;
		    try {
		    	final SchedulerContext schedulerContext = scheduler.getContext();
		        final ApplicationContext appContext = (ApplicationContext) schedulerContext.get(APPLICATION_CONTEXT);
		        bean = appContext.getBean(JobContextHolder.class);
		    } catch (final SchedulerException e) {
		       LOGGER.error("Failed to get JobContextHolder",e );
		    }

		return bean;
	}

}
