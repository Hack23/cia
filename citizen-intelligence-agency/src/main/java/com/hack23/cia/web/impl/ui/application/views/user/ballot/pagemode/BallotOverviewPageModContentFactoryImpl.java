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
package com.hack23.cia.web.impl.ui.application.views.user.ballot.pagemode;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotEmbeddedId_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotSummary_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class BallotOverviewPageModContentFactoryImpl.
 */
@Component
public final class BallotOverviewPageModContentFactoryImpl extends AbstractBallotPageModContentFactoryImpl {

	private static final int FIRST_OBJECT = 0;

	/** The Constant COMMITTEE. */
	private static final String BALLOT = "Ballot:";

	/** The Constant OVERVIEW. */
	private static final String OVERVIEW = "overview";

	/**
	 * Instantiates a new committee overview page mod content factory impl.
	 */
	public BallotOverviewPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		final String pageId = getPageId(parameters);
		return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
				|| parameters.contains(PageMode.OVERVIEW.toString()));
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);


		final DataContainer<ViewRiksdagenVoteDataBallotSummary, RiksdagenVoteDataBallotEmbeddedId> dataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenVoteDataBallotSummary.class);

		
		final List<ViewRiksdagenVoteDataBallotSummary> ballots = dataContainer.findListByEmbeddedProperty(ViewRiksdagenVoteDataBallotSummary.class, ViewRiksdagenVoteDataBallotSummary_.embeddedId, RiksdagenVoteDataBallotEmbeddedId.class, RiksdagenVoteDataBallotEmbeddedId_.ballotId, pageId);

		if (!ballots.isEmpty()) {
			getBallotMenuItemFactory().createBallotMenuBar(menuBar, pageId);

			final ViewRiksdagenVoteDataBallotSummary viewRiksdagenVoteDataBallotSummary = ballots.get(FIRST_OBJECT);
						
				final Label createHeader2Label = LabelFactory.createHeader2Label(OVERVIEW);
				panelContent.addComponent(createHeader2Label);


				final Panel formPanel = new Panel();
				formPanel.setSizeFull();

				panelContent.addComponent(formPanel);

				final FormLayout formContent = new FormLayout();
				formPanel.setContent(formContent);

				getFormFactory().addTextFields(formContent, new BeanItem<>(viewRiksdagenVoteDataBallotSummary),
						ViewRiksdagenVoteDataBallotSummary.class,
						Arrays.asList(new String[] { "embeddedId.ballotId","embeddedId.issue","embeddedId.concern",
							    "voteDate","rm", "ballotType",  "label","avgBornYear",   "totalVotes",
							    "yesVotes",
							    "noVotes",
							    "abstainVotes",
							    "absentVotes",
							    "approved",
							    "noWinner",
							    "percentageYes",
							    "percentageNo",
							    "percentageAbsent",
							    "percentageAbstain",
							    "percentageMale" }));
				
				
				panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);
				panelContent.setExpandRatio(formPanel, ContentRatio.GRID);

				panel.setCaption(BALLOT + viewRiksdagenVoteDataBallotSummary.getEmbeddedId().getBallotId());
				getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COMMITTEE_VIEW, ApplicationEventGroup.USER, NAME, parameters, pageId);
		}
		return panelContent;

	}

}
