/*
 * Copyright 2010-2024 James Pether Sörling
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

import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData;
import com.hack23.cia.model.internal.application.data.impl.ViewWorldbankIndicatorDataCountrySummary;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Interface ChartDataManager.
 */
@FunctionalInterface
public interface WorldIndicatorChartDataManager {


	/**
	 * Creates the indicator chart.
	 *
	 * @param content
	 *            the content
	 * @param list
	 *            the list
	 * @param summary
	 *            the summary
	 */
	void createIndicatorChart(AbstractOrderedLayout content,List<WorldBankData> list, ViewWorldbankIndicatorDataCountrySummary summary);

}
