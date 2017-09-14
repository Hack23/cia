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

public abstract class AbstractJob extends QuartzJobBean implements Serializable{

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractJob.class);

	private static final String APPLICATION_CONTEXT = "applicationContext";
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected final JobContextHolder getJobContextHolder(final JobExecutionContext jobContext) {
		final Scheduler scheduler = jobContext.getScheduler();
		    JobContextHolder bean = null;
		    try {
		    	SchedulerContext schedulerContext = scheduler.getContext();
		        final ApplicationContext appContext = (ApplicationContext) schedulerContext.get(APPLICATION_CONTEXT);
		        bean = appContext.getBean(JobContextHolder.class);
		    } catch (final SchedulerException e) {
		       LOGGER.error("Failed to get JobContextHolder",e );
		    }

		return bean;
	}

}
