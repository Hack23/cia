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
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class CommitteePageVisitHistoryPageModContentFactoryImpl.
 */
@Component
public final class CommitteePageVisitHistoryPageModContentFactoryImpl
		extends AbstractCommitteePageModContentFactoryImpl {

	/** The Constant COMMITTEE. */
	private static final String COMMITTEE = "Committee:";

	/**
	 * Instantiates a new committee page visit history page mod content factory
	 * impl.
	 */
	public CommitteePageVisitHistoryPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final ViewRiksdagenCommittee viewRiksdagenCommittee = getItem(parameters);
		getCommitteeMenuItemFactory().createCommitteeeMenuBar(menuBar, pageId);

		createPageHeader(panel, panelContent, "Committee Visits", "Page Visit History for Committees", "Tracks user interaction with committee pages for analytical purposes.");

		createPageVisitHistory(NAME, pageId, panelContent);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COMMITTEE_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters,PageMode.PAGEVISITHISTORY.toString());
	}

}
