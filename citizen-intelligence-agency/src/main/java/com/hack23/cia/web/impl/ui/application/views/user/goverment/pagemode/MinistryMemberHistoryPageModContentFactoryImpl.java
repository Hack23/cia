/*
 * Copyright 2014 James Pether Sörling
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
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.MinistryPageMode;
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

	/** The Constant MEMBER_HISTORY. */
	private static final String MEMBER_HISTORY = "Member History";

	/** The Constant MINISTRY. */
	private static final String MINISTRY = "Ministry:";

	/**
	 * Instantiates a new ministry member history page mod content factory impl.
	 */
	public MinistryMemberHistoryPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && !StringUtils.isEmpty(parameters) && parameters.contains(MinistryPageMode.MEMBERHISTORY.toString());
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final DataContainer<ViewRiksdagenMinistry, String> dataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenMinistry.class);

		final ViewRiksdagenMinistry viewRiksdagenMinistry = dataContainer.load(pageId);

		if (viewRiksdagenMinistry != null) {

			getMinistryMenuItemFactory().createMinistryMenuBar(menuBar, pageId);

			LabelFactory.createHeader2Label(panelContent,MEMBER_HISTORY);


			final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer = getApplicationManager()
					.getDataContainer(ViewRiksdagenGovermentRoleMember.class);

			getGridFactory().createBasicBeanItemGrid(
					panelContent, ViewRiksdagenGovermentRoleMember.class, govermentRoleMemberDataContainer.getAllBy(
							ViewRiksdagenGovermentRoleMember_.detail, viewRiksdagenMinistry.getNameId()),
					MEMBER_HISTORY,
					new String[] { "roleCode", "roleId", "personId", "firstName", "lastName", "party", "active","totalDaysServed", "detail",
							"fromDate", "toDate" }, new String[] { "roleId", "personId", "detail" },
					new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId"), null, null);

			panel.setCaption(NAME + "::" + MINISTRY + viewRiksdagenMinistry.getNameId());
			getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MINISTRY_VIEW, ApplicationEventGroup.USER, NAME,
					parameters, pageId);

		}

		return panelContent;

	}

}
