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
package com.hack23.cia.service.impl.rules;

import java.util.ArrayList;
import java.util.List;

import org.drools.core.common.DefaultFactHandle;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.service.api.action.kpi.ComplianceCheck;
import com.hack23.cia.service.data.api.DataViewer;

/**
 * The Class RulesEngineImpl.
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public final class RulesEngineImpl implements RulesEngine {

	/** The Constant politicianDrlFile. */
	private static final String politicianDrlFile = "rules/politician/PoliticianLeftPartyStillHoldingPositions.drl";
	
	private static final String politicianDrlFile2 = "rules/politician/PoliticianTimeToRetire.drl";
	
	private static final String politicianDrlFile3 = "rules/politician/PoliticianBusySchedule.drl";

	private static final String politicianDrlFile4 = "rules/politician/PoliticianMinisterWithoutParliamentExperience.drl";
		
	/** The Constant partyDrlFile. */
	private static final String partyDrlFile = "rules/party/PartyNoGovernmentExperience.drl";
		
	/** The data viewer. */
	@Autowired
	@Qualifier("DataViewer")
	private DataViewer dataViewer;

	@Override
	public List<ComplianceCheck> checkRulesCompliance() {
		KieServices kieServices = KieServices.Factory.get();

		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		kieFileSystem.write(ResourceFactory.newClassPathResource(politicianDrlFile));
		kieFileSystem.write(ResourceFactory.newClassPathResource(politicianDrlFile2));
		kieFileSystem.write(ResourceFactory.newClassPathResource(politicianDrlFile3));
		kieFileSystem.write(ResourceFactory.newClassPathResource(politicianDrlFile4));
		kieFileSystem.write(ResourceFactory.newClassPathResource(partyDrlFile));
		
		KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
		kieBuilder.buildAll();
		KieModule kieModule = kieBuilder.getKieModule();

		KieContainer newKieContainer = kieServices.newKieContainer(kieModule.getReleaseId());

		KieSession ksession = newKieContainer.newKieSession();			
		List<ComplianceCheck> complianceChecks = new ArrayList<>();
		ksession.addEventListener( new ComplianceCheckAgendaEventListener(complianceChecks));				

		insertPoliticians(ksession, dataViewer.getAll(ViewRiksdagenPolitician.class));
		insertParties(ksession, dataViewer.getAll(ViewRiksdagenPartySummary.class));

		ksession.fireAllRules();				
		ksession.dispose();
		return complianceChecks;
	}

	/**
	 * Insert politicians.
	 *
	 * @param ksession
	 *            the ksession
	 * @param list
	 *            the list
	 * @return the list
	 */
	private void insertPoliticians(KieSession ksession,final List<ViewRiksdagenPolitician> list) {
		for (ViewRiksdagenPolitician politicianData : list) {
			if (politicianData != null) {
				ksession.insert( new PoliticianComplianceCheckImpl( politicianData) );				
			}
		}
	}

	/**
	 * Insert parties.
	 *
	 * @param ksession
	 *            the ksession
	 * @param list
	 *            the list
	 * @return the list
	 */
	private void insertParties(KieSession ksession,
			final List<ViewRiksdagenPartySummary> list) {
		for (ViewRiksdagenPartySummary partyData : list) {
			if (partyData != null) {				  				
				ksession.insert( new PartyComplianceCheckImpl( partyData) );				
			}
		}
	}

	private final class ComplianceCheckAgendaEventListener extends DefaultAgendaEventListener {
		private final List<ComplianceCheck> complianceChecks;

		public ComplianceCheckAgendaEventListener(List<ComplianceCheck> complianceChecks) {
			this.complianceChecks = complianceChecks;
		}

		@Override
		public void afterMatchFired(AfterMatchFiredEvent event) {
		    super.afterMatchFired( event );			        
		    AbstractComplianceCheckImpl complianceCheck = (AbstractComplianceCheckImpl) ((DefaultFactHandle)event.getMatch().getFactHandles().iterator().next()).getObject();
		    complianceCheck.setRuleName(event.getMatch().getRule().getName());
		    complianceCheck.setRuleDescription(event.getMatch().getRule().getPackageName());
		    complianceChecks.add(complianceCheck);
		}
	}

}
