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

import org.apache.commons.lang3.StringUtils;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.AdminChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.PartyDataSeriesFactory;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.PageModeContentFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class PoliticianRankingView.
 */
@Service
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@VaadinView(value = PoliticianRankingView.NAME, cached = true)
public final class PoliticianRankingView extends AbstractRankingView {

	/** The Constant CHARTS. */
	private static final String CHARTS = "Charts:";

	/** The Constant DATAGRID. */
	private static final String DATAGRID = "Datagrid:";

	/** The Constant NAME. */
	public static final String NAME = UserViews.POLITICIAN_RANKING_VIEW_NAME;

	/** The Constant OVERVIEW. */
	private static final String OVERVIEW = "Overview:";

	/** The Constant PAGE_VISIT_HISTORY. */
	private static final String PAGE_VISIT_HISTORY = "Page Visit History:";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The admin chart data manager. */
	@Autowired
	private transient AdminChartDataManager adminChartDataManager;


	/** The application manager. */
	@Autowired
	private transient ApplicationManager applicationManager;


	/** The chart data manager. */
	@Autowired
	private transient ChartDataManager chartDataManager;


	/** The data series factory. */
	@Autowired
	private transient PartyDataSeriesFactory dataSeriesFactory;



	/** The grid factory. */
	@Autowired
	private transient GridFactory gridFactory;

	/** The menu item factory. */
	@Autowired
	private transient MenuItemFactory menuItemFactory;


	/** The politician data source. */
	private BeanItemContainer<ViewRiksdagenPolitician> politicianDataSource;


	/**
	 * Instantiates a new politician ranking view.
	 *
	 * @param context
	 *            the context
	 */
	public PoliticianRankingView(final ApplicationContext context) {
		super(context.getBeansOfType(PageModeContentFactory.class),NAME);
	}


	/**
	 * Creates the chart time series all.
	 *
	 * @return the data series
	 */
	protected DataSeries createChartTimeSeriesAll() {
		return dataSeriesFactory.createPartyChartTimeSeriesAll();
	}


	/**
	 * Creates the chart time series current.
	 *
	 * @return the data series
	 */
	protected DataSeries createChartTimeSeriesCurrent() {
		return dataSeriesFactory.createPartyChartTimeSeriesCurrent();
	}

	/**
	 * Creates the description.
	 *
	 * @return the text area
	 */
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
	 * Creates the extra chart layout.
	 *
	 * @return the layout
	 */
	protected Layout createExtraChartLayout() {
		return null;
	}

	/**
	 * Creates the menu bar.
	 */
	protected void createMenuBar() {
		menuItemFactory.createPoliticianRankingMenuBar(getBarmenu());
	}

	/**
	 * Creates the table.
	 *
	 * @return the component
	 */
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

	@Override
	public final void enter(final ViewChangeEvent event) {

		createMenuBar();

		final String parameters = event.getParameters();

		final VerticalLayout panelContent = new VerticalLayout();
		panelContent.setSizeFull();
		panelContent.setMargin(true);

		if (StringUtils.isEmpty(parameters) || parameters.contains(PageMode.OVERVIEW.toString())) {


			panelContent.addComponent(createDescription());

			getPanel().setCaption(OVERVIEW + event.getParameters());

		} else 	if (parameters.contains(PageMode.DATAGRID.toString())) {

			panelContent.addComponent(createTable());

			getPanel().setCaption(DATAGRID + event.getParameters());

		} else 	if (parameters.contains(PageMode.CHARTS.toString())) {

			final Layout chartLayout = new HorizontalLayout();
			chartLayout.setSizeFull();


			final Component chartPanelAll = chartDataManager.createChartPanel(createChartTimeSeriesAll(),"All");
			if (chartPanelAll!=null) {
				chartLayout.addComponent(chartPanelAll);
			}

			final Component chartPanelCurrent = chartDataManager.createChartPanel(createChartTimeSeriesCurrent(),"Current");
			if (chartPanelCurrent!=null) {
				chartLayout.addComponent(chartPanelCurrent);
			}
			panelContent.addComponent(chartLayout);

			final Layout extraChartLayout = createExtraChartLayout();
			if (extraChartLayout != null) {
				panelContent.addComponent(extraChartLayout);
			}

			getPanel().setCaption(CHARTS + event.getParameters());

		} else if (parameters.contains(PageMode.PAGEVISITHISTORY.toString())) {

			panelContent.addComponent(adminChartDataManager.createApplicationActionEventPageModeDailySummaryChart(getName()));

			getPanel().setCaption(PAGE_VISIT_HISTORY + event.getParameters());

		}

		getPanel().setContent(panelContent);

		pageActionEventHelper.createPageEvent(getViewAction(), ApplicationEventGroup.USER, getName(), parameters, null);



	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	protected String getName() {
		return NAME;
	}




	/**
	 * Gets the view action.
	 *
	 * @return the view action
	 */
	protected ViewAction getViewAction() {
		return ViewAction.VISIT_POLITICIAN_RANKING_VIEW;
	}

	/**
	 * Post construct.
	 */
	@PostConstruct
	public void postConstruct() {
		setSizeFull();
		createBasicLayoutWithPanelAndFooter(NAME);
	}




}