/*
 * Copyright 2010-2025 James Pether Sörling
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
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api;

import java.util.List;
import java.util.Map;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.web.widgets.charts.SankeyChart;
import com.vaadin.ui.TextArea;

/**
 * The Interface DecisionFlowChartManager.
 */
public interface DecisionFlowChartManager {

	/**
	 * Creates the all decision flow.
	 *
	 * @param committeeMap
	 *            the committee map
	 * @param rm
	 *            the rm
	 * @return the sankey chart
	 */
	SankeyChart createAllDecisionFlow(Map<String, List<ViewRiksdagenCommittee>> committeeMap, String rm);

	/**
	 * Creates the committee decision flow.
	 *
	 * @param viewRiksdagenCommittee
	 *            the view riksdagen committee
	 * @param committeeMap
	 *            the committee map
	 * @param rm
	 *            the rm
	 * @return the sankey chart
	 */
	SankeyChart createCommitteeDecisionFlow(ViewRiksdagenCommittee viewRiksdagenCommittee,
			Map<String, List<ViewRiksdagenCommittee>> committeeMap, String rm);

	/**
	 * Creates the committeee decision summary.
	 *
	 * @param committeeMap the committee map
	 * @param rm           the rm
	 * @return the text area
	 */
	TextArea createCommitteeeDecisionSummary(Map<String, List<ViewRiksdagenCommittee>> committeeMap, String rm);

	/**
	 * Creates the committeee decision summary.
	 *
	 * @param viewRiksdagenCommittee the view riksdagen committee
	 * @param rm the rm
	 * @return the text area
	 */
	TextArea createCommitteeeDecisionSummary(ViewRiksdagenCommittee viewRiksdagenCommittee, String rm);

}
