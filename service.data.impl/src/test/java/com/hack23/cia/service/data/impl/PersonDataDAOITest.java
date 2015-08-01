package com.hack23.cia.service.data.impl;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.data.api.PersonDataDAO;

@PerfTest(threads = 10, duration = 3000, warmUp = 1500)
@Required(max = 1000,average = 20,percentile95=40,throughput=1000)
public class PersonDataDAOITest extends AbstractServiceDataFunctionalIntegrationTest {

	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	@Autowired
	private PersonDataDAO personDataDAO;

	@Test
	public void testGetSize() throws Exception {

		assertTrue(personDataDAO.getSize() >= 0);
	}

}
