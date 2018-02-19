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
package com.hack23.cia.web.impl.ui.application.views.user.parliament.pagemode;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeDecisionTypeOrgDailySummary;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.widgets.charts.SankeyChart;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class ParliamentDecisionFlowPageModContentFactoryImpl.
 */
@Component
public final class ParliamentDecisionFlowPageModContentFactoryImpl extends AbstractParliamentPageModContentFactoryImpl {


	/** The Constant PARLIAMENT_DECISION_FLOW. */
	private static final String PARLIAMENT_DECISION_FLOW = "Parliament decision flow";

	
	/**
	 * Instantiates a new parliament decision flow page mod content factory impl.
	 */
	public ParliamentDecisionFlowPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && !StringUtils.isEmpty(parameters) && parameters.contains(PageMode.CHARTS.toString())
				&& parameters.contains(ChartIndicators.DECISION_FLOW_CHART.toString());
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		getParliamentMenuItemFactory().createParliamentTopicMenu(menuBar);

		final String pageId = getPageId(parameters);

		
		
		final DataContainer<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary, String> dataContainer = getApplicationManager().getDataContainer(ViewRiksdagenCommitteeDecisionTypeOrgDailySummary.class);
		
		Map<String, List<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary>> summaryMap = dataContainer.getAll()
			    .stream()
			    .collect(Collectors.groupingBy(p -> p.getEmbeddedId().getOrg()));
		
		
		SankeyChart chart = new SankeyChart();
		
		for (Entry<String, List<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary>> entry : summaryMap.entrySet()) {
			long  total = entry.getValue().stream().collect(Collectors.summarizingLong(p -> p.getTotal())).getSum();
			chart.addDataRow(entry.getKey(),"Decision",(int)total);
		}
		
        chart.drawChart();

        panelContent.addComponent(chart);		

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARLIAMENT_RANKING_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);
		panel.setCaption(new StringBuilder().append(NAME).append("::").append(PARLIAMENT_DECISION_FLOW).toString());

		return panelContent;

	}

}
