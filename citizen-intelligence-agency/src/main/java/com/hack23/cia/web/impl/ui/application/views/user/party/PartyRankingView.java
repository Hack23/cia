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
package com.hack23.cia.web.impl.ui.application.views.user.party;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary_;
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
 * The Class PartyRankingView.
 */
@Service
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@VaadinView(value = PartyRankingView.NAME, cached = true)
public final class PartyRankingView extends AbstractRankingView {

	/** The Constant CHARTS. */
	private static final String CHARTS = "Charts:";

	/** The Constant DATAGRID. */
	private static final String DATAGRID = "Datagrid:";

	/** The Constant NAME. */
	public static final String NAME = UserViews.PARTY_RANKING_VIEW_NAME;

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


	/**
	 * Instantiates a new party ranking view.
	 *
	 * @param context
	 *            the context
	 */
	public PartyRankingView(final ApplicationContext context) {
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
		final TextArea totalpartytoplistLabel = new TextArea(
				"Party Ranking by topic",
				"Time served in Parliament:ALL:CURRENT:"
						+ "\nTime served in Committees:ALL:CURRENT:"
						+ "\nTime served in Government:ALL:CURRENT:"
						+ "\nTop document author NR:ALL:YEAR:CURRENT:*FILTER:DocumnetType"
						+ "\nTop document author SIZE:YEAR:ALL:CURRENT:*FILTER:DocumnetType"

						+ "\nTop votes NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"
						+ "\nTop vote winner NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"
						+ "\nTop vote party rebel NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"
						+ "\nTop vote presence NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"
						+ "\nSearch by name");
		totalpartytoplistLabel.setSizeFull();
		return totalpartytoplistLabel;
	}


	/**
	 * Creates the extra chart layout.
	 *
	 * @return the layout
	 */
	protected Layout createExtraChartLayout() {
		final Layout chartLayout = new HorizontalLayout();
		chartLayout.setSizeFull();


		final Component chartPanelAll = chartDataManager.createChartPanel(dataSeriesFactory.createChartTimeSeriesCurrentGovernmentByParty(),"Current Government");
		if (chartPanelAll!=null) {
			chartLayout.addComponent(chartPanelAll);
		}

		final Component chartPanelCurrent = chartDataManager.createChartPanel(dataSeriesFactory.createChartTimeSeriesCurrentCommitteeByParty(),"Current Committee");
		if (chartPanelCurrent!=null) {
			chartLayout.addComponent(chartPanelCurrent);
		}

		return chartLayout;
	}

	/**
	 * Creates the menu bar.
	 */
	protected void createMenuBar() {
		menuItemFactory.createPartyRankingMenuBar(getBarmenu());
	}

	/**
	 * Creates the table.
	 *
	 * @return the component
	 */
	protected Component createTable() {
			final DataContainer<ViewRiksdagenPartySummary, String> dataContainer = applicationManager
					.getDataContainer(ViewRiksdagenPartySummary.class);

			final BeanItemContainer<ViewRiksdagenPartySummary> politicianDocumentDataSource = new BeanItemContainer<>(
					ViewRiksdagenPartySummary.class, dataContainer.getAllOrderBy(ViewRiksdagenPartySummary_.currentAssignments));

			return gridFactory.createBasicBeanItemGrid(politicianDocumentDataSource, "Parties",
					new String[] { "party", "active", "firstAssignmentDate", "lastAssignmentDate", "currentAssignments",
							"totalAssignments", "totalDaysServed", "activeEu", "totalActiveEu", "totalDaysServedEu",
							"activeGovernment", "totalActiveGovernment", "totalDaysServedGovernment", "activeCommittee",
							"totalActiveCommittee", "totalDaysServedCommittee", "activeParliament", "totalActiveParliament",
							"totalDaysServedParliament" },
					null, "party", new PageItemPropertyClickListener(UserViews.PARTY_VIEW_NAME, "party"), null);
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
		return ViewAction.VISIT_PARTY_RANKING_VIEW;
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