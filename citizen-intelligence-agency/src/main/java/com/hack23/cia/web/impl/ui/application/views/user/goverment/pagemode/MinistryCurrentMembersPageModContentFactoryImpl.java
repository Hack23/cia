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
package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember_;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.MinistryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class MinistryCurrentMembersPageModContentFactoryImpl.
 */
@Component
public final class MinistryCurrentMembersPageModContentFactoryImpl extends AbstractMinistryPageModContentFactoryImpl {

	private static final String[] COLUMN_ORDER = { "roleCode", "roleId", "personId", "firstName",
			"lastName", "party", "active", "totalDaysServed", "detail", "fromDate", "toDate" };

	/** The Constant CURRENT_MEMBERS. */
	private static final String CURRENT_MEMBERS = "Current Members";

	private static final String[] HIDE_COLUMNS = { "roleId", "personId", "detail", "active" };

	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.POLITICIAN_VIEW_NAME, "personId");

	/**
	 * Instantiates a new ministry current members page mod content factory
	 * impl.
	 */
	public MinistryCurrentMembersPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final ViewRiksdagenMinistry viewRiksdagenMinistry = getItem(parameters);

		getMinistryMenuItemFactory().createMinistryMenuBar(menuBar, pageId);

		CardInfoRowUtil.createPageHeader(panel, panelContent, "Ministry Members " + viewRiksdagenMinistry.getNameId(), "Current Members of Ministry", "Details the current composition of ministry members.");

		final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenGovermentRoleMember.class);

		getGridFactory().createBasicBeanItemGrid(panelContent, ViewRiksdagenGovermentRoleMember.class,
				govermentRoleMemberDataContainer.findListByProperty(
						new Object[] { viewRiksdagenMinistry.getNameId(), Boolean.TRUE },
						ViewRiksdagenGovermentRoleMember_.detail, ViewRiksdagenGovermentRoleMember_.active),
				CURRENT_MEMBERS, COLUMN_ORDER, HIDE_COLUMNS, LISTENER, null, null);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MINISTRY_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters, MinistryPageMode.CURRENTMEMBERS.toString());
	}

}
