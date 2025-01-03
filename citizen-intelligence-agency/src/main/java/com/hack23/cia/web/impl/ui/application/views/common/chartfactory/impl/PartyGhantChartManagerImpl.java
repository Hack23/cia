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
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.PartyGhantChartManager;

/**
 * The Class PartyGhantChartManagerImpl.
 */
@Service
public final class PartyGhantChartManagerImpl extends AbstractGhantChartManagerImpl<ViewRiksdagenPartyRoleMember>
		implements PartyGhantChartManager {

	/**
	 * Instantiates a new party ghant chart manager impl.
	 */
	public PartyGhantChartManagerImpl() {
		super();
	}

	/**
	 * Gets the comparator.
	 *
	 * @return the comparator
	 */
	@Override
	protected Comparator<ViewRiksdagenPartyRoleMember> getComparator() {
		return (o1, o2) -> o1.getFromDate().compareTo(o2.getFromDate());
	}

	/**
	 * Gets the role mapping.
	 *
	 * @return the role mapping
	 */
	@Override
	protected Function<ViewRiksdagenPartyRoleMember, String> getRoleMapping() {
		return new RoleMapping();
	}

	/**
	 * Gets the step mapping.
	 *
	 * @return the step mapping
	 */
	@Override
	protected StepMapping<ViewRiksdagenPartyRoleMember> getStepMapping() {
		return new StepMapping<>() {

			@Override
			public String getBackgroundColor(final ViewRiksdagenPartyRoleMember roleMember) {
				String color;

				if (roleMember.getRoleCode().toLowerCase(Locale.ENGLISH).contains("vice")) {
					color = "A8D999";
				} else if (roleMember.getRoleCode().toLowerCase(Locale.ENGLISH).contains("partiledare")
						|| roleMember.getRoleCode().toLowerCase(Locale.ENGLISH).contains("språkrör")
						|| roleMember.getRoleCode().toLowerCase(Locale.ENGLISH).contains("partisekreterare")) {
					color = "3271c8";
				} else {
					color = "0eab76";
				}

				return color;
			}

			@Override
			public Object getFirstName(final ViewRiksdagenPartyRoleMember roleMember) {
				return roleMember.getFirstName();
			}

			@Override
			public Date getFromDate(final ViewRiksdagenPartyRoleMember roleMember) {
				return roleMember.getFromDate();
			}

			@Override
			public Object getLastName(final ViewRiksdagenPartyRoleMember roleMember) {
				return roleMember.getLastName();
			}

			@Override
			public String getOrg(final ViewRiksdagenPartyRoleMember roleMember) {
				return roleMember.getDetail();
			}

			@Override
			public String getParty(final ViewRiksdagenPartyRoleMember roleMember) {
				return roleMember.getParty();
			}

			@Override
			public String getRoleCode(final ViewRiksdagenPartyRoleMember roleMember) {
				return roleMember.getRoleCode();
			}

			@Override
			public Date getToDate(final ViewRiksdagenPartyRoleMember roleMember) {
				return Optional.ofNullable(roleMember.getToDate()).orElseGet(Date::new);
			}

		};
	}

	/**
	 * The Class RoleMapping.
	 */
	private static final class RoleMapping implements Function<ViewRiksdagenPartyRoleMember, String> {

		/**
		 * Apply.
		 *
		 * @param roleMember the role member
		 * @return the string
		 */
		@Override
		public String apply(final ViewRiksdagenPartyRoleMember roleMember) {
			return roleMember.getRoleCode();
		}
	}

}
