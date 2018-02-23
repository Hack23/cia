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

import java.util.List;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.service.data.api.PersonDataDAO;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

public final class RuleServiceITest extends AbstractServiceFunctionalIntegrationTest {

	private static final String drlFile = "rules/Politician.drl";
	
	@Autowired
	private PersonDataDAO PersonData;
	
	@Test
	public void ruleEngineTest() throws Exception {
		KieServices kieServices = KieServices.Factory.get();

		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		kieFileSystem.write(ResourceFactory.newClassPathResource(drlFile));
		KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
		kieBuilder.buildAll();
		KieModule kieModule = kieBuilder.getKieModule();

		KieContainer newKieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
		
		
		List<com.hack23.cia.model.external.riksdagen.person.impl.PersonData> all = PersonData.getAll();

		KieSession ksession = newKieContainer.newKieSession();
		for (PersonData personData : all) {
			
			ksession.insert( personData );
		}
		System.out.println(ksession.fireAllRules());
		ksession.dispose();
		
        




	}

}
