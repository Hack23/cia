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
import com.hack23.cia.web.impl.ui.application.views.common.constants.CommitteeGridConstants;
import com.hack23.cia.web.impl.ui.application.views.common.constants.CommitteeViewConstants;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeRoleMember;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeRoleMember_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandCommitteeConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class CommitteeCurrentMembersHistoryPageModContentFactoryImpl.
 */
@Component
public final class CommitteeCurrentMembersHistoryPageModContentFactoryImpl
		extends AbstractCommitteePageModContentFactoryImpl {

	/** The Constant LISTENER. */
	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.POLITICIAN_VIEW_NAME, "personId");

	/**
	 * Instantiates a new committee current members history page mod content
	 * factory impl.
	 */
	public CommitteeCurrentMembersHistoryPageModContentFactoryImpl() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final ViewRiksdagenCommittee viewRiksdagenCommittee = getItem(parameters);
		getCommitteeMenuItemFactory().createCommitteeeMenuBar(menuBar, pageId);

		CardInfoRowUtil.createPageHeader(panel, panelContent,
		    CommitteeViewConstants.CM_TITLE_HEADER + viewRiksdagenCommittee.getEmbeddedId().getDetail(),
		    CommitteeViewConstants.CM_TITLE,
		    CommitteeViewConstants.CM_DESCRIPTION);

		final DataContainer<ViewRiksdagenCommitteeRoleMember, String> committeeRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenCommitteeRoleMember.class);

		getGridFactory().createBasicBeanItemGrid(panelContent, ViewRiksdagenCommitteeRoleMember.class,
				committeeRoleMemberDataContainer.findListByProperty(
						new Object[] { viewRiksdagenCommittee.getEmbeddedId().getDetail(), Boolean.TRUE },
						ViewRiksdagenCommitteeRoleMember_.detail, ViewRiksdagenCommitteeRoleMember_.active),
				CommitteeGridConstants.CURRENT_MEMBERS_GRID_NAME,
				CommitteeGridConstants.MEMBER_HISTORY_COLUMN_ORDER,
				CommitteeGridConstants.CURRENT_MEMBERS_HIDDEN_COLUMNS,
				LISTENER, null, null);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COMMITTEE_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandCommitteeConstants.COMMAND_COMMITTEE_CURRENT_MEMBERS.matches(page, parameters);
	}

}
