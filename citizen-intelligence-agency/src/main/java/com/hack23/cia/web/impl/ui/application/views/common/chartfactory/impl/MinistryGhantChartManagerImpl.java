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

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.MinistryGhantChartManager;

/**
 * The Class MinistryGhantChartManagerImpl.
 */
@Service
public final class MinistryGhantChartManagerImpl extends AbstractGhantChartManagerImpl<ViewRiksdagenGovermentRoleMember>
		implements MinistryGhantChartManager {

	/**
	 * Instantiates a new ministry ghant chart manager impl.
	 */
	public MinistryGhantChartManagerImpl() {
		super();
	}

	/**
	 * Gets the comparator.
	 *
	 * @return the comparator
	 */
	@Override
	protected Comparator<ViewRiksdagenGovermentRoleMember> getComparator() {
		return (o1, o2) -> o1.getFromDate().compareTo(o2.getFromDate());
	}

	/**
	 * Gets the role mapping.
	 *
	 * @return the role mapping
	 */
	@Override
	protected Function<ViewRiksdagenGovermentRoleMember, String> getRoleMapping() {
		return new RoleMapping();
	}

	/**
	 * Gets the step mapping.
	 *
	 * @return the step mapping
	 */
	@Override
	protected StepMapping<ViewRiksdagenGovermentRoleMember> getStepMapping() {
		return new StepMapping<>() {

			@Override
			public String getBackgroundColor(final ViewRiksdagenGovermentRoleMember roleMember) {
				return ChartUtils.getRoleColor(roleMember.getRoleCode(), "statsråd", "statsminister");
			}

			@Override
			public Object getFirstName(final ViewRiksdagenGovermentRoleMember roleMember) {
				return roleMember.getFirstName();
			}

					@Override
			public Date getFromDate(final ViewRiksdagenGovermentRoleMember roleMember) {
				return roleMember.getFromDate();
			}

			@Override
			public Object getLastName(final ViewRiksdagenGovermentRoleMember roleMember) {
				return roleMember.getLastName();
			}

			@Override
			public String getOrg(final ViewRiksdagenGovermentRoleMember roleMember) {
				return roleMember.getDetail();
			}

			@Override
			public String getParty(final ViewRiksdagenGovermentRoleMember roleMember) {
				return roleMember.getParty();
			}

			@Override
			public String getRoleCode(final ViewRiksdagenGovermentRoleMember roleMember) {
				return roleMember.getRoleCode();
			}

			@Override
			public Date getToDate(final ViewRiksdagenGovermentRoleMember roleMember) {
				return Optional.ofNullable(roleMember.getToDate()).orElseGet(Date::new);
			}

		};
	}

	/**
	 * The Class RoleMapping.
	 */
	private static final class RoleMapping implements Function<ViewRiksdagenGovermentRoleMember, String> {

		/**
		 * Apply.
		 *
		 * @param roleMember the role member
		 * @return the string
		 */
		@Override
		public String apply(final ViewRiksdagenGovermentRoleMember roleMember) {
			return roleMember.getRoleCode()+ ".["+ roleMember.getPersonId() +"]." + roleMember.getDetail();
		}
	}

}
