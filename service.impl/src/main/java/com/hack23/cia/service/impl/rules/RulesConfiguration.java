/*
 * Copyright 2010-2025 James Pether Sörling
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
package com.hack23.cia.service.impl.rules;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Class RulesConfiguration.
 */
@Configuration
public class RulesConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(RulesConfiguration.class);

	/**
	 * Kie container.
	 *
	 * @return the kie container
	 */
	@Bean
	public KieContainer kieContainer() {

		final KieServices kieServices = KieServices.Factory.get();
		final KieContainer kContainer = kieServices.getKieClasspathContainer();

		LOGGER.info("Using classloader {}, parent {}",kContainer.getClassLoader(),kContainer.getClassLoader().getParent());

		final Results verifyResults = kContainer.verify();
		for (final Message m : verifyResults.getMessages()) {
			LOGGER.warn("Kie container message: {}", m);
		}

		final KieBase kieBase = kContainer.getKieBase();


		for (final KiePackage kp : kieBase.getKiePackages()) {
			for (final Rule rule : kp.getRules()) {
				LOGGER.info("Loadded Rule: {} {}", kp, rule.getName());
			}
		}


		return kContainer;
	}
}
