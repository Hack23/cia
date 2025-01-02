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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.data.rules.impl.RuleViolation;
import com.hack23.cia.service.api.action.kpi.ComplianceCheck;
import com.hack23.cia.service.data.api.RuleViolationDAO;

/**
 * The Class ComplianceCheckService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, timeout = 300)
public final class RulesManagerImpl implements RulesManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RulesManagerImpl.class);

	@Autowired
	private RulesEngine rulesEngine;

	@Autowired
	@Qualifier("RuleViolationDAO")
	private RuleViolationDAO ruleViolationDAO;

	/**
	 * Instantiates a new compliance check service.
	 */
	public RulesManagerImpl() {
	}

	@Override
	public void processService() {
		LOGGER.info("{}", RulesManagerImpl.class.getClass().getSimpleName());

		final List<ComplianceCheck> complianceList = rulesEngine.checkRulesCompliance();

		for (final RuleViolation ruleViolation : ruleViolationDAO.getAll()) {
			ruleViolationDAO.delete(ruleViolation);
		}

		for (final ComplianceCheck complianceCheck : complianceList) {
			ruleViolationDAO.persist(complianceCheck.getRuleViolations());
		}
	}
}
