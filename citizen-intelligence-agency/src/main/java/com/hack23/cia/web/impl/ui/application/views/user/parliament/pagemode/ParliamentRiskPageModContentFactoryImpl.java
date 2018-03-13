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
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.github.markash.ui.component.card.CounterStatisticModel;
import com.github.markash.ui.component.card.CounterStatisticsCard;
import com.github.markash.ui.component.card.StatisticShow;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.action.kpi.ComplianceCheck;
import com.hack23.cia.service.api.action.kpi.ComplianceCheckRequest;
import com.hack23.cia.service.api.action.kpi.ComplianceCheckResponse;
import com.hack23.cia.service.api.action.kpi.ResourceType;
import com.hack23.cia.service.api.action.kpi.RuleViolation;
import com.hack23.cia.service.api.action.kpi.Status;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.RiskIndicators;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.ComplianceCheckPageItemRendererClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class ParliamentRiskPageModContentFactoryImpl.
 */
@Component
public final class ParliamentRiskPageModContentFactoryImpl extends AbstractParliamentPageModContentFactoryImpl {

	private static final ComplianceCheckPageItemRendererClickListener CLICK_LISTENER = new ComplianceCheckPageItemRendererClickListener();
	/** The Constant PARLIAMENT_DECISION_FLOW. */
	private static final String PARLIAMENT_RISK_SUMMARY = "Parliament Risk Summary";

	/**
	 * Instantiates a new parliament risk page mod content factory impl.
	 */
	public ParliamentRiskPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && !StringUtils.isEmpty(parameters) && parameters.contains(PageMode.RULES.toString())
				&& parameters.contains(RiskIndicators.RISK_SUMMARY.toString());
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		getParliamentMenuItemFactory().createParliamentTopicMenu(menuBar);

		final String pageId = getPageId(parameters);

		final ComplianceCheckRequest serviceRequest = new ComplianceCheckRequest();
		serviceRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		final ComplianceCheckResponse serviceResponse = (ComplianceCheckResponse) getApplicationManager()
				.service(serviceRequest);
		
		final HorizontalLayout horizontalLayout = new HorizontalLayout();

		for (final Entry<Status, List<RuleViolation>> statusEntry : serviceResponse.getStatusMap().entrySet()) {
			horizontalLayout.addComponent(new CounterStatisticsCard(
					VaadinIcons.WARNING,new CounterStatisticModel("ALL:" +statusEntry.getKey(),statusEntry.getValue().size()).withShow(StatisticShow.Sum)
                    .withIconHidden().withShowOnlyStatistic(true),"ALL:" +statusEntry.getKey()));			
		}

		for (final Entry<ResourceType, List<RuleViolation>> statusEntry : serviceResponse.getResourceTypeMap().entrySet()) {
			horizontalLayout.addComponent(new CounterStatisticsCard(
					VaadinIcons.WARNING,new CounterStatisticModel("ALL:" +statusEntry.getKey(),statusEntry.getValue().size()).withShow(StatisticShow.Sum)
                    .withIconHidden().withShowOnlyStatistic(true),"ALL:" +statusEntry.getKey()));			
		}

		
		panelContent.addComponent(horizontalLayout);		
		
		getGridFactory().createBasicBeanItemGrid(panelContent, ComplianceCheck.class, serviceResponse.getList(), "Risk",
				new String[] { "name", "resourceType", "numberRuleViolations", "ruleSummary" }, new String[] { "id", "ruleViolations" }, CLICK_LISTENER, null, null);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARLIAMENT_RANKING_VIEW, ApplicationEventGroup.USER,
				NAME, parameters, pageId);
		panel.setCaption(new StringBuilder().append(NAME).append("::").append(PARLIAMENT_RISK_SUMMARY).toString());

		return panelContent;

	}

}
