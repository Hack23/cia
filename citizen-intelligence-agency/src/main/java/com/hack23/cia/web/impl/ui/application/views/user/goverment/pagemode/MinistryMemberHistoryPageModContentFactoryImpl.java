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

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember_;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandMinistryConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class MinistryMemberHistoryPageModContentFactoryImpl.
 */
@Component
public final class MinistryMemberHistoryPageModContentFactoryImpl extends AbstractMinistryPageModContentFactoryImpl {

	/** The Constant LISTENER. */
	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.POLITICIAN_VIEW_NAME, MinistryMemberConstants.PERSON_ID);

	/**
	 * Instantiates a new ministry member history page mod content factory impl.
	 */
	public MinistryMemberHistoryPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final ViewRiksdagenMinistry viewRiksdagenMinistry = getItem(parameters);

		getMinistryMenuItemFactory().createMinistryMenuBar(menuBar, pageId);

		CardInfoRowUtil.createPageHeader(panel, panelContent,
			MinistryPageModeConstants.MEMBER_HISTORY_TITLE + " " + viewRiksdagenMinistry.getNameId(),
			MinistryPageModeConstants.MEMBER_HISTORY_SUBTITLE,
			MinistryPageModeConstants.MEMBER_HISTORY_DESC);

		final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenGovermentRoleMember.class);

		getGridFactory().createBasicBeanItemGrid(panelContent, ViewRiksdagenGovermentRoleMember.class,
				govermentRoleMemberDataContainer.getAllBy(ViewRiksdagenGovermentRoleMember_.detail,
						viewRiksdagenMinistry.getNameId()),
				MinistryMemberConstants.MEMBER_HISTORY,
				MinistryMemberConstants.MEMBER_COLUMN_ORDER,
				MinistryMemberConstants.MEMBER_HIDE_COLUMNS,
				LISTENER, null, null);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MINISTRY_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandMinistryConstants.COMMAND_MINISTRY_MEMBER_HISTORY.matches(page, parameters);
	}

}
