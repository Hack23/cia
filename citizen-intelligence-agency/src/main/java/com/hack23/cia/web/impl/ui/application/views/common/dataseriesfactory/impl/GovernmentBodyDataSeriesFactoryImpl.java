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
package com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.impl;

import java.util.List;
import java.util.Map;

import org.dussan.vaadin.dcharts.data.DataSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.service.external.esv.api.EsvApi;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api.GovernmentBodyDataSeriesFactory;

/**
 * The Class GovernmentBodyDataSeriesFactoryImpl.
 */
@Service
public final class GovernmentBodyDataSeriesFactoryImpl implements GovernmentBodyDataSeriesFactory {

	@Autowired
	private EsvApi esvApi;
	
	/**
	 * Instantiates a new government body data series factory impl.
	 */
	public GovernmentBodyDataSeriesFactoryImpl() {
		super();
	}

	@Override
	public DataSeries createGovernmentBodyHeadcountTimeSeries(String name) {
		Map<Integer, GovernmentBodyAnnualSummary> dataPerGovernmentBody = esvApi.getDataPerGovernmentBody(name);
				return null;
	}

	@Override
	public DataSeries createGovernmentBodyHeadcountByMinistryTimeSeries(String name) {
		Map<Integer, List<GovernmentBodyAnnualSummary>> dataPerMinistry = esvApi.getDataPerMinistry(name);
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataSeries createGovernmentBodyHeadcountAllMinistriesTimeSeries() {
		Map<Integer, List<GovernmentBodyAnnualSummary>> data = esvApi.getData();
		return null;
	}


}
