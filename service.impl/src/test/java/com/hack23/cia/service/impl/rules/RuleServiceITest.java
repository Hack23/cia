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

import java.io.IOException;

import org.drools.core.common.DefaultFactHandle;
import org.junit.Before;
import org.junit.Test;
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

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.action.kpi.ComplianceCheck;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class RuleServiceITest.
 */
public final class RuleServiceITest extends AbstractServiceFunctionalIntegrationTest {

	/**
	 * The listener interface for receiving complianceCheckAgendaEvent events. The
	 * class that is interested in processing a complianceCheckAgendaEvent event
	 * implements this interface, and the object created with that class is
	 * registered with a component using the component's
	 * <code>addComplianceCheckAgendaEventListener<code> method. When the
	 * complianceCheckAgendaEvent event occurs, that object's appropriate method is
	 * invoked.
	 *
	 * @see ComplianceCheckAgendaEventEvent
	 */
	private final class ComplianceCheckAgendaEventListener extends DefaultAgendaEventListener {
		public void afterMatchFired(AfterMatchFiredEvent event) {
		    super.afterMatchFired( event );			        
		    AbstractComplianceCheckImpl complianceCheck = (AbstractComplianceCheckImpl) ((DefaultFactHandle)event.getMatch().getFactHandles().iterator().next()).getObject();
		    complianceCheck.setRuleName(event.getMatch().getRule().getName());
		    complianceCheck.setRuleDescription(event.getMatch().getRule().getPackageName());
		    
		    System.out.println(  complianceCheck);
		}
	}

	/** The Constant politicianDrlFile. */
	private static final String politicianDrlFile = "rules/PoliticianLeftPartyStillHoldingPositions.drl";
	
	private static final String politicianDrlFile2 = "rules/PoliticianTimeToRetire.drl";
	
	private static final String politicianDrlFile3 = "rules/PoliticianBusySchedule.drl";

	private static final String politicianDrlFile4 = "rules/PoliticianMinisterWithoutParliamentExperience.drl";
	
	
	
	
	/** The Constant partyDrlFile. */
	private static final String partyDrlFile = "rules/PartyNoGovernmentExperience.drl";
		
	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;
	
	/**
	 * Auth admin user.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Before
	public void authAdminUser() throws IOException {
		setAuthenticatedAdminuser();
	}

	/**
	 * Rule engine test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void ruleEngineTest() throws Exception {
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
		ksession.addEventListener( new ComplianceCheckAgendaEventListener());				
		//ksession.addEventListener( new DebugAgendaEventListener() );

		insertPoliticians(ksession, applicationManager.getDataContainer(ViewRiksdagenPolitician.class));
		insertParties(ksession, applicationManager.getDataContainer(ViewRiksdagenPartySummary.class));

		ksession.fireAllRules();				
		ksession.dispose();

	}

	/**
	 * Insert politicians.
	 *
	 * @param ksession
	 *            the ksession
	 * @param politicianDataContainer
	 *            the politician data container
	 */
	private void insertPoliticians(KieSession ksession,
			final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer) {
		for (ViewRiksdagenPolitician politicianData : politicianDataContainer.getAll()) {
			if (politicianData != null) {
				ComplianceCheck complianceCheck  = new PoliticianComplianceCheckImpl( politicianData);
				ksession.insert( complianceCheck );				
			}
		}
	}

	/**
	 * Insert parties.
	 *
	 * @param ksession
	 *            the ksession
	 * @param partyDataContainer
	 *            the party data container
	 */
	private void insertParties(KieSession ksession,
			final DataContainer<ViewRiksdagenPartySummary, String> partyDataContainer) {		
		for (ViewRiksdagenPartySummary partyData : partyDataContainer.getAll()) {
			if (partyData != null) {
				  				
				ComplianceCheck complianceCheck = new PartyComplianceCheckImpl( partyData);
				ksession.insert( complianceCheck );				
			}
		}
	}

}
