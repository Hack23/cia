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

import java.util.Map;

import javax.annotation.PostConstruct;

import org.dussan.vaadin.dcharts.data.DataSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee_;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.CommitteeDataSeriesFactory;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.PartyDataSeriesFactory;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.PageModeContentFactory;
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
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@VaadinView(value = CommitteeRankingView.NAME, cached = true)
public final class CommitteeRankingView extends AbstractRankingView {

	/** The Constant CURRENT_PARTIES_HEADCOUNT. */
	private static final String CURRENT_PARTIES_HEADCOUNT = "Current Parties, headcount";

	/** The Constant COMMITTEE_RANKING_BY_TOPIC_DESCRIPTION. */
	private static final String COMMITTEE_RANKING_BY_TOPIC_DESCRIPTION = "Time served in Committee:ALL:CURRENT:" + "\nPoliticans served in Committee:ALL:CURRENT:"
			+ "\nTop document author NR:ALL:YEAR:CURRENT:*FILTER:DocumnetType"
			+ "\nTop document author SIZE:YEAR:ALL:CURRENT:*FILTER:DocumnetType"

			+ "\nTop decisions NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"
			+ "\nTop Votes NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"

			+ "\nTop vote winner NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"
			+ "\nSearch by name";

	/** The Constant COMMITTEE_RANKING_BY_TOPIC. */
	private static final String COMMITTEE_RANKING_BY_TOPIC = "Committee Ranking by topic";

	/** The Constant ALL_PARTIES_TOTAL_DAYS_SERVED. */
	private static final String ALL_PARTIES_TOTAL_DAYS_SERVED = "All Parties, total days served";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = UserViews.COMMITTEE_RANKING_VIEW_NAME;

	/** The application manager. */
	@Autowired
	private transient ApplicationManager applicationManager;

	/** The chart data manager. */
	@Autowired
	private transient ChartDataManager chartDataManager;

	/** The menu item factory. */
	@Autowired
	private transient MenuItemFactory menuItemFactory;

	/** The data series factory. */
	@Autowired
	private transient CommitteeDataSeriesFactory dataSeriesFactory;

	/** The data series factory2. */
	@Autowired
	private transient PartyDataSeriesFactory dataSeriesFactory2;

	/** The grid factory. */
	@Autowired
	private transient GridFactory gridFactory;

	private final transient Map<String, PageModeContentFactory> pageModeContentFactoryMap;

	/**
	 * Instantiates a new committee ranking view.
	 *
	 * @param context
	 *            the context
	 */
	public CommitteeRankingView(final ApplicationContext context) {
		super();
		pageModeContentFactoryMap = context.getBeansOfType(PageModeContentFactory.class);

	}


	/**
	 * Post construct.
	 */
	@PostConstruct
	public void postConstruct() {
		setSizeFull();
		createBasicLayoutWithPanelAndFooter(NAME);
	}

	@Override
	protected void createMenuBar() {
		menuItemFactory.createCommitteeeRankingMenuBar(getBarmenu());
	}

	@Override
	protected TextArea createDescription() {
		final TextArea totalCommitteeRankinglistLabel = new TextArea(COMMITTEE_RANKING_BY_TOPIC,
				COMMITTEE_RANKING_BY_TOPIC_DESCRIPTION);
		totalCommitteeRankinglistLabel.setSizeFull();
		return totalCommitteeRankinglistLabel;
	}

	@Override
	protected Layout createExtraChartLayout() {
		final Layout chartLayout = new HorizontalLayout();
		chartLayout.setSizeFull();

		final Component chartPanelAll = chartDataManager.createChartPanel(
				dataSeriesFactory.createChartTimeSeriesTotalDaysServedCommitteeByParty(),
				ALL_PARTIES_TOTAL_DAYS_SERVED);
		if (chartPanelAll != null) {
			chartLayout.addComponent(chartPanelAll);
		}

		final Component chartPanelCurrent = chartDataManager.createChartPanel(
				dataSeriesFactory2.createChartTimeSeriesCurrentCommitteeByParty(), CURRENT_PARTIES_HEADCOUNT);
		if (chartPanelCurrent != null) {
			chartLayout.addComponent(chartPanelCurrent);
		}

		return chartLayout;
	}

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

	@Override
	protected DataSeries createChartTimeSeriesAll() {
		return dataSeriesFactory.createCommitteeChartTimeSeriesAll();
	}

	@Override
	protected DataSeries createChartTimeSeriesCurrent() {
		return dataSeriesFactory.createCommitteeChartTimeSeriesCurrent();
	}

	@Override
	protected String getName() {
		return NAME;
	}

	@Override
	protected ViewAction getViewAction() {
		return ViewAction.VISIT_COMMITTEE_RANKING_VIEW;
	}

}