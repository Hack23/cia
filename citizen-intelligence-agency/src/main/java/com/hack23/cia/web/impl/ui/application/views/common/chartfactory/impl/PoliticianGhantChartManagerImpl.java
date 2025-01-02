/*
 * Copyright 2010-2025 James Pether Sörling
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
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.riksdagen.person.impl.AssignmentData;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.PoliticianGhantChartManager;

/**
 * The Class PoliticianGhantChartManagerImpl.
 */
@Service
public final class PoliticianGhantChartManagerImpl extends AbstractGhantChartManagerImpl<AssignmentData>
		implements PoliticianGhantChartManager {

	/** The Constant DEPARTEMENT. */
	private static final String DEPARTEMENT = "Departement";

	/** The Constant KAMMARUPPDRAG. */
	private static final String KAMMARUPPDRAG = "kammaruppdrag";

	/** The Constant LEDIG. */
	private static final String LEDIG = "LEDIG";

	/**
	 * Instantiates a new politician ghant chart manager impl.
	 */
	public PoliticianGhantChartManagerImpl() {
		super();
	}

	@Override
	protected Comparator<AssignmentData> getComparator() {
		return (o1, o2) -> o1.getFromDate().compareTo(o2.getFromDate());
	}

	@Override
	protected Function<AssignmentData, String> getRoleMapping() {
		return new RoleMapping();
	}

	@Override
	protected StepMapping<AssignmentData> getStepMapping() {
		return new StepMapping<>() {

			@Override
			public String getBackgroundColor(final AssignmentData t) {
				String color;

				final String parliamentType = KAMMARUPPDRAG;

				if (LEDIG.equalsIgnoreCase(t.getStatus())) {
					color = "e3e3e3";
				} else if (parliamentType.equalsIgnoreCase(t.getAssignmentType())) {
					color = "0eab76";
				} else if (DEPARTEMENT.equalsIgnoreCase(t.getAssignmentType())) {

					color = "ded858";
				} else {
					color = "3271c8";
				}

				return color;
			}

			@Override
			public Object getFirstName(final AssignmentData t) {
				return "";
			}

			@Override
			public Date getFromDate(final AssignmentData t) {
				return t.getFromDate();
			}

			@Override
			public Object getLastName(final AssignmentData t) {
				return "";
			}

			@Override
			public String getOrg(final AssignmentData t) {
				return t.getDetail();
			}

			@Override
			public String getParty(final AssignmentData t) {
				return t.getOrgCode();
			}

			@Override
			public String getRoleCode(final AssignmentData t) {
				return t.getRoleCode();
			}

			@Override
			public Date getToDate(final AssignmentData t) {
				return Optional.ofNullable(t.getToDate()).orElseGet(Date::new);
			}

		};
	}

	/**
	 * The Class RoleMapping.
	 */
	private static final class RoleMapping implements Function<AssignmentData, String> {

		/** The Constant RIKSDAGSLEDAMOT. */
		private static final String RIKSDAGSLEDAMOT = "Riksdagsledamot";

		@Override
		public String apply(final AssignmentData t) {
			if (KAMMARUPPDRAG.equalsIgnoreCase(t.getAssignmentType())) {
				return RIKSDAGSLEDAMOT;
			} else {
				return t.getAssignmentType() + '.' + t.getDetail() + ' ' + t.getRoleCode();
			}

		}
	}

}
