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
 *	$Id: CommitteeRankingView.java 6118 2015-07-31 17:41:55Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/citizen-intelligence-agency/src/main/java/com/hack23/cia/web/impl/ui/application/views/user/committee/CommitteeRankingView.java $
*/
package com.hack23.cia.web.impl.ui.application.views.user.committee;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.TableColumnResizeListener;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class CommitteeRankingView.
 */
@Service
@Scope("prototype")
@VaadinView(value = CommitteeRankingView.NAME, cached = true)
public final class CommitteeRankingView extends AbstractRankingView {

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



	/**
	 * Post construct.
	 */
	@PostConstruct
	public void postConstruct() {
		setSizeFull();
		createBasicLayoutWithPanelAndFooter(NAME);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView#createMenuBar()
	 */
	@Override
	protected void createMenuBar() {
		menuItemFactory.createCommitteeeRankingMenuBar(getBarmenu());
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView
	 * #createTable()
	 */
	@Override
	protected Component createTable() {
		final DataContainer<ViewRiksdagenCommittee, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenCommittee.class);

		final Table table = new Table("Committees");
		// Define two columns for the built-in container
		table.addContainerProperty("Rank", Integer.class, null);
		table.addContainerProperty("Name", String.class, null);
		table.addContainerProperty("Org Code", String.class, null);
		table.addContainerProperty("Current Members", Long.class, null);
		table.addContainerProperty("Average Member duration", String.class, null);
		table.addContainerProperty("Total Politician Days", Long.class, null);
		table.addContainerProperty("Total Members", Long.class, null);
		table.addContainerProperty("First Assignment Date", Date.class,
				null);
		table.addContainerProperty("Last Assignment Date", Date.class, null);
		table.addContainerProperty("Active", Boolean.class, null);
		table.addContainerProperty("View details", Link.class, null);

		int rank = 1;
		for (final ViewRiksdagenCommittee data : dataContainer.getAll()) {


			// Add a row the hard way
			final Object newItemId = table.addItem();
			final Item row1 = table.getItem(newItemId);
			row1.getItemProperty("Rank").setValue(rank++);
			row1.getItemProperty("Name").setValue(
					data.getEmbeddedId().getDetail());
			row1.getItemProperty("Org Code").setValue(
					StringUtils
					.defaultString(data.getEmbeddedId().getOrgCode()));

			row1.getItemProperty("Current Members").setValue(data.getCurrentMemberSize());

			row1.getItemProperty("Average Member duration").setValue(convertToYearsString(data.getTotalDaysServed() / data.getTotalAssignments()));


			row1.getItemProperty("Total Politician Days").setValue(data.getTotalDaysServed());

			row1.getItemProperty("Total Members").setValue(
					data.getTotalAssignments());
			row1.getItemProperty("First Assignment Date").setValue(
					data.getFirstAssignmentDate());
			row1.getItemProperty("Last Assignment Date").setValue(
					data.getLastAssignmentDate());

			row1.getItemProperty("Active").setValue(data.isActive());
			row1.getItemProperty("View details").setValue(
					pageLinkFactory.addCommitteePageLink(data));

		}

		// Allow selecting items from the table.
		table.setSelectable(true);
		table.setSizeFull();

		// Send changes in selection immediately to server.
		table.setImmediate(true);

		table.addColumnResizeListener(new TableColumnResizeListener(table));

		table.setColumnCollapsingAllowed(true);

		table.setPageLength(table.size());
		return table;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView
	 * #createDescription()
	 */
	@Override
	protected TextArea createDescription() {
		final TextArea totalCommitteeRankinglistLabel = new TextArea(
				"Committee Ranking by topic",
				"Time served in Committee:ALL:CURRENT:"
						+ "\nPoliticans served in Committee:ALL:CURRENT:"
						+ "\nTop document author NR:ALL:YEAR:CURRENT:*FILTER:DocumnetType"
						+ "\nTop document author SIZE:YEAR:ALL:CURRENT:*FILTER:DocumnetType"

						+ "\nTop decisions NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"
						+ "\nTop Votes NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"

						+ "\nTop vote winner NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"
						+ "\nSearch by name");
		totalCommitteeRankinglistLabel.setSizeFull();
		return totalCommitteeRankinglistLabel;
	}


	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView#createChartTimeSeriesAll()
	 */
	@Override
	protected DataSeries createChartTimeSeriesAll() {
		DataSeries dataSeries = new DataSeries();

		final DataContainer<ViewRiksdagenCommittee, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenCommittee.class);

		for (final ViewRiksdagenCommittee data : dataContainer.getAll()) {
			dataSeries =dataSeries.newSeries().add(data.getEmbeddedId().getDetail(),data.getTotalAssignments());
		}
		return dataSeries;
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView#createChartTimeSeriesCurrent()
	 */
	@Override
	protected DataSeries createChartTimeSeriesCurrent() {
		DataSeries dataSeries = new DataSeries();

		final DataContainer<ViewRiksdagenCommittee, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenCommittee.class);

		for (final ViewRiksdagenCommittee data : dataContainer.getAll()) {
			if (data.isActive()) {
				dataSeries =dataSeries.newSeries().add(data.getEmbeddedId().getDetail(),data.getCurrentMemberSize());
			}
		}
		return dataSeries;
	}


	/**
	 * Creates the chart time series current committee.
	 *
	 * @return the data series
	 */
	DataSeries createChartTimeSeriesCurrentCommittee() {
		DataSeries dataSeries = new DataSeries();

		final DataContainer<ViewRiksdagenParty, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenParty.class);

		final DataContainer<ViewRiksdagenPartySummary, String> partySummarydataContainer = applicationManager
				.getDataContainer(ViewRiksdagenPartySummary.class);

		partySummarydataContainer.getAll();

		for (final ViewRiksdagenParty data : dataContainer.getAll()) {
			final ViewRiksdagenPartySummary summary = partySummarydataContainer.load(data.getPartyId());
			if (summary != null && summary.isActive()) {

				dataSeries =dataSeries.newSeries().add(data.getPartyName(),summary.getTotalActiveCommittee());
			}
		}
		return dataSeries;
	}


	/**
	 * Creates the chart time series total days served committee.
	 *
	 * @return the data series
	 */
	DataSeries createChartTimeSeriesTotalDaysServedCommittee() {
		DataSeries dataSeries = new DataSeries();

		final DataContainer<ViewRiksdagenParty, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenParty.class);

		final DataContainer<ViewRiksdagenPartySummary, String> partySummarydataContainer = applicationManager
				.getDataContainer(ViewRiksdagenPartySummary.class);

		partySummarydataContainer.getAll();

		for (final ViewRiksdagenParty data : dataContainer.getAll()) {
			final ViewRiksdagenPartySummary summary = partySummarydataContainer.load(data.getPartyId());
			if (summary != null && summary.isActive()) {

				dataSeries =dataSeries.newSeries().add(data.getPartyName(),summary.getTotalDaysServedCommittee());
			}
		}
		return dataSeries;
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView#createExtraChartLayout()
	 */
	@Override
	protected Layout createExtraChartLayout() {
		final Layout chartLayout = new HorizontalLayout();
		chartLayout.setSizeFull();


		final com.vaadin.ui.Component chartPanelAll = chartDataManager.createChartPanel(createChartTimeSeriesTotalDaysServedCommittee(),"All Parties, total days served");
		if (chartPanelAll!=null) {
			chartLayout.addComponent(chartPanelAll);
		}

		final com.vaadin.ui.Component chartPanelCurrent = chartDataManager.createChartPanel(createChartTimeSeriesCurrentCommittee(),"Current Parties, headcount");
		if (chartPanelCurrent!=null) {
			chartLayout.addComponent(chartPanelCurrent);
		}

		return chartLayout;
	}


}