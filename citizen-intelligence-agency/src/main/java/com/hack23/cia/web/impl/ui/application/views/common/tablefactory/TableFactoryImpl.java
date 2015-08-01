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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.DataSummary;
import com.vaadin.ui.Table;

@Component
public final class TableFactoryImpl implements TableFactory {

	/** The application manager. */
	@Autowired
	@Qualifier("ApplicationManager")
	private transient ApplicationManager applicationManager;

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

}
