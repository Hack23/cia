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

import java.util.ArrayList;
import java.util.Collections;
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
import com.hack23.cia.service.api.action.kpi.ComplianceCheckRequest;
import com.hack23.cia.service.api.action.kpi.ComplianceCheckResponse;
import com.hack23.cia.service.api.action.kpi.ResourceType;
import com.hack23.cia.service.api.action.kpi.RuleViolation;
import com.hack23.cia.service.api.action.kpi.Status;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.RiskIndicators;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.RuleViolationPageItemRendererClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class ParliamentRuleViolationsPageModContentFactoryImpl.
 */
@Component
public final class ParliamentRuleViolationsPageModContentFactoryImpl extends AbstractParliamentPageModContentFactoryImpl {

	/** The Constant CLICK_LISTENER. */
	private static final RuleViolationPageItemRendererClickListener CLICK_LISTENER = new RuleViolationPageItemRendererClickListener();
	
	/** The Constant PARLIAMENT_DECISION_FLOW. */
	private static final String PARLIAMENT_RULE_VIOLATIONS = "Parliament Rules violations";

	/**
	 * Instantiates a new parliament rule violations page mod content factory impl.
	 */
	public ParliamentRuleViolationsPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && !StringUtils.isEmpty(parameters) && parameters.contains(PageMode.RULES.toString())
				&& parameters.contains(RiskIndicators.RULE_VIOLATIONS.toString());
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		getParliamentMenuItemFactory().createParliamentTopicMenu(menuBar);


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

		final List<RuleViolation> ruleViolations = new ArrayList<>();
		
		for (final Entry<ResourceType, List<RuleViolation>> statusEntry : serviceResponse.getResourceTypeMap().entrySet()) {
			horizontalLayout.addComponent(new CounterStatisticsCard(
					VaadinIcons.WARNING,new CounterStatisticModel("ALL:" +statusEntry.getKey(),statusEntry.getValue().size()).withShow(StatisticShow.Sum)
                    .withIconHidden().withShowOnlyStatistic(true),"ALL:" +statusEntry.getKey()));
			ruleViolations.addAll(statusEntry.getValue());
		}			
		panelContent.addComponent(horizontalLayout);		
		
		
		Collections.sort(ruleViolations, (o1, o2) -> o2.getStatus().compareTo(o1.getStatus()));
		
		getGridFactory().createBasicBeanItemGrid(panelContent, RuleViolation.class, ruleViolations, "Risk",
				new String[] { "name", "status", "resourceType", "ruleName", "ruleGroup", "ruleDescription", "positive" }, new String[] { "id" }, CLICK_LISTENER, null, null);

		final String pageId = getPageId(parameters);
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARLIAMENT_RANKING_VIEW, ApplicationEventGroup.USER,
				NAME, parameters, pageId);
		panel.setCaption(new StringBuilder().append(NAME).append("::").append(PARLIAMENT_RULE_VIOLATIONS).toString());

		return panelContent;

	}

}
