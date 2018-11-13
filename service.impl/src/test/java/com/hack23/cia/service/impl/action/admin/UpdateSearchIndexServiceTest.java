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
package com.hack23.cia.service.impl.action.admin;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.util.UUID;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.hack23.cia.service.api.action.admin.UpdateSearchIndexRequest;
import com.hack23.cia.service.api.action.admin.UpdateSearchIndexResponse;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationEventResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.SearchIndexer;
import com.hack23.cia.service.impl.action.common.BusinessService;
import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * The Class UpdateSearchIndexServiceTest.
 */
public final class UpdateSearchIndexServiceTest extends AbstractUnitTest {

	/**
	 * Service request interupt exception failure test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void serviceRequestInteruptExceptionFailureTest() throws Exception {
		final UpdateSearchIndexRequest serviceRequest = new UpdateSearchIndexRequest();
		serviceRequest.setSessionId(UUID.randomUUID().toString());
		
		final SearchIndexer searchIndexer = mock(SearchIndexer.class);
		final UpdateSearchIndexService updateSearchIndexService = new UpdateSearchIndexService(searchIndexer);
		
		doThrow(InterruptedException.class).when(searchIndexer).updateSearchIndex();
		
		BusinessService<CreateApplicationEventRequest, CreateApplicationEventResponse> createApplicationEventService = (BusinessService<CreateApplicationEventRequest, CreateApplicationEventResponse>) mock(BusinessService.class);
		ReflectionTestUtils.setField(updateSearchIndexService, "createApplicationEventService", createApplicationEventService);
		
		UpdateSearchIndexResponse response = updateSearchIndexService.processService(serviceRequest);		
				
		assertNotNull(response);
		assertEquals(ServiceResult.FAILURE,response.getResult());
		Mockito.verify(searchIndexer).updateSearchIndex();
	}

}
