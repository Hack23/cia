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
package com.hack23.cia.web.impl.ui.application.views.common.tablefactory;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.DataSummary;
import com.hack23.cia.web.impl.ui.application.views.common.TableColumnResizeListener;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.PageLinkFactory;
import com.vaadin.data.Item;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;

@Service
public final class TableFactoryImpl implements TableFactory {

	/** The Constant DAYS_PER_STANDARD_YEAR. */
	private static final long DAYS_PER_STANDARD_YEAR = 365L;

	/** The application manager. */
	@Autowired
	@Qualifier("ApplicationManager")
	private transient ApplicationManager applicationManager;

	/** The page link factory. */
	@Autowired
	private transient PageLinkFactory pageLinkFactory;

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.tablefactory.TableFactory#createDataSummaryTable()
	 */
	@Override
	public Table createDataSummaryTable() {
		final Table summaryTable = new Table();
		summaryTable.addContainerProperty("Name",
				String.class, null);
		summaryTable.addContainerProperty("Size",
				String.class, null);
		summaryTable.addContainerProperty("Missing",
				String.class, null);


		final DataContainer<DataSummary, String> dataContainer = applicationManager
				.getDataContainer(DataSummary.class);

		final List<DataSummary> all = dataContainer.getAll();

		if (!all.isEmpty()) {
			final DataSummary dataSummary = all.get(0);


			summaryTable.addItem(new Object[] { "Parliament member",
					Long.toString(dataSummary.personSize), Long.toString(0) }, Integer.valueOf(1));

			summaryTable.addItem(new Object[] { "DocumentElement",
					Long.toString(dataSummary.documentElementSize), Long.toString(0) }, Integer.valueOf(2));
			summaryTable.addItem(new Object[] {
					"documentContent",
					Long.toString(dataSummary.documentContentSize),
					Long.toString(dataSummary.documentElementSize
							- dataSummary.documentContentSize) },
					Integer.valueOf(3));

			summaryTable.addItem(new Object[] {
					"documentStatus",
					Long.toString(dataSummary.documentStatusSize),
					Long.toString(dataSummary.documentElementSize
					- dataSummary.documentStatusSize)} ,
					Integer.valueOf(4));
			summaryTable.addItem(new Object[] { "committeeProposalSize",
					Long.toString(dataSummary.committeeProposalSize), Long.toString(0)},
					Integer.valueOf(5));
			summaryTable.addItem(new Object[] { "Votes", Long.toString(dataSummary.voteSize),
					Long.toString(dataSummary.totalBallotVotes - dataSummary.voteSize) },
					Integer.valueOf(6));
			summaryTable.setSizeFull();

		}

		return summaryTable;
	}


	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.tablefactory.TableFactory#createCommitteesTable()
	 */
	@Override
	public Table createCommitteesTable() {
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

		return configureTable(table);
	}


	/**
	 * Configure table.
	 *
	 * @param table
	 *            the table
	 * @return the table
	 */
	private static Table configureTable(final Table table) {
		table.setSelectable(true);
		table.setSizeFull();
		table.setImmediate(true);
		table.addColumnResizeListener(new TableColumnResizeListener(table));
		table.setColumnCollapsingAllowed(true);
		table.setPageLength(table.size());
		return table;
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.tablefactory.TableFactory#createMinistryTable()
	 */
	@Override
	public Table createMinistryTable() {
		final DataContainer<ViewRiksdagenMinistry, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenMinistry.class);

		final Table table = new Table("Ministries");
		// Define two columns for the built-in container
		table.addContainerProperty("Rank", Integer.class, null);
		table.addContainerProperty("Name", String.class, null);
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
		for (final ViewRiksdagenMinistry data : dataContainer.getAll()) {


			// Add a row the hard way
			final Object newItemId = table.addItem();
			final Item row1 = table.getItem(newItemId);
			row1.getItemProperty("Rank").setValue(rank++);
			row1.getItemProperty("Name").setValue(data.getNameId());

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
					pageLinkFactory.addMinistryPageLink(data));

		}

		return configureTable(table);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.tablefactory.TableFactory#createPartiesTable()
	 */
	@Override
	public Table createPartiesTable() {
		final DataContainer<ViewRiksdagenParty, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenParty.class);


		final Table table = new Table("Parties");
		// Define two columns for the built-in container
		table.addContainerProperty("Rank", Integer.class, null);
		table.addContainerProperty("Name", String.class, null);
		table.addContainerProperty("Party Code", String.class, null);
		table.addContainerProperty("Total Members", Long.class, null);
		table.addContainerProperty("Current Name Registered Date",
				Date.class, null);
		table.addContainerProperty("View details", Link.class, null);

		int rank = 1;
		for (final ViewRiksdagenParty data : dataContainer.getAll()) {


			// Add a row the hard way
			final Object newItemId = table.addItem();
			final Item row1 = table.getItem(newItemId);
			row1.getItemProperty("Rank").setValue(rank++);
			row1.getItemProperty("Name").setValue(data.getPartyName());
			row1.getItemProperty("Party Code").setValue(data.getPartyId());
			row1.getItemProperty("Total Members").setValue(data.getHeadCount());
			row1.getItemProperty("Current Name Registered Date").setValue(
					data.getRegisteredDate());

			row1.getItemProperty("View details").setValue(
					pageLinkFactory.addPartyPageLink(data));

		}

		return configureTable(table);
	}

	/**
	 * Convert to years string.
	 *
	 * @param totalDays
	 *            the total days
	 * @return the string
	 */
	private static String convertToYearsString(final long totalDays) {
		final long years = totalDays / DAYS_PER_STANDARD_YEAR;
		final long days = totalDays - years * DAYS_PER_STANDARD_YEAR;

		return Long.toString(years) + " Years " + Long.toString(days) + " days";
	}


}
