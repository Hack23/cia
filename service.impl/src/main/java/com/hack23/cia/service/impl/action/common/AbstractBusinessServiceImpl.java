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
package com.hack23.cia.service.impl.action.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.api.action.common.ServiceRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse;

/**
 * The Class AbstractBusinessServiceImpl.
 *
 * @param <T>
 *            the generic type
 * @param <V>
 *            the value type
 */
public abstract class AbstractBusinessServiceImpl<T extends ServiceRequest, V extends ServiceResponse>
		implements BusinessService<T, V> {

	/** The clazz. */
	private final Class<T> clazz;

	/**
	 * Instantiates a new abstract business service impl.
	 *
	 * @param clazz
	 *            the clazz
	 */
	public AbstractBusinessServiceImpl(final Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see
	 * com.hack23.cia.service.impl.common.BusinessService#getSupportedService()
	 */
	@Override
	public final Class<? extends ServiceRequest> getSupportedService() {
		return clazz;
	}

	/**
	 * Gets the user account from security context.
	 *
	 * @return the user account from security context
	 */
	protected final UserAccount getUserAccountFromSecurityContext() {
		final SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			final Authentication authentication = context.getAuthentication();
			if (authentication != null) {
				final Object principal = authentication.getPrincipal();

				if (principal instanceof UserAccount) {
					return (UserAccount) principal;
				}
			}
		}

		return null;
	}


}
