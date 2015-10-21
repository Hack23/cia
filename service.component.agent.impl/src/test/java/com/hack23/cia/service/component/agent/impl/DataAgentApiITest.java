package com.hack23.cia.service.component.agent.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.data.impl.DataAgentOperation;
import com.hack23.cia.model.internal.application.data.impl.DataAgentTarget;
import com.hack23.cia.model.internal.application.data.impl.DataAgentWorkOrder;
import com.hack23.cia.service.component.agent.api.DataAgentApi;

public class DataAgentApiITest extends AbstractServiceComponentAgentFunctionalIntegrationTest {

	@Autowired
	private DataAgentApi dataAgentApi;

	@Test
	public void importRiksdagenDataSuccessTest() throws Exception {
		dataAgentApi.execute(new DataAgentWorkOrder().withOperation(DataAgentOperation.IMPORT).withTarget(DataAgentTarget.MODEL_EXTERNAL_RIKSDAGEN));
		Thread.sleep(2000);
	}


}
