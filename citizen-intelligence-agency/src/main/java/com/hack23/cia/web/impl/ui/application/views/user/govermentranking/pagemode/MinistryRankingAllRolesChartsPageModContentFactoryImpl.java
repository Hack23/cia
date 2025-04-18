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
package com.hack23.cia.web.impl.ui.application.views.user.govermentranking.pagemode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.MinistryGhantChartManager;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandMinistryRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class MinistryRankingAllRolesChartsPageModContentFactoryImpl.
 */
@Service
public final class MinistryRankingAllRolesChartsPageModContentFactoryImpl
        extends AbstractMinistryRankingPageModContentFactoryImpl {

    /** The ministry ghant chart manager. */
    @Autowired
    private MinistryGhantChartManager ministryGhantChartManager;

    /**
     * Instantiates a new ministry ranking all roles charts page mod content
     * factory impl.
     */
    public MinistryRankingAllRolesChartsPageModContentFactoryImpl() {
        super();
    }

    @Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
    @Override
    public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
        final VerticalLayout panelContent = createPanelContent();

        getMinistryRankingMenuItemFactory().createMinistryRankingMenuBar(menuBar);

        final String pageId = getPageId(parameters);

        CardInfoRowUtil.createPageHeader(panel, panelContent,
            MinistryRankingViewConstants.TITLE_MINISTRY_RANKINGS,
            MinistryRankingViewConstants.ALL_ROLES_TITLE,
            MinistryRankingViewConstants.ALL_ROLES_DESC);

        final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer =
            getApplicationManager().getDataContainer(ViewRiksdagenGovermentRoleMember.class);

        final List<ViewRiksdagenGovermentRoleMember> allMembers = govermentRoleMemberDataContainer.getAll();

        ministryGhantChartManager.createRoleGhant(panelContent, allMembers);

        getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MINISTRY_RANKING_VIEW, ApplicationEventGroup.USER,
                NAME, parameters, pageId);

        return panelContent;
    }

    @Override
    public boolean matches(final String page, final String parameters) {
    	return PageCommandMinistryRankingConstants.COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT.matches(page, parameters);
    }
}
