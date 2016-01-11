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

package com.hack23.cia.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Future;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.service.api.AgentContainer;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.DataSummary;
import com.hack23.cia.service.api.action.common.ServiceRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse;
import com.hack23.cia.service.impl.action.common.BusinessService;

/**
 * The Class ApplicationManagerImpl.
 */
@Service("ApplicationManager")
public final class ApplicationManagerImpl implements ApplicationManager, ApplicationContextAware{

	/** The authentication manager. */
	@Autowired
	@Qualifier("org.springframework.security.authenticationManager")
	private AuthenticationManager authenticationManager;

	/** The data agent container. */
	@Autowired
	@Qualifier("DataAgentContainer")
	private AgentContainer dataAgentContainer;

	/** The data summary data container. */
	@Autowired
	@Qualifier("DataSummaryDataContainer")
	private DataContainer<DataSummary, String> dataSummaryDataContainer;

	/** The person data container. */
	@Autowired
	@Qualifier("PersonDataContainer")
	private DataContainer<PersonData, String> personDataContainer;

	/** The view riksdagen committee data container. */
	@Autowired
	@Qualifier("ViewRiksdagenCommitteeDataContainer")
	private DataContainer<ViewRiksdagenCommittee,String> viewRiksdagenCommitteeDataContainer;


	/** The view data data container factory. */
	@Autowired
	private ViewDataDataContainerFactory viewDataDataContainerFactory;

	/** The service request business service map. */
	private final Map<Class<? extends ServiceRequest>, BusinessService> serviceRequestBusinessServiceMap = new HashMap<>();

	/** The application context. */
	private ApplicationContext applicationContext;


	/**
	 * Instantiates a new application manager impl.
	 */
	public ApplicationManagerImpl() {
		super();
	}


	/**
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.api.ApplicationManager#authenticate(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	@Secured({"ROLE_ANONYMOUS"})
	public Object authenticate(final String username, final String password) {
		final Authentication auth = new UsernamePasswordAuthenticationToken(
				username, password);

		final Authentication returned = authenticationManager
				.authenticate(auth);
		SecurityContextHolder.getContext().setAuthentication(returned);

		return returned;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.api.ApplicationManager#getAgentContainer(java.
	 * io.Serializable)
	 */
	@Override
	@Secured({"ROLE_ADMIN"})
	public AgentContainer getAgentContainer() {
		return dataAgentContainer;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.api.ApplicationManager#getDataContainer(java.io
	 * .Serializable)
	 */
	@Override
	@Secured({ "ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
	public <T extends Serializable, V extends Serializable> DataContainer<T, V> getDataContainer(
			final Class<T> dataObject) {

		DataContainer<T, V> result;

		if (dataObject.equals(DataSummary.class)) {
			result= (DataContainer<T, V>) dataSummaryDataContainer;
		} else if (dataObject.equals(PersonData.class)) {
			result= (DataContainer<T, V>) personDataContainer;
		}else if (dataObject.equals(ViewRiksdagenCommittee.class)) {
			result= (DataContainer<T, V>) viewRiksdagenCommitteeDataContainer;
		} else {
			result= viewDataDataContainerFactory.createDataContainer(dataObject);
		}

		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.api.ApplicationManager#logout()
	 */
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.ApplicationManager#service(com.hack23.cia.service
	 * .api.ServiceRequest)
	 */
	@Override
	@Secured({"ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
	public ServiceResponse service(final ServiceRequest serviceRequest) {

		initBusinessServiceMap(serviceRequest);

		final BusinessService businessService= serviceRequestBusinessServiceMap.get(serviceRequest.getClass());

		ServiceResponse serviceResponse = null;

		if (businessService != null) {
			serviceResponse = businessService.processService(serviceRequest);
		}

		return serviceResponse;
	}

	@Override
	@Secured({"ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
    @Async
	public Future<ServiceResponse> asyncService(final ServiceRequest serviceRequest) {

		initBusinessServiceMap(serviceRequest);

		final BusinessService businessService= serviceRequestBusinessServiceMap.get(serviceRequest.getClass());

		ServiceResponse serviceResponse = null;

		if (businessService != null) {
			serviceResponse = businessService.processService(serviceRequest);
		}

		return new AsyncResult<>(serviceResponse);
	}


	/**
	 * Inits the business service map.
	 *
	 * @param serviceRequest
	 *            the service request
	 */
	private void initBusinessServiceMap(final ServiceRequest serviceRequest) {
		if (serviceRequestBusinessServiceMap.get(serviceRequest.getClass()) == null) {
			final Map<String, BusinessService> beansOfType = applicationContext.getBeansOfType(BusinessService.class);

			for (final Entry<String, BusinessService> entry : beansOfType.entrySet()) {
				if (serviceRequest.getClass().equals(entry.getValue().getSupportedService())) {
					serviceRequestBusinessServiceMap.put(serviceRequest.getClass(), entry.getValue());
				}
			}
		}
	}

	/** (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(final ApplicationContext applicationContext)
			throws BeansException {
				this.applicationContext = applicationContext;
	}

}
