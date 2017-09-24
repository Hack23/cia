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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.service.api.ApplicationManager;
import com.vaadin.ui.Grid;

/**
 * The Class TableFactoryImpl.
 */
@Service
public final class TableFactoryImpl implements TableFactory {

	/** The Constant VOTES. */
	private static final String VOTES = "Votes";

	/** The Constant COMMITTEE_PROPOSAL_SIZE. */
	private static final String COMMITTEE_PROPOSAL_SIZE = "committeeProposalSize";

	/** The Constant DOCUMENT_STATUS. */
	private static final String DOCUMENT_STATUS = "documentStatus";

	/** The Constant DOCUMENT_CONTENT. */
	private static final String DOCUMENT_CONTENT = "documentContent";

	/** The Constant DOCUMENT_ELEMENT. */
	private static final String DOCUMENT_ELEMENT = "DocumentElement";

	/** The Constant PARLIAMENT_MEMBER. */
	private static final String PARLIAMENT_MEMBER = "Parliament member";

	/** The Constant COLUMN_MISSING. */
	private static final String COLUMN_MISSING = "Missing";

	/** The Constant COLUMN_SIZE. */
	private static final String COLUMN_SIZE = "Size";

	/** The Constant COLUMN_NAME. */
	private static final String COLUMN_NAME = "Name";

	/** The Constant FIRST_AND_ONLY. */
	private static final int FIRST_AND_ONLY = 0;

	/** The Constant ZERO_MISSING. */
	private static final String ZERO_MISSING = Long.toString(0);

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/**
	 * Instantiates a new table factory impl.
	 */
	public TableFactoryImpl() {
		super();
	}

	@Override
	public Grid createDataSummaryTable() {
//		final Table summaryTable = new Table();
//		summaryTable.addContainerProperty(COLUMN_NAME, String.class, null);
//		summaryTable.addContainerProperty(COLUMN_SIZE, String.class, null);
//		summaryTable.addContainerProperty(COLUMN_MISSING, String.class, null);
//
//		final DataContainer<DataSummary, String> dataContainer = applicationManager.getDataContainer(DataSummary.class);
//
//		final List<DataSummary> all = dataContainer.getAll();
//
//		if (!all.isEmpty()) {
//			final DataSummary dataSummary = all.get(FIRST_AND_ONLY);
//
//			int indexNr = 1;
//
//			summaryTable.addItem(new Object[] { PARLIAMENT_MEMBER, Long.toString(dataSummary.personSize), ZERO_MISSING },indexNr);
//			indexNr = indexNr + 1;
//
//
//			summaryTable.addItem(new Object[] { DOCUMENT_ELEMENT, Long.toString(dataSummary.documentElementSize), ZERO_MISSING },indexNr);
//			indexNr = indexNr + 1;
//
//
//			summaryTable.addItem(new Object[] { DOCUMENT_CONTENT, Long.toString(dataSummary.documentContentSize),Long.toString(dataSummary.documentElementSize - dataSummary.documentContentSize) },indexNr);
//			indexNr = indexNr + 1;
//
//
//			summaryTable.addItem(new Object[] { DOCUMENT_STATUS, Long.toString(dataSummary.documentStatusSize),Long.toString(dataSummary.documentElementSize - dataSummary.documentStatusSize) },indexNr);
//			indexNr = indexNr + 1;
//
//
//			summaryTable.addItem(new Object[] { COMMITTEE_PROPOSAL_SIZE,Long.toString(dataSummary.committeeProposalSize), ZERO_MISSING }, indexNr);
//			indexNr = indexNr + 1;
//
//
//			summaryTable.addItem(new Object[] { VOTES, Long.toString(dataSummary.voteSize),Long.toString(dataSummary.totalBallotVotes - dataSummary.voteSize) },indexNr);
//			summaryTable.setSizeFull();
//
//		}
//
//		return summaryTable;
		return new Grid();
	}

}
