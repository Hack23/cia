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
package com.hack23.cia.service.data.impl.liquidbase;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;

/**
 * The Class SpringLiquidBaseFailSafe.
 */
public final class SpringLiquidBaseFailSafe extends SpringLiquibase {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SpringLiquidBaseFailSafe.class);


	/** (non-Javadoc)
	 * @see liquibase.integration.spring.SpringLiquibase#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws LiquibaseException {
		try {
			super.afterPropertiesSet();
		} catch (final Exception e) {
			final String stackTrace = ExceptionUtils.getStackTrace(e);
			if( stackTrace.contains("Connection was already closed - calling hashCode is no longer allowed")) {
				LOGGER.warn("Problem after executing liquidbase, failed removing closed atomikos connection");
			} else {
				LOGGER.warn("Problem executing liquidbase", e);
			}

		}
	}

}
