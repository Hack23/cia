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

package com.hack23.cia.service.impl;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.service.api.AgentContainer;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.action.common.ServiceRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse;
import com.hack23.cia.service.impl.action.common.BusinessService;

/**
 * The Class ApplicationManagerImpl.
 */
@Service("ApplicationManager")
final class ApplicationManagerImpl implements ApplicationManager, ApplicationContextAware{

	/** The authentication manager. */
	@Autowired
	@Qualifier("org.springframework.security.authenticationManager")
	private AuthenticationManager authenticationManager;

	/** The data agent container. */
	@Autowired
	@Qualifier("DataAgentContainer")
	private AgentContainer dataAgentContainer;

	/** The view riksdagen committee data container. */
	@Autowired
	@Qualifier("ViewRiksdagenCommitteeDataContainer")
	private DataContainer<ViewRiksdagenCommittee,String> viewRiksdagenCommitteeDataContainer;

	/** The view data data container factory. */
	@Autowired
	private ViewDataDataContainerFactory viewDataDataContainerFactory;

	/** The service request business service map. */
	private final Map<Class<? extends ServiceRequest>, BusinessService<? extends ServiceRequest,? extends ServiceResponse>> serviceRequestBusinessServiceMap = new ConcurrentHashMap<>();


	/**
	 * Instantiates a new application manager impl.
	 */
	public ApplicationManagerImpl() {
		super();
	}


	@Secured({"ROLE_ADMIN"})
	@Override
	public AgentContainer getAgentContainer() {
		return dataAgentContainer;
	}

	@Secured({ "ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
	@Override
	public <T extends Serializable, V extends Serializable> DataContainer<T, V> getDataContainer(
			final Class<T> dataObject) {

		DataContainer<T, V> result;

		if (dataObject.equals(ViewRiksdagenCommittee.class)) {
			result= (DataContainer<T, V>) viewRiksdagenCommitteeDataContainer;
		} else {
			result= viewDataDataContainerFactory.createDataContainer(dataObject);
		}

		return result;
	}

	@Secured({"ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
	@Override
	public ServiceResponse service(final ServiceRequest serviceRequest) {
		final BusinessService businessService= serviceRequestBusinessServiceMap.get(serviceRequest.getClass());

		ServiceResponse serviceResponse = null;

		if (businessService != null) {
			serviceResponse = businessService.processService(serviceRequest);
		}

		return serviceResponse;
	}

    @Async("SecureTaskExecutor")
    @Secured({"ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
    @Override
	public Future<ServiceResponse> asyncService(final ServiceRequest serviceRequest) {
		return new AsyncResult<>(service(serviceRequest));
	}


	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) {
		final Map<String, BusinessService> beansOfType = applicationContext.getBeansOfType(BusinessService.class);

		for (final Entry<String, BusinessService> entry : beansOfType.entrySet()) {
			serviceRequestBusinessServiceMap.put(entry.getValue().getSupportedService(), entry.getValue());
		}
	}

}
