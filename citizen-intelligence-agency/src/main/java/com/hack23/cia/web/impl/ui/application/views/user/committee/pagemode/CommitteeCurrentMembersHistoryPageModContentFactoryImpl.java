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
package com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeRoleMember;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeRoleMember_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommitteePageMode;
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

	private static final String[] COLUMN_ORDER = { "roleCode", "roleId", "personId", "firstName",
			"lastName", "party", "active", "totalDaysServed", "detail", "fromDate", "toDate" };

	/** The Constant COMMITTEE. */
	private static final String COMMITTEE = "Committee:";

	/** The Constant CURRENT_MEMBERS. */
	private static final String CURRENT_MEMBERS = "Current Members";

	private static final String[] HIDE_COLUMNS = { "roleId", "personId", "detail", "active" };

	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.POLITICIAN_VIEW_NAME, "personId");

	/**
	 * Instantiates a new committee current members history page mod content
	 * factory impl.
	 */
	public CommitteeCurrentMembersHistoryPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final ViewRiksdagenCommittee viewRiksdagenCommittee = getItem(parameters);
		getCommitteeMenuItemFactory().createCommitteeeMenuBar(menuBar, pageId);

		LabelFactory.createHeader2Label(panelContent, CURRENT_MEMBERS);

		final DataContainer<ViewRiksdagenCommitteeRoleMember, String> committeeRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenCommitteeRoleMember.class);

		getGridFactory().createBasicBeanItemGrid(panelContent, ViewRiksdagenCommitteeRoleMember.class,
				committeeRoleMemberDataContainer.findListByProperty(
						new Object[] { viewRiksdagenCommittee.getEmbeddedId().getDetail(), Boolean.TRUE },
						ViewRiksdagenCommitteeRoleMember_.detail, ViewRiksdagenCommitteeRoleMember_.active),
				CURRENT_MEMBERS, COLUMN_ORDER, HIDE_COLUMNS, LISTENER, null, null);

		panel.setCaption(NAME + "::" + COMMITTEE + viewRiksdagenCommittee.getEmbeddedId().getDetail());
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COMMITTEE_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters,CommitteePageMode.CURRENT_MEMBERS.toString());
	}

}
