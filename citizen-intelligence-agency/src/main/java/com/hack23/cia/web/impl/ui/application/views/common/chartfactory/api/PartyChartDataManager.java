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

import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Interface ChartDataManager.
 */
public interface PartyChartDataManager {


	/**
	 * Creates the party age chart.
	 *
	 * @param content
	 *            the content
	 */
	void createPartyAgeChart(AbstractOrderedLayout content);

	/**
	 * Creates the party gender chart.
	 *
	 * @param content
	 *            the content
	 */
	void createPartyGenderChart(AbstractOrderedLayout content);

	/**
	 * Creates the party line chart.
	 *
	 * @param content
	 *            the content
	 * @param partyId
	 *            the party id
	 */
	void createPartyLineChart(AbstractOrderedLayout content,String partyId);

	/**
	 * Creates the party winner chart.
	 *
	 * @param content
	 *            the content
	 */
	void createPartyWinnerChart(AbstractOrderedLayout content);

}
