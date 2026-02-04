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
package com.hack23.cia.web.impl.ui.application.views.user.govermentbodyranking.pagemode;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.views.common.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandGovernmentBodyRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.constants.GovernmentBodyDescriptionConstants;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class GovernmentBodyRankingOverviewPageModContentFactoryImpl.
 */
@Component
public final class GovernmentBodyRankingOverviewPageModContentFactoryImpl extends AbstractGovernmentBodyRankingPageModContentFactoryImpl {


	/**
	 * Instantiates a new government body ranking overview page mod content factory
	 * impl.
	 */
	public GovernmentBodyRankingOverviewPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
        final String pageId = getPageId(parameters);

        createMenuBar(menuBar);
        createHeader(panel, panelContent);
        createOverviewContent(panelContent);

        getPageActionEventHelper().createPageEvent(ViewAction.VISIT_GOVERNMENT_BODY_RANKING_VIEW,
            ApplicationEventGroup.USER, NAME, parameters, pageId);

        return panelContent;
	}

    /**
     * Creates the menu bar.
     *
     * @param menuBar the menu bar
     */
    private void createMenuBar(final MenuBar menuBar) {
        getGovernmentBodyRankingMenuItemFactory().createGovernmentBodyRankingMenuBar(menuBar);
    }

    /**
     * Creates the header.
     *
     * @param panel the panel
     * @param panelContent the panel content
     */
    private void createHeader(final Panel panel, final VerticalLayout panelContent) {
        CardInfoRowUtil.createPageHeader(panel, panelContent,
            GovernmentBodyDescriptionConstants.RANKING_HEADER,
            GovernmentBodyDescriptionConstants.RANKING_SUBTITLE,
            GovernmentBodyDescriptionConstants.RANKING_DESC);
    }

    /**
     * Creates the overview content.
     *
     * @param panelContent the panel content
     */
    private void createOverviewContent(final VerticalLayout panelContent) {
        getGovernmentBodyRankingMenuItemFactory().createOverviewPage(panelContent);
    }

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW.matches(page, parameters);
	}


}
