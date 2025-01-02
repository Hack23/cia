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

	@Override
	protected Comparator<ViewRiksdagenPartyRoleMember> getComparator() {
		return (o1, o2) -> o1.getFromDate().compareTo(o2.getFromDate());
	}

	@Override
	protected Function<ViewRiksdagenPartyRoleMember, String> getRoleMapping() {
		return new RoleMapping();
	}

	@Override
	protected StepMapping<ViewRiksdagenPartyRoleMember> getStepMapping() {
		return new StepMapping<>() {

			@Override
			public String getBackgroundColor(final ViewRiksdagenPartyRoleMember t) {
				String color;

				if (t.getRoleCode().toLowerCase(Locale.ENGLISH).contains("vice")) {
					color = "A8D999";
				} else if (t.getRoleCode().toLowerCase(Locale.ENGLISH).contains("partiledare")
						|| t.getRoleCode().toLowerCase(Locale.ENGLISH).contains("språkrör")
						|| t.getRoleCode().toLowerCase(Locale.ENGLISH).contains("partisekreterare")) {
					color = "3271c8";
				} else {
					color = "0eab76";
				}

				return color;
			}

			@Override
			public Object getFirstName(final ViewRiksdagenPartyRoleMember t) {
				return t.getFirstName();
			}

			@Override
			public Date getFromDate(final ViewRiksdagenPartyRoleMember t) {
				return t.getFromDate();
			}

			@Override
			public Object getLastName(final ViewRiksdagenPartyRoleMember t) {
				return t.getLastName();
			}

			@Override
			public String getOrg(final ViewRiksdagenPartyRoleMember t) {
				return t.getDetail();
			}

			@Override
			public String getParty(final ViewRiksdagenPartyRoleMember t) {
				return t.getParty();
			}

			@Override
			public String getRoleCode(final ViewRiksdagenPartyRoleMember t) {
				return t.getRoleCode();
			}

			@Override
			public Date getToDate(final ViewRiksdagenPartyRoleMember t) {
				return Optional.ofNullable(t.getToDate()).orElseGet(Date::new);
			}

		};
	}

	/**
	 * The Class RoleMapping.
	 */
	private static final class RoleMapping implements Function<ViewRiksdagenPartyRoleMember, String> {

		@Override
		public String apply(final ViewRiksdagenPartyRoleMember t) {
			return t.getRoleCode();
		}
	}

}
