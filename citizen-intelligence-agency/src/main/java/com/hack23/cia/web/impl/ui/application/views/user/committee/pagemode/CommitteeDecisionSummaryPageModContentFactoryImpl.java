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

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeDecisions;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeDecisionsEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeDecisions_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommitteePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class CommitteeDecisionSummaryPageModContentFactoryImpl.
 */
@Component
public final class CommitteeDecisionSummaryPageModContentFactoryImpl
		extends AbstractCommitteePageModContentFactoryImpl {

	/** The Constant COMMITTEE. */
	private static final String COMMITTEE = "Committee:";

	/** The Constant DECISION_SUMMARY_NOT_IMPLEMENTED. */
	private static final String DECISION_SUMMARY = "Decision Summary";

	/**
	 * Instantiates a new committee decision summary page mod content factory
	 * impl.
	 */
	public CommitteeDecisionSummaryPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && (!StringUtils.isEmpty(parameters)
				&& parameters.contains(CommitteePageMode.DECISIONSUMMARY.toString()));
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final DataContainer<ViewRiksdagenCommittee, String> dataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenCommittee.class);

		final ViewRiksdagenCommittee viewRiksdagenCommittee = dataContainer.load(pageId);

		if (viewRiksdagenCommittee != null) {

			getCommitteeMenuItemFactory().createCommitteeeMenuBar(menuBar, pageId);

			LabelFactory.createHeader2Label(panelContent, DECISION_SUMMARY);

			final DataContainer<ViewRiksdagenCommitteeDecisions, ViewRiksdagenCommitteeDecisionsEmbeddedId> committeeDecisionDataContainer = getApplicationManager()
					.getDataContainer(ViewRiksdagenCommitteeDecisions.class);

			final List<ViewRiksdagenCommitteeDecisions> decisionPartySummaryList = committeeDecisionDataContainer
					.findOrderedListByProperty(ViewRiksdagenCommitteeDecisions_.org, pageId,
							ViewRiksdagenCommitteeDecisions_.createdDate);

			final BeanItemContainer<ViewRiksdagenCommitteeDecisions> committeeDecisionDataSource = new BeanItemContainer<>(
					ViewRiksdagenCommitteeDecisions.class, decisionPartySummaryList);

			getGridFactory().createBasicBeanItemNestedPropertiesGrid(panelContent, committeeDecisionDataSource,
					DECISION_SUMMARY, new String[] { "embeddedId.hangarId", "embeddedId.id", "embeddedId.issueNummer" },
					new String[] { "createdDate", "publicDate", "committeeReport", "embeddedId.hangarId",
							"embeddedId.id", "embeddedId.issueNummer", "rm", "decisionType", "winner", "title",
							"header", "endNumber", "org", "committeeProposalUrlXml", "ballotId",
							"againstProposalParties", "againstProposalNumber" },
					new String[] { "embeddedId", "embeddedId.hangarId", "embeddedId.id", "endNumber", "org",
							"committeeProposalUrlXml", "ballotId", "againstProposalParties", "againstProposalNumber",
							"createdDate" },
					new PageItemPropertyClickListener(UserViews.BALLOT_VIEW_NAME, "ballotId"), "ballotId", null);

			panel.setCaption(NAME + "::" + COMMITTEE + viewRiksdagenCommittee.getEmbeddedId().getDetail());
			getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COMMITTEE_VIEW, ApplicationEventGroup.USER,
					NAME, parameters, pageId);
		}
		return panelContent;

	}

}
