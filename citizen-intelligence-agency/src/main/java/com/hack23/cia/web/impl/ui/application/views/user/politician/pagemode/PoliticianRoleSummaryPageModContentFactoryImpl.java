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
package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.person.impl.AssignmentData;
import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PoliticianPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class RoleSummaryPageModContentFactoryImpl.
 */
@Component
public final class PoliticianRoleSummaryPageModContentFactoryImpl extends AbstractPoliticianPageModContentFactoryImpl {

	/** The Constant COMMITTEE_EXPERIENCE. */
	private static final String COMMITTEE_EXPERIENCE = "Committee experience:";

	/** The Constant EU_EXPERIENCE. */
	private static final String EU_EXPERIENCE = "EU experience:";

	/** The Constant GOVERNMENT_EXPERIENCE. */
	private static final String GOVERNMENT_EXPERIENCE = "Government experience:";

	/** The Constant PARLIAMENT_EXPERIENCE. */
	private static final String PARLIAMENT_EXPERIENCE = "Parliament experience:";

	/** The Constant PARTY_EXPERIENCE. */
	private static final String PARTY_EXPERIENCE = "Party experience:";

	/** The Constant SPEAKER_EXPERIENCE. */
	private static final String SPEAKER_EXPERIENCE = "Speaker experience:";

	/** The Constant TOTAL_ASSIGNMENTS. */
	private static final String TOTAL_ASSIGNMENTS = "Total Assignments:";

	/**
	 * Instantiates a new politician role summary page mod content factory impl.
	 */
	public PoliticianRoleSummaryPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);
		final ViewRiksdagenPolitician viewRiksdagenPolitician = getItem(parameters);
		getPoliticianMenuItemFactory().createPoliticianMenuBar(menuBar, pageId);

		CardInfoRowUtil.createPageHeader(panel, panelContent, viewRiksdagenPolitician.getFirstName() + ' ' + viewRiksdagenPolitician.getLastName() + '(' + viewRiksdagenPolitician.getParty() + ')' + " Role Summary", "Summary Overview", "Summarize the key roles and responsibilities of the politician.");

		final PersonData personData = getApplicationManager().getDataContainer(PersonData.class)
				.load(viewRiksdagenPolitician.getPersonId());

		final List<AssignmentData> assignmentList = personData.getPersonAssignmentData().getAssignmentList();

		createRoleSummary(panelContent, assignmentList, viewRiksdagenPolitician);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_POLITICIAN_VIEW, ApplicationEventGroup.USER,
		UserViews.POLITICIAN_VIEW_NAME, parameters, pageId);
		return panelContent;

	}

	/**
	 * Creates the role summary.
	 *
	 * @param roleSummaryLayoutTabsheet the role summary layout tabsheet
	 * @param assignmentList the assignment list
	 * @param viewRiksdagenPolitician the view riksdagen politician
	 */
	private static void createRoleSummary(final VerticalLayout roleSummaryLayoutTabsheet,
			final List<AssignmentData> assignmentList, final ViewRiksdagenPolitician viewRiksdagenPolitician) {

		final VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();

		layout.addComponent(new Label(PoliticianViewConstants.TOTAL_ASSIGNMENTS + assignmentList.size()));

		if (viewRiksdagenPolitician != null) {
			layout.addComponent(new Label(PoliticianViewConstants.GOVERNMENT_EXPERIENCE
					+ convertToYearsString(viewRiksdagenPolitician.getTotalDaysServedGovernment())));
			layout.addComponent(new Label(
					PoliticianViewConstants.SPEAKER_EXPERIENCE
					+ convertToYearsString(viewRiksdagenPolitician.getTotalDaysServedSpeaker())));
			layout.addComponent(new Label(PoliticianViewConstants.COMMITTEE_EXPERIENCE
					+ convertToYearsString(viewRiksdagenPolitician.getTotalDaysServedCommittee())));
			layout.addComponent(
					new Label(PoliticianViewConstants.EU_EXPERIENCE + convertToYearsString(viewRiksdagenPolitician.getTotalDaysServedEu())));
			layout.addComponent(new Label(PoliticianViewConstants.PARLIAMENT_EXPERIENCE
					+ convertToYearsString(viewRiksdagenPolitician.getTotalDaysServedParliament())));
			layout.addComponent(new Label(
					PoliticianViewConstants.PARTY_EXPERIENCE + convertToYearsString(viewRiksdagenPolitician.getTotalDaysServedParty())));
		}

		roleSummaryLayoutTabsheet.addComponent(layout);
		roleSummaryLayoutTabsheet.setExpandRatio(layout, ContentRatio.GRID);

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(PoliticianPageMode.ROLESUMMARY.toString());
	}

}
