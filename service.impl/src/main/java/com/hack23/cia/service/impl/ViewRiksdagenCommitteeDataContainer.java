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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.service.data.api.DataViewer;
import com.hack23.cia.service.data.api.ViewRiksdagenCommitteeDAO;

/**
 * The Class ViewRiksdagenCommitteeDataContainer.
 */
@Component("ViewRiksdagenCommitteeDataContainer")
@Transactional(propagation=Propagation.REQUIRED, readOnly = true)
@Secured({"ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
public final class ViewRiksdagenCommitteeDataContainer extends GenericDataContainer<ViewRiksdagenCommittee,String>{

	/** The view riksdagen committee dao. */
	@Autowired
	private ViewRiksdagenCommitteeDAO viewRiksdagenCommitteeDAO;

	/**
	 * Instantiates a new view riksdagen committee data container.
	 *
	 * @param dataViewer the data viewer
	 */
	@Autowired
	public ViewRiksdagenCommitteeDataContainer(@Qualifier("DataViewer") final DataViewer dataViewer) {
		super(ViewRiksdagenCommittee.class,dataViewer);
	}

	@Override
	public ViewRiksdagenCommittee load(final String id) {
		for (final ViewRiksdagenCommittee riksdagenCommittee : viewRiksdagenCommitteeDAO.getAll()) {
			if (id.equals(riksdagenCommittee.getEmbeddedId().getOrgCode())) {
				return riksdagenCommittee;
			}

		}

		return null;
	}

}
