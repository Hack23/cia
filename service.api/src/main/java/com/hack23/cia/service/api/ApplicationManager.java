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
 *	$Id: ApplicationManager.java 6075 2015-05-20 22:43:45Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/service.api/src/main/java/com/hack23/cia/service/api/ApplicationManager.java $
*/

package com.hack23.cia.service.api;

import java.io.Serializable;

import com.hack23.cia.service.api.action.common.ServiceRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse;




/**
 * The Interface ApplicationManager.
 */
public interface ApplicationManager {

	/**
	 * Authenticate.
	 *
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @return the object
	 */
	Object authenticate(String username, String password);

	/**
	 * Gets the agent container.
	 *
	 * @return the agent container
	 */
	AgentContainer getAgentContainer();

	/**
	 * Gets the data container.
	 *
	 * @param <T>
	 *            the generic type
	 * @param <V>
	 *            the generic type
	 * @param dataObject
	 *            the data object
	 * @return the data container
	 */
	<T extends Serializable,V extends Serializable> DataContainer<T,V> getDataContainer(Class<T> dataObject);


	/**
	 * Logout.
	 */
	void logout();

	/**
	 * Service.
	 *
	 * @param serviceRequest
	 *            the service request
	 * @return the service response
	 */
	ServiceResponse service(ServiceRequest serviceRequest);

}