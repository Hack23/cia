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
package com.hack23.cia.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.data.ministry.impl.GovernmentBodyData;
import com.hack23.cia.service.data.api.GovernmentBodyDataDAO;
import com.hack23.cia.service.external.esv.api.EsvApi;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;

/**
 * The Class GovernmentBodyDataLoaderService.
 * Loads government body data from ESV at application startup if not already loaded.
 */
@Component
public class GovernmentBodyDataLoaderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GovernmentBodyDataLoaderService.class);

	@Autowired
	private GovernmentBodyDataDAO governmentBodyDataDAO;

	@Autowired
	private EsvApi esvApi;

	/**
	 * Instantiates a new government body data loader service.
	 */
	public GovernmentBodyDataLoaderService() {
		super();
	}

	/**
	 * Load government body data at startup if table is empty.
	 */
	@PostConstruct
	@Transactional(propagation = Propagation.REQUIRED)
	public void loadGovernmentBodyDataIfEmpty() {
		try {
			configureAuthentication("ROLE_ADMIN");
			
			final List<GovernmentBodyData> existingData = governmentBodyDataDAO.getAll();
			
			if (existingData == null || existingData.isEmpty()) {
				LOGGER.info("Government body data table is empty, loading data from ESV...");
				loadDataFromEsv();
				LOGGER.info("Government body data loaded successfully");
			} else {
				LOGGER.info("Government body data already exists ({} records), skipping load", existingData.size());
			}
		} catch (final Exception e) {
			LOGGER.error("Failed to load government body data - application startup aborted", e);
			throw new RuntimeException("Failed to initialize government body data", e);
		} finally {
			clearAuthentication();
		}
	}

	/**
	 * Load data from ESV API using batch processing for efficiency.
	 */
	private void loadDataFromEsv() {
		final Map<Integer, List<GovernmentBodyAnnualSummary>> data = esvApi.getData();
		
		final List<GovernmentBodyData> entitiesToPersist = new ArrayList<>();
		int recordCount = 0;
		
		for (final Map.Entry<Integer, List<GovernmentBodyAnnualSummary>> entry : data.entrySet()) {
			final Integer year = entry.getKey();
			final List<GovernmentBodyAnnualSummary> summaries = entry.getValue();
			
			for (final GovernmentBodyAnnualSummary summary : summaries) {
				final GovernmentBodyData entity = new GovernmentBodyData(
					year,
					summary.getName(),
					summary.getConsecutiveNumber(),
					summary.getGovermentBodyId(),
					summary.getmCode(),
					summary.getMinistry(),
					summary.getOrgNumber(),
					summary.getHeadCount(),
					summary.getAnnualWorkHeadCount(),
					summary.getVat(),
					summary.getComment()
				);
				
				entitiesToPersist.add(entity);
				recordCount++;
			}
		}
		
		// Use batch persist for better performance
		if (!entitiesToPersist.isEmpty()) {
			governmentBodyDataDAO.persist(entitiesToPersist);
			LOGGER.info("Loaded {} government body records from ESV", recordCount);
		} else {
			LOGGER.warn("No government body records found in ESV data");
		}
	}

	/**
	 * Clear authentication.
	 */
	private static void clearAuthentication() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	/**
	 * Configure authentication.
	 *
	 * @param role the role
	 */
	private static void configureAuthentication(final String role) {
		final Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role);
		final Authentication authentication = new UsernamePasswordAuthenticationToken("service.impl.GovernmentBodyDataLoaderService", "n/a", authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
