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
package com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeDecisions;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeDecisionsEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeDecisions_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommitteePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class CommitteeDecisionSummaryPageModContentFactoryImpl.
 */
@Component
public final class CommitteeDecisionSummaryPageModContentFactoryImpl
		extends AbstractCommitteePageModContentFactoryImpl {

	private static final String BALLOT_ID = "ballotId";

	private static final String[] COLUMN_ORDER = { "createdDate", "publicDate", "committeeReport",
			"embeddedId.hangarId", "embeddedId.id", "embeddedId.issueNummer", "rm", "decisionType", "winner", "title",
			"header", "endNumber", "org", "committeeProposalUrlXml", BALLOT_ID, "againstProposalParties",
			"againstProposalNumber" };

	/** The Constant DECISION_SUMMARY. */
	private static final String DECISION_SUMMARY = "Decision Summary";

	private static final String[] HIDE_COLUMNS = { "embeddedId", "embeddedId.hangarId", "embeddedId.id",
			"endNumber", "org", "committeeProposalUrlXml", BALLOT_ID, "againstProposalParties", "againstProposalNumber",
			"createdDate" };

	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.BALLOT_VIEW_NAME, BALLOT_ID);

	private static final String[] NESTED_PROPERTIES = { "embeddedId.hangarId", "embeddedId.id",
			"embeddedId.issueNummer" };

	/**
	 * Instantiates a new committee decision summary page mod content factory
	 * impl.
	 */
	public CommitteeDecisionSummaryPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		getItem(parameters);
		getCommitteeMenuItemFactory().createCommitteeeMenuBar(menuBar, pageId);

		createPageHeader(panel, panelContent, "Committee Decision Summary", "Decision Summary", "Overview of decisions made by the committee.");

		final DataContainer<ViewRiksdagenCommitteeDecisions, ViewRiksdagenCommitteeDecisionsEmbeddedId> committeeDecisionDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenCommitteeDecisions.class);

		final List<ViewRiksdagenCommitteeDecisions> decisionPartySummaryList = committeeDecisionDataContainer
				.findOrderedListByProperty(ViewRiksdagenCommitteeDecisions_.org, pageId,
						ViewRiksdagenCommitteeDecisions_.createdDate);

		getGridFactory().createBasicBeanItemNestedPropertiesGrid(panelContent, ViewRiksdagenCommitteeDecisions.class,
				decisionPartySummaryList, DECISION_SUMMARY, NESTED_PROPERTIES, COLUMN_ORDER, HIDE_COLUMNS, LISTENER,
				BALLOT_ID, null);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COMMITTEE_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters, CommitteePageMode.DECISIONSUMMARY.toString());
	}

}
