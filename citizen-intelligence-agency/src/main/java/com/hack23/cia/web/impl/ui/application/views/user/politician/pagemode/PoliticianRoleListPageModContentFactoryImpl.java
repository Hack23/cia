/*
 * Copyright 2010-2024 James Pether Sörling
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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.person.impl.AssignmentData;
import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PoliticianPageMode;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class RoleListPageModContentFactoryImpl.
 */
@Component
public final class PoliticianRoleListPageModContentFactoryImpl extends AbstractPoliticianPageModContentFactoryImpl {

	private static final String ASSIGNMENTS = "Assignments";
	private static final String[] COLUMN_ORDER = { "roleCode", "assignmentType", "status", "detail",
			"orgCode", "fromDate", "toDate" };
	private static final String[] HIDE_COLUMNS = { "hjid", "intressentId", "orderNumber", "orgCode" };

	/**
	 * Instantiates a new politician role list page mod content factory impl.
	 */
	public PoliticianRoleListPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);
		final ViewRiksdagenPolitician viewRiksdagenPolitician = getItem(parameters);
		getPoliticianMenuItemFactory().createPoliticianMenuBar(menuBar, pageId);
		LabelFactory.createHeader2Label(panelContent, PoliticianPageMode.ROLELIST.toString());

		final PersonData personData = getApplicationManager().getDataContainer(PersonData.class)
				.load(viewRiksdagenPolitician.getPersonId());

		final List<AssignmentData> assignmentList = personData.getPersonAssignmentData().getAssignmentList();

		createRoleList(panelContent, assignmentList);

		pageCompleted(parameters, panel, pageId, viewRiksdagenPolitician);
		return panelContent;

	}

	/**
	 * Creates the role list.
	 *
	 * @param roleSummaryLayoutTabsheet the role summary layout tabsheet
	 * @param assignmentList the assignment list
	 */
	private void createRoleList(final VerticalLayout roleSummaryLayoutTabsheet,
			final List<AssignmentData> assignmentList) {

		final Comparator<AssignmentData> compare = (o1, o2) -> o1.getFromDate().compareTo(o2.getFromDate());

		Collections.sort(assignmentList, compare);

		getGridFactory().createBasicBeanItemGrid(roleSummaryLayoutTabsheet, AssignmentData.class, assignmentList,
				ASSIGNMENTS, COLUMN_ORDER, HIDE_COLUMNS, null, null, null);

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(PoliticianPageMode.ROLELIST.toString());
	}

}
