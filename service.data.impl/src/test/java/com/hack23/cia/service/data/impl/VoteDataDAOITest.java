package com.hack23.cia.service.data.impl;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.data.api.VoteDataDAO;

@PerfTest(threads = 1, duration = 3000, warmUp = 1500)
@Required(max = 1000,average = 500,percentile95=500,throughput=2)
public class VoteDataDAOITest extends AbstractServiceDataFunctionalIntegrationTest {

	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	@Autowired
	private VoteDataDAO voteDataDAO;

	@Test
	public void testGetSize() throws Exception {

		assertTrue(voteDataDAO.getSize() >= 0);
	}

}
