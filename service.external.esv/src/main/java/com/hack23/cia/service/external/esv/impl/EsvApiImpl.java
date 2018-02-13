/*
 * Copyright 2010 James Pether Sörling
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
package com.hack23.cia.service.external.esv.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hack23.cia.service.external.esv.api.EsvApi;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;

/**
 * The Class EsvApiImpl.
 */
@Component
final class EsvApiImpl implements EsvApi {

	/** The Constant NO_MINISTRY. */
	private static final String NO_MINISTRY = "Inget departement";

	@Autowired
	private EsvExcelReader esvExcelReader;

	private Map<Integer, List<GovernmentBodyAnnualSummary>> allData;

	/**
	 * Instantiates a new esv api impl.
	 */
	public EsvApiImpl() {
		super();
	}

	@Override
	public synchronized Map<Integer, List<GovernmentBodyAnnualSummary>> getData() {
	  if (allData == null) {
		allData = esvExcelReader.getDataPerMinistry(null);
	  }		
	  return allData;
	}

	@Override
	public Map<Integer, List<GovernmentBodyAnnualSummary>> getDataPerMinistry(final String name) {
		return esvExcelReader.getDataPerMinistry(name);
	}

	@Override
	public List<GovernmentBodyAnnualSummary> getDataPerMinistryAndYear(final String name, final int year) {
		final Map<Integer, List<GovernmentBodyAnnualSummary>> map = getDataPerMinistry(name);

		if (map.containsKey(year)) {
			return map.get(year);
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<String> getGovernmentBodyNames() {
		final Set<String> governmentBodyNameSet = new HashSet<>();

		final Map<Integer, List<GovernmentBodyAnnualSummary>> data = getData();

		for (final List<GovernmentBodyAnnualSummary> list : data.values()) {
			for (final GovernmentBodyAnnualSummary governmentBodyAnnualSummary : list) {
				if (!governmentBodyNameSet.contains(governmentBodyAnnualSummary.getName())
						&& governmentBodyAnnualSummary.getHeadCount() > 0) {
					governmentBodyNameSet.add(governmentBodyAnnualSummary.getName());
				}
			}
		}
		return new ArrayList<>(governmentBodyNameSet);
	}

	@Override
	public List<String> getGovernmentBodyNames(final String ministry) {
		Map<String, Set<String>> governmentBodyNameSetMinistryMap = new HashMap<>();

		final Set<String> governmentBodyNameSetMapEntry = new HashSet<>();
		governmentBodyNameSetMinistryMap.put(ministry, governmentBodyNameSetMapEntry);

		final Map<Integer, List<GovernmentBodyAnnualSummary>> data = getData();

		for (final List<GovernmentBodyAnnualSummary> list : data.values()) {
			for (final GovernmentBodyAnnualSummary governmentBodyAnnualSummary : list) {
				if (ministry.equalsIgnoreCase(governmentBodyAnnualSummary.getMinistry())
						&& !governmentBodyNameSetMapEntry.contains(governmentBodyAnnualSummary.getName())
						&& governmentBodyAnnualSummary.getHeadCount() > 0) {
					governmentBodyNameSetMapEntry.add(governmentBodyAnnualSummary.getName());
				}
			}
		}
		return new ArrayList<>(governmentBodyNameSetMinistryMap.get(ministry));
	}

	@Override
	public List<String> getMinistryNames() {
		final Set<String> ministryNameSet = new HashSet<>();

		final Map<Integer, List<GovernmentBodyAnnualSummary>> data = getData();

		for (final List<GovernmentBodyAnnualSummary> list : data.values()) {
			for (final GovernmentBodyAnnualSummary governmentBodyAnnualSummary : list) {
				if (!ministryNameSet.contains(governmentBodyAnnualSummary.getMinistry())
						&& governmentBodyAnnualSummary.getHeadCount() > 0
						&& !NO_MINISTRY.equalsIgnoreCase(governmentBodyAnnualSummary.getMinistry())) {
					ministryNameSet.add(governmentBodyAnnualSummary.getMinistry());
				}
			}
		}
		return new ArrayList<>(ministryNameSet);
	}

	@Override
	public Map<Integer, GovernmentBodyAnnualSummary> getDataPerGovernmentBody(final String name) {
		return esvExcelReader.getDataPerGovernmentBody(name);
	}

}
