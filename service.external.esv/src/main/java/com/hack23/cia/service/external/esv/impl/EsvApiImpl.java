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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hack23.cia.service.external.esv.api.EsvApi;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;

/**
 * The Class EsvApiImpl.
 */
@Component
final class EsvApiImpl implements EsvApi {

	private static final String NO_MINISTRY = "Inget departement";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EsvApiImpl.class);

	/** The Constant ministryNameSet. */
	private static final Set<String> ministryNameSet = new HashSet<>();

	/** The Constant governmentBodyNameSet. */
	private static final Set<String> governmentBodyNameSet = new HashSet<>();

	/** The Constant governmentBodyNameSetMinistryMap. */
	private static final Map<String,Set<String>> governmentBodyNameSetMinistryMap = new HashMap<>();
	
	/**
	 * Instantiates a new esv api impl.
	 */
	public EsvApiImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.esv.api.EsvApi#getData()
	 */
	@Override
	public Map<Integer, List<GovernmentBodyAnnualSummary>> getData() {
		return getDataPerMinistry(null);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.esv.api.EsvApi#getDataPerMinistry(java.lang.String)
	 */
	@Override
	public Map<Integer, List<GovernmentBodyAnnualSummary>> getDataPerMinistry(final String name) {
		final Map<Integer, List<GovernmentBodyAnnualSummary>> map = new TreeMap<>();
		try {
			final HSSFWorkbook myWorkBook = new HSSFWorkbook(
					EsvApiImpl.class.getResourceAsStream("/Myndighetsinformation.xls"));

			for (int sheetNr = 0; sheetNr < myWorkBook.getNumberOfSheets(); sheetNr++) {
				final HSSFSheet mySheet = myWorkBook.getSheetAt(sheetNr);

				addMinistryPerYearToMap(name, map, mySheet);
			}

			myWorkBook.close();
		} catch (

		final IOException e) {
			LOGGER.warn("Problem loading", e);
		}

		return map;
	}

	/**
	 * Adds the ministry per year to map.
	 *
	 * @param name
	 *            the name
	 * @param map
	 *            the map
	 * @param mySheet
	 *            the my sheet
	 */
	private static void addMinistryPerYearToMap(final String name, final Map<Integer, List<GovernmentBodyAnnualSummary>> map,
			final HSSFSheet mySheet) {
		if (mySheet.getSheetName().chars().allMatch(Character::isDigit)) {

			final int year = Integer.parseInt(mySheet.getSheetName());

			final List<GovernmentBodyAnnualSummary> yearList = new ArrayList<>();
			final Iterator<Row> rowIterator = mySheet.iterator();

			rowIterator.next();

			while (rowIterator.hasNext()) {
				final Row row = rowIterator.next();
				final short maxColIx = row.getLastCellNum();

				
				if (maxColIx == 10) {
					final GovernmentBodyAnnualSummary governmentBodyAnnualSummary = new GovernmentBodyAnnualSummary(
							year, row.getCell(0).toString(), getInteger(row.getCell(1).toString()),
							row.getCell(2).toString(), row.getCell(3).toString(), row.getCell(4).toString(),
							row.getCell(5).toString(), getInteger(row.getCell(6).toString()), getInteger(row.getCell(7).toString()),
							row.getCell(8).toString(), row.getCell(9).toString());

					if (name == null || name.equalsIgnoreCase(governmentBodyAnnualSummary.getMinistry())) {
						yearList.add(governmentBodyAnnualSummary);
					}

				}

			}
			map.put(year, yearList);
		}
	}
	
	/**
	 * Gets the integer.
	 *
	 * @param str
	 *            the str
	 * @return the integer
	 */
	private static int getInteger(String str) {
	    if (str == null || str.trim().length() == 0) {
	        return 0;
	    } else {
	        return Integer.parseInt(str);
	    }
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.esv.api.EsvApi#getDataPerMinistryAndYear(java.lang.String, int)
	 */
	@Override
	public List<GovernmentBodyAnnualSummary> getDataPerMinistryAndYear(final String name, final int year) {
		final Map<Integer, List<GovernmentBodyAnnualSummary>> map = getDataPerMinistry(name);

		if (map.containsKey(year)) {
			return map.get(year);
		} else {
			return new ArrayList<>();
		}
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.esv.api.EsvApi#getGovernmentBodyNames()
	 */
	@Override
	public List<String> getGovernmentBodyNames() {
		if (governmentBodyNameSet.isEmpty()) {

			final Map<Integer, List<GovernmentBodyAnnualSummary>> data = getData();

			for (final List<GovernmentBodyAnnualSummary> list : data.values()) {
				for (final GovernmentBodyAnnualSummary governmentBodyAnnualSummary : list) {
					if (!governmentBodyNameSet.contains(governmentBodyAnnualSummary.getName()) && governmentBodyAnnualSummary.getHeadCount() > 0) {
						governmentBodyNameSet.add(governmentBodyAnnualSummary.getName());
					}
				}
			}
		}
		return new ArrayList<>(governmentBodyNameSet);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.esv.api.EsvApi#getMinistryNames()
	 */
	@Override
	public List<String> getMinistryNames() {
		if (ministryNameSet.isEmpty()) {
			final Map<Integer, List<GovernmentBodyAnnualSummary>> data = getData();

			for (final List<GovernmentBodyAnnualSummary> list : data.values()) {
				for (final GovernmentBodyAnnualSummary governmentBodyAnnualSummary : list) {
					if (!ministryNameSet.contains(governmentBodyAnnualSummary.getMinistry()) && governmentBodyAnnualSummary.getHeadCount() > 0 && !NO_MINISTRY.equalsIgnoreCase(governmentBodyAnnualSummary.getMinistry())) {
						ministryNameSet.add(governmentBodyAnnualSummary.getMinistry());
					}
				}
			}
		}
		return new ArrayList<>(ministryNameSet);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.esv.api.EsvApi#getDataPerGovernmentBody(java.lang.String)
	 */
	@Override
	public Map<Integer, GovernmentBodyAnnualSummary> getDataPerGovernmentBody(String name) {
		final Map<Integer, GovernmentBodyAnnualSummary> map = new TreeMap<>();
		try {
			final HSSFWorkbook myWorkBook = new HSSFWorkbook(
					EsvApiImpl.class.getResourceAsStream("/Myndighetsinformation.xls"));

			for (int sheetNr = 0; sheetNr < myWorkBook.getNumberOfSheets(); sheetNr++) {
				final HSSFSheet mySheet = myWorkBook.getSheetAt(sheetNr);

				addDataForYearToMap(name, map, mySheet);
			}
			myWorkBook.close();
		} catch (

		final IOException e) {
			LOGGER.warn("Problem loading", e);
		}

		return map;
	}

	/**
	 * Adds the data for year to map.
	 *
	 * @param name
	 *            the name
	 * @param map
	 *            the map
	 * @param mySheet
	 *            the my sheet
	 */
	private static void addDataForYearToMap(String name, final Map<Integer, GovernmentBodyAnnualSummary> map,
			final HSSFSheet mySheet) {
		if (mySheet.getSheetName().chars().allMatch(Character::isDigit)) {

			final int year = Integer.parseInt(mySheet.getSheetName());

			final Iterator<Row> rowIterator = mySheet.iterator();

			rowIterator.next();

			while (rowIterator.hasNext()) {
				final Row row = rowIterator.next();
				final short maxColIx = row.getLastCellNum();

				
				if (maxColIx == 10) {
					final GovernmentBodyAnnualSummary governmentBodyAnnualSummary = new GovernmentBodyAnnualSummary(
							year, row.getCell(0).toString(), getInteger(row.getCell(1).toString()),
							row.getCell(2).toString(), row.getCell(3).toString(), row.getCell(4).toString(),
							row.getCell(5).toString(), getInteger(row.getCell(6).toString()), getInteger(row.getCell(7).toString()),
							row.getCell(8).toString(), row.getCell(9).toString());

					if (name == null || name.equalsIgnoreCase(governmentBodyAnnualSummary.getName())) {
						map.put(year, governmentBodyAnnualSummary);
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.esv.api.EsvApi#getGovernmentBodyNames(java.lang.String)
	 */
	@Override
	public List<String> getGovernmentBodyNames(String ministry) {
		if (!governmentBodyNameSetMinistryMap.containsKey(ministry)) {
			
			final Set<String> governmentBodyNameSetMapEntry = new HashSet<>();
			governmentBodyNameSetMinistryMap.put(ministry, governmentBodyNameSetMapEntry);
			
			final Map<Integer, List<GovernmentBodyAnnualSummary>> data = getData();

			for (final List<GovernmentBodyAnnualSummary> list : data.values()) {
				for (final GovernmentBodyAnnualSummary governmentBodyAnnualSummary : list) {
					if (ministry.equalsIgnoreCase(governmentBodyAnnualSummary.getMinistry()) && !governmentBodyNameSetMapEntry.contains(governmentBodyAnnualSummary.getName()) && governmentBodyAnnualSummary.getHeadCount() > 0) {
						governmentBodyNameSetMapEntry.add(governmentBodyAnnualSummary.getName());
					}
				}
			}
		}
		return new ArrayList<>(governmentBodyNameSetMinistryMap.get(ministry));
	}

}
