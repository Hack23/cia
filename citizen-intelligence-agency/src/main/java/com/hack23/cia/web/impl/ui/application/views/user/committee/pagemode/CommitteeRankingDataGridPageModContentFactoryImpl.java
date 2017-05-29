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
package com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class CommitteeRankingDataGridPageModContentFactoryImpl.
 */
@Service
public final class CommitteeRankingDataGridPageModContentFactoryImpl extends AbstractCommitteeRankingPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = UserViews.COMMITTEE_RANKING_VIEW_NAME;

	/** The Constant DATAGRID. */
	private static final String DATAGRID = "Datagrid";


	/**
	 * Instantiates a new committee ranking data grid page mod content factory
	 * impl.
	 */
	public CommitteeRankingDataGridPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && (!StringUtils.isEmpty(parameters) && parameters.contains(PageMode.DATAGRID.toString()));
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		getCommitteeRankingMenuItemFactory().createCommitteeeRankingMenuBar(menuBar);

		final String pageId = getPageId(parameters);

		final DataContainer<ViewRiksdagenCommittee, String> dataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenCommittee.class);

		final BeanItemContainer<ViewRiksdagenCommittee> politicianDocumentDataSource = new BeanItemContainer<>(
				ViewRiksdagenCommittee.class, dataContainer.getAllOrderBy(ViewRiksdagenCommittee_.currentMemberSize));

		politicianDocumentDataSource.addNestedContainerProperty("embeddedId.detail");
		politicianDocumentDataSource.addNestedContainerProperty("embeddedId.orgCode");

		getGridFactory().createBasicBeanItemGrid(panelContent, politicianDocumentDataSource,
				"Committees",
				new String[] { "embeddedId.orgCode", "embeddedId.detail", "totalDaysServed", "currentMemberSize",
						"totalAssignments", "firstAssignmentDate", "active", "lastAssignmentDate" }, new String[] { "embeddedId","embeddedId.orgCode" , "active" },
				new PageItemPropertyClickListener(UserViews.COMMITTEE_VIEW_NAME, "embeddedId.orgCode"), null, null);

		panel.setCaption(NAME + "::" + DATAGRID);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COMMITTEE_RANKING_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}

}
