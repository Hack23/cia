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
 * distributed on an "AS IS" BASIS,
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
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeRoleMember;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.CommitteeGhantChartManager;

/**
 * The Class CommitteeGhantChartManagerImpl.
 */
@Service
public final class CommitteeGhantChartManagerImpl
		extends AbstractGhantChartManagerImpl<ViewRiksdagenCommitteeRoleMember> implements CommitteeGhantChartManager {

	/**
	 * Instantiates a new committee ghant chart manager impl.
	 */
	public CommitteeGhantChartManagerImpl() {
		super();
	}

	/**
	 * Gets the comparator.
	 *
	 * @return the comparator
	 */
	@Override
	protected Comparator<ViewRiksdagenCommitteeRoleMember> getComparator() {
		return Comparator.comparing(ViewRiksdagenCommitteeRoleMember::getFromDate);
	}

	/**
	 * Gets the role mapping.
	 *
	 * @return the role mapping
	 */
	@Override
	protected Function<ViewRiksdagenCommitteeRoleMember, String> getRoleMapping() {
		return RoleMapping::new;
	}

	/**
	 * Gets the step mapping.
	 *
	 * @return the step mapping
	 */
	@Override
	protected StepMapping<ViewRiksdagenCommitteeRoleMember> getStepMapping() {
		return new StepMapping<>() {

			@Override
			public String getBackgroundColor(final ViewRiksdagenCommitteeRoleMember roleMember) {
				return ChartUtils.getRoleColor(roleMember.getRoleCode(), "suppleant", "ordförande");
			}

			@Override
			public Object getFirstName(final ViewRiksdagenCommitteeRoleMember roleMember) {
				return roleMember.getFirstName();
			}

			@Override
			public Date getFromDate(final ViewRiksdagenCommitteeRoleMember roleMember) {
				return roleMember.getFromDate();
			}

			@Override
			public Object getLastName(final ViewRiksdagenCommitteeRoleMember roleMember) {
				return roleMember.getLastName();
			}

			@Override
			public String getOrg(final ViewRiksdagenCommitteeRoleMember roleMember) {
				return roleMember.getDetail();
			}

			@Override
			public String getParty(final ViewRiksdagenCommitteeRoleMember roleMember) {
				return roleMember.getParty();
			}

			@Override
			public String getRoleCode(final ViewRiksdagenCommitteeRoleMember roleMember) {
				return roleMember.getRoleCode();
			}

			@Override
			public Date getToDate(final ViewRiksdagenCommitteeRoleMember roleMember) {
				return Optional.ofNullable(roleMember.getToDate()).orElseGet(Date::new);
			}

		};
	}

	/**
	 * The Class RoleMapping.
	 */
	private static final class RoleMapping implements Function<ViewRiksdagenCommitteeRoleMember, String> {

		/**
		 * Apply.
		 *
		 * @param roleMember the role member
		 * @return the string
		 */
		@Override
		public String apply(final ViewRiksdagenCommitteeRoleMember roleMember) {
			return roleMember.getRoleCode() +".["+ roleMember.getPersonId() +"]";
		}
	}

}
