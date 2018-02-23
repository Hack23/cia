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

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

public final class RuleServiceITest extends AbstractServiceFunctionalIntegrationTest {

	private static final String politicianDrlFile = "rules/Politician.drl";
	
	private static final String partyDrlFile = "rules/Party.drl";
		
	@Autowired
	private ApplicationManager applicationManager;
	
	@Before
	public void authAdminUser() throws IOException {
		setAuthenticatedAdminuser();
	}

	@Test
	public void ruleEngineTest() throws Exception {
		KieServices kieServices = KieServices.Factory.get();

		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		kieFileSystem.write(ResourceFactory.newClassPathResource(politicianDrlFile));
		kieFileSystem.write(ResourceFactory.newClassPathResource(partyDrlFile));
		
		KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
		kieBuilder.buildAll();
		KieModule kieModule = kieBuilder.getKieModule();

		KieContainer newKieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
		
		final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = applicationManager.getDataContainer(ViewRiksdagenPolitician.class);

		final DataContainer<ViewRiksdagenPartySummary, String> partyDataContainer = applicationManager.getDataContainer(ViewRiksdagenPartySummary.class);

		for (ViewRiksdagenPolitician politicianData : politicianDataContainer.getAll()) {
			if (politicianData != null) {
				KieSession ksession = newKieContainer.newKieSession();			
				ksession.insert( politicianData );
				System.out.println("Politician Rules fired:" +ksession.fireAllRules() + ":" + politicianData.getFirstName() + " " +politicianData.getLastName());
				ksession.dispose();
			}
		}

		for (ViewRiksdagenPartySummary partyData : partyDataContainer.getAll()) {
			if (partyData != null) {
				KieSession ksession = newKieContainer.newKieSession();			
				ksession.insert( partyData );
				System.out.println("Pary Rules fired:" +ksession.fireAllRules() + ":" + partyData.getParty());
				ksession.dispose();
			}
		}

	}

}
