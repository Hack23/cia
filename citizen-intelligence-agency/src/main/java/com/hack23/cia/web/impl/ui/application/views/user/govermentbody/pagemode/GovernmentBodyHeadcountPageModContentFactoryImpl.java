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
package com.hack23.cia.web.impl.ui.application.views.user.govermentbody.pagemode;
import com.hack23.cia.web.impl.ui.application.views.common.constants.GovernmentBodyDescriptionConstants;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;
import com.hack23.cia.web.impl.ui.application.views.common.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.GovernmentBodyChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandGovernmentBodyConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class GovernmentBodyHeadcountPageModContentFactoryImpl.
 */
@Component
public final class GovernmentBodyHeadcountPageModContentFactoryImpl extends AbstractGovernmentBodyPageModContentFactoryImpl {

	/** The government body chart data manager. */
	@Autowired
	private GovernmentBodyChartDataManager governmentBodyChartDataManager;

	/**
	 * Instantiates a new government body headcount page mod content factory impl.
	 */
	public GovernmentBodyHeadcountPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);
		final List<GovernmentBodyAnnualSummary> list = getItem(parameters);

		if (list != null && !list.isEmpty()) {
			final GovernmentBodyAnnualSummary governmentBodyAnnualSummary = list.get(0);

			if (governmentBodyAnnualSummary != null) {
				getGovernmentBodyMenuItemFactory().createGovernmentBodyMenuBar(menuBar, pageId,governmentBodyAnnualSummary.getName());
				CardInfoRowUtil.createPageHeader(panel, panelContent,
					formatTitle(governmentBodyAnnualSummary,
						GovernmentBodyDescriptionConstants.HEADCOUNT_HEADER),
					GovernmentBodyDescriptionConstants.HEADCOUNT_SUBTITLE,
					GovernmentBodyDescriptionConstants.HEADCOUNT_DESC);
				governmentBodyChartDataManager.createGovernmentBodyHeadcountSummaryChart(panelContent, governmentBodyAnnualSummary.getName());
			}
			getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MINISTRY_VIEW, ApplicationEventGroup.USER, NAME,
					parameters, pageId);

		}

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return  PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_HEADCOUNT.matches(page, parameters);
	}

}
