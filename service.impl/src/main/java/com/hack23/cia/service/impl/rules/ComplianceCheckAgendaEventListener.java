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
package com.hack23.cia.service.impl.rules;

import java.util.Map;

import org.drools.core.common.DefaultFactHandle;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;

import com.hack23.cia.service.api.action.kpi.ComplianceCheck;

/**
 * The Class ComplianceCheckAgendaEventListener.
 */
final class ComplianceCheckAgendaEventListener extends DefaultAgendaEventListener {

	/** The compliance checks. */
	private final Map<String, ComplianceCheck> complianceChecks;

	/**
	 * Instantiates a new compliance check agenda event listener.
	 *
	 * @param complianceChecks the compliance checks
	 */
	public ComplianceCheckAgendaEventListener(final Map<String, ComplianceCheck> complianceChecks) {
		this.complianceChecks = complianceChecks;
	}

	@Override
	public void afterMatchFired(final AfterMatchFiredEvent event) {
		super.afterMatchFired(event);
		final AbstractComplianceCheckImpl check = (AbstractComplianceCheckImpl) ((DefaultFactHandle) event.getMatch()
				.getFactHandles().stream().findFirst().orElse(null)).getObject();
		complianceChecks.put(check.getId(), check);
	}
}