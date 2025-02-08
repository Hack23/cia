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
package com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeRoleMember;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeRoleMember_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.CommitteeGhantChartManager;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandCommitteeConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class CommitteeRoleGhantPageModContentFactoryImpl.
 */
@Component
public final class CommitteeRoleGhantPageModContentFactoryImpl extends AbstractCommitteePageModContentFactoryImpl {

	/** The committee ghant chart manager. */
	@Autowired
	private CommitteeGhantChartManager committeeGhantChartManager;

	/**
	 * Instantiates a new committee role ghant page mod content factory impl.
	 */
	public CommitteeRoleGhantPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);
		final ViewRiksdagenCommittee viewRiksdagenCommittee = getItem(parameters);
		getCommitteeMenuItemFactory().createCommitteeeMenuBar(menuBar, pageId);

		CardInfoRowUtil.createPageHeader(panel, panelContent,
		    CommitteeViewConstants.RG_TITLE_HEADER + viewRiksdagenCommittee.getEmbeddedId().getDetail(),
		    CommitteeViewConstants.RG_TITLE,
		    CommitteeViewConstants.RG_DESCRIPTION);

		final DataContainer<ViewRiksdagenCommitteeRoleMember, String> committeeRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenCommitteeRoleMember.class);

		final List<ViewRiksdagenCommitteeRoleMember> allMembers = committeeRoleMemberDataContainer
				.getAllBy(ViewRiksdagenCommitteeRoleMember_.detail, viewRiksdagenCommittee.getEmbeddedId().getDetail());

		committeeGhantChartManager.createRoleGhant(panelContent, allMembers);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COMMITTEE_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandCommitteeConstants.COMMAND_COMMITTEE_ROLE_GHANT.matches(page, parameters);
	}

}
