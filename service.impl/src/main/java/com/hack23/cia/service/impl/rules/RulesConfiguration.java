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

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.drools.model.codegen.ExecutableModelProject;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * The Class RulesConfiguration.
 */
@Configuration
public class RulesConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(RulesConfiguration.class);

	/** DRL rules classpath pattern. */
	private static final String DRL_PATTERN = "classpath*:com/hack23/cia/service/impl/rules/**/*.drl";

	/** Prefix for DRL resource paths in KieFileSystem. */
	private static final String DRL_PREFIX = "com/hack23/cia/service/impl/rules/";

	/**
	 * Kie container.
	 *
	 * Uses ExecutableModelProject for rule compilation to avoid ECJ compiler
	 * compatibility issues with Java 26 module system.
	 *
	 * @return the kie container
	 */
	@Bean
	public KieContainer kieContainer() {

		final KieServices kieServices = KieServices.Factory.get();
		final KieFileSystem kfs = kieServices.newKieFileSystem();

		loadKmodule(kieServices, kfs);
		loadDrlResources(kieServices, kfs);

		try {
			final KieBuilder kieBuilder = kieServices.newKieBuilder(kfs);
			kieBuilder.buildAll(ExecutableModelProject.class);

			final Results buildResults = kieBuilder.getResults();
			for (final Message m : buildResults.getMessages()) {
				LOGGER.warn("Kie build message: {} - {}", m.getLevel(), m.getText());
			}

			final KieContainer kContainer = kieServices.newKieContainer(
					kieBuilder.getKieModule().getReleaseId());

			LOGGER.info("Using classloader {}, parent {}",
					kContainer.getClassLoader(), kContainer.getClassLoader().getParent());

			final KieBase kieBase = kContainer.getKieBase();

			for (final KiePackage kp : kieBase.getKiePackages()) {
				for (final Rule rule : kp.getRules()) {
					LOGGER.info("Loaded Rule: {} {}", kp, rule.getName());
				}
			}

			return kContainer;
		} catch (final Exception e) {
			LOGGER.error("Failed to compile rules with ExecutableModelProject, "
					+ "falling back to classpath container with native compiler", e);
			System.setProperty("drools.dialect.java.compiler", "NATIVE");
			return kieServices.getKieClasspathContainer();
		}
	}

	/**
	 * Load kmodule.xml from classpath.
	 *
	 * @param kieServices the kie services
	 * @param kfs the kie file system
	 */
	private void loadKmodule(final KieServices kieServices, final KieFileSystem kfs) {
		try (InputStream kmoduleStream = getClass().getClassLoader()
				.getResourceAsStream("META-INF/kmodule.xml")) {
			if (kmoduleStream != null) {
				kfs.write("src/main/resources/META-INF/kmodule.xml",
						kieServices.getResources().newByteArrayResource(
								kmoduleStream.readAllBytes()));
			}
		} catch (final IOException e) {
			LOGGER.warn("Failed to read kmodule.xml", e);
		}
	}

	/**
	 * Load all DRL resources from the classpath.
	 *
	 * @param kieServices the kie services
	 * @param kfs the kie file system
	 */
	private void loadDrlResources(final KieServices kieServices, final KieFileSystem kfs) {
		final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			final Resource[] resources = resolver.getResources(DRL_PATTERN);
			for (final Resource resource : resources) {
				try (InputStream is = resource.getInputStream()) {
					final byte[] bytes = is.readAllBytes();
					final String content = new String(bytes, StandardCharsets.UTF_8);
					if (content.trim().isEmpty() || !content.contains("rule ")) {
						continue;
					}
					final String resourcePath = resource.getURL().getPath();
					final int idx = resourcePath.indexOf(DRL_PREFIX);
					if (idx >= 0) {
						kfs.write("src/main/resources/" + resourcePath.substring(idx),
								kieServices.getResources().newByteArrayResource(bytes));
					}
				}
			}
			LOGGER.info("Loaded DRL resources from {}", DRL_PATTERN);
		} catch (final IOException e) {
			LOGGER.error("Failed to load DRL resources", e);
		}
	}
}
