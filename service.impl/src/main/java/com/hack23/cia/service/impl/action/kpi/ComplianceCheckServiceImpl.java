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
package com.hack23.cia.service.impl.action.kpi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.api.action.kpi.ComplianceCheck;
import com.hack23.cia.service.api.action.kpi.ComplianceCheckRequest;
import com.hack23.cia.service.api.action.kpi.ComplianceCheckResponse;
import com.hack23.cia.service.api.action.kpi.RuleViolation;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;
import com.hack23.cia.service.impl.action.common.BusinessService;
import com.hack23.cia.service.impl.rules.RulesEngine;

/**
 * The Class ComplianceCheckService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, timeout = 600)
public final class ComplianceCheckServiceImpl
		extends AbstractBusinessServiceImpl<ComplianceCheckRequest, ComplianceCheckResponse>
		implements BusinessService<ComplianceCheckRequest, ComplianceCheckResponse> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComplianceCheckServiceImpl.class);

	@Autowired
	private RulesEngine rulesEngine;

	/**
	 * Instantiates a new compliance check service.
	 */
	public ComplianceCheckServiceImpl() {
		super(ComplianceCheckRequest.class);
	}

	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN", "ROLE_ANONYMOUS" })
	public ComplianceCheckResponse processService(final ComplianceCheckRequest serviceRequest) {
		LOGGER.info("{}", serviceRequest.getClass().getSimpleName());
		final ComplianceCheckResponse inputValidation = inputValidation(serviceRequest);
		if (inputValidation != null) {
			return inputValidation;
		}


		final List<ComplianceCheck> complianceList = rulesEngine.checkRulesCompliance();

		final List<RuleViolation> ruleViolations = new ArrayList<>();

		for (final ComplianceCheck check : complianceList) {
			ruleViolations.addAll(check.getRuleViolations());
		}

		Collections.sort(complianceList, (o1, o2) -> Integer.compare(o2.getRuleViolations().size(), o1.getRuleViolations().size()));

		final ComplianceCheckResponse response = new ComplianceCheckResponse(ServiceResult.SUCCESS);
		response.setList(complianceList);
		response.setStatusMap(ruleViolations.stream().collect(Collectors.groupingBy(RuleViolation::getStatus)));
		response.setResourceTypeMap(
				ruleViolations.stream().collect(Collectors.groupingBy(RuleViolation::getResourceType)));

		final CreateApplicationEventRequest eventRequest = createApplicationEventForService(serviceRequest);
		eventRequest.setApplicationMessage(response.getResult().toString());
		getCreateApplicationEventService().processService(eventRequest);
		return response;
	}

	@Override
	protected CreateApplicationEventRequest createApplicationEventForService(
			final ComplianceCheckRequest serviceRequest) {
		final CreateApplicationEventRequest eventRequest = createBaseApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.USER);
		eventRequest.setApplicationOperation(ApplicationOperationType.READ);
		eventRequest.setActionName(ComplianceCheckRequest.class.getSimpleName());
		eventRequest.setSessionId(serviceRequest.getSessionId());
		return eventRequest;
	}

	@Override
	protected ComplianceCheckResponse createErrorResponse() {
		return new ComplianceCheckResponse(ServiceResult.FAILURE);
	}

}
