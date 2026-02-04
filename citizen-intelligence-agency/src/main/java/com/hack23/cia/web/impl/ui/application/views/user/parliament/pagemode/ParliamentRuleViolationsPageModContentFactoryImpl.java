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
package com.hack23.cia.web.impl.ui.application.views.user.parliament.pagemode;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.github.markash.ui.component.card.CounterStatisticModel;
import com.github.markash.ui.component.card.CounterStatisticsCard;
import com.github.markash.ui.component.card.StatisticShow;
import com.hack23.cia.model.internal.application.data.rules.impl.ResourceType;
import com.hack23.cia.model.internal.application.data.rules.impl.RuleViolation;
import com.hack23.cia.model.internal.application.data.rules.impl.Status;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandParliamentRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
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

	/**
	 * Instantiates a new parliament rule violations page mod content factory impl.
	 */
	public ParliamentRuleViolationsPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		getParliamentMenuItemFactory().createParliamentTopicMenu(menuBar);
		CardInfoRowUtil.createPageHeader(panel, panelContent,
			    ParliamentViewConstants.RULE_VIOLATIONS_TITLE,
			    ParliamentViewConstants.RULE_VIOLATIONS_SUBTITLE,
			    ParliamentViewConstants.RULE_VIOLATIONS_DESC);


		final HorizontalLayout horizontalLayout = new HorizontalLayout();

		final DataContainer<RuleViolation, String> dataContainer = getApplicationManager()
				.getDataContainer(RuleViolation.class);

		final List<RuleViolation> ruleViolations = dataContainer.getAll();

		for (final Entry<Status, List<RuleViolation>> statusEntry : ruleViolations.stream().collect(Collectors.groupingBy(RuleViolation::getStatus)).entrySet()) {
			horizontalLayout.addComponent(new CounterStatisticsCard(
					VaadinIcons.WARNING,new CounterStatisticModel("ALL:" +statusEntry.getKey(),statusEntry.getValue().size()).withShow(StatisticShow.Sum)
                    .withIconHidden().withShowOnlyStatistic(true),"ALL:" +statusEntry.getKey()));
		}


		for (final Entry<ResourceType, List<RuleViolation>> statusEntry : ruleViolations.stream().collect(Collectors.groupingBy(RuleViolation::getResourceType)).entrySet()) {
			horizontalLayout.addComponent(new CounterStatisticsCard(
					VaadinIcons.WARNING,new CounterStatisticModel("ALL:" +statusEntry.getKey(),statusEntry.getValue().size()).withShow(StatisticShow.Sum)
                    .withIconHidden().withShowOnlyStatistic(true),"ALL:" +statusEntry.getKey()));
		}
		panelContent.addComponent(horizontalLayout);


		Collections.sort(ruleViolations, (o1, o2) -> o2.getStatus().compareTo(o1.getStatus()));

		getGridFactory().createBasicBeanItemGrid(panelContent, RuleViolation.class, ruleViolations, "Risk",
				new String[] { "name", "status", "resourceType", "ruleName", "ruleGroup", "ruleDescription", "positive" }, new String[] { "referenceId" }, CLICK_LISTENER, null, null);

		final String pageId = getPageId(parameters);
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARLIAMENT_RANKING_VIEW, ApplicationEventGroup.USER,
				NAME, parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandParliamentRankingConstants.COMMAND_RULE_VIOLATION.matches(page, parameters);
	}

}
