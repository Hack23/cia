package com.hack23.cia.service.component.agent.impl;

import java.util.Collection;

import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.activemq.web.BrokerFacadeSupport;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.data.impl.DataAgentOperation;
import com.hack23.cia.model.internal.application.data.impl.DataAgentTarget;
import com.hack23.cia.model.internal.application.data.impl.DataAgentWorkOrder;
import com.hack23.cia.service.component.agent.api.DataAgentApi;

public class DataAgentApiITest extends AbstractServiceComponentAgentFunctionalIntegrationTest {

	@Autowired
	private DataAgentApi dataAgentApi;

	@Autowired
	private BrokerFacadeSupport brokerQuery;

	@Test
	public void importRiksdagenDataSuccessTest() {
		dataAgentApi.execute(new DataAgentWorkOrder().withOperation(DataAgentOperation.IMPORT).withTarget(DataAgentTarget.MODEL_EXTERNAL_RIKSDAGEN));

		try {
			while (!isAllCompleted(brokerQuery.getQueues()));
			while(!	(brokerQuery.getBrokerAdmin().getTotalDequeueCount() == brokerQuery.getBrokerAdmin().getTotalEnqueueCount()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isAllCompleted(Collection<QueueViewMBean> queues) {
		boolean allCompleted=true;
		for(QueueViewMBean queue: queues) {
			if (queue.getName().toLowerCase().contains("riksdagen")) {
				allCompleted = allCompleted && (queue.getEnqueueCount() == queue.getDequeueCount());
			}
		}
		return allCompleted;
	}


}
