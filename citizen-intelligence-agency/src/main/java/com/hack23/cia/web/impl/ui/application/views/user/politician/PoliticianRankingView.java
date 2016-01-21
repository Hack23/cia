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
package com.hack23.cia.web.impl.ui.application.views.user.politician;

import javax.annotation.PostConstruct;

import org.dussan.vaadin.dcharts.data.DataSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician_;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.DataSeriesFactory;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextArea;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class PoliticianRankingView.
 */
@Service
@Scope(value="prototype")
@VaadinView(value = PoliticianRankingView.NAME, cached = true)
public class PoliticianRankingView extends AbstractRankingView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = UserViews.POLITICIAN_RANKING_VIEW_NAME;

	/** The application manager. */
	@Autowired
	@Qualifier("ApplicationManager")
	private transient ApplicationManager applicationManager;

	/** The politician data source. */
	private BeanItemContainer<ViewRiksdagenPolitician> politicianDataSource;

	/** The menu item factory. */
	@Autowired
	private transient MenuItemFactory menuItemFactory;

	/** The grid factory. */
	@Autowired
	private transient GridFactory gridFactory;

	/** The data series factory. */
	@Autowired
	private transient DataSeriesFactory dataSeriesFactory;


	/**
	 * Post construct.
	 */
	@PostConstruct
	public void postConstruct() {
		setSizeFull();
		createBasicLayoutWithPanelAndFooter(NAME);
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView#createMenuBar()
	 */
	@Override
	protected void createMenuBar() {
		menuItemFactory.createPoliticianRankingMenuBar(getBarmenu());
	}


	/**
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView
	 * #createDescription()
	 */
	@Override
	protected TextArea createDescription() {
		final TextArea totalpoliticantoplistLabel = new TextArea(
				"Politician Ranking by topic",
				"Time served in Parliament:ALL:CURRENT:*FILTER:Gender,Party,ElectionRegion"
						+ "\nTime served in Committees:ALL:CURRENT:*FILTER:Gender,Party,ElectionRegion"
						+ "\nTime served in Government:ALL:CURRENT:*FILTER:Gender,Party,ElectionRegion"
						+ "\nTop document author NR:ALL:YEAR:CURRENT:*FILTER:DocumnetType,Gender,Party,ElectionRegion"
						+ "\nTop document author SIZE:YEAR:ALL:CURRENT:*FILTER:DocumnetType,Gender,Party,ElectionRegion"

						+ "\nTop votes:ALL:YEAR:CURRENT::*FILTER:Gender,Party,ElectionRegion"
						+ "\nTop vote winner NR/PERCENTAGE :ALL:YEAR:CURRENT::*FILTER:Gender,Party,ElectionRegion"
						+ "\nTop vote party rebel NR/PERCENTAGE :ALL:YEAR:CURRENT::*FILTER:Gender,Party,ElectionRegion"
						+ "\nTop vote presence NR/PERCENTAGE :ALL:YEAR:CURRENT::*FILTER:Gender,Party,ElectionRegion"
						+ "\nSearch by name");
		totalpoliticantoplistLabel.setSizeFull();
		return totalpoliticantoplistLabel;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView
	 * #createTable()
	 */
	@Override
	protected Component createTable() {

		if (politicianDataSource == null) {
			final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = applicationManager
					.getDataContainer(ViewRiksdagenPolitician.class);

			politicianDataSource = new BeanItemContainer<>(
					ViewRiksdagenPolitician.class,
					politicianDataContainer.getAllOrderBy(ViewRiksdagenPolitician_.currentAssignments));

		}

		return gridFactory.createBasicBeanItemGrid(politicianDataSource, "Politicians",
				new String[] { "personId", "firstName", "lastName", "party",
				"gender", "bornYear", "totalAssignments",
				"currentAssignments", "firstAssignmentDate",
				"lastAssignmentDate", "totalDaysServed",
				"totalDaysServedParliament",
				"totalDaysServedCommittee",
				"totalDaysServedGovernment", "totalDaysServedEu",

				"active", "activeEu", "activeGovernment",
				"activeCommittee", "activeParliament",

				"activeParty", "activeSpeaker",
				"totalDaysServedSpeaker", "totalDaysServedParty",

				"totalPartyAssignments", "totalMinistryAssignments",
				"totalCommitteeAssignments", "totalSpeakerAssignments",

				"currentPartyAssignments",
				"currentMinistryAssignments",
				"currentCommitteeAssignments",
		"currentSpeakerAssignments" },null, "personId",
		new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId"), null);
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView#createChartTimeSeriesAll()
	 */
	@Override
	protected DataSeries createChartTimeSeriesAll() {
		return dataSeriesFactory.createPartyChartTimeSeriesAll();
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView#createChartTimeSeriesCurrent()
	 */
	@Override
	protected DataSeries createChartTimeSeriesCurrent() {
		return dataSeriesFactory.createPartyChartTimeSeriesCurrent();
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView#getName()
	 */
	@Override
	protected String getName() {
		return NAME;
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView#getViewAction()
	 */
	@Override
	protected ViewAction getViewAction() {
		return ViewAction.VISIT_POLITICIAN_RANKING_VIEW;
	}


}