/*
 * Copyright 2010-2019 James Pether Sörling
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.DecisionFlowChartManager;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.DecisionFlowValueChangeListener;
import com.hack23.cia.web.widgets.charts.SankeyChart;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class ParliamentDecisionFlowPageModContentFactoryImpl.
 */
@Component
public final class ParliamentDecisionFlowPageModContentFactoryImpl extends AbstractParliamentPageModContentFactoryImpl {


	/** The Constant PARLIAMENT_DECISION_FLOW. */
	private static final String PARLIAMENT_DECISION_FLOW = "Parliament decision flow";
	
	@Autowired
	private DecisionFlowChartManager decisionFlowChartManager;
	
	/**
	 * Instantiates a new parliament decision flow page mod content factory impl.
	 */
	public ParliamentDecisionFlowPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters, PageMode.CHARTS.toString())
				&& parameters.contains(ChartIndicators.DECISION_FLOW_CHART.toString());
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		getParliamentMenuItemFactory().createParliamentTopicMenu(menuBar);

		String selectedYear = "2018/19";
		if (parameters != null && parameters.contains("[") && parameters.contains("]")) {
			selectedYear = parameters.substring(parameters.indexOf('[') + 1, parameters.lastIndexOf(']'));
		} 
		
		final DataContainer<ViewRiksdagenCommittee, String> dataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenCommittee.class);
		final List<ViewRiksdagenCommittee> allCommittess = dataContainer.getAll();

		final Map<String, List<ViewRiksdagenCommittee>> committeeMap = allCommittess.stream().collect(Collectors.groupingBy(c -> c.getEmbeddedId().getOrgCode().toUpperCase(Locale.ENGLISH)));
		
		final ComboBox<String> comboBox = new ComboBox<>("Select year", Collections.unmodifiableList(Arrays.asList("2018/19","2017/18","2016/17","2015/16","2014/15","2013/14","2012/13","2011/12","2010/11")));
		panelContent.addComponent(comboBox);
		panelContent.setExpandRatio(comboBox, ContentRatio.SMALL);
		comboBox.setSelectedItem(selectedYear);
		comboBox.addValueChangeListener(new DecisionFlowValueChangeListener(NAME,""));
		
		final SankeyChart chart = decisionFlowChartManager.createAllDecisionFlow(committeeMap,comboBox.getSelectedItem().orElse(selectedYear));
		panelContent.addComponent(chart);
		panelContent.setExpandRatio(chart, ContentRatio.LARGE);

		final TextArea textarea = decisionFlowChartManager.createCommitteeeDecisionSummary(committeeMap,comboBox.getSelectedItem().orElse(selectedYear));
		textarea.setSizeFull();
		panelContent.addComponent(textarea);
		panelContent.setExpandRatio(textarea, ContentRatio.SMALL_GRID);


		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARLIAMENT_RANKING_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, selectedYear);
		panel.setCaption(new StringBuilder().append(NAME).append("::").append(PARLIAMENT_DECISION_FLOW).toString());

		return panelContent;

	}

}
