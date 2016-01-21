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
package com.hack23.cia.web.impl.ui.application.views.user.committee;

import javax.annotation.PostConstruct;

import org.dussan.vaadin.dcharts.data.DataSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee_;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.DataSeriesFactory;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class CommitteeRankingView.
 */
@Service
@Scope(value="prototype")
@VaadinView(value = CommitteeRankingView.NAME, cached = true)
public class CommitteeRankingView extends AbstractRankingView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = UserViews.COMMITTEE_RANKING_VIEW_NAME;

	/** The application manager. */
	@Autowired
	@Qualifier("ApplicationManager")
	private transient ApplicationManager applicationManager;

	/** The chart data manager. */
	@Autowired
	private transient ChartDataManager chartDataManager;

	/** The menu item factory. */
	@Autowired
	private transient MenuItemFactory menuItemFactory;

	/** The data series factory. */
	@Autowired
	private transient DataSeriesFactory dataSeriesFactory;

	/** The grid factory. */
	@Autowired
	private transient GridFactory gridFactory;

	/**
	 * Post construct.
	 */
	@PostConstruct
	public void postConstruct() {
		setSizeFull();
		createBasicLayoutWithPanelAndFooter(NAME);
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.
	 * AbstractRankingView#createMenuBar()
	 */
	@Override
	protected void createMenuBar() {
		menuItemFactory.createCommitteeeRankingMenuBar(getBarmenu());
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.
	 * AbstractRankingView #createDescription()
	 */
	@Override
	protected TextArea createDescription() {
		final TextArea totalCommitteeRankinglistLabel = new TextArea("Committee Ranking by topic",
				"Time served in Committee:ALL:CURRENT:" + "\nPoliticans served in Committee:ALL:CURRENT:"
						+ "\nTop document author NR:ALL:YEAR:CURRENT:*FILTER:DocumnetType"
						+ "\nTop document author SIZE:YEAR:ALL:CURRENT:*FILTER:DocumnetType"

						+ "\nTop decisions NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"
						+ "\nTop Votes NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"

						+ "\nTop vote winner NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"
						+ "\nSearch by name");
		totalCommitteeRankinglistLabel.setSizeFull();
		return totalCommitteeRankinglistLabel;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.
	 * AbstractRankingView#createExtraChartLayout()
	 */
	@Override
	protected Layout createExtraChartLayout() {
		final Layout chartLayout = new HorizontalLayout();
		chartLayout.setSizeFull();

		final com.vaadin.ui.Component chartPanelAll = chartDataManager.createChartPanel(
				dataSeriesFactory.createChartTimeSeriesTotalDaysServedCommitteeByParty(),
				"All Parties, total days served");
		if (chartPanelAll != null) {
			chartLayout.addComponent(chartPanelAll);
		}

		final com.vaadin.ui.Component chartPanelCurrent = chartDataManager.createChartPanel(
				dataSeriesFactory.createChartTimeSeriesCurrentCommitteeByParty(), "Current Parties, headcount");
		if (chartPanelCurrent != null) {
			chartLayout.addComponent(chartPanelCurrent);
		}

		return chartLayout;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.
	 * AbstractRankingView#createTable()
	 */
	@Override
	protected Component createTable() {
		final DataContainer<ViewRiksdagenCommittee, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenCommittee.class);

		final BeanItemContainer<ViewRiksdagenCommittee> politicianDocumentDataSource = new BeanItemContainer<>(
				ViewRiksdagenCommittee.class, dataContainer.getAllOrderBy(ViewRiksdagenCommittee_.currentMemberSize));

		politicianDocumentDataSource.addNestedContainerProperty("embeddedId.detail");
		politicianDocumentDataSource.addNestedContainerProperty("embeddedId.orgCode");

		return gridFactory.createBasicBeanItemGrid(politicianDocumentDataSource, "Committees",
				new String[] { "embeddedId.orgCode", "embeddedId.detail", "totalDaysServed", "currentMemberSize",
						"totalAssignments", "firstAssignmentDate", "active", "lastAssignmentDate" },
				new String[] { "embeddedId" }, "embeddedId.orgCode",
				new PageItemPropertyClickListener(UserViews.COMMITTEE_VIEW_NAME, "embeddedId.orgCode"), null);
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView#createChartTimeSeriesAll()
	 */
	@Override
	protected DataSeries createChartTimeSeriesAll() {
		return dataSeriesFactory.createCommitteeChartTimeSeriesAll();
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView#createChartTimeSeriesCurrent()
	 */
	@Override
	protected DataSeries createChartTimeSeriesCurrent() {
		return dataSeriesFactory.createCommitteeChartTimeSeriesCurrent();
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
		return ViewAction.VISIT_COMMITTEE_RANKING_VIEW;
	}

}