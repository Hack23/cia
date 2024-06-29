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
package com.hack23.cia.service.external.esv.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;

/**
 * The Class EsvExcelReaderImpl.
 */
@Component
final class EsvExcelReaderImpl implements EsvExcelReader {

	/** The Constant COMMENT_CELL. */
	private static final int COMMENT_CELL = 12;

	/** The Constant VAT_CELL. */
	private static final int VAT_CELL = 7;

	/** The Constant ANNUAL_HEADCOUNT_CELL. */
	private static final int ANNUAL_HEADCOUNT_CELL = 6;

	/** The Constant HEADCOUNT_CELL. */
	private static final int HEADCOUNT_CELL = 5;

	/** The Constant ORG_NUMBER_CELL. */
	private static final int ORG_NUMBER_CELL = 4;

	/** The Constant MINISTRY_CELL. */
	private static final int MINISTRY_CELL = 3;

	/** The Constant MCODE_CELL. */
	private static final int MCODE_CELL = 2;

	/** The Constant GOVERNMENT_BODY_ID_CELL. */
	private static final int GOVERNMENT_BODY_ID_CELL = 2;

	/** The Constant CONSECUTIVE_NUMBER_CELL. */
	private static final int CONSECUTIVE_NUMBER_CELL = 1;

	/** The Constant NAME_CELL. */
	private static final int NAME_CELL = 0;

	/** The Constant EXPECTED_COLUMN_LENGTH. */
	private static final int EXPECTED_COLUMN_LENGTH = 12;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EsvExcelReaderImpl.class);

	/**
	 * Instantiates a new esv excel reader impl.
	 */
	public EsvExcelReaderImpl() {
		super();
	}

	@Override
	public Map<Integer, List<GovernmentBodyAnnualSummary>> getDataPerMinistry(final String name) {
		final Map<Integer, List<GovernmentBodyAnnualSummary>> map = new TreeMap<>();
		try {
			final HSSFWorkbook myWorkBook = createGovermentBodyWorkBook();

			for (int sheetNr = 0; sheetNr < myWorkBook.getNumberOfSheets(); sheetNr++) {
				addMinistryPerYearToMap(name, map, myWorkBook.getSheetAt(sheetNr));
			}

			myWorkBook.close();
		} catch (final IOException e) {
			LOGGER.warn("Problem loading", e);
		}

		return map;
	}

	private static HSSFWorkbook createGovermentBodyWorkBook() throws IOException {
		return new HSSFWorkbook(EsvExcelReaderImpl.class.getResourceAsStream("/Myndighetsinformation.xls"));
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
	private static void addMinistryPerYearToMap(final String name,
			final Map<Integer, List<GovernmentBodyAnnualSummary>> map, final HSSFSheet mySheet) {
		if (mySheet.getSheetName().chars().allMatch(Character::isDigit)) {

			final int year = Integer.parseInt(mySheet.getSheetName());

			final List<GovernmentBodyAnnualSummary> yearList = new ArrayList<>();
			final Iterator<Row> rowIterator = mySheet.iterator();

			// Skip header row, ignore first
			rowIterator.next();

			while (rowIterator.hasNext()) {
				addGovernmentBodyAnnualSummaryToList(name, year, yearList, rowIterator.next());
			}
			map.put(year, yearList);
		}
	}

	/**
	 * Adds the government body annual summary to list.
	 *
	 * @param name
	 *            the name
	 * @param year
	 *            the year
	 * @param yearList
	 *            the year list
	 * @param row
	 *            the row
	 */
	private static void addGovernmentBodyAnnualSummaryToList(final String name, final int year,
			final List<GovernmentBodyAnnualSummary> yearList, final Row row) {
		if (row.getLastCellNum() >= EXPECTED_COLUMN_LENGTH) {

			final GovernmentBodyAnnualSummary governmentBodyAnnualSummary = createGovernmentBodyAnnualSummaryFromRow(
					year, row);

			if (name == null || name.equalsIgnoreCase(governmentBodyAnnualSummary.getMinistry())) {
				yearList.add(governmentBodyAnnualSummary);
			}
		}
	}

	/**
	 * Gets the integer.
	 *
	 * @param str
	 *            the str
	 * @return the integer
	 */
	private static int getInteger(final String str) {
		if (str == null || str.trim().length() == 0) {
			return 0;
		} else {
			return Integer.parseInt(str);
		}
	}

	@Override
	public Map<Integer, GovernmentBodyAnnualSummary> getDataPerGovernmentBody(final String name) {
		final Map<Integer, GovernmentBodyAnnualSummary> map = new TreeMap<>();
		try {
			final HSSFWorkbook myWorkBook = createGovermentBodyWorkBook();

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
	private static void addDataForYearToMap(final String name, final Map<Integer, GovernmentBodyAnnualSummary> map,
			final HSSFSheet mySheet) {
		if (mySheet.getSheetName().chars().allMatch(Character::isDigit)) {
			final int year = Integer.parseInt(mySheet.getSheetName());
			final Iterator<Row> rowIterator = mySheet.iterator();

			rowIterator.next();

			while (rowIterator.hasNext()) {
				addGovernmentBodyAnnualSummaryToMap(name, map, year, rowIterator.next());
			}
		}
	}

	/**
	 * Adds the government body annual summary to map.
	 *
	 * @param name
	 *            the name
	 * @param map
	 *            the map
	 * @param year
	 *            the year
	 * @param row
	 *            the row
	 */
	private static void addGovernmentBodyAnnualSummaryToMap(final String name, final Map<Integer, GovernmentBodyAnnualSummary> map,
			final int year, final Row row) {
		if (row.getLastCellNum() >= EXPECTED_COLUMN_LENGTH) {

			final GovernmentBodyAnnualSummary governmentBodyAnnualSummary = createGovernmentBodyAnnualSummaryFromRow(
					year, row);
			if (name == null || name.equalsIgnoreCase(governmentBodyAnnualSummary.getName())) {
				map.put(year, governmentBodyAnnualSummary);
			}
		}
	}

	/**
	 * Creates the government body annual summary from row.
	 *
	 * @param year
	 *            the year
	 * @param row
	 *            the row
	 * @return the government body annual summary
	 */
	private static GovernmentBodyAnnualSummary createGovernmentBodyAnnualSummaryFromRow(final int year, final Row row) {
		return new GovernmentBodyAnnualSummary(year, defaultValueIfNull(row.getCell(NAME_CELL)), getInteger(defaultValueIfNull(row.getCell(CONSECUTIVE_NUMBER_CELL))),
				defaultValueIfNull(row.getCell(GOVERNMENT_BODY_ID_CELL)), defaultValueIfNull(row.getCell(MCODE_CELL)), defaultValueIfNull(row.getCell(MINISTRY_CELL)),
				defaultValueIfNull(row.getCell(ORG_NUMBER_CELL)), getInteger(defaultValueIfNull(row.getCell(HEADCOUNT_CELL))), getInteger(defaultValueIfNull(row.getCell(ANNUAL_HEADCOUNT_CELL))),
				defaultValueIfNull(row.getCell(VAT_CELL)), defaultValueIfNull(row.getCell(COMMENT_CELL)));
	}

	/**
	 * Default value if null.
	 *
	 * @param cell
	 *            the cell
	 * @return the string
	 */
	private static String defaultValueIfNull(final Cell cell) {
		if (cell != null) {
			return cell.toString();
		} else {
			return "";
		}
	}

}
